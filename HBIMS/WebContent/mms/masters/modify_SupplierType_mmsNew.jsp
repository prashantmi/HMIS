<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset="utf-8">
<title>Supplier Type Master</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css"
	rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css"
	rel="stylesheet" />
<link
	href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css"
	rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css"
	rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet"
	type="text/css">
<link
	href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css"
	rel="stylesheet" type="text/css">

<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript"
	src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>




<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">	
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language ="javaScript">


<!-- 
	/**
 	 * Developer : Tanvi Sappal
     * Create Date : 26/Oct/2009
     * Process Name : Supplier Type Master
     * Version : 1.1
     * Modify By/Date : 
     */ 
    -->

function validate1(){   
     
      var hisValidator = new HISValidator("suppliertypeBean");
 
            hisValidator.addValidation("strSupplierTypeName", "req", "Supplier Type Name is a Mandatory Field");
            hisValidator.addValidation("strSupplierTypeName",  "maxlen=50", "Supplier Type Name should have less than or equal to 50 Characters");
            hisValidator.addValidation("strEffectiveFrom", "date","Effective From is a mandatory field");
       		hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters");
			
			       
            var retVal = hisValidator.validate(); 

          if(retVal)
          {
                 	   document.forms[0].hmode.value = "UPDATE";
                       document.forms[0].submit();
           }
           else
           {
             return false;
           }
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

<body onLoad="document.forms[0].strSupplierTypeName.focus();">
<html:form name="suppliertypeBean" action="/masters/SupplierTypeMstBSCNT" type="mms.masters.controller.fb.SupplierTypeMstFB">
<center>
	<div class="errMsg"><bean:write name="suppliertypeBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="suppliertypeBean" property="strWarning"/></div>
	<div id="normalMsg" class="normalMsg"><bean:write name="suppliertypeBean" property="strMsg"/></div>

	<%-- <tag:tab tabLabel="Supplier Type Master"
				selectedTab="FIRST" align="center" width="TABLEWIDTH">
			</tag:tab> --%>
			</center>
			
	<br>		
<div class=" px-1 px-md-4  mx-auto">
   <div class="card">
   <div class="row">
				<div class="col-sm-9" align="left">
							<p class="subHeaders" style="margin-bottom: 0;">
								<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Supplier Type Master&nbsp;&nbsp;<i class="fas fa-angle-double-right"></i>&nbsp;Modify			
							</p>
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
	<label><font color="red">*</font>Supplier Type Name</label>
	</div>
	<div class="col-sm-3">
	<input type="text" name="strSupplierTypeName" class="form-control" maxlength="100" value ="${suppliertypeBean.strSupplierTypeName}"  onkeypress="return validateData(event,18);">
	</div>
	<div class="col-sm-1"></div>
	<div class="col-sm-3">
	<label><font color="red">*</font>Effective From</label>
	</div>
	<div class="col-sm-3">
	<bean:write name="suppliertypeBean" property="strEffectiveFrom"></bean:write>
	</div>
	</div>
	
	<div class="row">
	<div class="col-sm-2">
	<label>Remark</label>
	</div>
	<div class="col-sm-4">
	<textarea name="strRemarks" class="form-control" rows="2"><bean:write name="suppliertypeBean" property="strRemarks"/></textarea>
	</div>
	<div class="col-sm-3">
	<label>Record Status</label>
	</div>
	<div class="col-sm-3">
	<html:radio name="suppliertypeBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="suppliertypeBean" property="strIsValid" value="2">Inactive</html:radio>
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
    <td colspan="2">Supplier Type Master&gt;&gt;Modify</td>
  </tr>      
  
   <tr >
    <td class="LABEL"><font color="red">*</font>Supplier Type Name</td>
    <td width="50%" class ="CONTROL"><input type="text" name="strSupplierTypeName" class='txtFldMax' maxlength="100" value ="${suppliertypeBean.strSupplierTypeName}"  onkeypress="return validateData(event,18);"> </td>
   </tr>

  <tr >
    <td class ="LABEL" width ="50%"><font color="red">*</font>Effective From</td>
    <td class ="CONTROL"><bean:write name="suppliertypeBean" property="strEffectiveFrom"></bean:write></td>
  </tr>
    
    <tr>
      <td class="LABEL"><div align="right"> Remarks </div></td>
      <td class="CONTROL">
	  <div align="left">
        <textarea name="strRemarks" rows="2"><bean:write name="suppliertypeBean" property="strRemarks"/></textarea>
      </div></td>
    </tr>
    
    
    <%-- <tr >
    <td width ="45%" class ="LABEL">Record Status </td>
    <td width ="45%" class ="CONTROL" >
   <html:radio name="suppliertypeBean" property="strIsValid" value="1">Active</html:radio>
    <html:radio name="suppliertypeBean" property="strIsValid" value="2">Inactive</html:radio>
    
   </td>
    </tr> --%>
    <tr class="FOOTER"> 
    <td colspan="2" ><font size="2" color="red">*</font> Mandatory Fields  </td>
  </tr>
</table>																														

	    	<table border="0" class="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px" style="display:none;">
		<tr>
			<br>
			<td align="right">
			<!-- <img 
				src="../../hisglobal/images/btn-sv.png" onClick="return validate1();" />
				 -->
			<a href="#" class="button" id="" onclick=' return validate1();'><span class="save">Save</span></a>

			</td>
			<td align="left">
			<!-- <img src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" > -->
		<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>

	    <input type="hidden" name="chk" value="${suppliertypeBean.chk[0] }">
	   	<input type="hidden" name="hmode">
	   	<input type="hidden" name="strEffectiveFrom" value ="${suppliertypeBean.strEffectiveFrom}">
	 
	   	
	<cmbPers:cmbPers/> 
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>	
			
			