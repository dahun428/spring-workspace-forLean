
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>같이 보자! 공공연한사이</title>
<link rel="stylesheet" href="/resources/css/jquery.fullPage.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
<link rel="stylesheet" href="/resources/css/style.css" />

<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.js"></script>
<script type="text/javascript" src="/resources/js/jquery.color.js"></script>
<script type="text/javascript" src="/resources/js/custom.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<script src="https://cdn.jsdelivr.net/npm/chart.js@2.8.0"></script>

</head>
<body>
	<div class="header">
		<%@ include file="../common/navi.jsp"%>
	</div>
	
	<!-- 테스트용 db -->
	  
		
	<div class="body" style="margin-top: 50px;">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12 mt-5 p-3 text-center">				
					<h2 class="pl-3 font-weight-bold">
						'<c:out value="${title }" />'
						에 대한 전체 검색 결과				
					</h2>
				</div>
			</div>
			
			<div class="row mt-5 justify-content-center">
				<div class="col-8">
					<div class="d-flex justify-content-between">
						<div class="p-2">							
							<span>검색결과</span>
						</div>
						<div class="p-2">							
							<button type="button" class="btn btn-link text-danger"
								onclick="selectOrder('dateOrder')"  id="dateOrder">개봉일순</button>
							<button type="button" class="btn btn-link text-dark"
								onclick="selectOrder('likeOrder')" id="likeOrder">좋아요순</button>
							<button type="button" class="btn btn-link text-dark"
								onclick="selectOrder('salesOrder')" id="salesOrder">예매순</button>
						</div>
						<div>
							<form action="/performance/totalList.do" method="get"
							id="totalListForm">
								<input type="hidden" class="form-control" id="searchTitle"
								name="title" value="${title }"/>
								<input type="hidden" class="form-control" id="listOrder"
								name="order" value="dateOrder"/>
								<input type="hidden" name="pageNo" id="pageNo" />								
							</form>
						</div>
					</div>
					<hr />
				</div>
			</div>		
			
			<!-- 공연 목록 row시작 -->
			<div class="row mt-5 justify-content-center">
				<!-- 공연 목록 시작 -->
				<div class="col-8 mt-3">

				<c:forEach var="performance" items="${performances }">
					<div class="row mt-2 mb-3"> <!-- 공연정보 카드 1 시작 -->				
						<div class="col-12">
							<div class="card">								
								<div class="card-body">
									<div class="row">
										<div class="col-3">
											<img src="/resources/sample-images/${performance.imagePath }" 
												class="img-thumbnail">
										</div>
										<div class="col-9">
											<div class="row mb-2">
												<div class="col-12">
													<span class="badge badge-pill badge-warning mr-2">														
													<c:choose>
														<c:when test="${performance.rating eq 0}">
       														전체
    													</c:when>
    													<c:otherwise>
      														${performance.rating }
    													</c:otherwise>
    												</c:choose>													
													</span>
													<h5 class="d-inline font-weight-bold">
														<c:out value="${performance.title }" />
													</h5>
												</div>
											</div>
											<div class="row mb-2">
												<div class="col-7 ">
													<table class="table table-borderless">
														<colgroup>
															<col width="20%" />
															<col width="80%" />
														</colgroup>
														<tr>
															<th>장르</th>
															<td>
															<c:forEach var="genre" items="${performance.genres }"> 
																${genre }&nbsp;
															</c:forEach>															
															</td>
														</tr>
														<tr>
															<th>러닝타임</th>
															<td>
																${performance.runningTime }
															</td>
														</tr>
														<tr>
															<th>공연기간</th>
															<td>
																<fmt:formatDate value="${performance.startDate }" pattern="yyyy년 M월 d일" />
																~
																<fmt:formatDate value="${performance.endDate }" pattern="yyyy년 M월 d일" />
																
															</td>
														</tr>
														<tr>
															<th>분류</th>
															<td>${performance.category }</td>
														</tr>
														<tr>
															<th>공연장소</th>
															<td>${performance.hallName }</td>
														</tr>
														<tr>
															<th>좌석정보</th>
															<td>
															<c:forEach var="seat" items="${performance.seatPrices }">
																${seat.seatRate }석 
																<fmt:formatNumber value="${seat.price }" />원																
															</c:forEach>															
															</td>
														</tr>
														<tr>
															<td colspan="2">
																<label class="">
																<c:forEach var="genre" items="${performance.genres }"> 
																	<button class="btn btn-link" onclick="searchGenre('${genre}')">#${genre }</button>
																</c:forEach>																	
																</label>
															</td>
														</tr>
													</table>
												</div>
												<!-- 공연 간단 정보창 끝 -->
												<div class="col-5">
													<div class="row justify-content-center">
														<div class="col-12">
															<div class="card">
																<div class="card-body">
																	<div class="mb-4">
																		<i class='fas fa-bullhorn mr-2'
																			style='font-size: 24px'></i>
																		<h6 class="d-inline font-weight-bold">메이트 모집중</h6>
																	</div>
																	<div>
																		<h6>현재 00/000명이 해당 공연의 메이트가 되었습니다.</h6>
																	</div>
																	<div class="progress mt-3">
																		<div
																			class="progress-bar bg-danger progress-bar-striped"
																			role="progressbar" style="width: 75%;"
																			aria-valuenow="75" aria-valuemin="0"
																			aria-valuemax="100">75%</div>
																	</div>
																	<div class="mt-3 ">
																		<h6 class="d-inline">메이트 그룹 현황</h6>
																		<span class="d-inline">00/00</span>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- 메이트 그룹 창 끝 -->
											</div>
											<!-- 공연정보 표시끝 -->
										</div>
									</div>
									<div class="row mt-2 p-2 "> <!-- 좋아요수, 뒷풀이 게시판수, 예매하기, 상세보기 버튼그룹 -->
										<div class="col-12 d-flex justify-content-between">
											<div>
												<!-- 좋아요수, 뒷풀이 게시판 수 표시 -->
												<div class="d-inline mr-2">
													<i class='fas fa-heart mr-2'
														style='font-size: 24px; color: red;'></i><label>${performance.likes }</label>
												</div>
												<!-- 뒷풀이 게시판 -->
												<div class="d-inline" style="display:none;">
													<i class='far fa-comment-alt mr-2' style='font-size: 24px'></i><label></label>
												</div>
											</div>
											<div>
												<button type="button" class="btn btn-outline-danger mr-2"
													onclick="buyTicket(${performance.id})">예매하기</button>
												<button type="button" class="btn btn-outline-dark"
													onclick="showDetail(${performance.id})">상세보기</button>
											</div>
										</div>
									</div>	<!-- 예매하기 상세보기 버튼그룹 끝 -->
									<div class="row mt-2 p-2">
										<div class="col-12 d-flex justify-content-end">
											<button type="button" class="btn btn btn-info mr-2"
													onclick="updatePerformance(${performance.id})">수정하기</button>
											<button type="button" class="btn btn btn-danger "
													onclick="deletePerformance(${performance.id})">삭제하기</button>
										</div>
									</div>
									
									<!--  
									<div class="row">
										<label class="pr-2 pl-2"><a href="#">#범죄</a><a
											href="#">#액션</a><a href="#">#스릴러</a></label>
									</div>
									-->
									<!-- 태그 창 끝 -->
								</div>
								<!-- card body 끝 -->
							</div>
							<!-- card 끝 -->
						</div>
						<!-- 카드 row col-12끝 -->
					</div>
					<!-- 카드 row끝 공연정보카드1 끝남-->
				</c:forEach>
				
					
					<!-- 카드 row끝 -->
				</div>
			</div>
			<!-- 공연목록 끝 -->

			<!-- 공연 상세정보 모달창 -->
			<div class="modal" id="myModal">
				<div class="modal-dialog"
					style="max-width: 100%; width: 80%; display: table;">
					<div class="modal-content">

						<!-- Modal Header -->
						<div class="modal-header">
							<h6 class="modal-title font-weight-bold">공연 상세정보</h6>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>

						<!-- Modal body -->
						<div class="modal-body">
							<div class="card">
								<div class="card-body">
									<div class="row">
										<div class="col-3">
											<img src="/resources/sample-images/movie_image2.jpg"
												class="img-thumbnail" id="modalImg">
										</div>
										<div class="col-9">
											<div class="row mb-2">
												<div class="col-12">
													<span class="badge badge-pill badge-warning mr-2" id="modalAge"></span>
													<h5 class="d-inline font-weight-bold" id="modalTitle"></h5>
												</div>
											</div>
											<div class="row mb-2">
												<div class="col-7 ">
													<table class="table table-borderless">
														<colgroup>
															<col width="20%" />
															<col width="80%" />
														</colgroup>
														<tr>
															<th>장르</th>
															<td id="modalGenre"></td>
														</tr>
														<tr>
															<th>러닝타임</th>
															<td id="modalRunningTime"></td>
														</tr>
														<tr>
															<th>공연기간</th>
															<td id="modalPeriod"></td>
														</tr>
														<tr>
															<th>분류</th>
															<td id="modalCategory"></td>
														</tr>
														<tr>
															<th>공연장소</th>
															<td id="modalHallName"></td>
														</tr>
														<tr>
															<th>좌석정보</th>
															<td id="modalSeatInfo"></td>
														</tr>
														<tr>
															<td colspan="2">
																<label class="" id="modalTags">																	
																</label>
															</td>
														</tr>
													</table>
												</div>
												<!-- 공연 간단 정보창 끝 -->
												<div class="col-5">
													<div class="row justify-content-center">
														<div class="col-12">
															<div class="card">
																<div class="card-body">
																	<div class="mb-4">
																		<i class='fas fa-bullhorn mr-2'
																			style='font-size: 24px'></i>
																		<h6 class="d-inline font-weight-bold">메이트 모집중</h6>
																	</div>
																	<div>
																		<h6>현재 00/100명이 해당 공연의 메이트가 되었습니다.</h6>
																	</div>
																	<div class="progress mt-3">
																		<div
																			class="progress-bar bg-danger progress-bar-striped"
																			role="progressbar" style="width: 75%;"
																			aria-valuenow="75" aria-valuemin="0"
																			aria-valuemax="100">75%</div>
																	</div>
																	<div class="mt-3 ">
																		<h6 class="d-inline">메이트 그룹 현황</h6>
																		<span class="d-inline">00/30</span>
																	</div>
																</div>
																<!-- 카카오맵 api by LMS (start 2020.08.26) -->
																<div class="card-body">
																	<div id="map" style="width:380px; height:300px;"></div>
																</div>
															</div>
														</div>
													</div>
												</div>
												<!-- 메이트 그룹 창 끝 -->
											</div>
											<!-- 공연정보 표시끝 -->
										</div>
									</div>
									<div class="row mt-2 ">
										<div class="col-12 d-flex justify-content-between">
											<div>
												<!-- 좋아요수, 뒷풀이 게시판 수 표시 -->
												<div class="d-inline mr-2">
													<button type="button" class="btn btn-sm" id="clickLike">
														<i class='far fa-heart mr-2'
														style='font-size: 24px;'></i>
													</button>
													<label id="modalLikes"></label>
												</div>
												<div class="d-inline">
													<button type="button" class="btn btn-sm" id="goBoard">													
														<i class='far fa-comment-alt mr-2' style='font-size: 24px'></i>
													</button>
													<label id="modalAfterParty"></label>
												</div>
											</div>
											
										</div>
									</div>
									<div class="row justify-content-center">
									<!--  
										<label class="pr-2 pl-2"><a href="#">#범죄</a><a
											href="#">#액션</a><a href="#">#스릴러</a></label>
									-->
									<!--
										여기서 버튼 onclick 정의하지 말고 아래에 클릭이벤트 정의
										$("#bton").click(function(no){
										})
									-->
									
										<button type="button" class="btn btn-outline-danger mr-4 btn-lg"
											id="modalBuyBtn" data-no="">예매하기</button>
										<button type="button" class="btn btn-outline-dark btn-lg"
											id="modalMateGroupBtn" data-no="">메이트 그룹</button>
									</div>
									
									<div class="row justify-content-end mt-3">
										<button type="button" class="btn btn btn-info mr-4 btn-lg"
												id="modalUpdateBtn" data-no="" >수정하기</button>
										<button type="button" class="btn btn btn-danger mr-2 btn-lg"
												id="modalDeleteBtn" data-no="">삭제하기</button>	
									</div>
									
									
									<!-- 태그 창 끝 -->
								</div>
								<!-- card body 끝 -->
							</div>
							<!-- card 끝 -->
							<div> <!-- 공연상세 정보 -->
								<div class="mt-3 p-2">
									<h5 class="font-weight-bold">공연정보</h5>
									<table class="table table-bordered mt-3">
										<colgroup>
											<col width="20%">
											<col width="80%">
										</colgroup>
										<tbody>
											<tr>
												<th>공연명</th>
												<td id="modalDetailtitle"></td>
											</tr>
											<tr>
												<th>공연장소</th>
												<td id="modalDetailHallName">
													<p></p>
													<p></p>
												</td>
											</tr>
											<tr>
												<th>공연기간(개봉일)</th>
												<td id="modalDetailPeriod"></td>
											</tr>
											<!--  
											<tr>
												<th>공연시간</th>
												<td id="modalDetailTime"></td>
											</tr>
											-->
											<tr>
												<th>러닝타임</th>
												<td id="modalDetailRunningTime"></td>
											</tr>
											<tr>
												<th>좌석등급 및 가격</th>
												<td id="modalDetailSeatInfo"></td>
											</tr>
											<tr>
												<th>관람연령</th>
												<td id="modalDetailAge"></td>
											</tr>
										</tbody>									
									</table>							
								</div>	<!-- 공연정보 테이블 끝 -->
								<div class="mt-3 p-2"> <!-- 공연 -->
									<h5 class="font-weight-bold">내용</h5>				
									<div class="mt-3" style="width:85%">
										 <p id="modalDetailExplain"></p>
									</div>									
								</div>
								<div class="mt-3 p-2">
									<h5 class="font-weight-bold">이벤트 정보</h5>				
									<div class="mt-3" style="width:85%">
										<p>이벤트가 없습니다.</p>											
									</div>	
								</div>	
								<div class="mt-3 p-2">
									<h5 class="font-weight-bold">성별 나이별 관람추이</h5>				
									<div class="row mt-3 p-2 justify-content-start">
										<div class="col-5">
											<div class="card"> 
												<div class="card-body"> 
													<canvas id="chartGender">
													</canvas>
												</div> 
											</div>
										</div>
										<div class="col-5">
											<div class="card"> 
												<div class="card-body"> 
													<canvas id="chartAge">
													</canvas>
												</div> 
											</div>
										</div>
									</div>
								</div> <!-- 공연 상세정보 -->	
															
							</div>	<!-- 공연상세정보 끝 -->						
							
						</div>
						<!-- Modal footer -->
						
						<!--  -->						
						
					</div>
				</div>
			</div> <!-- 공연 상세정보 모달창 끝 -->
			
			<!--  
			<div>
				테스트용
				
				pageNo:  ${pageNo }
				totalPageCount: ${totalPageCount }
				rows: ${rows }
				pagination.beginPage: ${pagination.beginPage }
				pagination.endPage: ${pagination.endPage }
			</div>
			-->
			
			
			
			<div class="row mt-5 justify-content-center">
				<!-- 페이지네이션 -->
				<nav aria-label="Page navigation example">			
							
					<c:if test="${pageNo > 1 }">
						<button class="btn btn-light"
						 onclick="goPage('${pageNo-1}')">이전</button>
					</c:if>
						
					<c:forEach var="idx" begin="${pagination.beginPage }" end="${pagination.endPage }" step="1">
						
						<button class="btn btn-light paging-num ${pageNo == idx? 'active': '' }"
						onclick="goPage('${idx}')" >${idx }</button>
						
					</c:forEach>	
						
					<c:if test="${pageNo < totalPageCount }">
						<button class="btn btn-light"
						 onclick="goPage('${pageNo+1}')">다음</button>
					</c:if>	
						
					
				</nav>
			</div>
			<!-- 페이지네이션 끝 -->
		</div>
		<!-- container 끝 -->

	</div>
	<!-- body 끝 -->
	<div class="footer" style="height: 200px;"></div>

