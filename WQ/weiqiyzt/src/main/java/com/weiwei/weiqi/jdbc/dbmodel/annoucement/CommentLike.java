package com.weiwei.weiqi.jdbc.dbmodel.annoucement;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.weiwei.weiqi.jdbc.dbmodel.customer.Customer;

/**
 * CommentLike entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment_like")
public class CommentLike implements java.io.Serializable {

	// Fields
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;
	
	@Column(name = "like_time", length = 0)
	private Timestamp likeTime;
	
	@Column(name = "is_cancelled")
	private Boolean isCancelled;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="customer_id")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="session_id")
	private CommentSession commentSession;
	
	// Constructors

	/** default constructor */
	public CommentLike() {
	}

	// Property accessors
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	public Timestamp getLikeTime() {
		return this.likeTime;
	}

	public void setLikeTime(Timestamp likeTime) {
		this.likeTime = likeTime;
	}

	
	public Boolean getIsCancelled() {
		return this.isCancelled;
	}

	public void setIsCancelled(Boolean isCancelled) {
		this.isCancelled = isCancelled;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public CommentSession getCommentSession() {
		return commentSession;
	}

	public void setCommentSession(CommentSession commentSession) {
		this.commentSession = commentSession;
	}

	
	
}