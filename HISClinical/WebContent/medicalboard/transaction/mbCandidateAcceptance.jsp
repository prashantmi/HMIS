<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.utility.Entry"%>
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
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script><!--
function getCandidateList(obj){
	submitForm('GETCANDIDATELIST');

}
function getPrevCandidateList(obj){
	submitForm('GETPREVCANDIDATELIST');

}
function selectAllChecklist(obj){
	var selectedChecklist=document.getElementsByName("selectedCheckList")
	if(obj.checked){
		for(var i=0;i<selectedChecklist.length;i++){
			selectedChecklist[i].checked=true;
			enableChecklist(selectedChecklist[i])
		}
	}
	else{
		for(var i=0;i<selectedChecklist.length;i++){
			selectedChecklist[i].checked=false;
			enableChecklist(selectedChecklist[i])
		}
	}

}
function getPatientDetail(obj){
	document.getElementsByName("patCrNo")[0].value=obj.value.split("#")[0]
	document.getElementsByName("certificateTypeID")[0].value=obj.value.split("#")[1]
	document.getElementsByName("reqID")[0].value=obj.value.split("#")[2]
	document.getElementsByName("departmentUnitCode")[0].value=obj.value.split("#")[3]
	document.getElementsByName("visitNo")[0].value=obj.value.split("#")[4]
	submitForm('GETCHECKLIST');
}

function enableChecklist(obj){
	index=obj.value.split("#")[0]
	if(obj.checked){
		document.getElementsByName("remarks")[index].disabled=false;
		//document.getElementsByName("remarks")[obj].value=""
	}
	else{
		document.getElementsByName("remarks")[index].disabled=true;
		document.getElementsByName("remarks")[index].value=""
	}
}
function setSceduleDate(obj){
	if(obj.checked){
		document.getElementById("scheduleDateDiv").style.display="block";
	}
	else{
		document.getElementById("scheduleDateDiv").style.display="none";
		document.getElementsByName("examDate")[0].value="";
	}
}
function validateSave(){
	if(validateCheckList() && validateBoardNo()){
		submitForm('SAVE');
	}
	else{
		return false
	}
}

