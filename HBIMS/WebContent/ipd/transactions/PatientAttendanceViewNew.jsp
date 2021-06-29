<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<!-- 
Patient Attendance VIEW JSP

author: Debashis Sardar

dated: 18/02/2012
-->
<html>
<head>

<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 


<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">

<script language="Javascript" src="../../hisglobal/js/validation2.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/commonFunctions.js"/> </script>

<script language="JavaScript" src="../../ipd/js/PatientAttendanceView.js"></script>
<title>Patient Attendance View</title>

<script type="text/javascript">
</script>

<style> 

body {
font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
font-size: 14px;

line-height: 1.42857143;
color: #333;
background-color: #fff;
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
    padding-top:5px;
    padding-bottom:15px;
    min-width: 100;
}

.legend2 {
  position: absolute;
  top: -0.2em;
  right: 100px;
  background: aliceblue;
  line-height:1.2em;
   
}

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
.prescriptionTile
{
  margin:0.5% 0;
  border-bottom: 1px solid #d7d7d7; 
  padding-bottom: 10px;
  padding: 1% 2% 0.5% 2%; 
  background-color: #fff;
  transition:0.5s;
  box-shadow: 0 0.5px 10px 2px #b0acac;
  border-radius: .25rem;
   color: #666;
}
.prescriptionTile:hover
{ 
  /*box-shadow: 0 1px 18px 0px #bababa;*/ 
  box-shadow: 0 2px 10px 2px #847d7d;
  /* -webkit-transform: translateY(-4px); */
  /* transform: translateY(-4px); */
}
.row {
    padding-bottom: 5px;
    
}
.form-control {
    font-size: 0.8rem;
    font-family: "Helvetica Neue", "Helvetica";
}
.subHeaders{

    font-weight: 500 !important;

   
    font-size: 19px !important;

    padding-bottom: 6px !important;
}
</style>
</head>
<body>
<html:form action="/transactions/PatientAttendanceBSCNT">

<fieldset>
	
  <legend style="width:auto; margin-bottom: 0px;font-size: 1.5rem;font-weight: 400;margin-left:10px;color: #666;" id='nonPrintableLegend'>Patient Attendance View</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="float-right btn btn-outline-danger mt-1 btn-circle" onclick="cancelFunc();" style="padding: .175rem .35rem; line-height: 0.8;background-color: red;">
		<i class="fa fa-ban"  title="Cancel" style="color: white;font-size: 16px;"></i>
	</button>	
	<!-- <button  id="printbutton" type="button" class="float-right btn btn-outline-primary mt-1 btn-circle" data-toggle="modal" data-target="#printModal" onClick="view();" style="padding: .175rem .35rem; line-height: 0.8;background-color: #007bff;">
		<i class="fas fa-eye" title="view" style="color: white;"></i>
	</button>
	 
    <button  type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle"  onClick="return validate1();" name="patientAdmissionModiTransBean"  data-toggle="modal" data-target="#validateModal" style="padding: .175rem .35rem; line-height: 0.8;display: none;background-color: green;">					
		<i class="fa fa-save"  title="Save" style="color: white;"></i>
	</button>	-->											                 
  </div> 

