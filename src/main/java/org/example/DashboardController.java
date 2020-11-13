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

        String action = request.queryParamOrDefault("action", "");

        if(action.equals("undo")){
            ordersSytem.undo();
        }else if(action.equals("redo")){
            ordersSytem.redo();
        }

        int index = ordersSytem.getLogs().size();

        model.put("id",index);
        model.put("orders",ordersSytem.getOrdersList());
        model.put("logs",ordersSytem.getLogs());

        return Template.render("dashboard.html",model);
    }

}
