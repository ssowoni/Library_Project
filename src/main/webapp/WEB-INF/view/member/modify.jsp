<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html lang="en" data-bs-theme="auto">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.111.3">
    <title>회원 정보 수정</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">
    <link href="assets/dist/css/sign-in.css" rel="stylesheet">

    <jsp:include page="../includes/common_includes.jsp"></jsp:include>

    <script type="text/javascript" src="assets/dist/commonAjax.js"></script>
    <script type="text/javascript" src="assets/dist/js/memberJoinValidation.js"></script>

    <style>


        @media (min-width: 768px) {
            .bd-placeholder-img-lg {
                font-size: 3.5rem;
            }
        }


        .nav-scroller .nav {
            display: flex;
            flex-wrap: nowrap;
            padding-bottom: 1rem;
            margin-top: -1px;
            overflow-x: auto;
            text-align: center;
            white-space: nowrap;
            -webkit-overflow-scrolling: touch;
        }


    </style>



</head>
<script>

</script>
<body class="text-center">
<main class=" w-100 m-auto">
    <form  name="modify_form" action="/modify" method="post">
        <img class="mb-4" src="assets/brand/bootstrap-logo.svg" alt="" width="72" height="57"
             onclick="location.href='/'">
        <h1 class="h3 mb-3 fw-normal">My Page</h1>
        <div class="form-floating">
            <input type="email" class="form-control" name="email" id="email" value='<c:out value="${member.email}"/>' readonly>
            <!--label은 입력하는 칸 위에 작게 어떤 값을 입력하는지 알려주는 값.-->
            <label for="email">Email</label>
            <span id="id-check"></span>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="password" placeholder="Password" autoComplete="off">
            <label for="password">Password</label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" placeholder="passwordConfirm" autoComplete="off">
            <label for="passwordConfirm">passwordConfirm</label>
        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="nickname" id="nickname" value='<c:out value="${member.nickname}"/>'>
            <label for="nickname">nickname</label>
            <button class="btn btn-light rounded-pill px-3" type="button" name="btn" id="btnNicknameCheck">Duplicate check</button>
            <span id="nickname-check"></span>

        </div>
        <div class="form-floating">
            <input type="text" class="form-control" name="cellNo" id="cellNo" value='<c:out value="${member.cellNo}"/>'>
            <label for="cellNo">cellNo</label>
        </div>



        <div class="checkbox mb-3">
            <label>
                <input type="checkbox" value="remember-me"> Remember me
            </label>
        </div>
        <button class="w-50 btn btn-lg btn-dark" type="button" onclick="history.back();" >이전페이지로가기</button>
        <button class="w-50 btn btn-lg btn-primary" type="button" name="btn" id="btnModify" >회원정보수정</button>
    </form>
</main>



</body>
</html>
