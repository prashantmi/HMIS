<%try{ %>
<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/patientDtl.tld" prefix="pDtl"%>
<%@ taglib uri="/WEB-INF/accountDtl.tld" prefix="acDtl"%>
<%@ taglib uri="/WEB-INF/crNo.tld" prefix="crNo"%>
<%@ taglib uri="/WEB-INF/onlineClientDtl.tld" prefix="bDtl"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
<html>
<head>
<title>IPD Bill Management</title>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
 <link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
 <link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
 <link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
 

 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
 
  <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />

<link href="../css/transaction.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/dropdown.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/newpopup.css" rel="stylesheet" type="text/css">

<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"	type="text/css">
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="JavaScript" src="../../masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/multiRowTLD.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/popup.js"></script>
<script language="Javascript" src="../../hisglobal/js/newpopup.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/dropdown.js"></script>
<script language="Javascript" src="../../billing/js/IpdBillMangTransBS.js"></script>
<script language="Javascript" src="../../billing/js/tariffSearch.js"></script>
<script language="Javascript" src="../../billing/js/tariffCodeSearch.js"></script>
<script language="Javascript" src="../js/patientListing.js"></script>
<script language="Javascript" src="../../billing/js/billing.js"></script>

<style type="text/css">

hr {
    margin-top: 0;
    margin-bottom: 0;
   
}
.form-form-control:disabled, .form-form-control[readonly] {
    background-color:  #e9ecef;
    opacity: 1;
}
.form-form-control {
    display: block;
    width: 100%;
    height: calc(0.5em + 0.75rem + 2px);
    padding: 0 ;
    font-weight: 600;
    line-height: 0;
    color: #495057;
    background-color: #fff;
    background-clip: padding-box;
    border: 1px solid
    #fff;
}
</style>
<script type="text/javascript">
 function viewBill(event)
{
 var deptcode=document.getElementsByName('strDepartment')[0].value
 
var wardcode= document.getElementsByName('strSpecialWardType')[0].value
 var accountno=document.getElementsByName('strAcctNo')[0].value
 var chk=document.getElementsByName('strChk')[0].value
 var reportid='1';
 var reportFormat='pdf';
 
 var url="/HBIMS/billing/transactions/IpdBillManagementTransCNT.cnt?hmode=VIEWRPT&deptCode="+deptcode+"&wardCode="+wardcode+"&chk="+chk;
 
 openDependentPopup(url,event,500,800)
}

 function resetType()
 {
	 document.forms[0].hmode.value="BEDTRANSFER";
	 document.forms[0].submit();	
 }
 function _id(str)
 {
		return document.getElementById(str);
 }
 function focusNewDept()
 {
	  var tabl=_id("TABLEBEDTRANSFERHLP");
	 document.forms[0].strDepartmentNew.focus();
	 var mov_no=document.getElementsByName("strMovNo");
	 var k=new Array();
	 for(var i=0;i<mov_no.length;i++)
		 {
		 var BlkTime=_id("BlkTime"+i).value;
		 var outFlg=_id("outFlg"+i).value;
		 var j=1;
		
		 	 if(outFlg=="7" && BlkTime=="0")
			 	{
			 		k[i]=j+1;
		 			//tabl.deleteRow(j+1);
			 	}
		 	if(outFlg=="7" && BlkTime>0)
		 	{
	 			_id("deptName"+(i+1)).value=_id("deptName"+i).value+"(SA/OT)";
	 			_id("strTransferDeptCode"+(i+1)).value=_id("strTransferDeptCode"+i).value;
	 			_id("strTransferWardCode"+(i+1)).value=_id("strTransferWardCode"+i).value;
	 			_id("wardName"+(i+1)).value=_id("wardName"+i).value;
	 			_id("strTransferChargeType"+(i+1)).value=_id("strTransferChargeType"+i).value;
		 	} 
		 }
				
		 for(i=0;i<k.length;i++)
		 {
			 tabl.deleteRow(k[i]);
		 }   


	 
	  
 }
 function focusGo()
 {
	 document.getElementById("goButtonId").focus();
 }


</script>
</head>
<body onload='focusNewDept();'>
<script language="Javascript" src="../../hisglobal/js/wz_tooltip.js"></script>
<html:form action="transactions/IpdBillManagementTransBSCNT.cnt" method="post">

<fieldset form="form1">

			<legend class='legendHeader' id='nonPrintableLegend'>IPD
				Bill Management</legend>
			<div class="legend2" id='nonPrintableLegend2'>
				<!-- <button  type="button" id="cancelButton" class="btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancelFunc();"> -->
				<button type="button"
					class="btn btn-outline-danger mt-1 btn-circle cancelbtn"
					onclick="openMenu('SIGLEMENUHOME','')">
					<i class="fas fa-ban iround" title="Cancel"></i>
				</button>
				<button type="button" id="savebutton"
					class="btn btn-outline-success mt-1 btn-circle savebtn"
					tabindex='2' onclick='return goFuncBedTransfer();' data-toggle="modal">
					<i class="fas fa-save iround" title="save"></i>
				</button>
			</div>

			<div class="viewport" id="nonPrintable">
				<div class="container-fluid ">
				
				<jsp:include page="IpdBillMgmtHeader.jsp"/>
					<div class="prescriptionTile">
					
					
<div id="SHORTCUTDIV1" align="center"></div>
<div id="blanket" style="display:none;"></div>


     <div id="errMsg" class="errMsg"><bean:write     name="ipdBillManagementTransBean" property="strErrMsg"/></div> 
  	 <div id="warningMsg" class="warningMsg"><bean:write name="ipdBillManagementTransBean" property="strWarning"/></div>
 <div id="normalMsg" class="normalMsg"><bean:write  name="ipdBillManagementTransBean" property="strMsg"/></div>

<tag:tabNew tabList="${ipdBillManagementTransBean.lhm}" selectedTab="BEDTRANSFER" align="center" width="TABLEWIDTH"></tag:tabNew>
<!-- <hr style="border-top: 2px solid rgb(198, 196, 196)"> -->
<div class="row newrow1"></div>

<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px" style="display: none;">
 <tr class="HEADER"> 
   <td width="50%">Bed&gt;&gt;Transfer</td>
   <td width="50%" align="right">
   	<a id="viewBill" style="cursor: pointer;" onclick="viewBill(event)"  title="View Bill"><font color="blue" style=""><u>View Bill</u></font></a>
   </td>
 </tr>  
 <tr> 
   <td width="50%" class="LABEL">CR No.</td>
   <td width="50%" class="CONTROL">
   	<bean:write  name="ipdBillManagementTransBean" property="strCrNo" />
   </td>   
 </tr>
 </table>
 
 
  
<div id ="creditDtlsDivId" style="display: none;">	
		  		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="0px">
		<tr><td>
			<bDtl:onlnCltDtl crNo="${ipdBillManagementTransBean.strCrNo}" ></bDtl:onlnCltDtl>
			</td></tr>
		</table>					
	</div>
<bean:write name="ipdBillManagementTransBean" property="strPrevBedTransfer" filter="false"/>
                      
<div id='otherDetailsDivId' style="display: none;">
                     <div class="row rowFlex reFlex newrow">
								<div class="col-sm-4" align="center">
									<label class="lablecolor">OLD WARD/BED</label>
								</div>
								<div class="col-sm-2">
									<label class="lablecolor">Treatment Category<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="treatCatComboDivId">
										<select class="browser-default custom-select"
											name="strNewTreatmentCategory">
											<bean:write name="ipdBillManagementTransBean"
												property="strTreatmentCategoryValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-4" align="center">
									<label class="lablecolor">NEW WARD/BED</label>
								</div>

							</div>
                        	 <div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Department<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="deptComboDivId">
										<select class="browser-default custom-select"
											name="strDepartmentOld" onchange="getUnitdtls(this);">
											<bean:write name="ipdBillManagementTransBean"
												property="strDepartmentValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Department<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="deptComboDivId">
										<select class="browser-default custom-select"
											name="strDepartmentNew" id="strDepartmentNew"
											onchange="getUnitdtls(this);">
											<bean:write name="ipdBillManagementTransBean"
												property="strDepartmentValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-1"></div>
							</div>

							<div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Unit<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="UnitComboDivId">
										<select class="browser-default custom-select"
											name="strUnitold" onchange="getWardDtls(this);">
											<bean:write name="ipdBillManagementTransBean"
												property="strUnitNameValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Unit<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="unitComboDivId1">
										<select class="browser-default custom-select"
											name="strUnitNew" id="strUnitNew"
											onchange="getWardDtls(this);">
											<bean:write name="ipdBillManagementTransBean"
												property="strUnitNameValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-1"></div>
							</div>

							<div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Ward Name<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="wardOldDivId">
										<select class="browser-default custom-select"
											name="strWardOld" onchange='setChargeType(this)'>
											<bean:write name="ipdBillManagementTransBean"
												property="strSpecialWardTypeValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Ward Name<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="wardNewDivId">
										<select class="browser-default custom-select"
											name="strWardNew" id="strWardNew"
											onchange='setChargeType(this)'>
											<bean:write name="ipdBillManagementTransBean"
												property="strSpecialWardTypeValues" filter="false" />
										</select>
									</div>
									<div id="specialWardTypeLabelDivId" style="display: none;"></div>
								</div>
							</div>



							<div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Consultant</label>
								</div>
								<div class="col-sm-2">
									<div id="ConsultantTypeDivId">
										<select class="browser-default custom-select" name="strDocOld">
											<bean:write name="ipdBillManagementTransBean"
												property="strDocNameValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Consultant</label>
								</div>
								<div class="col-sm-2">
									<div id="ConsultantTypeDivId1">
										<select class="browser-default custom-select" name="strDocNew"
											id="strDocNew">
											<bean:write name="ipdBillManagementTransBean"
												property="strDocNameValues" filter="false" />
										</select>
									</div>
									<div id="wardTypeLabelDivId" style="display: none;"></div>
								</div>
								<div class="col-sm-1"></div>
							</div>

							<div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Charge Type<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="wardTypeDivId">
										<select class="browser-default custom-select"
											name="strChargeTypeOld">
											<bean:write name="ipdBillManagementTransBean"
												property="strWardTypeValues" filter="false" />
										</select>
									</div>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Charge Type<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="wardTypeDivId">
										<select class="browser-default custom-select"
											name="strChargeTypeNew">
											<bean:write name="ipdBillManagementTransBean"
												property="strWardTypeValues" filter="false" />
										</select>
									</div>
									<div id="wardTypeLabelDivId" style="display: none;"></div>
								</div>
								<div class="col-sm-1"></div>
							</div>



							<div class="row rowFlex reFlex">
							<div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>Start Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
								<div id="startDateNewDivId" >
										<bean:define id="strcurrentdate"
											name="ipdBillManagementTransBean" property="strcurrentdate" />
										<%
											String[] currentDate = strcurrentdate.toString().replace("-", "#").split("#");

													String[] currentDate1 = strcurrentdate.toString().replace(" ", "#").split("#");
													String[] currentDate2 = { "00", "00" };
													if (currentDate1.length > 1)
														currentDate2 = currentDate1[1].toString().replace(":", "#").split("#");

													System.out.println("strcurrentdate" + strcurrentdate);
										%>
										<input id="Datetimepicker" value='<%=currentDate[0]%>-<%=currentDate[1]%>-<%=currentDate[2].split(" ")[0]%> <%=currentDate2[0]%>:<%=currentDate2[1]%>' />
										<input type='hidden' name='strNewStartDate' maxlength='2'  id='strNewStartDate'    value="${ipdBillManagementTransBean.strcurrentdate}"> 
								</div></div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>Start Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
										<input id="Datetimepicker2" value='<%=currentDate[0]%>-<%=currentDate[1]%>-<%=currentDate[2].split(" ")[0]%> <%=currentDate2[0]%>:<%=currentDate2[1]%>' />
										<input type='hidden' name='strNewStartDate' maxlength='2'  id='strNewStartDate'    value="${ipdBillManagementTransBean.strcurrentdate}"> 
								</div>
								
								<div class="col-sm-1"></div>
							</div>

							<div class="row rowFlex reFlex">
							  <div class="col-sm-1"></div>
								<div class="col-sm-2">
									<label>End Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
									<div id="endDateOldDivId">
										<bean:define id="strCtDate" name="ipdBillManagementTransBean"
											property="strCtDate" />
										<bean:define id="strchkvalue"
											name="ipdBillManagementTransBean" property="strchkvalue" />
										<%
 			
 			String chkValue =strchkvalue.toString();
 			//System.out.println("chkValue "+chkValue+" strCtDate "+strCtDate);
 			
 			%>
										<%
 		if(chkValue.equalsIgnoreCase("1"))
 		{%>
										<%  
 			String[] ctDate =strCtDate.toString().replace("-","#").split("#");
 			
 			String[] ctDate1 =strCtDate.toString().replace(" ","#").split("#");
 			String[] ctDate2={"00","00"}; 
 			if(ctDate1.length>1)
 				ctDate2 =ctDate1[1].toString().replace(":","#").split("#");
 			
 			%>
										<input id="Datetimepicker1"
											value='<%=ctDate[0] %>-<%=ctDate[1] %>-<%=ctDate[2].split(" ")[0] %> <%=ctDate2[0] %>:<%=ctDate2[1] %>' />
										<input type='hidden' name='strOldEndDate' maxlength='2'
											id='strOldEndDate'
											value="${ipdBillManagementTransBean.strCtDate}">
									</div>
									<%
 		}else{
		%>
		<%  
 			String[] ctDate =strCtDate.toString().replace("-","#").split("#");
 			
 			String[] ctDate1 =strCtDate.toString().replace(" ","#").split("#");
 			String[] ctDate2={"00","00"}; 
 			if(ctDate1.length>1)
 				ctDate2 =ctDate1[1].toString().replace(":","#").split("#");
 			
 			%>
 			
 			<input id="Datetimepicker1" value='<%=ctDate[0] %>-<%=ctDate[1] %>-<%=ctDate[2].split(" ")[0] %> <%=ctDate2[0] %>:<%=ctDate2[1] %>' />
				<input type='hidden' name='strOldEndDate' maxlength='2'id='strOldEndDate' value="${ipdBillManagementTransBean.strCtDate}">						
		<%
 		}
		%>
								</div>
								<div class="col-sm-2"></div>
								<div class="col-sm-2">
									<label>End Date<font color="red">*</font></label>
								</div>
								<div class="col-sm-2">
								<div id="endDateNewDivId" >
										<input id="Datetimepicker3" value='<%=currentDate[0]%>-<%=currentDate[1]%>-<%=currentDate[2].split(" ")[0]%> <%=currentDate2[0]%>:<%=currentDate2[1]%>' />
										<input type='hidden' name='strNewEndDate' maxlength='2'  id='strNewEndDate'    value="${ipdBillManagementTransBean.strcurrentdate}">
							
								</div></div>
								<div class="col-sm-1"></div>
							</div>
							
								<div class="row rowFlex reFlex">
							<logic:notEqual name="ipdBillManagementTransBean"
								property="strAccStatus" value="0">
								<div class="col-sm-4"></div>
								<div class="col-sm-4" align="center">
									<a href="#" class="btn btn-sm btn-success" id="goButtonId"
										onkeypress="addBedTransfer();" onclick="addBedTransfer();"
										title="Add Bed Transfer Data" style="font-size: 1rem;">
										GO&nbsp;<i class="fas fa-angle-double-right"></i>
									</a>
								</div>
							</logic:notEqual>
							<div class="col-sm-1"></div>
							<bean:define id="userValueId"
								value='<%=(String) session.getAttribute("USERVALUE")%>'></bean:define>
							<logic:equal name="userValueId" value="0">
								<div class="col-sm-3">
									<label>Bed Transfer Flag</label>&nbsp;&nbsp;<html:checkbox name="ipdBillManagementTransBean" property="isBillFinal" value="91" tabindex='1'></html:checkbox>
								</div>
							</logic:equal>

							<logic:equal name="userValueId" value="1">
								
									<html:hidden name="ipdBillManagementTransBean"
										property="isBillFinal" value="91"></html:hidden>
								
							</logic:equal>
						</div>

</div>


							<%-- 					
							<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing ="1px" style="border:1px solid black;border-collapse: collapse;background-color: rgba(186, 185, 185, 1);" >
<!-- <tr>
<td class='TITLE'><div align="center">OLD WARD/BED</div></td>
<td class='TITLE'><div align="center">NEW WARD/BED</div></td>
</tr> -->
<tr>
       <td class="LABEL" width="25%"><font color="red">*</font>Treatment Category</td>
       <td class="CONTROL" width="25%">
       <div id="treatCatComboDivId" >
              	<select class="comboNormal" name="strNewTreatmentCategory"  >
              		<bean:write name="ipdBillManagementTransBean" property="strTreatmentCategoryValues" filter="false"   />
              	</select>
           </div>
           <div id="treatCatLabelDivId" style="display:none;" ></div>                  
       </td>
</tr>

<table width="100%" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr>  
  		<td class="LABEL" width="25%"><font color="red">*</font>Department</td>
        <td class="CONTROL" width="25%">
        <div id="deptComboDivId" >
	        <select class="comboNormal" name="strDepartmentOld" onchange="getUnitdtls(this);" >
	        	<bean:write name="ipdBillManagementTransBean" property="strDepartmentValues" filter="false"   />
	        </select>
        </div>
           <div id="deptLabelDivId" style="display: none;" ></div>                  
     	</td>
    </tr>
    
    <tr>  
  		<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
        <td class="CONTROL" width="25%">
        <div id="UnitComboDivId" >
	        <select class="comboNormal" name="strUnitold" onchange="getWardDtls(this);" >
	        	<bean:write name="ipdBillManagementTransBean" property="strUnitNameValues" filter="false"   />
	        </select>
        </div>
           <div id="deptLabelDivId" style="display: none;" ></div>                  
     	</td>
    </tr>
    
    <tr>
     	<td class="LABEL" width="25%"><font color="red">*</font>Ward Name</td>
		<td class="CONTROL" width="25%">
			<div id="wardOldDivId" >
			<select class="comboNormal" name="strWardOld" onchange='setChargeType(this)'>
				<bean:write name="ipdBillManagementTransBean" property="strSpecialWardTypeValues" filter="false"   />
			</select>                  
			</div>                  
		</td>                         
   </tr>   
   <tr>
       <td class="LABEL" width="25%">Consultant</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="ConsultantTypeDivId" >
    			<select class="comboNormal" name="strDocOld"  >
      				<bean:write name="ipdBillManagementTransBean" property="strDocNameValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    
    
   
   <tr>
       <td class="LABEL" width="25%"><font color="red">*</font>Charge Type</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="wardTypeDivId" >
    			<select class="comboNormal" name="strChargeTypeOld"  >
      				<bean:write name="ipdBillManagementTransBean" property="strWardTypeValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    <tr style="display: none;">
       <td class="LABEL" width="25%">Consultant</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="wardTypeDivId" >
    			<select class="comboNormal" name="strConsultantOld"  >
      				<bean:write name="ipdBillManagementTransBean" property="strWardTypeValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    <logic:notEqual name="ipdBillManagementTransBean"  property="strAccStatus" value="0">
	<tr>
		<td class="LABEL" width="25%"><font color="red">*</font>Start Date</td>
		<td class="CONTROL" width="25%">
 			<div id="startDateOldDivId">
 			
 			<bean:define id="endDate" name="ipdBillManagementTransBean" property="strStartDate" />
 			
 			
 			<% 
 			//System.out.println("endDt"+endDate);
 			String[] endDt =endDate.toString().replace("-","#").split("#");
 			String[] endDt1 =endDate.toString().replace(" ","#").split("#");
 			String[] endDt2={"00","00"}; 
 			if(endDt1.length>1)
 			endDt2 =endDt1[1].toString().replace(":","#").split("#");
 			
 			//System.out.println("endDt"+endDt2);
 			
 			%>
 			
 			<input type='text' name='strOldStartDateDD' maxlength='2'  id='strOldStartDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly" class='txtFldSmallDD' value='<%=endDt[0] %>'>-
			<input type='text' name='strOldStartDateMM' maxlength='2'  id='strOldStartDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  readonly="readonly"  class='txtFldSmallDD' value='<%=endDt[1] %>'>-
			<input type='text' name='strOldStartDateYYYY' maxlength='2'  id='strOldStartDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  readonly="readonly"  class='txtFldMinYYYY' value='<%=endDt[2].split(" ")[0] %>'>-
			
			<input type='text' name='strOldStartDateHH' maxlength='2'  id='strOldStartDateHH'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  readonly="readonly"  class='txtFldSmallDD' value='<%=endDt2[0] %>'>-
			<input type='text' name='strOldStartDateSS' maxlength='2'  id='strOldStartDateSS'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldSmallDD' value='<%=endDt2[1] %>'>
			<input type='hidden' name='strOldStartDate' maxlength='2'  id='strOldStartDate'    value="${ipdBillManagementTransBean.strEndDate}"> 
		</div>
		</td>
	</tr>
	<tr>
		<td class="LABEL" width="25%"><font color="red">*</font>End Date</td>
		<td class="CONTROL" width="25%">
 			<div id="endDateOldDivId" >
 			<bean:define id="strCtDate" name="ipdBillManagementTransBean" property="strCtDate" />
 			<bean:define id="strchkvalue" name="ipdBillManagementTransBean" property="strchkvalue" />
 			<%
 			
 			String chkValue =strchkvalue.toString();
 			//System.out.println("chkValue "+chkValue+" strCtDate "+strCtDate);
 			
 			%>
 		<%
 		if(chkValue.equalsIgnoreCase("1"))
 		{%>
 			<%  
 			String[] ctDate =strCtDate.toString().replace("-","#").split("#");
 			
 			String[] ctDate1 =strCtDate.toString().replace(" ","#").split("#");
 			String[] ctDate2={"00","00"}; 
 			if(ctDate1.length>1)
 				ctDate2 =ctDate1[1].toString().replace(":","#").split("#");
 			
 			%>
			<input type='text' name='strOldEndDateDD' maxlength='2'  id='strOldEndDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'   class='txtFldSmallDD' value='<%=ctDate[0] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateMM' maxlength='2'  id='strOldEndDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  class='txtFldSmallDD' value='<%=ctDate[1] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateYYYY' maxlength='2'  id='strOldEndDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  class='txtFldMinYYYY' value='<%=ctDate[2].split(" ")[0] %>' onblur="setNewStartDate(this)">-
			
			<input type='text' name='strOldEndDateHH' maxlength='2'  id='strOldEndDateHH'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  class='txtFldSmallDD' value='<%=ctDate2[0] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateSS' maxlength='2'  id='strOldEndDateSS'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)'  class='txtFldSmallDD' value='<%=ctDate2[1] %>' onblur="setNewStartDate(this)">
			<input type='hidden' name='strOldEndDate' maxlength='2'  id='strOldEndDate'    value="${ipdBillManagementTransBean.strCtDate}">
		</div>
		<%
 		}else{
		%>
		<%  
 			String[] ctDate =strCtDate.toString().replace("-","#").split("#");
 			
 			String[] ctDate1 =strCtDate.toString().replace(" ","#").split("#");
 			String[] ctDate2={"00","00"}; 
 			if(ctDate1.length>1)
 				ctDate2 =ctDate1[1].toString().replace(":","#").split("#");
 			
 			%>
		
		<input type='text' name='strOldEndDateDD' maxlength='2'  id='strOldEndDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldSmallDD' value='<%=ctDate[0] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateMM' maxlength='2'  id='strOldEndDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldSmallDD' value='<%=ctDate[1] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateYYYY' maxlength='2'  id='strOldEndDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldMinYYYY' value='<%=ctDate[2].split(" ")[0] %>' onblur="setNewStartDate(this)">-
			
			<input type='text' name='strOldEndDateHH' maxlength='2'  id='strOldEndDateHH'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldSmallDD' value='<%=ctDate2[0] %>' onblur="setNewStartDate(this)">-
			<input type='text' name='strOldEndDateSS' maxlength='2'  id='strOldEndDateSS'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' readonly="readonly"  class='txtFldSmallDD' value='<%=ctDate2[1] %>' onblur="setNewStartDate(this)">
			<input type='hidden' name='strOldEndDate' maxlength='2'  id='strOldEndDate'    value="${ipdBillManagementTransBean.strCtDate}">
		</div>
		<%
 		}
		%>
		
		</td>
	</tr>    
	</logic:notEqual>                  
