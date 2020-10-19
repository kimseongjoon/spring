package com.example.mapper;

import com.example.vo.MemberVO;
import org.apache.ibatis.annotations.*;
import sun.security.x509.CertAttrSet;

import java.util.List;

@Mapper
public interface MemberMapper {
    @Select({"SELECT * FROM membertbl"})
    List<MemberVO> memberList();

    @Select({
            "<script>",
            "SELECT * FROM membertbl WHERE userid IN (",
                "<foreach collection='chk' item='id' separator=', '>",
                    "#{id}",
                "</foreach>",
            ")",
            "</script>"
    })
    List<MemberVO> memberListByIds(@Param("chk") String[] chk);

    @Insert({"INSERT INTO MEMBERTBL(USERID, USERPW, USERNAME, USERPHONE, USERAGE, USERDATE, USERIMG) VALUES(#{mem.userid}, #{mem.userpw}, #{mem.username}, #{mem.userphone}, #{mem.userage}, SYSDATE, #{mem.userimg})"})
    public int memberJoin(@Param("mem") MemberVO mem);

    @Select({"SELECT USERID, USERNAME, USERAGE FROM MEMBERTBL WHERE USERID=#{mem.userid} AND USERPW=#{mem.userpw}"})
    public MemberVO memberLogin(@Param("mem") MemberVO mem);

    @Select({"SELECT USERIMG FROM MEMBERTBL WHERE USERID=#{id}"})
    MemberVO memberImg(@Param("id") String id);

    @Select({"SELECT count(*) FROM membertbl WHERE userid=#{id}"})
    int isMember(@Param("id") String id);

    @Insert({
            "<script>",
            "INSERT ALL",
                "<foreach collection='list' item='obj' separator=' '>",
                    "INTO MEMBERTBL(USERID, USERPW, USERNAME, USERPHONE, USERAGE, USERDATE)",
                    "VALUES (#{obj.userid}, #{obj.userpw}, #{obj.username}, #{obj.userphone}, #{obj.userage}, SYSDATE)",
                "</foreach>",
            "SELECT * FROM DUAL",
            "</script>"
    })
    int memberBatchInsert(@Param("list") List<MemberVO> list);


    @Delete({
            "<script>",
            "DELETE FROM membertbl WHERE userid IN(",
                "<foreach collection='array' item='tmp' separator=', '>",
                    "#{tmp}",
                "</foreach>",
            ")",
            "</script>"
    })
    int memberBatchDelete(@Param("array") String[] array);

    @Update({
            "<script>",
            "UPDATE membertbl SET",
                "userpw = (CASE",
                    "<foreach collection='ids' item='tmp' separator=' '>",
                        "WHEN userid=#{tmp.userid} THEN #{tmp.userpw}",
                    "</foreach>",
                "END),",
                "username = (CASE",
                    "<foreach collection='ids' item='tmp' separator=' '>",
                        "WHEN userid=#{tmp.userid} THEN #{tmp.username}",
                    "</foreach>",
                "END),",
                "userphone = (CASE",
                    "<foreach collection='ids' item='tmp' separator=' '>",
                        "WHEN userid=#{tmp.userid} THEN #{tmp.userphone}",
                    "</foreach>",
                "END),",
                "userage = (CASE",
                    "<foreach collection='ids' item='tmp' separator=' '>",
                        "WHEN userid=#{tmp.userid} THEN #{tmp.userage}",
                    "</foreach>",
                "END)",
            "WHERE userid IN (",
                "<foreach collection='ids' item='tmp' separator=', '>",
                    "#{tmp.userid}",
                "</foreach>",
                ")",
            "</script>"
    })
    void memberBatchUpdate(@Param("ids") List<MemberVO> list);


}
