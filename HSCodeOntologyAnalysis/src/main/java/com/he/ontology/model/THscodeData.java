package com.he.ontology.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * THscodeData entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_data", catalog = "hscodetest")
public class THscodeData implements java.io.Serializable {

	// Fields

	private Integer id;
	private String hscode;
	private String name;
	private String nameSeg;
	private String describle;
	private String describleSeg;
	private Integer count;
	private String advice;
	private Integer result;
	private Set<THscodeWordFrequency> THscodeWordFrequencies = new HashSet<THscodeWordFrequency>(
			0);

	// Constructors

	/** default constructor */
	public THscodeData() {
	}

	/** full constructor */
	public THscodeData(String hscode, String name, String nameSeg,
			String describle, String describleSeg, Integer count,
			String advice, Integer result,
			Set<THscodeWordFrequency> THscodeWordFrequencies) {
		this.hscode = hscode;
		this.name = name;
		this.nameSeg = nameSeg;
		this.describle = describle;
		this.describleSeg = describleSeg;
		this.count = count;
		this.advice = advice;
		this.result = result;
		this.THscodeWordFrequencies = THscodeWordFrequencies;
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

	@Column(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "name_seg")
	public String getNameSeg() {
		return this.nameSeg;
	}

	public void setNameSeg(String nameSeg) {
		this.nameSeg = nameSeg;
	}

	@Column(name = "describle")
	public String getDescrible() {
		return this.describle;
	}

	public void setDescrible(String describle) {
		this.describle = describle;
	}

	@Column(name = "describle_seg")
	public String getDescribleSeg() {
		return this.describleSeg;
	}

	public void setDescribleSeg(String describleSeg) {
		this.describleSeg = describleSeg;
	}

	@Column(name = "count")
	public Integer getCount() {
		return this.count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "advice")
	public String getAdvice() {
		return this.advice;
	}

	public void setAdvice(String advice) {
		this.advice = advice;
	}

	@Column(name = "result")
	public Integer getResult() {
		return this.result;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "THscodeData")
	public Set<THscodeWordFrequency> getTHscodeWordFrequencies() {
		return this.THscodeWordFrequencies;
	}

	public void setTHscodeWordFrequencies(
			Set<THscodeWordFrequency> THscodeWordFrequencies) {
		this.THscodeWordFrequencies = THscodeWordFrequencies;
	}

}