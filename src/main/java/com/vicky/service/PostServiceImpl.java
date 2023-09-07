package com.vicky.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vicky.binder.PostBinder;
import com.vicky.constants.AppConstants;
import com.vicky.entity.Post;
import com.vicky.entity.User;
import com.vicky.repositary.PostRepositary;
import com.vicky.repositary.UserRepositary;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepositary postRepo;

	@Autowired
	private UserRepositary userepo;

	@Autowired
	private HttpSession session;

	@Override
	public Boolean savePost(PostBinder post) {
		Post blog = new Post();
		BeanUtils.copyProperties(post, blog);

		Integer userId = (Integer) session.getAttribute(AppConstants.USER_ID);
		Optional<User> findById = userepo.findById(userId);
		if (findById.isPresent()) {
			User user = findById.get();
			blog.setUser(user);
			postRepo.save(blog);
		}

		return true;
	}

	@Override
	public List<Post> userDashboardBlogs(Integer id) {

		Post post = new Post();
		User user = new User();
		user.setId(id);
		post.setUser(user);

		Example<Post> exmp = Example.of(post);
		return  postRepo.findAll(exmp);

		
	}

	@Override
	public Boolean deleteBlog(Integer id) {

		postRepo.deleteById(id);

		return true;
	}

	@Override
	public List<Post> indexCardBlogs() {

		return  postRepo.findAll();
		
	}

}
