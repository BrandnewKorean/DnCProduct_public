<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BoardView</title>
</head>
<body>
	<span>${bv.title}</span>
	<hr>	
	<textarea>${bv.content}</textarea>
</body>
</html>