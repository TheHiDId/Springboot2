package com.kh.start.comment.model.dto;

import java.sql.Date;

import jakarta.validation.constraints.NotBlank;
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
public class CommentDTO {

	private Long commentNo;
	
	@NotBlank(message = "댓글 내용을 작성해 주세요.")
	private String commentContent;
	private String commentWriter;
	private Date createDate;
	private Long refBoardNo;

}
