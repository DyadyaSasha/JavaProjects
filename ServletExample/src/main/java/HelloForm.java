import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

public class HelloForm extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("GET");
        Enumeration<String> enumeration = req.getHeaderNames();
        while (enumeration.hasMoreElements()){
            String header = enumeration.nextElement();
            System.out.println(header + " " + req.getHeader(header));
        }
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();
        String name = req.getParameter("first_name");
        String lname = req.getParameter("last_name");

        pw.println("<h1>" + name + "</h1>");
        pw.println("<h1>" + lname + "</h1>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("POST");
        doGet(req, resp);

    }
}
