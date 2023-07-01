<template>
	<div class="nowData">
		<el-row justify="space-evenly">
			<SearchShopping @clickSearch="updateShoppings" :clearSearchData="clearSearchData" :goodsFamilies="goodsFamilies" />
		</el-row>
		<el-table max-height="450px" ref="table" @row-click="rowClick" :data="tickets" :empty-text="emptyTable" stripe
			border @select-all="selectUser" @select="selectUser">
			<el-table-column width="80px" align="center" type="selection" />
			<el-table-column align="center" type="index" label="ID">
			</el-table-column>
			<el-table-column sortable align="center" prop="goodsName" label="商品名称">
			</el-table-column>
			<el-table-column sortable align="center" prop="family" label="商品类别">
			</el-table-column>
			<el-table-column sortable align="center" prop="createTime" label="创建时间">
			</el-table-column>
			<el-table-column sortable align="center" prop="goodsPrice" label="商品价格">
			</el-table-column>
			<el-table-column sortable align="center" label="商品数量">
				<template v-slot="scope">
					<button class="btn" @click.stop="reduceCount(scope.row)">-</button>
					<span class="btn_span">{{ scope.row.goodsCount }}</span>
					<button class="btn" @click.stop="addCount(scope.row)">+</button>
				</template>
			</el-table-column>
			<el-table-column align="center" label="操作数据">
				<template v-slot="scope">
					<el-button color="red" @click.stop="deleteGoodByName(scope.row.goodsId)">删除</el-button>
				</template>
			</el-table-column>
		</el-table>
		<el-row class="row-bg" justify="space-between">
			<el-col :span="4">
				<div class="clic_shoppings">
					<el-button class="clear_shopping" v-show="showBtn"
						@click="selectionAll">{{selectionText}}</el-button>
				</div>
			</el-col>
			<el-col :span="10">
				<div class="clic_shoppings">
					<el-button class="settle_shopping" @click="settleShopping" v-show="showClear">结算选中的商品</el-button>
					<span class="price_shopping" v-show="showClear">总价：￥{{rowShoppingsTotal}}</span>
				</div>
			</el-col>
			<el-col :span="4">
				<div class="clic_shoppings">
					<el-button class="clear_shopping" v-show="showClear" @click="clearShopping">删除选中的商品</el-button>
				</div>
			</el-col>
		</el-row>
	</div>
</template>