</table> 

<table width="100%" align="center" border="0" cellpadding="1px" cellspacing ="1px">
  	<tr>  
  		<td class="LABEL" width="25%"><font color="red">*</font>Department</td>
        <td class="CONTROL" width="25%">
        <div id="deptComboDivId" >
	        <select class="comboNormal" name="strDepartmentNew" id="strDepartmentNew" onchange="getUnitdtls(this);" >
	        	<bean:write name="ipdBillManagementTransBean" property="strDepartmentValues" filter="false"   />
	        </select>
        </div>        
     	</td>
    </tr>
    
    	<tr>  
  		<td class="LABEL" width="25%"><font color="red">*</font>Unit</td>
        <td class="CONTROL" width="25%">
        <div id="unitComboDivId1" >
	        <select class="comboNormal" name="strUnitNew" id="strUnitNew" onchange="getWardDtls(this);" >
	        	<bean:write name="ipdBillManagementTransBean" property="strUnitNameValues" filter="false"   />
	        </select>
        </div>        
     	</td>
    </tr>
    
    <tr>
     	<td class="LABEL" width="25%"><font color="red">*</font>Ward Name</td>
		<td class="CONTROL" width="25%">
			<div id="wardNewDivId" >
			<select class="comboNormal" name="strWardNew" id="strWardNew" onchange='setChargeType(this)'>
				<bean:write name="ipdBillManagementTransBean" property="strSpecialWardTypeValues" filter="false"   />
				</select>                  
		</div>
  			<div id="specialWardTypeLabelDivId" style="display: none;" ></div>                  
		</td>                         
   </tr>   
   
   <tr>
       <td class="LABEL" width="25%">Consultant</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="ConsultantTypeDivId1" >
    			<select class="comboNormal" name="strDocNew" id="strDocNew" >
      				<bean:write name="ipdBillManagementTransBean" property="strDocNameValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    
    
   <tr>
       <td class="LABEL" width="25%"><font color="red">*</font>Charge Type</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="wardTypeDivId" >
    			<select class="comboNormal" name="strChargeTypeNew"  >
      				<bean:write name="ipdBillManagementTransBean" property="strWardTypeValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    <tr style="display: none;">
       <td class="LABEL" width="25%"><font color="red">*</font>Consultant</td>
	   <td class="CONTROL" width="25%"  >
     		<div id="wardTypeDivId" >
    			<select class="comboNormal" name="strConsultantNew"  >
      				<bean:write name="ipdBillManagementTransBean" property="strWardTypeValues" filter="false" />
			</select> 
		</div>
		<div id="wardTypeLabelDivId" style="display: none;" ></div>
		</td>		
    </tr>
    <logic:notEqual name="ipdBillManagementTransBean"  property="strAccStatus" value="0">
	<tr>
	
		<td class="LABEL" width="25%"><font color="red">*</font>Start Date</td>
		<td class="CONTROL" width="25%">
 			<div id="startDateNewDivId" >
 			<bean:define id="strcurrentdate" name="ipdBillManagementTransBean" property="strcurrentdate" />
 				<%  
 			String[] currentDate =strcurrentdate.toString().replace("-","#").split("#");
 			
			String[] currentDate1 =strcurrentdate.toString().replace(" ","#").split("#");
 			String[] currentDate2={"00","00"}; 
 			if(currentDate1.length>1)
 				currentDate2 =currentDate1[1].toString().replace(":","#").split("#");
 			
 			System.out.println("strcurrentdate"+strcurrentdate);
 			
 			%>
 			
			<input type='text' name='strNewStartDateDD' maxlength='2'  id='strNewStartDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate[0]%>'>-
			<input type='text' name='strNewStartDateMM' maxlength='2'  id='strNewStartDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate[1]%>'>-
			<input type='text' name='strNewStartDateYYYY' maxlength='2'  id='strNewStartDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldMinYYYY' value='<%=currentDate[2].split(" ")[0]%>'>-
			
			<input type='text' name='strNewStartDateHH' maxlength='2'  id='strNewStartDateHH'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate2[0]%>'>-
			<input type='text' name='strNewStartDateSS' maxlength='2'  id='strNewStartDateSS'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate2[1]%>'>
			<input type='hidden' name='strNewStartDate' maxlength='2'  id='strNewStartDate'    value="${ipdBillManagementTransBean.strcurrentdate}"> 
		</div>
		</td>
		
	</tr>
	<tr>
	
		<td class="LABEL" width="25%"><font color="red">*</font>End Date</td>
		<td class="CONTROL" width="25%">
 			<div id="endDateNewDivId" >
			<input type='text' name='strNewEndDateDD' maxlength='2'  id='strNewEndDateDD'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate[0] %>'>-
			<input type='text' name='strNewEndDateMM' maxlength='2'  id='strNewEndDateMM'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate[1] %>'>-
			<input type='text' name='strNewEndDateYYYY' maxlength='2'  id='strNewEndDateYYYY'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldMinYYYY' value='<%=currentDate[2].split(" ")[0]%>'>-
			
			<input type='text' name='strNewEndDateHH' maxlength='2'  id='strNewEndDateHH'   onkeypress='return validateData(event,5);' onkeyup='moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate2[0] %>'>-
			<input type='text' name='strNewEndDateSS' maxlength='2'  id='strNewEndDateSS'   onkeypress='return validateData(event,5);' onkeyup='focusGo();moveUpDown();deleteRow()' onkeydown='checkFirstKey(event)' class='txtFldSmallDD' value='<%=currentDate2[1] %>'>
			<input type='hidden' name='strNewEndDate' maxlength='2'  id='strNewEndDate'    value="${ipdBillManagementTransBean.strcurrentdate}">
		</div>
		</td>
		
	</tr> 
	</logic:notEqual>                     
