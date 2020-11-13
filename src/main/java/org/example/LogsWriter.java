package org.example;

import java.io.IOException;
import java.util.List;

public interface LogsWriter {
    public void write(List<String> logs);
}
