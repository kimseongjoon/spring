<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <table border="1">
        <tr>
            <th>
                물품번호
            </th>
            <td>
                ${obj.itmno}
            </td>
        </tr>
        <tr>
            <th>
                물품명
            </th>
            <td>
                ${obj.itmname}
            </td>
        </tr>
        <tr>
            <th>
                물품내용
            </th>
            <td>
                ${obj.itmcontent}
            </td>
        </tr>
        <tr>
            <th>
                물품가격
            </th>
            <td>
                ${obj.itmprice}원
            </td>
        </tr>
    </table>
</body>
</html>
--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jpa/item_image_insert.do" method="post" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
        물품번호 : <input type="text" name="no" value="${obj.itmno}" readonly><br/>
        물품명 : <input type="text" name="name" value="${obj.itmname}"><br/>
        물품내용 : <textarea name="content">${obj.itmcontent}</textarea><br/>
        물품가격 : <input type="number" name="price" value="${obj.itmprice}"><br/>
        이미지1 : <input type="file" name="img1"><br/>
        이미지2 : <input type="file" name="img1"><br/>
        <input type="submit" value="상품수정">
    </form>
    <hr/>

        <div style="display: flex;">
        <c:forEach var="tmp" items="${list}">
            <form action="${pageContext.request.contextPath}/jpa/item_image_delete.do" method="get">
            <div style="text-align: center">
                <input type="hidden" name="itmno" value="${tmp.itmno}">
                <input type="hidden" name="no" value="${tmp.no}">
                <img src="data:image/png;base64,${tmp.strImg}" width="100px" height="100px"/><br/>
                <button type="submit">삭제</button>
            </div>

            </form>
        </c:forEach>
        </div>
</body>
</html>
