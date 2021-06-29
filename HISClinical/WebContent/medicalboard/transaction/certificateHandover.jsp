<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/calendar.js" />
<his:javascript src="/registration/js/popup.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

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

function validateNumberWithDotsOnly(e)
{
	var key;
	var keychar;
	
	

	if (window.event)
	   key = window.event.keyCode;
	else if (e)
	   key = e.which;
	else
	   return true;
	keychar = String.fromCharCode(key);

	keychar = keychar.toUpperCase();
//	alert(key);
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) || (key==32))
	   return true;

	// alphas and space (give here which character u want to allow 
	else if ((("0123456789-").indexOf(keychar) > -1))

	   return true;
	 
	else
	   return false;
}

function getRequisitionDate(mode)
{
		document.getElementsByName("remarks")[0].value="";
	
	/*
	if(document.getElementsByName("certificateTypeID")[0].value=="-1")
	{
		alert("Please select certificate type");
		document.getElementsByName("certificateTypeID")[0].focus();
		return false;
	}
	*/
	submitPage(mode);
}

/**
	open dependent popup
	*/
function openDependentPopup(url, height, width,resize)
	{
		
		   	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10,dependent=yes,resizable='+resize+'');  
		  	child.moveTo(450,250);
		 	child.focus(); 
		
			if(!child.opener)
		  	 child.opener = self;
	 	
	 	return child
}

function getCadidateList(mode)
{
	
	submitPage(mode);
}

function validateGetHandOverDtlForm(mode)
{
	var len=document.getElementsByName("reqIdArray").length;
	var flag=false;
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("reqIdArray")[i].checked)
		{
			flag=true
			
		}
	}
	if(!flag)
	{
		alert("Please select candidate");
		document.getElementsByName("reqIdArray")[0].focus();
		return false;
	}
	
	/*******************getting max approved Date*****************/	
	var count=0;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("reqIdArray")[i].checked)
		{
			count++;
		}
	}
	
	// alert("count "+count);
	var apprDateArray=new Array(count);
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("reqIdArray")[i].checked)
		{
			apprDateArray[i]=document.getElementsByName("approvedDateArray")[i].value;
		}
	}
	
	// alert("apprDateArray "+ apprDateArray);
	// alert("approvedDateLen "+count);
	
	var maxDate="";
	if(count!=0)
	{
		maxDate=apprDateArray[0];
	}
	// alert("first maxDate "+maxDate);
	if(count>1)
	{
		for(var i=1;i<count;i++)
		{
			if(noOfDays(maxDate,apprDateArray[i])>0)
			{
				maxDate=maxDate;
			}
			else
			{
				maxDate=apprDateArray[i];
			}
		}
	}
	// alert("maxDate "+maxDate);
	document.getElementsByName("selApprovedDate")[0].value=maxDate;
	/********************************************************************/
	submitPage(mode);
}

function openCertificatePrint()
{	
	//alert(document.getElementsByName("certificateNo")[0].value);
	//alert(document.forms[0].certificateNo.value);
	var path='/HISClinical/medicalboard/certificateHandover.cnt?hmode=POPUP&templateId='+document.forms[0].templateId.value
	+'&handoverDate='+document.getElementsByName("handOverDate")[0].value+'&remarks='+document.getElementsByName("remarks")[0].value
	+'&handoverDate='+document.getElementsByName("handOverDate")[0].value+'&patCrNo='+document.getElementsByName("patCrNo")[0].value
	+'&orgName='+document.getElementsByName("orgName")[0].value+'&opinion='+document.getElementsByName("opinion")[0].value
	+'&certificateNo='+document.getElementsByName("certificateNo")[0].value
	+'&isPatient='+document.getElementsByName("isPatient")[0].value;
	//alert("path "+path);
	openDependentPopup(path,250,1000,false);
}