</table>
</td>
</tr>
</table> --%>

<%-- <table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
 <tr> 
  <logic:notEqual name="ipdBillManagementTransBean"  property="strAccStatus" value="0">                     
  <td class="LABEL" colspan="2" align="right" width="50%" >                      
  	<img src="../../hisglobal/images/Go.png" tabindex="1" id="goButtonId" style="cursor: pointer;" onkeypress="addBedTransfer();" onclick="addBedTransfer();"   title="Add Bed Transfer Data">                      
 </td>
 </logic:notEqual>
 <bean:define id="userValueId"  value='<%=(String)session.getAttribute("USERVALUE") %>' ></bean:define>
 <logic:equal name="userValueId" value="0">
           	<td colspan="0" width="40%" class="LABEL">Bed Transfer Flag</td>
           	<td colspan="0" width="10%" class="CONTROL"> 
    			<html:checkbox name="ipdBillManagementTransBean" property="isBillFinal" value="91" tabindex='1'></html:checkbox>
    		</td> 
           </logic:equal>	  
           
           
    	<logic:equal name="userValueId" value="1">    
   			<td colspan="0" width="40%" class="LABEL"></td>
           	<td colspan="0" width="10%" class="CONTROL"> 
    			<html:hidden name="ipdBillManagementTransBean"  property="isBillFinal" value="91"></html:hidden>
    		</td>	    		
  		</logic:equal>	  
           <td width="30%" class="LABEL"></td>
           <td width="10%" class="multiControl" style="font-weight: bold; color: red"></td>
       </tr>      
