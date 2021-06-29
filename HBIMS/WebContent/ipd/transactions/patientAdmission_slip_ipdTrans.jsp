<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ page import ="java.util.*,java.text.*,java.io.*" %>
<%@page import="hisglobal.hisconfig.*,hisglobal.utility.HisUtil,billing.BillConfigUtil"%>
<%@page import="ipd.*;" %>
<jsp:include page="/DisplayImage" />
<html>
<head>
<meta charset=utf-8>
<title>Admission Slip</title>


<script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script>
<script language="Javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<script type="text/javascript">
function DrawCode39Barcode(data, checkDigit)
{

	console.log(":::::::::::::::::::::::::::::::::::::::");


	console.log(data+" "+checkDigit);
	//alert("inside barcode_code39.js");
	return DrawHTMLBarcode_Code39(data,checkDigit,"no",
					"in", 0,2,0.3,4,//0,1,0.2,2,//these parameters were changed by Mukund on 23.01.2017 for making barcode large enough to be scannable using barcode scanner
					"bottom","left", "",
					"black","white"); 
	//return DrawHTMLBarcode_Code39(data,checkDigit,"no","in", 0,2,0.2,3,"bottom","left", "","black","white"); 
}	
</script>

<style type="text/css">
	body{
                margin:0;
                padding:0;
                width:100%;
                height:100%;
				font-family: "Times New Roman", Times, serif; 
		font-size: 12px;
		font-style: normal;
            }
	.SLIPCONTROL 
	{	
		font-family: "Times New Roman", Times, serif; 
		font-size: 12px;
		font-style: normal;
/*		border:2px solid black;
		border-collapse: collapse; */
		
	}
	.SLIPCONTROLBORDERFREE
	{	
		font-family: "Times New Roman", Times, serif; 
		font-size: 12px;
		font-style: normal;
		font-weight: bold;
	}
	.SLIPCONTROLBORDERFREE2
	{	
		font-family: "Times New Roman", Times, serif; 
		font-size: 10px;
		font-style: normal;
		font-weight: bold;
	}
	.aligned 
	{
		-webkit-transform:  rotate(314deg);
		-moz-transform:  rotate(314deg);
		-o-transform:  rotate(314deg);
		writing-mode: lr-tb;
		width: 400px;
		height: 100px;
		position: absolute;
		top:0;
		bottom: 0;
		left: 0;
		right: 0;
		margin: auto;
		z-index: 1;
		background: transparent;
		-webkit-text-fill-color: transparent;
  		-webkit-background-clip: text;
		
	}
	.SLIPCONTROLHEADER 
	{	
		font-family: "Times New Roman", Times, serif; 
		font-size: 15px;
		font-style: normal;
	}
	.SLIPCONTROLHH 
	{	
		font-family: "Times New Roman", Times, serif;
		font-size: 14px;
		font-style: normal;
	}
	
	.container{
                width:100%;
                padding-top:0px;
                height:auto;
                border:1px #000000 solid;
                border-collapse:collapse;
                border-radius:5px;
            }

</style>

</head>
<body onload='printSlip();'>
<html:form action="/transactions/PatientAdmissionTransCNT.cnt" method="post">
<% 
		final ResourceBundle hisProp = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties"); 
		final String header1 =hisProp.getString("PHDM_HEADER1");
		final String header2 =hisProp.getString("PHDM_HEADER2");
		final String header3 =hisProp.getString("PHDM_HEADER3");
		final String header4 =hisProp.getString("PHDM_HEADER4");
		
		String hospcode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		

		Map reqMap=new HashMap();
		
		reqMap.put("REQUEST",request);
		reqMap.put("HOSPCODE",hospcode);
		reqMap.put("FORMAT","html");
		
		HisUtil hisUtil=new HisUtil("IPD","PatAdmSlip");	
		final String strHeader=hisUtil.getHospitalHeaderMain(reqMap);
		final String strHeader1=hisUtil.getHospitalHeader2(hospcode, 1, "html");

	/* 	final String strHeader=hisUtil.getHospitalHeader1(hospcode, 1, "html");
		final String strHeader1=hisUtil.getHospitalHeader2(hospcode, 1, "html"); */
		
		String filePath2=HisUtil.getParameterFromHisPathXML("TEMP_PATH");
	    //String filePath = "C:\\PHDM\\AHIMS\\Qrcode images\\"+formBean.getStrAdmNo()+".png";
		String path_globalImages = filePath2+File.separator+request.getAttribute("strAdmNo").toString()+".png";
		
		 //String path_globalImages = resourceBundle.getValueFromKey("IMAGE_PATH_QR")+"JD"+request.getAttribute("strAdmNo").toString()+".png";
		    
		 //System.out.println("path is "+path_globalImages+"adm no is "+request.getAttribute("strAdmNo"));

