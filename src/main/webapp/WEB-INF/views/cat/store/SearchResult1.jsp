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
	
	$('.result1_page').click(function(){
		var i = parseInt($(this).text());
		var str = "${pageMaker1.makeSearch(1)}";
		console.log(str);
		
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: i,
				perPage: 10,
				keyword: "${search1.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			},
		});
	});
	
	$('#result1_first_page').click(function(){
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: 1,
				perPage: 10,
				keyword: "${search1.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			}
		});
	});
	
	$('#result1_prev_page').click(function(){
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: "${pageMaker1.startPageNo-1}",
				perPage: 10,
				keyword: "${search1.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			}
		});
	});
	
	$('#result1_next_page').click(function(){
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: "${pageMaker1.endPageNo+1}",
				perPage: 10,
				keyword: "${search1.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			}
		});
	});
	
	$('#result1_end_page').click(function(){
		$.ajax({
			url: "searchresult1",
			data:{
				currentPage: "${pageMaker1.lastPageNo}",
				perPage: 10,
				keyword: "${search1.keyword}"
			},
			beforeSend: function(){
				$('#result1').append('<div style="position:absolute; left: 50%; top: 25%; height: 100%; z-index: 1;"><img src="resources/image/catloading.gif" style="position:relative; left: -50%; height: 50%;"></div>')
			},
			success: function(result){
				$('#result1').html(result);
			}
		});
	});
});
</script>
</head>
<body>
	<span style="color: red;">
		총 ${pageMaker1.getTotalRow()} 개의 상품 중 ${list1.size()}개의 상품이 검색되었습니다.
	</span>	
	<br>
	<c:choose>
		<c:when test='${list1.size() > 0}'>
			<c:forEach var="pl1" items='${list1}'>
				<div class="products" id="${pl1.productcode}_${pl1.seq}">
					<a class=group>분류 : ${pl1.group1} > ${pl1.group2}</a>
					<div class=product_innerimg>
						<img class=product_image src="resources/productimage/${productimageMap1.get(pl1.seq).get(0).filename}" width=200px height=200px>
					</div>
					${productMap1.get(pl1.seq).name}<br>
					<a class=brand>『${productMap1.get(pl1.seq).brand}』</a><br>
					<a class=price>${priceMap1.get(pl1.seq)}</a>원
				</div>
			</c:forEach>
		</c:when>
		<c:otherwise>결과가 없습니다</c:otherwise>
	</c:choose>
	<div class=blocks>
		<c:if test='${pageMaker1.prev}'>
			<button id=result1_next_page>Next</button>
			<button id=result1_end_page>End</button>
		</c:if>
		<c:forEach begin='${pageMaker1.startPageNo}' end='${pageMaker1.endPageNo}' var="i">
			<c:choose>
				<c:when test='${pageMaker1.search.currentPage==i}'>
					<font size="5" color="orange">${i}</font>&nbsp;
				</c:when>
				<c:otherwise>
					<button class=result1_page>${i}</button>
<%-- 					<a href='searchresult${pageMaker.makeSearch(i)}'>${i}</a>&nbsp; --%>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		<c:if test='${pageMaker1.next && pageMaker1.endPageNo > 0}'>
			<button id=result1_next_page>Next</button>
			<button id=result1_end_page>End</button>
		</c:if>
	</div>
</body>
</html>