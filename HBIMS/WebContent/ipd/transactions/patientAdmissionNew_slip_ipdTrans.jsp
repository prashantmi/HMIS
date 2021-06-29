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
<jsp:include page="/DisplayImage" />
<html>
<head>
<meta charset=utf-8>
<title>Admission Slip</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> 
 <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script> -->



<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.min.css" rel="stylesheet">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>

  
<script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script>
<script language="Javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<script type="text/javascript">
function DrawCode39Barcode(data, checkDigit)
{

	console.log(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
	console.log(data,checkDigit);
	//alert("inside barcode_code39.js");
	return DrawHTMLBarcode_Code39(data,checkDigit,"no",
					"in", 0,2,0.3,4,//0,1,0.2,2,//these parameters were changed by Mukund on 23.01.2017 for making barcode large enough to be scannable using barcode scanner
					"bottom","left", "",
					"black","white"); 
	//return DrawHTMLBarcode_Code39(data,checkDigit,"no","in", 0,2,0.2,3,"bottom","left", "","black","white"); 
}	
</script>

<style>

.btn-circle {
    width: 37px;
    height: 34px;
    text-align: center;
    padding: 6px 0;
    font-size: 12px;
    line-height: 1.428571429;
    border-radius: 17px;
    margin: -3px;
}
fieldset
{
    border:1px solid #c2c4d9;
    -moz-border-radius:5px;
    -webkit-border-radius:5px;	
    border-radius:5px;	
    padding-left: 10px;
    margin-left: 10px;
    margin-right: 10px;
    padding-right:20px;
    padding-top:10px;
}

.cancelbtn{
	background:#dc3545
}


</style>
</head>
<body onload='printSlip();'>
<html:form action="/transactions/PatientAdmissionTransBSCNT.cnt" method="post">

<% 
		final ResourceBundle hisProp = ResourceBundle.getBundle("hisglobal.hisconfig.hisProperties"); 
		final String header1 =hisProp.getString("PHDM_HEADER1");
		final String header2 =hisProp.getString("PHDM_HEADER2");
		final String header3 =hisProp.getString("PHDM_HEADER3");
		final String header4 =hisProp.getString("PHDM_HEADER4");
		
		HisUtil hisUtil=new HisUtil("IPD","PatAdmSlip");
		String hospcode=request.getSession().getAttribute("HOSPITAL_CODE").toString();
		
		
		Map reqMap=new HashMap();
		
		reqMap.put("REQUEST",request);
		reqMap.put("HOSPCODE",hospcode);
		reqMap.put("FORMAT","html");
		
		final String strHeader=hisUtil.getHospitalHeaderMain(reqMap);
	/* 	final String strHeader=hisUtil.getHospitalHeader1(hospcode, 1, "html");
		final String strHeader1=hisUtil.getHospitalHeader2(hospcode, 1, "html"); */
		
		String filePath2=HisUtil.getParameterFromHisPathXML("TEMP_PATH");
	    //String filePath = "C:\\PHDM\\AHIMS\\Qrcode images\\"+formBean.getStrAdmNo()+".png";
		String path_globalImages = filePath2+File.separator+request.getAttribute("strAdmNo").toString()+".png";
		
		IpdConfigUtil configIpd=new IpdConfigUtil(hospcode);
		
		 //String path_globalImages = resourceBundle.getValueFromKey("IMAGE_PATH_QR")+"JD"+request.getAttribute("strAdmNo").toString()+".png";
		    
		 //System.out.println("path is "+path_globalImages+"adm no is "+request.getAttribute("strAdmNo"));

%>
<div class="errMsg" id="errID"><bean:write name="paientAdmissionTransBean" property="strMsgString"/></div>
<!-- <table width="90%" align="center">
<tbody>
<tr style='display: none;'> -->
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

<br>
                       <div class="container">	
		                   <div class="form-group row">
		                   			<div class="col-md-5"></div>								   								     
								    <div class="col-md-3" ><B>ADMISSION CARD</B></div>								   								     
								    <div class="col-md-4" >Print Date:&nbsp;<%=dateNow %></div>								   								     
					       </div>								
                      	</div>

	                  <div class="container">	
				          <div class="form-group row" id="validateModal">
					       		   <div class=" col-md-5" >
					       		    <div align="left"><b>CR No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCrNo"/></div>
					                <div id='divBarCodeControlCrNo' align="center"><bean:write name="paientAdmissionTransBean" property="strCrNo"/></div>				       		    
					       		   </div>								   								     					       
                                    <div class="col-md-5" >
					       		    <div align="left"><b>IPD No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>
					                <div id='divBarCodeControlAdmNo' align="center"><bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>				       		    
					       		   </div>						          
                                   <div id='divQRCodeControl' class="col-md-2" align="left">
		   				           <div><img src="/HBIMS/DisplayImage?stradm=<%=request.getAttribute("strAdmNo") %>" width="100"></div> 
						          </div>						           
			               </div>
			     
				           <div class="form-group row">
                                   <div class=" col-md-6">
						           <div align="left"><b>Name :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatName"/></div>
						           <div align="left"><b>Father/Spouse Name :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strFatherName"/><bean:write name="paientAdmissionTransBean" property="strHusbandName"/></div>		       		    
                                   <div align="left"><b>Category:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatCategoryName"/></div>
                                   <div align="left"><b>Gate Pass No.:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCaseSheetNo"/></div>
                                   </div>
                                   <div class="col-md-6">
								   <div align="left"><b>Age/Gender :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strAge"/></div>
								   <div align="left"><b>Marital Status :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strMaritalStatus"/></div>
<%-- 					       		   <div align="left"><b>AADHAR No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatientAdhaarNo"/></div>				       		  		       		    
 --%>					  
                                    <div align="left"><b>MLC No :</b>&nbsp;<logic:equal name="paientAdmissionTransBean" property="strMlcNo" value="0">NA</logic:equal>
                                     </div>			
                                  <%--  <div class=" col-md-3">
                                   <div align="left" style="display:none;"><b>Case Sheet No:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCaseSheetNo"/></div>
					             	<logic:notEqual name="paientAdmissionTransBean" property="strMlcNo" value="0">
						            <bean:write name="paientAdmissionTransBean" property="strMlcNo"/>
						           </logic:notEqual></div> --%>
					       		   </div>							           
					        </div>
	
				           <div class="form-group row ">
					       		   <div class="col-md-12" align="left">
					       		   <logic:equal value="0" name="paientAdmissionTransBean" property="isDuplicateSlip">  
									<div align="left"><b>Department/Unit :</b>&nbsp;&nbsp;
										<bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/></div>	
									</logic:equal>
									
									<logic:equal value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
									<div align="left"><b>Department/Unit :</b>&nbsp;&nbsp;
										<bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/>
									</div>	
									</logic:equal>
					       		   </div>
					       		   <div  class="col-md-12" align="left"><b>Ward/Bed :</b>&nbsp;&nbsp;
									<bean:write name="paientAdmissionTransBean" property="strWardName"/>
										<logic:notEmpty name="paientAdmissionTransBean" property="strBed">
											<bean:write name="paientAdmissionTransBean" property="strBed"/></logic:notEmpty>							
									</div>
 						             <div class="col-md-12"  align="left">
						             <div><b>Consulting Doctor :</b>&nbsp;&nbsp;<bean:write name="paientAdmissionTransBean" property="strConsultantName"/></div>
								     </div>													
									
					       </div>


    			  
						
 			<%-- 	<logic:equal    value="1" name="paientAdmissionTransBean" property="strIsAdvanceAmountAtAdmission"> --%>
				<logic:notEqual value="0" name="paientAdmissionTransBean" property="strAdmissionCharge">
         			<tr>
						<td class='SLIPCONTROL' colspan="2" width="50%">
							<div align="left"><b>Admission Charges(Rs.):&nbsp;<font size="2">
								<bean:write name="paientAdmissionTransBean" property="strAdmissionChargeValue"/>&nbsp;&nbsp;
								<img src='/HBIMS/hisglobal/images/INR.png'></font></b>
							</div>
						</td>
						<td class='SLIPCONTROL' colspan="1" width="50%"><div align="left"></div></td>
					</tr>
				</logic:notEqual>
				<%-- </logic:equal>  --%>
                        				
				<logic:equal value="1" name="paientAdmissionTransBean" property="strIsNewBorn">
				<tr>
					<td class='SLIPCONTROL' colspan="1" width="30%"><div align="left"><b>Mother IPD No.:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMotherAdmissionNo"/></font></b></div></td>
					<td class='SLIPCONTROL' colspan="1" width="40%"><div align="left"><b>Mother Name:&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strMotherName"/></font></b></div></td>		
					<td class='SLIPCONTROL' colspan="1" width="30%"><div align="left"><b>Registration Charges(Rs.):&nbsp;<font size="2"><bean:write name="paientAdmissionTransBean" property="strRegChg"/></font></b></div></td>				
				</tr>
				</logic:equal>
		</div>
		
	                   <div class="container">
						 <div class="form-group row">
                                   <div class=" col-md-6">
						           <div align="left"><b>Patient Address :</b>&nbsp;</div>		       		    
                                   <div align="left"><b>House No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strHouseNo"/></div>
                                   <div align="left"><b>Village/Town :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCity"/></div>
                                   <div align="left"><b>District :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strDistrict"/></div>
                                   <div align="left"><b>State :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strStateName"/></div>
                                   <div align="left"><b>Telephone:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPhoneNo"/></div>
                                   <div align="left"><b>Emergency Contact Person,Address & Phone:</b>
										<bean:write name="paientAdmissionTransBean" property="strFirstPersonName"/>
										<logic:notEmpty name="paientAdmissionTransBean" property="strEmgAddress1">
										<bean:write name="paientAdmissionTransBean" property="strEmgAddress1"/></logic:notEmpty>							
										<bean:write name="paientAdmissionTransBean" property="strFirstpersonphone" /></div>
                                   </div>
                                   <div class="col-md-6">
                                   <div align="left"><b>Street :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strStreet"/></div>
                                   <div align="left"><b>Landmark :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strTehsil"/></div>	
                                   <div align="left"><b>Pin Code :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPinCode"/></div>
                                   <div align="left"><b>Country:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCountryName"/></div>
                                   <div align="left"><b>Mobile No:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strMobileNo"/></div>
                                   <div align="left"><b>Email Id :</b>&nbsp;</div> 				       		  		       		    
					       		   </div>			
                                   							           
					       </div>
	                	</div>
		

	        <div class="container">
		         
				           <div class="form-group row">
                                   <div class=" col-md-12" align="left">
                                   <div><b>Adm. Date/Time(24 HH:MI):</b><bean:write name="paientAdmissionTransBean" property="strAdmDateTime"/></div>
                                   </div>
                                   <div class="col-md-12" align="left"><b>Discharge Date/Time(24 HH:MI):&nbsp;...........</b>&nbsp;  </div>			
                                   	<div class="col-md-12" align="left" ><b>Discharge Status:</b>&nbsp;&nbsp;RELIEVED/CURED/IMPROVED/LAMA/DAMA/REFERRED TO...........</div>						           
					                <div class="col-md-12" align="left" ><b>Dead/Death Date/Time(24 HH:MI)</b>&nbsp;&nbsp;................</div>						           
					       
					       </div>
			
				     <hr>
				          <div class="form-group row">
                                   <div class=" col-md-5">
						           <div align="left"><b>For Medico Legal Purpose</b>&nbsp;</div>
                                   </div>
                                   <div class="col-md-4">
                                   </div>
                                   <div class=" col-md-3">
					       		   </div>							           
					       </div>
					       <div class="form-group row">
                                   <div class=" col-md-6">
						          <div align="left" ><b>Details of Police Station:</b>&nbsp;
						             <bean:write name="paientAdmissionTransBean" property="strDetPs"/></div>
						          <div align="left"><b>Police Information:</b>&nbsp;
						             <bean:write name="paientAdmissionTransBean" property="strPolInfo"/></div>
                                   </div>
                                   <div class="col-md-6">
                                   	<div align="left"><b>Name of Informant:</b>&nbsp;
                                       <bean:write name="paientAdmissionTransBean" property="strNamInf"/></div>
                                   <div align="left"><b>Identification Marks:</b>&nbsp;
						               <bean:write name="paientAdmissionTransBean" property="strIdenMark"/></div>
						           <div align="left"><b>MLC Remarks:</b>&nbsp;
						               <bean:write name="paientAdmissionTransBean" property="strRefRem"/></div>
                                   </div>
                                   							           
					       </div>
				<hr>
				 <div class="form-group row">
                                   <div class=" col-md-5">
                                   </div>
                                   <div class="col-md-4">
                                   <div align="Right"><b>ICD/SNOMED CT</b>&nbsp;</div>
                                   </div>
                                   <div class=" col-md-3">
					       		   </div>							           
					       </div>
					       <div class="form-group row">
                                   <div class=" col-md-5">
						             <div align="left"><b>Provisional Diagnosis</b></div>
						             <div align="left"><b>Differential Diagnosis</b></div>
						             <div align="left"><b>Final Diagnosis</b></div>
                                   </div>
                                   <div class="col-md-4">
                                   </div>
                                   <div class=" col-md-3">
					       		   </div>							           
					       </div>
					       
					    
					      
					        
					       <hr><br>
					        <div class="form-group row">
                                   <div class=" col-md-6">
                                   <div align="left">Name &amp; Signature of MO</div>
                                   <div align="left">Date &amp; Time :</div>
                                   </div>
                                   <div class="col-md-6">
                                   <div align="left">Name &amp; Signature of Specialist/Consultant</div>
                                   <div align="left">Date &amp; Time :</div>
                                   </div>	
					       		   						           
					       </div>
	           </div>
	
	
	
	
	<div class="container-fluid">
	<img  src='/HBIMS/hisglobal/images/Scissor.png' width="100%">
	
	
			<div class="row">
			<div align="left"> <%=strHeader%></div>
			 </div>
			 <br>
			 
	                 <div class="form-group row">
	                  <div class=" col-md-1">
	                  </div>
                                   <div class=" col-md-5">
                                    <div align="left" ><b>NAME OF PATIENT :</b>&nbsp;
						            <bean:write name="paientAdmissionTransBean" property="strPatName"/></div>
						            <logic:equal value="0" name="paientAdmissionTransBean" property="isDuplicateSlip"> 
						            <div align="left"><b>DEPARTMENT :</b>&nbsp;
						            <bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/></div>
						            </logic:equal>
						            <logic:equal value="1" name="paientAdmissionTransBean" property="isDuplicateSlip">
										<div align="left"><b>DEPARTMENT/UNIT :</b>&nbsp;
										<bean:write name="paientAdmissionTransBean" property="strDeptUnitName"/></div>
										</logic:equal>
										 <div align="left"><b>TREATING DOCTOR'S NAME :</b>&nbsp;
						          <bean:write name="paientAdmissionTransBean" property="strConsultantName"/></div>
						          <div align="left"><b>PATIENT ATTENDANT'S NAME :</b>&nbsp;
						          <bean:write name="paientAdmissionTransBean" property="strFirstPersonName"/></div>
                                   </div>
                                   <div class="col-md-1">
                                   </div>
                                   <div class="col-md-5">
                                   <div align="left" ><b>AGE/GENDER :</b>&nbsp;
						            <bean:write name="paientAdmissionTransBean" property="strAge"/></div>
						            <div align="left"><b>WARD/BED :</b>&nbsp;
						                <bean:write name="paientAdmissionTransBean" property="strWardName"/>
							             <logic:notEmpty name="paientAdmissionTransBean" property="strBed" >
								         <bean:write name="paientAdmissionTransBean" property="strBed"/></logic:notEmpty></div>
								         <div align="left"><b>IP No. :</b>&nbsp;
						            <bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>
						            </div>
                             			           
					       </div>
					       
					        <div class="form-group row">
                                   <div class=" col-md-1">
                                   
                                   </div>
                                   <div class="col-md-3">
                                   <div align="left"><b>Valid for 7 days</b></div>  
                                   </div>
                                   <div class=" col-md-5">
					       		   </div>
					       		   <div class=" col-md-3">
					       		   <div ><b>SIGN. OF ISSUING CLERK</b></div>
					       		   </div>							           
					       </div>
					        </div>



	<%if(configIpd.getStrAdvanceDepsoitAtAdmissionCounter().equals("1")){ %>




		<logic:equal value="0" name="paientAdmissionTransBean"
			property="isDuplicateSlip">

			<!-- Billing Reciept for Advance -->

			<br>
			<br>
			<img src='/HBIMS/hisglobal/images/Scissor.png' width="100%">
			<br>
			<br>


			<div class="container">
				<table width="100%" cellspacing="0px" cellpadding="0">
					<%=strHeader%>
				</table>
			</div>

			<table
				style="border: 1px solid black; border-collapse: collapse; background-color: rgba(186, 185, 185, 1);"
				width="100%" cellspacing="0" cellpadding="0">
				<tr>
					<td class='SLIPCONTROL' colspan="6"><div align="center">
							<b></b>
						</div></td>
				</tr>
				<tr>
					<td class='SLIPCONTROLBOLD' colspan="6"><div align="center">
							<b>BILLING SERVICES RECEIPT</b>
						</div></td>
				</tr>
			</table>
			<%
				/* if(request.getAttribute("filePath")!=null)
								{ */
						//String file=request.getAttribute("filePath").toString();
						//String file="C:/NIMS/AHIMSG5/PrintTemp/Billing10047.dat";

						String file = HisUtil.getParameterFromHisPathXML("TEMP_PATH") + "/" + "Billing"
								+ request.getSession().getAttribute("SEATID").toString() + ".dat";
						//String file="C:/AIIMSP/AHIMSG5/PrintTemp/Billing10018.dat";
						BufferedReader reader = new BufferedReader(new FileReader(file));
						StringBuilder sb = new StringBuilder();
						String line;
						while ((line = reader.readLine()) != null) {
							sb.append(line + "\n");
						}
						out.println("<center><pre>" + sb.toString() + "</pre></center>");
						/* } */
			%>

		</logic:equal>
		
		
 <%} %>

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
<script>$("#printModal").modal('show');</script>
</body>
</html>