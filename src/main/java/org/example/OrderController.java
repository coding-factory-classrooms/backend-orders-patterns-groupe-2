package org.example;

import org.example.core.Template;
import org.example.utils.URLUtils;
import spark.Request;
import spark.Response;

import java.util.Map;

public class OrderController {
    private final OrdersSytem ordersSytem;
    public OrderController(OrdersSytem ordersSytem) {
        this.ordersSytem = ordersSytem;
    }


    public String create(Request req, Response res) {
        Map<String, String> query = URLUtils.decodeQuery(req.body());

        String shoe_1 = query.get("shoe_1");
        if (shoe_1.equals("basket")){

        }
        return Template.render("order.html", model);
    }
}
