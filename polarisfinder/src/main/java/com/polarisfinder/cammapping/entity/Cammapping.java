package com.polarisfinder.cammapping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;

@Entity
@Table(name="cammapping")
public class Cammapping implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cammapping_id")
	private int cammapping_id;
	@Column(name="fileupload_id")
	private int fileupload_id;
	@Column(name="line_id")
	private int line_id;
	@Column(name="start_x")
	private int start_x;
	@Column(name="end_x")
	private int end_x;
	@Column(name="start_y")
	private int start_y;
	@Column(name="end_y")
	private int end_y;
	@Column(name="booksf_id", nullable = false)
	@ColumnDefault("'0'")
	private Integer booksf_id;
	@Column(name="cammapping_user_id")
	private Integer cammapping_user_id;
	
	
	public int getCammapping_id() {
		return cammapping_id;
	}
	public void setCammapping_id(int cammapping_id) {
		this.cammapping_id = cammapping_id;
	}
	public int getLine_id() {
		return line_id;
	}
	public void setLine_id(int line_id) {
		this.line_id = line_id;
	}
	public int getStart_x() {
		return start_x;
	}
	public void setStart_x(int start_x) {
		this.start_x = start_x;
	}
	public int getEnd_x() {
		return end_x;
	}
	public void setEnd_x(int end_x) {
		this.end_x = end_x;
	}
	public int getStart_y() {
		return start_y;
	}
	public void setStart_y(int start_y) {
		this.start_y = start_y;
	}
	public int getEnd_y() {
		return end_y;
	}
	public void setEnd_y(int end_y) {
		this.end_y = end_y;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getFileupload_id() {
		return fileupload_id;
	}
	public void setFileupload_id(int fileupload_id) {
		this.fileupload_id = fileupload_id;
	}
	public Integer getCammapping_user_id() {
		return cammapping_user_id;
	}
	public void setCammapping_user_id(Integer cammapping_user_id) {
		this.cammapping_user_id = cammapping_user_id;
	}
	public void setBooksf_id(Integer booksf_id) {
		this.booksf_id = booksf_id;
	}
	public Integer getBooksf_id() {
		return booksf_id;
	}
}
