package org.example;

import java.util.ArrayList;
import java.util.List;

public class Momento {
    private final List<String> logs;
    private final List<Order> ordersList;

    public Momento(List<String> logs, List<Order> ordersList){
        this.logs = new ArrayList<>(logs.size());
        this.logs.addAll(logs);
        this.ordersList = new ArrayList<>(ordersList.size());
        for (Order order: ordersList) {
            this.ordersList.add(new Order(order));
        }
    }

    public List<String> getLogs(){
        return logs;
    }
    public List<Order> getOrdersSaved(){
        return ordersList;
    }

    @Override
    public String toString() {
        return "Momento{" +
                "logs=" + logs +
                "orders=" + ordersList+
                '}';
    }
}
