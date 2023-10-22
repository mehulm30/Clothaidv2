package com.example.tmkc;

import javafx.event.ActionEvent;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSend {

    public static void sendEmail(String email,String feedback ) {
        // Recipient's email address
        String to = email;

        // Sender's email address and password
        final String from = "clothaid2@gmail.com";
        final String password = "ifqq dtwr fyzb rktq";

        // Setup mail properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Get the Session object
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        });

        try {
            // Create a default MimeMessage object
            Message message = new MimeMessage(session);

            // Set From: header field of the header
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("ClothAid Feedback");

            // Now set the actual message
            message.setText(feedback);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
