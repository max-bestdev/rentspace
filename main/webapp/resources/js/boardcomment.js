/**
* myreply.js: 댓글/답글 데이터 처리용 Ajax Closure Module
*/



var boardCommentClsr = (function() {
	//브라우저의 상세 페이지에서 Javascript에 의해 호출 시 전달받는 매개변수 설명
	//reply_freeboard_noAndPage: 서버로 전달할 값,
	//callback: 서버 처리 성공 시 브라우저에 의해 처리되는 함수
	//error: 서버 처리 실패 시 브라우저에 의해 처리되는 함수
	//댓글 목록(페이징) - ajax() 함수 사용
	function getCmtList(pagingParams, callback, error) {
		var reply_freeboard_no = pagingParams.reply_freeboard_no;
		var page = pagingParams.page || 1;
		console.log("getCmtList()가 전달받은 reply_freeboard_no: " + reply_freeboard_no);
		console.log("getCmtList()가 전달받은 page: " + page);
		console.log("getCmtList()함수의 ajax 처리 시작....................");
		//댓글 목록 조회 컨트롤러의 매핑 URL: GET /replies/pages/{reply_freeboard_no}/{page}
		//$.ajax() 함수는, 자바스크립트 객체를 매개값으로 받아 처리
		$.ajax({
			type: 'get', //전송유형: get 방식
			url: "/pro/replies/pages/" + reply_freeboard_no + "/" + page, //컨트롤러 메소드 호출 URL
			dataType: 'json', //서버로부터 받는 값들이 JSON 형식임(서버의 produces 속성에 대응)
			//'json', 'xml' , 'text', 'html' ,'script' 값을 설정할 수 있음
			//서버처리 성공 시 실행 함수, XHR (XML Http Request)
			//result 매개변수를 통해 서버로부터 전달받은 데이터가 callback 함수에 의해 처리됨
			success: function(replyPagingCreator, status, xhr) {
				if (callback) { //callback 매개변수에 함수가 있으면 ,참
					callback(replyPagingCreator);
				}
			},
			//서버처리 실패 시, 매개변수로 전달된 함수 실행, XHR (XML Http Request)
			error: function(xhr, status, er) {
				if (error) { //error 매개변수에 함수가 있으면 ,참
					error(er);
				}
			}
		}
		); //.ajax END
	}//getCmtList END
	return {
		getCmtList: getCmtList
	};
})(); //즉시 실행 함수


//댓글 목록(페이징) - getJSON() 함수 사용
function getCmtList(reply_freeboard_noAndPage, callback, error) {
	var reply_freeboard_no = reply_freeboard_noAndPage.reply_freeboard_no;
	var page = reply_freeboard_noAndPage.page || 1;
	//$.getJSON() 함수는, 컨트롤러 메서드에 get 방식으로 요청하고, 결과를 JSON 객체를 받음
	//[형식] $.getJSON(url, [data,서버로 전달할 데이터], callback-function)
	//서버에서 오류발생 시, 처리할 함수가 지정되지 못하므로, fail() 함수로 별도로 지정해야 함
	$.getJSON("/pro/replies/pages/" + reply_freeboard_no + "/" + page,
		function(replyPagingCreator) {
			if (callback) {
				callback(replyPagingCreator);
			}
		} //callback 처리함수 END
	).fail(function(xhr, status, err) { //jQuery Ajax Chaining 메서드: fail 메서드 사용(ajax의 error를 대체)
		if (error) {
			error();
		}
	}
	);
}//getCmtList END