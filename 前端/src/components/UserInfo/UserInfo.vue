<template>
	<div>
		<img src="../../assets/img/bg_img/2.jpg" alt="">
		<div class="user_main">
			<div class="title">
				<router-link to="/">
					<el-button class="btn_left" link>
						<el-icon>
							<ArrowLeft />
						</el-icon>
						返回首页
					</el-button>
				</router-link>
				<h1>个人信息</h1>
				<router-link to="/ShoppingCar">
					<el-button class="btn_right" link>
						我的购物车
						<el-icon>
							<ArrowRight />
						</el-icon>
					</el-button>
				</router-link>
			</div>
			<el-table :data="userInfo" stripe border v-show="!showInput">
				<el-table-column align="center" prop="name" label="姓名">
				</el-table-column>
				<el-table-column align="center" prop="emailName" label="邮箱地址">
				</el-table-column>
				<el-table-column align="center" prop="age" label="年龄">
				</el-table-column>
				<el-table-column align="center" label="性别" prop="gender">
					<span>{{userInfo[0].gender}}</span>
				</el-table-column>
				<el-table-column align="center" prop="username" label="账号名称">
				</el-table-column>
				<el-table-column align="center" label="密码">***
				</el-table-column>
				<el-table-column align="center" prop="update" label="操作数据">
					<el-button color="orange" @click="showUpdate">修改</el-button>
					<el-button color="red" @click="deleteUser">注销</el-button>
				</el-table-column>
			</el-table>

			<el-table :data="userInfo" stripe border v-show="showInput">
				<el-table-column align="center" prop="name" label="姓名">
					<input @keyup.enter="click_update" class="infoInput" v-model="userInfo[0].name" />
				</el-table-column>
				<el-table-column align="center" prop="emailName" label="邮箱地址">
					<input @keyup.enter="click_update" class="infoInput" v-model="userInfo[0].emailName" />
				</el-table-column>
				<el-table-column align="center" prop="age" label="年龄">
					<input @keyup.enter="click_update" type="number" class="infoInput" v-model="userInfo[0].age" />
				</el-table-column>
				<el-table-column align="center" label="性别" prop="gender">
					<el-radio-group v-model="userInfo[0].gender">
						<el-radio label="男" />
						<el-radio label="女" />
					</el-radio-group>
				</el-table-column>
				<el-table-column align="center" prop="username" label="账号名称">
					<input @keyup.enter="click_update" class="infoInput" v-model="userInfo[0].username" />
				</el-table-column>
				<el-table-column align="center" prop="password" label="密码">
					<input @keyup.enter="click_update" class="infoInput" v-model="userInfo[0].password" />
				</el-table-column>
				<el-table-column align="center" label="操作数据">
					<el-button class="update_btn" color="red" @click="click_update">确认修改</el-button>
					<el-button class="update_btn" color="orange" @click="showInput=!showInput">取消修改</el-button>
				</el-table-column>
			</el-table>


			<el-dialog center width="20%" v-model="oldPasswordShow" title="请确认是本人">
				<el-input placeholder="请输入旧密码" ref="refInput" @keyup.enter="updateOK" type="password" show-password
					v-show="oldEmailName === userInfo[0].emailName" v-model="oldPassword" />
				<el-input placeholder="请输入验证码" type="text" v-model="emailCode"
					v-show="oldEmailName !== userInfo[0].emailName" size="default">
					<template #append>
						<a class="yzm" @click="sendEmailCode">获取验证码</a>
					</template>
				</el-input>
				<template #footer>
					<span>
						<el-button type="primary" @click="updateOK">
							确认
						</el-button>
					</span>
				</template>
			</el-dialog>

			<p class="box">
				<span class="roll">个人信息页面</span>
			</p>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		onMounted
	} from 'vue'
	import {
		sendCodeByEmail,
		codeIsOk,
		getUserInfo,
		getUserOldPasswordByUserId,
		updateUserInfo,
		clearUser
	} from '../../api/axiosApi.js'
	import {
		ElMessage,
		ElMessageBox
	} from 'element-plus'
	// 用户输入的验证码
	const emailCode = ref();
	// 使输入旧密码的input获取焦点
	const refInput = ref();
	// 是否显示
	const oldPasswordShow = ref(false)
	// 存放用户输入
	const oldPassword = ref('')

	// 用于存放个人信息
	const userInfo = ref([{
		id: 0,
		name: '',
		emailName: '',
		age: 0,
		gender: '',
		username: '',
		password: '',
	}]);
	// 存放未修改前的邮箱地址
	const oldEmailName = ref('');
	// 是否显示可修改的信息
	const showInput = ref(false);
	// 点击修改按钮
	const showUpdate = () => {
		userInfo.value[0].emailName = userInfo.value[0].emailName == '暂无填写' ? '' : userInfo.value[0].emailName;
		showInput.value = !showInput.value
	}
	// 点击确认修改个人信息
	const click_update = () => {
		if (userInfo.value[0].password.trim() === '' || userInfo.value[0].name.trim() === '' || userInfo.value[0]
			.username.trim() === '' || userInfo.value[0].emailName.trim() === '') {
			ElMessage.error('数据不能为空')
		} else {
			if (userInfo.value[0].age <= 0 || userInfo.value[0].age > 200) {
				ElMessage.error('年龄应该在1~200岁')
			} else {
				var reg01 = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
				if (reg01.test(userInfo.value[0].emailName)) {
					oldPasswordShow.value = true
					setTimeout(() => {
						refInput.value.focus();
					})
				} else {
					ElMessage.error('邮箱格式不正确')
				}
			}
		}
	}
	// 发送验证码
	const sendEmailCode = () => {
		// 向用户邮箱发送验证码
		sendCodeByEmail(userInfo.value[0].emailName).then(data => {
			if (data.data.code === 1) {
				ElMessage.success(data.data.data)
			}
		}).catch()
	}
	// 判断 旧密码/验证码 来确认修改
	const updateOK = () => {
		// 判断刚开始显示的邮箱地址和现在提交的邮箱地址是否相同
		if (oldEmailName.value === userInfo.value[0].emailName) {
			// console.log("没变过邮箱地址");
			// 判断旧密码是否正确
			getUserOldPasswordByUserId(userInfo.value[0].id, oldPassword.value).then(data => {
				if (data.data.code === 1) {
					updateUser();
				}
			}).catch();
		} else {
			// console.log("变过邮箱地址");
			// 向后端发送用户输入的验证码判断是否正确
			codeIsOk(emailCode.value).then(data => {
				if (data.data.code === 1) {
					// 正确则进行修改
					updateUser();
				}
			}).catch();
		}
	}
	// 修改个人信息
	const updateUser =  () => {
		userInfo.value[0].name = userInfo.value[0].name.trim();
		userInfo.value[0].username = userInfo.value[0].username.trim();
		userInfo.value[0].password = userInfo.value[0].password.trim();
		showInput.value = !showInput.value;
		updateUserInfo(userInfo.value[0]).then(data => {
			if (data.data.code === 1) {
				localStorage.setItem('username', userInfo.value[0].username);
				oldPasswordShow.value = false
				getInfo();
				ElMessage({
					type: 'success',
					message: '修改成功',
				})
			}
		}).catch(error => {});
	}
	// 获取个人信息并展示
	const getInfo = () => {
		oldPassword.value = '';
		const username = localStorage.getItem('username');
		getUserInfo(username).then(data => {
			if (data.data.code !== 0) {
				userInfo.value[0].id = data.data.data.id
				userInfo.value[0].name = data.data.data.name
				userInfo.value[0].emailName = data.data.data.emailName || "暂无填写"
				oldEmailName.value = data.data.data.emailName
				userInfo.value[0].age = data.data.data.age
				userInfo.value[0].gender = data.data.data.gender
				userInfo.value[0].username = data.data.data.username
				userInfo.value[0].password = data.data.data.password
			} else {
				location.href = '/LoginAndRegister'
			}
		}).catch(error => {
			console.log(error);
		})
	}
	// 注销用户
	const deleteUser = () => {
		ElMessageBox.confirm(
			'确认注销账号吗？', {
				confirmButtonText: '确认',
				cancelButtonText: '取消',
				type: 'warning',
			}).then(() => {
			clearUser(userInfo.value[0].id).then(data => {
				if (data.data.code === 1) {
					localStorage.removeItem("username");
					localStorage.removeItem("token");
					location.href = "/LoginAndRegister"
				}
			}).catch();
		}).catch(() => {});
	}
	onMounted(() => {
		getInfo();
	})
