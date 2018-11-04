package cmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadJsonFile {
    public String readfile () throws IOException {
        String json;
        try (BufferedReader br = new BufferedReader(new FileReader("conf/config.json"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            json = sb.toString();

        }return json;



    }
}
