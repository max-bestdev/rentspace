package com.rentspace.pro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rentspace.pro.common.paging.FreeBoardPagingCreatorDTO;
import com.rentspace.pro.common.paging.FreeBoardPagingDTO;
import com.rentspace.pro.domain.FreeBoardVO;
import com.rentspace.pro.service.FreeBoardService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/freeboard")

public class FreeBoardController {

	@Setter(onMethod_ = @Autowired)
	private FreeBoardService freeBoardService;

	// 빈 주입 방법1: 생성자 주입방법(권장), 단일 생성자만 존재해야 함.
	// 주의: 기본 생성자가 존재 시에 오류 발생
	// private FreeBoardService freeBoardService;
	// public FreeBoardController(FreeBoardService freeBoardService) {
	// this.freeBoardService = freeBoardService;
	// }
	// 빈 주입 방법2: @Autowired(내부적으로 setter를 이용)
	// @Autowired
	// private FreeBoardService freeBoardService;
	// 빈 주입 방법3: setter 주입방법: lombok에서의 @Setter(onMethod_ = @Autowired) 이용하는 방법과 동일
	// private FreeBoardService freeBoardService;
	//
	// @Autowired
	// public void setFreeBoardService(FreeBoardService freeBoardService) {
	// this.freeBoardService = freeBoardService;
	// }

	// 게시물 목록 조회 2 - 페이징 고려

	@GetMapping("/list")
	public void showBoardList(FreeBoardPagingDTO freeBoardPagingDTO, Model model) {
		log.info("컨트롤러 - 게시물 목록 조회 시작.....");
		log.info("컨트롤러에 전달된 사용자의 페이징처리 데이터: " + freeBoardPagingDTO);
		model.addAttribute("boardList", freeBoardService.getBoardList(freeBoardPagingDTO));
		Long rowAmountTotal = freeBoardService.getRowAmountTotal(freeBoardPagingDTO);
		log.info("컨트롤러에 전달된 게시물 총 개수: " + rowAmountTotal);
		FreeBoardPagingCreatorDTO freeBoardPagingCreatorDTO = new FreeBoardPagingCreatorDTO(rowAmountTotal,
				freeBoardPagingDTO);
		log.info("컨트롤러에서 생성된 FreeBoardCreaingPagingDTO 객체 정보: " + freeBoardPagingCreatorDTO.toString());
		model.addAttribute("pagingCreator", freeBoardPagingCreatorDTO);
		// model.addAttribute("freeBoardCreatingPagingDTO", new
		// FreeBoardCreaingPagingDTO(rowAmountTotal,freeBoardPagingDTO));
		log.info("컨트롤러 - 게시물 목록(페이징고려) 조회 완료.....");
	}

	// 게시물 등록 - 페이지 호출
	@GetMapping("/register")
	public void showBoardRegisterPage() {
		log.info("컨트롤러 - 게시물 등록 페이지 호출");
	}

	// 게시물 등록 - 처리
	@PostMapping("/register")
	public String registerNewBoard(FreeBoardVO freeBoard, RedirectAttributes redirectAttr) {
		log.info("컨트롤러 - 게시물 등록: " + freeBoard);
		long freeboard_no = freeBoardService.registerBoard(freeBoard);
		log.info("등록된 개시물의 freeboard_no: " + freeboard_no);
		redirectAttr.addFlashAttribute("result", freeboard_no);
		return "redirect:/freeboard/list";
	}

//	// 게시물 조회 페이지 호출
//	@GetMapping("/detail")
//	public void showBoardDetail(@RequestParam("freeboard_no") Long freeboard_no, Model model) {
//		log.info("컨트롤러 - 게시물 조회 페이지 호출: " + freeboard_no);
//		model.addAttribute("board", freeBoardService.getBoard(freeboard_no));
//		log.info("컨트롤러 - 화면으로 전달할 model: " + model);
//	}

	// 게시물 조회-수정 페이지 호출
	@GetMapping({ "/detail", "/modify" })
	public void showBoardDetail(@RequestParam("freeboard_no") Long freeboard_no,
			@ModelAttribute("freeBoardPagingDTO") FreeBoardPagingDTO freeBoardPagingDTO, Model model) {
		log.info("컨트롤러 - 게시물 조회/수정 페이지 호출: " + freeboard_no);
		log.info("컨트롤러 - 전달된 FreeBoardPagingDTO: " + freeBoardPagingDTO);
		model.addAttribute("board", freeBoardService.getBoard(freeboard_no));
		log.info("컨트롤러 - 화면으로 전달할 model: " + model);
	}