%>
<div class="errMsg" id="errID"><bean:write name="paientAdmissionTransBean" property="strMsgString"/></div>
<%--<table width="90%" align="center">
<tbody>
 <tr style='display: none;'>
	<td>
	<div class="container">	
		<table cellspacing="0px" cellpadding="0" width="100%" style='display: none;'>
		<tr>
			<td colspan="1"><div align="right"><img src="/HBIMS/hisglobal/images/logo.png" height="85" width="85"/></div></td>
			<td colspan="4">
					<div align="center">
						<table width="100%">
							<tr>
								<td class='SLIPCONTROL' colspan="3">
									<div align="left">
										<b><bean:write name="paientAdmissionTransBean" property="strHospDtl"/></b>
									</div>
								</td>
							</tr>			
							<tr>
								<td class='SLIPCONTROL' colspan="3"><div align="left"><b><%=header1 %></b></div></td>
							</tr>
							<tr>
								<td class='SLIPCONTROL' colspan="3"><div align="left"><b><%=header2 %></b></div></td>
							</tr>
							<tr>
								<td class='SLIPCONTROL' colspan="3"><div align="left"><b><%=header3 %></b></div></td>
							</tr>
						</table>
					</div>
			</td>
			<td colspan="1"><div align="right"></div></td>
		</tr>			
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>
		<tr>
			<td class='SLIPCONTROL' colspan="6"><div align="center"><b></b></div></td>
		</tr>		
		</table>
	</div>
	</td>
</tr>--%>
	<% Calendar currentDate = Calendar.getInstance();
  		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
  		String dateNow = formatter.format(currentDate.getTime());
  %>
<%-- <tr>
	<td>--%>
	
		<%-- <%if(new BillConfigUtil(hospcode).getLogoReq().equals("1")){%> --%>
		<%-- <div class="container">	--%>
		  <table width="100%" cellspacing="0px"  cellpadding="0"><tr><td width="90%">
			<%=strHeader%></td><td width="10%">	<div id='divQRCodeControl' class='div-table-col' style='width: 5%;margin-top:10px;' align='right' >
						<img src="/HBIMS/DisplayImage?stradm=<%=request.getAttribute("strAdmNo") %>" width="1000%">
						</div>		</td></tr>
		  </table>
		<!--  </div>-->
	<%-- 	<% } 
	    else { %>
	    <table width="100%" cellspacing="0px"  cellpadding="0">
	    <thead><tr style="height:100px;"><th></th></tr></thead>
	    </table>
	    <% } %> --%>
   	<%-- </td>
</tr>
<tr><td><br></td></tr>--%>
<%-- <tr>
	<td>
	<div class="container">	--%>
		<table width="100%" cellspacing="0px"  cellpadding="0">
			<tr>				
				<logic:equal value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
					<td class='SLIPCONTROL' width='20%'><div align="center"><b>&nbsp;</b></div></td>
					<td class='SLIPCONTROL' width='50%'><div align="center"><b>DUPLICATE ADMISSION CARD</b></div></td>
					<td class='SLIPCONTROL' width='30%'><div align="right"><b>Print Date:</b>&nbsp;<%=dateNow %></div></td>					
				</logic:equal>
				<logic:notEqual value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
					<td class='SLIPCONTROL' width='20%'><div align="center"><b>&nbsp;</b></div></td>
					<td class='SLIPCONTROL' width='50%'><div align="center"><b>ADMISSION CARD</b></div></td>
					<td class='SLIPCONTROL' width='30%'><div align="right"><b>Print Date:</b>&nbsp;<%=dateNow %></div></td>					
				</logic:notEqual>								
			</tr>					
		</table>
		<hr>
	<%--</div>
	</td>
