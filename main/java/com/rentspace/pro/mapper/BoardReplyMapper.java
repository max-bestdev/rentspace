package com.rentspace.pro.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.rentspace.pro.domain.BoardReplyVO;

public interface BoardReplyMapper {

	// 특정 게시물에 대한 댓글 목록 조회
	public List<BoardReplyVO> selectBoardReplyList(@Param("reply_freeboard_no") Long reply_freeboard_no);

	// 특정 게시물에 대한 댓글 등록
	public long insertBoardReplyForBoard(BoardReplyVO boardReplyVO);

	// 특정 게시물에 대한 댓글의 댓글 등록
	public long insertBoardReplyForReply(BoardReplyVO boardReplyVO);

	// 특정 게시물에 대한 특정 댓글 조회
	public BoardReplyVO selectBoardReply(@Param("reply_freeboard_no") Long reply_freeboard_no, @Param("reply_no") Long reply_no);

	// 특정 게시물에 대한 특정 댓글 수정
	public int updateBoardReply(BoardReplyVO boardReply);

	// 특정 게시물에 대한 특정 댓글 삭제
	public int deleteBoardReply(@Param("reply_freeboard_no") Long reply_freeboard_no, @Param("reply_no") Long reply_no);
	// 특정 게시물에 대한 모든 댓글 삭제(관리자) -- myreply.bno 컬럼의 F.K 에 ON DELETE CASCADE 를 사용하는
	// 경우, 필요 없음
	// public int deleteAllReply(@Param("reply_freeboard_no") Long reply_freeboard_no);
}
