package org.example;

import org.example.models.Clothes;
import org.example.models.Shoe;
import org.example.models.Sweater;

import java.util.ArrayList;
import java.util.List;

public class OrdersSytem {
    private List<Order> ordersList;
    private List<Clothes> availableClothes;

    public OrdersSytem() {
        ordersList = new ArrayList<>();
        availableClothes = createAvailableClothes();
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }
    public List<Clothes> getAvailableClothes(){return availableClothes;}


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
    public List<Clothes> createAvailableClothes(){
        ArrayList<Clothes> availableClothes = new ArrayList<>();
        availableClothes.add(new Shoe("botte",50,42));
        availableClothes.add(new Shoe("sandale",45,44));
        availableClothes.add(new Sweater("longues",20,Sweater.Size.S));
        availableClothes.add(new Sweater("colV",15,Sweater.Size.M));
        return availableClothes;
    }
}
