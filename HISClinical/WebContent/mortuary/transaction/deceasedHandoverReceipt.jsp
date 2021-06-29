<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="mortuary.MortuaryConfig"%>
<%@page import="hisglobal.vo.DeceasedHandoverDtlVO"%>
<%@page import="hisglobal.vo.HospitalMstVO" %>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>	
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/calendar.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<style>
@media print {
  #printPageButton {
    display: none;
  }
  
}

</style>
<script type="text/javascript">



function submitPage(mode)
{
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

window.onload=function(){
	<% DeceasedHandoverDtlVO VO = (DeceasedHandoverDtlVO)session.getAttribute(MortuaryConfig.DECEASED_HANDOVER_DTL_VO);
	HospitalMstVO HVO = (HospitalMstVO)session.getAttribute(MortuaryConfig.HOSPITAL_MST_VO);
	if(VO.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_HOSPITAL_STAFF))
	{
		VO.setIsHandoverToLabel("Hospital Staff"); %>
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	<%}
	else if (VO.getIsHandoverTo().equals(MortuaryConfig.BODY_HANDOVER_TO_POLICE))
	{
		VO.setIsHandoverToLabel("Police"); %>
		document.getElementById("divBodyHandoverToPolice").style.display="block";
		document.getElementById("divBodyHandoverToRelative").style.display="none";
	<%}
	else
	{
		VO.setIsHandoverToLabel("Relative"); %>
		document.getElementById("divBodyHandoverToPolice").style.display="none";
		document.getElementById("divBodyHandoverToRelative").style.display="block";
	<%}
	if(VO.getBelongingDetail()==null)
			VO.setBelongingDetail("NA"); %>
}

function printReceipt()
{
	//var url="/HISClinical/hisglobal/utility/generictemplate/printingTile.cnt?errorMode=NONE&modePrint=ONLYHTML";
	//child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=300,width=700,left=10,top=10');  
  	//child.moveTo(250,250);
 	//child.focus(); 
 	window.print();
}

function openPrintPopup(url, height, width)
{
	child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 
}

</script>

