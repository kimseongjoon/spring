package com.example.mapper;

import com.example.vo.BoardVO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;

import java.sql.JDBCType;
import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert({"INSERT INTO BOARDTBL(BRD_NO, BRD_TITLE, BRD_CONTENT, BRD_HIT, BRD_DATE, BRD_WRITER, BRD_IMG) " +
            "VALUES (SEQ_BOARDTBL_BRD_NO.NEXTVAL, #{obj.brd_title}, #{obj.brd_content}, #{obj.brd_hit}, SYSDATE, #{obj.brd_writer}, #{obj.brd_img})"})
    int boardWrite(@Param("obj")BoardVO obj);

//    @Select({"SELECT BRD_NO, BRD_TITLE, BRD_HIT, BRD_DATE, BRD_WRITER FROM BOARDTBL ORDER BY BRD_NO DESC"})
    @Select({"SELECT * " +
            "FROM (SELECT brd_no, brd_title, brd_content, brd_hit, brd_date, brd_writer, row_number() over (ORDER BY brd_no DESC) rown " +
                    "FROM boardtbl " +
                    "WHERE brd_title LIKE '%' || #{txt} || '%' ) b1 " +
            "WHERE rown BETWEEN #{start} AND #{end}"})
    List<BoardVO> boardList(@Param("start") int start, @Param("end") int end, @Param("txt") String txt);

    @Select({"SELECT BRD_NO, BRD_TITLE, BRD_CONTENT, BRD_HIT, BRD_DATE, BRD_WRITER FROM BOARDTBL WHERE BRD_NO=#{no}"})
    BoardVO boardOne(@Param("no") int no);

    @Select({"SELECT BRD_NO, BRD_TITLE, BRD_CONTENT, BRD_HIT, BRD_DATE, BRD_WRITER FROM BOARDTBL WHERE BRD_NO=#{no}"})
    BoardVO boardOne1(@Param("no") int no);

    @Update({"UPDATE BOARDTBL SET BRD_HIT=BRD_HIT+1 WHERE BRD_NO=#{no}"})
    int boardUpdateHit(@Param("no") int no); // INSERT, UPDATE, DELETE => int

    @Update({"UPDATE BOARDTBL SET BRD_TITLE=#{obj.brd_title}, BRD_CONTENT=#{obj.brd_content} WHERE BRD_NO=#{obj.brd_no}"})
    int updateBoardOne(@Param("obj") BoardVO obj);

    @Select({"SELECT NVL(MAX(BRD_NO), 0) FROM BOARDTBL WHERE BRD_NO < #{no}"})
    int boardPrevNo(@Param("no") int no);

    @Select({"SELECT NVL(MIN(BRD_NO), 0) FROM BOARDTBL WHERE BRD_NO > #{no}"})
    int boardNextNo(@Param("no") int no);

    @Delete({"DELETE FROM BOARDTBL WHERE BRD_NO=#{no}"})
    int boardDeleteOne(@Param("no") int no);

    @Results({
            @Result(property = "brd_img", column = "BRD_IMG", jdbcType = JdbcType.BLOB, javaType = byte[].class)
    })
    @Select({"SELECT BRD_IMG FROM BOARDTBL WHERE BRD_NO=#{no}"})
    BoardVO boardImg(@Param("no") int no);

    @Select({"SELECT COUNT(*) FROM BOARDTBL WHERE brd_title LIKE '%' || #{txt} || '%'"})
    int boardCount(@Param("txt") String txt);
}
