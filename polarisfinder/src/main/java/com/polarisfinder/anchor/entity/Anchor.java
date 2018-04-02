package com.polarisfinder.anchor.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.polarisfinder.user.entity.User;

@Entity
@Table(name="anchor")
public class Anchor implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="type")
	private int type;
	
	@Column(name="related_id")
	private int related_id;

	@Column(name="url")
    private String url;
	

	@Column(name="user_id")
	private int user_id;
	
	
	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
	@Temporal(TemporalType.TIMESTAMP)
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


	public final Date getReg_dt() {
		return reg_dt;
	}

	public final void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public final int getType() {
		return type;
	}

	public final void setType(int type) {
		this.type = type;
	}

	public final int getRelated_id() {
		return related_id;
	}

	public final void setRelated_id(int related_id) {
		this.related_id = related_id;
	}

	public final String getUrl() {
		return url;
	}

	public final void setUrl(String url) {
		this.url = url;
	}

	public final int getUser_id() {
		return user_id;
	}

	public final void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public final User getUser() {
		return user;
	}

	public final void setUser(User user) {
		this.user = user;
	}




}
