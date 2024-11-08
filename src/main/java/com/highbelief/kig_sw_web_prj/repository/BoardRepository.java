package com.highbelief.kig_sw_web_prj.repository;

import com.highbelief.kig_sw_web_prj.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
