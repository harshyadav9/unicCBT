package com.exam.cbt.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Config")
public class Config {
	
	@EmbeddedId
	private ConfigId id;
	
	@Column(name = "DateOfExam")
	private Date dateOfExam;
	
	@Column(name = "DurationHr")
	private int durationHr;
	
	@Column(name = "DurationMin")
	private int durationMin;
	
	@Column(name = "NoOfSet")
	private int noOfSet;
	
	@Column(name = "SetStart")
	private int setStart;
	
	public int getSetStart() {
		return setStart;
	}

	public void setSetStart(int setStart) {
		this.setStart = setStart;
	}

	public int getNoOfSet() {
		return noOfSet;
	}

	public void setNoOfSet(int noOfSet) {
		this.noOfSet = noOfSet;
	}

	public ConfigId getId() {
		return id;
	}

	public void setId(ConfigId id) {
		this.id = id;
	}

	public Date getDateOfExam() {
		return dateOfExam;
	}

	public void setDateOfExam(Date dateOfExam) {
		this.dateOfExam = dateOfExam;
	}

	public int getDurationHr() {
		return durationHr;
	}

	public void setDurationHr(int durationHr) {
		this.durationHr = durationHr;
	}

	public int getDurationMin() {
		return durationMin;
	}

	public void setDurationMin(int durationMin) {
		this.durationMin = durationMin;
	}

	@Override
	public String toString() {
		return "Config [id=" + id + ", dateOfExam=" + dateOfExam + ", durationHr=" + durationHr + ", durationMin="
				+ durationMin + ", noOfSet=" + noOfSet + ", setStart=" + setStart + "]";
	}

}
