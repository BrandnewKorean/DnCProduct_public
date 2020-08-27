<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${search.group2} - CatStore</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/CatStoreView.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script>
$(function(){
	$('.products').click(function(){
		location.href = "products?productcode="+$(this).attr('id').substr(0,$(this).attr('id').indexOf('_'))+"&seq="+$(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
	});
	
	$('#keyword_in_result').keypress(function(e){
		if(e.keyCode == 13) $('#search_in_resultButton').click();
	});
	
	$('#search_in_resultButton').click(function(){
		var keyword = $('#keyword_in_result').val();
		var group1 = "${search.group1}";
		var group2 = "${search.group2}";
		
		self.location = "catstoreview?"
				+"keyword="+keyword
				+"&group1="+group1
				+"&group2="+group2;
	});
});
</script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div class=container>
		<c:choose>
			<c:when test="${list.size() > 0}">
				<input type="text" id="keyword_in_result"> <button id=search_in_resultButton>검색</button><br>
				<c:forEach var="pl" items="${list}">
					<div class=products id="${pl.productcode}_${pl.seq}">
						<div class=product_innerimg>
							<c:if test="${productimageMap.get(pl.seq).size() > 0}">
								<img class=product_image src="resources/productimage/${productimageMap.get(pl.seq).get(0).filename}" width=200px height=200px>
							</c:if>
						</div>
						${productMap.get(pl.seq).name}<br>
						<a class=brand>『${productMap.get(pl.seq).brand}』</a><br>
						<a class=price>${priceMap.get(pl.seq).toString()}</a>원
					</div>
				</c:forEach>
			</c:when>
			<c:otherwise>
				등록된 상품이 없습니다
			</c:otherwise>
		</c:choose>
	</div>
	<div class=blocks>
		<c:if test="${pageMaker.prev}">
			<a href="catstoreview${pageMaker.makeSearch(1)}&group1=${search.group1}&group2=${search.group2}">First</a>
			<a href="catstoreview${pageMaker.makeSearch(pageMaker.startPageNo-1)}&group1=${search.group1}&group2=${search.group2}">Prev&nbsp;</a>
		</c:if>
		<c:forEach begin="${pageMaker.startPageNo}" end="${pageMaker.endPageNo}" var="i">
			<c:choose>
				<c:when test="${pageMaker.search.currentPage==i}">
					<font size="5" color="orange">${i}</font>&nbsp;
				</c:when>
				<c:otherwise>
					<a href="catstoreview${pageMaker.makeSearch(i)}&group1=${search.group1}&group2=${search.group2}">${i}</a>&nbsp;
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test="${pageMaker.next && pageMaker.endPageNo > 0}">
			<a href="catstoreview${pageMaker.makeSearch(pageMaker.endPageNo+1)}&group1=${search.group1}&group2=${search.group2}">Next&nbsp;&nbsp;</a>
			<a href="catstoreview${pageMaker.makeSearch(pageMaker.lastPageNo)}&group1=${search.group1}&group2=${search.group2}">End&nbsp;&nbsp;</a>
		</c:if>
	</div>
	<br><br><br>
</body>
</html>