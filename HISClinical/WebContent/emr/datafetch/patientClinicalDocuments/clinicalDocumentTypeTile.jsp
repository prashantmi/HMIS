<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="ehr.EHRConfig"%>
<%@page import="hisglobal.exceptions.HisRecordNotFoundException"%>
<%@page import="hisglobal.presentation.Status"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="javax.servlet.http.HttpServletRequest"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script src="/HISClinical/emr/datafetch/js/clinical_document.js"></script>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<style>

      .success {
  -webkit-animation: seconds 1.0s forwards;
  -webkit-animation-iteration-count: 1;
  -webkit-animation-delay: 10s;
  animation: seconds 1.0s forwards;
  animation-iteration-count: 1;
  animation-delay: 10s;
  position: relative;
    
}
@-webkit-keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px; 
    position: absolute;   
  }
}
@keyframes seconds {
  0% {
    opacity: 1;
  }
  100% {
    opacity: 0;
    left: -9999px;
    position: absolute;     
  }
}

</style>

<script>


 window.onload=function()
{
	
	if(document.getElementsByName("saveFlag")[0].value=="1")
			saveFlag();
	
	if(document.getElementsByName("clinicalDocumentMode")[0].value=="CLINICALDOCUMENTS" || document.getElementsByName("clinicalDocumentMode")[0].value=="MODIFY")
		{
	//	alert(document.getElementsByName("clinicalDocumentMode")[0].value);
	<%Status objStatus = new Status();
	objStatus.add(Status.NEW, "", "Clinical Document Saved Successfully");
	WebUTIL.setStatus(request, objStatus);
	%>		
		}
	
	submitdocumentType();
}

 
 function submitDocumentDetails(obj,obj1,obj2,obj3,mode)
 {
 		//alert("inside submit"+obj)
 		document.getElementsByName("documentId")[0].value=obj;
 		//document.getElementsByName("documentHeader")[0].value=obj1;
 		//document.getElementsByName("serialNo")[0].value=obj2;
 		document.getElementsByName("documentType")[0].value=obj3;
 		document.getElementsByName("hmode")[0].value=mode;
 		document.forms[0].submit();
 }

function saveFlag()
{
	        document.getElementsByName("saveFlag")[0].value="0";
			document.getElementById("savemsg").style.display="block";
			//document.getElementById("notsavemsg").style.display="none";
}

var documentGenerationMode=0;

function generateAutomaticDocument(e)
{
	<% String gen_url="/HISClinical/ehr/patientClinicalDocuments.cnt?hmode=VIEWAUTOMATICGENRATEDDOCUMENT";%>
	var hmode="VIEWAUTOMATICGENRATEDDOCUMENT";
	var autoDocumentCalledFrom=0  //patientDocument =0, patientRefered=1, externalRefered=2
	var documentType=document.getElementsByName("documentType")[0].value;
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var deskId=document.getElementsByName("deskId")[0].value;
	var episodeCode=document.getElementsByName("episodeCode")[0].value;
	var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
	var url="/HISClinical/opd/opdPatientDocument.cnt?hmode="+hmode+"&patCrNo="+patCrNo+"&autoDocumentCalledFrom="+autoDocumentCalledFrom+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&documentGenerationMode="+documentGenerationMode+"&deskId="+deskId+"&documentType="+documentType;
	//alert("url :"+url);
	openDependentPopup(url,e,700,700,true);
	
}
function setAccessDocumentPriv(e,accessType,obj,docStatus,url)
{
	//if(accessType==1 || accessType==4)
	//{
	///	alert("Dont need to set Access Priviledges");
	//	return;		
//	}
		
		// alert("status"+docStatus);
		var status="<%=EHRConfig.CLINICAL_DOCUMENT_STATUS_INPROCESS%>";
		// alert("stat"+status)
		if(docStatus==status)
		{
			alert("Please First Generate The Document");
			return false;
		}
		else
		{
			document.getElementsByName("documentId")[0].value=obj;
			var episodeVisitNo=document.getElementsByName("episodeVisitNo")[0].value;
			url=url+'&documentId='+document.getElementsByName("documentId")[0].value+'&episodeVisitNo='+episodeVisitNo;
			//alert("url"+url)
			openDependentPopup(url,e,700,700,true);
		}
}
  
