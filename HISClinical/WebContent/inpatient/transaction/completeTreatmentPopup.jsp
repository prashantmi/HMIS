<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="inpatient.InpatientConfig"%>
<%@page import="java.util.List"%>
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

function submitPage(mode)
{
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=mode;
	document.forms[0].submit();
	self.close();
}
function viewRemarks(obj)
{
	document.getElementsByName("showRemarks")[0].value=obj;
	document.getElementById("divViewRemarks").style.display="block"; 
	document.getElementById("divViewSchidule").style.display="none";
	
}

function showPrevDrugSchedule(index,hmode)
{	
	//alert("index "+index);
	//alert("hmode "+hmode);
	document.getElementsByName("scheduleIndex")[0].value=index;
	
	
	elmt=document.getElementsByName("hmode")[0];  
	elmt.value=hmode;
	document.forms[0].submit();
	
//	alert("afterrrrrrrrrrr");
	
	
	/*
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=PREVDRUGSHEDULE&scheduleIndex='+index;
	child = window.open(createFHashAjaxQuery(path),'popupWindow','status=yes,scrollbars=yes,height='+200+',width='+500+',left=10,top=10');  
	child.moveTo(250,250);
	child.focus(); 
	if(!child.opener)
	child.opener = self;
	return child
	*/
}

</script>
<html:form action="/nurDrugAdministration">
	<body >
		<his:TitleTag name="Complete Treatment">
		</his:TitleTag>
		
		<logic:notEmpty name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>">
		<his:SubTitleTag name="Treatment Advice Detail">
			
		</his:SubTitleTag>		
		<his:ContentTag>
			<table width="100%" border="0" cellspacing="1" cellpadding="0" >
				<tr>
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugname" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="dosage" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="drugRoute" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="emptyStomach" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="frequency" />
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="startDate" />
								</b>
							</font>
						</div>
					</td><td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="endDate" />
								</b>
							</font>
						</div>
					</td>
					
   				<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="schedule" />
								</b>
							</font>
						</div>
					</td>
					
					
					
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:message key="instructions" />
								</b>
							</font>
						</div>
					</td>
				</tr>
			<logic:iterate id="drugDtlVO" indexId="idx" name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>" type="hisglobal.vo.PatDrugTreatmentDetailVO">
				<%String ind=idx.toString(); %>
				<tr id="trSpecimen">
				
					<td width="15%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="doseName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="drugRouteName"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:write name="drugDtlVO" property="isEmptyStomach"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="frequencyName"/>
								</b>
							</font>	
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="startDate"/>
								</b>
							</font>
						</div>
					</td>
					<td width="10%" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
								<bean:write name="drugDtlVO" property="endDate"/>
								</b>
							</font>
						</div>
					</td>
					<td class="tdfont" width="10%" >
							<div align="center">
								<a style="cursor:pointer" onclick="showPrevDrugSchedule(<%=ind %>,'PREVDRUGSHEDULE')" >
								VIEW
								</a>
								<html:hidden name="DrugAdministrationFB" property="prevDrugId" value="<%=drugDtlVO.getDrugId() %>"/>
							</div>
						</td>
					<td class="tdfont" width="10%" >
							<div align="center">
								<a style="cursor:pointer" onclick="viewRemarks('<%=(drugDtlVO.getRemarks()==null)?"No Remark":drugDtlVO.getRemarks() %>')" >
								VIEW
								</a>
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
        
        <html:hidden name="DrugAdministrationFB" property="hmode"/>
        <html:hidden name="DrugAdministrationFB" property="scheduleIndex"/>	
	</body>
	<div id="divViewRemarks" style="display: none;">
       			<his:SubTitleTag name="Instruction">
       			</his:SubTitleTag>
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="DrugAdministrationFB" property="showRemarks" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
	<div id="divViewSchidule">	
	<logic:present name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG %>">
	<his:SubTitleTag name="DRUG SHEDULE">
	</his:SubTitleTag>	
			<his:ContentTag>
				<%List lst = (List)session.getAttribute(OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG);
				if(lst!= null && lst.size()!=0)
				{
					%>
			<table width="100%" cellpadding="0" cellspacing="1" >
				<%-- <tr>
					<td class="tdfonthead" width="20%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="shift"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
						<td class="tdfont" width="20%">
							<% String shift="";
									if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.MORNING_SHIFT_ID))
											shift="Morning";
									if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.NOON_SHIFT_ID))
										shift="Noon";
									if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.EVENING_SHIFT_ID))
										shift="Evening";
									if(drugSheduleDtlVO.getDoseShift().equals(OpdConfig.NIGHT_SHIFT_ID))
										shift="Night";
									
									%>
							<%=shift %>
							
						</td>
					</logic:iterate>
				</tr> --%>
				<tr>
					<td class="tdfonthead" width="20%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="time(HrsMin)"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfont" width="20%">
						<div align="left">
							<%String[] time= drugSheduleDtlVO.getDoseTime().split(":");%>
							<%=time[0] %>
							:
							<%=time[1] %>
						</div>
					</td>
					</logic:iterate>		
				</tr>
				<%--<tr style="display: none;">
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
							<% if(drugSheduleDtlVO.getIsEmptyStomach().equals("1"))	{ %>
							<input type="checkbox"  name="popupIsEmptyStomachArray"   checked="checked" disabled="disabled">
							<% }else { %>
							<input type="checkbox"  name="popupIsEmptyStomachArray" disabled="disabled" >
							<%} %>
						</div>
					</td>
					</logic:iterate> 	
				</tr> --%>	
				<tr>
					<td class="tdfonthead" width="20%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:message key="dosage"/>
							</font>	
						</div>
					</td>
					<logic:iterate id="drugSheduleDtlVO" indexId="idx" name="<%=OpdConfig.PREV_DRUG_SCHEDULE_LIST_FOR_PARTICULAR_DRUG%>" type="hisglobal.vo.DrugSheduleDtlVO">
					<td class="tdfont" width="20%" id="firstDosageId">
						<div align="left">
							<b>
							<%=drugSheduleDtlVO.getDoseName() %>
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
	</logic:present>
	</div>	
	
	<logic:empty name="<%=InpatientConfig.PAT_LAST_VISIT_DRUG_DETAIL_LST%>">
		<table width="100%" cellpadding="0" cellspacing="1" >
					<tr>
						<td>
						<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>No treatment is remaining </b>
						</font>
						</td>
					</tr>
				</table>
	</logic:empty>
</html:form>		