<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- подключаем встроенные JSTL функции --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Using JSTL Functions</title>
    </head>
    <body>

        <c:set var="theString" value="I am test String 123"/>
        <%-- проверка вхождения подстроки в строку ( ${fn:contains(theString, 'test')} - будет игнорировать регистр символов)--%>
        <c:if test="${fn:contains(theString, 'test')}">
            Found 'test' in string
        </c:if>


        <c:if test="${fn:endsWith(theString, '123')}">
            Ends with 123
        </c:if>

        <%-- позволяет не игнорировать XML теги --%>
        <c:set var="xmlString" value="This <abc>is second String.</abc>"/>
        <p>Xml escape string: ${fn:escapeXml(xmlString)}</p>
        <p>Without escapeXml(): ${xmlString}</p>

        <p>Index of: ${fn:indexOf(theString, "123")}</p>

        <%-- разделяет строку по указанному символу на массив строк --%>
        <c:set var="splitString" value="${fn:split(theString, ' ')}"/>
        <%-- соединяет массив строк в одну строку вставляя между ними указанный символ --%>
        <c:set var="joinString" value="${fn:join(splitString, '-')}"/>
        <p>String after split and join: ${joinString}</p>

        <%-- возвращает длину строки или размер коллекции --%>
        <p>Length split Strings array: ${fn:length(splitString)}</p>
        <p>Length join String: ${fn:length(joinString)}</p>

        <c:set var="replaceString" value="${fn:replace(theString, 't', 'T')}"/>
        <p><c:out value="${replaceString}"/></p>

        <p><c:if test="${fn:startsWith(theString, 'I am')}">
            Starts with 'I am'
        </c:if></p>


        <p><c:out value="${fn:substring(theString, 3, 8)}"/></p>

        <p><c:out value="${fn:substringBefore(theString, 'am')}"/></p>

        <p><c:out value="${fn:substringAfter(theString, 'am')}"/></p>

        <p><c:out value="${fn:toLowerCase(theString)}"/></p>

        <p><c:out value="${fn:toUpperCase(theString)}"/></p>

        <p><c:out value="${fn:trim('    AAAAAAaaaaa     ')}"/></p>
    </body>
</html>
