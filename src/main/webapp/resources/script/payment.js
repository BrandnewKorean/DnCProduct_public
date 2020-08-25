$(function(){
	var IMP = window.IMP; // 생략가능
	IMP.init('imp86261773');

	$('#payment').click(function(){
		console.log('payment click');
		IMP.request_pay({
		    pg : 'inicis', // version 1.1.0부터 지원.
		    pay_method : 'card',
		    merchant_uid : 'merchant_' + new Date().getTime(),
		    name : $('#product_name').text(),
		    amount : parseInt($('#total_price').text().replace(',','')),
		    buyer_email : $('#myinfo_email').text(),
		    buyer_name : $('#myinfo_name').text(),
		    buyer_tel : '010-1234-5678',
		    buyer_addr : $('#myinfo_address').text(),
		    buyer_postcode : '123-456',
		    m_redirect_url : window.location.href
		}, function(rsp) {
		    if ( rsp.success ) {
		        var msg = '결제가 완료되었습니다.';
		        msg += '고유ID : ' + rsp.imp_uid;
		        msg += '상점 거래ID : ' + rsp.merchant_uid;
		        msg += '결제 금액 : ' + rsp.paid_amount;
		        msg += '카드 승인번호 : ' + rsp.apply_num;
		    } else {
		        var msg = '결제에 실패하였습니다.';
		        msg += '에러내용 : ' + rsp.error_msg;
		    }
		    alert(msg);
		});
	});
	
	$('#shopping_basket').click(function(){
		console.log($('#myinfo_name').text());
	});
});