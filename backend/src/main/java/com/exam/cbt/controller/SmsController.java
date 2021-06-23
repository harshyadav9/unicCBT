package com.exam.cbt.controller;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.cbt.sms.SMS;

@RestController
@RequestMapping("/cbt/sms")
@CrossOrigin(origins = "*")
public class SmsController {

	@Autowired
	SMS sms;

	@PostMapping(value = "/send")
	public String sendSms() {
		try {
			sms.sendSms();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		return "Sms sent successfully";
	}

}
