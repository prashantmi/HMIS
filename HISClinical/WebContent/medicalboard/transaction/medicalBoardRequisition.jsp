<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="medicalboard.transactions.controller.fb.MedicalBoardRequisitionFB"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>

<%@page import="registration.RegistrationConfig"%><his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script><!--
window.onload=function(){
// alert("reqBy "+document.getElementsByName("reqBy")[0].value)
	if(document.getElementsByName("reqBy")[0]){
		if(document.getElementsByName("reqBy")[0].value=="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ANYONE%>"){
			if(document.getElementsByName("requestFrom")[0].checked){
				document.getElementsByName("requestFrom")[0].checked=true
			}
			else if(document.getElementsByName("requestFrom")[1].checked){
				document.getElementsByName("requestFrom")[1].checked=true
			}
			else{
				document.getElementsByName("requestFrom")[0].checked=true
			}	
		}
		else if(document.getElementsByName("reqBy")[0].value=="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL%>"){
			document.getElementsByName("requestFrom")[0].disabled=true
			document.getElementsByName("requestFrom")[0].checked=true
			document.getElementsByName("requestFrom")[1].disabled=true
		}	
		else if(document.getElementsByName("reqBy")[0].value=="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION%>"){
			document.getElementsByName("requestFrom")[0].disabled=true
			document.getElementsByName("requestFrom")[1].checked=true
			document.getElementsByName("requestFrom")[1].disabled=true
		}	
	}
	
	var checkedItem=document.getElementsByName("checkedItem")[0].value.split("#")
	for(var i=0;i<document.getElementsByName("selectedCheckListId").length;i++){
		for(var j=0;j<checkedItem.length;j++){
				if(document.getElementsByName("selectedCheckListId")[i].value.split("#")[0]==checkedItem[j]){
					document.getElementsByName("selectedCheckListId")[i].checked=true
					showCheckedDocument(document.getElementsByName("selectedCheckListId")[i])
					break;
				}
		}
	}
	showOrgDetails();
}

function getPatientDetail(obj){
	
	document.getElementsByName("patCrNo")[0].value=obj.value
	document.getElementsByName("patCrNo")[1].value=obj.value
	document.getElementsByName("hmode")[0].value='GETCERTIFICATETYPE'
	document.forms[0].submit();
}

function getCheckListDetail(obj){
	if(obj.value!="-1"){
		document.getElementsByName("hmode")[0].value='GETCHECKLIST'
		document.forms[0].submit();
	}
}

function showOrgDetails(){

    if(document.getElementsByName('requestFrom')[1].checked==true){
      document.getElementById("showOrgDetails").style.display="block";
      showOrg()   }
    else {
      document.getElementById("showOrgDetails").style.display="none";
    }
}
 
function showOrg(){

 	if(document.getElementsByName('orgID')[0].value=="-1"){
     document.getElementsByName('otherOrgName')[0].value="";
	 document.getElementsByName('otherOrgName')[0].disabled=true;
	 document.getElementsByName('orgTypeID')[0].value="-1";
	 document.getElementsByName('orgAddress')[0].value="";
   }
   if(document.getElementsByName('orgID')[0].value=="<%=MedicalBoardConfig.ORGANISATION_ID_OTHER%>"){
 	 document.getElementsByName('otherOrgName')[0].value="";
	 document.getElementsByName('otherOrgName')[0].disabled=false;
	 document.getElementsByName('orgTypeID')[0].value="-1";
	 document.getElementsByName('orgAddress')[0].value="";
	  // alert(document.getElementsByName('orgName')[0].disabled)
    }
    else{
    	document.getElementsByName('otherOrgName')[0].disabled="true";
    }
   
}

function getOrgDetail(){

 	var checklist=document.getElementsByName("selectedCheckListId")
	var checkedItem=document.getElementsByName("checkedItem")[0].value
	for(var i=0;i<checklist.length;i++){
		if(checklist[i].checked){
			checkedItem=checkedItem + "#" + checklist[i].value.split("#")[0]
			}
	}
	checkedItem=checkedItem.substring(1);
   document.getElementsByName("checkedItem")[0].value=checkedItem
   
   submitForm('GETORGDTL');
}
 
