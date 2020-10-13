package com.example.mapper;

import com.example.vo.BoardVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface BoardMapper {
    @Insert({"INSERT INTO BOARDTBL(BRD_NO, BRD_TITLE, BRD_CONTENT, BRD_HIT, BRD_DATE, BRD_WRITER) " +
            "VALUES (SEQ_BOARDTBL_BRD_NO.NEXTVAL, #{obj.brd_title}, #{obj.brd_content}, #{obj.brd_hit}, SYSDATE, #{obj.brd_writer})"})
    int boardWrite(@Param("obj")BoardVO obj);

    @Select({"SELECT * FROM BOARDTBL ORDER BY BRD_NO DESC"})
    List<BoardVO> boardList();

    @Select({"SELECT * FROM BOARDTBL WHERE BRD_NO=#{no}"})
    BoardVO boardOne(@Param("no") int no);

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
}
