package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

@WebServlet(name = "LogoutServlet", urlPatterns = "/logout")
public class LogoutServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        long id = ((AuthUser) rq.getSession().getAttribute("authUser")).getId();
        log.info("User with id - {} logged out : {}", id, LocalDateTime.now());
        rq.getSession().removeAttribute("authUser");
        rq.getSession().invalidate();
        WebUtils.forward("login", rq, rs);
    }
}
