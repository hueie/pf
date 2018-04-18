package com.polarisfinder.animals.entity;

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
@Table(name="animals")
public class Animals implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="animals_id")
    private int animals_id;  

	@Column(name="animals_nm")
    private String animals_nm;

	public final int getAnimals_id() {
		return animals_id;
	}

	public final void setAnimals_id(int animals_id) {
		this.animals_id = animals_id;
	}

	public final String getAnimals_nm() {
		return animals_nm;
	}

	public final void setAnimals_nm(String animals_nm) {
		this.animals_nm = animals_nm;
	}

	public static final long getSerialversionuid() {
		return serialVersionUID;
	}
	




}
