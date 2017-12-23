<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 26.11.2017
  Time: 20:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>JavaBean</title>
    </head>
    <body>
    <%-- useBean позволяет использовать бин(создает объект указанного класса для данной страницы) на jsp-страничке --%>
    <%-- <jsp:useBean id = "bean's name" scope = "bean's scope" typeSpec/>
      scope может быть page, request, session, application based
      id - требуется(любое имя уникально идентифецирующее бин)--%>
      <jsp:useBean id="date" class="java.util.Date"/>
      <p>The date is <%= date%>

    <%-- говорим, какой бин нужно создать для страницы и сеттим поля--%>
    <jsp:useBean id="student" class="students.StudentsBean">
          <%-- name - это id бина, которому нужно засеттить поля(можно использовать вне тега <jsp:useBean>) --%>
        <jsp:setProperty name="student" property="firstName" value="Zara"/>
        <jsp:setProperty name="student" property="lastName" value="Ali"/>
        <jsp:setProperty name="student" property="age" value="10"/>
     </jsp:useBean>

    <%-- получаем поля
    name - это id бина, из которого нужно взять поле --%>
    <p><jsp:getProperty name="student" property="firstName"/>
    <p><jsp:getProperty name="student" property="lastName"/>
    <p><jsp:getProperty name="student" property="age"/>

    </body>
</html>
