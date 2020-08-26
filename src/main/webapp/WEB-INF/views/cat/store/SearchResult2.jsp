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
	
	$('.result2_page').click(function(){
		var i = parseInt($(this).text());
		var str = "${pageMaker2.makeSearch(1)}";
		console.log(str);
		
		$.ajax({
			url: "searchresult2",
			data:{
				currentPage: i,
				perPage: 10,
				keyword: "${search2.keyword}"
			},
			beforeSend: function(){
				$('#result2').append('<div style="position:absolute; left: 50%; top: 25%; width: 100%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result2').html(result);
			},
		});
	});
});
</script>
</head>
<body>
	<c:choose>
		<c:when test='${list2.size() > 0}'>
			<c:forEach var="pl2" items='${list2}'>
				<div class="products" id="${pl2.productcode}_${pl2.seq}">
					<a class=group>분류 : ${pl2.group1} > ${pl2.group2}</a>
					<div class=product_innerimg>
						<img class=product_image src="resources/productimage/${productimageMap2.get(pl2.seq).get(0).filename}" width=200px height=200px>
					</div>
					${productMap2.get(pl2.seq).name}<br>
					<a class=brand>『${productMap2.get(pl2.seq).brand}』</a><br>
					<a class=price>${priceMap2.get(pl2.seq)}</a>원
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>결과가 없습니다</c:otherwise>
	</c:choose>
	<div class=blocks>
		<c:if test='${pageMaker2.prev}'>
			<a href='catstoreview${pageMaker2.makeSearch(1)}&group1=${search2.group1}'>First</a>
			<a href='catstoreview${pageMaker2.makeSearch(pageMaker2.startPageNo-1)}&group1=${search2.group1}&group2=${search2.group2}'>Prev&nbsp;</a>
		</c:if>
		<c:forEach begin='${pageMaker2.startPageNo}' end='${pageMaker2.endPageNo}' var="i">
			<c:choose>
				<c:when test='${pageMaker2.search.currentPage==i}'>
					<font size="5" color="orange">${i}</font>&nbsp;
				</c:when>
				<c:otherwise>
					<button class=result2_page>${i}</button>
<%-- 					<a href='searchresult${pageMaker2.makeSearch(i)}'>${i}</a>&nbsp; --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test='${pageMaker2.next && pageMaker2.endPageNo > 0}'>
			<a href='catstoreview${pageMaker2.makeSearch(pageMaker2.endPageNo+1)}&group1=${search2.group1}&group2=${search2.group2}'>Next&nbsp;&nbsp;</a>
			<a href='catstoreview${pageMaker2.makeSearch(pageMaker2.lastPageNo)}&group1=${search2.group1}&group2=${search2.group2}'>End&nbsp;&nbsp;</a>
		</c:if>
	</div>
</body>
</html>