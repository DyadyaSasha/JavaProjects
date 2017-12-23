<%-- если не написать isELIgnored="false", то Expression Language(${}}) не будет работать,
 т.к. по умолчанию isELIgnored="true"
 EL позволяет получать доступ к полям бина, к полям, объявленным на jsp страничке,
  использовать функции --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%-- jstl теги инкапсулируют некоторую функциональность, свойственную jsp приложениям;
 виды jstl тегов: Core tags, Formatting tags, SQL tags, XML tags, JSTL Functions
 --%>

<%-- объявление core tags: --%>
<%--  директива taglib  нужна, чтобы определить дополнительные теги для использования
 <%@ taglib uri = "uri" prefix = "prefixOfTag" >
 uri - адрес ресурса, определяющего теги
 prefix - определяет имя, по которому можно будет использовать теги --%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="myTag" uri="/WEB-INF/custom.tld" %>
<html>
    <head>
        <title>Title</title>
    </head>
    <body>

    <myTag:Hello message="myAttribute">
        Alex
    </myTag:Hello>
    <%-- ${ns:func(param1, param2, ...)}
     ns - namespace функции может быть определена в JSTL или в своей кастомной taglib
     func - имя функции
     param1, param2, ... - параметры --%>
    <p>${fn:length("Get my length")}
    <%-- EL позволяет использовать в ${} неявные оюъекты:
    pageScope - Scoped variables from page scope
    requestScope - Scoped variables from request scope
    sessionScope - Scoped variables from session scope
    applicationScope - Scoped variables from application scope
    param - Request parameters as strings
    paramValues - Request parameters as collections of strings
    header - HTTP request headers as strings
    headerValues - HTTP request headers as collections of strings
    initParam - Context-initialization parameters
    cookie - Cookie values
    pageContext - The JSP PageContext object for the current page
    --%>
    <p>${pageContext.request.queryString}
    <p>${header["user-agent"]}
        <%-- используется для вывода результата на экран, аналог <%= %>
        (между : и видом core тега не должно быть пробела)<c:out > позволяет выводить через . свойства объектов
        в данном случае выведет: <tag> , & --%>
    <p><c:out value="${'<tag> , &'}"/>

        <%-- эквивалентно <jsp:setProperty> и объявлению и инициализации переменных в скриплете <%! %>,
         но <jsp:setProperty> обычно используется для бинов --%>
        <c:set var="salary" scope="session" value="${2000*2}"/>
    <p><c:out value="${salary}"/>

    <%-- удаляет переменную из заданного scope, если scope не определён, то из первоого же scope,
     где найдена эта переменная--%>
    <p><c:remove var="salary" scope="session"/>
    <p>After remove: <c:out value="${salary}"/>

    <%-- используется чтобы отловить ошибку
      var - имя переменной, в которой нужно будет держать обект ошибки--%>
    <c:catch var="catchException">
        <% int x = 5/0; %>
    </c:catch>

    <%-- блок if выполняется если test==true --%>
    <c:if test="${catchException != null}">
        <p>The exception is : ${catchException} <br />
        There is an exception: ${catchException.message}</p>
    </c:if>


    <c:set var="salary" scope="session" value="200"/>
    <%-- аналогично switch --%>
    <c:choose>
        <%-- тоже самое, что и case --%>
        <c:when test="${salary <= 0}">
            Salary is very low to survive.
        </c:when>
        <c:when test="${salary > 1000}">
            Salary is very good.
        </c:when>
        <%-- тоже самое, что и default --%>
        <c:otherwise>
            No comment
        </c:otherwise>
    </c:choose>

    <%-- циклы
    <c:forEach> - итерация по коллекциии ()
    <c:forTokens> - разбивает строку на токены и итерируется по ней --%>

    <c:forEach var="i" begin="0" end="4">
        <p><c:out value="${i}"/>
    </c:forEach>

    <c:forTokens items="Zara,nuha,roshy" delims="," var="word">
        <p><c:out value="${word}"/>
    </c:forTokens>


    <%-- редирект на другую страницу, происходит сразу припереходе на текущую страницу,
    в данном случае при переходе на /jstl выходит страница, указанная в url(может также быть указана jsp-страница, но и в url в строке
    браузера будет указано имя этой страницы, поэтому так делать небезопасно)
     если в url указать . , то редирект будет на адрес /
     можно также использовать respone.sendRedirect("url") --%>
    <%--<c:redirect  url="/bean"/>--%>

     <%-- используется для того чтобы задать url (може использоваться для перехода на другую страницу, по щелчку на соответствующей линке или для назначения
     request параметров) --%>
     <a href = "<c:url value = "/bean"/>">JavaBean</a>

    <c:url value = "/bean" var = "myURL">
        <%-- <c:param> позволяет задать request параметры данному url --%>
        <c:param name = "trackingId" value = "1234"/>
        <c:param name = "reportType" value = "summary"/>
    </c:url>
    <%-- <c:import>  аналоогичен <%@ include file = "относительный url" >
      но ещё позволяет подключать абсолютный url ресурса(сайт, html-страница и т.п.)
      Он вставляет в текущую страницу страницу, указанную в url--%>
    <c:import url="${myURL}"/>

    </body>
</html>
