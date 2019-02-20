$(function(){
	var nowTime=new Date();
	var H=nowTime.getHours();
	var M=nowTime.getMinutes();
	var S=nowTime.getSeconds();
	$('.hour').css({'-webkit-transform':'rotate('+(H*30+30/60*M)+'deg)','-moz-transform':'rotate('+(H*30+30/60*M)+'deg)',
	'-o-transform': 'rotate('+(H*30+30/60*M)+'deg)','-ms-transform': 'rotate('+(H*30+30/60*M)+'deg)',transform: 'rotate('+(H*30+30/60*M)+'deg)'});
	
	$('.minute').css({'-webkit-transform':'rotate('+M*6+'deg)','-moz-transform':'rotate('+M*6+'deg)',
	'-o-transform': 'rotate('+M*6+'deg)','-ms-transform': 'rotate('+M*6+'deg)',transform: 'rotate('+M*6+'deg)'});
	
	$('.second').css({'-webkit-transform':'rotate('+S*6+'deg)','-moz-transform':'rotate('+S*6+'deg)',
	'-o-transform': 'rotate('+S*6+'deg)','-ms-transform': 'rotate('+S*6+'deg)',transform: 'rotate('+S*6+'deg)'});
});

var url;
function openTab(text,url,iconCls){
	if($("#tabs").tabs("exists",text)){
		$("#tabs").tabs("select",text);
	}else{
		var content="<iframe frameborder=0 scrolling='auto' style='width:100%;height:100%' src='../manage/"+url+".html'></iframe>";
		$("#tabs").tabs("add",{
			title:text,
			iconCls:iconCls,
			closable:true,
			content:content
		});
	}
}

function openPasswordModifyDialog(cUser){
	$("#dlg").dialog("open").dialog("setTitle","修改密码");
	$("#username").val(cUser);
	url="../user/modifyPassword";
}

function modifyPassword(){
	$("#fm").form("submit",{
		url:url,
		onSubmit:function(){
			var username = $("#username").val();
			var oldPassword=$("#oldPassword").val();
			var newPassword=$("#newPassword").val();
			var newPassword2=$("#newPassword2").val();
			if(!$(this).form("validate")){
				return false;
			}
/* 				if(oldPassword!='${currentUser.password}'){
				$.messager.alert("系统提示","用户原密码输入错误！");
				return false;
			} */
			if(newPassword!=newPassword2){
				$.messager.alert("系统提示","确认密码输入错误！");
				return false;
			}
			return true;
		},
		success:function(result){
			var result=eval('('+result+')');
			if(result==1){
				$.messager.alert("系统提示","密码修改成功，下一次登录生效！");
				resetValue();
				$("#dlg").dialog("close");
			}else{
				$.messager.alert("系统提示","密码修改失败！可能是原密码错误");
				return;
			}
		}
	 });
}

function closePasswordModifyDialog(){
	resetValue();
	$("#dlg").dialog("close");
}

function resetValue(){
	$("#oldPassword").val("");
	$("#newPassword").val("");
	$("#newPassword2").val("");
}
	
function logout(){
	$.messager.confirm("系统提示","您确定要退出系统吗？",function(r){
		if(r){
			window.location.href='../user/logout';
		} 
	 });
}