<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/board/write" method="post">
    제목 <input type="text" name="brd_title"/> <br/>
    내용 <textarea name="brd_content"></textarea> <br/>
    작성자 <input type="text" name="brd_writer"> <br/>
    <input type="submit" value="글쓰기"/> <br/>
    <a href="${pageContext.request.contextPath}/home">홈으로</a>
</form>
</body>
</html>