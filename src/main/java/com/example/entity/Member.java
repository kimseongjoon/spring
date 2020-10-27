package com.example.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Getter @Setter @ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "membertbl")
// 아이디, 암호 이름, 전화번호, 나이, 가입일자
public class Member {
	@Id
	private String userid = null;
	private String userpw = null;
	private String username = null;
	private String userphone = null;
	private int userage = 0;
	@CreationTimestamp
	private Date userdate = null;
	private byte[] userimg = null;

}
