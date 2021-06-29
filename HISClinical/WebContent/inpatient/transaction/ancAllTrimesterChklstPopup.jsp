<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
</head>

<body>
<html:form action="/ancTrimesterChecklistDetail">
	
	<his:TitleTag key="allTriWiseChkList">
	</his:TitleTag>

	<his:statusTransactionInProcess>
	<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST%>">
		<his:SubTitleTag key="tri1ChkLst">
		</his:SubTitleTag>
		
		<his:ContentTag>			
			<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="slNo"/>
							</font>
						</div>
					</td>
					<td width="70%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="ancchklstName"/>
							</font>
						</div>
					</td>					
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="compulsoryFlagDesc"/>
							</font>
						</div>
					</td>
				</tr>
				<%	int i=1; %>
				<logic:iterate id="chklistVO" indexId="indx" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST%>" type="hisglobal.vo.ANCTrimesterChecklistMasterVO">
					<logic:equal name="chklistVO" property="trimester" value="<%=InpatientConfig.TRIMESTER_ONE%>">
					<tr>
						<td width="10%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=Integer.toString(i++)%>.</b>
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="checklistName"/></b>
								</font>
							</div>
						</td>					
						<td width="15%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="compulsoryFlagDesc"/></b>
								</font>
							</div>
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			</table>
		</his:ContentTag>

		<his:SubTitleTag key="tri2ChkLst">
		</his:SubTitleTag>
		
		<his:ContentTag>			
			<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="slNo"/>
							</font>
						</div>
					</td>
					<td width="70%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="ancchklstName"/>
							</font>
						</div>
					</td>					
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="compulsoryFlagDesc"/>
							</font>
						</div>
					</td>
				</tr>
				<% int j=1; %>
				<logic:iterate id="chklistVO" indexId="indx" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST%>" type="hisglobal.vo.ANCTrimesterChecklistMasterVO">
					<logic:equal name="chklistVO" property="trimester" value="<%=InpatientConfig.TRIMESTER_TWO%>">
					<tr>
						<td width="10%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=Integer.toString(j++)%>.</b>
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="checklistName"/></b>
								</font>
							</div>
						</td>					
						<td width="15%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="compulsoryFlagDesc"/></b>
								</font>
							</div>
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			</table>
		</his:ContentTag>

		<his:SubTitleTag key="tri3ChkLst">
		</his:SubTitleTag>
		
		<his:ContentTag>			
			<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="10%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="slNo"/>
							</font>
						</div>
					</td>
					<td width="70%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="ancchklstName"/>
							</font>
						</div>
					</td>					
					<td width="15%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="compulsoryFlagDesc"/>
							</font>
						</div>
					</td>
				</tr>
				<%	int k=1; %>
				<logic:iterate id="chklistVO" indexId="indx" name="<%=InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST%>" type="hisglobal.vo.ANCTrimesterChecklistMasterVO">
					<logic:equal name="chklistVO" property="trimester" value="<%=InpatientConfig.TRIMESTER_THREE%>">
					<tr>
						<td width="10%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><%=Integer.toString(k++)%>.</b>
								</font>
							</div>
						</td>
						<td width="70%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="checklistName"/></b>
								</font>
							</div>
						</td>					
						<td width="15%" class="tdfont" nowrap="nowrap">
							<div align="center">
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<b><bean:write name="chklistVO" property="compulsoryFlagDesc"/></b>
								</font>
							</div>
						</td>
					</tr>
					</logic:equal>
				</logic:iterate>
			</table>
		</his:ContentTag>
	</logic:notEmpty>
	</his:statusTransactionInProcess>
	
	<his:status/>
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
	</his:ButtonToolBarTag>
</html:form>
</body>
</html>