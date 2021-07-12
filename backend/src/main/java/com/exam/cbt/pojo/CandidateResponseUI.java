package com.exam.cbt.pojo;

import java.util.List;

public class CandidateResponseUI {
	
	//private Integer registrationNo;
	
	private List<com.exam.cbt.entity.CandidateResponse> resp;
	
//	public Integer getRegistrationNo() {
//		return registrationNo;
//	}
//
//	public void setRegistrationNo(Integer registrationNo) {
//		this.registrationNo = registrationNo;
//	}

	public List<com.exam.cbt.entity.CandidateResponse> getResp() {
		return resp;
	}

	public void setResp(List<com.exam.cbt.entity.CandidateResponse> resp) {
		this.resp = resp;
	}

	@Override
	public String toString() {
		return "CandidateResponseUI [resp=" + resp + "]";
	}


}
