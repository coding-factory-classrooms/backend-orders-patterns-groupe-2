package org.example;

import org.example.core.Template;
import org.example.models.Clothes;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyClothesController {
  private final OrdersSytem ordersSytem;
    public BuyClothesController(OrdersSytem ordersSytem) {
        this.ordersSytem = ordersSytem;
    }

    public String list (Request request, Response response){
        Map<String, Object> model = new HashMap<>();
        model.put("clothes",ordersSytem.getAvailableClothes());

        return Template.render("buyClothes.html",model);
    }
}
