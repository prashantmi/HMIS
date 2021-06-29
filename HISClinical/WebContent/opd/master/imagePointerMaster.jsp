<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="opd.OpdConfig"%>
<%@page import="opd.master.controller.fb.ImagePointerMasterFB"%>
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
<his:css src="/hisglobal/css/color_picker.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/colorPicker.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function validateAdd()
{
	var valid=true;
	if(isEmpty(document.forms[0].imageDesc,"Image description"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}



function clearAddForm()
{
	document.getElementsByName("imageDesc")[0].value="";
	document.getElementsByName("color")[0].value="#000000";
}


</script>
<title>Image Pointer Master</title>

<body>
	<html:form action="/master/imagePointerMaster">
	<%!boolean readOnly; %>
   <% this.readOnly=false; %>
   
   <logic:equal name="ImagePointerMasterFB" property="hmode" value="VIEW">
   		<% this.readOnly=true; %>
   		</logic:equal>
		<his:TransactionContainer>
		
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Image Pointer Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Image Pointer Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Image Pointer Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="ImagePointerMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="imageDesc"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="ImagePointerMasterFB" property="imageDesc" maxlength="50" size="30" onkeypress="return validateAlphabetsOnly(event,this)" >
								</html:text>
								<html:hidden name="ImagePointerMasterFB" property="slNo"/>
								<html:hidden name="ImagePointerMasterFB" property="imageDescId"/>
							</div>
						</td>
					</tr>
					<tr>
					<logic:notEqual name="ImagePointerMasterFB" property="hmode" value="VIEW">
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*
	  								</font>
								<b><bean:message key="isDefault"/></b>
							</font>
						</div>
					</td>
					<td width="80%" class="tdfont">
						<div align="left">
						    <html:radio name="ImagePointerMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="ImagePointerMasterFB" property="isDefault" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
						</div>
					</td>
					</logic:notEqual>
					
				</tr>
					<tr height="25px">
					<td class="tdfonthead" width="50%">
					<div align="right">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<font color="#FF0000">*
	  								</font>
						<b><bean:message key="color"/></b>
					</font>
					</div>
					</td>
					<td class="tdfont" width="50%">
					<div align="left">
						<logic:equal name="ImagePointerMasterFB" property="hmode" value="ADD">
					<input name="color" id="color" readonly="readonly" value='#000000'  
						type="text" maxlength="7" size="10" 
						style="width: 80px; font-size: 12px; height: 17px; float: left; border: 0px; padding-top: 2px">
						</logic:equal>
						<logic:equal name="ImagePointerMasterFB" property="hmode" value="MODIFY">
					<input name="color" id="color" readonly="readonly" value='<%=((ImagePointerMasterFB)request.getAttribute("ImagePointerMasterFB")).getColor()%>'
						type="text" maxlength="7" size="10" 
						style="width: 80px; font-size: 12px; height: 17px; float: left; border: 0px; padding-top: 2px">
						</logic:equal>
						<img src="/HISClinical/hisglobal/images/select_arrow.gif" id="color1"
								style="float: right; padding-right: 1px; padding-top: 1px" title="Color Picker"
									onmouseover="this.src='/HISClinical/hisglobal/images/select_arrow_over.gif'"
										onmouseout="this.src='/HISClinical/hisglobal/images/select_arrow.gif'">
														
									<script language="JavaScript">
									ColorPicker.setup({ inputField : "color", button : "color1", singleClick : true });	
										</script>
							</div>
							</td>
						</tr>
				<logic:equal name="ImagePointerMasterFB" property="hmode" value="MODIFY">
				
				<tr>
			        <td width="50%" class="tdfonthead">
			         	<div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/></b>
					  		 </font>
				    	 </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="ImagePointerMasterFB" property="isValid"  styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
				</logic:equal>
				</table>	
			</his:ContentTag>
		</logic:notEqual>
				<logic:equal name="ImagePointerMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="imageDesc"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<bean:write name="ImagePointerMasterFB" property="imageDesc" />
								
							</div>
						</td>
					</tr>
				
				<tr>
				    <td width="20%" class="tdfonthead">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<font color="#FF0000">*
	  								</font>
								<b><bean:message key="isDefault"/></b>
							</font>
						</div>
					</td>
					<td width="80%" class="tdfont">
						<div align="left">
						    <html:radio name="ImagePointerMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.NO%>" />No
							<html:radio name="ImagePointerMasterFB" property="isDefault" disabled="true" tabindex="1"  value="<%=OpdConfig.YES%>" />Yes
						</div>
					</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="color"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="ImagePointerMasterFB" property="color" disabled="true" maxlength="50" size="30" >
								</html:text>
								
								<label style="background-color:<bean:write name="ImagePointerMasterFB" property="color"/>" >&nbsp;&nbsp;&nbsp;&nbsp;</label>
							</div>
						</td>
					</tr>
				<logic:equal name="ImagePointerMasterFB" property="hmode" value="VIEW">
				
				<tr>
			        <td width="50%" class="tdfonthead">
			         	<div align="right">
					   <font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
					   </font> 
				       		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
							<b>	<bean:message key="isActive"/></b>
					  		 </font>
				    	 </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="ImagePointerMasterFB" property="isValid"  disabled="<%=this.readOnly %>" styleClass="regcbo">
							<html:option value="<%=Config.IS_VALID_ACTIVE%>">Active</html:option>
							<html:option value="<%=Config.IS_VALID_INACTIVE%>">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
				</logic:equal>
				
				</table>		
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick="if(validateAdd()) submitForm('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm('SAVE')">
			</logic:equal>
			
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick="validateAdd() && submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateAdd() && submitForm('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
			
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="ImagePointerMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			<html:hidden name="ImagePointerMasterFB" property="hmode"/>
			<html:hidden name="ImagePointerMasterFB" property="tempMode"/>
		</his:TransactionContainer>
		
		<center><b><his:status/></b></center>
	</html:form>
</body>
</html>			