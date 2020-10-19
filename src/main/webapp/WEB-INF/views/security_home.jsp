<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Vector" %>
<%@ page import="java.util.Enumeration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <a href="${PageContext.request.contextPath}/security/join">회원가입</a>
    <a href="${PageContext.request.contextPath}/security/login">로그인</a> <br/>

    <sec:authorize access="isAuthenticated()"/>
    <sec:authentication property="principal.username" var="user_id" />
    <div id="user_id">안녕하세요. ${user_id }</div>

    <br/>

    <%
        String id=(String)session.getAttribute("id");
        Enumeration<?> v1 = session.getAttributeNames();

        while (v1.hasMoreElements()) {
            out.println(v1.nextElement());
        }


    %>

${sessionScope.username}

</body>
</html>
