var selected = null;

$(function(){
	var image = 2;
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var today; 
	if(month < 10){
		if(day < 10) today = year+'-0'+month+'-0'+day;
		else today = year+'-0'+month+'-'+day;
	}else{
		if(day < 10) today = year+'-'+month+'-0'+day;
		else today = year+'-'+month+'-'+day;
	}
	selected = today;
	
	$('#catmainlogo').click(function(){
		location.href = 'catmain';
	});
	
	$('.intro').css({
		backgroundImage: 'url("/ex01/resources/interval/diary/interval'+1+'.jpg")',
	});
	
	$('.intro').animate({
		opacity: "0.8"
	},1000);
	setTimeout(function() {
		$('.intro').animate({
			opacity: "0"
		},1000);
	}, 3000);
	
	setInterval(function() {
		$('.intro').css({
			backgroundImage: 'url("/ex01/resources/interval/diary/interval'+image+'.jpg")',
		});
		$('.intro').animate({
			opacity: "0.8"
		},1000);
		setTimeout(function() {
			$('.intro').animate({
				opacity: "0"
			},1000);
		}, 3000);
		image++;
		if(image > 10) image = 1;
	}, 5100);
	
	$('#year').text(year);
	if(month < 10) $('#month').text('0'+month);
	else $('#month').text(month);
	$('#selected_year').text(year);
	if(month < 10) $('#selected_month').text('0'+month);
	else $('#selected_month').text(month);
	if(day < 10) $('#selected_day').text('0'+day);
	else $('#selected_day').text(day);
	
	build(year, month-1, day);
	loadData(today);
	
	$('.year_btn').click(function(){
		if($(this).text() == '+'){
			$('#year').text(++year);
		}else{
			$('#year').text(--year);
		}
		build(year, month-1, day);
	});
	
	$('.month_btn').click(function(){
		if($(this).text() == '+'){
			if(month < 12){
				if((month+1) < 10) $('#month').text("0"+(++month));
				else $('#month').text(++month);
			}
		}else{
			if(month > 1){
				if((month-1) < 10) $('#month').text("0"+(--month));
				else $('#month').text(--month);
			}
		}
		build(year, month-1, day);
	});
	
	$('#writef').click(function(){
		var result = 1;
		
		if($('#content').text() == "내용이 없습니다"){
			$('#content').empty();
			$('#content').append('<label id=diary_image for=diaryupload><input type="file" id=diaryupload multiple></label><br>');
			$('#content').append('<div id=filepreview></div>');
			$('#content').append('<textarea id=diary_text></textarea><br>');
			$('#content').append('<button id=diarywrite>글쓰기</button>');
		}else{
			if(confirm('기존의 내용을 지우고 다시 입력하시겠습니까?')){
				$.ajax({
					url: 'diarydelete',
					data: {wdate: selected},
					async: false,
					success: function(data){
						if(data.code == 0){
							result = data.code;
						}else if(data.code == 1){
							alert('삭제 실패');
							location.reload();
						}else{
							alert('로그인 후 사용하세요');
							location.href = 'catmain';
						}
					}
				});
				if(result == 0){
					$('#content').empty();
					$('#content').append('<label id=diary_image for=diaryupload><input type="file" id=diaryupload multiple></label><br>');
					$('#content').append('<div id=filepreview></div>');
					$('#content').append('<textarea id=diary_text></textarea><br>');
					$('#content').append('<button id=diarywrite>글쓰기</button>');
				}
			}
		}
				
		$('#diaryupload').change(function(e){
			$('#filepreview').empty();
			var files = e.target.files;
			var fileArr = Array.prototype.slice.call(files);
			
			if(fileArr.length > 1){
				$('#filepreview').append('<button id=pre_button><</button>');
				$('#filepreview').append('<button id=next_button>></button>');
			}
			
			var i = 0;
			fileArr.forEach(function(f){
				if(!f.type.match("image.*")){
					alert('이미지 확장자만 가능합니다');
					return;
				}
				
				var reader = new FileReader();
				reader.onload = function(e){
					if(i == 0){
						$('#filepreview').append('<img id=image'+i+' src="'+e.target.result+'" width=100% height=100% style="display: block;">');
					}else{
						$('#filepreview').append('<img id=image'+i+' src="'+e.target.result+'" width=100% height=100% style="display: none;">');
					}
					
					i++;
				}
				reader.readAsDataURL(f);
			});
			
			i = 0;
			
			$('#pre_button').click(function(){
				i--;
				if(i < 0) i = fileArr.length-1;
				for(var j=0;j<fileArr.length;j++){
					$('#image'+j).css('display','none');
				}
				$('#image'+i).css('display','block');
			});
			
			$('#next_button').click(function(){
				i++;
				if(i > fileArr.length-1) i = 0;
				for(var j=0;j<fileArr.length;j++){
					$('#image'+j).css('display','none');
				}
				$('#image'+i).css('display','block');
			});
		});
		
		$('#diarywrite').click(function(){
			if($('#diary_text').val() == ''){
				alert('내용을 입력해주세요');
				return;
			}
			var formData = new FormData();
			formData.append('wdate', selected);
			formData.append('content', $('#diary_text').val());
			
			for(var i=0;i<$('#diaryupload')[0].files.length;i++){
				formData.append('files',$('#diaryupload')[0].files[i]);
			}
			
			$.ajax({
				url: 'diarywrite',
				type: 'post',
				data: formData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				success: function(data){
					switch(data.code){
					case 0:
						alert('작성 성공');
						location.reload();
						break;
					case 1:
						alert('파일 업로드 실패');
						location.reload();
						break;
					case 2:
						alert('작성 실패');
						location.reload();
						break;
					default:
						alert('로그인 후 사용하세요');
						alert(data.code);
						break;
					}
				}
			});
		});
	});
	
	$('#edit').click(function(){
		var content;
		var uploadlist;
		
		if($('#content').text() == "내용이 없습니다"){
			alert('입력된 내용이 없습니다');
			return;
		}
		$.ajax({
			url: 'diary',
			data: {wdate: selected},
			async: false,
			success: function(data){
				switch(data.code){
				case 0:
					if(data.uploadlist != null) uploadlist = data.uploadlist;
					content = data.dv.content;
					break;
				case 1:
					alert('작성목록 불러오기 실패');
					break;
				default:
					alert('로그인 후 사용하세요');
					location.href = 'catmain';
					break;
				}
			}
		});
		$('#content').empty();
		$('#content').append('<label id=diary_image for=diaryupload><input type="file" id=diaryupload multiple></label><br>');
		
		var i = 0;
		if(uploadlist != null){
			$('#content').append('<div id=filepreview></div>');
			if(uploadlist.length > 1){
				$('#filepreview').append('<button id=pre_button><</button>');
				$('#filepreview').append('<button id=next_button>></button>');
			}
			for(i=0;i<uploadlist.length;i++){
				if(i == 0) $('#filepreview').append('<img id=image'+i+' src="/ex01/resources/diaryupload/'+uploadlist[i].filename+'" width=100% height=100% style="display: block;">');
				else $('#filepreview').append('<img id=image'+i+' src="/ex01/resources/diaryupload/'+uploadlist[i].filename+'" width=100% height=100% style="display: none;">');
			}
		}
		$('#content').append('<textarea id=diary_text>'+content+'</textarea><br>');
		$('#content').append('<button id=diaryupdate>수정</button>');
		
		i = 0;
		
		$('#pre_button').click(function(){
			i--;
			if(i < 0) i = uploadlist.length-1;
			for(var j=0;j<uploadlist.length;j++){
				$('#image'+j).css('display','none');
			}
			$('#image'+i).css('display','block');
		});
		
		$('#next_button').click(function(){
			i++;
			if(i > uploadlist.length-1) i = 0;
			for(var j=0;j<uploadlist.length;j++){
				$('#image'+j).css('display','none');
			}
			$('#image'+i).css('display','block');
		});
		
		$('#diaryupload').change(function(e){
			$('#filepreview').empty();
			var files = e.target.files;
			var fileArr = Array.prototype.slice.call(files);
			
			if(fileArr.length > 1){
				$('#filepreview').append('<button id=pre_button><</button>');
				$('#filepreview').append('<button id=next_button>></button>');
			}
			
			var i = 0;
			fileArr.forEach(function(f){
				if(!f.type.match("image.*")){
					alert('이미지 확장자만 가능합니다');
					return;
				}
				
				var reader = new FileReader();
				reader.onload = function(e){
					if(i == 0){
						$('#filepreview').append('<img id=image'+i+' src="'+e.target.result+'" width=100% height=100% style="display: block;">');
					}else{
						$('#filepreview').append('<img id=image'+i+' src="'+e.target.result+'" width=100% height=100% style="display: none;">');
					}
					
					i++;
				}
				reader.readAsDataURL(f);
			});
			
			i = 0;
			
			$('#pre_button').click(function(){
				i--;
				if(i < 0) i = fileArr.length-1;
				for(var j=0;j<fileArr.length;j++){
					$('#image'+j).css('display','none');
				}
				$('#image'+i).css('display','block');
			});
			
			$('#next_button').click(function(){
				i++;
				if(i > fileArr.length-1) i = 0;
				for(var j=0;j<fileArr.length;j++){
					$('#image'+j).css('display','none');
				}
				$('#image'+i).css('display','block');
			});
		});
		
		$('#diaryupdate').click(function(){
			if($('#diary_text').val() == ''){
				alert('내용을 입력해주세요');
				return;
			}
			var formData = new FormData();
			
			formData.append('wdate',selected);
			formData.append('content',$('#diary_text').val());
			
			for(var i=0;i<$('#diaryupload')[0].files.length;i++){
				formData.append('files',$('#diaryupload')[0].files[i]);
			}
			
			console.log($('#diaryupload')[0].files);
			
			$.ajax({
				url: 'diaryupdate',
				type: 'post',
				data: formData,
				enctype: 'multipart/form-data',
				processData: false,
				contentType: false,
				success: function(data){
					switch(data.code){
					case 0:
						alert('수정 성공');
						location.reload();
						break;
					case 1:
						alert('수정 실패');
						break;
					default:
						alert('로그인 후 사용하세요');
						location.href = 'catmain';
						break;
					}
				}
			});
		});
	});
	
	$('#delete').click(function(){
		if(confirm('정말로 삭제하시겠습니까?')){
			$.ajax({
				url: 'diarydelete',
				data: {wdate: selected},
				success: function(data){
					switch(data.code){
					case 0:
						alert('삭제가 완료되었습니다');
						location.reload();
						break;
					case 1:
						alert('삭제 실패');
						break;
					default:
						alert('로그인 후 사용하세요');
						location.href = 'catmain';
						break;
					}
				}
			});
		}
	});
});

