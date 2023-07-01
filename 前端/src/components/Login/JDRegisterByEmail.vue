<template>
	<div class="register">
		<h1 class="register_title">邮箱注册</h1>
		<el-form :model="registerFormData" label-width="10%" :rules="rules" ref="registerByEmailForm">
			<el-form-item prop="emailPath">
				<el-input placeholder="请输入邮箱地址" type="text" v-model="registerFormData.emailPath"
					size="default"></el-input>
			</el-form-item>
			<el-form-item prop="emailCode">
				<el-input placeholder="请输入验证码" type="text" v-model="registerFormData.emailCode" size="default">
					<template #append>
						<a class="yzm" @click="sendEmailCode">获取验证码</a>
					</template>
				</el-input>
			</el-form-item>
			<el-button type="primary" @click="register_email">注 册</el-button>
			<router-link class="email_register" to="/LoginAndRegister/Register">
				普通注册
			</router-link>
		</el-form>
	</div>
</template>

<script setup>
	import {
		ref,
		reactive
	} from 'vue'
	import {
		useRouter
	} from 'vue-router'
	import {
		ElMessage
	} from 'element-plus'
	import {
		register,
		sendCodeByEmail,
		codeIsOk
	} from '../../api/axiosApi.js'
	// 获取路由
	const router = useRouter();
	// 用于判断输入是否满足所有要求
	const registerByEmailForm = ref();
	// 注册包含的相关属性
	const registerFormData = reactive({
		emailPath: '',
		emailCode: ''
	});
	// 定义表单校验规则
	const rules = reactive({
		emailPath: [{
				required: true,
				message: '请输入邮箱地址！！',
				trigger: ['blur', 'change'],
			},
			{
				type: 'email',
				message: '请输入正确的邮箱地址！！',
				trigger: ['blur', 'change'],
			},
		]
	})
	// 发送验证码
	const sendEmailCode = () => {
		registerByEmailForm.value.validate((valid) => {
			if (valid) { //valid成功为true，失败为false
				// 向用户邮箱发送验证码
				sendCodeByEmail(registerFormData.emailPath).then(data => {
					if (data.data.code === 1) {
						ElMessage.success(data.data.data)
					}
				}).catch()
			} else {
				console.log('校验失败');
				return false;
			}
		});
	}
	// 点击邮箱注册
	const register_email = () => {
		registerByEmailForm.value.validate((valid) => {
			if (valid) { //valid成功为true，失败为false
				if (registerFormData.emailCode !== '') {
					// 向后端发送用户输入的验证码判断是否正确
					codeIsOk(registerFormData.emailCode).then(data => {
						if (data.data.code === 1) {
							// 正确则注册用户信息
							register(registerFormData).then(data => {
								if (data.data.code === 1) {
									ElMessage.success("注册成功")
									router.push({
										path: '/LoginAndRegister/Login'
									})
								}
							}).catch(error => {
								console.log(error);
							})
						}
					}).catch();
				} else {
					ElMessage.error("请输入验证码！！")
				}
			} else {
				console.log('校验失败');
				return false;
			}
		});
	}
</script>

<style scoped>
	.register {
		width: 50%;
		height: 100%;
		float: right;
	}

	.el-input {
		width: 90%;
		height: 30px;
	}

	.el-form-item {
		margin-left: 0px;
		margin-top: 30px;
	}

	.register_title {
		text-align: center;
		margin-top: 25%;
	}

	.el-button {
		width: 20%;
		margin-left: 40%;
	}

	.yzm {
		color: blue;
		width: 55px;
		margin-left: -14px;
	}
	.email_register {
		display: block;
		width: 20%;
		font-size: 15px;
		margin-left: 40%;
		margin-top: 20px;
		color: #aaffff;
		font-weight: 500;
	}
</style>