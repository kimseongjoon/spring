<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <security:authorize access="!isAuthenticated()">
        <a href="${PageContext.request.contextPath}/security/join">회원가입</a>
        <a href="${PageContext.request.contextPath}/security/login">로그인</a> <br/>
    </security:authorize>

    <security:authorize access="isAuthenticated()">
        OBJECT : <security:authentication property="principal.username" var="user"/>
        ${user}<hr/>
        ROLE : <security:authentication property="authorities" /><br/>
        USERNAME : <security:authentication property="name"/>님 환영합니다.<br/>

        <security:authorize access="hasAuthority('ADMIN')">
            관리자입니다.
        </security:authorize>
        <security:authorize access="hasAuthority('MANAGER')">
            매니저입니다.
        </security:authorize>
        <security:authorize access="hasAuthority('USER')">
            사용자입니다.
        </security:authorize>

        <form action="${PageContext.request.contextPath}/security/logout" method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
            <input type="submit" value="로그아웃">
        </form>
    </security:authorize>

</body>
</html>
