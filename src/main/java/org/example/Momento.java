package org.example;

import java.util.ArrayList;
import java.util.List;

public class Momento {
    private final List<String> logs;

    public Momento(List<String> logs){
        this.logs = new ArrayList<>();
        this.logs.addAll(logs);
    }

    public List<String> getLogs(){
        return logs;
    }

    @Override
    public String toString() {
        return "Momento{" +
                "logs=" + logs +
                '}';
    }
}
