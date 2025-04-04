package com.kh.start.board.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class Board {
	
	private Long boardNo;
	private String boardTitle;
	private String boardContent;
	private String boardWriter;
	private String boardFileUrl;
	private Date createDate;
	private Date modifyDate;
	private String status;
	
}
