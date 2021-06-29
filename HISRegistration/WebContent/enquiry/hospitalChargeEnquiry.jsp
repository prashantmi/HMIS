<%@page import="enquiry.enquiryConfig"%>
<html>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<%@page import="registration.*,enquiry.*"%>
<%@page import="enquiry.vo.*,java.util.*,hisglobal.tools.tag.*,enquiry.transaction.controller.fb.*" %>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/calendar-blue2.css" />

<script>
window.onload=function(){
	if(document.getElementsByName('tariffName')[0]){
		document.getElementsByName('tariffName')[0].focus();
	}	

}
function submitPage(hmode){
	document.forms[0].hmode.value=hmode;
	document.forms[0].submit();
}

function getChargeDetailByTariff(hmode,tariffId,tariffName,groupName){

	document.getElementsByName('hmode')[0].value=hmode;
	document.getElementsByName('tariffId')[0].value=tariffId;
	document.getElementsByName('tariffName')[0].value=tariffName;
	document.getElementsByName('groupName')[0].value=groupName;
	document.forms[0].submit();
}

function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}
function getTarrifBySelectedHospital(obj)
{
	//alert(obj.value)
	document.getElementsByName('hmode')[0].value='GETSELECTEDHOSPITALTARIFFLIST';
	document.getElementsByName('strModeGrpTariff')[0].value="1";
	document.forms[0].submit();
}

function getTarrifByGroup(obj)
{
	//alert(obj.value)
	document.getElementsByName('groupId')[0].value=obj.value;
	document.getElementsByName('hmode')[0].value='GETTARIFFBYGROUPID';
	/*var isDirectCall=document.getElementsByName('isDirectCall')[0].value
	if(isDirectCall=="DIRECT"){
		document.getElementsByName('hmode')[0].value='unspecified';
	}
	else{
		document.getElementsByName('hmode')[0].value='NEW';
	}	*/
	document.forms[0].submit();
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}
</script>

