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

        int index = ordersSytem.getLogs().size();

        model.put("id",index);
        model.put("orders",ordersSytem.getOrdersList());
        model.put("logs",ordersSytem.getLogs());

        return Template.render("dashboard.html",model);
    }
}
