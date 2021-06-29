<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.OpdConfig"%>
<%@page import="mrd.MrdConfig"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
	function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}
	
</script>
	


<html:form action="/emrDesk">
<%int index=0; %>
	<body>
		<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>
		<his:TitleTag name="Chronic Disease">
</his:TitleTag>	
		<logic:present name="<%=MrdConfig.PAT_CHRONIC_DISEASE_ARRAY %>" >
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					
					<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="chronicDisease"/>
							</font>
						</div>
					</td>
					<td width="25%" class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
								
							</font>
						</div>
					</td>
					<%-- <td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="entryDate"/>
								
							</font>
						</div>
					</td>
					<td width="20%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="enterBy"/>
								
							</font>
						</div>
					</td>
					
	   			<td width="15%" class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="remarks"/>
							</font>
						</div>
					</td>
				--%>
				</tr>
				
				
				<logic:iterate name="<%= MrdConfig.PAT_CHRONIC_DISEASE_ARRAY%>" id="arrAllAlert" type="hisglobal.vo.PatientAlertsDetailVO">
					<%String color="#000000";  %>
					<%if(arrAllAlert.getEffectiveTo()!=null){ %>
					<% color="#0000FF"; %>
					<%} %>
					<tr>
						<td class="tdfont" >
							<div align="center">
							  <b>
								<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="arrAllAlert" property="alertName"/>
								<%//= arrAllAlert.getAlertName()  %>
								</font>
								</b>
							</div>
						</td>
						<td class="tdfont" >
							<div align="center">
							<b>
								<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<bean:write name="arrAllAlert" property="durationDate"/>
								<%//= arrAllAlert.getDurationDate() %>
								</font>
								</b>
							</div>
						</td>
						<%-- <td class="tdfont" >
							<div align="center">
							<b>
								<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<%//= arrAllAlert.getEntryDate() %>
								<bean:write name="arrAllAlert" property="entryDate"/>
								</font>
								</b>
							</div>
						</td>
						
						<td class="tdfont" >
							<div align="center">
							<b>
								<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<%//= arrAllAlert.getSeatId() %>
								<bean:write name="arrAllAlert" property="seatId"/>
								</font>
								</b>
							</div>
						</td>
						
					<td class="tdfont" >
							<div align="center">
							<b>
								<font color="<%=color%>" size="2"face="Verdana, Arial, Helvetica, sans-serif">
								<%//= (arrAllAlert.getRemarks()==null)?"-":arrAllAlert.getRemarks() %>
								<bean:write name="arrAllAlert" property="remarks"/>
								</font>
								</b>
							</div>
						</td> 
						--%>
					</tr>
					<%index++; %>
				</logic:iterate>
			</table>
		</his:ContentTag>		
		</logic:present>
	
	<%if(index==0){ %>
		<his:ContentTag>
		<table width="100%" >
			<tr>
			<td class="tdfont" width="100%" nowrap>
			 	<div align="center">
				 	<font color="#000000" size="2"face="Verdana, Arial, Helvetica, sans-serif">
					<b>	<bean:message key="noChronicDiseaseFound"/> </b>
					</font>
				</div>
			</td>
			</tr>
		</table>
		</his:ContentTag>
	<%} %>
		
		
		
		<his:SubTitleTag>
		<his:name>
			<bean:message key="legends"/>
		</his:name>
		<table width="100%" cellspacing="0" cellpadding="0">
		<tr>
		<td width="70%"> </td>
			<td width="30%">
			<div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Show </font><img src='<his:path src="/hisglobal/images/arrow_down.gif"/>' style="cursor: pointer;" onclick="showLegends();">		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">Hide	</font><img src='<his:path src="/hisglobal/images/arrow_up.gif"/>' style="cursor: pointer;" onclick="showLegendsNone();">
			</div>
			</td>			
		</tr>
		</table>
	</his:SubTitleTag>
		<div id="divLegends" style="display:block;">
	<his:ContentTag>
		<table width="100%" cellpadding="0" cellspacing="0" style="clear:both; border-left:1px solid #003366; border-top:1px solid #003366">
			<%-- 
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					D :
					</font>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					Day
			
					</font>
							</div>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Wk :
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					Week
			
					</font>
							</div>
				</td>				
			</tr>
			
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Mth :
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					Month
			
					</font>
							</div>
				</td>				
			</tr>
	
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Yr :
					</font>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					Year
		
					</font>
								</div>
				</td>				
			</tr>
			--%>
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Black : 
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					
					Active Chronic Disease
			
					</font>
							</div>
				</td>				
			</tr>
			<tr>
				<td width="10%">
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Blue : 
					</font>
				</td>
				<td width="90%">
				<div align="left">
					<font color="#0000FF" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Inactive Chronic Disease
					</font>
						</div>
				</td>				
			</tr>
		</table>
	</his:ContentTag>
	</div>
	</body>	
	<his:status/>

</html:form>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 