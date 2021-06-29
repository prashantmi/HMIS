<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="java.util.List"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="inpatient.transaction.controller.fb.PendingTaskListFB"%>
<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>    
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head><head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
window.onload=function(){
	
	var value=document.getElementsByName("taskCode")[0].value
	//alert(document.getElementById(value))
	document.getElementById(value).style.backgroundColor="#F1ECE6"
};

function getConsentDetail(crNo)
{
	document.getElementsByName("patCrNo")[0].value=crNo
	var mode
	var taskCode=document.getElementsByName("taskCode")[0].value
	switch(parseInt(taskCode)){
		case 0:	mode="GETCONSENTDTLBYCRNO" ;
				break;
		case 1:	mode="GETTREATMENTDTLBYCRNO" ;
				break;
		case 2:	mode="GETSAMPLECOLLECTIONDTLBYCRNO" ;
				break;
		case 3:	mode="GETPENDINGVITALMONITORINGBYCRNO" ;
				break;
		case 4:	mode="" ;
				return false;
				break;
	}
	//alert(mode)
	//document.getElementsByName("hmode")[0].value=mode
	//document.forms[0].submit();
	submitForm21(mode);

}
function setTaskName(){
	var taskcode=document.getElementsByName("taskCode")[0];
	var taskname;
	for(var i=0;i<taskcode.length;i++){
		if(taskcode.options[i].selected){
			taskname=taskcode.options[i].text
			break;
		}
	}
	document.getElementsByName("taskName")[0].value=taskname
}

function getPendingTaskDetails(taskCode){
	var mode;
	if(taskCode==document.getElementsByName("taskCode")[0].value){
		return false;
	}
	else{
		//var value=parseInt(obj.value)
		switch(taskCode){
			case -1:	return false;
					break;
			case 0:	mode="NEW" ;
					break;
			case 1:	mode="GETPENDINGTREATMENT" ;
					break;
			case 2:	mode="GETPENDINGSAMPLECOLLECTION" ;
					break;
			case 3:	mode="GETPENDINGVITALMONITORING" ;
					break;
			case 4:	mode="" ;
					return false;
					break;		
			case 5:	mode="" ;
					return false;
					break;		
			case 6:	mode="" ;
					return false;
					break;		
		}
		//alert(mode)
		//setTaskName();
		document.getElementsByName("taskCode")[0].value=taskCode
		submitForm21(mode);
	}	
} 

</script>
<his:TitleTag name="Pending Task List">
</his:TitleTag>
<table  width="100%" border="0" cellspacing="1" cellpadding="1">
	<tr>
		<%int sizeArray[]=(int[])session.getAttribute(InpatientConfig.PENDING_TASK_LIST_SIZE);
		if(sizeArray!=null)
		{
		int count=sizeArray.length;
		String width=100/count+"%";
		String pendingTask[]=InpatientConfig.PENDING_TASKS;
		for(int i=0;i<pendingTask.length;i++){ %>
		<td id="<%=i%>" class="tdfonthead" width="<%=width%>" onclick="getPendingTaskDetails(<%=i%>)" style="cursor: pointer;border-top:
		 2px outset black; border-bottom: 2px inset black;border-left: 2px outset black;" title="<%=pendingTask[i]%>">
			<div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><%=pendingTask[i]+ "("+sizeArray[i]+")"%></b>
			</font>
			</div>
		</td>
		<%} }%>
	</tr>
</table>

		<%	String taskName="";
			String taskCode=((PendingTaskListFB)pageContext.findAttribute("pendingTaskListFB")).getTaskCode();
			String array[]=InpatientConfig.PENDING_TASKS;
			taskName=array[Integer.parseInt(taskCode)];
			System.out.println("taskName	:"+taskName);%>
			
		<%	PaginationFB fbPage= new PaginationFB();
			pageContext.setAttribute("fbPage",fbPage);
			fbPage.setCurrentPage(((PendingTaskListFB)request.getAttribute("pendingTaskListFB")).getCurrentPage());
			fbPage.setObjArrName(InpatientConfig.INPATIENT_DTL_VO_LIST);
			fbPage.setTitleRequired(false);
			//fbPage.setAppendInTitle(taskName + " Pending");
			fbPage.setAppendInTitle(taskName);
			fbPage.setMaxRecords(15);
		%>


<his:statusList>
<his:PaginationTag name="fbPage"></his:PaginationTag>

