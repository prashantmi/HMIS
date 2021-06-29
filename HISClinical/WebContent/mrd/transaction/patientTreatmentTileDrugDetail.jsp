
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<%@page import="opd.transaction.controller.fb.PatientTreatmentDetailFB"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<his:javascript src="/opd/js/desk_treatment_detail.js" />
<his:javascript src="/opd/js/wz_tooltip.js" />

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
<!--

//-->
function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function showInstruction(event,index)
{	
		var instruction=document.getElementsByName("prevSelInstructionArray")[index].value;
		var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=INSTRUCTION&popupInstruction='+instruction;
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
	/*
	if(document.getElementsByName("extension")[index].checked)
	{
		
		document.getElementsByName("extensionDays")[index].disabled=false;
	}
	else
	{
		document.getElementsByName("extensionDays")[index].value="";
		document.getElementsByName("extensionDays")[index].disabled=true;
	}	
	*/
	
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
		if(document.getElementsByName("selIsEmptyStomach")[FreqImageInd].checked)
		{
			var popupIsEmptyStomach=document.getElementsByName("selIsEmptyStomach")[FreqImageInd].value;
		}
		else
		{
			popupIsEmptyStomach="off";
		}
		
		var popupDrugId=document.getElementsByName("selDrugId")[FreqImageInd].value;
		var popupDoseId=document.getElementsByName("selDoseId")[FreqImageInd].value;
		
		var popupDoseName=document.getElementsByName("selDoseName")[FreqImageInd].value;
		var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DrugTime&popupFreqId='+freqId+'&popupItemTypeId='+popupItemTypeId +'&popupIsEmptyStomach='+popupIsEmptyStomach+'&popupDoseId='+popupDoseId+'&popupRowIndex='+FreqImageInd+'&popupDrugId='+popupDrugId+'&popupDoseName='+popupDoseName;
		child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	    child.opener = self;
	 
	 	return child
	}
}

