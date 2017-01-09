package com.he.ontology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THscodeEntry entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_entry", catalog = "hscodetest")
public class THscodeEntry implements java.io.Serializable {

	// Fields

	private Integer id;
	private String entry;
	private Integer num;
	private double weight;
	private String hscode;

	// Constructors

	/** default constructor */
	public THscodeEntry() {
	}

	/** full constructor */
	public THscodeEntry(String entry, Integer num, double weight, String hscode) {
		this.entry = entry;
		this.num = num;
		this.weight = weight;
		this.hscode = hscode;
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

	@Column(name = "entry", length = 200)
	public String getEntry() {
		return this.entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "weight", precision = 16, scale = 6)
	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Column(name = "hscode", length = 50)
	public String getHscode() {
		return this.hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

}