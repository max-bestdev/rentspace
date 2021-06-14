package com.rentspace.pro.service;

import java.util.List;

import com.rentspace.pro.common.paging.FreeBoardPagingDTO;
import com.rentspace.pro.domain.FreeBoardVO;

public interface FreeBoardService{

	// 게시물 목록 조회 서비스1
	//public List<FreeBoardVO> getBoardList();

	//게시물 목록 조회 서비스 2 - 페이징 고려
	public List<FreeBoardVO> getBoardList(FreeBoardPagingDTO freeBoardPagingDTO);	
	
	// 게시물 등록: selectKey이용
	public long registerBoard(FreeBoardVO freeBoard);

	// 게시물 조회: by bno + 조회수 증가 고려
	public FreeBoardVO getBoard(Long freeBoard_no);

	// 게시물 수정
	public boolean modifyBoard(FreeBoardVO freeBoard);

	// 게시물 삭제 - bdelFlag 컬럼만 1로 수정
	public boolean setBoardDeleted(Long freeBoard_no);

	// 게시물 삭제 - 실제 삭제 발생
	public boolean removeBoard(Long freeBoard_no);

	// 게시물 삭제(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
	public int removeAllDeletedBoard();

	//게시물 총 개수 조회 서비스 - 페이징 시 필요
	public Long getRowAmountTotal(FreeBoardPagingDTO freeBoardPagingDTO);	
	
}