</tr>
<tr>
	<td width="100%" >
	<div class="container">	--%>
			<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:2px;padding-left:10px;margin-top:2px;font-size: 12px">				
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>CR No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strCrNo"/></font></b></div>
						<div id='divBarCodeControlCrNo' align="center"><bean:write name="paientAdmissionTransBean" property="strCrNo"/></div>
					</td>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>IPD No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strAdmNo"/></font></b></div>
						<div id='divBarCodeControlAdmNo' align="center"><bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>
					</td>
					<td class="SLIPCONTROL" colspan="1" width="20%">
						<div align="left"><b>Gate Pass No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strCaseSheetNo"/></font></b></div>
					</td>
					<%-- <td class="SLIPCONTROL" colspan="1" width="20%">
						
						<div id='divQRCodeControl' class='div-table-col' style='width: 5%;margin-top:10px;' align='right' >
						<img src="/HBIMS/DisplayImage?stradm=<%=request.getAttribute("strAdmNo") %>" width="100">
						</div>
					</td>--%>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Name:&nbsp;<font size="3pt"><bean:write name="paientAdmissionTransBean" property="strPatName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Age/Gender<label style="padding-left: 1%;">:</label>&nbsp;<font size="2pt"><bean:write name="paientAdmissionTransBean" property="strAge"/></font></b></div>
					</td>
					<td class="SLIPCONTROL" colspan="1" width="20%">
					    <div align="left" style="display:none;"><b>Case Sheet No:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCaseSheetNo"/></div>
						<div align="left"><b>MLC No.:</b>&nbsp;<logic:equal name="paientAdmissionTransBean" property="strMlcNo" value="0">NA</logic:equal>
						<logic:notEqual name="paientAdmissionTransBean" property="strMlcNo" value="0">
						<font size="2"><bean:write name="paientAdmissionTransBean" property="strMlcNo"/></font>
						</logic:notEqual></div> 
					</td> 
				</tr>										
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Father/Spouse Name:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strFatherName"/>/<bean:write name="paientAdmissionTransBean" property="strHusbandName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="1" width="25%">
						<div align="left"><b>Marital Status:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMaritalStatus"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Monthly Income(Rs.):&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMonthlyIncome"/></font></b></div></td>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Category:&nbsp;<font size="2">
						<bean:write name="paientAdmissionTransBean" property="strPatCategoryName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="4" width="60%">
						<div align="left"><b>AADHAR No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strPatientAdhaarNo"/></font></b></div>
					</td>
				</tr>
				
				<tr>
						<td class="SLIPCONTROL" colspan="1" width="35%">
						
						<!-- for admission card by manisha-->
						<logic:equal value="0" name="paientAdmissionTransBean" property="isDuplicateSlip">  
						<div align="left"><b>Department/Unit<label style="padding-left: 1%;">:</label>&nbsp;&nbsp;
							<bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/>
						</b></div>	
						</logic:equal>
						
						<logic:equal value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
						<div align="left"><b>Department/Unit<label style="padding-left: 1%;">:</label>&nbsp;&nbsp;
							<bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/>
						</b></div>	
						</logic:equal>
						
												
						</td>
						<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Ward/Bed<label style="padding-left: 12.5%;">:</label>&nbsp;&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strWardName"/>
							<logic:notEmpty name="paientAdmissionTransBean" property="strBed">
								<bean:write name="paientAdmissionTransBean" property="strBed"/></logic:notEmpty></b>							
						</div>						
						</td>
					 	<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Consulting Doctor<label style="padding-left: 1%;">:</label>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strConsultantName"/>
							</b>							
						</div>						
						</td>  
						<%-- <td class="SLIPCONTROL" colspan="1" width="35%">
						 <div align="left"><b>Doctor<label style="padding-left: 18%;">:</label>&nbsp;&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strConsultantName"/></b></div>
					 </td>--%>
				</tr>
						
				<logic:equal    value="1" name="paientAdmissionTransBean" property="strAdmissionCharge">
				
                <tr>
					<td class='SLIPCONTROL' colspan="2" width="50%"><div align="left"><b>Admission Charges:&nbsp;<img src='/HBIMS/hisglobal/images/INR.png'/><font size="2"><bean:write name="paientAdmissionTransBean" property="strAdmissionChargeValue"/>&nbsp;&nbsp;</font></b></div></td>
					<td class='SLIPCONTROL' colspan="1" width="50%"><div align="left"></div></td>
				</tr>
				
				</logic:equal>
                        				
				<logic:equal value="1" name="paientAdmissionTransBean" property="strIsNewBorn">
				<tr>
					<td class='SLIPCONTROL' colspan="1" width="30%"><div align="left"><b>Mother IPD No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMotherAdmissionNo"/></font></b></div></td>
					<td class='SLIPCONTROL' colspan="1" width="40%"><div align="left"><b>Mother Name:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMotherName"/></font></b></div></td>		
					<td class='SLIPCONTROL' colspan="1" width="30%"><div align="left"><b>Registration Charges(Rs.):&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strRegChg"/></font></b></div></td>				
				</tr>
				</logic:equal>
				</table>
		<%-- </div>
		</td>
