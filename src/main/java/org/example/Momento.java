package org.example;

import java.util.List;

public class Momento {
    private List<String> logs;

    public Momento(List<String> logs){
        this.logs = logs;
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
