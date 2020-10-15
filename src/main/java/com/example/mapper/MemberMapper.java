package com.example.mapper;

import com.example.vo.MemberVO;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MemberMapper {
    @Insert({"INSERT INTO MEMBERTBL(USERID, USERPW, USERNAME, USERPHONE, USERAGE, USERDATE, USERIMG) VALUES(#{mem.userid}, #{mem.userpw}, #{mem.username}, #{mem.userphone}, #{mem.userage}, SYSDATE, #{mem.userimg})"})
    public int memberJoin(@Param("mem") MemberVO mem);

    @Select({"SELECT USERID, USERNAME, USERAGE FROM MEMBERTBL WHERE USERID=#{mem.userid} AND USERPW=#{mem.userpw}"})
    public MemberVO memberLogin(@Param("mem") MemberVO mem);

    @Select({"SELECT USERIMG FROM MEMBERTBL WHERE USERID=#{id}"})
    MemberVO memberImg(@Param("id") String id);
}
