package com.polarisfinder.cubemap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="box")
public class Box implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="box_id")
    private int box_id;  
	
	@Column(name="box_nm")
    private String box_nm;
	@Column(name="box_remk")
	private String box_remk;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getBox_id() {
		return box_id;
	}

	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}

	public String getBox_nm() {
		return box_nm;
	}

	public void setBox_nm(String box_nm) {
		this.box_nm = box_nm;
	}

	public String getBox_remk() {
		return box_remk;
	}

	public void setBox_remk(String box_remk) {
		this.box_remk = box_remk;
	}
	
}
