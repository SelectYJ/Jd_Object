<template>
	<div class="top_div">
		<div class="location" v-show="showLocation==1?true:false" @mouseover="showLocation=1"
			@mouseout="showLocation=0">
			<ul>
				<li v-for="(location,index) in locations" :key="index" @click="clickSheng(location)">
					<a class="location_a"
						:style="{color:location==nowLocation?'#fff':'',backgroundColor:location==nowLocation?'red':''}">{{location}}</a>
				</li>
			</ul>
		</div>
		<div id="float_right_div">
			<ul>
				<li class="location_sheng" @mouseover="showLocation=1" @mouseout="showLocation=0"
					:class="{location_bg:showLocation==1}">
					<LocationInformation class="location_img" />
					<a :class="{location_bg:showLocation==1}">{{nowLocation}}</a>
				</li>
				<li class="header_top maxRight"><a>网站无障碍</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>手机京东</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>网站导航</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>商家服务</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>企业采购</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>我的京东</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top"><a>我的订单</a></li>
				<li class="header_top spacer"></li>
				<li class="header_top">
					<router-link v-show="!userInfo" to="/LoginAndRegister/Register">
						<a href="#" class="reg">免费注册</a>
					</router-link>
					<a v-show="userInfo" class="reg" @click.prevent="logOut">退出账号</a>
				</li>
				<li class="header_top spacer"></li>
				<li class="header_top">
					<router-link v-show="!userInfo" to="/LoginAndRegister">
						你好，请登录
					</router-link>
					<router-link v-show="userInfo" to="/UserInfo">
						<div>
							<el-avatar :size="30"
								src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png" />
							<span>{{userInfo}}</span>
						</div>
					</router-link>
				</li>
			</ul>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		onMounted
	} from 'vue'
	import {
		ElMessage,
		ElMessageBox
	} from 'element-plus'
	import {
		getUserInfo,
		searchShopping
	} from "../../../api/axiosApi.js"
	import {
		userShoppingTotalStore
	} from '../../../store/shoppingTotal.js'
	const shoppingTotal = userShoppingTotalStore(); // 用于修改当前用户账号的购物车数量
	const nowLocation = ref("江西"); // 存放所在的地址
	const showLocation = ref(0); // 是否显示地址选择的div
	const locations = ref(["北京", "上海", "天津", "重庆", "河北", "山西", "河南", "辽宁", "吉林", "黑龙江", "江西"]);
	// 点击地址换地址
	const clickSheng = (loc) => {
		nowLocation.value = loc;
		showLocation.value = 0;
	}
	// 退出登录
	const logOut = () => {
		ElMessageBox.confirm(
			'确认退出登录吗？', {
				confirmButtonText: '确认',
				cancelButtonText: '取消',
				type: 'warning',
			}).then(() => {
			// 退出登录，删除浏览器存放的用户信息
			localStorage.removeItem("token");
			localStorage.removeItem("username");
			localStorage.removeItem("name");
			location.href = '/LoginAndRegister'
		}).catch(() => {});
	}
	// 存放用户名
	const userInfo = ref();
	onMounted(() => {
		// 获取浏览器存放的用户名
		const username = localStorage.getItem("username")
		// 判断浏览器里有没有存放用户名
		if (username !== null) {
			// 访问后端判断token
			getUserInfo(username).then(data => {
				// 返回为1，则token没过期
				if (data.data.code !== 0) {
					// token没过期，表示当前用户不用重新登录
					userInfo.value = localStorage.getItem("username");
					// 则访问后端获取购物车的数量显示到页面
					searchShopping().then(data => {
						if (data.data.data !== null) {
							// 把购物车上面的数字显示为当前用户的购物车数量
							shoppingTotal.setShoppingTotal(data.data.data.length);
						} else {
							shoppingTotal.setShoppingTotal(0);
						}
					}).catch(error => {
						console.log(error.message);
					})
				} else {
					// 返回为0，则token过期，删除用户所有存在的用户信息
					localStorage.removeItem("token");
					localStorage.removeItem("username");
					localStorage.removeItem("name");
				}
			}).catch(error => {
				console.log(error.message);
			});
		} else {
			// username为null，则删除用户所有存在的用户信息
			localStorage.removeItem("token");
			localStorage.removeItem("name");
		}
	})
</script>

<style scoped>
	.top_div{
		position: fixed;
		left: 0;
		background-color: #dedede;
		width: 100%;
		z-index: 99;
	}
	#float_right_div {
		overflow: hidden;
		width: 1450px;
	}

	.maxRight {
		margin-right: 180px;
	}

	.header_top {
		float: right;
		margin-left: 10px;
		color: #989898;
		font-size: 12px;
		cursor: pointer;
	}

	.el-avatar {
		float: left;
		margin-right: 5px;
	}

	.reg {
		color: red;
	}

	.location {
		width: 300px;
		position: absolute;
		background-color: #ffffff;
		z-index: 999999;
		top: 30px;
		left: 164px;
		border-right: 1px solid #ebebeb;
		border-bottom: 1px solid #ebebeb;
		border-left: 1px solid #ebebeb;
		padding: 10px;
	}

	.location li {
		width: 20%;
		height: 30px;
		float: left;
		font-size: 12px;
		text-align: center;
	}

	.location a:hover {
		background-color: #cccccc;
	}

	.location_a {
		display: block;
		width: 40px;
		height: 25px;
		color: #9c9c9c;
		text-align: center;
		line-height: 25px;
	}

	.location_bg {
		background-color: #ffffff;
		color: #ff0004;
	}

	.spacer {
		margin: 8px 5px 0px 10px;
		width: 1px;
		height: 15px;
		background-color: #ccc;
	}

	.location_img {
		display: block;
		width: 15px;
		color: red;
		margin-top: 6px;
		margin-left: 5px;
		float: left;
	}

	.location_sheng {
		position: absolute;
		margin-left: 165px;
		color: #989898;
		font-size: 12px;
		width: 60px;
		text-align: center;
	}

	.location_sheng a {
		float: left;
	}
</style>