<table id="data" title="信息" class="easyui-datagrid"
		url="<%=request.getContextPath() %>/querySmoke.do" toolbar="#ToolBar" pagination="true" pageList="[2,5,10]"
		fitColumns="true" fit="true" rownumbers="true">
		<thead>
			<tr>
				<th data-options="field:'sid',width:100">编号</th>
				<th data-options="field:'sname',width:100">品牌</th>
                 <th data-options="field:'tname',width:100">类型</th>
				<th data-options="field:'tar',width:100,formatter:formattar">焦油量</th>
				<th data-options="field:'status',width:100,formatter:formatstatus">状态</th>
				<th data-options="field:'qwe',width:100,formatter:formatcaozuo">操作</th>
				
			</tr>
		</thead>
	</table>
	<div id="ToolBar">
		
		?品牌：?<input type="text" id="bname" />
		类型<select id="smoketype" style="width:70px"></select>
            <a id="searchBtn" class="easyui-linkbutton" class="easyui-linkbutton"
                iconCls="icon-search" plain="true">搜索</a>
            <br>生命值：<div id="p" style="width:400px;"></div> 
	</div>

<%-- 	<script type="text/javascript" src="<%=request.getContextPath() %>/js/MyEasyUIjs.js"></script> --%>
</body>
<script type="text/javascript">
$(function(){
	//queryuser();
	//selecttype();
	
	
	//条查
	$("#searchBtn").click(function(){
		var name=$("#bname").val();//名称（文本框）
		var typeid = $("#smoketype").combobox('getValue');//类型（动态 下拉）
		$("#data").datagrid('load',{
			sname:name,
			tid:typeid,
		})
	})

})
function formattar(value,row,index){
	return value+'mg';
}
function formatstatus(value,row,index){
	var a=value==1?'正常测试':'终止测试';
	return a;
}
function formatcaozuo(value,row,index){
	var str="";
	
	
	var yikoul=row.tar*0.01*0.1
	var liangkoul=row.tar*0.02*0.1
	var yikou='<a href="javascript:xiyan('+yikoul+')">吸一口</a>丨';
	var liangkou='<a href="javascript:xiyan('+liangkoul+')">吸两口</a>丨';
	
	var z=row.status==1?'终止测试':'开始测试';
	var status=row.status==1?'2':'1';
	var ceshi='<a href="javascript:ceshi('+row.sid+','+status+')">'+z+'</a>';
	
	str+=yikou;
	str+=liangkou;
	str+=ceshi;
	return str;
}


//进度条
function queryuser(){
	$.ajax({
		url:"./queryuser.do",
		success:function(data){
			for (var i = 0; i < data.length; i++) {
				//进度条
				$('#p').progressbar({
					value: data[i].life
				}); 
			}
		},
		error:function(){}
	})
}

function selecttype(){
	$("#smoketype").combobox({
		url:'./queryType.do',
		valueField:'tid',
		textField:'tname',
	});
}




function xiyan(zhi){
	$.ajax({
		url:"./updateUser.do",
		type:"post",
		data:{"zhi":zhi},
		success:function(data){
			if(data){
				queryuser();
			}
		},
		error:function(){}
	})
}

function ceshi(id,status){
	$.ajax({
		url:"./updateSmoke.do",
		type:"post",
		data:{"id":id,"status":status},
		success:function(data){
			if(data){
				$("#data").datagrid('reload');
			}
			
		},
		error:function(){}
	})
}

</script>