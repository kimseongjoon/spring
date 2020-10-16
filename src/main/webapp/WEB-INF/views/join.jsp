<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
	<form action="${pageContext.request.contextPath}/member/join" method="post" enctype="multipart/form-data">
		아이디 <input type="text" name="userid"/><div id="chk">아이디중복확인</div><br/>
		암호 <input type="password" name="userpw"/> <br/> 
		암호 <input type="password" /> <br/>
		이름 <input type="text" name="username"/> <br/>
		전화번호 <input type="text" name="userphone"/> <br/>
		나이 <input type="number" name="userage"/> <br/>
		사진첨부 <input type="file" name="tmp_img"> <br>
		<input type="submit" value="회원가입"/>
	</form>
	<script>
		$(function () {
			$('input[name=userid]').bind('keyup', function (event) {
				var id = $(this).val();
				if (id.length > 0) {
					//rest 호출 $.get()
					// $.get('http://localhost:8080/rest/ismember.json?id=' + id, function (result) {
					$.post('http://localhost:8080/rest/ismember.json', {id:id}, function (result) {
						// result값에 따라 아이디 사용유무 변경
						if (result < 1) {
							$('#chk').text('사용가능한 아이디입니다.');
						} else {
							$('#chk').text('이미 사용중인 아이디입니다.')
						}
					}, 'json');
				}
				else {
					$('#chk').text('아이디중복확인');
				}
			})
		})
	</script>
</body>
</html>