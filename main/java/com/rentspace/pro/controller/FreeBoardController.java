package com.rentspace.pro.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	// 게시물 목록 조회 1
	@GetMapping("/list")
	public void showBoardList(Model model) {
		log.info("컨트롤러 - 게시물 목록 조회.....");
		model.addAttribute("boardList", freeBoardService.getBoardList());
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

	// //게시물 조회-수정 페이지 호출
	// @GetMapping({"/detail","/modify"})
	// public void showBoardDetail(@RequestParam("freeboard_no") Long freeboard_no, Model model) {
	// log.info("컨트롤러 - 게시물 조회/수정 페이지 호출: "+ freeboard_no);
	// model.addAttribute("board", freeBoardService.getBoard(freeboard_no));
	// log.info("컨트롤러 - 화면으로 전달할 model: "+ model);
	// }
	// 게시물 조회 페이지 호출
	@GetMapping("/detail")
	public void showBoardDetail(@RequestParam("freeboard_no") Long freeboard_no, Model model) {
		log.info("컨트롤러 - 게시물 조회 페이지 호출: " + freeboard_no);
		model.addAttribute("board", freeBoardService.getBoard(freeboard_no));
		log.info("컨트롤러 - 화면으로 전달할 model: " + model);
	}

	// 게시물 수정 페이지 호출
	@GetMapping("/modify")
	public void showBoardModify(@RequestParam("freeboard_no") Long freeboard_no, Model model) {
		log.info("컨트롤러 - 게시물 수정 페이지 호출: " + freeboard_no);
		model.addAttribute("board", freeBoardService.getBoard(freeboard_no));
		log.info("컨트롤러 - 화면으로 전달할 model: " + model);
	}

	// 게시물 수정 처리
	@PostMapping("/modify")
	public String modifyBoard(FreeBoardVO freeBoard) {
		log.info("컨트롤러 - 게시물 수정 전달된 freeBoard 값: " + freeBoard);
		log.info("컨트롤러 - 게시물 수정 처리 결과(boolean): " + freeBoardService.modifyBoard(freeBoard));
		return "redirect:/freeboard/detail?freeboard_no=" + freeBoard.getFreeboard_no();
	}

	// 게시물 삭제 - 실제 삭제는 안됨
	@PostMapping("/delete")
	public String setBoardDeleted(@RequestParam("freeboard_no") Long freeboard_no) {
		log.info("컨트롤러 - 게시물 삭제(bdelFlag값변경 글번호): " + freeboard_no);
		freeBoardService.setBoardDeleted(freeboard_no);
		return "redirect:/freeboard/list";
	}

	// 게시물 삭제 - 실제 삭제 발생
	@PostMapping("/remove")
	public String removeBoard(@RequestParam("freeboard_no") Long freeboard_no, RedirectAttributes redirectAttr) {
		log.info("컨트롤러 - 게시물 삭제: 삭제되는 글번호: " + freeboard_no);
		if (freeBoardService.removeBoard(freeboard_no)) {
			redirectAttr.addFlashAttribute("result", "success");
		}
		return "redirect:/freeboard/list";
	}

	// 게시물 삭제 - 삭제 요청된 모든 게시물 삭제 - By 관리자: 실제 삭제 발생
	@PostMapping("/removeAll")
	public String removeAllDeletedBoard(Model model, RedirectAttributes redirectAttr) {
		model.addAttribute("removedRowCnt", freeBoardService.removeAllDeletedBoard());
		log.info("관리자에 의해 삭제된 총 행수: " + model.getAttribute("removedRowCnt"));
		redirectAttr.addFlashAttribute("result", "success");
		return "redirect:/freeboard/list";
	}
}