<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
<script type="text/javascript"
	src="/resources/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="//dapi.kakao.com/v2/maps/sdk.js?appkey=333f1bf83c9503d02a701757427f6892&libraries=services"></script>	
<script type="text/javascript">
$(function(){
	
	console.log("시작");
	
	
	
	
});

function updatePerformance(no) {
	console.log("updatePerformance(no="+no+")");
	
	var category = '${category}';
	location.href='/performance/update/main.do?category='+category+'&performanceId='+no;
	//location.href='/payment/step1.do?no='+no;
}

function deletePerformance(no) {
	console.log("deletePerformance(no="+no+")");
	
	var category = '${category}';
	
	location.href='/performance/delete.do?category='+category+'&performanceId='+no;
	
}





function goPage(no) {
	console.log("page 링크를 눌렀다.");
	console.log("page: "+no);
	if ($("#pageNo").val() == "") {
		console.log("비었ㄷ.");
	}else{
		
		console.log("pageNo="+$("#pageNo").val());
	}
	
	$("#pageNo").val(no);
	$("#totalListForm").submit();
}

function searchGenre(genre) {
	console.log("clickTag: "+genre);
	
	var checked = $("#search-form input[name=genre]:checked");
	
	console.log(checked);
	
	var genreVals = $("#search-form input[name=genre]");
	console.log(genreVals);
	
	for (var idx = 0; idx < genreVals.length; idx++ ) {
		
		var genreBtn = genreVals[idx];
		console.log($(genreBtn).val());
		
		if ($(genreBtn).val() == genre) {
			console.log($(genreBtn).val()+"가 누른 태그임");
			$(genreBtn).prop("checked", true);			
		}else {
			$(genreBtn).prop("checked", false);	
		}		
	}
	
	$("#search-form").submit();
	
	
}


