<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>
<his:javascript src="/registration/js/popup.js" />
<%-- <his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/> --%>
<%@page import="mrd.MrdConfig"%>
<script type="text/javascript">
function getAllergyDetail(event,path)
{
	openPopup(createFHashAjaxQuery(path),event,300,600);
}
function showLegends(){
  document.getElementById("divLegends").style.display=""; 
}
function showLegendsNone(){
  document.getElementById("divLegends").style.display="none";
}

</script>

<html:form action="/emrDesk">



<jsp:include page="/mrd/transaction/emrHeader.jsp" flush="true"></jsp:include>

<his:TitleTag name="Allegies Detail" >
</his:TitleTag>
<his:ContentTag>
<%String indexEpisodeOpen="-1"; %>
			
			
				<table width="100%" border="0"  cellspacing="1" cellpadding="0">
					<tr>
								
						<td width="13%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="diagnosisDate"/>
							</font>
						</div>
						</td>
							<td width="30%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">	           
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="allergyName"/>
									</font>
								</div>
							</td>
							
							<td width="12%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">	   
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="allergyType"/>
									</font>
								</div>
							</td>
							
							<td width="15%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">	           
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="duration"/>
									</font>
								</div>
							</td>
								<td width="10%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
								<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<bean:message key="symptoms"/>
									</font>
								</div>
							</td>
							<td width="10%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergySite"/>
										</font>
									</div>
							</td>
							<td width="10%"  class="tdfonthead" style="border-top: outset black 2px;border-bottom: inset black 2px;">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="sensitivity"/>
										</font>
									</div>
							</td>
							</tr>
							<logic:present name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
							<logic:iterate name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" id="patAllergyDetailVO" indexId="index" type="hisglobal.vo.EpisodeAllergiesVO">
						<%--	<logic:equal name="patAllergyDetailVO" property="episodeIsOpen" value="1"> --%>
								<tr>
									<%String color="#000000"; %>
										<logic:equal name="patAllergyDetailVO"  property="isRevoked" value="0">
										<% color="#000000"; %> 
										</logic:equal>
										<logic:equal name="patAllergyDetailVO" property="isRevoked" value="1">
										<% color="#0000FF"; %>
										</logic:equal>
										
									<td class="tdfont" >
										<div align="center">
										
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
										<%=patAllergyDetailVO.getEntryDate()  %>
										</font>
										</div>
									</td>
									
									<td  class="tdfont">
										<div align="center">	           
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="patAllergyDetailVO" property="allergyName" />
	  										</font>
										</div>
									</td>
									<td  class="tdfont">
										<div align="center">	   
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="allergyTypeName"  />
											</font>
										</div>
									</td>
									<td class="tdfont">
										<div align="center">	           
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="duration" />
											</font>
										</div>
									</td>			
									<td class="tdfont" >
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=(patAllergyDetailVO.getAllergySymtoms()==null)?"-":patAllergyDetailVO.getAllergySymtoms()  %>
										</font>
										</div>
									</td>
									<td class="tdfont" >
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<%=(patAllergyDetailVO.getAllergySite()==null)?"-":patAllergyDetailVO.getAllergySite()  %>
										</font>
										</div>
									</td>		  
									<td class="tdfont">
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											
											<%=(patAllergyDetailVO.getSensitivityName()==null)?"-":patAllergyDetailVO.getSensitivityName()  %>
										</font>
										</div>
									</td>		  
								</tr> 
						<%--		</logic:equal> --%>
								<%indexEpisodeOpen=index.toString(); %>
							</logic:iterate>
							<%if(indexEpisodeOpen.equals("-1")){%>
								<his:ContentTag>
									<tr>
														<td class="tdfont" colspan="5" width="25%" nowrap>
														<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="noAllergiesFound" /></font></div></td>
									</tr>
									</his:ContentTag>
							<%} %>
								</logic:present>	
						</table>
					
						<logic:notPresent name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
						<his:ContentTag>
						
									<tr>
														<td class="tdfont" width="100%" nowrap>
														<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="noAllergiesFound" /></font></div></td>
									</tr>
					
									</his:ContentTag>
						</logic:notPresent>
						
