<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="medicalboard.transactions.controller.fb.MedicalBoardRequisitionChangeFB"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/calendar-blue2.css" />
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
<his:css src="/hisglobal/css/tab.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script><!--
window.onload=function(){
	if(document.getElementsByName("requestBy")[0]){
		setFormForRequest(document.getElementsByName("requestBy")[0].value)
		id=document.getElementsByName("requestBy")[0].value
		//alert(id)
		document.getElementById(id).style.backgroundColor="#F1ECE2" 
						
	}
}

function enableForm(obj)
{
   var index=obj.value.split("#")[0]	
  if(obj.checked)
     {
       document.getElementsByName('changeReason')[index].disabled=false;
       document.getElementsByName('certificateTypeId')[0].value=obj.value.split("#")[1]	
       document.getElementsByName('departmentUnitCode')[0].value=obj.value.split("#")[2]	
       
     }else{
       document.getElementsByName('changeReason')[index].disabled=true;
       document.getElementsByName('changeReason')[index].value="";
       document.getElementsByName('examDate')[index].value="";
     }
}   
 
function setFormForRequest(requestBy)
{
	//alert("requestBy "+requestBy)
	switch(parseInt(requestBy)){
		case 1:
				document.getElementById("requestByPatDiv").style.display="block"
				document.getElementById("requestByOrgDiv").style.display="none"
				document.getElementById("requestByHospitalDiv").style.display="none"
				document.getElementsByName("hmode")[0].value='NEW'
				break;
		case 2:
				document.getElementById("requestByPatDiv").style.display="none"
				document.getElementById("requestByOrgDiv").style.display="block"
				document.getElementById("requestByHospitalDiv").style.display="none"
				document.getElementsByName("hmode")[0].value='SETREQUESTBYORG'
				break;
		case 3:
				document.getElementById("requestByPatDiv").style.display="none"
				document.getElementById("requestByOrgDiv").style.display="none"
				document.getElementById("requestByHospitalDiv").style.display="block"
				document.getElementsByName("hmode")[0].value='SETREQUESTBYHOSPITAL'
				break;
				
	}
	
}   

function setRequestBy(requestBy){
	
	setFormForRequest(requestBy);
	document.getElementsByName("requestBy")[0].value=requestBy
	document.getElementsByName("patCrNo")[0].value=""
	//document.getElementsByName("hmode")[0].value='SETREQUESTBY'
	document.forms[0].submit();

}
   
function getScheduleDate(index,certificateTypeId,unitCode,oldExamDate){

	var winl = (screen.width - 500) / 2;
	var wint = (screen.height - 300) / 2;
	if(document.getElementsByName("selectAll")[0].checked && document.getElementsByName("requestBy")[0].value!=1){
		index="-1";
	}
	
	popup=window.open("/HISClinical/medicalboard/medicalBoardRequisition.cnt?hmode=GETSCHEDULELIST&unitCode=" + unitCode + "&certificateType=" + certificateTypeId  
	+"&index=" + index  , "Select Schedule","height=300,width=500 ,top=" + wint + ",left=" + winl);
	if(!popup.opener){
     popup.opener = self;
 	}
 	
 	/*
 	if(oldExamDate==document.getElementsByName("examDate")[index].value){
 		alert("New Schedule Date can not be same as old Schedule Date")
 	}*/

}   

function validateSave(){
	var requisitions=document.getElementsByName("selectedRequisition")
	var valid=true;
	if(getSelectedRequisitionCount()==0){
		alert("Please Select at least one Requisition to reschedule");
		return false;
	}
	else{
		for(var i=0;i<requisitions.length;i++){
			if(requisitions[i].checked){
				if(isEmpty(document.getElementsByName("examDate")[i],"New Schedule Date") &&
					isEmpty(document.getElementsByName("changeReason")[i],"Change Reason")&&
					validateExamDate(i)){
					valid= true
				}
				else{
					return false
				}
			}
		}
		
		if(valid){
			submitForm('SAVE');
		}
		else{
			return false;
		}
	}	

}

function getSelectedRequisitionCount(){
	var count=0;
	var requisitions=document.getElementsByName("selectedRequisition")
	for(var i=0;i<requisitions.length;i++){
		if(requisitions[i].checked){
			count++
		}
	}			
	return count;
}

