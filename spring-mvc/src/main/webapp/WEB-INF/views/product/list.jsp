<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources\bootstrap\css\bootstrap.css">

<title>Insert title here</title>
</head>
<body>
	<div class="container mt-3 mb-5">
		<div class="row">
			<div class="col-12">
				<div class="card">
					<div class="card-header">
						제품리스트 <select class="float-right w-25" name="" id="">
							<c:forEach items="${categories }" var="cat">
								<option value="${cat.id }">${cat.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="card-body">
						<table class="table">
							<colgroup>
								<col width="10%" />
								<col width="50%" />
								<col width="20%" />
								<col width="10%" />
								<col width="10%" />
								
							</colgroup>
							<thead>
								<tr>
									<th>번호</th>
									<th>상품명</th>
									<th>가격(할인가격)</th>
									<th>남은 재고</th>
									<th>구매</th>
								</tr>
							</thead>
							<tbody>
							<c:choose>
								<c:when test="${empty products }">
								<tr>
									<td>등록된 상품이 없습니다.</td>
								</tr>
								</c:when>
							<c:otherwise>
							<c:forEach items="${products }" var="product">
							<tr>
								<td>${product.no }</td>
								<td>${product.name }</td>
								<td>${product.price } (<fmt:formatNumber value="${product.discountPrice }" /> )원</td>
								<td>${product.stock }</td>
								<td><a href="/order/step1.do?no=${product.no }" class="btn btn-primary" type="button">구매하기</a> </td>
							</tr>
							</c:forEach>
							</c:otherwise>
							</c:choose>
							</tbody>
						</table>


					</div>
					<div class="card-footer text-right">
						<a href="add.do" class="btn btn-primary">제품 등록</a>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/jquery/custom.js"></script>
</body>
</html>