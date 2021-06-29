<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset=utf-8>
<title>Unit Master Modify Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>



<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>

<script type="text/javascript">

function validate1(){	
	
		var hisValidator = new HISValidator("unitBean");
		hisValidator.addValidation("strUnitName", "req", "Unit Name is a Mandatory Field" );
		hisValidator.addValidation("strEffectiveDate", "req","Effective From is a Madatory Field");
		hisValidator.addValidation("strUnitName", "maxlen=25", "Remarks should have less than or equal to 25 Characters" );
		
			if(document.unitBean.strRemarks.value != ""){
				hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
			}
		
		var retVal = hisValidator.validate(); 
		
		if(retVal){
				document.forms[0].hmode.value = "UPDATE";
				document.forms[0].submit();
		}else{
		
				return false;
		}
	}
	
function isDecimalShow()
{
if(document.forms[0].strIsBaseUnit.value=="Yes")
{
	document.getElementById("isDecimalId").style.display="block";
	if(document.forms[0].decimalFlag.value=="1")
	{
		document.forms[0].strIsDecimal.checked=true ;
	}
}
else{
	document.getElementById("isDecimalId").style.display="none";
}

document.forms[0].strUnitName.focus();
}

</script>
<style type="text/css">
.legendNew {
    position: absolute;
    right: 100px;
    line-height: 1.2em;
    top: -2.5em;
}
</style>
</head>
<body onLoad="isDecimalShow();">
<html:form action="/masters/BSCNTUnitMst.cnt"
	type="billing.masters.controller.fb.UnitMstFB" name="unitBean">
	
	<div class="errMsg"><bean:write name="unitBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="unitBean" property="strWarning"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="unitBean" property="strMsg"/></div>
	<%-- <tag:tab tabLabel="Modify Unit" selectedTab="FIRST" align="center" width="TABLEWIDTH">
	</tag:tab> --%>
	
	
	<br>
	<div class=" px-1 px-md-4  mx-auto">
   <div class="card">
   <div class="row">
				<div class="col-sm-9" align="left">
							<p class="subHeaders" style="margin-bottom: 0;">
								<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Unit Master&nbsp;&nbsp;<i class="fas fa-angle-double-right"></i>&nbsp;Modify										</p>
				       </div>
				        
				       
							<div class="col-sm-3" align="right">
							<div class="legendNew" id="nonPrintableLegend2">
									<button type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancel('LIST');">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									
									<button type="button" id="savebutton" class="float-right btn btn-outline-success mt-1 btn-circle" tabindex="2" onclick=' return validate1();' style="background-color: #5cb85c;">
										<i class="fas fa-save iround" title="save"></i>
									</button>
								</div>
							</div>
						</div>
		
		<hr>
		
		<div class="row">
		<div class="col-sm-2">
		<label>Module Name</label>
		</div>
		<div class="col-sm-2">
		<bean:write name="unitBean" property="strModuleVal"/>
		</div>
		<div class="col-sm-2">
		<label><font color="red">*</font>Unit Name</label>
		</div>
		<div class="col-sm-2">
		<input name="strUnitName" type="text" class="form-control" id="unitName" maxlength="25" value="${unitBean.strUnitName}" onkeypress="return validateData(event,17);"> 
		</div>
		<div class="col-sm-2">
		<label>Whether Base Unit:</label>
		</div>
		<div class="col-sm-2">
		<bean:write property="strIsBaseUnit" name="unitBean" />
		</div>
		</div>
		
		
		<div class="row">
		<div class="col-sm-2">
		<label>Base Unit:</label>
		</div>
		<div class="col-sm-2">
		<bean:write name="unitBean" property="strBaseUnit"/>
		</div>
		<div class="col-sm-2">
		<label><font color="red">*</font>Effective From</label>
		</div>
		<div class="col-sm-2">
		<%-- <dateTag:date name="strEffectiveDate" id="effDateId"
				 value="${unitBean.strEffectiveDate}"/> --%>
		<input  type="text" class="form-control datepicker" name="strEffectiveDate" id="effDateId" value="${unitBean.strEffectiveDate}">
		</div>
		<div class="col-sm-4" id="isDecimalId" style="display: none;">
		<div class="row">
		<div class="col-sm-9">
		<label>Whether Unit Value could be in Decimal</label>
		</div>
		<div class="col-sm-1">
		<input name="strIsDecimal" type="checkbox" class="form-control"
				id="IsDecimal" value="1" >
		</div>
		<div class="col-sm-2"></div>
		</div>
		</div>
		</div>
		
		
		<div class="row">
		<div class="col-sm-2">
		<label>Record Status</label>
		</div>
		<div class="col-sm-2">
		 <html:radio name="unitBean" property="strValid" value="1" />Active
