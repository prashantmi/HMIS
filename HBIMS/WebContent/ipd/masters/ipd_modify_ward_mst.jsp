<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html>
<head>
<meta charset=utf-8>
<title>Ward Master Modify Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/datepicker1.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="javascript">

	var flag=0;
	function setOption(){
	
	if(document.forms[0].updateMode[0].checked)
	document.forms[0].updateMode.value =0;
	else if(document.form[0].updateMode[1].checked)
	document.forms[0].updateMode.value =1;
	
	}
	/*function validate1(mode){	
		  
		var hisValidator = new HISValidator("wardBean"); 
		hisValidator.addValidation("strWardName", "req", "Ward Name  is a Mandatory Field" ); 
		hisValidator.addValidation("strWardType","dontselect=0","Please select a value from Ward Type Combo");
		hisValidator.addValidation("strNoOfBed","dontselect=0","Please select a value from Bed Combo");
		hisValidator.addValidation("strBuilding","dontselect=0","Please select a value from Building Combo");
         hisValidator.addValidation("strBlock","dontselect=0","Please select a value from Block Combo");
	    hisValidator.addValidation("strRroom","dontselect=0","Please Select Room from the List");
	      hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
	    if(document.forms[0].strEffectiveTo.value !=""){
	   hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
	    }
	   	// if(document.forms[0].updateMode[0].checked){
		hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardBean.strCtDate}" , "Effective From Date should be Greater than or Equal to Current Date");
		hisValidator.addValidation("strEffectiveTo", "dtgtet=${wardBean.strCtDate}" , "Effective To Date should be Greater than or Equal to Current Date");
		hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
	
	//}
	 if(document.forms[0].updateMode[1].checked){
		hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardBean.strCtDate}" , "Effective From Date should be Greater than or Equal to Current Date");
		hisValidator.addValidation("strEffectiveTo", "dtgtet=${wardBean.strCtDate}" , "Effective To Date should be Greater than or Equal to Current Date");
		hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
	
	}

		
		var retVal = hisValidator.validate(); 
		
		alert("Checking");
		if(retVal){
				document.forms[0].hmode.value = mode;
				selectListRecords("strRroom");		
				document.forms[0].submit();
				return true;
		}else{
		
		hisValidator.clearAllValidations();
		return false;
		}
		
	}*/
function validate1(mode)
{	
		var hisValidator = new HISValidator("wardBean"); 
		hisValidator.addValidation("strWardName", "req", "Ward Name  is a Mandatory Field" ); 
		hisValidator.addValidation("strWardType","dontselect=0","Please select a value from Ward Type Combo");
		hisValidator.addValidation("strNoOfBed","dontselect=0","Please select a value from Bed Combo");
		//hisValidator.addValidation("strBuilding","dontselect=-1","Please select a value from Building Combo");
        //hisValidator.addValidation("strBlock","dontselect=-1","Please select a value from Block Combo");
        hisValidator.addValidation("strMaxPatPerBed", "req", "Max no. of Patient Per Bed is a Mandatory Field" ); 
	    hisValidator.addValidation("strMaxPatPerBed", "maxlen=2","Max no. of Patient Per Bed can't have more than two digits");  
	    hisValidator.addValidation("strMaxPatPerBed", "numlt=11","Max no. of Patient Per Bed can't be greater than 10."); 
	    hisValidator.addValidation("strMaxPatPerBed", "numgt=0","Max no. of Patient Per Bed can't be less than 1."); 
       
        hisValidator.addValidation("strbookingAllowed", "maxlen=50","Booking allowed can't have more than 2 digits");
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
	    	hisValidator.addValidation("strmaxNoWaitingList", "req", "Maxium no of patient in waiting list is mandatory" );
	    } 
	    hisValidator.addValidation("strRroom","dontselect=0","Please Select Room from the List");
	  //if(document.forms[0].updateMode[1].checked){
	  //	hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardBean.strCtDate}" , "Effective From Date should be Greater than or Equal to Current Date");
		//hisValidator.addValidation("strEffectiveTo", "dtgtet=${wardBean.strEffectiveFrom}" , "Effective To Date should be Greater than or Equal to Effect From Date");
		/*obj=document.forms[0].strEffectiveTo.value;
		//obj.trim();
		if(trim(document.forms[0].strEffectiveTo.value)!="" )
		{
			hisValidator.addValidation("strEffectiveFrom" , "dtltet="+document.forms[0].strEffectiveTo.value ,"Effective From Date should be Less than or Equal to Effective To Date");
		}	
		hisValidator.addValidation("strEffectiveFrom" , "dtgtet="+document.forms[0].strCtDate.value ,"Effective From Date should be greater than or Equal to Current Date");*/
		hisValidator.addValidation("strRemark", "maxlen=50","Remarks Cannot be More than 50 Characters");
		var retVal = hisValidator.validate(); 
		if(retVal)
		{
			document.forms[0].hmode.value = mode;
			selectListRecords("strRroom");	
			document.forms[0].strEffectiveTo.value=trim(document.forms[0].strEffectiveTo.value);	
			document.forms[0].submit();
			return true;
		}
		else
		{
			hisValidator.clearAllValidations();
			return false;
		}		
}

