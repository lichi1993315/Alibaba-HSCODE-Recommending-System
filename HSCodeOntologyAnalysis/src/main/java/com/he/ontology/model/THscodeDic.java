package com.he.ontology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THscodeDic entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_dic", catalog = "hscodetest")
public class THscodeDic implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hscode;
	private String define;
	private String defineSeg;

	// Constructors

	/** default constructor */
	public THscodeDic() {
	}

	/** full constructor */
	public THscodeDic(String hscode, String define, String defineSeg) {
		this.hscode = hscode;
		this.define = define;
		this.defineSeg = defineSeg;
	}

	// Property accessors
	@Id
	@GeneratedValue
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "hscode")
	public String getHscode() {
		return this.hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	@Column(name = "define")
	public String getDefine() {
		return this.define;
	}

	public void setDefine(String define) {
		this.define = define;
	}

	@Column(name = "define_seg")
	public String getDefineSeg() {
		return this.defineSeg;
	}

	public void setDefineSeg(String defineSeg) {
		this.defineSeg = defineSeg;
	}

}