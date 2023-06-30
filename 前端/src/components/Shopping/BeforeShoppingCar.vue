<template>
	<div>
		<el-table ref="table" max-height="560px" :data="buyShopping" stripe border>
			<el-table-column align="center" width="160px" type="index" label="ID">
			</el-table-column>
			<el-table-column sortable align="center" prop="goodsName" label="商品名称">
			</el-table-column>
			<el-table-column sortable align="center" prop="goodsCount" label="结算数量">
			</el-table-column>
			<el-table-column sortable align="center" prop="createTime" label="结算时间">
			</el-table-column>

			<el-table-column align="center" prop="update" label="操作数据">
				<template v-slot="scope">
					<el-button color="red" @click="deleteBuyShopping(scope.row)">删除记录</el-button>
				</template>
			</el-table-column>
		</el-table>
	</div>
</template>

<script setup>
	import {
		ref,
		onMounted
	} from 'vue'
	import {
		getBuyShoppings,
		deleteRecycleBuyByGoodsId
	} from '../../api/axiosApi.js'
	import {
		ElMessage,
		ElMessageBox
	} from 'element-plus'
	const buyShopping = ref([]);
	const deleteBuyShopping = (row) => {
		ElMessageBox.confirm(
			'确定删除名称为：<font size="3" color="red">' + row.goodsName +
			'</font></br>结算时间为：<font size="3" color="red">' + row.createTime + '</font> 这条记录吗？', {
				confirmButtonText: '确认',
				cancelButtonText: '取消',
				type: 'warning',
				dangerouslyUseHTMLString: true,
				draggable: true
			}
		).then(() => {
			deleteRecycleBuyByGoodsId(row.goodsId, row.createTime).then(data => {
				if (data.data.code === 1) {
					ElMessage({
						type: 'success',
						message: '删除成功',
					})
					getBuyShoppings().then(data => {
						buyShopping.value = data.data.data
					}).catch()
				}
			}).catch()
		}).catch(() => {
			ElMessage({
				type: 'info',
				message: '取消删除',
			})
		})
	}
	onMounted(() => {
		getBuyShoppings().then(data => {
			buyShopping.value = data.data.data
		}).catch()
	})
</script>

<style>
</style>