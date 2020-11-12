package org.example;

import java.util.ArrayList;
import java.util.List;

public class SystemLogger {
    private final List<String> logsList;

    public SystemLogger() {
        logsList = new ArrayList<>();
    }

    public void addLog(String message) {
        logsList.add(message);
        System.out.println(message);
    }

    public List<String> getLogsList() {
        return logsList;
    }
}
