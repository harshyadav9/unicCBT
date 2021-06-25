package com.exam.cbt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "ImageUploadStatus")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ImageUploadStatus {
	
	@Id
	@Column(name= "Id") 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
    @Column(name= "ImageName") 
    private String imageName;


	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ImageUploadStatus [id=" + id + ", imageName=" + imageName + "]";
	}

}