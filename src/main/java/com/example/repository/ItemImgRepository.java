package com.example.repository;

import com.example.entity.Item;
import com.example.entity.ItemImg;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemImgRepository extends CrudRepository<ItemImg, Long> {
    // SELECT * from item_img_tbl WHERE itmno=:itmno
    List<ItemImg> findAllByItmno(long itmno);
}
