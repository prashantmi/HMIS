
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="registration.*"%>

<%@page import="enquiry.enquiryConfig"%>


<%@page import="enquiry.transaction.controller.fb.GuidlinesEnquiryFB"%>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />


<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />


<script>
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}


function getTemplateId(mode)
{
	submitPage(mode);
}
</script>
<html:form action="/guidlinesEnquiry">
<his:TransactionContainer>
<his:TitleTag name="GUIDLINES ENQUIRY">
</his:TitleTag>
<his:ContentTag>
	<table width="100%" cellspacing="1" cellpadding="0" >
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="right">	           
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>
						<bean:message key="tmplname"/>
					</b>	
					</font>
				</div>
  			</td> 
   		 	<td width="25%" class="tdfont" >
				<div align="left" >
					<html:select name="GuidlinesEnquiryFB" property="templateId" onchange="getTemplateId('GETTEMPLATEID')"> 
						<html:option value="-1">Select Value</html:option>
							<logic:present name="<%=enquiryConfig.TEMPLATE_LIST_FOR_GUIDLINE%>">
								<html:options collection="<%=enquiryConfig.TEMPLATE_LIST_FOR_GUIDLINE%>" property="value" labelProperty="label" />
							</logic:present>
					</html:select>
				</div>
			</td>
		</tr>	
</table>
<logic:present name="<%=enquiryConfig.SELECTED_TEMPLATEID_LIST%>">
	<table width="100%" cellspacing="1" cellpadding="0" >
		<logic:iterate id="templateId" indexId="idx" name="<%=enquiryConfig.SELECTED_TEMPLATEID_LIST%>" type="java.lang.String">
				<tr>
					<td width="100%">
						<his:GenericTemplateTag templateId="<%=templateId %>"></his:GenericTemplateTag>
					</td>
				</tr>	
		</logic:iterate>
	</table>			
</logic:present>
</his:ContentTag>			
			
<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitPage('CANCEL')" onkeypress="if(event.keyCode==13) submitPage('CANCEL')">
    <img class="button" src='<his:path src="/hisglobal/images/btn-clr.png"/>'  style=cursor:pointer tabindex="1" onclick ="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
</his:ButtonToolBarTag>
<html:hidden name="GuidlinesEnquiryFB" property="hmode"/>

</his:TransactionContainer>
</html:form>