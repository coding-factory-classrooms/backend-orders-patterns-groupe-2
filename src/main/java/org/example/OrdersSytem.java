package org.example;

import java.util.ArrayList;
import java.util.List;

public class OrdersSytem {
    private List<Order> ordersList;

    public OrdersSytem() {
        ordersList = new ArrayList<>();
    }
    public List<Order> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }

    /**
     *
     * @param order to add
     * @return true if the statement of the order is NEW, else false
     */
    public boolean addOrder(Order order) {
        if (order.getState() != Order.State.NEW) {
           return false;
        }
        return ordersList.add(order);
    }
}
