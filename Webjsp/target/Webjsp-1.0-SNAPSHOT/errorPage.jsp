<%-- isErrorPage при true даёт доступ к объекту Throwable и к его
 методам через переменную exception --%>
<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<html>
<head>
    <title>ErrorPage</title>
</head>
<body>
<h1>This is Error Page</h1>
<%=exception.getMessage() %>
</body>
</html>
