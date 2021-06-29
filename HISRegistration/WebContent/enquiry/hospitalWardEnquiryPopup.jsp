<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/popup.js" />

<body>
<his:TransactionContainer>
<html:form action="/hospitalWardEnquiry">

<his:TitleTag name="Ward Detail">
	</his:TitleTag>
	<his:ContentTag>
	<bean:define id="wardDetailVoList" name="<%=enquiryConfig.ENQUIRY_WARD_DETAIL_VO_VIEW %>" type="java.util.List"></bean:define>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="ward" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><%=((HospitalWardEnquiryVO)wardDetailVoList.get(0)).getWard() %></b>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="department" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><%=((HospitalWardEnquiryVO)wardDetailVoList.get(0)).getDepartment() %></b>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="unit" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><%=((HospitalWardEnquiryVO)wardDetailVoList.get(0)).getDepartmentUnit() %></b>
		</td>
	</tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="room" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><%=((HospitalWardEnquiryVO)wardDetailVoList.get(0)).getRoom() %></b>
		</td>
	</tr>
	</table>
	<his:SubTitleTag name="Bed Detail">
	</his:SubTitleTag>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td width="25%"  class="tdfonthead">
		<div align="center">
			<b><bean:message key="bedNo" /></b>
			</div>
		</td>
		<td width="25%"  class="tdfonthead">
			<div align="center">
			<b><bean:message key="bedStatus" /></b>
			</div>
		</td>
		
	</tr>
	<logic:iterate id="wardEnquiryVO" name="wardDetailVoList" type="enquiry.vo.HospitalWardEnquiryVO">
	<tr>
		<td class="tdfont">
		<div align="center">
		<bean:write name="wardEnquiryVO" property="bed" />
		</div>
		</td>
		<td class="tdfont">
		<div align="center">
		 <bean:write name="wardEnquiryVO" property="bedStatus" />
		</div>
		</td>
	 </tr>
	</logic:iterate>
	</table>
	</his:ContentTag>
	
	<html:hidden name="hospitalWardEnquiryFB" property="hmode"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationBlock"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationFloor"/>
	<html:hidden name="hospitalWardEnquiryFB" property="locationBuilding"/>
	<html:hidden name="hospitalWardEnquiryFB" property="wardCode"/>
	<html:hidden name="hospitalWardEnquiryFB" property="ward"/>
</html:form>
<his:ButtonToolBarTag>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitForm('NEW');" tabindex="1" onclick="self.close();">
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>