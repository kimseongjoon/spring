<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form action="${pageContext.request.contextPath}/jpa/item_update.do" method="post">
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">--%>
        <input type="hidden" name="itmno" value="${item.itmno}">
        name : <input type="text" name="itmname" value="${item.itmname}"><br/>
        content : <textarea name="itmcontent">${item.itmcontent}</textarea><br/>
        price : <input type="number" name="itmprice" value="${item.itmprice}"><br/>
        <input type="submit" value="상품수정">
    </form>
</body>
</html>
