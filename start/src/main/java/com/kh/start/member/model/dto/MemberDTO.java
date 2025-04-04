package com.kh.start.member.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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
public class MemberDTO {
	
	private Long memberNo;
	
	@Size(min = 6, max = 15, message = "아이디 길이는 6자에서 15자 사이여야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "잘못된 아이디 형식입니다.")
	@NotBlank(message = "아이디는 비어있을 수 없습니다.")
	private String memberId;
	
	@Size(min = 4, max = 20, message = "비밀번호 길이는 4자에서 20자 사이여야 합니다.")
	@Pattern(regexp = "^[a-z0-9]*$", message = "잘못된 비밀번호 형식입니다.")
	@NotBlank(message = "비밀번호는 비어있을 수 없습니다.")
	private String memberPw;
	private String memberName;
	private String role;
	
}