</tr>	
<tr>					
	<td>
	<div class="container">--%>
		<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:2px;padding-left:10px;margin-top:2px;font-size: 12px">
								<tbody>
								<tr>
									<td class="SLIPCONTROL" colspan="3" width="100%"> 
									<font size="2pt"><div align="left"><b>Patient Address:</b>&nbsp;</font>
								<!-- 	<logic:notEqual name="paientAdmissionTransBean"
							property="strHouseNo" value="">
							<bean:write name="paientAdmissionTransBean" property="strHouseNo" />,
						</logic:notEqual>


						<logic:notEqual name="paientAdmissionTransBean"
							property="strStreet" value="">
							<bean:write name="paientAdmissionTransBean" property="strStreet" />,
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strCity" value="">
							<bean:write name="paientAdmissionTransBean" property="strCity" />,
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strTehsil" value="">
							<bean:write name="paientAdmissionTransBean" property="strTehsil" />
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strDistrict" value="">
							<bean:write name="paientAdmissionTransBean"
								property="strDistrict" />,
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strPinCode" value="">
							<bean:write name="paientAdmissionTransBean" property="strPinCode" />,
						</logic:notEqual>

						<logic:notEqual name="paientAdmissionTransBean"
							property="strStateName" value="">
							<bean:write name="paientAdmissionTransBean"
								property="strStateName" />,
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strCountryName" value="">
							<bean:write name="paientAdmissionTransBean"
								property="strCountryName" />
						</logic:notEqual>
						<logic:notEqual name="paientAdmissionTransBean"
							property="strPhoneNo" value="">
							,<b>Ph No:</b><bean:write name="paientAdmissionTransBean" property="strPhoneNo" />
						</logic:notEqual>

						<logic:notEqual name="paientAdmissionTransBean"
							property="strMobileNo" value="">
							,<b>Mob No:</b><bean:write name="paientAdmissionTransBean"
								property="strMobileNo" />
						</logic:notEqual>&nbsp; ,<b>Email Id:</b>&nbsp; --></div></td></tr>
								 <tr>
									<td class="SLIPCONTROL" colspan="1" width="40%">
										<div align="left"><b>House No:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strHouseNo"/>
										</div></td>
									<td class="SLIPCONTROL" colspan="2" width="60%">
										<div align="left"><b>Street:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strStreet"/>
										</div></td>
								</tr>
								<tr>
									<td class="SLIPCONTROL">
										<div align="left"><b>Village/Town:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strCity"/>
										</div></td>
									<td class="SLIPCONTROL">
										<div align="left"><b>Mandal:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strTehsil"/>
										</div></td>
								</tr>
								<tr>
									<td class="SLIPCONTROL">
										<div align="left"><b>District:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strDistrict"/>
										</div></td>
									<td class="SLIPCONTROL">
										<div align="left"><b>Pin Code:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strPinCode"/>
										</div></td>
								</tr>
								<tr>
									<td class="SLIPCONTROL">
										<div align="left"><b>State:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strStateName"/>
										</div></td>
									<td class="SLIPCONTROL">
										<div align="left"><b>Country:</b>&nbsp;
											<bean:write name="paientAdmissionTransBean" property="strCountryName"/>
										</div></td>
								</tr>
								<tr>
                					<td class="SLIPCONTROL" colspan="1" width="40%">
                						<div align="left"><b>Telephone:&nbsp;</b>
                							<bean:write name="paientAdmissionTransBean" property="strPhoneNo"/>
                						</div>
                					</td>
                					<td class="SLIPCONTROL" colspan="1" width="35%">
                						<div align="left"><b>Mobile No:&nbsp;</b>
                							<bean:write name="paientAdmissionTransBean" property="strMobileNo"/>
                						</div>
                					</td>
                					<td class="SLIPCONTROL" colspan="1" width="25%">
										<div align="left"><b>Email Id:</b>&nbsp;</div>
									</td>
								</tr>
								<tr>
								<td class="SLIPCONTROL" colspan="3" width="100%">
									<div align="left"><b>Emergency Contact Person,Address and Phone :</b>&nbsp;
										<bean:write name="paientAdmissionTransBean" property="strFirstPersonName"/>&nbsp;
										<logic:notEmpty name="paientAdmissionTransBean" property="strEmgAddress1">,
										<bean:write name="paientAdmissionTransBean" property="strEmgAddress1"/>&nbsp;,</logic:notEmpty>							
										<bean:write name="paientAdmissionTransBean" property="strFirstpersonphone" />
									</div>
								</td>
								</tr>
						</table>
		<%-- </div>
		</td>
