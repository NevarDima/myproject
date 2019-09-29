package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.AuthUser;
import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.service.impl.DefaultSecurityService;
import by.nevar.dima.myproject.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private SecurityService securityService = DefaultSecurityService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        Object authUser = rq.getSession().getAttribute("authUser");
        if (authUser == null) {
            WebUtils.forword("login", rq, rs);
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
            WebUtils.forword("login", rq, rs);
        }
        rq.getSession().setAttribute("authUser", user);
        try {
            rs.sendRedirect(rq.getContextPath() +"/user");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