function submitdocumentType(event,obj)
{
	// alert("inside submit")
	//alert("document type "+document.getElementsByName("documentType")[1].value)
	obj = document.getElementsByName("documentType")[1];
	
	var documentTitle="<%=EHRConfig.DOCUMENT_HEADER_DISCHARGE_TYPE_DESC%>";
	if(document.getElementsByName("documentType")[0].value=="<%=EHRConfig.DOCUMENT_TYPE_DISCHARGE%>")
	{
		document.getElementsByName("documentTitle")[0].value=documentTitle;
	}
	else if(document.getElementsByName("documentType")[0].value== "<%=EHRConfig.DOCUMENT_TYPE_CASESHEET%>")
	{
		document.getElementsByName("documentTitle")[0].value="<%=EHRConfig.DOCUMENT_TYPE_CASESHEET_DESC%>";
	}
	else if(document.getElementsByName("documentType")[0].value=="<%=EHRConfig.DOCUMENT_TYPE_GENERAL%>")
	{
		document.getElementsByName("documentTitle")[0].value="<%=EHRConfig.DOCUMENT_TYPE_GENERAL_DESC%>";
	}
	else
	{
		document.getElementsByName("documentTitle")[0].value="";
	}
	
	var documentType=0;
	if(obj.value!="-1"){
		//if(document.getElementsByName("documentType")[0].value=="<%=EHRConfig.DOCUMENT_TYPE_DISCHARGE%>")
		{
		var documentTitle=obj.value.split("#")[2];
		documentType=obj.value.split("#")[0];
		//alert(obj.value);
		documentGenerationMode=obj.value.split("#")[3];
		//alert(documentGenerationMode);
		document.getElementsByName("documentTitle")[0].value=documentTitle;
		document.getElementsByName("documentType")[0].value=obj.value;
		document.getElementsByName("documentGenerationMode")[0].value=documentGenerationMode;
		//alert(document.getElementsByName("documentGenerationMode")[0].value);
		
		}}
	else{
		//document.getElementsByName("documentTitle")[0].value="";
	}
	
	if((documentGenerationMode == 2) || (documentGenerationMode==3))
	{
		document.getElementById('next').style.display = 'none'
		document.getElementById('generate').style.display = ''
		document.getElementById('imgStepGenerateId').style.display = 'none'
	}
	else
	{
		document.getElementById('next').style.display = ''
		document.getElementById('generate').style.display = 'none'
		document.getElementById('imgStepGenerateId').style.display = ''
	}
	//alert("document Title "+document.getElementsByName("documentTitle")[0].value)
}

function viewPrintDocument(e,docStatus,url)
{
	var status="<%=EHRConfig.CLINICAL_DOCUMENT_STATUS_INPROCESS%>";
	
	if(docStatus==status)
	{
			alert("Please First Generate The Document");
			return false;
	}
	else
	{
		openDependentPopup(url,e,700,700,true);
	}
}

function validateForm()
{
	//alert("ddkk"+document.getElementsByName("documentGenerationType")[0].checked)
	//alert("ddkknnnn"+document.getElementsByName("documentGenerationType")[1].value)
	if(isSelected(document.getElementsByName("documentType")[1],"Document Type") &&
		isEmpty(document.getElementsByName("documentTitle")[0],"Document Title"))
	{
		document.getElementsByName("documentType")[0].value=document.getElementsByName("documentType")[1].value;
	}
	else
		return false;
	
	
	var dup = false;
	var proHead = document.getElementsByName("documentTitle")[0].value;
	for(var i=0; i<document.getElementsByName("proName").length; i++)
		if(document.getElementsByName("proName")[i].value == proHead)
		{
			dup = true;
			break;
		}
	
	if(dup)
	{
		alert("Document Header already Used!");
		document.getElementsByName("documentTitle")[0].focus();
		return false;
	}
	return true;
}


