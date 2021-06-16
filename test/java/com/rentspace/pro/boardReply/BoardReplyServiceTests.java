package com.rentspace.pro.boardReply;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rentspace.pro.domain.BoardReplyVO;
import com.rentspace.pro.service.BoardReplyService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
@WebAppConfiguration //테스트 시, DispatcherServlet 의 servlet-context.xml 설정 구성파일(들)을 사용하기 위한 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@Log4j
public class BoardReplyServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private BoardReplyService boardReplyService;	
	
	
	// BoardReplyService 빈 생성 유무 확인 테스트
	@Test
	public void testBoardReplyServiceExist() {
		log.info(boardReplyService);
		assertNotNull(boardReplyService);
	}

//	// 게시물에 대한 댓글 목록 조회 테스트 //SQL*Developer 에서 댓글이 있는 게시물 번호 확인해서 코드 수정 후 테스트 수행할 것
//	@Test
//	public void testGetReplyList() {
//		BoardReplyPagingCreatorDTO boardReplyPagingCreator = boardReplyService
//				.getReplyListByReply_freeboard_no(new BoardReplyPagingDTO(524301, 1));
//		log.info("댓글-서비스-게시물에 대한 댓글 목록 조회 테스트- 반환된 BoardReplyPagingCreatorDTO: " + boardReplyPagingCreator);
//	}
//
//	// 게시물에 대한 댓글 등록(reply_no 반환) 테스트
//	@Test
//	public void testRegisterReplyForBoard() {
//		BoardReplyVO boardReply = new BoardReplyVO();
//		boardReply.setReply_freeboard_no(524301L);
//		boardReply.setReply_content("서비스 게시물에 대한 댓글 등록 테스트");
//		boardReply.setReply_writer("test");
//		Long registeredReply_no = boardReplyService.registerReplyForBoard(boardReply);
//		log.info("서비스 게시물에 대한 댓글 등록 테스트-등록된 댓글번호: " + registeredReply_no);
//	}
//
//

	// 특정 게시물에 대한 특정 댓글 조회
	@Test
	public void testGetReply() {
		log.info(boardReplyService.getReply(524301L, 5L));
	}
//
//	// 게시물에 대한 특정 댓글 수정 테스트
//	@Test
//	public void testModifyReply() {
//		BoardReplyVO boardReply = boardReplyService.getReply(458752L, 5L);
//		if (boardReply == null) {
//			return;
//		}
//		boardReply.setReply_content("서비스-댓글 수정테스트입니다.수정");
//		log.info("서비스-댓글 수정테스트시 반환된 값(수정된 댓글 수): " + boardReplyService.modifyReply(boardReply));
//		log.info("서비스-댓글 수정테스트: 수정후 조회(수정된 boardReplyVO): " + boardReplyService.getReply(458752L, 6L));
//	}
//
//	// 게시물에 대한 특정 댓글 삭제
//	@Test
//	public void testRemoveReply() {
//		// 댓글 목록 보기 테스트를 다시해서 댓글의 reply_freeboard_no 와 reply_no 존재 여부를 확인하고 테스트할 것
//		log.info("서비스: 특정 게시물 삭제 테스트: " + boardReplyService.removeReply(458751L, 19L));
//	}	
	
}
