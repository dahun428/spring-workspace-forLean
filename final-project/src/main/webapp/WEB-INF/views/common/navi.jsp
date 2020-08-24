<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<input type="hidden" id="login-type" value="${LOGIN_TYPE }" />
		<nav class="navbar sticky-top transparent custom-color" id="navbar-top">
			<!-- side navi bar btn -->
			<div class="col-1">
				<button id="sidebarCollapse" class="btn navbar-toggler"
					type="button" data-toggle="collapse">
					<div id="sidenav-icon">
						<span></span> <span></span> <span></span> <span></span> <span></span>
						<span></span>
					</div>
				</button>
			</div>
			<!-- side navi bar btn -->
			<!-- brand name, title -->
			<div class="col-3 text-left">
				<a class="navbar-brand font-weight-bolder" href="/home.do"> <span
					class="h2 font-weight-bolder custom-color"><img id="brand-logo" style="width: 200px;" src="/resources/logo/gongsaLogo.png"/></span>
				</a>
			</div>
			<!-- brand name, title -->
			<!-- search bar -->
			<div class="col-4">
				<form class="form-inline mr-sm-2 searchform">
					<input type="search" class="form-control" id="search-box" />
					<a href="#" class="btn" type="button">
						<i class="fas fa-search fa-2x custom-color"></i>
					</a>
				</form>
			</div>
			<!-- search bar -->
			<!-- user icons -->
			<div class="col-4 text-right">
			
			<c:choose>
				<c:when test="${empty LOGIN_USER}">
					<a href="/signin.do" class="btn" type="button">
						<i class="fas fa-sign-in-alt fa-2x custom-color"></i>
					</a>
				</c:when>
				<c:otherwise>
					<a class="btn" href="/mypage/mypagemain.do">
						<i class="far fa-user fa-2x custom-color"></i>
					</a>
				
					<a href="#" class="btn" type="button">
						<i class="far fa-bell fa-2x custom-color"></i>
					</a>
					<a href="#" class="btn" type="button">
						<i class="far fa-envelope fa-2x custom-color"></i>
					</a>
		
					<a href="/signout.do" class="btn" type="button" id="btn-logout">
						<i class="fas fa-sign-out-alt fa-2x custom-color"></i>
					</a>
				</c:otherwise>
			</c:choose>
			
			</div>
			<!-- user icons -->
		</nav>
		<!-- Vertical navbar -->
		<nav class="vertical-nav active" id="sidebar">
			<div class="row">
				<div class="col-12 sidebar-top"></div>
			</div>

			<ul class="nav flex-column m-3">
				<li class="nav-item dropdown">
					<ul class="list-unstyled components mb-5">
						<li class="active"><a href="#homeSubmenu"
							data-toggle="collapse" aria-expanded="false"
							class="dropdown-toggle h4">공연예매</a>
							<ul class="collapse list-unstyled" id="homeSubmenu">
								<li><a href="/performance/list.do?category=콘서트" class="h6">콘서트</a></li>
								<li><a href="/performance/list.do?category=뮤지컬" class="h6">뮤지컬</a></li>
								<li><a href="/performance/list.do?category=연극" class="h6">연극</a></li>
							</ul></li>
						<li><a href="#" class="h4">메이트</a></li>
						<li><a href="#" class="h4">뒤풀이</a></li>
						<li><a href="#" class="h4">이벤트</a></li>
						<li><a href="/qna/list.do" class="h4">고객문의센터</a></li>
						
						<li><a href="#" class="h4">마이페이지</a></li>
					</ul>
				</li>
			</ul>
		</nav>
<script>
$(function() {
	$("#btn-logout").click(function(event) {
		event.preventDefault();
		
		var loginType = $("#login-type").val();
		
		if (loginType == 'web') {
			location.href = "/signout.do";
		} else if (loginType == "sns") {
			location.href = "https://kauth.kakao.com/oauth/logout?client_id=8c912903836a6643f0a91b7b98862cf7&logout_redirect_uri=http://localhost/sns/logout.do"
		}
		
		
	});
})
</script>
 