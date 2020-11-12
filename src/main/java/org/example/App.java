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

        Order order = new Order();
//        order.addClothes(new Shoe());
//        order.addClothes(new Sweater());
        OrdersSytem orderSystem = new OrdersSytem();
        BuyClothesController buyClothesController = new BuyClothesController(orderSystem);
        OrderController orderController = new OrderController(orderSystem);


        Spark.get("/", (req, res) -> {
            return Template.render("home.html", new HashMap<>());
        });
        Spark.get("/buyClothes", (req, res) -> buyClothesController.list(req,res));
        Spark.post("/createOrder", (req, res) -> orderController.create(req,res));

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
