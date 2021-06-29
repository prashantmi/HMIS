
<%--##		Creation Date			: 	16-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Created By				:	AKASH SINGH
--%>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/buttons.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/layout.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/easyui.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/css/basic.css" />

<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/jquery-ui-themes-1.8.23/themes/redmond/jquery-ui.css" />
<link rel="stylesheet" type="text/css" href="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/css/ui.jqgrid.css" />


<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.easyui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jqueryExtValidation.js"></script>

<script type='text/javascript' src='/HIS/hisglobal/js/jquery/js/basic.js'></script>
<script type='text/javascript' src='/HIS/hisglobal/js/jquery/jquery.simplemodal.js'></script>

<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/js/jquery-ui-custom.min.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/js/jquery.layout.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/src/i18n/grid.locale-en.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.jqGrid-4.6.0/src/jquery.jqGrid.js"></script>    

<script type='text/javascript' src='/HISClinical/hisglobal/utility/dynamicdesk/js/deskNew.js'></script>
<script type='text/javascript' src='/HISClinical/opd/js/opd_desk_new.js'></script>
<script type='text/javascript' src="/hisglobal/js/validation.js"/></script>
<script type='text/javascript' src="/hisglobal/transactionutil/js/master.js"/></script>
<script type='text/javascript' src="/hisglobal/js/util.js"/></script> 
 <script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="/HISClinical/opd/js/casualtyDeskPatientList.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/commonFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>


<style type="text/css">

@media print { 
	#nonprintableDiv1 
	{
		display: none; 
	}
	#nonprintableDiv2 
	{
		display: none; 
	}
	#nonprintableDiv3 
	{
		display: none; 
	}
}


.ui-jqgrid .loading { font-size: 11px; }
.ui-jqgrid .ui-jqgrid-pager { font-size: 11px; }
.ui-jqgrid .ui-jqgrid-view { font-size: 11px; }
.ui-jqgrid-labels{ font-size: 11px; }
.ui-widget-content{ font-size: 11px; }
.ui-pg-table{ font-size: 11px; }
.ui-jqgrid { font-size: 11px; }
.clearsearchclass{ font-size: 11px; }
.ui-pg-table{color:#2E6E9E}

#divPatientListFilterId .filterLabel {
		line-height: 10px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		vertical-align: baseline;
		 }
#divPatientListFilterId .filterCombo {
		width:150px;
		line-height: 10px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		color: #000000;
		vertical-align: top;
		 }
</style>

<script>
	$(window).on("load.loading1", function(){
		//empregvalidate.fetchDefaultValues();
		
		//alert("onload1");
		showPatientList();
		$("#divPatientListId").hide();
		//hideAllTabs(); // Initially hide all content
		
	    //$("#tabs li:first").attr("id","current"); // Activate first tab
	    $("#divPatientListId").fadeIn(200); // Show first tab content
		$("#divPatientListId").show();
	    //$('[name="strCurrentTab"]')[0].value='tab1';
	    	
	    /*$('#tabs a').click(function(e) {
	        e.preventDefault();        
	        //$("#content div").hide(); //Hide all content
	        
	        hideAllTabs(); // Initially hide all content
	        
	        $("#tabs li").attr("id",""); //Reset id's
	        $(this).parent().attr("id","current"); // Activate this
	        $('#' + $(this).attr('title')).fadeIn(1500); // Show content for current tab
	       
	        //Setting Current Tab Value
	        $('[name="strCurrentTab"]')[0].value=$(this).attr('title');
	       
	        if($(this).attr('title')=='tab1')
	        {
	        	$('#divValidationDetailsHeadingId').show();
	        }
	        else
	        {
	        	$('#divValidationDetailsHeadingId').hide();
	        }
	        
	    });*/
	    
	}); 
	
	 
	/*function hideAllTabs()
	{
		$("#divErrorMsgId").html("");
		$("#divNormalMsgId").html("");
		$("#divPrintId").html("");
		
		$("#content #tab1").hide(); // Initially hide all content
	    $("#content #tab2").hide(); // Initially hide all content
	    $("#content #tab3").hide(); // Initially hide all content
	   // $("#content #tab4").hide(); // Initially hide all content
	}*/
</script>
<script>
// Call Function onload 
/* window.onload=function()
{
	showPatientList();
} */
function showLegends()
{
	document.getElementById("divLegends").style.display="block"; 
}

function showLegendsNone()
{
	document.getElementById("divLegends").style.display="none";
}

</script>
<title>Patient List</title>
</head>
	
<body>

<html:form action="/casualtyDeskPatientList">

<div id="divPatientListFilterId" style="margin-top: 1px; width: 99%;" class="wrapper rounded">
	<div class="div-table width100" >
		<div class="div-table-row ">
			<div class="div-table-col width15 filterLabel" align="right">
				Dept/Unit :
			</div>
			<div class="div-table-col width30 " align="left">
					<html:select name="OpdPatientDeskFB" property="departmentUnitCode" tabindex="1" 
						styleClass="filterCombo" onchange="getRoomListByUnit();">
						<html:option value="0">All</html:option>
						<logic:present name="<%=OpdConfig.OPD_DESK_UNIT_LIST%>" >
						<html:options collection="<%=OpdConfig.OPD_DESK_UNIT_LIST%>"
							property="value" labelProperty="label" />
						</logic:present>
					</html:select>
			</div>
					
			<div class="div-table-col width10 filterLabel" align="right">
				Room :
			</div>
			<div class="div-table-col width30 " align="left">
				<html:select name="OpdPatientDeskFB" property="roomCode"  tabindex="1" styleClass="filterCombo">
					<html:option value="0">All</html:option>
				</html:select>
			</div>
			<div class="div-table-col width10"  align="right">
				<img class="button" src="/HISClinical/hisglobal/images/filter.png" width="20px" height="20px" tabindex="1" border="0" style="cursor: pointer" title="Filter Patient List" onkeypress="if(event.keyCode==13)filterPatientList();" onClick="filterPatientList();">
			</div>
		</div>
	</div>
</div>
<div id="divPatientListId" style="margin-top: 1px; width: 99%;" class="wrapper rounded" onmouseover="refreshList();">
	<table id="list1" style="width: 99%;" ><tr><td/></tr></table>
	<div id="pager1"></div>




<html:hidden name="OpdPatientDeskFB" property="selectedPatCrNo" />
<html:hidden name="OpdPatientDeskFB" property="departmentUnitCode" />
<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">
<html:hidden name="OpdPatientDeskFB" property="patCrNo" />

<div class="legends">

<a href="#" onmouseover="showLegends();" onmouseout="showLegendsNone();" style="color:blue;font-family:arial; font-size:14px; ">Legends</a>
      <div id="divLegends" style="display:none; float:inherit; z-index:2; border-style:groove;border-radius:6px; position:absolute; top:97%; margin-left:8%; background:#E5E5E6; width:60%;">
	
		<table width="100%">
			<tr>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Normal 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Waiting Patients
					</font>	
				</td>
				<td width="1%"></td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<i>Italic</i> 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Attended Patients
					</font>
				</td>				
			</tr>
			
			<tr>
				<td bgcolor="white">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					No Background 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Not Selected Patients
					</font>	
				</td>
				<td width="1%"></td>
				<td bgcolor="yellow">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Yellow Background 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Selected Patient
					</font>
				</td>				
			</tr>
		</table>
	
	</div>
<his:status />
      
</div>
</div>


</html:form>

</body>
</html>
