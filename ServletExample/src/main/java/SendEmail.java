

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;


@WebServlet(value = "/sendmail", initParams = {
        @WebInitParam(name = "username", value = "username"),
        @WebInitParam(name = "password", value = ""),
        @WebInitParam(name = "to", value = "serebryakovalexx@yandex.ru"),
        @WebInitParam(name = "from", value = "serebryakov_sash@list.ru")
})
public class SendEmail extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", "localhost");
        properties.setProperty("mail.user", getInitParameter("from"));
        properties.setProperty("mail.password", getInitParameter("password"));

        Session session = Session.getDefaultInstance(properties);
        resp.setContentType("text/html");
        PrintWriter pw = resp.getWriter();

        try {

            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(getInitParameter("from")));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(getInitParameter("to")));

            message.setSubject("MAIL FROM JAVA");
            message.setText("HELLO JAVA!");
            Transport.send(message);
            pw.println("<h1>Send message</h1>");

        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {
        }


    }
}
