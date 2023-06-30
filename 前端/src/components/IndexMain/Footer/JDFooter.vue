<template>
	<div class="footer">
		<div class="footer_top">
			<a href="" v-for="name in 12" :key="name">
				<div class="footer_top_bg1"><img src="../../../assets/img/static_li_img/bg_2.png" alt=""></div>
				<div class="footer_top_bg2"><img src="../../../assets/img/static_li_img/img_main.png" alt=""></div>
				<div class="footer_top_bg3">
					<div>
						<img src="../../../assets/img/static_li_img/bg.png" alt="">
					</div>
					<span>5折封顶</span>
				</div>
				<span class="text">京东图书</span>
			</a>
		</div>
		<FooterBottom/>
	</div>
</template>

<script setup>
	import FooterBottom from './FooterBottom.vue'
</script>
<style scoped>
	.footer{
		width: 100%;
		height: 100%;
	}
	.footer_top{
		width: 1250px;
		height: 480px;
		background: url(../../../assets/img/footer_bg.png) no-repeat;
		background-size: 100% 100%;
		margin: 30px auto;
		padding-top: 120px;
		border-radius: 45px 45px 0px 0px;
		overflow: hidden;
	}
	.footer_top>a{
		display: block;
		width: 15.5%;
		height: 50%;
		float: left;
		overflow: hidden;
		margin-left: 1%;
	}
	.footer_top_bg1,.footer_top_bg2,.footer_top_bg3{
		position: absolute;
	}
	.footer_top_bg1{
		width: 190px;
		height: 210px;
	}
	.footer_top_bg2{
		width: 130px;
		height: 130px;
		margin-left: 30px;
		margin-top: 10px;
	}
	.footer_top_bg3{
		width: 190px;
		height: 32px;
		margin-top: 145px;
		text-align: center;
		line-height: 30px;
	}
	.footer_top_bg3 div{
		position: absolute;
		height: 100%;
	}
	.footer_top_bg3>span{
		position: absolute;
		top: 0px;
		left: 33%;
		text-align: center;
		color: #fff;
		font-weight: 700;
		font-size: 17px;
	}
	.footer_top_bg1 img{
		width: 190px;
		height: 210px;
	}
	.footer_top_bg2 img{
		width: 130px;
		height: 130px;
	}
	.footer_top_bg3 img{
		width: 190px;
		height: 32px;
	}
	.text{
		position: relative;
		left: -10px;
		top: 75%;
		color: red;
		font-size: 16px;
		font-weight: 600;
	}
</style>
