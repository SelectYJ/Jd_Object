<template>
	<div style="height: 100%;">
		<!-- 主内容左侧隐藏的div -->
		<div class="show" v-show="divShow!=-1?true:false">
			<div class="show_div" v-for="(showLeftData,index) in showLeftDatas" :key="index" v-show="index==divShow"
				@mouseleave="divShow=-1" @mousemove="divShow=index">
				<div class="show_div_top">
					<a href="https://pro.jd.com/mall/active/2vx2zyXR2KhRouYS6LrSEdnLF1P5/index.html" target="_blank"
						v-for="data in showLeftData.topName" :key="data">{{data}}&gt;</a>
				</div>
				<div class="show_div_bottom" v-for="data in showLeftData.mainData" :key="data">
					<dl>
						<dt>
							<a href="https://pro.jd.com/mall/active/2vx2zyXR2KhRouYS6LrSEdnLF1P5/index.html"
								target="_blank">{{data.mainDataTop}}&gt;</a>
						</dt>
						<dd>
							<a href="https://pro.jd.com/mall/active/2vx2zyXR2KhRouYS6LrSEdnLF1P5/index.html"
								target="_blank" v-for="maindata in data.mainData" :key="maindata">{{maindata}}</a>
						</dd>
					</dl>
				</div>
			</div>
		</div>
		<!-- 主内容左侧文本 -->
		<div class="main_left">
			<div class="main_text">
				<ul>
					<li v-for="(leftData,index) in leftDatas" :key="index" @mouseout="divShow=-1"
						@mouseover="divShow=index" :class="{ main_text_li:divShow==index}">
						<div v-for="(data,index1) in leftData" :key="index1">
							<a href="https://pro.jd.com/mall/active/2vx2zyXR2KhRouYS6LrSEdnLF1P5/index.html"
								target="_blank">{{data.name}}</a>
							<span>{{data.type}}</span>
						</div>
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
	const divShow = ref(-1); // 主内容左侧隐藏的div是否显示

	// 主内容左侧隐藏的div的数据
	const showLeftDatas = ref([{
			topName: ["时尚箱包", "品质鞋履", "潮流珠宝", "奢侈品", "鞋包企业购"],
			mainData: [{
				mainDataTop: "时尚女鞋1",
				mainData: [
					"新品推荐", "女士休闲鞋", "女士凉鞋", "女士单鞋", "女士拖鞋",
					"高跟鞋", "帆布鞋", "布鞋/绣花鞋", "内增高", "雨鞋/雨靴",
					"女靴", "雪地靴", "鱼嘴鞋", "马丁靴", "踝靴", "松糕鞋", "坡跟鞋", "防水台", "鞋配件"
				]
			}, {
				mainDataTop: "时尚女鞋2",
				mainData: [
					"新品推荐", "女士休闲鞋", "女士凉鞋", "女士单鞋", "女士拖鞋",
					"高跟鞋", "帆布鞋", "布鞋/绣花鞋", "内增高", "雨鞋/雨靴",
					"女靴", "雪地靴", "鱼嘴鞋", "马丁靴", "踝靴", "松糕鞋", "坡跟鞋", "防水台", "鞋配件"
				]
			}, {
				mainDataTop: "时尚女鞋3",
				mainData: [
					"新品推荐", "女士休闲鞋", "女士凉鞋", "女士单鞋", "女士拖鞋",
					"高跟鞋", "帆布鞋", "布鞋/绣花鞋", "内增高", "雨鞋/雨靴",
					"女靴", "雪地靴", "鱼嘴鞋", "马丁靴", "踝靴", "松糕鞋", "坡跟鞋", "防水台", "鞋配件"
				]
			}, ]
		},
		{
			topName: ["时尚箱包1", "品质afe鞋履1", "aef潮12流珠宝", "奢12侈品", "鞋包12企业购"],
			mainData: [{
				mainDataTop: "时尚67鞋",
				mainData: [
					"新品1212推荐", "11女士休a闲鞋", "女士aef凉鞋", "女士feaf单鞋", "女faf士拖鞋",
					"高跟121鞋", "帆aef布鞋", "布鞋/绣aef花鞋", "内aef增高", "雨鞋/sf雨靴"
				]
			}, ]
		}
	])
	// 主内容左侧的文本
	const leftDatas = ref([
		[{
			name: "家用电器",
			type: ""
		}],
		[{
				name: "手机",
				type: " / "
			},
			{
				name: "运营商",
				type: "/"
			},
			{
				name: "数码",
				type: ""
			}
		],
		[{
				name: "电脑",
				type: " / "
			},
			{
				name: "办公",
				type: ""
			}
		],
		[{
				name: "家居",
				type: " / "
			},
			{
				name: "家具",
				type: "/"
			},
			{
				name: "家装",
				type: "/"
			},
			{
				name: "厨具",
				type: "/"
			}
		],
		[{
				name: "电脑",
				type: " / "
			},
			{
				name: "办公",
				type: ""
			}
		],
		[{
				name: "家居",
				type: " / "
			},
			{
				name: "家具",
				type: " / "
			},
			{
				name: "家装",
				type: " / "
			},
			{
				name: "厨具",
				type: ""
			}
		],
		[{
				name: "电脑",
				type: " / "
			},
			{
				name: "办公",
				type: ""
			}
		]
	]);
</script>

<style scoped>
	dt {
		color: #000000;
		width: 65px;
		height: 100px;
		float: left;
		font-size: 12px;
		margin-left: 4px;
		font-weight: 700;
	}

	dd>a {
		display: block;
		float: left;
		color: #767980;
		font-size: 12px;
		margin-left: 10px;
		font-weight: 700;
	}

	.show {
		width: 690px;
		height: 480px;
		position: absolute;
		top: 0px;
		left: 190px;
		background-color: #fff;
		z-index: 2;
	}

	.show_div {
		width: 100%;
		height: 100%;
		border: 1px solid #ebebeb;
	}

	.show_div_top {
		width: 100%;
		height: 40px;
		padding: 20px 0px 0px 20px;
	}

	.show_div_top>a {
		float: left;
		display: block;
		height: 20px;
		background-color: #000000;
		color: #fff;
		margin-left: 4px;
		line-height: 20px;
		text-align: center;
		font-size: 12px;
		padding: 3px 8px 3px 8px;
		margin-right: 4px;
	}

	.show_div_top>a:hover {
		background-color: red;
	}

	.show_div_bottom {
		width: 80%;
		height: 50px;
		padding: 5px 0px 0px 20px;
		float: left;
	}

	/* 主体的左边 */
	.main_left {
		width: 190px;
		height: 100%;
		background-color: #fff;
	}

	.main_text {
		width: 100%;
		height: 80%;
		padding-top: 8px;
		font-size: 14px;
	}

	.main_text>ul>li {
		height: 25px;
		margin-top: 0px;
		padding-left: 15px;
		line-height: 25px;
	}

	.main_text>ul>li div {
		float: left;
	}

	.main_text_li {
		background-color: #c9d0d8;
	}
</style>