<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>

<%@page import="ehr.treatmentgiven.presentation.EHRSection_TreatmentGivenFB"%>
<%@page import="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO"%>

<%-- <his:javascript src="/ehr/js/EHR_spp_treatment.js" /> --%>
<his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />
<his:javascript src="/ehr/js/EHR_spp_treatmentgiven.js" />   
<%-- <his:javascript src="/opd/js/desk_treatment_detail.js" /> --%>
<his:javascript src="/hisglobal/js/wz_tooltip.js" />

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />

<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

//function treatmentOnload()
//{
	//alert("neha")
//	TDonLoad();
// }
/* window.onload=function()
{
	var elemStkDtl = document.getElementsByName("selStock");
	alert(elemStkDtl.length);
	for(i=0;i<(elemStkDtl.length);i++)
	{
		alert(elemStkDtl[i].value);	
		if(elemStkDtl[i].value=="0")
			document.getElementById("stk_unavl"+i).style.display="";
		else if (elemStkDtl[i].value=="1")
			document.getElementById("stk_avl"+i).style.display="";
		else
		{
			document.getElementById("stk_avl"+i).style.display="none";
			document.getElementById("stk_unavl"+i).style.display="none";
		}
	}
	
} */

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function showInstruction(event,index)
{	
	var instruction=document.getElementsByName("prevSelInstructionArray")[index].value;
	//	alert("index"+index+""+instruction);
		var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=INSTRUCTION&popupInstruction='+instruction;
		child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
}



function enterDays(event,index)
{	
		
	var index=index.value.split("$")[1];
	var revokeLen=document.getElementsByName("revoke").length;
	var rxContinueLen=document.getElementsByName("rxContinue").length;
	
	if(revokeLen==rxContinueLen)
	{
		if(document.getElementsByName("revoke")[index].checked || document.getElementsByName("rxContinue")[index].checked)
		{
			alert("You can select only one checkbox in a row ");
			document.getElementsByName("extension")[index].checked=false;
			return false;
		}
	}
	
	
	if(document.getElementsByName("revoke")[index].checked || document.getElementsByName("rxContinue")[index].checked)
	{
		alert("You can select only one checkbox in a row");
		document.getElementsByName("extension")[index].checked=false;
		return false;
	}
	
	
	if(document.getElementsByName("extension")[index].checked)
	{
		var prevDrugIdLen=document.getElementsByName("prevDrugId").length;
		var prevDrugId=document.getElementsByName("prevDrugId")[index].value;
		var selEndDate=document.getElementsByName("selEndDate")[index].value
		var extensionFlag=false;
		
		for(var i=0;i<prevDrugIdLen;i++)
		{
			if(prevDrugId==document.getElementsByName("prevDrugId")[i].value && i!=index)
			{
				extensionFlag=true;
				var days=noOfDays(document.getElementsByName("selEndDate")[i].value,selEndDate);
				if(days>0)
				{
					alert("U can not extend this drug");
					document.getElementsByName("extensionDays")[index].value="";
					document.getElementsByName("extensionDays")[index].disabled=true;
					document.getElementsByName("extension")[index].checked=false;
					return false;
				}
				else
				{
					document.getElementsByName("extensionDays")[index].disabled=false;
				}
			}
		}
		
		if(!extensionFlag)
		{
			document.getElementsByName("extensionDays")[index].disabled=false;
		}
		
		
	}
	else
	{
		document.getElementsByName("extensionDays")[index].value="";
		document.getElementsByName("extensionDays")[index].disabled=true;
	}
	
}




// get no of days between two date
function noOfDays(a,b)
{
	valid=true;
	var day=0;
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	day=(adate-bdate)/86400000;
	return day;
}

function showDrugSchedule(validate,event,index)
{	
	if(validate)
	{
		document.getElementsByName("frequencyImageIndex")[0].value=index;
		if(document.getElementsByName("selFrequencyId")[index].value=="14")
		{	
			alert("For SOS No Need Of Schedule");
			return false;
		}
		if(document.getElementsByName("selFrequencyId")[0].value=="-1")
		{
			alert("Please Select Frequency");
			document.getElementsByName("selFrequencyId")[0].focus();
			return false;
		}
		
		var FreqImageInd = parseInt(document.getElementsByName("frequencyImageIndex")[0].value);
		var freqId=document.getElementsByName("selFrequencyId")[FreqImageInd].value;
		var popupItemTypeId=document.getElementsByName("selDrugItemTypeId")[FreqImageInd].value;
		//if(document.getElementsByName("selIsEmptyStomach")[FreqImageInd].checked)   //commented by manisha gangwar date:13.4.2016 to remove isEMpty Stomach chekcbox
		//{
			//var popupIsEmptyStomach=document.getElementsByName("selIsEmptyStomach")[FreqImageInd].value;
		//}
		//else
		//{
			var popupIsEmptyStomach="off";
		//}
		
		var popupDrugId=document.getElementsByName("selDrugId")[FreqImageInd].value;
		var popupDoseId=document.getElementsByName("selDoseId")[FreqImageInd].value;
		
		var popupDoseName=document.getElementsByName("selDoseName")[FreqImageInd].value;
		var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=DrugTime&popupFreqId='+freqId+'&popupItemTypeId='+popupItemTypeId +'&popupIsEmptyStomach='+popupIsEmptyStomach+'&popupDoseId='+popupDoseId+'&popupRowIndex='+FreqImageInd+'&popupDrugId='+popupDrugId+'&popupDoseName='+popupDoseName;
		child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+550+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	    child.opener = self;
	 
	 	return child
	}
}