function validateExamDate(index){
	var oldExamDate=document.getElementsByName("oldExamDate")[index]
	var newExamDate=document.getElementsByName("examDate")[index]
	
	if(oldExamDate.value == newExamDate.value){
		alert("New Schedule Date cannot be same as Old Schedule Date ");
		newExamDate.focus();
		return false
	}			
	return true;
}

function clearForm(){
	
	for(var i=0;i<document.getElementsByName("selectedRequisition").length;i++){
		document.getElementsByName("selectedRequisition")[i].checked=false
		document.getElementsByName("examDate")[i].value=""
		document.getElementsByName("changeReason")[i].value=""
		enableForm(document.getElementsByName("selectedRequisition")[i])
	}
	document.getElementsByName("selectAll")[0].checked=false;
}

function getRequisitionsByOrg(){
	
	var valid=false;
	if(isSelected(document.getElementsByName("orgID")[0],"Organisation Name")){
		if(document.getElementsByName("orgID")[0].value=="0"){
			if(isEmpty(document.getElementsByName("otherOrgName")[0],"Other Organisaion Name")){
				valid=true
			}
			else{
				return false
			}
		}
		else{
			valid=true
		}
	}
	else{
		return false;
	}
	
	if(valid){
		submitForm('GETREQUISITIONDTLBYORG')
	}
}

function getRequisitionByDate(){
	if(!isEmpty(document.getElementsByName("scheduleDate")[0],"Schedule Date")){
		return false;
	}
	var scheduleDate=document.getElementsByName("scheduleDate")[0]
	var sysdate=document.getElementsByName("sysdate")[0]
	
	if(!compareDate(sysdate,scheduleDate,2)){
		alert("Schedule Date should be greater than or equal to current date")
		return false
	}
	
	submitForm('GETREQUISITIONBYDATE');
	
}
function showOtherOrgName(obj){
	if(obj.value=='<%=MedicalBoardConfig.ORGANISATION_ID_OTHER%>'){
		document.getElementsByName("otherOrgName")[0].disabled=false;
	}
	else{
		document.getElementsByName("otherOrgName")[0].value="";
		document.getElementsByName("otherOrgName")[0].disabled=true;
	}	
}

function checkAllSchedule(obj){
	if(obj.checked){
		for(var i=0;i<document.getElementsByName("selectedRequisition").length;i++){
			document.getElementsByName("selectedRequisition")[i].checked=true
			enableForm(document.getElementsByName("selectedRequisition")[i])
		}
	}
	else{
		for(var i=0;i<document.getElementsByName("selectedRequisition").length;i++){
			document.getElementsByName("selectedRequisition")[i].checked=false
			enableForm(document.getElementsByName("selectedRequisition")[i])
		}
	}	
}