</tr>
</table>  --%>

					


						<table class="TABLEWIDTH" align="center" border="0" cellpadding="1px" cellspacing ="1px">
       <tr>
           
  	
     	<tr style="display:none">
  		<td colspan="1" width="50%" class="LABEL">Remarks</td>
    	<td colspan="3" width="50%" class="CONTROL"> <textarea rows="2" cols="18" name="strRemarks"></textarea></td> 
 	  	</tr>
</table>

<!-- <table class="TABLEWIDTH" border="0" cellpadding="1px" cellspacing ="1px" align="center">
			<tr class="FOOTER">
				<td width='3%'>
					<div id="plusHelpId" align="center">
						<img style="cursor: pointer;cursor: hand" src="../../hisglobal/images/plus.gif" name="plusHelp" align="middle" onclick="showHelpDetails('HelpId');" />
					</div>
					<div id="minusHelpId" style="display: none" align="center">
						<img style="cursor: pointer;cursor: hand" src="../../hisglobal/images/minus1.gif" name="minusHelp" onclick="hideHelpDetails('HelpId');"> 
					</div>
				</td>
				<td><div align="left">Help</div></td>
				<td><div align="right"><font size="2" color="red">*</font> Mandatory Fields</div></td>					
			</tr>
</table> -->
<!-- <div id="HelpId" style="display:none">
<table class="TABLEWIDTH" border="0" cellspacing="1px" align="center">
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Tab Key to Navigate Forward</b>, <b>Shift+Tab Key To Navigate Backward</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Alt+Tab Key to Navigate Up/Down</b> in Combo Box</td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Up/Down Key to Navigate Up or Down</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Alt & + to Enter Tariff Code</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Alt + F1 to Open Shortcut Key Help Menu</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Alt + 1,2,3,4(Numpad) to Navigate Tabs in Sequential Order(Add Service-1, Update Account-2,Bill Approval-3,ViewBill-4)</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>Alt + H to Open Help</b></td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>%</b> to Find Any Data of Any Length (Including zero length)</td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Use <b>_</b> to Find Data on a Single Character Exclusion</td>
		</tr>
		<tr>
			<td class="CONTROL"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If Auto Bed Charges Through Job Then Only SIngle Day (Last Day-Discharge Day) Bed Charges Applicable</td>
		</tr>
		<tr class=FOOTER><td></td></tr>
