package cmd;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class man {
    public String help_me() throws IOException {
        String manpage;
        try (BufferedReader br = new BufferedReader(new FileReader("conf/man.page"))) {
            StringBuilder sb = new StringBuilder();
            String line = br.readLine();

            while (line != null) {
                sb.append(line).append("\n");
                sb.append(System.lineSeparator());
                line = br.readLine();
            }
            manpage = sb.toString();
        }
        return manpage;
    }
}