function selectOrder(option) {
	//console.log(option);	
	var optionId = "#"+option;
	//console.log($(optionId));
	
	$(optionId).removeClass("text-dark").addClass("text-danger");
	$(optionId).siblings().removeClass("text-danger").addClass("text-dark");
	$("#listOrder").val(option);
	
	// 이후 form submit 출력
}

$("#performanceStartDay").change(function() {
	//console.log(this);
	//console.log($(this).val());
	
	var now = new Date().toISOString().substring(0, 10);		
	var $endDay = $("#performanceEndDay");
	
	if ($endDay.val() == "") return;
	if ($endDay.val() < $(this).val()) {
		//console.log("시작날짜가 끝나는 날짜보다 커");
		$(this).val("");
	}
})

$("#performanceEndDay").change(function() {
	console.log(this);
	console.log($(this).val());
	
	var $startDay = $("#performanceStartDay");
	if ($(this).val() < $startDay.val()) {
		$(this).val("");		
	}
	
	//var now = new Date().toISOString().substring(0, 10);	
	//if ($(this).val() < now) {
	//	console.log("마감날짜가  오늘보다 작아");
	//}
})

function buyTicket(no) {
	console.log("buyTicket(no="+no+")");
	
	location.href='/payment/step1.do?no='+no;
}

