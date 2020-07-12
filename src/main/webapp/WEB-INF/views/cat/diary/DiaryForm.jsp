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
	<div class="header">
		<img id="catmainlogo" src="resources/image/logob.png" width=7%>
	</div>
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
					<a id=selected_year></a>년<a id=selected_month></a>월<a id=selected_day></a>일<br>
					<label for=diary_upload id=diary_upload_label title="이미지 업로드">+<input id=diary_upload type='file'></label>
					<textarea id=write placeholder="여기에 내용을 입력해주세요"></textarea><br>
					<button>글쓰기</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>