function showCheckedDocument(chkDocument)
{
  if(chkDocument.checked)
     {
       document.getElementsByName('checkListIdArray')[chkDocument.value.split("#")[0]].disabled=false;
       document.getElementsByName('remarks')[chkDocument.value.split("#")[0]].disabled=false;
     }else{
       document.getElementsByName('checkListIdArray')[chkDocument.value.split("#")[0]].disabled=true;
       document.getElementsByName('remarks')[chkDocument.value.split("#")[0]].disabled=true;
       document.getElementsByName('remarks')[chkDocument.value.split("#")[0]].value="";
     }
}   
/*   
function validateSave()
{
	document.getElementsByName("hmode")[0].value='SAVE'
	document.forms[0].submit();
}   
*/
   
function getSchedule(){

	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	
	var departmentUnitCode=document.getElementsByName("departmentUnitCode")[0].value
	var certificateTypeId=document.getElementsByName("certificateTypeID")[0].value
	popup=window.open("/HISClinical/medicalboard/medicalBoardRequisition.cnt?hmode=GETSCHEDULELIST&unitCode=" + departmentUnitCode + "&certificateType=" + certificateTypeId + "&index=0" ,
	"Select Schedule","height=300,width=500 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}

}   

function getCIDNos(evnt)
{
	var winl = (screen.width - 900) / 2;
	var wint = (screen.height - 700) / 2;
	
	popup=window.open("/HISClinical/medicalboard/medicalBoardRequisition.cnt?hmode=GETCIDNOLIST" ,
	"Select CID No","height=700,width=900 ,top=" + wint + ",left=" + winl);
	if(!popup.opener)
	{
		popup.opener = self;
	}
}   

function validateSave()
{
	var valid=true;
	if(isSelected(document.getElementsByName("certificateTypeID")[0],"Certificate Type"))
	{
		valid=true;
	}
	else
	{
		return false;
	}
	
	if(!isEmpty(document.getElementsByName("examDate")[0],"Schedule Date"))
	{
		return false;
	}
	
	if(document.getElementsByName("reqBy")[0].value=="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION%>" || document.getElementsByName("requestFrom")[1].checked)
	{
		if(isSelected(document.getElementsByName("orgID")[0],"Organisation Name") && validateOrgName() &&
			isSelected(document.getElementsByName("orgTypeID")[0],"Organisation Type") &&
			isEmpty(document.getElementsByName("orgAddress")[0],"Address")  )
		{
			valid=true;
		}
		else
		{
			return false;
		}
	}
	if(validateBillNo() && validateCheckList() && valid==true)
	{  
	
		/*
		if(document.getElementsByName("CIDNo")[0].value=="")
		{
			if(!confirm("Are you sure you want to save without mapping CID No?"))
			{	
				alert("Hi");
				return false;
			}
		}
		*/
		submitForm('SAVE');
	}
	else
	{
		return false;
	}
}

function validateOrgName(){
	if(document.getElementsByName("orgID")[0].value=="<%=MedicalBoardConfig.ORGANISATION_ID_OTHER%>"){
		if(isEmpty(document.getElementsByName("otherOrgName")[0],"Other Organisation Name")){
			return true
		}
		else{
			return false
		}
	}
	else{
		return	true
	}		
}

function validateBillNo(){
	if(document.getElementsByName("billNo")){
		if(isSelected(document.getElementsByName("billNo")[0],"Bill No.")){
			return true;
		}
		else{
			return false;
		}
	}
	else{
		return true;
	}		
}

function validateCheckList(){
	
	var valid=true;
	if(document.getElementsByName("selectedCheckListId")){
		var checklist=document.getElementsByName("selectedCheckListId")
		for(var i=0;i<checklist.length;i++){
			/*if(checklist[i].checked){
				if(isEmpty(document.getElementsByName("remarks")[i],"Remarks")){
					valid= true
				}
				else{
					return false
				}
			}*/
			if(checklist[i].value.split("#")[1]=="<%=MedicalBoardConfig.IS_CHECKLIST_COMPULSORY_YES%>"){
				if(!checklist[i].checked){
					alert("Please select the compulsory checklist :"+ document.getElementsByName("checkListName")[i].value)
					return false
				}
				else{
					valid= true;
				}
			}
		}
	}
	return valid
}

function clearForm(){
	
	if(document.getElementsByName("orgID")[0])	document.getElementsByName("orgID")[0].value="-1"
	if(document.getElementsByName("orgTypeID")[0])	document.getElementsByName("orgTypeID")[0].value="-1"
	if(document.getElementsByName("otherOrgName")[0])	document.getElementsByName("otherOrgName")[0].value=""
	if(document.getElementsByName("orgAddress")[0])	document.getElementsByName("orgAddress")[0].value=""
	if(document.getElementsByName("examDate")[0])	document.getElementsByName("examDate")[0].value=""
		
	for(var i=0;i<document.getElementsByName("selectedCheckListId").length;i++){
		document.getElementsByName("selectedCheckListId")[i].checked=false
		document.getElementsByName("remarks")[i].value=""
		showCheckedDocument(document.getElementsByName("selectedCheckListId")[i])
	}
	
	document.getElementsByName("selectAll")[0].checked=false
}

function selectAllCheckList(obj){
	var selectedCheckListId=document.getElementsByName("selectedCheckListId")
	if(obj.checked){
		for(var i=0;i<selectedCheckListId.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=true
			document.getElementsByName("remarks")[i].value=""
			showCheckedDocument(document.getElementsByName("selectedCheckListId")[i])
		}
	}
	else{
		for(var i=0;i<selectedCheckListId.length;i++){
			document.getElementsByName("selectedCheckListId")[i].checked=false
			document.getElementsByName("remarks")[i].value=""
			showCheckedDocument(document.getElementsByName("selectedCheckListId")[i])
		}
	}	
}
--></script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalBoardRequisition">	   
		   
		   <his:TitleTag name="Medical Board Requisition"> 		
		   </his:TitleTag> 
<his:statusList>
   	<his:ContentTag>
   		<his:InputCrNoTag name="medicalBoardRequisitionFB">
		</his:InputCrNoTag>
	</his:ContentTag>
	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.ESSENTIAL_PATIENT_LIST_FOR_REQ %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="13%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="crNo"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="patient"/> <bean:message key="name"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="gender/age"/></b>
			 </font>
			</div>
		  </td>
		  
		 <td width="21%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="dept/unit"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		<logic:iterate id="patientVO" name="<%=MedicalBoardConfig.ESSENTIAL_PATIENT_LIST_FOR_REQ %>" type="hisglobal.vo.PatientVO">
		<tr>
		 <td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="medicalBoardRequisitionFB" property="selectedPatCrNo" value="<%=patientVO.getPatCrNo() %>" onclick="getPatientDetail(this)"></html:radio>
			 </font>
			</div>
		  </td>
		 <td width="13%" class="tdfont">
		  <div align="center">
			
			 <bean:write name="patientVO" property="patCrNo"/>
			
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center" >
			 <bean:write name="patientVO" property="patFirstName"/>
			 <bean:write name="patientVO" property="patMiddleName"/>
			 <bean:write name="patientVO" property="patLastName"/>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
			 <bean:write name="patientVO" property="patGender"/>/
			 <bean:write name="patientVO" property="patAge"/>
			</div>
		  </td>
		 <td width="21%" class="tdfont">
		   <div align="center">
			 <bean:write name="patientVO" property="departmentCode"/>/
			 <bean:write name="patientVO" property="departmentUnitCode"/>
			</div>
		  </td>
		  </tr>
		</logic:iterate>
	 </table>
	</logic:present>		
</his:ContentTag>
</his:statusList>	
<his:statusTransactionInProcess>
	<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	
	<his:ContentTag >
	<!--
	<table width="100%"> 
		<tr>
		  <td width="25%" align="left" class="tdfonthead">
		   <div align="right">
		  <font color="#000000" size="2"> 
		  Capture/View Image
		  </font>
		  </div>
		  </td>
		  <td width="5%" align="left"  class="tdfont"">
				 <div align="left">
						<img class="button" style="cursor:pointer" src='<his:path src="/hisglobal/images/camera2.gif"/>' 
							 alt="Capture Photo" title="Capture Photo" onkeypress="if(event.keyCode==13) openPopup('<his:path src="/medicalboard/webCamImageCapture.cnt"/>',event,550,600)" 
							 onclick="openPopup('<his:path src="/medicalboard/webCamImageCapture.cnt"/>',event,550,600)" tabindex="2">
					</div>	
		  </td>
		  <td class="tdfont" width="20%"><img class="button" style="cursor:pointer" src='<his:path 
			src="/hisglobal/images/view.psd.1.gif"/>' alt="View Photo" title="View Photo" onkeypress="if(event.keyCode==13) 
			openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" onclick="openPopup('<his:path src="/registration/enlargedImage.cnt"/>',event,300,600)" tabindex="2">
			</td>  
			<td width="50%" align="left"  class="tdfonthead">
		    </td>
		</tr>
		
	
	</table>
	-->
	<table width="100%">
	<logic:present name = '<%=RegistrationConfig.UPLOADED_FILE_AS_ARRAY%>'>
		<tr>
		<td width="25%" align="left" class="tdfonthead">
		   <div align="left">
		  <font color="#000000" size="2"> <b>
		 	Patient Image is already Captured. Please click View Photo to view the image.
		  </b></font>
		  </div>
		  </td>
		</tr>
		</logic:present>
	</table>
	</his:ContentTag>
	
	
	<his:SubTitleTagBroad name="Requisition Detail">
		 <his:statusRecordFound>
		 	<logic:equal name="medicalBoardRequisitionFB" property="isDataCorrect" value="true">
			<table width="100%" cellspacing="1" cellpadding="0">
				<tr>
					<td width="10%" align="right">
						<div align="right">
							<b><font color="#FF0000">*</font>
								<bean:message key="scheduleDate"/>
				 			</b>
						<input type="text" name="examDate" readonly="readonly" size="12" value="<%=((MedicalBoardRequisitionFB)pageContext.findAttribute("medicalBoardRequisitionFB")).getExamDate() %>">
				 		<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>'  style="cursor:pointer" border="1" 
		   					onkeypress="if(event.keyCode==13) getSchedule()" onclick="getSchedule()" title="Click to select Date">
				 	</div>
				 </td>
				</tr>
			</table>	
			</logic:equal>
		</his:statusRecordFound>
	</his:SubTitleTagBroad>
	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<his:statusRecordFound>
		<html:hidden name="medicalBoardRequisitionFB" property="CIDNo" />
			<!--
			<tr>
			 <td width="25%" class="tdfont" colspan="3">
				  <div align="left" >
							<b id="divMappedCIDNo"></b>
					</div>
			  </td>
			 <td width="25%" class="tdfonthead">
			  <div align="right" >
				<b><font color="#FF0000">
					<html:hidden name="medicalBoardRequisitionFB" property="CIDNo" />
					<a style="cursor:pointer" onclick="getCIDNos(event)" >Map CID No</a>&nbsp;&nbsp;&nbsp;
					<script>
						var cidNo=document.getElementsByName("CIDNo")[0].value;
						{
							if(cidNo!="")
							{
								document.getElementById("divMappedCIDNo").innerHTML="Mapped CID No <font color='red'><b>'" + cidNo +"'</b></font>";
							}
						}
					</script>
				</font></b>
				</div>
			  </td>
			 </tr>
			 -->
		 </his:statusRecordFound>
		<tr>
		
		 <td width="25%" class="tdfonthead">
		  <div align="right" >
			<b><font color="#FF0000">*</font></b>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="certificateType"/></b>
			 </font> 
			</div>
		  </td>
		 <td width="25%" class="tdfont">
		  <div align="left" >
			<html:select name="medicalBoardRequisitionFB" property="certificateTypeID" styleClass="regcbo" onchange="getCheckListDetail(this);" tabindex="1" >
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>">
					<html:options
						collection="<%= MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>"
						property="value" labelProperty="label"  />
					</logic:present>	
			</html:select>
			</div>
		  </td>
		   <his:statusRecordFound>
			   <td width="20%" class="tdfonthead">
		      	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="requestFrom"/>
				</font>
				</td>
		  
		    	<td  width="30%" class="tdfont">
		        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="individual"/>
				</font>
				  <html:radio name="medicalBoardRequisitionFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL %>" onclick="showOrgDetails()" tabindex="1" />
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					<bean:message key="organization"/>
				</font>
				  <html:radio name="medicalBoardRequisitionFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION%>" onclick="showOrgDetails()" tabindex="1"/>
				 </td>
			 </his:statusRecordFound>
		 </tr>
		</table> 
	</his:ContentTag>
</his:statusTransactionInProcess>	

<his:statusRecordFound>
	  <div id="showOrgDetails" style="display:none;">	
			<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<b><font color="#FF0000">*</font></b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="orgName"/>
					</font>
				</td>
				<td width="25%" class="tdfont">
			       <div id="divOrgId"  style="display:block">     
			            <html:select name="medicalBoardRequisitionFB" property="orgID" tabindex="1" onkeypress="if(event.keyCode==13) showOrg(); if(this.value!='-1') 
			            {getOrgDetail()};" onchange="showOrg(); if(this.value!='-1') {getOrgDetail()};" styleClass="regcbo" > 
							<html:option value="-1">Select Value</html:option>
							<html:option value="<%=MedicalBoardConfig.ORGANISATION_ID_OTHER %>">Other</html:option>
							<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>">
								<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>" labelProperty="label" property="value"/>
							</logic:present>
							<html:hidden name="medicalBoardRequisitionFB" property="orgName"/>
						</html:select> 
					</div>
				 </td>		
				 <td width="25%" class="tdfonthead">
				 	<b><font color="#FF0000">*</font></b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="otherOrgName"/>
						</font>
					
				 </td>
				 <td width="25%" class="tdfont">
				  	 <div id="divOrgName"  style="display:block" align="left">
			           <html:text name="medicalBoardRequisitionFB" property="otherOrgName" tabindex="1"  maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this)" disabled="true" >
					   </html:text> 
					  </div> 
				  </td>
			     
			         
	            </tr>   
	            <tr>
	             <td width="25%" class="tdfonthead">
	             		<b><font color="#FF0000">*</font></b>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="organisation"/> <bean:message key="type"/>
						</font>
				  </td>
				  <%boolean readonly=false;
				  	MedicalBoardRequisitionFB fb=(MedicalBoardRequisitionFB)pageContext.findAttribute("medicalBoardRequisitionFB"); 
				  	if(fb.getOrgID().equals("0")){
				  		readonly=false;
				  	}else{
				  		readonly=true;
				  	}
				  %>
			      <td width="25%" class="tdfont">
			          <div align="left">
			           		<html:select name="medicalBoardRequisitionFB" property="orgTypeID" styleClass="regcbo" tabindex="1" disabled="<%=readonly%>"> 
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES %>">
								<html:options collection="<%=MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES %>" labelProperty="label" property="value"/>
								</logic:present>
							</html:select>
							<%if(readonly){%>
								<html:hidden name="medicalBoardRequisitionFB" property="orgTypeID" value="<%=fb.getOrgTypeID() %>"/>
							<%} %> 
						</div>						
			      </td>
				    <td width="25%" class="tdfonthead">
						<div align="right">
							<b><font color="#FF0000">*</font></b>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="address"/>
							</font>
						</div>
				    </td>
			        <td width="25%" class="tdfont" >
			          <div align="left">
			            <html:textarea name="medicalBoardRequisitionFB" property="orgAddress" onkeypress="return CheckMaxLength(event,this,100,1)"  style="width:180px" tabindex="1" readonly="<%=readonly %>"> 
						 </html:textarea>
					  </div>
			        </td>
			         
			        </tr>
			      </table>
			  </his:ContentTag>
		 </div>	
		<logic:equal name="medicalBoardRequisitionFB" property="isBillingDone" value="1">
		<his:SubTitleTag name="Billing Detail">
		</his:SubTitleTag>
		<table width="100%">
			 <tr>
				<td width="50%" class="tdfonthead">
				 <div align="right">
				 <b><font color="#FF0000">*</font></b>
				 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="billNo"/></b>	
				 </font>
				 </div>
				</td>
				<td width="50%" class="tdfont">
				  <%List billNoList=(List)session.getAttribute(MedicalBoardConfig.BILL_NO_LIST); %>
				  <%if(billNoList!=null && billNoList.size()>1){ %>
				  <html:select name="medicalBoardRequisitionFB" property="billNo" tabindex="1" styleClass="regcbo">
					  <html:option value="-1">Select Value</html:option>
					  <html:options collection="<%=MedicalBoardConfig.BILL_NO_LIST%>" labelProperty="label" property="value"/>
				  </html:select>
				  <%}else{ %>
					  <%= ((Entry)billNoList.get(0)).getValue()%>	
				  <%}%>	
				</td>
			</tr>	
		</table>
		</logic:equal>
	
		 <logic:present name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY %>" >
		 	  <his:SubTitleTag name="CheckList Detail">
			   </his:SubTitleTag>
			   <his:ContentTag>
			   <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                <tr>
					<td width="5%" class="tdfonthead">
					 <div align="center">
					 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					  <input type="checkbox" name="selectAll" onchange="selectAllCheckList(this);">	
					 </font>
					 </div>
					</td>
					<td width="45%" class="tdfonthead">
					  <div align="center">
					   <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					    <b><bean:message key="checklist"/></b>	
					   </font>
					  </div>
					 </td>
					 <td width="50%" class="tdfonthead">
					  <div align="center">
