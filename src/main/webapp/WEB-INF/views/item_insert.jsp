<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jpa/item_insert.do" method="post">
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
        name : <input type="text" name="itmname"><br/>
        content : <textarea name="itmcontent"></textarea><br/>
        price : <input type="number" name="itmprice"><br/>
        <input type="submit" value="상품추가">
    </form>
</body>
</html>
