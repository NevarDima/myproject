package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.service.SecurityService;
import by.nevar.dima.myproject.web.WebUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    private SecurityService securityService;

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        rq.getSession().removeAttribute("authUser");
        rq.getSession().invalidate();
        WebUtils.forword("login", rq, rs);
    }
}
