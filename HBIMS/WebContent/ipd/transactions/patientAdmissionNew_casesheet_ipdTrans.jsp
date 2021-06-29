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
<%-- <jsp:include page="/DisplayImage" /> --%>
<html>
<head>
<meta charset=utf-8>
<title>Casesheet</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
 -->
<link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/all.css">

<!-- <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no"> -->
<!-- <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>

<!-- <script language="JavaScript" src="../../ipd/js/patientAdmission.js"></script> -->
<script language="Javascript" src="../../hisglobal/js/barcode_code39.js"></script>

<script type="text/javascript">
function DrawCode39Barcode(data, checkDigit)
{
	//alert("inside barcode_code39.js");
	return DrawHTMLBarcode_Code39(data,checkDigit,"no",
					"in", 0,2,0.3,4,//0,1,0.2,2,//these parameters were changed by Mukund on 23.01.2017 for making barcode large enough to be scannable using barcode scanner
					"bottom","left", "",
					"black","white"); 
	//return DrawHTMLBarcode_Code39(data,checkDigit,"no","in", 0,2,0.2,3,"bottom","left", "","black","white"); 
}	


function printcasesheetBarcode()
{
	get_object("divBarCodeControlCrNo").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlCrNo").innerHTML, 0);
	get_object("divBarCodeControlAdmNo").innerHTML=DrawCode39Barcode(get_object("divBarCodeControlAdmNo").innerHTML, 0);
	//window.print();
	//var t=setTimeout("printSlip1()",2000);
}
</script>


<style>

</style>
</head>
<body onload=' printcasesheetBarcode(); window.print(); window.close();'> <!--   -->
<html:form action="/transactions/PatientAdmissionTransCNT.cnt" method="post">
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
		//String path_globalImages = filePath2+File.separator+request.getAttribute("strAdmNo").toString()+".png";
		
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
                       <div class="container">	
		                   <div class="form-group row">
		                   			<div class="col-sm-4"></div>								   								     
								    <div class="col-sm-5" align="center"><B>CASESHEET</B></div>								   								     
								    <div class="col-sm-3" align="center">Print Date:&nbsp;<%=dateNow %></div>								   								     
					       </div>								
                      	</div>

	                  <div class="container">	
				          <div class="form-group row">
					       		   <div class=" col-sm-4" >
					       		    <div align="left"><b>CR No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCrNo"/></div>
					                <div id='divBarCodeControlCrNo' align="center"><bean:write name="paientAdmissionTransBean" property="strCrNo"/></div>				       		    
					       		   </div>								   								     					       
                                    <div class="col-sm-4" >
					       		    <div align="left"><b>IPD No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>
					                <div id='divBarCodeControlAdmNo' align="center"><bean:write name="paientAdmissionTransBean" property="strAdmNo"/></div>				       		    
					       		   </div>						          
                                   <div id='divQRCodeControl' class="col-sm-4" align="left">
		   				           <div><img src="/HBIMS/DisplayImage?stradm=<%=request.getAttribute("strAdmNo") %>" width="100"></div> 
						          </div>						           
			               </div>
			     
				           <div class="form-group row">
                                   <div class=" col-sm-4">
						           <div align="left"><b>Name :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatName"/></div>
						           <div align="left"><b>Father/Spouse Name :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strFatherName"/><bean:write name="paientAdmissionTransBean" property="strHusbandName"/></div>		       		    
                                   <div align="left"><b>Category:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatCategoryName"/></div>
                                   </div>
                                   <div class="col-sm-4">
								   <div align="left"><b>Age/Gender :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strAge"/></div>
								   <div align="left"><b>Marital Status :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strMaritalStatus"/></div>
					       		   <div align="left"><b>AADHAR No :</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strPatientAdhaarNo"/></div>				       		  		       		    
					       		   </div>			
                                   <div class=" col-sm-4">
                                   <div align="left" style="display:none;"><b>Case Sheet No:</b>&nbsp;<bean:write name="paientAdmissionTransBean" property="strCaseSheetNo"/></div>
					       		   <div align="left"><b>MLC No :</b>&nbsp;<logic:equal name="paientAdmissionTransBean" property="strMlcNo" value="0">NA</logic:equal>
					             	<logic:notEqual name="paientAdmissionTransBean" property="strMlcNo" value="0">
						            <bean:write name="paientAdmissionTransBean" property="strMlcNo"/>
						           </logic:notEqual></div>
					       		   </div>							           
					        </div>
	
				           <div class="form-group row">
					       		   <div class="col-sm-4" align="left">
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
						           <div class="col-sm-4" align="left"><b>Ward/Bed :</b>&nbsp;&nbsp;
									<bean:write name="paientAdmissionTransBean" property="strWardName"/>
										<logic:notEmpty name="paientAdmissionTransBean" property="strBed">
											<bean:write name="paientAdmissionTransBean" property="strBed"/></logic:notEmpty>							
									</div>		
						           <div class="col-sm-4" align="left">
						             <div><b>Consulting Doctor :</b>&nbsp;&nbsp;</div>
								     <div><bean:write name="paientAdmissionTransBean" property="strConsultantName"/></div>													
								</div>	
					        </div>

						
			
		</div>
		
	              
		

	        <div class="container">
		         
				           <div class="form-group row">
                                   <div class=" col-sm-4" align="left">
                                   <div><b>Adm. Date/Time(24 HH:MI):</b></div>
                                   <div><bean:write name="paientAdmissionTransBean" property="strAdmDateTime"/></div>
                                   </div>
                                   <div class="col-sm-4" align="left"> <div><b>Discharge Date/Time(24 HH:MI):</b>&nbsp;  </div>		
                                      <div><bean:write name="paientAdmissionTransBean" property="strDisDateTime"/></div>	
                                  <!--  <div class=" col-sm-4" align="left"><b>Hospital Stay:&nbsp;...........</b></div>					 -->		           
					       </div>
			
				     <hr>
				        <div class="form-group row">
                               						           
					       </div>
					       
					    
	           </div>
	
	</div>
	
	
	
	
	 
</html:form>
</body>
</html>