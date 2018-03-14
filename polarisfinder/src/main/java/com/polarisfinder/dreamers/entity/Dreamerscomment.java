package com.polarisfinder.dreamers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="dreamerscomment")
public class Dreamerscomment implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="dreamers_id")
    private int dreamers_id;  

	@Column(name="dreamers_comment")
    private String dreamers_comment;
	
	@Column(name="user_id")
	private int user_id;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getDreamers_id() {
		return dreamers_id;
	}


	public void setDreamers_id(int dreamers_id) {
		this.dreamers_id = dreamers_id;
	}


	public String getDreamers_comment() {
		return dreamers_comment;
	}


	public void setDreamers_comment(String dreamers_comment) {
		this.dreamers_comment = dreamers_comment;
	}


	public final int getUser_id() {
		return user_id;
	}


	public final void setUser_id(int user_id) {
		this.user_id = user_id;
	}




}
