<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>[${search.keyword}] 검색결과</title>
<link rel="stylesheet" type="text/css" href="resources/css/cat/store/SearchResult.css?ver=<%= System.currentTimeMillis()%>">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript">
$(function(){
	var keyword = "${search.keyword}"
	
	$.ajax({
		url: "searchresult1",
		data: {
			keyword: keyword
		},
		beforeSend: function(){
			$('#result1').append('<img src="resources/image/catloading.gif" style="position:relative; top:25%; height:50%;">')
		},
		success: function(result){
			$('#result1').html(result);
		},
		error: function(){
			alert('error');
		}
	});
});
</script>
</head>
<body>
	<jsp:include page="StoreHeader.jsp"></jsp:include>
	<div class=container>
		<a class=group1_title>[식료품] 검색결과</a><br>
		<div class=result id=result1></div>
		<a class=group1_title>[배변/위생용품] 검색결과</a><br>
		<div class=result id=result2></div>
		<a class=group1_title>[미용용품] 검색결과</a><br>
		<div class=result id=result3></div>
		<a class=group1_title>[생활용품] 검색결과</a><br>
		<div class=result id=reuslt4></div>
	</div>
</body>
</html>