<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="http://localhost:9000/banana/js/jquery-3.5.1.min.js"></script>
<script>
	$(document).ready(function(){
		$(".review_all").click(function(){
			$(".review_all_table_area").show();
			$(".review_seller_table_area").hide();
			$(".review_buyer_table_area").hide();
			$(".review_all").css("color","black").css("border-bottom","4px solid black");
			$(".review_seller").css("color","#ccc").css("border-bottom","1px solid #ccc");
			$(".review_buyer").css("color","#ccc").css("border-bottom","1px solid #ccc");
		});
		
		$(".review_seller").click(function(){
			$(".review_all_table_area").hide();
			$(".review_seller_table_area").show();
			$(".review_buyer_table_area").hide();
			$(".review_all").css("color","#ccc").css("border-bottom","1px solid #ccc");
			$(".review_seller").css("color","black").css("border-bottom","4px solid black");
			$(".review_buyer").css("color","#ccc").css("border-bottom","1px solid #ccc");
		});
		
		$(".review_buyer").click(function(){
			$(".review_all_table_area").hide();
			$(".review_seller_table_area").hide();
			$(".review_buyer_table_area").show();
			$(".review_all").css("color","#ccc").css("border-bottom","1px solid #ccc");
			$(".review_seller").css("color","#ccc").css("border-bottom","1px solid #ccc");
			$(".review_buyer").css("color","black").css("border-bottom","4px solid black");
		});
	});
</script>
<style>
	.mypage_review {
		text-align: center;
	}
	
	.review_change_area .review_title {
		text-align: left;
		margin-top: 50px;
		margin-left: 50px;
		font-size: 2rem;
		font-weight: bold;
	}
	
	.review_change_area div button {
		all: unset;
		font-size: 2rem;
		margin-top: 50px;
		width: 13.5rem;
		height: 7rem;
		color: #ccc;
		border-bottom: 1px solid #ccc;
		font-weight: bold;
	}
	
	.review_change_area div .review_all {
		color: black;
		border-bottom: 4px solid black;
	}
	
	.review_change_area div .review_all:active,
	.review_change_area div .review_seller:active,
	.review_change_area div .review_buyer:active {
		background-color: #FEE500;
		opacity: 0.7;
	}
	
	.review_change_area div .review_seller {
		margin-left: -5px;
	}
	
	.mypage_review .review_all_table_area,
	.mypage_review .review_seller_table_area,
	.mypage_review .review_buyer_table_area {
		display: flex;
		justify-content: center;
	}
	
	.mypage_review .review_all_table_area .review_all_table,
	.mypage_review .review_seller_table_area .review_seller_table, 
	.mypage_review .review_buyer_table_area .review_buyer_table {
		border-bottom: 1px solid #ccc;
		width: 650px;
	}
	
	.mypage_review .review_all_table_area .review_all_table tr td,
	.mypage_review .review_seller_table_area .review_seller_table tr td,
	.mypage_review .review_buyer_table_area .review_buyer_table tr td {
		font-size: 2.1rem;
	}
	td.review{
		margin-top:10px;
		float:left;
	}
	td.review>img{
		width:35px;
		height:35px;
		margin-left:30px;
		border-radius:50%;
     	border:2px solid #fff;
      	box-shadow: 0 0 16px rgb(221,221,221);
	}
	td.review>label{
		position: relative;
        top: -11px;
        color: rgb(98,71,24);
        font-weight:550;
        font-size:20px;
	}
	td.review>label:nth-child(3){
		margin-left:6px;
        font-size:15px;
        font-weight:500;
        position: relative;
        top:-10px;
	}
	td.review>label.review{
		margin-left:-70px;
		font-weight:normal;
	}
	td.review>label.date{
		font-size:15px;
		margin-left:-70px;
		color:lightgray;
	}
</style>
</head>
<body>
	<div class="mypage_review">
		<section class="review_change_area">
			<div class="review_title">받은 거래 후기</div>
			<div>
				<button type="button" class="review_all">전체후기</button>
				<button type="button" class="review_seller">판매자 후기</button>
				<button type="button" class="review_buyer">구매자 후기</button>
			</div>
		</section>
		<section class="review_all_table_area">
			<div>
			<table class="review_all_table">
				<!-- <tr>
					<td style="padding: 230px 0; color: #ccc;">전체후기가 없습니다.</td>
				</tr> -->
				<tr>
					<td class="review">
						<img src="images/banana.jpg">
						<label>히히</label>
						<label>이의동</label><div></div>
						<label class="review">내용</label><div></div>
						<label class="date">30초전</label>
					</td>
				</tr>
			</table>
			</div>
		</section>
		<section class="review_seller_table_area" style="display: none;">
			<div>
			<table class="review_seller_table">
				<tr>
					<td style="padding: 230px 0; color: #ccc;">판매자 후기가 없습니다.</td>
				</tr> 
			</table>
			</div>
		</section>
		<section class="review_buyer_table_area" style="display: none;">
			<div>
			<table class="review_buyer_table">
				<tr>
					<td style="padding: 230px 0; color: #ccc;">구매자 후기가 없습니다.</td>
				</tr> 
			</table>
			</div>
		</section>
	</div>
</body>
</html>