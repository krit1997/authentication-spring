package com.example.authentication.spring.boots.services.SendNotification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.authentication.spring.boots.config.UiUrlPropertyConfig;
import com.example.authentication.utils.JwtTokenUtil;

@Service
public class GmailSenderService {

    @Autowired
    private JavaMailSender mailSender;
    private JwtTokenUtil jwtTokenUtil;
    private UiUrlPropertyConfig uiUrlPropertyConfig;

    public Boolean sendResetPasswordEmail(String to) {
        try {
            // if(to == null){
            // throw ""
            // }
            String resetLink = uiUrlPropertyConfig + "/reset-password?token=" + jwtTokenUtil.generateResetToken(to);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Reset your password");
            message.setText("Click this link to reset your password: " + resetLink);

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            throw e;
        }

    }
}
