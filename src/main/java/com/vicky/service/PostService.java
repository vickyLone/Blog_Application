package com.vicky.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vicky.binder.PostBinder;
import com.vicky.entity.Post;

@Service
public interface PostService {

	
	public Boolean savePost(PostBinder post);
	
	public List<Post> userDashboardBlogs(Integer id);
	
	public Boolean deleteBlog(Integer id);
	
	public List<Post> indexCardBlogs();
	
}
