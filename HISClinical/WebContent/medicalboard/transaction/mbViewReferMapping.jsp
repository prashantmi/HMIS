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
	
	selectedReferDept=document.getElementsByName("selectedReferDept")
	var selectedReferDeptString="";
	var valid=false;
	for(var i=0;i<selectedReferDept.length;i++){
		//alert("selectedReferDept[" + i +"].style.disabled==false "+ selectedReferDept[i].style.disabled)
		if(selectedReferDept[i].checked){
			selectedReferDeptString=selectedReferDeptString+"@"+selectedReferDept[i].value;
			valid=true;
		}
	}
	selectedReferDeptString=selectedReferDeptString.substring(1);
	if(valid){
		opener.document.getElementsByName("referDept")[0].value=selectedReferDeptString
		self.close();
		//opener.document.getElementsByName("hmode")[0].value='SAVEREFERDETAIL'
		//opener.document.forms[0].submit();
		opener.saveReferDetail();
	}
	else{
		alert("Please Select at least one department")
		return false;
	}	
}


</script>

<%try{ %>	
<his:TransactionContainer>
<html:form action="/medicalExamInitiation">	   
<his:TitleTag name="Select Refer Department/Unit">
</his:TitleTag>	   
<his:statusList>
   	<his:ContentTag>
		<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST %>">
		<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
		 <td width="5%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="select"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="dept/unit"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="referType"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="visit"/> <bean:message key="status"/></b>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfonthead">
		  <div align="center" >
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			  <b><bean:message key="visit"/> <bean:message key="date"/></b>
			 </font>
			</div>
		  </td>
		</tr>
		
		<logic:iterate id="referMappingVO" name="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST %>" type="hisglobal.vo.MedicalBoardReferMappingVO" indexId="index">
		<tr>
		 <td width="5%" class="tdfont">
		 <%String checked="",disabled=""; %>
		  <div align="center">
		  	 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
		  	 	<%if(referMappingVO.getIsOptional().equals(MedicalBoardConfig.MEDICAL_BOARD_REFER_IS_OPTIONAL_NO)){
		  	 		checked="checked='checked'";
		  	 	}else{
		  	 		checked="";
		  	 	}%>
		  	 	<logic:notEqual name="referMappingVO" property="isReferred" value="<%=MedicalBoardConfig.IS_REFERRED_YES %>">
			  	 	<input type="checkbox" name="selectedReferDept" value='<%=referMappingVO.getReferType()+"#"+referMappingVO.getDepartmentCode()+"#"+referMappingVO.getDepartmentUnitCode()%>' tabindex="1" <%=checked%> />
		  	 	</logic:notEqual>
			 </font>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  <div align="center">
				<%=referMappingVO.getDepartment()+ (referMappingVO.getDepartmentUnit()==null?"":"/"+ referMappingVO.getDepartmentUnit())%>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		   <div align="center" >
		   	<logic:equal name="referMappingVO" property="referType" value="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_TYPE_DEPARTMENT %>">
			 	Department
			 </logic:equal>
		   	<logic:equal name="referMappingVO" property="referType" value="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_TYPE_UNIT %>">
			 	Unit
			 </logic:equal>
			 <html:hidden name="medicalExamInitiationFB" property="referType" value="<%=referMappingVO.getReferType() %>"/>
			</div>
		  </td>
		 <td width="17%" class="tdfont">
		  	<%String visitDate= "";%>
		  	<div align="center">
		  	 <logic:equal name="referMappingVO" property="isReferred" value="<%=MedicalBoardConfig.IS_REFERRED_YES %>">
		  	 <%if(referMappingVO.getReferVisitDate()==null){
		  	 	out.println("Visit pending");
		  	 }	 
		  	 else{
		  	 	visitDate=referMappingVO.getReferVisitDate();
		  		out.println("Visit Done");
		  	 } %>	
			 </logic:equal>
			 
			 </div>
		  </td>
		  <td width="17%" class="tdfont">
		  <div align="center">
				<bean:write name="referMappingVO" property="referVisitDate"/> 	
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
         	<logic:present name="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST %>">
         	<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) return saveReferDetail()" onclick ="return saveReferDetail();" tabindex="1"/>
         	</logic:present>
            <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onkeypress="if(event.keyCode==13) window.close();" tabindex="1" onclick ="window.close();">
	     </div>
     </his:ButtonToolBarTag>

   <html:hidden name="medicalExamInitiationFB" property="hmode"/>
   <html:hidden name="medicalExamInitiationFB" property="departmentCode" />
   <html:hidden name="medicalExamInitiationFB" property="departmentUnitCode" />
   <html:hidden name="medicalExamInitiationFB" property="certificateTypeID" />
</html:form>

<logic:notPresent name="<%=MedicalBoardConfig.MEDICAL_BOARD_REFER_MAPPING_VO_LIST %>">
	<font color="red"><b><bean:message key="noReferFound"/></b></font>
</logic:notPresent>
</his:TransactionContainer>
<% } catch(Exception e) {e.printStackTrace();}%>
