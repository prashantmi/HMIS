<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="java.util.*"%>
<%@page import="hisglobal.vo.*" %>

<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<script type="text/javascript">
function closeForm()
{
	self.close();
}
</script>

<his:javascript src="/hisglobal/js/commonFunctions.js"/> 
<% List lst = (List)session.getAttribute(InpatientConfig.PREV_DRUG_SCHEDULE_LIST_WITH_PARTICULAR_DRUG_NAME);
				List mornningLst=new ArrayList();
				List aftNoonLst=new ArrayList();
				List eveningLst=new ArrayList();
				List nightLst=new ArrayList();
					for(int i=0;i<lst.size();i++)
					{
						DrugSheduleDtlVO drugSheduleDtlVO=(DrugSheduleDtlVO)lst.get(i);
						if(drugSheduleDtlVO.getDoseShift().equals(InpatientConfig.MORNING_SHIFT_ID))
							mornningLst.add(drugSheduleDtlVO);
						if(drugSheduleDtlVO.getDoseShift().equals(InpatientConfig.NOON_SHIFT_ID))
							aftNoonLst.add(drugSheduleDtlVO);
						if(drugSheduleDtlVO.getDoseShift().equals(InpatientConfig.EVENING_SHIFT_ID))
							eveningLst.add(drugSheduleDtlVO);
						if(drugSheduleDtlVO.getDoseShift().equals(InpatientConfig.NIGHT_SHIFT_ID))
							nightLst.add(drugSheduleDtlVO);
						
					}
					session.setAttribute(InpatientConfig.MORNING_SHIFT_SCHDULE_LST,mornningLst);
					session.setAttribute(InpatientConfig.NOON_SHIFT_SCHDULE_LST,aftNoonLst);
					session.setAttribute(InpatientConfig.EVENING_SHIFT_SCHDULE_LST,eveningLst);
					session.setAttribute(InpatientConfig.NIGHT_SHIFT_SCHDULE_LST,nightLst);
%>
		<html:form action="/nurDrugAdministration">
		<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
		<body>
		
		<his:TitleTag name="DRUG SHEDULE">
		</his:TitleTag>
			
			
			<logic:notEmpty name="<%=InpatientConfig.MORNING_SHIFT_SCHDULE_LST %>">
			<his:SubTitleTag name="Morrning Shift">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drug"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				</tr>	
				<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=InpatientConfig.MORNING_SHIFT_SCHDULE_LST%>" type="hisglobal.vo.DrugSheduleDtlVO">
				<tr>	
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDrugName() %>
							</b>
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="center">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
							</b>
						</div>
					</td>
				</tr>	
			  </logic:iterate>		
			 </table>	
			</his:ContentTag>	
			</logic:notEmpty>	
			
			<logic:notEmpty name="<%=InpatientConfig.NOON_SHIFT_SCHDULE_LST %>">
			<his:SubTitleTag name="Noon Shift">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drug"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				</tr>	
				<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=InpatientConfig.NOON_SHIFT_SCHDULE_LST%>" type="hisglobal.vo.DrugSheduleDtlVO">
				<tr>	
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDrugName() %>
							</b>
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="center">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
							</b>
						</div>
					</td>
				</tr>	
			</logic:iterate>		
			</table>	
			</his:ContentTag>	
			</logic:notEmpty>	
			
			<logic:notEmpty name="<%=InpatientConfig.EVENING_SHIFT_SCHDULE_LST %>">
			<his:SubTitleTag name="Evening Shift">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drug"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				</tr>	
				<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=InpatientConfig.EVENING_SHIFT_SCHDULE_LST%>" type="hisglobal.vo.DrugSheduleDtlVO">
				<tr>	
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDrugName() %>
							</b>
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="center">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
							</b>
						</div>
					</td>
				</tr>	
			</logic:iterate>		
			</table>	
			</his:ContentTag>	
			</logic:notEmpty>	
			
			<logic:notEmpty name="<%=InpatientConfig.NIGHT_SHIFT_SCHDULE_LST %>">
			<his:SubTitleTag name="Night Shift">
			</his:SubTitleTag>
			<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<tr>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="drug"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<td class="tdfonthead" width="25%">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
				</tr>	
				<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=InpatientConfig.NIGHT_SHIFT_SCHDULE_LST%>" type="hisglobal.vo.DrugSheduleDtlVO">
				<tr>	
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDrugName() %>
							</b>
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="center">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					<td class="tdfont" width="25%" id="firstDosageId">
						<div align="center">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
							</b>
						</div>
					</td>
				</tr>	
				</logic:iterate>		
			</table>	
			</his:ContentTag>	
			</logic:notEmpty>	
			
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
			</his:ButtonToolBarTag>
		</body>
		</html:form>
		