/* function generateDocument()
{	
	//document.getElementsByName('documentHTML')[0].value = document.getElementById('documentHtml').innerHTML;
	//opener.document.getElementsByName('hmode')[0].value = "GENRATEPROFILE";
	//opener.document.forms[0].hmode.value = "GENRATEPROFILE";
	//opener.submit();
	//opener.submitForm('GENRATEDOCUMENT');
	document.forms[0].hmode.value = "GENRATEDOCUMENT";
	document.forms[0].submit();
	//window.close();
} */

function generateDocument(e,url)
{
	var valid=false;
	var count =0;
	var selectedIndex=document.getElementsByName("selectedIndex");
	var i=0;
	var j=0;
	for(;i<selectedIndex.length;i++)
	{
		if(selectedIndex[i].checked)
		{
			valid=true;
			count=count+1;
			j=i;
		}
	}
	//alert("j"+j)
	if(valid==true && count==1)
	{
		if(document.getElementsByName("proStatus")[j].value == 1) // Generated
		{
			alert("Document already Generated!");
			return;
		}
		
		var selectedIndex =document.getElementsByName("selectedIndex")[j].value;
		var deskId=document.getElementsByName("deskId")[0].value;
		var deskType=document.getElementsByName("deskType")[0].value;
		var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value;
		var wardCode=document.getElementsByName("wardCode")[0].value;
		var patCategoryCode=document.getElementsByName("patCategoryCode")[0].value;
		var patCrNo=document.getElementsByName("patCrNo")[0].value;
		var episodeCode=document.getElementsByName("episodeCode")[0].value;
		var admissionNo=document.getElementsByName("admissionNo")[0].value;
		url=url+'&selectedIndex='+selectedIndex+'&deskId='+deskId+'&departmentUnitCode='+departmentUnitCode+'&patCategoryCode='+patCategoryCode+'&deskType='+deskType+'&patCrNo='+patCrNo+'&episodeCode='+episodeCode+'&admissionNo='+admissionNo+'&wardCode='+wardCode;
		//   alert("url "+url)
		openDependentPopup(url,e,700,700,true);
	}
	else if(valid==true && count>1)
	{
		alert("Please Select Single Profile")
	}
	else
	{
		alert("Please Select Profile");
	} 
}


function validateRemoveDocument()
{
	var chks = document.getElementsByName("selectedIndex");
	var sels = 0;
	for(var i=0; i<chks.length; i++)
	{
		if(chks[i].checked)
			sels++;
	}
	if(sels==0)
	{
		alert("Select at Least one Document to Delete");
		chks[0].focus();
		return false; 
	}
	if(confirm("Are You sure to delete ("+sels+") Documents ?"))
		return true;
	else
		return false;
}

function removeDocument(documentId, serialNo, choice)
{
	document.getElementsByName("selectedDocumentId")[0].value=documentId;
	document.getElementsByName("selectedSerialNo")[0].value=serialNo;
	document.getElementsByName("selectedIndex")[0].value=choice;
	submitFormOnValidate(true,'REMOVECLINICALDOCUMENT');
}

</script>


<his:TitleTag key="patientclinicalDocument">
</his:TitleTag>

<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include>

<his:statusList>
	<his:SubTitleTag key="previousClinicalDocDtl">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
			<tr>
				<td width="5%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="sel" />
						</font>
					</div>
				</td>

				<td width="20%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="documentTitle" />
						</font>
					</div>
				</td>
				
				<td width="20%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="documentType" />
						</font>
					</div>
				</td>
<%--
				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="accessType" /></b>
						</font>
					</div>
				</td>

				<td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="userlevel" /></b>
						</font>
					</div>
				</td>
