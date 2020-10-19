package com.example.repository;

import com.example.vo.Member1Vo;
import com.example.vo.Member2Vo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Member1Repository extends JpaRepository<Member2Vo, Long> {
    Member2Vo findByUsername(String username);
}
