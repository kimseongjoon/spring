package com.example.mapper;

import com.example.vo.BoardVO;
import com.example.vo.UserVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    @Select({"SELECT * FROM USERS ORDER BY ID"})
    List<UserVO> userList();

    @Select({"SELECT * FROM USERS WHERE ID=#{id}"})
    UserVO fetchUserByID(int id);

    void updateUser(UserVO user);

    @Insert({"INSERT INTO users(id, username, passwords, firstname, lastname, age, salary) VALUES(USERS_SEQ.NEXTVAL, #{username}, #{passwords}, #{firstname}, #{lastname}, #{age}, #{salary})"})
    void insertUser(UserVO user);

    @Delete({"DELETE FROM users WHERE id = #{id}"})
    void deleteUser(@Param("id") int id);
}
