<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="/resources\bootstrap\css\bootstrap.css">
<title>자유 게시판</title>
 
</head>
<body>
	<div class="container mt-3 mb-5">
		<div class="row">
			<div class="col-12">
			<form action="add.do" method="post" id="form-board" enctype="multipart/form-data">
				<div class="card">
					<div class="card-header">새 글 입력폼</div>
					<div class="card-body">
						<div class="form-group">
							<label for="add-title">제목</label>
							<input id="add-title" type="text" name="title" class="form-control" />
						</div>
						 
						<div class="form-group">
							<label for="add-content">내용</label>
							<textarea name="content" id="add-content" rows="5" class="form-control"></textarea>
						</div>
						<div class="form-group ">
							<label for="add-file">첨부파일</label>
							<input type="file" id="add-file" name="upfile" class="form-control " />
						</div>
						<div class="form-group">
							<label for="add-password">비밀번호</label>
							<input type="password" name="password" id="add-password" class="form-control" />
						</div>
					</div>
					<div class="card-footer text-right">
						<a href="add.do" class="btn btn-secondary">취소</a>
						<button type="submit" class="btn btn-primary">등록</button>
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