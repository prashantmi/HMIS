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
<script>

function submitPage(hmode)
{

 document.getElementsByName('hmode')[0].value=hmode
 document.forms[0].submit();
}
function getOTDetail(hmode,theaterCode)
{
 document.getElementsByName('hmode')[0].value=hmode
 document.getElementsByName('theaterCode')[0].value=theaterCode
 document.forms[0].submit();
}

</script>

<body>
<his:TransactionContainer>
<html:form action="/operationTheaterEnquiry">

<his:TitleTag name="Operation Theater Enquiry">
	</his:TitleTag>
	<his:statusList>
	
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<bean:define id="allOperationTypeMap" name="<%=enquiryConfig.OPERATION_TYPE_MAP%>" type="java.util.LinkedHashMap"></bean:define>
		<% int tdWidth=allOperationTypeMap.size();
		tdWidth=100/tdWidth;
		String oddFontColor="background-Color:#F1ECE2; text-align:center; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		String evenFontColor="background-Color:#FFEBD5; text-align:center; text-transform: capitalize; height:15; font-size: 10px; font-weight: bold;";
		%>
		<logic:iterate indexId="idx" id="operationType" name="allOperationTypeMap" type="java.util.Map.Entry">
		<%int indexValue=idx.intValue(); 
		String fontColor=oddFontColor;
		//if(indexValue%2==0)
			//fontColor=evenFontColor;
		%>
		
		<td style=position: valign="top"  width="<%=tdWidth %>%" >
		<bean:define id="opertaionTypeMapKey" name="operationType" property="key" type="java.lang.String"></bean:define>
		<bean:define id="operationTheaterList" name="operationType" property="value" type="java.util.List"></bean:define>
			<his:SubTitleTag name=""><div align="center"><%=opertaionTypeMapKey%></div>
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0">
			<logic:iterate id="operationTheaterEnquiryVO" name="operationTheaterList"  type="enquiry.vo.OperationTheaterEnquiryVO">
				<tr>
					<td width="50%" class="tdfont">
					<div align="center">
					<a onclick="getOTDetail('GETOTDETAIL','<%=operationTheaterEnquiryVO.getTheaterCode()%>')" style="cursor:pointer"> 
					 <bean:write name="operationTheaterEnquiryVO" property="theaternameDept"/>
					 </a>
					 </div>
					</td>
					
				</tr>
			</logic:iterate>
			</table>
			</his:ContentTag>
		</td>
		</logic:iterate>
		
	</tr>
	</table>
	</his:statusList>
	<his:statusTransactionInProcess>
	<his:ContentTag>
	<bean:define id="operationTheaterEnqVO" name="<%=enquiryConfig.OPERATION_THEATER_ENQUIRY_VO %>"></bean:define>
	<table width="100%" cellspacing="1" cellpadding="0">
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="theaterName" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><bean:write  name="operationTheaterEnqVO" property="theaterName"/></b>
		</td>
	 </tr>
	   <tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="department" /> </b>&nbsp;
		</td>
		<td width="50%" class="tdfont">
			&nbsp;<b><bean:write  name="operationTheaterEnqVO" property="department"/></b>
		</td>
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="25%" class="tdfonthead">
				<div align="center">
				<b><bean:message key="building" /> </b>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
				<b><bean:message key="block" /> </b>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
				<b><bean:message key="floor" /> </b>
				</div>
			</td>
			<td width="25%" class="tdfonthead">
				<div align="center">
				<b><bean:message key="room" /> </b>
				</div>
			</td>
		</tr>
		 <tr>
			<td width="25%" class="tdfont">
				<div align="center">
				<b><bean:write  name="operationTheaterEnqVO" property="locationBuilding"/></b>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
				<b><bean:write  name="operationTheaterEnqVO" property="locationBlock"/></b>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
				<b><bean:write  name="operationTheaterEnqVO" property="locationFloor"/></b>
				</div>
			</td>
			<td width="25%" class="tdfont">
				<div align="center">
				<b><bean:write  name="operationTheaterEnqVO" property="locationRoom"/></b>
				</div>
			</td>
		</tr>
	</table>
	</his:ContentTag>
	</his:statusTransactionInProcess>
		
	<html:hidden name="operationTheaterEnquiryFB" property="hmode"/>
	<html:hidden name="operationTheaterEnquiryFB" property="theaterCode"/>
	<html:hidden name="operationTheaterEnquiryFB" property="departmentCode"/>
</html:form>
<his:ButtonToolBarTag>
	<his:statusList>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');">
	</his:statusList>
	<his:statusTransactionInProcess>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');">
	</his:statusTransactionInProcess>
	
</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>