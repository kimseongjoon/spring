package com.example.repository;

import com.example.entity.Item;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>{

    // SELECT * FROM itemtbl WHERE itmno =:no
    Item findAllByItmno(long no);

    // SELECT COUNT(*) FROM itemtbl WHERE itmname LIKE '%' || '사과' || '%'
    long countByItmnameIgnoreCaseContaining(String itmname);

    // SELECT * FROM ITEMTBL WHERE itmname LIKE '%' || '사과' || '%'
    // ORDER BY itmno ASC
    List<Item> findAllByItmnameIgnoreCaseContainingOrderByItmnoAsc(String itmname, Pageable pageable);

    List<Item> findAllByOrderByItmnoDesc();

    @Query(value = "SELECT * FROM itemtbl ORDER BY itmno DESC", nativeQuery = true)
    List<Item> sqlOrderBynoDesc();

    //DELETE FROM itemtbl WHERE itmno=:no
    @Transactional
    int deleteByItmno(long itmno);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM itemtbl WHERE itmno=:itmno", nativeQuery = true)
    int sqlDeleteByNo(@Param("itmno") long itmno);

    @Transactional
    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE itemtbl SET itmname=:#{#obj.itmname}, itmcontent=:#{#obj.itmcontent}, itmprice=:#{#obj.itmprice} WHERE itmno=:#{#obj.itmno}", nativeQuery = true)
    int sqlUpdateByNo(@Param("obj") Item obj);
}
