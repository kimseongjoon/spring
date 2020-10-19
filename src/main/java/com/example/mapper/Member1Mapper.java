package com.example.mapper;

import com.example.vo.Member1Vo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface Member1Mapper {

    @Insert({"INSERT INTO MEMBER1TBL(id, USERNAME, password, name, role, userdate) VALUES(member1_seq.nextval, #{mem.username}, #{mem.password}, #{mem.name}, #{mem.role}, SYSDATE)"})
    void memberJoin(@Param("mem") Member1Vo mem);

    @Select({"SELECT * FROM member1tbl where username=#{username}"})
    Member1Vo memberLogin(@Param("username") String username);
}
