<%@page autoFlush="true" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="registration.*"%>

<%@page import="hisglobal.hisconfig.Config"%>
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">



function validateDepartments(renewalType)
{
	var len;
	var isValid = true;

count=0;

	len=document.getElementsByName("selectEpisodeCode").length;
	
				for(i=0;i<len;i++)
				{
				if(document.getElementsByName("selectEpisodeCode")[i].checked){
				count++;
					}
				}
	if (renewalType==3 || renewalType==4 || renewalType==5)
	{
    	if(count==0)
    	{
  		isValid = false;
    	alert("Select department");
		}
	
		else
		{
		isValid = true;
		}
	}
return isValid;
}

///////////////////////////////////////////////////
function enableDepartment(obj,amount){

// alert(obj.value);
// alert("chhhhhhhhhhhhhhhhhhhhhhhhh"+i);
j=parseInt(i);

if(obj.checked==true)
{
document.getElementsByName('amount')[0].value=parseInt(document.getElementsByName('amount')[0].value)+parseInt(amount);
}
else
document.getElementsByName('amount')[0].value=parseInt(document.getElementsByName('amount')[0].value)-parseInt(amount);
}
</script>


<%@page
	import="java.util.*,registration.*,hisglobal.vo.*,registration.controller.util.FileNoChangeUTIL"%>

<%
	String systemDate=hisglobal.presentation.WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy HH:mm");
	%>


<his:TitleTag>
	<his:name>
		<bean:message key="renewalOfregistration" />
	</his:name>
	<b> <font  size="2"
		face="Verdana, Arial, Helvetica, sans-serif"> 
</font> </b>
</his:TitleTag>



<%boolean flagIsStatusNew = false;
			String varStatus = "";
			boolean varIsNewStatus = false;%>
<his:statusNew>
	<%flagIsStatusNew = true;
				varIsNewStatus = true;
				varStatus = "New";%>
</his:statusNew>

<his:InputCrNoTag name="RenewalOfRegistrationFB">
</his:InputCrNoTag>

<his:statusInProcess>


<jsp:include page="/registration/patientDetail.cnt" flush="true" />

</his:statusInProcess>

<his:statusTransactionInProcess>

<%System.out.println(Config.RENEWAL_TYPE);
if (Config.RENEWAL_TYPE.equals("1")
							|| Config.RENEWAL_TYPE.equals("2")) {

						%>
	<his:SubTitleTag name="Hospital Renewal">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>

				<td class="tdfonthead" width="25%">
				<div id="expiryDateLabel"  align="right"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="expiryDate" /></font></div>

				</td>

				<td class="tdfont" width="25%">
				<div id='expiryDateControl' 
					align="left"><bean:write name="RenewalOfRegistrationFB" property="expiryDate" />
					
					
</div>

				</td>

				<td class="tdfonthead" width="25%">
				<div id='divtoDate'  align="right"> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="nextExpiryDate" /> </font></div>

				</td>
				

				<td class="tdfont" width="25%">
				<div id='divtoDateControl'  align="left">
				<bean:write name="RenewalOfRegistrationFB" property="nextExpiryDate"/>
				</div>

				</td>
			</tr>
		</table>
		<html:hidden name="RenewalOfRegistrationFB" property="expiryDate"/>
		<html:hidden name="RenewalOfRegistrationFB" property="nextExpiryDate"/>
	</his:ContentTag>
<%}	%>


<%if (Config.RENEWAL_TYPE.equals("3")
							|| Config.RENEWAL_TYPE.equals("4")
							|| Config.RENEWAL_TYPE.equals("5")) {

					%>
	<his:SubTitleTag name="Department Wise Renewal">
		<his:name>
			<bean:message key="expiryDate" />
		</his:name>
	</his:SubTitleTag>
<his:ContentTag>

		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<%int i = 0;%>
			<tr>

				<td width="25%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="selectDepartment" /> </font> </b></div></td>

				<td width="5%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">

				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="department" /> </font> </b></div></td>
				<td width="25%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="expiryDate" /> </font> </b></div></td>
				<td width="25%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif">
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="remarks" /> </font> </b></div></td>
			</tr>
			
			<logic:iterate id="CollecFile"
				name="<%=RegistrationConfig.COLL_EPISODE%>" indexId="j">
				<tr>
					<td width="5%" class="tdfonthead">
					<div align="center">
					<bean:define name="CollecFile" property="episodeCode"
						id="episodeCode" type="java.lang.String"></bean:define>
						
						 <html:checkbox
						name="RenewalOfRegistrationFB" 
						property="selectEpisodeCode" value="<%=episodeCode%>"></html:checkbox>
					</div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center"><bean:write name="CollecFile"
						property="department" /></div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center"><bean:write name="CollecFile"
						property="expiryDate" /></div>
					</td>
					<td width="25%" class="tdfonthead">
					<div align="center">
					<html:text name="RenewalOfRegistrationFB" property="remarks"  value="" size="20">  </html:text>
					</div>
					</td>
				</tr>




			</logic:iterate>
		</table>
	</his:ContentTag>

<%}

					%>

	<his:SubTitleTag name="Amount Collected">
		<his:name>
			<bean:message key="lastVisitdate" />
		</his:name>
	</his:SubTitleTag>
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">

			<tr>
				<td width="18%" class="tdfonthead">
				<div align="right"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="rupees" /> </font> </b></div>
				</td>
				<td width="25%" class="tdfont">

				<div align="left"><html:text name="RenewalOfRegistrationFB"
					property="amount" maxlength="3" size="4" readonly="true"  /></div>
				</td>



			</tr>


		</table>
	</his:ContentTag>


	<%--  </his:statusNew>	--%>

	<!--- End ReferInternal ---- Details of all open episodes-->




</his:statusTransactionInProcess>





<!-- ...............Code for Button Tool Bar.......... -->
<his:ButtonToolBarTag>

	<his:statusNew>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"
		style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	</his:statusNew>
	
	
	<his:statusTransactionInProcess>
	<div align="center"><img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-sv.png"/>' style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitFormOnValidate(validateDepartments('<%=Config.RENEWAL_TYPE%>'),'SAVE');"
		tabindex="1"
		onclick="submitFormOnValidate(validateDepartments('<%=Config.RENEWAL_TYPE%>'),'SAVE');"> <img
		class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'
		tabindex="1" style=cursor:pointer
		onkeypress="if(event.keyCode==13) submitPage('CANCEL');" tabindex="1"
		onclick="submitPage('CANCEL');"> <img class="button"
		src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer
		tabindex="1" onclick="submitForm('NEW')"
		onkeypress="if(event.keyCode==13) submitForm('NEW');"></div>
	</his:statusTransactionInProcess>
	
</his:ButtonToolBarTag>
<!-- .......End........Code for Button Tool Bar.......... -->

<his:status />
<input type="hidden" name="hmode" value="unspecified" />
<html:hidden name="RenewalOfRegistrationFB" property="patCrNo"/>

