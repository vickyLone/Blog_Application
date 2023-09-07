package com.vicky.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.binder.CommentBinder;
import com.vicky.constants.AppConstants;
import com.vicky.entity.Comments;
import com.vicky.entity.Post;
import com.vicky.repositary.PostRepositary;
import com.vicky.service.CommentService;

@Controller
public class ViewBlogsController {

	@Autowired
	private CommentService commentService;


	@Autowired
	private PostRepositary postRepo;

	@GetMapping("/blog/{id}")
	public String loadViewBlogsPage(@PathVariable(AppConstants.ID) Integer id, Model model) {

		Optional<Post> findOptional = postRepo.findById(id);
		if(findOptional.isPresent()) {
			Post blog = findOptional.get();
			model.addAttribute(AppConstants.BLOG, blog);

			List<Comments> comments = commentService.fetchComments(id);
			model.addAttribute(AppConstants.COMMENTS, comments);

			CommentBinder commentBinder = new CommentBinder();
			model.addAttribute(AppConstants.COMMENT_BINDER, commentBinder);
		}
		
		return AppConstants.VIEW_BLOGS;
	}

	@PostMapping("/comment")
	public String saveComments(@RequestParam(AppConstants.ID) Integer id,
			@ModelAttribute(AppConstants.COMMENT_BINDER) CommentBinder commentBinder, Model model) {
		Boolean saveComment = commentService.saveComment(commentBinder);
		if (saveComment) {
			return "redirect:/blog/" + id;
		}
		return AppConstants.VIEW_BLOGS;
	}
}
