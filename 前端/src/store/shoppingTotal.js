import { ref } from 'vue'
import {
	defineStore
} from 'pinia'
export const userShoppingTotalStore = defineStore('shoppingTotal', () => {
	const shoppingTotal = ref(0)

	function setShoppingTotal(total) {
		shoppingTotal.value = total
	}

	return {
		shoppingTotal,
		setShoppingTotal
	}
})