function loadData(selected){
	$('#content').empty();
	$.ajax({
		url: 'diary',
		type: 'post',
		data: {wdate: selected},
		success: function(data){
			switch(data.code){
			case 0:
				var i = 0;
				if(data.uploadlist.length > 0){
					$('#content').append('<div id=filepreview></div>');
					if(data.uploadlist.length > 1){
						$('#filepreview').append('<button id=pre_button><</button>');
						$('#filepreview').append('<button id=next_button>></button>');
					}
					for(i=0;i<data.uploadlist.length;i++){
						if(i == 0) $('#filepreview').append('<img id=image'+i+' src="/ex01/resources/diaryupload/'+data.uploadlist[i].filename+'" width=100% height=100% style="display: block;">');
						else $('#filepreview').append('<img id=image'+i+' src="/ex01/resources/diaryupload/'+data.uploadlist[i].filename+'" width=100% height=100% style="display: none;">');
					}
				}
				$('#content').append("<pre>"+data.dv.content+"</pre>");
				
				i = 0;
				
				$('#pre_button').click(function(){
					i--;
					if(i < 0) i = data.uploadlist.length-1;
					for(var j=0;j<data.uploadlist.length;j++){
						$('#image'+j).css('display','none');
					}
					$('#image'+i).css('display','block');
					console.log('pre click '+i);
				});
				
				$('#next_button').click(function(){
					i++;
					if(i > data.uploadlist.length-1) i = 0;
					for(var j=0;j<data.uploadlist.length;j++){
						$('#image'+j).css('display','none');
					}
					$('#image'+i).css('display','block');
				});
				
				break;
			case 1:
				$('#content').append("<pre>내용이 없습니다</pre>");
				break;
			case 2:
				alert('로그인 후 사용하세요');
				location.href = "catmain";
				break;
			}
		}
	});
}

