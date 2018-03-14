package com.polarisfinder.chitchatpub.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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
	private String placelatitude;
	@Column	(name="placelongitude")
	private String placelongitude;
	
	

	@Column(name="placecomment")
	private String placecomment;
	
	
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


	public final String getPlacelatitude() {
		return placelatitude;
	}


	public final void setPlacelatitude(String placelatitude) {
		this.placelatitude = placelatitude;
	}


	public final String getPlacelongitude() {
		return placelongitude;
	}


	public final void setPlacelongitude(String placelongitude) {
		this.placelongitude = placelongitude;
	}


}
