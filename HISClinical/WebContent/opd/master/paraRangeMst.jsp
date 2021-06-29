<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@page import="opd.OpdConfig"%>
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

<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function checkGender()
{

	if(document.getElementsByName("genderCode")[0].value=="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>")
 	{
 		document.getElementsByName("lowAge")[0].value="";
		document.getElementsByName("highAge")[0].value="";
 		document.getElementsByName("lowAge")[0].disabled=true;
 		document.getElementsByName("highAge")[0].disabled=true;
 	}
 	else
 	{
 		
 		document.getElementsByName("lowAge")[0].disabled=false;
 		document.getElementsByName("highAge")[0].disabled=false;
 		
 	}
		
}

function checkValue()
{	
	var low=parseFloat(document.getElementsByName('lowValue')[0].value);
	var high=parseFloat(document.getElementsByName('highValue')[0].value);
	var valid=true;
	if(document.forms[0].lowValue!="" && document.forms[0].highValue!="")
	{	
		if(low > high)
		{
			alert("Low value cannot be greater than high value");
			valid=false;
		}
	}
	return valid;
}


function checkAge()
{	
	var low=parseInt(document.getElementsByName('lowAge')[0].value);
	var high=parseInt(document.getElementsByName('highAge')[0].value);
	var valid=true;
	if(document.forms[0].lowAge!="" && document.forms[0].highAge!="")
	{	
		if(low > high){
			alert("Low Age cannot be greater than high age");
			valid=false;}
		
	}
	return valid;
	
}

function validateAdd()
{
	if(!comboValidation(document.forms[0].genderCode,"Gender"))
	{
		return ;
	}
	
	
	if(document.getElementsByName("genderCode")[0].value!="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>" &&	
		isEmpty(document.forms[0].lowAge,"Low Age")
		&& isEmpty(document.forms[0].highAge,"High Age")
		&& isEmpty(document.forms[0].lowValue,"Low Value")
		&& isEmpty(document.forms[0].highValue,"High Value")
		&& isEmpty(document.forms[0].unitOfMeasure,"Unit of Measurement")
		)
	{
		if(checkAge() && checkValue())
		{
			submitForm21('SAVE');
		}
	}
	
	if(document.getElementsByName("genderCode")[0].value=="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>"	&&
		isEmpty(document.forms[0].lowValue,"Low Value")
		&& isEmpty(document.forms[0].highValue,"High Value")
		&& isEmpty(document.forms[0].unitOfMeasure,"Unit of Measurement")
		)
	{
		if(checkValue())
		{
			submitForm21('SAVE');
		}
	}
	else
		return ;
	
}

function validateModify()
{
	if(!comboValidation(document.forms[0].genderCode,"Gender"))
	{
		return ;
	}
	
	
	if(document.getElementsByName("genderCode")[0].value!="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>" &&	
		isEmpty(document.forms[0].lowAge,"Low Age")
		&& isEmpty(document.forms[0].highAge,"High Age")
		&& isEmpty(document.forms[0].lowValue,"Low Value")
		&& isEmpty(document.forms[0].highValue,"High Value")
		&& isEmpty(document.forms[0].unitOfMeasure,"Unit of Measurement")
		)
	{
		if(checkAge() && checkValue())
		{
			submitForm21('MODIFYSAVE');
		}
	}
	
	if(document.getElementsByName("genderCode")[0].value=="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>"	&&
		isEmpty(document.forms[0].lowValue,"Low Value")
		&& isEmpty(document.forms[0].highValue,"High Value")
		&& isEmpty(document.forms[0].unitOfMeasure,"Unit of Measurement")
		)
	{
		if(checkValue())
		{
			submitForm21('MODIFYSAVE');
		}
	}
	else
		return ;
	
}

function clearAddForm()
{
	document.getElementsByName("genderCode")[0].value="-1";
	document.getElementsByName("lowAge")[0].readOnly=false;
 	document.getElementsByName("highAge")[0].readOnly=false;

	document.getElementsByName("lowAge")[0].value="";
	document.getElementsByName("highAge")[0].value="";
	document.getElementsByName("lowValue")[0].value="";
	document.getElementsByName("highValue")[0].value="";
	document.getElementsByName("unitOfMeasure")[0].value="";
	
	
}

function clearModifyForm()
{
	document.getElementsByName("genderCode")[0].value="-1";
	document.getElementsByName("lowAge")[0].readOnly=false;
 	document.getElementsByName("highAge")[0].readOnly=false;

	document.getElementsByName("lowAge")[0].value="";
	document.getElementsByName("highAge")[0].value="";
	document.getElementsByName("lowValue")[0].value="";
	document.getElementsByName("highValue")[0].value="";
	document.getElementsByName("unitOfMeasure")[0].value="";
	document.getElementsByName("isValid")[0].value="-1";
}

</script>

<title>Parameter Range Master</title>

