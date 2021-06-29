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
<his:javascript src="/opd/js/advice_on_discharge.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/opd/js/generic_patient_profile.js"/>
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
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
		//alert("FreqImageInd "+FreqImageInd);
		var freqId=document.getElementsByName("selFrequencyId")[FreqImageInd].value;
		//alert("freqId "+freqId);
		var popupItemTypeId=document.getElementsByName("selDrugItemTypeId")[FreqImageInd].value;
		//alert("popupItemTypeId "+popupItemTypeId);
		if(document.getElementsByName("selIsEmptyStomach")[FreqImageInd].checked)
		{
			var popupIsEmptyStomach=document.getElementsByName("selIsEmptyStomach")[FreqImageInd].value;
			//alert("popupIsEmptyStomach "+popupIsEmptyStomach);
		}
		else
		{
			popupIsEmptyStomach="0";
		}
		
		var popupDrugId=document.getElementsByName("selDrugId")[FreqImageInd].value;
		//alert("popupDrugId "+popupDrugId);
		var popupDoseId=document.getElementsByName("selDoseId")[FreqImageInd].value;
		//alert("popupDoseId "+popupDoseId);
		var popupDoseName=document.getElementsByName("selDoseName")[FreqImageInd].value;
		//alert("popupDoseName "+popupDoseName);
		
	//	alert("FreqImageInd "+FreqImageInd);
		//var path='/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=DrugTime&popupFreqId='+freqId+'&popupItemTypeId='+popupItemTypeId +'&popupIsEmptyStomach='+popupIsEmptyStomach+'&popupDoseId='+popupDoseId+'&popupRowIndex='+FreqImageInd+'&popupDrugId='+popupDrugId+'&popupDoseName='+popupDoseName;
		var path='/HISClinical/opd//opdPatientProfile.cnt?hmode=DRUGSCHEDULE&popupFreqId='+freqId+'&popupItemTypeId='+popupItemTypeId +'&popupIsEmptyStomach='+popupIsEmptyStomach+'&popupDoseId='+popupDoseId+'&popupRowIndex='+FreqImageInd+'&popupDrugId='+popupDrugId+'&popupDoseName='+popupDoseName;
	//	alert("path"+ path);
		
		child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	  	child.moveTo(250,250);
	 	child.focus(); 
	
		if(!child.opener)
	   child.opener = self;
	 
	 	return child
	}
}

	

