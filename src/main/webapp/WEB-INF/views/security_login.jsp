<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/security/home">홈으로</a>
<form action="${pageContext.request.contextPath}/security/loginProcess" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    아이디 : <input type="text" name="username"> <br>
    암호 : <input type="password" name="passwd"> <br>
    <input type="submit" value="로그인">
</form>
</body>
</html>
