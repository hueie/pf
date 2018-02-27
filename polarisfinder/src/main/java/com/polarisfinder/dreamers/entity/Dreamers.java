package com.polarisfinder.dreamers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dreamers")
public class Dreamers implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="content")
    private String content;
	
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


}
