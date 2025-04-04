package com.kh.start.board.model.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.auth.model.service.AuthService;
import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.board.model.dao.BoardMapper;
import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.vo.Board;
import com.kh.start.file.service.FileService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {
	
	private final BoardMapper boardMapper;
	private final AuthService authService;
	private final FileService fileService; 

	@Override
	public void insert(BoardDTO board, MultipartFile file) {

		CustomUserDetails user = authService.getUserDetails();
		
		Long memberNo = user.getMemberNo();
		
		Board requestData = null; 
		
		board.setBoardWriter(String.valueOf(memberNo));
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			
			requestData = Board.builder()
							   .boardTitle(board.getBoardTitle())
							   .boardContent(board.getBoardContent())
							   .boardWriter(String.valueOf(memberNo))
							   .boardFileUrl(filePath)
							   .build();
		} else {
			requestData = Board.builder()
					   		   .boardTitle(board.getBoardTitle())
					   		   .boardContent(board.getBoardContent())
					   		   .boardWriter(String.valueOf(memberNo))
					   		   .build();
		}
		
		boardMapper.insert(requestData);
	}

	@Override
	public List<BoardDTO> selectAll(int pageNo) {
		
		int size = 3;

		RowBounds rowBounds = new RowBounds(pageNo * size, size);

		return boardMapper.selectAll(rowBounds);
	}

	@Override
	public BoardDTO selectOne(Long boardNo) {
		
		BoardDTO board = boardMapper.selectOne(boardNo);
		
		if(board == null) {
			throw new RuntimeException("존재하지 않는 게시글 요청 입니다.");
		}
		
		return board;
	}

	@Override
	public BoardDTO update(BoardDTO board, MultipartFile file) {
		
		if(file != null && !file.isEmpty()) {
			String filePath = fileService.store(file);
			
			board.setBoardFileUrl(filePath);
		}
		
		boardMapper.update(board);
		
		return board;
	}

	@Override
	public void deleteOne(Long boardNo) {
		
		boardMapper.deleteOne(boardNo);
	}

}
