package com.kh.start.board.model.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardDTO {
	
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFileUrl;
	private Date createDate;
	private Date modifyDate;
	private String status;
	
}
