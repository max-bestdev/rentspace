package com.rentspace.pro.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rentspace.pro.domain.FreeBoardVO;
import com.rentspace.pro.mapper.FreeBoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
@AllArgsConstructor
public class FreeBoardServiceImpl implements FreeBoardService {
	
	private FreeBoardMapper freeBoardMapper;
	
	// //FreeBoardMapper를 주입받기 위한 생성자
	@Override	
	public List<FreeBoardVO> getBoardList() {
		log.info("FreeBoardService.getBoardList() 실행");
		return freeBoardMapper.selectFreeBoardList();
	}

	//게시물 등록:selectKey이용
	@Override
	public long registerBoard(FreeBoardVO freeBoard) {
	log.info("FreeBoardService.registerBoard()에 전달된 FreeBoardVO: " + freeBoard);
	freeBoardMapper.insertFreeBoardSelectKey(freeBoard);
	System.out.println("FreeBoardService에서 등록된 게시물의 freeboard_no: " + freeBoard.getFreeboard_no());
	return freeBoard.getFreeboard_no();
	}
	
	//게시물 조회: by freeboard_no + 조회수 증가 고려
	@Override
	public FreeBoardVO getBoard(Long freeboard_no) {
	log.info("FreeBoardService.getBoard()에 전달된 freeboard_no: " + freeboard_no);
	//조회수 증가 후, freeboard_no 게시물 데이터 반환
	freeBoardMapper.updateBviewsCnt(freeboard_no);
	return freeBoardMapper.selectFreeBoard(freeboard_no);
	}
	
	//게시물 수정 처리
	@Override
	public boolean modifyBoard(FreeBoardVO freeBoard){
	log.info("서비스에서의 게시물 수정 메소드(modify): " + freeBoard);
	//게시물 정보 수정, 수정된 행수가 1 이면 True.
	return freeBoardMapper.updateFreeBoard(freeBoard) == 1;
	}
	
	//게시물 삭제 - bdelFlag 컬럼만 1로 수정
	@Override
	public boolean setBoardDeleted(Long freeboard_no){
	log.info("FreeBoardService.setBoardDeleted()에 전달된 freeboard_no: " + freeboard_no);
	return freeBoardMapper.updateBdelFlag(freeboard_no) == 1;
	}
	
	//게시물 삭제 - 실제 삭제 발생
	@Override
	public boolean removeBoard(Long freeboard_no){
	log.info("FreeBoardService.removeBoard()에 전달된 freeboard_no: " + freeboard_no);
	return freeBoardMapper.deleteFreeBoard(freeboard_no) == 1;
	}
	
	//게시물 삭제(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
	@Override
	public int removeAllDeletedBoard(){
	log.info("FreeBoardService.removeAllDeletedBoard() 실행");
	//삭제된 총 행수 반환
	return freeBoardMapper.deleteAllBoardSetDeleted() ;
	}




	
	
}
