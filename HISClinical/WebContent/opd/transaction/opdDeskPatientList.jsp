<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>


<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.transaction.controller.fb.OpdPatientDeskFB"%>

<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

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
<script type="text/javascript" src="/HISClinical/opd/js/opdDeskPatientList.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/dynamicdesk/dynamicDeskPanelGenerator.js"></script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/dojotoolkit/dojo/dojo.js" djConfig="parseOnLoad: true"> </script>
<script type="text/javascript" src="/HISClinical/hisglobal/js/commonFunctions.js"></script> 
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<script language="JavaScript" src="/HISClinical/registration/js/validationCommon.js"></script>

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
		//alert("onload");
		var today = document.getElementsByName('episodeDate')[0].value;
		//alert(today);

		getVisitDates(today);
		 	
		//empregvalidate.fetchDefaultValues();
		document.getElementsByName("srchValue")[0].value="";
		showPatientList();
		$("#divPatientListId").hide();
		//hideAllTabs(); // Initially hide all content
		
	    //$("#tabs li:first").attr("id","current"); // Activate first tab
	    $("#divPatientListId").fadeIn(200); // Show first tab content
		$("#divPatientListId").show();
		
		
		document.getElementsByName("patCrNo")[0].value=document.getElementsByName("hospitalCode")[0].value ; 
		
	    // $('[name="strCurrentTab"]')[0].value='tab1';
	    	
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
		if(!validateAlphaValue(srchValue))
		{
			alert("Enter Alphabets Only for Patient Name Search");
			return false;
		}
	return true;
}

function filterPatientList()
{
	// fetch filter data
//	alert("hi");
	//alert("called..");
	parent.document.getElementById("frmDynamicDeskHeader").contentWindow.setPatStats(); // For Rfreshing Desk Statics
	var tempdepartmentUnitCode = document.getElementsByName("departmentUnitCode")[0].value;
	var departmentUnitCode=new Array();
	departmentUnitCode = tempdepartmentUnitCode.toString().split("^");
	var visitType = document.getElementsByName("visitType")[0].value;
	var roomCode = document.getElementsByName("roomCode")[0].value;
	var srchCriteria = document.getElementsByName("srchCriteria")[0].value;
	var srchValue = document.getElementsByName("srchValue")[0].value;
	var visitDate = document.getElementsByName("episodeDate")[0].value;
	//alert("departmentUnitCode :"+departmentUnitCode[0]+"  visitType  :"+visitType+"roomCode"+roomCode);
	//jQuery("#list1").jqGrid().setGridParam({url : '/HISClinical/opd/opdDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType+"&roomCode="+roomCode}).trigger("reloadGrid")
		
	
	if((persistRowFlag != "0") && (jQuery("#list1").jqGrid().setGridParam({url : createFHashAjaxQuery('/HISClinical/opd/opdDeskPatientList.cnt?hmode=AJX_G_PATIENTLIST'+"&departmentUnitCode="+departmentUnitCode[0]+"&visitType="+visitType+"&roomCode="+roomCode+"&srchCriteria="+srchCriteria+"&srchValue="+srchValue+"&episodeDate="+visitDate)}).trigger("reloadGrid"))>0)
	{	    	
		//alert("loadComplete..");
		jQuery("#list1").setSelection(persistRowFlag);
				
	} 
	
	//if($("#list1").getGridParam("reccount") == 0)
	//	alert("CR No invalid");
	
	//document.getElementsByName("patCrNo")[0].value=document.getElementsByName("hospitalCode")[0].value ; 
	
	
	
	 
}
//Added by Vasu to get current date 
function getVisitDates(obj)
{
	var ele = document.getElementById('visitDateId');
	var innerHtml = "";
	var strDate = obj;	
	//alert("A: "+strDate);
	var today = convertStrToDate(strDate, 'dd-Mon-yyyy');
	//alert("B: "+today);
	innerHtml +=  "<option value='" + strDate + "' selected='selected'>" + strDate + '</option>';

	var yesterday = addToDate(today,-1,"D");
	//alert("C: "+yesterday);
	strDate = convertDateToStr(yesterday, 'dd-Mon-yyyy')
	innerHtml +=  '<option value="' + strDate + '">' + strDate + '</option>';

	var daybefore = addToDate(yesterday,-1,"D");
	//alert("D: "+daybefore);
	strDate = convertDateToStr(daybefore, 'dd-Mon-yyyy')
	innerHtml +=  '<option value="' + strDate + '">' + strDate + '</option>';
	
	//alert("E: "+innerHtml);
	ele.innerHTML =  innerHtml;

}

function getVisitDate()
{
	var visitDate = document.getElementById('visitDateId').value;
	//alert(visitDate);
	document.getElementsByName("episodeDate")[0].value = visitDate;
	
}
</script>
<title>Patient List</title>
</head>
	
<body>

<html:form action="/opdDeskPatientList">