$("#modalBuyBtn").click(function() {
	var no = $("#modalBuyBtn").data("no");
	console.log("modalBuyBtn(no="+no+")");
	buyTicket(no);
})

$("#modalUpdateBtn").click(function() {
	var no = $("#modalUpdateBtn").data("no");
	console.log("#modalUpdateBtn(no="+no+")");
	updatePerformance(no);
})

$("#modalDeleteBtn").click(function() {
	var no = $("#modalDeleteBtn").data("no");
	console.log("#modalDeleteBtn"+no+")");
	deletePerformance(no);
})


function numberWithComma(num)
{
	var regexp = /\B(?=(\d{3})+(?!\d))/g;
	return num.toString().replace(regexp, ',');
}

function showDetail(performanceId) {	
	console.log("showDetail(performanceId="+performanceId+")");	
	
	$.ajax({
		type:"GET",
		url:"/performance/detail.do",
		data: {id:performanceId},
		dataType: 'json',
		success: function(data) {
			var hallInfo = data.hallInfo;
			var performance = data.performance;
			console.log("디테일을 눌렀다.");
			
			$("#modalImg").attr("src", "/resources/sample-images/${performance.imagePath }"+performance.imagePath);			
			var rating = performance.rating;
			if (performance.rating == "0") {
				rating = "전체";
			}
						
			$("#modalAge").text(rating);
			$("#modalTitle").text(performance.title);
			
			var performanceGenres = "";						
			for (var idx=0; idx < performance.genres.length; idx++) {
				performanceGenres += performance.genres[idx]+ " ";				
			}			
			
			$("#modalGenre").text(performanceGenres);	
			
			console.log(performance.runningTime);
			$("#modalRunningTime").text(performance.runningTime);
			
			var period = performance.startDate + " ~ " +  performance.endDate;		
			$("#modalPeriod").text(period);
			$("#modalCategory").text(performance.category);
			$("#modalHallName").text(performance.hallName);
						
			var seatPrices = "";
			for (var idx=0; idx < performance.seatPrices.length; idx++) {
				seatPrices += performance.seatPrices[idx].seatRate + "석 "
				+ numberWithComma(performance.seatPrices[idx].price) + "원 ";
			}
			
			console.log(seatPrices);
			
			$("#modalSeatInfo").text(seatPrices);
			
			var tags="";
			for (var idx=0; idx < performance.genres.length; idx++) {
				tags += "#"+performance.genres[idx];				
			}	
			
			$("#modalTags").text(tags).css("color", "#06F");
			
			$("#modalLikes").text(performance.likes);
			$("#modalAfterParty").text();
			
			$("#modalDetailtitle").text(performance.title);
			$("#modalDetailHallName p:eq(0)").text(performance.hallName);
			$("#modalDetailHallName p:eq(1)").text("("+performance.hallAddress+")");
			$("#modalDetailPeriod").text(period);
			//$("#modalDetailTime").text(performance.expla);
			$("#modalDetailRunningTime").text(performance.runningTime);
			$("#modalDetailSeatInfo").text(seatPrices);
			
			if (rating != '전체') {
				rating = "만 "+rating+"이상 관람가";	
			}else {
				rating = rating+"관람가";
			}			
			
			$("#modalDetailAge").text(rating);
			$("#modalDetailExplain").text(performance.explain);
			
			
			$("#modalBuyBtn").data("no", performance.id);
			$("#showMateGroupBtn").data("no", performance.id);
			$("#modalDeleteBtn").data("no", performance.id);
			$("#modalUpdateBtn").data("no", performance.id);
			
			console.log("성공함");
			console.log(performance);
			
			var id = document.getElementById('map');
			kakaoMap(id, hallInfo);
		}
	})
	
	
	
	// 성별 그래프
	var genderColors=['skyblue', '#e23b3b'];
	
	var donutOptions= {
		cutoutPercentage: 30, //도넛두께 : 값이 클수록 얇아짐 
		legend: {
					position:'bottom', 
					padding:5, 
					labels: {
						pointStyle:'circle', 
						usePointStyle:true
					}
				}
	};
	
	var chDonutData = {
		labels: ['남자', '여자'], 
		datasets: [ { 
			backgroundColor: genderColors.slice(0,2), 
			borderWidth: 0, 
			data: [74, 40] // 데이터
		} ]
	};
	
	var $chDonut = $("#chartGender"); 
	
	if ($chDonut) { 
		new Chart($chDonut, { 
			type: 'pie', 
			data: chDonutData, 
			options: donutOptions 
			}
		); 
	}
	
	// 나이대별 관람추이
	var $chBar = $("#chartAge");
	
	if ($chBar) {
		new Chart($chBar, {
			type: 'bar',
			data: {
				labels: ['10대', '20대', '30대', '40대', '50대↑'],
				datasets: [{
					label: '# of Numbers',
					data: [12,34,47,16,12],	// 퍼센트 구해서 넣기
					backgroundColor: [
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)',
						'rgba(54, 162, 235, 0.2)'
					],
					borderColor: [
						'rgba(54, 162, 235, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(54, 162, 235, 1)',
						'rgba(54, 162, 235, 1)'
					],
					borderWidth: 1
				}]
			},
			options: {
				scales: {
					yAxes: [{
						ticks: {
							beginAtZero: true
						}
					}]
				}
			}
			
		})		
	}
	
	
	
	$("#myModal").modal("show");
	// modal 창이 열리는 시점(스타일이 변경되는 시점)에서 지도 영역이 변경되었다고 알려주는 함수 호출
	map.relayout();
	
	
};

