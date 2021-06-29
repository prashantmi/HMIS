<!--
							Author: CDAC NOIDA
							Developer: Pawan kumar B N
							Created on : 03-Jul-2012
							JSP: MRD Check List Master Modify

-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

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

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>

<script type="text/javascript">

function validateAddForm(mode)
{
	if(document.getElementsByName("checkList")[0].value=="")
	{
		alert("Please Enter Check List");
		document.getElementsByName("checkList")[0].focus();
		return false;
	}
	
	if(document.getElementsByName("isValid")[0].value=="-1")
	{
		alert("Please Select Is Active Status");
		document.getElementsByName("isValid")[0].focus();
		return false;
	}
	
	
	submitForm21(mode);
}

function clearForm()
{
	document.getElementsByName("checkList")[0].value="";
	document.getElementsByName("isValid")[0].value="-1";
}

</script>
<title>Check List Master</title>

<body>
	<html:form action="/master/mrdCheckListMater">
		<his:TransactionContainer>
			<logic:equal name="MrdCheckListMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Check List Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MrdCheckListMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Check List Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="MrdCheckListMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Check List Master">
				</his:TitleTag>
			</logic:equal>
			
			<his:ContentTag>
				<logic:notEqual name="MrdCheckListMasterFB" property="hmode" value="VIEW">
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="checklist"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MrdCheckListMasterFB" property="checkList" maxlength="50" size="30" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</html:text>
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
									<b><bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="MrdCheckListMasterFB" property="isValid"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
					 </html:select>
				     </div>
			       </td>  
			     </tr>
					
					
			 	
				</table>	
				</logic:notEqual>
				
				<logic:equal name="MrdCheckListMasterFB" property="hmode" value="VIEW">
					<table width="100%" border="0" cellspacing="1" cellpadding="0">
				
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
	  								<b>
	  									<bean:message key="checklist"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="MrdCheckListMasterFB" property="checkList" maxlength="50" size="30" disabled="true" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</html:text>
							</div>
						</td>
					</tr>
					
					
					<tr>
			        <td width="50%" class="tdfonthead">
			         <div align="right">
					  
				       <font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<b><bean:message key="isActive"/>&nbsp;</b>
					   </font>
				     </div>
			        </td>
			        <td width="50%" class="tdfont">
			         <div align="left">
				     <html:select name="MrdCheckListMasterFB" property="isValid"  style="width:160;" disabled="true">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
					 </html:select>
				     </div>
			       </td>  
			     </tr>
					
					
			 	
				</table>
				</logic:equal>
			</his:ContentTag>
		
			
			
			<his:ButtonToolBarTag>
			<logic:notEqual value="VIEW" property="hmode" name="MrdCheckListMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAddForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13) validateAddForm('MODIFYSAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearForm()" onkeypress="if(event.keyCode==13)  clearForm();">
			</logic:notEqual>
			<logic:equal value="VIEW" property="hmode" name="MrdCheckListMasterFB">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			</logic:equal>
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="MrdCheckListMasterFB" property="hmode"/>
			<html:hidden name="MrdCheckListMasterFB" property="checkListId"/>
			<html:hidden name="MrdCheckListMasterFB" property="slNo"/>
			
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			