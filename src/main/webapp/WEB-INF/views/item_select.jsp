<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>물품목록</title>
</head>
<body>
    <a href="${pageContext.request.contextPath}/jpa/item_insert.do">물품등록</a>
    <hr>
    <form action="${pageContext.request.contextPath}/jpa/item_select.do", method="get">
        <input type="text" name="name" placeholder="검색할 물품명을 입력"/>
        <input type="submit" value="검색"/>
    </form>
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
                <td class="no"><a href="${pageContext.request.contextPath}/jpa/item_detail.do?itmno=${tmp.itmno}">${tmp.itmno}</a></td>
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
    <c:forEach var="i" begin="1" end="${tot}" step="1">
        <a href="${pageContext.request.contextPath}/jpa/item_select.do?page=${i}&name=${param.name}">${i}</a>
    </c:forEach>


    <form action="${pageContext.request.contextPath}/jpa/item_search.do" name="search" method="get">
        <td align="center">
            <select name="field" size="1">
                <option value="subject">물품명
                <option value="writer">작성자
            </select>
            <input type="text" size="16" name="word">
            <input type="submit" value="검색">
        </td>
    </form>

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
