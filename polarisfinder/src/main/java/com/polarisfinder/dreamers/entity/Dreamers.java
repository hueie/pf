package com.polarisfinder.dreamers.entity;

import java.io.Serializable;
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
	
	@Column(name="like_cnt")
    private int like_cnt;  

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	//@OneToMany(targetEntity = Dreamerscomment.class, cascade = CascadeType.ALL)
    //@JoinColumn(name = "dreamers_id",referencedColumnName="id")
	@Transient
    private List dreamerscomment_list;
	
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


}
