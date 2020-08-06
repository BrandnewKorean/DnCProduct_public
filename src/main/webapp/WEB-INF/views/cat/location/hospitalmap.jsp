<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hospital Map</title>
<script src="resources/script/jquery-3.2.1.min.js"></script>
<style>
/* ** info Design ** */
.label {margin-bottom: 96px;}
.label * {display:inline-block; vertical-align:middle;}
.label .left {background: url("http://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_l.png") no-repeat;display: inline-block;height: 20px;overflow: hidden;vertical-align:middle;width: 7px;}
.label .center {background: url(http://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_bg.png) repeat-x;display: inline-block;height: 20px;font-size: 12px;font-weight:bold;line-height: 12px;}
.label .right {background: url("http://t1.daumcdn.net/localimg/localimages/07/2011/map/storeview/tip_r.png") -1px 0  no-repeat;display: inline-block;height: 20px;overflow: hidden;width: 6px;}
</style>
</head>
<body>
<div id='map' style= "width:90%; height:400px;"></div>

<script type="text/javascript" 
		src="//dapi.kakao.com/v2/maps/sdk.js?appkey=5aa24558714e59eec84f58205d2a4fbf&libraries=services"></script>

<script>
// 이 스크립트는 BODY 영역에 작성 한다. 
var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
    mapOption = {
        center: new kakao.maps.LatLng(33.450701, 126.570667), // 지도의 중심좌표
        level:13 // 지도의 확대 레벨
    };  
// 지도를 생성합니다    
var map = new kakao.maps.Map(mapContainer, mapOption); 

var addrs = [
	{ content:'24시아토즈동물병원',  
	addr:'경기 성남시 분당구 삼평동 737' },
	 { content:'24시 폴동물병원',  
	addr:'경기 성남시 분당구 정자동 15-2' },
	 { content:'24시 분당 리더스 동물의료원',  
	addr:'경기 성남시 분당구 야탑로 24' },
	 { content:'24시동물병원 ',  
	addr:'경기 용인시 기흥구 구갈동 391-5' },
	 { content:'24시스카이동물의료센터',  
	addr:'인천 남동구 구월동 1126-2 거문빌딩' },
	{ content:'24시독스동물병원',  
	 addr:'인천 남동구 간석2동 210-2'},
	{ content:'고덕24시동물병원',  
	 addr:'서울 강동구 명일동 48-17 한화오벨리스크스위트 106호'},
	{ content:'닥터윤24시 동물병원',  
	 addr:'서울 은평구 대조동 187-24 1층' },
	{ content:'와이즈24시동물병원',  
	 addr:'서울 강남구 논현동 123-5' },
	{ content:'가나24시동물병원',  
	 addr:'경기 안산시 단원구 고잔동 537-4 롯데프라자 1층'},
	{ content:'파주24시동물병원',  
	 addr:'경기 파주시 목동동 941-1 삼성프라자 2층 201호' },
	 { content:'대구24시동물병원',  
	addr:'대구 북구 노원동1가 1-8' },
	 { content:'24시프라임동물병원',  
	addr:'대구 달서구 대곡동 964' },
	{ content:'대구24시응급연합동물병원',  
	 addr:'대구 수성구 지산동 1051 3층' },
	{ content:'메트로24시응급동물병원',  
	 addr:'대구 수성구 만촌동 950-1' },
	{ content:'만덕24시동물병원',  
	 addr:'부산 북구 만덕동 916-7' },
	{ content:'해운대24시동물의료원',  
	 addr:'부산 해운대구 좌동 1476-1' },
	{ content:'자이안트동물병원',  
	 addr:'부산 남구 용호동 176-30' },
	 { content:'웰펫동물병원',  
		  addr:'서울특별시 중구 동호로 178' },
		{ content:'동물사랑병원',   
		  addr:'서울특별시 마포구 월드컵로 73' },  
		{ content:'누리봄동물병원',  
		  addr:'서울특별시 종로구 사직로12길 2' },
		{ content:'윤신근동물병원',  
		  addr:'서울특별시 중구 퇴계로 226' },
		{ content:'페리토',  
		  addr:'경기도 고양시 일산동구 호수로 596' },
		{ content:'누리동물병원 유기동물보호소',   
		  addr:'부산광역시 해운대구 해운대로 1252' },  
		{ content:'남문가축병원',   
		  addr:'전라북도 전주시 완산구 호암로 40' },
		{ content:'누리동물병원 유기동물보호소',   
		  addr:'부산광역시 해운대구 해운대로 1252' },
		{ content:'진도동물병원',   
		 addr:'전라남도 진도군 진도읍 교동3길 40-1' },
		 { content:'김대진애견종합병원',   
		  addr:'경상북도 영주시 영주로 178' },
		 { content:'대관령가축병원',   
		  addr:'경상북도 영천시 완산동 1122-2' },
	    { content:'남산동물병원',   
		  addr:'경상북도 경산시 남산면 하남로 319' },  
		{ content:'남양가축병원',  
		  addr:'경기도 화성시 남양읍 남양리 1165-1' },
		{ content:'김동물병원',  
		  addr:'전라남도 함평군 해보면 해삼로 205-9' },
		{ content:'구지가축병원',  
		  addr:'대구광역시 달성군 구지면 창리 429-2' },
		{ content:'예산가축병원',   
		  addr:'충청남도 예산군 예산읍 역전로 51' },  
		{ content:'초록동물병원',   
		  addr:'울산광역시 울주군 언양읍 남부리' },
		{ content:'한강동물병원',   
		  addr:'경기도 남양주시 진접읍 장현리' },
		{ content:'통일가축병원',   
		 addr:'경기도 동두천시 생연2동 688-3' },
		 { content:'석곡동물병원',   
		  addr:'전라남도 곡성군 석곡면 석곡로 49' },
		 { content:'더봄동물병원',   
		  addr:'경기도 용인시 기흥구 선천서로 12번길 4-8 102호' },
		  { content:'탑케어동물의료원',   
		  addr:'경기도 고양시 덕양구 화신로272번길 5' },  
		{ content:'전남대학교 광주 캠퍼스 동물병원',  
		  addr:'광주광역시 북구 용봉로 45' },
		{ content:'중앙동물병원',  
		  addr:'제주특별자치도 제주시 성신로 15' },
		{ content:'나음동물병원',  
		  addr:'제주특별자치도 제주시 연북로 18' },
		{ content:'신동동물병원',   
		  addr:'충청남도 공주시 공주대학로 23-44' },  
		{ content:'태흥동물병원',   
		  addr:'제주특별자치도 제주시 연북로 685' },
		{ content:'한국동물병원',   
		  addr:'인천광역시 미추홀구 낙섬중로 25' },
		{ content:'이영준동물병원',   
		 addr:'전라북도 완주군 봉동읍 봉동동서로 40' },
		 { content:'늘푸른종합동물병원',   
		  addr:'강원도 원주시 만대로 15' },
		 { content:'정자동물병원',   
		  addr:'경기도 수원시 장안구 대평로 86' },
	    { content:'죽전TNR동물병원',   
		  addr:'경기도 광주시 경안천로 144 1층' },  
		{ content:'장원종합동물병원',  
		  addr:'부산광역시 북구 사랑로 1' },
		{ content:'함평축협동물병원',  
		  addr:'전라남도 함평군 학교면 월산리 1579' },
		{ content:'전동물병원',  
		  addr:'충청북도 음성군 대소면 대금로 822' },
		{ content:'산책동물병원',   
		  addr:'서울특별시 마포구 창전로 50-11 1층' },  
		{ content:'사하라동물병원',   
		  addr:'경상남도 통영시 중앙로 306' },
		{ content:'프렌즈동물병원',   
		  addr:'경기도 의정부시 호국로 1300' },
		{ content:'노성남 동물병원',   
		 addr:'인천광역시 연수구 독배로 55' },
		 { content:'지팡이동물병원',   
		  addr:'충청남도 청양군 정산면 효자길 35' },
		 { content:'양양가축병원',   
		  addr:'강원 양야군 양양읍 양양로 69-1' } 
	];
var geocoder = new kakao.maps.services.Geocoder();
var bounds = new kakao.maps.LatLngBounds();	

var total = addrs.length;
var counter = 0;

for(var i=0; i<total; i++) {
	var address=addrs[i].addr ;
	geocoder.addressSearch(address, function(result, status) {
		if (status === kakao.maps.services.Status.OK) {
			var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
			console.log('i='+i+'yLat[i]='+result[0].y+'xLng[i]='+result[0].x);
			console.log('address='+address);
			var marker = new kakao.maps.Marker({
				map: map,
				position: coords
			}); // marker

			bounds.extend(coords);
		
			var content = '<div class ="label"><span class="left"></span><span class="center">'
				+'<a href="https://map.kakao.com/link/map/'
				+addrs[counter].content+','+result[0].y+','+result[0].x
				+'" style="color:blue" target="_blank">'+addrs[counter].content+'</a></span><span class="right"></span></div>';

				var customOverlay = new kakao.maps.CustomOverlay({
			    position: coords,
			    content: content   
			});
				customOverlay.setMap(map);
			
			counter++ ;

			if (total===counter) map.setBounds(bounds);
		} // if	
	}); // geocoder.addressSearch	
} // for	

</script>
</body>
</html>