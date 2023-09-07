package com.vicky.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.constants.AppConstants;
import com.vicky.entity.Post;
import com.vicky.repositary.PostRepositary;
import com.vicky.service.PostService;

@Controller
public class IndexController {

	@Autowired
	private PostService postService;
	
	
	@Autowired
	private PostRepositary blogRepository;

	@GetMapping("/")
	public String loadIndexPage(Model model) {
		List<Post> cards = postService.indexCardBlogs();

		model.addAttribute(AppConstants.CARDS, cards);

		return AppConstants.INDEX;
	}

	@PostMapping("/")
	public String welcomeCards(Model model) {

		List<Post> cards = postService.indexCardBlogs();
		model.addAttribute(AppConstants.CARDS, cards);

		return AppConstants.INDEX;
	}

	@GetMapping("/search")
	public String search(@RequestParam(name = "q") String query, Model model) {
	    List<Post> blogs = blogRepository.findByTitleContainingOrBodyContaining(query, query);
	    model.addAttribute(AppConstants.CARDS, blogs);
	    model.addAttribute(AppConstants.QUERY, query);
	    return AppConstants.INDEX;
	}

}
