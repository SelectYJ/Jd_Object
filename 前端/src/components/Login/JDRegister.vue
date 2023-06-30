<template>
	<div class="register">
		<h1 class="register_title">用户注册</h1>
		<el-form :model="registerFormData" label-width="80px" :rules="rules" ref="registerForm" class="item">
			<el-form-item label="名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;字" prop="name">
				<el-input type="text" v-model="registerFormData.name" autocomplete="off" size="default"></el-input>
			</el-form-item>
			<el-form-item label="账&nbsp;&nbsp;号&nbsp;&nbsp;名" prop="username">
				<el-input type="text" v-model="registerFormData.username" autocomplete="off" size="default"></el-input>
			</el-form-item>
			<el-form-item label="性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别" prop="sex">
				<el-radio-group v-model="registerFormData.sex">
					<el-radio label="男" />
					<el-radio label="女" />
				</el-radio-group>
			</el-form-item>
			<el-form-item label="密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码" prop="password">
				<el-input type="password" v-model="registerFormData.password" show-password autocomplete="off"
					size="default"></el-input>
			</el-form-item>
			<el-form-item label="确认密码" prop="makePassword">
				<el-input type="makePassword" @keyup.enter="register_confirm" v-model="registerFormData.makePassword"
					show-password autocomplete="off" size="default"></el-input>
			</el-form-item>
			<el-button type="primary" @click="register_confirm">注 册</el-button>

			<router-link class="email_register" to="/LoginAndRegister/RegisterByEmail">
				邮箱注册
			</router-link>
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
		ElMessage
	} from 'element-plus'
	import {
		register
	} from '../../api/axiosApi.js'
	// 注册包含的相关属性
	const registerFormData = ref({
		name: '',
		username: '',
		sex: '',
		password: '',
		makePassword: ''
	});
	// 用于判断输入是否满足所有要求
	const registerForm = ref();
	// 获取路由
	const router = useRouter();
	// 点击注册按钮
	const register_confirm = () => {
		registerForm.value.validate((valid) => {
			if (valid) { //valid成功为true，失败为false
				register(registerFormData.value).then(data => {
					if (data.data.code === 1) {
						ElMessage.success("注册成功")
						router.push({
							path: '/LoginAndRegister/Login'
						})
					}
				}).catch(error => {
					console.log(error);
				})
			} else {
				console.log('校验失败');
				return false;
			}
		});
	}
	// 确认密码的校验规则
	const validatePass2 = (rule, value, callback) => {
		if (value === '') {
			callback(new Error('请再次输入密码'))
		} else if (value !== registerFormData.value.password) {
			callback(new Error('两次输入密码不一致!'))
		} else {
			callback()
		}
	}
	// 定义表单校验规则
	const rules = ref({
		name: [{
				required: true,
				message: '请输入用户姓名',
				trigger: 'blur'
			},
			{
				min: 2,
				max: 20,
				message: '用户名长度在 2 到 20 个字符',
				trigger: 'blur'
			}
		],
		username: [{
				required: true,
				message: '请输入账户名',
				trigger: 'blur'
			},
			{
				min: 2,
				max: 20,
				message: '账户名长度在 2 到 20 个字符',
				trigger: 'blur'
			}
		],
		sex: [{
			required: true,
			message: '请选择性别',
			trigger: 'change'
		}],
		password: [{
				required: true,
				message: '请输密码',
				trigger: 'blur'
			},
			{
				min: 3,
				max: 20,
				message: '密码长度在 3 到 6 个字符',
				trigger: 'blur'
			}
		],
		makePassword: [{
			required: true,
			validator: validatePass2,
			trigger: 'blur'
		}]
	})
</script>

<style scoped>
	.register {
		width: 50%;
		height: 100%;
		float: right;
	}

	.el-input {
		width: 200px;
		height: 30px;
	}

	.register_title {
		text-align: center;
		margin: 15px;
	}

	.el-button {
		width: 20%;
		margin-left: 40%;
	}

	.email_register {
		font-size: 15px;
		margin: 25px;
		color: #aaffff;
		font-weight: 500;
	}
</style>