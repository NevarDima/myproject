package by.nevar.dima.myproject.web.filter;

import by.nevar.dima.myproject.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "AuthFilter")
public class AuthFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forward("login", rq, ((HttpServletResponse) servletResponse));
        }
        try {
            filterChain.doFilter(rq, servletResponse);
        } catch (IOException | ServletException e) {
            log.error("AuthFilter error", e);
            e.printStackTrace();
        }
    }
}
