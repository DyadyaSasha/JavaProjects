<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Get form</title>
</head>
<body>
<form action="GETform.jsp" method="get">
    First name: <input type="text" name="firstName"/>
    Last name: <input type="text" name="lastName"/>
    <input type="submit" value="Submit">
</form>

<c:if test='<%= request.getParameter("firstName") != null && request.getParameter("lastName") != null %>'>
    <c:out value='First Name: ${pageContext.request.getParameter("firstName")}'/>
    <c:out value='Last Name: ${pageContext.request.getParameter("lastName")}'/>
</c:if>

</body>
</html>
