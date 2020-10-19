<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
<form action="${pageContext.request.contextPath}/security/join" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    아이디 <input type="text" name="username"/> <br/>
    암호 <input type="password" name="password"/> <br/>
    암호확인 <input type="password" /> <br/>
    이름 <input type="text" name="name"/> <br/>
    권한
    <select name="role">
        <option value="ADMIN">관리자</option>
        <option value="MANAGER">매니저</option>
        <option value="USER">회원</option>
    </select> <br/>
    <input type="submit" value="회원가입"/>
<%--    <input type="radio" name="role" id="admin" value="admin"/><label for="admin">admin</label>
    <input type="radio" name="role" id="manager" value="manager"/><label for="manager">manager</label>
    <input type="radio" name="role" id="general" value="general"/><label for="general">general</label> <br/>
    --%>
</form>
</body>
</html>