<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="inpatient.InpatientConfig"%>
<%@page import="java.util.List"%>
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
<html:form action="/antenatalNeonatalDetail">
	
	<his:TitleTag key="newnatalapgardtl">
	</his:TitleTag>

	<his:statusTransactionInProcess>
	<his:ContentTag>
		<logic:notEmpty name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>">
			<%
				List lstApgarDtl = (List) session.getAttribute(InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST);
				String width = (75/lstApgarDtl.size())+"%";
			%>
			<table width="100%" align="center" border="0" cellpadding="2" cellspacing="1">
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgarTime"/></b>
							</font>		
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="apgarTimeDesc"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="activityscore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="activityApgar"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgaractivity"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="activity"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="pulsescore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="heartRateApgar"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgarpulse"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="heartRate"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="grimacescore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="grimaceApgar"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgargrimace"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="grimace"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="appearancescore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="colorApgar"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgarappearance"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="color"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="respirationscore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="respirationApgar"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="apgarrespiration"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfont" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="respiration"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
				<tr>
					<td width="25%" class="tdfonthead" nowrap="nowrap">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:message key="tot"/> <bean:message key="apgarscore"/></b>
							</font>
						</div>
					</td>
				<logic:iterate id="apgarDlVO" name="<%=InpatientConfig.ANCDETAIL_SELECTED_NEONAT_APGAR_DETAIL_LIST%>" type="hisglobal.vo.ANCNeonatalApgarVO">
					<td width="<%=width%>" class="tdfonthead" nowrap="nowrap">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b><bean:write name="apgarDlVO" property="apgarScore"/></b>
							</font>
						</div>
					</td>
				</logic:iterate>
				</tr>
			</table>
		</logic:notEmpty>
	</his:ContentTag>
	</his:statusTransactionInProcess>
	
	<his:ButtonToolBarTag>
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' style="cursor:pointer" tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
	</his:ButtonToolBarTag>

	<his:status/>
</html:form>
</body>
</html>