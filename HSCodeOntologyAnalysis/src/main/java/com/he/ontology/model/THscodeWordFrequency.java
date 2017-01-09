package com.he.ontology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * THscodeWordFrequency entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_word_frequency", catalog = "hscodetest")
public class THscodeWordFrequency implements java.io.Serializable {

	// Fields

	private Integer id;
	private THscodeData THscodeData;
	private THscodeWord THscodeWord;
	private String wordname;
	private Integer num;
	private String hscode;

	// Constructors

	/** default constructor */
	public THscodeWordFrequency() {
	}

	/** full constructor */
	public THscodeWordFrequency(THscodeData THscodeData,
			THscodeWord THscodeWord, String wordname, Integer num, String hscode) {
		this.THscodeData = THscodeData;
		this.THscodeWord = THscodeWord;
		this.wordname = wordname;
		this.num = num;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "nameid")
	public THscodeData getTHscodeData() {
		return this.THscodeData;
	}

	public void setTHscodeData(THscodeData THscodeData) {
		this.THscodeData = THscodeData;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "wordid")
	public THscodeWord getTHscodeWord() {
		return this.THscodeWord;
	}

	public void setTHscodeWord(THscodeWord THscodeWord) {
		this.THscodeWord = THscodeWord;
	}

	@Column(name = "wordname")
	public String getWordname() {
		return this.wordname;
	}

	public void setWordname(String wordname) {
		this.wordname = wordname;
	}

	@Column(name = "num")
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "hscode")
	public String getHscode() {
		return this.hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

}