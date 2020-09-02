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
<script>
$(function(){
	var number = 1;
	var price = new Array();
	$('.prices').each(function(){
		price.push(parseInt($(this).text()));
	});
	console.log(price);
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
					<a class=prices>${storeMap.get(list.seq).price}</a><br>
					<button class=number_buttons id=number_minus>-</button><input id=number_input type="text"><button class=number_buttons id=number_plus>+</button><br>
					총 <a id=number></a> 개 <a id=total_price></a>원<br>
					<button class=buy>구매하기</button>
				</span>
			</div>
		</c:forEach>
	</div>
	<div id=total>
		Total 영역
	</div>
	<jsp:include page="StoreFooter.jsp"></jsp:include>
</body>
</html>