function populateEachTextBox(obj){
	if(document.getElementsByName("selectAll")[0].checked && document.getElementsByName("requestBy")[0].value!=1){
		for(var i=0;i<document.getElementsByName("changeReason").length;i++){
			document.getElementsByName("changeReason")[i].value=obj.value
		}
	}
}
--></script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalBoardRequisitionChange">	   
		   
   <his:TitleTag name="Medical Board Requisition Reschedule"> 		
   </his:TitleTag> 
	<% String requestByArray[]= MedicalBoardConfig.REQUISITION_CHANGE_BY; %>
	
	<table width="100%" cellspacing="1" cellpadding="0">
			
		<tr>
		<%for(int i=0;i<requestByArray.length;i++){ %>
		   <td bordercolor="black" id="<%=(i+1) %>" width="5%" style="cursor: pointer;height: 20px ;border-left:1px solid #E1E0D2;
				border-top:1px solid #E1E0D2;border-right:1px solid #8D8961;border-bottom:1px solid #8D8961;"
		    class="tdfonthead"  onclick="setRequestBy('<%=i+1%>')" title="<%=requestByArray[i]%>">
		    <div align="center" >
			<b><%=requestByArray[i]%></b> 	
			</div>
		  </td>
		  <%} %>
		</tr>	   
	</table>
	<% 
	   String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	%>   
	<div id="requestByPatDiv" style="display: block">
		<his:statusNew>
			<his:ContentTag>
				<his:InputCrNoTag name="medicalBoardRequisitionChangeFB">
				</his:InputCrNoTag>
			</his:ContentTag>
		</his:statusNew>
	</div>	
	<div id="requestByOrgDiv" style="display: none">
		<his:statusNew>
		<%boolean disabled=false; %>
		<logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
			<%disabled=true; %>
		</logic:present>
		<logic:notPresent name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
			<%disabled=false; %>
		</logic:notPresent>
		<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		 <tr>
			 <td width="25%" class="tdfonthead">
			  <div align="right" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><font color="#FF0000">*</font></b>
				  <b><bean:message key="orgName"/></b>
				 </font>
				</div>
			  </td>
			 <td width="25%" class="tdfont">
			  <div align="left" >
				<html:select name="medicalBoardRequisitionChangeFB" property="orgID" tabindex="1" styleClass="regcbo" onchange="showOtherOrgName(this)" disabled="<%=disabled %>">
					<html:option value="-1">Select Value</html:option>
					<html:option value="<%=MedicalBoardConfig.ORGANISATION_ID_OTHER %>">Other</html:option>
					<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>">
					<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>"  property="value" labelProperty="label"/>
					</logic:present>
				</html:select>
				</div>
			  </td>
			  <td width="25%" class="tdfonthead">
			  <div align="right" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><font color="#FF0000">*</font></b>
				  <b><bean:message key="otherOrgName"/></b>
				 </font>
				</div>
			  </td>
			  <td width="25%" class="tdfont">
			  <div align="left" >
				<html:text name="medicalBoardRequisitionChangeFB" property="otherOrgName" maxlength="60" size="25" tabindex="1" disabled="true" onkeypress="return validateAlphaNumericOnly(event,this)"></html:text>
				</div>
			  </td>
		  </tr>
		  <logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
		  <tr>
			 <td width="25%" class="tdfonthead">
			  <div align="right" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><font color="#FF0000">*</font></b>
				  <b><bean:message key="scheduleDate"/></b>
				 </font>
				</div>
			  </td>
			 <td width="25%" class="tdfont">
			  <div align="left" >
				<html:select name="medicalBoardRequisitionChangeFB" property="scheduleDate" tabindex="1" styleClass="regcbo">
					<html:option value="-1">Select Value</html:option>
					<logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
					<html:options collection="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>"  property="value" labelProperty="label"/>
					</logic:present>
				</html:select>
				</div>
			  </td>
			  <td width="25%" class="tdfonthead"></td>
			  <td width="25%" class="tdfont"></td>
			  </tr>
			</logic:present>  
		</table> 
		</his:ContentTag> 
		</his:statusNew>
	</div>	
	
	<div id="requestByHospitalDiv" style="display: none">
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
			 	<tr>
					 <td width="50%" class="tdfonthead">
					  <div align="right" >
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						  <b><bean:message key="scheduleDate"/></b>&nbsp;
						 </font>
						</div>
					  </td>
					  <td width="15%" class="tdfont">
					  	<div align="left" >
							&nbsp;<his:date name="scheduleDate" ></his:date>
						</div>
					  </td>
					  <td width="35%" class="tdfont">
					  	<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' style="cursor:pointer" 
						onkeypress="if(event.keyCode==13) getRequisitionByDate();" tabindex="1" onclick ="getRequisitionByDate();">
					  </td>	
				 </tr>
			</table>
		</his:ContentTag>	  
	</div>	
		   
 <his:statusList>
 	<his:SubTitleTag name="Schedule List">
	</his:SubTitleTag>
 	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 	<input type="checkbox" name="selectAll" onclick="checkAllSchedule(this)" tabindex="1" />
			 </font>
			</div>
		  </td>
		 <td width="12%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="crNo"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="patient"/> <bean:message key="name"/> </b>
			 </font>
			</div>
		  </td>
		 <td width="22%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="certificatetype"/></b>
			 </font>
			</div>
		  </td>
		 <td width="15%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="oldExamDate"/></b>
			 </font>
			</div>
		  </td>
		  
		 <td width="18%" class="tdfonthead">
		  <div align="center">
		  	<b><font color="#FF0000">*</font></b>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="newExamDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="20%" class="tdfonthead">
		  <div align="center">
		  	<b><font color="#FF0000">*</font></b>
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="changeReason"/></b>
			 </font>
			</div>
		  </td>
		  <logic:equal name="medicalBoardRequisitionChangeFB" property="requestBy" value="<%=MedicalBoardConfig.REQUISITION_CHANGE_REQ_MODE_BY_ORGANIZATION %>">
		  	 <td width="20%" class="tdfonthead">
			  <div align="center">
			  	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				 <b><bean:message key="orgName"/></b>
				 </font>
				</div>
			  </td>
		  </logic:equal>
		  
		</tr>
		<logic:iterate id="requisitionVO" name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>" type="hisglobal.vo.MedicalBoardRequisitionVO" indexId="index">
		<tr>
		 <td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:checkbox name="medicalBoardRequisitionChangeFB" property="selectedRequisition" value='<%=index + "#" + requisitionVO.getReqID()+ "#"+requisitionVO.getSlNo()%>' onclick="enableForm(this)" tabindex="1"></html:checkbox>
			 </font>
			</div>
		  </td>
		 <td width="12%" class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="patCrNo"/>
			</div>
		  </td>
		 <td width="15%" class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="patName"/>
			</div>
		  </td>
		 <td width="22%" class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="certificateTypeName"/>
			</div>
		  </td>
		 <td width="15%" class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="examDate"/>
			 <html:hidden name="medicalBoardRequisitionChangeFB" property="oldExamDate" value='<%=requisitionVO.getExamDate() %>'/>
			</div>
		  </td>
		 <td width="18%" class="tdfont" nowrap="nowrap">
		   <div align="center">
		    <%MedicalBoardRequisitionChangeFB fb=(MedicalBoardRequisitionChangeFB)pageContext.findAttribute("medicalBoardRequisitionChangeFB");%>
		   	<html:text name="medicalBoardRequisitionChangeFB" property="examDate" value='<%=(fb.getExamDate()==null?"":fb.getExamDate()[index]) %>' size="15" tabindex="1" readonly="true" ></html:text>
		   	<img class="button" src='<his:path src="/hisglobal/images/imgDatepicker.png"/>'  style=cursor:pointer border="1" 
		   	onkeypress="if(event.keyCode==13) getScheduleDate('<%=index %>','<%=requisitionVO.getCertificateTypeID()%>','<%=requisitionVO.getDepartmentUnitCode()%>','<%=requisitionVO.getExamDate()%>');" tabindex="1" 
		   	onclick ="getScheduleDate('<%=index %>','<%=requisitionVO.getCertificateTypeID()%>','<%=requisitionVO.getDepartmentUnitCode()%>','<%=requisitionVO.getExamDate()%>');" title="Click to select Date">
			</div>
		  </td>
		 <td width="20%" class="tdfont">
		   <div align="center">
		   		<html:text name="medicalBoardRequisitionChangeFB" property="changeReason" size="20" maxlength="100" tabindex="1" value="" onkeypress="return validateAlphaNumericOnly(event,this)" disabled="true" onblur="populateEachTextBox(this)"></html:text>
			</div>
		  </td>
		  <logic:equal name="medicalBoardRequisitionChangeFB" property="requestBy" value="<%=MedicalBoardConfig.REQUISITION_CHANGE_REQ_MODE_BY_ORGANIZATION %>">	
			 <td width="20%" class="tdfont">
			   <div align="center">
			   		<bean:write name="requisitionVO" property="orgName"/>
				</div>
		  	</td>
		  </logic:equal>
		  </tr>
		</logic:iterate>
	 </table>
	</logic:present>		