function showPrevDrugSchedule(index,hmode)
{	
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=PREVDRUGSHEDULE&scheduleIndex='+index;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
	child.opener = self;
	return child
	}

function validateSheduleRow(index)
{
	if(document.getElementsByName("selDrugName")[index].value=="" || document.getElementsByName("selDrugId")[index].value==0)
	{
		alert("Please Enter Drug Name");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selDrugName")[index].focus();
		return false;
	}
	if(document.getElementsByName("selDoseId")[index].value=="-1")
	{
		alert("Please Enter Dose");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selDoseId")[index].focus();
		return false;
	}		
	if(document.getElementsByName("selDoseName")[index].value=="")
	{
		alert("Please Enter Dose");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selDoseName")[index].focus();
		return false;
	}		
	if(document.getElementsByName("selFrequencyId")[index].value=="-1")
	{
		alert("Please Enter Frequecy");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selFrequencyId")[index].focus();
		return false;
	}
	//if(document.getElementsByName("selDays")[index].value=="")
	//{
	//	alert("Please Enter Days");
	//	setToDesiredTab("tabDrugAdvice");
	//	document.getElementsByName("selDays")[index].focus();
	//	return false;
	//}
	if(document.getElementsByName("selStartDay")[index].value=="")
	{
		alert("Please Enter Start Day");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selStartDay")[index].focus();
		return false;
	}
	
	
		
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#"; // 1 for empty stomach
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#"; // 0 for not empty stomach
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	return true;
}

function validateHrs(Hrs)
{
	if(document.getElementsByName('requirmentTimeHrs')[findIndex(Hrs)].value<0 || document.getElementsByName('requirmentTimeHrs')[findIndex(Hrs)].value>24)
		{
			alert("Hours can be in 0-23");
			document.getElementsByName('requirmentTimeHrs')[findIndex(Hrs)].focus();
			return false;
		}
}

function validateMin(Min)
{
	if(document.getElementsByName('requirmentTimeMin')[findIndex(Min)].value<0 || document.getElementsByName('requirmentTimeMin')[findIndex(Min)].value>59)
		{
			alert("Minut can be in 0-59");
			document.getElementsByName('requirmentTimeMin')[findIndex(Min)].focus();
			return false;
		}
}

	
	



