
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>



<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:javascript src="/opd/opdJs/opdAjax.js"/>
<his:javascript src="/opd/opdJs/opd.js"/>

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
<html:form action="/opdEnquiry">
<%@page import="enquiry.*"%>


<his:SubTitleTag name="Opd Consulant Details">
</his:SubTitleTag>

<his:ContentTag>
	
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
			<td width="17%"  class="tdfonthead">
			<div align="right">	  
			<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<b><bean:message key="hou"/></b></font></div>
	  		</td>
	  		<td class="tdfont">
	  			<logic:notEqual name="opdEnquiryFB" property="headOfUnit" value="null">
	  			&nbsp;<bean:write name="opdEnquiryFB" property="headOfUnit"/>
	  			</logic:notEqual>
	  		</td>
		
			<td width="17%"  class="tdfonthead">
				<div align="right">	  
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="department"/></b></font></div>
	  		</td>
	  		<td class="tdfont">
	  		&nbsp;<bean:write name="opdEnquiryFB" property="deptName"/>
	  		</td>
	  		
	  		<td width="17%"  class="tdfonthead">
				<div align="right">	  
				    
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="unit"/></b></font></div>
	  		</td>
	  		<td class="tdfont">
	  		&nbsp;<bean:write name="opdEnquiryFB" property="unitName"/>
	  		</td>
	  	</tr>
	  	
	  	
	  </table>
	  
</his:ContentTag>
<his:ContentTag>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0">
		<tr>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	  
				    
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="doctor"/></b></font></div>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	  
				    
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<b><bean:message key="timing"/></b></font></div>
	  		</td>
	  	</tr>
	  	<logic:present name="<%=enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO %>"></logic:present>
	  	<logic:iterate id="opdConsulantDetail" name="<%=enquiryConfig.ALL_CONSULTANT_DETAILS_OPD_ENQUIRY_VO %>" type="hisglobal.vo.OpdEnquiryVO">
	    <%-- Start:Sheeldarshi:10-Nov-2014:Enquiry changes 
	  	<logic:equal name="opdConsulantDetail" property="hgnum_is_hou" value="0"> --%>
	  	<logic:equal name="opdConsulantDetail" property="hou" value="No">
	  	<%-- End:Sheeldarshi:10-Nov-2014:Enquiry changes--%>
	  	<tr>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	  
				<%if(opdConsulantDetail.getEmployeeName()!=null){ %>
				<%= opdConsulantDetail.getEmployeeName()%>    
				<%} %>
	  		</td>
	  		<td width="20%"  class="tdfonthead">
				<div align="center">	  
					<bean:write name="opdEnquiryFB" property="timing"/>    
				</div>
	  		</td>
	  	</tr>
	  	</logic:equal>
	  	</logic:iterate>
	  </table>
</his:ContentTag>
<his:ButtonToolBarTag>
	 <img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="cancelPopUp()" onkeypress="if(event.keyCode==13) cancelPopUp()">
</his:ButtonToolBarTag>

</html:form>
<his:status/>
</body>





</html>