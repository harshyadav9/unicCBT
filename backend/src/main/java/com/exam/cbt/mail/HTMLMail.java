package com.exam.cbt.mail;

import java.io.File;
import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class HTMLMail {

	@Autowired
	private JavaMailSender javaMailSender;

	public void sendEmailWithAttachment() throws MessagingException, IOException {

		MimeMessage msg = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(msg, true);

		helper.setTo("email address to whom you send the mail");

		helper.setSubject("Testing from Spring Boot");

		helper.setText("<h1>Check attachment for image!</h1>", true);

		// hardcoded a file path
		FileSystemResource file = new FileSystemResource(new File("C:/Users\\1302143\\Desktop\\ssl4.png"));

		helper.addAttachment("ssl4.png", file);

		javaMailSender.send(msg);

	}

}
