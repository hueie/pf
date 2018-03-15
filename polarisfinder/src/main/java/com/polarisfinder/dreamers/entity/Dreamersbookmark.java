package com.polarisfinder.dreamers.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

@Entity
@Table(name="dreamersbookmark")
public class Dreamersbookmark implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="dreamers_id")
    private int dreamers_id;
	

	@Column(name="user_id")
    private int user_id;

	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reg_dt;
	
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


	public int getUser_id() {
		return user_id;
	}


	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}


	public final Date getReg_dt() {
		return reg_dt;
	}


	public final void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}




}
