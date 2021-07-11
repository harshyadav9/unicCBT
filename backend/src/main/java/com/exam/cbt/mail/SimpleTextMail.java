package com.exam.cbt.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class SimpleTextMail {

	@Autowired
    private JavaMailSender javaMailSender;
	
	@Value( "${mail.text}" )
	private String mailText;
	
	public void sendEmail(String emailId, String examName) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(emailId);

        msg.setSubject(examName);
        msg.setText(mailText);

        javaMailSender.send(msg);

    }

}