<%--						
<his:SubTitleTag key="closeDuration" >
</his:SubTitleTag>
<%String indexEpisodeClosed="0"; %>
	<logic:present name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
			
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							
							<tr>
								
								<td width="13%"  class="tdfonthead">
						<div align="center">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</div>
					</td>
								<td width="16%"  class="tdfonthead">
									<div align="center">	           
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergyName"/>
										</font>
									</div>
								</td>
								
								<td width="13%"  class="tdfonthead">
									<div align="center">	   
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergyType"/>
										</font>
									</div>
								</td>
								
								<td width="11%"  class="tdfonthead">
									<div align="center">	           
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="duration"/>
										</font>
									</div>
								</td>
									<td width="22%"  class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="symptoms"/>
										</font>
									</div>
							</td>
							<td width="21%"  class="tdfonthead">
									<div align="center">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergySite"/>
										</font>
									</div>
							</td>
							</tr>
							<logic:iterate name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" id="patAllergyDetailVO" indexId="index" type="hisglobal.vo.EpisodeAllergiesVO">
								<logic:equal name="patAllergyDetailVO" property="episodeIsOpen" value="0">
								<tr>
								
									<%String color="#000000"; %>
										<logic:equal name="patAllergyDetailVO"  property="isRevoked" value="0">
										<% color="#0000FF"; %>
										</logic:equal>
										<logic:equal name="patAllergyDetailVO" property="isRevoked" value="1">
										<% color="#000000"; %>
										</logic:equal>
									
									<td class="tdfont" width="13%" >
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=patAllergyDetailVO.getEntryDate()  %>
											</font>
										</div>
									</td>
									
									<td width="16%"  class="tdfont">
										<div align="center">	           
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:write name="patAllergyDetailVO" property="allergyName" />
	  										</font>
										</div>
									</td>
									<td width="13%"  class="tdfont">
										<div align="center">	   
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="allergyTypeName" />
											</font>
										</div>
									</td>
									<td width="11%"  class="tdfont">
										<div align="center">	           
											<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="duration" />
											</font>
										</div>
									</td>			
									<td class="tdfont"  width="22%">
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=(patAllergyDetailVO.getAllergySite()==null)?"-":patAllergyDetailVO.getAllergySite()  %>
										</font>
										</div>
									</td>
									<td class="tdfont"  width="21%">
										<div align="center">
										<font color="<%=color%>" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<%=(patAllergyDetailVO.getRemarks()==null)?"-":patAllergyDetailVO.getRemarks()  %>
										</font>
										</div>
									</td>		
								</tr>
								<%indexEpisodeClosed=index.toString(); %>
								</logic:equal>
							</logic:iterate>
							
								<%if(indexEpisodeOpen.equals("0")){%>
								<his:ContentTag>
									<tr>
														<td class="tdfont" colspan="5" width="25%" nowrap>
														<div align="center">
														<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
														<bean:message key="noAllergiesFound" /></font></div></td>
									</tr>
									</his:ContentTag>
							<%} %>
						</table>
						</logic:present>	
						--%>
						
					</his:ContentTag> 
					

<%-- 
<his:ContentTag>
			<logic:present name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" >
						<table width="100%" border="0"  cellspacing="1" cellpadding="0">
							<tr>
								
								
								<td width="33%"  class="tdfonthead">
									<div align="center">	           
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergyName"/>
										</font>
									</div>
								</td>
								
								<td width="33%"  class="tdfonthead">
									<div align="center">	   
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="allergyType"/>
										</font>
									</div>
								</td>
								
								<td width="33%"  class="tdfonthead">
									<div align="center">	           
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<bean:message key="duration"/>
										</font>
									</div>
								</td>
							</tr>
							<logic:iterate name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY%>" id="patAllergyDetailVO" indexId="index" type="hisglobal.vo.EpisodeAllergiesVO">
								<tr>
									<%String path="/HISClinical/mrd/transaction/emrEpisodeAllergyDetailsPopUp.jsp?index="+index.toString(); %>
									
									<td width="20%"  class="tdfont">
										<div align="center">	           
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												
												<a style="cursor:pointer" onclick="getAllergyDetail(event,'<%= path%>')" >	
	  												<bean:write name="patAllergyDetailVO" property="allergyName" />
	  											</a>	
											</font>
										</div>
									</td>
									<td width="20%"  class="tdfont">
										<div align="center">	   
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="allergyTypeName" />
											</font>
										</div>
									</td>
									<td width="15%"  class="tdfont">
										<div align="center">	           
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:write name="patAllergyDetailVO" property="duration" />
											</font>
										</div>
									</td>				
								</tr>
							</logic:iterate>
						</table>
						</logic:present>	
						<logic:notPresent name="<%=MrdConfig.PAT_EPISODE_ALLERGIES_VO_ARRAY %>" >
							<table width = "100%"  border="0" cellpadding="0" cellspacing="1">  
								<tr>
									<td class="tdfonthead" width="25%" nowrap>
									<div align="center">
									<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:message key="noAllergiesFound" /></font></div></td>
								</tr>
						</table> 
						</logic:notPresent>	
					
</his:ContentTag>
--%>

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
			<tr>
				<td width="10%">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
					Black : 
					</font>
				</td>
				<td width="90%">
					<div align="left">
					<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
				
					Active Allergies
		
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
			
					Inactive Allergies
			
					</font>
							</div>
				</td>				
			</tr><!--
			
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
		--></table>
	</his:ContentTag>
	</div>


</html:form>
</html>
<%@include file="/mrd/transaction/emrFooter.jsp"%> 