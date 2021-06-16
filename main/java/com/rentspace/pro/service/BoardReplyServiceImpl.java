package com.rentspace.pro.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rentspace.pro.common.paging.BoardReplyPagingCreatorDTO;
import com.rentspace.pro.common.paging.BoardReplyPagingDTO;
import com.rentspace.pro.domain.BoardReplyVO;
import com.rentspace.pro.mapper.BoardReplyMapper;
import com.rentspace.pro.mapper.FreeBoardMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
//@AllArgsConstructor

public class BoardReplyServiceImpl implements BoardReplyService {

	// 자동주입 방법1: 생성자 자동 주입 방식으로 주입 시
	private BoardReplyMapper boardReplyMapper;
	private FreeBoardMapper freeBoardMapper;

	public BoardReplyServiceImpl(BoardReplyMapper boardReplyMapper, FreeBoardMapper freeBoardMapper ) {
		this.boardReplyMapper = boardReplyMapper;
		this.freeBoardMapper = freeBoardMapper;
		}	
	
	// 특정 게시물에 대한 댓글 목록 조회(페이징2-전체댓글 수 고려)

	public BoardReplyPagingCreatorDTO getReplyListByFreeboard_no(BoardReplyPagingDTO boardReplyPaging) {
		log.info("댓글-서비스-게시물에 대한 댓글 목록 조회 - 전달된 BoardReplyPagingDTO: " + boardReplyPaging);
		int replyTotalByFreeboard_no = boardReplyMapper.selectReplyTotalByFreeboard_no(boardReplyPaging);

		Integer pageNum = boardReplyPaging.getPageNum();
		if (replyTotalByFreeboard_no == 0) {
			boardReplyPaging.setPageNum(1);
			log.info("댓글-서비스- 댓글이 없는 경우, pageNum은 1: 수정된 boardReplyPaging: " + boardReplyPaging);
			BoardReplyPagingCreatorDTO boardReplyPagingCreator = new BoardReplyPagingCreatorDTO(
					replyTotalByFreeboard_no, boardReplyPaging, null);
			return boardReplyPagingCreator;
		} else {
			if (pageNum == -1) {
				pageNum = (int) Math
						.ceil(replyTotalByFreeboard_no / (boardReplyPaging.getRowAmountPerPage() * 1.0));
				boardReplyPaging.setPageNum(pageNum);
				log.info("댓글-서비스-댓글추가 후, 마지막 댓글 페이지로 이동(boardReplyPaging): " + boardReplyPaging);
			}

			BoardReplyPagingCreatorDTO boardReplyPagingCreator = new BoardReplyPagingCreatorDTO(
					boardReplyMapper.selectReplyTotalByFreeboard_no(boardReplyPaging), boardReplyPaging,
					boardReplyMapper.selectBoardReplyList(boardReplyPaging));
			log.info("댓글-서비스-게시물에 대한 댓글 목록 조회 - 생성된 boardReplyPagingCreatorDTO: " + boardReplyPagingCreator);
			log.info("댓글-서비스-게시물에 대한 댓글 목록 조회 - boardReplyPagingCreator가 컨트롤러로 전달됨");
			return boardReplyPagingCreator;
		}
	}
	// 특정 게시물의 댓글 총 개수확인

	public long getReplyTotalByFreeboard_no(BoardReplyPagingDTO boardReplyPaging) {
		return boardReplyMapper.selectReplyTotalByFreeboard_no(boardReplyPaging);
	}

	// 특정 게시물에 대한 댓글 등록: reply_no 반환

	public long registerReplyForBoard(BoardReplyVO boardReply) {
		log.info("댓글-서비스-게시물에 대한 댓글 등록 시 처음 전달된 boardReplyVO: " + boardReply);
		// 게시물 댓글 수 증가
		freeBoardMapper.updateBReplyCnt(boardReply.getFreeboard_no(), 1);
		// 게시물에 대한 댓글 등록 처리
		boardReplyMapper.insertBoardReplyForBoard(boardReply);
		log.info("댓글-서비스 - 게시물에 대한 댓글 등록: 등록된 boardReply: " + boardReply);
		log.info("댓글-서비스 - 게시물에 대한 댓글 등록: 등록된 reply_no(byGetter): " + boardReply.getReply_no());
		log.info("댓글-서비스 - 게시물에 대한 댓글 등록: 등록된 reply_no가 컨트롤러로 전달됨 ");
		return boardReply.getReply_no();
	}

	// 특정 게시물에 대한 댓글의 댓글 등록: reply_no 반환
	@Transactional

	public long registerReplyForReply(BoardReplyVO boardReply) {
		log.info("댓글-서비스-게시물에 대한 댓글 등록 시 boardReplyVO: " + boardReply);
		// 게시물 댓글 수 증가
		freeBoardMapper.updateBReplyCnt(boardReply.getFreeboard_no(), 1);
		// 게시물에 대한 댓글 등록 처리
		boardReplyMapper.insertBoardReplyForBoard(boardReply);
		// 게시물의 댓글에 대한 댓글 등록 처리
		boardReplyMapper.insertBoardReplyForReply(boardReply);
		log.info("댓글-서비스 - 댓글에 대한 댓글 등록: 등록된 boardReply: " + boardReply);
		log.info("댓글-서비스 - 댓글에 대한 댓글 등록: 등록된 reply_no(ByGetter): " + boardReply.getReply_no());
		log.info("댓글-서비스 - 댓글에 대한 댓글 등록: 등록된 reply_no가 컨트롤러로 전달됨 ");
		return boardReply.getReply_no();
	}

	// 특정 게시물에 대한 특정 댓글 조회

	public BoardReplyVO getReply(long freeboard_no, long reply_no) {
		log.info("댓글-서비스-댓글-조회 시 전달된 freeboard_no: " + freeboard_no);
		log.info("댓글-서비스-댓글-조회 시 전달된 reply_no: " + reply_no);
		BoardReplyVO boardReply = boardReplyMapper.selectBoardReply(freeboard_no, reply_no);
		log.info("댓글-서비스-댓글-조회: 매퍼로부터 전달된 boardReply: " + boardReply);
		log.info("댓글-서비스-댓글-조회: boardReply가 컨트롤러로 전달됨");
		return boardReply;
	}

	// 특정 게시물에 대한 특정 댓글 수정: 수정된 행수(1) 반환

	public int modifyReply(BoardReplyVO boardReply) {
		log.info("댓글-서비스-댓글-수정시 전달된 boardReplyVO: " + boardReply);
		Integer modCnt = boardReplyMapper.updateBoardReply(boardReply);
		log.info("댓글-서비스-수정된 댓글 개수: " + modCnt);
		return modCnt;
	}

	// 특정 게시물에 대한 특정 댓글 삭제: 삭제된 행수(1) 반환
	@Transactional

	public int removeReply(long freeboard_no, long reply_no) {
		log.info("댓글-서비스-댓글-삭제시 전달된 freeboard_no: " + freeboard_no);
		log.info("댓글-서비스-댓글-삭제시 전달된 reply_no: " + reply_no);
		// 댓글 삭제: 댓글 개수 감소
		freeBoardMapper.updateBReplyCnt(freeboard_no, -1);
		Integer delCnt = boardReplyMapper.deleteBoardReply(freeboard_no, reply_no);
		log.info("댓글-서비스-삭제된 댓글 개수: " + delCnt);
		return delCnt;
	}
}