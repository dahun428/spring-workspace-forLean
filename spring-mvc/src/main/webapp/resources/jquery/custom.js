$(function(){
	
	//입력 폼에서 submit 이벤트 발생시 실행할 이벤트 핸들러 함수 등록해두기
//	$('#form-board').submit(function(){
//		
//		var $title = $('#add-title');
//		var $writer = $('#add-writer');		
//		var $pwd = $('#add-password');
//		
//		$title.css('border','1px solid gray');
//		$writer.css('border','1px solid gray');
//		$pwd.css('border','1px solid gray');
//		
//		
//		if(!$title.val()){
//			$title.css('border','1px solid red').attr('placeholder','제목을 입력하세요').focus();
//			return false;
//		}
//		if(!$writer.val()){
//			$writer.css('border','1px solid red').attr('placeholder','작성자를 입력하세요').focus();
//			return false;
//		}
//		if(!$pwd.val()){
//			$pwd.css('border','1px solid red').attr('placeholder','비밀번호를 입력하세요').focus();
//			return false;
//		}
//		return true;
//	});
	
		$(':input[name=password]').keyup(function(){
			if($(this).val() != ""){
				$('#btn-confirm').prop('disabled', false);
			} else {
				$('#btn-confirm').prop('disabled', true);
			}
		});
})