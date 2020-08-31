<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/cat/diary/DiaryForm.css?ver=<%= System.currentTimeMillis()%>">
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script type="text/javascript" src="resources/script/diary.js?ver=<%= System.currentTimeMillis()%>"></script>
<title>Diary</title>
</head>
<body>
	<jsp:include page="../../Header.jsp"></jsp:include>
	<div class=intro>
	</div>
	<div class=container>
		<div id=diary>
			<div id=paper_range>
				<div id=calendar_wrap>
					<button class=year_btn>-</button><a id=year></a><button class=year_btn>+</button>
					<button class=month_btn>-</button><a id=month></a><button class=month_btn>+</button>
					<div id=calendar></div>
				</div>
				<div id=write_wrap>
					<div id=diary_menu>
						<label for=diarywritef class=button_label id=writef_label><button id=diarywritef title="글쓰기">글쓰기</button></label>
						<label for=diaryedit class=button_label id=edit_label><button id=diaryedit title="수정하기">수정하기</button></label>
						<label for=diarydelete class=button_label id=delete_label><button id=diarydelete title="삭제">삭제하기</button></label>
					</div>
					<a id=selected_year></a>년<a id=selected_month></a>월<a id=selected_day></a>일<br>
					<div id=content>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../../Footer.jsp"></jsp:include>
</body>
</html>