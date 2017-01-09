package com.alibaba.model;

public class TrainingHscodeBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String gmt_create;
	private String gmt_modified;
	private String pid;
	private String pname;
	private String hscode;
	private String customs;
	private Integer status;
	public TrainingHscodeBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TrainingHscodeBean(Integer id, String gmt_create,
			String gmt_modified, String pid, String pname, String hscode,
			String customs, Integer status) {
		super();
		this.id = id;
		this.gmt_create = gmt_create;
		this.gmt_modified = gmt_modified;
		this.pid = pid;
		this.pname = pname;
		this.hscode = hscode;
		this.customs = customs;
		this.status = status;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getGmt_create() {
		return gmt_create;
	}
	public void setGmt_create(String gmt_create) {
		this.gmt_create = gmt_create;
	}
	public String getGmt_modified() {
		return gmt_modified;
	}
	public void setGmt_modified(String gmt_modified) {
		this.gmt_modified = gmt_modified;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getHscode() {
		return hscode;
	}
	public void setHscode(String hscode) {
		this.hscode = hscode;
	}
	public String getCustoms() {
		return customs;
	}
	public void setCustoms(String customs) {
		this.customs = customs;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}