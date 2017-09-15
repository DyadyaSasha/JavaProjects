package rest;

import org.hibernate.cfg.Configuration;

import javax.servlet.*;
import java.io.IOException;

public class DatabaseCreateFactory implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("init filter\ncreate session factory");
        DatabaseFactory.factory = new Configuration()
                .configure()
                .addAnnotatedClass(rest.User.class)
                .buildSessionFactory();
    }

    @Override
    public void destroy() {
        System.out.println("destroy filter\ndestroy session factory");
        DatabaseFactory.factory.close();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