</his:ContentTag>
</his:statusList>


 	<his:ButtonToolBarTag>
         <div align="center">	
       		<his:statusList>
			 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) return validateSave();" onclick ="return validateSave();" tabindex="1"/>
		        <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
		 		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	        </his:statusList>
	         <his:statusNew>
		        <logic:equal name="medicalBoardRequisitionChangeFB" property="requestBy" value="2">
		        	<logic:notPresent name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
		        	<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) getRequisitionsByOrg();" tabindex="1" onclick ="getRequisitionsByOrg();">
		        	</logic:notPresent>
		        	<logic:present name="<%=MedicalBoardConfig.REQUISITION_SCHEDULE_DATE_LIST %>">
		        		<img class="button" src='<his:path src="/hisglobal/images/GoNew.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) getRequisitionByDate();" tabindex="1" onclick ="getRequisitionByDate()">
		        	</logic:present>
		        </logic:equal>
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
         </div>
     </his:ButtonToolBarTag>
     
<his:status/>
   <html:hidden name="medicalBoardRequisitionChangeFB" property="hmode"/>
   <html:hidden name="medicalBoardRequisitionChangeFB" property="patCrNo"/>
   <html:hidden name="medicalBoardRequisitionChangeFB" property="departmentUnitCode" />
   <html:hidden name="medicalBoardRequisitionChangeFB" property="requestBy" />
   <input type="hidden" name="certificateTypeId">
  
   <input type="hidden" name="sysdate" value="<%=sysDate%>">

   
</html:form>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
