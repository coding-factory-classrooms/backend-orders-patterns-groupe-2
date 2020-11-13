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



    private final List<Momento> momentoList = new ArrayList<Momento>();


    public List<Momento> getMomentoList() {
        return momentoList;
    }

    private Momento momento;

    public Momento getMomento() {
        return momento;
    }


    public OrdersSytem(SystemLogger logs) {
        ordersList = new ArrayList<>();
        availableClothes = createAvailableClothes();
        this.logs = logs;
        momento = new Momento(new ArrayList<String>(), new ArrayList<Order>());
        getMomentoList().add(momento);
    }

    public List<String> getLogs() {
        return logs.getLogsList();
    }

    public List<Order> getOrdersList() {
        return ordersList;
    }
    public List<Clothes> getAvailableClothes(){return availableClothes;}




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
        ordersList.add(order);
        saveLogs();
        return true;
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
        saveLogs();
    }

    public void saveLogs(){

        momento = logs.save(momentoList, ordersList);
    }

    public void undo() {
        if(momentoList.indexOf(momento) == 0){
            return;
        }

        int index = momentoList.indexOf(momento)-1;


        momento = momentoList.get(index);
        ordersList = momento.getOrdersSaved();
        logs.restore(momento);

    }
    public void redo() {

        if(momentoList.indexOf(momento) == momentoList.size()-1){
            return;
        }

        int index = momentoList.indexOf(momento) + 1;

        momento = momentoList.get(index);

        System.out.println(momentoList.indexOf(momento));

        ordersList = momento.getOrdersSaved();
        logs.restore(momento);

    }


}
