<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
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
<script>

function saveReferDetail(){
	
	selectedLabTest=document.getElementsByName("selectedLabTestCode")
	var selectedLabTestCodeString="";
	var selectedSampleCodeString="";
	var valid=false;
	for(var i=0;i<selectedLabTest.length;i++){
		if(selectedLabTest[i].checked){
			selectedLabTestCodeString=selectedLabTestCodeString+"@"+selectedLabTest[i].value.split("#")[1];
			selectedSampleCodeString=selectedSampleCodeString+"@"+document.getElementsByName("testSampleCode")[selectedLabTest[i].value.split("#")[0]].value;
			valid=true;
		}
	}
	selectedLabTestCodeString=selectedLabTestCodeString.substring(1);
	selectedSampleCodeString=selectedSampleCodeString.substring(1);
	if(valid){
		opener.document.getElementsByName("labTestCode")[0].value=selectedLabTestCodeString
		opener.document.getElementsByName("selectedSampleCode")[0].value=selectedSampleCodeString
		self.close();
		//opener.document.getElementsByName("hmode")[0].value='SAVEREFERDETAIL'
		//opener.document.forms[0].submit();
		opener.saveRaisedInvestigation();
	}
	else{
		alert("Please Select at least one investigation")
		return false;
	}	
}


</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalExamInitiation">	   
<his:TitleTag name="Select Investigation">
</his:TitleTag>	   
<his:statusList>
   	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="labTest"/></b>
			 </font>
			</div>
		  </td>
		 <td width="20%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="labName"/></b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="sample/specimen"/></b>
			 </font>
			</div>
		  </td>
		 <td width="25%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="status"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		
		<logic:iterate id="investigationMappingVO" name="<%=MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST %>" type="hisglobal.vo.MedicalBoardInvestigationMappingVO" indexId="index">
		<tr>
		 <td class="tdfont">
		 <%String checked=""; %>
		  <div align="center">
		  	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		  	 	<%if(investigationMappingVO.getIsOptional().equals(MedicalBoardConfig.MEDICAL_BOARD_INVESTIGATION_IS_OPTIONAL_NO)){
		  	 		checked="checked='checked'";
		  	 	}else{
		  	 		checked="";
		  	 	}%>
		  	 	<logic:notEqual name="investigationMappingVO" property="isInvRaised"  value="<%=MedicalBoardConfig.IS_INVESTIGATION_RAISED_YES %>">
		  	 		<input type="checkbox" name="selectedLabTestCode" value='<%=index+"#"+investigationMappingVO.getLabTestCode()%>' tabindex="1" <%=checked%> />
		  	 	</logic:notEqual>
			 </font>
			</div>
		  </td>
		 <td class="tdfont">
		   <div align="center" >
		   		<bean:write name="investigationMappingVO" property="labTestName"/> 	
			</div>
		  </td>
		 <td class="tdfont">
		  <div align="center">
				<bean:write name="investigationMappingVO" property="labName"/> 	
			</div>
		  </td>
		  <td class="tdfont">
		  	<logic:notEqual name="investigationMappingVO" property="testType" value="P">
		  	 <div align="center" >
			  	<html:select name="medicalExamInitiationFB" property="testSampleCode" tabindex="1" styleClass="regcbo">
			  		<logic:present name="<%=investigationMappingVO.getLabTestCode() %>">
			  		<html:options collection="<%=investigationMappingVO.getLabTestCode() %>" labelProperty="label" property="value"/>
			  		</logic:present>
			  	</html:select>
			  		<logic:notPresent name="<%=investigationMappingVO.getLabTestCode() %>">
				  		<html:hidden name="medicalExamInitiationFB" property="testSampleCode" value=""/>
			  		</logic:notPresent>
		  	</div>
			 </logic:notEqual> 	
		  </td>
		  <td class="tdfont">
		   <div align="center" >
		   		<bean:write name="investigationMappingVO" property="testStatus"/>	 	
			</div>
		  </td>
		  </tr>
		</logic:iterate>
		
	 </table>
	</logic:present>		
</his:ContentTag>
</his:statusList>	
	
 	<his:ButtonToolBarTag>
         <div align="center">
         	<logic:present name="<%=MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST %>">
         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) return saveReferDetail()" onclick ="return saveReferDetail();" tabindex="1"/>
         	</logic:present>
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

   <html:hidden name="medicalExamInitiationFB" property="hmode"/>
   <html:hidden name="medicalExamInitiationFB" property="departmentCode" />
   <html:hidden name="medicalExamInitiationFB" property="departmentUnitCode" />
   <html:hidden name="medicalExamInitiationFB" property="certificateTypeID" />
   
</html:form>
<logic:notPresent name="<%=MedicalBoardConfig.MB_INVESTIGATION_MAPPING_VO_LIST %>">
<font color="red"><b><bean:message key="noInvFound"/></b></font>
</logic:notPresent>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
