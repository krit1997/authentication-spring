package com.example.authentication.spring.boots.services.SendNotification;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class GmailOAuth2Service {
    @Value("${gmail.client-id}")
    private String clientId;

    @Value("${gmail.client-secret}")
    private String clientSecret;

    @Value("${gmail.refresh-token}")
    private String refreshToken;

    @Value("${gmail.email}")
    private String fromEmail;

    public void sendEmail(String to, String subject, String htmlBody) throws Exception {
        String accessToken = getAccessToken();

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.auth.mechanisms", "XOAUTH2");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, accessToken);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(fromEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(subject);
        message.setContent(htmlBody, "text/html");

        Transport.send(message);
    }

    private String getAccessToken() throws Exception {
        Credential credential = new GoogleCredential.Builder()
                .setClientSecrets(clientId, clientSecret)
                .setJsonFactory(com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance())
                .setTransport(com.google.api.client.googleapis.javanet.GoogleNetHttpTransport.newTrustedTransport())
                .build()
                .setRefreshToken(refreshToken);

        credential.refreshToken();
        return credential.getAccessToken();
    }
}
