<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ page import ="java.util.*,java.text.*,java.io.*" %>
<%@page import="hisglobal.hisconfig.*,hisglobal.utility.HisUtil,billing.BillConfigUtil"%>
<%@page import="ipd.*" %>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Discharge  Slip</title>


<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet">


<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>


<link href="../css/newlayout.css" rel="stylesheet" type="text/css">


<script language="Javascript" src="../js/patientDischarge.js"></script>
<script language="Javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<script type="text/javascript">
function DrawCode39Barcode(data, checkDigit)
{
	//alert("inside barcode_code39.js");
	return DrawHTMLBarcode_Code39(data,checkDigit,"no",
						"in", 0,2,0.3,4,//0,1,0.2,2,//these parameters were changed by Mukund on 23.01.2017 for making barcode large enough to be scannable using barcode scanner
						"bottom","left", "",
						"black","white"); 
}
</script>

<style type="text/css">
	

</style>

</head>
<body onload='printSlip();'>
<html:form action="/transactions/PatientFinalDischargeBSCNT.cnt" method="post">
<% 
		final ResourceBundle hisProp = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties"); 
		final String header1 =hisProp.getString("PHDM_HEADER1");
		final String header2 =hisProp.getString("PHDM_HEADER2");
		final String header3 =hisProp.getString("PHDM_HEADER3");
		final String header4 =hisProp.getString("PHDM_HEADER4");
		
		HisUtil hisUtil=new HisUtil("IPD","PatAdmSlip");
		String hospcode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		/* final String strHeader=hisUtil.getHospitalHeader1(hospcode, 1, "html");
		final String strHeader1=hisUtil.getHospitalHeader2(hospcode, 1, "html"); */
		
 		Map logoReq=new HashMap();  
	      logoReq.put("FORMAT", "html");
	      logoReq.put("REQUEST", request);
	      logoReq.put("HOSPCODE", hospcode);
    
    final String strHeader=hisUtil.getHospitalHeaderMain(logoReq);
    
		
		//String filePath2=HisUtil.getParameterFromHisPathXML("TEMP_PATH");
	    //String filePath = "C:\\PHDM\\AHIMS\\Qrcode images\\"+formBean.getStrAdmNo()+".png";
		//String path_globalImages = filePath2+File.separator+request.getAttribute("strAdmNo").toString()+".png";
		
		 //String path_globalImages = resourceBundle.getValueFromKey("IMAGE_PATH_QR")+"JD"+request.getAttribute("strAdmNo").toString()+".png";
		    
		 //System.out.println("path is "+path_globalImages+"adm no is "+request.getAttribute("strAdmNo"));

%>
<div class="errMsg" id="errID"><bean:write name="patientDischargeBean" property="strMsgString"/></div>
<table width="100%" align="center">
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
										<b><bean:write name="patientDischargeBean" property="strHospDtl"/></b>
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
</tr>
	<% Calendar currentDate = Calendar.getInstance();
  		SimpleDateFormat formatter= new SimpleDateFormat("dd-MMM-yyyy");
  		String dateNow = formatter.format(currentDate.getTime());
  %>
<tr>
	<td>
	
		<%-- <%if(new BillConfigUtil(hospcode).getLogoReq().equals("1")){%> --%>
		<div class="container">	
		<table width="100%" cellspacing="0px"  cellpadding="0">
			<%=strHeader%>			
		</table>
		</div>
	<%-- 	<% } 
	    else { %>
	    <table width="100%" cellspacing="0px"  cellpadding="0">
	    <thead><tr style="height:100px;"><th></th></tr></thead>
	    </table>
	    <% } %> --%>
   	</td>
</tr>
<tr><td><br></td></tr>
<tr>
	<td>
	<div class="container">	
		<table width="100%" cellspacing="0px"  cellpadding="0">
			<tr>
			<td class='SLIPCONTROL' colspan="2"width="30%"><div align="right"><b></b></div></td>
				<td class='SLIPCONTROL' colspan="3"width="40%"><div align="center"><b>Discharge Card</b></div></td>
			<td class='SLIPCONTROL' colspan="1" width="30%"><div align="right"><b>Print Date:</b>&nbsp;<%=dateNow %></div></td>
			</tr>
			<logic:equal value="1" name="patientDischargeBean" property="isDuplicateSlip">
			<tr>
				<td colspan="6"><div align="center" class='aligned'><b><font size="25" color='Gainsboro' style="opacity:.6;">Duplicate Card</font></b></div></td>
			
			</tr>
		    </logic:equal>
			<tr>
			   
				
			</tr>			
		</table>
	</div>
	</td>
