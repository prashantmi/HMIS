<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<script type="text/javascript">
function cancelPopUp()
{
	self.close()
}
</script>

<body>
<html:form action="/opdScheduleEnquiry">
<%@page import="enquiry.*"%>


<his:SubTitleTag name="Consultant Detail">
</his:SubTitleTag>

	<logic:present name="<%=enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO %>">
	<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
	  		<td width="40%"  class="tdfonthead">
				<div align="center">	  
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="doctor"/></b></font></div>
	  		</td>
	  		<td width="35%"  class="tdfonthead">
				<div align="center">	  
				    
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="designation"/></b></font></div>
	  		</td>
	  		
	  	</tr>
	  	
	  	<logic:iterate id="opdConsulantDetail" name="<%=enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO %>" type="hisglobal.vo.OpdEnquiryVO">
	  	<tr>
	  		<td width="40%"  class="tdfont">
				<div align="center">	  
				<b><%if(opdConsulantDetail.getEmployeeName()!=null){ %>
				<%= opdConsulantDetail.getEmployeeName()%>
				&nbsp;<logic:equal name="opdConsulantDetail" property="hgnum_is_hou" value="1">
					<bean:message key="hod"/>
				</logic:equal>
				<%} %></b>
			</div>	
	  		</td>
	  		<td width="35%"  class="tdfont">
				<div align="center">	  
					<b><bean:write name="opdConsulantDetail" property="designation"/></b>
				</div>
	  		</td>
	  		
	  	</tr>
	  	</logic:iterate>
	  	
	  </table>
	  </his:ContentTag>
	  </logic:present>

<his:ButtonToolBarTag>
	 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style=cursor:pointer tabindex="1" onclick ="cancelPopUp()" onkeypress="if(event.keyCode==13) cancelPopUp()">
</his:ButtonToolBarTag>

	<html:hidden name="opdScheduleEnquiryFB" property="departmentCode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="departmentUnitCode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="departmentUnit"/>
	<html:hidden name="opdScheduleEnquiryFB" property="department"/>
	<html:hidden name="opdScheduleEnquiryFB" property="hmode"/>
	<html:hidden name="opdScheduleEnquiryFB" property="hod"/>

</html:form>
<his:status/>
</body>
</html>