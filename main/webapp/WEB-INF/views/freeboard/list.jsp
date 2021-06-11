<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
 
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<%@ include file="../freeboardinclude/boardheader.jsp" %>

        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<h3 class="page-header">Board - List</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading"> 

                        <!-- /.panel-heading -->
                        <div class="panel-body">
                        
                        <form class="form-inline" id="frmSendValue" action="${contextPath}/freeboard/list" method="get">
                          <div class="form-group">
	                             <label class="sr-only">frmSendValues</label>
	                                   
	                             <select class="form-control" id="selectAmount" name="rowAmountPerPage">
	                              <option value="10" <c:out value="${pagingCreator.freeBoardPagingDTO.rowAmountPerPage eq '10'
	                                            ? 'selected' : ''}"/>>10개</option>
	                              <option value="20" <c:out value="${pagingCreator.freeBoardPagingDTO.rowAmountPerPage eq '20'
	                                            ? 'selected' : ''}"/>>20개</option>
	                              <option value="50" <c:out value="${pagingCreator.freeBoardPagingDTO.rowAmountPerPage eq '50'
	                                            ? 'selected' : ''}"/>>50개</option>
	                              <option value="100" <c:out value="${pagingCreator.freeBoardPagingDTO.rowAmountPerPage eq '100'
	                                            ? 'selected' : ''}"/>>100개</option>
	                             </select>
	                             
	                             <select class="form-control" id="selectScope" name="scope">
	                              <option value="" <c:out value="${pagingCreator.freeBoardPagingDTO.scope == null
	                                          ? 'selected':''}"/>>검색범위</option>
	                              <option value="T" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'T'
	                                          ? 'selected' : ''}"/>>제목</option>
	                              <option value="C" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'C'
	                                          ? 'selected' : ''}"/>>내용</option>
	                              <option value="W" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'W'
	                                          ? 'selected' : ''}"/>>작성자</option>
	                              <option value="TC" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'TC'
	                                           ? 'selected' : ''}"/>>제목 + 내용</option>
	                              <option value="TW" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'TW'
	                                           ? 'selected' : ''}"/>>제목 + 작성자</option>
	                              <option value="TCW" <c:out value="${pagingCreator.freeBoardPagingDTO.scope eq 'TCW'
	                                             ? 'selected' : ''}"/>>제목 + 내용 + 작성자</option>
	                             </select>
	                             
	                         <div class="input-group">
	                             <input class="form-control" id="inputKeyword" name="keyword" type="text"
	                              placeholder="검색어를 입력하세요" value='<c:out value="${pagingCreator.freeBoardPagingDTO.keyword}"/>' />
	                             <span class="input-group-btn">
	                               <button class="btn btn-info" type="button" id="btnSearchGo">
	                                     검색 &nbsp;<i class="fa fa-search"></i>
	                               </button>
	                             </span>
	                         </div>
	                         
	                         <div class="input-group">
	                             <button id="btnReset" class="btn btn-warning" type="reset">검색초기화</button>
	                         </div>
                           </div><%-- /.form-group --%> 
 
 <input type='hidden' name='pageNum' value='${pagingCreator.freeBoardPagingDTO.pageNum}'>   <input type='hidden' name='lastPageNum' value='${pagingCreator.lastPageNum}'>   </form>
						 <input type='hidden' name='pageNum' value='${pagingCreator.freeBoardPagingDTO.pageNum}'>
						 <input type='hidden' name='rowAmountPerPage' value='${pagingCreator.freeBoardPagingDTO.rowAmountPerPage}'>
						 <input type='hidden' name='lastPageNum' value='${pagingCreator.lastPageNum}'>
						</form> 
						
                            <table class="table table-striped table-bordered table-hover"                             
                             style="width:100%;text-align:center;"> 
                                <thead>
                                    <tr>
                                        <th style="text-align:center;">번호</th>
                                        <th style="text-align:center;">제목</th>
                                        <th style="text-align:center;">작성자</th>
                                        <th style="text-align:center;">작성일</th>
                                        <th style="text-align:center">조회수</th>
                                    </tr>
                                </thead>
                                <tbody>
 									<c:forEach items="${boardList}" var="board">
 										<c:if test="${board.freeboard_del_flag == 1}">
 											<tr style="backgroud-color:Moccasin; text-align:center">
 												<td><c:out value="${board.freeboard_no}" /></td>
 												<td colspan="5"><em>작성자에 의하여 삭제된 게시글입니다.</em></td>
 											</tr>
 										</c:if>
 										<c:if test="${board.freeboard_del_flag == 0}">
 											 <tr class="moveDetail" data-freeboard_no='<c:out value="${board.freeboard_no}"/>'> 
 												<td><c:out value="${board.freeboard_no}" /></td>
 												<td style="text-align:left;">
 												<c:out value="${board.freeboard_title}" />
                                                <small>[댓글수: <strong><c:out value="${board.freeboard_reply_count}"/></strong>]</small>
 												</td>
 												<td><c:out value="${board.freeboard_member_id}" /></td>
 												<td><fmt:formatDate pattern="yyyy/MM/dd" value="${board.freeboard_register_date}" /><br>
 													
 												</td>

 												<td>${board.freeboard_views_counnt}</td>
 											</tr>
 										</c:if>	
 									</c:forEach>
 											
                                </tbody>
                            </table>
                            <!-- /.table-responsive -->
                            