<logic:present name="<%=InpatientConfig.INPATIENT_DTL_VO_LIST %>">
	<his:ContentTag>
		<table  width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
			<td width="30%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="crNo"/></b>
					</font>
					</div>
		  		</td>
				<td width="50%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="patientName"/></b>
					</font>
					</div>
		  		</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="gender/age"/></b>
					</font>
					</div>
		  		</td>
  			</tr>
		  	<%	List lst=(List)session.getAttribute(InpatientConfig.INPATIENT_DTL_VO_LIST );
			int startIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX)).intValue();
			int endIndex = ((Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX)).intValue();
			for(int i=startIndex;i<=endIndex;i++)
			{
				PatientDetailVO patientDtlVO=(PatientDetailVO)lst.get(i);
			%>
		  	<tr>
			<td width="30%"  class="tdfont">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					 <a onclick="getConsentDetail('<%=patientDtlVO.getPatCrNo()%>')" style="cursor: pointer;" title="Get Detail"> 
					 <%=patientDtlVO.getPatCrNo() %></a>
					 </b>
					</font>
					</div>
		  		</td>
				<td width="50%"  class="tdfont">
					<div align="center">	           
<!--					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
					<b><%=patientDtlVO.getPatName()%></b>
<!--					</font>-->
					</div>
		  		</td>
				<td width="20%"  class="tdfont">
					<div align="center">	           
<!--					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">-->
					<b><%=patientDtlVO.getPatGender() %>/
						<%=patientDtlVO.getPatAge() %></b>
<!--					</font>-->
					</div>
		  		</td>
		  		</tr>
		  	<%} %>
	</table>
	</his:ContentTag>
	</logic:present>
</his:statusList>

<his:statusTransactionInProcess>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include> 
<his:SubTitleTag name="Consent Detail">
</his:SubTitleTag>
	<logic:present name="<%=InpatientConfig.SELECTED_PATIENT_CONSENT_VO_LIST %>">
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="serviceType"/></b>
					</font>
					</div>
		  		</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="service"/></b>
					</font>
					</div>
		  		</td>
	  		
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="consentName"/></b>
					</font>
					</div>
		  		</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="raisedBy"/></b>
					</font>
					</div>
		  		</td>
				<td width="20%"  class="tdfonthead">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b><bean:message key="date&time"/></b>
						</font>
					</div>
		  		</td>
		</tr>
	
		<logic:iterate id="consentVO" indexId="idx" name="<%=InpatientConfig.SELECTED_PATIENT_CONSENT_VO_LIST %>" type="hisglobal.vo.ConsentRequestVO" >
	 		<tr>
				<td width="20%" class="tdfont">
			         <div align="center">	
			         	<bean:write name="consentVO" property="serviceTypeDesc"/>
			         </div>
				</td>
				 <td width="20%" class="tdfont">
			         <div align="center">	
				         <bean:write name="consentVO" property="serviceDesc"/>
			         </div>
				 </td>
				<td width="20%" class="tdfont">
					<div align="center">	           
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:define id="patCrNo" name="pendingTaskListFB" property="patCrNo"></bean:define>
						<% String context=request.getContextPath();
							String Path=context+"/opd/consentRequest.cnt?hmode=POPUP&patCrNo="+patCrNo+"&templateId="+consentVO.getConsentId();%>
						<a style="cursor:pointer" onclick="openPopup(createFHashAjaxQuery('<%=Path%>'),event,300,600)" >
						<%=consentVO.getTemplateDesc()%>
						</a>
					</font>
					</div>
		  		</td>
				<td width="20%" class="tdfont">
			         <div align="center">	
				         <bean:write name="consentVO" property="raisedBy"/>
				         <html:hidden name="consentVO" property="requestID"/>
			         </div>
				 </td>
				<td width="20%" class="tdfont">
			         <div align="center">	
			         	<bean:write name="consentVO" property="raisedDate"/>
			         </div>
				 </td>
			</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</logic:present>	
</his:statusTransactionInProcess>

<his:statusDone>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include> 
<his:SubTitleTag name="Pending Treatment Detail">
</his:SubTitleTag>
	<logic:present name="<%=InpatientConfig.SELECTED_PATIENT_TREATMENT_VO_LIST%>">
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="drugname"/></b>
				</font>
				</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="dosage"/></b>
				</font>
				</div>
	  		</td>
  		
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="doseTime"/></b>
				</font>
				</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="shift"/></b>
					</font>
					</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="remarks"/></b>
				</font>
				</div>
	  		</td>
		</tr>
	
	<logic:iterate id="drugDoseVO" indexId="idx" name="<%=InpatientConfig.SELECTED_PATIENT_TREATMENT_VO_LIST %>" type="hisglobal.vo.DrugAdminDtlVO" >
	 <tr>
		<td width="20%" class="tdfont">
	         <div align="center">	
		         <bean:write name="drugDoseVO" property="drugName"/>
		      </div>
		</td>
 	    <td width="20%" class="tdfont">
		    <div align="center">	
		        <bean:write name="drugDoseVO" property="doseName"/>
		     </div>
		</td>
		<td width="20%" class="tdfont">
			<div align="center">	           
				<bean:write name="drugDoseVO" property="doseTime"/>
			</div>
		</td>
		<td width="20%" class="tdfont">
		    <div align="center">
		       	<logic:equal name="drugDoseVO" property="doseShift" value="<%=InpatientConfig.MORNING_SHIFT_ID %>">
			         		<%="Morning" %>
	         	</logic:equal>	
	         	<logic:equal name="drugDoseVO" property="doseShift" value="<%=InpatientConfig.NOON_SHIFT_ID %>">
	         		<%="Noon" %>
	         	</logic:equal>	
	         	<logic:equal name="drugDoseVO" property="doseShift" value="<%=InpatientConfig.EVENING_SHIFT_ID %>">
	         		<%="Evening" %>
	         	</logic:equal>	
	         	<logic:equal name="drugDoseVO" property="doseShift" value="<%=InpatientConfig.NIGHT_SHIFT_ID %>">
	         		<%="Night" %>
	         	</logic:equal>	
	         </div>
		 </td>
		 <td width="20%" class="tdfont">
	         <div align="center">	
	         	<bean:write name="drugDoseVO" property="remarks"/>
	         </div>
		 </td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</logic:present>	
