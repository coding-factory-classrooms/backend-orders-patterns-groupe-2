package org.example;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileLogsWriter implements LogsWriter {
    public void write(List<String> logs) {
        BufferedWriter out = null;
        try {
            FileWriter fs = new FileWriter("./doc/logs_file.txt", false); //true tells to append data.
            out = new BufferedWriter(fs);
            for (String log : logs) {
                out.write(log+"\n");
            }
        } catch (IOException e) {
            System.err.println("Les logs n'ont pas pu s'écrire. Error: " + e.getMessage());
        }
        finally {
            try {
                if(out != null) {
                    out.close();
                }
            } catch (IOException e) {
                System.err.println("Erreur à la fermeture du fichier. Error: " + e.getMessage());
            }
        }
    }
}
