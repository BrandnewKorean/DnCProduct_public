<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="resources/css/cat/board/catboardview.css?ver=<%=System.currentTimeMillis()%>">
<meta charset="UTF-8">
<title>BoardView</title>
</head>
<script type="text/javascript" src="resources/script/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="resources/script/writecomment.js?ver=<%= System.currentTimeMillis()%>"></script>

<script type="text/javascript">
function catboarddelete(){
	$.ajax({
		url:'catboarddelete',
		data:{
			seq:$('#seq').val()
		},
		success:function(data){
			if(data.bcode==0){
				alert('삭제 되었습니다.');
				location.href="catboard";
			}else if(data.bcode==1){
				alert('삭제 실패했습니다');
			}else{
				alert('로그인 후 이용해주세요');
				console.log(data.bcode);
 				location.href="catmain";
			}
		}
	});
}

function commentdelete(counter){
	$.ajax({
		url:'commentdelete',
		data:{
			seq:$('#seq').val(),
			counter:counter
		},
		success:function(data){
			if(data.code==0){
				alert('삭제 되었습니다.');
				location.reload();
			}else if(data.code==1){
				alert('삭제 실패했습니다');
			}else{
				alert('로그인 후 이용해주세요');
				console.log(data.code);
 				location.href="catmain";
			}
		}
	});
}

function submitupdate(counter){
    console.log('update counter ='+counter);
	$.ajax({
	url:'commentupdate',
	data:{
		content:$("#updatetext" +counter).val(),
		counter:counter
	},
	success:function(data){
		if(data.code==0){
			$("#ub" +counter).attr("onclick", "commentupdate(${c.counter}, '${c.content}')")
			$(".td1").empty();
			$(".td1").append("<pre>${c.content}</pre>");
			alert('수정성공');
			location.reload();
		}else if(data.code==1){
			alert('수정실패');
		}else{
			alert('로그인 후 이용하세요');
			location.href='catmain';
		}	
	}
  });
}

function commentupdate(counter, content){
	console.log(content);
	$("#td1" +counter).empty();
	$("#td1" +counter).append("<textarea id='updatetext" +counter +"'>" +content +"</textarea>");
	$("#ub" +counter).attr("onclick", "submitupdate(" +counter +")");
}

$(function(){
	var backImg = '';
	var backPos = '';
	var backRe = '';
	var backPosX = '';
	var backSize = '';
	
	$.ajax({
		url: 'catboardmedia',
		data: {
			seq: $('#seq').val()
		},
		success: function(data){
			console.log(data.upload);
			if(data.upload.length > 0){
				$('#viewcontent').append('<div id="viewupload"></div>');
				if(data.upload.length > 1){
					$('#viewupload').append('<button id=left_button><img src="resources/image/left.png" width=100%></button>');
					$('#viewupload').append('<button id=right_button><img src="resources/image/right.png" width=100%></button>');
					for(var i=0;i<data.upload.length;i++){
						if(i == 0){
							backImg += 'url("resources/catboardupload/'+data.upload[i].seq+'_'+data.upload[i].uploadfile+'"), ';
							backSize += 'contain, ';
							backPos += "center center, ";
							backRe += "no-repeat, ";
						}else if(i < data.upload.length-1){
							backImg += 'url("resources/catboardupload/'+data.upload[i].seq+'_'+data.upload[i].uploadfile+'"), ';
							backSize += 'contain, ';
							backPos += 700*i+"px center, ";
							backRe += "no-repeat, ";
						}else{
							backImg += 'url("resources/catboardupload/'+data.upload[i].seq+'_'+data.upload[i].uploadfile+'")';
							backSize += "contain";
							backPos += 700*i+"px center";
							backRe += "no-repeat";
						}
					}
				}else{
					backImg += 'url("resources/catboardupload/'+data.upload[0].seq+'_'+data.upload[0].uploadfile+'")';
					backSize += 'contain';
					backPos += "center center";
					backRe += "no-repeat";
				}
				console.log(backImg);
				$('#viewupload').css({
					backgroundImage: backImg,
					backgroundSize: backSize,
					backgroundPosition: backPos,
					backgroundRepeat: backRe
				});
			}
			var move = 0;
			$('#left_button').css('display','none');
			
			$('#left_button').click(function(){
				backPosX = '';
				move--;
				if(move < 0) move = 0;
				
				if(move < data.upload.length-1) $('#right_button').css('display','block');
				if(move == 0) $('#left_button').css('display','none');
				
				for(var i=0;i<data.upload.length;i++){
					var tmp = (-700*move)+(700*i);
					if(i < data.upload.length-1){
						if(tmp == 0){
							backPosX += "center, ";
						}else{
							backPosX += tmp+"px, ";
						}
					}else{
						if(tmp == 0){
							backPosX += "center";
						}else{
							backPosX += tmp+"px";
						}
					}
				}
				console.log(backPosX);
				$('#viewupload').css({
					backgroundPositionX: backPosX
				}, 1000);
			});
			$('#right_button').click(function(){
				backPosX = '';
				move++;
				if(move > data.upload.length-1)	move = data.upload.length-1;
				
				if(move > 0) $('#left_button').css('display','block');
				if(move == data.upload.length-1) $('#right_button').css('display','none');
				
				for(var i=0;i<data.upload.length;i++){
					var tmp = (-700*move)+(700*i);
					if(i < data.upload.length-1){
						if(tmp == 0){
							backPosX += "center, ";
						}else{
							backPosX += tmp+"px, ";
						}
					}else{
						if(tmp == 0){
							backPosX += "center";
						}else{
							backPosX += tmp+"px";
						}
					}
				}
				console.log(backPosX);
				$('#viewupload').css({
					backgroundPositionX: backPosX
				});
			});
		}
	});
	
	$('#content').on("input",function(){
		console.log($('#content').val().trim());
		if($('#content').val() != ''){
			if($('#content').val().trim().length == 0){
				$('#submit').attr('disabled', true);
			}else{
				$('#submit').attr('disabled', false);
			}
		}
		else $('#submit').attr('disabled', true);
	});
});

