<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.hisconfig.Config"%>
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
    
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/opd/opdJs/pagination.js"/>
<his:javascript src="/opd/js/MacroMstAddMod.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<body>
<html:form action="/master/macroMstAdd" >
<%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="macroMstFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   		</logic:equal>
	
	<html:hidden name="macroMstFB" property="hmode"/>
	<html:hidden name="macroMstFB" property="processID"/>
	<html:hidden name="macroMstFB" property="slNo"/>
 	<html:hidden name="macroMstFB" property="macroID"/>
     <html:hidden name="macroMstFB" property="length"/>
    
    <his:TransactionContainer>

		<his:TitleTag name="Macro Master">
		</his:TitleTag>
         <his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0">
			 
			  <tr> 
			    <td width="50%" class="tdfonthead">
			      <div align="right">
						 <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="processName"/>&nbsp;
								<html:hidden name="macroMstFB" property="processName"/>
							
						 </font>
				  </div>
			    </td>
			   
			    <td width="50%" class="tdfont">
			      <div align="left">
			       		<html:hidden name="macroMstFB" property="processName" />
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
						&nbsp;<b><bean:write name="macroMstFB" property="processName"/></b> 
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
								<bean:message key="macroHeader"/>&nbsp;
						 </font>
				  </div>
			    </td>
			   
			    <td width="50%" class="tdfont">
			      <div align="left">
			        <logic:notEqual name="macroMstFB" property="hmode" value="VIEW">
			             &nbsp;<html:text name="macroMstFB" property="macroHeader"  maxlength="50" size="30"  onkeypress="return validateAlphaOnly(this,event)">
							   </html:text>
					</logic:notEqual>		   
				    <logic:equal name="macroMstFB" property="hmode" value="VIEW">
								<html:hidden name="macroMstFB" property="macroHeader" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								&nbsp;<b><bean:write name="macroMstFB" property="macroHeader"/></b> 
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
								<bean:message key="macroDesc"/>&nbsp;
						 </font>
				  </div>
			    </td>
			    <td width="50%" class="tdfont">
			      <div align="left">
			        <logic:notEqual name="macroMstFB" property="hmode" value="VIEW">
							  &nbsp;<html:textarea name="macroMstFB" property="macroDesc" rows="3" cols="50"  onkeypress="return validateAlphaNumericOnly(event)">
							   </html:textarea>
					</logic:notEqual>		   
				    <logic:equal name="macroMstFB" property="hmode" value="VIEW">
								<html:hidden name="macroMstFB" property="macroDesc" />
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								&nbsp;<b><bean:write name="macroMstFB" property="macroDesc"/></b> 
								</font>
				    </logic:equal>				   
			      </div>
			    </td>
			  </tr>
			     
			     		 
			  <logic:notEqual name="macroMstFB" property="hmode" value="ADD">
			 <logic:notEqual name="macroMstFB" property="hmode" value="SAVE">
			      <tr>
			        <td width="50%" class="tdfonthead">
			         <div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								<bean:message key="isActive"/>&nbsp;
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     &nbsp;<html:select name="macroMstFB" property="isActive"  disabled="<%=this.readOnly %>" style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
			   </logic:notEqual>
			</table>
		</his:ContentTag>
			
			<his:ButtonToolBarTag>
			<span id="saveDiv">
			    <logic:notEqual name="macroMstFB" property="hmode" value="MODIFY">
			    <logic:notEqual name="macroMstFB" property="hmode" value="VIEW">
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('SAVE')" onclick="finalSubmit('SAVE')">
				  <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:notEqual>
				</logic:notEqual>
			    <logic:equal name="macroMstFB" property="hmode" value="MODIFY">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-mo.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) finalSubmit('MODIFYSAVE')" onclick="finalSubmit('MODIFYSAVE')">
				   <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor: pointer" onclick="clearForm()" onkeypress="if(event.keyCode==13) clearForm()">
				</logic:equal>  
			      <img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor: pointer" onclick="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
			</span>
		</his:ButtonToolBarTag>
	</his:TransactionContainer>	
	
	<center><b><his:status/></b></center>
</html:form>
</body>
</html>
