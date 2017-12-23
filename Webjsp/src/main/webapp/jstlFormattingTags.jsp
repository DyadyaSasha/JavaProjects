
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%-- format тэги используются для форматирования текста, даты, времени, чисел для их вывода в должном виде   --%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
    <head>
        <title>Formatting Tags</title>
    </head>
    <body>
        <%-- <fmt:formatNumber> используется , чтобы форматировать числа, проценты, валюту --%>
        <c:set var = "balance" value="120000.2309"/>

        <p>Formatted Number (1): <fmt:formatNumber value="${balance}" type="currency"/></p>
        <p>Formatted Number (2): <fmt:formatNumber value="${balance}" type="number" maxIntegerDigits="3"/></p>
        <p>Formatted Number (3): <fmt:formatNumber value="${balance}" type="number" maxFractionDigits="3"/></p>
        <p>Formatted Number (4): <fmt:formatNumber value="${balance}" type="number" groupingUsed="false"/></p>
        <p>Formatted Number (5): <fmt:formatNumber value="${balance}" type="percent" maxIntegerDigits="3"/></p>
        <p>Formatted Number (6): <fmt:formatNumber value="${balance}" type="percent" maxFractionDigits="10"/></p>
        <p>Formatted Number (8):<fmt:formatNumber value="${balance}" type="number" pattern="###.###E0"/></p>
            <fmt:setLocale value="en_US"/>
        <p>Currency in USA: <fmt:formatNumber value="${balance}" type="currency"/></p>

        <%-- парсит число, проценты, валюту по заданным правилам --%>
        <fmt:parseNumber var="i" value="${balance}" type="number"/>
        <p>Parsed Number(1): <c:out value="${i}"/></p>
        <fmt:parseNumber var="i" value="${balance}" integerOnly="true"
        type="number"/>
        <p>Parsed Number(2): <c:out value="${i}"/></p>

        <%-- форматирование дат --%>
        <c:set var="now" value="<%= new java.util.Date()%>"/>
        <p>Formatted date(1): <fmt:formatDate value="${now}" type="time"/></p>
        <p>Formatted date(2):<fmt:formatDate value="${now}" type="date"/></p>
        <p>Formatted date(3):<fmt:formatDate value="${now}" type="both"/></p>
        <p>Formatted date(4):<fmt:formatDate value="${now}" type="both" dateStyle="short" timeStyle="short"/></p>
        <p>Formatted date(5):<fmt:formatDate value="${now}" type="both" dateStyle="medium" timeStyle="medium"/></p>
        <p>Formatted date(6):<fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long"/></p>
        <p>Formatted date(7):<fmt:formatDate value="${now}" pattern="yyyy-MM-dd"/></p>

        <%-- парсинг дат --%>
        <p><fmt:parseDate value="20-10-2010" var="parsedValue" pattern="dd-MM-yyyy"/></p>
        <p>Parsed date: <c:out value="${parsedValue}"/></p>

        <%-- <fmt:timeZone></fmt:timeZone> - устанавливает временную зону, которую будут использовать все теги, находящиеся внутри данного тега --%>
        <%-- <fmt:setTimeZone />  устанавливает временную зону для переменной, если переменная не указана, то для всех переменных, связанных с временем--%>

        <%-- нужно, чтобы сопаставить или назначить нужную локаль с переменной --%>
        <%--<fmt:setLocale value="es_ES"/>--%>
        <%--<fmt:requestEncoding value=""/> - чтобы назачить кодировку запроса(обычно используется в формах)--%>
        <%--<fmt:bundle/> <fmt:message/>  - используются для интернационализации --%>


    </body>
</html>
