package com.kh.start.board.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.kh.start.board.model.dto.BoardDTO;
import com.kh.start.board.model.service.BoardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@PostMapping
	public ResponseEntity<?> insert(@Valid BoardDTO board, @RequestParam(name = "file", required = false) MultipartFile file) {

		boardService.insert(board, file);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<List<BoardDTO>> selectAll(@RequestParam(name = "page", defaultValue = "0") int page) {
		
		return ResponseEntity.ok(boardService.selectAll(page));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<BoardDTO> selectOne(@PathVariable(name = "id") @Min(value = 1, message = "값이 너무 작습니다.") Long boardNo) {
		
		return ResponseEntity.ok(boardService.selectOne(boardNo));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BoardDTO> update(@PathVariable(name = "id") Long boardNo, BoardDTO board, @RequestParam(name = "file", required = false) MultipartFile file) {
		
		board.setBoardNo(boardNo);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(boardService.update(board, file));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BoardDTO> deleteOne(@PathVariable(name = "id") Long boardNo) {
		
		boardService.deleteOne(boardNo);
		
		return ResponseEntity.ok().build();
	}
}
