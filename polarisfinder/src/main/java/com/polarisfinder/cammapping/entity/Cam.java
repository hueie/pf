package com.polarisfinder.cammapping.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="cam")
public class Cam implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="cam_id")
	private int cam_id;
	@Column(name="fileupload_id")
	private int fileupload_id;
	@Column(name="cam_nm")
	private String cam_nm;
	@Column(name="cam_imgsrc")
	private String cam_imgsrc;
	public int getCam_id() {
		return cam_id;
	}
	public void setCam_id(int cam_id) {
		this.cam_id = cam_id;
	}
	public int getFileupload_id() {
		return fileupload_id;
	}
	public void setFileupload_id(int fileupload_id) {
		this.fileupload_id = fileupload_id;
	}
	public String getCam_nm() {
		return cam_nm;
	}
	public void setCam_nm(String cam_nm) {
		this.cam_nm = cam_nm;
	}
	public String getCam_imgsrc() {
		return cam_imgsrc;
	}
	public void setCam_imgsrc(String cam_imgsrc) {
		this.cam_imgsrc = cam_imgsrc;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