function showMateGroup(no) {
	console.log(no);
	
};

$("#clickLike").click(function(){
	//console.log(this);
	
	//console.log($(this).find("i"));
	
	// 로그인 여부 확인하고 로그인 상태이면, 좋아요 표시
	var loginUser="${ LOGIN_USER}";
	console.log(loginUser);
	
	if (loginUser == "") {
		console.log("로그인필요");
	} else {
		console.log("로그인됨");
		// 아래의 작업을 수행한다.
	}
	
	$("#clickLike").find("i").hasClass("far")
	if ($(this).find("i").hasClass("far")){	// 빈 하트라면, 좋아요를 하지 않았다면,
		$(this).find("i").removeClass("far").addClass("fas").css("color", "red");
	} else {	// 좋아요 취소
		$(this).find("i").removeClass("fas").addClass("far").css("color", "black");
	}
	
	
})

$("#goBoard").click(function() {
	// board로 보냄
})


$(function() {
	$(":input[name=genre]").closest('label').click(function(event) {
		event.preventDefault();
		console.log("before", ($(this).find('input').prop("checked")));

		$(this).find('input').prop("checked", !($(this).find('input').prop("checked")))
		
		console.log("after", ($(this).find('input').prop("checked")));
		
		console.log($(this).find('input').val());
		
		
		
		if ($(this).find('input').prop("checked")) {
			
			
			$(this).removeClass("btn-outline-danger").addClass("btn-danger");
			
			// 무시해도되는 코드임
			// 전체 누르면 다른 버튼은 취소되도록
			if ($(this).find('input').val()=="전체") {				
				$(this).siblings().find('input').prop("checked", false);
				$(this).siblings().removeClass("btn-danger").addClass("btn-outline-danger");				
			}else {	// 전체 말고 다른 버튼 눌렀을 때 전체 버튼이 이미 눌러져 있는경우 전체버튼 체크해제됨
				if ($("#genreAll").prop("checked")) {
					console.log("다른 장르버튼 눌렀을때 전체버튼이 체크되어있는가");
					$("#genreAll").closest('label').removeClass("btn-danger").addClass("btn-outline-danger");	
					$("#genreAll").prop("checked", false);
				}	
			
			}			
			
			console.log("체크되었음");
		} else {
			$(this).removeClass("btn-danger").addClass("btn-outline-danger");
		}
	})
})

