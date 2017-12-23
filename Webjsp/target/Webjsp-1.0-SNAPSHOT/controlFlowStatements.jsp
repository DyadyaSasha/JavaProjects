<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Control Flow Statements</title>
    </head>
    <body>
        <%-- мужду незавершённых скриплетов можно вставлять html и java код --%>
        <h1>if...else statement</h1>
        <%! int day = 3; %>
        <%if (day == 1 || day == 7){ %>
        <p>Today is weekend</p>
        <% } else { %>
        <p>Today is not weekend</p>
        <% } %>

        <h1>switch statement</h1>
        <%
            switch (day){
                case 0:
                    out.println("It\\'s Sunday");
                    break;
                case 1:
                    out.println("It\'s Monday.");
                    break;
                case 2:
                    out.println("It\'s Tuesday.");
                    break;
                case 3:
                    out.println("It\'s Wednesday.");
                    break;
                case 4:
                    out.println("It\'s Thursday.");
                    break;
                case 5:
                    out.println("It\'s Friday.");
                    break;
                default:
                    out.println("It's Saturday.");
            }
        %>

        <h1>for statement</h1>
            <%for(int fontSize = 1; fontSize <= 3; fontSize++){%>
                <font color="green" size="<%= fontSize%>">
                    JSP
                </font><br/>
            <%}%>

        <h1>while statement</h1>
        <%! int fontSize; %>
        <% while (fontSize <= 3){%>
            <font color="green" size="<%= fontSize%>">
                JSP
            </font><br/>
        <% fontSize++;%>
        <%}%>
    </body>
</html>
