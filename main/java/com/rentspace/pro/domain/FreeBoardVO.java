package com.rentspace.pro.domain;


import java.util.Date;
import lombok.Data;

@Data
public class FreeBoardVO {
	
	private long freeboard_no;
	private String freeboard_title;
	private String freeboard_content;
	private String freeboard_member_id;
	private int freeboard_views_count;
	private int freeboard_reply_count;
	private int freeboard_del_flag; // 1: 삭제요청됨, 0: 유지
	private Date freeboard_register_date;
	
}
