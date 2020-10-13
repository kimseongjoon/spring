<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/board/write">글쓰기</a>
    <hr>
    <table>
        <tr>
            <th>글번호</th>
            <th>글제목</th>
            <th>작성자</th>
            <th>조회수</th>
            <th>날짜</th>
        </tr>
        <c:forEach items="${list}" var="tmp">
            <tr>
                <td>${tmp.brd_no}</td>
                <td><a href="${pageContext.request.contextPath}/board/content?no=${tmp.brd_no}">${tmp.brd_title}</a></td>
                <td>${tmp.brd_writer}</td>
                <td>${tmp.brd_hit}</td>
                <td>${tmp.brd_date}</td>
            </tr>
        </c:forEach>

    </table>
</body>
</html>