<body>
<his:TransactionContainer>
<%String isDirectCall=((HospitalChargeEnquiryFB)pageContext.findAttribute("hospitalChargeEnquiryFB")).getIsDirectCall().toString(); %>
 <%if(isDirectCall.equals("DIRECT")){ %>
 	<form action="<his:path src='/enquiry/hospitalChargeEnquiry.cnt' />" method="post">
 <%} %>
	
		<%	PaginationFB fbPage= new PaginationFB();
				pageContext.setAttribute("fbPagination",fbPage);
				fbPage.setCurrentPage(((HospitalChargeEnquiryFB)request.getAttribute("hospitalChargeEnquiryFB")).getCurrentPage());
				fbPage.setObjArrName(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW1);
				fbPage.setAppendInTitle("Tariff List");
				fbPage.setTitleRequired(false);
				fbPage.setMaxRecords(enquiryConfig.RECORD_PER_PAGE);
			%>
			
			<html:hidden name="hospitalChargeEnquiryFB" property="currentPage"/>

	<his:TitleTag name="Charge Enquiry">
	</his:TitleTag>
	<his:statusList>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				<td class="tdfonthead" nowrap width="20%">
					<div align="right"><font color="#FF0000"
						size="1" face="Verdana, Arial, Helvetica, sans-serif\">*</font> <font
						color="#000000" size="2"
						face="Verdana, Arial, Helvetica, sans-serif"> <b> <bean:message
						key="hospname" /></b> </font></div>
				</td>
				<td class="tdfont" width="18%">
					<html:select name="hospitalChargeEnquiryFB" property="hospitalCode" tabindex="1" styleClass="registrationCmb" onchange="getTarrifBySelectedHospital(this);"> 
						<logic:present
							name="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>">
							<!--<html:option value="-1">Select Value</html:option>-->
							<html:options collection="<%=RegistrationConfig.HOSPITAL_COMBO_LIST%>" property="value" labelProperty="label" />
						</logic:present>
					</html:select>
				</td>
				<td class="tdfonthead" nowrap width="62%" colspan="3" ></td>
			</tr>
			<tr>
				<td width="20%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b> <bean:message key="tariff" /> <bean:message key="name" />
					</b>
					</font></div>
				</td>
				<td width="18%" class="tdfont">
					
					<font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
						<html:text name="hospitalChargeEnquiryFB" property="tariffName" onkeypress="if(event.keyCode==13) submitPage('GETTARIFFBYNAME');"></html:text>
					</font>
				</td>
				<td  class="tdfont">	
					<img class="button" src='<his:path src="/hisglobal/images/Search.png"/>' tabindex="1" style="cursor: pointer"
						onkeypress="if(event.keyCode==13) submitPage('GETTARIFFBYNAME');" tabindex="1" onclick="submitPage('GETTARIFFBYNAME');">
				</td>
				<td width="30%" class="tdfonthead">
				<div align="right"><font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
					<b> <bean:message key="group" /> <bean:message key="name" />
					</b>
					</font></div>
				</td>
				<td width="20%" class="tdfont">
				<div align="left">
					<html:select name="hospitalChargeEnquiryFB" property="groupId" styleClass="regcbo" onchange="getTarrifByGroup(this);">
						<html:option value="-1">Select Value</html:option>
						<html:options collection="<%=enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_GROUP_LIST %>" labelProperty="label" property="value" />
					</html:select>
				</div>
				</td>
			</tr>
		</table>
	</his:ContentTag>
	<logic:present name="<%=enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW %>">
	<his:PaginationTag name="fbPagination"></his:PaginationTag>
	</logic:present>
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
		<logic:present name="<%=enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW %>">
		<%try{
		List lst=(List)session.getAttribute(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW1);
		List lst2=(List)session.getAttribute(enquiryConfig.HOSPITAL_CHARGE_ENQUIRY_VO_LIST_VIEW2);
		int startIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_START_INDEX);
		int endIndex = (Integer)request.getAttribute(PaginationTag.PAGINATION_END_INDEX);
		for(int i=startIndex;i<=endIndex;i++)
		{
			
			HospitalChargeEnquiryVO chargeVo=(HospitalChargeEnquiryVO)lst.get(i);
			HospitalChargeEnquiryVO chargeVo2=(HospitalChargeEnquiryVO)lst2.get(i);
		%>
				
					<tr>
						<td width="50%" class="tdfont">
							<div align="left">
								<a onclick="getChargeDetailByTariff('GETCHARGEDTL','<%=chargeVo.getTariffId()%>','<%=chargeVo.getTariffName()%>','<%=chargeVo.getGroupName() %>')" style="cursor:pointer"><%=chargeVo.getTariffName() %></a>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<a onclick="getChargeDetailByTariff('GETCHARGEDTL','<%=chargeVo2.getTariffId()%>','<%=chargeVo2.getTariffName()%>','<%=chargeVo.getGroupName() %>')" style="cursor:pointer"><%=chargeVo2.getTariffName() %></a>
							</div>
						</td>
						
					</tr>
				<%} }catch(Exception e){e.printStackTrace();}%>
			</logic:present>	
			</table>
		</his:ContentTag>
	</his:statusList>
	
	<his:statusTransactionInProcess>
	<his:SubTitleTag name="Charges Detail">
	</his:SubTitleTag>
	<table width="100%" cellspacing="1" cellpadding="0">
		<tr>
			<td width="50%" class="tdfonthead">
				<b> <bean:message key="tariff" /> <bean:message key="name" /></b>
			</td>
			<td width="50%" class="tdfont">
			 	<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<bean:write name="hospitalChargeEnquiryFB" property="tariffName"/>
				</font>	
			</td>
		</tr>
		<tr>
			<td width="50%" class="tdfonthead">
				<b> <bean:message key="group" /> <bean:message key="name" /></b>
			</td>
			<td width="50%" class="tdfont">
			 	<font size="2" face="Verdana, Arial, Helvetica, sans-serif">
				&nbsp;<bean:write name="hospitalChargeEnquiryFB" property="groupName"/>
				</font>	
			</td>
		</tr>
	</table>		
	 <his:ContentTag>
		<table width="100%" cellspacing="1" cellpadding="0">
			<bean:define id="list" name="<%=enquiryConfig.HOSPITAL_CHARGE_TYPE_LIST %>" type="java.util.List"></bean:define>
			<%int len=list.size();len++;
				 String width=(100/len)+"%" ;
				System.out.println("HOSPITAL_CHARGE_TYPE_LIST.size() :"+list.size());	 
			%>
			<tr>
				<td width="<%=width %>" class="tdfonthead">
					<div align="center">
						<b><bean:message key="category" /></b>
					</div>
				</td>
				
				<logic:iterate id="chargeList" indexId="index" name="<%=enquiryConfig.HOSPITAL_CHARGE_TYPE_LIST %>" type="hisglobal.utility.Entry">
						
						<td width="<%=width %>" class="tdfonthead">
						<div align="center">
							<b><%=chargeList.getLabel() + " Charges"%></b>
						</div>
					</td>
				</logic:iterate>
			</tr>
				
			<logic:iterate id="chargeVoMap" indexId="idx" name="<%=enquiryConfig.HOSPITAL_CHARGE_DETAIL_MAP %>" type="java.util.Map.Entry">
			<bean:define id="chargeVoList" name="chargeVoMap" property="value" type="java.util.List"></bean:define>	
				<tr>
					<td width="<%=width %>" class="tdfont">
						<div align="center">
							<b><bean:write name="chargeVoMap" property="key"/></b>
						</div>
					</td>
					<logic:iterate id="chargeVo" indexId="index" name="chargeVoList" type="enquiry.vo.HospitalChargeEnquiryVO">
						<td width="<%=width %>" class="tdfont">
						<div align="center">
							<c:if test="${chargeVo.charge==null}">
								0
							</c:if>
							<c:if test="${chargeVo.charge!=null}">
								<bean:write name="chargeVo" property="charge"/>
							</c:if>
						</div>
					</td>
					</logic:iterate>
				</tr>
			</logic:iterate>
			</table>
		</his:ContentTag>
		
	<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' onclick="showLegends();">		<font color="#ffffff" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
	<div id="divLegends" style="display:none">
	<his:ContentTag>
		<table width="100%" cellpadding="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<tr>
				<td width="10%" style="color:#653232;">
					<b>(P)</b>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Patient Category
					</font>
					</div>
				</td>				
			</tr>
			<tr>
				<td width="10%" style="color:#653232;">
					<b>(T)</b>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Treatment Category
					</font>		
					</div>
				</td>				
			</tr>
		</table>
	</his:ContentTag>
	</div>	
