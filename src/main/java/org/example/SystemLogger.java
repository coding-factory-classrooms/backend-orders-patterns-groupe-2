package org.example;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SystemLogger {
    private List<String> logsList;


    public SystemLogger() {
        logsList = new ArrayList<>();
    }

    public void addLog(String message) {
        logsList.add(message);
        //System.out.println(message);
    }

    public List<String> getLogsList() {
        return logsList;
    }


    public Momento save(List<Momento> momentoList, List<Order> ordersToSave){
        Momento moment = new Momento(getLogsList(),ordersToSave);
        momentoList.add(moment);
        return moment;
    }
    public void restore(Momento momento){
        logsList = momento.getLogs();
    }

}
