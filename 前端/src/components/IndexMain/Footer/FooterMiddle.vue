<template>
	<div class="main">
		<el-row type="flex" class="row-bg" justify="space-around" v-for="name in 3" :key="name">
			<el-col :span="4" v-for="n in 4" :key="n">
				<div class="grid-content bg-purple">
					<img src="../../../assets/img/jd_second.png" alt="">
					<span style="float: left;width: 100%;display: block;">1111</span>
				</div>
			</el-col>
		</el-row>
	</div>

</template>

<script>
</script>

<style scoped>
	.el-row {
		background-color: #333333;
		padding-top: 20px;
	}
	.main{
		width: 80%;
		margin-left: 10%;
	}
</style>