<!-- Pagination 시작 -->
 <!-- <div class='pull-right'> -->
 <div class='text-center'>
   <ul class="pagination pagination-sm">
    <!-- 페이징 버튼 클릭 시, jQuery로 페이지 번호를 전달하도록 a 태그에 전달된 pagingCreator 객체의 필드 지정  -->
       <c:if test="${pagingCreator.prev}">
            <li class="paginate_button previous">
                  <a href="1">&laquo;</a><!-- 맨 앞으로 -->
            </li>
       </c:if>
       <c:if test="${pagingCreator.prev}">
            <li class="paginate_button previous">
                  <a href="${pagingCreator.startPagingNum - 1}">이전</a>
            </li>
       </c:if> 
 
	   <c:forEach var="pageNum" begin="${pagingCreator.startPagingNum}" end="${pagingCreator.endPagingNum}">
	        <!-- 선택된 숫자의 경우, Bootstrap의 active 클래스 이름 추가  -->
	             <!--  <li class="paginate_button"> -->
	         <li class='paginate_button ${pagingCreator.freeBoardPagingDTO.pageNum == pageNum ? "active":"" }'>
	               <a href="${pageNum}">${pageNum}</a>
	         </li>
	   </c:forEach> 
	 
	  <c:if test="${pagingCreator.next}">
	      <li class="paginate_button next">
	           <a href="${pagingCreator.endPagingNum +1}">다음</a>
	      </li>
	 </c:if> 
	 
 	  <c:if test="${pagingCreator.next}">
	      <li class="paginate_button next">
	           <a href="${pagingCreator.lastPageNum}">&raquo;</a>
	      </li>
	  </c:if> 
    </ul>
 </div> <!-- Pagination 끝 -->                             
                            
<%-- START 모달 --%>
<%-- Modal --%>
<%-- <div class="modal fade" id="freeboardModal" tabindex="-1" role="dialog" 
     aria-labelledby="freeboardModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				<h4 class="modal-title" id="freeboardModalLabel">처리결과</h4>
			</div>
			<div class="modal-body">처리가 완료되었습니다.</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">확인</button>
			</div>
		</div>END .modal-content
	</div>END .modal-dialog
</div>END .modal
END 모달  --%>                           
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
         </div>
        <!-- /#page-wrapper -->
        
<%-- 상세 페이지 이동-방법2, 3에서 사용되는 form: 목록화면에서 상세 화면 호출 시, 전달할 값들이 저장됨 --%>
<!-- 목록 화면에서 페이징 화면 이동 시에도 사용됨-->        
<form id="frmSendValue">
 <input type='hidden' name='pageNum' value='${pagingCreator.freeBoardPagingDTO.pageNum}'>
 <input type='hidden' name='rowAmountPerPage' value='${pagingCreator.freeBoardPagingDTO.rowAmountPerPage}'>
 <input type='hidden' name='lastPageNum' value='${pagingCreator.lastPageNum}'>
</form> 
 
 
<script> 
 
