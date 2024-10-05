package com.example.BankApplication.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Email {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long emailId;
    private String recipients;
    private String messagebody;

    private String subject;

    private String attachment;


}
