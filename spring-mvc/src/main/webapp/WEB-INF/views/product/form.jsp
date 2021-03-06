<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
	
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources\bootstrap\css\bootstrap.css">

<title>쇼핑몰</title>
</head>
<body>
	<div class="container mt-3 mb-5">
		<div class="row">
			<div class="col-12">

				<form action="add.do" id="product-form" method="post">
					<div class="card">
						<div class="card-header">새 상품 등록 폼</div>
						<div class="card-body">
							<div class="form-group">
								<label for="">카테고리</label>
								<select name="catId" id="category-id" class="form-control">
									<option value="" selected="selected" disabled="disabled">--선택하세요--</option>
									<c:forEach items="${categories }" var="cat">
										<option value="${cat.id }">${cat.name }</option>
									</c:forEach>
								</select>
							</div>
							<div class="form-group">
								<label for="">상품이름</label>
								<input type="text" name="name" id="product-name" class="form-control" />
							</div>							
							<div class="form-group">
								<label for="">상품가격</label>
								<input type="number" name="price" id="product-price" class="form-control" />
							</div>							
							<div class="form-group">
								<label for="">상품 할인가격</label>
								<input type="number" name="discountPrice" id="product-discount-price" class="form-control" />
							</div>							
							
						
						</div>
						<div class="card-footer text-right">
							<a href="list.do" class="btn btn-secondary">취소</a>
							<button class="btn btn-primary" type="submit">등록</button>
							
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/jquery/custom.js"></script>

</body>
</html>