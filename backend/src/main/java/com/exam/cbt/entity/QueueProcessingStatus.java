package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "QueueProcessingStatus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class QueueProcessingStatus {
	
	@EmbeddedId
	private QueueProcessingStatusId id;
	
    @Column(name= "ProcessingStatus") 
    private String processingStatus;

	public QueueProcessingStatusId getId() {
		return id;
	}

	public void setId(QueueProcessingStatusId id) {
		this.id = id;
	}

	public String getProcessingStatus() {
		return processingStatus;
	}

	public void setProcessingStatus(String processingStatus) {
		this.processingStatus = processingStatus;
	}

	@Override
	public String toString() {
		return "QueueProcessingStatus [id=" + id + ", processingStatus=" + processingStatus + "]";
	}
	 
}
