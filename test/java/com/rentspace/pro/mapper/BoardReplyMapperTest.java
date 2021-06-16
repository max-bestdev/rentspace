package com.rentspace.pro.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentspace.pro.common.paging.BoardReplyPagingDTO;
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

//	// 특정 게시물에 대한 댓글 목록 조회 테스트
//	@Test
//	public void testSelectBoardReplyList() {
//		Long targetFreeboard_no = 117L;
//		List<BoardReplyVO> boardReplies = boardReplyMapper.selectBoardReplyList(targetFreeboard_no);
//		boardReplies.forEach(boardReply -> System.out.println(boardReply));
//	}

//	// 특정 게시물에 대한 댓글 등록 테스트
//	@Test
//	public void testInsertBoardReplyForBoard() {
//		BoardReplyVO boardReply = new BoardReplyVO();
//		boardReply.setFreeboard_no(524310L);
//		boardReply.setReply_content("매퍼 댓글 입력 테스트 ");
//		boardReply.setReply_writer("test03");
//		log.info("매퍼 - 추가 전 댓글 객체: " + boardReply);
//		boardReplyMapper.insertBoardReplyForBoard(boardReply);
//		log.info("매퍼 - 추가 후 댓글 객체: " + boardReply);
//		boardReplyMapper.selectBoardReply(524310L, boardReply.getReply_no());
//	}

//	// 특정 게시물에 대한 댓글의 댓글 등록 테스트
//	@Test
//	public void testInsertBoardReplyForReply() {
//		BoardReplyVO boardReply = new BoardReplyVO();
//		boardReply.setFreeboard_no(117L);
//		boardReply.setReply_content("매퍼 댓글의 댓글 입력 테스트 ");
//		boardReply.setReply_writer("test03");
//		boardReply.setFrno("1L");
//		log.info("매퍼 - 추가 전 댓글 객체: " + boardReply);
//		boardReplyMapper.insertBoardReplySelectKey(boardReply);
//		log.info("매퍼 - 추가 후 댓글 객체: " + boardReply);
//		boardReplyMapper.selectBoardReply(117L, boardReply.getReply_no());
//	}

//	// 특정 게시물에 대한 특정 댓글 조회 테스트
//	@Test
//	public void testSelectBoardReply() {
//		Long targetFreeboard_no = 117L;
//		Long targetReply_no = 2L;
//		// 458752L
//		BoardReplyVO boardReply = boardReplyMapper.selectBoardReply(targetFreeboard_no, targetReply_no);
//		log.info(boardReply);
//	}

//	// 특정 게시물에 대한 특정 댓글 수정 테스트
//	@Test
//	public void testUpdateBoardReply() {
//		Long targetFreeboard_no = 117L;
//		Long targetReply_no = 86L;
//		BoardReplyVO boardReply = boardReplyMapper.selectBoardReply(targetFreeboard_no, targetReply_no);
//		boardReply.setReply_content("매퍼 댓글의 수정 테스트 ");
//		int count = boardReplyMapper.updateBoardReply(boardReply);
//		log.info("UPDATE COUNT: " + count);
//		log.info(boardReplyMapper.selectBoardReply(targetFreeboard_no, targetReply_no));
//	}

//	// 특정 게시물에 대한 특정 댓글 삭제 테스트
//	@Test
//	public void testDeleteBoardReply() {
//		Long targetFreeboard_no = 117L;
//		Long targetReply_no = 86L;
//		int count = boardReplyMapper.deleteBoardReply(targetFreeboard_no, targetReply_no);
//		log.info("DELETE COUNT: " + count);
//		log.info(boardReplyMapper.selectBoardReply(targetFreeboard_no, targetReply_no));
//	}

	// 특정 게시물에 대한 댓글 목록 조회(페이징) 테스트
	@Test
	public void testSelectBoardReplyListPaging() {
		long targetFreeboard_no = 117L;
		BoardReplyPagingDTO boardReplyPagingDTO = new BoardReplyPagingDTO(targetFreeboard_no, 1);
		long totalReplyCnt = boardReplyMapper.selectReplyTotalByFreeboard_no(boardReplyPagingDTO);
		log.info("댓글 총 개수: " + totalReplyCnt);
		List<BoardReplyVO> boardReplies = boardReplyMapper.selectBoardReplyList(boardReplyPagingDTO);
		boardReplies.forEach(boardReply -> System.out.println(boardReply));
	}

}