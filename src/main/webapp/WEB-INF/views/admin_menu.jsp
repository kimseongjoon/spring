<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
전체 회원목록
<hr/>
<form action="${pageContext.request.contextPath}/admin/batchupdatedelete" method="post">
    <input type="submit" value="일괄삭제" name="btn"/>
    <input type="submit" value="일괄수정" name="btn"/>
    <table>
        <thead>
            <tr>
                <th>체크</th>
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
                <td><input type="checkbox" value="${user.userid}" name="chk[]"/></td>
                <td>${user.userid}</td>
                <td>${user.userpw}</td>
                <td>${user.username}</td>
                <td>${user.userphone}</td>
                <td>${user.userage}</td>
                <td>${user.userdate}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</form>
<hr/>