<html:radio name="unitBean" property="strValid" value="2" />Inactive
		</div>
		<div class="col-sm-2">
		<label>Remark</label>
		</div>
		<div class="col-sm-6">
		<textarea name="strRemarks" cols="20" rows="2" class="form-control"
				id="remarks"><bean:write name="unitBean"  property="strRemarks"/></textarea>
		</div>
		
		</div>
		 
		 
		 
		  <hr>
   <div class="row rowFlex reFlex">
							<div class="col-sm-9"></div>
							<div class="col-sm-3" align="right">
								<i class="fas fa-asterisk" style="color: red; font-size: smaller;"></i>&nbsp;Fields Mandatory
							</div>
						</div>
		
		</div>
	</div>
	
	
	
	
	
	
<table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" style="display:none;">
  <tr class="HEADER"> 
    <td colspan="2">Unit Master&gt;&gt; Modify</td>
  </tr>
  <tr> 
    <td width="50%" class="LABEL"> Module Name</td>
    <td width="50%" class="CONTROL"><bean:write name="unitBean" property="strModuleVal"/></td>
  </tr>
  <tr> 
    <td class="LABEL"><font color="red">*</font>Unit Name</td>
    <td class="CONTROL"> <input name="strUnitName" type="text" id="unitName" maxlength="25" value="${unitBean.strUnitName}" onkeypress="return validateData(event,17);">  </td>
  </tr>
  <tr> 
    <td class="LABEL">Whether Base Unit</td>
    <td class="CONTROL"> <bean:write property="strIsBaseUnit" name="unitBean" />
     </td>
  </tr>
  </table>
  <div id="isDecimalId" style="display: none">
  <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" style="display:none;">
  
  <tr>
			<td class="LABEL" width="50%">Whether Unit Value could be in Decimal</td>
			<td class="CONTROL" width="50%"><input name="strIsDecimal" type="checkbox"
				id="IsDecimal" value="1" ></td>
		</tr>
  
   </table>
   </div>
   
  <table class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" style="display:none;">
  <tr>
    <td class="LABEL" width="50%">Base Unit</td>
    <td class="CONTROL" width="50%"><bean:write name="unitBean" property="strBaseUnit"/></td>
  </tr>
  <tr> 
    <td class="LABEL"> <font color="red">*</font>Effective From </td>
    <td class="CONTROL"> <dateTag:date name="strEffectiveDate" id="effDateId"
				 value="${unitBean.strEffectiveDate}"/> </td>
  </tr>
  <tr> 
    <td class="LABEL"> Remarks</td>
    <td class="CONTROL"> <textarea name="strRemarks" cols="20" rows="2"
				id="remarks"><bean:write name="unitBean"  property="strRemarks"/></textarea> </td>
  </tr>
  <%--  <tr> 
    <td class="LABEL">Record Status</td>
    <td  class="CONTROL" >
        <html:radio name="unitBean" property="strValid" value="1" />Active
<html:radio name="unitBean" property="strValid" value="2" />Inactive
</td>
  </tr> --%>
  <tr class="FOOTER"> 
    <td colspan="2" align="right"><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>
	<table border="0" class="TABLEWIDTH" align="center" style="display:none;">
		<tr>
			<br>
			<td align="right">

			
			<!-- <img style="cursor:pointer;cursor:hand"   title="Save Record" src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" > -->
			<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>

	</td>
			<td align="left">
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			<!-- <img style="cursor:pointer;cursor:hand" title="Cancel the Process" src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" > -->


			</td>
		</tr>
	</table>
	<input type="hidden" name="chk" value="${unitBean.chk[0]}" >
<input type="hidden" name="hmode"/>
<input type="hidden" name="strIsBaseUnit" value="${unitBean.strIsBaseUnit}"/>
<input type="hidden" name="decimalFlag" value="${unitBean.strIsDecimal}"/>
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
<script type="text/javascript">

$('.datepicker').each(function(){
    $(this).datepicker({ modal: true, header: true, footer: true ,format: 'dd-mmm-yyyy'});
});
var today=new Date();
var arr=["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"];
var mmm=arr[today.getMonth()];
var hrs=today.getHours();
var dd=today.getDate()+"-"+mmm+"-"+today.getFullYear();
$('.datepicker').val(dd);

</script>
</body>
</html>