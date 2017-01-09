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
 * THscodeWord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_word", catalog = "hscodetest")
public class THscodeWord implements java.io.Serializable {

	// Fields

	private Integer id;
	private String word;
	private Integer num;
	private String hscode;
	private double weight;
	private Integer docsnum;
	private Set<THscodeWordFrequency> THscodeWordFrequencies = new HashSet<THscodeWordFrequency>(
			0);

	// Constructors

	/** default constructor */
	public THscodeWord() {
	}

	/** full constructor */
	public THscodeWord(String word, Integer num, String hscode, double weight,
			Integer docsnum, Set<THscodeWordFrequency> THscodeWordFrequencies) {
		this.word = word;
		this.num = num;
		this.hscode = hscode;
		this.weight = weight;
		this.docsnum = docsnum;
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

	@Column(name = "word", length = 100)
	public String getWord() {
		return this.word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "hscode", length = 50)
	public String getHscode() {
		return this.hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

	@Column(name = "weight", precision = 16, scale = 6)
	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Column(name = "docsnum")
	public Integer getDocsnum() {
		return this.docsnum;
	}

	public void setDocsnum(Integer docsnum) {
		this.docsnum = docsnum;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "THscodeWord")
	public Set<THscodeWordFrequency> getTHscodeWordFrequencies() {
		return this.THscodeWordFrequencies;
	}

	public void setTHscodeWordFrequencies(
			Set<THscodeWordFrequency> THscodeWordFrequencies) {
		this.THscodeWordFrequencies = THscodeWordFrequencies;
	}

}