package com.weiwei.weiqi.jdbc.dbmodel.annoucement;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * DedeCoHtmls entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "dede_co_htmls")
public class Htmls implements java.io.Serializable {

	// Fields

	private Integer aid;
	private Integer nid;
	private Short typeid;
	private String title;
	private String litpic;
	private String url;
	private Integer dtime;
	private Boolean isdown;
	private Boolean isexport;
	private String result;

	// Constructors

	/** default constructor */
	public Htmls() {
	}
	
	public Htmls(Integer aid) {
		this.aid = aid;
	}

	/** minimal constructor */
	public Htmls(Integer nid, Short typeid, String title, String litpic,
			String url, Integer dtime, Boolean isdown, Boolean isexport) {
		this.nid = nid;
		this.typeid = typeid;
		this.title = title;
		this.litpic = litpic;
		this.url = url;
		this.dtime = dtime;
		this.isdown = isdown;
		this.isexport = isexport;
	}

	/** full constructor */
	public Htmls(Integer nid, Short typeid, String title, String litpic,
			String url, Integer dtime, Boolean isdown, Boolean isexport,
			String result) {
		this.nid = nid;
		this.typeid = typeid;
		this.title = title;
		this.litpic = litpic;
		this.url = url;
		this.dtime = dtime;
		this.isdown = isdown;
		this.isexport = isexport;
		this.result = result;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "aid", unique = true, nullable = false)
	public Integer getAid() {
		return this.aid;
	}

	public void setAid(Integer aid) {
		this.aid = aid;
	}

	@Column(name = "nid", nullable = false)
	public Integer getNid() {
		return this.nid;
	}

	public void setNid(Integer nid) {
		this.nid = nid;
	}

	@Column(name = "typeid", nullable = false)
	public Short getTypeid() {
		return this.typeid;
	}

	public void setTypeid(Short typeid) {
		this.typeid = typeid;
	}

	@Column(name = "title", nullable = false, length = 60)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "litpic", nullable = false, length = 100)
	public String getLitpic() {
		return this.litpic;
	}

	public void setLitpic(String litpic) {
		this.litpic = litpic;
	}

	@Column(name = "url", nullable = false, length = 100)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "dtime", nullable = false)
	public Integer getDtime() {
		return this.dtime;
	}

	public void setDtime(Integer dtime) {
		this.dtime = dtime;
	}

	@Column(name = "isdown", nullable = false)
	public Boolean getIsdown() {
		return this.isdown;
	}

	public void setIsdown(Boolean isdown) {
		this.isdown = isdown;
	}

	@Column(name = "isexport", nullable = false)
	public Boolean getIsexport() {
		return this.isexport;
	}

	public void setIsexport(Boolean isexport) {
		this.isexport = isexport;
	}

	@Column(name = "result", length = 16777215)
	public String getResult() {
		return this.result;
	}

	public void setResult(String result) {
		this.result = result;
	}

}