function validateSheduleRow(index)
{
	//alert("inside button Validation");
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
	
	var rowCount = parseInt(document.getElementsByName("drugDetailRows")[0].value);
	
	
	for(var i=0;i<rowCount-1;i++)
	{
		var drugId = document.getElementsByName("selDrugId")[i].value;
		
		for(var j=i+1;j<rowCount;j++)
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
	
	var length=document.getElementsByName("prevDrugName").length;
	//alert("length "+length);
	<%
		List lst=(List)session.getAttribute(OpdConfig.PAT_TREATMENT_DTL_TODAY_DRUG_DETAIL_LIST);
	if(lst==null || lst.size()==0)
	{
	%>
		for(var i=0;i<rowCount;i++)
		{
			for(var j=0;j<length;j++)
			{
				//alert("prev drug name "+document.getElementsByName("selDrugName")[j].value);
				if(document.getElementsByName("selDrugName")[i].value==document.getElementsByName("prevDrugName")[j].value)
				{
					alert(document.getElementsByName("selDrugName")[i].value+" is Already Given");	
					setToDesiredTab("tabDrugAdvice");
					document.getElementsByName("selDrugName")[i].focus();
					return false;
				}
			}
		 }	
	<%	
	}
	%>
	
	
	
	//For isEmptyStomach field in drug Advice
	var len=document.getElementsByName("selIsEmptyStomach").length;
	//alert("len  "+len);
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
	//alert("empty stomach Array "+document.getElementsByName("emptyStomachIndexArray")[0].value);
		
	
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

window.onload=function()
{
	// alert("inside onload")
	//for highliting default drug advice tab
	document.getElementById('tabDrugAdvice').className="highlight";
	document.getElementById('tabDietAdvice').className="dehighlite";
	document.getElementById('tabLAAdvice').className="dehighlite";
	document.getElementById('tabRestAdvice').className="dehighlite";
		
	activateTab(document.getElementById('<bean:write name="GenericPatientProfileFB" property="activeTab"/>'));
}

function validateCheck(obj)
{
	var index=obj.value.split("$")[1];
	//alert("index "+ index);
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
	
	//var itemIdAndIndex=document.getElementsByName("revoke")[].value.split("$");
}

function showOtherInstruction(index,hmode)
{	
	var path='/HISClinical/opd/opdPatientProfile.cnt?hmode=OTHERINSTRUCTION';
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+400+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
		child.opener = self;
	return child
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
   		background:#E0EBEB;//FFEBD5;
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
    background-color: #E0EBEB;//FFEBD5;
    white-space: nowrap;
    background:#E0EBEB;
    border-style:solid;
	border-width:1px;
   		}
</STYLE>

<his:TransactionContainer>

<bean:define id="menuURL" name="GenericPatientProfileFB" property="hmode" type="java.lang.String"></bean:define>
<%	String targetHmode = "SET" + "ADVICEONDISCHARGE"; %>
<his:TitleTag>
	<his:name>
		<bean:message key="pattreatmentdtl"/>
	</his:name>
</his:TitleTag>

<%	String sysDate=WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");	%>


<his:statusTransactionInProcess>

	<table width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td id="tabDrugAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center" >Drug Advice</div></td>
			<td id="tabLAAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Other Advice</div></td>
			<td id="tabDietAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Diet Advice</div></td>
			<td id="tabRestAdvice" onclick="activateTab(this)" width="25%" class="tdfonthead"><div align="center">Rest Advice</div></td>
		</tr>
	</table>
	<div id="divDrugAdvice" style="display: block;">
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
						<img class="button" style="cursor: pointer" alt="Search" title="Search"	src='<his:path src="/hisglobal/images/btn-search.png"/>' onclick="openPopup(createFHashAjaxQuery('/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=SEARCHDRUG'),event,300,600);">
					</div>
				</td>
			</tr>
		</table>
		</his:SubTitleTag>
		<his:ContentTag>
			
			<html:hidden name="GenericPatientProfileFB" property="drugDetailRows" />
		<table id="tblDrugDetailId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			<tr>
				<td width="14%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="drugname" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosage" />
							</b>
						</font>
					</div>
				</td>
				<td width="14%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="frequency" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="7%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<font color="#FF0000">*</font>
								<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				
				<%-- <td class="tdfonthead" width="10%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="emptyStomach"/>
								</b>
							</font>	
						</div>
					</td> --%>
					
					
					<td class="tdfonthead" width="15%" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<%-- <bean:message key="emptyStomach"/> --%>
								<bean:message key="drugAdminFlag"/> 
								</b>
							</font>	
						</div>
					</td>
					
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="drugRoute" />
							</b>
						</font>
					</div>
				</td>
				<td width="16%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="instructions" />
							</b>
						</font>
					</div>
				</td>
								
				<td width="4%" class="tdfonthead">
				</td>
			</tr>
			<%int countNew=0;%>
			<logic:present name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>">
			<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_DRUG_DETAIL_LIST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
			
			<tr id="trRowID<%=i%>">
			<logic:notEqual value="modify" name="drugDtlVO" property="rxContinueFlag">
			<%countNew++;%>
				<td  class="tdfont">
					<div align="center">
						<html:hidden name="GenericPatientProfileFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="GenericPatientProfileFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<%String temp="fillData("+i+")"; %>	
						<html:text name="GenericPatientProfileFB" property="selDrugName" maxlength="100" size="25" tabindex="1" 
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onblur="callOnBlur()"
							onfocus="<%=temp %>">
							</html:text>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0"+"^"+"0"+"^"+"0")) { %>
							<html:hidden name="GenericPatientProfileFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="GenericPatientProfileFB" property="selDoseName" tabindex="1" 
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="12"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" ></html:text>
						<% } else { %>
							<html:select name="GenericPatientProfileFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  onchange="showQuantity(<%=i %>)" styleClass="regcbo">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="GenericPatientProfileFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
					
						<html:select name="GenericPatientProfileFB" property="selFrequencyId" tabindex="1" styleClass="regcbo" style="width:80;"
							value="<%=drugDtlVO.getFrequencyId() %>" >
							<html:option value="-1">Select</html:option >
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
						<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
				
						
						<html:hidden name="GenericPatientProfileFB" property="endDate" value=""/>
					</div>
					<html:hidden  name="GenericPatientProfileFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
				</td>
				</logic:notEqual>
				<logic:equal value="modify" name="drugDtlVO" property="rxContinueFlag">
				<td width="25%" class="tdfont">
					<div align="center">
						<html:hidden name="GenericPatientProfileFB" property="selDrugId" 
							value='<%=drugDtlVO.getDrugId().split("#")[0]%>' />
						<html:hidden name="GenericPatientProfileFB" property="selDrugItemTypeId"
							value='<%=drugDtlVO.getDrugId().split("#")[1]%>' />
						<html:text name="GenericPatientProfileFB" property="selDrugName" maxlength="100" size="25" tabindex="1" 
							value="<%=drugDtlVO.getDrugName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
							onkeyup="getDrugDropDownData(event,this);" 
							onblur="callOnBlur()"></html:text>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
						<% if(drugDtlVO.getDoseId().equals("0")) { %>
							<html:hidden name="GenericPatientProfileFB" property="selDoseId" value="<%=drugDtlVO.getDoseId()%>"></html:hidden>
							<html:text name="GenericPatientProfileFB" property="selDoseName" tabindex="1" 
								value="<%=drugDtlVO.getDoseName()%>" maxlength="20" size="8"
								onkeypress="return validateAlphaNumOnly(this,event)" style="width: 100px;" ></html:text>
						<% } else { %>
							<html:select name="GenericPatientProfileFB" property="selDoseId" tabindex="1" value="<%=drugDtlVO.getDoseId() %>" style="width: 100px;"  onchange="showQuantity(<%=i %>)">
								<html:option value="<%=drugDtlVO.getDoseId()%>">
									<bean:write name="drugDtlVO" property="doseName"/> 
								</html:option>
							</html:select>
							<html:hidden name="GenericPatientProfileFB" property="selDoseName" value="<%=drugDtlVO.getDoseName()%>"></html:hidden>
						<% } %>
					</div>
				</td>
				<td class="tdfont">
					<div align="center">
						<html:select name="GenericPatientProfileFB" property="selFrequencyId" tabindex="1" 
							value="<%=drugDtlVO.getFrequencyId() %>" >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
						<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
			
						<html:hidden name="GenericPatientProfileFB" property="endDate" value=""/>
					</div>
					<html:hidden  name="GenericPatientProfileFB" property="rxContinueFlag" value="<%=drugDtlVO.getRxContinueFlag() %>"/>
					
				</td>
				</logic:equal>
				<td  class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selDays" maxlength="2" size="3" tabindex="1" 
							value="<%=drugDtlVO.getDays() %>" onkeypress="return validateIntegerOnly(this,event)" ></html:text>
					</div>
				</td>
							
				<td class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selStartDay" maxlength="2" size="3" tabindex="1" 
							value="<%=drugDtlVO.getStartDay() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					</div>
				</td>
			<!-- 	<td class="tdfont" id="firstEmptyStomach"> -->
					<div align="center" style="display:none">
						<%String isEmptyStomach=drugDtlVO.getIsEmptyStomach();
						if(isEmptyStomach.equals("1")){	%>
						<input type="checkbox" name="selIsEmptyStomach" checked="checked" />
						<% } else { %>
						<input type="checkbox" name="selIsEmptyStomach"  />
						<% } %>
						<img name="drugSchedule" class="button"  src='<his:path src="/hisglobal/images/imgDatepicker.png"/>' style='cursor:pointer' title="Drug Schedule " onclick ="showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this))" onkeypress="if(event.keyCode==13) showDrugSchedule(validateSheduleRow(findIndex(this)),event,findIndex(this));">
					</div>
				<!-- </td> -->
				
				
				<td class="tdfont">
					<div  align="center">
						<html:select name="GenericPatientProfileFB" property="selDrugAdminId" tabindex="1" value="<%=drugDtlVO.getDrugAdminId() %>"   onchange="setDrugAdminName(findIndex(this))" >
							<html:option value="-1">Select</html:option >
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_ALL_DRUG_ADMIN_FLAGS%>" property="value" labelProperty="label"/>
							</logic:present>
							</html:select>
							</div>
						<html:hidden name="GenericPatientProfileFB" property="selDrugAdminName" value="<%=drugDtlVO.getDrugAdminName()%>"></html:hidden>	
																
				</td>
				
				
				
				<td  class="tdfont">
					<div  align="center">
						<html:select name="GenericPatientProfileFB" property="selDrugRouteId" tabindex="1" value="<%=drugDtlVO.getDrugRouteId() %>"  onchange="setDrugRouteName(findIndex(this))" style="width: 100px;" styleClass="regcbo">	
						<logic:notEmpty name="drugDtlVO" property="drugRouteId">
							<html:option value="<%=drugDtlVO.getDrugRouteId() %>">
								<bean:write name="drugDtlVO" property="drugRouteName"/> 
							</html:option>	
						</logic:notEmpty>
						<logic:empty name="drugDtlVO" property="drugRouteId">
							<html:option value="">Select Value</html:option>
						</logic:empty>
						</html:select>
						<html:hidden name="GenericPatientProfileFB" property="selDrugRouteName" value="<%=drugDtlVO.getDrugRouteName()%>"></html:hidden>	
					</div>
				</td>
				
				<td class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selInstructions" maxlength="500" size="20" tabindex="1" 
							value="<%=drugDtlVO.getRemarks() %>" onkeypress="return validateAlphaNumOnly(this,event)" onkeyup="goToNextIndex(event);"></html:text>
					<html:hidden name="GenericPatientProfileFB" property="serealNo" value="<%=drugDtlVO.getSerialNo() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="todayVisitFlag" value="<%=drugDtlVO.getTodayVisitFlag() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevEntryDate" value="<%=drugDtlVO.getEntryDate() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevDoseQty" value="<%=drugDtlVO.getDoseQty() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevIssueQty" value="<%=drugDtlVO.getIssueQty() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevRequiredQty" value="<%=drugDtlVO.getRequiredQty() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevEndDate" value="<%=drugDtlVO.getEndDate()%>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevStartDate" value="<%=drugDtlVO.getChangeStartDate() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevDoseId" value="<%=drugDtlVO.getDoseId() %>"/>
					<html:hidden name="GenericPatientProfileFB" property="prevFreqId" value="<%=drugDtlVO.getFrequencyId() %>"/>
					
				</div>
				</td>
				<td class="tdfont" id="tdButtonPlus">
					<%if(countNew==1){%>
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRows(),'ADDDRUGROW');" onclick="submitFormOnValidate(validateRows(),'ADDDRUGROW');">
					<% } else { %>
						&nbsp;<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteRow(document.getElementById('trRowID<%=i%>'));" onclick="deleteRow(document.getElementById('trRowID<%=i%>'))">
					<%} %>
				</td>
			</tr>
			</logic:iterate>
			</logic:present>
		</table>
		
		<div id="summary" >
			<table width="100%">
		
				<bean:write name="GenericPatientProfileFB" property="summary" filter="false"/>
			
			</table>
		</div>
		</his:ContentTag>
	</div>
	
	<div id="divDietAdvice" style="display: none;">
	<his:SubTitleTag name="Diet Advice Detail">
		</his:SubTitleTag>
		<his:ContentTag>
		<table id="dietDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="dietType" />
							</b>
						</font>
					</div>
				</td>				
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="remark" />
							</b>
						</font>
					</div>
				</td>
			</tr>
			
			<tr>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="GenericPatientProfileFB" property="dietTypeId" tabindex="1" disabled="false">
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" >
							<html:options collection="<%=OpdConfig.PAT_TREATMENT_DTL_ALL_DIET_TYPE_LIST%>" property="value" labelProperty="label" />
							</logic:present>
						</html:select>
					</div>
					<html:hidden name="GenericPatientProfileFB" property="dietSerialNo" value="" />
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="dietDays" onkeypress="return validateIntegerOnly(this,event)" maxlength="2" size="4" tabindex="1"></html:text>
					</div>
				</td>
				<td width="40%" class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="dietRemark" maxlength="500" onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:text>
					</div>
				</td>
				</tr>
		</table>
		</his:ContentTag>
	</div>
	
	<div id="divLAAdvice" style="display: none;">
	
	<his:SubTitleTag name="Other Advice Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<html:hidden name="GenericPatientProfileFB" property="extDrugDetailRows" />
		<table id="tblExtTreatMentDetailId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			<tr>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="ExtTreatmentName" />
							</b>
						</font>
					</div>
				</td>
				
				<td width="10%" class="tdfonthead">
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
							<font color="#FF0000">*</font>
							<bean:message key="dosedays" />
							</b>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<font color="#FF0000">*</font>
							<bean:message key="startday" />
							</b>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
							<bean:message key="instructions" />
							</b>
						</font>
					</div>
				</td>
				<td width="5%" class="tdfonthead">
				</td>
			</tr>
			<%int countNewExt=0;%>
			<logic:iterate id="extTretDtlVO" indexId="i" name="<%=OpdConfig.PAT_TREATMENT_DTL_EXT_DETAIL_LIST%>" type="hisglobal.vo.PatExtTreatmentDetailVO">
			<tr id="trRowID<%=i%>">
				<%countNewExt++;%>
				<td width="10%" class="tdfont">
					<div id="extTreatList" style="display: none; position: absolute;">
									<select name="extTreatList" id="extTreatList" multiple="multiple" size="4">
										<logic:iterate name="<%=OpdConfig.ESSENTIALS_LIST_ALL_EXT_TREATMENT%>" id="list">
											<option value="<bean:write name='list' property='value'/>" > <bean:write name='list' property='label'/></option>
										</logic:iterate>
									</select>
								</div>
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selExtTreatmentName" maxlength="100" size="25" tabindex="1" 
							value="<%=extTretDtlVO.getExtTreatmentName() %>" 
							onkeypress="return validateAlphaOnly(this,event)" 
						onkeyup="getEXTTreatmentDropDownData(event,this);" 
						onblur="callOnBlurExt()"></html:text>
						<html:hidden name="GenericPatientProfileFB" property="selExtTreatmentId" value="<%=extTretDtlVO.getExtTreatmentId() %>"/>

					</div>			
				</td>				
				
				
				<td width="10%" class="tdfont">
					<div align="center">
						<html:select name="GenericPatientProfileFB" property="selExtFrequencyId" tabindex="1" 
						value="<%=extTretDtlVO.getFrequencyId() %>" >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" >
							<html:options collection="<%=OpdConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY%>" property="value" labelProperty="label"/>
							</logic:present>
						</html:select>
							<html:hidden name="GenericPatientProfileFB" property="endDate" value=""/>
							<html:hidden  name="GenericPatientProfileFB" property="rxContinueFlag" value="<%=extTretDtlVO.getRxContinueFlag() %>"/>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selExtDays" maxlength="2" size="4" tabindex="1" 
						value="<%=extTretDtlVO.getDays() %>" onkeypress="return validateIntegerOnly(this,event)" ></html:text>
					</div>
				</td>
				<td width="10%" class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selExtStartDay" maxlength="2" size="4" tabindex="1" 
						value="<%=extTretDtlVO.getStartDay() %>" onkeypress="return validateIntegerOnly(this,event)"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfont">
					<div align="center">
						<html:text name="GenericPatientProfileFB" property="selExtInstructions" maxlength="500" size="25" tabindex="1" 
						value="<%=extTretDtlVO.getRemarks() %>" onkeypress="return validateAlphaNumOnly(this,event)" onkeyup="goToNextIndex(event);"></html:text>
					</div>
				</td>
				<td width="5%" class="tdfont" id="tdButtonPlus">
					<%if(countNewExt==1){%>
						<img class="button" id="addButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/Pl_Green_Sqr.png"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateExtTreatmentRows(),'ADDEXTTREATMENTROW');" onclick="submitFormOnValidate(validateExtTreatmentRows(),'ADDEXTTREATMENTROW');" >
					<% } else { %>
						<img class="button" id="deleteButton" style="cursor: pointer" src='<his:path src="/hisglobal/images/minus.gif"/>' alt="Add Diagnosis" title="Add Diagnosis" onkeypress="if(event.keyCode==13) deleteExtTreatmentRow(document.getElementById('trRowID<%=i%>'));" onclick="deleteExtTreatmentRow(document.getElementById('trRowID<%=i%>'))">
					<%} %>
				</td>
			</tr>
			</logic:iterate>
		</table>
		</his:ContentTag>	
	</div>
	
	<div id="divRestAdvice" style="display: none;">
	<his:ContentTag>
			<table id="restAdviceTableId" width="100%" border="0" cellspacing="1" cellpadding="0" >
			
					
				<tr>
					<td width="10%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="dosedays" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="left">
							<html:text name="GenericPatientProfileFB" property="restDays" onkeypress="return validateIntegerOnly(this,event)" maxlength="2" size="4"  tabindex="1"></html:text>
						</div>
					</td>
					
					<td width="10%" class="tdfont">
						
					</td>
					<td width="10%" class="tdfont">
						
					</td>
																			
				</tr>
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<font color="#FF0000">*</font>
								<bean:message key="reason" />
								</b>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont" colspan="3">
						<div align="left">
						<font color="#000000" size="1" face="Verdana, Arial, Helvetica, sans-serif">	
							<html:textarea name="GenericPatientProfileFB" property="restReason" rows="3" cols="69"  onkeypress="return validateAlphaNumOnly(this,event)" tabindex="1"></html:textarea>
							<html:hidden name="GenericPatientProfileFB" property="restSerialNo" />
						</font>
						</div>
					</td>
					
				</tr>
											
			
			</table>
			</his:ContentTag>
		</div>	
	

		
	
	</his:statusTransactionInProcess>
	
	
		
	

	
	<his:ButtonToolBarTag>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRowsSave(),'<%=targetHmode%>');" onclick="submitFormOnValidate(validateRowsSave(),'<%=targetHmode%>');" tabindex="1" />		
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('PROFILEOPTIONS')" onkeypress="if(event.keyCode==13) submitForm21('PROFILEOPTIONS');">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm21('<%=menuURL%>')" onkeypress="if(event.keyCode==13) submitForm21('<%=menuURL%>')">
	</his:statusTransactionInProcess>
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
				<%String valDrugRoute = voDrugRoute.getDrugRouteId()+"#"+voDrugRoute.getDrugItemId(); %> 			
				<option value="<%=valDrugRoute%>"> <%=voDrugRoute.getDrugRouteName()%></option>
			</logic:iterate>
		</logic:present>								
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


