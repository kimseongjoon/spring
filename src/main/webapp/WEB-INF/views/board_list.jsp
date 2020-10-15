<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/board/write">글쓰기</a>
    <hr>
    <form action="" method="get">
        <select name="field" size="1">
            <option value="subject">제 목
            <option value="writer">작성자
        </select>
        <input type="text" name="txt">
        <input type="hidden" name="page" value="1">
        <input type="submit" value="검색">
    </form>
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
    <c:forEach var="i" begin="1" end="${cnt}">
        <a href="${pageContext.request.contextPath}/board/list?page=${i}">${i} </a>
    </c:forEach>
</body>
</html>
