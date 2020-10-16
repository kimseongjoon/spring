<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>admin</title>
</head>
<body>
    <a href="${PageContext.request.contextPath}/admin/home?menu=1">게시판관리</a>
    <a href="${PageContext.request.contextPath}/admin/home?menu=2">회원추가</a>
    <a href="${PageContext.request.contextPath}/admin/home?menu=3">물품등록관리</a>
    <a href="${PageContext.request.contextPath}/admin/home?menu=4">회원목록</a>
    <hr>

    <c:choose>
        <c:when test="${param.menu eq 1}">
            게시판관리
        </c:when>
        <c:when test="${param.menu eq 2}">
            회원추가
            <hr>
            <form action="${pageContext.request.contextPath}/admin/batchinsert" method="post">
                <table>
                <c:forEach var="i" begin="1" end="5">
                    <tr>
                        <td><input type="text" value="" name="id[]"></td>
                        <td><input type="text" value="1" name="pw[]"></td>
                        <td><input type="text" value="홍길동" name="name[]"></td>
                        <td><input type="text" value="010-0000-000" name="phone[]"></td>
                        <td><input type="text" value="11" name="age[]"></td>
                    </tr>
                </c:forEach>
                </table>
                <input type="submit" value="일괄추가">
            </form>
        </c:when>
        <c:when test="${param.menu eq 3}">
            물품등ㄹ고
        </c:when>
        <c:when test="${param.menu eq 4}">
            <jsp:include page="admin_menu.jsp"/>
        </c:when>
    </c:choose>
</body>
</html>
