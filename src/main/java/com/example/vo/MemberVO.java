package com.example.vo;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor
// 아이디, 암호 이름, 전화번호, 나이, 가입일자
public class MemberVO {
	private String userid = null;
	private String userpw = null;
	private String username = null;
	private String userphone = null;
	private int userage = 0;
	private String userdate = null;
}
