<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>

<%--
/**
 * Developer Name : Saratkumar Thokchom
 * Process Name : Engineer Desk
 * Date : 10/July/2013
 * Modify By/Date : 
 */ 
 --%>

<html>
<head>

<his:css src="/hisglobal/css/tab.css" />
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:css src="/hisglobal/css/calendar-tas.css" />

<his:javascript  src="/hisglobal/masterutil/js/master.js" />
<his:javascript  src="/hisglobal/js/util.js" />
<his:javascript  src="/hisglobal/js/tab.js" />
<his:javascript  src="/hisglobal/js/calendar.js" />
<his:javascript  src="/hisglobal/js/validation.js" />
<his:javascript  src="/hisglobal/js/time.js" />
<his:javascript  src="/hisglobal/js/commonFunctions.js" />
<his:javascript  src="/hisglobal/js/popup.js" />
<his:javascript  src="/hisglobal/js/utilityFunctions.js" />
<his:javascript	src="/bmed/js/maintenance_warranty_contract.js"/>
<his:javascript	src="/bmed/transactions/js/bmed_engineer_desk_trans.js" />


</head>

<body marginheight="0" marginwidth="0"  onload="chkRecordSaved()">
<html:form name="hemmsEngineerDeskFB" action="/transactions/HemmsEngineerDeskACTION"
	type="bmed.transactions.controller.fb.HemmsEngineerDeskFB">
	<%
         
	    String fl=(String)request.getAttribute("flag");
         try
         {
	       if(fl.equals(null) || fl.equals(""))
	       {
		      fl="0";
	       }
         }
         catch(Exception e)
         {
	        fl="0";
          }
         int flag=Integer.parseInt(fl);
         System.out.println("Flag ID "+String.valueOf(flag));
         int counter=0;
         String cntval=request.getParameter("counter");
         try
         {
	      if(cntval.equals(null) || cntval.equals(""))
	      {
			cntval="0";
	      }
	     else
	      {
			counter=Integer.parseInt(cntval);
			counter++;
			}
      }
           catch(Exception e)
         {
	       cntval="0";
          }
      counter=flag;	
      System.out.println("flag::"+flag);
  %>
  
   <input type="hidden" name="tselected">
  <input type="hidden" name="counter" value="<%=counter%>">
  <input type="hidden" name="flag" value="<%=flag%>">
  
	<his:TransactionContainer>
	
	<logic:equal name="hemmsEngineerDeskFB" property="strPageFlag" value="ITEM">	
	
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>
	
	<logic:equal name="hemmsEngineerDeskFB" property="strPageFlag" value="NON_ITEM">
		<his:TitleTag name="Engineer Desk">
		</his:TitleTag>
	</logic:equal>	
	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td colspan="4" class="LABEL_TD">

							<html:radio name="hemmsEngineerDeskFB" property="strItemOrNonItemMode" value="1" onclick="changeViewMode();" /> Item
							<!-- html:radio name="hemmsEngineerDeskFB" property="strItemOrNonItemMode" value="0" onclick="changeViewMode();" / --> <!-- Non-Item -->
					</td>
				</tr>
		</table>
		</his:ContentTag>		

