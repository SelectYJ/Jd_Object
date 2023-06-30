<template>
	<el-container>
		<el-header>
			<HeaderTop />
		</el-header>
		<el-main style="padding: 0px;">
			<HeaderMain />
		</el-main>
	</el-container>
</template>

<script setup>
	import HeaderTop from './HeaderTop.vue'
	import HeaderMain from './HeaderMain.vue'
</script>

<style scoped>
	.el-header {
		position: relative;
		width: 100%;
		height: 30.8px;
		background-color: #dedede;
		color: #333;
		line-height: 30px;
	}

	.el-main {
		width: 1200px;
		height: 140.8px;
		overflow: hidden;
		background-color: #ffffff;
		color: #000;
		margin: auto;
	}
</style>