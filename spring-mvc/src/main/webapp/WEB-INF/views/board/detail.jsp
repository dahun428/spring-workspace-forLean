<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources/bootstrap/css/bootstrap.css">
<title>자유게시판</title>
</head>
<body>
	<div class="container mt-3 mb-5">
		<div class="row">

			<div class="col-12">
				<div class="card">
					<div class="card-header">게시글 상세 정보</div>
					<div class="card-body">
						<table class="table table-bordered">
							<colgroup>
								<col width="15%" />
								<col width="35%" />
								<col width="15%" />
								<col width="35%" />
							</colgroup>
							<tbody>
								<tr>
									<th>번호</th>
									<td>${board.no }</td>
									<th>등록일</th>
									<td><fmt:formatDate value="${board.createdDate }"
											pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<th>제목</th>
									<td><c:out value="${board.title }" /></td>
									<th>작성자</th>
									<td><c:out value="${boar.writer }" /></td>
								</tr>
								<tr>
									<th>조회수</th>
									<td><c:out value="${board.likes }" /></td>
									<th>최근 변경일</th>
									<td><fmt:formatDate value="${board.updatedDate }"
											pattern="yyyy-MM-dd" /></td>
								</tr>
								<tr>
									<th>첨부파일</th>
									<td colspan="3"><c:choose>
											<c:when test="${empty board.filename }">없음</c:when>
											<c:otherwise>
												<strong> ${board.originalFilename }</strong>
												<a href="download.do?no=${board.no }"
													class="btn btn-outline-primary">다운로드</a>
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
									<th>내용</th>
									<td colspan="3"><c:out value="${board.content }" /></td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="card-footer">
						<div class="d-flex justify-content-between">
							<div>
								<a href="modify.do?no=${board.no }" class="btn btn-warning">수정</a>
								<button data-toggle="modal" data-target="#modal-password-form"
									class="btn btn-danger">삭제</button>
							</div>
							<div>
								<a href="list.do" class="btn btn-primary">목록</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="modal-password-form">
			<div class="modal-dialog modal-sm">
				<form id="password-form" method="post" action="delete.do">
					<input type="hidden" value="${board.no }" name="no"/>
					<div class="modal-content">
						<div class="modal-header">
							<h4 class="modal-title">비밀번호 확인</h4>
							<button type="button" class="close" data-dismiss="modal">&times;</button>
						</div>
						<div class="modal-body">
							<div class="form-group">
								<label for="board-delete-password"> 비밀번호 </label> <input
									type="password" name="password" id="board-delete-password"
									class="form-control" />
							</div>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-danger" data-dismiss="modal">Close</button>
							<button type="submit" id="btn-confirm" class="btn btn-primary" disabled="disabled">확인</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="/resources/jquery/jquery.min.js"></script>
	<script src="/resources/bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript" src="/resources/jquery/custom.js"></script>
</body>
</html>