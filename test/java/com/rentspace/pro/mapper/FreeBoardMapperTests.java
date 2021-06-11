package com.rentspace.pro.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.rentspace.pro.common.paging.FreeBoardPagingDTO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/mybatis-context.xml")
@Log4j

public class FreeBoardMapperTests {
	@Setter(onMethod_ = @Autowired)
	private FreeBoardMapper freeBoardMapper;

//게시물 목록 조회 테스트 테스트 후 메서드 주석처리
@Test
public void testSelectBoardList() {
//freeBoardMapper.selectFreeBoardList().forEach(freeBoard -> log.info(freeBoard));
freeBoardMapper.selectFreeBoardList().forEach(freeBoard -> System.out.println(freeBoard));
}

////게시물 등록 테스트 - selectKey 사용 안함
//@Test
//public void testInsertFreeBoard() {
//FreeBoardVO freeBoard = new FreeBoardVO();
//freeBoard.setFreeboard_title(" 메퍼 테스트-입력제목 ");
//freeBoard.setFreeboard_content(" 매퍼 테스트-입력내용 ");
//freeBoard.setFreeboard_member_id("test");
//freeBoardMapper.insertFreeBoard(freeBoard);
//log.info(freeBoard);
//}

////게시물 등록 테스트 - selectKey 사용
//@Test
//public void testInsertFreeBoardSelectKey() {
//FreeBoardVO freeBoard = new FreeBoardVO();
//freeBoard.setFreeboard_title(" 메퍼 테스트-입력제목 ");
//freeBoard.setFreeboard_content(" 매퍼 테스트-입력내용 ");
//freeBoard.setFreeboard_member_id("test");
//freeBoardMapper.insertFreeBoardSelectKey(freeBoard);
//log.info(freeBoard);
//}

////게시물 조회 테스트(by freeboard_no)
//@Test
//public void testSelectFreeBoard() {
////존재하는 게시물 번호로 테스트
//log.info(freeBoardMapper.selectFreeBoard(1L));
//}

//게시물 목록 조회 테스트2 - 페이징 고려
@Test
public void testSelectBoardListWithPaging() {
FreeBoardPagingDTO freeBoardPagingDTO = new FreeBoardPagingDTO();//기본 생성자 이용(1, 10)
freeBoardMapper.selectFreeBoardList(freeBoardPagingDTO).forEach(freeBoard -> System.out.println(freeBoard));

freeBoardPagingDTO = new FreeBoardPagingDTO(2, 10);
freeBoardMapper.selectFreeBoardList(freeBoardPagingDTO).forEach(freeBoard -> System.out.println(freeBoard));
}

////게시물 삭제 테스트 - 삭제 요청된 게시물들 전체 삭제 (관리자)
//@Test
//public void testDeleteAllBoard() {
//log.info("DELETE COUNT: " + freeBoardMapper.deleteAllBoardSetDeleted());
//log.info(freeBoardMapper.selectFreeBoard(10L));
//}

////게시물 수정 테스트
//	@Test
//	public void testUpdateFreeBoard() {
//		FreeBoardVO freeBoard = new FreeBoardVO();
//		freeBoard.setFreeboard_no(34L); // 실행 전 존재하는 번호인지 확인할 것
//		freeBoard.setFreeboard_title("수정된 제목1");
//		freeBoard.setFreeboard_content("수정된 내용1");
//		log.info("UPDATE COUNT: " + freeBoardMapper.updateFreeBoard(freeBoard));
//		freeBoard = freeBoardMapper.selectFreeBoard(34L);
//		System.out.println("수정된 행 값: " + freeBoard.toString());
//	}

//	//게시물 조회수 증가 테스트: 3번 수행
//	@Test
//	public void testUpdateBviewsCnt() {
//	freeBoardMapper.updateBviewsCnt(34L);
//	System.out.println("수정된 행 값: " + freeBoardMapper.selectFreeBoard(34L).toString());
//	}	
	
}