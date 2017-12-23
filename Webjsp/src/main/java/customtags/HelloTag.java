package customtags;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.io.StringWriter;

public class HelloTag extends SimpleTagSupport{
//  связь с определением атрибута в custom.tld
//  происходит посредством одинаковых имён
    private String message;

    public void setMessage(String msg){
        message = msg;
    }

//  создаём чтобы считать содержимое внутри тега с помощью getJspBody().invoke(sw)
    StringWriter sw = new StringWriter();

    @Override
    public void doTag() throws JspException, IOException {
        JspWriter out = getJspContext().getOut();
        getJspBody().invoke(sw);
        String attributeMessage = "";
        if (message !=null){
            attributeMessage = message;
        }
        out.print("Hello custom tag! body:  " + sw.toString() +
                ", attribute: " + attributeMessage);
    }
}