</script>

<style scoped>
	img {
		position: absolute;
		width: 100%;
		height: 100%;
		min-height: 250px;
		min-width: 950px;
		left: 0;
		right: 0;
		top: 0;
	}

	h1 {
		text-align: center;
		float: left;
		width: 80%;
	}

	.user_main {
		position: absolute;
		width: 1300px;
		height: 250px;
		background-color: #fff;
		left: 50%;
		top: 50%;
		margin-left: -650px;
		margin-top: -150px;
		border-radius: 10px;
		box-shadow: 10px 10px 5px -4px rgba(0, 0, 0, 0.8);
	}

	.title {
		height: 100px;
		line-height: 100px;
	}

	.btn_left {
		float: left;
		width: 10%;
		height: 100%;
	}

	.btn_right {
		float: right;
		width: 10%;
		height: 100%;
	}

	.box {
		width: 100%;
		margin: 20px auto;
		overflow: hidden;
	}

	.roll {
		width: 110%;
		white-space: nowrap;
		animation: 10s loop linear infinite normal;
		display: inherit;
		margin-left: -10%;
		color: #5985ff;
	}

	@keyframes loop {
		0% {
			transform: translateX(-10%);
			-webkit-transform: translateX(-10%);
		}

		100% {
			transform: translateX(100%);
			-webkit-transform: translateX(100%);
		}
	}

	.infoInput {
		text-align: center;
		height: 25px;
		width: 155px;
		border-radius: 5px;
		border: 1px solid #c4c4c4;
	}

	.update_btn {
		font-size: 13px;
		width: 60px;
	}

	.yzm {
		color: blue;
		width: 55px;
		margin-left: -14px;
	}
</style>