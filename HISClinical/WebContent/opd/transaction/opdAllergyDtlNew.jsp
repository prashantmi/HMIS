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

function viewRemarks(obj)
{
	document.getElementsByName("viewRemarks")[0].value=obj;
	document.getElementById("divViewNotes").style.display="block"; 
}
</script>
	<html:form action="/opdEpisodeAllergies" >
	<body>
		<his:TitleTag>
			<his:name>
				<bean:message key="allergyDtl"/>
			</his:name>
		</his:TitleTag>
		
		<his:ContentTag>
			<%-- <table width="100%" cellpadding="0" cellspacing="1">
				<tr>
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
							&nbsp;<bean:write name="OpdEpisodeAllergyFB" property="selectedAllergyTypeName"/>
						</div>
					</td>
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
							&nbsp;<bean:write name="OpdEpisodeAllergyFB" property="selectedAllergyName"/>
						</div>
					</td>
				</tr>
			</table> --%>
			<table width="100%" cellpadding="0" cellspacing="1">
				<tr>
					<td width="15%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
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
					<td width="25%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="duration"/>
							</font>
						</div>
					</td>
					<td width="20%" class="addtoolbar" style="border-top:outset black 2px; border-bottom:inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="advice"/>
							</font>
						</div>
					</td>
				</tr>
				
				<logic:iterate id="allAllergyDtlVO" name="<%=OpdConfig.ARRAY_ALL_ALLERGY_DETAIL_VO%>" indexId="idx" type="hisglobal.vo.EpisodeAllergiesVO">
					<tr>
						<td class="tdfont" width="15%" >
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
						<td class="tdfont"  width="25%">
							<div align="center">
								<%=(allAllergyDtlVO.getDurationDays()==null)?"-":allAllergyDtlVO.getDurationDays()  %>
							</div>
						</td>
						<td class="tdfont"  width="20%">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="View Notes" 
								onclick="viewRemarks('<%=(allAllergyDtlVO.getAdvice()==null)?"No Advice":allAllergyDtlVO.getAdvice()  %>')" 
								onkeypress="if(event.keyCode==13) viewRemarks('<%=(allAllergyDtlVO.getAdvice()==null)?"No Advice":allAllergyDtlVO.getAdvice()  %>')">								
							</div>
						</td>
						<%-- <td class="tdfont"  width="20%">
							<div align="center">
								<img class="button" src='<his:path src="/hisglobal/images/icon-vrf.png"/>' style='cursor:pointer' title="View Notes" 
								onclick="viewRemarks('<%=(allAllergyDtlVO.getRemarks()==null)?"No Remark":allAllergyDtlVO.getRemarks()  %>')" 
								onkeypress="if(event.keyCode==13) viewRemarks('<%=(allAllergyDtlVO.getRemarks()==null)?"No Remark":allAllergyDtlVO.getRemarks()  %>')">								
							</div>
						</td>	 --%>
					</tr>
				</logic:iterate>
			</table>
		</his:ContentTag>	
		
		<his:ButtonToolBarTag>
			<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick ="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
        </his:ButtonToolBarTag>
        
        	<div id="divViewNotes" style="display: none;">
		        <his:ContentTag>
					<table width="100%" cellpadding="0" cellspacing="1">
						<tr>
							<td width="100%">
								<div align="center">
									<html:textarea name="OpdEpisodeAllergyFB" property="viewRemarks" rows="5" cols="110" readonly="true"></html:textarea>
								</div>
							</td>
						</tr>
					</table>
				</his:ContentTag>		
			</div>
			
			 
	</body>
</html:form>