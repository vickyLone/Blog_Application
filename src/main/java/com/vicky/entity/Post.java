package com.vicky.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Post {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "post_id")
	    private Integer id;

	    @Column(name = "title", nullable = false)
	    @Length(min = 1, message = "*Your title must have at least 5 characters")
	    private String title;

	    @Column(name = "body")
	    @Lob
	    private String body;

	    @Temporal(TemporalType.TIMESTAMP)
	    @Column(name = "create_date", nullable = false, updatable = false)
	    @CreationTimestamp
	    private Date createDate;
	    
	    
	    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
	    private List<Comments> comments;

	    @ManyToOne
	    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	    @NotNull
	    private User user;

	  
}
