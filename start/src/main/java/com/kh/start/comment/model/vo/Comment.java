package com.kh.start.comment.model.vo;

import java.sql.Date;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Comment {
	
	private Long commentNo;
	private String commentContent;
	private Long commentWriter;
	private Date createDate;
	private Long refBoardNo;
	
}