<body>
	<html:form action="/deceasedHandover">
		<his:TransactionContainer>
		<div id="pdfPrintingHTMLData" align="center">
			<table width="100%">
			<tr>
					<td width="10%" class="tdfonthead">
					<div align="center">
						<img style="width: auto; height: 100px; align:center" src='<his:path src="/hisglobal/images/nims-logo.png"/>'>
						</div>
					</td>
					<td width="90%">
						<table width="100%">
							<tr>
							<td class="tdfonthead" width="100%" align="left">
							<div align="center">
								<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
								<%=HVO.getHospitalName() %>
								</font>
								</div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" width="100%" align="left">
							<div align="center">
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
							<%=HVO.getAddress1() %> </font> </div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" width="100%" align="left">
							<div align="center">
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
							<%=HVO.getAddress2() %>,<%=HVO.getCity() %> - <%=HVO.getPinCode() %>,
							<%=HVO.getStateName() %>, INDIA						</font></div>
							</td>
						</tr>
						<tr>
							<td class="tdfonthead" width="100%" align="left">
							<div align="center">
							<font color="#000000" size="3" face="Verdana, Arial, Helvetica, sans-serif">
							Phone:<%=HVO.getPhone() %> Fax:<%=HVO.getFax() %> </font></div>
							</td>
						</tr>
						</table>
					</td>
					</tr>
						<tr>
							<td  width="100%" align="center" colspan=2>
							<div align="center">
							<font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" weight="bold">
							&nbsp;
							</font> </div>
							</td>
						</tr>
					</tr>
						<tr>
							<td class="tdfonthead" width="100%" align="center" colspan=2>
							<div align="center">
							<font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" weight="bold">
							Deceased Handover Receipt
							</font> </div>
							</td>
						</tr>
						</tr>
						<tr>
							<td width="100%" align="center" colspan=2>
							<div align="center">
							<font color="#000000" size="4" face="Verdana, Arial, Helvetica, sans-serif" weight="bold">
							&nbsp;
							</font> </div>
							</td>
						</tr>
			</table>
			
				<jsp:include page="/mortuary/deceasedTile.cnt" flush="true" />
				
				
				
					<his:SubTitleTag name="Deceased Handover Detail">
					</his:SubTitleTag>
					
					
						<table width="100%" >
							<tr>
								<td width="25%" class="tdfonthead">
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">											
											<bean:message key="handoverto"/>
										</font>
									</div>
								</td>
								<td width="25%" class="tdfont">
									<div align="left">
										<%=VO.getIsHandoverToLabel() %>
									</div>
								</td>
								
								<td width="25%" class="tdfonthead" >
									<div align="right">
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">											
											<bean:message key="itemDesc"/>
										</font>
										
									</div>
								</td>
								<td width="25%" class="tdfont" >
									<div align="left">
										<%=VO.getBelongingDetail() %>
									</div>
								</td>
								
							</tr>
						</table>
						<div id="divBodyHandoverToRelative" style="display: none;">						
								<table width="100%" border="0"  cellspacing="1" cellpadding="0">
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativename"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=VO.getHandoverToAddress() %>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="realtionship"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=VO.getRelationName() %>
											</div>
										</td>
									</tr>
									<tr>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativeaddress"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=VO.getHandoverToAddress() %>
											</div>
										</td>
										<td width="25%" class="tdfonthead">
											<div align="right">
												<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
													<bean:message key="relativeContactNo"/>
												</font>
											</div>
										</td>
										<td width="25%" class="tdfont">
											<div align="left">
												<%=VO.getHandoverToPhone() %>
											</div>
										</td>
									</tr>
									<tr>
								<td colspan=4>&nbsp;</td>
								</tr>
								</table>
							</div>
						
						<div id="divBodyHandoverToPolice" style="display: none;">
							<table width="100%" border="0"  cellspacing="1" cellpadding="0">
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="name"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
										<%=VO.getHandoverToName() %>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="designation"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<%=VO.getOfficerDesignation() %>
										</div>
									</td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="officer"/>
												<bean:message key="batchno"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<%=VO.getOfficerBadgeNo() %>
										</div>
									</td>
									<td width="25%" class="tdfonthead"></td>
									<td width="25%" class="tdfont"></td>
								</tr>
								<tr>
									<td width="25%" class="tdfonthead">
										<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceStnDtls"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
										<div align="left">
											<%=VO.getPoliceStnHandOver() %>
										</div>
									</td>
									<td width="25%" class="tdfonthead">
									<div align="right">
											<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
												<bean:message key="PoliceCntctNo"/>
											</font>
										</div>
									</td>
									<td width="25%" class="tdfont">
									<div align="left">
											<%=VO.getPoliceContactNo() %>
										</div>
									</td>
								</tr>
								<tr>
								<td colspan=4>&nbsp;</td>
								</tr>
							</table>
						</div>
					
			
			</div>
				
					
				<table width="100%"><tr><td width="100%">		
				
				<div align="center">
						<img class="button" id="printPageButton" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-pnt.png"/>'  style=cursor:pointer tabindex="1" onclick =" printReceipt()" onkeypress="if(event.keyCode==13)printReceipt()">
						
						
			</div></td></tr></Table>	
			
				
		
		</his:TransactionContainer>
		
		<html:hidden name="DeceasedHandoverFB" property="hmode"/>
		<html:hidden name="DeceasedHandoverFB" property="patCrNo" />
		<html:hidden name="DeceasedHandoverFB" property="deceasedNo" />
		<html:hidden name="DeceasedHandoverFB" property="isMlcCase" />
		<html:hidden name="DeceasedHandoverFB" property="bodyStatus" />
		<html:hidden name="DeceasedHandoverFB" property="relativeExist" />
		<html:hidden name="DeceasedHandoverFB" property="isHandover" />
		<html:hidden name="DeceasedHandoverFB" property="printFlag" />
		
	</html:form>
	<his:status/>

</body>


</html>