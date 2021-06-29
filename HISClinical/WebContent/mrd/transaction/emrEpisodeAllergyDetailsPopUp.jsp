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
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="hisglobal.vo.EpisodeAlertDtlVO"%>
<%@page import="hisglobal.vo.EpisodeAllergiesVO"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script type="text/javascript">
function closeForm()
{
	self.close();
}
</script>
	<%@page import="mrd.MrdConfig"%>

	<body>
		<his:TitleTag>
			<his:name>
				<bean:message key="allergyDtl"/>
			</his:name>
		</his:TitleTag>
		
		
		<%
			EpisodeAllergiesVO allAllergyDtlVO=null;
			String selectedIndex=request.getParameter("index");
		%>
	
			
		
		<logic:iterate id="tempAllergyDetailVO" name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" type="hisglobal.vo.EpisodeAllergiesVO" indexId="index" >
			
			<%if(index.toString().equals(selectedIndex))
			{
				allAllergyDtlVO=tempAllergyDetailVO;
			}	
			%>
		
		</logic:iterate>
		
		<his:ContentTag>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="allergyName"/>
								</b>	
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
							&nbsp; <%=(allAllergyDtlVO.getAllergyName()==null)?"-":allAllergyDtlVO.getAllergyName()  %>
						</div>
					</td>
					
					<td class="tdfonthead" width="25%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>
									<bean:message key="allergyType"/>
								</b>	
							</font>	
						</div>
					</td>
					<td class="tdfont" width="25%">
						<div align="left">
						&nbsp; <%=(allAllergyDtlVO.getAllergyTypeName()==null)?"-":allAllergyDtlVO.getAllergyTypeName()  %>
						</div>
					</td>
				</tr>
			</table>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</div>
					</td>
					
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="symptoms"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="allergySite"/>
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
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
							</font>
						</div>
					</td>
				</tr>
				
				
					<tr>
						<td class="tdfont" width="20%" >
							<div align="center">
								<%=allAllergyDtlVO.getEntryDate()  %>
							</div>
						</td>
						<td class="tdfont"  width="20%">
							<div align="center">
								<%=(allAllergyDtlVO.getAllergySymtoms()==null)?"-":allAllergyDtlVO.getAllergySymtoms()  %>
							</div>
						</td>	
						<td class="tdfont"  width="20%">
							<div align="center">
								<%=(allAllergyDtlVO.getAllergySite()==null)?"-":allAllergyDtlVO.getAllergySite()  %>
							</div>
						</td>
						<td class="tdfont"  width="20%">
							<div align="center">
								<%=(allAllergyDtlVO.getRemarks()==null)?"-":allAllergyDtlVO.getRemarks()  %>
							</div>
						</td>	
						<td class="tdfont"  width="20%">
							<div align="center">
								<%=(allAllergyDtlVO.getDuration()==null)?"-":allAllergyDtlVO.getDuration()  %>
							</div>
						</td>
					</tr>
				
			</table>
		</his:ContentTag>	
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
	</body>
</html>