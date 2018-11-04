import cmd.Bash;
import cmd.man;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Robot extends Thread {

    void robot() throws Exception {
        Imap imap = new Imap();
        Smtp smtp = new Smtp();
        Bash bash = new Bash();
        man man = new man();
        String systemEncoding = "UTF-8";
        Settings settings = new Settings();
        List<String> access = new ArrayList<>();
        String[] addressParts;
        //addressParts = new String();
        //List<String> fromToList = new ArrayList<>();


        String nMap;
        try (BufferedReader bufReader = new BufferedReader(new FileReader(new File("conf/access.cfg")), 1024)) {
            while ((nMap = bufReader.readLine()) != null)
                access.add(nMap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            imap.imap();
            if (imap.from != null) {
                addressParts = imap.from.split("<");
                String temp = (addressParts[1]);
                String fromTO = temp.substring(0, temp.indexOf(">"));
                //fromToList.add(fromTO);
                boolean ifEqual = false;
                for (String aaccess : access) {
                    ifEqual = false;
                    //for (String afromToList : fromToList) {
                    if (aaccess.equals(fromTO)) {
                        ifEqual = true;
                        String message = imap.body;
                        String msg = message.substring(0, message.indexOf("\r\n"));
                        String[] rawCMD = msg.split(" ");
                        String cmd = (rawCMD[0]);
                        String volueCMD = (rawCMD[1]);
                        System.out.println("Команда: " + cmd + volueCMD);
                        System.out.println("Сообщение от: " + fromTO);
                        switch (cmd.toLowerCase()) {

                            case ("help"):
                                switch (volueCMD) {
                                    case ("me"):
                                        smtp.smtp(man.help_me(), fromTO.toLowerCase(), "Re: " + cmd, settings.recepientBody);
                                        break;
                                }
                                break;

                            default:
                                smtp.smtp("Команда " + cmd.toLowerCase() + " не найдена. Для получения сиправки отправьте Help me.", fromTO.toLowerCase(), "Re: " + cmd, settings.recepientBody);
                                break;
                        }
                    }
                }
                if (!ifEqual) {
                    System.out.println(ifEqual);
                    //smtp.smtp(settings.accessDeny+ "Адрес: "+fromTO+" не найден!", imap.from, settings.reBody + " " + imap.subject, settings.recepientBody);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
