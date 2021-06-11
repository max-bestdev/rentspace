<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>   
 
<c:set var="contextPath" value="${pageContext.request.contextPath}" /> 

<%@ include file="../freeboardinclude/boardheader.jsp" %>

<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                	<h3 class="page-header">게시글 수정</h3>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                        <h4>게시글 수정-삭제</h4></div>
                        <!-- /.panel-heading -->
                        
                        <div class="panel-body">
                        	<form id="frmModify" role="form" method="post">
                        	
		                        	<div class="form-group">
		                        		<label>글번호</label> 
		                        		<input class="form-control" value="${board.freeboard_no}" name="freeboard_no" readonly="readonly" />
		                        	</div>
		                         		
		                        	<div class="form-group">
		                        		<label>글제목</label> 
		                        		<input class="form-control" value='<c:out value="${board.freeboard_title}" />'
		                        				name="freeboard_title" />
		                            </div>
		                        		
		                       		<div class="form-group">
		                       			<label>글내용</label> 
		                       			<textarea class="form-control" rows="3" 
		                       			    name="freeboard_content"><c:out value="${board.freeboard_content}" /></textarea>
		                       		</div>
		                        		
		                       		<div class="form-group">
		                       			<label>작성자</label> 
		                       			<input class="form-control" value='<c:out value="${board.freeboard_member_id}" />'
		                       				name="freeboard_member_id" readonly="readonly" />
		                       		</div>
		                       		
		                       		<div class="form-group">
		                       			<label>최종수정일</label> [등록일시: <fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
		                       					value="${board.freeboard_register_date}"/>]
		                       			<input class="form-control" name="freeboard_register_date" 
		                       				value='<fmt:formatDate pattern="yyyy/MM/dd HH:mm:ss"
		                       					value="${board.freeboard_register_date}"/>' 
		                       				disabled="disabled" />
		                       		</div>
		                        		
		                       		<button type="button" id="btnModify" data-oper="modify"
		                       			class="btn btn-default">수정</button>
		                       		<button type="button" id="btnRemove" data-oper="remove"
		                       			class="btn btn-danger">삭제</button>
		                       		<button type="button" id="BtnMoveList" data-oper="list" 
		                       		class="btn btn-info">취소</button> 
		                       		
		                       		<input type='hidden' name='pageNum' value='${boardPagingDTO.pageNum}'>
		 							<input type='hidden' name='rowAmountPerPage' value='${boardPagingDTO.rowAmountPerPage}'>
                        			<input type='hidden' name='scope' value='${boardPagindDTO.scope}'>
                        			<input type='hidden' name='keyword' value='${boardPagingDTO.keyword}'>
                        	</form>
                        		
                        </div><!-- /.panel-body -->
                     </div><!-- /.panel -->
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <!-- /.row -->
         </div>
        <!-- /#page-wrapper -->
               
        
 <script>
 
//form의 수정/삭제/목록보기 버튼에 따른 동작 제어
var frmModify = $("#frmModify");
$('button').on("click", function(e){
	
	var operation = $(this).data("oper");
	
	alert("operation: " + operation);
	
	if(operation == "modify"){
		

		frmModify.attr("action", "${contextPath}/freeboard/modify");
	} else if(operation == "remove"){
		frmModify.attr("action", "${contextPath}/freeboard/delete");
	} else if(operation == "list"){
		
		 var pageNumInput = $("input[name='pageNum']").clone();
		 var rowAmountInput = $("input[name='rowAmountPerPage']").clone();
		 var scopeInput = $("input[name='scope']").clone();
		 var keywordInput = $("input[name='keyword']").clone();
		 
		 frmModify.empty(); //form의 모든 input을 삭제
		 
		 frmModify.attr("action","${contextPath}/freeboard/list").attr("method","get");
		 frmModify.append(pageNumInput);
		 frmModify.append(rowAmountInput);
		 frmModify.append(scopeInput);
		 frmModify.append(keywordInput);
	}
 
	
	frmModify.submit();
	
});


 </script>       
<%@ include file="../freeboardinclude/boardfooter.jsp" %>