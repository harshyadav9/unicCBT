package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "InstitutionNameMaster")
public class InstituteNameMaster {
	
	@EmbeddedId
	private InstituteNameMasterId id;
	
	@Column(name = "InstName")
	private String instName;

	public InstituteNameMasterId getId() {
		return id;
	}

	public void setId(InstituteNameMasterId id) {
		this.id = id;
	}

	public String getInstName() {
		return instName;
	}

	public void setInstName(String instName) {
		this.instName = instName;
	}

	@Override
	public String toString() {
		return "InstituteNameMaster [id=" + id + ", instName=" + instName + "]";
	}

}