function validateSaveForm(mode)
{
	if(document.getElementsByName('print')[0].value=='1')
	{
		alert('Certificate has been closed. This Process has Already been Saved. Please Re-start the process')
		return false;
	}
	if(document.getElementsByName("requtionByFlag")[0].value=="NO")
	{
		if(document.getElementsByName("handOverTo")[0].checked==false && document.getElementsByName("handOverTo")[1].checked==false && document.getElementsByName("handOverTo")[2].checked==false)
		{
			alert("Please select hand over to");
			document.getElementsByName("handOverTo")[0].focus();
			return false;
		}
		if(document.getElementsByName("handOverTo")[0].checked)
		{
			
			
			if(document.getElementsByName("dispatchDate")[0].value=="")
			{
				alert("Please select dipatch date");
				document.getElementsByName("dispatchDate")[0].focus();
				return false;
			}
			
			var days=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("dispatchDate")[0].value)
			
			if(days<0)
			{
				alert("Dispatch date can not be greater than today date");
				document.getElementsByName("dispatchDate")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("deliveryType")[0].value=="-1")
			{
				alert("Please select delivery type");
				document.getElementsByName("deliveryType")[0].focus();
				return false;
			}
		}
		if(document.getElementsByName("handOverTo")[2].checked)
		{
			if(document.getElementsByName("relativeName")[0].value=="")
			{
				alert("Please enter relative name");
				document.getElementsByName("relativeName")[0].focus();
				return false;
			}
			if(document.getElementsByName("relationShipCode")[0].value=="-1")
			{
				alert("Please select relationship");
				document.getElementsByName("relationShipCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeAddr")[0].value=="")
			{
				alert("Please enter relative address");
				document.getElementsByName("relativeAddr")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeContactNo")[0].value=="")
			{
				alert("Please enter relative contact number");
				document.getElementsByName("relativeContactNo")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeIdRemark")[0].value=="")
			{
				alert("Please enter relative relative Id");
				document.getElementsByName("relativeIdRemark")[0].focus();
				return false;
			}
			if(document.getElementsByName("isAuthorityProved")[0].checked==false && document.getElementsByName("isAuthorityProved")[1].checked==false)
			{
				alert("Please select is authority proved");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				if(document.getElementsByName("authorityProofDescription")[0].value=="")
				{
					alert("Please enter authority proof description");
					document.getElementsByName("authorityProofDescription")[0].focus();
					return false;
				}
			}
			if(document.getElementsByName("isAuthorityProved")[1].checked)
			{
				alert("Without authority proof you cannot issue certificate ");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
		}
	}
	else
	{
		if(document.getElementsByName("handOverTo")[0].checked==false && document.getElementsByName("handOverTo")[1].checked==false )
		{
			alert("Please select hand over to");
			document.getElementsByName("handOverTo")[0].focus();
			return false;
		}
		
		if(document.getElementsByName("handOverTo")[1].checked)
		{
			if(document.getElementsByName("relativeName")[0].value=="")
			{
				alert("Please enter relative name");
				document.getElementsByName("relativeName")[0].focus();
				return false;
			}
			if(document.getElementsByName("relationShipCode")[0].value=="-1")
			{
				alert("Please select relationship");
				document.getElementsByName("relationShipCode")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeAddr")[0].value=="")
			{
				alert("Please enter relative address");
				document.getElementsByName("relativeAddr")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeContactNo")[0].value=="")
			{
				alert("Please enter relative contact number");
				document.getElementsByName("relativeContactNo")[0].focus();
				return false;
			}
			if(document.getElementsByName("relativeIdRemark")[0].value=="")
			{
				alert("Please enter relative relative Id");
				document.getElementsByName("relativeIdRemark")[0].focus();
				return false;
			}
			if(document.getElementsByName("isAuthorityProved")[0].checked==false && document.getElementsByName("isAuthorityProved")[1].checked==false)
			{
				alert("Please select is authority proved");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				if(document.getElementsByName("authorityProofDescription")[0].value=="")
				{
					alert("Please enter authority proof description");
					document.getElementsByName("authorityProofDescription")[0].focus();
					return false;
				}
			}
			if(document.getElementsByName("isAuthorityProved")[1].checked)
			{
				alert("Without authority proof u can not issue certificate ");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
		}
		
		
		
	}
	
	
	if(document.getElementsByName("definedIsuueType")[0].value==<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY%>)
	{
		if(document.getElementsByName("handOverTo")[1].checked)
		{
			if(document.getElementsByName("isAuthorityProved")[0].checked==false && document.getElementsByName("isAuthorityProved")[1].checked==false)
			{
				alert("Please select is authority proved");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
			
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				if(document.getElementsByName("authorityProofDescription")[0].value=="")
				{
					alert("Please enter authority proof description");
					document.getElementsByName("authorityProofDescription")[0].focus();
					return false;
				}
			}
			if(document.getElementsByName("isAuthorityProved")[1].checked)
			{
				alert("Without authority proof u can not issue certificate ");
				document.getElementsByName("isAuthorityProved")[0].focus();
				return false;
			}
		}
	}
	
	if(document.getElementsByName("handOverBy")[0].value=="-1")
	{
		alert("Please select handed over by");
		document.getElementsByName("handOverBy")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("handOverDate")[0].value=="")
	{
		alert("Please select handed over date");
		document.getElementsByName("handOverDate")[0].focus();
		return false;
	}
	
	var handOverdays=noOfDays(document.getElementsByName("sysdate")[0].value,document.getElementsByName("handOverDate")[0].value)
	
		if(handOverdays<0)
		{
			alert("HandOver date can not be greater than today date");
			document.getElementsByName("handOverDate")[0].focus();
			return false;
		}
		
	if(document.getElementsByName("requtionByFlag")[0].value=="NO")
	{
		if(document.getElementsByName("handOverTo")[0].checked)
		{
			if(document.getElementsByName("dispatchDate")[0].value!=document.getElementsByName("handOverDate")[0].value)
			{
				alert("Dispatch date and Hand over date should be equal");
				document.getElementsByName("dispatchDate")[0].focus;
				return false;
			}
		}
	}
		
	if(document.getElementsByName("reqStatus")[0].value==<%=MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER%>)
	{
		if(document.getElementsByName("duplicateReason")[0].value=="")
		{
			alert("Please enter the reason");
			document.getElementsByName("duplicateReason")[0].focus();
			return false;
		}
	}
	
	if(document.getElementsByName("selApprovedDate")[0].value!="")
	{
		var tempdays=noOfDays(document.getElementsByName("selApprovedDate")[0].value,document.getElementsByName("handOverDate")[0].value)
		//alert("tempdays "+tempdays)
		if(tempdays>0)
		{
			alert("HandOver date can not be less than Approved date "+document.getElementsByName("selApprovedDate")[0].value);
			document.getElementsByName("handOverDate")[0].focus();
			return false;
		}
	}
	
	submitPage(mode);
}

function clearForm()
{

document.getElementsByName("print")[0].value='0'

	if(document.getElementsByName("requtionByFlag")[0].value=="NO")
	{
		
		if(document.getElementsByName("handOverTo")[0].checked)
		{
			document.getElementsByName("dispatchDate")[0].value=document.getElementsByName("sysdate")[0].value;
			document.getElementsByName("deliveryType")[0].value="-1";
			document.getElementsByName("referenceNo")[0].value="";
			
		}
		if(document.getElementsByName("handOverTo")[2].checked)
		{
			document.getElementsByName("relativeName")[0].value="";
			document.getElementsByName("relationShipCode")[0].value="-1";
			document.getElementsByName("relativeAddr")[0].value="";
			document.getElementsByName("relativeContactNo")[0].value="";
			document.getElementsByName("relativeIdRemark")[0].value="";
			
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				document.getElementsByName("authorityProofDescription")[0].value="";
			}
			
		}
	}
	else
	{
		if(document.getElementsByName("handOverTo")[1].checked)
		{
			document.getElementsByName("relativeName")[0].value="";
			document.getElementsByName("relationShipCode")[0].value="-1";
			document.getElementsByName("relativeAddr")[0].value="";
			document.getElementsByName("relativeContactNo")[0].value="";
			document.getElementsByName("relativeIdRemark")[0].value="";
			
			
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				document.getElementsByName("authorityProofDescription")[0].value="";
			}
		}
	}
	
	
	if(document.getElementsByName("definedIsuueType")[0].value==<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY%>)
	{
		if(document.getElementsByName("handOverTo")[1].checked)
		{
			if(document.getElementsByName("isAuthorityProved")[0].checked)
			{
				document.getElementsByName("authorityProofDescription")[0].value="";
			}
		}
	}
	
	document.getElementsByName("handOverBy")[0].value="-1";
	document.getElementsByName("handOverDate")[0].value=document.getElementsByName("sysdate")[0].value;
	document.getElementsByName("remarks")[0].value="";
		
	if(document.getElementsByName("reqStatus")[0].value==<%=MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER%>)
	{
		document.getElementsByName("duplicateReason")[0].value="";
	}
}

function showAuthorityDesc()
{
	if(document.getElementsByName("isAuthorityProved")[0].checked)
	{
		document.getElementById("authorityProofDescId").style.display="";
	}
	else
	{
		document.getElementById("authorityProofDescId").style.display="none";
	}
}

function getSelectedReqList(mode)
{
	var len=document.getElementsByName("selReqNoArray").length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName("selReqNoArray")[i].checked)
		{
			
			document.getElementsByName("selApprovedDate")[0].value=document.getElementsByName("approvedDateArray")[i].value;
			//alert(document.getElementsByName("selApprovedDate")[0].value);
		}
	}
	
	
	
	submitPage(mode);
}

function showCriteria(mode)
{
	document.getElementsByName("patCrNo")[0].value="";
	document.getElementsByName("remarks")[0].value="";
	
	submitPage(mode);
	
	//alert(document.getElementsByName("searchType")[0].checked);
	//alert(document.getElementsByName("searchType")[1].checked);
	/*
	if(document.getElementsByName("searchType")[0].checked)
	{
		document.getElementById("patientWiseTableId").style.display="";
		document.getElementById("certificateTypeWiseTableId").style.display="none";
	}
	
	if(document.getElementsByName("searchType")[1].checked)
	{
		document.getElementById("patientWiseTableId").style.display="none";
		document.getElementById("certificateTypeWiseTableId").style.display="";
	}
	*/
	
}function callThisOnload(){	
	

	//alert(document.getElementsByName('print')[0].value);
	//alert(document.getElementsByName("hmode")[0].value=='SAVE');
	if(document.getElementsByName('print')[0].value=='1'&& document.getElementsByName("hmode")[0].value=='SAVE' )
	{
	//alert("1");
		openCertificatePrint();
		}
}

</script>
<his:TransactionContainer>
	<html:form action="/certificateHandover">
		<body onload="callThisOnload()">
		<his:TitleTag name="Certificate Handover">
		</his:TitleTag>


		<bean:define id="patCrNo" property="patCrNo"
			name="CertificateHandoverFB"></bean:define>
		<bean:define id="systemDate" name="CertificateHandoverFB"
			property="sysdate"></bean:define>
		<%
		String sysdate = (String) systemDate;
		%>
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="3">
				<tr>
					<td class="tdfonthead" width="25%" nowrap="nowrap"><font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
						key="selectCriteria" /> </font></td>
					<td width="25%" class="tdfont" nowrap="nowrap"><b> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> Certificate
					Type Wise<html:radio property="searchType"
						value="<%=MedicalBoardConfig.SEARCH_TYPE_CERTIFICATE_TYPE_WISE %>"
						name="CertificateHandoverFB" onclick="showCriteria('SEARCHTYPE')"></html:radio>
					</font> </b></td>
					<td width="25%" class="tdfont" nowrap="nowrap"><b> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> Patient Wise<html:radio
						property="searchType"
						value="<%=MedicalBoardConfig.SEARCH_TYPE_PATIENT_WISE %>"
						name="CertificateHandoverFB" onclick="showCriteria('SEARCHTYPE')"></html:radio>
					</font> </b></td>
					<td class="tdfont" width="25%" nowrap="nowrap"></td>
				</tr>
			</table>
		</his:ContentTag>
		<logic:equal value="<%=MedicalBoardConfig.SEARCH_TYPE_PATIENT_WISE %>"
			name="CertificateHandoverFB" property="searchType">
			<his:ContentTag>
				<his:InputCrNoTag name="CertificateHandoverFB">
				</his:InputCrNoTag>
			</his:ContentTag>
			<logic:notEmpty name="CertificateHandoverFB" property="patCrNo">
				<jsp:include page="/registration/patientDetail.cnt" flush="true" />
			</logic:notEmpty>

		</logic:equal>

		<logic:equal
			value="<%=MedicalBoardConfig.SEARCH_TYPE_CERTIFICATE_TYPE_WISE %>"
			name="CertificateHandoverFB" property="searchType">
			<his:ContentTag>
				<table width="100%" cellspacing="1" cellpadding="0"
					id="certificateTypeWiseTableId">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
							key="certificateType" /></b> </font></div>
						</td>
						<td width="25%" class="tdfont">
						<div align="left"><html:select name="CertificateHandoverFB"
							property="certificateTypeID" tabindex="1" styleClass="regcbo"
							onchange="getRequisitionDate('REQDATE');">
							<html:option value="-1">Select</html:option>
							<logic:present
								name="<%=MedicalBoardConfig.ALL_VARIFIED_CERTIFICATE_TYPE_LIST %>">
								<html:options
									collection="<%=MedicalBoardConfig.ALL_VARIFIED_CERTIFICATE_TYPE_LIST %>"
									labelProperty="label" property="value" />
							</logic:present>
						</html:select></div>
						</td>

						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
							key="requisitionDate" /></b> </font></div>
						</td>
						<td width="25%" class="tdfont">
						<div align="left"><html:select name="CertificateHandoverFB"
							property="requistionDate" tabindex="1" styleClass="regcbo"
							onchange="getCadidateList('GETCANDIDATELIST');">
							<html:option value="0">All</html:option>
							<logic:present
								name="<%=MedicalBoardConfig.ALL_REQ_DATE_BY_CERTIFICATE_TYPE_ID %>">
								<html:options
									collection="<%=MedicalBoardConfig.ALL_REQ_DATE_BY_CERTIFICATE_TYPE_ID %>"
									labelProperty="label" property="value" />
							</logic:present>
						</html:select></div>
						</td>
					</tr>
				</table>
			</his:ContentTag>
		</logic:equal>

		<logic:present
			name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
			<logic:notEmpty
				name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO%>">
				<his:SubTitleTag name="Requisition Detail List">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <!--			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllCandidate(this)">-->
							<b><bean:message key="select" /></b> </font></div>
							</td>
							<td width="13%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="certificateTypeName" /></b> </font></div>
							</td>
							<td width="17%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requisitionDate" /></b> </font></div>
							</td>


							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requestFrom" /></b> </font></div>
							</td>
							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="boardName" /></b> </font></div>
							</td>

							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="status" /></b> </font></div>
							</td>

						</tr>
						<logic:iterate id="requisitionVO"
							name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>"
							type="hisglobal.vo.MedicalBoardRequisitionVO">
							<tr>
								<td width="5%" class="tdfont">
								<div align="center"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <html:radio
									property="selReqNoArray" name="CertificateHandoverFB"
									value="<%=requisitionVO.getReqID() %>"
									onclick="getSelectedReqList('HANDOVERTOBYPATWISE')"></html:radio>
								<html:hidden property="approvedDateArray"
									name="CertificateHandoverFB"
									value="<%=requisitionVO.getApprovedDate()%>"></html:hidden>
									<input type="hidden" name="certificateNo" value="<%=requisitionVO.getCertificateNo()%>"> </font></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="certificateTypeName" /></div>
								</td>

								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="entryDate" /></div>
								</td>

								<td width="21%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="requestFromName" /></div>
								</td>
								<td width="21%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="boardName" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="reqStatusDesc" /></div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:notEmpty>
		</logic:present>
		<logic:present
			name="<%=MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE %>">
			<logic:notEmpty
				name="<%=MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE %>">
				<his:SubTitleTag name="Selected Requisition Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>

							<td width="13%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="certificateTypeName" /></b> </font></div>
							</td>
							<td width="17%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requisitionDate" /></b> </font></div>
							</td>


							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requestFrom" /></b> </font></div>
							</td>
							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="boardName" /></b> </font></div>
							</td>
							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="status" /></b> </font></div>
							</td>

						</tr>
						<logic:iterate id="requisitionVO"
							name="<%=MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE %>"
							type="hisglobal.vo.MedicalBoardRequisitionVO">
							<tr>
							
								<td width="17%" class="tdfont">
								<input type="hidden" name="certificateNo" value="<%=requisitionVO.getCertificateNo()%>">
								<div align="center"><bean:write name="requisitionVO"
									property="certificateTypeName" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="entryDate" /></div>
								</td>

								<td width="21%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="requestFromName" /></div>
								</td>
								<td width="21%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="boardName" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="reqStatusDesc" /></div>
								</td>
							</tr>
						</logic:iterate>
					</table>
					<his:SubTitleTag name="Hand Over Detail">
					</his:SubTitleTag>

					<his:ContentTag>
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td width="15%" class="tdfonthead">
								<div align="center"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
									key="handoverto" /></b> </font></div>
								</td>
								<logic:notEqual value="YES" name="CertificateHandoverFB"
									property="requtionByFlag">
									<td width="28%" class="tdfont">
									<div align="left">Organization<html:radio
										property="handOverTo"
										value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
										name="CertificateHandoverFB"
										onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
									</td>
								</logic:notEqual>
								<td width="28%" class="tdfont">
								<div align="left">Candidate<html:radio
									property="handOverTo"
									value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>"
									name="CertificateHandoverFB"
									onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
								</td>
								<td width="28%" class="tdfont">
								<div align="left">Relative<html:radio
									property="handOverTo"
									value="<%=MedicalBoardConfig.ISSUE_TO_RELATIVE %>"
									name="CertificateHandoverFB"
									onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
								</td>


							</tr>
						</table>
					</his:ContentTag>

					<logic:equal
						value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
						name="CertificateHandoverFB" property="definedIsuueType">
						<logic:equal
							value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>"
							name="CertificateHandoverFB" property="handOverTo">
							<table width="100%" border="0" cellspacing="1" cellpadding="0">
								<tr>

									<td width="25%" class="tdfonthead">
									<div align="right"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
										color="#FF0000">*</font> <bean:message key="isAuthorityProved" />
									</b> </font></div>
									</td>
									<td width="75%" class="tdfont" colspan="3">
									<div align="left">Yes<html:radio
										property="isAuthorityProved" name="CertificateHandoverFB"
										value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_YES %>"
										onclick="showAuthorityDesc()"></html:radio> No<html:radio
										property="isAuthorityProved" name="CertificateHandoverFB"
										value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_NO %>"
										onclick="showAuthorityDesc()"></html:radio></div>
									</td>
								</tr>
								<tr id="authorityProofDescId" style="display: none;">
									<td width="25%" class="tdfonthead">
									<div align="right"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
										color="#FF0000">*</font> <bean:message
										key="authorityProofDescription" /> </b> </font></div>
									</td>
									<td width="75%" class="tdfont" colspan="3">
									<div align="left"><html:textarea
										name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
										property="authorityProofDescription"
										onkeypress="return (validateTextArea(event,this,'50'))">
									</html:textarea></div>
									</td>
								</tr>

							</table>
						</logic:equal>
					</logic:equal>

					<logic:equal
						value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
						name="CertificateHandoverFB" property="handOverTo">
						<his:ContentTag>
							<table width="100%" cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
									<div align="right"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <font
										color="#FF0000"><b>*</b></font> <b><bean:message
										key="dipatchDate" /></b> </font></div>
									</td>
									<td width="25%" class="tdfont" nowrap="nowrap">
									<div align="left"><his:date name="dispatchDate"
										dateFormate="%d-%b-%Y" value="<%=sysdate %>"></his:date></div>
									</td>
									<td width="25%" class="tdfonthead">
									<div align="right"><font color="#000000" size="2"
										face="Verdana, Arial, Helvetica, sans-serif"> <font
										color="#FF0000"><b>*</b></font> <b><bean:message
										key="deliverytype" /></b> </font></div>
									</td>
									<td width="25%" class="tdfont" nowrap="nowrap">
									<div align="left"><html:select
										name="CertificateHandoverFB" property="deliveryType"
										tabindex="1" styleClass="regcbo">
										<html:option value="-1">Select Value</html:option>
										<html:option
											value="<%=MedicalBoardConfig.DELIVERY_TYPE_NORMAL_POST %>">Normal Post</html:option>
										<html:option
											value="<%=MedicalBoardConfig.DELIVERY_TYPE_REGISTERED_POST %>">Registered Post</html:option>
									</html:select></div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
									<div align="right"><font color="#000000" size="2"
										face="Verdana,Arial,Helvetica,sans-serif"> <b><bean:message
										key="referenceNo" />&nbsp;</b> </font></div>
									</td>
									<td class="tdfont" colspan="3"><font color="#000000"
										size="2" face="Verdana,Arial,Helvetica,sans-serif"> <html:textarea
										name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
										property="referenceNo"
										onkeypress="return (validateTextArea(event,this,'50'))">
									</html:textarea> </font></td>
								</tr>
							</table>
						</his:ContentTag>
					</logic:equal>

					<logic:equal value="<%=MedicalBoardConfig.ISSUE_TO_RELATIVE %>"
						name="CertificateHandoverFB" property="handOverTo">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="relativename" /> </b>
								</font></div>
								</td>
								<td width="25%" class="tdfont">
								<div align="left"><html:text property="relativeName"
									name="CertificateHandoverFB" maxlength="80" tabindex="1"
									onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
								</div>
								</td>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <font
									color="#FF0000">*</font> <b><bean:message
									key="realtionship" /></b> </font></div>
								</td>
								<td width="25%" class="tdfont">
								<div align="left"><html:select
									name="CertificateHandoverFB" property="relationShipCode">
									<html:option value="-1">Select Value</html:option>
									<logic:present
										name="<%=MedicalBoardConfig.ALL_RELATIONSHIP_LIST%>">
										<html:options
											collection="<%=MedicalBoardConfig.ALL_RELATIONSHIP_LIST%>"
											property="value" labelProperty="label" />
									</logic:present>
								</html:select></div>
								</td>

							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="relativeaddress" />
								</b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left"><html:textarea
									name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
									property="relativeAddr"
									onkeypress="return (validateTextArea(event,this,'100'))">
								</html:textarea></div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <font
									color="#FF0000">*</font> <b><bean:message
									key="relativeContactNo" /></b> </font></div>
								</td>
								<td width="25%" class="tdfont">
								<div align="left"><html:text property="relativeContactNo"
									name="CertificateHandoverFB" maxlength="50"
									onkeypress="return validateNumberWithDotsOnly(event)"
									tabindex="1"></html:text></div>
								</td>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <font
									color="#FF0000">*</font> <b><bean:message key="relativeId" /></b>
								</font></div>
								</td>
								<td width="25%" class="tdfont">
								<div align="left"><html:text property="relativeIdRemark"
									name="CertificateHandoverFB" maxlength="50" tabindex="1"
									onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
								</div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="isAuthorityProved" />
								</b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left">Yes<html:radio
									property="isAuthorityProved" name="CertificateHandoverFB"
									value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_YES %>"
									onclick="showAuthorityDesc()"></html:radio> No<html:radio
									property="isAuthorityProved" name="CertificateHandoverFB"
									value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_NO %>"
									onclick="showAuthorityDesc()"></html:radio></div>
								</td>
							</tr>
							<tr id="authorityProofDescId" style="display: none;">
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message
									key="authorityProofDescription" /> </b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left"><html:textarea
									name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
									property="authorityProofDescription"
									onkeypress="return (validateTextArea(event,this,'50'))">
								</html:textarea></div>
								</td>
							</tr>

						</table>
					</logic:equal>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message key="handOverBy" /></b>
							</font></div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left"><html:select name="CertificateHandoverFB"
								property="handOverBy">
								<html:option value="-1">Select Value</html:option>
								<logic:present
									name="<%=MedicalBoardConfig.ALL_HAND_OVER_BY_EMP_LIST%>">
									<html:options
										collection="<%=MedicalBoardConfig.ALL_HAND_OVER_BY_EMP_LIST%>"
										property="value" labelProperty="label" />
								</logic:present>
							</html:select></div>
							</td>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message key="handoverDate" /> </b> </font></div>
							</td>
							<td width="25%" class="tdfont" nowrap="nowrap">
							<div align="left"><his:date name="handOverDate"
								dateFormate="%d-%b-%Y" value="<%=sysdate %>"></his:date></div>
							</td>
						</tr>
						<logic:equal
							value="<%=MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER %>"
							name="CertificateHandoverFB" property="reqStatus">
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="duplicateReason" />
								</b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left"><html:textarea
									name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
									property="duplicateReason"
									onkeypress="return (validateTextArea(event,this,'100'))">
								</html:textarea></div>
								</td>
							</tr>
						</logic:equal>
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
								key="remarks" /> </b> </font></div>
							</td>
							<td width="75%" class="tdfont" colspan="3">
							<div align="left"><html:textarea
								name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
								property="remarks"
								onkeypress="return (validateTextArea(event,this,'50'))">
							</html:textarea></div>
							</td>
						</tr>

					</table>

				</his:ContentTag>
			</logic:notEmpty>
		</logic:present>


		<logic:present
			name="<%=MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE %>">
			<logic:notEmpty
				name="<%=MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE%>">
				<his:SubTitleTag name="Candidate List">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="5%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <!--			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllCandidate(this)">-->
							<b><bean:message key="select" /></b> </font></div>
							</td>
							<td width="13%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="candidate" /> <bean:message key="name" /></b> </font></div>
							</td>
							<td width="17%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="gender/age" /></b> </font></div>
							</td>

							<td width="15%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requisitionDate" /></b> </font></div>
							</td>
							<td width="15%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="medicalCertNo" /></b> </font></div>
							</td>
							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requestFrom" /></b> </font></div>
							</td>

						</tr>
						<logic:iterate id="requisitionVO"
							name="<%=MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE %>"
							type="hisglobal.vo.MedicalBoardRequisitionVO">
							<tr>
								<td width="5%" class="tdfont">
								<div align="center"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <!--			 	<html:checkbox property="reqIdArray" name="CertificateHandoverFB" value="<%=requisitionVO.getReqID() %>"></html:checkbox>-->
								<html:radio property="reqIdArray" name="CertificateHandoverFB"
									value="<%=requisitionVO.getReqID() %>"></html:radio> <html:hidden
									name="CertificateHandoverFB" property="approvedDateArray"
									value="<%=requisitionVO.getApprovedDate() %>" /> </font></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="patName" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="patAgeGender" /></div>
								</td>

								<td width="21%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="entryDate" /></div>
								</td>
								<td width="15%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="certificateNo" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="requestFromName" /></div>
								</td>
							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>
			</logic:notEmpty>
		</logic:present>



		<logic:present name="<%=MedicalBoardConfig.SELECTED_CANDIDATE_LIST %>">
			<logic:notEmpty
				name="<%=MedicalBoardConfig.SELECTED_CANDIDATE_LIST %>">
				<his:SubTitleTag name="Candidate Detail">
				</his:SubTitleTag>
				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="13%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="candidate" /> <bean:message key="name" /></b> </font></div>
							</td>
							<td width="17%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="gender/age" /></b> </font></div>
							</td>


							<td width="15%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requisitionDate" /></b> </font></div>
							</td>

							<td width="15%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="medicalCertNo" /></b> </font></div>
							</td>

							<td width="21%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="requestFrom" /></b> </font></div>
							</td>

						</tr>
						<logic:iterate id="requisitionVO"
							name="<%=MedicalBoardConfig.SELECTED_CANDIDATE_LIST %>"
							type="hisglobal.vo.MedicalBoardRequisitionVO">
							<tr>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="patName" /></div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="patAgeGender" /></div>
								</td>

								<td width="15%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="entryDate" /></div>
								</td>
								<td width="15%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="certificateNo" />
									<input type="hidden" name="certificateNo" value="<%=requisitionVO.getCertificateNo()%>">
									</div>
								</td>
								<td width="17%" class="tdfont">
								<div align="center"><bean:write name="requisitionVO"
									property="requestFromName" /></div>
								</td>

							</tr>
						</logic:iterate>
					</table>
				</his:ContentTag>

				<his:SubTitleTag name="Hand Over Detail">
				</his:SubTitleTag>

				<his:ContentTag>
					<table width="100%" cellspacing="1" cellpadding="0">
						<tr>
							<td width="15%" class="tdfonthead">
							<div align="center"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:message
								key="handoverto" /></b> </font></div>
							</td>
							<logic:notEqual value="YES" name="CertificateHandoverFB"
								property="requtionByFlag">
								<td width="28%" class="tdfont">
								<div align="left">Organization<html:radio
									property="handOverTo"
									value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
									name="CertificateHandoverFB"
									onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
								</td>
							</logic:notEqual>
							<td width="28%" class="tdfont">
							<div align="left">Candidate<html:radio
								property="handOverTo"
								value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>"
								name="CertificateHandoverFB"
								onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
							</td>
							<td width="28%" class="tdfont">
							<div align="left">Relative<html:radio property="handOverTo"
								value="<%=MedicalBoardConfig.ISSUE_TO_RELATIVE %>"
								name="CertificateHandoverFB"
								onclick="submitPage('SELECTHANDOVERTO');"></html:radio></div>
							</td>


						</tr>
					</table>
				</his:ContentTag>

				<logic:equal
					value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
					name="CertificateHandoverFB" property="definedIsuueType">
					<logic:equal
						value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>"
						name="CertificateHandoverFB" property="handOverTo">
						<table width="100%" border="0" cellspacing="1" cellpadding="0">
							<tr>

								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message key="isAuthorityProved" />
								</b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left">Yes<html:radio
									property="isAuthorityProved" name="CertificateHandoverFB"
									value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_YES %>"
									onclick="showAuthorityDesc()"></html:radio> No<html:radio
									property="isAuthorityProved" name="CertificateHandoverFB"
									value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_NO %>"
									onclick="showAuthorityDesc()"></html:radio></div>
								</td>
							</tr>
							<tr id="authorityProofDescId" style="display: none;">
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
									color="#FF0000">*</font> <bean:message
									key="authorityProofDescription" /> </b> </font></div>
								</td>
								<td width="75%" class="tdfont" colspan="3">
								<div align="left"><html:textarea
									name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
									property="authorityProofDescription"
									onkeypress="return (validateTextArea(event,this,'100'))">
								</html:textarea></div>
								</td>
							</tr>

						</table>
					</logic:equal>
				</logic:equal>

				<logic:equal
					value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>"
					name="CertificateHandoverFB" property="handOverTo">
					<his:ContentTag>
						<table width="100%" cellspacing="1" cellpadding="0">
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <font
									color="#FF0000"><b>*</b></font> <b><bean:message
									key="dipatchDate" /></b> </font></div>
								</td>
								<td width="25%" class="tdfont" nowrap="nowrap">
								<div align="left"><his:date name="dispatchDate"
									dateFormate="%d-%b-%Y" value="<%=sysdate %>"></his:date></div>
								</td>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana, Arial, Helvetica, sans-serif"> <font
									color="#FF0000"><b>*</b></font> <b><bean:message
									key="deliverytype" /></b> </font></div>
								</td>
								<td width="25%" class="tdfont" nowrap="nowrap">
								<div align="left"><html:select
									name="CertificateHandoverFB" property="deliveryType"
									tabindex="1" styleClass="regcbo">
									<html:option value="-1">Select Value</html:option>
									<html:option
										value="<%=MedicalBoardConfig.DELIVERY_TYPE_NORMAL_POST %>">Normal Post</html:option>
									<html:option
										value="<%=MedicalBoardConfig.DELIVERY_TYPE_REGISTERED_POST %>">Registered Post</html:option>
								</html:select></div>
								</td>
							</tr>
							<tr>
								<td width="25%" class="tdfonthead">
								<div align="right"><font color="#000000" size="2"
									face="Verdana,Arial,Helvetica,sans-serif"> <b><bean:message
									key="referenceNo" />&nbsp;</b> </font></div>
								</td>
								<td class="tdfont" colspan="3"><font color="#000000"
									size="2" face="Verdana,Arial,Helvetica,sans-serif"> <html:textarea
									name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
									property="referenceNo"
									onkeypress="return (validateTextArea(event,this,'50'))">
								</html:textarea> </font></td>
							</tr>
						</table>
					</his:ContentTag>
				</logic:equal>

				<logic:equal value="<%=MedicalBoardConfig.ISSUE_TO_RELATIVE %>"
					name="CertificateHandoverFB" property="handOverTo">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message key="relativename" /> </b> </font></div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left"><html:text property="relativeName"
								name="CertificateHandoverFB" maxlength="80" tabindex="1"
								onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
							</div>
							</td>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message key="realtionship" /></b>
							</font></div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left"><html:select name="CertificateHandoverFB"
								property="relationShipCode">
								<html:option value="-1">Select Value</html:option>
								<logic:present
									name="<%=MedicalBoardConfig.ALL_RELATIONSHIP_LIST%>">
									<html:options
										collection="<%=MedicalBoardConfig.ALL_RELATIONSHIP_LIST%>"
										property="value" labelProperty="label" />
								</logic:present>
							</html:select></div>
							</td>

						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message key="relativeaddress" />
							</b> </font></div>
							</td>
							<td width="75%" class="tdfont" colspan="3">
							<div align="left"><html:textarea
								name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
								property="relativeAddr"
								onkeypress="return (validateTextArea(event,this,'100'))">
							</html:textarea></div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message
								key="relativeContactNo" /></b> </font></div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left"><html:text property="relativeContactNo"
								name="CertificateHandoverFB" maxlength="50"
								onkeypress="return validateNumberWithDotsOnly(event)"
								tabindex="1"></html:text></div>
							</td>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <font
								color="#FF0000">*</font> <b><bean:message key="relativeId" /></b>
							</font></div>
							</td>
							<td width="25%" class="tdfont">
							<div align="left"><html:text property="relativeIdRemark"
								name="CertificateHandoverFB" maxlength="50" tabindex="1"
								onkeypress="return validateAlphaNumOnly(this,event)"></html:text>
							</div>
							</td>
						</tr>
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message key="isAuthorityProved" />
							</b> </font></div>
							</td>
							<td width="75%" class="tdfont" colspan="3">
							<div align="left">Yes<html:radio
								property="isAuthorityProved" name="CertificateHandoverFB"
								value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_YES %>"
								onclick="showAuthorityDesc()"></html:radio> No<html:radio
								property="isAuthorityProved" name="CertificateHandoverFB"
								value="<%=MedicalBoardConfig.IS_AUTHORITY_PROVED_NO %>"
								onclick="showAuthorityDesc()"></html:radio></div>
							</td>
						</tr>
						<tr id="authorityProofDescId" style="display: none;">
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message
								key="authorityProofDescription" /> </b> </font></div>
							</td>
							<td width="75%" class="tdfont" colspan="3">
							<div align="left"><html:textarea
								name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
								property="authorityProofDescription"
								onkeypress="return (validateTextArea(event,this,'100'))">
							</html:textarea></div>
							</td>
						</tr>

					</table>
				</logic:equal>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <font
							color="#FF0000">*</font> <b><bean:message key="handOverBy" /></b>
						</font></div>
						</td>
						<td width="25%" class="tdfont">
						<div align="left"><html:select name="CertificateHandoverFB"
							property="handOverBy">
							<html:option value="-1">Select Value</html:option>
							<logic:present
								name="<%=MedicalBoardConfig.ALL_HAND_OVER_BY_EMP_LIST%>">
								<html:options
									collection="<%=MedicalBoardConfig.ALL_HAND_OVER_BY_EMP_LIST%>"
									property="value" labelProperty="label" />
							</logic:present>
						</html:select></div>
						</td>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
							color="#FF0000">*</font> <bean:message key="handoverDate" /> </b> </font></div>
						</td>
						<td width="25%" class="tdfont" nowrap="nowrap">
						<div align="left"><his:date name="handOverDate"
							dateFormate="%d-%b-%Y" value="<%=sysdate %>"></his:date></div>
						</td>
					</tr>
					<logic:equal
						value="<%=MedicalBoardConfig.REQUEST_STATUS_CERTIFICATE_HANDOVER %>"
						name="CertificateHandoverFB" property="reqStatus">
						<tr>
							<td width="25%" class="tdfonthead">
							<div align="right"><font color="#000000" size="2"
								face="Verdana, Arial, Helvetica, sans-serif"> <b> <font
								color="#FF0000">*</font> <bean:message key="duplicateReason" />
							</b> </font></div>
							</td>
							<td width="75%" class="tdfont" colspan="3">
							<div align="left"><html:textarea
								name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
								property="duplicateReason"
								onkeypress="return (validateTextArea(event,this,'100'))">
							</html:textarea></div>
							</td>
						</tr>
					</logic:equal>
					<tr>
						<td width="25%" class="tdfonthead">
						<div align="right"><font color="#000000" size="2"
							face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
							key="remarks" /> </b> </font></div>
						</td>
						<td width="75%" class="tdfont" colspan="3">
						<div align="left"><html:textarea
							name="CertificateHandoverFB" tabindex="1" rows="2" cols="70"
							property="remarks"
							onkeypress="return (validateTextArea(event,this,'50'))">
						</html:textarea></div>
						</td>
					</tr>

				</table>


			</logic:notEmpty>
		</logic:present>

		<his:ButtonToolBarTag>
			<his:statusDone>
				<img class="button"
					src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
					style="cursor: pointer" tabindex="1"
					onclick="submitPage('CANCEL');"
					onkeypress="if(event.keyCode==13) submitPage('CANCEL');">
			</his:statusDone>
			<logic:present
				name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
				<logic:empty
					name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1" onclick="submitPage('NEW');"
						onkeypress="if(event.keyCode==13) submitPage('NEW');">
				</logic:empty>
			</logic:present>


			<logic:present
				name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
				<logic:notEmpty
					name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick="submitPage('SEARCHTYPE');"
						onkeypress="if(event.keyCode==13) submitPage('SEARCHTYPE');">
				</logic:notEmpty>
			</logic:present>
			<logic:present
				name="<%=MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE %>">
				<logic:notEmpty
					name="<%=MedicalBoardConfig.CANDIDATE_LIST_BY_CERTIFIACTE_TYPE_AND_REQDATE %>">
					<img class='button'
						src='<his:path src="/hisglobal/images/GoNew.png"/>'
						style="cursor: pointer" tabindex='1'
						onclick="validateGetHandOverDtlForm('HANDOVERTO');"
						onkeypress="if(event.keyCode==13)validateGetHandOverDtlForm('HANDOVERTO');")>
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick="submitPage('SEARCHTYPE');"
						onkeypress="if(event.keyCode==13) submitPage('SEARCHTYPE');">
				</logic:notEmpty>
			</logic:present>
			<logic:present
				name="<%=MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE %>">
				<logic:notEmpty
					name="<%=MedicalBoardConfig.SELECTED_REQUISITION_BY_PATIENTWISE %>">
					<img class='button'
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
						style="cursor: pointer" tabindex='1'
						onclick="validateSaveForm('SAVE'); "
						onkeypress="if(event.keyCode==13)validateSaveForm('SAVE');")>
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick="submitPage('GETREQLIST');"
						onkeypress="if(event.keyCode==13) submitPage('GETREQLIST');">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
						style="cursor: pointer" tabindex="1" onclick="clearForm();"
						onkeypress="if(event.keyCode==13) clearForm();">
				</logic:notEmpty>
			</logic:present>
			<logic:present
				name="<%=MedicalBoardConfig.SELECTED_CANDIDATE_LIST %>">
				<logic:notEmpty
					name="<%=MedicalBoardConfig.SELECTED_CANDIDATE_LIST %>">
					<img class='button'
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'
						style="cursor: pointer" tabindex='1'
						onclick="validateSaveForm('SAVE'); "
						onkeypress="if(event.keyCode==13)validateSaveForm('SAVE');")>
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
						style="cursor: pointer" tabindex="1"
						onclick="submitPage('GETCANDIDATELIST');"
						onkeypress="if(event.keyCode==13) submitPage('GETCANDIDATELIST');">
					<img class="button"
						src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'
						style="cursor: pointer" tabindex="1" onclick="clearForm();"
						onkeypress="if(event.keyCode==13) clearForm();">
				</logic:notEmpty>
			</logic:present>

			<!--<img class='button'
				src='<his:path src="/hisglobal/images/btn-pnt.png"/>'
				style="cursor: pointer" tabindex='1'
				onclick="openCertificatePrint(event); "
				onkeypress="if(event.keyCode==13) openCertificatePrint(event);")>
		-->
		</his:ButtonToolBarTag>

		<logic:present
			name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
			<logic:empty
				name="<%=MedicalBoardConfig.REQUISITION_NO_LIST_BY_CRNO_NO %>">
				<font color="#FF0000" size="2"> This patient does not have
				any requisition </font>
			</logic:empty>
		</logic:present>

		<his:status />
		<html:hidden name="CertificateHandoverFB" property="hmode" />
		<html:hidden name="CertificateHandoverFB" property="requtionBy" />
		<html:hidden name="CertificateHandoverFB" property="requtionByFlag" />
		<html:hidden name="CertificateHandoverFB" property="definedIsuueType" />
		<html:hidden name="CertificateHandoverFB" property="reqStatus" />
		<html:hidden name="CertificateHandoverFB" property="sysdate" />
		<html:hidden name="CertificateHandoverFB" property="templateId" />
		<html:hidden name="CertificateHandoverFB" property="print" />
		<html:hidden name="CertificateHandoverFB" property="handOverDate" />
		<html:hidden name="CertificateHandoverFB" property="remarks" />
		<html:hidden name="CertificateHandoverFB" property="selApprovedDate" />
		<html:hidden name="CertificateHandoverFB" property="orgName" />
		<html:hidden name="CertificateHandoverFB" property="patCrNo" />
		<html:hidden name="CertificateHandoverFB" property="opinion" />
		<html:hidden name="CertificateHandoverFB" property="isPatient" />
		

		</body>
	</html:form>
</his:TransactionContainer>
