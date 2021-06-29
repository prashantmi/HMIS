<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}
</script>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
		<html:form action="/patTreatmentDetailTile">
		<body>
		
		<his:TitleTag name="DRUG SCHEDULE">
		</his:TitleTag>
			<his:ContentTag>
			<%List lst = (List)session.getAttribute(OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG);
				if(lst.size()!=0)
				{
					%>
			<table width="100%" cellpadding="0" cellspacing="1" >
				
			
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="rupturetime"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfont" width="25%">
						<div align="left">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					</logic:iterate>		
				</tr>
				
<!--					This tr not in use -->
				<tr style="display: none;"> 
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="emptyStomach"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfont" width="25%" id="firstEmptyStomach">
						<div align="left">
							<% if(drugSheduleDtlVO.getIsEmptyStomach()!=null && drugSheduleDtlVO.getIsEmptyStomach().equals("1"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" disabled="disabled">
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray" disabled="disabled" >
							<%} %>
						</div>
					</td>
					</logic:iterate>		
				</tr>
				<tr >
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfonthead" width="25%" id="firstDosageId">
						<div align="left">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
							</b>
						</div>
					</td>
					</logic:iterate>		
				</tr>
				<tr >
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="cutoffbef"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfonthead" width="25%" id="firstCutOffBefore">
						<div align="left">
							<b>
							<%=drugSheduleDtlVO.getCutOffTime() %>
							</b>
						</div>
					</td>
					</logic:iterate>		
				</tr>
				<tr >
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="cutofaft"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfonthead" width="25%" id="firstCutOffAfter">
						<div align="left">
							<b>
							<%=drugSheduleDtlVO.getCutOffTimeafter() %>
							</b>
						</div>
					</td>
					</logic:iterate>		
				</tr>
			</table>		
				<%
				}
				else
				{
					%>
				<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td class="tdfonthead" width="25%">
							<div align="center">
							<b>No Schedule For This Drug</b>
							</div>
						</td>
					</tr>
				</table>	
					
					<%
				}
			%>
			
			</his:ContentTag>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
			</his:ButtonToolBarTag>
		</body>
		</html:form>
		<html:hidden name="PatientTreatmentDetailFB" property="scheduleIndex"/>	