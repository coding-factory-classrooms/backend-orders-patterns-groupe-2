package org.example;

import org.example.core.Conf;
import org.example.core.Template;
import org.example.middlewares.LoggerMiddleware;
import org.example.models.Shoe;
import org.example.models.Sweater;
import spark.Spark;

import java.util.HashMap;

public class App {
    public static void main(String[] args) {
        initialize();

        Order order1 = new Order();
        order1.addClothes(new Shoe("botte",50,42));

        Order order2 = new Order();
        order2.addClothes(new Shoe("sandales",50,42));

        OrdersSytem orderSystem = new OrdersSytem();
        orderSystem.addOrder(order1);
        orderSystem.addOrder(order2);

        BuyClothesController buyClothesController = new BuyClothesController(orderSystem);
        OrderController orderController = new OrderController(orderSystem);
        DashboardController dashboardController = new DashboardController(orderSystem);


        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });
        Spark.get("/buyClothes", (req, res) -> buyClothesController.list(req,res));
        Spark.post("/createOrder", (req, res) -> orderController.create(req,res));


        Spark.get("/dashboard", (req, res) -> dashboardController.list(req,res));

        Spark.get("/order/:id", (req, res) -> orderController.detail(req,res));


        Spark.get("/seeOrder/:id", (req, res) -> orderController.see(req,res));

    }

    static void initialize() {
        Template.initialize();

        // Display exceptions in logs
        Spark.exception(Exception.class, (e, req, res) -> e.printStackTrace());

        // Serve static files (img/css/js)
        Spark.staticFiles.externalLocation(Conf.STATIC_DIR.getPath());

        // Configure server port
        Spark.port(Conf.HTTP_PORT);
        final LoggerMiddleware log = new LoggerMiddleware();
        Spark.before(log::process);
    }
}
