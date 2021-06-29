<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="medicalboard.MedicalBoardConfig"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/popup.js"/>

<body>

<script type="text/javascript">
function showOrg(){
if (document.forms[0].orgID.value=="11"){
 document.getElementById("showOrg").style.display="block";
	}
	else{
	    document.getElementById("showOrg").style.display="none";
	
}
}
function showOrgDetails(){

document.getElementById("showOrgDetails").style.display="block";


}
function DontshowOrgDetails(){

document.getElementById("showOrgDetails").style.display="none";


}
</script>

<html:form action="/mbSearchRegistration">

<his:TitleTag name="Medical Board Registration Search">
	   <b>
	   <font size="2" face="Verdana, Arial, Helvetica, sans-serif">
       
	   </font>
	   </b>    

</his:TitleTag>

<his:InputCrNoTag name="MbNewRegistrationFB">
	
	</his:InputCrNoTag>
<bean:define id="crNo" name="MbNewRegistrationFB" property="crNo" type="java.lang.String"/>

<his:statusInProcess>
	
	<jsp:include page="/registration/patientDetail.cnt" flush="true"/>
	
	<his:ContentTag>
 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="primCat"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
			          <div align="left">
			            <html:select name="MbNewRegistrationFB" property="patPrimaryCatCode" style="width:160;" onkeypress="if(event.keyCOde==13)submitForm('GETCONSULTANT');" onchange="submitForm('GETCONSULTANT');" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>				
			        </td>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="certificateType"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
			    	<div align="left">
			            <html:select name="MbNewRegistrationFB" property="certificateTypeID" style="width:160;" >
							<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE%>" >
								<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_CERTIFICATE_TYPE %>" labelProperty="label" property="value"/>
							</logic:present>
						</html:select> 
					</div>				
			    </td>
			</tr>

			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="designation"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" >
			       <div align="left">
			          <html:text name="MbNewRegistrationFB" property="designation"  maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event)"  >
						   </html:text> 
					</div>				
			    </td>
			   
				<td width="25%" class="tdfonthead">
				<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="requestFrom"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont" >
				<div align="left">
						    <html:radio name="MbNewRegistrationFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_INDIVIDUAL%>" onclick="DontshowOrgDetails()" />Individual
							<html:radio name="MbNewRegistrationFB" property="requestFrom" tabindex="1"  value="<%=MedicalBoardConfig.MEDICAlBOARD_REQUESTFROM_ORGANIZATION%>" onclick="showOrgDetails()" />Organization 
						</div>
				</td>
			        </tr>
			</table>
			</his:ContentTag>
			<div id="showOrgDetails" style="display:none;">
			<his:SubTitleTag name="Organization Details">
			</his:SubTitleTag>
			
			 	<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td width="25%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<bean:message key="organization"/>
						</font>
					</div>
				</td>
				<td width="25%" class="tdfont">
			          <div align="left">
			            <html:select name="MbNewRegistrationFB" property="orgID" style="width:160;" onkeypress="if(event.keyCOde==13)showOrg();" onchange="showOrg()" styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MedicalBoardConfig.ESSENTIALBO_OPTION_ORGANISATION_NAME %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>				
			        </td>
			        <td width="25%" class="tdfonthead">
			        </td>
			        <td width="25%" class="tdfont">
			        </td>
			         	
			<his:statusInProcess>
		<div id="showOrg" style="display:none;">
	     	 <table width="100%" border="0" cellspacing="1" cellpadding="0">
			    <tr>
	   			  <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="organizationType"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfont">
			          <div align="left">
			            <html:select name="MbNewRegistrationFB" property="orgTypeID" style="width:160;"  styleClass="registrationCmb" > 
								<html:option value="-1">Select Value</html:option>
								<html:options collection="<%=MedicalBoardConfig.MEDICALBOARD_ORGANIZATIONTYPES %>" labelProperty="label" property="value"  />
							</html:select> 
						</div>				
			        </td>
	
				  <td width="25%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="address"/>
							</font>
						</div>
					</td>
			<td width="25%" class="tdfont" >
			       <div align="left">
			          <html:text name="MbNewRegistrationFB" property="orgAddress"  maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event)"  >
						   </html:text> 
					</div>
				
			    </td>
			
				</tr>
			</table>
			</div>
		</his:statusInProcess>
		
			</tr>
			</table>
			
			</div>
			
			
			
			  <his:SubTitleTag name="Document Details">
			   </his:SubTitleTag>
			   <his:ContentTag>
                <table width="100%" border="0"  cellspacing="1" cellpadding="0">
                
                <tr>
					<td width="10%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="select"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="45%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="checklist"/>
					</b>	
					</font>
					</div>
					</td>
					<td width="45%" class="tdfonthead">
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
					<bean:message key="remarks"/>
					</b>	
					</font>
					</div>
					</td>
                </tr>
                 <logic:iterate id="checklistDtlVOs" indexId="idxxx" name="<%=MedicalBoardConfig.MEDICALBOARD_CHECKLIST_VO_ARRAY %>" type="hisglobal.vo.MedicalBoardChecklistVO" >
             			    
                <tr>
                	<td width="10%" class="tdfont">
						<div align="center">
						<html:checkbox name="MbNewRegistrationFB" property="selectedCheckListId" value="<%=checklistDtlVOs.getChecklistID()%>" onclick="showRemarksTextBox(this)" ></html:checkbox>
						
						</div>
					</td>
                <td width="45%" class="tdfont">
						 <div align="center">
					     <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
					     <bean:write name="checklistDtlVOs" property="checklist"/>
					     <html:hidden name="MbNewRegistrationFB" property="isCompulsoryArray" value="<%=checklistDtlVOs.getIsCompulsory() %>"/>
					     <html:hidden name="MbNewRegistrationFB" property="checkListNameArray" value="<%=checklistDtlVOs.getChecklist() %>"/>
					     
					     </font>
					     </div>
				</td>
				<td width="45%" class="tdfont">
					<div align="center">
			          &nbsp;<html:text name="MbNewRegistrationFB" property="remarks" disabled="true" maxlength="50" size="10"
							onkeypress="return validateAlphaNumericOnly(event)">
						</html:text>
						</div>
						</td>
			
                </tr>
                </logic:iterate>
                </table>
                </his:ContentTag>
                               
			
			
			
			</his:statusInProcess>
	<his:ButtonToolBarTag>
		  <img class='button' src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>'  style=cursor:pointer onclick ="finalSubmit('SAVE')" onkeypress="if(event.keyCode==13) submitForm('SAVE')") tabindex="1">
		  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW')" tabindex="1">
          <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>'  style=cursor:pointer onclick ="clearForm()" onkeypress="if(event.keyCode==13) clearForm();" tabindex="1">
	</his:ButtonToolBarTag>
	<html:hidden name="MbNewRegistrationFB" property="hmode"/>
	<html:hidden name="MbNewRegistrationFB" property="patCrNo"/>
	</html:form>
</body>
</html>

	