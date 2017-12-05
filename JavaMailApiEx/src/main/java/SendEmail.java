import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * отправка сообщения через SMTP сервер Google
 * !!!перед отправкой нужно в настройках аккаунта Google разрешить использовать
 * аккаунт небезопасным приложениям!!!("Ненадежные приложения разрешены" -> true)
 */

public class SendEmail{

    public static void main(String args[]){

        String to = "полный адресс кому отправляем";


//      логин без @gmail.com
        String username = "*********";
        String password = "*********";

        String host = "smtp.gmail.com";

        Properties prop = new Properties();
        prop.put("mail.smpt.auth", "true");
        prop.put("mail.smtp.user", username);
        prop.put("mail.smtp.password", password);
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", host);
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, null);

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
//          тема сообщения(заголовок)
            message.setSubject("Java Message");
//          текст сообщения
            message.setText("Hello Alex!");

            Transport transport = session.getTransport("smtp");
            transport.connect(host, username, password);
            transport.sendMessage(message, message.getAllRecipients());

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}