<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>물품목록</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/jpa/item_insert.do">물품등록</a>
    <hr>
    <table>
        <tr>
            <th>물품번호</th>
            <th>물품명</th>
            <th>물품내용</th>
            <th>물품가격</th>
            <th>등록일</th>
            <th>비고</th>
        </tr>
        <c:forEach items="${list}" var="tmp">
            <tr>
                <td class="no">${tmp.itmno}</td>
                <td>${tmp.itmname}</td>
                <td>${tmp.itmcontent}</td>
                <td>${tmp.itmprice}</td>
                <td>${tmp.itmdate}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/jpa/item_update.do?itmno=${tmp.itmno}">수정</a>
                    <a href="#" class="btn_delete">삭제</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script>
        $(function () {
            $('.btn_delete').click(function () {
                // 현재 클릭한 삭제 버튼의 위치 가져오기
                var idx = $(this).index('.btn_delete');
                var no = $('.no').eq(idx).text();
                alert(idx);
                var result = confirm('삭제 하시겠습니까?');
                if (result) {
                    location.href = '${pageContext.request.contextPath}/jpa/item_delete.do?itmno=' + no;
                }
            })
        })
    </script>
</body>
</html>