</tr>
<tr>
	<td width="100%" >
	<div class="container">	
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-bottom:2px;padding-left:10px;margin-top:10px;font-size: 12px">				
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>CR No.:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strCrNo"/></font></b></div>
						<div id='divBarCodeControlCrNo' align="left"><bean:write name="patientDischargeBean" property="strCrNo"/></div>
					</td>
					<td class="SLIPCONTROL" colspan="1" width="20%">
						<div align="left"><b>IPD No.:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strAdmNo"/></font></b></div>
						<div id='divBarCodeControlAdmNo' align="left"><bean:write name="patientDischargeBean" property="strAdmNo"/></div>
					</td>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Case Sheet No:</b>&nbsp;<bean:write name="patientDischargeBean" property="strCaseSheetNo"/></div>
						<div align="left"><b>MLC No.:</b>&nbsp;<logic:equal name="patientDischargeBean" property="strMlcNo" value="0">NA</logic:equal>
						<logic:notEqual name="patientDischargeBean" property="strMlcNo" value="0">
						<font size="2"><bean:write name="patientDischargeBean" property="strMlcNo"/></font>
						</logic:notEqual></div>
					</td>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Name:&nbsp;<font size="3pt"><bean:write name="patientDischargeBean" property="strPatName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="4" width="60%">
						<div align="left"><b>Age/Gender<label style="padding-left: 1%;">:</label>&nbsp;<font size="2pt"><bean:write name="patientDischargeBean" property="strAge"/></font></b></div>
					</td>
				</tr>										
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Father/Spouse Name:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strFatherName"/>/<bean:write name="patientDischargeBean" property="strHusbandName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="1" width="25%">
						<div align="left"><b>Marital Status:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strMaritalStatus"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Monthly Income(Rs.):&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strMonthlyIncome"/></font></b></div></td>
				</tr>
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="40%">
						<div align="left"><b>Category:&nbsp;<font size="2">
						<bean:write name="patientDischargeBean" property="strPatCategoryName"/></font></b></div></td>
					<td class="SLIPCONTROL" colspan="4" width="60%">
						<div align="left"><b>UHID/AADHAR No.:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strPatientAdhaarNo"/></font></b></div>
					</td>
				</tr>
				
				<tr>
					<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Department/Unit<label style="padding-left: 1%;">:</label>&nbsp;&nbsp;
							<bean:write name="patientDischargeBean" property="strDeptUnitName"/></b></div>							
						</td>
						<td class="SLIPCONTROL" colspan="1" width="35%">
						<div align="left"><b>Ward/Bed<label style="padding-left: 12.5%;">:</label>&nbsp;&nbsp;
						<bean:write name="patientDischargeBean" property="strWardName"/>
							<logic:notEmpty name="patientDischargeBean" property="strBed">
								/<bean:write name="patientDischargeBean" property="strBed"/></logic:notEmpty></b>							
						</div>						
						</td>
						<td class="SLIPCONTROL" colspan="1" width="35%">
						 <div align="left"><b>Doctor<label style="padding-left: 18%;">:</label>&nbsp;&nbsp;
						<bean:write name="patientDischargeBean" property="strConsultantName"/></b></div>
				       	</td>
				</tr>
						
				<logic:equal    value="1" name="patientDischargeBean" property="strIsAdvanceAmountAtAdmission">
				<logic:notEqual value="0" name="patientDischargeBean" property="strAdmissionCharge">
                <tr>
					<td class='SLIPCONTROL' colspan="3" width="50%"><div align="left"><b>Admission Charges(Rs.):&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strAdmissionCharge"/>&nbsp;&nbsp;<img src='/HBIMS/hisglobal/images/INR.png'></font></b></div></td>
					<td class='SLIPCONTROL' colspan="3" width="50%"><div align="left"></div></td>
				</tr>
				</logic:notEqual>
				</logic:equal>
                        				
				<logic:equal value="1" name="patientDischargeBean" property="strIsNewBorn">
				<tr>
					<td class='SLIPCONTROL' colspan="3" width="50%"><div align="left"><b>Mother IPD No.:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strMotherAdmissionNo"/></font></b></div></td>
					<td class='SLIPCONTROL' colspan="3" width="50%"><div align="left"><b>Mother Name.:&nbsp;<font size="2"><bean:write name="patientDischargeBean" property="strMotherName"/></font></b></div></td>			
				</tr>
				</logic:equal>
				</table>
		</div>
		</td>
