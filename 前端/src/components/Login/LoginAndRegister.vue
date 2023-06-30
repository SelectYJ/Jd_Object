<template>
	<div>
		<img src="../../assets/img/bg_img/2.jpg">
		<div class="main">
			<div class="content">
				<transition name="el-fade-in-linear">
					<el-row justify="space-evenly" align="middle" v-show="!ifShow" class="btn transition-box">
						<el-col :span="6">
							<router-link to="/LoginAndRegister/Login">
								<el-button type="primary" @click="btn">登录</el-button>
							</router-link>
						</el-col>
					</el-row>
				</transition>
				<router-view :style="{opacity:isOpacity}"></router-view>
				<transition name="el-fade-in">
					<el-row justify="space-evenly" align="middle" v-show="ifShow" class="btn transition-box btn_register">
						<el-col :span="6">
							<router-link to="/LoginAndRegister/Register">
								<el-button type="primary" @click="btn">普通注册</el-button>
							</router-link>
							<router-link class="emailBtn" to="/LoginAndRegister/RegisterByEmail">
								<el-button type="primary" @click="btn">邮箱注册</el-button>
							</router-link>
						</el-col>
					</el-row>
				</transition>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		watch,
		onBeforeUpdate
	} from 'vue'
	const timer = ref(); // 用于存储定时器，方便关闭上一次开启的定时器
	const isOpacity = ref(0); // 定义改变透明度的属性
	const ifShow = ref(true); // 定义存储登录、注册两个按钮是否显示
	const href = location.href.split('/'); // 获取当前页面路由最后一个斜杠右边的字符串，判断登录/注册
	// 开启定时器
	timer.value = setInterval(() => {
		isOpacity.value += 0.01;
	}, 10)
	// 判断当前网页路径是不是登录/注册
	if (href[href.length - 1] == "Login") {
		ifShow.value = true;
	} else {
		ifShow.value = false;
	}
	// 点击 登录/注册 按钮调用的函数
	const btn = () => {
		// 点击后把isOpacity属性的值归零
		clearInterval(timer.value);
		isOpacity.value = 0;
		timer.value = setInterval(() => {
			isOpacity.value += 0.01;
		}, 10)
		ifShow.value = !ifShow.value
	}
	// 监视isOpacity属性
	watch(isOpacity, (newValue) => {
		// 判断isOpacity属性有没有到达 1
		if (newValue >= 1) {
			clearInterval(timer.value);
		}
	})
	// 每次跳转判断路由是登录还是注册
	onBeforeUpdate(()=>{
		const href = location.href.split('/');
		if(href[href.length-1]==='Login'){
			ifShow.value = true
		}else{
			ifShow.value = false
		}
	})
	
</script>

<style scoped>
	#app {
		position: relative;
		width: 100%;
		height: 100%;
	}

	img {
		position: absolute;
		width: 100%;
		height: 100%;
		left: 0;
		right: 0;
		top: 0;
	}

	.main {
		position: absolute;
		width: 100%;
		height: 100%;
		left: 0;
		right: 0;
		bottom: 0;
	}

	.content {
		position: absolute;
		width: 600px;
		height: 380px;
		left: 50%;
		top: 40%;
		margin-left: -300px;
		margin-top: -180px;
		box-shadow: 0 0 12px 0px #373737;
		border-radius: 10px;
	}

	.el-row {
		height: 100%;
	}

	.btn {
		position: absolute;
		width: 50%;
		height: 100%;
	}

	.btn_register {
		right: 0;
	}

	.el-button {
		width: 100%;
	}
	.emailBtn{
		display: block;
		margin-top: 60px;
	}
</style>