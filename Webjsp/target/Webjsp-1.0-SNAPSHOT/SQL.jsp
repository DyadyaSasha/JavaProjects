<%-- sql теги используются для взаимодействия с БД --%>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html>
    <head>
        <title>Using SQL</title>
    </head>
    <body>

        <%-- требуется для подключения к базе, информация о подключении сохраняется в переменной, определённой
         в атрибуте var--%>
        <sql:setDataSource var="dataSource" driver="org.postgresql.Driver"
            url="jdbc:postgresql://localhost:5432/postgres"
            user="postgres"
            password="user"/>

        <h1>SELECT</h1>
        <%-- испольлзуется, чтобы выполнять SELECT запросы к БД
         dataSource - переменнная, хранящая подключение к базе
         var - определяется имя переменной, в которой будет храниться результат запроса,
         к этой переменной можно будет обращаться(итерироваться), чтобы получить записи из таблицы--%>
        <sql:query dataSource="${dataSource}" var="result">
            SELECT * FROM sqljsp;
        </sql:query>

        <table border="" width="100%">
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Age</th>
            </tr>

            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.id}"/></td>
                    <td><c:out value = "${row.first}"/></td>
                    <td><c:out value = "${row.last}"/></td>
                    <td><c:out value = "${row.age}"/></td>
                </tr>
            </c:forEach>
        </table>


            <h1>INSERT</h1>
            <%-- требуется, чтобы выполнять INSERT, UPDATE, DELETE; в переменной,
            объявленной в атрибуте var будет храниться число изменившихся строк таблицы --%>
            <sql:update dataSource="${dataSource}" var="insertRes">
                INSERT INTO sqljsp VALUES(104, 2, 'Nuha', 'Ali');
            </sql:update>

            <%-- повторно result не получится испольовать, т.к. она хранит данные с прошлого запроса,
             поэтому нужно создать новый запрос --%>
            <sql:query dataSource="${dataSource}" var="result">
                SELECT * FROM sqljsp;
            </sql:query>

        <table border="" width="100%">
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Age</th>
            </tr>

            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.id}"/></td>
                    <td><c:out value = "${row.first}"/></td>
                    <td><c:out value = "${row.last}"/></td>
                    <td><c:out value = "${row.age}"/></td>
                </tr>
            </c:forEach>
        </table>

        <h1>UPDATE</h1>
        <sql:update dataSource="${dataSource}" var="updateRes">
            UPDATE sqljsp SET last = 'SASHA' WHERE id = 104;
        </sql:update>

        <sql:query dataSource="${dataSource}" var="result">
            SELECT * FROM sqljsp;
        </sql:query>

        <table border="" width="100%">
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Age</th>
            </tr>

            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.id}"/></td>
                    <td><c:out value = "${row.first}"/></td>
                    <td><c:out value = "${row.last}"/></td>
                    <td><c:out value = "${row.age}"/></td>
                </tr>
            </c:forEach>
        </table>

        <h1>DELETE</h1>
        <%-- <sql:transaction> - используется, чтобы сгруппировать несколько запросов в одну
       транзакцию(позволяет делать rollback)--%>
        <sql:transaction dataSource="${dataSource}">
        <c:set var="id" value="<%= 104%>"/>
            <%--внутри транзакции dataSource определять не нужно --%>
        <sql:update var="deleteRes">
            DELETE FROM sqljsp WHERE id = ?
            <%-- используется в запросах !любых! типов, чтобы подставлять значение параметра вместо '?' --%>
            <sql:param value="${id}"/>
            <%-- <sql:dateParam> - аналогично <sql:param>, но для дат  --%>
        </sql:update>

            <%--внутри транзакции dataSource определять не нужно --%>
        <sql:query  var="result">
            SELECT * FROM sqljsp;
        </sql:query>
        </sql:transaction>
        <table border="" width="100%">
            <tr>
                <th>ID</th>
                <th>FirstName</th>
                <th>LastName</th>
                <th>Age</th>
            </tr>

            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.id}"/></td>
                    <td><c:out value = "${row.first}"/></td>
                    <td><c:out value = "${row.last}"/></td>
                    <td><c:out value = "${row.age}"/></td>
                </tr>
            </c:forEach>
        </table>

    </body>
</html>
