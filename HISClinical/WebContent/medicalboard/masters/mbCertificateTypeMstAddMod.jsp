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
<his:javascript src="/medicalboard/js/certificateTypeMstAddMod.js" />

<script>
 
function clearForm()
{
	document.getElementsByName("certificateCatID")[0].value="-1";
	document.getElementsByName("certificateTypeName")[0].value="";
	document.getElementsByName("description")[0].value="";
	document.getElementsByName("boardType")[0].value="-1";
	document.getElementsByName("issueType")[0].value="-1";
	document.getElementsByName("minRequest")[0].value="";
	document.getElementsByName("maxRequest")[0].value="";
	document.getElementsByName("requisitionBy")[0].value="-1";
	document.getElementsByName("deptUnitCode")[0].value="-1";
	document.getElementsByName("ageBound")[1].checked="true";
	document.getElementsByName('minAge')[0].value="";
	document.getElementsByName('maxAge')[0].value="";
	document.getElementsByName('minAge')[0].disabled=true;
    document.getElementsByName('maxAge')[0].disabled=true;
	document.getElementsByName("locationBound")[1].checked="true";
	document.getElementById('showDistrictDetails').style.display="none";
	document.getElementsByName("isCertNoRequired")[1].checked="true";
	document.getElementsByName('certNoStartFrom')[0].value="";
	document.getElementsByName('certNoStartFrom')[0].disabled=true;
}
 
function callThisOnload(){	
 if(document.getElementsByName('hmode')[0].value!='ADD')
 //alert("on Load")
	showDistrict();
    showAge();
 }
 

 
</script>


