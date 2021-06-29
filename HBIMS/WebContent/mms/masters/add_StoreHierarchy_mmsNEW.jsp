<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>


<html>
<head>
<meta charset=utf-8>
<title>Store Hierarchy Master Add Page</title>


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
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<script language="javaScript">

function validate1(){   
     
     selectListRecords("strRightStoreNames"); 
      var hisValidator = new HISValidator("StoreHierarchyBean"); 
        hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
        //hisValidator.addValidation("strEffectiveFrom", "date","Effective Date is a mandatory field");
       //  hisValidator.addValidation("strRightStoreNames", "dontselect=0","Please move atleast one store from left to right \n And select only those stores which are to be added from right list");
        
      var retVal = hisValidator.validate(); 
    	
          if(retVal){
        		 var count = selectListRecords("strRightStoreNames");
        		 if(count==0)
        		 {alert("Please select a store which is not already added");
        		 return false;
        		 }
       			   document.forms[0].hmode.value = "SAVE";
                        document.forms[0].submit();
            }else{
	           return false;
			   }
    }


function LeftListTransfer()
{
	var ob1=document.forms[0].strLeftStoreNames.value;
	var ob=document.getElementById("LeftStores");
	shiftToRight("strLeftStoreNames","strRightStoreNames",1);
}

function searchInList()
{ 
   searchInListBox("strLeftStoreNames",document.forms[0].searchVal.value);  
}


function changeViewMode(chkObj)
{ 	
    if(chkObj.value =='1')
 	{ 	
 		 document.forms[0].strCheck.value = '1'; 
         document.forms[0].strAll.checked = true;
 		 document.forms[0].strAssociated.checked = false;
 		 getAssociatedStore("1");
 	}
 	if(chkObj.value =='2')
 	{ 		 
 		 
 		 document.forms[0].strCheck.value = '2'; 
         document.forms[0].strAll.checked = false;
 		 document.forms[0].strAssociated.checked = true;
 		 getAssociatedStore("2");
 		 
 	}
}
function setValue()
{
         document.forms[0].strCheck.value = '1'; 
         document.forms[0].strAll.checked = true;
 		 document.forms[0].strAssociated.checked = false;
} 


function getAssociatedStore(flg)
{	
	 var url;
	 
	 var mode = 'ASSOCIATEDSTORE';   
	 url="StoreHierarchyMstCNTNEW.cnt?hmode="+mode+"&StoreId="+document.forms[0].strFromStoreId.value+"&ItemCatId="+document.forms[0].strItemCatgory.value+"&RequestTypeId="+document.forms[0].strRequestTypeId.value+"&Flag="+flg;       
	 //alert(url);
	 ajaxFunction(url,"1");
}
function getAjaxResponse(res,mode)
{
		
	   if(mode=="1")
	   {   
	
				var err = document.getElementById("errMsg");
		 		var temp1 = res.split("####");
			         
        		 if(temp1[0] == "ERROR")
        		 {
         			err.innerHTML = temp1[1];	
        		 }
				 else
				 {
				    var ox = res.split("^^^^");
				    objVal= document.getElementById("LeftStores");
				    objVal.innerHTML = "<select name='strLeftStoreNames' size='8' multiple style='width: 280px'>"+ox[0]+"</select>";
				    
				    objVal= document.getElementById("RightStores");
				    objVal.innerHTML = "<select name='strRightStoreNames' size='8' multiple style='width: 280px'>"+ox[1]+"</select>";
				 }
		 }
		  
}


</script>

