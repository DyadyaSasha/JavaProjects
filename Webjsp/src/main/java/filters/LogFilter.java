package filters;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

/**
 * фильтры для jsp такие же как и для сервлетов, т.к. контейнер всё равно переводит jsp в сервлет
 * чтобы контейнер видел фильтр, фильтр нужно определить в web.xml
 * фильтры хорошо помогают если нужно исключить параметры из post запроса старой формы
 */
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter");
//        берётся из web.xml
        String testParam = filterConfig.getInitParameter("test-param");
        System.out.println("Test Param: " + testParam);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipAddress = servletRequest.getRemoteAddr();
        System.out.println("IP: "+ ipAddress + ", Time "+ new Date().toString());
//      нужно обязательно вызывать этот метод, чтобы request и response шли дальше по цепочке к искомому сервлету или jsp-странице
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy filter");
    }
}
