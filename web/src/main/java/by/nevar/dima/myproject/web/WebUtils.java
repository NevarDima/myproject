package by.nevar.dima.myproject.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class WebUtils {
    private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

    public static void forward(String page, HttpServletRequest rq, HttpServletResponse rs) {
        try {
            rq.getRequestDispatcher("/" + page + ".jsp").forward(rq, rs);
        } catch (ServletException | IOException e) {
            log.error("Error in forward method, page - {}", page, e);
            throw new RuntimeException(e);
        }

    }
}
