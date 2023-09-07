package com.vicky.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vicky.constants.AppConstants;
import com.vicky.entity.Comments;
import com.vicky.props.ApplicationProperties;
import com.vicky.service.CommentService;

@Controller
public class CommentController {

	@Autowired
	private CommentService commentService;

	@Autowired
	private HttpSession session;

	@Autowired
	private ApplicationProperties props;

	@GetMapping("/loadComments")
	public String getAllComments(Model model) {
		Integer userId = (Integer) session.getAttribute(AppConstants.USER_ID);
		List<Comments> commentTable = commentService.loadCommentTable(userId);
		model.addAttribute(AppConstants.COMMENT_TABLE, commentTable);

		return AppConstants.COMMENT_TABLE;
	}

	@GetMapping("/deleteComment")
	public String deleteComment(@RequestParam(AppConstants.COMMENT_ID) Integer commentId, Model model) {

		Boolean status = commentService.deleteComment(commentId);
		if (status) {
			model.addAttribute(AppConstants.DELETE_MSG, props.getMessages().get(AppConstants.COMMENT_DELETE_MSG));

		}
		return "redirect:/loadComments";
	}

}
