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

<%@page import="opd.OpdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}

function showRemarks(obj)
{
	document.getElementsByName("showRemarksTextArea")[0].value=obj;
	document.getElementById("divRemarks").style.display="block";
}
</script>
	<html:form action="/opdDiagnosis" >
	<body>
		<his:TitleTag>
			<his:name>
				<bean:message key="allPrevDiag"/>
			</his:name>
		</his:TitleTag>
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="visitDate"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagnosisCode"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="40%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagnosisName"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagnosisCodeType"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagonosisType"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="diagnosisSite"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="8%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="status"/>
								</b>	
							</font>
						</div>
					</td>
					<td width="7%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="remarks"/>
								</b>	
							</font>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="allPrevDiagnosis" name="<%=OpdConfig.PREVIOUS_ALL_DIAGNOSIS_VO%>" indexId="idx" type="hisglobal.vo.EpisodeDiagnosisVO">
					<tr>
						<td class="tdfontheadExam" width="10%" >
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=allPrevDiagnosis.getEntryDate()  %>
								</font>	
							</div>
						</td>
						<td class="tdfontheadExam"  width="7%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=allPrevDiagnosis.getDiagnosticCode()  %>
								</font>	
							</div>
						</td>	
						<td class="tdfontheadExam"  width="40%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=allPrevDiagnosis.getDignosisName()  %>
								</font>	
							</div>
						</td>	
						<td class="tdfontheadExam"  width="8%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=allPrevDiagnosis.getDiagnosisCodeLabel()  %>
								</font>	
							</div>
						</td>	
						<td class="tdfontheadExam"  width="10%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=allPrevDiagnosis.getDiagnosticTypeName()  %>
								</font>	
							</div>
						</td>
						<td class="tdfontheadExam"  width="10%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%= allPrevDiagnosis.getDiagnosisSiteLabel()==null?"-": allPrevDiagnosis.getDiagnosisSiteLabel() %>
								</font>	
							</div>
						</td>
						<td class="tdfontheadExam"  width="8%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=OpdConfig.DIAGNOSIS_IS_REPEAT_ARRAY[Integer.parseInt(allPrevDiagnosis.getIsRepeat())] %>
								</font>	
							</div>
						</td>	
						<td class="tdfontheadExam"  width="7%">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"><b>
									<%String str=((String) allPrevDiagnosis.getRemarks()==null?"No Remarks":(String) allPrevDiagnosis.getRemarks()); %>
										<a style="cursor: pointer;" onclick="showRemarks('<%=str %>')">VIEW</a></b>
								</font>	
							</div>
						</td>	
					</tr>
					
				</logic:iterate>
			</table>
		</his:ContentTag>	
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        <div id="divRemarks" style="display: none;">
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="100%" class="tdfont" >
						<div align="center">
							<html:textarea name="OpdDiagnosisFB" property="showRemarksTextArea" rows="3" cols="150" readonly="true">
							</html:textarea>
						</div>	
					</td>
				</tr>
			</table>	
		</his:ContentTag>
	</div>
	<html:hidden name="OpdDiagnosisFB" property="showRemarksTextArea"/>
	</body>
</html:form>