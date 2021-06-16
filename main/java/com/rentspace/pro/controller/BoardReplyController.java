package com.rentspace.pro.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rentspace.pro.common.paging.BoardReplyPagingCreatorDTO;
import com.rentspace.pro.common.paging.BoardReplyPagingDTO;
import com.rentspace.pro.domain.BoardReplyVO;
import com.rentspace.pro.service.BoardReplyService;

import lombok.extern.log4j.Log4j;
@RequestMapping(value= {"/replies"})
@RestController //컨트롤러 클래스 내에 메소드들은 JSP 파일을 호출할 수 없습니다.(이유: 데이터만 전달해 주므로)
@Log4j
//@AllArgsConstructor


public class BoardReplyController {
	// 생성자 자동 주입
	private BoardReplyService boardReplyService;

	public BoardReplyController(BoardReplyService boardReplyService) {
	this.boardReplyService = boardReplyService;
	}


	// 게시물에 대한 댓글 목록 조회(페이징 2-전체댓글 수 고려)
	@GetMapping(value = "/pages/{freeboard_no}/{page}", produces = { "application/json; charset=UTF-8",
			"application/xml; charset=UTF-8" })
	public ResponseEntity<BoardReplyPagingCreatorDTO> showReplyList(@PathVariable("freeboard_no") Long freeboard_no,
			@PathVariable("page") Integer pageNum) {
		log.info("댓글-컨트롤러 - 댓글목록 표시-URI 추출 pageNum: " + pageNum);
		log.info("댓글-컨트롤러 - 댓글목록 표시-URI 추출 freeboard_no: " + freeboard_no);
		BoardReplyPagingDTO boardReplyPaging = new BoardReplyPagingDTO(freeboard_no, pageNum);
		log.info("댓글-컨트롤러 - 댓글목록 표시 - 생성된 BoardReplyPagingDTO: " + boardReplyPaging);
		BoardReplyPagingCreatorDTO replyPagingCreator = boardReplyService.getReplyListByFreeboard_no(boardReplyPaging);
		ResponseEntity<BoardReplyPagingCreatorDTO> responseEntity = new ResponseEntity<>(replyPagingCreator,
				HttpStatus.OK);
		log.info("댓글-컨트롤러 - 댓글목록 표시 - 브라우저로 전달되는 ResponseEntity<>: " + responseEntity);
		return responseEntity;
		// return new ResponseEntity<>(replyPagingCreator, HttpStatus.OK);
	}