<html:hidden name="GenericPatientProfileFB" property="hmode" />

<html:hidden name="GenericPatientProfileFB" property="patCrNo" />
<html:hidden name="GenericPatientProfileFB" property="episodeCode" />
<html:hidden name="GenericPatientProfileFB" property="episodeVisitNo" />
<html:hidden name="GenericPatientProfileFB" property="departmentUnitCode" />
<html:hidden name="GenericPatientProfileFB" property="wardCode" />
<html:hidden name="GenericPatientProfileFB" property="admissionNo" />
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="deskId" />
<html:hidden name="GenericPatientProfileFB" property="profileId" />
<html:hidden name="GenericPatientProfileFB" property="serialNo" />
<html:hidden name="GenericPatientProfileFB" property="entryDate" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />

<html:hidden name="GenericPatientProfileFB"	property="profileHeader" />
<html:hidden name="GenericPatientProfileFB" property="accessType" />
<html:hidden name="GenericPatientProfileFB" property="userLevel" />
<html:hidden name="GenericPatientProfileFB" property="remarks" />
<html:hidden name="GenericPatientProfileFB" property="allergyCheckFlag" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenu" />
<html:hidden name="GenericPatientProfileFB" property="selectedMenuId" />
<html:hidden name="GenericPatientProfileFB" property="dischargeModifyFlag" />
<html:hidden name="GenericPatientProfileFB" property="reportMode" />
<html:hidden name="GenericPatientProfileFB" property="activeTab"/>
<html:hidden name="GenericPatientProfileFB" property="quantity"/>
<html:hidden name="GenericPatientProfileFB" property="frequencyImageIndex"/>
<html:hidden name="GenericPatientProfileFB" property="emptyStomachIndexArray"/>
<html:hidden name="GenericPatientProfileFB" property="sysDateForValidation"/> 
<html:hidden name="GenericPatientProfileFB" property="prescriptionDate"/> 