function build(y, m, d){
	var week = ['일','월','화','수','목','금','토'];
	var cal = new Date(y,m,1);
	var start = cal.getDay();
	var end;
	var selectedYear = parseInt($('#selected_year').text());
	var selectedMonth = parseInt($('#selected_month').text());
	var selectedDay = parseInt($('#selected_day').text());
	selected = $('#selected_year').text()+"-"+$('#selected_month').text()+"-"+$('#selected_day').text();
	
	switch(m+1){
	case 1: case 3: case 5: case 7: case 8: case 10: case 12:
		end = 31;
		break;
	case 4: case 6: case 9: case 11:
		end = 30;
		break;
	case 2:
		if((year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)){
			end = 29;
		}else{
			end = 28;
		}
		break;
	}
	
	$('#calendar').empty();
	$('#calendar').append('<div class=table_row></div>');
	
	for(var i=0;i<7;i++){
		$('#calendar').append('<div class=week>'+week[i]+'</div>');
	}
	
	for(var i=0;i<42;i++){
		if(i % 7 == 0) $('#calendar').append('<div class=table_row></div>');
		if(i>=start && (i-start+1)<=end){
			var d;
			if((i-start+1) < 10) d = $('#year').text()+'-'+$('#month').text()+'-0'+(i-start+1);
			else d = $('#year').text()+'-'+$('#month').text()+'-'+(i-start+1);
			
			$('#calendar').append('<div class=days id='+d+'>'+(i-start+1)+'</div>');
			
			if(y == selectedYear && (m+1) == selectedMonth && (i-start+1) == selectedDay){
				$('#'+d).css({
					backgroundColor: "red"
				});
			}
			
			if(y == selectedYear && (m+1) == selectedMonth && (i-start+1) == selectedDay){
				$('#'+d).css({
					backgroundColor: "red"
				});
			}
		}else{
			$('#calendar').append('<div class=days>&nbsp;</div>');
		}
	}
	
	var startday = $('#year').text()+'-'+$('#month').text()+'-01';
	var endday = $('#year').text()+'-'+$('#month').text()+'-'+end;
	
	$.ajax({
		url: 'iswrited',
		data:{
			start: startday,
			end: endday
		},
		success: function(data){
			for(var i=0;i<data.writed.length;i++){
				$('#'+data.writed[i]).css({
					backgroundImage: 'url("resources/image/cat_footprint.png")',
					backgroundSize: "contain",
					backgroundRepeat: "no-repeat"
				});
			}
		}
	});
	
	$('.days').click(function(){
		if($(this).html() != '&nbsp;'){
			$('.days').css({
				backgroundColor: "yellow"
			});
			$(this).css({
				backgroundColor: "red"
			});
			$('#selected_year').text(y);
			if((m+1) < 10) $('#selected_month').text("0"+(m+1));
			else $('#selected_month').text(m+1);
			if(parseInt($(this).text()) < 10) $('#selected_day').text("0"+$(this).text());
			else $('#selected_day').text($(this).text());
			selectedYear = parseInt($('#selected_year').text());
			selectedMonth = parseInt($('#selected_month').text());
			selectedDay = parseInt($('#selected_day').text());
			selected = $('#selected_year').text()+"-"+$('#selected_month').text()+"-"+$('#selected_day').text();
			loadData(selected);
		}
	});
}