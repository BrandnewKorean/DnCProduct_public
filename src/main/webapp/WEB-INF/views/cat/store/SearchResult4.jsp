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
	
	$('.result4_page').click(function(){
		var i = parseInt($(this).text());
		var str = "${pageMaker4.makeSearch(1)}";
		console.log(str);
		
		$.ajax({
			url: "searchresult4",
			data:{
				currentPage: i,
				perPage: 10,
				keyword: "${search4.keyword}"
			},
			beforeSend: function(){
				$('#result4').append('<div style="position:absolute; left: 50%; top: 25%; width: 100%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result4').html(result);
			},
		});
	});
});
</script>
</head>
<body>
	<c:choose>
		<c:when test='${list4.size() > 0}'>
			<c:forEach var="pl4" items='${list4}'>
				<div class="products" id="${pl4.productcode}_${pl4.seq}">
					<a class=group>분류 : ${pl4.group1} > ${pl4.group2}</a>
					<div class=product_innerimg>
						<img class=product_image src="resources/productimage/${productimageMap4.get(pl4.seq).get(0).filename}" width=200px height=200px>
					</div>
					${productMap4.get(pl4.seq).name}<br>
					<a class=brand>『${productMap4.get(pl4.seq).brand}』</a><br>
					<a class=price>${priceMap4.get(pl4.seq)}</a>원
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>결과가 없습니다</c:otherwise>
	</c:choose>
	<div class=blocks>
		<c:if test='${pageMaker4.prev}'>
			<a href='catstoreview${pageMaker4.makeSearch(1)}&group1=${search4.group1}'>First</a>
			<a href='catstoreview${pageMaker4.makeSearch(pageMaker4.startPageNo-1)}&group1=${search4.group1}&group2=${search4.group2}'>Prev&nbsp;</a>
		</c:if>
		<c:forEach begin='${pageMaker4.startPageNo}' end='${pageMaker4.endPageNo}' var="i">
			<c:choose>
				<c:when test='${pageMaker4.search.currentPage==i}'>
					<font size="5" color="orange">${i}</font>&nbsp;
				</c:when>
				<c:otherwise>
					<button class=result4_page>${i}</button>
<%-- 					<a href='searchresult${pageMaker4.makeSearch(i)}'>${i}</a>&nbsp; --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test='${pageMaker4.next && pageMaker4.endPageNo > 0}'>
			<a href='catstoreview${pageMaker4.makeSearch(pageMaker4.endPageNo+1)}&group1=${search4.group1}&group2=${search4.group2}'>Next&nbsp;&nbsp;</a>
			<a href='catstoreview${pageMaker4.makeSearch(pageMaker4.lastPageNo)}&group1=${search4.group1}&group2=${search4.group2}'>End&nbsp;&nbsp;</a>
		</c:if>
	</div>
</body>
</html>