</table>
</div>    -->
 
  <%-- <table border="0" class="TABLEWIDTH" align="center">
	<tr>
       	<td align="center">
       	<!-- <img style="cursor:hand;cursor:pointer" name="saveButton" tabindex='1'  src="../../hisglobal/images/btn-sv.png" onclick=" return goFuncAddService();"/>
       	<img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-clr.png" tabindex='1'  onclick="getInvisbleforAddService();"/>
       	<img style="cursor:hand;cursor:pointer" src="../../hisglobal/images/btn-ccl.png" tabindex='1' onClick="cancel1('LIST');"> -->
       	<logic:equal name="userValueId" value="0">
       	<a href="#" class="button" id="btn-sv" onclick='return goFuncBedTransfer();'><span class="save">Save</span></a>
		<a href="#" class="button" id="btn-clr" onclick="getInvisbleforAddService();"><span class="clear">Clear</span></a> 
		<a href="#" class="button" id="btn-ccl" onClick="cancel1('LIST');"><span class="cancel">Cancel</span></a>
		<a href="#" class="button" id="btn-ccl" onClick="resetType()"><span class="reset">Reset</span></a>
		</logic:equal>
       	</td>
   	</tr>
</table> --%>
<div id="loadingmessage" class="wrapper rounded" style="display:none;width:180px;height:100px;position:absolute;top:40%;left:42%;padding:2px;z-index:100;">
			<div class="div-table">
				<div class="div-table-row">
					<div class="div-table-col title width100 ">
							Please Wait...
					</div>
				</div>
				<div class="div-table-row alignCenter" >
					<div class="div-table-col width100 " style="margin-top: 7px;">
							<img src="/HIS/hisglobal/images/ajax-loader.gif" width="50" height="50" />
					</div>
				</div>
			</div>
