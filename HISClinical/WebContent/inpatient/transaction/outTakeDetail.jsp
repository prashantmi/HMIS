<!-- 
/**
 * @copyright CDAC
 * @developer Hruday Meher
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

function viewRemarks(obj)
{
	document.getElementsByName("viewRemarks")[0].value=obj;
	document.getElementById("divViewNotes").style.display="block"; 
}
</script>

<html:form action="/patientOutTake">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<logic:equal name="OutTakeFB" property="intakeOutMode" value="<%=InpatientConfig.INTAKEOUT_MODE_OUTTAKE %>">
			<his:TitleTag name="Patient Output Deatils">
			</his:TitleTag>
		</logic:equal>
		
		<logic:equal name="OutTakeFB" property="intakeOutMode" value="<%=InpatientConfig.INTAKEOUT_MODE_INTAKE %>">
			<his:TitleTag name="Patient Intake Deatils">
			</his:TitleTag>
		</logic:equal>
	<his:statusTransactionInProcess>	
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</div>
					</td>
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="para"/>
							</font>
						</div>
					</td>
					<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="volume"/>(ML)
							</font>
						</div>
					</td>
					<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="route"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
					
				<logic:iterate id="patOutTakeDtl" name="<%=InpatientConfig.ARR_PATIENT_OUTTAKE_DETAIL %>" type="hisglobal.vo.PatIntakeOutDtlVO">
					<tr>
						<td class="tdfont" width="25%" >
							<div align="center">
								<%=patOutTakeDtl.getInTakeOutTime() %>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
								<%=patOutTakeDtl.getInTakeOutParaName() %>
							</div>
						</td>
						<td class="tdfont" width="15%" >
							<div align="center">
								<%=patOutTakeDtl.getVolume() %>
							</div>
						</td>
						<td class="tdfont" width="15%" >
							<div align="center">
								<%=patOutTakeDtl.getRouteName() %>
							</div>
						</td>
						<td class="tdfont" width="20%" >
							<div align="center">
								
								<a style="cursor:pointer" onclick="viewRemarks('<%=(patOutTakeDtl.getRemarks()==null)?"No Remarks":patOutTakeDtl.getRemarks() %>')" >
								VIEW NOTES	
								</a>
							</div>
						</td>
					</tr>	
				</logic:iterate>	
				</tr>
			</table>
			<logic:equal name="OutTakeFB" property="intakeOutMode" value="<%=InpatientConfig.INTAKEOUT_MODE_OUTTAKE %>">
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="totalOuttake"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<b>
									&nbsp;&nbsp;&nbsp;<bean:write name="OutTakeFB" property="totalViewOuttake"/>  ML
								</b>	
							</div>
						</td>	
					</tr>
				</table>
			</logic:equal>	
			<logic:equal name="OutTakeFB" property="intakeOutMode" value="<%=InpatientConfig.INTAKEOUT_MODE_INTAKE %>">
				<table width="100%" cellpadding="0" cellspacing="1">
					<tr>
						<td width="50%" class="tdfonthead">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b>
										<bean:message key="totalIntake"/>
									</b>	
								</font>
							</div>
						</td>
						<td width="50%" class="tdfont">
							<div align="left">
								<b>
									&nbsp;&nbsp;&nbsp;<bean:write name="OutTakeFB" property="totalViewIntake"/>  ML
								</b>
							</div>
						</td>	
					</tr>
				</table>
			</logic:equal>
		</his:ContentTag>
	</his:statusTransactionInProcess>				
		
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
    </his:ButtonToolBarTag>
    
    		<div id="divViewNotes" style="display: none;">
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="OutTakeFB" property="viewRemarks" rows="5" cols="130" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
    <html:hidden name="OutTakeFB" property="intakeOutMode"/>
		
	</body>
<his:status/>
</html:form>