<!-- <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td>Patient Attendance View</td></tr>
</table> -->		
<div class="normalMsg" id="normalMsg"></div>
							<%-- <table width="100%" align="center">
								<tr>
									
									<td class='LABEL' width="25%" height="10%">
									<div align="right"><font color="red">*</font>Service Type</div>
									</td>
									<td class="CONTROL" width="25%"><select name="strServiceType"
										class='comboNormal' onChange="getServiceName(this)" >
										<bean:write name="PatientAttendanceFB"
											property="strServiceType" filter='false' />
									</select></td>
									
									<td class='LABEL' width="25%" >
									<div align="right" id="SERVICE_NAME">
									<font color="red">*</font>Service Name</div>
									</td>
									<td class="CONTROL" width="25%"><select name="strServiceName"
										class='comboNormal' onChange='serviceNameChange(this)'>
										<option value='0'>Select Value</option>
									</select></td>
								</tr>
								<tr>
					<td width="25%" class="LABEL">
						<div align="right" id="divFromDate"><font color="red">*</font>From Date</div>
					</td>
					<td width="25%" class="CONTROL"><div id="divFromDateControl">
						<date:date name="strEffectiveFrom" value="${PatientAttendanceFB.strCtDate}"></date:date></div>
					</td>
					<td width="25%" class="LABEL">
						<div align="right" id="divToDate"><font color="red">*</font>To Date
						</div>
					</td>
					<td width="25%" class="CONTROL"><div id="divToDateControl">
						<date:date name="strEffectiveTo" value="${PatientAttendanceFB.strCtDate}"></date:date>				
						<img title="view patients status" src="../../hisglobal/images/Go.png"  onclick="return GOFunc();" style="cursor: pointer"  tabindex="1">
					
						</div>
					</td>
				</tr>
																					
						</table> --%>
						
						                    <div class="prescriptionTile">
											<div class="row rowFlex reFlex">
												<div class="col-sm-1"></div>
												<div class="col-sm-1"></div>
												<div class="col-sm-2"><label><font color="red">*</font>Service Type</label></div>
												<div class="col-sm-2"><select name="strServiceType"
																						class='form-control' onChange="getServiceName(this)" >
																						<bean:write name="PatientAttendanceFB"
																							property="strServiceType" filter='false' />
																					</select></div>
												<div class="col-sm-2"><label><font color="red">*</font>Service Name</label></div>
												<div class="col-sm-2"><select name="strServiceName"
																						class='form-control' onChange='serviceNameChange(this)'>
																						<option value='0'>Select Value</option>
																					</select></div>
												<div class="col-sm-1"></div>
												<div class="col-sm-1"></div>
												</div>
                                              <div class="row rowFlex reFlex">
												<div class="col-sm-1"></div>
												<div class="col-sm-1"></div>
												<div class="col-sm-2"><label><font color="red">*</font>From Date</label></div>
												<div class="col-sm-2"><div id="divFromDateControl">
						                        <input type="date" class="form-control" name="strEffectiveFrom" value="${PatientAttendanceFB.strCtDate}"></div></div>
												<div class="col-sm-2"><label><font color="red">*</font>To Date</label></div>
												<div class="col-sm-2"><div id="divToDateControl">
						<input type="date" class="form-control" name="strEffectiveTo" value="${PatientAttendanceFB.strCtDate}">				
<!-- 						<img title="view patients status" src="../../hisglobal/images/Go.png"  onclick="return GOFunc();" style="cursor: pointer"  tabindex="1">
 -->					
						</div></div>
												<div class="col-sm-1"></div>
												<div class="col-sm-1"></div>
												</div>
						
<div id="ID_PAT_LIST_HEADER" style="display: none">
<!-- <table class="TABLEWIDTH" cellpadding="0" cellspacing="1px" width="100%" align="center">
			<tr class="TITLE">
				<td colspan="3">Patient List</td>				
			</tr>
			<tr>
				<td width="100%" height="100%">
						<div id="ID_PAT_LIST" >	 
							</div>					
				</td>
				</tr>	
		</table>	 -->
		<div id="ID_PAT_LIST" >	 
							</div>
	 </div>	
<div id="ID_HEADER" style="">
						<!-- table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td> </td></tr>
</table> -->	
</div>	
<div id="ID_CAN_CLR" style="">
<!-- <table class="TABLEWIDTH" width="100%" cellpadding="0" cellspacing="1">
<tr><td align="center">				

		<br>
				<a href="#" class="button"	onClick="formClear();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
</td></tr>
</table> -->

                                         <div align="center">
												
												<button type="button" class="btn btn-success" onclick="return GOFunc();">
												Go&nbsp;<i class="fas fa-angle-double-right"></i></button>
										</div>
</div>	
	
		
<html:hidden name="PatientAttendanceFB" property="strStatus"/> 
<html:hidden name="PatientAttendanceFB" property="strControl" value="-1"/>
<html:hidden name="PatientAttendanceFB" property="strAcceptedFlag" value="0"/>	
<html:hidden name="PatientAttendanceFB" property="hmode"/>
<html:hidden name="PatientAttendanceFB" property="strAge" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strGenderCode" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strAgeCode" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strTreatmentCat" value="0"/> 
<html:hidden name="PatientAttendanceFB" property="strCrNo"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultUnitFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultUnit" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultWard" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultWardFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strIsVacant" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strChk" value=""/>  
<input type="hidden" name="cnt" value="">
<input type="hidden" name="strCtDate" value="${PatientAttendanceFB.strCtDate}">

</div>

</fieldset>
</html:form>
		
</body>
</html>