</his:statusTransactionInProcess>	
		<html:hidden name="hospitalChargeEnquiryFB" property="hmode" />
		<html:hidden name="hospitalChargeEnquiryFB" property="tariffId" />
<%--		<html:hidden name="hospitalChargeEnquiryFB" property="tariffName" /> --%>
		<html:hidden name="hospitalChargeEnquiryFB" property="groupId" />
		<html:hidden name="hospitalChargeEnquiryFB" property="groupName" />
		<html:hidden name="hospitalChargeEnquiryFB" property="isDirectCall" />
		<html:hidden name="hospitalChargeEnquiryFB" property="strModeGrpTariff" />
		
  <%if(isDirectCall.equals("DIRECT")){ %> 
	</form>
	<%} %>
	
	<his:ButtonToolBarTag>
		<his:statusList>	
		<logic:equal name="hospitalChargeEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"onclick="submitPage('CANCEL');">
		</logic:equal>	
		<logic:notEqual name="hospitalChargeEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitDesk('NEW');" tabindex="1"onclick="submitDesk('NEW');">
		</logic:notEqual>
		</his:statusList>	
		<his:statusTransactionInProcess>	
		<logic:equal name="hospitalChargeEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('unspecified');" tabindex="1"onclick="submitPage('unspecified');">
		</logic:equal>	
		<logic:notEqual name="hospitalChargeEnquiryFB" property="isDirectCall" value="DIRECT">
		<img class="button" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" style="cursor: pointer"
			onkeypress="if(event.keyCode==13) submitPage('NEW');" tabindex="1"onclick="submitPage('NEW');">
		</logic:notEqual>	
		</his:statusTransactionInProcess>	
	</his:ButtonToolBarTag>
<his:status />
</his:TransactionContainer>
</body>
</html>