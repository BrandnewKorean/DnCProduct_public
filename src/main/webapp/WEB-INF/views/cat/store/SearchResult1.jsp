<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>food</title>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){
	$('.products').click(function(){
		location.href = "products?productcode="+$(this).attr('id').substr(0,$(this).attr('id').indexOf('_'))+"&seq="+$(this).attr('id').substr($(this).attr('id').indexOf('_')+1);
	});
	
	$('.food').click(function(){
		var i = parseInt($(this).text());
		var str = "${pageMaker.makeSearch(1)}";
		console.log(str);
		
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: i,
				perPage: 10,
				keyword: "${search.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			},
		});
	});
});
</script>
</head>
<body>
	<c:choose>
		<c:when test='${list.size() > 0}'>
			<c:forEach var="pl" items='${list}'>
				<div class="products" id="${pl.productcode}_${pl.seq}">
					<div class=product_innerimg>
						<img class=product_image src="resources/productimage/${productimageMap.get(pl.seq).get(0).filename}" width=200px height=200px>
					</div>
					${productMap.get(pl.seq).name}<br>
					<a class=brand>『${productMap.get(pl.seq).brand}』</a><br>
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>결과가 없습니다</c:otherwise>
	</c:choose>
	<div class=blocks>
		<c:if test='${pageMaker.prev}'>
			<a href='catstoreview${pageMaker.makeSearch(1)}&group1=${search.group1}'>First</a>
			<a href='catstoreview${pageMaker.makeSearch(pageMaker.startPageNo-1)}&group1=${search.group1}&group2=${search.group2}'>Prev&nbsp;</a>
		</c:if>
		<c:forEach begin='${pageMaker.startPageNo}' end='${pageMaker.endPageNo}' var="i">
			<c:choose>
				<c:when test='${pageMaker.search.currentPage==i}'>
					<font size="5" color="orange">${i}</font>&nbsp;
				</c:when>
				<c:otherwise>
					<button class=food>${i}</button>
<%-- 					<a href='searchresult${pageMaker.makeSearch(i)}'>${i}</a>&nbsp; --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test='${pageMaker.next && pageMaker.endPageNo > 0}'>
			<a href='catstoreview${pageMaker.makeSearch(pageMaker.endPageNo+1)}&group1=${search.group1}&group2=${search.group2}'>Next&nbsp;&nbsp;</a>
			<a href='catstoreview${pageMaker.makeSearch(pageMaker.lastPageNo)}&group1=${search.group1}&group2=${search.group2}'>End&nbsp;&nbsp;</a>
		</c:if>
	</div>
</body>
</html>