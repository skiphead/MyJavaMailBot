import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;


class Smtp {
    void smtp(String body, String email_from, String re, String recepient) throws IOException {
        Settings settings = new Settings();
        final String user = settings.userName;
        final String pass = settings.passWord;

        // Сюда необходимо подставить адрес получателя сообщения
        String to = email_from;
        String from = recepient;
        // Сюда необходимо подставить SMTP сервер, используемый для отправки
        //String host = settings.smtpHost;
        // Тут указываем порт SMTP сервера.
        //int port = settings.smtpPort;

        // Создание свойств, получение сессии
        Properties props = new Properties();

        // При использовании статического метода Transport.send()
        // необходимо указать через какой хост будет передано сообщение
        props.put("mail.smtp.host", settings.mailSsmtpHost);
        // Если почтовый сервер использует SSL
        props.put("mail.smtp.starttls.enable", settings.mailSmtpStarttlsEnable);
        props.put("mail.smtp.ssl.trust", settings.mailSmtpSslTrust);
        // Указываем порт SMTP сервера.
        props.put("mail.smtp.port", settings.mailSmtpPort);
        // Большинство SMTP серверов, используют авторизацию.
        props.put("mail.smtp.auth", settings.mailSmtpAuth);
        // Включение debug-режима
        props.put("mail.debug", settings.mailSmtpDebug);
        // Авторизируемся.
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            // Указываем логин пароль, от почты, с которой будем отправлять сообщение.
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message msg = new MimeMessage(session);

            // Установка атрибутов сообщения
            msg.setFrom(new InternetAddress(email_from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(re);
            msg.setSentDate(new Date());

            // тело сообщения
            msg.setText(body);

            // Отправка сообщения
            Transport.send(msg);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
