<%@ page import="java.io.File" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.*" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Implicit Objects</title>
    </head>
    <body>
        <%-- JSP страница автоматически
         поддерживает 9 уже автоматически определённых объектов
1.request - This is the HttpServletRequest object associated with the request to the server.
(через request можно вызывать методы для получения HTTP заголовков, form data и другие методы, относящиеся к клиенту)
2.response - This is the HttpServletResponse object associated with the response to the client.
(через request можно вызывать методы для получения HTTP заголовков, кодов статуса и другие методы, относящиеся к серверу)
3.out - This is the PrintWriter object used to send output to the client.
4.session - This is the HttpSession object associated with the request.
(нужно, чтобы прослеживать сессию клиента между его запросами, по умолчанию jsp отслеживаает сессию клиента(чтобы отключить
нужно в директиве page установить session = false))
5.application - This is the ServletContext object associated with the application context.
(объект, представляющий JSP страницу, с начала(jspInit()) и до конца(jspDestroy()) жизни, т.е. на всём жизненном цикле jsp страницы)
6.config - This is the ServletConfig object associated with the page.
(позволяет получить доступ к параметрам инициализации сервлета  или jsp таких как: пути к файлу,
имя сервлета(которое определено web.xml в теге <servlet-name>) и другим)
7.pageContext - This encapsulates use of server-specific features like higher performance JspWriters.
(используется для доступа к содержанию jsp-страницы, содержит ссылки на объекты response, request,exception, out, config, session и др.,
содержит информацию о директивах(и что в них содержится) jsp страницы)
8.page - This is simply a synonym for "this", and is used to call the methods defined by the translated servlet class.
9.exception - The Exception object allows the exception data to be accessed by designated JSP.
(доступен при условии, что в директиве page атрибут isErrorPage = true)
--%>

        <h1>request Object Example</h1>
        <br>
        <table width="100%" border="1" align="center">
            <tr bgcolor="#949494">
                <th>Header Name</th>
                <th>Header Value(s)</th>
            </tr>
            <%
                for (Enumeration<String> headerNames = request.getHeaderNames();
                headerNames.hasMoreElements();){
                    String paramName = headerNames.nextElement();
                    out.print("<tr><td>" + paramName + "</td>\n");
                    String paramValue = request.getHeader(paramName);
                    out.println("<td>" + paramValue + "</td></tr>\n");
                }
            %>
        </table>

        <h1>response Object Example</h1>
        <br>
        <%
            //устанавливаем загаловок Refresh = 5 (автосатическое обновление страницы через каждые 5 сек)
            response.setIntHeader("Refresh", 5);
            Calendar calendar = new GregorianCalendar();

            String am_pm;
            int hour = calendar.get(Calendar.HOUR);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);

            if(calendar.get(Calendar.AM_PM) == 0){
                am_pm = "AM";
            } else {
                am_pm = "PM";
                String CT = hour+":"+ minute +":"+ second +" "+ am_pm;
                out.println("Current Time is: " + CT + "\n");

            }
        %>

        <h1>session Object Example</h1>
        <br>
        <%
            Date createTime = new Date(session.getCreationTime());
            Date lastAccessTime = new Date(session.getLastAccessedTime());

            String title = "Welcome back!";
            Integer visitCount = 0;
            String visitCountKey = "visitCount";
            String userIDKey = "userID";
            String userID = "ABCD";

            if(session.isNew() || session.getAttribute(userID) == null){
                title = "Welcome to site!";
//                аналагично, что и в примере с объектом application, только значения для каждого пользователя
//                будут свои, а не общие как при application
                session.setAttribute(userIDKey, userID);
                if (session.getAttribute(visitCountKey) == null) session.setAttribute(visitCountKey, visitCount);
            }
            visitCount = (Integer) session.getAttribute(visitCountKey);
            if (visitCount != null) visitCount = visitCount + 1;
            session.setAttribute(visitCountKey, visitCount);
        %>

        <p><%= title%></p>
        <table border="1" align="center">
            <tr bgcolor="#949494">
                <th>Session info</th>
                <th>Value</th>
            </tr>
            <tr>
                <td>Creation Time</td>
                <td><%= createTime%></td>
            </tr>
            <tr>
                <td>Time of last Access</td>
                <td><% out.print(lastAccessTime);%></td>
            </tr>
            <tr>
                <td>User ID</td>
                <td><%= userID%></td>
            </tr>
            <tr>
                <td>Number of visits</td>
                <td><%= visitCount%></td>
            </tr>
        </table>

        <h1>application Object Example</h1>
        <br>
        <%
            Integer hitsCount = (Integer) application.getAttribute("hitCounter");
            if(hitsCount == null || hitsCount == 0){
                out.println("Welcome to website!");
                hitsCount = 1;
            } else {
                out.println("Welcome back to website!");
                hitsCount += 1;
            }
            // можно создавать атрибуты типа key-value, которые будут доступны на протяжении
            // жизни jsp-страницы
            application.setAttribute("hitCounter", hitsCount);

        %>
        <p>Total Number of visits: <%= hitsCount%></p>

        <h1>pageContext Object Example </h1>
        <br>
        <%-- для форм всегда метод POST
        после нажатия на кнопку "Upload File"
         запрос будет перенаправлен на uploadFile.jsp,
         --%>

        <form action="uploadFile.jsp" method="post"
        enctype="multipart/form-data">
            <input type="file" name="file" size="50"/>
            <br/>
            <input type="submit" value="Upload File"/>
        </form>



    </body>
</html>
