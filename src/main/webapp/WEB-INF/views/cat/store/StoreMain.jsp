<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cat Store</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/StoreMain.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="/resources/script/storemain.js?ver=<%= System.currentTimeMillis() %>>"></script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div class=intro>
		<div id=ad_banner>
			<ul class=slide_wrap>
				<li class=slide_item><a href="products?productcode=cat03010003&seq=163"><img class=item src="/resources/catstoreevent/choose-cat-brush.jpg"></a></li>
				<li class=slide_item><a href="products?productcode=cat02020011&seq=131"><img class=item src="/resources/catstoreevent/catstoreevent2.png"></a></li>
				<li class=slide_item><a href="products?productcode=cat01010041&seq=41"><img class=item src="/resources/catstoreevent/saryo.jpg"></a></li>
				<li class=slide_item><a href="products?productcode=cat04030008&seq=288"><img class=item src="/resources/catstoreevent/cattower.PNG"></a></li>
			</ul>
		</div>
		<div id=ad_banner_remote>
			<button class=banner_btn id=banner_left>&#60;</button>
			<button class=banner_btn id=banner_right>&#62;</button>
		</div>
	</div>
	<div class="container">
		<div class=top5>
			<div id=top5_menu>
				<font size="10">TOP 5</font>
				<button id=food class=top5_buttons>식료품</button>
				<button id=medic class=top5_buttons>배변/위생용품</button>
				<button id=beauty class=top5_buttons>미용용품</button>
				<button id=live class=top5_buttons>생활용품</button>
			</div>
			<div id=top5_contents>
				<div class=top id=t1>
				</div>
				<div class=top id=t2>
				</div>
				<div class=top id=t3>
				</div>
				<div class=top id=t4>
				</div>
				<div class=top id=t5>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="StoreFooter.jsp"></jsp:include>
</body>
</html>