<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jpa/item_update_batch.do" method="post">
    <table>
        <tr>
            <th>물품명</th>
            <th>물품내용</th>
            <th>물품가격</th>
        </tr>
        <c:forEach items="${list}" var="item">
            <tr>
                <td>
                    <input type="hidden" name="ids[]" value="${item.itmno}">
                    <input type="text" name="names[]" value="${item.itmname}">
                </td>
                <td><input type="text" name="contents[]" value="${item.itmcontent}"></td>
                <td><input type="text" name="prices[]" value="${item.itmprice}"></td>
            </tr>
        </c:forEach>
    </table>
    <input type="submit" value="일괄변경" />
    </form>
</body>
</html>
