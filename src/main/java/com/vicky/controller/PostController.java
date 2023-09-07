package com.vicky.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vicky.binder.PostBinder;
import com.vicky.constants.AppConstants;
import com.vicky.props.ApplicationProperties;
import com.vicky.service.PostService;

@Controller
public class PostController {

	@Autowired
	private PostService postService;
	
	@Autowired
	private ApplicationProperties props;


	@GetMapping("/post")
	public String looadPostPage(Model model) {
		PostBinder postForm = new PostBinder();

		model.addAttribute(AppConstants.POSTFORM, postForm);

		return AppConstants.POSTPAGE;

	}

	@PostMapping("/post")
	public String saveBlog(@ModelAttribute(AppConstants.POSTFORM) PostBinder postform, Model model) {
		Boolean savePost = postService.savePost(postform);
		if (savePost) {
			model.addAttribute(AppConstants.SUCC_MSG, props.getMessages().get(AppConstants.BLOG_SAVED_MSG));
		} else {
			model.addAttribute(AppConstants.ERR_MSG, props.getMessages().get(AppConstants.BLOG_SAVED_ERR_MSG));
		}

		return AppConstants.POSTPAGE;

	}

}
