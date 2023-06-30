<template>
	<div>
		<div class="main">
			<h3>频道广场</h3>
			<div class="data" :style="{height:dataHeight+'px'}">
				<ul>
					<li v-for="(data,index) in datas" @click="shoppingClick(data.id)" :key="index" ref="liHeight"
						:style="{height: getLiWidth + 'px'}">
						<a class="data_div">
							<img :src="'src/assets/img/shoppings/'+data.img"
								:style="{height: (getLiWidth<=109?(getLiWidth+35)*0.625:getLiWidth*0.625) + 'px'}"
								alt="">
							<a class="title">
								<span id="name">{{data.name}}</span>
								<span id="price" v-show="getLiWidth>=109">￥{{data.price}}</span>
							</a>
						</a>
					</li>
				</ul>
			</div>
			<div class="bottom">
				<span v-show="loading">{{getData}}</span>
				<span v-show="!loading" v-loading="!loading" element-loading-background="rgba(0, 0, 0, 0)"></span>
			</div>
		</div>
		<div class="shopping_show" @click.self="closeInfo" v-show="showShoppingInfo">
			<div class="shopping_show_div">
				<div class="small_btn">
					<div class="shopping_div shopping_div_img">
						<img :src="'src/assets/img/shoppings/'+shoppingInfo.img" alt="">
					</div>
					<div class="shopping_div shopping_div_info">
						<div class="info">
							<p>
							<h1>{{shoppingInfo.name}}</h1>
							</p>
							<p>{{shoppingInfo.information}}</p>
							<p>￥{{shoppingInfo.price}}</p>
							<p>
								<span>
									<button class="count" @click="reduceCount">-</button>
								</span>
								<span class="number">{{count}}</span>
								<span>
									<button class="count" @click="addCount">+</button>
								</span>
							</p>
							<p>
								<span>
									<button class="add_btn" @click="addShopping">添加到购物车</button>
								</span>
							</p>
						</div>
					</div>
					<div class="close_btn">
						<Close @click="closeInfo" :color="'#000'" :size="50" />
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		watch,
		onMounted,
		onBeforeUnmount
	} from 'vue'
	import {
		goodsShow,
		getGoodsInfo,
		addGoodsInShopping,
		getShopping
	} from '../../../api/axiosApi.js'
	import {
		userShoppingTotalStore
	} from '../../../store/shoppingTotal.js'
	import {
		ElMessage
	} from 'element-plus'
	const shoppingTotal = userShoppingTotalStore();
	const loading = ref(false); // 数据加载中的动画
	const get = ref(true); // 用于限制请求，只能请求完上一次之后再进行下一次请求
	const datas = ref([]); // 存放初始数据
	const page = ref(1); // 用于存放商品页码
	const dataHeight = ref(0); // 底部放数据的盒子的高度
	const liHeight = ref(0); // 绑定数据里面的li，用于获取li的宽度
	const getLiWidth = ref(0); // 获取li标签的宽度来设置img的宽度
	const getData = ref(""); // 展示有没有到底
	const count = ref(1); // 存放商品数量
	const showShoppingInfo = ref(false) // 是否显示商品展示div
	// 存放商品信息
	const shoppingInfo = ref([{
		createTime: "2023-06-10T22:15:23",
		id: 1,
		img: "1.jpg",
		information: "性能好",
		goodsName: "华为",
		goodsPrice: 99,
		updateTime: "2023-06-10T22:15:27"
	}]);
	// 点击商品li展示商品信息
	const shoppingClick = (id) => {
		getGoodsInfo(id).then(data => {
			if (data.data.code !== 0) {
				showShoppingInfo.value = true;
				shoppingInfo.value = data.data.data
			}
		}).catch(error => {
			console.log(error);
		})
	}
	// 关闭商品展示
	const closeInfo = () => {
		showShoppingInfo.value = false
		count.value = 1
	}
	// 减少商品数量
	const reduceCount = () => {
		if (count.value > 1) {
			count.value--
		}
	}
	// 增加商品数量
	const addCount = () => {
		if (count.value < 999) {
			count.value++
		}
	}

	// 添加进购物车
	const addShopping = () => {
		addGoodsInShopping(shoppingInfo.value.id, count.value).then(data => {
			closeInfo();
			if (data.data.code !== 0) {
				const username = localStorage.getItem("username")
				getShopping(username).then(data => {
					shoppingTotal.setShoppingTotal(data.data.data.length);
					ElMessage({
						type: 'success',
						message: '添加成功',
					})
				}).catch(error => {
					console.log(error);
				})
			}
		}).catch(error => {
			console.log(error);
		})
	}
	//获取当前可视范围的宽度
	const getClientWidth = function() {
		if (liHeight.value != 0) {
			getLiWidth.value = liHeight.value[0].clientWidth;
			if (datas.value.length != 0) {
				dataHeight.value = (getLiWidth.value + 20) * (Math.floor((datas.value.length / 5)) + (datas
					.value.length % 5 == 0 ? 0 : 1));
			} else {
				dataHeight.value = getLiWidth.value + 40;
			}
		}
	};
	//获取当前可视范围的高度
	const getClientHeight = function() {
		var clientHeight = 0;
		if (document.body.clientHeight && document.documentElement.clientHeight) {
			clientHeight = Math.min(document.body.clientHeight, document.documentElement.clientHeight)
		} else {
			clientHeight = Math.max(document.body.clientHeight, document.documentElement.clientHeight)
		}
		return clientHeight
	};
	//获取文档完整的高度
	const getScrollHeight = function() {
		return Math.max(document.body.scrollHeight, document.documentElement.scrollHeight)
	};
	//获取当前滚动条的位置
	const getScrollTop = function() {
		var scrollTop = 0;
		//window.pageYOffset = document.documentElement.scrollTop
		if (document.documentElement && document.documentElement.scrollTop) {
			scrollTop = document.documentElement.scrollTop
		} else if (document.body) {
			scrollTop = document.body.scrollTop
		}
		return scrollTop
	}
	// 监视商品的li有没有增加
	watch(liHeight, () => {
		getClientWidth();
	})
	//回调函数
	const windowScroll = () => {
		//获取三个值
		var scrollTop = getScrollTop();
		var clientHeight = getClientHeight();
		var scrollHeight = getScrollHeight();
		// 判断滚动条往上滚了
		if((scrollHeight - scrollTop - clientHeight) > 30){
			loading.value = false
		}
		// 判断页面有没有li
		if (liHeight.value.length == 0) { // 没有
			setTimeout(() => {
				loading.value = true;
				getData.value = "暂无数据";
			}, 1000)
		} else if (!loading.value) { // 有，判断有没有到最后一个
			//如果满足公式则表示滚动条到达了底部
			if ((scrollHeight - scrollTop - clientHeight) <= 30 & get.value) {
				getData.value = "";
				get.value = false;
				goodsShow(page.value++, 5).then(data => {
					get.value = true;
					if (data.data.data.length !== 0 || data.data.data.length >= 5) {
						datas.value = datas.value.concat(data.data.data);
						if (liHeight.value !== 0) {
							getLiWidth.value = liHeight.value[0].clientWidth;
							dataHeight.value = (getLiWidth.value + 20) * (Math.floor((datas.value.length /
								5)) + (
								datas.value.length % 5 == 0 ? 0 : 1));
						}
						if (data.data.data.length < 5) {
							loading.value = true
							getData.value = "已经到底了";
						}
					} else {
						loading.value = true
						getData.value = "已经到底了";
					}
				}).catch(error => {
					ElMessage.error(error.message);
					loading.value = true
					getData.value = "网络错误/服务器未启动！！";
					get.value = true;
					// setTimeout(() => {
					// 	get.value = true;
						// loading.value = false
						// getData.value = "";
					// }, 1000)
				});
			}
		}
	}
	onMounted(() => {
		window.addEventListener('scroll', windowScroll, true) //监听页面滚动
		window.addEventListener('resize', getClientWidth) //监听页面宽度
	})
	onBeforeUnmount(() => {
		window.removeEventListener("scroll", windowScroll); //销毁滚动事件
		window.removeEventListener('resize', getClientWidth) //销毁获取页面宽度事件
	})