function ajaxFun(mode)
{ 
 var url;
 	if(mode=="MODEBLOCK")
 	{
	  url="CNTWardMst.cnt?hmode=MODEBLOCK&modName="+document.forms[0].strBuilding.value;
	  ajaxFunction(url,"1");
 	}
	else if(mode=="MODEROOMCOR")
	{
		//url="CNTWardMst.cnt?hmode=MODEROOM&modName="+"&modName1="+document.forms[0].strBlock.value;
		var url="CNTWardMst.cnt?hmode=MODEROOM"+"&strBuildingId="+document.forms[0].strBuilding.value+"&strBlockId="+document.forms[0].strBlock.value;
 		ajaxFunction(url,"2");
	}
}

function getAjaxResponse(res,mode)
{
	var objVal;
	if(mode=="1")
	{
		objVal= document.getElementById("blockid");
		objVal.innerHTML = "<select name ='strBlock' class='comboNormal' onChange = 'ajaxFun(\"MODEROOMCOR\");' >" + res + "</select>";
		document.forms[0].strBlock.value="0";
		var url="CNTWardMst.cnt?hmode=MODEROOM"+"&strBuildingId="+document.forms[0].strBuilding.value+"&strBlockId="+document.forms[0].strBlock.value;
	   	ajaxFunction(url,"2");
	}
	else if(mode=="2")
	{
		var obj = document.getElementById("strLroomDivId");
		obj.innerHTML = "<select name='strLroom' size='10' multiple='multiple' style='width: 178px;'>" + res + "</select>";
		var obj2 = document.getElementById("strRoomRDivId");
		obj2.innerHTML = "<select name='strRroom' size='10' multiple='multiple' style='width: 178px;'> </select>";
	}
}

function dtFunc()
{
     	   if(document.forms[0].strmsapproveRequired.value==0)
           {
           		document.forms[0].strmsapproveRequired.checked= false;
           		document.getElementById("divWaiting").style.display="none";
           }
           if(document.wardBean.strbookingAllowed.value=="0")
		   {
				document.wardBean.strbookingAllowed.value="";
		   }	
            /*if(document.wardBean.updateMode[0].checked){
                  document.getElementById("frDt2").style.display="none";
                  document.getElementById("frDt1").style.display="block";
            }else{
                  document.getElementById("frDt1").style.display="none";
                  document.getElementById("frDt2").style.display="block";
                  flag=1;
            }*/
}

	/*function multInitFunc(){
		var rowLength 
		document.multirow.rowLength1.value = rowLength;
		document.multirow.rowIndex1.value = rowLength; 
	}*/
function funWaitingList()
{
            if(document.wardBean.strmsapproveRequired.checked == true)
            {
                  document.getElementById("divWaiting").style.display="block";
                  document.wardBean.strmsapproveRequired.value="1";
            }
            else
            {
			      document.getElementById("divWaiting").style.display="none";
			      document.wardBean.strmsapproveRequired.value="0";
			      document.forms[0].strmaxNoWaitingList.value="0";
			}
}


