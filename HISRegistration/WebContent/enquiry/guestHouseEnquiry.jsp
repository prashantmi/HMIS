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

function getGuestHouseDetail(guestHouse){
	document.getElementsByName('hmode')[0].value='GETGUESTHOUSEDTL'
	document.getElementsByName('guestHouse')[0].value=guestHouse
	document.forms[0].submit();
}

</script>

<body>
<his:TransactionContainer>

<form action="<his:path src='/enquiry/guestHouseEnquiry.cnt' />" method="post">

<his:TitleTag name="Guest House Enquiry">
</his:TitleTag>
	<his:SubTitleTag name="">
	<div align="left">Guest House List</div>
	</his:SubTitleTag>	
	<his:statusList>
	<his:ContentTag>
	<bean:define id="guestHouseList" name="<%=enquiryConfig.GUEST_HOUSE_LIST %>" type="java.util.List"> </bean:define>
	<table width="100%" cellspacing="1" cellpadding="0">
	<logic:iterate id="guestHouseEnqVO" name="guestHouseList" type="enquiry.vo.GuestHouseEnquiryVO"  indexId="index">
		<tr>
			<td width="20%" class="tdfont">
			<div align="left">
				<a onclick="getGuestHouseDetail('<%=guestHouseEnqVO.getGuestHouse()%>')" style="cursor: pointer;">
				<bean:write name="guestHouseEnqVO" property="guestHouse"/>
				</a>
			</div>
			</td>
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</his:statusList>
	<his:statusTransactionInProcess>
	<his:ContentTag>
	
	<bean:define id="guestHouseList" name="<%=enquiryConfig.GUEST_HOUSE_DETAIL_LIST %>" type="java.util.List"> </bean:define>
	<table width="100%" cellspacing="1" cellpadding="0">
	<tr>
		<td width="50%" class="tdfonthead">
			<b><bean:message key="guestHouse"/></b>
		</td>
		<td width="50%" class="tdfont">
			<bean:write name="guestHouseEnquiryFB" property="guestHouse"/>	
		</td> 
	</tr>
	</table>
	<table width="100%" cellspacing="1" cellpadding="0">	
	<tr>
		<td width="25%" class="tdfonthead">
			<div align="center"><b><bean:message key="building"/></b></div>
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><b><bean:message key="block"/></b></div>
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><b><bean:message key="floor"/></b></div>
		</td>
		<td width="25%" class="tdfonthead">
			<div align="center"><b><bean:message key="room"/></b></div>
		</td>
	</tr>
		
	<tr>	
		<td width="25%" class="tdfont">
			<div align="center">
			<bean:write name="guestHouseEnquiryFB" property="locationBuilding"/>
			</div>	
		</td> 
		<td width="25%" class="tdfont">
			<div align="center">
			<bean:write name="guestHouseEnquiryFB" property="locationBlock"/>
			</div>	
		</td> 
		<td width="25%" class="tdfont">
			<div align="center">
			<bean:write name="guestHouseEnquiryFB" property="locationFloor"/>	
			</div>
		</td> 
		<td width="25%" class="tdfont">
				
		</td> 
	</tr>
	</table>
	<his:SubTitleTag name="">
	</his:SubTitleTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="17%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="floor"/></b>
				</div>
			</td>
			<td width="17%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="room"/></b>
				</div>
			</td>
			<td width="17%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="bedNo"/></b>
				</div>
			</td>
			<td width="17%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="status"/></b>
				</div>
			</td>
			<td width="17%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="vacantOn"/></b>
				</div>
			</td>
			<td width="15%" class="tdfonthead">
				<div align="center">
					<b><bean:message key="rent"/></b>
				</div>
			</td>
			
		</tr>	
	<logic:iterate id="guestHouseEnqVO" name="guestHouseList" type="enquiry.vo.GuestHouseEnquiryVO"  indexId="index">
		<tr>
			<td width="17%" class="tdfont">
			<div align="center">
				<bean:write name="guestHouseEnqVO" property="locationFloor"/>
			</div>
			</td>
			<td width="17%" class="tdfont">
			<div align="center">
				<bean:write name="guestHouseEnqVO" property="locationRoom"/>
			</div>
			</td>
			<td width="17%" class="tdfont">
			<div align="center">
				<bean:write name="guestHouseEnqVO" property="bed"/>
			</div>
			</td>
			<td width="17%" class="tdfont">
			<div align="center">
				<bean:write name="guestHouseEnqVO" property="status"/>
			</div>
			</td>
			<td width="17%" class="tdfont">
			<div align="center">
				<logic:equal name="guestHouseEnqVO" value="alloted" property="status">
				<bean:write name="guestHouseEnqVO" property="vacantOn"/>
				</logic:equal>
			</div>
			</td>
			<td width="15%" class="tdfont">
			<div align="center">
				<bean:write name="guestHouseEnqVO" property="rent"/>
			</div>
			</td>
			
		</tr>
		</logic:iterate>
	</table>
	</his:ContentTag>
	</his:statusTransactionInProcess>
	
	<html:hidden name="guestHouseEnquiryFB" property="hmode"/>
	<html:hidden name="guestHouseEnquiryFB" property="guestHouse"/>
	<html:hidden name="guestHouseEnquiryFB" property="locationBuilding"/>
	<html:hidden name="guestHouseEnquiryFB" property="locationBlock"/>
	<html:hidden name="guestHouseEnquiryFB" property="locationFloor"/>
	

</form>


<his:ButtonToolBarTag>
	<his:statusList>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1" onclick="submitPage('CANCEL');"/>
	</his:statusList>
	<his:statusTransactionInProcess>
	<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1" onclick="submitPage('NEW');"/>
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<his:status/>
</his:TransactionContainer>
</body>
</html>