</tr>	
<tr>					
	<td>
	<div class="container">
		<table width="99%" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-bottom:2px;padding-left:10px;margin-top:1px;font-size: 12px">
								<tbody>
								<tr>
									<td class="SLIPCONTROL" colspan="3" width="100%"> 
									<font size="2pt"><div align="left"><b>Patient Address:</b>&nbsp;</font></div></td>
								</tr>
								<tr>
									<td class="SLIPCONTROL" colspan="1" width="40%">
										<div align="left"><b>House No:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strHouseNo"/>
										</div></td>
									<td class="SLIPCONTROL" colspan="1" width="30%">
										<div align="left"><b>Street:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strStreet"/>
										</div></td>
								<td class="SLIPCONTROL" colspan="1" width="30%">
										<div align="left"><b>Village/Town:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strCity"/>
										</div></td>
								</tr>
								<tr>
									
									<td class="SLIPCONTROL">
										<div align="left"><b>Mandal:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strTehsil"/>
										</div></td>
										<td class="SLIPCONTROL">
										<div align="left"><b>District:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strDistrict"/>
										</div></td>
										<td class="SLIPCONTROL">
										<div align="left"><b>Pin Code:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strPinCode"/>
										</div></td>
									
								</tr>
								<tr>
									<td class="SLIPCONTROL" colspan="1" width="40%">
										<div align="left"><b>State:</b>&nbsp;
											<bean:write name="patientDischargeBean" property="strStateName"/>
										</div></td>
									<td class="SLIPCONTROL" colspan="1" width="35%" >
                						<div align="left"><b>Telephone:&nbsp;</b>
                							<bean:write name="patientDischargeBean" property="strPhoneNo"/>
                						</div>
                					</td>
                					<td class="SLIPCONTROL" colspan="1" width="25%">
                						<div align="left"><b>Mobile No:&nbsp;</b>
                							<bean:write name="patientDischargeBean" property="strMobileNo"/>
                						</div>
                					</td>
                					
								</tr>
								<tr>
								<td class="SLIPCONTROL" colspan="3" width="100%">
									<div align="left"><b>Emergency Contact Person,Address and Phone :</b>&nbsp;
										<bean:write name="patientDischargeBean" property="strFirstPersonName"/>&nbsp;
										<logic:notEmpty name="patientDischargeBean" property="strEmgAddress1">,
										<bean:write name="patientDischargeBean" property="strEmgAddress1"/>&nbsp;,</logic:notEmpty>							
										<bean:write name="patientDischargeBean" property="strFirstpersonphone" />
									</div>
								</td>
								</tr>
						</table>
		</div>
		</td>
</tr>

<tr><td>
		<div class="container">
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-bottom:2px;padding-left:10px;margin-top:1px;font-size: 12px">
				<tr>
					<td class="SLIPCONTROL" colspan="2" width="40%">
						<div align="left"><b>Adm. Date/Time(24 HH:MI):</b>&nbsp;<bean:write name="patientDischargeBean" property="strAdmDateTime"/></div></td>
					<% Calendar currentDate1 = Calendar.getInstance();
  		SimpleDateFormat formatter1= new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
  		String dateNow1 = formatter1.format(currentDate.getTime());
  %>
					<td class="SLIPCONTROL" colspan="2" width="35%">
						<div align="left"><b>Discharge Date/Time(24 HH:MI):&nbsp;</b><bean:write name="patientDischargeBean" property="strDisDateTime"/></div>						
					</td>	
					<td class="SLIPCONTROL" colspan="2" width="25%">
						<div align="left"><b>Hospital Stay:&nbsp;</b><bean:write name="patientDischargeBean" property="strDaysStay"/>&nbsp;Days</div>						
					</td>					
				</tr>
			<!--  	<tr>
					<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				</tr>				
				<tr>
					<td width="100%" colspan='6' class="SLIPCONTROL">
						<bean:write name="patientDischargeBean" property="strPatCatSlip" filter="false"/>
					</td>
				</tr> -->
				<tr>
					<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%">
						<div align="left"><b>For Medico Legal Purpose</b>&nbsp;</div>
					</td>
					<td class='SLIPCONTROL' colspan="2" width="35%">
						<div align="left" ><b>Details of Police Station:</b>&nbsp;
						<bean:write name="patientDischargeBean" property="strDetPs"/></div>
						<div align="left"><b>Police Information:</b>&nbsp;
						<bean:write name="patientDischargeBean" property="strPolInfo"/></div>
					</td>
					<td class="SLIPCONTROL" colspan="2" width="25%">
						<div align="left"><b>Name of Informant:</b>&nbsp;
						<bean:write name="patientDischargeBean" property="strNamInf"/></div>
						<div align="left"><b>Identification Marks:</b>&nbsp;
						<bean:write name="patientDischargeBean" property="strIdenMark"/></div>
						<div align="left"><b>MLC Remarks:</b>&nbsp;
						<bean:write name="patientDischargeBean" property="strRefRem"/></div>
					</td>
				</tr>
				 <tr>
						<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
					<tr>
				<tr>			
					<td class='SLIPCONTROL' colspan="2" width="40%" >
						<!-- <div align="left"><b>Final Diagnosis&nbsp;</b></div> --></td>
					<td class='SLIPCONTROL' colspan="2" width="25%" ></td>
					<td class='SLIPCONTROL' colspan="2" width="35%" ><!-- div align="left"><b>ICD/SNOMED CT</b>&nbsp;</div> --></td>
					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="80">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
		
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="40">
				<!-- 		<div align="left"><b>Present Complaints</b></div> --></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="70">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="50">
						<!-- <div align="left"><b>History</b></div> --></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="80">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>  
				
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Name &amp; Signature of Resident</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Name &amp; Signature of Faculty</div></td>					
				</tr>
				<tr>
				<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Date</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Date</div></td>					
				</tr>
				<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				
						</table>
		</div>
		</td>
