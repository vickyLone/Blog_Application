package com.vicky.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.vicky.binder.CommentBinder;
import com.vicky.entity.Comments;
import com.vicky.entity.Post;
import com.vicky.entity.User;
import com.vicky.repositary.CommentRepository;
import com.vicky.repositary.PostRepositary;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private PostRepositary postRepo;

	@Autowired
	private CommentRepository commentRepo;

	@Override
	public Boolean saveComment(CommentBinder commentBinder) {
		Comments comment = new Comments();
		BeanUtils.copyProperties(commentBinder, comment);

		Optional<Post> findById = postRepo.findById(commentBinder.getId());
		if (findById.isPresent()) {
			Post post = findById.get();
			comment.setPost(post);
			commentRepo.save(comment);
		}

		return true;
	}

	@Override
	public List<Comments> fetchComments(Integer id) {
		Comments comments = new Comments();
		Post post = new Post();
		post.setId(id);
		comments.setPost(post);

		Example<Comments> exmp = Example.of(comments);
		return commentRepo.findAll(exmp);

	}

	@Override
	public List<Comments> loadCommentTable(Integer id) {
		Comments comments = new Comments();
		Post post = new Post();
		User user = new User();
		user.setId(id);
		post.setUser(user);
		comments.setPost(post);

		Example<Comments> exmp = Example.of(comments);
		return commentRepo.findAll(exmp);

	}

	@Override
	public Boolean deleteComment(Integer commentId) {

		commentRepo.deleteById(commentId);

		return true;
	}

}
