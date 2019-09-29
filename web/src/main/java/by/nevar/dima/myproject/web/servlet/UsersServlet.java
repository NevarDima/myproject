package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.User;
import by.nevar.dima.myproject.service.UserService;
import by.nevar.dima.myproject.service.impl.DefaultUserService;
import by.nevar.dima.myproject.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet("/user")
public class UsersServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UsersServlet.class);
    private UserService userService = DefaultUserService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) {
        List<User> users = userService.getUser();
        rq.setAttribute("users", users);
        WebUtils.forword("user", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) {
        String firstName = rq.getParameter("firstName");
        String lastName = rq.getParameter("lastName");
        String phone = rq.getParameter("phone");
        String email = rq.getParameter("email");
        String id = userService.saveUser(new User(firstName, lastName, phone, email));
        log.info("user created:{} at {}", id, LocalDateTime.now());

        try {
            rs.sendRedirect(rq.getContextPath() + "/user");
        } catch (IOException e) {
            log.error("unknown error", e);
            throw new RuntimeException(e);
        }
    }
}
