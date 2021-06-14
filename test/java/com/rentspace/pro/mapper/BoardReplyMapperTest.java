package com.rentspace.pro.mapper;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentspace.pro.domain.BoardReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/mybatis-context.xml")
@Log4j

public class BoardReplyMapperTest {

	@Setter(onMethod_ = @Autowired)	
	private BoardReplyMapper boardReplyMapper;

	// 매퍼 인스턴스 생성 테스트
	@Test
	public void testMapper() {
		log.info(boardReplyMapper);
	}

	// 특정 게시물에 대한 댓글 목록 조회 테스트
	@Test
	public void testSelectBoardReplyList() {
		Long targetReply_freeboard_no = 458752L;
		List<BoardReplyVO> boardReplies = boardReplyMapper.selectBoardReplyList(targetReply_freeboard_no);
		boardReplies.forEach(boardReply -> System.out.println(boardReply));
	}

	// 특정 게시물에 대한 댓글 등록 테스트
//	@Test
//	public void testInsertBoardReplyForBoard() {
//		BoardReplyVO boardReply = new BoardReplyVO();
//		boardReply.setReply_freeboard_no(458751L);
//		boardReply.setReply_content("매퍼 댓글 입력 테스트 ");
//		boardReply.setReply_member_id("replyer");
//		log.info("매퍼 - 추가 전 댓글 객체: " + boardReply);
//		boardReplyMapper.insertBoardReplySelectKey(boardReply);
//		log.info("매퍼 - 추가 후 댓글 객체: " + boardReply);
//		boardReplyMapper.selectBoardReply(458751L, boardReply.getReply_no());
//	}
//
//	// 특정 게시물에 대한 댓글의 댓글 등록 테스트
//	@Test
//	public void testInsertBoardReplyForReply() {
//		BoardReplyVO boardReply = new BoardReplyVO();
//		boardReply.setReply_freeboard_no(458752L);
//		boardReply.setReply_content("매퍼 댓글의 댓글 입력 테스트 ");
//		boardReply.setReply_member_id("test03");
////		boardReply.setPrno("1L");
//		log.info("매퍼 - 추가 전 댓글 객체: " + boardReply);
//		boardReplyMapper.insertBoardReplySelectKey(boardReply);
//		log.info("매퍼 - 추가 후 댓글 객체: " + boardReply);
//		boardReplyMapper.selectBoardReply(458752L, boardReply.getReply_no());
//	}

//	// 특정 게시물에 대한 특정 댓글 조회 테스트
//	@Test
//	public void testSelectBoardReply() {
//		Long targetReply_freeboard_no = 458752L;
//		Long targetReply_no = 2L;
//		// 458752L
//		BoardReplyVO boardReply = boardReplyMapper.selectBoardReply(targetReply_freeboard_no, targetReply_no);
//		log.info(boardReply);
//	}
//
//	// 특정 게시물에 대한 특정 댓글 수정 테스트
//	@Test
//	public void testUpdateBoardReply() {
//		Long targetReply_freeboard_no = 458752L;
//		Long targetReply_no = 2L;
//		BoardReplyVO boardReply = boardReplyMapper.selectBoardReply(targetReply_freeboard_no, targetReply_no);
//		boardReply.setReply_content("매퍼 댓글의 수정 테스트 ");
//		int count = boardReplyMapper.updateBoardReply(boardReply);
//		log.info("UPDATE COUNT: " + count);
//		log.info(boardReplyMapper.selectBoardReply(targetReply_freeboard_no, targetReply_no));
//	}
//
//	// 특정 게시물에 대한 특정 댓글 삭제 테스트
//	@Test
//	public void testDeleteBoardReply() {
//		Long targetReply_freeboard_no = 458752L;
//		Long targetReply_no = 4L;
//		int count = boardReplyMapper.deleteBoardReply(targetReply_freeboard_no, targetReply_no);
//		log.info("DELETE COUNT: " + count);
//		log.info(boardReplyMapper.selectBoardReply(targetReply_freeboard_no, targetReply_no));
//	}
}