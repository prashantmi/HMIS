<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.vo.MortuaryExtLabRequestDtlVO"%>
<%@page import="mortuary.MortuaryConfig"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<his:css src="/hisglobal/css/tab.css"/>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>


<script type="text/javascript">

function closeForm()
{
	self.close();
}
</script>

<html:form action="/opinionApproval">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
	<body>
		<his:SubTitleTag name="Received Lab Test Report">
		</his:SubTitleTag>
		<%MortuaryExtLabRequestDtlVO[] extVO=(MortuaryExtLabRequestDtlVO[])session.getAttribute(MortuaryConfig.ARR_RECEIVED_REPORT_BY_POSTMORTEM_NO_VO);
			if(extVO!=null){ %>
				
				<%Map mapReqId=(Map)session.getAttribute(MortuaryConfig.MAP_REQUEST_ID_N_FINAL_RESULT_BY_POSTMORTEM_NO); 
				Iterator mapKeyItr = mapReqId.keySet().iterator();
				while(mapKeyItr.hasNext())
				{
					String key = (String)mapKeyItr.next();
					MortuaryExtLabRequestDtlVO vo=(MortuaryExtLabRequestDtlVO)mapReqId.get(key);
					
				%>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="requestDate"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=vo.getRequestDate() %>
								</div>
							</td>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="extLab"/>
										<bean:message key="name"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont">
								<div align="left">
									&nbsp;<%=vo.getExtLabName() %>
								</div>
							</td>
						</tr>
						<%if(vo.getStatusCode().equals(MortuaryConfig.EXTERNAL_LAB_REQUESTE_STATUS_REPORT_RECEIVED)){ %>
						<tr>
							<td width="25%" class="tdfonthead" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="investigation"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfonthead" colspan="3" style="border-top:outset black 2px; border-bottom:inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="result"/>
									</font>
								</div>
							</td>
						</tr>	
						<%Map mapLabId=(Map)session.getAttribute(MortuaryConfig.MAP_REQUEST_ID_N_LAB_TEST_BY_POSTMORTEM_NO); 
						Iterator mapLabKeyItr = mapLabId.keySet().iterator();
						while(mapLabKeyItr.hasNext())
						{
							String labKey = (String)mapLabKeyItr.next();
							if(labKey.equals(key))
							{
								List<MortuaryExtLabRequestDtlVO> lst=(List<MortuaryExtLabRequestDtlVO>)mapLabId.get(labKey);
								for(int i=0;i<lst.size();i++)
								{
						%>
							<tr>
								<td width="25%" class="tdfont">
									<div align="center">
										<%=lst.get(i).getExtLabTest()%>
									</div>
								</td>
								<td width="25%" class="tdfont" colspan="3">
									<div align="center">
										<%=lst.get(i).getExtLabTestResult()%>
									</div>
								</td>
							</tr>
						<%}}} %>
						<tr>
							<td width="25%" class="tdfonthead">
								<div align="right">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="final"/>
										<bean:message key="result"/>
									</font>
								</div>
							</td>
							<td width="25%" class="tdfont" colspan="3">
								<div align="left">
									&nbsp;<%=vo.getResult() %>
								</div>
							</td>
						</tr>
						<%}else{ %>
							<tr>
								<td width="25%" class="tdfont" colspan="4">
									<div align="center">
										<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=vo.getStatus() %>
										</font>
									</div>	
								</td>
							</tr>
						<%} %>		
					</table>	
				</his:ContentTag>
				
				<%} %>
			<%}else{ %>
				<his:ContentTag>
					<table width="100%" border="0"  cellspacing="1" cellpadding="0">
						<tr>
							<td width="100%" class="tdfont">
								<div align="center">
									<font color="#FF0000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										No Sample Send To External Lab
									</font>
								</div>	
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			<%} %>
			<his:ButtonToolBarTag>
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>' tabindex="1"  style=cursor:pointer onkeypress="if(event.keyCode==13) closeForm();" tabindex="1" onclick ="closeForm();">
			</his:ButtonToolBarTag>	
	</body>
		
</html:form>