function showOtherInstruction(index,hmode)
{	
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=OTHERINSTRUCTION';
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function showLog(index,hmode)
{	
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=LOGDETAIL';
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+500+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function showPrint()
{	
	var empName=document.getElementsByName("empName")[0].value;
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=PRINT&empName='+empName;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+500+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}



function showDrugInstruction(index)
{
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=DRUGINSTRUCTION&drugInstructionIndex='+index;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}




function showRevRmarks(index)
{
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=REVOKEREMARKS&drugInstructionIndex='+index;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}
function showRevokeRemarks(obj,index)
{
	if(obj.checked)
      document.getElementById('revokeRem'+index).style.display="";
    else
      document.getElementById('revokeRem'+index).style.display="none";
}
</script>
<STYLE TYPE="text/css">
	.highlight {
		line-height: 16px;
		font-weight: bold;
		font-family: arial;
		font-size: 11px;
		padding-top: 1px;
		vertical-align: middle;
		padding-left: 6px;
   		/*background:#FFEBD5;*/
   		background:#96BAEA;
   		   		
   		border-style:solid;
		border-width:1px;
		
	
   		}
</STYLE>
<STYLE TYPE="text/css">
	.dehighlite {
	font-family: verdana, arial, sans-serif;
    font-size:12px;
    text-decoration: none;
    color: black;
   /* background-color: #FFEBD5;*/
    background-color: #E0EBEB;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>



<%-- <%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
 --%><%-- <html:hidden name="EHRSection_TreatmentFB" property="entryDate" value="<%=sysDate%>"/> --%>

 <his:statusTransactionInProcess> 
		
	
		<%-- <html:hidden name="EHRSection_TreatmentFB" property="drugDetailRows" /> --%>
		<table class="ContentTagSideLine" id="" width="100%" cellpadding="1" cellspacing="1" style="margin:1%">
			<tr>
				<td width="5%"  class="">
					<div align="center">
					<input type ="checkbox" name = "selectChk" checked>	
 				</div>
				</td>
				<td width="15%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>
						<font color="#FF0000">*</font>
						Type
						</b>
						</font>
					</div>
				</td>
				<td width="27%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <b><bean:message key="diagnosisName"/></b> --%>
							<b>
							<font color="#FF0000">*</font>
							Treatment
							</b>
						</font>
					</div>
				</td>
				<td width="27%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<%-- <b><bean:message key="diagonosisType"/></b> --%>
							<b>Given On</b>
						</font>
					</div>
				</td>
				<td width="22%"  class="">
					<div align="center">	           
						<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="remarks"/></b>
						</font>
					</div>
				</td>
				<td width="4%" class="">
				   <div align="center">
				   </div>
				</td>
			</tr>
		</table>
		  <%-- <table width="100%" cellpadding="0" cellspacing="0">
		   <logic:notEmpty name="<%=EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS%>">
		    <logic:iterate id="EHRSection_TreatmentGivenVO" name="<%=EHRConfig.EHR_PAT_TREATMENT_GIVEN_DETAILS%>" indexId="id" type="ehr.treatmentgiven.vo.EHRSection_TreatmentGivenVO" >
		      <tr id="treatmentGivenRow#"> 
		       <td width="5%"  class="" nowrap="nowrap">
						<div align="center">	   
						<html:checkbox name="EHRSection_DiagnosisFB" property="selectedRow" value="<%=id.toString()%>" ></html:checkbox>
                        <input type="checkbox" name="ehrcomp_TREATMENTGIVEN_chk_select" checked="checked"   value="<%=EHRSection_TreatmentGivenVO.getSelectedSectionData()%>" onchange="setPatDocSelectJSON('TREATMENTGIVEN')"  />
						<input type="hidden" name="ehrcomp_TREATMENTGIVEN_dataelem_json_<%=EHRSection_TreatmentGivenVO.getSelectedSectionData()%>" value='<%=EHRSection_TreatmentGivenVO.getJSONObj().toString() %>'  />         
 						</div>
					</td>
					<td width="15%"  class="" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_TreatmentGivenVO" property="treatmentType" />
								<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentType" value="<%=EHRSection_TreatmentGivenVO.getTreatmentType() %>"/>
							</font>
						</div>
					</td>
					 <td width="27%"  class="" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_TreatmentGivenVO" property="snomedCTTreatmentName" />
								<html:hidden name="EHRSection_TreatmentGivenFB" property="snomedCTTreatmentName" value="<%=EHRSection_TreatmentGivenVO.getSnomedCTTreatmentName() %>"/>
							</font>
						</div>
					</td>
					<td width="27%"  class="" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_TreatmentGivenVO" property="treatmentDate" />
								<html:hidden name="EHRSection_TreatmentGivenFB" property="treatmentDate" value="<%=EHRSection_TreatmentGivenVO.getTreatmentDate() %>"/>
							</font>
						</div>
					</td> 
					<td width="22%"  class="" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="EHRSection_TreatmentGivenVO" property="remarks" />
								<html:hidden name="EHRSection_TreatmentGivenFB" property="remarks" value="<%=EHRSection_TreatmentGivenVO.getRemarks() %>"/>
							</font>
						</div>
					</td> 
				</tr>	    
		    </logic:iterate>
		    </logic:notEmpty>
		</table>   --%>



<%-- <html:hidden name="EHRSection_TreatmentFB" property="summary" />
<html:hidden name="EHRSection_TreatmentFB" property="indexDelete" />
<html:hidden name="EHRSection_TreatmentFB" property="hmode" />
<html:hidden name="EHRSection_TreatmentFB" property="patCrNo" />
<html:hidden name="EHRSection_TreatmentFB" property="index" />

<html:hidden name="EHRSection_TreatmentFB" property="episodeCode"/>
<html:hidden name="EHRSection_TreatmentFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_TreatmentFB" property="admissionNo"/>
<html:hidden name="EHRSection_TreatmentFB" property="activeTab"/>

<html:hidden name="EHRSection_TreatmentFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_TreatmentFB" property="frequencyImageIndex"/>
<html:hidden name="EHRSection_TreatmentFB" property="emptyStomachIndexArray"/>
<html:hidden name="EHRSection_TreatmentFB" property="hiddenPatDeadStatus"/>
<html:hidden name="EHRSection_TreatmentFB" property="sysDate"/> 
<html:hidden name="EHRSection_TreatmentFB" property="prescriptionDate"/> 
<html:hidden name="EHRSection_TreatmentFB" property="empName"/> 
<html:hidden name="EHRSection_TreatmentFB" property="opdIpdFlag"/>
<html:hidden name="EHRSection_TreatmentFB" property="sysDateForValidation"/> 
<html:hidden name="EHRSection_TreatmentFB" property="deskType"/> 
<html:hidden name="EHRSection_TreatmentFB" property="drugListId" />
<html:hidden name="EHRSection_TreatmentFB" property="isPregnantFlag" />
<html:hidden name="EHRSection_TreatmentFB" property="drugAdminId" />
<html:hidden name="EHRSection_TreatmentFB" property="drugAdminName" />
<html:hidden name="EHRSection_TreatmentFB" property="isSetTREATMENT"/>
<input type="hidden" value="<%=request.getContextPath()%>" name="path">
 --%>
<script type="text/javascript">
	TDSetDataExist();

</script> --%>
</his:statusTransactionInProcess>




