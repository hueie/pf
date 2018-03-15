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
@Table(name="dreamers")
public class Dreamers implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) //Change Your Column REGULATION About PK, NN(Not Null), and AI(Auto-Increased)
	@Column(name="id")
    private int id;  

	@Column(name="content")
    private String content;
	
	@Column(name="bookmark_cnt")
    private int bookmark_cnt;  
	
	@Column(name="like_cnt")
    private int like_cnt;  
	
	@Column(name="user_id")
	private int user_id;

	@Column (name="reg_dt", columnDefinition="datetime", insertable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date reg_dt;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//@OneToMany(targetEntity = Dreamerscomment.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "dreamers_id",referencedColumnName="id")
	@Transient
    private List dreamerscomment_list;
	
	@Transient
	private int like_checked;
	@Transient
	private int bookmark_checked;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public int getLike_cnt() {
		return like_cnt;
	}


	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}


	public List getDreamerscomment_list() {
		return dreamerscomment_list;
	}


	public void setDreamerscomment_list(List dreamerscomment_list) {
		this.dreamerscomment_list = dreamerscomment_list;
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


	public final int getLike_checked() {
		return like_checked;
	}


	public final void setLike_checked(int like_checked) {
		this.like_checked = like_checked;
	}


	public final int getBookmark_checked() {
		return bookmark_checked;
	}


	public final void setBookmark_checked(int bookmark_checked) {
		this.bookmark_checked = bookmark_checked;
	}


	public final int getBookmark_cnt() {
		return bookmark_cnt;
	}


	public final void setBookmark_cnt(int bookmark_cnt) {
		this.bookmark_cnt = bookmark_cnt;
	}


}