</tr>

<tbody>
<%-- <tr style='display: none;'>
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
										<b><bean:write name="patientDischargeBean" property="strHospDtl"/></b>
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
</tr> --%>

<%-- <tr>
	<td>
	
		<%if(new BillConfigUtil(hospcode).getLogoReq().equals("1")){%>
		<div class="container">	
		<table width="100%" cellspacing="0px"  cellpadding="0">
			<%=strHeader%>			
		</table>
		</div>
		<% } 
	    else { %>
	    <table width="100%" cellspacing="0px"  cellpadding="0">
	    <thead><tr style="height:100px;"><th></th></tr></thead>
	    </table>
	    <% } %>
   	</td>
</tr> --%>

<!-- <tr><td><br></td></tr> -->
<!-- <tr><td>
		<div class="container">
			<table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" style="margin-bottom:2px;padding-left:10px;margin-top:1px;font-size: 12px">
					 <tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="60">
						<div align="left"><b>Clinical Summary</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="120">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="70">
						<div align="left"><b>Discharge Advice</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="120">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="70">
						<div align="left"><b>Investigation</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="120">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="20%" >
						<div align="left"><b>Discharge Status</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="80%">RELIEVED/CURED/IMPROVED/NOT RELIEVED/REFERRED TO...........</td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="10">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" >
						<div align="left"><b>LAMA</b></div></td>
					<td class='SLIPCONTROL' colspan="4" width="60%"></td>										
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="10">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" >
						<div align="left"><b>Expired</b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%" ><b>Date &amp; Time</b></td>
					<td class='SLIPCONTROL' colspan="2" width="35%" ><div align="left"><b>Autopsy:  Yes/No</b>&nbsp;</div></td>
					
				</tr>
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROL' colspan="6" width="100%" height="15">
						<div align="left"><b>Remarks:</b></div></td>
				</tr>	
						<tr>
					<td class='SLIPCONTROL' colspan="2" width="40%" height="10">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>		
				<tr>
						<td class="SLIPCONTROL" colspan="6" width="100%"><hr /></td>
				<tr>
				<td class='SLIPCONTROL' colspan="2" width="40%" height="10">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Name &amp; Signature of Resident</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Name &amp; Signature of Faculty</div></td>					
				</tr>
				<tr>
				<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
				<tr>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Date</div></td>
					<td class='SLIPCONTROLBORDERFREE' colspan="3" width="50%"><div align="left">Date</div></td>					
				</tr>
				<td class='SLIPCONTROL' colspan="2" width="40%" height="20">
						<div align="left"><b></b></div></td>
					<td class='SLIPCONTROL' colspan="2" width="25%"></td>
					<td class='SLIPCONTROL' colspan="2" width="35%"></td>					
				</tr>
		</table>
	</div>
	</td>
</tr> -->
</tbody></table>	
	
	
	<!--  <hr style="border: 1px dashed black; background-image:url('/HBIMS/hisglobal/images/Scissor.png'); ">  </hr>-->


	
	<input type="hidden" name="hmode" value="PRINTSLIP"/>
	<input type="hidden" name="strWardName" value="${patientDischargeBean.strWardName}"/>
	<input type="hidden" name="strMlcNo" value="${patientDischargeBean.strMlcNo}"/>
	<input type="hidden" name=strDepartmentName value="${patientDischargeBean.strDepartmentName}"/>
	<input type="hidden" name="strAgeUnit" value="">
	<input type="hidden" name="strSexCode" value="">
	<input type="hidden" name="strAge" value="">
	<input type="hidden" name="strDeptUnitName" value="${patientDischargeBean.strDeptUnitName}">
	<input type="hidden" name="strCrNo" value="${patientDischargeBean.strCrNo}">
</html:form>

<script> $('#printModal').modal('show'); </script>
</body>
</html>