<his:ContentTag>
			<table class="TABLE_STYLE">
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintType}
					<input type="hidden" name="strComplaintType" value="${hemmsEngineerDeskFB.strComplaintType}" />
					</td>
					<td width="25%" class="LABEL_TD"></td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Complaint Id</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintId}</td>
					<td width="25%" class="LABEL_TD">Complaint Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintDate}
					<input type="hidden" name="strComplaintDate" value="${hemmsEngineerDeskFB.strComplaintDate}" />
					</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Hospital Name</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strStoreName}
					<input type="hidden" name="strStoreName" value="${hemmsEngineerDeskFB.strStoreName}" />
					</td>
					<td width="25%" class="LABEL_TD">Lab Name</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strLabName}
					<input type="hidden" name="strLabName" value="${hemmsEngineerDeskFB.strLabName}" />
					</td>
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Engineering Item Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEnggItemType}
					<input type="hidden" name="strEnggItemType" value="${hemmsEngineerDeskFB.strEnggItemType}" />
					</td>
					<td width="25%" class="LABEL_TD">Engineering Item Sub Type</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEnggItemSubType}
					<input type="hidden" name="strEnggItemSubType" value="${hemmsEngineerDeskFB.strEnggItemSubType}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Equipment Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemName}
					<input type="hidden" name="strItemName" value="${hemmsEngineerDeskFB.strItemName}" />
					</td>
					<td class="LABEL_TD">Equipment Model</td>
					<!--<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemBatchNo}</td>commented out on 08-July-2013 as per requirement-->
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemModel}
					<input type="hidden" name="strItemModel" value="${hemmsEngineerDeskFB.strItemModel}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">UIN</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strEquipmentUIDNo}
					<input type="hidden" name="strEquipmentUIDNo" value="${hemmsEngineerDeskFB.strEquipmentUIDNo}" />
					</td>
					<td width="25%" class="LABEL_TD">Item Serial No.</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strItemSerialNo}
					<input type="hidden" name="strItemSerialNo" value="${hemmsEngineerDeskFB.strItemSerialNo}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Manufacturer/Supplier Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strManufacturerName}
					<input type="hidden" name="strManufacturerName" value="${hemmsEngineerDeskFB.strManufacturerName}" />
					</td>
					<td width="25%" class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				<tr>
					<td class="LABEL_TD">Complaint Description</td>
					<td class="CONTROL_TD" colspan="3">${hemmsEngineerDeskFB.strComplaintDescription}
					<input type="hidden" name="strComplaintDescription" value="${hemmsEngineerDeskFB.strComplaintDescription}" />
					</td>
				</tr>
			</table>
		</his:ContentTag>
		
		<his:SubTitleTag name="">
		<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('warrantyDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Guarantee Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Warranty Details">
			<table class="TABLE_STYLE" id="warrantyDetailsTable" style="display: none;">
				<bean:write name="hemmsEngineerDeskFB" property="strWarrantyDetailsTable" filter="false"/>
				<html:hidden property="strWarrantyDetailsTable" value="${hemmsEngineerDeskFB.strWarrantyDetailsTable}"/>
			</table>
		</his:ContentTag>

		
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('maintenanceContractDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Maintenance Contract Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		
		<his:ContentTag name="Maintenance Contract Details">
			<table class="TABLE_STYLE" id="maintenanceContractDetailsTable" style="display: none;">
				<bean:write name="hemmsEngineerDeskFB" property="strMaintenanceContractDetailsTable" filter="false"/>
				<html:hidden property="strMaintenanceContractDetailsTable" value="${hemmsEngineerDeskFB.strMaintenanceContractDetailsTable}"/>
			</table>
		</his:ContentTag>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('acknowledgeDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >Complaint Acknowledgment and Approval Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Complaint Acknowledgment">
			<table class="TABLE_STYLE" id="acknowledgeDetailsTable" style="display: none;" >
			     <tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsAcknowledge}
			<input type="hidden" name="strIsAcknowledge" value="${hemmsEngineerDeskFB.strIsAcknowledge}" />
			</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strRemarks}
			<input type="hidden" name="strRemarks" value="${hemmsEngineerDeskFB.strRemarks}" />
			</td>
			
		</tr>
		<tr>
					<td width="25%" class="LABEL_TD">Complaint Nature</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strComplaintNature}
					<input type="hidden" name="strComplaintNature" value="${hemmsEngineerDeskFB.strComplaintNature}" />
					</td>
					<td width="25%" class="LABEL_TD">Action Taken</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strActionTaken}
					<input type="hidden" name="strActionTaken" value="${hemmsEngineerDeskFB.strActionTaken}" />
					</td>
			</tr>     
			     <tr>
			<td width="25%" class="LABEL_TD" >Approved</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsApproved}
			<input type="hidden" name="strIsApproved" value="${hemmsEngineerDeskFB.strIsApproved}" />
			</td>

					<td width="25%" class="LABEL_TD" >Remarks</td>
					<td width="25%" class="CONTROL_TD" >${hemmsEngineerDeskFB.strRemarks}
					<input type="hidden" name="strRemarks" value="${hemmsEngineerDeskFB.strRemarks}" />
					</td>
				</tr>
		</table>
		</his:ContentTag>
		<his:ContentTag name="HEMM Acknowledgment">
			<table class="TABLE_STYLE" style="display: none;">
			<tr>
			<td width="25%" class="LABEL_TD">Acknowledgment</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIsHEMAcknowledge}
			<input type="hidden" name="strIsHEMAcknowledge" value="${hemmsEngineerDeskFB.strIsHEMAcknowledge}" />
			</td>
			<td width="25%" class="LABEL_TD">Remarks</td>
			<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strAckRemarks}
			<input type="hidden" name="strAckRemarks" value="${hemmsEngineerDeskFB.strAckRemarks}" />
			</td>
			</tr>
			     
			</table>
		</his:ContentTag>	
		
		
		<%-- ************** Schedule Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('scheduleDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >HEMM Schedule Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag name="Schedule Details">
			<table class="TABLE_STYLE" id="scheduleDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Expected Schedule Date
					</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strScheduleDate}
					<input type="hidden" name="strScheduleDate" value="${hemmsEngineerDeskFB.strScheduleDate}" />
					</td>
					<td width="25%" class="LABEL_TD">Schedule
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strScheduleTime}
					<input type="hidden" name="strScheduleTime" value="${hemmsEngineerDeskFB.strScheduleTime}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Service Engineer Remarks</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strServiceEngineerRemarks}
					<input type="hidden" name="strServiceEngineerRemarks" value="${hemmsEngineerDeskFB.strServiceEngineerRemarks}" />
					</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Intimation
					Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIntimationDate}
					<input type="hidden" name="strIntimationDate" value="${hemmsEngineerDeskFB.strIntimationDate}" />
					</td>
					<td width="25%" class="LABEL_TD">Intimation
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strIntimationTime}
					<input type="hidden" name="strIntimationTime" value="${hemmsEngineerDeskFB.strIntimationTime}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Contact Person</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strContactPerson}
					<input type="hidden" name="strContactPerson" value="${hemmsEngineerDeskFB.strContactPerson}" />
					</td>
					<td class="LABEL_TD">Contact No.</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strContactNo}
					<input type="hidden" name="strContactNo" value="${hemmsEngineerDeskFB.strContactNo}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Company Name</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCompanyName}
					<input type="hidden" name="strCompanyName" value="${hemmsEngineerDeskFB.strCompanyName}" />
					</td>
					<td class="LABEL_TD">Company Address</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCompanyAddress}
					<input type="hidden" name="strCompanyAddress" value="${hemmsEngineerDeskFB.strCompanyAddress}" />
					</td>
				</tr>
				<tr>
					<td class="LABEL_TD">Vendor Id</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strVendorId}
					<input type="hidden" name="strVendorId" value="${hemmsEngineerDeskFB.strVendorId}" />
					</td>
					<td class="LABEL_TD">Communication
					Id</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strCommunicationId}
					<input type="hidden" name="strCommunicationId" value="${hemmsEngineerDeskFB.strCommunicationId}" />
					</td>
				</tr>
				<tr>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hemmsEngineerDeskFB.strServiceProviderRemarks}
				<input type="hidden" name="strServiceProviderRemarks" value="${hemmsEngineerDeskFB.strServiceProviderRemarks}" />
				</td>
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
		</his:ContentTag> 
		<%--======================== Schedule Details End ========================= --%>	
	<%-- ************** Attend Details Start ***************** --%>
		<his:SubTitleTag name="">
			<table width="100%" cellpadding="0" cellspacing="0" border="0" style="border-collapse: collapse;">
				<tr>
					<td width="5%" style="text-align: center;"><img alt="Show" src="/HEMMS_ODISHA/hisglobal/images/plus.gif" style="cursor: pointer;" onclick="tableShow('attendDetailsTable',this);"></td>
					<td width="95%" style="text-align: left;" >HEMM Attend Details</td>
				</tr>
			</table>
		</his:SubTitleTag>
		<his:ContentTag  name="Attend Details">
		
			<table class="TABLE_STYLE" id="attendDetailsTable" style="display: none;">
				<tr>
					<td width="25%" class="LABEL_TD">Service Engineer Name
					</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEngineerName}</td>
					<input type="hidden" name="strEngineerName" value="${hemmsEngineerDeskFB.strEngineerName}" />
					<td width="25%" class="LABEL_TD">Service Engineer Address</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strEngineerAddress}</td>
					<input type="hidden" name="strEngineerAddress" value="${hemmsEngineerDeskFB.strEngineerAddress}" />
				</tr>
				<tr>
					<td class="LABEL_TD">Mobile Number</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strMobileNo}</td>
					<input type="hidden" name="strMobileNo" value="${hemmsEngineerDeskFB.strMobileNo}" />
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>	
				</tr>
				<tr>
					<td width="25%" class="LABEL_TD">Attend
					Date</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strHEMAttendDate}</td>
					<input type="hidden" name="strHEMAttendDate" value="${hemmsEngineerDeskFB.strHEMAttendDate}" />
					<td width="25%" class="LABEL_TD">Attend
					Time</td>
					<td width="25%" class="CONTROL_TD">${hemmsEngineerDeskFB.strHEMAttendTime}&nbsp;[HH:MM]
					24hr</td>
					<input type="hidden" name="strHEMAttendTime" value="${hemmsEngineerDeskFB.strHEMAttendTime}" />
				</tr>
				<tr>
					<td class="LABEL_TD">Actual Problem Description</td>
					<td class="CONTROL_TD">${hemmsEngineerDeskFB.strActualProblemDesc}</td>
					<input type="hidden" name="strActualProblemDesc" value="${hemmsEngineerDeskFB.strActualProblemDesc}" />
					<td class="CONTROL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
				<tr>
				<td class="LABEL_TD">Remarks</td>
				<td class="CONTROL_TD">${hemmsEngineerDeskFB.strAttendRemarks}</td>
				<input type="hidden" name="strAttendRemarks" value="${hemmsEngineerDeskFB.strAttendRemarks}" />
					<td class="LABEL_TD"></td>
					<td class="CONTROL_TD"></td>
				</tr>
			</table>
			
		</his:ContentTag> 
		
		<%--======================== Attend Details End ========================= --%>
	<his:TitleTag name="Engineer Desk">
		</his:TitleTag>	
		
		<his:ContentTag>
			<table class="TABLE_STYLE">
			<tr>
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">Vendor Name</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVendorServiceEngId">
						<select name="strVendorServiceEngName" class='COMBO_NORMAL' onchange="getAttendName();"  >
									<bean:write name="hemmsEngineerDeskFB" property="strServiceEngNameCombo" filter="false" />
								</select>
								<html:hidden property="strServiceEngNameCombo" value="${hemmsEngineerDeskFB.strServiceEngNameCombo}"/>
								</div>
					</td>
			
