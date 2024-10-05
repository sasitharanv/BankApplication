package com.example.BankApplication.Service;

import com.example.BankApplication.Model.Email;

public interface EmailService {

    String sendsimplemail(Email email);

    String sendMailwithAttachments(Email email);
}
