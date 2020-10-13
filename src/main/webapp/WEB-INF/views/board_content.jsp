<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    글번호 : ${obj.brd_no}<br>
    글제목 : ${obj.brd_title}<br>
    글내용 : ${obj.brd_content}<br>
    작성자 : ${obj.brd_writer}<br>
    조회수 : ${obj.brd_hit}<br>
    날짜 : ${obj.brd_date}<br>
    <hr>
    <a href="${pageContext.request.contextPath}/board/list">글목록</a>
    <c:if test="${prev ne 0}">
        <a href="${pageContext.request.contextPath}/board/content?no=${prev}">이전글</a>
    </c:if>
    <c:if test="${next ne 0}">
        <a href="${pageContext.request.contextPath}/board/content?no=${next}">다음글</a>
    </c:if>

    <a href="${pageContext.request.contextPath}/board/update?no=${obj.brd_no}">글수정</a>

    <form action="${pageContext.request.contextPath}/board/delete" method="post">
        <input type="hidden" name="no" value="${obj.brd_no}" />
        <input type="submit" value="글삭제">
    </form>
</body>
</html>