<body>
 <html:form action="/master/CertificateTypeMasterACTION">
	
	<his:TransactionContainer>
		<his:TitleTag name="Certificate Type Master">
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
								<bean:message key="certificateCategory"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				      &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="certificateCatID" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <logic:present name="<%= MedicalBoardConfig.LIST_OF_CERTIFICATE_CATEGORY %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_CERTIFICATE_CATEGORY %>" property = "value" labelProperty = "label"/>
                      </logic:present>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" disabled="true" property="certificateCatID" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                        <logic:present name="<%= MedicalBoardConfig.LIST_OF_CERTIFICATE_CATEGORY %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_CERTIFICATE_CATEGORY %>" property = "value" labelProperty = "label"/>
                      </logic:present>
                      </html:select> 
						   </font>   
				    </logic:equal>
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
						<bean:message key="certificateType"/>&nbsp;
					</font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">   
				   <div align="left">
				     <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				  &nbsp;<html:text name="CertificateTypeMstFB" property="certificateTypeName" maxlength="50" size="30" onkeypress="return validateAlphaNumericOnly(event,this);" tabindex="1">
						</html:text>
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB"  property="certificateTypeName" /> </b>
						   </font>   
				    </logic:equal>
				   </div>
				 </td>
			    </tr>
			  <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="description"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				      &nbsp;<html:text name="CertificateTypeMstFB" property="description" maxlength="100" size="30"  onkeypress="return validateAlphaNumericOnly(event,this);" tabindex="1">
						</html:text>  
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB" property="description" /> </b>
						   </font>   
				    </logic:equal>
				   </div>
				 </td>
			   </tr>
			   <tr>
				   <td width="40%" class="tdfonthead">
						<div align="right">
						 <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							*
					      </font>
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="boardType"/>&nbsp;
							</font>
						</div>
				      </td>
				    <td width="50%" class="tdfont">
				     <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="boardType" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <logic:present name="<%= MedicalBoardConfig.LIST_OF_BOARD_TYPE %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_BOARD_TYPE %>" property ="value" labelProperty = "label"/>
                      </logic:present>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						   &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="boardType" disabled="true" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                          <logic:present name="<%= MedicalBoardConfig.LIST_OF_BOARD_TYPE %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_BOARD_TYPE %>" property ="value" labelProperty = "label"/>
                      </logic:present>
                      </html:select>    

						   </font>   
				    </logic:equal>
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
						<bean:message key="minimumRequisition"/>&nbsp;
					</font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">   
				   <div align="left">
				     <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				  &nbsp;<html:text name="CertificateTypeMstFB" property="minRequest" maxlength="2" size="10"  onkeypress="return validateNumeric(event);" tabindex="1">
						</html:text>
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB"  property="minRequest" /> </b>
						   </font>   
				    </logic:equal>
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
						<bean:message key="maximumRequisition"/>&nbsp;
					</font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">   
				   <div align="left">
				     <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				  &nbsp;<html:text name="CertificateTypeMstFB" property="maxRequest" maxlength="3" size="10"  onkeypress="return validateNumeric(event);" tabindex="1">
						</html:text>
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB"  property="maxRequest" /> </b>
						   </font>   
				    </logic:equal>
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
								<bean:message key="requisitionBy"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="requisitionBy" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <html:option value="1">Organization</html:option>
                       <html:option value="2">Individual</html:option>
                       <html:option value="3">Organization or Individual</html:option>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						  &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="requisitionBy" disabled="true" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <html:option value="1">Organization</html:option>
                       <html:option value="2">Individual</html:option>
                        <html:option value="3">Organization or Individual</html:option>
                      </html:select>   
						   </font>   
				    </logic:equal>
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
								<bean:message key="issueType"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="issueType" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <html:option value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>">Organization</html:option>
                       <html:option value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>">Individual</html:option>
                       <html:option value="<%=MedicalBoardConfig.ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION %>">Organigation or Individual</html:option>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						  &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="issueType" disabled="true" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <html:option value="<%=MedicalBoardConfig.ISSUE_TO_ORGANIGATION_ONLY %>">Organization</html:option>
                       <html:option value="<%=MedicalBoardConfig.ISSUE_TO_CANDIDATE_ONLY %>">Individual</html:option>
                        <html:option value="<%=MedicalBoardConfig.ISSUE_TO_BOTH_CANDIDATE_OR_ORGANIZATION %>">Organigation or Individual</html:option>
                      </html:select>   
						   </font>   
				    </logic:equal>
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
								<bean:message key="deptUnitName"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="deptUnitCode" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <logic:present name="<%= MedicalBoardConfig.LIST_OF_DEPARTMENT_UNIT_CODE %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_DEPARTMENT_UNIT_CODE %>" property = "value" labelProperty = "label"/>
                     </logic:present>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						 &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="deptUnitCode" disabled="true" styleClass="regcbo" >
                                <html:option value="-1">Select Value</html:option>
                                 <logic:present name="<%= MedicalBoardConfig.LIST_OF_DEPARTMENT_UNIT_CODE %>">
                                <html:options collection = "<%= MedicalBoardConfig.LIST_OF_DEPARTMENT_UNIT_CODE %>" property = "value" labelProperty = "label"/>
                                </logic:present>
                               </html:select>   
						</font>   
				    </logic:equal>
				   </div>
				 </td>
			  </tr> 	
			  	  
			     <tr>
				  <td width="40%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="isAgeBound"/>&nbsp;
							</font>
						</div>
				   </td>
				   <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				   <td width="60%" class="tdfont">
						<div align="left">
						   &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Yes
							</font>
							<html:radio name="CertificateTypeMstFB" property="ageBound" value="1" onclick="showAge()"/>
							&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								No
							</font>
							<html:radio name="CertificateTypeMstFB" property="ageBound" value="0" onclick="showAge()" />
						 </div>
				    </td>
				    </logic:notEqual>
				    
				    	<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				      <td width="60%" class="tdfont">
						<div align="left">
						   &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Yes
							</font>
							<html:radio name="CertificateTypeMstFB" property="ageBound" value="1" onclick="showAge()" disabled="true"/>
							&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								No
							</font>
							<html:radio name="CertificateTypeMstFB" property="ageBound" value="0" onclick="showAge()" disabled="true"/>
						 </div>
				    </td>
				    </logic:equal>
			  </tr>  
			        <tr>
			   <td width="50%" class="tdfonthead">
			    <div align="right">
			       
			        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="minage"/>&nbsp;
					</font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">   
				   <div align="left">
				     <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				  &nbsp;<html:text name="CertificateTypeMstFB" property="minAge" maxlength="2" size="10" disabled="true" onkeypress="return validateNumeric(event);" tabindex="1">
						</html:text>
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB"  property="minAge" /> </b>
						   </font>   
				    </logic:equal>
				      <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						Year
					  </font>
				   </div>
				 </td>
			    </tr>
			      
			  <tr>
			   <td width="50%" class="tdfonthead">
			    <div align="right">
			      
			        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						<bean:message key="maxage"/>&nbsp;
					</font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">   
				   <div align="left">
				     <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				  &nbsp;<html:text name="CertificateTypeMstFB" property="maxAge" maxlength="3" size="10" disabled="true" onkeypress="return validateNumeric(event);" tabindex="1">
						</html:text>
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				           <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						     <b><bean:write name="CertificateTypeMstFB"  property="maxAge" /> </b>
						   </font>   
				    </logic:equal>
				    <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						Year
					</font>
				   </div>
				 </td>
			    </tr> 
			
			      
			   <tr>
				  <td width="40%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="isLocationBound"/>&nbsp;
							</font>
						</div>
				   </td>
				   <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
				   <td width="60%" class="tdfont">
						<div align="left">
						   &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Yes
							</font>
							<html:radio name="CertificateTypeMstFB" property="locationBound" value="1" onclick="showDistrict()"/>
							&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								No
							</font>
							<html:radio name="CertificateTypeMstFB" property="locationBound" value="0" onclick="showDistrict()"/>
						 </div>
				    </td>
				    </logic:notEqual>
				    
				    <logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				      <td width="60%" class="tdfont">
						<div align="left">
						   &nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								Yes
							</font>
							<html:radio name="CertificateTypeMstFB" property="locationBound" value="1" onclick="showDistrict()" disabled="true"/>
							&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								No
							</font>
							<html:radio name="CertificateTypeMstFB" property="locationBound" value="0" onclick="showDistrict()" disabled="true"/>
						 </div>
				    </td>
				      
				    </logic:equal>
				    
				    
			       </tr>  
			       
			       
			       			  
			  
			      
			 <tr>
			    <td width="50%" class="tdfonthead">
			        <div align="right">
				        <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
						</font> 
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="tmpl"/>&nbsp;
						 </font>
				     </div>
			      </td>
			      <td width="50%" class="tdfont">
				   <div align="left">
				    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW" >
				     &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="templateCode" styleClass="regcbo" >
                       <html:option value="-1">Select Value</html:option>
                       <logic:present name="<%= MedicalBoardConfig.LIST_OF_TEMPLATE  %>">
                       <html:options collection = "<%= MedicalBoardConfig.LIST_OF_TEMPLATE  %>" property = "value" labelProperty = "label"/>
                     </logic:present>
                      </html:select>    
					</logic:notEqual>	 
					<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				        <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">   
						 &nbsp;<html:select name="CertificateTypeMstFB" tabindex="1" property="templateCode" disabled="true" styleClass="regcbo" >
                                <html:option value="-1">Select Value</html:option>
                                 <logic:present name="<%= MedicalBoardConfig.LIST_OF_TEMPLATE  %>">
                                <html:options collection = "<%= MedicalBoardConfig.LIST_OF_TEMPLATE  %>" property = "value" labelProperty = "label"/>
                                </logic:present>
                               </html:select>   
						</font>   
				    </logic:equal>
				   </div>
				 </td>
			  </tr> 	

					
						
				
					 <tr style="display:none">
						<td width="40%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="isCertNoReq"/>&nbsp;
								</font>
							</div>
						</td>
						<% boolean strDis=false; %>
						<logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
							<logic:equal name="CertificateTypeMstFB" property="hmode" value="MODIFY">
								<logic:equal name="CertificateTypeMstFB" property="isCertNoRequired" value="1">
									<% strDis=true; %>
									<html:hidden name="CertificateTypeMstFB" property="isCertNoRequired" />
									<html:hidden name="CertificateTypeMstFB" property="certNoStartFrom" />
								</logic:equal>
							</logic:equal>
						</logic:notEqual>
						<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
							<% strDis=true; %>
							<html:hidden name="CertificateTypeMstFB" property="isCertNoRequired" />
							<html:hidden name="CertificateTypeMstFB" property="certNoStartFrom" />
						</logic:equal>
						<td width="60%" class="tdfont">
							<div align="left">
								&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									Yes
								</font>
								<html:radio name="CertificateTypeMstFB" property="isCertNoRequired" value="1" onchange="showCertNo()" disabled='<%=strDis%>' />
								&nbsp;&nbsp;&nbsp;<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									No
								</font>
								<html:radio name="CertificateTypeMstFB" property="isCertNoRequired" value="0" onchange="showCertNo()" disabled='<%=strDis%>' />
							</div>
						</td>
					</tr>  
					<tr style="display:none">
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="ceertStartFrm"/>&nbsp;
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
									&nbsp;<html:text name="CertificateTypeMstFB" property="certNoStartFrom" maxlength="4" size="10" disabled="true" onkeypress="return validateNumeric(event);" tabindex="1"></html:text>
								</logic:notEqual>
								<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<b><bean:write name="CertificateTypeMstFB"  property="certNoStartFrom" /> </b>
									</font>
								</logic:equal>
							</div>
						</td>
					</tr> 
				</table>
			</his:ContentTag>
		
			<div id="showDistrictDetails" style="display:none;"> 
			 
			    <his:SubTitleTag key="district">
		        </his:SubTitleTag>
		         <his:ContentTag>
			   <table width="100%" border="0" cellspacing="1" cellpadding="0">
			    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
			     <tr>   
					   <td width="35%"  class="tdfonthead">
							<div align="center" >
						  <html:select name="CertificateTypeMstFB" property="district" multiple="true" size="6" >
									<logic:present name="<%=MedicalBoardConfig.LIST_OF_DISTRICT%>" >
										<html:options collection="<%=MedicalBoardConfig.LIST_OF_DISTRICT%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
						</td>
						<td width="15%"  class="tdfont">
							<div align="center">
								<img src="/HIS/hisglobal/images/avai/forward3.gif"   class="link"  onClick='moveRightSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/forwardward.gif"   class="link"  onClick='moveRightAll();'/> 	
								<br><br>
								<img src="/HIS/hisglobal/images/avai/back3.gif"   class="link"  onClick='moveLeftSingle();'/> 	
								<img src="/HIS/hisglobal/images/avai/backward.gif"   class="link"  onClick='moveLeftAll();'/> 	
							</div>
						</td>
						<td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="CertificateTypeMstFB" property="selectedDistrict" multiple="true" size="6">
								<logic:present name="<%=MedicalBoardConfig.DISTRICTID_LIST_SELECTED%>" >
										<html:options collection="<%=MedicalBoardConfig.DISTRICTID_LIST_SELECTED%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
					    </td>
				</tr>
				</logic:notEqual>
				<logic:equal name="CertificateTypeMstFB" property="hmode" value="VIEW">
				       <td width="30%"  class="tdfonthead"></td>
				     <td width="35%"  class="tdfonthead">
							<div align="center">
								<html:select name="CertificateTypeMstFB" property="selectedDistrict" disabled="true" multiple="true" size="6">
								<logic:present name="<%=MedicalBoardConfig.DISTRICTID_LIST_SELECTED%>" >
										<html:options collection="<%=MedicalBoardConfig.DISTRICTID_LIST_SELECTED%>" property="value" labelProperty="label" />
									</logic:present>
								</html:select>
							</div>
					    </td>
				   <td width="30%"  class="tdfonthead"></td>
				  
			    </logic:equal>
			    

			 
	         </table>
			</his:ContentTag>
		</div>
			  
	
		<his:ButtonToolBarTag>
				<span id="saveDiv">
			    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="CertificateTypeMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('SAVE')" onclick="submitPage('SAVE')" tabindex="1">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()" tabindex="1">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="CertificateTypeMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('MODIFYSAVE')" onclick="submitPage('MODIFYSAVE')" tabindex="1">
				</logic:equal> 
	               <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')" tabindex="1">
			</span>
		</his:ButtonToolBarTag>
		
	  </his:TransactionContainer>
	
	<html:hidden name="CertificateTypeMstFB" property="hmode" />
	<html:hidden name="CertificateTypeMstFB" property="certificateTypeID" />
	<html:hidden name="CertificateTypeMstFB" property="slNo" />
	<his:status/>
  </html:form>
  </body>


 </html>