<%-- 
 Unit Value Master ADD jsp
 author: Debashis Sardar
  Created on : 16-Sep-2011
  --%>
 <!DOCTYPE html> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
        <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
		<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
		<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
		<%@taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
	    <%@taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
	    <%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>
		
<html>
<head>
<meta charset=utf-8>
<title>Unit Value Master Add Page</title>
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
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../billing/js/UnitValueMstNew.js"></script>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>
<style type="text/css">
.legendNew {
    position: absolute;
    right: 100px;
    line-height: 1.2em;
    top: -2.5em;
}
</style>
</head>
<body >
<html:form name="unitvalueBean" action="/masters/CNTUnitValueMstBS" type="billing.masters.controller.fb.UnitValueMstFB">
<div class="errMsg"><bean:write name="unitvalueBean" property="strErrorMsg"/></div>
<div class="warningMsg"><bean:write name="unitvalueBean" property="strWarning"/></div>
<div class="normalMsg" id='normalMsg'><bean:write name="unitvalueBean" property="strMsg"/></div>



<%-- <tag:tab tabLabel="Add Unit Value" selectedTab="FIRST" align ="center" width ="TABLEWIDTH"> </tag:tab> --%>
<br>
<div class=" px-1 px-md-4  mx-auto">
   <div class="card">
   <div class="row">
				<div class="col-sm-9" align="left">
							<p class="subHeaders" style="margin-bottom: 0;">
								<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Unit Value Master&nbsp;&nbsp;<i class="fas fa-angle-double-right"></i>&nbsp;Add			
							</p>
				       </div>
							<div class="col-sm-3" align="right">
							<div class="legendNew" id="nonPrintableLegend2">
									<button type="button" class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn" onclick="cancel('LIST');">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle" onclick="document.forms[0].reset(),document.forms[0].strmoduleName.focus();" style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear" style="width: 23px; color: #fff;">
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
<label><span class="style1">*</span>Module Name</label>
</div>
<div class="col-sm-2">
 <select name ="strmoduleName" class="form-control" onChange="combo1('FROMUNITVAL');">
	<bean:write name="unitvalueBean" property="strCmbval" filter="false"/>
	</select>
</div>
<div class="col-sm-2">
<label><span class="style1">*</span>From Unit</label>
</div>
<div class="col-sm-2">
<div id ="fromUnitId"><select name ="strfromUnit" class="form-control" ><option value="0">Select Value</option></select> </div>
  
</div>
<div class="col-sm-2">
<label><span class="style1">*</span>To Unit</label>
</div>
<div class="col-sm-2">
<div id ="toUnitId">
      <select name ="strtoUnit" class="form-control">
        <option value ="0">Select Value</option>
      </select>
    </div>
</div>
</div>


<div class="row">

<div class="col-sm-2">
<label><span class="style1">*</span>Converted Value</label>
</div>
<div class="col-sm-2">
<input type="text" class="form-control" name="strconvertedValue" value ="" maxlength="10" onKeypress ='return validateData(event,7);'> 
</div>
<div class="col-sm-2">
<label><span class="style1">*</span> Effective From</label>
</div>
<div class="col-sm-2">
<%-- <date:date name="streffectiveFrom" value ="${unitvalueBean.strCtDate}"> </date:date> --%>
<input  type="text" class="form-control datepicker" name="streffectiveFrom" value ="${unitvalueBean.strCtDate}">
</div>
<div class="col-sm-2">
<label>Remarks if Any</label>
</div>
<div class="col-sm-2">
<textarea class="form-control" name="strremark" ></textarea>
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

<table CLASS ="TABLEWIDTH" align ="center" cellpadding="1px" cellspacing="1px" style="display:none;">
   <tr class="HEADER"> 
    <td colspan="2">Unit Value Master&gt;&gt;Add</td>
  </tr>               
   <tr>
  <%--   <td class="LABEL"><span class="style1">*</span>Module Name </td>
    <td width="50%" class ="CONTROL">
    <select name ="strmoduleName" class="comboNormal" onChange="combo1('FROMUNITVAL');">
	<bean:write name="unitvalueBean" property="strCmbval" filter="false"/>
	</select></td>
  </tr>
 
  <tr>
    <td class="LABEL"><span class="style1">*</span>From Unit </td>
    <td width="50%" class ="CONTROL"><div id ="fromUnitId"><select name ="strfromUnit" class="comboNormal" ><option value="0">Select Value</option></select> </div>
    </td>
  </tr>
   <tr >
    <td class="LABEL"><span class="style1">*</span>To Unit </td>
    <td width="50%" class ="CONTROL"><div id ="toUnitId">
      <select name ="strtoUnit" class="comboNormal">
        <option value ="0">Select Value</option>
      </select>
    </div></td>
  </tr> --%>
   <!-- <tr >
    <td class="LABEL"><span class="style1">*</span>Converted Value </td>
    <td width="50%" class ="CONTROL"><input type="text" class="txtFldMax" name="strconvertedValue" value ="" maxlength="10" onKeypress ='return validateData(event,7);'> </td>
  </tr> -->
  <tr >
    <td class ="LABEL" width ="50%"><div align="right"><span class="style1">*</span> Effective From</div></td>
    <td class ="CONTROL"><date:date name="streffectiveFrom" value ="${unitvalueBean.strCtDate}"> </date:date></td>
  </tr>
  <tr >
    <td width ="50%" class ="LABEL"valign="top">Remarks if Any </td>
    <td width ="50%" class ="CONTROL"><textarea  name="strremark" ></textarea></td>
  </tr>
    <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
   </tr>
</table>
	 
	    <table CLASS ="TABLEWIDTH" align="center" style="display:none;">
	      <tr> 
	<td align="center">
		<!-- <img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-sv.png" title="Save Record" onClick=" return validate1(); "/>
		<img style="cursor:pointer;cursor:hand"  src="../../hisglobal/images/btn-clr.png" title="Reset Content" onClick="document.forms[0].reset(),document.forms[0].strmoduleName.focus();" />
		<img style="cursor:pointer;cursor:hand" src="../../hisglobal/images/btn-ccl.png" title="Cancel Process" onClick ="cancel('LIST');"/> -->
	   
	   <br>					 
				<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>
				<a href="#" class="button"	onclick="document.forms[0].reset(),document.forms[0].strmoduleName.focus();"><span class="clear">Clear</span></a> 
				<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	    </td>
	      </tr>
	    </table>
	    
	   	<input type="hidden" name="hmode">
	   	<input type="hidden" name="StrHospitalCode" value =""/>
	   	<input type="hidden" name="StrModuleId" value =""/>
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
			
			