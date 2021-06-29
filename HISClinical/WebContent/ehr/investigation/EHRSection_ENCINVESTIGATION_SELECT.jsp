<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@page import="opd.OpdConfig"%>
<%@page import="ehr.EHRConfig"%>

<%@page import="ehr.investigation.presentation.EHRSection_InvestigationAdviceFB"%>
<%@page import="ehr.investigation.vo.EHRSection_InvestigationAdviceVO"%>

<his:javascript src="/opd/js/generic_patient_profile.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<style>


</style>
<script type="text/javascript">


</script>

<html>
<table class="table table-condensed table-responsive table-borderless" >
		<tr>
 		<td style="font-size:1.2em;font-weight:bold;"colspan="5">
 		Investigation Details&nbsp;:
		<img id="Image" src="/HISClinical/hisglobal/images/icon_refresh.gif"  style="cursor:pointer;cursor:hand;display:none;"/> 
		</td>
		</tr>
		</table>
<his:ContentTag>

		
		<table class="table table-condensed table-responsive table-borderless" >
	
				<%
				try
				{
					EHRSection_InvestigationAdviceVO[] arrVo=(EHRSection_InvestigationAdviceVO[])session.getAttribute(EHRConfig.EHR_COMPOSITION_SESSION_KEY_LIST_PATIENT_ENCOUNTER_INVESTIGATION);
					int i=0;
					//for (int i=0;i<arrVo.length;i++)
				    
				%>
				
				<tr>
					
				
					<th width='20%' align='left'  >
						<b>
							<!-- <font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif"> -->
							<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="visitDate"/>
							</font>
						</b>
					</th>
					<!-- <th width='10%' align='left'  >
						<b>
							<font color="#126295" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 Type
							</font>
						</b>
					</th> -->
					<!-- <td width='10%' align='center'> -->
					<th width='40%' align='left'>
						<b>
							<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="test"/>
							</font>
						</b>
					</th>
					<th width='26%' align='left'>
						<b>
					<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="result"/>
							</font>
					</b></th>
					
					<th width='10%' align='left'>
						<b>
					<font color="" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<bean:message key="unit"/>
							</font>
					</b></th>
					
					
					
				</tr>
				<%String type = arrVo[i].getInvestigationType();
				if(type.equals("INTERNAL"))
				{ %>
			
			<%		while(i<arrVo.length)
					{
						EHRSection_InvestigationAdviceVO vo = arrVo[i];	
						String visitDate=arrVo[i].getPatvisitdate();
				%>
				<%
						do
						{
							String testName = arrVo[i].getTestName();
							//String type = arrVo[i].getInvestigationType();
				%>
						
						<tr>
							
							<td width='20%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=visitDate%>	
								</font>
							</td>
							<%-- <td width='10%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=type%>	
								</font>
							</td> --%>
							<td width='40%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=testName%>	
								</font>
							</td>
							<td width='26%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
	
								</font>
							</td>
							<td width='5%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</font>
							</td>
							<td width='5%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</font>
							</td>
						</tr>
				<%
							visitDate = "";
							do
							{
				%>
						<tr>
						
							<td width='20%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"></font>
							</td>
							<!-- <td width='10%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</font>
							</td> -->
							<td width='40%' align='left'>
								<i><font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									&nbsp;&nbsp;&nbsp;&nbsp;
									<%=arrVo[i].getParameterName()%>
								</font></i>
							</td>
							<td width='26%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=arrVo[i].getTestValue()%>
								</font>
							</td>
							<td width='5%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=(arrVo[i].getTestUnit()==null ? "":arrVo[i].getTestUnit())%>
								</font>
							</td>
							<td width='5%' align='left'>
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									
								</font>
							</td>
						</tr>
				<%
								i++;
							} while(arrVo[i-1].getPatvisitdate().equals(arrVo[i].getPatvisitdate()) && arrVo[i-1].getTestName().equals(arrVo[i].getTestName()));
						} while(arrVo[i-1].getPatvisitdate().equals(arrVo[i].getPatvisitdate()));
					}
					
				}
				}
				catch(Exception e){
					System.out.print("no investigation report found");
				}%>
			</table> 
			
</his:ContentTag>
		<%-- <%String ExtInvUrl = "/emr/ehrComposition_EXT_INV.cnt?hmode=PATCLINICALDOC_EXT_INV_SELECT"; %>
			<jsp:include page="<%=ExtInvUrl%>" flush="false"  /> --%>
</html>
