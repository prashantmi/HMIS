
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="opd.OpdConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/hisglobal/js/validationCommon.js"/>
<his:javascript src="/hisglobal/js/validationCalls.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
<his:javascript src="/hisglobal/js/dateFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/icd_include_exclude_master.js"/>

<body>
	<html:form action="/master/icdIncludeExcludeMst.cnt">
		<his:TransactionContainer>
		
			<his:statusTransactionInProcess>

				<his:TitleTag name=" ICD-10 Disease Include/Exclude/Synonym Master >> ADD">
				</his:TitleTag>
				
				<his:ContentTag>
					<table width="100%" border="0" cellspacing="1" cellpadding="0">  
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="group"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="icdGroup"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="icdGroupCode" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="subGroup"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="icdSubgroup"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="icdSubgroupCode" />
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="disease"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="mainDisease"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="diseaseCode" />
								</div>
							</td>
						</tr>
						<!-- <tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="diseaseType"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="diseaseTypeName"/>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="iscommomly"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
					            	&nbsp;<logic:equal name="ICDIncludeExcludeFB" property="isCommon" value="<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_NOTCOMMON%>">
					            	<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_NOTCOMMON)] %>
					            	</logic:equal>
					            	<logic:equal name="ICDIncludeExcludeFB" property="isCommon" value="<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_COMMON%>">
					            	<%=OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_IS_COMMONLY_USED_COMMON)] %>
					            	</logic:equal>
								</div>
							</td>
						</tr> -->
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="recordType"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            <html:hidden name="ICDIncludeExcludeFB" property="recordType" />
						           	&nbsp;<logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE%>">
						            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE)] %>
						            </logic:equal>
						            <logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE%>">
						            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE)] %>
						            </logic:equal>
						            <logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM%>">
						            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM)] %>
						            </logic:equal>
								</div>
							</td>
						</tr>
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE%>">
								            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_INCLUDE)] %>
								            </logic:equal>
								            <logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE%>">
								            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_EXCLUDE)] %>
								            </logic:equal>
								            <logic:equal name="ICDIncludeExcludeFB" property="recordType" value="<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM%>">
								            	<%=OpdConfig.ICD_DISEASE_RECORD_TYPE_ARR[Integer.parseInt(OpdConfig.ICD_DISEASE_RECORD_TYPE_SYNONYM)] %>
								            </logic:equal>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="disease"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="disease" />
								</div>
							</td>
						</tr>
						<logic:notEmpty name="ICDIncludeExcludeFB" property="parentCode">
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="icdCode"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            <html:hidden name="ICDIncludeExcludeFB" property="parentCode" />
						  			
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="parentCode"/>
						  			
								</div>
							</td>
						</tr>
						</logic:notEmpty>
						<tr>
							<td width="50%" class="tdfonthead" colspan="2">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	  									<b><bean:message key="remark"/>&nbsp;</b>
	  								</font>
								</div>
							</td>
							<td width="50%" class="tdfont" colspan="2">
								<div align="left">
						            &nbsp;<bean:write name="ICDIncludeExcludeFB" property="diseaseRemark"/>
						            <html:hidden name="ICDIncludeExcludeFB" property="diseaseRemark" />
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>
			</his:statusTransactionInProcess>
			
			<his:ButtonToolBarTag>
				<his:statusTransactionInProcess>
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
	    		</his:statusTransactionInProcess>
	    		<his:statusUnsuccessfull>
		    		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="submitForm('CANCEL')" onkeypress="if(event.keyCode==13) submitForm('CANCEL')">
	    		</his:statusUnsuccessfull>
			</his:ButtonToolBarTag>
			<center><b><his:status/></b></center>
		</his:TransactionContainer>

		<html:hidden name="ICDIncludeExcludeFB" property="hmode"/>
		<html:hidden name="ICDIncludeExcludeFB" property="isActive"/>
		<html:hidden name="ICDIncludeExcludeFB" property="slNo"/>

		<html:hidden name="ICDIncludeExcludeFB" property="mainDisease"/>
		<html:hidden name="ICDIncludeExcludeFB" property="isCommon"/>
		<html:hidden name="ICDIncludeExcludeFB" property="diseaseTypeCode"/>
		<html:hidden name="ICDIncludeExcludeFB" property="diseaseTypeName"/>
	</html:form>
</body>
</html>