<!--					  <b><font color="#FF0000">*</font></b>-->
					  <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					   <b><bean:message key="remarks"/></b>	
					  </font>
					 </div>
					</td>
                </tr>
               
                <logic:iterate id="checklistDtlVOs" indexId="idx" name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY %>" type="hisglobal.vo.MedicalBoardChecklistVO" >
                    <%String index=idx.toString(); %>
                <tr>
                	<td width="5%" class="tdfont">
						<div align="center">
						<html:checkbox name="medicalBoardRequisitionFB" property="selectedCheckListId" value='<%=index + "#"+ checklistDtlVOs.getIsCompulsory() %>' onclick="showCheckedDocument(this)" tabindex="1" ></html:checkbox>
						</div>
					</td>
                <td width="45%" class="tdfont">
						 <div align="center">
						 <%String fontColor="#000000"; %>
						 <logic:equal name="checklistDtlVOs" property="isCompulsory" value="<%=MedicalBoardConfig.COMPULSORY_AT_TIME_OF_REQ %>">
						 	<%fontColor="#990000"; %>
						  </logic:equal>
						  <font color="<%=fontColor %>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					       <bean:write name="checklistDtlVOs" property="checklist"/>
					       <html:hidden name="medicalBoardRequisitionFB" property="checkListIdArray" value="<%=checklistDtlVOs.getChecklistID() %>" />
					       <html:hidden name="medicalBoardRequisitionFB" property="checkListName" value="<%=checklistDtlVOs.getChecklist() %>" />
					      	</font>
					     </div>
				</td>
				<td width="50%" class="tdfont">
					<div align="center">
			          <%MedicalBoardRequisitionFB fb1=(MedicalBoardRequisitionFB)pageContext.findAttribute("medicalBoardRequisitionFB"); %>
			          &nbsp;<html:text name="medicalBoardRequisitionFB" property="remarks" disabled="true" maxlength="50" size="30" tabindex="1"
							value='<%=(fb1.getRemarks()==null?"":fb1.getRemarks()[idx]) %>' onkeypress="return validateAlphaNumericOnly(event,this)">
						</html:text>
						</div>
				</td>
                </tr>
                </logic:iterate>
               
                </table>
                </his:ContentTag>
              </logic:present>
