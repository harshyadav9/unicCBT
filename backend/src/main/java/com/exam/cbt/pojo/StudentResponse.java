package com.exam.cbt.pojo;

import java.util.List;

public class StudentResponse {
	
	public Integer getRegistrationNo() {
		return registrationNo;
	}

	public void setRegistrationNo(Integer registrationNo) {
		this.registrationNo = registrationNo;
	}

	public List<com.exam.cbt.entity.StudentResponse> getResp() {
		return resp;
	}

	public void setResp(List<com.exam.cbt.entity.StudentResponse> resp) {
		this.resp = resp;
	}

	private Integer registrationNo;
	
	private List<com.exam.cbt.entity.StudentResponse> resp;

}
