//這個是登錄后的操作
$.ajax({
	url : '../user/currentUser',
	type : 'POST',
	dataType : 'json',
	data : "currentUser",
	success : function(result){
		$(".login-content").children().eq(0).text("个人中心");
		$(".login-content").children().eq(0).attr("href","https://www.baidu.com");
		$(".login-content").children().eq(1).text("注销");
		$(".login-content").children().eq(1).attr("href","/user/logout?flag=front");
	},
	error : function(){
		alert("尚未登录");
	}
});