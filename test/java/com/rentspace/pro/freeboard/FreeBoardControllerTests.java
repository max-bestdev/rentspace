package com.rentspace.pro.freeboard;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration // DispatcherServlet의 구성설정파일을 사용하여 컨트롤러를 테스트하기 위해 필요한 설정
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/mybatis-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" })
@Log4j
public class FreeBoardControllerTests {

	// 테스트 환경 구성
//컨트롤러 테스트를 위해서는 WebApplicationContext 를 객체로 주입 받아야 합니다.
	@Setter(onMethod_ = { @Autowired })
	private WebApplicationContext ctx;
	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}
//테스트 환경 구성-끝

	//게시물 목록 조회 테스트
	@Test
	public void testShowBoardList() throws Exception {
	log.info(mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/list")
	.param("pageNum", "2") //페이징 테스트 시 추가
	.param("rowAmount", "10") //페이징 테스트 시 추가
	).andReturn()
	.getModelAndView()
	.getModelMap() );
	}

//게시물 등록-페이지 호출 테스트
@Test
public void testShowBoardRegisterPage() throws Exception {
log.info(mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/register"))
.andReturn()
.getModelAndView()
.getModelMap());
}

//게시물 등록 처리 테스트
 @Test
public void testRegisterNewBoard() throws Exception {
String resultPage =
mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/register")
.param("freeboard_title", "게시물 등록 -컨트롤러 테스트 제목")
.param("freeboard_content", "게시물 등록 -컨트롤러 테스트 내용")
.param("freeboard_member_id", "test"))
.andReturn().getModelAndView().getViewName();
log.info(resultPage);
}

//게시물 수정 처리
@Test
public void testModifyBoard() throws Exception {
String resultPage = mockMvc
.perform(MockMvcRequestBuilders.post("/freeboard/modify")
.param("freeboard_no", "1")
.param("freeboard_title", "게시물 수정-컨트롤러 테스트 제목")
.param("freeboard_content", "게시물 수정-컨트롤러 테스트 내용")
.param("freeboard_member_id", "test"))
.andReturn()
.getModelAndView()
.getViewName();
log.info(resultPage);
}

//게시물 조회-수정 페이지 호출 테스트: /freeboard/detail, /freeboard/modify 2개 모두 테스트도 수행
@Test
public void testshowBoardDetail() throws Exception {
log.info(mockMvc.perform(MockMvcRequestBuilders.get("/freeboard/detail")
.param("freeboard_no", "1"))
.andReturn()
.getModelAndView()
.getModelMap());
}

//게시물 삭제 테스트- By 사용자: 실제 삭제는 안됨: freeboard_no를 "4", "5" 로 두 번 수행
@Test
public void testSetBoardDeleted() throws Exception {
String resultPage = mockMvc
.perform(MockMvcRequestBuilders.post("/freeboard/delete")
.param("freeboard_no", "5")
.param("freeboard_del_flag", "1"))
.andReturn()
.getModelAndView()
.getViewName();
log.info(resultPage);
}

//게시물 삭제 테스트 - 특정 게시물 삭제 - By 관리자: 실제 삭제 발생
@Test
public void testRemoveBoard() throws Exception {
//삭제전 데이터베이스에 게시물 번호 확인할 것
String resultPage = mockMvc
.perform(MockMvcRequestBuilders.post("/freeboard/remove")
.param("freeboard_no", "3"))
.andReturn()
.getModelAndView()
.getViewName();
log.info(resultPage);
}

//게시물 삭제 테스트 - 삭제 설정된 모든 게시물 삭제 - By 관리자: 실제 삭제 발생
@Test
public void testRemoveAllDeletedBoard() throws Exception {
String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/freeboard/removeAll"))
.andReturn()
.getModelAndView()
.getViewName();
 log.info(resultPage);
}
}