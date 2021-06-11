package com.rentspace.pro.mapper;

import java.util.List;

import com.rentspace.pro.domain.FreeBoardVO;


public interface FreeBoardMapper {
//게시물 조회 - 목록
	public List<FreeBoardVO> selectFreeBoardList();

//게시물 등록1 - selectKey 이용않함
	public Integer insertFreeBoard(FreeBoardVO freeBoard);

//게시물 등록2 - selectKey 이용
	public Integer insertFreeBoardSelectKey(FreeBoardVO freeBoard);

//게시물 조회
	public FreeBoardVO selectFreeBoard(Long freeboard_no);

//게시물 조회수 증가
	public void updateBviewsCnt(Long freeboard_no);

//게시물 삭제 - 해당 글의 bdelFlag을 1로 수정
	public int updateBdelFlag(Long freeboard_no);

//게시물 삭제 - 실제 삭제
	public int deleteFreeBoard(Long freeboard_no);

//게시물 삭제(관리자) – 사용자 삭제 요청된 게시물 전체 삭제: bdelFlag = 1
	public int deleteAllBoardSetDeleted();

//게시물 수정
	public int updateFreeBoard(FreeBoardVO freeBoard);


}
