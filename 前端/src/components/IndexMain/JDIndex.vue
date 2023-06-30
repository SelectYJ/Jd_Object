<template>
	<el-container>
		<el-backtop :bottom="80">
			<div class="back">
				<span class="back_top">^</span>
				<span class="back_top_text">顶部</span>
			</div>
		</el-backtop>
		<el-header>
			<JDHeader />
		</el-header>
		<el-main>
			<JDMain />
		</el-main>
		<el-footer>
			<JDFooter />
		</el-footer>
	</el-container>
</template>

<script setup>
	import JDHeader from './Header/JDHeader.vue'
	import JDMain from './Main/JDMain.vue'
	import JDFooter from './Footer/JDFooter.vue'
</script>

<style scoped>
	.el-header {
		width: 100%;
		height: 171.6px;
		background-color: #fff;
		color: #333;
		padding: 0px;
	}

	.el-footer {
		width: 100%;
		height: 100%;
		color: #e1e1e1;
		text-align: center;
		background-color: #f1f1f1;
	}

	.el-main {
		width: 100%;
		height: 770px;
		overflow: hidden;
		background-color: #E9EEF3;
		position: relative;
		padding-top: 10px;
	}

	.el-backtop {
		width: 60px;
		height: 60px;
	}

	.back {
		height: 100%;
		width: 100%;
		background-color: #f2f5f6;
		box-shadow: 0 0 6px rgba(0, 0, 0, .12);
		text-align: center;
		color: #1989fa;
		border-radius: 10px;
		line-height: 40px;
	}

	.back_top {
		width: 100%;
		float: left;
		height: 20px;
	}

	.back_top_text {
		float: left;
		width: 100%;
	}
</style>