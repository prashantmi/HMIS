<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@ page import="hisglobal.utility.generictemplate.GenericTemplateConfig"%>
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
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery-ui.custom.min.js">
<link href="/HIS/hisglobal/snomedct/css/searchtool.css" rel="stylesheet">
<link rel="icon" href="/HIS/hisglobal/snomedct/css/images/csnotk.ico">
<!-- <link rel="stylesheet" href="/HIS/hisglobal/css/jquery-ui.css"> -->
<script type="text/javascript" src="/HIS/hisglobal/snomedct/ext/js/jquery.js"></script>
<link rel="stylesheet" href="/HIS/hisglobal/snomedct/ext/js/jquery.autocomplete.js">
<script type="text/javascript" src="/HIS/hisglobal/snomedct/js/searchtoolnewautocomplete.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-1.10.2.js"></script>
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script type="text/javascript">

function selectValue(value, callback) 
{
	
	var data = unescape(value);

	var term = data.split('###$$$');

	var returnterm_OUT = term[1] +","+ term[0].replace("'", "");

	if (typeof callback === "function")
		callback(returnterm_OUT);

	$("#dialog-form").dialog("close");

}



</script>
<title>Intake Output Para Master</title>

<body>
	<html:form action="/master/intakeOutputParaMstACT">
		<his:TransactionContainer>
	
		
				<his:TitleTag name="Intake Output Para Master >> VIEW">
				</his:TitleTag>
		
			
			<his:ContentTag>
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="paraName"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<html:text name="IntakeOutputParaMasterFB" property="paraName" size="40" readonly="true">
							</html:text>
						</td>
					</tr>
					
					<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="snomedCtConceptId"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
						
						
					<html:text readonly="true" name="IntakeOutputParaMasterFB" property="prefferedTerm" size="40"></html:text>
						
						</td>
					</tr>
					
					<tr>
						<td width="25%"  class="tdfonthead" >
							<div align="right">
								<font color="#000000"  size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<font color="#ff0000">*</font>
									<b><bean:message key="paraType"/></b>
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
						
						
					<html:text readonly="true" name="IntakeOutputParaMasterFB" property="paraType" ></html:text>
						
						</td>
					</tr>
					
					<tr>
						<td class="tdfonthead"  width="25%" align="right">
							<div align="right">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:message key="isActive"/></b> 
								</font>
							</div>
						</td>
						<td width="25%"  class="tdfont">
							<logic:equal name="IntakeOutputParaMasterFB"  property="isActive" value="<%=Config.IS_VALID_ACTIVE %>">
								<html:text name="IntakeOutputParaMasterFB" property="isActive" size="30" value="Active" readonly="true"/>
							</logic:equal>
							<logic:equal name="IntakeOutputParaMasterFB"  property="isActive" value="<%=Config.IS_VALID_INACTIVE %>">
								<html:text name="IntakeOutputParaMasterFB" property="isActive" size="30" value="In Active" readonly="true"/>
							</logic:equal>
						</td>
					</tr>
				</table>
				</his:ContentTag>
			<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" onclick="submitForm21('CANCEL')" onkeypress="if(event.keyCode==13) submitForm21('CANCEL')">
				</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
			
			
			<html:hidden name="IntakeOutputParaMasterFB" property="hmode"/>
			
						
		</his:TransactionContainer>
		<html:hidden name="IntakeOutputParaMasterFB" property="inTakeOutParaId"/>
		<html:hidden name="IntakeOutputParaMasterFB" property="slNo"/>
		
	</html:form>
</body>
</html>			