<!-- 
Copyright Information			: C-DAC, Noida  
 ## Project Name				: NIMS
 ## Name of Developer		 	: Amit Garg
 ## Module Name					: MRD
 ## Process/Database Object Name:Estimate Certificate issue after Request
 ## Purpose						:Certificate Issue Process
 ## Date of Creation			:02-Dec-2014 

 -->

<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@page import="mrd.MrdConfig"%>
<%@page import="mrd.vo.PackageServiceMstVO"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="java.util.Date"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="mrd.vo.EstimateCertificateIssueVO"%>
<his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/>

<his:javascript src="/registration/js/registration.js"/>
<his:javascript src="/registration/js/validationCalls.js"/>
<his:javascript src="/registration/js/validationCommon.js"/>
<his:javascript src="/registration/js/commonFunctions.js"/>
<his:javascript src="/hisglobal/js/commonFunctions.js" />
<his:javascript src="/registration/js/popup.js"/>
<his:javascript src="/hisglobal/js/calendar.js"/>
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

function printPage() 
{
	 //var frameElement = parent.document.getElementById("f2");  
	 window.print();
	/*var win = frameElement.contentWindow ;*/
	document.getElementById("noPrint").style.display="none"; 
	//win.print(); 
	document.getElementById("noPrint").style.display="block" ; 
 }

</script>

<html:form action="/estimateCertificateIssue">
<body>  

	<bean:define name="EstimateCertificateIssuetFB" property="sysDate" id="sysDate" type="java.lang.String" />
	<%
		if(sysDate==null||sysDate.equalsIgnoreCase(""))
		{
			sysDate = WebUTIL.getCustomisedSysDate((Date)session.getAttribute(Config.SYSDATEOBJECT), "dd-MMM-yyyy");
		}
	%>
	<html:hidden name="EstimateCertificateIssuetFB" property="sysDate" value="<%=sysDate%>"/>
	
	<div id="noPrint" align="right"> 
				<img class="button" src='<his:path src="/hisglobal/images/btn-pnt.png"/>'  tabindex="1" style="cursor: pointer" onclick="printPage()" onkeypress="if(event.keyCode==13) printPage()">
				<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  tabindex="1" style="cursor: pointer" onclick="closeForm()" onkeypress="if(event.keyCode==13) closeForm()">
	</div>
		<br><br><br><br><br><br>
