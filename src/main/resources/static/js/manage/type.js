function newType(flag){
				$('#dlg').dialog('open').dialog('setTitle','添加公告');
				$('#fm').form('clear');
				url = '../../type/newType?flag='+flag;
}
			
function saveType(){
	$('#fm').form('submit',{
		url: url,
		onSubmit: function(){
			return $(this).form('validate');
		},
		success: function(result){
			
			if (!result){
				$.messager.show({
					title: 'Error',
					msg: "操作失败"
				});
			} else {
				alert("操作成功");
				$('#dlg').dialog('close');		// close the dialog
				$('#dg').datagrid('reload');	// reload the user data
			}
		}
	});
}

function delType(){
	var row = $('#dg').datagrid('getSelected');
	console.log(row);
	if (row){
		$.messager.confirm('Confirm','你确定要删除该条信息么?',function(r){
			if (r){
				$.post('../../type/delType',{id:row.id},function(result){
					if (result){
						alert("成功删除");
						$('#dg').datagrid('reload');	// reload the user data
					} else {
						$.messager.show({	// show error message
							title: 'Error',
							msg: result.errorMsg
						});
					}
				},'json');
				}
		});
	}
}

//当编辑用户时，打开一个对话框并从 datagrid 选择的行中加载表单数据。
function editType(){
	var row = $('#dg').datagrid('getSelected');
	if(row==null) alert("请选择要修改的行");
	if (row){
		$('#dlg').dialog('open').dialog('setTitle','编辑类型');
		//$('#fm').form('load',row);
		$('#typeName').val(row.typeName);
		$('#describes').val(row.describe);
		$('#url').val(row.url);
		url = '../../type/editType?id='+row.id;
	}
}

function doSearch(){
	$('#dg').datagrid('load',{
		typeName: $('#typename').val()
	});
}