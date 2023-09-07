package com.vicky.repositary;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vicky.entity.Post;

public interface PostRepositary extends JpaRepository<Post, Integer>{

   public Optional<Post> findById(Integer id);

	public List<Post> findByTitleContainingOrBodyContaining(String query, String query2);

}
