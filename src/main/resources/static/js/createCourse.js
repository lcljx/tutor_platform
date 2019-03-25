function getCourseTypes(){
	$.ajax({
		url : '../type/getCourseTypes',
		type : 'POST',
		dataType : 'json',
		data : {"flag" : "课程"},
		success : function(data){
			
			var len = data.length;
			for(var i=0;i<len;i++){
				 $("#courseType").append("<option class='courseType' value ='"+data[i].id+"'>"+data[i].typeName+"</option>");
			}
		},
		error : function(){
			alert("出了一些问题");
		}
		
	});
}

function createCourse(){
	//var data = $('#createCourse2').serialize();
	var formData = new FormData();
	var imgs = $('#uploadImg');
	var fileObj = imgs[0].files[0];
	var courseType =$('#courseType').val();
	var courseContent =$('#courseContent').val();
	var coursePrice =$('#coursePrice').val();
	var period =$('#period').val();
	var status =$('#status').val();
	var courseName = $('#courseName').val();
	console.log(courseType);
	formData.append("file", fileObj); 
	formData.append("typeId",courseType);
	formData.append("courseName", courseName);
	formData.append("courseDescribe",courseContent); 
	formData.append("price",coursePrice); 
	formData.append("period",period);
	formData.append("status",status); 
	$.ajax({
		url : '../course/createCourse',
		type : 'POST',
		cache: false,
		processData: false,
		contentType: false,
		data : formData,
		success : function(data){
			if(data)
				alert("发布成功");
		},
		error : function(){
			alert("您的表单似乎有空的呢");
		}
		
	});
}