--%>
				<td width="25%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="creationDate" />
						</font>
					</div>
				</td>
				
				<%-- <td width="15%" class="tdfonthead">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="privilege" />
						</font>
					</div>
				</td> --%>
				<td width="15%" class="tdfonthead">
					
				</td>
			</tr>
			<logic:iterate id="patDocumentVO" name="<%=EHRConfig.PATIENT_DOCUMENT_EPISODE_DOCUMENTS_LIST%>" indexId="idx" type="emr.vo.PatientClinicalDocDetailVO">
				<%
					String modifyDivId = "modify" + idx.toString();
				%>
			<tr>
				<td width="5%" class="tdfont">
					<div align="center">
						<html:checkbox name="PatientClinicalDocumentsFB" property="selectedIndex" value="<%=idx.toString()%>" tabindex="1" onchange="onChangeSelectedIndex(this)"></html:checkbox>
					</div>
				</td>

				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				        <% String url="/HISClinical/emr/patientClinicalDocuments.cnt?hmode=VIEWPRINTDOCUMENT&documentId="+patDocumentVO.getDocumentId(); %>
						<a style="cursor: pointer;" onclick="viewPrintDocument(event,'<%=patDocumentVO.getDocStatus() %>','<%=url%>');">
						<bean:write name="patDocumentVO" property="documentTitle"/>
						</a>
				
						<input type="hidden" name="proName" value='<bean:write name="patDocumentVO" property="documentTitle"/>'>
						<input type="hidden" name="proStatus" value='<bean:write name="patDocumentVO" property="docStatus"/>'>
						
						</b></font>
					</div>
					
				</td>
				
				<td width="20%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patDocumentVO" property="documentTypeDesc" /></b>
						</font>
					</div>
				</td>
				
<%--				<td width="15%" class="tdfont">
					<div align="center">
						<% String selonchange="setUserLevel("+idx.toString()+")"; %>
						<html:select name="PatientClinicalDocumentsFB" property="selectedAccessType" onchange="<%=selonchange%>" value="<%=patDocumentVO.getAccessType()%>" tabindex="1" disabled="true">
							<html:option value="<%=EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_ALL%>">To All</html:option>
							<html:option value="<%=EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_OWNING_UNIT%>">Owning Unit</html:option>
							<html:option value="<%=EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_UNIT_SPECIFIC%>">Unit Specific</html:option>
							<html:option value="<%=EHRConfig.PATIENT_DOCUMENT_ACCESS_TYPE_RESTRICTED_USERS%>">Restricted User</html:option>
						</html:select>
					</div>
				</td>

				<td width="15%" class="tdfont">
					<div align="center">
						<html:select name="PatientClinicalDocumentsFB" property="selectedUserLevel" value="<%=patDocumentVO.getUserLevel()%>" tabindex="1" disabled="true">
							<html:option value="-1">Select Value</html:option>
							<html:option value="0">No Level</html:option>
							<html:option value="1">Level 1</html:option>
							<html:option value="2">Level 2</html:option>
							<html:option value="3">Level 3</html:option>
							<html:option value="4">Level 4</html:option>
							<html:option value="5">Level 5</html:option>
							<html:option value="6">Level 6</html:option>
						</html:select>
					</div>
				</td>
