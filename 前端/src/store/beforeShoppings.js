import {
	defineStore
} from 'pinia'
export const userShoppingStore = defineStore('shopping', {
	state: () => {
		return{
			shoppings: [{
				id: 1,
				name: 'zhangsan',
				deleteTime: new Date().getTime()
			}]
		}
	},
	actions: {
		addShopping(shopping) {
			this.shoppings.push(shopping);
		}
	}
})