function validateCheckList(){
	
	var valid=true;
	if(document.getElementsByName("selectedCheckList")){
		var checklist=document.getElementsByName("selectedCheckList")
		for(var i=0;i<checklist.length;i++){
		
			/*
			if(checklist[i].checked){
				if(isEmpty(document.getElementsByName("remarks")[i],"Remarks")){
					valid= true
				}
				else{
					return false
				}
			}
			*/
			if(checklist[i].value.split("#")[1]=="<%=MedicalBoardConfig.COMPULSORY_AT_TIME_OF_ACCEPTANCE%>"){
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

function validateBoardNo(){
	if(isSelected(document.getElementsByName('boardNo')[0],"Board")){
		return true;
	}
	else{
		return false;
	}

}


function showOrHidePreviousDate()
{
	if(document.forms[0].chkPreviousDate.checked)
	{
	document.getElementById("strPreviousDate").style.display='block';
	document.getElementById("strPreviousDateBtn").style.display='block';
	}
	else
	{
	   document.getElementById("strPreviousDate").style.display='none';
	   document.getElementById("strPreviousDateBtn").style.display='none';
	   	submitForm('NEW');
	   	
	}

}
</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/mbCandidateAcceptance">	   
		   
   <his:TitleTag name="Candidate Acceptance"> 		
   </his:TitleTag>
<his:statusNew>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="25%" colspan="1" class="tdfonthead">
		
			
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="certificateType"/></b>
			 </font>
	
		  </td>
		 <td width="25%" colspan="1" class="tdfont">
			<html:select name="mbCandidateAcceptanceFB" property="certificateTypeID" tabindex="1" styleClass="regcbo" >
				<html:option value="%">All</html:option>
				<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>">
				<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>" labelProperty="label" property="value"/>
				</logic:present>
			</html:select>
		  </td>
		
		 <td width="25%" class="tdfont">
		 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Date</font>
		 </td>
		 <td width="25%"  class="tdfonthead">	
		 	<bean:define id="strPreviousDate" name="mbCandidateAcceptanceFB"
			property="strPreviousDate"></bean:define>
		<%
		String sysdate = (String) strPreviousDate;
		%>
		 
		  	 	<his:date name="strPreviousDate"
										dateFormate="%d-%b-%Y" value="<%=sysdate %>"></his:date>
		</td>
		<td width="25%"  class="tdfonthead">
				<img class="button" src='<his:path src="/hisglobal/images/GO.png"/>' tabindex="1"  style=cursor:pointer onclick ="getCandidateList()" onkeypress="if(event.keyCode==13) getCandidateList();">
		</td>
		 </tr>
		 
	</table>	  

</his:statusNew>   
    
<his:statusList>
	<logic:present name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>">
   <his:SubTitleTag name="Candidate List">
   </his:SubTitleTag>
   	<his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
<!--			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllCandidate(this)">-->
				<b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="13%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="candidate"/> <bean:message key="name"/></b>
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
			 <b><bean:message key="certificateType"/></b>
			 </font>
			</div>
		  </td>
		 <td width="21%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="requisitionDate"/></b>
			 </font>
			</div>
		  </td>
		 <td width="21%" class="tdfonthead">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <b><bean:message key="visitNo"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		<logic:iterate id="requisitionVO" name="<%=MedicalBoardConfig.REQUISITION_DETAIL_VO_LIST %>" type="hisglobal.vo.MedicalBoardRequisitionVO">
		<tr>
		 <td width="5%" class="tdfont">
		  <div align="center">
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			 <html:radio name="mbCandidateAcceptanceFB" property="selectedCandidate" value='<%=requisitionVO.getPatCrNo()+"#"+
			 requisitionVO.getCertificateTypeID()+"#"+ requisitionVO.getReqID()+"#"+requisitionVO.getDepartmentUnitCode()+"#"+requisitionVO.getVisitNo()%>' onclick="getPatientDetail(this)" tabindex="1"></html:radio>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center" >
			 <bean:write name="requisitionVO" property="patName"/>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="patAgeGender"/>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
			 <bean:write name="requisitionVO" property="certificateTypeName"/>
			</div>
		  </td>
		 <td width="21%" class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="entryDate"/>
			</div>
		  </td>
		 <td width="21%" class="tdfont">
		   <div align="center">
				<bean:write name="requisitionVO" property="visitNo"/>
			</div>
		  </td>
		  </tr>
		</logic:iterate>
	 </table>
	</his:ContentTag>
</logic:present>		
</his:statusList>	
	
<his:statusTransactionInProcess>
<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	
	<his:SubTitleTag name="CheckList Detail">
	</his:SubTitleTag>
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <input type="checkbox" name="selectAll" tabindex="1" onclick="selectAllChecklist(this)">
			 </font>
			</div>
		  </td>
		 <td width="45%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="checklist"/></b>
			 </font>
			</div>
		  </td>
		 <td width="50%" class="tdfonthead">
		  <div align="center" >
		  	<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="remarks"/></b>
			 </font>
			</div>
		  </td>
		  </tr>
		  <!------------------------Detail of checklist already Added---------------------- -->
		  <logic:present name="<%=MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST %>">
		   <logic:iterate id="checklistVO" name="<%=MedicalBoardConfig.MB_REQUISITION_CHECKLIST_VO_LIST%>" type="hisglobal.vo.MedicalBoardChecklistVO">
		  	 <tr>
		  	 <td width="5%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <input type="checkbox" name="checkList" disabled="disabled" checked="checked">
				 </font>
				</div>
			  </td>
		  	 <td width="45%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <bean:write name="checklistVO" property="checklist"/>
				 </font>
				</div>
			  </td>
		  	 <td width="50%" class="tdfont">
			  	<div align="center" >
			  		<bean:write name="checklistVO" property="remarks"/>
			  	</div>
			  </td>
		  	</tr>
		  	</logic:iterate>
		  </logic:present>	
		  
		  <!---------------------------------Checklist Detail---------------------------------  -->
		  
		   <logic:present name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST %>">
		  <logic:iterate id="checklistVO" name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_LIST%>" type="hisglobal.vo.MedicalBoardChecklistVO" indexId="index">
		  	 <tr>
		  	 <td width="5%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <input type="checkbox" name="selectedCheckList" tabindex="1" value='<%=index +"#"+checklistVO.getIsCompulsory()%>' onclick="enableChecklist(this)" >
				  	
				 </font>
				</div>
			  </td>
		  	 <td width="45%" class="tdfont">
			  <div align="center" >
			  	<%String fontColor="#000000"; %>
					<logic:equal name="checklistVO" property="isCompulsory" value="<%=MedicalBoardConfig.COMPULSORY_AT_TIME_OF_ACCEPTANCE %>">
						<%fontColor="#990000"; %>
					</logic:equal>
					<font color="<%=fontColor %>" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:write name="checklistVO" property="checklist"/>
					</font>
				<input type="hidden" name="checkListName" value="<%=checklistVO.getChecklist() %>"/>	
			  </div>
			 </td>
		  	 <td width="50%" class="tdfont">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				    <html:text name="mbCandidateAcceptanceFB" property="remarks" size="40" maxlength="50" tabindex="1" disabled="true" onkeypress="return validateAlphaNumericOnly(event,this)"/>
				 </font>
				</div>
			  </td>
		  	</tr>
		  </logic:iterate>
		  </logic:present>
		 </table>
		</his:ContentTag>
		
		<!------------------ Detail of Board which are active on current day ----------------------------->
		
		
		<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST %>">
		<his:SubTitleTag name="Board Detail">
		</his:SubTitleTag>
		  <his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
			   <tr>
				 <td width="50%" class="tdfonthead">
				  <div align="right" >
					<b><font color="#FF0000">*</font></b>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 	<bean:message key="board"/>
					 </font>
					</div>
				 </td>
				 <td width="50%" class="tdfont">
					 <logic:equal name="mbCandidateAcceptanceFB" property="visitNo" value="1">
				 	<div align="left" >
				 	<%List list=(List)session.getAttribute(MedicalBoardConfig.MEDICAL_BOARD_LIST); %>
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					 	<%if(list.size()>1){ %>
					 	<html:select name="mbCandidateAcceptanceFB" property="boardNo" tabindex="1" styleClass="regcbo">
					 	<html:option value="-1">Select Value</html:option>
					 	<html:options collection="<%=MedicalBoardConfig.MEDICAL_BOARD_LIST%>" labelProperty="label" property="value"/>
					 	</html:select>
					 	<%}else{ %>
					 		<%=((Entry)list.get(0)).getLabel() %>
					 		<html:hidden name="mbCandidateAcceptanceFB" property="boardNo" value="<%=((Entry)list.get(0)).getValue() %>"/>
					 	<%} %>	
					 </font>
					</div>
					</logic:equal>
					<logic:greaterThan name="mbCandidateAcceptanceFB" property="visitNo" value="1">
					<div>
						<bean:write name="mbCandidateAcceptanceFB" property="boardName"/>
					</div>
					</logic:greaterThan>
				  </td>
				 </tr>
			   </table>
			</his:ContentTag>  
		
		</logic:present>
		
		<!------------------------ Refer detail of the candidate if any --------------------------------->
		
		<logic:present name="<%=MedicalBoardConfig.EPISODE_REF_VO_LIST %>">
		<his:SubTitleTag name="Refer Detail">
		</his:SubTitleTag>
		<his:ContentTag>
			<table width="100%" cellspacing="1" cellpadding="0">
			<tr>
			 <td width="30%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="referToDeptUnit"/></b>
				 </font>
				</div>
			  </td>
			 <td width="25%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="referDate"/></b>
				 </font>
				</div>
			  </td>
			 <td width="20%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="visit"/> <bean:message key="status"/></b>
				 </font>
				</div>
			  </td>
			 <td width="25%" class="tdfonthead">
			  <div align="center" >
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				  <b><bean:message key="visit"/> <bean:message key="date"/></b>
				 </font>
				</div>
			  </td>
			</tr>
			
			<logic:iterate id="episodeRefVO" name="<%=MedicalBoardConfig.EPISODE_REF_VO_LIST %>" type="hisglobal.vo.EpisodeRefDtlVO" indexId="index">
			<tr>
			 <td class="tdfont">
			 	<div align="center">
			  		<bean:write name="episodeRefVO" property="toDepartment"/>/ 
			  		<bean:write name="episodeRefVO" property="toDepartmentUnit"/> 
				</div>
			  </td>
			 <td class="tdfont">
			  <div align="center">
					<bean:write name="episodeRefVO" property="reffDateTime"/> 
				</div>
			  </td>
			 <td class="tdfont">
			  	<div align="center">
			  	 <%if(episodeRefVO.getEpisodeReferAcceptDate()==null){
			  	 	out.println("Visit pending");
			  	 }	 
			  	 else{
			  	 	out.println("Visit Done");
			  	 } %>	
				 </div>
			  </td>
			  <td class="tdfont">
			  <div align="center">
					<bean:write name="episodeRefVO" property="episodeReferAcceptDate"/> 	
				</div>
			  </td>
			  </tr>
			</logic:iterate>
		 </table>
	</his:ContentTag>
	</logic:present>
		<!---------------------------- refer detail end ----------------------------------------->
</his:statusTransactionInProcess>	

 	<his:ButtonToolBarTag>
         <div align="center">	
       		 <his:statusTransactionInProcess>
			 	<logic:equal name="mbCandidateAcceptanceFB" property="flag" value="true">
			 	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) return validateSave();" onclick ="return validateSave();" tabindex="1"/>
			    </logic:equal>
			    <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick ="submitForm('NEW');">
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' tabindex="1"  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();">
			 </his:statusTransactionInProcess>
	         <his:statusList>
	         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusList>
	        <%--
	        <his:statusNew>
	        	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) submitForm('CANCEL');" tabindex="1" onclick ="submitForm('CANCEL');">
	        </his:statusNew>
	         --%>
         </div>
     </his:ButtonToolBarTag>
     
<his:status/>
   <html:hidden name="mbCandidateAcceptanceFB" property="hmode"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="patCrNo"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="certificateTypeID"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="reqID"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="departmentUnitCode"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="flag"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="visitNo"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="boardName"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="chkPreviousDate"/>
   <html:hidden name="mbCandidateAcceptanceFB" property="strPreviousDate"/>
   </html:form>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
