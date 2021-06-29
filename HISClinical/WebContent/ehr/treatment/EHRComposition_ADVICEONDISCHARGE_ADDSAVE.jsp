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
<%@page import="ehr.treatmentdetail.dataentry.EHRSection_TreatmentFB"%>
<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="java.util.List"%>
<%@page import="ehr.treatmentdetail.vo.EHRSection_TreatmentVO"%>
<his:javascript src="/ehr/js/EHR_spp_treatment.js" />
<%-- <his:javascript src="/emr/dataentry/spp/js/EHR_uni_page_prescription.js" />    --%>
<%-- <his:javascript src="/opd/js/desk_treatment_detail.js" /> --%>
<his:javascript src="/hisglobal/js/wz_tooltip.js" />

<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" />



<%@ page buffer="none" %>


<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
</head>

<script type="text/javascript">

$(document).ready(function(){ 
		ShowExternalDiv('1');
	
	
});



</script>

<script type="text/javascript">

//function treatmentOnload()
//{
	//alert("neha")
//	TDonLoad();
// }

//Added by Vasu on 11.Feb.2019 to Validate Treatment Advice
function validate_ENC_MED_ADV()
{
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	//for(var i=0;i<rowCount;i++)
	//{
		/* if(document.getElementsByName("selDrugName")[0].value!="" || document.getElementsByName("selDrugId")[0].value!=0)
		{
			alert("Please Enter Drug Name");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDrugName")[0].focus();
			return false;
		} */
		while(document.getElementsByName("selDrugName")[0].value!="" || document.getElementsByName("selDrugId")[0].value!=0)
           {
		if(document.getElementsByName("selDoseId")[0].value=="-1")
		{
			alert("Please Enter Dose");
			//alert("Please press + icon to add Drug Advice");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseId")[0].focus();
			return false;
		}
			
		if(document.getElementsByName("selDoseName")[0].value=="")
		{
			//alert("Please press + icon to add Drug Advice");
			alert("Please Enter Dose");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDoseName")[0].focus();
			return false;
		}	
			
		if(document.getElementsByName("selFrequencyId")[0].value=="-1")
		{
			//alert("Please press + icon to add Drug Advice");
			alert("Please Enter Frequecy");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selFrequencyId")[0].focus();
			return false;
		}
		if(document.getElementsByName("selDays")[0].value=="" || document.getElementsByName("selDays")[0].value=="0")
		{
			//alert("Please press + icon to add Drug Advice");
			alert("Please Enter Days");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selDays")[0].focus();
			return false;
		}
		if(document.getElementsByName("selStartDay")[0].value=="")
		{
			//alert("Please press + icon to add Drug Advice");
			alert("Please Enter Start Day");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("selStartDay")[0].focus();
			return false;
		}
		return true;
	}
	         return true;
			}

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
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
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
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+550+',left=10,top=10');  
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
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
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
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}

function showLog(index,hmode)
{	
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=LOGDETAIL';
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+500+',width='+800+',left=10,top=10');  
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
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+500+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}



function showDrugInstruction(index)
{
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=DRUGINSTRUCTION&drugInstructionIndex='+index;
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
}




function showRevRmarks(index)
{
	var path='/HISClinical/emr/ehrSection_DESKTREATMENT.cnt?hmode=REVOKEREMARKS&drugInstructionIndex='+index;
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+800+',left=10,top=10');  
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



