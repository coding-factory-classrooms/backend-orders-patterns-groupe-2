package org.example;

import org.example.core.Template;
import org.example.models.Clothes;
import org.example.utils.URLUtils;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

public class OrderController {
    private final OrdersSytem ordersSytem;
    private Order order;
    public OrderController(OrdersSytem ordersSytem) {
        this.ordersSytem = ordersSytem;
    }


    public String create(Request req, Response res) {

        order = new Order();
        Map<String, String> query = URLUtils.decodeQuery(req.body());

        String shoe_1 = query.get("shoe_1");
        searchClothes(shoe_1);
        String shoe_2 = query.get("shoe_2");
        searchClothes(shoe_2);
        String sweater = query.get("sweater");
        searchClothes(sweater);


        ordersSytem.addOrder(order);


        Map<String, Object> model = new HashMap<>();
        model.put("id", ordersSytem.getOrdersList().size() - 1);
        model.put("order", order);
        return Template.render("orderCustomer.html", model);
    }
    public String detail(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        int index = id - 1;

        order = ordersSytem.getOrdersList().get(index);

        String state = req.queryParamOrDefault("action", "");

        if(!state.isEmpty()){
            order.setState(Order.State.valueOf(state));
            System.out.println("State changed: " + order.getState());
        }

        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        model.put("order", order);
        return Template.render("orderDetail.html", model);
    }

    public String see(Request req, Response res) {
        int id = Integer.parseInt(req.params(":id"));
        int index = id - 1;

        order = ordersSytem.getOrdersList().get(index);


        Map<String, Object> model = new HashMap<>();
        model.put("id", id);
        model.put("order", order);
        return Template.render("orderCustomer.html", model);
    }

    private void searchClothes(String clothesToSearch){
        for (Clothes clothes: this.ordersSytem.getAvailableClothes()) {
            if(clothes.getName().equals(clothesToSearch)){
                order.addClothes(clothes);
                return;
            }
        }
    }
}
