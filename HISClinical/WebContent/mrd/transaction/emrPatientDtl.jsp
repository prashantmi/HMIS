<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="ehr.EHRConfig"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

var ineerPatEHRObj=null;
function setPatientEHRObj()
{
	ineerPatEHRObj= JSON.parse('<%=session.getAttribute(EHRConfig.EHR_JSON_OBJECT_FIELD_NAME_PATIENT_DATA)%>');
}

window.onload=function()
{
	setPatientEHRObj();
	parent.patEHRObj= ineerPatEHRObj;
	//alert("head:"+ineerPatEHRObj + "fgfg"+parent.patEHRObj);
	
}
</script>
</head>

<html:form action="/emrDesk">
<his:TitleTag name="EMR Desk" >
		<%-- 
		<td>
			<div align="center">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="EmrCommonDeskFB" property="patFirstName" />
				<bean:write name="EmrCommonDeskFB" property="patMiddleName" />
				<bean:write name="EmrCommonDeskFB" property="patLastName" />, 
				<bean:write	name="EmrCommonDeskFB" property="patAge" /> 
				<bean:write	name="EmrCommonDeskFB" property="patAgeUnit" />/
				<bean:write name="EmrCommonDeskFB" property="patGender" /> 
				&nbsp;(<bean:write name="EmrCommonDeskFB" property="patCrNo" />)
				</font>
				</b>
			</div>	
		</td>
		--%>
</his:TitleTag>
		<%-- <table width="100%" height="20%" >
			<tr>
				<td class="tdfonthead" width="18%">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"><bean:message
					key="name" /></font></div>
				</td>
				<td width="18%" class="tdfont">
				<b>
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write	name="EmrCommonDeskFB" property="patFirstName" />
				<bean:write name="EmrCommonDeskFB" property="patMiddleName" />
				<bean:write name="EmrCommonDeskFB" property="patLastName" />
				</font>
				</b>
				</td>
					<td width="18%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="age" />
				<bean:message key="slash" />
				<bean:message key="gender" />
				</font></div>
				</td>
				
				<td width="18%" nowrap class="tdfont"><b><font color="#000000"
					size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:write
					name="EmrCommonDeskFB" property="patAge" /> <bean:write
					name="EmrCommonDeskFB" property="patAgeUnit" /><logic:notEqual
					name="EmrCommonDeskFB" property="patGender" value="">/</logic:notEqual>
				<bean:write name="EmrCommonDeskFB" property="patGender" /></font></b></td>
				<td width="18%" class="tdfonthead">
				<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="crNo" />
				</font>
				</div>
				</td>
				
				
			<td class="tdfont" width="18%" >
				<div align="left">
				<b><bean:write name="EmrCommonDeskFB" property="patCrNo" />
						
				</b>
				</div>
				</td>
			
			</tr>
		
				
			</table>--%>
			
<html:hidden name="EmrCommonDeskFB" property="patCrNo" />
<html:hidden name="EmrCommonDeskFB" property="hmode" />
</html:form>