<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@ page import ="hisglobal.hisconfig.Config" %>	

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script>

function submitForm(mode)
{	
	document.getElementsByName('hmode')[0].value=mode;
	document.forms[0].submit();
}

function validate()
{
	if(document.getElementsByName("reason")[0].value=="")
	{
		alert("Please Enter Reason");
		document.getElementsByName("reason")[0].focus();
		return false;
	}
	return true;
}

function clearTextFields()
{
	document.getElementsByName("reason")[0].value="";
	document.getElementsByName("reason")[0].focus();
}	
</script>

<body>
	<html:form action="/master/attendantReasonMaster.cnt">
		<his:TransactionContainer>
		
		<!-- Start ADD Page here -->
			<logic:equal name="AttendantReasonFB" property="hmode" value="ADD">
				<his:TitleTag name=" Attendant Reason Master >> Add">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*</font>
	  									<b><bean:message key="attendant"/> <bean:message key="reason"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
						            &nbsp;<html:textarea name="AttendantReasonFB" property="attendantReason" tabindex="1"  onkeypress="return CheckMaxLength(event,this,100,3)" 
            						style="width: 180px;font-family: Verdana, Arial, Helvetica, sans-serif;font-size: 12;height:40px;color: #000000;"/>
								</div>
							</td>
						</tr>
					</table>	
				</his:ContentTag>
				<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer  onclick ="submitFormOnValidate(validate(),'SAVE')" onkeypress="if(event.keyCode==13) submitFormOnValidate(validate(),'SAVE')">
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer  onclick ="clearTextFields();" onkeypress="if(event.keyCode==13) clearTextFields();">          	
				</his:ButtonToolBarTag>
			</logic:equal>
		<%--ADD Page ends here --%>


		<%--Modify Page begins here--%>
			<logic:equal name="AttendantReasonFB" property="hmode" value="MODIFY">
				<his:TitleTag name=" Reason Type Master >> MODIFY">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="reason"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="AttendantReasonFB"  property="reason"></html:text>
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
	  										<bean:message key="isvalid"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="40%"  class="tdfont">
							<div align="left">
								
								<html:select name="AttendantReasonFB" property="isvalid" >
						  			<html:option value="-1">Select Value</html:option>
									<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
									<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option>
					   			</html:select>
								
								
								</div>
							</td>
						</tr>
						
			</table>	
			
			
		</his:ContentTag>
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer  onclick ="submitForm('MODIFYSAVE')" onkeypress="if(event.keyCode==13) submitForm('MODIFYSAVE')">
    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
    		          	
		</his:ButtonToolBarTag>
		
		</logic:equal>
		
		<%--Modify Page ends here --%>
		
		
		
		
		
		
		
		
		
		
		
		<%--VIEW Page begins here--%>
		
		
		
		<logic:equal name="AttendantReasonFB" property="hmode" value="VIEW">
					
				<his:TitleTag name=" Reason Type Master >> VIEW">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<font color="#FF0000">*
	  									</font>
	  									<b>
	  										<bean:message key="reason"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont">
								<div align="left">
									&nbsp;<html:text name="AttendantReasonFB"  property="reason" disabled="true"></html:text>
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
	  										<bean:message key="isvalid"/>
	  									</b>
	  								</font>
								</div>
							</td>
							<td width="40%"  class="tdfont">
							<div align="left">
								
								&nbsp;<html:select name="AttendantReasonFB" property="isvalid" disabled="true">
						  			<html:option value="-1">Select Value</html:option>
									<html:option value="<%=Config.IS_VALID_ACTIVE %>">Active</html:option>
									<html:option value="<%=Config.IS_VALID_INACTIVE %>">InActive</html:option>
					   			</html:select>
								
								
								</div>
							</td>
						</tr>
				</table>	
			
			</his:ContentTag>
		
		<his:ButtonToolBarTag>
		
    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
    		          	
		</his:ButtonToolBarTag>
		
		</logic:equal>
		
		
		
		
		
		<%--VIEW Page ends here --%>
		
		
		<his:status/>
		
	  </his:TransactionContainer>
	  <html:hidden property="hmode"  name="AttendantReasonFB"/>
	  <html:hidden name="AttendantReasonFB" property="reasonid"/>
	  <html:hidden name="AttendantReasonFB" property="slno"/>
   </html:form>
  
</html> 