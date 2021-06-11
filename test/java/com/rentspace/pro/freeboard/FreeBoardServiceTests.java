package com.rentspace.pro.freeboard;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.rentspace.pro.domain.FreeBoardVO;
import com.rentspace.pro.service.FreeBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class FreeBoardServiceTests {

	@Setter(onMethod_ = { @Autowired })
	private FreeBoardService freeBoardService;


//MyBoardService 빈 생성 유무 확인 테스트
	@Test
	public void testFreeBoardServiceExist() {
		log.info(freeBoardService);
		assertNotNull(freeBoardService);
	}
//게시물 목록 조회 서비스 테스트
@Test
public void testGetBoardList() {
//페이징 고려 안함
freeBoardService.getBoardList().forEach(freeBoard -> log.info(freeBoard));
}



//게시물 등록: selectKey 이용 테스트
@Test
public void testRegisterBoard() {
	FreeBoardVO freeBoard = new FreeBoardVO();
	freeBoard.setFreeboard_title("서비스 새글 입력 테스트 제목");
	freeBoard.setFreeboard_content("서비스 새글 입력 테스트 내용");
	freeBoard.setFreeboard_member_id("test");
	freeBoardService.registerBoard(freeBoard);
	log.info("등록된 게시물의 freeboard_no: " + freeBoard.getFreeboard_no());
}

//게시물 수정 테스트
@Test
public void testModifyBoard() {
	FreeBoardVO freeBoard = freeBoardService.getBoard(1L);
	if (freeBoard == null) {
		return;
	}
	freeBoard.setFreeboard_title("제목 수정:서비스 테스트");
	log.info("수정된 게시물 조회 결과(boolean): " + freeBoardService.modifyBoard(freeBoard));
}

//게시물 조회 테스트: by freeboard_no + 조회수 증가 고려
@Test
public void testGetBoard() {
	log.info(freeBoardService.getBoard(1L));
}

//게시물 삭제 테스트- By 사용자: bdelFlag 컬럼만 1로 수정
@Test
public void testSetBoardDeleted() {
//게시물 번호의 존재 여부를 확인하고 테스트할 것
	log.info("수행결과: " + freeBoardService.setBoardDeleted(7L));
	log.info("수행결과: " + freeBoardService.setBoardDeleted(8L));
	log.info(freeBoardService.getBoard(7L));
	log.info(freeBoardService.getBoard(8L));
}

//게시물 삭제 테스트- By 관리자: 실제 삭제 발생
@Test
public void testRemoveBoard() {
//게시물 번호의 존재 여부를 확인하고 테스트할 것
	log.info("서비스: 특정 게시물 삭제 테스트: " + freeBoardService.removeBoard(6L));
}

//게시물 삭제 테스트(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
@Test
public void testRemoveAllDeletedBoard() {
	log.info("서비스: 삭제 설정된 모든 게시물 삭제로 삭제된 행 수: " + freeBoardService.removeAllDeletedBoard());
}

}