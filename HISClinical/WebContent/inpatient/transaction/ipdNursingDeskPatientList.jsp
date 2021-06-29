<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>

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

<script type='text/javascript' src="/HISClinical/hisglobal/js/validation.js"/></script>
<script type='text/javascript' src="/HISClinical/hisglobal/transactionutil/js/master.js"/></script>
<script type='text/javascript' src="/HISClinical/hisglobal/js/util.js"/></script>

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
<script type="text/javascript" src="/HIS/hisglobal/js/dateFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/utility/generictemplate/js/date_validator.js"></script>
<script type="text/javascript" src="/HISClinical/inpatient/js/ipdNursingDeskPatientList.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/commonFunctions.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<script type="text/javascript" src="/HISClinical/registration/js/validationCommon.js"></script>
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
		width:130px;
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
		//alert("onload");
		//empregvalidate.fetchDefaultValues();
		document.getElementsByName("srchValue")[0].value="";
		document.getElementsByName("srchCriteria")[0].value="";
		showPatientList();
		//alert("onLoad2");
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
//window.onload=function()
//{
	//showPatientList();
//}
function showLegends()
{
	document.getElementById("divLegends").style.display="block"; 
}

function showLegendsNone()
{
	document.getElementById("divLegends").style.display="none";
}

function criteriaChk()
{
	//alert("Checking");
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	//alert("srchCriteria:"+srchCriteria);
	//alert(document.getElementsByName("srchValue")[0].value.length);
	if(srchCriteria=="1")
	{
		if(document.getElementsByName("srchValue")[0].value.length<15)
		{
			alert("Please Enter a 15 Digit CR No");
			return false;
		}
		if(!validatePositiveIntegerValue(srchValue))
		{
			alert("Enter Digits Only");
			return false;
		}
	}
	if(srchCriteria=="2")
	{
		if(document.getElementsByName("srchValue")[0].value.length<15)
		{
			alert("Please Enter a 15 Digit Admission No");
			return false;
		}
		if(!validatePositiveIntegerValue(srchValue))
		{
			alert("Enter Digits Only");
			return false;
		}
	}
	if(srchCriteria=="3")
		if(!validateAlphaValue(srchValue))
		{
			alert("Enter Alphabets Only for Patient Name Search");
			return false;
		}
	return true;
}
</script>
<title>Patient List</title>
</head>
	
<body>

<html:form action="/ipdDeskPatientList">

<div id="divPatientListFilterId" style="margin-top: 1px; width: 99%;" class="wrapper rounded">
	<div class="div-table width100" >
		<div class="div-table-row ">
			<div class="div-table-col width5 filterLabel" align="right" style="margin-top: 5px;">
				Dept/Unit :
			</div>
			<div class="div-table-col width10 " align="left">
					<html:select name="IpdPatDocDeskFB" property="departmentUnitCode" tabindex="1" 
						styleClass="filterCombo" onchange="getWardListByUnit();">
						<html:option value="0">All</html:option>
						<logic:present name="<%=InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST%>" >
						<html:options collection="<%=InpatientConfig.NURSING_DESK_DEPT_UNIT_LIST%>"
							property="value" labelProperty="label" />
						</logic:present>
					</html:select>
			</div>
					
			<div class="div-table-col width5 filterLabel" align="right" style="margin-top: 5px;">
				Ward :
			</div>
			<div class="div-table-col width10 " align="left">
				<html:select name="IpdPatDocDeskFB" property="wardCode"  tabindex="1" styleClass="filterCombo" onchange="getRoomListByUnit();">
					<html:option value="0">All</html:option>
						<logic:present name="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT%>" >
						<html:options collection="<%=InpatientConfig.NURSING_DESK_WARD_LIST_ON_BASIS_UNIT%>"
							property="value" labelProperty="label" />
						</logic:present>
				</html:select>
			</div>
			
			<div class="div-table-col width5 filterLabel" align="right" style="margin-top: 5px;">
				Room :
			</div>
			<div class="div-table-col width10" align="left">
				<html:select name="IpdPatDocDeskFB" property="roomCode"  tabindex="1" styleClass="filterCombo">
					<html:option value="0">All</html:option>
				</html:select>
			</div>
			
			<div class="div-table-col width8 filterLabel" align="right" style="margin-top: 5px;">
				Search By :
			</div>
			<div class="div-table-col width10 filterLabel" align="left">
				<html:select name="IpdPatDocDeskFB" property="srchCriteria" tabindex="1" styleClass="filterCombo">
					<html:option value="0">Select Value</html:option>	
					<html:option value="1">CR No</html:option>					
					<html:option value="2">IPD No</html:option>
					<html:option value="3">Patient Name</html:option>
				</html:select>
			</div>
			<div class="div-table-col width15" align="right">
				<html:text name="IpdPatDocDeskFB" property="srchValue" maxlength="15" tabindex="1" onkeypress="if(event.keyCode==13){ if(criteriaChk()) filterPatientList();} else return validateAlphaNumOnly(this,event);">					
				</html:text>
			</div>			
			
			
			<div class="div-table-col width3"  align="right">
				<img class="button" src="/HISClinical/hisglobal/images/filter.png" width="20px" height="20px" tabindex="1" border="0" style="cursor: pointer" title="Filter Patient List" onkeypress="if(event.keyCode==13) if(criteriaChk()){filterPatientList();}" onClick="if(criteriaChk()){filterPatientList();}">
			</div>
			
			<div class="div-table-col width5 filterLabel" align="right" style="margin-top: 5px;">
				Services :
			</div>
			<div class="div-table-col width10 " align="left">
				<html:select name="IpdPatDocDeskFB" property="services" tabindex="1" onchange="filterPatientList();"
					styleClass="filterCombo">
					<html:option value="1">Admitted</html:option>					
					<html:option value="3">Permit To Go Home</html:option>
					<html:option value="4">On Transit</html:option>
					<html:option value="2">Non Acceptance</html:option>
				</html:select>
			</div>
			
		</div>
	</div>
</div>
<div id="divPatientListId" style="margin-top: 1px; width: 99%;" class="wrapper rounded" onmouseover="refreshList();">
	<table id="list1" style="width: 99%;" ><tr><td/></tr></table>
	<div id="pager1"></div>




<html:hidden name="IpdPatDocDeskFB" property="selectedPatCrNo" />
<html:hidden name="IpdPatDocDeskFB" property="departmentUnitCode" />
<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">
<html:hidden name="IpdPatDocDeskFB" property="patCrNo" />
<div class="legends">

<a href="#" onmouseover="showLegends();" onmouseout="showLegendsNone();" style="color:blue;font-family:arial; font-size:14px; ">Legends</a>
     <div id="divLegends" style="display:none; float:inherit; z-index:2; border-style:groove;border-radius:6px; position:absolute; top:97%; margin-left:8%; background:#E5E5E6; width:60%;">
	
		<table width="100%">
			<tr>
			<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Black 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Patients has balance
					</font>
				</td>
				
				<td width="1%"></td>
				<td>
					<font color="hotpink" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Pink 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Balance less than 0 or negative
					</font>	
				</td>				
			</tr>
			
			<tr>
			<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Normal 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Not Discharged Patients 
					</font>
				</td>
				
				<td width="1%"></td>
				<td>
					<font color="green" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><i>Green </i></b> 
					</font>
				</td>
				<td>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Discharge Requested Patients
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
      
</div></div>

</html:form>

</body>
</html>
