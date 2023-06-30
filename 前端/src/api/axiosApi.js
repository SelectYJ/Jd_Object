import axios from 'axios'
import {
	ElMessage
} from 'element-plus'

// axios.defaults.baseURL = 'http://192.168.137.1:8080/';
// axios.defaults.baseURL = 'http://10.1.7.123:8080/';
axios.defaults.baseURL = 'http://localhost:8080/';
// axios.defaults.headers.common['Authorization'] = '';
// axios.defaults.headers.post['Content-Type'] = 'application/json';
let token = localStorage.getItem("token");

// 添加请求拦截器
axios.interceptors.request.use(function(config) {
	// console.log(config.url);
	// 判断是不是以getEmail/sendEmail?tos=结尾并且判断有没有token
	if (token&&config.url.indexOf('getEmail') === -1) {
		config.headers.token = token;
	}
	return config;
}, function(error) {
	console.log(error);
	// 对请求错误做些什么
	return Promise.reject(error);
});

// 添加响应拦截器
axios.interceptors.response.use(function(response) {
	// console.log(response); 
	// 1 表示数据正确
	// 对响应数据做点什么
	if (response.headers !== undefined && response.data !== '') {
		if (response.data.code !== 1) {
			ElMessage.error(response.data.msg)
		}
		if (response.headers.token) {
			token = response.headers.token;
			localStorage.setItem("token", token);
		}
	}
	return response;
}, function(error) {
	if (error.code === "ERR_NETWORK") {
		error.message = "请检查你的网络！！！"
	}
	// 超出 2xx 范围的状态码都会触发该函数。
	// 对响应错误做点什么
	return Promise.reject(error);
});

/*=======================================登录注册======================================*/
// 登录页面
const login = (params) => {
	return axios.post('login', {
		"username": params.username,
		"password": params.password
	})
}
// 注册页面
const register = (params) => {
	return axios.post('register', {
		"name": params.name,
		"emailName": params.emailPath,
		"gender": params.sex,
		"username": params.username,
		"password": params.password,
	})
}
// 邮箱注册
const sendCodeByEmail = (emailPath) => {
	return axios.get('getEmail/sendEmail?tos=' + emailPath)
}
// 判断验证码是否正确
const codeIsOk = (code) => {
	return axios.get('getEmail/codeIsOk?code=' + code)
}

/*===========================================用户相关==================================*/
// 个人信息获取
const getUserInfo = (username) => {
	return axios.post('userInfo', {
		"username": username
	})
}
// 获取用户的旧密码
const getUserOldPasswordByUserId = (userId,userNewPassword) => {
	return axios.post('getUserOldPasswordByUserId', {
		"id": userId,
		"password": userNewPassword
	})
}
// 修改个人信息
const updateUserInfo = (params) => {
	return axios.post('updateInfo', {
		"id": params.id,
		"name": params.name,
		"emailName": params.emailName,
		"age": params.age,
		"gender": params.gender,
		"username": params.username,
		"password": params.password
	})
}
// 注销当前用户
const clearUser = (id) => {
	return axios.post('clearUser', {
		"id": id
	})
}

/*===========================================商品相关==================================*/
// 主页面获取商品展示
const goodsShow = (page, count) => {
	return axios.get('goods/show?page=' + page + '&count=' + count)
}
// 商品信息获取
const getGoodsInfo = (id) => {
	return axios.get('goods/' + id)
}
// 添加商品到购物车
const addGoodsInShopping = (goodsId, count) => {
	return axios.get('shopping/increaseGoods?goodsId=' + goodsId + '&goodsCount=' + count)
}

/*===========================================用户的购物车相关==================================*/
// 购物车页面
const getShopping = (params) => {
	return axios.post('shopping/show', {
		"username": params
	})
}
// 修改商品数量
const updateShoppingCount = (id, count) => {
	return axios.get('shopping/updateCount/' + id + ',' + count)
}
// 删除商品
const deleteShoppingByIds = (listId) => {
	return axios.get('shopping/deleteShopping?ids=' + listId)
}
// 结算商品
const buyShopping = (buyAll) => {
	return axios.post('shopping/buyShopping',{
		"buyShopping":buyAll
	})
}
// 获取结算的商品信息展示
const getBuyShoppings = () => {
	return axios.get('shopping/getBuyShopping')
}

//
const deleteRecycleBuyByGoodsId = (goodsId,createTime) => {
	return axios.get('shopping/deleteRecycleShoppingByGoodsId?goodsId=' + goodsId + '&createTime=' + createTime)
}


export {
	// 用户相关
	login,// 登录
	register,// 注册
	sendCodeByEmail,
	codeIsOk,
	getUserInfo,
	getUserOldPasswordByUserId,
	updateUserInfo,
	clearUser,
	// 商品相关
	goodsShow,
	getGoodsInfo,
	addGoodsInShopping,
	// 用户的购物车相关
	getShopping,
	updateShoppingCount,
	deleteShoppingByIds,
	buyShopping,
	getBuyShoppings,
	deleteRecycleBuyByGoodsId
}