</script>

<style scoped>
	.main {
		width: 100%;
		margin: 10px auto;
		height: 100%;
		margin-bottom: 50px;
	}

	.main h3 {
		color: #000000;
		font-size: 28px;
		margin-bottom: 30px;
	}

	.data {
		width: 90%;
		background-color: #d9d9d9;
		margin: 0px auto;
		border-radius: 10px;
		padding-bottom: 30px;
	}

	.data_div {
		display: block;
		width: 100%;
		height: 100%;
		background-color: #fff;
		border-radius: 10px;
		overflow: hidden;
	}

	li {
		width: 18%;
		float: left;
		margin-left: 1.6%;
		margin-top: 20px;
	}

	img {
		margin-top: 8%;
	}

	.title {
		display: block;
		width: 100%;
		height: 20%;
		padding-top: 8%;
	}

	#name {
		color: black;
	}

	#price {
		margin: 0px 3px 0px 3px;
		color: red;
	}

	.bottom {
		width: 100%;
		float: left;
		height: 40px;
		color: #000000;
		text-align: center;
		position: relative;
	}

	.bottom>span {
		display: block;
		width: 100%;
		height: 100%;
		color: black;
	}

	.shopping_show {
		position: fixed;
		width: 100%;
		height: 100%;
		background-color: black;
		background: rgba(0, 0, 0, 0.8);
		top: 0;
		bottom: 0;
		left: 0;
		right: 0;
		z-index: 999999;
	}

	.shopping_show_div {
		position: fixed;
		width: 800px;
		height: 400px;
		background-color: #f7f7f7;
		bottom: 50%;
		margin-bottom: -200px;
		left: 50%;
		margin-left: -400px;
		z-index: 999999;
		border-radius: 5px;
	}

	.small_btn {
		width: 100%;
		height: 100%;
		margin: auto auto;
	}

	.shopping_div {
		float: left;
		height: 100%;
	}

	.shopping_div_img {
		width: 50%;
	}

	.shopping_div_info {
		width: 45%;
	}

	.shopping_div img {
		width: 80%;
		margin-top: 10%;
	}

	.shopping_div p {
		height: 15%;
		margin-top: 10px;
	}

	.info {
		width: 60%;
		height: 70%;
		margin-top: 18%;
		text-align: left;
	}

	.info p {
		color: red;
	}

	.number {
		margin: 10px;
		font-size: 20px;
		font-weight: 700px;
	}

	.count {
		width: 50px;
		height: 26px;
		border-radius: 10px;
		border: 1px solid #bbadad;
		font-size: 20px;
		cursor: pointer;
	}

	.add_btn {
		width: 180px;
		height: 50px;
		border-radius: 10px;
		background-color: #ff0000;
		color: #fff;
		border: 0;
		font-size: 24px;
		font-family: cursive;
	}

	.close_btn {
		float: right;
		width: 30px;
		height: 30px;
	}
</style>