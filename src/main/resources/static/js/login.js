$(function () {
		$('#login').bootstrapValidator({
			message: 'This value is not valid',
			feedbackIcons:{
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields:{
				userName:{
					message: '用户名验证失败',
					validators:{
						notEmpty: {
                            message: '用户名不能为空'
                        },
                        stringLength: {/*长度提示*/
                            min: 4,
                            max: 8,
                            message: '用户名长度必须在4到8之间。'
                        },
                        regexp: {
                            regexp: /^[a-zA-Z0-9_\.]+$/,
                            message: '用户名由数字字母下划线和.组成'
                        }
					}
				},
				pass: {
	                message:'密码无效',
	                validators: {
	                     notEmpty: {
	                         message: '密码不能为空'
	                     },
	                     stringLength: {
	                         min: 6,
	                         max: 30,
	                         message: '用户名长度必须在6到30之间'
	                     },
	                     regexp: {
	                         regexp: /^[a-zA-Z0-9_\.]+$/,
	                         message: 'The username can only consist of alphabetical, number, dot and underscore'
	                     }
	                 }
	             }
	            
	         
	            
			}
		});
	});





function login(){
	var bootstrapValidator = $("#login").data('bootstrapValidator');
	bootstrapValidator.validate();
    if (!bootstrapValidator.isValid()){
    	alert("表单验证错误");
    	return;
    }
	 $.ajax({
            //几个参数需要注意一下
                type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/user/login" ,//url
                data: $('#login').serialize(),
                success: function (result) {
                   alert(result);
                },
                error : function(result) {
                    alert("异常！");
                }
            });
}