</div>

						<hr>
						<div class="row rowFlex reFlex">
							<div class="col-sm-10"></div>
							<div class="col-sm-2" align="right">
								<i class="fas fa-asterisk"
									style="color: red; font-size: smaller;"></i>Fields Mandatory
							</div>
						</div>

						<input type="hidden" name="hmode">
<input type="hidden" name="strChk" value="${ipdBillManagementTransBean.strChk}">
<input type="hidden" name="chk" value="${ipdBillManagementTransBean.strChk}">
<input type="hidden" name="strChkBoxComboValue" value="${ipdBillManagementTransBean.strChkBoxComboValue}">
<input type="hidden" name="strWardCode" value="${ipdBillManagementTransBean.strWardCode}">
<input type="hidden" name="strHospitalCode" value="${ipdBillManagementTransBean.strHospitalCode}">
<input type="hidden" name="strIpdRoundOff" value="${ipdBillManagementTransBean.strIpdRoundOff}">
<input type="hidden" name="strIpdThirdPartyBilling" value="${ipdBillManagementTransBean.strIpdThirdPartyBilling}">	
<input type="hidden" name="strExcessCreditLimit" value="${ipdBillManagementTransBean.strExcessCreditLimit}" />	 
<input type="hidden" name="strCtDate" value="${ipdBillManagementTransBean.strCtDate}" />
<input type="hidden" name="strcurrentdate" value="${ipdBillManagementTransBean.strcurrentdate}" />	
<input type="hidden" name="strIpdBillManagementMode" value="${ipdBillManagementTransBean.strIpdBillManagementMode}" />
<input type="hidden" name="strTempTreatCat" value="0" />
<input type="hidden" name="strTempWardCode" value="0" />	
<input type="hidden" name="searchColumn" value='<%=request.getParameter("searchColumn") %>' />
<input type="hidden" name="search" value='<%=request.getParameter("search") %>' />
<input type="hidden" name="blockNo" value='<%=request.getParameter("blockNo") %>' />
<input type="hidden" name="prevNext" value='<%=request.getParameter("prevNext") %>' />
<input type="hidden" name="rowNum" value='<%=request.getParameter("rowNum") %>'/>
<input type="hidden" name="divisionId" value='<%=request.getParameter("divisionId") %>' />
<input type="hidden" name="strOfflineTotalPayAmountWithoutConsumable" value="${ipdBillManagementTransBean.strOfflineTotalPayAmountWithoutConsumable}" />
<input type="hidden" name="strGroupIdForConsumableConcatenated" value="${ipdBillManagementTransBean.strGroupIdForConsumableConcatenated}" />
<input type="hidden" name="strConsumableChargesGroupId" value="${ipdBillManagementTransBean.strConsumableChargesGroupId}" />
<input type="hidden" name="strConsumableChargesTariffCode" value="${ipdBillManagementTransBean.strConsumableChargesTariffCode}" />
<input type="hidden" name="strUrgSur" value="${ipdBillManagementTransBean.strUrgSur}">
<input type="hidden" name="strNumRows" value="${ipdBillManagementTransBean.strNumRows}">
<input type="hidden" name="selectedTab">
<input type="hidden" name="strIsCalledFromIpdBillNew" value="${ipdBillManagementTransBean.strIsCalledFromIpdBillNew}" />
<input type="hidden" name="strAcctStatMode" value="${ipdBillManagementTransBean.strAcctStatMode}" />
<input type="hidden" name="strchkvalue" value="${ipdBillManagementTransBean.strchkvalue}" />
<input type="hidden" name="strAccStatus" value="${ipdBillManagementTransBean.strAccStatus}" />
<input type="hidden" name="strbcflag" value="${ipdBillManagementTransBean.strbcflag}" />

	<cmbPers:cmbPers/>
	</div>
	</div>
	</div>
	</fieldset>
</html:form>


    <script>
        $('#Datetimepicker').datetimepicker({
            uiLibrary: 'bootstrap4',
            modal: true,
            footer: true,
            format: 'dd-mm-yyyy HH:MM'
        });
        $('#Datetimepicker1').datetimepicker({
            uiLibrary: 'bootstrap4',
            modal: true,
            footer: true,
            format: 'dd-mm-yyyy HH:MM'
        });
        $('#Datetimepicker2').datetimepicker({
            uiLibrary: 'bootstrap4',
            modal: true,
            footer: true,
            format: 'dd-mm-yyyy HH:MM'
        });
        $('#Datetimepicker3').datetimepicker({
            uiLibrary: 'bootstrap4',
            modal: true,
            footer: true,
            format: 'dd-mm-yyyy HH:MM'
        });
    </script>
</body>
</html>
<%}catch(Exception e)
{
e.printStackTrace();	
}%>