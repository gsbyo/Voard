<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인 페이지</title>
</head>
<body>
    <h1>로그인</h1>
    <hr>

    <form action="/login" method="post">
        <input type="hidden" th:name="${_csrf.parameterName}" th:value="${_csrf.token}" />

        <input type="text" name="username" placeholder="이메일 입력해주세요">
        <input type="password" name="password" placeholder="비밀번호">

        <p>
            <label for="remember-me" style>로그인 유지</label>  <input type="checkbox" id="remember-me" name="remember-me" />
        </p>

        
        <button type="submit">로그인</button>
    </form>
</body>
</html>