</tr>
<tr><td>
		<div class="container">--%>
			<table width="99%" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-bottom:2px;padding-left:10px;margin-top:2px;font-size: 12px">
						<tr>
					<td class="SLIPCONTROL"  colspan="3" width="60%">
						<div align="left"><b>Adm. Date/Time(24 HH:MI):</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strAdmDateTime"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					
						<b>Discharge Date/Time(24 HH:MI):&nbsp;...........</b>&nbsp;	&nbsp;	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;			
						
					
						<b>Hospital Stay:&nbsp;...........</b></div>						
					</td>					
				</tr>
				</table>
				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:2px;padding-left:10px;margin-top:2px;font-size: 12px">
				<tr>
					<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				</tr>				
				<%--  <tr>
					<td width="100%" colspan='6' class="SLIPCONTROL">
						<bean:write name="paientAdmissionTransBean" property="strPatCatSlip" filter="false"/>
					</td>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				</tr>--%>
				<tr>
					<td class='SLIPCONTROL'width="100%" colspan="6">
						<div align="left"><b>For Medico Legal Purpose</b>&nbsp;</div>
					</td></tr>
					<tr>
					<td class='SLIPCONTROL'  colspan="1" width="35.8%">
						<div align="left" ><b>Details of Police Station:</b>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strDetPs"/></div>
						<div align="left"><b>Police Information:</b>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strPolInfo"/></div>
					</td>
					<td class="SLIPCONTROL"   width="44.2%">
						<div align="left"><b>Name of Informant:</b>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strNamInf"/></div>
						<div align="left"><b>Identification Marks:</b>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strIdenMark"/></div>
						<div align="left"><b>MLC Remarks:</b>&nbsp;
						<bean:write name="paientAdmissionTransBean" property="strRefRem"/></div>
					</td>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				</tr>
				</table>
				<table width="99%" border="0" align="center" cellpadding="0" cellspacing="0" style="margin-bottom:2px;padding-left:10px;margin-top:2px;font-size: 12px">
				
				<%--<tr>			
					
					<td class='SLIPCONTROL' colspan="2" ></td>
					<td class='SLIPCONTROL' colspan="2"  ><div align="left"><b>ICD/SNOMED CT</b>&nbsp;</div></td>
					<td class='SLIPCONTROL' colspan="2" ></td>
				</tr>-->
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="2">
						<div align="left"><b>Provisional/Differential/Final Diagnosis(ICD/SNOMED CT)</b></div></td>
						
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<%--   <tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="5">
						<div align="left"><b>Differential Diagnosis</b></div></td>
					<td class='SLIPCONTROL' colspan="2" ></td>
					<td class='SLIPCONTROL' colspan="2"></td>					
				</tr>-->
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="2">
						<div align="left"><b>Final Diagnosis</b></div></td>
					<td class='SLIPCONTROL' colspan="2" ></td>
					<td class='SLIPCONTROL' colspan="2"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="6" width="100%" height="2"></td>
					</tr>--%>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b>Admission Status: </b>
						
						<bean:write name="paientAdmissionTransBean" property="admissionTypeName"/></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b>Consultations/Operation/Procedure/Treatment Notes</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
				<%-- <tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="5">
						<div align="left"><b>Consultations</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>--%>
				<!--  <tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="5">
						<div align="left"><b>Date/Time</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>-->
				<tr>
					<td class='SLIPCONTROL' colspan="3" width="70%" height="50">
						<div align="left"><b>Discharge Status</b>&nbsp;&nbsp;RELIEVED/CURED/IMPROVED/LAMA/DAMA/REFERRED TO...........</div></td>
					<!--  <td class='SLIPCONTROL' colspan="1" width="40%"></td>	-->
					<td class='SLIPCONTROL' colspan="2" >
						<div align="left" colspan="1" width="30%" ><b>Signature</b></div></td>									
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="1" width="40%" >
						<div align="left"><b>Death Cause</b></div></td>
					<td class='SLIPCONTROL' colspan="1" width="20%" ><b>Date &amp; Time</b></td>
					<td class='SLIPCONTROL' colspan="1" width="30%" ><div align="left"><b>Autopsy:  Yes/No</b>&nbsp;</div></td>
					<td class='SLIPCONTROL'colspan="1" width="10%" >
						<div align="left"colspan="1" width="30%" ><b>Signature</b></div></td>
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="6" width="100%" height="20">
						<div align="left"><b>Remarks:</b></div></td>
				</tr>			
				<tr>
						<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				<tr>
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="2" width="30%"><div align="left">Name &amp; Signature of MO</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="4" width="70%"><div align="left">Name &amp; Signature of Specialist/Consultant</div></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="2" width="30%"><div align="left">Date</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="4" width="70%"><div align="left">Date</div></td>					
				</tr>
		</table>
	<%-- </div>
	</td>