<body  onload="checkGender()">
	<html:form action="/master/ParaRangeMaster">
		<his:TransactionContainer>
	
			<logic:equal name="ParaRangeMstFB" property="hmode" value="ADD">
				<his:TitleTag name="Parameter Range Master >> ADD">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ParaRangeMstFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Parameter Range Master >> MODIFY">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="ParaRangeMstFB" property="hmode" value="VIEW">
				<his:TitleTag name="Parameter Range Master >> VIEW">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="ParaRangeMstFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="paraName"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="paraName" />
								<html:hidden name="ParaRangeMstFB" property="paraName"/>
								<html:hidden name="ParaRangeMstFB" property="paraId"/>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="gender"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								 &nbsp;<html:select name="ParaRangeMstFB" property="genderCode"  style="width:160;" onchange="checkGender()">
								    <html:option value="-1">Select Value</html:option>
									<html:option value="<%=OpdConfig.GENDER_FLAG_FOR_BOTH%>"><%=OpdConfig.GENDER_FLAG_FOR_ARR[Integer.parseInt(OpdConfig.GENDER_FLAG_FOR_BOTH)]%></html:option>
									<html:option value="<%=OpdConfig.GENDER_FLAG_FOR_MALE%>"><%=OpdConfig.GENDER_FLAG_FOR_ARR[Integer.parseInt(OpdConfig.GENDER_FLAG_FOR_MALE)]%></html:option>
									<html:option value="<%=OpdConfig.GENDER_FLAG_FOR_FEMALE%>"><%=OpdConfig.GENDER_FLAG_FOR_ARR[Integer.parseInt(OpdConfig.GENDER_FLAG_FOR_FEMALE)]%></html:option>
									<html:option value="<%=OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED%>"><%=OpdConfig.GENDER_FLAG_FOR_ARR[Integer.parseInt(OpdConfig.GENDER_FLAG_FOR_NOT_REQUIRED)]%></html:option>
								 </html:select>
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="lowAge"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<html:text name="ParaRangeMstFB" property="lowAge" maxlength="2" size="5" onkeypress="return validateNumeric(event)" >
								</html:text>
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="highAge"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<html:text name="ParaRangeMstFB" property="highAge" maxlength="2" size="5"  onkeypress="return validateNumeric(event)"  >
								</html:text>
							</div>
						</td>
					</tr>
					
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="lowValue"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<html:text name="ParaRangeMstFB" property="lowValue" maxlength="10" size="20" onkeypress="return validateNumericOnly(this,event)"  >
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="highValue"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<html:text name="ParaRangeMstFB" property="highValue" maxlength="10" size="20"  onkeypress="return validateNumericOnly(this,event)"  >
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*</font>
	  								<b><bean:message key="uom"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<html:text name="ParaRangeMstFB" property="unitOfMeasure" maxlength="20" size="20" onkeypress="return validateAlphaNumericWithSpecialCharacterOnly(event)"  >
								</html:text>
							</div>
						</td>
					</tr>
				
		  			<logic:equal name="ParaRangeMstFB" property="hmode" value="MODIFY">
					<tr>
			        	<td width="50%" class="tdfonthead">
			         		<div align="right">
					   			<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif">*</font> 
				       			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
									<b><bean:message key="isActive"/>&nbsp;</b>
					   			</font>
				     		</div>
			        	</td>
			        	<td width="50%" class="tdfont">
			         		<div align="left">
				      			&nbsp;<html:select name="ParaRangeMstFB" property="isValid"  style="width:160;">
						    		<html:option value="-1">Select Value</html:option>
									<html:option value="1">Active</html:option>
									<html:option value="2">InActive</html:option>
						 		</html:select>
				     		</div>
			       		</td>  
			     	</tr>
			   		</logic:equal>
				</table>	
			</his:ContentTag>
		</logic:notEqual>
		<logic:equal name="ParaRangeMstFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">									
	  								<b><bean:message key="paraName"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="paraName" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">									
	  								<b><bean:message key="gender"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<bean:define id="gender" name="ParaRangeMstFB" property="genderCode" type="java.lang.String"></bean:define>
						<td width="50%" class="tdfont">
							<div align="left">
								 &nbsp;<%=OpdConfig.GENDER_FLAG_FOR_ARR[Integer.parseInt(gender)]%>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b><bean:message key="lowAge"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="lowAge" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b><bean:message key="highAge"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="highAge" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b><bean:message key="lowValue"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="lowValue" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b><bean:message key="highValue"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="highValue" />
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  								<b><bean:message key="uom"/>&nbsp;</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								&nbsp;<bean:write name="ParaRangeMstFB" property="unitOfMeasure" />
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
			        	<bean:define id="isActice" name="ParaRangeMstFB" property="isValid" type="java.lang.String"></bean:define>
			        	<td width="50%" class="tdfont">
			         		<div align="left">
				      			&nbsp;<%=Config.IS_VALID_ARR[Integer.parseInt(isActice)]%>
				     		</div>
			       		</td>  
			     	</tr>
				</table>		
			</his:ContentTag>
			</logic:equal>
			<his:ButtonToolBarTag>
			<logic:equal name="ParaRangeMstFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick="validateAdd()" onkeypress="if(event.keyCode==13)validateAdd()">
			</logic:equal>
			
			<logic:equal name="ParaRangeMstFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style="cursor:pointer" tabindex="1" onclick="validateModify() " onkeypress="if(event.keyCode==13)validateModify()">
			</logic:equal>
			
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
			
			<logic:equal name="ParaRangeMstFB" property="hmode" value="ADD">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick=" clearAddForm()" onkeypress="if(event.keyCode==13)  clearAddForm();">
			</logic:equal>
			<logic:equal name="ParaRangeMstFB" property="hmode" value="MODIFY">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style="cursor:pointer" tabindex="1" onclick=" clearModifyForm()" onkeypress="if(event.keyCode==13)  clearModifyForm();">
			</logic:equal>
				
			</his:ButtonToolBarTag>
			
			<html:hidden name="ParaRangeMstFB" property="paraRangeId"/>
			<html:hidden name="ParaRangeMstFB" property="hmode"/>
			
						
		</his:TransactionContainer>
		
	<center><b><his:status/></b></center>	
	</html:form>
	
	
	
</body>
</html>			