<script setup>
	import {
		ref,
		computed,
		onMounted
	} from 'vue'
	import SearchShopping from './SearchShopping.vue'
	import {
		ElMessage,
		ElMessageBox
	} from 'element-plus'
	import {
		searchFamily,
		searchShopping,
		updateShoppingCount,
		deleteShoppingByIds,
		buyShopping
	} from '../../api/axiosApi.js'

	const showBtn = ref(false); // 判断是否显示 选择全部商品 的按钮
	const showClear = ref(false); // 判断是否显示 删除选中的商品 的按钮
	const table = ref(); // 用于得到当前的table表格的数据（当前是为了得到某行数据）
	const selectionText = ref('选择全部商品') // 用于显示选中全部商品按钮中的文本
	const saveClearShoppings = ref([]); // 用于保存选中的商品
	const tickets = ref([]) //这里是从后端获取的所有数据

	// 购物车中没有商品时显示的文本
	const emptyTable = ref('购物车中没有商品,快去首页添加吧 ! ! !');

	// 存放商品的所有种类
	const goodsFamilies = ref([]);
	// 判断是否清空搜索栏输入的所有数据
	const clearSearchData = ref(false);

	// 删除弹框确认
	const openDeleteDiv = (msg, id) => {
		ElMessageBox.confirm(
			msg, {
				confirmButtonText: '确认',
				cancelButtonText: '取消',
				type: 'warning',
			}
		).then(() => {
			deleteShoppingByIds(id).then(data => {
				if (data.data.code === 1) {
					updateShoppings();
					saveClearShoppings.value = [];
					showClear.value = false;
					ElMessage.success('删除成功')
					clearSearchData.value = true
				}
			}).catch(error => {

			})
		}).catch(() => {
			ElMessage.info('取消删除')
		})
	}

	// 点击行选择复选框
	const rowClick = (row, column, event) => {
		if (unique(row)) {
			saveClearShoppings.value.push(row)
			// 当前行在选中列表中则选中该行
			table.value.toggleRowSelection(row, true)
		} else {
			// 当前行不在选中列表中则取消则选中该行
			table.value.toggleRowSelection(row, false)
		}
		selectUser(saveClearShoppings.value);
	}

	// 判断点击的是不是同一个，去重复
	const unique = (row) => {
		for (let i = 0; i < saveClearShoppings.value.length; i++) {
			if (saveClearShoppings.value[i] === row) {
				saveClearShoppings.value.splice(i, 1);
				return false;
			}
		}
		return true;
	}

	// 点击选择全部商品按钮
	const selectionAll = () => {
		table.value.toggleAllSelection()
	}

	// 当选中商品时调用的函数
	const selectUser = (row) => {
		saveClearShoppings.value = row;
		if (row.length) {
			showClear.value = true
			if (row.length === tickets.value.length) {
				selectionText.value = '取消选中商品'
				showClear.value = true
			} else {
				selectionText.value = '选择全部商品'
			}
		} else {
			selectionText.value = '选择全部商品'
			showClear.value = false
		}
	}

	// 增加商品数量
	const addCount = (row) => {
		row.goodsCount++
		updateGoods(row);
	}

	// 减少商品数量
	const reduceCount = (row) => {
		if (row.goodsCount > 1) {
			row.goodsCount--;
			updateGoods(row);
			return;
		}
		openDeleteDiv('您确认要删除吗？', row.goodsId);
	}

	// 商品数量更新
	const updateGoods = (row) => {
		updateShoppingCount(row.goodsId, row.goodsCount).then(data => {
			updateShoppings();
		});
	}

	// 删除单个商品
	const deleteGoodByName = (id) => {
		openDeleteDiv('您确认要删除吗？', id)
	}

	// 点击 删除选中的商品 按钮时
	const clearShopping = () => {
		let ids = '';
		for (let i = 0; i < saveClearShoppings.value.length; i++) {
			ids = ids + saveClearShoppings.value[i].goodsId + ','
		}
		openDeleteDiv('您确认要删除所有选中的商品吗？', ids)
	}

	// 更新购物车信息
	const updateShoppings = (params) => {
		clearSearchData.value = false
		/**
		 * 根据用户输入的条件查询购物车信息
		 * 当没有输入条件的时候查询该用户下的所有购物车信息
		 */
		searchShopping(params).then(data => {
			if (data.data.code !== 0) {
				if (data.data.data === null || data.data.data.length === 0) {
					showBtn.value = false
					showClear.value = false
					if (params !== undefined) {
						emptyTable.value = '搜索到0条商品'
					}
				} else {
					showBtn.value = true
					saveClearShoppings.value = [];
					showClear.value = false;
					selectionText.value = '选择全部商品';
				}
				tickets.value = data.data.data
				tickets.value.forEach((ticket, index) => {
					goodsFamilies.value.forEach(goodsFamily => {
						if (goodsFamily.id === ticket.family) {
							ticket.family = goodsFamily.family
							return;
						}
					})
				})
			} else {
				location.href = '/LoginAndRegister'
			}

		}).catch(error => {
			ElMessage.error(error.message);
			setTimeout(() => {
				location.href = '/'
			}, 1000)
		})
	}

	// 点击结算选中的购物车商品
	const settleShopping = () => {
		ElMessageBox.confirm(
			"是否结算选中商品？去付款￥" + rowShoppingsTotal.value, {
				confirmButtonText: '确认',
				cancelButtonText: '取消',
				type: 'warning',
			}
		).then(() => {
			buyShopping(saveClearShoppings.value).then(data => {
				if (data.data.code === 1) {
					updateShoppings();
					ElMessage.success('已成功结算')
				}
			}).catch()
		}).catch(() => {
			ElMessage.info('取消结算')
		})
	}

	// 计算所选中的商品的总价格
	const rowShoppingsTotal = computed(() => {
		let saveClearShoppingsPrice = 0;
		saveClearShoppings.value.forEach(saveClearShopping => {
			saveClearShoppingsPrice += saveClearShopping.goodsPrice * saveClearShopping.goodsCount
		})
		// return Math.round(saveClearShoppingsPrice*100)/100;// 保留两位小数
		return saveClearShoppingsPrice.toFixed(2) // 保留两位小数,五后非零就进一，五后为零看奇偶，五前为偶应舍去，五前为奇要进一
	})

	// 挂载完成后立马获取（更新）购物车信息
	onMounted(() => {
		updateShoppings();
		// 获取所有的商品类型
		searchFamily().then(data => {
			goodsFamilies.value = data.data.data
			tickets.value.forEach((ticket, index) => {
				// 把商品类型的数字转化为文本
				goodsFamilies.value.forEach(goodsFamily => {
					if (goodsFamily.id === ticket.family) {
						ticket.family = goodsFamily.family
						return;
					}
				})
			})
		}).catch()
	})
</script>

<style scoped>
	.btn {
		height: 20px;
		width: 30px;
		border: 1px solid #dedede;
		border-radius: 5px;
		line-height: 20px;
	}

	.btn:hover {
		cursor: pointer;
		background-color: #dcdcdc;
	}

	.btn_span {
		width: 60px;
		margin: 0px 10px 0px 10px;
	}

	.clic_shoppings {
		margin: 10px 0px;
		text-align: center;
	}

	.clear_shopping {
		background-color: red;
		color: #fff;
		border: 0px;
	}

	.settle_shopping {
		background-color: #3791ff;
		color: black;
	}

	.price_shopping {
		margin: 10px;
		color: red;
	}
</style>