<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>같이 보자! 공공연한사이</title>
<link rel="stylesheet" href="/resources/css/jquery.fullPage.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css">
<link rel="stylesheet" href="/resources/bootstrap-datepicker/css/bootstrap-datepicker.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="/resources/css/style.css" />
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.js"></script>
<script src="/resources/bootstrap-datepicker/js/bootstrap-datepicker.min.js"></script>
<script src="/resources/bootstrap-datepicker/locales/bootstrap-datepicker.ko.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery.color.js"></script>
<script type="text/javascript" src="/resources/js/custom.js"></script>
</head>
<body>
<div class="header">
<%@ include file="../common/navi.jsp" %>
</div>
<div class="container " style="margin-top: 150px;">
	<div class="row">
		<div class="col-12">
			<div class="row"> 
				<div class="col-2" >
					<table class="table table-bordered" >
						<tbody>
							<tr style="background-color: orange">
								<th style="color: white; font-size: 20px;">회원관리</th>
							</tr>
							<tr>
								<th>고객 조회</th>
							</tr>
							<tr>
								<th>최근가입 일자 순</th>
							</tr>
							<tr>
								<th>좋아요 많은 순</th>
							</tr>
							<tr>
								<th>이 달의 생일 고객</th>
							</tr>
							<tr>
								<th>탈퇴한 고객</th>
							</tr>
							<tr>
								<th>블랙리스트</th>
							</tr>
							<tr >
								<th>신고리스트 조회</th>
							</tr>
						</tbody>
					</table>	
				</div>
			<div class="col-10">
				<table class="table">
						<thead class="thead-dark" style="background-color: orange; color: white">
							<tr>
								<td>no.</td>
								<td>회원ID</td>
								<td>닉네임</td>
								<td>가입일자</td>
								<td>좋아요</td>
								<td></td>
							</tr>
						</thead>
						<tbody style="border-bottom: 1px solid gray ;">
						<c:choose>
							<c:when test="${empty users }">
								<tr>
									<td colspan="5" class="text-center">회원이 존재하지 않습니다.</td>
								</tr>
							</c:when>
							<c:otherwise>
								<c:forEach var="userDto" items="${users }">
									<tr>
										<td>11</td>
										<td>${userDto.id }</td>
										<td>${userDto.nickname }</td>
										<td>${userDto.regDate }</td>
										<td>${userDto.getlike }</td>
										<td><button class="btn btn-secondary btn-sm userBtn" data-toggle="modal" data-target="#userDetailModal" data-userId="${userDto.id }" 
										style="margin-left: 15px; background-color: orange">상세보기</button></td>
									</tr>
								</c:forEach>
							</c:otherwise>	
						</c:choose>	
						</tbody>
				</table>	
			</div>
		</div>
		</div>
		
		<div >
			<input type="text" placeholder="search" style="margin-left: 500px; margin-top: 20px;">
			<button class="btn btn-outline-secondary btn-sm" style="margin-left: 5px; margin-bottom: 5px;">검색</button>
		</div>
	</div>

	

	
</div>


<!-- Modal -->
<div class="modal fade" id="userDetailModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-xl">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
		<div class="row">
			<div class="col-12">
				<table border="1" class="table">
					<tr>
						<td>ID</td>
						<td><span id="id"></span></td>
						<td>닉네임</td>
						<td><span id="nickname"></span></td>
						
						
					</tr>
					<tr>
						<td>성별</td>
						<td><span id="gender"></span></td>
						<td>생년월일</td>
						<td><span id="birthday"></span></td>
					</tr>
					<tr>
						<td>TEL</td>
						<td><span id="tel"></span></td>
						<td>Email</td>
						<td><span id="email"></span></td>
					</tr>
					<tr>
						<td>소지 포인트</td>
						<td><span id="totalPoint"></span></td>
						<td>받은 좋아요</td>
						<td><span id="getlike"></span></td>
					</tr>
				<!-- 		
					<tr>
						<td>보유 뱃지</td>
						<td></td>
						<td>받은 신고</td>
						<td></td>
					</tr>
				 -->	
					<tr>
						<td>주소</td>
						<td colspan="3"><span id="address"></span></td>
					</tr>
				</table>
			</div>
		</div>     
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div>
    </div>
  </div>
</div>




</body>

<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script src='//code.jquery.com/jquery.min.js'></script>
<script type="text/javascript">

$(function(){
		$('.userBtn').click(function(){
			var userid = $(this).data('userid')
			$('#id').text(userid)
			
			$.ajax({
				url:'/adminProfileDetail.do',
				type:'POST',
				data:{
					"id":userid
				},
				success:function(data){
					console.log(data.nickname)
					console.log(data.gender)
					console.log(data.birthday)
					console.log(data.email)
					console.log(data.tel)
					console.log(data.getlike)
					console.log(data.regDate)
					console.log(data.totalPoint)
					console.log(data.postalCode)
					console.log(data.sido)
					console.log(data.sigungu)
					console.log(data.addr)
					$('#nickname').text(data.nickname)
					$('#gender').text(data.gender)
					$('#birthday').text(data.birthday)
					$('#email').text(data.email)
					$('#tel').text(data.tel)
					$('#getlike').text(data.getlike)
					$('#regDate').text(data.regDate)
					$('#totalPoint').text(data.totalPoint)
					
					var address = (data.postalCode) + (data.sido) + (data.sigungu) + (data.addr)
					
					$('#address').text(address)
				
				
				}
			
			})
			
			
			
			
			
			
			console.log(userid)
		})
})

</script>

</html>















