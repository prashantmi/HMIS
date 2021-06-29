<%@page  language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/controlPanel/controlPanelGenerator.css" rel="stylesheet" type="text/css">

<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.min.js"></script>

<script type='text/javascript' src='/HIS/hisglobal/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/basic.js'></script>

<script type='text/javascript' src='/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/dynamicdesk/deskNew.js'></script>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>

<script>

 
$('#tt').tabs({
    border:false,
    fit:true,
    tabPosition:'bottom'
    });

$(document).ready( function (){
	var ht = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()	) + "px";
	var wt = Math.round($(window).width()) + "px";
	$('.body').height(ht);
	
	var wdDeskCenter = Math.round($(parent.window).innerWidth() - 200 ) + "px";//2*($("#divDDLeftMenu").width())
	$("#divDDCentre").width(wdDeskCenter);
	//var ht2 = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()-$("#menuStrip").outerHeight()) + "px";
	//$('.bodyContent').height(ht2);
	//$('.bodyContent').css({'margin-top':$("#menuStrip").outerHeight()});

	//alert("ready");
	addTab('Patient List','DESKPATLIST','0','-1',true);
});


    
</script>
</head>

<body style="margin: 0px;">

<div style="margin: 0px;" class="wrapper rounded" >
	<div class="div-table width100">
		<div class="div-table-row " style="position: fixed; top: 0px; z-index: 2;">
			<div class="div-table-col width100" >
				<iframe id="frmDynamicDeskHeader" name="frmDynamicDeskHeader" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/headerNew.cnt"  
				scrolling="no"  frameborder="0" width="100%" height="35px" marginwidth="0" marginheight="0" ></iframe>
			</div>
		</div>
		<div class="div-table-row " style="position: absolute; top: 35px; width: 99%; z-index: 1">
			<div id="divDDLeftMenu" class="default-skin div-table-col" style="width: 80px position: relative; left: 0px;">
		   		<jsp:include page="dynamicDeskNonPatientricTab.jsp"  flush="false"></jsp:include>
			</div>
			<div id="divDDCentre" class="div-table-col" style="position: fixed; left: 80px;">
				<div id="tt" class="easyui-tabs" style="border-bottom-style: none; border: 0px;" >
				</div>
			</div>
			<div id="divDDRightMenu" class="div-table-col default-skin demo" style="width: 80px; position: absolute; right: 0px;">
		   		<jsp:include page="dynamicDeskPatRightTabs.jsp"  flush="true"></jsp:include>
			</div>
			<div id="divDDFooter" class="div-table-col" style="display: none;">
				<iframe id="frmDynamicDeskFooter" name="frmDynamicDeskHeader" src="/HISInvestigationG5/hisglobal/utility/dynamicdesk/footer.cnt"  
				scrolling="no"  frameborder="0" width="100%" height="35px" marginwidth="0" marginheight="0" ></iframe>
			</div>
		</div>
	</div>		
</div>

</body>
</html>
