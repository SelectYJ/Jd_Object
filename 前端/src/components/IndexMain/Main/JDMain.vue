<template>
	<div style="height: 100%;">
		<div class="con_div">
			<a id="con_a"></a>
		</div>
		<el-container>
			<el-aside width="200px">
				<MainLeft />
			</el-aside>
			<!-- 主内容中间两个轮播图 -->
			<el-aside width="800px">
				<MainMiddle />
			</el-aside>
			<!-- 主内容右侧文本 -->
			<el-aside width="200px">
				<MainRight />
			</el-aside>
		</el-container>
		<MainBottom />
	</div>
</template>

<script setup>
	import MainLeft from './MainLeft.vue'
	import MainMiddle from './MainMiddle.vue'
	import MainRight from './MainRight.vue'
	import MainBottom from './MainBottom.vue'
</script>

<style scoped>
	.el-container {
		width: 1200px;
		height: 65%;
		margin: 0px auto;
		overflow: hidden;
		position: relative;
		z-index: 1;
	}

	.el-aside {
		overflow: hidden;
	}

	/* 背景图 */
	.con_div {
		position: absolute;
		z-index: 1;
		height: 100%;
		left: 0px;
		top: 0px;
		margin: 0px;
		width: 100%;
		display: block;
		background-color: #f0f0f0;
	}

	#con_a {
		display: block;
		background: url(../../../assets/img/main_bg.png) 50% 0px no-repeat;
		height: 710px;
		z-index: 9;
	}
</style>