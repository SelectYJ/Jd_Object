<template>
	<el-container>
		<el-aside width="200px">
			<div class="main main_div_img">
				<img class="main_img" src="../../../assets/img/main_bottom_1.jpg">
			</div>
		</el-aside>
		<el-aside width="800px">
			<div style="width: 100%;height: 100%;text-align: center;position: relative;">
				<div class="main main_input_btn">
					<div class="main_input">
						<input placeholder="123" type="text" />
						<button>
							<Search class="search" />
						</button>
						<Camera class="Camera" />
					</div>
					<div class="main_shopping">
						<el-button size="small" @click="MyShopping">
							<el-badge :value="shoppingTotal.shoppingTotal" class="item">
								<ShoppingTrolley style="width: 18px;height: 18px;color: red;font-weight: 700;" />
							</el-badge>
							我的购物车
						</el-button>
					</div>
				</div>
				<div class="main main_a">
					<a class="main_a_a" style="color: red;">121212</a>
					<a class="main_a_a">iPhone14</a>
					<a class="main_a_a">电脑预售</a>
					<a class="main_a_a">低价好物</a>
					<a class="main_a_a">盖狮果泥</a>
					<a class="main_a_a">品质装修</a>
					<a class="main_a_a">定金加赠</a>
					<a class="main_a_a">粮油618</a>
					<a class="main_a_a">健康预售</a>
				</div>
				<div class="main" id="mian_router">
					<ul>
						<li><a>京东超市</a></li>
						<li><a>秒杀</a></li>
						<li><a>京东家电</a></li>
						<li><a>京东生鲜</a></li>
						<li><a>拍卖</a></li>
						<li><a>PLUS会员</a></li>
						<li><a>便宜包邮</a></li>
						<li><a>进口好物</a></li>
						<li><a>电脑数码</a></li>
						<li><a>京东五金城</a></li>
					</ul>
				</div>
			</div>
		</el-aside>
		<el-aside width="200px">
			<div class="main main_left">
				<img src="../../../assets/img/left.png" />
			</div>
		</el-aside>
	</el-container>
</template>

<script setup>
	import {
		userShoppingTotalStore
	} from '../../../store/shoppingTotal.js'
	import {
		ElMessageBox
	} from 'element-plus'
	const shoppingTotal = userShoppingTotalStore(); // 用于获取当前用户账号的购物车数量显示到页面
	// 点击我的购物车按钮
	const MyShopping = () => {
		const username = localStorage.getItem("token");
		if (username) {
			location.href = '/ShoppingCar'
		} else {
			ElMessageBox.confirm(
				'请先登录！！', {
					confirmButtonText: '登录',
					cancelButtonText: '取消',
					type: 'warning',
				}).then(() => {
				location.href = '/LoginAndRegister'
			}).catch(() => {});
		}
	}
</script>

<style scoped>
	.main_div_img {
		margin-top: 10px;
	}

	.main_img {
		width: 190px;
		height: 120px;
	}

	.main_input_btn {
		width: 90%;
		height: 70px;
		margin-left: 5%;
	}

	.main_input {
		float: left;
		width: 75%;
		height: 33px;
		border-radius: 2px;
		border: 2px solid #ff0004;
		margin-left: 4%;
		margin-top: 25px;
	}

	.main_shopping {
		float: left;
		width: 18%;
		height: 36px;
		margin-top: 25px;
		margin-left: 10px;
	}

	.el-button {
		width: 90%;
		height: 36px;
	}

	.el-button:hover {
		color: red;
		background-color: #fff;
		border: 1px solid red;
	}

	.el-badge {
		margin-right: 10px;
	}

	.Camera {
		float: right;
		width: 15px;
		height: 15px;
		margin-top: 10px;
		margin-right: 10px;
	}

	.main_input>input {
		width: 80%;
		height: 100%;
		float: left;
		border: 0px;
		padding-left: 10px;
	}

	.main_input>input:focus {
		border: none;
		outline: none;
		box-shadow: 0px 0px 3px #fefefe inset;
	}

	.main_input>button {
		width: 10%;
		height: 100%;
		float: right;
		border: 0px;
		background-color: #ff0004;
	}

	.search {
		width: 20px;
		margin-top: 5px;
	}

	.main_a {
		width: 60%;
		height: 20px;
		margin-left: 8%;
	}

	.main_a>a {
		float: left;
		font-size: 12px;
		color: #989898;
		cursor: pointer;
		margin-left: 5px;
	}

	.main_a_a:hover {
		color: red;
	}

	.main_left {}

	.main_left>img {
		width: 190px;
		height: 120px;
		margin-top: 10px;
	}

	#mian_router {
		width: 100%;
		height: 30px;
		position: absolute;
		left: 0px;
		bottom: 0px;
	}

	#mian_router a {
		height: 20px;
		float: left;
		font-size: 15px;
		margin-left: 20px;
		margin-top: 5px;
	}
</style>