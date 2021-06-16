package com.rentspace.pro.common.paging;



import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
@EqualsAndHashCode
public class BoardReplyPagingDTO {

	private long freeboard_no; // 게시물 번호
	private Integer pageNum; // 현재 페이지 번호
	private int rowAmountPerPage; // 페이지당 출력할 레코드 개수
	// 생성자 - 댓글의 페이징번호 클릭 시,
	// 페이지번호와 행수를 각각 사용자가 선택한 페이징번호와 10 으로 전달

	public BoardReplyPagingDTO(long freeboard_no, Integer pageNum) {
	this.freeboard_no = freeboard_no;
	if(pageNum == null) {
	this.pageNum = 1 ;
	} else {
	this.pageNum = pageNum;
	}
	this.rowAmountPerPage = 10 ;
	}
}
