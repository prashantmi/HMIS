<%@page import="enquiry.enquiryConfig"%>
<%@page import="enquiry.vo.*"%>
<%@page import="hisglobal.hisconfig.*"%>
<%@page import="enquiry.transaction.controller.fb.*"%>
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
<script>
function submitPage(hmode){

	document.getElementsByName('hmode')[0].value=hmode
	document.forms[0].submit();
}

function getHolidayList(obj){
	document.getElementsByName('hmode')[0].value='GETHOLIDAYLIST'
	document.getElementsByName('year')[0].value=obj.value
	document.forms[0].submit();
}

</script>

<body>
<his:TransactionContainer>
<logic:equal name="holidayEnquiryFB" property="isDirectCall" value="DIRECT">
<form action="<his:path src='/enquiry/holidayEnquiry.cnt' />" method="post">
</logic:equal>

<his:TitleTag name="Holiday Enquiry">
</his:TitleTag>
<table width="100%" cellspacing="1" cellpadding="0">	
	<tr>
		<td width="80%" class="tdfonthead">
			<bean:message key="year"/>
		</td>
		<td width="20%" class="tdfont">
			<html:select name="holidayEnquiryFB" property="year"  onchange="if(this.value!='-1') getHolidayList(this)">
			<html:option value="-1">Select Value</html:option>
			<logic:present name="<%=enquiryConfig.HOLIDAY_YEAR_LIST %>">
			<html:options collection="<%=enquiryConfig.HOLIDAY_YEAR_LIST %>" property="value" labelProperty="label"/>
			</logic:present>
			</html:select>
		</td>
	</tr>
	</table>

	<his:SubTitleTag name="">
	<div align="left">Holiday List</div>
	</his:SubTitleTag>	
	<his:statusList>
	<logic:present name="<%=enquiryConfig.HOLIDAY_LIST %>">
	<his:ContentTag>
	<bean:define id="holidayList" name="<%=enquiryConfig.HOLIDAY_LIST %>" type="java.util.List"> </bean:define>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td width="20%" class="tdfonthead">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
				<bean:message key="date"/>
				</font>
			</div>
		</td>
		<td width="20%" class="tdfonthead">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="day"/>
				</font>
			</div>
		</td>
		<td width="40%" class="tdfonthead">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="holidayName"/>
				</font>
			</div>
		</td>
		<td width="20%" class="tdfonthead">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:message key="holidayType"/>
				</font>
			</div>
		</td>
	</tr>
	<logic:iterate id="holidayEnqVO" name="holidayList" type="enquiry.vo.HospitalHolidayEnquiryVO"  indexId="index">
		<tr>
			<td width="20%" class="tdfont">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="holidayEnqVO" property="holidayDate"/>
				</font>
			</div>
			 </td>
			<td width="20%" class="tdfont">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="holidayEnqVO" property="day"/>
				</font>
			</div>
			 </td>
			<td width="40%" class="tdfont">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="holidayEnqVO" property="holidayName"/>
				</font>
			</div>
			 </td>
			<td width="20%" class="tdfont">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				<bean:write name="holidayEnqVO" property="holidayType"/>
				</font>
			</div>
			 </td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</logic:present>
	</his:statusList>
	
	<html:hidden name="holidayEnquiryFB" property="hmode"/>
	<html:hidden name="holidayEnquiryFB" property="year"/>
	<html:hidden name="holidayEnquiryFB" property="isDirectCall"/>
	
<logic:equal name="holidayEnquiryFB" property="isDirectCall" value="DIRECT">
</form>
</logic:equal>

<his:ButtonToolBarTag>
	<logic:equal name="holidayEnquiryFB"  property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');"/>
	</logic:equal>
	<logic:notEqual name="holidayEnquiryFB"  property="isDirectCall" value="DIRECT">
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1" onclick="submitDesk('NEW');"/>
	</logic:notEqual>
<!--	<logic:notEqual name="holidayEnquiryFB" property="isDirectCall" value="DIRECT">-->
<!--	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitDesk('CANCEL');" tabindex="1" onclick="submitDesk('CANCEL');"/>-->
<!--	</logic:notEqual>-->
</his:ButtonToolBarTag>

<his:status/>
</his:TransactionContainer>
</body>
</html>