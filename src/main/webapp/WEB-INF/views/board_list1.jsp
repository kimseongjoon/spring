<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>
    <c:forEach var="i" begin="1" end="${cnt}">
        <a href="#" class="href">${i}</a>
    </c:forEach>

    <table id="output">
        <thead id="thead">
            <tr>
                <th>글번호</th>
                <th>글제목</th>
                <th>작성자</th>
                <th>조회수</th>
                <th>날짜</th>
            </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
    <script>
        $(function () {
            $.restCall = function (idx) {
                $.get('http://localhost:8080/rest/boardlist.json?page=' + (idx + 1), function (result) {
                    $('#tbody').empty();

                    let len = result.list.length;
                    for (let i = 0; i < len; i++) {
                        $('#tbody').append(
                            '<tr>' +
                            '<td>' + result.list[i].brd_no + '</td>' +
                            '<td>' + result.list[i].brd_title + '</td>' +
                            '<td>' + result.list[i].brd_writer + '</td>' +
                            '<td>' + result.list[i].brd_hit + '</td>' +
                            '<td>' + result.list[i].brd_date + '</td>' +
                            '</tr>'
                        )
                        console.log(result.list[i].brd_content);
                    }
                }, 'json');
            };

            $('.href').click(function () {
                var idx = $(this).index('.href');
                $.restCall(idx);
            });

            $.restCall(0);
        });
    </script>
</body>
</html>