function hideMaxPatPerBed()
{
     if(document.getElementsByName("strWardType")[0].value == document.getElementsByName("strPrivateWardType")[0].value )
     {		
     		document.getElementsByName("strMaxPatPerBed")[0].value = "1";
     		document.getElementsByName("strMaxPatPerBed")[0].disabled  = "true";
     }
     else
     {      
     		document.getElementsByName("strMaxPatPerBed")[0].disabled  = "";
     		document.getElementsByName("strMaxPatPerBed")[0].value = ${wardBean.strMaxPatPerBed};
     }

}      

</script>
</head>
<body onLoad="dtFunc();hideMaxPatPerBed();">
<html:form action="/masters/CNTWardMst">
	<div class="errMsg"><bean:write name="wardBean" property="strErrorMsg"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="wardBean" property="msg"/></div>
	<div class="warningMsg"><bean:write name="wardBean" property="warnings"/></div>
	
	<tag:tab tabLabel="Modify Ward" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="6">Ward Master&gt;&gt;Modify</td>
		</tr>		
		<tr>
			<td width="25%" class="LABEL" nowrap><font color="red">*</font>Ward Name</td>
			<td width="25%" colspan="3" class="CONTROL">
			<input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" name="strWardName"  
			maxlength ="40" onkeypress="return validateData(event,9)" value="${wardBean.strWardName}"></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL"><font color="red">*</font>Ward Type</td>
			<td width="25%" class="CONTROL"><select name="strWardType" class='comboNormal' onchange="hideMaxPatPerBed();">
				<bean:write name="wardBean" property="wardTypeAdd" filter="false"/>
			</select></td>
			<td width="25%" class="LABEL" nowrap><font color="red">*</font>No Of Beds</td>
			<td width="25%" class="CONTROL" colspan="2"><select name="strNoOfBed" class='comboNormal'>
				<bean:write property="strBedAdd" name="wardBean" filter="false"/>
			</select></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL">Building</td>
			<td width="25%" class="CONTROL"><select name="strBuilding" class='comboNormal'
				onChange="ajaxFun('MODEBLOCK');">
				<bean:write name="wardBean" property="buildingAdd" filter="false"/>
			</select></td>
			<td width="25%" class="LABEL">Block</td>
			<td width="25%" class="CONTROL" >
				<div id="blockid">
					<select name="strBlock" class='comboNormal' onChange="ajaxFun('MODEROOMCOR');">
						<bean:write name="wardBean" property="blockAdd" filter="false"/>
					</select>
				</div>
			</td>
		</tr>
		<tr>
		<td width="20%" class="LABEL" nowrap colspan="1"><font color="red">*</font>Max No. of Patient Per Bed</td>
		<td colspan="5" class="CONTROL">
		<input type="text" onkeyup="lTrim(this);" onblur="Trim(this);"  maxlength ="2" 
		name="strMaxPatPerBed" class='txtFldNormal' onkeypress="return validateData(event,5);" value="${wardBean.strMaxPatPerBed}" ></td>
	    </tr>
		<tr>
			<td width="25%" class ="LABEL" nowrap>Booking Allowed </td>
			<td width="25%" class="CONTROL" nowrap>
				<input onkeyup="lTrim(this);" onblur="Trim(this);" type ="text" name ="strbookingAllowed" 
				value ="${wardBean.strbookingAllowed}" 
				class='txtFldNormal' maxlength="2" OnKeyPress ="return validateData(event,5);"><strong>%</strong>
			</td>	
			<td  width="25%" CLASS ="CONTROL"  colspan="2">
				<html:select property="strmoreorless" name ="wardBean" styleClass ="normalCombo" >
					<html:option value="0">Select Value</html:option>
					<html:option value="1">More</html:option>
					<html:option value="2">Less</html:option>
				</html:select>
			 of to be Vacant beds
			</td>
		</tr>
		<tr>
			<td class ="LABEL" colspan="5">Ms Approval Required
		 	<html:checkbox name ="wardBean" property ="strmsapproveRequired"  
		 	value="${wardBean.strmsapproveRequired}" onclick="funWaitingList();"/></td>
		</tr>
		</table>
		<div id ="divWaiting" style="display:block">
			<table CLASS="TABLEWIDTH" align="center" cellspacing="1px">
				<tr>
					<td  class ="CONTROL" colspan="5">
					Max No of Patient in waiting list
					<html:text name ="wardBean"  property = "strmaxNoWaitingList" maxlength ="3" 
					onkeypress ="return validateData(event,5);"/>
					</td>
				</tr>
		</table>
		</div>
		<div id="block1">
		<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr class="HEADER">
			<td colspan="6">&nbsp;</td>
		</tr>
		<tr>
			<td WIDTH="100%" class="CONTROL" colspan="4" valign="top"><font color="red">*</font>
			<b>Room Desc.</b></td>
		</tr>
		<tr>	
			<td WIDTH="20%" class="CONTROL" colspan="1">
			<div id="strLroomDivId" align="center">
			<select name="strLroom" size="10" multiple="multiple" style="width: 178px;" >
				<bean:write name="wardBean" property="strLRoomModi" filter="false"/>
			</select>
			</div>
			</td>
			<td width="5%" class="CONTROL" colspan="2">
			<div align="center">
			<p><img src="../../hisglobal/images/forward3.gif" width="35"
				height="31" align="top"  style="cursor:hand;pointer:hand"
				onClick='shiftToRight("strLroom","strRroom",1);'></p>
			</div>			
			<div align="center">
			<p><img src="../../hisglobal/images/forwardward.gif" width="35"
				height="31" align="middle"  style="cursor:hand;pointer:hand"
				onClick='shiftAllToRight("strLroom","strRroom",1);'></p>
			</div>			
			<div align="center">
			<p><img src="../../hisglobal/images/backward.gif" width="35"
				height="31"  style="cursor:hand;pointer:hand"
				onClick='shiftAllToLeft("strLroom","strRroom",1);'></p>
			</div>			
			<div align="center">
			<p><img src="../../hisglobal/images/back3.gif" width="35" height="31"  style="cursor:hand;pointer:hand"
				 onClick='shiftToLeft("strLroom","strRroom",1);'></p>
			</div>
			</td>
			<td colspan="1" class="CONTROL" WIDTH="20%">
			<div id="strRoomRDivId" align="center">
			<select name="strRroom" size="10" style="width: 178px;"
				multiple="multiple">
				<% 
					out.print((String ) request.getAttribute("combo"));
				%>
				</select>
				</div>
			</td>
		</tr>
	</table>
	</div>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr style="display:none">
			<td class="LABEL" nowrap width="25%"><font color="red">*</font>Effective From Date</td>
			<td class="CONTROL" width="25%">
				<div id="frDt1" style="display: none">
					<bean:write property="strEffectiveFrom" name="wardBean" filter="false"/>
				</div>
				<div id="frDt2">
					<date:date name="strEffectiveFrom" value="${wardBean.strEffectiveFrom}"></date:date>
				</div>
			</td>
			<td class="LABEL" nowrap width="25%">Effective To</td>
			<td class="CONTROL" width="25%"><bean:write name="wardBean" property="strEffectiveTo" filter="false"/></td>
		</tr>
		<tr>
			<td width="25%" class="LABEL" colspan="2">Remarks</td>
			<td width="25%" colspan="2" class="CONTROL">
				<textarea onkeyup="lTrim(this);" onblur="Trim(this);"  
				name="strRemark" cols="25" rows="2"><bean:write name="wardBean" property="strRemark"/></textarea>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="6"><font size="2" color="red">*</font>Mandatory Fields</td>
		</tr>
	</table>	
	<table CLASS ="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
	<tr> 
		<td align="center">
			<!-- <img src="../../hisglobal/images/btn-sv.png" style="cursor:pointer" onClick="return validate1('UPDATE');"/>
			<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" onClick ="cancel('LIST');"/> -->
			
			<br><a href="#" class="button" id="" onClick="return validate1('UPDATE');" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
	   </td>
	</tr>
	</table>	
	<input type="hidden" name="strPrivateWardType" value="${wardBean.strPrivateWardType}" >	
	<input type="hidden" name="chk" value="${wardBean.strChk}">
	<input type="hidden" name="strCtDate" value="${wardBean.strCtDate}">
	<input type="hidden" name="strEffectiveFromModi" value="${wardBean.strEffectiveFrom}">
	<input type="hidden" name="strEffectiveTo" value="${wardBean.strEffectiveTo}"/>
	<input type="hidden" name="hmode">
	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>