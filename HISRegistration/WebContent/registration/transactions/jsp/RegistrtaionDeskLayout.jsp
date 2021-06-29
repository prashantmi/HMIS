<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/easyui.css">

    
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.min.js"></script>
<title>Registration Desk</title>
<script>
var frameId=0;

$(document).ready(function(){
	$('#divTabsId').tabs({
	    border:false,
	    onSelect:function(title,index){
	    	//console.log("frameId :"+frameId);
	    	if(frameId>3){
	    		var reloadFrame= '#frameTab'+(index+1);
	    		$(reloadFrame).attr("src",$(reloadFrame).attr("src"));
	    		console.log("frametab :"+$(reloadFrame).get(0));
	    	}
	    }
	});
	
	callme();
	
	
});	
function addTab(_title, url)
{
	//console.log("inside addTab()");
	//console.log("title :"+_title);
	if ($('#divTabsId').tabs('exists', _title))
	{
		$('#divTabsId').tabs('select', _title);
	} 
	else 
	{
		frameId++;
		var contentData = '<iframe id="frameTab'+frameId+'" scrolling="auto" frameborder="0" src="'+url+'" style="width:100%;height:100%;"></iframe>';
		 $('#divTabsId').tabs('add',{
								title:_title,
								content:contentData,
								border:false,
								closable:true
								 
								 
							});
		
	}
}

function callme()
{
	
	//console.log("inside callme()");
	addTab('Patient Visit','/HISRegistration/registration/transactions/PatientVisit.action');
	addTab('Patient Detail Modification','/HISRegistration/registration/transactions/PatientDetailModDesk.action?strPatRegCatCode=11');
	addTab('Patient Registration','/HISRegistration/registration/transactions/NewRegistration.action');
	frameId++;
	
}
    
</script>

</head>

<body style="margin: 0px;" >

<div id="divTabsId"  style="height:650px; border-bottom-style: none; border: 0px;  ">

</div>


   
</body>

</html>