<template>
	<router-view></router-view>
</template>

<style>
	* {
		margin: 0px;
		padding: 0px;
	}

	li {
		list-style: none;
	}

	a {
		text-decoration: none;
	}

	a:hover {
		color: red;
		cursor: pointer;
		transition: color 0.2s;
	}

	.btn {
		z-index: 99;
	}

	.btn>span,
	.main_right_btn>span {
		display: block;
		width: 25px;
		height: 35px;
		background: rgba(150, 150, 150, 0.6);
		opacity: 0.8;
		color: #fff;
		font-size: 15px;
		line-height: 35px;
		text-align: center;
		cursor: pointer;
	}

	.btn .prev,
	.main_right_btn .prev {
		position: absolute;
		left: 0;
		top: 50%;
		margin-top: -18px;
		border-radius: 0px 25px 25px 0px;
	}

	.btn .next,
	.main_right_btn .next {
		position: absolute;
		right: 0;
		top: 50%;
		margin-top: -18px;
		border-radius: 25px 0px 0px 25px;
	}

	.item .el-form-item__label {
		color: black;
	}
</style>