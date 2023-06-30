<template>
	<div class="footer_out">
		<div class="footer_top">
			<a href="" class="footer_left">
				<div class="footer_left_div1">京东秒杀</div>
				<div class="footer_left_div2">
					<div style="height: 30px;line-height: 30px;">
						<b style="font-size: 20px;">{{time.slice(9,15)}}&nbsp;</b>
						<b style="font-weight: 500;">点场&nbsp;距结束</b>
					</div>
					<span>
						<span class="footer_span_number">{{finalHours}}</span>
						<span>:</span>
						<span class="footer_span_number">{{finalMinute}}</span>
						<span>:</span>
						<span class="footer_span_number">{{finalSecond}}</span>
					</span>
				</div>
			</a>
			<el-carousel height="100%" indicator-position="none" class="footer_div">
				<el-carousel-item v-for="item in 4" :key="item">
					<ul>
						<li class="play" v-for="playImg in playImgs" :key="playImg">
							<div class="play_img"><a href=""><img :src="playImg.imgUrl" alt=""></a></div>
							<h6>{{playImg.title}}</h6>
							<div class="play_price">￥ {{playImg.price}}</div>
						</li>
					</ul>
				</el-carousel-item>
			</el-carousel>
			<a href="#" class="footer_right"></a>
		</div>
	</div>
</template>

<script setup>
	import {
		ref,
		watch,
		onMounted,
		onBeforeUnmount
	} from 'vue'
	const playImgs = ref([{
			imgUrl: `src/assets/img/small_Icon_img/iconSmall_1.png`,
			title: '饼干wqererfeaffsgsggsfgeaefrefsefsrfrsfsr1',
			price: 199.9
		},
		{
			imgUrl: `src/assets/img/small_Icon_img/iconSmall_2.png`,
			title: '饼干2',
			price: 299.9
		},
		{
			imgUrl: `src/assets/img/small_Icon_img/iconSmall_3.png`,
			title: '饼干3',
			price: 399.9
		},
		{
			imgUrl: `src/assets/img/small_Icon_img/iconSmall_4.png`,
			title: '饼干4',
			price: 499.9
		}
	]);
	const myDate = ref(); // 定义获取当前时间的属性
	// 获取当天时间
	const nowTime = new Date().getFullYear() + '-' + (new Date().getMonth()+1) + '-' + new Date().getDate() + ' 19:00:00';
	const time = ref(nowTime) // 秒杀结束时间
	const finalHours = ref(); // 定义获取剩余多少小时的属性
	const finalMinute = ref(); // 定义获取剩余多少分钟的属性
	const finalSecond = ref(); // 定义获取剩余多少秒的属性
	const timer = ref(); // 定义存放定时器的属性
	// 监视秒杀结束时间的变化，暂时没用上
	watch(time, (newTime) => {
		console.log(newTime);
		if (timer.value) {
			clearInterval(timer);
		}
		startTimer(newTime.value);
	})
	// 启动定时器的方法
	const startTimer = (t) => {
		timer.value = setInterval(() => {
			// 获取当前时间并转成时间戳
			myDate.value = new Date().getTime();
			// 拿当前时间的时间戳减去设置的时间的时间戳
			const overTime = Math.ceil((new Date(t).getTime() - new Date(myDate.value).getTime()) / 1000);
			// 判断时、分、秒是不是个位数，如果是个位数则在前面加上0
			finalSecond.value = (overTime % 60).toString().length == 1 ? '0' + (overTime % 60) : (overTime %
				60);
			finalMinute.value = ((overTime - finalSecond.value) % 3600 / 60).toString().length == 1 ? '0' + ((
				overTime - finalSecond.value) % 3600 / 60) : ((overTime - finalSecond.value) % 3600 / 60);
			finalHours.value = ((overTime - finalSecond.value - finalMinute.value * 60) / 3600).toString()
				.length ==
				1 ? '0' + ((overTime - finalSecond.value - finalMinute.value * 60) / 3600) : ((overTime -
					finalSecond
					.value - finalMinute.value * 60) / 3600);
			closeTimer();
		}, 1000);
	}
	// 判断时间有没有小于0，小于0就关闭定时器timer
	const closeTimer = () => {
		// 判断当秒数不为undefined的时候再进行判断秒数为不为负数
		if (finalSecond.value != undefined) {
			// 判断时间是否为00:00:00或者秒数是否为负数
			if (finalHours.value == '00' && finalMinute.value == '00' && finalSecond.value == '00' || finalSecond.value
				.toString().slice(0, 1) == '-') {
				// 如果是则把时间倒计时设置为00:00::
				finalSecond.value = '00'
				finalMinute.value = '00'
				finalHours.value = '00'
				// 并且关闭定时器timer
				clearInterval(timer.value);
			}
		}
	}
	onMounted(() => {
		// 当页面挂载完毕后开启定时器，开始倒计时
		startTimer(time.value);
	})
	onBeforeUnmount(() => {
		// 销毁Vue实例的时候关闭定时器
		clearInterval(timer.value);
	})
</script>

<style scoped>
	.footer_out {
		width: 100%;
		height: 280px;
		position: absolute;
		z-index: 9;
		left: 0px;
		margin-top: 25px;
	}

	.footer_top {
		width: 1200px;
		height: 260px;
		margin: 0px auto;
	}


	.footer_left {
		display: block;
		width: 190px;
		height: 100%;
		background-image: url(../../../assets/img/jd_second.png);
		overflow: hidden;
		float: left;
	}

	.footer_left>div {
		width: 80%;
		height: 50%;
		text-align: center;
		margin-left: 10%;
	}

	.footer_left_div1 {
		color: #fff;
		font-size: 30px;
		font-weight: 700;
		text-align: center;
		margin-top: 30px;
	}

	.footer_left_div2 {
		font-size: 14px;
		color: #fff;
	}

	.footer_left_div2 span {
		height: 30px;
		float: left;
		font-size: 20px;
		font-weight: 600;
		line-height: 30px;
	}

	.footer_left_div2>span {
		width: 90%;
		margin-left: 9%;
		margin-top: 10px;
	}

	.footer_span_number {
		width: 35px;
		background-color: #000000;
		margin-right: 5px;
	}


	.footer_div {
		width: 818px;
		height: 100%;
		float: left;
		position: relative;
		margin-left: 2px;
	}

	.footer_div ul {
		width: 100%;
		height: 100%;
	}

	.play {
		width: 24.85%;
		height: 100%;
		float: left;
		background-color: #fff;
		border-right: 1px solid #eaeaea;
	}

	.play_img {
		height: 60%;
		text-align: center;
		overflow: hidden;
		margin-top: 30px;
	}

	.play_img img {
		width: 140px;
		height: 140px;
	}

	.play h6 {
		white-space: nowrap;
		overflow: hidden;
		text-overflow: ellipsis;
	}

	.play h6,
	.play_price {
		width: 60%;
		text-align: center;
		margin-left: 20%;
	}

	.play_price {
		font-size: 12px;
		color: red;
		font-weight: 700;
	}

	.footer_btn {
		position: absolute;
	}


	.footer_right {
		display: block;
		width: 190px;
		height: 260px;
		background: url(../../../assets/img/jd_second2.png) no-repeat center center;
		background-size: cover;
		overflow: hidden;
		float: right;
	}
</style>