function ShowExternalDiv(flg)
{
	
	// alert(flg);
	 	if (flg=="1" || flg==undefined || flg==" ") 
		{
	 		document.getElementById("extDrugDiv").style.display="none";
	 		document.getElementById("internalDrugDiv").style.display="";
	 		document.getElementsByName("drugMode")[0].value = "1";
	 		//document.getElementById("divMappedOtherICD").style.display="none";
	 		//document.getElementById("divMappedICD").style.display="";
	 		document.getElementById("extrad").checked=true;
	 		clearFirstRow();
		}
	 
	 	if (flg=="2") 
			{
			document.getElementById("extDrugDiv").style.display="";
			document.getElementById("internalDrugDiv").style.display="none";
			document.getElementsByName("drugMode")[0].value = "2";
			handleGetSetDrugRouteList(0,0);
		//	document.getElementById("divMappedICD").style.display="none";
		//	document.getElementById("divMappedOtherICD").style.display="";
			clearFirstRow();
			}
	 	
	 //	 alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);
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


<body>
<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>
<html:hidden name="EHRSection_TreatmentFB" property="entryDate" value="<%=sysDate%>"/>
	<his:ContentTag>
<his:statusTransactionInProcess>
		
	
		<html:hidden name="EHRSection_TreatmentFB" property="drugDetailRows" />
		
		<table class="table table-condensed table-responsive">
		<tr>
		<td style="font-size:1.2em;font-weight:bold;"width="100%">
	Advice on Discharge&nbsp;:
	<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
	</td>
		</tr>
		</table>
		<table id="treatmentTable" class="table table-condensed table-responsive" style="display:block;">
				<tr style="background: linear-gradient(to bottom, #74a9d8 , white); font-weight:bold;"> 
				<td width="20%" nowrap="nowrap">
					<div align="center">
						
								<font color="#FF0000">*</font>
								<bean:message key="drugname" />
						
					</div>
				</td>
				<td width="10%"  nowrap="nowrap">
					<div align="center">
					
								<font color="#FF0000">*</font>
								<bean:message key="dosage" />
							
					</div>
				</td>
				<td width="10%"  nowrap="nowrap">
					<div align="center">
	
								<font color="#FF0000">*</font>
								<bean:message key="frequency" />
						
					</div>
				</td>
				<td width="1%">
					</td>	
				<td width="5%" nowrap="nowrap">
					<div align="center">
						
							
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
							
					</div>
				</td>
				<td width="5%"  nowrap="nowrap">
					<div align="center">
					
								<font color="#FF0000">*</font>
								<bean:message key="startday" />
							
						
					</div>
				</td>
				
				<td  width="15%" nowrap="nowrap">
						<div align="center">
							
							
								<bean:message key="drugAdminFlag"/> 
							
							
						</div>
					</td>
				<td width="10%"  nowrap="nowrap">
					<div align="center">
					
						
								<bean:message key="drugRoute" />
							
					
					</div>
				</td>
				<td width="5%"  nowrap="nowrap">
					<div align="center">
					Instruction
					</div>
				</td>
				
				 
				<td width="5%"  nowrap="nowrap">
					<div align="center">
					
							
								<bean:message key="quantity" />
							
						
					</div>
				</td>
				
				<td width="5%" nowrap="nowrap">
				<div align="center" style="display:block">
		
						<font color="#000000" size="2">
							
						</font>
					</div>
				</td>
			</tr>
			<%int countNew=0;%>
			<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>" type="ehr.treatmentdetail.vo.EHRSection_TreatmentVO">
			<%-- <tr id="treatmentRow#<%=i.intValue()+2%>" >
			<tr id="trRowID<%=i%>"> 	
			 --%><tr>
			 <logic:notEqual value="modify" name="drugDtlVO" property="rxContinueFlag">
			<%countNew++;%><%System.out.print("manisha"+drugDtlVO.getDrugId()); %>
				<td width="20%"> 
					<div align="center" id="internalDrugDiv" style="display: none;">
						<html:hidden name="EHRSection_TreatmentFB" property="treatmentRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_NOT_SAVED %>" ></html:hidden>
				 
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
					<html:hidden name="EHRSection_TreatmentFB" property="selDrugBrandId" value='<%=drugDtlVO.getDrugBrandId()%>' />
							<%String temp="fillData("+i+")"; %>
							<%String showqty="calculateQuantity("+i+")"; %>
						   <%String showfreqQty="setFreqNamecalculateQuantity("+i+")"; %>
						
						<html:text name="EHRSection_TreatmentFB" property="selDrugName" maxlength="100" size="25" tabindex="1"  styleClass="form-control"
							value="<%=drugDtlVO.getDrugName()%>" 
							onkeypress="return validateAlphaOnly(this,event);" 
							onkeyup="getDrugDropDownData(event,this);" 
							onfocus="<%=temp %>"
						></html:text>    	<%-- onblur="callOnBlur()" --%>
					</div>
					
					<div align="center" id="extDrugDiv" style="display: none;">
					
					<input id="txt-other" name="txt-external"  type="text" value="" class="form-control" onfocus="<%=temp %>">
					
					
							<!-- <input id="txt-other" name="txt-other"  type="text" value="" class="form-control"> -->
							
								<%-- <html:text name="EHRSection_TreatmentFB" property="selDays" maxlength="4" size="3" tabindex="1" styleClass="form-control"
							value="<%=drugDtlVO.getDays() %>" onkeypress="return validateNumberic(event)" onchange="<%=showqty%>" ></html:text> --%>
                        </div> 
                         
					
				</td>
					<td width="15%" >
					<div align="center">
					
						<% if(drugDtlVO.getDoseId().equals("0"+"^"+"0"+"^"+"0")) { %>
							<html:hidden name="EHRSection_TreatmentFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="EHRSection_TreatmentFB" property="selDoseName" tabindex="1" styleClass="form-control"
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" onchange="<%=showqty%>" ></html:text>
						<% } else { %>
							<html:select name="EHRSection_TreatmentFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  onchange="<%=showqty%>">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="EHRSection_TreatmentFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
			<td width="14%" >
					<div align="center">
						<html:hidden name="EHRSection_TreatmentFB" property="selFrequencyName" value=""/>
						<html:select name="EHRSection_TreatmentFB" property="selFrequencyId" tabindex="1" styleClass="form-control"
							value="<%=drugDtlVO.getFrequencyId() %>" onchange="<%=showfreqQty%>" >
							<html:option value="-1">Select</html:option >
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
						
								
						<html:hidden name="EHRSection_TreatmentFB" property="endDate" value=""/>
					</div>
					</td>
					<td width="1%" >
					<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/scheduler.png"/>' style='cursor:pointer;vertical-align:top;'align="left" title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
					<html:hidden  name="EHRSection_TreatmentFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
					<html:hidden  name="EHRSection_TreatmentFB" property="changeStartDate" value=""/>
				</td>
				</logic:notEqual>
				<logic:equal value="modify" name="drugDtlVO" property="rxContinueFlag">
				<td width="20%">
					<div align="center">
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugBrandId" value='<%=drugDtlVO.getDrugBrandId()%>'  />
							<%String temp1="fillData("+i+")"; %>
						   <%String showqty="calculateQuantity("+i+")"; %>
						     <%String showfreqQty="setFreqNamecalculateQuantity("+i+")"; %>
						   
						
						
						<html:text name="EHRSection_TreatmentFB" property="selDrugName" maxlength="100" size="25" tabindex="1"  readonly="true" styleClass="form-control"
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onfocus="<%=temp1 %>"
						
							></html:text> 	<%-- onblur="callOnBlur()" --%>
					</div>
				</td>
				<td width="10%" >
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0")) { %>
							<html:hidden name="EHRSection_TreatmentFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="EHRSection_TreatmentFB" property="selDoseName" tabindex="1" value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8" styleClass="form-control"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" onchange="<%=showqty%>" ></html:text>
						<% } else { %>
							<html:select name="EHRSection_TreatmentFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  styleClass="form-control" onchange="<%=showqty%>">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="EHRSection_TreatmentFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td width="15%">
					<div align="center">
					<html:hidden name="EHRSection_TreatmentFB" property="selFrequencyName" value=""/>
						<html:select name="EHRSection_TreatmentFB" property="selFrequencyId" tabindex="1" styleClass="form-control"
							value="<%=drugDtlVO.getFrequencyId() %>" onchange="<%=showfreqQty%>" >
							<html:option value="">Select</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
							<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/scheduler.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
				
						<html:hidden name="EHRSection_TreatmentFB" property="endDate" value=""/>
					</div>
					<html:hidden  name="EHRSection_TreatmentFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
					<html:hidden  name="EHRSection_TreatmentFB" property="changeStartDate" value="<%=drugDtlVO.getChangeStartDate() %>"/>
					
				</td>
				</logic:equal>
				<td width="8%" >
					<div align="center">
				<%String showqty="calculateQuantity("+i+")"; %>
						<html:text name="EHRSection_TreatmentFB" property="selDays" maxlength="4" size="3" tabindex="1" styleClass="form-control"
							value="<%=drugDtlVO.getDays() %>" onkeypress="return validateNumberic(event)" onchange="<%=showqty%>" ></html:text>
					</div>
				</td>
							
				<td width="5%" >
					<div align="center">
					
						<html:text name="EHRSection_TreatmentFB" property="selStartDay" maxlength="2" size="3" tabindex="1" styleClass="form-control"
							value="<%=drugDtlVO.getStartDate() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					
					
					</div>
				</td>
				
				
							
				<td width="15%">
					<div  align="center">
						<html:select name="EHRSection_TreatmentFB" property="selDrugAdminId" tabindex="1" value="<%=drugDtlVO.getDrugAdminId() %>" styleClass="form-control"  onchange="setDrugAdminName(findIndex(this))" >
							<html:option value="-1">Select Value</html:option >
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>" property="value" labelProperty="label"/>
							</logic:present>
							</html:select>
							</div>
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugAdminName" value="<%=drugDtlVO.getDrugAdminName()%>"></html:hidden>	
																
				</td>
				
				
							
				<td width="10%" >
					<div  align="center">
						<html:select name="EHRSection_TreatmentFB" property="selDrugRouteId" tabindex="1" value="<%=drugDtlVO.getDrugRouteId() %>" styleClass="form-control" onchange="setDrugRouteName(findIndex(this))" style="width: 120px;" >	
						<logic:notEmpty name="drugDtlVO" property="drugRouteId">
							
							<html:option value="<%=drugDtlVO.getDrugRouteId() %>">
								<bean:write name="drugDtlVO" property="drugRouteName"/> 
							</html:option>	
						</logic:notEmpty>
					<logic:empty name="drugDtlVO" property="drugRouteId">
							<html:option value="-1">Select </html:option>
							</logic:empty> 
						</html:select>
						<html:hidden name="EHRSection_TreatmentFB" property="selDrugRouteName" value="<%=drugDtlVO.getDrugRouteName()%>"></html:hidden>	
					</div>
				</td>
				
				<td width="10%" >
					<div align="center">
					<html:hidden name="EHRSection_TreatmentFB" property="selInstructions" value="<%=drugDtlVO.getRemarks()%>"></html:hidden>
					<img name="drugInstruction" class="button"  src='<his:path src="/hisglobal/images/add_remarks.png"/>' style='cursor:pointer' title="Drug Instruction " onclick ="showDrugInstruction(findIndex(this))" onkeypress="if(event.keyCode==13) showDrugInstruction(findIndex(this));" id="drugInstructionButton">
					
					
					<html:hidden name="EHRSection_TreatmentFB" property="todayVisitFlag" value="<%=drugDtlVO.getTodayVisitFlag() %>"/>

					</div>
				</td>
				<td width="5%" >
					<div align="center">
					 <% boolean readonly=false;
					 if(drugDtlVO.getQuantity().equals("")){ 
						 readonly=false;
						 
						 
					 }
					 %>
						<html:text name="EHRSection_TreatmentFB" property="quantity" maxlength="2" size="3" tabindex="1" styleClass="form-control" 
							 onkeypress="return validateIntegerOnly(this,event)" readonly="<%=readonly %>" value="<%=drugDtlVO.getDoseQty() %>"></html:text>
					</div>
				</td>
				<td width="5%"  id="tdButtonPlus">
				<div align="center">
					<%if(countNew==1){%>
					<%-- 	<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitAjaxOnValidate(validateRows(),'ADDDRUGROW');" onclick="submitAjaxOnValidate(validateRows(),'ADDDRUGROW');">
					 --%>	
					 
					  <button type="button"  id="addButton"  class="btn btn-info btn-sm" onkeypress="if(event.keyCode==13) submitAjaxOnValidate();" onclick="submitAjaxOnValidate();">Add</button>
					
					<% } else { %>
					<button type="button"  id="deleteButton"  class="btn btn-info btn-sm" onkeypress="if(event.keyCode==13) deleteDrugRow('DELETEDRUGROW',<%=i %>)" onclick="deleteDrugRow('DELETEDRUGROW',<%=i %>)">Delete</button>
						<%} %></div>
				</td>
			</tr>
		
			</logic:iterate>
			
			
					 <html:radio name="EHRSection_TreatmentFB" property="drugMode" value="1" onclick="ShowExternalDiv('1');" styleId="extrad" ></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									Internal
								</b>							</font>
							
							
							<html:radio name="EHRSection_TreatmentFB" property="drugMode" value="2" onclick="ShowExternalDiv('2');"></html:radio>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									External
								</b>
							</font> 
			
			
	
			
		</table>	
				<table id="prevDrugDtlTableId" width=100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			
			
			
			
				<logic:notEmpty name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>">
		
				<logic:iterate id="drugDtlVO" indexId="idx" name="<%=OpdConfig.PAT_TREATMENT_DTL_PREV_DRUG_DETAIL_LIST%>" type="ehr.treatmentdetail.vo.EHRSection_TreatmentVO">
				<%String ind=idx.toString(); %>
				<tr id="treatmentRow#<%=idx.intValue()+2%>">

					<td width="20%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="drugName"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDrugId" value="<%=drugDtlVO.getDrugId() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDrugName" value="<%=drugDtlVO.getDrugName() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDrugBrandId" value="<%=drugDtlVO.getDrugBrandId() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="treatmentRecordStatus" value="<%=EHRConfig.EHR_SECTION_RECORD_STATUS_SAVED %>" ></html:hidden>
				 					<html:hidden name="EHRSection_TreatmentFB" property="serealNo" value="<%=drugDtlVO.getSerialNo() %>"/>
				 					 <html:hidden name="EHRSection_TreatmentFB" property="prevEntryDate" value="<%=drugDtlVO.getEntryDate() %>"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								
									<html:hidden name="EHRSection_TreatmentFB" property="prevDoseName" value="<%=drugDtlVO.getDoseName() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDoseId" value="<%=drugDtlVO.getDoseId() %>"/>
									<bean:write name="drugDtlVO" property="doseName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="15%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<html:hidden name="EHRSection_TreatmentFB" property="previousFrequencyId" value="<%=drugDtlVO.getFrequencyId() %>"/>
								<html:hidden name="EHRSection_TreatmentFB" property="prevFrequencyName" value="<%=drugDtlVO.getFrequencyName() %>"/>
								<bean:write name="drugDtlVO" property="frequencyName"/>
								<img name="prevDrugSchedule" class="button"  src='<his:path src="/hisglobal/images/scheduler.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showPrevDrugSchedule(<%=ind %>,'PREVDRUGSHEDULE')" >
								
							</b>
							</font>	
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDays" value="<%=drugDtlVO.getDays() %>"/>
									<bean:write name="drugDtlVO" property="days"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="5%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<html:hidden name="EHRSection_TreatmentFB" property="prevStartDate" value="<%=drugDtlVO.getStartDate() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevEndDate" value="<%=drugDtlVO.getEndDate() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="selEndDate" value="<%=drugDtlVO.getEndDate() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="selStartDate" value="<%=drugDtlVO.getStartDate() %>"/>
					
									<bean:write name="drugDtlVO" property="startDate"/>
								</b>
							</font>
						</div>
					</td>
								
					<td width="15%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									 <html:hidden name="EHRSection_TreatmentFB" property="prevDrugAdminName" value="<%=drugDtlVO.getDrugAdminName() %>"/>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDrugAdminId" value="<%=drugDtlVO.getDrugAdminId() %>"/>
								 <bean:write name="drugDtlVO" property="drugAdminName"/>
										</b>
							</font>
						</div>
					</td>
					
					<td width="10%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									 <html:hidden name="EHRSection_TreatmentFB" property="prevRouteName" value="<%=drugDtlVO.getDrugRouteName() %>"/>
								<html:hidden name="EHRSection_TreatmentFB" property="prevRouteId" value="<%=drugDtlVO.getDrugRouteId() %>"/>
								<bean:write name="drugDtlVO" property="drugRouteName"/>
								 </b>
							</font>
						</div>
					</td> 
					
					
					
					<td width="5%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<html:hidden name="EHRSection_TreatmentFB" property="prevSelInstructionArray" value="<%=drugDtlVO.getRemarks()%>"/>
									<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="Instruction " onclick ="showInstruction(event,<%=ind %>)" onkeypress="if(event.keyCode==13) showInstruction(event,<%=ind %>);">
							</b>
							</font>
						</div>
					</td>
					
					
					<td width="5%" class="tdfont">
						<div align="center" id='revokeRem<%=ind %>' style="display:none">
							<logic:notEqual value="before" name="drugDtlVO" property="date">
								<img name="instruction" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Revoke Remarks " onclick ="showRevRmarks(<%=ind %>)" onkeypress="if(event.keyCode==13) showRevRmarks(<%=ind %>);">
								<html:hidden property="revokeHidden" name="EHRSection_TreatmentFB"/>
								<html:hidden property="revokeRemarks" name="EHRSection_TreatmentFB"/>
							</logic:notEqual>
						</div>
					</td>
				
					
						<td width="5%" class="tdfont">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<% if(drugDtlVO.getDoseQty()==null)
								{%>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDoseQty" value=""/>
							<% }else{%>
									<html:hidden name="EHRSection_TreatmentFB" property="prevDoseQty" value="<%=String.valueOf(Math.round(Float.parseFloat(drugDtlVO.getDoseQty())))%>"/>
						<% }%>
							
							<%-- 	<%= String.valueOf(Math.round(Float.parseFloat(drugDtlVO.getDoseQty())))%> --%>
									<bean:write name="drugDtlVO" property="doseQty"/>
							</b>
							</font>
						</div>
					</td>
					
					
					<td width="5%" class="tdfont" id="tdButtonPlus">
					<div align="center">
						<img class="button" id="revokeButton" style="cursor:pointer" src="/HIS/hisglobal/images/avai/minus.gif" title="Cancel Treatment"  onclick="showRevokeRemarks(this,'<%=idx.toString()%>');  hideTreatmentRow('<%=idx.toString()%>'); ">
					</div><%-- <div>
					<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteDrugRow('DELETEDRUGROW',<%=idx %>)" onclick="deleteDrugRow('DELETEDRUGROW',<%=idx%>)">
					</div> --%>
					</td>
						
				</tr>
				</logic:iterate>
				
		</logic:notEmpty>
		
			
		</table>
		<bean:define id="summaryId" name="EHRSection_TreatmentFB" property="summary" type="java.lang.String"></bean:define>
		<div id="divSummary" >
			<table width="98%">
			
				<bean:write name="EHRSection_TreatmentFB" property="summary" filter="false"/>
			
			</table>
		</div>
		
	</div>

</his:statusTransactionInProcess>
</his:ContentTag>
<%-- <his:ButtonToolBarTag>
	<img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" tabindex="1" onclick="submitFormOnValidate(validateRowsSave(),'SAVE');" onkeypress="if(event.keyCode==13)submitFormOnValidate(validateRowsSave(),'SAVE');")>
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" tabindex="1" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<!-- <img class='button' src='<his:path src="/hisglobal/images/btn-pnt.png"/>' style="cursor: pointer" tabindex="1" onclick="showPrint()" onkeypress="if(event.keyCode==13) showPrint()")> -->
</his:ButtonToolBarTag> --%>

<div id="divDropDown" class="hisStyle.css" style="display: none; position: absolute; z-index: 10;"> <font color="black">
	<select id="cboDropDown" size="10" tabindex="1" onKeyDown="onKeyDownDrop(event)" onchange="onChangeDrop()" 
		ondblclick="setClickedValue()" onclick="setClickedValue()">
		<option value="-1"></option>
	</select>
	</font>
</div>

<div id="divDropDownExt" class="hisStyle.css" style="display: none; position: absolute;">
	<select id="cboDropDownExt" size="10" tabindex="1" onKeyDown="onKeyDownDropExt(event)" onchange="onChangeDropExt()" 
		ondblclick="setClickedValueExt()" onclick="setClickedValueExt()">
		<option value="-1"></option>
	</select>
</div>


						
<div id="divDrugAdminList" style="display: none; position: absolute;">
	<select name="drugAdminList" id="drugAdminList" multiple="multiple" size="4">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>">
			<logic:iterate id="objEntry" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>" type="hisglobal.utility.Entry">
				<option value="<%=objEntry.getValue()%>"> <%=objEntry.getLabel()%></option>
			</logic:iterate>
		</logic:present>								
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


<div id="divDrugPregCatCode" style="display: none; position: absolute;">
	<select name="drugPatPregCatList" id="drugPatPregCatList" multiple="multiple" size="4">
		<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_PREGNANT_CATEGORY%>">
			<logic:iterate id="objEntry" name="<%=OpdConfig.ESSENTIALS_LIST_ALL_PREGNANT_CATEGORY%>" type="hisglobal.utility.Entry">
				<option value="<%=objEntry.getValue()%>"> <%=objEntry.getLabel()%></option>
			</logic:iterate>
		</logic:present>								
	</select>
</div>




<html:hidden name="EHRSection_TreatmentFB" property="summary" />
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

<script type="text/javascript">
	TDSetDataExist();

</script>


</body>


