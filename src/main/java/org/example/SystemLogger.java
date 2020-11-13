package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SystemLogger {
    private List<String> logsList;
    private LogsWriter writer; //interface implementation : Le système n'a pas à savoir si la sortie est en console ou en fichier


    public SystemLogger() {
        logsList = new ArrayList<>();
        writer = new FileLogsWriter();
    }

    public void addLog(String message) {
        logsList.add(message);
        writer.write(logsList);
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
