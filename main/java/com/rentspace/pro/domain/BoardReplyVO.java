package com.rentspace.pro.domain;


import java.sql.Timestamp;

import lombok.Data;
@Data
public class BoardReplyVO {

	private long freeboard_no;
	private long reply_no;
	private String reply_content;
	private String reply_writer;
	private Timestamp reply_register_date;
	private long frno;

	private int lvl; //계층쿼리의 level 값을 저장할 필드
	
}