function kakaoMap(id, hallInfo) {
	
	// 카카오맵 api script 부분 by LMS (2020.08.26)
	var mapContainer = id, // 지도를 표시할 div 
	mapOption = {
	    center: new kakao.maps.LatLng(hallInfo.gpsX, hallInfo.gpsY), // 지도의 중심좌표
	    level: 3 // 지도의 확대 레벨
	};  
	
	//지도를 생성합니다    
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	//주소-좌표 변환 객체를 생성합니다
	var geocoder = new kakao.maps.services.Geocoder();
	
	//주소로 좌표를 검색합니다
	geocoder.addressSearch(hallInfo.address, function(result, status) {
	
	// 정상적으로 검색이 완료됐으면 
		 if (status === kakao.maps.services.Status.OK) {
		
		    var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
		
		    // 결과값으로 받은 위치를 마커로 표시합니다
		    var marker = new kakao.maps.Marker({
		        map: map,
		        position: coords
		    });
		
		    // 인포윈도우로 장소에 대한 설명을 표시합니다
		    var infowindow = new kakao.maps.InfoWindow({
		        content: '<div style="width:150px;text-align:center;padding:6px 0;">'+hallInfo.name+'</div>'
		    });
		    infowindow.open(map, marker);
		
		    // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
		    map.setCenter(coords);
		} 
	})
}
</script>
</body>
</html>