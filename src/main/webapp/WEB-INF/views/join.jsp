<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/member/join" method="post" enctype="multipart/form-data">
		아이디 <input type="text" name="userid"/> <br/>
		암호 <input type="password" name="userpw"/> <br/> 
		암호 <input type="password" /> <br/>
		이름 <input type="text" name="username"/> <br/>
		전화번호 <input type="text" name="userphone"/> <br/>
		나이 <input type="number" name="userage"/> <br/>
		사진첨부 <input type="file" name="tmp_img"> <br>
		<input type="submit" value="회원가입"/>
	</form>
</body>
</html>