</his:statusDone>

<his:statusRecordFound>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include> 
<his:SubTitleTag name="Pending Sample Collection Detail">
</his:SubTitleTag>
	<logic:present name="<%=InpatientConfig.SELECTED_PATIENT_PENDING_SAMPLE_COLLECTION%>">
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="15%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="testName"/></b>
				</font>
				</div>
	  		</td>
			<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="requisitionNo"/></b>
				</font>
				</div>
	  		</td>
  		
			<td width="10%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="proposedTestDate"/></b>
				</font>
				</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="labName"/></b>
					</font>
					</div>
	  		</td>
			<td width="15%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="remarks"/></b>
					</font>
					</div>
	  		</td>
			<td width="15%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="rejectionAction"/></b>
					</font>
					</div>
	  		</td>
			<td width="15%"  class="tdfonthead">
					<div align="center">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b><bean:message key="rejectionReason"/></b>
					</font>
					</div>
	  		</td>
		</tr>
	
	<logic:iterate id="pendingSampleColVO" indexId="idx" name="<%=InpatientConfig.SELECTED_PATIENT_PENDING_SAMPLE_COLLECTION %>" type="inpatient.transaction.vo.PendingSampleCollectionVO" >
	 <tr>
		<td width="15%" class="tdfont">
	         <div align="center">	
		         <bean:write name="pendingSampleColVO" property="testName"/>
		      </div>
		</td>
 	    <td width="10%" class="tdfont">
		    <div align="center">	
		        <bean:write name="pendingSampleColVO" property="requisitionNo"/>
		     </div>
		</td>
		<td width="10%" class="tdfont">
			<div align="center">	           
				<bean:write name="pendingSampleColVO" property="proposedTestDate"/>
			</div>
		</td>
		<td width="20%" class="tdfont">
	         <div align="center">	
	         	<bean:write name="pendingSampleColVO" property="labName"/>
	         </div>
		 </td>
		<td width="15%" class="tdfont">
	         <div align="center">	
	         	<bean:write name="pendingSampleColVO" property="remarks"/>
	         </div>
		 </td>
		<td width="15%" class="tdfont">
	         <div align="center">	
	         	<bean:write name="pendingSampleColVO" property="strRejectionAction"/>
	         </div>
		 </td>
		<td width="15%" class="tdfont">
	         <div align="center">	
	         	<bean:write name="pendingSampleColVO" property="strRejectionReason"/>
	         </div>
		 </td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</logic:present>	
</his:statusRecordFound>

<his:statusNew>
<jsp:include page="/inpatient/inpatientDetail.cnt" flush="true"></jsp:include> 
<his:SubTitleTag name="Pending Monitoring Detail">
</his:SubTitleTag>
	<logic:present name="<%=InpatientConfig.SELECTED_PATIENT_PENDING_MONITORING%>">
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="paraName"/></b>
				</font>
				</div>
	  		</td>
			<td width="20%"  class="tdfonthead">
				<div align="center">	           
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="lastRecordedDate"/></b>
				</font>
				</div>
	  		</td>
  		</tr>
	
	<logic:iterate id="pendingMointoringVO" indexId="idx" name="<%=InpatientConfig.SELECTED_PATIENT_PENDING_MONITORING %>" type="hisglobal.vo.PatientMonitoringMstVO" >
	 <tr>
		<td width="20%" class="tdfont">
	         <div align="center">	
		         <bean:write name="pendingMointoringVO" property="paraName"/>
		      </div>
		</td>
		
 	    <td width="20%" class="tdfont">
		    <div align="center">	
		        <bean:write name="pendingMointoringVO" property="duration"/>
		     </div>
		</td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</logic:present>	
</his:statusNew>

<his:ButtonToolBarTag>
	<his:statusList>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitToDesk('NEW','NEW')" onkeypress="if(event.keyCode==13) submitToDesk('NEW','NEW')">
	</his:statusList>
	<his:statusTransactionInProcess>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusTransactionInProcess>
	<his:statusDone>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusDone>
	<his:statusRecordFound>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusRecordFound>
	<his:statusNew>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style="cursor:pointer" tabindex="1" onclick ="submitForm21('NEW')" onkeypress="if(event.keyCode==13) submitForm21('NEW');">
	</his:statusNew>
</his:ButtonToolBarTag>

<html:hidden property="hmode" name="pendingTaskListFB" />
<html:hidden property="patCrNo" name="pendingTaskListFB" />
<html:hidden name="pendingTaskListFB" property="taskName"/>
<html:hidden name="pendingTaskListFB" property="taskCode"/>
<html:hidden name="pendingTaskListFB" property="currentPage"/>
