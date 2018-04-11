package com.polarisfinder.chitchatpub.entity;

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

import com.polarisfinder.user.entity.User;

@Entity
@Table(name="chitchatpub")
public class Chitchatpub implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  
	
	@Column(name="placename")
    private String placename;
	@Column	(name="placelatitude")
	private Float placelatitude;
	@Column	(name="placelongitude")
	private Float placelongitude;
	
	@Column(name="placecomment")
	private String placecomment;
	
	@Column(name="star_tot_cnt")
    private int star_tot_cnt;  

	@Column(name="user_id")
	private int user_id;
	
	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
	@Temporal(TemporalType.TIMESTAMP)
    private Date reg_dt;
	
	@Transient
	private int star_cnt;

	@Transient
    private User user;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getPlacename() {
		return placename;
	}


	public void setPlacename(String placename) {
		this.placename = placename;
	}



	public String getPlacecomment() {
		return placecomment;
	}


	public void setPlacecomment(String placecomment) {
		this.placecomment = placecomment;
	}


	public final Float getPlacelatitude() {
		return placelatitude;
	}


	public final void setPlacelatitude(Float placelatitude) {
		this.placelatitude = placelatitude;
	}


	public final Float getPlacelongitude() {
		return placelongitude;
	}


	public final void setPlacelongitude(Float placelongitude) {
		this.placelongitude = placelongitude;
	}


	public final int getStar_tot_cnt() {
		return star_tot_cnt;
	}


	public final void setStar_tot_cnt(int star_tot_cnt) {
		this.star_tot_cnt = star_tot_cnt;
	}


	public final int getStar_cnt() {
		return star_cnt;
	}


	public final void setStar_cnt(int star_cnt) {
		this.star_cnt = star_cnt;
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


	public final User getUser() {
		return user;
	}


	public final void setUser(User user) {
		this.user = user;
	}


}
