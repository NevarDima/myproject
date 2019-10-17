package by.nevar.dima.myproject.web.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LocaleFilter")
public class LocaleFilter implements Filter {

    private static final Logger log = LoggerFactory.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        HttpServletRequest rq = (HttpServletRequest) servletRequest;
        HttpSession session = rq.getSession();

        String locale = servletRequest.getParameter("locale");
        if (locale != null) {
            session.setAttribute("locale", locale);
        } else if ((session.getAttribute("locale")) == null) {
            String DEFAULTLOCALE = "en_US";
            session.setAttribute("locale", DEFAULTLOCALE);
        }
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (IOException | ServletException e) {
            log.error("LocaleFilter error", e);
            e.printStackTrace();
        }
    }
}
