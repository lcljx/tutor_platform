$(function(){
	$('#login').dialog({
		title : '登录后台',
		width : 470,
		height : 300,
		modal : true,
		buttons : '#btn',
	});
	
	//账户验证
	$('#admin').validatebox({
		required : true,
		missingMessage : '请输入管理员账号',
		validType : 'length[5,30]',
		invalidMessage : '账号不合规范',
	});
	
	//密码验证
	$('#password').validatebox({
		required : true,
		missingMessage : '请输入管理员账号',
		validType : 'length[6,30]',
		invalidMessage : '账号不合规范',
	});
	
	//提交时验证
	$('#btn a').click(function(){
		var data = {
				"userName" : $('#admin').val(),
				"pass" : $('#password').val()
				};
		if(!$('#admin').validatebox('isValid')){
			$('#admin').focus();
		}else if(!$('#password').validatebox('isValid')){
			$('#password').focus();
		}else{
		$.ajax({
			url : '../user/login',
			type : 'POST',
			dataType : 'text',
			data : data,
			beforeSend : function(){
				$.messager.progress({
					text : '正在登录中',
					interval:'600'
				});
			},
			success : function(result){
				alert(result);
				if(result=="登录成功"){
					location.href="../manage/main.html"
				}else{
						$.messager.progress('close');
				}
			},
			error : function(result){
				alert(result);
			}
			
		});
	}
	});
	
	//加载时验证
	if(!$('#admin').validatebox('isValid')){
		$('#admin').focus();
	}else if(!$('#password').validatebox('isValid')){
		$('#password').focus();
	}
});