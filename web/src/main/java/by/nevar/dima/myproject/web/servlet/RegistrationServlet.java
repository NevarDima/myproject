package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.service.impl.DefaultSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;

import static by.nevar.dima.myproject.web.WebUtils.forward;

@WebServlet(name = "RegistrationServlet", urlPatterns = "/registration")
public class RegistrationServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(RegistrationServlet.class);

    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        forward("registration", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {

        final String login = rq.getParameter("login");
        final String password = rq.getParameter("password");
        final AuthUser authUser = securityService.saveAuthUser(new AuthUser(login, password));
        if (authUser == null) {
            log.error("Failed to register user with login - {} pass - {} : {}", login, password, LocalDateTime.now());
            doGet(rq, rs);
            return;
        }
        rq.getSession().setAttribute("authUser",authUser);
        log.info("User - {} registered : {}", login, LocalDateTime.now());
        try {
            rs.sendRedirect(rq.getContextPath() + "/index");
        } catch (IOException e) {
            log.error("Failed to redirect to main page : {}", LocalDateTime.now());
            throw new RuntimeException();
        }
    }
}
