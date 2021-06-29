<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<!-- 
Patient Attendance JSP

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
 
 
<%-- <link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/cr-no-format.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css"> --%>

<link href="../css/newlayout.css" rel="stylesheet" type="text/css">

<style type='text/css'>

</style>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../transUtil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/newMultiRow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multirow.js"></script>
<script language="JavaScript" src="../../hisglobal/js/dropdown.js"></script>
<script language="JavaScript" src="../../hisglobal/js/common.js"></script>

<script language="JavaScript" src="../../ipd/js/PatientAttendanceBS.js"></script>

<title>Patient Attendance</title>

<script type="text/javascript">

</script>
</head>
<body onLoad="">
<html:form action="/transactions/PatientAttendanceBSCNT" >

<fieldset>
	
	<legend class='legendHeader' id='nonPrintableLegend'>Patient Attendance Transfer in Service Areas</legend>
  <div class="legend2" id='nonPrintableLegend2'>
	<button  type="button" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();">
		<i class="fa fa-ban iround"  title="Cancel"></i>
	</button>
	<button  id="printbutton" type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" data-toggle="modal" data-target="#printModal" onClick="view();">
		<i class="fas fa-eye iround" title="view"></i>
	</button>
	
    <button  type="button" id="savebutton" class="btn btn-outline-success mt-1 btn-circle savebtn"  onClick="return validate1();" data-toggle="modal" data-target="#validateModal" style="display: none;">					
		<i class="fa fa-save iround"  title="Save"></i>
	</button>
  </div>
  <div id="nonPrintable" class="viewport">
 
<div align="center" style="display:none" id="blkHrsId"></div>
<!-- <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td width="75%">Patient Attendance</td>
              <td colspan="1" class="" width="25%" align="right">
              <input type="checkbox" name="strIsView" value="" onClick="view();"> View</td>
		
		</tr>
</table> -->		
<div class="normalMsg" id="normalMsg"></div>
							<%-- <table class="TABLEWIDTH" width="100%" align="center">
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
									 
									<td class='LABEL' width="25%" height="10%">
									<div align="right"><font color="red">*</font>Status</div>
									</td>        
									<td class="CONTROL" width="25%"><select name="strStatusCombo"
										class='comboNormal' onChange="return getPatList(this)">
										<option value='0'>Select Value</option>
									</select></td>
									
									<td class='LABEL' width="25%" >
									</td>
									<td class='CONTROL' width="25%" >
									</td>	
								</tr>
																					
						</table> --%>
						<div class="prescriptionTile">
						<div class="row rowFlex reFlex">
						<div class="col-sm-1"></div>
						<div class="col-sm-1"></div>
						<div class="col-sm-2"><label><font color="red">*</font>Service Type</label></div>
						<div class="col-sm-2"><select name="strServiceType"
										class='browser-default custom-select' onChange="getServiceName(this)" >
										<bean:write name="PatientAttendanceFB"
											property="strServiceType" filter='false' />
									</select></div>
<div class="col-sm-2"><label><font color="red">*</font>Service Name</label></div>
<div class="col-sm-2"><select name="strServiceName"
										class='browser-default custom-select' onChange='serviceNameChange(this)'>
										<option value='0'>Select Value</option>
									</select></div>
<div class="col-sm-1"></div>
<div class="col-sm-1"></div>
</div>
               <div class="row rowFlex reFlex">
<div class="col-sm-1"></div>
<div class="col-sm-1"></div>
<div class="col-sm-2"><label><font color="red">*</font>Status</label></div>
<div class="col-sm-2"><select name="strStatusCombo"
										class='browser-default custom-select' onChange="return getPatList(this)">
										<option value='0'>Select Value</option>
									</select></div>
<div class="col-sm-2"></div>
<div class="col-sm-2"></div>
<div class="col-sm-1"></div>
<div class="col-sm-1"></div>
                  </div>
				<div id="ID_HEADER" style="">
						<!-- <table class="TABLEWIDTH" align="center" cellspacing="0" cellpadding="0" width="100%">
	<tr class="HEADER">
		<td> </td></tr>
</table>	 -->
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
		<p class="subHeaders"><i class="fas fa-list" style="font-size: 26px;">&nbsp;</i>Patient List</p>
	   <div id="ID_PAT_LIST" >	 
							</div>	 
	 </div>	
<div id="ID_CAN_CLR" style="">
<!-- <table  class="TABLEWIDTH" width="100%" cellpadding="0" cellspacing="1">
<tr><td align="center">			
				
				 <br>
				<a href="#" class="button"	onClick="formClear();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel();"><span class="cancel">Cancel</span></a>
</td></tr> 
</table> -->
</div>	
	<div id="ID_MSG" align="center">
	<logic:notEmpty name="PatientAttendanceFB" property="strErrMsgString">
			<font color="red" size="2"
				face="Verdana, Arial, Helvetica, sans-serif"> <b><bean:write
				name="PatientAttendanceFB" property="strErrMsgString" /> </b></font>
		</logic:notEmpty>	
	</div>
	
	<hr>
	<div class="row rowFlex reFlex">
			<div class="col-sm-12" align="right"><i class="fa fa-asterisk"  style="color: red;font-size: smaller;"></i>Fields Mandatory</div>							
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
<html:hidden name="PatientAttendanceFB" property="strDefaultRoom" value=""/>
<html:hidden name="PatientAttendanceFB" property="strDefaultWardFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strDefaultRoomFlag" value="0"/>  
<html:hidden name="PatientAttendanceFB" property="strIsVacant" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strChk" value=""/>  
<html:hidden name="PatientAttendanceFB" property="strBed" />
<input type="hidden" name="cnt" value="">
</div>
</div>
</fieldset>
</html:form>
		
</body>
</html>