package com.kh.start.comment.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.start.auth.model.service.AuthService;
import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.board.model.service.BoardService;
import com.kh.start.comment.model.dao.CommentMapper;
import com.kh.start.comment.model.dto.CommentDTO;
import com.kh.start.comment.model.vo.Comment;
import com.kh.start.exception.InvalidUserRequestException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;
	private final BoardService boardService;
	private final AuthService authService;
	
	@Override
	public void insertComment(CommentDTO comment) {
		
		boardService.selectOne(comment.getRefBoardNo());
		
		String tokenMemberNo = String.valueOf(((CustomUserDetails)authService.getUserDetails()).getMemberNo());
		
		if(!tokenMemberNo.equals(comment.getCommentWriter())) {
			throw new InvalidUserRequestException("권한이 없습니다.");
		}
		
		Comment requestData = Comment.builder()
									 .commentWriter(Long.parseLong(tokenMemberNo))
									 .commentContent(comment.getCommentContent())
									 .refBoardNo(comment.getRefBoardNo())
									 .build();
		
		commentMapper.insertComment(requestData);
	}

	@Override
	public List<CommentDTO> selectCommentList(Long boardNo) {
		
		boardService.selectOne(boardNo);
		
		return commentMapper.selectCommentList(boardNo);
	}

}
