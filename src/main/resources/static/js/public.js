var currentUser;
//這個是登錄后的操作
$.ajax({
	url : '../user/currentUser',
	type : 'POST',
	dataType : 'json',
	data : "currentUser",
	success : function(result){
		currentUser=result;
		$(".login-content").append("<a href='myCourse.html'>我的课程</a>");
		$(".login-content").append("<a href='createCourse.html'>发布课程</a>");
		$(".login-content").children().eq(0).text("我的收藏");
		$(".login-content").children().eq(0).attr("href","../shopcart.html");
		$(".login-content").children().eq(1).text("注销");
		$(".login-content").children().eq(1).attr("href","/user/logout?flag=front");
	},
	error : function(){
		alert("尚未登录");
		window.location.href='login.html';
	}
});