</head>
<body onLoad="setValue();">
<html:form name="StoreHierarchyBean" action="/masters/StoreHierarchyMstCNTNEW"
	type="mms.masters.controller.fb.StoreHierarchyMstFB">
	
	
	<div class="viewport" id="nonPrintable">
			<div class="container-fluid">

			<div class="row justify-content-center">
					<div class="col-sm-12">
						<br>
						<div class="prescriptionTile">
						<div class="row rowFlex reFlex">
						<div class="col-md-12" align="center">
								<div class="errMsg"><bean:write name="StoreHierarchyBean"
		property="strErr" /></div>
	<div class="warningMsg"><bean:write name="StoreHierarchyBean"
		property="strWarning" /></div>
	<div id="normalMsg"  class="normalMsg"><bean:write name="StoreHierarchyBean"
		property="strMsg" /></div>

						</div>
						</div>
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancel('LIST');">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle"
										onclick="document.forms[0].reset()"
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<!-- <button  type="button" class="btn btn-outline-primary mt-1 btn-circle printbtn" onclick="transferToViewPageNEW();">
											<i class="fas fa-eye iround"  title="View"></i>
									</button> -->
									<button type="button" id="savebutton"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' onclick=' return validate1();'
										name="patientAdmissionModiTransBean"
										style="background-color: #5cb85c;">
										<i class="fa fa-download iround" title="Save"></i>
									</button>
								</div>
							</div>


					<div class="row rowFlex reFlex">
								<div class="col-sm-5">
									<p class="subHeaders" style="margin-bottom: 0;">
										<i class="fas fa-university" style="font-size: 26px;">&nbsp;</i>Store Hierarchy Master<i class="fas fa-angle-double-right"></i>&nbsp;Add		
									</p>
									
							</div>
							</div>
	
	
		<div class="row rowFlex reFlex">
		<div class='col-md-2 px-4'>From Store</div>
		<div class='col-md-2' style='color:blue;'><bean:write name="StoreHierarchyBean" property="strFromStoreName" filter="false" /></div>
		<div class='col-md-2'>Item Category</div>
		<div class='col-md-2' style='color:blue'><bean:write name="StoreHierarchyBean" property="strItemCatId" filter="false" /></div>
		<div class='col-md-2'>Request Type</div>
		<div class='col-md-2' style='color:blue'><bean:write name="StoreHierarchyBean" property="strRequestType" filter="false" /></div>
		
		</div>
	
		<div class="row rowFlex reFlex">
		<div class='col-md-2 px-4'>Hospital</div>
		<div class='col-md-2'> 
					<input type="radio"	name="strAll"    value="1" 	onClick="changeViewMode(this);"/>All
		            <input type="radio"	name="strAssociated" value="2"	onClick="changeViewMode(this);" />Associated</div>
		<div class='col-md-2'><font color="red">*</font> Effective From</div>
		<div class='col-md-2'><input  name="strEffectiveFrom"
											class="form-control datepicker"
											value="${StoreHierarchyBean.strCtDate}"
											style="color: rgba(113, 111, 111, 0.87);"></div>
		
		</div>
			
		<div class="row">
			<div class="col-md-2 px-4"><font color="red">*</font>To Store</div>
		</div>
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-3">
			<div id="LeftStores" align="right">
			    <select class="form-control" name="strLeftStoreNames" size="8" 
				multiple >
				<bean:write name="StoreHierarchyBean" property="strLeftStoreNamesList" filter="false"/>
				</select>
			</div>
			</div>
		
			<div class="col-md-1">
			<center>
			<img src="../../hisglobal/images/forward3.gif" width="35"
				height="31" 
				onclick ="LeftListTransfer();"></center>
				
				<center><img src="../../hisglobal/images/forwardward.gif" width="35" height="31" 
				align="middle" onClick="shiftAllToRight('strLeftStoreNames','strRightStoreNames',1);"/></center>
				<br/>
				<center>
				
				<img src="../../hisglobal/images/backward.gif" width="35"
				height="31" 
				onClick='shiftAllToLeft("strLeftStoreNames","strRightStoreNames",1);'>
				</center>
				
				<center><img src="../../hisglobal/images/back3.gif" width="35" 
				height="31" 
				onclick="shiftToLeft('strLeftStoreNames','strRightStoreNames',1);"/></center>
			</div>
			<div class="col-md-3">
			<div id="RightStores" align="left"><select class="form-control" name="strRightStoreNames" size="8" 
			multiple><bean:write name="StoreHierarchyBean" property="strRightStoreNamesList" filter="false"/>
			</select></div>
			</div></div>
			
<div class="row">
	<div class='col-md-2 px-4'>Remarks if Any</div>
	<div class='col-md-10'><textarea name="strRemarks" class='form-control' cols="25" rows="2"></textarea></div>
</div>
		
<div class="row">
	<div class='col-md-12' align="right"><font size="2" color="red">*</font> Mandatory Fields </div>
</div>
		


	<br>

<input type="hidden" name="hmode"/>
<input type="hidden" name="comboValue" value="${StoreHierarchyBean.strComboValue}">
<input type="hidden" name="strCheck" value="">
<input type="hidden" name="strFromStoreId" value="${StoreHierarchyBean.strFromStoreId}">
<input type="hidden" name="strRequestTypeId" value="${StoreHierarchyBean.strRequestTypeId}">
<input type="hidden" name="strItemCatId" value="${StoreHierarchyBean.strItemCatId}">
<input type="hidden" name="strItemCatgory" value="${StoreHierarchyBean.strItemCatgory}">

<cmbPers:cmbPers/>

</div>
</div>
</div>
</div>
</div>

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