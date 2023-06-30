<template>
	<div>
		<img src="../../assets/img/bg_img/2.jpg" alt="">
		<div class="content">
			<div class="header">
				<el-row class="row-bg" justify="space-evenly">
					<el-col :span="10">
						<span class="title_top">
							<router-link to="/">
								<el-button link>
									<el-icon>
										<ArrowLeft />
									</el-icon>
									返回首页
								</el-button>
							</router-link>
						</span>
					</el-col>
					<el-col :span="14">
						<div class="title_div">
							<router-link to="/UserInfo">
								<h2 class="title_text username_title">{{username}}</h2>
							</router-link>
							<span class="title_text">{{titleUsername}}</span>
						</div>
					</el-col>
				</el-row>
			</div>
			<el-button-group>
				<router-link to="/ShoppingCar/NowShoppingCar" @click="disabled=true">
					<el-button class="btn_left" type="primary" :disabled="disabled">
						<el-icon class="el-icon--right">
							<ArrowLeft />
						</el-icon>
						购物车
					</el-button>
				</router-link>
				<router-link to="/ShoppingCar/BeforeShoppingCar" @click="disabled=false">
					<el-button class="btn_right" type="primary" :disabled="!disabled">
						已结算
						<el-icon class="el-icon--right">
							<ArrowRight />
						</el-icon>
					</el-button>
				</router-link>
			</el-button-group>
			<div class="shoppings">
				<router-view></router-view>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		onMounted,
		onBeforeUpdate,
		watch
	} from 'vue'
	const disabled = ref(true);
	const username = localStorage.getItem('username');
	const titleUsername = ref('的购物车');
	watch(disabled, () => {
		titleUsername.value = !disabled.value ? '已结算的商品信息' : '的购物车';
	})
	const updateIndex = () => {
		const href = location.href.split('/');
		if (href[href.length - 1] !== 'BeforeShoppingCar') {
			disabled.value = true
		} else {
			disabled.value = false
		}
	}
	onMounted(() => {
		updateIndex()
	})
	onBeforeUpdate(() => {
		updateIndex()
	})
</script>
<style scoped>
	a {
		text-decoration: none;
		color: #000000;
	}

	img {
		position: absolute;
		width: 100%;
		height: 100%;
		min-height: 750px;
		min-width: 1202px;
		left: 0;
		right: 0;
		top: 0;
	}

	.content {
		width: 1200px;
		position: absolute;
		margin-left: -600px;
		top: 30px;
		left: 50%;
		border-radius: 10px;
		border: 1px solid #000;
		overflow: hidden;
		background-color: #fff;
		box-shadow: 10px 10px 5px -4px rgba(0, 0, 0, 0.8);
	}

	.header {
		width: 80%;
		height: 50px;
		line-height: 50px;
		margin: 0px auto;
	}

	.title_top {
		float: right;
	}

	.title_div {
		color: #000;
	}

	.title_text {
		float: left;
		margin-left: 10px;
	}

	.username_title {
		width: 200px;
		overflow: hidden;
		white-space: nowrap;
		text-overflow: ellipsis;
		text-align: center;
	}

	.el-button-group {
		width: 100%;
		border-top: 1px solid #e0d9d9;
	}

	.el-button {
		width: 100px;
		height: 100%;
	}

	.btn_left {
		border-top-right-radius: 0px;
		border-bottom-right-radius: 0px;
		border-bottom-left-radius: 0px;
	}

	.btn_right {
		border-top-left-radius: 0px;
		border-bottom-left-radius: 0px;
		border-bottom-right-radius: 0px;
	}

	.shoppings {
		position: relative;
		width: 100%;
		float: left;
	}
</style>