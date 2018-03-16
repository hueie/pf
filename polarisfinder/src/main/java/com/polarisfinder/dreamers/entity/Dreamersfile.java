package com.polarisfinder.dreamers.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

@Entity
@Table(name="dreamersfile")
public class Dreamersfile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="dreamersfile_id")
    private int dreamersfile_id;  

	@Column(name="dreamers_id")
    private int dreamers_id;  
	
	@Column(name="dreamersfile_orign_nm")
    private String dreamersfile_orign_nm;

	@Column(name="dreamersfile_orign_path")
    private String dreamersfile_orign_path;
	
	@Column(name="dreamersfile_serv_nm")
    private String dreamersfile_serv_nm;

	@Column(name="dreamersfile_serv_path")
    private String dreamersfile_serv_path;
	
	@Column(name="user_id")
	private int user_id;

	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reg_dt;

	public final int getDreamersfile_id() {
		return dreamersfile_id;
	}

	public final void setDreamersfile_id(int dreamersfile_id) {
		this.dreamersfile_id = dreamersfile_id;
	}

	public final String getDreamersfile_orign_nm() {
		return dreamersfile_orign_nm;
	}

	public final void setDreamersfile_orign_nm(String dreamersfile_orign_nm) {
		this.dreamersfile_orign_nm = dreamersfile_orign_nm;
	}

	public final String getDreamersfile_serv_nm() {
		return dreamersfile_serv_nm;
	}

	public final void setDreamersfile_serv_nm(String dreamersfile_serv_nm) {
		this.dreamersfile_serv_nm = dreamersfile_serv_nm;
	}

	public final String getDreamersfile_serv_path() {
		return dreamersfile_serv_path;
	}

	public final void setDreamersfile_serv_path(String dreamersfile_serv_path) {
		this.dreamersfile_serv_path = dreamersfile_serv_path;
	}

	public final int getUser_id() {
		return user_id;
	}

	public final void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public final Date getReg_dt() {
		return reg_dt;
	}

	public final void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}

	public final int getDreamers_id() {
		return dreamers_id;
	}

	public final void setDreamers_id(int dreamers_id) {
		this.dreamers_id = dreamers_id;
	}

	public final String getDreamersfile_orign_path() {
		return dreamersfile_orign_path;
	}

	public final void setDreamersfile_orign_path(String dreamersfile_orign_path) {
		this.dreamersfile_orign_path = dreamersfile_orign_path;
	}

}
