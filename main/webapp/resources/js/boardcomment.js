/**
 *  mycomment.js: 댓글/답글 데이터 처리용 Ajax Closure Module */

var boardCommentClsr = (function() {

	//댓글 목록(페이징) - ajax() 함수 사용
	function getCmtList(pagingParams, callback, error) {

		var freeboard_no = pagingParams.freeboard_no;
		var page = pagingParams.page || 1;

		console.log("getCmtList()가 전달 받은 freeboard_no: " + freeboard_no);
		console.log("getCmtList()가 전달 받은 page: " + page);
		console.log("getCmtList()함수의 ajax 처리 시작 ....");

		//- 컨트롤로의 매핑 URL 목록
		//게시물에 대한 댓글 목록 조회        : GET  /replies/pages/{freeboard_no}/{page}
		//게시물에 대한 댓글 등록(rno 반환)   : POST /replies/{freeboard_no}/new
		//댓글에 대한 답글 등록(rno 반환)     : POST /replies/{freeboard_no}/{reply_freeboard_no}/new
		//게시물에 대한 특정 댓글 조회         : GET            /replies/{freeboard_no}/{reply_no}
		//게시물에 대한 특정 댓글 수정         : POST 또는 PATCH /replies/{freeboard_no}/{reply_no}
		//게시물에 대한 특정 댓글 삭제         : DELETE         /replies/{freeboard_no}/{reply_no}


		$.ajax({
			type: 'get',
			url: "/ex00/replies/pages/" + freeboard_no + "/" + page,
			dataType: 'json',
			suscess: function(ReplyPagingCreator, status, xhr) {
				if (callback) {
					callback(ReplyPagingCreator);
				}
			},
			error: function(xhr, status, er) {
				if (error) {
					error(er);
				}
			}
		}
		);

	}


	//댓글 등록 
	/*   function registerCmt(comment, callback, error){
		  
		  var freeboard_no = comment.freeboard_no;
		  
		  console.log("registerCmt() 전달받은 freeboard_no : " + freeboard_no);
		  console.log("registerCmt()함수의 comment 등록 ajax 처리 시작....");
		  
		  //data: 브라우저가 서버에게 보내는 데이터
			//contentType: 서버에게 보내는 데이터의 MIME타입 설정, 컨트롤러의 consumes 속성에 대응
		  $.ajax( {    type: "post",
					 url: "/pro/boardcommnts/" + freeboard_no + "/new",
				   data: JSON.stringify(comment),
				   contentType : "application/json; charset=utf-8",
				   success : function(result, status, xhr) {
					  if(callback){
						 callback(result);
					  }
				   },
				   error : function(xhr, status, er) {
					  if(error){
						 error(er);
					  }
				   }
				}
		  );
				   
	   }
	   
	   
	   //답글 등록:
	   function registerReply(reply, callback, error) {
		  var freeboard_no = reply.freeboard_no;
		  var pcno = reply.reply_freeboard_no;
		  
		  console.log("registerReply() 전달받은 freeboard_no: " + freeboard_no);
		  console.log("registerReply() 전달받은 reply_freeboard_no: " + reply_freeboard_no);
		  console.log("registerReply()함수의 댓글에 대한 답글 등록 ajax 처리 시작.....");
		  
		  $.ajax( {   type: "post",
				   url: "/pro/boardcommnts/" + freeboard_no + "/" + reply_freeboard_no + "/new",
				   contentType : "application/json; charset=utf-8",
				   success : function(result, status, xhr){
					  if(callback){
						 callback(result);
					  }
				   },
				error : function(xhr,status, er){
				   if(error){
					  error(er);
				   }
				}
			 }
		  );
	   }//registerReply 함수 END
	
/*		//JQuery Ajax의 get() 메소드 사용: url 주소로 GET 방식으로 요청, 객체로 받음
		//get( url [, data] [, success(data, textStatus, jqXHR)] [, dataType] )
		//댓글 조회
	
	   function getCmtReply(bnoAndCno, callback, error) {
		  
		  var freeboard_no = bnoAndCno.freeboard_no;
		  var cno = freeboard_noAndCno.cno;
		  
		  console.log("getCmtReply() 전달받은 freeboard_no: " + freeboard_no);
		  console.log("getCmtReply() 전달받은 rno: " + rno);
		  console.log("getCmtReply() 함수의 특정 댓글에 조회 ajax처리 시작 ....");
		  
		  $.get("/pro/boardcommnts/" + freeboard_no + "/" + rno + ".json",
				function(result) {
				   if(callback) {
					  callback(result);
				   }
				}
			 ).fail(function(xhr, status, err){
				if(error) {
				   error();
				}
			 }
		  );
	   }//get 함수 END*/
	
	// 	//댓글 수정
	//    function modifyCmtReply(comment, callback, error) {
		  
	// 	  var freeboard_no = comment.freeboard_no;
	// 	  var reply_no = comment.reply_no;
		  
	// 	  console.log("modifyCmtReply() 전달받은 freeboard_no: " + freeboard_no);
	// 	  console.log("modifyCmtReply() 전달받은 reply_no: " + reply_no);
	// 	  console.log("getCmtReply() 함수의 특정 댓글에 조회 ajax처리 시작 ....");
		  
	// 	  $.ajax( {   type: "put",
	// 			   url: "/pro/boardcommnts/" + freeboard_no + "/" + reply_no,
	// 			   data: JSON.stringify(comment),
	// 			   contentType: "application/json; charset=utf-8",
	// 			   dataType : "text",
	// 			   success : function(modifyResult, status, xhr){
	// 				  if(callback) {
	// 					 callback(modifyResult);
	// 					 }
	// 				  },
	// 			   error: function(xhr, status, er) {
	// 				  if(error) {
	// 					 error(er);
	// 				  }
	// 			   }
	// 			 }
	// 			);
	//    }//update END
	
	// 	//댓글 삭제(cdelFlg만 1로 수정) 
	//    function setCmtReplyDeleted(comment, callback, error) {
		  
	// 	  var freeboard_no = comment.freeboard_no;
	// 	  var reply_no = comment.reply_no;
	// 	  var reply_member_id = comment.reply_member_id
		  
	// 	  console.log("modifyCmtReply() 전달받은 freeboard_no: " + freeboard_no);
	// 	  console.log("modifyCmtReply() 전달받은 reply_no: " + reply_no);
	// 	  console.log("getCmtReply() 함수의 특정 댓글에 조회 ajax처리 시작 ....");
		  
	// 	  $.ajax(   {   type : "patch",
	// 			   url : "/pro/boardcommnts/" + freeboard_no + "/" + reply_no,
	// 			   contentType : "application/json; charset=utf-8",
	// 			   success : function(modifyResult, status, xhr) {
	// 				  if(callback) {
	// 					 callback(modifyResult);
	// 					 }
	// 				  },
	// 			   error: function(xhr, status, er) {
	// 				  if(error) {
	// 					 error(er);
	// 				  }
	// 			   }
	// 			}
	// 		 );
		  
	//    }//update END
	
	
	// 	//댓글 삭제(실제 삭제)
	//    function removeCmtReply(comment, callback, error) {
	// 	  var freeboard_no = comment.freeboard_no;
	// 	  var reply_no = comment.reply_no;
	// 	  var reply_member_id = comment.reply_member_id
		  
	// 	  console.log("removeCmtReply() 잔달 받은 freeboard_no: " + freeboard_no);
	// 	  console.log("removeCmtReply() 전달받은 reply_no: " + reply_no);
	// 		console.log("removeCmtReply() 전달받은 reply_member_id: " + reply_member_id);
	// 		console.log("removeCmtReply() 함수의 댓글 삭제 ajax 처리 시작.......");
	
	// 	  $.ajax(   {   type:"delete",
	// 			   url : "/pro/boardcommnts/" + freeboard_no + "/" + reply_no,
	// 			   data : JSON.stringify({freeboard_no:freeboard_no, reply_no:reply_no, reply_member_id:reply_member_id}),
	// 			   contentType : "application/json; charset=utf-8",
	// 			   success : function(removeResult, status, xhr) {
	// 				  if(callback) {
	// 					 callback(removeResult);
	// 					 }
	// 				  },
	// 			   error : function(xhr, status, er) {
	// 				  if(error) {
	// 					 error(er);
	// 				  }
	// 			   }
	// 			}
	// 		 );
		  
	//    }//removeCmtReply 함수 END
	
	// 	//날자시간 표시형식 설정(서버와 상관 없음)
	// 	//JSON 날짜시간을 그대로 표시하면 1594169682000 이렇게 표시됩니다.
	// 	//일반적인 날짜 시간 표시 형식으로 표시, 
	//    function showDatatime(datetimeValue) {
	// 	  var dataObj = new Date(datetimeValue);
		  
	// 	  var str = "";
		  
	// 	  var yyyy = dateObj.getFullYear();
	// 	  var mm = dateObj.getMonth() + 1;
	// 	  var dd = dateObj.getDate();
		  
	// 	  var hh = dateObj.getHours();
	// 	  var mi = dateObj.getMinutes();
	// 	  var ss = dateObj.getSeconds();
		  
	// 	  str= [yyyy, '/', 
	// 			 (mm > 9 ? '' : '0') + mm, '/',
	// 			 (dd > 9 ? '' : '0') + dd, ' ',
	// 			 (hh > 9 ? '' : '0') + hh, ':', 
	// 			 (mi > 9 ? '' : '0') + mi,   ':', 
	// 			 (ss > 9 ? '' : '0') + ss].join(''); //배열값으로 하나의 문자열로 합침
	
	// 		return str ;
	//    }*/

	// return {
	// 	getCmtList: getCmtList,   //댓글 목록 
	// 	registerCmt: registerCmt,   //댓글 등록 
	// 	registerReply: registerReply,   //답글 등록
	// 	getCmtReply: getCmtReply,   //댓글-답글 조회
	// 	//        modifyCmtReply : modifyCmtReply,   //댓글-답글 수정
	// 	//        setCmtReplyDeleted : setCmtReplyDeleted,    //댓글삭-답글 삭제(cdelFlg=1 수정)
	// 	//        removeCmtReply : removeCmtReply,    //댓글-답글 삭제(실제삭제)
	// 	//        showDatetime : showDatetime  //날짜시간 표시형식 지정
	// 	// showLastModTime : showLastModTime 
	// };

})();  //즉시 실행 함수
