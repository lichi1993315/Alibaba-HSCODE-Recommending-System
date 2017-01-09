package com.he.ontology.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * THscodeEdge entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_hscode_edge", catalog = "hscodetest")
public class THscodeEdge implements java.io.Serializable {

	// Fields

	private Integer id;
	private String source;
	private String target;
	private double weight;
	private String hscode;

	// Constructors

	/** default constructor */
	public THscodeEdge() {
	}

	/** full constructor */
	public THscodeEdge(String source, String target, double weight,
			String hscode) {
		this.source = source;
		this.target = target;
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

	@Column(name = "source")
	public String getSource() {
		return this.source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	@Column(name = "target")
	public String getTarget() {
		return this.target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	@Column(name = "weight", precision = 22, scale = 0)
	public double getWeight() {
		return this.weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Column(name = "hscode")
	public String getHscode() {
		return this.hscode;
	}

	public void setHscode(String hscode) {
		this.hscode = hscode;
	}

}