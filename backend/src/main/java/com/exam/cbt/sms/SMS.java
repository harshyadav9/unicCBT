package com.exam.cbt.sms;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SMS {
	
	RestTemplate restTemplate = new RestTemplate();
	
	public void sendSms() throws URISyntaxException {
		
		String msg = "Welcome ! Your ABCON Registration No:ABCON-" + " and password:";
		
		Input in = new Input();
		
		in.setUname("20141082");
		in.setPass("unic12345");
		in.setSend("DABCON");
		in.setDest("9100883258");
		in.setMsg(msg);
		String url = "http://164.52.195.161/API/SendMsg.aspx";
		
	    ResponseEntity<String> response = this.restTemplate.getForEntity(url, String.class,in);
		
	    System.out.println(response.getStatusCode());
		
	}
	

	

}
