<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%!
    int pageCount = 0;
    void addCount(){
        pageCount++;
    }
%>
<% addCount(); %>
<html>
<head>
    <title>Include Directive</title>
</head>
<body>
    <center>
        <h2>Include Directive</h2>
        <p>This site has been visited <%= pageCount %> times.</p>
    </center>
    <br/><br/>
