package org.example;

import org.example.core.Template;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class DashboardController {
  private final OrdersSytem ordersSytem;
    public DashboardController(OrdersSytem ordersSytem) {
        this.ordersSytem = ordersSytem;
    }

    public String list (Request request, Response response){
        Map<String, Object> model = new HashMap<>();

        model.put("orders",ordersSytem.getOrdersList());

        return Template.render("dashboard.html",model);
    }
}
