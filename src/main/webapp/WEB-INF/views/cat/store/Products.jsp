<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${pv.name}</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/Products.css?ver=<%= System.currentTimeMillis()%>">
</head>
<body>
	<jsp:include page="StoreHeader.jsp"/>
	<div class=container>
		<input type="hidden" id=seq value="${cs.seq}">
		<input type="hidden" id=productcode value="${pv.productcode}">
		<div id=product_image>
			<c:if test="${imagelist.size() > 0}">
				<img id=image_view src="resources/productimage/${imagelist.get(0).filename}" width=100% height=100%>
				<div id=product_image_button></div>
			</c:if>
		</div>
		<div id=product_info>
			<a id=product_name>${pv.name}</a><br>
			<a id=product_price>${price}</a>원<br>
			<hr>
			<div id=product_detail>
				상품 상세정보
			</div>
			<div id=buy_buttons>
				<button class=number_buttons id=number_minus>-</button><input id=number_input type="text"><button class=number_buttons id=number_plus>+</button><br>
				총 <a id=number></a> 개 <a id=total_price></a>원<br>
				<button id=shopping_basket>장바구니에 담기</button> <button id=payment>구매하기</button>
			</div>
		</div>
	</div>
<%-- 	<jsp:include page="StoreFooter.jsp"></jsp:include> --%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/DecimalFormat.js"></script>
<script type="text/javascript" src="resources/script/products.js?ver=<%= System.currentTimeMillis()%>"></script>
<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript" src="resources/script/payment.js?ver=<%= System.currentTimeMillis()%>"></script>
</body>
</html>