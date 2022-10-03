package controller;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailSendingController {

    public static void sendMail(String receiver) throws MessagingException {
        Properties properties=new Properties();

        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");

        String myMail="sgstorespannipitiya@gmail.com";
        String pswd = "sgstores.123";

        Session session = Session.getDefaultInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myMail, pswd);
            }
        });
        Message message = prepareMessage(session, myMail, receiver);
        Transport.send(message);

    }



    public static Message prepareMessage(Session session, String myMail, String receiver) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myMail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
            message.setSubject("order new items");
            message.setText("Content");
            return message;

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
