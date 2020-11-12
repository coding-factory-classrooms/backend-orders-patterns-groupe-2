package org.example;

import org.example.models.Clothes;
import org.example.models.Shoe;
import org.example.models.Sweater;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class OrdersSytem implements Order.onStateOrderChangedListener{
    private List<Order> ordersList;
    private List<Clothes> availableClothes;
    private SystemLogger logs;

    public OrdersSytem(SystemLogger logs) {
        ordersList = new ArrayList<>();
        availableClothes = createAvailableClothes();
        this.logs = logs;
    }

    public List<String> getLogs() {
        return logs.getLogsList();
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }
    public List<Clothes> getAvailableClothes(){return availableClothes;}


    public void setOrdersList(List<Order> ordersList) {
        this.ordersList = ordersList;
    }


    /**
     * @param order to add
     * @return true if the statement of the order is NEW, else false
     */
    public boolean addOrder(Order order) {
        if (order.getState() != Order.State.NEW) {
            return false;
        }
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:s");
        logs.addLog(simpleFormat.format(date) + "</td> <td>" + order);
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

    @Override
    public void onStateChanged(Order order) {
        Date date = new Date();
        SimpleDateFormat simpleFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:s");
        logs.addLog(simpleFormat.format(date) + "</td> <td>" + order);
    }
}