	// 게시물 수정 처리
	@PostMapping("/modify")
	public String modifyBoard(FreeBoardVO freeBoard, FreeBoardPagingDTO freeBoardPagingDTO, // 전달된 페이징 값들을 받음
			RedirectAttributes redirectAttr) { // 전달할 페이징 값을 저장하는 객체
		log.info("컨트롤러 - 게시물 수정 전달된 freeBoard 값: " + freeBoard);
		log.info("컨트롤러 - 전달된 FreeBoardPagingDTO: " + freeBoardPagingDTO);
		if (freeBoardService.modifyBoard(freeBoard)) {
			redirectAttr.addFlashAttribute("result", "success");
		}
		redirectAttr.addAttribute("freeboard_no", freeBoard.getFreeboard_no());
		redirectAttr.addAttribute("pageNum", freeBoardPagingDTO.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", freeBoardPagingDTO.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", freeBoardPagingDTO.getScope());
		redirectAttr.addAttribute("keyword", freeBoardPagingDTO.getKeyword());
		return "redirect:/freeboard/detail";
	}

	// 게시물 삭제 - 실제 삭제는 안됨
	@PostMapping("/delete")
	public String setBoardDeleted(@RequestParam("freeboard_no") Long freeboard_no,
			FreeBoardPagingDTO freeBoardPagingDTO, // 전달된 페이징 값들을 받음
			RedirectAttributes redirectAttr) { // 전달할 페이징 값을 저장하는 객체
		log.info("컨트롤러 - 게시물 삭제(bdelFlag값변경 글번호): " + freeboard_no);
		log.info("컨트롤러 - 전달된 FreeBoardPagingDTO: " + freeBoardPagingDTO);
		if (freeBoardService.setBoardDeleted(freeboard_no)) {
			redirectAttr.addFlashAttribute("result", "success");
		}
		redirectAttr.addAttribute("pageNum", freeBoardPagingDTO.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", freeBoardPagingDTO.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", freeBoardPagingDTO.getScope());
		redirectAttr.addAttribute("keyword", freeBoardPagingDTO.getKeyword());
		return "redirect:/freeboard/list";
	}

	// 게시물 삭제 - 실제 삭제 발생
	@PostMapping("/remove")
	public String removeBoard(@RequestParam("freeboard_no") Long freeboard_no, FreeBoardPagingDTO freeBoardPagingDTO, // 받음
			RedirectAttributes redirectAttr) { // 전달할 페이징 값을 저장하는 객체
		log.info("컨트롤러 - 게시물 삭제: 삭제되는 글번호: " + freeboard_no);
		log.info("컨트롤러 - 전달된 FreeBoardPagingDTO: " + freeBoardPagingDTO);
		if (freeBoardService.removeBoard(freeboard_no)) {
			redirectAttr.addFlashAttribute("result", "success");
		}
		redirectAttr.addAttribute("pageNum", freeBoardPagingDTO.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", freeBoardPagingDTO.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", freeBoardPagingDTO.getScope());
		redirectAttr.addAttribute("keyword", freeBoardPagingDTO.getKeyword());
		return "redirect:/freeboard/list";
	}

	// 게시물 삭제 - 삭제 요청된 모든 게시물 삭제 - By 관리자: 실제 삭제 발생
	@PostMapping("/removeAll")
	public String removeAllDeletedBoard(FreeBoardPagingDTO freeBoardPagingDTO, // 전달된 페이징 값들을 받음
			RedirectAttributes redirectAttr) {// 전달할 페이징 값을 저장하는 객체
		log.info("컨트롤러 - 전달된 FreeBoardPagingDTO: " + freeBoardPagingDTO);
		// 삭제된 행수를 removedRowCnt 이름으로 반환
		redirectAttr.addFlashAttribute("removedRowCnt", freeBoardService.removeAllDeletedBoard());
		log.info("관리자에 의해 삭제된 총 행수: " + redirectAttr.getAttribute("removedRowCnt"));
		redirectAttr.addAttribute("pageNum", freeBoardPagingDTO.getPageNum());
		redirectAttr.addAttribute("rowAmountPerPage", freeBoardPagingDTO.getRowAmountPerPage());
		redirectAttr.addAttribute("scope", freeBoardPagingDTO.getScope());
		redirectAttr.addAttribute("keyword", freeBoardPagingDTO.getKeyword());
		return "redirect:/freeboard/list";
	}
}
