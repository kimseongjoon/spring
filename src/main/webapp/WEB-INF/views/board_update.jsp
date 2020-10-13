<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>게시판 수정</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/board/update" method="post">
    <input type="hidden" value="${obj.brd_no}" name="brd_no">
    글번호 : ${obj.brd_no}<br>
    글제목 : <input type="text" value="${obj.brd_title}" name="brd_title"/> <br>
    글내용 : <textarea name="brd_content">${obj.brd_content}</textarea><br>
    작성자 : ${obj.brd_writer}<br>
    조회수 : ${obj.brd_hit}<br>
    날짜 : <input type="text" value="${obj.brd_date}" readonly><br>
    <input type="submit" value="수정하기" >
</form>

</body>
</html>
