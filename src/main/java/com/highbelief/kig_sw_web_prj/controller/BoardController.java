package com.highbelief.kig_sw_web_prj.controller;

import com.highbelief.kig_sw_web_prj.model.Board;
import com.highbelief.kig_sw_web_prj.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "http://localhost:63342") // 프론트엔드 도메인 허용
public class BoardController {

    private final BoardService boardService;
    private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Board>> list() {
        List<Board> boards = boardService.findAll();
        if (boards.isEmpty()) {
            logger.info("No boards found.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 데이터가 없을 경우 204 상태 코드 반환
        } else {
            logger.info("Fetched boards: {}", boards);
            return new ResponseEntity<>(boards, HttpStatus.OK); // 성공 시 200 상태 코드와 데이터 반환
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Board> view(@PathVariable Long id) {
        Optional<Board> board = Optional.ofNullable(boardService.findById(id));
        if (board.isPresent()) {
            logger.info("Fetched board with ID {}: {}", id, board.get());
            return new ResponseEntity<>(board.get(), HttpStatus.OK); // 성공 시 200 상태 코드와 데이터 반환
        } else {
            logger.warn("Board with ID {} not found.", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 게시글이 없을 경우 404 상태 코드 반환
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Board> create(@RequestBody Board board) {
        Board createdBoard = boardService.save(board);
        logger.info("Created new board: {}", createdBoard);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED); // 생성 성공 시 201 상태 코드 반환
    }
}
