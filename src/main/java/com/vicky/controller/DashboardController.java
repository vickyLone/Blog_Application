package com.vicky.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.constants.AppConstants;
import com.vicky.entity.Post;
import com.vicky.props.ApplicationProperties;
import com.vicky.repositary.PostRepositary;
import com.vicky.service.PostService;

@Controller
public class DashboardController {

	@Autowired
	private PostService postService;

	@Autowired
	private HttpSession session;

	@Autowired
	private PostRepositary postRepo;
	
	@Autowired
	private ApplicationProperties props;

	@GetMapping("/dashboard")
	public String loadDashboard(Model model) {
		Integer userId = (Integer) session.getAttribute(AppConstants.USER_ID);
		List<Post> userDashboard = postService.userDashboardBlogs(userId);

		model.addAttribute(AppConstants.USER_DASHBOARD, userDashboard);
		return AppConstants.DASHBOARD;

	}

	@GetMapping("/edit")
	public String editform(@RequestParam(AppConstants.ID) Integer id, Model model) {

		Optional<Post> findById = postRepo.findById(id);

		if (findById.isPresent()) {

			model.addAttribute(AppConstants.POSTFORM, findById);

		}

		return AppConstants.POSTPAGE;

	}

	@GetMapping("/delete")
	public String deleteBlog(@RequestParam(AppConstants.ID) Integer id, Model model) {

		Boolean status = postService.deleteBlog(id);
		if (status) {
			model.addAttribute(AppConstants.DELETE_MSG, props.getMessages().get(AppConstants.BLOG_DELETE_MSG));

		}
		return "redirect:/dashboard";
	}

}