<!-- Communicate Id/Contact No -->	


	
			
				<td width="25%" class="LABEL_TD">Communicate Id/Contact No</td>
					<td width="25%" class="CONTROL_TD">
					<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strCommunicateIdContactId" value="${hemmsEngineerDeskFB.strCommunicateIdContactId}" onkeypress="return validateData(event,2);">
					</td>
			
			
	<!--	Attend Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date of Attend</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strAttendDate" value="${hemmsEngineerDeskFB.strAttendDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time of Attend
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strAttendTime" value="${hemmsEngineerDeskFB.strAttendTime}"  onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>		
			
			
			
			
			
				<!--	Closing Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date of Closing</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strClosingDate" value="${hemmsEngineerDeskFB.strClosingDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time of Closing
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strClosingTime" value="${hemmsEngineerDeskFB.strClosingTime}"  onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>		
			
			
			
			<!-- Is Spare Part Maintenance Involved -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						Spare Parts Used
					</td>
					
					<td width="25%" class="CONTROL_TD">
					<input type="checkbox" name="strIsSparePartsMaintenanceInvolved" checked="checked" value="1" onclick="isSparePartsMaintenanceInvolved();">
					<!--<html:checkbox name="hemmsEngineerDeskFB" property="strIsSparePartsMaintenanceInvolved" value="1" onclick="isSparePartsMaintenanceInvolved();"/>--> 
					<html:hidden property="strIsSparePartsMaintenanceInvolved" value="${hemmsEngineerDeskFB.strIsSparePartsMaintenanceInvolved}"/>	
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
			
			
			<!-- Spare Part Multi-row begins here -->
			<tr>
			<td width="100%" colspan="4">
			<his:ContentTag>
	   			<div id="spareDiv" style="display: block;">
				   <table class="TABLE_STYLE" cellspacing="0px" cellpadding="0px">
						<tr>
							<td width="10%" colspan="1" class="LABEL_TD">Sequence No.</td>
							<td width="20%" colspan="1" class="LABEL_TD">Spare Name</td>
							<td width="20%" colspan="1" class="LABEL_TD">Manuf. Name</td>
							<td width="10%" colspan="1" class="LABEL_TD">Spare Sl. No.</td>
							<td width="10%" colspan="1" class="LABEL_TD">Manuf. Sl. No.</td>
							<td width="15%" colspan="1" class="LABEL_TD">Status</td>
							<td width="15%" colspan="1" class="LABEL_TD">Spare Cost</td>
						</tr>
					<%
        			try{
        			%>	
        		<tr>
        		<td width="80%" colspan="7" class="LABEL_TD">
        		<div align="right"><img src="../../hisglobal/images/plus.gif" style="cursor:hand" width="17" height="18" onclick='<%="addrow("+counter+")"%>'></div>
	      	   </td>
	      	   </tr>
	      	   <%	
    
        }catch(Exception e)
        	{
	     		System.out.println("error before logic"+e);
	    	}
        System.out.println("Counter Data = "+counter);   
	    if(counter!=0)
		{
	    	
			int temp_cnt=1;
			int sequence=1;
			int m=0;
			try{
				%>
				<logic:iterate name='hemmsEngineerDeskFB' property='rows' id='record' indexId='k'> 
			       <%
					System.out.println("flag in iterate::"+flag);
				  
			 	   	
			 %>
			
			<tr>
				<td width="10%" colspan="1" class="LABEL_TD"><div align="center">
				<html:text name="record" property="strSpareSeqNo" readonly="true" size="5" value='<%=""+sequence%>'></html:text>
				</div></td>
				<td width="20%" colspan="1" class="LABEL_TD">
				<div id="sparePartNameDiv" align="center">
				<html:select name="record" property="strSparePartName" onchange=""  >
				<bean:write name="hemmsEngineerDeskFB" property="strSparePartNameOptions" filter="false" />
				</html:select>
				</div>
				</td>
				<td width="20%" colspan="1" class="LABEL_TD">
				<div id="ManufactorNameDiv" align="center" style="width:100%;">
				<html:select name="record" property="strSpareManufName" style="width:100%" onchange="">
				<bean:write name="hemmsEngineerDeskFB" property="strManufactureNameOptions" filter="false" />
				</html:select>
				</div>
				</td>
				<td width="10%" colspan="1" class="LABEL_TD"><div align="center">
				<html:text name="record" property="strSpareSlNo" maxlength="12" onkeypress="return validateData(event,7);"></html:text>
				</div></td>
				<td width="10%" colspan="1" class="LABEL_TD"><div align="center">
				<html:text name="record" property="strSpareManufSlNo" maxlength="12" onkeypress="return validateData(event,7);"></html:text>
				</div>
				</td>
				<td width="15%" colspan="1" class="LABEL_TD"><div align="center">
				<div id="statusDiv" align="center">
				<html:select name="record" property="strStatus" onchange=""  >
				<bean:write name="hemmsEngineerDeskFB" property="strSparePartStatusOptions" filter="false" />
				</html:select>
				</div>
				</div></td>
				<td width="15%" colspan="1" class="LABEL_TD"><div style="float: left;">
				<html:text name="record" property="strSpareCost" styleId="strSpareCost" maxlength="10" size="8" onblur="calculateTotal(this);" onkeypress="return validateData(event,7);  "></html:text>
				</div>
				<div  align="right"><img src="../../hisglobal/images/minus.gif" style="cursor:hand" 
				width="17" height="18" alt="Remove Row" onclick="delrow(<%=k%>);"></div>
				</td>
			</tr>	
			<%
   			temp_cnt++;
		   sequence++;
   	    %>
  		</logic:iterate>
  
  		<%
		 
			}//try ends
			catch(Exception e)
				{
					System.out.println("erroe in logic iterate-->" + e);
				}
			}//if ends
       %>   
                  </table>
                  </div>
                  </his:ContentTag>
			</td>
			</tr>
		</table>
		</his:ContentTag>	
		<his:ContentTag>
			<table class="TABLE_STYLE">
			
<!--	Problem Description -->

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Problem Description</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="hemmsEngineerDeskFB" property="strProblemDescription" cols="25" rows="2" onkeypress="return validateData(event,9);" />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				

<!--	Solution provided -->

				<tr>
					<td width="25%" class="LABEL_TD"><font color="red">*</font>Solution Provided</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="hemmsEngineerDeskFB" property="strSolutionProvided" cols="25" rows="2" onkeypress="return validateData(event,9);" />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>			
				
<!--	Remarks -->

				<tr>
					<td width="25%" class="LABEL_TD">Remarks</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="hemmsEngineerDeskFB" property="strServiceRemarks" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
				
				
<!--	Reason for close -->

				<tr>
					<td width="25%" class="LABEL_TD">Reason for Close</td>
					<td width="25%" class="CONTROL_TD">
						<html:textarea name="hemmsEngineerDeskFB" property="strReasonForClosing" cols="25" rows="2"  />
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
				</tr>		
				
	<!-- Is  Item In working Condition -->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Is Item In Working Condition
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:radio name="hemmsEngineerDeskFB" property="strIsItemInWorkingCondition" value="0" onclick="isItemInWorkingConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="CONTROL_TD">	
						<html:radio name="hemmsEngineerDeskFB" property="strIsItemInWorkingCondition" value="1" onclick="isItemInWorkingConditionchangeMode();" /> NO
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				
				
				<!--	From Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>From Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strFromDate" value="${hemmsEngineerDeskFB.strFromDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>From Time
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strFromTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>					

			<!-- Total Cost-->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Total Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strTotalCost" id="strTotalCost" onkeypress="return validateData(event,2);">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				<tr >
					<td width="25%" class="LABEL_TD">
						Is Penality
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<html:checkbox name="hemmsEngineerDeskFB" property="strIsPenality" styleId="strIsPenality" value="0" onclick="isPenaltyConditionchangeMode();"/> Yes
					</td>
					
					<td width="25%" class="LABEL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
				</table>
				</his:ContentTag>
				<his:ContentTag>
				<table class="TABLE_STYLE" id="penaltyAmtId" style="display: none;">
				<tr>
					<td width="30%" class="LABEL_TD">Penalty Amount
					</td>
					<td width="20%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strPenalityAmount" onblur="setNetAmount(this);" onkeypress="return validateData(event,2);">
					</td>
					<td width="30%" class="CONTROL_TD"></td>
					<td width="20%" class="CONTROL_TD"></td>
					
				</tr>
				<tr>
					<td width="30%" class="LABEL_TD"><font color="red">*</font>Order No.
					</td>
					<td width="20%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strOfficeOrderNo" onkeypress="return validateData(event,7);">
					</td>
					<td width="30%" class="LABEL_TD">Order Date
					</td>
					<td width="20%" class="CONTROL_TD">
					<dateTag:date name="strOfficeOrderDt" value="${hemmsEngineerDeskFB.strOfficeOrderDt}"></dateTag:date>
					</td>
				</tr>
				</table>
				</his:ContentTag>
				
				<!-- Order No and Order Date-->
			
			<his:ContentTag>
				<table class="TABLE_STYLE">
				
				<!-- Net Cost-->
			
			<tr>
					<td width="25%" class="LABEL_TD">
						<font color="red">*</font>Net Cost
					</td>
					
					<td width="25%" class="CONTROL_TD">
						<input type="text" class="TEXT_FIELD_MAX" maxlength="25" name="strNetCost" id="strNetCost" onkeypress="return validateData(event,2);">
					</td>
					
					<td width="25%" class="CONTROL_TD">
					</td>
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
<!--	Contact Person Name	&	Contact No.		-->

					<tr>
			
<!--  Vendor/Service Engineer Name -->			
			
			<td width="25%" class="LABEL_TD">VerfiedBy</td>
					<td width="25%" class="CONTROL_TD">
					<div id="strVerifiedById">
						<select name="strVerifiedBy" class='COMBO_NORMAL' onchange="getAttendName();"  >
									<bean:write name="hemmsEngineerDeskFB" property="strVerifiedByOptions" filter="false" />
								</select>
								</div>
								<html:hidden property="strVerifiedByOptions" value="${hemmsEngineerDeskFB.strVerifiedByOptions}"/>
					</td>
					
					
					
					<td width="25%" class="CONTROL_TD">	
						
					</td>
					
					
					<td width="25%" class="CONTROL_TD"></td>
				</tr>
	
	
		<!--	Verfied  Date & Time	-->

					<tr >
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Date</td>
						<td width="25%" class="CONTROL_TD">
							<dateTag:date name="strVerifyDate" value="${hemmsEngineerDeskFB.strVerifyDate}"></dateTag:date>
						</td>					
					
						<td width="25%" class="LABEL_TD"><font color="red">*</font>Time 
						</td>
						
						<td width="25%" class="CONTROL_TD">
							<input type="text" class="TEXT_FIELD_MIN" maxlength="5" name="strVerifyTime" onkeypress='return checkTime(event,this);' /> (HH:MM 24Hr)					
						</td>					
					</tr>					
				
		

			</table>
		</his:ContentTag>

		
		<his:ContentTag>
			<table class="TABLE_STYLE">

				<tr class="FOOTER_TR">
					<td><font size="2" color="red">*</font> Mandatory Fields</td>
				</tr>
			</table>
		</his:ContentTag>


		<his:ButtonToolBarTag>
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-sv.png" onClick="return validate2();" onkeypress="if(event.keyCode==13) validate2();"/>
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-clr.png" onClick="clearPage();" onkeypress="if(event.keyCode==13) clearPage();" />
			<img style="cursor: pointer;" src="/HEMMS_ODISHA/hisglobal/images/btn-ccl.png" onClick="cancelPage();" onkeypress="if(event.keyCode==13) cancelPage();" />
		</his:ButtonToolBarTag>


		<div class="ERR_DIV" id="errMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strErrMsg" /></div>
		<div class="WARNING_DIV" id="warningMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strWarningMsg" /></div>
		<div style="display:none;" class="NORMAL_DIV" id="normalMsg"><bean:write
			name="hemmsEngineerDeskFB" property="strNormalMsg" /></div>

		<input type="hidden" name="hmode" />
		
		<input type="hidden" name="strStoreName" value="" />
		<input type="hidden" name="strStockInfoVal" value="" />
		<input type="hidden" name="strConfigPropertyValue" value="0" />
		<input type="hidden" name="strEmpName" value="${hemmsEngineerDeskFB.strEmpName}" />
		<input type="hidden" name="strItemId" value="${hemmsEngineerDeskFB.strItemId}" />
		<input type="hidden" name="strStoreId" value="${hemmsEngineerDeskFB.strStoreId}" />
		<input type="hidden" name="strEnggItemTypeId" value="${hemmsEngineerDeskFB.strEnggItemTypeId}" />
		<input type="hidden" name="strEnggItemSubTypeId" value="${hemmsEngineerDeskFB.strEnggItemSubTypeId}" />
		<input type="hidden" name="strComplaintId" value="${hemmsEngineerDeskFB.strComplaintId}" />
		<input type="hidden" name="strPageFlag" value="${hemmsEngineerDeskFB.strPageFlag}"  />
		<input type="hidden" name="strDesignation" value="" />
		<input type="hidden" name="strOldDepartmentCode" value="${hemmsEngineerDeskFB.strDeptCode}" />
		<input type="hidden" name="strPath" value="${hemmsEngineerDeskFB.strPath}">
			<input type="hidden" name="strChk" value="${hemmsEngineerDeskFB.strChk}">
		<html:hidden property="strSparePartNameOptions" value="${hemmsEngineerDeskFB.strSparePartNameOptions}"/>
		<html:hidden property="strManufactureNameOptions" value="${hemmsEngineerDeskFB.strManufactureNameOptions}"/>
		<html:hidden property="strSparePartStatusOptions" value="${hemmsEngineerDeskFB.strSparePartStatusOptions}"/>
		<input type="hidden" name="strRetValue" value="${hemmsEngineerDeskFB.strRetValue}" />
		<input type="hidden" name="strMsgString" value="${hemmsEngineerDeskFB.strMsgString}" />
		
		<input type="hidden" name="strUploadFileId" value="" />
		<input type="hidden" name="strAttendedContactPerson" value="" />
		<html:hidden name="hemmsEngineerDeskFB" property="strSerialNo" value="0"/>
		<html:hidden name="hemmsEngineerDeskFB" property="strSparePartsFlag" value="0"/>
		<div id="blanket" style="display: none;"></div>
		<div class="popUpDiv" id="popUpDiv" style="display: none;">
		<table bgcolor="white">
			<tr>
				<td>

				<div id="stockDtlsDivId" style="display: block;"></div>
				</td>
			</tr>
		</table>
		</div>
	</his:TransactionContainer>
</html:form>
</body>
</html>