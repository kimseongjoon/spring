package com.example.vo;

import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class BoardVO {
    private long brd_no = 0;
    private String brd_title = null;
    private String brd_content = null;
    private int brd_hit = 0;
    private String brd_date = null;
    private String brd_userid = null;
    private String brd_writer = null;
    private byte[] brd_img = null;
}
