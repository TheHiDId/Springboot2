package com.kh.start.comment.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.start.comment.model.dto.CommentDTO;
import com.kh.start.comment.model.vo.Comment;

@Mapper
public interface CommentMapper {
	
	void insertComment(Comment comment);
	
	List<CommentDTO> selectCommentList(Long boardNo);
}
