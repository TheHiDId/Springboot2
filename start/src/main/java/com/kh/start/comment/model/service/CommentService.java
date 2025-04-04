package com.kh.start.comment.model.service;

import java.util.List;

import com.kh.start.comment.model.dto.CommentDTO;

public interface CommentService {
	
	void insertComment(CommentDTO comment);
	
	List<CommentDTO> selectCommentList(Long boardNo);
}
