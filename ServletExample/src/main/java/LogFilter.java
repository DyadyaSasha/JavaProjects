import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

public class LogFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String testParam = filterConfig.getInitParameter("test-param");
        System.out.println("Test Param: " + testParam);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String ipAddress = servletRequest.getRemoteAddr();
        System.out.println("IP: " + ipAddress + " Time " + new Date().toString());

        /**
         * нужно всегда на объекте FilterChain вызывать в конце doFilter()
         * чтобы request и response передавались дальше по цепочке  и в конечном
         * счёте до сервлета
         * */
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("Filter destroyed");
    }
}
