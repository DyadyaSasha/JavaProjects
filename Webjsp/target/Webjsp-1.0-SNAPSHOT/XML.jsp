<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%-- подключаем теги для манипуляции, парсинга XML --%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <title>Using XML</title>
    </head>
    <body>
    <%-- не хочет подгружаться xml --%>
        <%--<c:url value="books.xml" var="books"/>--%>
        <%--<c:import var="books" url="WEB-INF/books.xml"/>--%>
        <%--<x:parse xml="${books}" var="output"/>--%>


        <c:set var="xmlText">
            <books>
                <book>
                    <name>Padam History</name>
                    <author>ZARA</author>
                    <price>100</price>
                </book>

                <book>
                    <name>Great Mistry</name>
                    <author>NUHA</author>
                    <price>2000</price>
                </book>
            </books>
        </c:set>

        <%-- парсит указанный xml файл/строку --%>
        <x:parse  var="output" doc="${xmlText}"/>
        <b>The title of the first book is</b>:
        <%-- показывает результат XPath выражения в аргументе select --%>
        <x:out select="$output/books/book[1]/name"/>
        <br><b>The price of the second book</b>:
        <%-- присваивает переменной значение XPath выражения
         почему-то нормально работать не хочет, поэтому лучше использовать верхний вариант--%>
        <x:set var="price" select="$output/books/book[1]/price"/>
        <c:out  value="${price}"/>
        <br>
        <%-- если выражение XPath истинно, выполняет тело if --%>
        <x:if select="$output//book">
            Document has at least one <book> element.
        </x:if>
        <br>
        <x:if select="$output/books[1]/book/price > 100">
            Book prices are very high
        </x:if>
        <br>
        <%-- используется, чтобы пройтись по всем нодам xml документа --%>
        <ul class = "list">
            <x:forEach select = "$output/books/book/name" var = "item">
                <li>Book Name: <x:out select = "$item" /></li>
            </x:forEach>
        </ul>
        <br>
        <%-- аналог switch --%>
        <x:choose>
            <%-- аналог case --%>
            <x:when select="$output//book/author = 'ZARA'">
                Book is written by ZARA
            </x:when>

            <x:when select = "$output//book/author = 'NUHA'">
                Book is written by NUHA
            </x:when>
            <%-- аналог default --%>
            <x:otherwise>
                Unknown author.
            </x:otherwise>
        </x:choose>
    </body>
</html>