function showPrevDrugSchedule(index,hmode)
{	
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=PREVDRUGSHEDULE&scheduleIndex='+index;
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
	if(document.getElementsByName("selDays")[index].value=="")
	{
		alert("Please Enter Days");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selDays")[index].focus();
		return false;
	}
	if(document.getElementsByName("selStartDay")[index].value=="")
	{
		alert("Please Enter Start Day");
		setToDesiredTab("tabDrugAdvice");
		document.getElementsByName("selStartDay")[index].focus();
		return false;
	}
	
	/*
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	var drugId = document.getElementsByName("selDrugId")[index].value;
	
	for(var j=0;j<rowCount-1;j++)
	{
		if(index!=j)
		{
			if(document.getElementsByName("selDrugId")[j].value==drugId)
			{
				alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");	
				setToDesiredTab("tabDrugAdvice");
				document.getElementsByName("selDrugName")[j].focus();
				return false;
			}
		}
	}
		*/
	/*
	for(var i=0;i<rowCount-1;i++)
	{
		var drugId = document.getElementsByName("selDrugId")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
		{
			if(index!=j)
			{
				if(document.getElementsByName("selDrugId")[j].value==drugId)
				{
					alert(document.getElementsByName("selDrugName")[j].value+" is Already Added");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[j].focus();
					return false;
				}
			}
			
	     }
	}
	*/
	
	//var length=document.getElementsByName("prevDrugName").length;
	<%
	//List lst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST);
	//if(lst==null || lst.size()==0)
	//{
	%>
		//for(var i=0;i<rowCount;i++)
		//{
		//	for(var j=0;j<length;j++)
			//{
				//alert("prev drug name "+document.getElementsByName("selDrugName")[j].value);
				//if(document.getElementsByName("selDrugName")[i].value==document.getElementsByName("prevDrugName")[j].value)
				//{
				//	alert(document.getElementsByName("selDrugName")[i].value+" is Already Given");	
				//	setToDesiredTab("tabDrugAdvice");
					//document.getElementsByName("selDrugName")[i].focus();
					//return false;
				//}
			//}
		 //}	
	<%	
	//}
	%>
		
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
function activateTab(tabObj){
	<%
		PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)pageContext.findAttribute("PatientTreatmentDetailFB");
		if(fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
	%>
		var divDrug = document.getElementById('divDrugAdvice');
		var divDiet = document.getElementById('divDietAdvice');
		var divLA = document.getElementById('divLAAdvice'); 
	
	//alert("divDiet "+divDiet+"divDrug "+divDrug+"divLA "+divLA+"divRest "+divRest);
	if(typeof divDrug != 'undefined' && typeof divDiet != 'undefined' 
		&& typeof divLA != 'undefined' )
	{
		//alert("tabObj.id "+tabObj.id);
		if(tabObj.id=="tabDrugAdvice")
		{
			
			document.getElementById('tabDrugAdvice').className="highlight";
			document.getElementById('tabDietAdvice').className="dehighlite";
			document.getElementById('tabLAAdvice').className="dehighlite";
			
			
			divDrug.style.display="block";
			divDiet.style.display="none";
			divLA.style.display="none";
			
		}
		else if(tabObj.id=="tabDietAdvice")
		{
			document.getElementById('tabDrugAdvice').className="dehighlite";
			document.getElementById('tabDietAdvice').className="highlight";
			document.getElementById('tabLAAdvice').className="dehighlite";
			
			
			divDrug.style.display="none";
			divDiet.style.display="block";
			divLA.style.display="none";
			
		}
		else if(tabObj.id=="tabLAAdvice")
		{
			document.getElementById('tabDrugAdvice').className="dehighlite";
			document.getElementById('tabDietAdvice').className="dehighlite";
			document.getElementById('tabLAAdvice').className="highlight";
			
			
			divDrug.style.display="none";
			divDiet.style.display="none";
			divLA.style.display="block";
			
					
		}
		
	}
	<%		
		}
		else
		{
	%>
			
	var divDrug = document.getElementById('divDrugAdvice');
	var divDiet = document.getElementById('divDietAdvice');
	var divLA = document.getElementById('divLAAdvice'); 
	var divRest = document.getElementById('divRestAdvice'); 
	//alert("divDiet "+divDiet+"divDrug "+divDrug+"divLA "+divLA+"divRest "+divRest);
	if(typeof divDrug != 'undefined' && typeof divDiet != 'undefined' 
		&& typeof divLA != 'undefined' && typeof divRest != 'undefined')
	{
		//alert("tabObj.id "+tabObj.id);
		if(tabObj.id=="tabDrugAdvice")
		{
			
			document.getElementById('tabDrugAdvice').className="highlight";
			document.getElementById('tabDietAdvice').className="dehighlite";
			document.getElementById('tabLAAdvice').className="dehighlite";
			document.getElementById('tabRestAdvice').className="dehighlite";
			
			divDrug.style.display="block";
			divDiet.style.display="none";
			divLA.style.display="none";
			divRest.style.display="none";
		}
		else if(tabObj.id=="tabDietAdvice")
		{
			document.getElementById('tabDrugAdvice').className="dehighlite";
			document.getElementById('tabDietAdvice').className="highlight";
			document.getElementById('tabLAAdvice').className="dehighlite";
			document.getElementById('tabRestAdvice').className="dehighlite";
			
			divDrug.style.display="none";
			divDiet.style.display="block";
			divLA.style.display="none";
			divRest.style.display="none";
		}
		else if(tabObj.id=="tabLAAdvice")
		{
			document.getElementById('tabDrugAdvice').className="dehighlite";
			document.getElementById('tabDietAdvice').className="dehighlite";
			document.getElementById('tabLAAdvice').className="highlight";
			document.getElementById('tabRestAdvice').className="dehighlite";
			
			divDrug.style.display="none";
			divDiet.style.display="none";
			divLA.style.display="block";
			divRest.style.display="none";
					
		}
		
		else if(tabObj.id=="tabRestAdvice")
		{
			document.getElementById('tabDrugAdvice').className="dehighlite";
			document.getElementById('tabDietAdvice').className="dehighlite";
			document.getElementById('tabLAAdvice').className="dehighlite";
			document.getElementById('tabRestAdvice').className="highlight";
			
			divDrug.style.display="none";
			divDiet.style.display="none";
			divLA.style.display="none";
			divRest.style.display="block";
		}
		
	}	
	<%
		}
	%>
	
	
}
window.onload=function()
{
	//for highliting default drug advice tab
	<%
		fb=(PatientTreatmentDetailFB)pageContext.findAttribute("PatientTreatmentDetailFB");
		if(fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
		{
	%>
		document.getElementById('tabDrugAdvice').className="highlight";
		document.getElementById('tabDietAdvice').className="dehighlite";
		document.getElementById('tabLAAdvice').className="dehighlite";
	<%		
		}
		else
		{
	%>
		document.getElementById('tabDrugAdvice').className="highlight";
		document.getElementById('tabDietAdvice').className="dehighlite";
		document.getElementById('tabLAAdvice').className="dehighlite";
		document.getElementById('tabRestAdvice').className="dehighlite";		
	<%
		}
	%>
	
	activateTab(document.getElementById('<bean:write name="PatientTreatmentDetailFB" property="activeTab"/>'));
	var revokeArray=document.getElementsByName("revokeHidden")[0].value.split("#");
	var revoke=document.getElementsByName("revoke");
			 
	for(i=0;i<revokeArray.length;i++)
	{
		for(j=0;j<revoke.length;j++)
		{
			if(revokeArray[i]==revoke[j].value.split("$")[0]+"$"+revoke[j].value.split("$")[2]+"$"+revoke[j].value.split("$")[3])
			{
				revoke[j].checked=true;
			}
		}
	}
	
	/*
	
	// this is for extension
	var extensionArray=document.getElementsByName("extensionHidden")[0].value.split("#");
	var extension=document.getElementsByName("extension");
	var extensionDaysArray=document.getElementsByName("extensionDaysHidden")[0].value.split("#");
			
	for(i=0;i<extensionArray.length;i++)
	{
		for(j=0;j<extension.length;j++)
		{
			if(extensionArray[i]==extension[j].value.split("$")[0]+"$"+extension[j].value.split("$")[2]+"$"+extension[j].value.split("$")[3])
			{
				extension[j].checked=true;
				document.getElementsByName("extensionDays")[j].disabled=false;
				document.getElementsByName("extensionDays")[j].value=extensionDaysArray[i];
				
			}
		}
	}
	*/
}

/*
//for rxExtenstion
function validateCheck(obj)
{
	var index=obj.value.split("$")[1];
	var extLen=document.getElementsByName("extension").length;
	var rxContinueLen=document.getElementsByName("rxContinue").length;
	
	if(extLen==rxContinueLen)
	{
		if(document.getElementsByName("extension")[index].checked || document.getElementsByName("rxContinue")[index].checked)
		{
			alert("You can only select one check box in a row");
			document.getElementsByName("revoke")[index].checked=false;
			return false;
		}
	}
}
*/

function showOtherInstruction(index,hmode)
{	
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=OTHERINSTRUCTION';
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function showLog(index,hmode)
{	
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=LOGDETAIL';
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
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=PRINT&empName='+empName;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+500+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function deleteDrugRow(hmode,index)
{
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	var emptyStomachIndex="";
	for(i=0;i<len;i++)
	{
		if(document.getElementsByName("selIsEmptyStomach")[i].checked)
		{	
			emptyStomachIndex=emptyStomachIndex+"1"+"#";
		}
		else
		{
			emptyStomachIndex=emptyStomachIndex+"0"+"#";
		}
	}
	document.getElementsByName("emptyStomachIndexArray")[0].value=emptyStomachIndex;
	
	document.getElementsByName("indexDelete")[0].value=index;
	submitPage(hmode);
}

function deleteExtRow(hmode,index)
{
	document.getElementsByName("indexDelete")[0].value=index;
	submitPage(hmode);
}


function showDrugInstruction(index)
{
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DRUGINSTRUCTION&drugInstructionIndex='+index;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function showRevRmarks(index)
{
	var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DRUGINSTRUCTION&drugInstructionIndex='+index;
	child = window.open(path,'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}
function showRevokeRemarks(obj)
{
	if(obj.checked)
      document.getElementById('tabDrugAdvice').style.display="";
    else
      document.getElementById('tabDrugAdvice').style.display="none";
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
   		background:#FFEBD5;
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
    background-color: #FFEBD5;
    white-space: nowrap;
    background:#F1ECE2;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>

<his:TransactionContainer>
<his:TitleTag>
	<his:name>
		<bean:message key="pattreatmentdtl" />
		
	</his:name>
</his:TitleTag>

<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
<html:hidden name="PatientTreatmentDetailFB" property="entryDate" value="<%=sysDate%>"/>

<his:statusTransactionInProcess>

	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<%
			fb=(PatientTreatmentDetailFB)pageContext.findAttribute("PatientTreatmentDetailFB");
			if(fb.getDeskType().equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
			%>
				<td id="tabDrugAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center" >Drug Advice</div></td>
				<td id="tabLAAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Other Advice</div></td>
				<td id="tabDietAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Diet Advice</div></td>
			<%		
				}
				else
				{
			%>
				<td id="tabDrugAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center" >Drug Advice</div></td>
				<td id="tabLAAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Other Advice</div></td>
				<td id="tabDietAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Diet Advice</div></td>
				<td id="tabRestAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Rest Advice</div></td>		
			<%
				}
			%>
		</tr>
	</table>
	
	<div id="divDrugAdvice" style="display: block;">
		<his:ContentTag>
						
			<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>
			<bean:define id="prescriptionDate" name="PatientTreatmentDetailFB" property="prescriptionDate"></bean:define>
			<bean:define id="opdIpdFlag" name="PatientTreatmentDetailFB" property="opdIpdFlag"></bean:define>
			<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_ALL_DRUG_DETAIL_LIST_FOR_LOG%>">
				
				<%if(opdIpdFlag.equals(OpdConfig.IS_OPD_TREATMENT))
				{
				%>
				<his:SubTitleTag name="Last Prescription Date (OPD)">
					<div  style="position: absolute;left: 26%;">
						<%=prescriptionDate %>
					</div>
				<a style="cursor:pointer" onclick="showLog(event)" >
		      		<bean:message key="prevAllDrugAdviceDetail"/>
		      	</a>
				</his:SubTitleTag>
				<%	
				}
				else
				{
				%>
				<his:SubTitleTag name="Last Prescription Date (Discharge)">
					<div  style="position: absolute;left: 28%;">
						<%=prescriptionDate %>
					</div>
				<a style="cursor:pointer" onclick="showLog(event)" >
		      		<bean:message key="prevAllDrugAdviceDetail"/>
		      	</a>
				</his:SubTitleTag>
				<%	
				}
				%>
				
			</logic:notEmpty>
			
			<table id="prevDrugDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			
			<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>">
				<tr>
					
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="frequency" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="intakeDays" />
								</b>
							</font>
						</div>
					</td>
					<td width="13%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="startDate" />
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="endDate" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="schedule" />
								</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="revoke" />
							</b>
						</font>
					</div>
				</td>

				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="change" />
							</b>
						</font>
					</div>
				</td>
					
				</tr>
				<logic:iterate id="drugDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
				<%String ind=idx.toString(); %>
				<tr id="trSpecimen">

					<td width="15%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="drugName"/>
									<html:hidden name="PatientTreatmentDetailFB" property="prevDrugName" value="<%=drugDtlVO.getDrugName() %>"/>
									
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="doseName"/>
									<html:hidden name="PatientTreatmentDetailFB" property="previousDoseId" value="<%=drugDtlVO.getDoseId() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="7%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="frequencyName"/>
								<html:hidden name="PatientTreatmentDetailFB" property="previousFrequencyId" value="<%=drugDtlVO.getFrequencyId() %>"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="7%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="intakeDays"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="13%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="startDate"/>
									<html:hidden name="PatientTreatmentDetailFB" property="selStartDate" value="<%=drugDtlVO.getStartDate() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="18%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="endDate"/>
									<html:hidden name="PatientTreatmentDetailFB" property="selEndDate" value="<%=drugDtlVO.getEndDate() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<img name="prevDrugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showPrevDrugSchedule(<%=ind %>,'PREVDRUGSHEDULE')" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
<!--									<bean:write name="drugDtlVO" property="remarks"/>-->

									<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Instruction " onclick ="showInstruction(event,<%=ind %>)" onkeypress="if(event.keyCode==13) showInstruction(event,<%=ind %>);">

									<html:hidden name="PatientTreatmentDetailFB" property="prevSelInstructionArray" value="<%=drugDtlVO.getRemarks()%>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="center">
							<logic:notEqual value="before" name="drugDtlVO" property="date">
								<html:checkbox property="revoke" name="PatientTreatmentDetailFB" value='<%=drugDtlVO.getDrugId()+"$"+ind +"$"+drugDtlVO.getFrequencyId()+"$"+drugDtlVO.getDoseId()%>' onclick="showRevokeRemarks(this)" ></html:checkbox>      <!-- onclick="validateCheck(this)" -->
								<html:hidden property="revokeHidden" name="PatientTreatmentDetailFB"/>
							</logic:notEqual>
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="center" id="revokeRem" style="display:none">
							<logic:notEqual value="before" name="drugDtlVO" property="date">
								<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Revoke Remarks " onclick ="showRevRmarks(<%=ind %>)" onkeypress="if(event.keyCode==13) showRevRmarks(<%=ind %>);">
							</logic:notEqual>
						</div>
					</td>					
					<% String temp="DelRowFromTableForRxContinue('RxContinueDelRowFromTable',"+idx+")"; %>
					<td width="5%" class="tdfont">
						<div align="center">
							<logic:notEqual value="before" name="drugDtlVO" property="date">
								<html:checkbox property="rxContinue" name="PatientTreatmentDetailFB"  onclick="<%=temp %>"></html:checkbox>
							</logic:notEqual>
						</div>
					</td>
				

					
					
					
				</tr>
				</logic:iterate>
				
		</logic:notEmpty>
		
		</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="Drug Advice Detail">
			<table  width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td>
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<a style="cursor:pointer" onclick="showOtherInstruction(event)" >
	      							<bean:message key="otherInstruction"/>
	      						</a>
							</b>
						</font>		
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="right">
						<img class="button" style="cursor: pointer" alt="Search" title="Search"	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup('/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=SEARCHDRUG',event,500,800);">
					</div>
				</td>
			</tr>
		</table>
				
		</his:SubTitleTag>
		<his:ContentTag>
			<html:hidden name="PatientTreatmentDetailFB" property="drugDetailRows" />
		<table id="tblDrugDetailId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			<tr>
				<td width="15%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="drugname" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosage" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="frequency" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				
				<td class="tdfonthead" width="10%" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="emptyStomach"/>
								</b>
							</font>	
						</div>
					</td>
				<td width="20%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="drugRoute" />
							</b>
						</font>
					</div>
				</td>
				<td width="20%" class="tdfonthead" nowrap="nowrap">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="instructions" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="quantity" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="5%" class="tdfonthead">
				</td>
			</tr>
			<%int countNew=0;%>
			<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
			
			<tr id="trRowID<%=i%>">
			<logic:notEqual value="modify" name="drugDtlVO" property="rxContinueFlag">
			<%countNew++;%>
				<td width="15%" class="tdfont">
					<div align="center">
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<%String temp="fillData("+i+")"; %>
						<html:text name="PatientTreatmentDetailFB" property="selDrugName" maxlength="100" size="25" tabindex="1" 
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onblur="callOnBlur()"
							onfocus="<%=temp %>"
							></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0"+"^"+"0"+"^"+"0")) { %>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="PatientTreatmentDetailFB" property="selDoseName" tabindex="1" 
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" ></html:text>
						<% } else { %>
							<html:select name="PatientTreatmentDetailFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  onchange="showQuantity(<%=i %>)">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
					
						<html:select name="PatientTreatmentDetailFB" property="selFrequencyId" tabindex="1" 
							value="<%=drugDtlVO.getFrequencyId() %>" >
							<html:option value="-1">Select</html:option >
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
						
						<html:hidden name="PatientTreatmentDetailFB" property="endDate" value=""/>
					</div>
					<html:hidden  name="PatientTreatmentDetailFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
					<html:hidden  name="PatientTreatmentDetailFB" property="changeStartDate" value=""/>
				</td>
				</logic:notEqual>
				<logic:equal value="modify" name="drugDtlVO" property="rxContinueFlag">
				<td width="25%" class="tdfont">
					<div align="center">
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<%String temp="fillData("+i+")"; %>
						<html:text name="PatientTreatmentDetailFB" property="selDrugName" maxlength="100" size="25" tabindex="1"  readonly="true"
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onblur="callOnBlur()"
							onfocus="<%=temp %>"
							></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0")) { %>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="PatientTreatmentDetailFB" property="selDoseName" tabindex="1" 
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" ></html:text>
						<% } else { %>
							<html:select name="PatientTreatmentDetailFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  onchange="showQuantity(<%=i %>)">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="PatientTreatmentDetailFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="PatientTreatmentDetailFB" property="selFrequencyId" tabindex="1" 
							value="<%=drugDtlVO.getFrequencyId() %>" >
							<html:option value="">Select</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
						<html:hidden name="PatientTreatmentDetailFB" property="endDate" value=""/>
					</div>
					<html:hidden  name="PatientTreatmentDetailFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
					<html:hidden  name="PatientTreatmentDetailFB" property="changeStartDate" value="<%=drugDtlVO.getChangeStartDate() %>"/>
					
				</td>
				</logic:equal>
				<td width="7%" class="tdfont">
					<div align="center">
						<html:text name="PatientTreatmentDetailFB" property="selDays" maxlength="2" size="3" tabindex="1" 
							value="<%=drugDtlVO.getDays() %>" onkeypress="return validateNumberic(event)" ></html:text>
					</div>
				</td>
							
				<td width="7%" class="tdfont">
					<div align="center">
					
						<html:text name="PatientTreatmentDetailFB" property="selStartDay" maxlength="2" size="3" tabindex="1" 
							value="<%=drugDtlVO.getStartDate() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					
					
					</div>
				</td>
				<td class="tdfont" width="10%" id="firstEmptyStomach">
					<div align="center">
						<%String isEmptyStomach=drugDtlVO.getIsEmptyStomach();
						if(isEmptyStomach.equals("1")){	%>
						<input type="checkbox" name="selIsEmptyStomach" checked="checked" />
						<% } else { %>
						<input type="checkbox" name="selIsEmptyStomach"  />
						<% } %>
						<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div  align="center">
						<html:select name="PatientTreatmentDetailFB" property="selDrugRouteId" tabindex="1" value="<%=drugDtlVO.getDrugRouteId() %>"  onchange="setDrugRouteName(findIndex(this))" style="width: 100px;" >	
						<logic:notEmpty name="drugDtlVO" property="drugRouteId">
							
							<html:option value="<%=drugDtlVO.getDrugRouteId() %>">
								<bean:write name="drugDtlVO" property="drugRouteName"/> 
							</html:option>	
						</logic:notEmpty>
						<logic:empty name="drugDtlVO" property="drugRouteId">
							<html:option value="">Select Value</html:option>
						</logic:empty>
						</html:select>
						<html:hidden name="PatientTreatmentDetailFB" property="selDrugRouteName" value="<%=drugDtlVO.getDrugRouteName()%>"></html:hidden>	
					</div>
				</td>
				
				<td width="20%" class="tdfont">
					<div align="center">
						<html:hidden name="PatientTreatmentDetailFB" property="selInstructions" value="<%=drugDtlVO.getRemarks()%>"></html:hidden>
					
					<img name="drugInstruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Instruction " onclick ="showDrugInstruction(findIndex(this))" onkeypress="if(event.keyCode==13) showDrugInstruction(findIndex(this));" id="drugInstructionButton">
					
					<html:hidden name="PatientTreatmentDetailFB" property="serealNo" value="<%=drugDtlVO.getSerialNo() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="todayVisitFlag" value="<%=drugDtlVO.getTodayVisitFlag() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevEntryDate" value="<%=drugDtlVO.getEntryDate() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevDoseQty" value="<%=drugDtlVO.getDoseQty() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevIssueQty" value="<%=drugDtlVO.getIssueQty() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevRequiredQty" value="<%=drugDtlVO.getRequiredQty() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevEndDate" value="<%=drugDtlVO.getEndDate()%>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevStartDate" value="<%=drugDtlVO.getChangeStartDate() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevDoseId" value="<%=drugDtlVO.getDoseId() %>"/>
					<html:hidden name="PatientTreatmentDetailFB" property="prevFreqId" value="<%=drugDtlVO.getFrequencyId() %>"/>
					</div>
				</td>
				<td width="5%" class="tdfont">
					<div align="center">
					 <% boolean readonly=false;
					 if(drugDtlVO.getQuantity().equals("")){ 
						 readonly=true;
					 }
					 %>
						<html:text name="PatientTreatmentDetailFB" property="quantity" maxlength="2" size="3" tabindex="1" 
							 onkeypress="return validateNumberWithoutZeroOnly(event)" readonly="<%=readonly %>" value="<%=drugDtlVO.getQuantity() %>"></html:text>
					</div>
				</td>
				<td width="5%" class="tdfont" id="tdButtonPlus">
					<%if(countNew==1){%>
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/sign-plus.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRows(),'ADDDRUGROW');" onclick="submitFormOnValidate(validateRows(),'ADDDRUGROW');">
					<% } else { %>
						<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteDrugRow('DELETEDRUGROW',<%=i %>)" onclick="deleteDrugRow('DELETEDRUGROW',<%=i %>)">
					<%} %>
				</td>
			</tr>
			</logic:iterate>
		</table>
		<bean:define id="summaryId" name="PatientTreatmentDetailFB" property="summary" type="java.lang.String"></bean:define>
		<div id="divSummary" >
			<table width="100%">
			
				<bean:write name="PatientTreatmentDetailFB" property="summary" filter="false"/>
			
			</table>
		</div>
		</his:ContentTag>
	</div>
	
	<div id="divDietAdvice" style="display: none;">
		<jsp:include page="/opd/transaction/dietAdviceDtl.jsp"></jsp:include>
	</div>
	
	<div id="divLAAdvice" style="display: none;">
		<jsp:include page="/opd/transaction/LAAdviceDetail.jsp"></jsp:include>	
	</div>
	
	<div id="divRestAdvice" style="display: none;">
		<jsp:include page="/opd/transaction/restAdviceDetail.jsp"></jsp:include>		
	</div>

</his:statusTransactionInProcess>
<his:ContentTag>

</his:ContentTag>
<his:ButtonToolBarTag>
	<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onclick="submitFormOnValidate(validateRowsSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateRowsSave(),'SAVE');")>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<!-- <img class='button' src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style="cursor: pointer" tabindex="1" onclick="showPrint()" onkeypress="if(event.keyCode==13) showPrint()")> -->
</his:ButtonToolBarTag>
</his:TransactionContainer>

<div id="divDropDown" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="cboDropDown" size="10" tabindex="1" onKeyDown="onKeyDownDrop(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
</div>

<div id="divDropDownExt" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="cboDropDownExt" size="10" tabindex="1" onKeyDown="onKeyDownDropExt(event)" onchange="onChangeDropExt()" 
		ondblclick="setClickedValueExt()" onclick="setClickedValueExt()">
		<option value="-1"></option>
	</select>
</div>


<div id="divDrugList" style="display: none; position: absolute;">
	<select name="drugList" id="drugList" multiple="multiple" size="4">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUGS%>">
			<logic:iterate id="objEntry" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUGS%>" type="hisglobal.utility.Entry">
				<option value="<%=objEntry.getValue()%>"> <%=objEntry.getLabel()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>

<div id="divDrugDoseList" style="display: none; position: absolute;">
	<select name="drugDoseList" id="drugDoseList">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>">
			<logic:iterate id="voDrugDose" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES%>" type="hisglobal.vo.DrugDoseVO">
				<%String valDrugDose = voDrugDose.getDoseId()+"^"+voDrugDose.getIsFrequencyBound()+"^"+voDrugDose.getDoseQty()+"#"+voDrugDose.getItemTypeId(); %> 			
				<option value="<%=valDrugDose%>"> <%=voDrugDose.getDoseName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>

<div id="divDrugRouteList" style="display: none; position: absolute;">
	<select name="drugRouteList" id="drugRouteList">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE%>">
			<logic:iterate id="voDrugRoute" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE%>" type="hisglobal.vo.DrugRouteMstVO">
				<%String valDrugRoute = voDrugRoute.getDrugRouteId()+"#"+voDrugRoute.getItemTypeId(); %> 			
				<option value="<%=valDrugRoute%>"> <%=voDrugRoute.getDrugRouteName()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>


<html:hidden name="PatientTreatmentDetailFB" property="summary" />
<html:hidden name="PatientTreatmentDetailFB" property="indexDelete" />
<html:hidden name="PatientTreatmentDetailFB" property="hmode" />
<html:hidden name="PatientTreatmentDetailFB" property="patCrNo" />
<html:hidden name="PatientTreatmentDetailFB" property="index" />
<html:hidden name="PatientTreatmentDetailFB" property="extIndex" />
<html:hidden name="PatientTreatmentDetailFB" property="episodeCode"/>
<html:hidden name="PatientTreatmentDetailFB" property="episodeVisitNo"/>
<html:hidden name="PatientTreatmentDetailFB" property="admissionNo"/>
<html:hidden name="PatientTreatmentDetailFB" property="activeTab"/>

<html:hidden name="PatientTreatmentDetailFB" property="departmentUnitCode"/>
<html:hidden name="PatientTreatmentDetailFB" property="frequencyImageIndex"/>
<html:hidden name="PatientTreatmentDetailFB" property="emptyStomachIndexArray"/>
<html:hidden name="PatientTreatmentDetailFB" property="hiddenPatDeadStatus"/>
<html:hidden name="PatientTreatmentDetailFB" property="sysDate"/> 
<html:hidden name="PatientTreatmentDetailFB" property="prescriptionDate"/> 
<html:hidden name="PatientTreatmentDetailFB" property="empName"/> 
<html:hidden name="PatientTreatmentDetailFB" property="opdIpdFlag"/>
<html:hidden name="PatientTreatmentDetailFB" property="sysDateForValidation"/> 
<html:hidden name="PatientTreatmentDetailFB" property="deskType"/> 
<html:hidden name="PatientTreatmentDetailFB" property="drugListId" />


