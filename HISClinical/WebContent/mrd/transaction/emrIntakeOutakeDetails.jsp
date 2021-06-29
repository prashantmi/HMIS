<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>


<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<script type="text/javascript">

</script>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<html:form action="/emrDesk">

<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
	<body>
		
			<his:TitleTag name="Patient Outtake Details">
			</his:TitleTag>
		
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="para"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="volume"/>(ML)
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="route"/>
							</font>
						</div>
					</td>
					</tr>
				<logic:present name="<%=MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY %>" >
				<logic:iterate id="patOutTakeDtl" name="<%=MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY %>" type="hisglobal.vo.PatIntakeOutDtlVO">
					<tr>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getInTakeOutTime() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getInTakeOutParaName() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getVolume() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=(patOutTakeDtl.getRouteName()==null?"-":patOutTakeDtl.getRouteName()) %>
								<%--<bean:write name="patOutTakeDtl" property="routeName"/> --%>
								</font>
							</div>
						</td>
						
					</tr>	
				</logic:iterate>
					</table>	
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
									&nbsp;&nbsp;&nbsp;<bean:write name="EmrCommonDeskFB" property="totalViewOuttake"/>  ML
								</b>	
							</div>
						</td>	
					</tr>
				</table>
				</logic:present>
				
		
			
		 <logic:notPresent name="<%=MrdConfig.PAT_OUTTAKE_DETAIL_ARRAY%>" >
    		<his:ContentTag>
    	
				<tr>
					<td class="tdfont" width="100%"  nowrap>
						<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="noOutakeDetailsFound" /></b></font></div></td>
				</tr>
		
			</his:ContentTag>
    	</logic:notPresent>
		
		</his:ContentTag>
		
			<his:TitleTag name="Patient Intake Details">
			</his:TitleTag>
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
				<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="para"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="volume"/>(ML)
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="route"/>
							</font>
						</div>
					</td>
				</tr>
				<logic:present name="<%=MrdConfig.PAT_INTAKE_DETAIL_ARRAY%>">
				<logic:iterate id="patOutTakeDtl" name="<%=MrdConfig.PAT_INTAKE_DETAIL_ARRAY %>" type="hisglobal.vo.PatIntakeOutDtlVO">
					<tr>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getInTakeOutTime() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getInTakeOutParaName() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=patOutTakeDtl.getVolume() %>
								</font>
							</div>
						</td>
						<td class="tdfont" width="25%" >
							<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%=(patOutTakeDtl.getRouteName()==null?"-":patOutTakeDtl.getRouteName()) %>
								
								</font>
							</div>
						</td>
						
					</tr>	
				</logic:iterate>	
					</table>
			
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
									&nbsp;&nbsp;&nbsp;<bean:write name="EmrCommonDeskFB" property="totalViewIntake"/>  ML
								</b>
							</div>
						</td>	
					</tr>
				</table>
				</logic:present>
				
			 <logic:notPresent name="<%=MrdConfig.PAT_INTAKE_DETAIL_ARRAY%>" >
    		<his:ContentTag>
    	
				<tr>
					<td class="tdfont" width="100%"  nowrap>
					<div align="center">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noIntakeDetailsFound" /></b></font></div></td>
				</tr>
			</his:ContentTag>
    	</logic:notPresent>
		</his:ContentTag>
					
		

    
		
	</body>
<his:status/>
</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 