package com.weiwei.weiqi.jdbc.dbmodel.annoucement;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.OrderBy;

/**
 * CommentSession entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "comment_session")
public class CommentSession implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6746264705039596790L;

	// Fields
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announce_id")
	private Htmls announce;

	//@BatchSize(size=2)
	@OrderBy(clause="commentTime")
	@OneToMany(mappedBy = "commentSession", fetch = FetchType.LAZY)
	private List<Comment> comments;
	
	//@BatchSize(size=2)
	@OrderBy(clause="likeTime")
	@OneToMany(mappedBy = "commentSession", fetch = FetchType.LAZY)
	private List<CommentLike> commentLikes;

	// Constructors
	/** default constructor */
	public CommentSession() {
	}

	// Property accessors
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Htmls getAnnounce() {
		return announce;
	}

	public void setAnnounce(Htmls announce) {
		this.announce = announce;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<CommentLike> getCommentLikes() {
		return commentLikes;
	}

	public void setCommentLikes(List<CommentLike> commentLikes) {
		this.commentLikes = commentLikes;
	}
	

}