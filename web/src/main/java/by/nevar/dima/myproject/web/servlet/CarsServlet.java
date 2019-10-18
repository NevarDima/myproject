package by.nevar.dima.myproject.web.servlet;

import by.nevar.dima.myproject.model.Car;
import by.nevar.dima.myproject.model.RoleCar;
import by.nevar.dima.myproject.service.CarService;
import by.nevar.dima.myproject.service.impl.DefaultCarService;
import by.nevar.dima.myproject.web.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

@WebServlet(name = "CarsServlet", urlPatterns = "/index")
public class CarsServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CarsServlet.class);
    private CarService carService = DefaultCarService.getInstance();

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws UnsupportedEncodingException {
        WebUtils.utf(rq, rs);
        List<Car> cars = carService.getAllCars();
        rq.setAttribute("cars", cars);
        WebUtils.forward("index", rq, rs);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws UnsupportedEncodingException {
        WebUtils.utf(rq, rs);
        String brand = rq.getParameter("brand");
        String model = rq.getParameter("model");
        RoleCar roleCar = RoleCar.valueOf(rq.getParameter("role_car"));
        Car car = carService.saveCar(new Car(brand, model, roleCar));
        log.info("Car with id - {} created : {}", car.getId(), LocalDateTime.now());

        try {
            rs.sendRedirect(rq.getContextPath() + "/car");
        } catch (IOException e) {
            log.error("unknown error", e);
            throw new RuntimeException(e);
        }
    }
}
