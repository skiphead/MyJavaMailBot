
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;

public class Imap {
    protected int msgCount;
    String from;
    String body;
    String subject;
    String recipient;
    String replyTO;

    protected void imap() throws Exception {
        Settings settings = new Settings();
        final String user = settings.userName;
        final String pass = settings.passWord;
        final String host = settings.mailStoreHost;
        final String boxmode = settings.BoxMode;
        Properties props = new Properties();
        props.put("mail.debug", settings.debugMode);
        props.put("mail.store.protocol", settings.mailStoreProtocol);
        props.put("mail.imap.starttls.enable", settings.mailImapStarttlsEnable);
        props.put("mail.imap.ssl.trust", settings.mailImapSslTrust);
        props.put("mail.imap.plain.disable", settings.mailImapPlainDisable);
        Session session = Session.getInstance(props);
        try {
            Store store = session.getStore();
            store.connect(host, user, pass);
            Folder inbox = store.getFolder("INBOX");
            if (boxmode.toUpperCase().equals("READ_ONLY")) {
                inbox.open(Folder.READ_ONLY);
            }
            if (boxmode.toUpperCase().equals("READ_WRITE")) {
                inbox.open(Folder.READ_WRITE);
            }
            msgCount = inbox.getMessageCount();
            if (msgCount != 0) {
                Message m = inbox.getMessage(inbox.getMessageCount());
                from = InternetAddress.toString(m.getFrom());
                replyTO = InternetAddress.toString(m.getReplyTo());
                subject = m.getSubject();
                recipient = InternetAddress.toString(m.getRecipients(Message.RecipientType.TO));
                Object content = m.getContent();
                if (content instanceof String) {
                    body = (String) content;
                } else if (content instanceof Multipart) {
                    Multipart mp = (Multipart) content;
                    BodyPart bp = mp.getBodyPart(0);
                    body = (String) bp.getContent();
                }
                m.setFlag(Flags.Flag.DELETED, true);
                inbox.close(true);
                store.close();
            } else {
                Thread.sleep(60000);
            }
        } catch (MessagingException e) {
            //Какая то прилетела бага :)
            System.out.println(e);
        }
    }
}