<%--//새글 등록 버튼 클릭 이벤트 처리: 게시물 등록 화면 이동////////////////////////////////// --%> 
$("#btnMoveRegister").on("click", function(){  
	self.location = "${contextPath}/freeboard/register"; 
}) 
 
<%--//게시물 행이나 제목 클릭 이벤트 처리: 게시물 상세 화면 이동//////////////////////////////// --%> 
var frmSendValue = $("#frmSendValue"); 
 

<%--//방법3: tr 태그 클릭 시 폼을 통해 detail 화면 요청 --%> 
$(".moveDetail").on("click", function(e) { 
 
 var freeboard_no = $(this).data("freeboard_no"); <%--방법3에서 tr 태그의 freeboard_no 값을 변수에 저장 --%>    

 /* alert("클릭된 행의 freeboard_no" + freeboard_no);  */ <%--//방법2: a 링크의 값을 전송할 form에 추가 --%>  
 
 <%--//방법3: freeboard_no 변수에 저장된 값을 전송할 form에 추가 --%>  
 frmSendValue.append("<input type='hidden' name='freeboard_no' value='" + freeboard_no + "'/>"); 
 
 
 frmSendValue.attr("action", "${contextPath}/freeboard/detail");  
 
 frmSendValue.attr("method", "get"); 
 
 frmSendValue.submit(); 
 
 }); 
 
   

window.addEventListener('popstate',function(event){
	history.pushState(null,null,location.href);	//다시 push함으로 뒤로가기 block 
})

var result = '<c:out value="${result}"/>';


function checkModal(result){
	if(result === "||history.state"){
		return;
		
	} else if(result === 'successModify'){
		var myMsg = "글이 수정되었습니다";
		
	} else if(result === 'successRemove'){
		var myMsg = "글이 삭제되었습니다";
		
	} else if(parseInt(result) > 0){
		var myMsg = "게시글" + parseInt(result) + "번이 등록되었습니다.";
	}
	$(".modal-body").html(myMsg);
	$("#freeboardModal").modal("show");
	myMsg='';
}

<%--//페이징 버튼 클릭 이벤트 처리: 폼에 저장된 페이지번호를 클릭한 페이지번호로 변경한 후, 전송////--%>
//페이징 화면이동 
$(".paginate_button a").on("click", function(e) {

	e.preventDefault();   //a 태그의 동작 막음
	//alert("페이징번호 클릭함"); 

	//폼에 저장된 현재 화면의 페이지번호를 클릭한 페이징 버튼의 페이지번호로 변경
	frmSendValue.find("input[name='pageNum']").val($(this).attr("href"));
	frmSendValue.attr("action", "${contextPath}/freeboard/list");
	frmSendValue.attr("method", "get");
	
	frmSendValue.submit();
});

//표시행수 변경 이벤트 처리
$("#selectAmount").on("change", function(){
	frmSendValue.find("input[name='pageNum']").val(1);
	frmSendValue.attr("action", "${contextPath}/freeboard/list")
	;  frmSendValue.attr("method", "get");
	frmSendValue.submit();
});

//검색버튼 클릭 이벤트 처리
$("#btnSearchGo").on("click", function(e) { 

if (!$("#selectScope").find("option:selected").val()) {
	alert("검색범위를 선택하세요");
	return false;
	} 

if (!frmSendValue.find("input[name='keyword']").val()) {
	alert("검색어를 입력하세요");
	return false;
	} 

frmSendValue.find("input[name='pageNum']").val("1");
//e.preventDefault(); 

frmSendValue.submit();
});

//검색초기화 버튼 이벤트처리
$("#btnReset").on("click", function(){
	$("#selectAmount").val(10);
	$("#selectScope").val("");
	$("#inputKeyword").val("") ;
	$("#hiddenPageNum").val(1);
	$("#hiddenLastPageNum").val(""); 

frmSendValue.submit();
}); 


//모달 표시 유무 결정/모달에 표시할 내용 수정/모달 표시 실행
$(document).ready(function(){
	checkModal(result);
	
	history.pushState(null,null,location.href);
});

</script>  
  
<%@ include file="../freeboardinclude/boardfooter.jsp" %>