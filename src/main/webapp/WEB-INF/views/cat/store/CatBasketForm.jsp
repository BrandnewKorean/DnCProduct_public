<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<style>
#table {width: 60%; display: inline-table; margin: 0 0 0 5%; text-align: center;}

.cell {position:relative; display: table-cell; padding: 3px; border-bottom: 1px solid #DDD;}

.check{
	width: 25px;
	height: 25px;
	border: none;
}

.row {display: table-row;}

.col1 {
	width: 10%;
	vertical-align: top;
	text-align: right;
}

.col2 {width: 10%;}

.col3 {
	font-size:20pt;
	width: 40%;
	vertical-align: middle;
}

.col4 {
	width: 10%;
	vertical-align: middle;
}

.col5 {
	width: 10%;
	vertical-align: middle;
}

#total{
	display:inline-block;
	float: right;
	background-color: yellow;
	margin: 0 5% 0 0;
	width: 30%;
}
</style>
<head>
<meta charset="UTF-8">
<title>장바구니</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/DecimalFormat.js"></script>
<script>
$(function(){
	var number = 1;
	var price = new Array();
	$('.product_price').each(function(){
		price.push(parseInt($(this).text()));
	});
	
	var df = new DecimalFormat("###,###");
	
	$('#number').text(number);
	$('#total_price').text(df.format(price[0]));
	$('#number_input').val(number);
	
	$('#number_minus').click(function(){
		number--;
		if(number <= 0) number = 1;
		$('#number').text(number);
		$('#number_input').val(number);
		$('#total_price').text(df.format(number*price[0]));
	});
	
	$('#number_plus').click(function(){
		number++;
		$('#number').text(number);
		$('#number_input').val(number);
		$('#total_price').text(df.format(number*price[0]));
	});
	
	$('#number_input').focusout(function() {
		if(parseInt($('#number_input').val())){
			if(parseInt($('#number_input').val()) < 1){
				alert('수량은 1이상만 입력 가능합니다');
				$('#number_input').val(number);
			}else{
				number = parseInt($('#number_input').val());
			}
		}else{
			alert('잘못 입력');
			$('#number_input').val(number);
		}
		
		$('#number').text(number);
		$('#total_price').text(df.format(number*price[0]));
	});
	
});

</script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<h1>장바구니</h1>
	<div id="table">
		<c:forEach var="list" items="${list}">
			<div class="row">
				<span class="cell col1">
					<input class=check type="checkbox">
				</span>
				<span class="cell col2">
					<img src="/resources/productimage/${imageMap.get(list.seq).filename}" width="100%">
				</span>
				<span class="cell col3">
					${productMap.get(list.seq).name}
				</span>
				<span class="cell col4">
					<a class=product_price>${storeMap.get(list.seq).price}</a><br>
					<button class=number_buttons id=number_plus>+</button>
					<input id=number_input type="text" size="10">
					<button class=number_buttons id=number_minus>-</button><br>
					총 <a id=number></a> 개
					<a id=total_price></a>원<br>	
					<button class=payment>구매하기</button>
				</span>
			</div>
		</c:forEach>
	</div>
<!-- 	<div id=total> -->
<!-- 		Total 영역 -->
<!-- 	</div> -->
	<jsp:include page="StoreFooter.jsp"></jsp:include>
</body>
</html>