<template>
	<!-- 主内容左侧大轮播图 -->
	<div class="main_middle" @mouseenter="clearTimer()" @mouseleave="startTimer()">
		<ul class="img_left" :style="{marginLeft:'-'+bigPlayImgsWidth+'px'}">
			<li v-for="bigPlayImg in bigPlayImgs" :key="bigPlayImg">
				<a class="main_middle_a" href="#"><img :src="bigPlayImg" />{{bigPlayImg}}</a>
			</li>
		</ul>
		<ul class="tab">
			<li v-for="item in bigPlayImgs.length" :key="item"
				:class="{active:(item-1)==(bigPlayImgsWidth/590)}" @mouseenter="bigPlayImgsWidth=(item-1)*590"></li>
		</ul>
		<div class="btn">
			<button @click="clickPrev" :disabled="dis"><span class="prev">&lt;</span></button>
			<button @click="clickNext" :disabled="dis"><span class="next">&gt;</span></button>
		</div>
	</div>
	<!-- 主内容右侧小轮播图 -->
	<div class="main_right">
		<el-carousel height="481px" :interval="3000" indicator-position="none">
			<el-carousel-item v-for="item in 4" :key="item">
					<ul class="img_right">
						<li>
							<a href="#"><img src="../../../assets/img/play_right_img/right_1.png" /></a>
						</li>
						<li style="margin-top: 10px;">
							<a href="#"><img src="../../../assets/img/play_right_img/right_2.png" /></a>
						</li>
						<li style="margin-top: 10px;">
							<a href="#"><img src="../../../assets/img/play_right_img/right_3.png" /></a>
						</li>
					</ul>
			</el-carousel-item>
		</el-carousel>
	</div>
</template>

<script setup>
	import {
		ref,
		onMounted,
		onDeactivated
	} from 'vue'
	import radio1 from "../../../assets/img/play_img/1.png";
	import radio2 from "../../../assets/img/play_img/2.jpg";
	import radio3 from "../../../assets/img/play_img/3.jpg";
	import radio4 from "../../../assets/img/play_img/4.jpg";
	// 大轮播图的图片路径数据
	const bigPlayImgs = ref([
		radio1, radio2, radio3, radio4
	]);
	// 定时器
	const timer = ref();
	// 大轮播图动起来
	const bigPlayImgsWidth = ref(0);
	// 计算大轮播图一共多长
	const maxWidth = ref((bigPlayImgs.value.length - 1) * (590));
	// 切换图片是否可点
	const dis = ref(false);
	const clickPrev = () => {
		dis.value = true;
		setTimeout(() => {
			dis.value = false
		}, 300)
		if (bigPlayImgsWidth.value <= 0) {
			bigPlayImgsWidth.value = maxWidth.value;
		} else {
			bigPlayImgsWidth.value -= 590;
		}
	}
	const clickNext = () => {
		dis.value = true;
		setTimeout(() => {
			dis.value = false
		}, 300)
		if (bigPlayImgsWidth.value < maxWidth.value) {
			bigPlayImgsWidth.value += 590;
		} else {
			bigPlayImgsWidth.value = 0;
		}
	}
	// 开启定时器
	const startTimer = () => {
		timer.value = setInterval(() => {
			if (bigPlayImgsWidth.value >= maxWidth.value) {
				bigPlayImgsWidth.value = 0;
			} else {
				bigPlayImgsWidth.value += 590;
			}
		}, 3500)
	}
	// 关闭定时器
	const clearTimer = () => {
		clearInterval(timer.value);
	}
	onMounted(() => {
		startTimer();
	});
	onDeactivated(() => {
		clearInterval(timer.value);
	})
</script>

<style scoped>
	
	/* 轮播图 */
	.main_middle {
		width: 590px;
		height: 100%;
		position: absolute;
		overflow: hidden;
	}
	
	.main_middle_a>img {
		width: 590px;
		height: 100%;
	}
	
	.img_left {
		width: 9999px;
		height: 100%;
		overflow: hidden;
		transition: margin-left 0.01s;
	}
	
	.img_left li {
		width: 590px;
		float: left;
		height: 100%;
	}
	
	.tab {
		position: absolute;
		bottom: 5px;
		left: 20%;
		margin-left: -100px;
		height: 30px;
		padding-left: 10px;
	}
	
	.tab li {
		width: 8px;
		height: 8px;
		float: left;
		background: #fff;
		border-radius: 50%;
		margin: 5px 10px 0 0;
		opacity: 0.6;
		cursor: pointer;
	}
	
	.tab li.active {
		background: #000000;
	}
	
	/* 大轮播图右边的小轮播图 */
	.main_right {
		width: 190px;
		height: 100%;
		position: absolute;
		left: 800px;
	}
	
	.img_right li {
		float: left;
	}
	
	.img_right img {
		width: 190px;
		height: 150px;
	}
</style>
