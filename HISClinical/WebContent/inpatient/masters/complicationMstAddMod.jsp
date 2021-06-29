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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">


function validateAdd()
{
	var valid=true;
	if(isEmpty(document.forms[0].complication,"Complication")&&comboValidation(document.forms[0].complicationTypeId,"Complication Type"))
	{
		valid=true;
	}
	else
	{
		valid=false;
	}
	return valid;
}

function validateModify()
{
	var valid=true;
	if(isEmpty(document.forms[0].complication,"Complication")
		&& comboValidation(document.forms[0].isValid,"Is Active"))
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
	document.getElementsByName("complication")[0].value="";
	document.getElementsByName("complicationTypeId")[0].value="-1";	
}

function clearModifyForm()
{
	document.getElementsByName("complication")[0].value="";
	document.getElementsByName("complicationTypeId")[0].value="-1";
	document.getElementsByName("isValid")[0].value="-1";
}

</script>
<title>Complication Master</title>

<body>
	<html:form action="/master/ComplicationMaster">
		<his:TransactionContainer>
	
			<logic:equal name="ComplicationMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Complication Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ComplicationMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Complication Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ComplicationMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Complication Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="ComplicationMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="complicationType"/>
	  								</b>
								</font>
							</div>
						</td>
					 <td width="50%" class="tdfont">
			         <div align="left">
				   <bean:write name="ComplicationMasterFB" property="complicationTypeName" />
				     </div>
			       </td>  
					</tr>
				<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
	  								<b>
	  									<bean:message key="complications"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
						<html:text name="ComplicationMasterFB" property="complication" maxlength="50" size="30"   >
								</html:text>
								<html:hidden name="ComplicationMasterFB" property="slNo"/>
								<html:hidden name="ComplicationMasterFB" property="complicationId"/>
							</div>
						</td>
					</tr>
				
				
				 <logic:notEqual name="ComplicationMasterFB" property="hmode" value="ADD">
			 <logic:notEqual name="ComplicationMasterFB" property="hmode" value="SAVE">
			 <logic:notEqual name="ComplicationMasterFB" property="hmode" value="VIEW">
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
				     &nbsp;<html:select name="ComplicationMasterFB" property="isValid"  style="width:160;">
						    <html:option value="-1">Select Value</html:option>
							<html:option value="1">Active</html:option>
							<html:option value="2">InActive</html:option>
						 </html:select>
				     </div>
			       </td>  
			     </tr>
			   </logic:notEqual>
			   </logic:notEqual>
			   </logic:notEqual> 	
				</table>	
			</his:ContentTag>
		</logic:notEqual>
				<logic:equal name="ComplicationMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="complicationType"/>
	  								</b>
								</font>
							</div>
						</td>
					 <td width="50%" class="tdfont">
			         <div align="left">
				 <bean:write name="ComplicationMasterFB" property="complicationTypeName" />
				     </div>
			       </td>  
					</tr>
				<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
	  								<b>
	  									<bean:message key="complications"/>
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
						<html:text name="ComplicationMasterFB" property="complication" maxlength="50" size="30" disabled="true"  >
								</html:text>
								<html:hidden name="ComplicationMasterFB" property="slNo"/>
								<html:hidden name="ComplicationMasterFB" property="complicationId"/>
							</div>
						</td>
					</tr>
				
				</table>		
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="ComplicationMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="if(validateAdd()) submitForm21('SAVE')" onkeypress="if(event.keyCode==13)if(validateAdd()) submitForm21('SAVE')">
			</logic:equal>
			
			<logic:equal name="ComplicationMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateModify() && submitForm21('MODIFYSAVE')" onkeypress="if(event.keyCode==13)validateModify() && submitForm21('MODIFYSAVE')">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			
			<logic:equal name="ComplicationMasterFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="ComplicationMasterFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" clearModifyForm()" onkeypress="if(event.keyCode==13)  clearModifyForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="ComplicationMasterFB" property="hmode"/>
			<html:hidden name="ComplicationMasterFB" property="tempMode"/>
			<html:hidden name="ComplicationMasterFB" property="complicationTypeId"/>
			<html:hidden name="ComplicationMasterFB" property="complicationTypeName"/>
			
						
		</his:TransactionContainer>
		
		<his:status/>
	</html:form>
</body>
</html>			