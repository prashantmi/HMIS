
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
	if(document.getElementsByName("drugListName")[0].value=="")
	{
		alert("Please Enter Drug List Name");
		document.getElementsByName("drugListName")[0].focus();
		return false;
	}
	
	
	submitForm21(mode);
}

</script>
<title>Keyword Master</title>

<body>
	<html:form action="/master/drugListMaster">
		<his:TransactionContainer>
			<logic:equal name="DrugListMasterFB" property="hmode" value="ADD">
				<his:TitleTag name="Drug List Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="DrugListMasterFB" property="hmode" value="MODIFY">
				<his:TitleTag name="Drug List Master">
				</his:TitleTag>
			</logic:equal>
			<logic:equal name="DrugListMasterFB" property="hmode" value="VIEW">
				<his:TitleTag name="Drug List Master">
				</his:TitleTag>
			</logic:equal>
			<logic:notEqual name="DrugListMasterFB" property="hmode" value="VIEW">
			<his:ContentTag>
				<table width="100%" border="0" cellspacing="1" cellpadding="0">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#FF0000">*
	  								</font>
	  								<b>
	  									<bean:message key="drugListName"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="DrugListMasterFB" property="drugListName" maxlength="50" size="30" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="diseaseCode"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:text name="DrugListMasterFB" property="diseaseCode" maxlength="100" size="30" onkeypress="return validateAlphaNumericWithDotsOnly(event,this)">
								</html:text>
							</div>
						</td>
					</tr>
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
	  									<bean:message key="remark"/>
	  									
	  								</b>
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<html:textarea name="DrugListMasterFB" tabindex="1" rows="3" cols="33" property="remark" onkeypress="return (validateTextArea(event,this,'500'))">
								</html:textarea>
							</div>
						</td>
					</tr>
							 	
				</table>	
			</his:ContentTag>
			</logic:notEqual>
			
			
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer tabindex="1" onclick="validateAddForm('SAVE')" onkeypress="if(event.keyCode==13) validateAddForm('SAVE')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick=" submitForm21('ADD')" onkeypress="if(event.keyCode==13)  submitForm21('ADD');">
			</his:ButtonToolBarTag>
			
			
			<html:hidden name="DrugListMasterFB" property="hmode"/>
			
		</his:TransactionContainer>
		<div align="center">
			<center><b><his:status/></b></center>
		</div>
		
	</html:form>
</body>
</html>			