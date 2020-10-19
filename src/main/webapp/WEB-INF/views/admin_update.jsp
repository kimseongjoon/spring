<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/admin/update" method="post">
    <table>
        <thead>
        <tr>
            <th>id</th>
            <th>pw</th>
            <th>name</th>
            <th>phone</th>
            <th>age</th>
            <th>date</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${list}" var="user">
            <tr>
                <%--<td><input type="checkbox" value="${user.userid}" name="chk[]"/></td>--%>
                <td><input type="text" value="${user.userid}" name="id[]" readonly/></td>
                <td><input type="text" value="${user.userpw}" name="pw[]"/></td>
                <td><input type="text" value="${user.username}" name="name[]"/></td>
                <td><input type="text" value="${user.userphone}" name="phone[]"/></td>
                <td><input type="text" value="${user.userage}" name="age[]"/></td>
                <td><input type="text" value="${user.userdate}" name="date[]" readonly/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <input type="submit">
</form>
</body>
</html>