	// 브라우저에서 http://localhost:8080/replies/pages/4849721/1 <--XML 형식으로 표시됨
	// 브라우저에서 http://localhost:8080/replies/pages/4849721/1.json <--JSON 형식으로 표시됨
	// 게시물에 대한 댓글 등록: reply_no 반환
	// @PreAuthorize("isAuthenticated()")
	@PostMapping(value = "/{freeboard_no}/new",
			// 웹브라우저로부터 받아서 메소드가 사용하는 값의 MIME 유형을 지정
			// consumes = {"application/json; charset=UTF-8"},
			// 이 메소드가 웹브라우저로 전달할 데이터의 MIME-유형을 지정
			produces = { "text/plain; charset=UTF-8" }) // { MediaType.TEXT_PLAIN_VALUE }
	public ResponseEntity<String> registerReplyForBoard(@PathVariable("freeboard_no") Long freeboard_no, @RequestBody BoardReplyVO boardReply) {
		log.info("댓글-컨트롤러-게시물에 대한 댓글 등록-URI 추출 freeboard_no: " + freeboard_no);
		log.info("댓글-컨트롤러-게시물에 대한 댓글 등록-boardReply.getFreeboard_no: " + boardReply.getFreeboard_no());
		log.info("댓글-컨트롤러-게시물에 대한 댓글 등록-서비스로 전달되는 BoardReplyVO: " + boardReply);
		// boardReply.setFreeboard_no(freeboard_no);
		Long registerdReply_no = boardReplyService.registerReplyForBoard(boardReply);
		log.info("댓글-컨트롤러-게시물에 대한 댓글 등록-반환된 reply_no(registerdReply_no): " + registerdReply_no);
		log.info("댓글-컨트롤러-게시물에 대한 댓글 등록-boardReply.getReply_no: " + boardReply.getReply_no());
		// 반환값을 삼항연산자로 처리
		return registerdReply_no != null ? new ResponseEntity<>("게시물의 댓글 등록 성공", HttpStatus.OK)
				: new ResponseEntity<>("게시물의 댓글 등록 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}



	// 게시물에 대한 특정 댓글 조회
	@GetMapping(value = "/{freeboard_no}/{reply_no}", produces = { "application/json; charset=UTF-8",
			"application/xml; charset=UTF-8" })
	public ResponseEntity<BoardReplyVO> showReply(@PathVariable("freeboard_no") Long freeboard_no, @PathVariable("reply_no") Long reply_no) {
		log.info("댓글-컨트롤러 - 댓글 조회- URI 추출 freeboard_no: " + freeboard_no);
		log.info("댓글-컨트롤러 - 댓글 조회- URI 추출 reply_no: " + reply_no);
		BoardReplyVO boardReply = boardReplyService.getReply(freeboard_no, reply_no);
		log.info("댓글-컨트롤러 댓글 조회 - 브라우저로 전달되는 BoardReplyVO: " + boardReply);
		return new ResponseEntity<>(boardReply, HttpStatus.OK);
		// return new ResponseEntity<>(boardReplyService.getReply(freeboard_no, reply_no),
		// HttpStatus.OK);
	}

	// HTTP, PUT: 리소스의 모든 것을 업데이트 한다, PATCH : 리소스의 일부를 업데이트 한다.
	// 따라서, PUT 은 누락된 값 --> null 로 처리 -> db 에서 DEFAULT 등으로 처리해서 차이가 거의 없다.
	// 게시물에 대한 특정 댓글 수정
	// @PreAuthorize("principal.username == #boardReply.rwriter")
	@RequestMapping(value = "/{freeboard_no}/{reply_no}", method = { RequestMethod.PUT,
			RequestMethod.PATCH }, consumes = "application/json; charset=UTF-8", produces = "text/plain; charset=UTF-8")
	public ResponseEntity<String> modifyReply(@PathVariable("freeboard_no") Long freeboard_no, @PathVariable("reply_no") Long reply_no,
			@RequestBody BoardReplyVO boardReply) {
		log.info("댓글-컨트롤러 - 댓글 조회-URI 추출 freeboard_no: " + freeboard_no);
		log.info("댓글-컨트롤러 - 댓글 조회-URI 추출 reply_no: " + reply_no);
		log.info("댓글-컨트롤러 - 게시물에 대한 댓글 수정-전달된 BoardReplyVO: " + boardReply);
		// put 의 경우
		boardReply.setFreeboard_no(freeboard_no);
		boardReply.setReply_no(reply_no);
		int modCnt = boardReplyService.modifyReply(boardReply);
		log.info("댓글-컨트롤러 댓글 수정 - 수정된 댓글 수: " + modCnt);
		return modCnt == 1 ? new ResponseEntity<>("댓글 수정 성공", HttpStatus.OK)
				: new ResponseEntity<>("댓글 수정 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 게시물에 대한 특정 댓글 삭제
	// @PreAuthorize("principal.username == #boardReply.rwriter")
	@DeleteMapping(value = "/{freeboard_no}/{reply_no}", produces = { "text/plain; charset=UTF-8" })
	public ResponseEntity<String> removeReply(@PathVariable("freeboard_no") Long freeboard_no, @PathVariable("reply_no") Long reply_no) {
		log.info("댓글-컨트롤러 - 댓글 삭제-URI 추출 freeboard_no: " + freeboard_no);
		log.info("댓글-컨트롤러 - 댓글 삭제-URI 추출 reply_no: " + reply_no);
		int delCnt = boardReplyService.removeReply(freeboard_no, reply_no);
		log.info("댓글-컨트롤러 - 댓글 삭제-삭제된 댓글 수: " + delCnt);
		return delCnt == 1 ? new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK)
				: new ResponseEntity<>("댓글 삭제 실패", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}