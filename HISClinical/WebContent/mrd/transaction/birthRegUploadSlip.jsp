
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="hisglobal.vo.PatientVO"%>
<%@page import="mrd.MrdConfig"%>
<style type="text/css">
@media print { #noPrint { display: none; }} 
</style>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function printPage() 
{
	window.print();
}
function formClose()
{
	self.close();
}
</script>
<html:form action="/birthRegistrationUpload">
	<%PatientVO birthSlipDtlVO=(PatientVO)session.getAttribute(MrdConfig.BIRTH_REGISTRATION_SLIP_DETAIL_VO); %>
	<body>
		<table width="80%" align="center">
			<tr>
				<td width="100%">
					<div id="noPrint" align="right"> 
						<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
						<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="formClose();" onkeypress="if(event.keyCode==13) formClose();">
					</div>
				</td>
			</tr>
			<br>
			<br>
			
			<tr>	
				<td width="100%">	
					<div align="center">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>
								<u>
									Birth Registration Slip
								</u>	
							</b>
						</font>
					</div>
				</td>
			</tr>	
			<br>
			<br>
			<tr>
				<td width="100%">
					<table width="100%"  >
						<tr>
							<td width="25%">
								<div align="left">
									<b>
										D.O.B. &nbsp;
									</b>
								</div>
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getPatDOB() %>
								</div>
							</td>
							<td width="25%">
								<div align="left">
									<b>
										Gender &nbsp;
									</b>
								</div>	
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getPatGender() %>
								</div>
							</td>
						</tr>
					</table>	
				</td>
			</tr>
			
			<tr>
				<td width="100%">
					<table width="100%"  >
						<tr>
							<td width="25%">
								<div align="left">
									<b>
										Father's Name &nbsp;
									</b>	
								</div>
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getPatGuardianName() %>
								</div>
							</td>
							<td width="25%">
								<div align="left">
									<b>
										Mother's Name &nbsp;
									</b>
								</div>		
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getPatMotherName() %>
								</div>
							</td>
						</tr>
					</table>	
				</td>
			</tr>
			
			<tr>
				<td width="100%" >
					<table width="100%" >
						<tr>
							<td width="25%">
								<div align="left">
									<b>
										Registration No. &nbsp;
									</b>	
								</div>
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getBirthRegNo() %>
								</div>
							</td>
							<td width="25%">
								<div align="left">
									<b>
										Date &nbsp;
									</b>
								</div>		
							</td>
							<td width="25%">
								<div align="left">
									&nbsp;:&nbsp;<%=birthSlipDtlVO.getSystemDate() %>
								</div>
							</td>
						</tr>
					</table>	
				</td>
			</tr>	
		</table>
		
		 <html:hidden name="BirthRegistrationUploadFB" property="hmode"/>
		 <html:hidden name="BirthRegistrationUploadFB" property="selectedChild"/>
		 
	</body>
</html:form>		
