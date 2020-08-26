<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>같이 보자! 공공연한사이</title>
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css"/>
    <link rel="stylesheet" href="/resources/css/style.css"/>
    <link rel="stylesheet" href="/resources/css/card.css"/>
    <link rel="stylesheet" href="/resources/css/mypage.css"/>
    <link rel="stylesheet" href="/resources/css/ticket.css"/>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.4.4/umd/popper.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.js"></script>
    <script type="text/javascript" src="/resources/js/jquery.color.js"></script>
    <script type="text/javascript" src="/resources/js/custom.js"></script>
    <script type="text/javascript" src="/resources/js/mypage/mymateroom.js"></script>
    <style>
        .card-body {
            background-color: #ddd;
        }

        .item {
            width: 98%
        }
    </style>
</head>
<body>
<div class="header">
    <%@ include file="../common/navi.jsp" %>
</div>
<div class="body mt-5">
    <div class="container-fluid mb-4">
        <div class="row">
            <%@ include file="./mypagemenubar.jsp" %>
            <div class="col-8 mt-4">
                <div class="container">
                    <%@ include file="./mypageheader.jsp" %>
                    <div class="mypage-card-header mt-3 ">
                        <div class="row">
                            <div class="col-12">
                                <h2 class="font-weight-bold">내 메이트 보기</h2>
                                <div class="card mt-3">
                                    <div class="card-body">

                                        <div class="item">

                                            <div class="item-right">
                                                <div class="mt-3"></div>
                                                <h2 class="text-danger font-weight-bold text-center">R<span>석</span>
                                                </h2>
                                                <div class="mt-3"></div>
                                                <h1 class="num">
                                                    08/10
                                                </h1>
                                                <div class="mt-3"></div>
                                                <p class="day"><span>
														뭐
														</span>요일
                                                </p>
                                                <div class="text-center">
                                                    <!-- <button class="btn btn-primary mt-2" style="width: 130px;">좌석미리보기
                                                    </button>
                                                    <a href="matedetail.do?pid=&mnum="
                                                       class="btn btn-warning show-mate-btn mt-2" style="width: 130px;"
                                                       type="button">들어가기</a> -->
                                                </div>
                                                <span class="up-border"></span> <span class="down-border"></span>
                                            </div>

                                            <!-- end item-right -->

                                            <div class="item-left">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div>
                                                            <h2 class="title" style="display: inline-block;">카테고리</h2>
                                                            <span class="badge badge-info">상태</span>
                                                        </div>
                                                        <div class="event text-primary text-truncate mt-2">

                                                            <span>태그태그</span>

                                                        </div>
                                                        <div class="mt-5 text-muted	"
                                                             style="border-bottom: 1px solid gray;">
                                                            <i class="fas fa-flag"></i> 인원
                                                            <span id="mate-mem-now-count-0" class="ml-2">0</span>
                                                            <span>/</span> <span id="mate-mem-tot-count-0">4</span>
                                                        </div>
                                                        <div style="border-bottom: 1px solid gray;">
                                                            <div class="text-muted">
                                                                <i class="fas fa-user-friends"></i> 참여자 :

                                                                <span class="text-primary">닉네임/</span>

                                                            </div>
                                                            <div></div>
                                                        </div>
                                                        <div class="fix"></div>
                                                        <div class="loc">
                                                            <div class="text-muted">

                                                                <i class="fas fa-couch"></i> 좌석번호 :
                                                                <span>A1-1 ~ A1-3</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- end item-right -->
                                        </div>
                                        <div class="item">

                                            <div class="item-right">
                                                <div class="mt-3"></div>
                                                <h2 class="text-danger font-weight-bold text-center">R<span>석</span>
                                                </h2>
                                                <div class="mt-3"></div>
                                                <h1 class="num">
                                                    08/10
                                                </h1>
                                                <div class="mt-3"></div>
                                                <p class="day"><span>
														뭐
														</span>요일
                                                </p>
                                                <div class="text-center">
                                                    <!-- <button class="btn btn-primary mt-2" style="width: 130px;">좌석미리보기
                                                    </button>
                                                    <a href="matedetail.do?pid=&mnum="
                                                       class="btn btn-warning show-mate-btn mt-2" style="width: 130px;"
                                                       type="button">들어가기</a> -->
                                                </div>
                                                <span class="up-border"></span> <span class="down-border"></span>
                                            </div>

                                            <!-- end item-right -->

                                            <div class="item-left">
                                                <div class="row">
                                                    <div class="col-12">
                                                        <div>
                                                            <h2 class="title" style="display: inline-block;">카테고리</h2>
                                                            <span class="badge badge-info">상태</span>
                                                        </div>
                                                        <div class="event text-primary text-truncate mt-2">

                                                            <span>태그태그</span>

                                                        </div>
                                                        <div class="mt-5 text-muted	"
                                                             style="border-bottom: 1px solid gray;">
                                                            <i class="fas fa-flag"></i> 인원
                                                            <span id="mate-mem-now-count-1" class="ml-2">0</span>
                                                            <span>/</span> <span id="mate-mem-tot-count-1">4</span>
                                                        </div>
                                                        <div style="border-bottom: 1px solid gray;">
                                                            <div class="text-muted">
                                                                <i class="fas fa-user-friends"></i> 참여자 :

                                                                <span class="text-primary">닉네임/</span>

                                                            </div>
                                                            <div></div>
                                                        </div>
                                                        <div class="fix"></div>
                                                        <div class="loc">
                                                            <div class="text-muted">

                                                                <i class="fas fa-couch"></i> 좌석번호 :
                                                                <span>A1-1 ~ A1-3</span>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <!-- end item-right -->
                                        </div>
                                    </div>
                                </div>
                            </div>


                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="footer">
        <%@ include file="../common/footer.jsp" %>
    </div>
</div>
</body>
</html>