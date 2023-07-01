<template>
	<el-form :inline="true" :model="formInline" class="demo-form-inline">
		<el-form-item>
			<el-input v-model="formInline.goodsName" placeholder="商品名称" />
		</el-form-item>
		<el-form-item>
			<el-select v-model="formInline.family" placeholder="商品类型" clearable>
				<el-option v-for="family in goodsFamilies" :key="family.family" :label="family.family"
					:value="family.id" />
			</el-select>
		</el-form-item>
		<el-form-item>
			<el-date-picker v-model="formInline.startDateTime" type="datetime" placeholder="从x年x月x日x时x分x秒开始"
				value-format="YYYY-MM-DD HH:mm:ss" clearable />
			<span style="margin: auto 10px;">至</span>
			<el-date-picker v-model="formInline.endDateTime" type="datetime" placeholder="从x年x月x日x时x分x秒结束"
				value-format="YYYY-MM-DD HH:mm:ss" clearable />
		</el-form-item>
		<el-form-item>
			<el-button type="primary" @click="onSubmit">搜索</el-button>
		</el-form-item>
	</el-form>
</template>

<script setup>
	import {
		reactive,
		defineEmits,
		onUpdated,
	} from 'vue'
	// 父类传过来的函数
	const emit = defineEmits(['clickSearch'])
	// 父类传过来的属性
	const props = defineProps(['goodsFamilies', 'clearSearchData'])
	// 存放用户输入的条件
	const formInline = reactive({
		goodsName: '',
		family: '',
		startDateTime: '',
		endDateTime: ''
	})
	// 点击搜索框
	const onSubmit = () => {
		emit('clickSearch', formInline)
	}
	onUpdated(() => {
		if (props.clearSearchData) {
			formInline.goodsName = ''
			formInline.family = ''
			formInline.startDateTime = ''
			formInline.endDateTime = ''
		}
	})
</script>

<style scoped>
	.el-form-item {
		margin: 20px;
	}
</style>