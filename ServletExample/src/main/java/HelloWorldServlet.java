import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HelloWorldServlet extends HttpServlet{

    private String message = "";

    @Override
    public void init() throws ServletException{
        System.out.println("initialization");
        message = "TOMCAT";
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        pw.println("<h1>" + message + "</h1>");

        Enumeration<String> enumeration = req.getParameterNames();
        if(!enumeration.hasMoreElements()) System.out.println("sssss");
        do{
            System.out.println(req.getParameter(enumeration.nextElement()));
        } while (enumeration.hasMoreElements());
        System.out.println();
    }


    @Override
    public void destroy(){
        System.out.println("destroying");
    }
}
