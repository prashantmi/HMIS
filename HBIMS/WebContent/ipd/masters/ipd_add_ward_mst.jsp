<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset=utf-8>
<title>Ward Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="javascript">

/*function shiftRightCond(){
                 //alert(document.forms[0].strLDisease.options[document.forms[0].strLDisease.selectedIndex].text);
                  var ret = searchInListBox("strRroom",document.forms[0].strLroom.options[document.forms[0].strLroom.selectedIndex].text);
                  if(!ret){
                  shiftToRight("strLroom","strRroom",1);
                  }else{
                  alert("Data Already Exists");
                  }
      }*/
      
      
function hideMaxPatPerBed()
{
     if(document.getElementsByName("strWardType")[0].value == document.getElementsByName("strPrivateWardType")[0].value )
     {
      	document.getElementsByName("strMaxPatPerBed")[0].value = "1";
     	document.getElementsByName("strMaxPatPerBed")[0].disabled  = "true";
     }
     else
     {
     	document.getElementsByName("strMaxPatPerBed")[0].value = "";
     	document.getElementsByName("strMaxPatPerBed")[0].disabled  = "";
     }

}      

function validate1()
{	
		var hisValidator = new HISValidator("wardBean"); 
		hisValidator.addValidation("strWardName", "req", "Ward Name  is a Mandatory Field" ); 
		hisValidator.addValidation("strWardType","dontselect=0","Please select a value from Ward Type Combo");
		hisValidator.addValidation("strNoOfBed","dontselect=0","Please select no. of bed");
		//hisValidator.addValidation("strBuilding","dontselect=-1","Please select a value from Building Combo");
	    //hisValidator.addValidation("strBlock","dontselect=-1","Please select a value from Block Combo");
	    hisValidator.addValidation("strMaxPatPerBed", "req", "Max no. of Patient Per Bed is a Mandatory Field" ); 
	    hisValidator.addValidation("strMaxPatPerBed", "maxlen=2","Max no. of Patient Per Bed can't have more than two digits");  
	    hisValidator.addValidation("strMaxPatPerBed", "numlt=11","Max no. of Patient Per Bed can't be greater than 10."); 
	    hisValidator.addValidation("strMaxPatPerBed", "numgt=0","Max no. of Patient Per Bed can't be less than 1."); 
	   
	    hisValidator.addValidation("strbookingAllowed", "maxlen=2","Booking allowed can't have more than two digits");  
	    if(document.forms[0].strbookingAllowed.value.length>=1)
	    {
	    	if(document.forms[0].strbookingAllowed.value<1)
	  	 	 {
	    		alert('The booking allow should be greater than zero');
	    		return false;
	  	 	 }
	    	hisValidator.addValidation("strmoreorless","dontselect=0","Please select value from booking allowed combo");
	    }
	    if(document.forms[0].strmsapproveRequired.checked)
	    {
	    	hisValidator.addValidation("strmaxNoWaitingList", "req", "Maximum no of patient in waiting list is mandatory" );
	    	
	    }
	    hisValidator.addValidation("strRroom","dontselect=0","Please Select Room from the List");
		//hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
	    //hisValidator.addValidation("strEffectiveFrom", "date","Effective From should be a valid Date");
		//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
		/*if(document.forms[0].strEffectiveTo.value !=""){
			hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
		  }*/
		hisValidator.addValidation("strRemark", "maxlen=50","Remarks Cannot be More than 50 Characters");
		var retVal = hisValidator.validate(); 
			hisValidator.clearAllValidations();
		if(retVal)
		{
		        selectListRecords("strRroom");		
				document.forms[0].hmode.value = "SAVE";
				document.forms[0].submit();
				return true;
		}
		else
		{
			hisValidator.clearAllValidations();
			return false;
		}		
	}
 
function ajaxFunBlock(mode)
{ 
   //alert("inside ajaxFunBlock");
   var mode ="MODEBLOCK";
   var url="CNTWardMst.cnt?hmode=MODEBLOCK&modName="+document.forms[0].strBuilding.value;
   ajaxFunction(url,"1");
}
function ajaxFunRoom(mode)
{ 
	//alert("inside ajaxFunRoom");
  	var mode ="MODEROOM";
  	var url="CNTWardMst.cnt?hmode=MODEROOM"+"&strBuildingId="+document.forms[0].strBuilding.value+"&strBlockId="+document.forms[0].strBlock.value;
   	ajaxFunction(url,"2");

}

