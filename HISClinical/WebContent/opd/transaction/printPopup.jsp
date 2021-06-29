
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="java.util.List"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Iterator"%>
<%@page import="hisglobal.vo.PatDrugTreatmentDetailVO"%>
<%@page import="hisglobal.vo.PatientDetailVO"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.WebUTIL"%>


<%@page import="opd.transaction.controller.fb.PatientTreatmentDetailFB"%>
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/opdJs/opd.js" />
<his:javascript src="/opd/opdJs/opdAjax.js" />

<%@ page import="opd.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">

function closeForm()
{
	self.close();
}


</script>

<html:form action="/patTreatmentDetailTile">
	<body >
		<table id="prevDrugDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
			<tr>
			<td width="5%" class="tdfonthead">
				<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>CHANDIGARH - 160012 (INDIA)</b>
					
				</font>
			</div>
			</td>
			</tr>
			<tr>
			<td width="5%" class="tdfonthead">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								
					<b>POST GRADUATE INSTITUTE OF MEDICAL EDUCATION & RESEARCH</b>
				</font>
			</div>
			</td>
			</tr>
			<tr><td width="5%" class="tdfonthead"></td></tr>
			<tr><td width="5%" class="tdfonthead"></td></tr>
			
			
		</table>
		<br>
		<br>
	<table  width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
		<logic:iterate id="patDtlVO" indexId="i" name="<%=OpdConfig.PAT_DETAIL_VO_FOR_PRINTING%>" type="hisglobal.vo.PatientDetailVO">
			<tr>
				<td width="5%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="crNo" />
							</b>	
						</font>
					</div>
				</td>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						&nbsp;<bean:write name="patDtlVO" property="patCrNo"/>
					</font>	
				</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="name" />
							</b>	
						</font>
					</div>
					</td>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="patDtlVO" property="patFirstName"/>
						</font>	
					</div>
				</td>
				<td width="10%" class="tdfonthead" nowrap="nowrap">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="docName" />
							</b>	
						</font>
					</div>
					</td>
					<% PatientTreatmentDetailFB fb=(PatientTreatmentDetailFB)pageContext.findAttribute("PatientTreatmentDetailFB"); %>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<%=fb.getEmpName() %>
						</font>	
					</div>
				</td>
			</tr>
			<tr>
				<td width="5%" class="tdfonthead" nowrap="nowrap">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="visitNo" />
							</b>	
						</font>
					</div>
				</td>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="patDtlVO" property="episodeVisitNo"/>
						</font>	
					</div>
				</td>	
				<td width="5%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="gender/age" />
							</b>	
						</font>
					</div>
				</td>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<bean:write name="patDtlVO" property="patGenderAge"/>
						</font>	
					</div>
				</td>
				<td width="5%" class="tdfonthead">
					<div align="right">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="date" />
							</b>	
						</font>
					</div>
				</td>
				<%
				Date sysdate = (Date)session.getAttribute(Config.SYSDATEOBJECT);
				String sysadteString=WebUTIL.getCustomisedSysDate(sysdate,"dd-MM-yyyy");
				%>
				<td width="7%" class="tdfontheadExam" nowrap="nowrap">
					<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							&nbsp;<%=sysadteString %>
						</font>	
					</div>
				</td>	
			</tr>
			
		</logic:iterate>
	</table>	
	<br>
	<br>
	
	<table id="prevDrugDtlTableId" width="100%" border="0" cellspacing="1" cellpadding="0" style="display:block;">
	<tr>
					
					<td width="5%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="Sr.No." />
							</b>	
							</font>
						</div>
					</td>
					<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="drugname" />
							</b>	
							</font>
						</div>
					</td>
					
					<td width="7%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="schedule" /> & <bean:message key="dosage" />
							</b>	
							</font>
						</div>
					</td>
										
					<td width="13%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="startDate" />
							</b>	
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<bean:message key="days" />
							</b>	
							</font>
						</div>
					</td>
				
				
					
				</tr>
	
	
	<logic:iterate id="drugDtlVO" indexId="i" name="<%=OpdConfig.DRUG_LIST_FOR_PRINTING%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
	<%String ind=new Integer(i+1).toString(); %>
	<tr>
		<td width="5%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<%=ind %>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="drugDtlVO" property="drugName"/>
				</font>	
			</div>
		</td>
		
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="drugDtlVO" property="doseTime"/>&nbsp;
					<bean:write name="drugDtlVO" property="emptyStomatchDesc"/>
				</font>	
			</div>
		</td>
		
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="drugDtlVO" property="startDate"/>
				</font>	
			</div>
		</td>
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="drugDtlVO" property="days"/>
				</font>	
			</div>
		</td>
	</tr>
	<tr>
		<td width="5%" class="tdfonthead">
						<div align="center">
						</div>
					</td>
		<td width="7%" class="tdfontheadExam" nowrap="nowrap">
			<div align="center">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<bean:write name="drugDtlVO" property="remarks"/>
					
				</font>	
			</div>
		</td>
	</tr>
	</logic:iterate>
	</table>	
	<br>
	
	<logic:present name="<%=OpdConfig.PREV_OTHER_INSTRUCTION_LIST %>">
	<logic:notEmpty name="<%=OpdConfig.PREV_OTHER_INSTRUCTION_LIST %>">
	<table>
	<tr>
		<td width="10%" class="tdfontheadExam" nowrap="nowrap">
			Other Instruction :
		</td>
		<td width="3%" class="tdfontheadExam"></td>
		<td width="85%" class="tdfontheadExam"></td>
	</tr>
	
	
	<logic:iterate id="extTreatVO" indexId="i" name="<%=OpdConfig.PREV_OTHER_INSTRUCTION_LIST%>" type="hisglobal.vo.PatExtTreatmentDetailVO">	
		<%String ind=new Integer(i+1).toString(); %>
		<tr>
			<td width="10%" class="tdfontheadExam"></td>
			<td width="3%" class="tdfontheadExam">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<%=ind %>
					</font>
				</div>	
				
			</td>
			<td width="85%" class="tdfontheadExam">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<bean:write name="extTreatVO" property="extTreatmentName"/>
					</font>	
				</div>
			</td>
		</tr>
	</logic:iterate>	
	</table>	
	
	</logic:notEmpty>
	</logic:present>
	
	
	<logic:present name="<%=OpdConfig.PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING %>">
	<logic:notEmpty name="<%=OpdConfig.PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING %>">
	<table>
	<tr>
		<td width="10%" class="tdfontheadExam" nowrap="nowrap">
			Diet Advice :
		</td>
		<td width="85%" class="tdfontheadExam"></td>
	</tr>
	
	
	<logic:iterate id="dietAdviceDtlVO" indexId="i" name="<%=OpdConfig.PREV_ALL_DIET_ADVICE_DTL_FOR_PRINTING%>" type="hisglobal.vo.PatDietAdviceDetailVO">	
		
		<tr>
			<td width="10%" class="tdfontheadExam" nowrap="nowrap">
			</td>
			<td width="85%" class="tdfontheadExam">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Take <bean:write name="dietAdviceDtlVO" property="dietTypeDesc"/> diet for <bean:write name="dietAdviceDtlVO" property="days"/> days.
					</font>	
				</div>
			</td>
		</tr>
	</logic:iterate>	
	</table>	
	
	</logic:notEmpty>
	</logic:present>
	
	
	<logic:present name="<%=OpdConfig.PREV_REST_DETAIL_LIST_FOR_PRINTING %>">
	<logic:notEmpty name="<%=OpdConfig.PREV_REST_DETAIL_LIST_FOR_PRINTING %>">
	<table>
	<tr>
		<td width="10%" class="tdfontheadExam" nowrap="nowrap">
			Rest Advice :
		</td>
		<td width="85%" class="tdfontheadExam"></td>
	</tr>
	
	
	<logic:iterate id="restAdviceDtlVO" indexId="i" name="<%=OpdConfig.PREV_REST_DETAIL_LIST_FOR_PRINTING%>" type="hisglobal.vo.RestAdviceDtlVO">	
		
		<tr>
			<td width="10%" class="tdfontheadExam" nowrap="nowrap">
			</td>
			<td width="85%" class="tdfontheadExam">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Take <bean:write name="dietAdviceDtlVO" property="days"/> days rest.
					</font>	
				</div>
			</td>
		</tr>
	</logic:iterate>	
	</table>	
	
	</logic:notEmpty>
	</logic:present>
	
	<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
	 <html:hidden name="PatientTreatmentDetailFB" property="hmode"/>
	 
	</body>
	
</html:form>		