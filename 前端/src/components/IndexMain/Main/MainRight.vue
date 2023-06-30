<template>
	<div class="right" style="width: 100%;height: 100%;overflow: hidden;display: block;">
		<div class="right_top">
			<div class="right_top_outer">
				<div class="within within_left">
					<img src="../../../assets/img/small_Icon_img/iconSmall_0.png" alt="">
				</div>
				<div class="within within_right">
					<a href="">Hi~欢迎逛京东！</a>
					<p style="color: #767980;">
						<a>登录</a>
						<span style="margin: 0px 5px;">|</span>
						<a style="margin-right: 10px;">注册</a>
					</p>
				</div>
			</div>
			<div class="within_bottom">
				<a class="a_left">新人福利</a>
				<a class="a_right">PLUS会员</a>
			</div>
			<hr />
		</div>
		<div class="right_middle">
			<div>
				<h5>京东快报</h5>
				<a href="">更多&gt;</a>
			</div>
			<ul>
				<li>
					<a class="right_middle_a" href="">
						<span>最新</span>
						同事都跟我抢着吃的小零食，松脆酥香太爱了
					</a>
				</li>
				<li>
					<a class="right_middle_a" href="">
						<span>最新</span>
						同事都跟我抢着吃的小零食，松脆酥香太爱了
					</a>
				</li>
				<li>
					<a class="right_middle_a" href="">
						<span>最新</span>
						同事都跟我抢着吃的小零食，松脆酥香太爱了
					</a>
				</li>
				<li>
					<a class="right_middle_a" href="">
						<span>最新</span>
						同事都跟我抢着吃的小零食，松脆酥香太爱了
					</a>
				</li>
			</ul>
			<hr />
		</div>
		<!-- 底部小图标 -->
		<div class="right_bottom">
			<div>
				<ul>
					<li class="right_bottom_li" v-for="(imgData,index) in imgDatas" :key="index"
						@mouseenter="enterIcon=index" @mouseleave="enterIcon=-1">
						<a href="">
							<!-- <img :src="enterIcon==index?imgData.img2:imgData.img1" alt=""> -->
							<img :src="imgData.img2" v-show="enterIcon==index" alt="">
							<img :src="imgData.img1" v-show="enterIcon!=index" alt="">
							<span>{{imgData.text}}</span>
						</a>
					</li>
				</ul>
			</div>
		</div>
	</div>
</template>

<script setup>
	import {
		ref
	} from 'vue'
	// 小图标，移入变颜色
	const imgDatas = ref([{
			img1: `src/assets/img/small_Icon_img/iconSmall_1.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_2.png`,
			text: "加油卡"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_3.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_4.png`,
			text: "电影票"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_5.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_6.png`,
			text: "云建站"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_7.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_8.png`,
			text: "五金城"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_9.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_10.png`,
			text: "白条"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_11.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_12.png`,
			text: "火车票"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_13.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_14.png`,
			text: "酒店"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_15.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_16.png`,
			text: "游戏"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_17.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_18.png`,
			text: "话费"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_19.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_20.png`,
			text: "机票"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_21.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_22.png`,
			text: "礼品卡"
		},
		{
			img1: `src/assets/img/small_Icon_img/iconSmall_23.png`,
			img2: `src/assets/img/small_Icon_img/iconSmall_24.png`,
			text: "企业购"
		},
	]);
	// 判断是否移入小图标
	const enterIcon = ref(-1);
</script>

<style scoped>
	
	/* 主体最右边的文字_头部 */
	.right_top {
		width: 100%;
		height: 25%;
		position: relative;
		background-color: #fff;
	}
	
	.right_top_outer {
		width: 80%;
		height: 60%;
		margin-left: 14%;
		margin-top: 8%;
		position: absolute;
	}
	
	.right_top_outer>.within {
		float: left;
		margin-right: 10px;
	}
	
	.within_left {
		width: 40px;
		height: 40px;
		border-radius: 40px;
		background-color: #00FFFF;
		overflow: hidden;
		font-size: 12px;
	}
	
	.within_left>img {
		width: 40px;
		height: 40px;
	}
	
	.within_right {
		width: 60%;
		height: 20px;
		line-height: 20px;
		font-size: 12px;
	}
	
	.within_bottom {
		position: absolute;
		width: 80%;
		height: 25px;
		left: 10%;
		top: 60%;
	}
	
	.within_bottom>a {
		display: block;
		width: 70px;
		height: 25px;
		border-radius: 20px;
		font-size: 12px;
		float: left;
		text-align: center;
		line-height: 25px;
		margin-left: 5%;
	}
	
	.within_bottom>.a_left {
		background-color: #E1251B;
		color: #fff;
	}
	
	.within_bottom>.a_right {
		background-color: #363634;
		color: #e5d790;
	}
	
	hr {
		/*透明渐变水平线*/
		width: 80%;
		margin: 0 auto;
		border: 0;
		height: 1px;
		background-image: linear-gradient(to right, rgba(226, 226, 226, 0.0), rgba(171, 171, 171, 0.7), rgba(226, 226, 226, 0.0));
		position: absolute;
		bottom: 13px;
		left: 20px;
	}
	
	/* 主体最右边的文字_中部 */
	.right_middle {
		width: 100%;
		height: 30%;
		background-color: #fff;
		position: relative;
	}
	
	.right_middle>div {
		height: 15%;
		width: 80%;
		margin-left: 10%;
		margin-bottom: 10px;
	}
	
	.right_middle h5 {
		float: left;
		height: 20px;
		line-height: 20px;
		font-size: 14px;
	}
	
	.right_middle a {
		float: right;
		line-height: 20px;
		font-size: 12px;
		/* color: #909399; */
	}
	
	.right_middle>ul>li {
		width: 82%;
		height: 16px;
		margin-left: 10%;
		margin-bottom: 6px;
	}
	
	.right_middle_a {
		width: 160px;
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
		display: block;
		float: right;
	}
	
	.right_middle_a span {
		width: 35px;
		height: 16px;
		display: inline-block;
		background-color: rgba(225, 37, 27, .08);
		color: red;
		line-height: 16px;
		text-align: center;
		margin-right: 4px;
	}
	
	/* 主体最右边的文字_底部 */
	.right_bottom {
		width: 100%;
		height: 45%;
		background-color: #fff;
		text-align: center;
	}
	
	.right_bottom div {
		width: 98%;
		height: 100%;
		background-color: #fff;
		margin-left: 1.5%;
	}
	
	.right_bottom_li {
		float: left;
		width: 63px;
		height: 50px;
	}
	
	.right_bottom_li a {
		display: block;
		width: 100%;
		height: 100%;
	}
	
	.right_bottom_li img {
		width: 28px;
	}
	
	.right_bottom_li span {
		font-size: 12px;
		display: block;
	}
</style>
