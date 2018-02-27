package com.polarisfinder.cubemap.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bookarng")
public class Bookarng implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name="arng_id")
    private int arng_id;  

	@Column(name="box_id")
    private int box_id;  
	@Column(name="stack_id")
    private int stack_id;  
	@Column(name="booksf_id")
    private int booksf_id;  
	@Column(name="booksf_f_no")
    private int booksf_f_no;  
	@Column(name="booksf_r_no")
    private int booksf_r_no;  
	@Column(name="booksf_r_sno")
    private int booksf_r_sno;  
	

	@Column(name="arng_cd")
    private int arng_cd;  
	@Column(name="box_arng_dt")
    private int box_arng_dt;
	public int getArng_id() {
		return arng_id;
	}
	public void setArng_id(int arng_id) {
		this.arng_id = arng_id;
	}
	public int getBox_id() {
		return box_id;
	}
	public void setBox_id(int box_id) {
		this.box_id = box_id;
	}
	public int getStack_id() {
		return stack_id;
	}
	public void setStack_id(int stack_id) {
		this.stack_id = stack_id;
	}
	public int getBooksf_id() {
		return booksf_id;
	}
	public void setBooksf_id(int booksf_id) {
		this.booksf_id = booksf_id;
	}
	public int getBooksf_f_no() {
		return booksf_f_no;
	}
	public void setBooksf_f_no(int booksf_f_no) {
		this.booksf_f_no = booksf_f_no;
	}
	public int getBooksf_r_no() {
		return booksf_r_no;
	}
	public void setBooksf_r_no(int booksf_r_no) {
		this.booksf_r_no = booksf_r_no;
	}
	public int getBooksf_r_sno() {
		return booksf_r_sno;
	}
	public void setBooksf_r_sno(int booksf_r_sno) {
		this.booksf_r_sno = booksf_r_sno;
	}
	public int getArng_cd() {
		return arng_cd;
	}
	public void setArng_cd(int arng_cd) {
		this.arng_cd = arng_cd;
	}
	public int getBox_arng_dt() {
		return box_arng_dt;
	}
	public void setBox_arng_dt(int box_arng_dt) {
		this.box_arng_dt = box_arng_dt;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}  
	
	
}
