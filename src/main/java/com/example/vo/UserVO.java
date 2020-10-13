package com.example.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class UserVO {
    private int id = 0;
    private String username;
    private String passwords = "";
    private String firstname;
    private String lastname;
    private int age;
    private int salary;

}