package com.rentspace.pro.service;


import com.rentspace.pro.common.paging.BoardReplyPagingCreatorDTO;
import com.rentspace.pro.common.paging.BoardReplyPagingDTO;
import com.rentspace.pro.domain.BoardReplyVO;

public interface BoardReplyService {

	// 특정 게시물에 대한 댓글 목록 조회(페이징1)
	//public List<BoardReplyVO> getReplyListByFreeboard_no(BoardReplyPagingDTO boardReplyPaging);
	// 특정 게시물에 대한 댓글 목록 조회(페이징2-수정)
	public BoardReplyPagingCreatorDTO getReplyListByFreeboard_no(BoardReplyPagingDTO boardReplyPaging);

	// 특정 게시물의 댓글 총 개수확인
	public long getReplyTotalByFreeboard_no(BoardReplyPagingDTO boardReplyPaging);

	// 특정 게시물에 대한 댓글 등록: reply_no 반환
	public long registerReplyForBoard(BoardReplyVO boardReply);

	// 댓글의 답글 등록: reply_no 반환
	public long registerReplyForReply(BoardReplyVO boardReply);

	// 특정 게시물에 대한 특정 댓글/답글 조회
	public BoardReplyVO getReply(long freeboard_no, long reply_no);

	// 특정 게시물에 대한 특정 댓글/답글 수정
	public int modifyReply(BoardReplyVO boardReply);

	// 특정 게시물에 대한 특정 댓글/답글 삭제
	public int removeReply(long freeboard_no, long reply_no);
}