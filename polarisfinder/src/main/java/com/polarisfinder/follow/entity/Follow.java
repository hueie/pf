package com.polarisfinder.follow.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import com.polarisfinder.user.entity.User;

@Entity
@Table(name="follow")
public class Follow implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="user_id")
	private int user_id;
	
	@Column(name="following_user_id")
	private int following_user_id;
	
	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reg_dt;
	
	@Transient
    private User user;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public final int getId() {
		return id;
	}

	public final void setId(int id) {
		this.id = id;
	}

	public final int getUser_id() {
		return user_id;
	}

	public final void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public final int getFollowing_user_id() {
		return following_user_id;
	}

	public final void setFollowing_user_id(int following_user_id) {
		this.following_user_id = following_user_id;
	}

	public final Date getReg_dt() {
		return reg_dt;
	}

	public final void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}




}