--%>
				<td width="25%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:write name="patDocumentVO" property="entryDate" /></b>
						</font>
					</div>
				</td>
				
				<!-- (1) this block has been modified   -->
				<%-- <logic:equal name="patDocumentVO" property="documentType" value="<%=EHRConfig.DOCUMENT_TYPE_DISCHARGE%>"> 
				<td width="15%" class="tdfont">
					
				</td>
				</logic:equal>
				<logic:notEqual name="patDocumentVO" property="documentType" value="<%=EHRConfig.DOCUMENT_TYPE_DISCHARGE%>">
				--%>
				<%-- <td width="15%" class="tdfont">
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
				  		<% String urlPri="/HISClinical/ehr/patientClinicalDocuments.cnt?hmode=SETACCESSPRIV&accessPrivilSerialNo="+idx.toString(); %>
						<a style="cursor: pointer;" onclick="setAccessDocumentPriv(event,'<%=patDocumentVO.getAccessType()%>','<%=patDocumentVO.getDocumentId()%>','<%=patDocumentVO.getDocStatus()%>','<%=urlPri%>');">
							SET
						</a>
						</b></font>
					</div>
				</td> --%>
				<%--</logic:notEqual> --%>
				<!-- (1) Modification ends here --> 
				<logic:equal name="patDocumentVO" property="docStatus" value="<%=EHRConfig.CLINICAL_DOCUMENT_STATUS_GENERATED %>">
				<td width="15%" class="tdfont">
					
				</td>
				</logic:equal>
				<logic:notEqual name="patDocumentVO" property="docStatus" value="<%=EHRConfig.CLINICAL_DOCUMENT_STATUS_GENERATED %>">
				<td width="15%" class="tdfont">
					<div align="center">
					  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' tabindex="1" style="cursor: pointer" onclick="submitDocumentDetails('<%=patDocumentVO.getDocumentId()%>','<%=patDocumentVO.getDocumentTitle()%>','<%=patDocumentVO.getSerialNo()%>',<%=patDocumentVO.getDocumentType() %>,'FETCHDOCUMENTDETAILS')" onkeypress="if(event.keyCode==13) submitDocumentDetails('<%=patDocumentVO.getDocumentId()%>','<%=patDocumentVO.getDocumentTitle()%>','<%=patDocumentVO.getSerialNo()%>',<%=patDocumentVO.getDocumentType() %>,'FETCHDOCUMENTDETAILS');">
					</div>
				</td>
				</logic:notEqual>
			
				
			</tr>
					<!--<td width="20%" class="tdfont" nowrap="nowrap">
					</td>
					 <td width="10%" class="tdfont" nowrap="nowrap">
						<div id="<%=modifyDivId%>" align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<a style="cursor: pointer;" onclick="modifyDocument('<bean:write name="patDocumentVO" property="documentId" />','<bean:write name="patDocumentVO" property="serialNo" />','<%=idx.toString()%>')">
									<bean:message key="modify" />
								</a>
							</font>
						</div>
					</td> 
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div id="<%=modifyDivId%>" align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<a style="cursor: pointer;" onclick="removeDocument('<bean:write name="patDocumentVO" property="documentId" />','<bean:write name="patDocumentVO" property="serialNo" />','<%=idx.toString()%>')">
									<bean:message key="remove" />
								</a>
							</font>
						</div>
					</td>
				</tr>-->
			</logic:iterate>
		</table>
	</his:ContentTag>
</his:statusList>

<his:statusNew>
	<his:SubTitleTag key="ClinicalDocumentDtl">
	</his:SubTitleTag>

	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="1">
		<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="clinicalDocumentType" />
						</font>
					</div>
				</td>
				<td width="75%" class="tdfont" colspan="3">
					<div align="left">
						<input type="hidden" name="documentType"/>
						<html:select name="PatientClinicalDocumentsFB" property="documentType" onchange="submitdocumentType(event,this)" styleClass="regcbo">
						<html:option value="-1" >Select Value</html:option>
						<html:options collection="<%=EHRConfig.DOCUMENT_TYPE_LIST %>" property="value" labelProperty="label"  />
						</html:select>
					</div>
				</td>
			</tr>
			
						
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#FF0000" size="1" face="Verdana, Arial, Helvetica, sans-serif">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="documentTitle" />
						</font>
					</div>
				</td>
				<td width="50%" class="tdfont" >
					<div align="left">
						<html:text name="PatientClinicalDocumentsFB" property="documentTitle" tabindex="1" size="50" maxlength="50" onkeypress="return validateAlphaNumOnly(this,event);"></html:text>
					</div>
				</td>
				<td width="25%" class="tdfont"  colspan="2">
					<div align="right" >
						<img class="button" id="next"  src='<his:path src="/hisglobal/images/btn-next.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateForm(),'CLINICALDOCUMENTS');" onclick="submitFormOnValidate(validateForm(),'CLINICALDOCUMENTS');" tabindex="1" />
											
						<img class="button" id="generate" style="display: none" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) generateAutomaticDocument(event);" onclick="generateAutomaticDocument(event);" tabindex="1" />
					</div>		
				</td>
			</tr>
			
		</table>
	</his:ContentTag>
