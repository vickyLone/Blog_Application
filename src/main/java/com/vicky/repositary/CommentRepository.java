package com.vicky.repositary;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicky.entity.Comments;

public interface CommentRepository extends JpaRepository<Comments, Integer>{

}
