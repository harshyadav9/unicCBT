package com.exam.cbt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.mail.SimpleTextMail;

@RestController
@RequestMapping("/cbt/email")
@CrossOrigin(origins = "*")
public class EmailController {

	@Autowired
	SimpleTextMail simpleTextMail;

	@PostMapping(value = "/send")
	public String sendEmail() {
		simpleTextMail.sendEmail("aquachestha@gmail.com","MDMS");

		return "Email sent successfully";
	}

}
