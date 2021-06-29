

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/controlPanel/controlPanelGenerator.css" rel="stylesheet" type="text/css">

<link href="/HIS/hisglobal/css/basic.css" rel="stylesheet" type="text/css">

<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/masterutil/js/jquery/jqueryExtValidation.js"></script>

<script type='text/javascript' src='/HIS/hisglobal/masterutil/js/jquery/js/basic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<!-- Metro JS -->
<script type="text/javascript" src="/HIS/hisglobal/eDesk/transactions/MetroJs/MetroJs.js"></script>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/eDesk/transactions/MetroJs/MetroJs.css" />
<!-- end Metro JS -->

<!-- Icon CSS -->
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/flaticon.css" />
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.1.1.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.min.js"></script>

<script type='text/javascript' src='/HIS/hisglobal/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/basic.js'></script>

<script type='text/javascript' src='/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/dynamicdesk/deskNew.js'></script>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>
<script type="text/javascript" src="/HISClinical/investigation/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>

<!-- ScrollBar JS -->
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/jquery.jquery.min.js'></script>
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/scrollbar/css/prettify.css" />
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/jquery.slimscroll.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/scrollbar/js/prettify.js'></script>
<!-- end ScrollBar -->
<script>

 
$('#tt').tabs({
    border:false,
    fit:true,
    tabPosition:'bottom'
    });

$(document).ready( function (){
	var ht = Math.round($(window).height() - $(".header").outerHeight()-$(".footer").outerHeight()) + "px";
	var wt = Math.round($(window).width()) + "px";

	
	var wdDeskCenter = Math.round($(parent.window).innerWidth() - 85 ) + "px";//2*($("#divDDLeftMenu").width())
	$("#divDDCentre").width(wdDeskCenter);

	
	addTab('Patient List','DESKPATLIST','0','-1',true);
});


    
</script>
<style type="text/css">
.deskMenuStyle {
    background-color: #1679b6;
    color: white;
    display: block;
    height: 40px;
    line-height: 13px;
    text-decoration: none;
    width: 100%;
    text-align: center;
}
.deskMenuStyle1 {
    background-color: #1679b6;
    color: white;
    display: block;
    height: 30px;
    line-height: 13px;
    text-decoration: none;
    width: 100%;
    text-align: center;
}
</style>

</head>

<body style="margin: 0px;">

<div id="deskId" style="margin: 0px;" class="wrapper rounded" >
	<div class="div-table width100" >
		<div class="div-table-row " style="position: fixed; top: 0px; z-index: 2;">
			<div class="div-table-col width100" >
				<iframe id="frmDynamicDeskHeader" name="frmDynamicDeskHeader" src="/HISClinical/hisglobal/utility/dynamicdesk/headerNew.cnt"  
				scrolling="no"  frameborder="0" width="100%" height="35px" marginwidth="0" marginheight="0"  style="height:25px;"></iframe>
			</div>
		</div>
		<div class="div-table-row " style="position: fixed; top: 25px; width: 99%; z-index: 1;" >
			<div id="divDDLeftMenu" class="div-table-col" style="width: 80px; position: fixed; left: 0px;">
		   		<jsp:include page="dynamicDeskNonPatientricTab.jsp"  flush="false"></jsp:include>
			</div>
			<div id="divDDCentre" class="div-table-col" style="position: fixed; left: 80px;">
				<div id="tt" class="easyui-tabs" style="border-bottom-style: none; border: 0px;" >
			</div>
				
			<!-- <div style="position: absolute; height: 60px; z-index: 3; width: 100%; ">
					<jsp:include page="dynamicDeskPatBottomTabs.jsp"  flush="true"></jsp:include>
				</div>--> 
			</div> 
			<div id="divDDRightMenu" class="div-table-col" style="width: 80px; position: fixed; right: 0px; display: none;">
		   		<jsp:include page="dynamicDeskPatRightTabs.jsp"  flush="true"></jsp:include>
			</div>
			<div id="divDDFooter" class="div-table-col" style="display: none;">
				<iframe id="frmDynamicDeskFooter" name="frmDynamicDeskFooter" src="/HISClinical/hisglobal/utility/dynamicdesk/footer.cnt"  
				scrolling="no"  frameborder="0" width="100%" height="35px" marginwidth="0" marginheight="0" ></iframe>
			</div>
		</div>
	</div>		
</div>
</body>
</html>
