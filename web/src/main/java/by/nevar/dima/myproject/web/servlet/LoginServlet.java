package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.service.impl.DefaultSecurityService;
import by.nevar.dima.myproject.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(LoginServlet.class);

    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forward("login", rq, rs);
        }
        try {
            rs.sendRedirect(rq.getContextPath() +"/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String login = rq.getParameter("login");
        String password = rq.getParameter("password");
        AuthUser user = securityService.login(login, password);
        if (user == null) {
            rq.setAttribute("error", "login or password invalid");
            log.info("Login - {} or password is invalid : {}", login, LocalDateTime.now());
            WebUtils.forward("login", rq, rs);
        }
        rq.getSession().setAttribute("authUser", user);
        log.info("User - {} logged in : {}", login, LocalDateTime.now());
        try {
            rs.sendRedirect(rq.getContextPath() +"/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