</his:statusNew>

<his:ButtonToolBarTag>	
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onclick="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1" style="cursor: pointer" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
	<his:statusList>
		<img class="button" src='<his:path src="/hisglobal/images/btn-del.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateRemoveDocument(),'REMOVECLINICALDOCUMENT');" onclick="submitFormOnValidate(validateRemoveDocument(),'REMOVECLINICALDOCUMENT');" tabindex="1" />
	</his:statusList>
	<% String gurl="/HISClinical/emr/patientClinicalDocuments.cnt?hmode=VIEWGENRATEDDOCUMENT";%>
	<img id="imgStepGenerateId" class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-generate.png"/>' tabindex="1" style="cursor: pointer;" onkeypress="if(event.keyCode==13) generateDocument(event,'<%=gurl%>');" onclick="generateDocument(event,'<%=gurl%>');" tabindex="1" />
	
</his:ButtonToolBarTag>



<div id="savemsg" class="alert alert-success alert-dismissable success"  style="display:none; font-size: 16px; width: 50%;">
	<a href="#" class="close" data-dismiss="alert" aria-label="close">×</a>
	<strong style="font-size: 16px;">Success!</strong> Data saved successfully
</div>

<html:hidden name="PatientClinicalDocumentsFB" property="hmode" />

<html:hidden name="PatientClinicalDocumentsFB" property="patCrNo" />
<html:hidden name="PatientClinicalDocumentsFB" property="episodeCode" />
<html:hidden name="PatientClinicalDocumentsFB" property="episodeVisitNo" />
<html:hidden name="PatientClinicalDocumentsFB" property="departmentUnitCode" />
<html:hidden name="PatientClinicalDocumentsFB" property="wardCode" />
<html:hidden name="PatientClinicalDocumentsFB" property="admNo" />
<html:hidden name="PatientClinicalDocumentsFB" property="deskType" />
<html:hidden name="PatientClinicalDocumentsFB" property="deskId" />
<html:hidden name="PatientClinicalDocumentsFB"	property="documentGenerationMode" /> 
<html:hidden name="PatientClinicalDocumentsFB" property ="admissionNo"/>
<html:hidden name="PatientClinicalDocumentsFB" property ="hospitalCode"/>
<html:hidden name="PatientClinicalDocumentsFB" property="documentHTML"/> 

<html:hidden name="PatientClinicalDocumentsFB" property="documentId" /> 
<html:hidden name="PatientClinicalDocumentsFB" property="patCategoryCode" />
<html:hidden name="PatientClinicalDocumentsFB"	property="selecteddocumentId" />
<%-- <html:hidden name="PatientClinicalDocumentsFB" property="selectedSerialNo" />
<html:hidden name="PatientClinicalDocumentsFB"	property="dischargeModifyFlag" /> --%>
<html:hidden name="PatientClinicalDocumentsFB"	property="documentType" />
<html:hidden name="PatientClinicalDocumentsFB" property="mode" />
<html:hidden name="PatientClinicalDocumentsFB"	property="saveFlag" />
<html:hidden name="PatientClinicalDocumentsFB" property="clinicalDocumentMode" />
 <html:hidden name="PatientClinicalDocumentsFB" property="serialNo" /> 
<%-- 
 <html:hidden name="PatientClinicalDocumentsFB" property="entryDate" />
<html:hidden name="PatientClinicalDocumentsFB" property="patCategoryCode" />
<html:hidden name="PatientClinicalDocumentsFB"	property="selecteddocumentId" />
<html:hidden name="PatientClinicalDocumentsFB" property="selectedSerialNo" />
<html:hidden name="PatientClinicalDocumentsFB"	property="dischargeModifyFlag" />
<html:hidden name="PatientClinicalDocumentsFB"	property="documentType" />
<html:hidden name="PatientClinicalDocumentsFB"	property="documentGenerationMode" /> --%>


