<template>
	<div class="login">
		<h1 class="login_title">用户登录</h1>
		<el-form :model="loginFormData" label-width="80px" :rules="rules" ref="loginForm" class="item">
			<el-form-item label="账号名" prop="name">
				<el-input type="text" v-model="loginFormData.name" autocomplete="off" size="default"></el-input>
			</el-form-item>
			<el-form-item label="密&nbsp;&nbsp;&nbsp;码" prop="password">
				<el-input @keyup.enter="login_confirm" type="password" v-model="loginFormData.password" show-password
					autocomplete="off" size="default"></el-input>
			</el-form-item>
			<!-- <el-form-item> -->
			<el-button type="primary" @click="login_confirm">登 录</el-button>
			<!-- </el-form-item> -->
		</el-form>
	</div>
</template>

<script setup>
	import {
		ref
	} from 'vue'
	import {
		useRouter
	} from 'vue-router'
	import {
		login
	} from '../../api/axiosApi.js'
	// 登录相关属性
	const loginFormData = ref({
		name: '',
		password: ''
	});
	// 关联表单
	const loginForm = ref();
	// 获取路由
	const router = useRouter();
	// 点击登录按钮
	const login_confirm = () => {
		loginForm.value.validate((valid) => {
			if (valid) { //valid成功为true，失败为false
				login({
					"username": loginFormData.value.name,
					"password": loginFormData.value.password,
				}).then((data) => {
					if (data.data.code === 1) {
						localStorage.setItem('username', loginFormData.value.name)
						router.push({
							path: '/'
						})
					}
					// loginForm.value.resetFields(); // 清空表单项
				}).catch((err) => {
					console.log(err);
				})
			} else {
				return false;
			}
		});
	}
	// 定义表单校验规则
	const rules = ref({
		name: [{
				required: true,
				message: '请输入用户名',
				trigger: 'blur'
			},
			{
				min: 2,
				max: 20,
				message: '用户名长度在 2 到 20 个字符',
				trigger: 'blur'
			}
		],
		password: [{
				required: true,
				message: '请输密码',
				trigger: 'blur'
			},
			{
				min: 2,
				max: 20,
				message: '密码长度在 2 到 20 个字符',
				trigger: 'blur'
			}
		]
	})
</script>

<style scoped>
	.login {
		width: 50%;
		height: 100%;
		float: left;
	}

	.el-input {
		width: 200px;
		height: 30px;
	}

	.el-form-item {
		margin-left: 0px;
		margin-top: 30px;
	}

	.login_title {
		text-align: center;
		margin-top: 23%;
	}

	.el-button {
		width: 20%;
		margin-left: 40%;
	}
</style>