function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1"){
	
		objVal= document.getElementById("blockid");
		objVal.innerHTML = "<select name ='strBlock' class='comboNormal' onChange = 'ajaxFunRoom(\"MODEROOM\")' >" + res + "</select>";
		document.forms[0].strBlock.value="0";
		var url="CNTWardMst.cnt?hmode=MODEROOM"+"&strBuildingId="+document.forms[0].strBuilding.value+"&strBlockId="+document.forms[0].strBlock.value;
	   	ajaxFunction(url,"2");
	   	
		
		
	}
	else if(mode=="2"){
		 var obj1 = document.getElementById("roomLDivId");
		 obj1.innerHTML = "<select name='strLroom'  size='10' style='width: 220px' multiple>" + res + "</select>";
		 var obj2 = document.getElementById("rroomId");
		 obj2.innerHTML = "<select name='strRroom' size='10' style='width: 178px;' multiple></select>";
	
	//var obj2= document.getElementById("rroomId");
	//obj2.innerHTML = "<select name='strRroom' size='8'> </select>";
	}
}
function funWaitingList()
{
            if(document.wardBean.strmsapproveRequired.checked == true)
                  document.getElementById("divWaiting").style.display="block";
               else
			        document.getElementById("divWaiting").style.display="none";
}

</script>
</head>
<body>
<html:form action="/masters/CNTWardMst">	
	<div class="errMsg"><bean:write name="wardBean" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="wardBean" property="msg"/></div>
	<div class="warningMsg"><bean:write name="wardBean" property="warnings"/></div>
	<tag:tab tabLabel="Add Ward" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	<tr class="HEADER">
		<td colspan="6">Ward Master&gt;&gt;Add</td>
	</tr>
	<tr>
		<td width="20%" class="LABEL" nowrap colspan="1"><font color="red">*</font>Ward Name</td>
		<td colspan="5" class="CONTROL">
		<input type="text" onkeyup="lTrim(this);" onblur="Trim(this);"  maxlength ="40" 
		name="strWardName" onkeypress="return validateData(event,9);" value=""></td>
	</tr> 
	<tr>
			<td class="LABEL" colspan="1"><font color="red">*</font>Ward Type</td>
			<td class="CONTROL" colspan="2"><select name="strWardType" class='comboNormal' onchange="hideMaxPatPerBed();">
				<bean:write name="wardBean" property="wardTypeAdd" filter="false"/>
			</select></td>
			<td class="LABEL" nowrap colspan="1"><font color="red">*</font>No Of Beds</td>
			<td class="CONTROL" colspan="2"><select name="strNoOfBed" class='comboNormal'>
				<bean:write name="wardBean" property="strBedAdd" filter="false"/>		
			</select></td>
		</tr>
		<tr>
			<td width="15%" class="LABEL" colspan="1">Building</td>
			<td class="CONTROL" colspan="2"><select name="strBuilding" class='comboNormal'
				onChange="ajaxFunBlock('MODEBLOCK')">
					<bean:write name="wardBean" property="buildingAdd" filter="false"/>			
			</select></td>
			<td class="LABEL" colspan="1">Block</td>
			<td class="CONTROL" colspan="2">
			<div id="blockid"><select name="strBlock" class='comboNormal'>
				<bean:write name="wardBean" property="blockAdd" filter="false"/>
			</select></div>
			</td>
		</tr>
		<tr>
		<td width="20%" class="LABEL" nowrap colspan="1"><font color="red">*</font>Max No. of Patient Per Bed</td>
		<td colspan="5" class="CONTROL">
		<input type="text" onkeyup="lTrim(this);" onblur="Trim(this);"  maxlength ="2" 
		name="strMaxPatPerBed" class='txtFldNormal' onkeypress="return validateData(event,5);" value=""></td>
	    </tr>
		<tr >
		<td class ="LABEL" colspan="1">Booking Allowed</td>
		<td class="CONTROL" colspan='2'>
		<input type ="text" onkeyup="lTrim(this);" onblur="Trim(this);"  name ="strbookingAllowed" class='txtFldNormal' maxlength="2" onkeypress ="return validateData(event,5);">
		  <strong>%</strong></td>	
		<td  CLASS ="CONTROL" colspan="3">
		 <select name = "strmoreorless">
		<option value ="0" >Select Value</option>
		<option value ="1" >More</option>
		<option value ="2">Less</option>
		</select> 
		  of to be Vacant beds.
		</td>
		</tr>
		<tr>
		<td class ="LABEL" colspan="6">Ms Approval Required
		 <html:checkbox name ="wardBean" property ="strmsapproveRequired"  value ="1" onclick="funWaitingList();"/></td>
		</tr>
		</table>
		<div id ="divWaiting" style="display:none" align="left">
		<table CLASS="TABLEWIDTH" align="center">
		<tr>
		<td  class ="LABEL" colspan="1" width="40%">
		<font color="red">*</font>Max No of Patient in waiting list		
		</td>
		<td  class ="CONTROL" colspan="5" width="60%">
		<input type ="text" name ="strmaxNoWaitingList" class='txtFldSmall' maxlength ="3" 
			onkeypress ="return validateData(event,5);">
		</td>
		</tr>
		</table>
		</div>
		<table CLASS="TABLEWIDTH" align="center" cellspacing="1px">
		<tr>
			<td WIDTH="100%" class="CONTROL" colspan="4" valign="top">
			<div align="left"><font color="red">*</font><b>Room Desc.</b></div></td>
		</tr>
		<tr>
			<td WIDTH="20%" class="CONTROL" colspan="1">
			<div id="roomLDivId" align="center">
			<select name="strLroom" size="10" style="width:178px;" multiple>
				<bean:write name="wardBean" property="strRoom" filter="false"/>
			</select>

				</div>
			</td>
			<td width="5%" class="CONTROL" colspan="2">
			<div align="center">
			<img src="../../hisglobal/images/forward3.gif" width="35"
				height="31" align="top"  style="cursor:hand;pointer:hand"
				onClick='shiftToRight("strLroom","strRroom",1);'>
			</div>
			<br>
			<div align="center">
			<img src="../../hisglobal/images/forwardward.gif" width="35"
				height="31" align="middle"  style="cursor:hand;pointer:hand"
				onClick='shiftAllToRight("strLroom","strRroom",1);'>
			</div>
			<br><div align="center">
			<img src="../../hisglobal/images/backward.gif" width="35"
				height="31"   style="cursor:hand;pointer:hand"
				onClick='shiftAllToLeft("strLroom","strRroom",1);'>
			</div>
			<br>
			<div align="center">
			<img src="../../hisglobal/images/back3.gif" width="35" height="31"  style="cursor:hand;pointer:hand"
				 onClick='shiftToLeft("strLroom","strRroom",1);'>
			</div>
			</td>
			<td colspan="1" class="CONTROL" WIDTH="20%">
			<div id="rroomId" align="center"><select name="strRroom" size="10" style="width: 178px;"
				multiple>
			</select></div>
			</td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
		<tr style="display:none">
			<td class="LABEL" colspan="3" width="25%"><font color="red">*</font>Effective Date</td>
			<td class="CONTROL"><date:date name="strEffectiveFrom"
				value="${wardBean.strEffectiveFrom}"></date:date></td>
			<td class="LABEL" nowrap>Effective To</td>
			<td class="CONTROL"><date:date name="strEffectiveTo" 
				value="${wardBean.strEffectiveTo}"></date:date></td>
		</tr>
		<tr>
			<td width="15%" class="LABEL">Remarks</td>
		<td colspan="5" class="CONTROL">
		<textarea  onkeyup="lTrim(this);" onblur="Trim(this);" name="strRemark" cols="20" rows="2" ></textarea></td>
		</tr>
		<tr class="FOOTER">
			<td colspan="6"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>
	
	 <table CLASS ="TABLEWIDTH" align="center" cellpadding="1px" cellspacing="1px">
	      <tr> 
		<td align="center">
		<!-- <img src="../../hisglobal/images/btn-sv.png"  style="cursor:pointer" onclick="return validate1('SAVE'); "/>
		<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" onClick="document.forms[0].reset();">
		<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');"/> -->
		
		<br><a href="#" class="button" id="" onClick="return validate1('SAVE');" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	   </td>
	      </tr>
	    </table>	
	<input type="hidden" name="hmode">
	<cmbPers:cmbPers/>
	<input type="hidden" name="strPrivateWardType" value="${wardBean.strPrivateWardType}" >
</html:form>

<script>
document.forms[0].strNoOfBed.value="0";
</script> 
<tag:autoIndex></tag:autoIndex>
</body>
</html>