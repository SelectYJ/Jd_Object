import {
	createRouter,
	createWebHistory
} from 'vue-router';
const routes = [ //配置具有关系
	// 主页面 路由
	{
		path: '/',
		component: () => import('../components/IndexMain/JDIndex.vue')
	},
	// 登录/注册 路由
	{
		path: '/LoginAndRegister',
		name: 'LoginAndRegister',
		component: () => import('../components/Login/LoginAndRegister.vue'),
		redirect: '/LoginAndRegister/Login',
		children: [ //嵌套一级子路由
			{
				path: 'Login', //子路由前不需要加斜杠 /
				name: 'Login',
				component: () => import('../components/Login/JDLogin.vue'),
			},
			{
				path: 'Register', //子路由前不需要加斜杠 /
				name: 'Register',
				component: () => import('../components/Login/JDRegister.vue'),
			},
			{
				path: 'RegisterByEmail', // 邮箱注册
				name: 'RegisterByEmail',
				component: () => import('../components/Login/JDRegisterByEmail.vue'),
			}
		]
	},
	// 购物车路由
	{
		path: '/ShoppingCar',
		name: 'ShoppingCar',
		component: () => import('../components/Shopping/ShoppingCar.vue'),
		redirect: '/ShoppingCar/NowShoppingCar',
		children: [ //嵌套一级子路由
			{
				path: 'NowShoppingCar', //子路由前不需要加斜杠 /
				name: 'NowShoppingCar',
				component: () => import('../components/Shopping/NowShoppingCar.vue'),
			},
			{
				path: 'BeforeShoppingCar', //子路由前不需要加斜杠 /
				name: 'BeforeShoppingCar',
				component: () => import('../components/Shopping/BeforeShoppingCar.vue'),
			}
		]
	},
	// 个人信息
	{
		path: '/UserInfo',
		name: 'UserInfo',
		component: () => import('../components/UserInfo/UserInfo.vue')
	},
	// 错误信息
	{
		path: '/error',
		name: 'error',
		component: () => import('../components/error/error.vue')
	}
]
const router = createRouter({ //第二步，生成一个router对象，并进行配置路由映射关系
	history: createWebHistory(), // 注意是函数使用，不是变量！！！！！！
	routes //配置具有关系
})
// 路由拦截器
router.beforeEach((to, from) => {
	// ❗️ 避免无限重定向
	// localStorage.getItem("token")检查用户是否已登录
	if (to.path === '/' || to.name === 'Login' || to.name === 'Register' || to.name === 'RegisterByEmail' || localStorage.getItem("token") &&
		localStorage.getItem("username")) {
		return true
	}
	// 将用户重定向到登录页面
	return {
		name: 'Login'
	}
})
export default router; //第三步，导出（挂载）