</his:statusRecordFound>	

		
 	<his:ButtonToolBarTag>
         <div align="center">	
         		 <his:statusRecordFound>	
			 	<logic:equal name="medicalBoardRequisitionFB" property="isDataCorrect" value="true">
			 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) return validateSave();" onclick ="return validateSave();" tabindex="1"/>
			 	</logic:equal>
			 	</his:statusRecordFound>
       		 <his:statusTransactionInProcess>
			    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style="cursor:pointer" onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
			 </his:statusTransactionInProcess>
	         <his:statusList>
	         <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusList>
         </div>
     </his:ButtonToolBarTag>
     
<his:status/>
   <html:hidden name="medicalBoardRequisitionFB" property="hmode"/>
   <html:hidden name="medicalBoardRequisitionFB" property="patCrNo"/>
   <html:hidden name="medicalBoardRequisitionFB" property="departmentCode" />
   <html:hidden name="medicalBoardRequisitionFB" property="departmentUnitCode" />
   <html:hidden name="medicalBoardRequisitionFB" property="reqBy" />
   <html:hidden name="medicalBoardRequisitionFB" property="isBillingDone" />
   <%--<html:hidden name="medicalBoardRequisitionFB" property="examDate" />--%>
   <html:hidden name="medicalBoardRequisitionFB" property="isDataCorrect" />
   <html:hidden name="medicalBoardRequisitionFB" property="checkedItem" />
      
   </html:form>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
