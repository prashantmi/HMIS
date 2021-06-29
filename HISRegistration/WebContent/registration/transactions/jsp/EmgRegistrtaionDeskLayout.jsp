<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">
    
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.min.js"></script>

<title>Emergency Registration Desk</title>
<script>
	var frameId=0;
	
	$(document).ready(function(){
		$('#divTabsId').tabs({
		    border:false,
		    onSelect:function(title,index){
		    	if(frameId>3){
		    		var reloadFrame= '#frameTab'+(index+1);
		    		$(reloadFrame).attr("src",$(reloadFrame).attr("src"));
		    	}
		    }
		});
		
		callme();
		
	});	
	function addTab(_title, url){
		if ($('#divTabsId').tabs('exists', _title)){
			$('#divTabsId').tabs('select', _title);
		}else {
			frameId++;
			var contentData = '<iframe id="frameTab'+frameId+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;"></iframe>';
			/* $('#divTabsId').tabs('add',{
				title:_title, content:contentData, border:false, closable:true 
			}); */
			 if(frameId==1){
				 $('#divTabsId').tabs('add',{
							title:_title, content:contentData, border:false, closable:true , selected:true
						});
			}else{
				$('#divTabsId').tabs('add',{
					title:_title, content:contentData, border:false, closable:true, selected:false 
				});
			} 
		}
	}
	
	function callme(){
		//console.log("inside callme()");
		addTab('Emergency Patient Registration','/HISRegistration/registration/transactions/EmgRegistration.action');
		addTab('Emergency Patient Visit','/HISRegistration/registration/transactions/EmgPatientVisit.action');
		addTab('Emergency Patient Detail Modification','/HISRegistration/registration/transactions/EmgPatDetailMod.action');
		frameId++;
	}
</script>

</head>

<body style="margin: 0px;" >

	<div id="divTabsId"  style="height:600px; border-bottom-style: none; border: 0px;  "></div>
	
	<div style="margin-bottom:10px">
		<a href="#" class="easyui-linkbutton" onclick="addTab('Emergency Patient Registration','/HISRegistration/registration/transactions/EmgRegistration.action');">Emergency  Registration</a>
		<a href="#" class="easyui-linkbutton" onclick="addTab('Emergency Patient Visit','/HISRegistration/registration/transactions/EmgPatientVisit.action');">Emergency Patient Visit</a>
		<a href="#" class="easyui-linkbutton" onclick="addTab('Emergency Patient Detail Modification','/HISRegistration/registration/transactions/EmgPatDetailMod.action');">Emergency Patient detail Modification</a>
	</div>
   
</body>

</html>