function like() {
	$.ajax({
		url:"BoardServlet",
		type:"POST",
		cache:false,
		dataType:"json",
		data:$('#like_form').serialize(),
		success:
			function(data) {
				alert('좋아요가 반영되었습니다.');
				$("#like_result").html(data.like);
			},
			error:
				function(request,status,error) {
					alert('ajax 실패');
				}
	});
}

$(function(){

	$('.heart').click(function(){
		var seq = parseInt($(this).attr('id').substr($(this).attr('id').indexOf('_')+1));
		console.log(seq);
		
		$.ajax({
			url: 'likeCheck',
			data: {seq: seq},
			success: function(data){
				switch(data.code){
				case 0:
					alert('좋아요 취소');
					location.reload();
					break;
				case 1:
					alert('좋아요 취소 실패');
					location.reload();
					break;
				case 2:
					alert('좋아요 추가');
					location.reload();
					break;
				case 3:
					alert('좋아요 추가 실패');
					location.reload();
					break;
				case 4:
					alert('로그인 후 이용하세요');
					location.reload();
					break;
				default:
					alert('error');
					location.reload();
					break;
				}
			}
		});
	});
	
	
});



</script>


<body>
<div class=header>
	<img src="resources/image/logod.png" width=7% onclick="location.href='catboard'">
	
	<div id="header_menu">
		<c:if test="${logID==bv.id}">
			<button onclick="location.href='catboardupdatef?seq=${bv.seq}'">글 수정</button>
			<button onclick="catboarddelete()">글 삭제</button>
	</c:if>
	</div>
</div>


<%-- <c:choose>
	<c:when test="${view}">
		<button onclick="location.href = 'catboard?code=image'">이전으로</button>
	</c:when>
	<c:otherwise>
		<button onclick="location.href = 'catboard?code=list'">이전으로</button>
	</c:otherwise>
</c:choose>
 --%>
 
<!-- <h2>View</h2> -->

<div class="container">
	<input type="hidden" id="seq" value="${bv.seq}">
	<span id="viewtitle">${bv.title}</span>
	<hr>

	<div id="viewcontent">
	${bv.content}
	</div>
	
	<h2 style="color: blue">댓글(${bv.comments})</h2>
	<div>
	<h4>좋아요</h4> 
	<c:choose>
			<c:when test="${islike}">
				<button class=heart id="heart_${bv.seq}" style="background-color: snow; outline:none; border: none;"><img src="/resources/image/heart1.png" width=50px height=50px></button>
			</c:when>
			<c:otherwise>
				<button class=heart id="heart_${bv.seq}" style="background-color: snow; outline:none; border: none;"><img src="/resources/image/heart_empty.png" width=50px height=50px></button>
			</c:otherwise>
		</c:choose>
		${bv.heart}
	</div>	
	<span class=table>
		<c:if test="${comment == '[]'}">
			<div>등록된 댓글이 없습니다</div>
		</c:if>
		<c:if test="${comment != '[]'}">
			<c:forEach var="c" items="${comment}" varStatus="vs">
				<div class="td1" id="td1${c.counter}">
					<span>${c.id} : </span><pre>${c.content}</pre>
				</div>
<%-- 				<div class="td2"><pre>${c.id}</pre></div> --%>
				<c:if test="${logID==c.id}">   
					<button id="ub${c.counter}" onclick="commentupdate(${c.counter}, '${c.content}')">수정</button>
					<button onclick="commentdelete(${c.counter})">삭제</button><br>
				</c:if>
			</c:forEach>
		</c:if>
	</span>
</div>
<hr>
<br><br><br><br>
<div class="commentinput">
	<textarea id=content placeholder="내용"></textarea>
	<button id=submit onclick="writeComment()" disabled="disabled">작성</button>
</div>
</body>
</html>