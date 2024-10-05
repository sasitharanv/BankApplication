package com.example.BankApplication.Service.impl;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.BankApplication.Model.Email;
import com.example.BankApplication.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceimpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;


    @Value("${spring.mail.username}") private  String sender;
    @Override


    public String sendsimplemail(Email email) {
        try{

        SimpleMailMessage mailMessage=new SimpleMailMessage();
        mailMessage.setFrom(sender);
        mailMessage.setTo(email.getRecipients());
        mailMessage.setText(email.getMessagebody());
        mailMessage.setSubject(email.getSubject());

        javaMailSender.send(mailMessage);
        return "Mail Sent SuccessFully!...";
        }
        catch (Exception e){
            e.printStackTrace(); // Log the exception for debugging
            return  "Error While Sending Mail";
        }
    }

    @Override
    public String sendMailwithAttachments(Email email) {

        return null;
    }
}