</tr>
</tbody></table>	--%>
	
	<!-- Visitor Pass Not Required in AIIMS Raipur -->
	  <hr style="border: 1px dashed black; background-image:url('/HBIMS/hisglobal/images/Scissor.png'); ">  </hr>
	<!--  <img  src='/HBIMS/hisglobal/images/Scissor.png' >-->
	<table width="90%" align="center">
	 <tfoot>
	 <%=strHeader1%>
				<tr><td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%">
						<div align="left" ><b>NAME OF PATIENT :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strPatName"/></font></b></div></td>
					<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%">
					    <div align="left" ><b>AGE/GENDER :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strAge"/></font></b></div></td>
				</tr>
				<tr>
				
						<logic:equal value="0" name="paientAdmissionTransBean" property="isDuplicateSlip"> 
						<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"> 
						<div align="left"><b>DEPARTMENT :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/></font></b></div>
						</td>
						</logic:equal>
						<logic:equal value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
						<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%">
						<div align="left"><b>DEPARTMENT/UNIT :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/></font></b></div>
						</td>
						</logic:equal>				
						<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%">
						<div align="left"><b>WARD/BED :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strWardName"/>
							<logic:notEmpty name="paientAdmissionTransBean" property="strBed">
								/<bean:write name="paientAdmissionTransBean" property="strBed"/></logic:notEmpty></font></b></div></td>
				</tr>
				<tr> <td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"><div align="left"><b>TREATING DOCTOR'S NAME :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strConsultantName"/></font></b></div></td>
					 <td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"><div align="left"><b>IP No. :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strAdmNo"/></font></b></div></td>	
				 	<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"><div align="left"><b></b>&nbsp;
						<b><font size="2"></font></b></div></td>
					 
				<tr><td class='SLIPCONTROLBORDERFREE2' colspan="6" width="100%"><div align="left"><b>PATIENT ATTENDANT'S NAME :</b>&nbsp;
						<b><font size="2"><bean:write name="paientAdmissionTransBean" property="strFirstPersonName"/></font></b></div></td></tr>
				<tr><td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"><div align="left"><b>Valid for 7 days</b></div></td>
					<td class='SLIPCONTROLBORDERFREE2' colspan="3" width="50%"><div align="center"><b>SIGN. OF ISSUING CLERK</b></div>
				</td>
				</tr>
	  </tfoot>
	  
	  </table> 

	<!--  HIDDEN FIELDS COMMENTED BECAUSE SAME FIELDS WERE PRESENT IN PAT ADM JSP AND CAUSING PROBLEM IN SECURITY, DUPLICATE VALUES-->
	<%-- <input type="hidden" name="hmode" value="PRINTSLIP"/>
	<input type="hidden" name="strWardName" value="${paientAdmissionTransBean.strWardName}"/>
	<input type="hidden" name="strMlcNo" value="${paientAdmissionTransBean.strMlcNo}"/>
	<input type="hidden" name=strDepartmentName value="${paientAdmissionTransBean.strDepartmentName}"/>
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
	<input type="hidden" name="strDeptUnitName" value="${paientAdmissionTransBean.strDeptUnitName}">
	<input type="hidden" name="strCrNo" value="${paientAdmissionTransBean.strCrNo}"> --%>
</html:form>
</body>
</html>