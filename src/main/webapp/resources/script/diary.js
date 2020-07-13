$(function(){
	var image = 2;
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var today = year+"-"+month+"-"+day;
	
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
	$('#month').text(month);
	$('#selected_year').text(year);
	$('#selected_month').text(month);
	$('#selected_day').text(day);
	
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
			if(month < 12) $('#month').text(++month);
		}else{
			if(month > 1) $('#month').text(--month);
		}
		build(year, month-1, day);
	});
	
});

function loadData(selected){
	console.log(selected);
	$.ajax({
		url: 'diary',
		type: 'post',
		data: {wdate: selected},
		success: function(data){
			switch(data.code){
			case 0:
				$('#content').html(data.dv.content);
				break;
			case 1:
				$('#content').html("내용이 없습니다");
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
	var selected = selectedYear+"-"+selectedMonth+"-"+selectedDay;
	
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
			if(y == selectedYear && (m+1) == selectedMonth){
				if((i-start+1) == selectedDay){
					$('#calendar').append('<div class=days style="background-color:red;">'+(i-start+1)+'</div>');
				}else{
					$('#calendar').append('<div class=days>'+(i-start+1)+'</div>');
				}
			}else{
				$('#calendar').append('<div class=days>'+(i-start+1)+'</div>');
			}
		}else{
			$('#calendar').append('<div class=days>&nbsp;</div>');
		}
	}
	
	$('.days').click(function(){
		if($(this).html() != '&nbsp;'){
			$('.days').css({
				backgroundColor: "yellow"
			});
			$(this).css({
				backgroundColor: "red"
			});
			$('#selected_year').text(y);
			$('#selected_month').text(m+1);
			$('#selected_day').text($(this).text());
			selectedYear = parseInt($('#selected_year').text());
			selectedMonth = parseInt($('#selected_month').text());
			selectedDay = parseInt($('#selected_day').text());
			selected = selectedYear+"-"+selectedMonth+"-"+selectedDay;
			loadData(selected);
		}
	});
}