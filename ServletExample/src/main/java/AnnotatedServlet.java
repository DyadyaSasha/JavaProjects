import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(value = "/annotated", initParams = {
        @WebInitParam(name = "hello", value = "Hello "),
        @WebInitParam(name = "world", value = "World!")
})
public class AnnotatedServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        pw.println("<html><body>");
        pw.println("<h1>Hello @Annotation</h1>");
        pw.println(getInitParameter("hello"));
        pw.println(getInitParameter("world"));
        pw.println("</body></html>");

    }
}
