<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/home">홈으로</a>
    <form action="${pageContext.request.contextPath}/member/login" method="post">
        아이디 : <input type="text" name="userid"> <br>
        암호 : <input type="password" name="userpw"> <br>
        <input type="submit" value="로그인">
    </form>
</body>
</html>
