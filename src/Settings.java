import cmd.ReadJsonFile;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;


public class Settings  {
    private ReadJsonFile readjson = new ReadJsonFile();
    private String jsonF = readjson.readfile();
    private JsonParser parser = new JsonParser();
    //JsonElement jsonElement = parser.parse("{\"smtpHost\":\"mx-1.gosniip.ru\",\"smtpPort\":\"587\",\"userName\":\"robot\",\"passWord\":\"rob2018\"}");
    private JsonElement jsonElement = (JsonElement) parser.parse(String.valueOf(jsonF));



    private JsonObject rootObject = jsonElement.getAsJsonObject();
    private JsonObject settingsOject = rootObject.getAsJsonObject("settings");
    String userName = settingsOject.get("userName").getAsString();
    String passWord = settingsOject.get("passWord").getAsString();
    String BoxMode = settingsOject.get("permitFolder").getAsString();
    String debugMode = settingsOject.get("debugMode").getAsString();

    String mailStoreHost = settingsOject.get("mailStoreHost").getAsString();
    String mailStoreProtocol = settingsOject.get("mailStoreProtocol").getAsString();
    String mailImapStarttlsEnable = settingsOject.get("mailImapStarttlsEnable").getAsString();
    String mailImapSslTrust = settingsOject.get("mailImapSslTrust").getAsString();
    String mailImapPlainDisable = settingsOject.get("mailImapPlainDisable").getAsString();

    String mailSsmtpHost = settingsOject.get("mailSsmtpHost").getAsString();
    String mailSmtpStarttlsEnable = settingsOject.get("mailSmtpStarttlsEnable").getAsString();
    String mailSmtpSslTrust = settingsOject.get("mailSmtpSslTrust").getAsString();
    String mailSmtpPort = settingsOject.get("mailSmtpPort").getAsString();
    String mailSmtpAuth = settingsOject.get("mailSmtpAuth").getAsString();
    String mailSmtpDebug = settingsOject.get("mailSmtpDebug").getAsString();

    private JsonObject sentmsgOject = rootObject.getAsJsonObject("sentmsg");
    protected String messageBody = sentmsgOject.get("message").getAsString();
    protected String accessDeny = sentmsgOject.get("accessDeny").getAsString();
    protected String fromBody = sentmsgOject.get("from").getAsString();
    protected String reBody = sentmsgOject.get("re").getAsString();
    String recepientBody = sentmsgOject.get("recepient").getAsString();


    Settings() throws IOException {
    }
}
