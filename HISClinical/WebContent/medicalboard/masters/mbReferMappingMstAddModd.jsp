<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="medicalboard.MedicalBoardConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
	<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:css src="/hisglobal/css/calendar-blue2.css"/>    

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/medicalboard/js/mbReferMappingMstAddModdjs.js" />



<body>
<html:form action="/masters/ReferMappingMstACTION">
	<html:hidden name="ReferMappingMstFB" property="hmode" />
	<html:hidden name="ReferMappingMstFB" property="mode" />
	<html:hidden name="ReferMappingMstFB" property="certificateTypeID" />
	<html:hidden name="ReferMappingMstFB" property="index" />
	<html:hidden name="ReferMappingMstFB" property="certificateTypeName" />
	<html:hidden name="ReferMappingMstFB" property="noOfRow" />

	<his:TransactionContainer>
		<his:TitleTag name="Refer Mapping Master">
			<his:insertDateTag/>
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="certificatetype"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     &nbsp;&nbsp;<b><bean:write name="ReferMappingMstFB"  property="certificateTypeName" /> </b>
						</font> 
				   </div>
			     </td>
			     </tr>
			   
			     <tr>
			      <td width="50%" class="tdfonthead">
			        <div align="right">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="refertype"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
			      <div align="left">
			         &nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Department
							</font>
							<logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW"> 
						    <html:radio name="ReferMappingMstFB" property="referType" value="<%=MedicalBoardConfig.REFER_TYPE_FLAG_DEPARTMENT %>" tabindex="1"  onclick="showDeptOrUnit()"/>
							</logic:notEqual>
							<logic:equal name="ReferMappingMstFB" property="hmode" value="VIEW">
							   <html:radio name="ReferMappingMstFB" property="referType" value="<%=MedicalBoardConfig.REFER_TYPE_FLAG_DEPARTMENT %>" tabindex="1" disabled="true" />
							</logic:equal>
					&nbsp;&nbsp; <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								       Unit
							     </font>
						  <logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW"> 
							<html:radio name="ReferMappingMstFB" property="referType" value="<%=MedicalBoardConfig.REFER_TYPE_FLAG_UNIT %>" tabindex="1"  onclick="showDeptOrUnit()"/>
				          </logic:notEqual>
				          <logic:equal name="ReferMappingMstFB" property="hmode" value="VIEW">
				          <html:radio name="ReferMappingMstFB" property="referType" value="<%=MedicalBoardConfig.REFER_TYPE_FLAG_UNIT %>" tabindex="1" disabled="true" />
				          </logic:equal>
				          
				          
				   </div>
			     </td>
			     </tr>
			   </table>
			     
			 <logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW"> 
			  <table width="100%" border="0" cellspacing="1" cellpadding="0">
			   <tr>
			    <td width="30%" class="tdfonthead">
			        <div align="center" id="deptID" style="display:block">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="department"/>&nbsp;
						 </font>
				     </div>
				     
				     <div align="center" id="unitID" style="display:none">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="unit"/>&nbsp;
						 </font>
				     </div>
				     
				     
			      </td>
			       <td width="30%" class="tdfonthead">
			        <div align="center">
			             <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						 </font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isOptional"/>&nbsp;
						 </font>
				     </div>
			      </td>
			       <td width="30%" class="tdfonthead">
			        <div align="center">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="remark"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      
			   
			     <td width="10%" class="tdfonthead"></td>
			     
			     
			     </tr>
			     
			     
			     
			     
			     <tr>
			<td class="tdfont">
				  <div align="center" id="deptIdValue" style="display:block">
			          <html:select name="ReferMappingMstFB" property="deptCode" style="width:140;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.ALL_DEPARTMENT_LIST%>">
  								<html:options collection="<%=MedicalBoardConfig.ALL_DEPARTMENT_LIST%>" property="value" labelProperty="label" />
							    </logic:present>
							   </html:select> 
				  </div>
				   
				  <div align="center" id="unitIdValue" style="display:none">
			          <html:select name="ReferMappingMstFB" property="deptUnitCode" style="width:140;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
								<logic:present name="<%=MedicalBoardConfig.ALL_UNIT_LIST%>">
  								<html:options collection="<%=MedicalBoardConfig.ALL_UNIT_LIST%>" property="value" labelProperty="label" />
							    </logic:present>
							   </html:select> 
				  </div>
				   
			</td>
			
			<td class="tdfont">
				<div align="center">
			          <html:select name="ReferMappingMstFB" property="isOptional" style="width:140;" tabindex="1">
								<html:option value="-1">Select Value</html:option>
							     <html:option value="0">No</html:option>
							     <html:option value="1">Yes</html:option>
							   </html:select> 
				   </div>
			</td>
			
			
			<td class="tdfont">
				<div align="center">
			         &nbsp;<html:text name="ReferMappingMstFB" property="remarks"  maxlength="50" size="25" onkeypress="return validateAlphabetsOnly(this,event)" tabindex="1">
							   </html:text> 	   
				   </div>
			</td>
			
				<td class="tdfont">
				<div align="center">
				<logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW">
					<img class="button" id="addButton" style="cursor:pointer" src='<his:path src="/hisglobal/images/plus.png"/>' alt="Add Value" title="Add Value" onclick="submitFormOnValidate(validateOnAdd(),'ADDLIST');" tabindex="1"/>
				</logic:notEqual>
				</div>
			</td>
		   </tr>
		 </table>
	   </logic:notEqual>  
	</his:ContentTag>	 
	<his:statusList>
		<his:ContentTag>
			<table width="100%"  cellspacing="1" cellpadding="0">
			<tr>
				<td width="30%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<bean:message key="deptunit"/>
					</div>
				</td>
				
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<bean:message key="refertype"/>
					</div>
				</td>
				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<bean:message key="isOptional"/>
					</div>
				</td>
				<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
					<div align="center">
						<bean:message key="remark"/>
					</div>
				</td>
				<td class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;"></td>
			</tr>
			<logic:present name="<%=MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST%>"> 
			<logic:iterate indexId="idx" name='<%=MedicalBoardConfig.DEPARTMENT_UNIT_SELECTED_LIST%>' id="vo" type="hisglobal.vo.MbReferMappingMstVO">
				 <%String index=idx.toString(); %>
				<tr>
					<td width="30%" class="tdfont">
						<div align="center">
							<bean:write name="vo" property="deptOrUnitName" />
						</div>
					</td>
					
					<td width="25%" class="tdfont">
						<div align="center">
							<bean:write name="vo" property="referTypeName" />
						</div>
					</td>
					<td width="10%" class="tdfont">
						<div align="center">
						
						<bean:write name="vo" property="isOptionalLabel" />
													
						</div>
					</td>
					<td width="25" class="tdfont">
						<div align="center">
						  <bean:write name="vo" property="remarks" />
						</div>
					</td>
				
					<td width="5%" class="tdfont">
					  <logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW"> 
						<div align="center">
							<img class="button" id="minusButton"  style="cursor:pointer" src='<his:path src="/hisglobal/images/Minus.png"/>' alt="Remove Value" title="Remove Value" onclick="removeModData(<%=index%>);" tabindex="1"/>
						</div>
					  </logic:notEqual>	
					</td>
				
				</tr>
			</logic:iterate>
			</logic:present>
			</table>
			
		</his:ContentTag>
		</his:statusList>      
		<his:ButtonToolBarTag>
			  <span id="saveDiv">
			     <logic:notEqual name="ReferMappingMstFB" property="hmode" value="MODIFY">
			     <logic:notEqual name="ReferMappingMstFB" property="hmode" value="VIEW">
					   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateOnSave(),'SAVE')" onclick="submitFormOnValidate(validateOnSave(),'SAVE')" tabindex="1">
				 </logic:notEqual>
				 </logic:notEqual>
				 <logic:equal name="ReferMappingMstFB" property="hmode" value="MODIFY">
				     <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitFormOnValidate(validateOnSave(),'MODIFYSAVE')" onclick="submitFormOnValidate(validateOnSave(),'MODIFYSAVE')" tabindex="1">
				 </logic:equal>    
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		  </his:ButtonToolBarTag>
	<his:status/>
	</his:TransactionContainer>
 </html:form>
 
</html>