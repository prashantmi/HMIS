<!--
	@author Pragya Sharma
	Creation Date: 16-Aug-2011
 -->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>

<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script language="JavaScript" src="/HISClinical/hisglobal/js/utilityFunctions.js"></script> 
<script language="JavaScript" src="/HISClinical/registration/reports/report1/js/userWiseCashCollection.js"></script>

<style type="text/css">
@media print { #noPrint { display: none; }} 
</style>

<%
	String sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
	String sysTime = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), " HH : mm ");
%>
	
<his:statusNew>
	<div align="center">
		<b><font color="#FF0000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:write name="UserWiseCashCollectionFB" property="strWarning"/>
			&nbsp;&nbsp;&nbsp;<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">	
		</font></b>
	</div>
</his:statusNew>

<his:statusTransactionInProcess>
<%
	HospitalMstVO voHosp = ControllerUTIL.getHospitalVO(request);
	List lstData = (List) session.getAttribute(RegistrationConfig.RPT_DATA_USER_WISE_CASH_COLLECTION);
	
	if(lstData!=null && lstData.size()>0)
	{
%>
	<div id="noPrint" align="right"> 
		<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
		<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">
	</div>
		
	<div id="pdfPrintingHTMLData">

		<br>
		<div align="right">
			<b><font color="#000000" size="2" face="Verdana">
				Report Date: <%=sysDate%> <%=sysTime%>
			</font></b>
		</div>
		
		
		<br>
		
		<div align="center">
			<b><font color="#000000" size="2" face="Verdana">
				 <%=voHosp.getHospitalName()%>
			</font></b>
		</div>
		<br>
		<div align="center">
			<b><font color="#000000" size="2" face="Verdana">
				 <%=voHosp.getAddress1()+" "+voHosp.getAddress2()+" "+voHosp.getCity()+" "+voHosp.getPinCode()%>
				</font></b>
		</div>
		
		
		<br>
		<div align="center">
			<b><font color="#000000" size="3" face="Verdana">
				REGISTRATION CASH COLLECTION USER WISE REPORT
			</font></b>
			<br>
			<b><font color="#000000" size="2" face="Verdana">For User : </font></b><font color="#000000" size="2" face="Verdana"><bean:write name="UserWiseCashCollectionFB" property="userName"/></font>
			<br>
			<b><font color="#000000" size="2" face="Verdana">From Date : </font></b><font color="#000000" size="2" face="Verdana"><bean:write name="UserWiseCashCollectionFB" property="fromDate"/>&nbsp;&nbsp;</font>
			<b><font color="#000000" size="2" face="Verdana">To Date : </font></b><font color="#000000" size="2" face="Verdana"><bean:write name="UserWiseCashCollectionFB" property="toDate"/></font>
		</div>		

		
		
		<% int ntimeList = 0; %>
		<table width="100%" align="center" cellspacing="0" cellpadding="0" border="1" bordercolor="#000000">
			<tr>
				<td align="center" valign="middle" width="10%">
					<table width="100%" align="center" cellspacing="1" cellpadding="0">
						<tr>
							<td valign="middle" width="100%" style="border-bottom: solid #000000 2px;">
								<div align="center">
									<b><font color="#000000" size="2" face="Verdana">
										Patient Category
									</font></b>
								</div>
							</td>
						</tr>
						<tr>
							<td valign="middle" width="100%" >
								<div align="center">
									<b><font color="#000000" size="2" face="Verdana">
										User Name
									</font></b>
								</div>
							</td>
						</tr>
					</table>
				</td>
				
				<logic:iterate id="voPriCat" indexId="idx" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_SHORT_NAMES%>" type="hisglobal.vo.PatientCategoryVO">
					<%ntimeList++; %>	
					<td valign="middle" width="8%">
						<div align="center">
							<b><font color="#000000" size="2" face="Verdana"><%=voPriCat.getPatCatShortName()%></font></b>
						</div>
					</td>
				</logic:iterate>
				
				<td valign="middle" width="10%">
					<div align="center">
						<b><font color="#000000" size="2" face="Verdana">
							Total No. of Patients
						</font></b>
					</div>
				</td>
				<td valign="middle" width="10%">
					<div align="center">
						<b><font color="#000000" size="2" face="Verdana">
							Total Cash Collected
						</font></b>
					</div>
				</td>
			</tr>
		<%
			int[] arrTotal = new int[ntimeList];
			for(int i=0;i<ntimeList;i++)	arrTotal[i]=0;
			int totalPatiens=0, totalAmount = 0; 
			for(int i=0; i<lstData.size(); i++)
			{
				PatientVO vo = (PatientVO) lstData.get(i);
				boolean bSameUser = true;
				int totalPatSum=0, totalCashSum=0;
		%>
				<tr>
					<td valign="top" width="5%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana">
								<%=vo.getSeatId()%>
							</font>
						</div>
					</td>
					<logic:iterate id="voPriCat" indexId="idx" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_SHORT_NAMES%>" type="hisglobal.vo.PatientCategoryVO">
					<%
						int totalPat=0;
						if(bSameUser && vo.getPatCatCode().equalsIgnoreCase(voPriCat.getPatCatCode()))
						{
							totalPat = Integer.parseInt(vo.getPatCrNo());
							totalPatSum += totalPat;
							totalCashSum += Integer.parseInt(vo.getPatAmountCollected());
							
							boolean bSameCategory = true;											
							while(bSameCategory)
							{
								if((i+1)!=lstData.size())
								{
									PatientVO voNext = (PatientVO) lstData.get(i+1);
									if(voNext.getSeatId().equalsIgnoreCase(vo.getSeatId())
											&& voNext.getPatCatCode().equalsIgnoreCase(vo.getPatCatCode()))
									{
										i++;
										vo = voNext;
									}
									else if(!voNext.getSeatId().equalsIgnoreCase(vo.getSeatId()))
									{
										bSameCategory = false;
										bSameUser = false;
									}
									else if(!voNext.getPatCatCode().equalsIgnoreCase(vo.getPatCatCode()))
										bSameCategory = false;
									else
									{
										bSameCategory = false;
										bSameUser = false;
									}
								}
								else
								{
									bSameCategory = false;
									bSameUser = false;
								}
								if(bSameCategory)
								{
									totalPat += Integer.parseInt(vo.getPatCrNo());
									totalPatSum += totalPat;
									totalCashSum += Integer.parseInt(vo.getPatAmountCollected());
								}
							}
							
							if((i+1)!=lstData.size())
							{
								PatientVO voNext = (PatientVO) lstData.get(i+1);
								if(voNext.getSeatId().equalsIgnoreCase(vo.getSeatId()))
								{
									i++;
									vo = voNext;
								}
								else
								{
									bSameCategory = false;
									bSameUser = false;
								}
							}
						}
					%>
					<td width="8%" valign="top">
						<div align="right">
							<font color="#000000" size="2" face="Verdana">
								<%=Integer.toString(totalPat)%>
								<%	arrTotal[idx.intValue()]+=totalPat;%>
							</font>
						</div>
					</td>
					</logic:iterate>
					<td valign="top" width="10%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana">
								<%=Integer.toString(totalPatSum)%>
								<%	totalPatiens+=totalPatSum; %>
							</font>
						</div>
					</td>
					<td valign="top" width="10%">
						<div align="right">
							<font color="#000000" size="2" face="Verdana">
								<%=Integer.toString(totalCashSum)%>
								<%	totalAmount+=totalCashSum; %>
							</font>
						</div>
					</td>
				</tr>
		<%
				if((i+1)!=lstData.size())
				{
					PatientVO voNext = (PatientVO) lstData.get(i+1);
					if(!voNext.getSeatId().equalsIgnoreCase(vo.getSeatId()))
					{
						bSameUser = false;
					}
				}
				else
					bSameUser = false;
			}
		%>
			<tr>
				<td align="center" valign="middle" width="10%">
					<div align="left">
						<b><font color="#000000" size="2" face="Verdana">
							Total
						</font></b>
					</div>
				</td>
				<logic:iterate id="voPriCat" indexId="idx" name="<%=RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY_SHORT_NAMES%>" type="hisglobal.vo.PatientCategoryVO">
					<td valign="middle" width="8%">
						<div align="right">
							<b><font color="#000000" size="2" face="Verdana">
							<%=Integer.toString(arrTotal[idx.intValue()])%>
							</font></b>
						</div>
					</td>
				</logic:iterate>
				
				<td valign="middle" width="10%">
					<div align="right">
						<b><font color="#000000" size="2" face="Verdana">
							<%=Integer.toString(totalPatiens)%>
						</font></b>
					</div>
				</td>
				<td valign="middle" width="10%">
					<div align="right">
						<b><font color="#000000" size="2" face="Verdana">
							<%=Integer.toString(totalAmount)%>
						</font></b>
					</div>
				</td>
			</tr>
		</table>
	</div>
<%	
	}
	else
	{
%>
	<div align="center">
		<b><font color="#FF0000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
			<bean:write name="UserWiseCashCollectionFB" property="strWarning"/>
			&nbsp;&nbsp;&nbsp;<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="submitPage('NEW')" onkeypress="if(event.keyCode==13) submitPage('NEW')">	
		</font></b>
	</div>
<%
	}
%>

</his:statusTransactionInProcess>

<html:hidden name="UserWiseCashCollectionFB" property="reportMode"/>            
<html:hidden name="UserWiseCashCollectionFB" property="reportType"/>            
<%--<html:hidden name="UserWiseCashCollectionFB" property="htmlCode"/>--%>            

<input type="hidden"  name="mode" value="USERWISECASHCOLLECTION">


<%@page import="registration.RegistrationConfig"%>
<%@page import="hisglobal.vo.PatientVO"%>
