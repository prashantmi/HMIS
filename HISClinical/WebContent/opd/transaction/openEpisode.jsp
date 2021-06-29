<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/> 
<his:javascript src="/registration/js/dateFunctions.js"/> 
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">

function openEpisode(hmode)
{
	
	elmt=document.getElementsByName("hmode")[0];
	elmt.value=hmode;
	document.forms[0].submit();
	
}
</script>

<%@page	import="opd.*"%>
<body>
<html:form action="/opd/openEpisode">

<his:TitleTag>
	<his:name>
		<bean:message key="openOfEpisode"/>
	</his:name>
	
	<b> 
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:message key="date" />
		 	<bean:message key="and" /> 
		 	<bean:message key="time" />
		 	<bean:write name="<%=OpdConfig.SYSDATE%>"/>
		 </font>
	</b>	 	
</his:TitleTag>



<his:ContentTag>
	<his:InputCrNoTag name="OpenEpisodeFB">
	</his:InputCrNoTag>
</his:ContentTag>

<his:statusInProcess>
	<his:ContentTag>
		<jsp:include page="/registration/patientDetail.cnt" flush="true" />
	</his:ContentTag>	
</his:statusInProcess>

<his:statusTransactionInProcess>
	
	
	<his:SubTitleTag name="Close Episode">
	</his:SubTitleTag>
	
	<his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<tr>
				
				<td width="20%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"></font>
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="epicode" /> </font> </b></div></td>
				
				<td width="20%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"></font>
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="episodeType" /> </font> </b></div></td>
					
				<td width="20%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"></font>
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="department" /> </font> </b></div></td>
					
				<td width="20%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"></font>
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="unit" /> </font> </b></div></td>
				
				<td width="20%" class="addtoolbar"
					style="border-top:outset black 2px; border-bottom:inset black 2px;"><font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"></font>
				<div align="center"><b> <font color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> <bean:message
					key="reason" /> </font> </b></div></td>
			</tr>
			
			<logic:iterate id="closeEpisode"  name="<%=OpdConfig.COLL_CLOSE_EPISODE %>">
				<tr>
					<td width="20%" class="tdfonthead">
						<div align="center">
							
							<bean:define name="closeEpisode" property="episodeCode" id="episodeCode" type="java.lang.String">
							</bean:define>
							<html:checkbox name="OpenEpisodeFB" property="selectEpisodeCode" value="<%=episodeCode%>">
							</html:checkbox>
							<bean:write name="closeEpisode" property="episodeCode"/>
						
						</div>
					</td>
				
					<td width="20%" class="tdfonthead">
						<div align="center">
							<bean:write name="closeEpisode" property="episodeType"/>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							
						</div>
					</td> 
					<td width="20%" class="tdfonthead">
						<div align="center">
							<html:text name="OpenEpisodeFB" property="reason" value="" size="20"></html:text>
						</div>
					</td>
				</tr>
			
			</logic:iterate>
		</table>
</his:ContentTag>

</his:statusTransactionInProcess>
<!-- ...............Code for Button Tool Bar.......... -->
<his:ButtonToolBarTag>
	<his:statusNew>
	
		<div align="center">
		<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" onclick="submitPage('CANCEL');"> 
		<img class="button"	src='<his:path src="/../HIS/hisglobal/images/buttons/btn-clr.png"/>' style=cursor:pointer tabindex="1" onclick="submitForm('NEW')" onkeypress="if(event.keyCode==13) submitForm('NEW');">
		</div>
	</his:statusNew>
	
	<his:statusTransactionInProcess>	
		
		<div align="center">
		<b><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><bean:message key="WantToReopen"/></font></b>
		</div><div align="center">
		<img class="button" src='<his:path src="/hisglobal/images/btn-yes.png"/>' style=cursor:pointer tabindex="1" onclick="openEpisode('OPEN')" onkeypress="if(event.keyCode==13) openEpisode()">
		<img class="button"	src='<his:path src="/hisglobal/images/btn-no.png"/>' tabindex="1" style=cursor:pointer onkeypress="if(event.keyCode==13) submitPage('CANCEL');" onclick="submitPage('CANCEL');"> 
		</div>
		
	</his:statusTransactionInProcess>
</his:ButtonToolBarTag>

<!-- .......End........Code for Button Tool Bar.......... -->

<his:status/>
		
<html:hidden name="OpenEpisodeFB" property="hmode"/>
<html:hidden name="OpenEpisodeFB" property="patCrNo"/>
</html:form>
</body>
</html>