<div align="center">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b style="font-size:20;">
			<bean:write name="EstimateCertificateIssuetFB" property="hospName" />
			</b>
		</font>
	</div>	
	<div align="center">
		<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
			
			<bean:write name="EstimateCertificateIssuetFB" property="hospAdd" />
			
		</font>
	</div>
	<div align="center">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
			<bean:write name="EstimateCertificateIssuetFB" property="hospAdd1" />
			</b>
		</font>
	</div>
		<br>

	<div align="center">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
			<u>	Estimation Certificate</u>
			</b>
		</font>
	</div>
	<br>
	<div align="left">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
				&nbsp;&nbsp;&nbsp;&nbsp;CERTIFICATE SEQ NO:&nbsp;<bean:write name="EstimateCertificateIssuetFB" property="certificateId" />
			</b>
		</font>
	</div>
	
	<div align="right">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
				&nbsp;&nbsp;&nbsp;&nbsp;Date:&nbsp;<%=sysDate%>
			</b>
		</font>
	</div>
	<br>
	<div align="left">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
				<% 
					String str="";	
					EstimateCertificateIssueVO[] billNoQtyVOArr=(EstimateCertificateIssueVO[])session.getAttribute(MrdConfig.ESTIMATE_CERTIFIATE_ISSUE_PAT_DTL);
						
					EstimateCertificateIssueVO vobillNoQty =(EstimateCertificateIssueVO)billNoQtyVOArr[0];
					String BillNo=vobillNoQty.getBillnoqty();
					String val[]=BillNo.replace("^","#").split("#");
					String deptUnitCode=vobillNoQty.getDeptUnitCode();
					String wardCode=vobillNoQty.getWardCode();
					//System.out.println("billNoQty"+billNoQty);
					if(val[0].equals("0"))
					{
					%>
					
					  <html:hidden name="EstimateCertificateIssuetFB" property="billNo"  value="<%=val[0]%>"/>
						Bill No:- 
					<%
					}
					else{
					%>
					  <html:hidden name="EstimateCertificateIssuetFB" property="billNo"  value="<%=val[0]%>"/>
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						Bill No:-	<%=val[0] %>
							</font>
						</div>	
					<%
					}
					%>
				
			</b>
		</font>
	</div>
	<br>
	<div align="center">
		<font color="#000000" size="15" face="Verdana, Arial, Helvetica, sans-serif">
			<b>
				<u>	TO WHOMSOEVER IT MAY CONCERN </u>
			</b>
		</font>
	</div>			
	<br><br>
    	<his:ContentTag>
    		<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>
					<td width="100%"  colspan="3">
						<div align="left">								
								<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	1.</b>	This is certify that Shri/Smt/Kum  
									<u><B><bean:write name="EstimateCertificateIssuetFB" property="patFullName"/></B></u>
									&nbsp;Son/Wife/Daughter of &nbsp;<u><B><bean:write name="EstimateCertificateIssuetFB" property="patFatherName"/></B></u>
									 is undergoing treatment for &nbsp;&nbsp;&nbsp;&nbsp;
									 <logic:present name="<%=MrdConfig.ESTIMATE_CERTIFIATE_DIAGNOSIS_DTL %>">
									 <logic:iterate name="<%=MrdConfig.ESTIMATE_CERTIFIATE_DIAGNOSIS_DTL %>" id="arrPatDtls" type="mrd.vo.EstimateCertificateIssueVO" indexId="idx">
									
										<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
											<b><u>
											
												<bean:write name="arrPatDtls" property="diagnosisName"/>,
											</u></b>
										</font>
							 
									 </logic:iterate>
									 </logic:present>
								</font>
						</div>
					</td>
				</tr>
				<tr>
				<tr>	
					<%-- <td width="100%"  colspan="3">
						<div align="left">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	2.</b> Request No.  
									<u><B>	<bean:write name="EstimateCertificateIssuetFB" property="certificateId"/></B></u>
							</font>
						</div>
					</td> --%>
					
					<td width="100%"  colspan="3">
						<div align="left">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	2.</b> The procedure operation required is &nbsp;&nbsp;&nbsp;&nbsp;
									<logic:iterate name="<%=MrdConfig.ESTIMATE_CERTIFIATE_GENERATION_DTL %>" id="arrPatDtll" type="mrd.vo.PackageServiceMstVO" indexId="idx">
									<b><u><bean:write name="arrPatDtll" property="tariffName"/>,</u></b>
									</logic:iterate>	
								</font>
						</div>
					</td> 
					
				</tr>	
				<tr>	
				
					<td width="33%" >
						<div align="">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	3.</b> Out Patient No &nbsp;&nbsp;&nbsp;&nbsp;
									<u><bean:write name="EstimateCertificateIssuetFB" property="patCrNo"/></u>
								</font>
						</div>
					</td> 
					<td width="33%">
						<div align="">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 From Date&nbsp;&nbsp;&nbsp;&nbsp;
									<u><bean:write name="EstimateCertificateIssuetFB" property="episodeStartDate"/></u>
								</font>
						</div>
					</td> 
					
				</tr>
				<logic:notEmpty name="EstimateCertificateIssuetFB" property="admNo">	
				<tr>	
				
					<td width="33%">
						<div align="">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								&nbsp;&nbsp;&nbsp; In Patient No &nbsp;&nbsp;&nbsp;&nbsp;
									<u><bean:write name="EstimateCertificateIssuetFB" property="admNo"/></u>
								</font>
						</div>
					</td> 
					<td width="33%">
						<div align="">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 From Date&nbsp;&nbsp;&nbsp;&nbsp;
									<u><bean:write name="EstimateCertificateIssuetFB" property="admDate"/></u>
								</font>
						</div>
					</td> 
					<td width="33%">
						<div align="">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								 To Date&nbsp;&nbsp;&nbsp;&nbsp;
									<u><bean:write name="EstimateCertificateIssuetFB" property="disDate"/></u>
								</font>
						</div>
					</td> 
					
				</tr>	
				</logic:notEmpty>
				<tr>	
					<td width="100%"  colspan="3">
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<b>	4.</b> The approximate expenditure for above procedure operation for a patient staying in general ward will be as follows.
						</font>
						</div>
					</td>
				</tr>
		</Table>
		<table width="100%" border="1"  cellspacing="1" cellpadding="0">		
				<tr>
				
				<td width="40%" >
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="packageName"/> </b>
						</font>
						</div>
					</td>
				
					<td width="40%" >
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						<b>	<bean:message key="particulars"/> </b>
						</font>
						</div>
					</td>
					<td width="10%" >
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<B><bean:message key="quantity"/></B>
						</font>
						</div>
					</td>
					<td width="10%"  >
						<div align="left">
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<B>Unit Rate (Rs.)</B>
						</font>
						</div>
					</td>
				</tr>
				<logic:notEmpty name="<%=MrdConfig.ESTIMATE_CERTIFIATE_GENERATION_DTL %>">
				<% 
					double totalAmt=0;   String pkgnam="";  String tmp="";
				%>
				<logic:iterate name="<%=MrdConfig.ESTIMATE_CERTIFIATE_GENERATION_DTL %>" id="arrPatDtl" type="mrd.vo.PackageServiceMstVO" indexId="idx">
				<% 
				String[] arr=arrPatDtl.getAmount().split("\\^");
				String amt=arr[0].toString();
				System.out.println(arrPatDtl.getAmount());
				System.out.println("helloooooooooooooooooooo"+amt);
				totalAmt += Double.parseDouble(arr[0])*Double.parseDouble(arrPatDtl.getQuantity()); 
				System.out.println(pkgnam);
				pkgnam= arrPatDtl.getPkgName();
				
				//System.out.println(pkgnam);
				//System.out.println(tmp);
				
				%>
			
				<tr>
				
				<td width="40%">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<% 
					        if(pkgnam.equalsIgnoreCase(tmp))
					        { 
							} 
							else
							{	%>
							
								<bean:write name="arrPatDtl" property="pkgName"/>  
							<% 	}
							tmp=pkgnam;					
							%> 
							</font>
						</div>	
					</td>
					
					
					<td width="40%" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<bean:write name="arrPatDtl" property="tariffName"/>
							</font>
						</div>	
					</td>
					
					<td width="10%" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<bean:write name="arrPatDtl" property="quantity"/>
							</font>
						</div>
					</td>
					
					<td width="10%" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<%-- <bean:write name="arrPatDtl" property="amount"/>
							 --%>
								<%=amt %>
							</font>
						</div>	
					</td>
				</tr>											
				</logic:iterate>
				
				<tr>
					<td width="80%"  colspan="2">
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
							<B><bean:message key="approximateTotal"/></B>
							</font>
						</div>	
					</td>
					<td width="10%" >
						<div align="left">
							
						</div>
					</td>
					<td width="10%" >
						<div align="left">
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
									<%=totalAmt %>
							</font>
						</div>
					</td>
				</tr>
				
			</logic:notEmpty></table>
			
			<table width="100%" border="0"  cellspacing="1" cellpadding="0">
				<tr>	
					<td width="100%"  colspan="3">
						<div align="left">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	4.</b> It may be noted that the actuals may vary from the estimates in accordance with the additional procedures/Tests conducted extra materials
								   used(like non-ionic contrast materials/extra balloons etc) during treatment period, depending on the patient's condition.
							</font>
						</div>
					</td>
				</tr>	
				<tr>	
					<td width="100%"  colspan="3">
						<div align="left">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	5.</b> Patients staying in shared paying rooms/Single rooms have to pay for the special services also as per approved rates.
							</font>
						</div>
					</td>
				</tr>
				<tr>	
					<td width="100%"  colspan="3">
						<div align="left">							
							<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
								<b>	6.</b> The cost of disposables must be deposited in advance at the time of admission itself by all patients including the cases referred by credit organisation.
							</font>
						</div>
					</td>
				</tr>
				<tr>	
					<td width="100%"  colspan="3" align="right">																		
					</td>
				</tr>
				<tr>	
					<td width="100%"  colspan="3" align="right">																		
					</td>
				</tr>
				<tr>	
					<td width="100%"  colspan="3" align="right"><br><br>
						<b><bean:write name="EstimateCertificateIssuetFB" property="advisedBy"/>(<bean:write name="EstimateCertificateIssuetFB" property="docDept"/>)</b>									
					</td>
				</tr>
				<tr>	
					<td width="100%"  colspan="3" align="right"><br><br>
						<b><bean:write name="EstimateCertificateIssuetFB" property="docDesig"/></b>									
					</td>
				</tr>
		 </table>		 
		</his:ContentTag> 
</body>
<his:status/>
</html:form>    