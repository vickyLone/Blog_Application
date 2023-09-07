package com.vicky.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vicky.binder.CommentBinder;
import com.vicky.entity.Comments;

@Service
public interface CommentService {
	
	public Boolean saveComment(CommentBinder comment);
	
	public List<Comments> fetchComments(Integer id);
	
	public List<Comments> loadCommentTable(Integer id);
	
	public Boolean deleteComment(Integer commentId);



}
