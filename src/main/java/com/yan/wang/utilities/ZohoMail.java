package com.yan.wang.utilities;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class ZohoMail {

    public String username = "";
    public String password = "";

    public void sendMail (String btcLastValue) {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.host", "smtp.zoho.com");
        properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        properties.setProperty("mail.smtp.socketFactory.fallback", "false");
        properties.setProperty("mail.smtp.port", "465");
        properties.setProperty("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.debug", "true");
        properties.put("mail.store.protocol", "pop3");
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.debug.auth", "true");
        properties.setProperty("mail.pop3.socketFactory.fallback", "false");


        BitstampUtils bitstampUtils = new BitstampUtils();
        List<String> stringList = bitstampUtils.getAuthKeys();

        for (String s : stringList) {
            if (s.startsWith("zohomailusername=")) {
                username = s.substring(17, s.length());
            } else if (s.startsWith("zohomailpassword=")) {
                password = s.substring(17, s.length());
            } else {
                System.out.println("Not interested in");
            }
        }

        Session session = Session.getDefaultInstance(properties, new javax.mail.Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(MimeMessage.RecipientType.TO, InternetAddress.parse("yan.wang.ch@gmail.com"));
            message.setSubject("BTC last value");
            message.setText(btcLastValue + " USD");
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