<div id="divPatientListFilterId" style="margin-top: 1px; width: 99%;" class="wrapper rounded">
	<div class="div-table width100" >
		<div class="div-table-row ">
			<div class="div-table-col width5 filterLabel" align="right" style="margin-top: 5px;">
				Dept/Unit :
			</div>
			<div class="div-table-col width10 " align="left">
					<html:select name="OpdPatientDeskFB" property="departmentUnitCode" tabindex="1" 
						styleClass="filterCombo" onchange="getRoomListByUnit();">
						<html:option value="0">All</html:option>
						<logic:present name="<%=OpdConfig.OPD_DESK_UNIT_LIST%>" >
						<html:options collection="<%=OpdConfig.OPD_DESK_UNIT_LIST%>"
							property="value" labelProperty="label" />
						</logic:present>
					</html:select>
			</div>
					
			<div class="div-table-col width6 filterLabel" align="right" style="margin-top: 5px;">
				Room :
			</div>
			<div class="div-table-col width10 " align="left">
				<html:select name="OpdPatientDeskFB" property="roomCode"  tabindex="1" styleClass="filterCombo">
					<html:option value="0">All</html:option>
				</html:select>
			</div>
			
			<div class="div-table-col width8 filterLabel" align="right" style="margin-top: 5px;">
				Search By :
			</div>
			<div class="div-table-col width12 filterLabel" align="left">
				<html:select name="OpdPatientDeskFB" property="srchCriteria" tabindex="1" styleClass="filterCombo">
					<html:option value="0">Select Value</html:option>
					<html:option value="1">CR No</html:option>
					<html:option value="2">Patient Name</html:option>
				</html:select>
			</div>
			<div class="div-table-col width10" align="right">
				<html:text name="OpdPatientDeskFB" property="srchValue" maxlength="15" size="15" tabindex="1" onkeypress="if(event.keyCode==13){ if(criteriaChk()) filterPatientList();} else return validateAlphaNumOnly(this,event);">					
				</html:text>
			</div>	

			<div class="div-table-col width5 filterLabel" align="left" style="margin-top: 5px;">
				Visit Date :
			</div>
			<div class="div-table-col width10 " align="left">
				<%-- <html:select  name="OpdPatientDeskFB" property="episodeDate"  tabindex="1" styleClass="filterCombo">
					<html:option value="0">All</html:option>
				</html:select> --%>
				<select id = "visitDateId" onchange="getVisitDate();"></select>
			</div>
			
			<div class="div-table-col width3"  align="center">
				<img class="button" src="/HISClinical/hisglobal/images/filter.png" width="20px" height="20px" tabindex="1" border="0" style="cursor: pointer" title="Filter Patient List" onkeypress="if(event.keyCode==13){ if(criteriaChk()) filterPatientList();}" onClick="if(criteriaChk()) filterPatientList();">
			</div>
			<div class="div-table-col width7 filterLabel" align="right" style="margin-top: 5px;">
				Visit Criteria :
			</div>
			<div class="div-table-col width10 " align="left">
				<html:select name="OpdPatientDeskFB" property="visitType" tabindex="1" onchange="filterPatientList();"
					styleClass="filterCombo">
					<html:option value="0">All</html:option>					
					<html:option value="6">Waiting</html:option>
					<html:option value="1">First Visit</html:option>
					<html:option value="2">Old Visit</html:option>
					<html:option value="3">Appointment Based</html:option>
					<html:option value="4">Referred</html:option>
					<html:option value="5">Attended</html:option>
				</html:select>
			</div>
			
			
			
			
		</div>
	</div>
</div>
<div id="divPatientListId" style="margin-top: 1px; width: 99%;" class="wrapper rounded" onmouseover="refreshList();">
	<table id="list1" style="width: 99%;" ><tr><td/></tr></table>
	<div id="pager1" class="wrapper rounded"></div>




<html:hidden name="OpdPatientDeskFB" property="selectedPatCrNo" />
<html:hidden name="OpdPatientDeskFB" property="departmentUnitCode" />
<html:hidden name="OpdPatientDeskFB" property="hospitalCode" />
<html:hidden name="OpdPatientDeskFB" property="episodeDate" />

<input type="hidden" name="hmode" value="">
<input type="hidden" name="orderBy" value="">

   


<%-- <div style="margin-top: 1px; width: 99%;display: block;" class="wrapper rounded" >
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		
		<table width="80%" cellspacing="0" cellpadding="0">
			<tr>
				<td width="60%"></td>
				<td width="20%">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show</font>
						<img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide</font>
						<img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
					</div>
				</td>
			</tr>
		</table>
	</his:SubTitleTag> --%>

	<!-- <div id="divLegends" style="display:block;">
	 -->	
	   <div class="legends" style="z-index:-1;">
					<a href="#" onmouseover="showLegends();" onmouseout="showLegendsNone();" style="color:blue; font-family:arial; font-size:14px;">Legends</a>
 
	   <div id="divLegends" style="display:none; float:inherit; z-index:2; border-style:groove;border-radius:6px; position:absolute; top:97%; margin-left:8%; background:#E5E5E6; width:60%;">
	
	
			<table width="100%">
				<tr>
					<td width="20%"><div align="left">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR_NAME%>: 
						</font></div>
					</td>
					<td width="30%">
						<div align="left">
							<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_FIRST_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								First Visit
							</font>
						</div>
					</td>
			
					<td width="20%"><div align="left">
						<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR_NAME%>:
						</font></div>
					</td>
					<td width="30%">
						<div align="left">
							<font color="<%=OpdConfig.OPD_DOCTOR_DESK_PAT_LIST_OLD_VISIT_COLOR%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								Old Visit
							</font>
						</div>
					</td>
					</tr>
				<tr>
				<td width="20%">
			<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Normal 
					</font>
					</div>
				</td>
				<td width="30%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Waiting Patients
					</font>	</div>
				</td>
				
				
				<td width="20%"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<i>Italic</i> 
					</font></div>
				</td>
				
				<td width="30%"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Attended Patients
					</font></div>
				</td>				
			</tr>
				
				<tr>
				<td width="20%" bgcolor="white"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					No Background 
					</font></div>
				</td>
				<td width="30%"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Not Selected Patients
					</font>	</div>
				</td>
				
				<td width="20%" bgcolor="#fbec88"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Yellow Background 
					</font></div>
				</td>
				<td width="30%"><div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Selected Patient
					</font></div>
				</td>	</tr>		
			</table>
		
	 </div> 


<his:status />
</div></div>

</html:form>



</body>
</html>
