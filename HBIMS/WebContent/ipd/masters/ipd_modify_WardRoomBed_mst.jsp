
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>
<html><meta charset="utf-8" />
<head>
<title>Room Bed Master Modify Page</title>
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
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script type="text/javascript">
function validate1()
{
				var hisValidator = new HISValidator("wardroombedBean"); 
				hisValidator.addValidation("strBedName2", "req", "Bed Name  is a Mandatory Field" ); 
				hisValidator.addValidation("strBedTypeName","dontselect=0","Please select a value from Bed Type Combo");
				hisValidator.addValidation("strBedStatusName","dontselect=0","Please select a value from Bed status Combo");
				//hisValidator.addValidation("strEffectiveFrom", "date=${wardroombedBean.strCtDate}","Effective From Date should be a Valid Date");
				//hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardroombedBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
				hisValidator.addValidation("strRemarks", "maxlen=50", "Remarks should have less than or equal to 50 Characters" );
				var retCheck = hisValidator.validate();
				if(retCheck)
				{
					if(validateThroughRegExp(document.forms[0].strBedName2,6))
					{
						document.forms[0].hmode.value = "UPDATE";
						document.forms[0].submit();
					}
					else
					{
						return;
					}
				}
				else return false;
}

function openPopup(obj)
{
	display_popup_menu(obj,'divProperty','300','100'); 
}

function okProperty()
{
	var propertyId = document.getElementsByName("strPropertyId"); 
	var length = propertyId.length;
	var objProperty = "";
	if(length>1){
		for(i=0;i<length;i++)
		{
	   		if(propertyId[i].checked)
	  	 	objProperty+= propertyId[i].value+'^';
		}
	}
	if(length=='1')
	{
		if(propertyId[0].checked)
		objProperty= propertyId[0].value+'^';
	}
	document.forms[0].strModifyChkProperties.value = objProperty;
	//document.getElementById("divProperty").style.display="none";
	hide_popup_menu("divProperty");
}

function cancelPopUP()
{
	var propertyId = document.getElementsByName("strPropertyId");  
	var length = propertyId.length;
	if(length>1){
		for(i=0;i<length;i++)
		{
		   	propertyId[i].checked = false;
		}
	}
	if(length=='1')
	{
		propertyId[0].checked = false;
	}
	
	document.forms[0].strModifyChkProperties.value = "";
	//document.getElementById("divProperty").style.display="none"; 
	hide_popup_menu("divProperty");
}
function closeProperties(){
hide_popup_menu("divProperty");
//document.getElementById("divProperty").style.display="none";
}
</script>
</head>
<body onload="document.forms[0].strBedName2.focus();">
<html:form name="wardroombedBean" action="/masters/CNTWardRoomBedMst" type="ipd.masters.vo.WardRoomBedMstVO">	
<div class="errMsg"><bean:write name="wardroombedBean" property="strErr"/></div>	
<tag:tab tabLabel="Modify Room Bed" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	
<table class="TABLEWIDTH" align="center" cellspacing="1px">
  <tr class="HEADER"> 
    <td colspan="2" width="100%">Room Bed Master&gt;&gt; Modify</td>
  </tr>
  <!--<tr> 
    <td width="50%" class="LABEL">Ward Name</td>
    <td width="50%" class="CONTROL">
    	<bean:write name="wardroombedBean" property="strWardName" filter="false"/>
    </td>
  </tr> -->
  <tr> 
    <td class="LABEL" width="50%">Room Desc.</td>
    <td class="CONTROL" width="50%">
	<bean:write property="strRoomName" name="wardroombedBean" filter="false"/>
	</td>
  </tr>
  <tr>
	   	<td class="LABEL" width="50%"><font color="red">*</font>Bed Name</td>
		<td class="CONTROL" width="50%">
		<input onkeyup="lTrim(this);" onblur="Trim(this);" type="text" name="strBedName2" style="width: 120px;"
		 	value="${wardroombedBean.strBedName}"  maxlength="10" onkeypress='return validateData(event,9);'>
		  <img name="Property" src="../../hisglobal/images/property.png" 
		  onClick="openPopup(this);" style="position: absolute;cursor: pointer;"/></td>
		</tr>
</table>
<div id='divProperty' class = 'popup' align = 'center' style="display:  none;;">
	<bean:write name="wardroombedBean" property="strmodProperties" filter="false"/></div>
<table class="TABLEWIDTH" align="center" cellspacing="1px">
    <tr>
	   	<td class="LABEL" width="50%"><font color="red">*</font>Bed Type</td>
		<td class="CONTROL" width="50%"><select name="strBedTypeName"  class='comboNormal' >
		<bean:write name="wardroombedBean" property="strBedTypeModi" filter="false"/>
		</select>
		</td>
	</tr>
	<tr>
	   	<td class="LABEL" width="50%"><font color="red">*</font>Bed Status</td>
		<td class="CONTROL" width="50%"><select name="strBedStatusName"  class='comboNormal'>
		 <bean:write property="strBedStatusModi" name="wardroombedBean" filter="false"/>
		 </select>  
	</tr>  
		<tr>
		<td class="LABEL" width="50%">Is Sharable</td>
		<td class="CONTROL" width="50%">
		 <html:checkbox name ="wardroombedBean" property ="strIsSharable" disabled="true" value="${wardroombedBean.strIsSharable[0]}" /></td>
		</tr>     
    <tr>
    	<td class ="LABEL" width ="50%">Effective From Date</td>
    	<td class ="CONTROL" width="50%"><bean:write name="wardroombedBean" property="strEffectiveFrom" /></td>
    </tr>
   	<tr>
 	    	<td class="LABEL" width="50%">Remarks</td>
			<td class="CONTROL" width="50%">
			<textarea  onkeyup="lTrim(this);" onblur="Trim(this);" name="strRemarks" cols="25" 
			rows="2"><bean:write name="wardroombedBean" property="strRemarks" filter="false"/>
			</textarea></td>
	</tr>    
    <tr>
    <td width="50%" class ="LABEL"><font color="red">*</font>Record Status</td>
    <td width="50%" class ="CONTROL">
  		<html:radio name="wardroombedBean" property="strValid" value="1">Active</html:radio>
    	<html:radio name="wardroombedBean" property="strValid" value="2">InActive</html:radio>
    </td>
    </tr>    
   <tr class ="FOOTER" >
 	<td colspan="2"><font size="2" color="red">*</font>Mandatory Fields</tr>
</table>
<table CLASS ="TABLEWIDTH" align="center" cellspacing="1px">
	      <tr> 
		   <td align="center">
			 <!--   <img name="save" src="../../hisglobal/images/btn-sv.png" onClick="return validate1(); " style="cursor: pointer;"/>
	           <img  src="../../hisglobal/images/btn-ccl.png" onClick="cancel('LIST');" style="cursor: pointer;"/>  -->
	           
	           <br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
			<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a> 
	   		</td>
	      </tr>
</table>
        <input type="hidden" name="chk" value="${wardroombedBean.strChk} }">
	   	<input type="hidden" name="hmode">
	   	<input type="hidden" name="strEffectiveFrom" value ="${wardroombedBean.strDate}" />
	   	<input type="hidden" name="strModifyChkProperties">
<cmbPers:cmbPers/>	
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>