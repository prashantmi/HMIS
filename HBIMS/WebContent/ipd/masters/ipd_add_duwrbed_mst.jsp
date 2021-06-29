
<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head>
<meta charset=utf-8>
<title>DUWR Bed Master Add Page</title>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet"
	type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet"
	type="text/css">
<style type='text/css'>
	.comboLarge {
	font-family: Verdana, Arial, Helvetica, sans-serif;
	font-size: 13px;
	font-style: normal;
	font-weight: normal;
	color: #000000;
	height: 20px;
	width: 280px;
}
	
</style>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="JavaScript" src="../../hisglobal/js/util.js"></script>
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script type="text/javascript">
function ajaxFunRoom()
{ 
   var mode ="ROOMVALUE";
   var wardName=document.forms[0].strWardName.value;

   var url="CNTDUWRBedMst.cnt?hmode=ROOMVALUE&wardName="+wardName;
   ajaxFunction(url,"1");
}
function ajaxFunLBed()

{ 
   var mode ="LBEDVALUE";
   var ward=document.forms[0].strWardName.value;
   var room=document.forms[0].strRoomName.value;
   var url="CNTDUWRBedMst.cnt?hmode=LBEDVALUE&deptunitName="+document.forms[0].strDeptUnitName.value+"&wardName="+ward+"&roomName="+room;
   ajaxFunction(url,"2");
}
function ajaxFunRBed()
{ 
  var mode ="RBEDVALUE";
  var ward=document.forms[0].strWardName.value;
  var room=document.forms[0].strRoomName.value;
  var url="CNTDUWRBedMst.cnt?hmode=RBEDVALUE&deptunitName="+document.forms[0].strDeptUnitName.value+"&wardName="+ward+"&roomName="+room;
  ajaxFunction(url,"3");
}

function getAjaxResponse(res,mode){
				
				var objVal;
				if(mode=="1"){
					objVal= document.getElementById("roomid");
					objVal.innerHTML = "<select name ='strRoomName' class='comboNormal' onChange ='ajaxFunRBed(),ajaxFunLBed(),getRoomType();'>" + res + "</select>";
				}
				 if(mode=="2"){
			 		var obj1 = document.getElementById("lBedId");
			        obj1.innerHTML = "<select name='strLBed' size='8' multiple style='width: 280px'>" + res + "</select>";
			 	    ajaxFunRBed();
			 	    
				}
				else if(mode=="3"){
				
					var obj2= document.getElementById("rBedId");
					obj2.innerHTML = "<select name='strRBed' size='8' multiple style='width: 280px'> " + res + " </select>";
					}
}


function validate1(){	
	
	    var hisValidator = new HISValidator("duwrbedBean"); 
		hisValidator.addValidation("strDeptUnitName","dontselect=0", "Please select a value from DeptUnit Name Combo"); 
		hisValidator.addValidation("strWardName","dontselect=0","Please select a value from Ward Name Combo");
		hisValidator.addValidation("strRoomName","dontselect=0","Please select a value from Room Name Combo");
		hisValidator.addValidation("strRemarks", "maxlen=50","Remarks Cannot be More than 50 Characters");
		hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
	    hisValidator.addValidation("strEffectiveFrom", "dtgtet=${duwrbedBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
	    hisValidator.addValidation("strRBed","dontselect=0","No Bed Data Available");
	    retVal = hisValidator.validate(); 
		if(retVal){
				var count=selectListRecords("strRBed");		
				//alert("count->"+count);
				document.forms[0].hmode.value ="SAVE";
				document.forms[0].submit();
			}else{
				return false;
			}
}
function getWardType()
{
	var a;
	var b;
	var obj;
	a=document.forms[0].strWardName.value;
	b=a.split('^');
	obj=document.getElementById("wardTypeId");
	obj.innerHTML=b[1];
	
}
function getRoomType()
{
	
	var a;
	var b;
	var obj;
	a=document.forms[0].strRoomName.value;
	b=a.split('^');
	obj=document.getElementById("roomTypeId");
	obj.innerHTML=b[1];
	
	
}
function right()
{
	var ob1=document.forms[0].strLBed.value;
	var ob=document.getElementById("lBedId");
	shiftToRight("strLBed","strRBed",1);
}

function rightbed(){
//var myArray=new Array();

		var obj=document.forms[0].strRBed.value;
		if(obj.length>9)
		{
			alert("Added Bed can not be shifted");
		}
		else
		{
			shiftToLeft("strLBed","strRBed",1);

		}
	/*	for(var i=0;i<document.forms[0].strRBed.length;i++)
		{
			//alert("rbeed:"+document.forms[0].strRBed.length);
			if(document.forms[0].strRBed.length
			alert("Added Bed can not be shifted");
			var z= document.forms[0].strRBed[document.forms[0].strRBed.selectedIndex].value;
			//alert("z:"+z);
			if(!isNaN(z))
			{
				shiftToLeft("strLBed","strRBed",1);
			}
		}*/
		
}
function fun(){

	objVal= document.getElementById("roomid");
	objVal.innerHTML = "<select name ='strRoomName' class='comboNormal'> </select>";
	var obj1 = document.getElementById("lBedId");
	obj1.innerHTML = "<select name='strLBed' size='8' multiple style='width: 280px'> </select>";
	var obj2= document.getElementById("rBedId");
	obj2.innerHTML = "<select name='strRBed' size='8' multiple style='width: 280px'> </select>";
}

function setComboCss()
{
	if(document.forms[0].strDeptUnitName.value==0)
	{
		document.forms[0].strDeptUnitName.className="comboNormal";
	}
	else
	{
		document.forms[0].strDeptUnitName.className="comboLarge";
	}
}
function clear1()
{
document.forms[0].strLBed.options.length=0;
document.forms[0].strRBed.options.length = 0;
}
</script>
</head>
<body onLoad="document.forms[0].strDeptUnitName.focus();">
<html:form action="/masters/CNTDUWRBedMst.cnt" method="post">

	<div class="errMsg"><bean:write name="duwrbedBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="duwrbedBean" property="strWarning"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="duwrbedBean" property="strMsg"/></div>

	<tag:tab tabLabel="Add DUWR Bed" selectedTab="FIRST" align="center"
		width="TABLEWIDTH"></tag:tab>
		
		<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
  <tr class="HEADER"> 
    <td colspan="5">DUWR Bed Master &gt;&gt; Add</td>
  </tr>
  <tr> 
    <td width="50%" colspan="2" class="LABEL"><font color="red">*</font> Dept/Unit </td> 
   <td width="50%"  colspan="3" class="CONTROL"><select name="strDeptUnitName" class='comboNormal' onchange="setComboCss();">
 		 <bean:write name="duwrbedBean" property="deptUnitAdd" filter="false"/>
   </select> </td>   
  </tr> 
  <tr> 
    <td width="25%"  class="LABEL"><font color="red">*</font>Ward Name</td>
    <td width="25%"  class="CONTROL">
    <select name="strWardName" class='comboNormal' onChange="ajaxFunRoom(),getWardType();" >
    <bean:write name="duwrbedBean" property="wardNameAdd"  filter="false"/></select>
    </td>
    <td noWrap class="LABEL" width="25%" >Ward Type:- </td> <td colspan="2" class="CONTROL"><div id="wardTypeId"></div></td>
     
  </tr>
  <tr> 
    <td width="25%"  class="LABEL"><font color="red">*</font>Room Name</td>
    <td width="25%"  class="CONTROL"><div id="roomid">
    <select name="strRoomName" class='comboNormal' onChange ="ajaxFunLBed(),getRoomType();">
    <option value="0">Select Value</option>
    </select>
     </div></td>
     <td noWrap width="25%" colspan="1" class="LABEL">Room Type:-</td><td colspan="2" class="CONTROL" width="25%"><div id="roomTypeId"></div></td>
     </tr>
     <tr><td width="100%" colspan="5" class="LABEL"><center><font color="red">*</font>Bed Name</center></td>
 			 <tr>
  			 <td class="CONTROL" colspan="2">
			<div id="lBedId" align="right"><select name="strLBed" size="8" 
				 multiple style="width: 280px" id="idLBed"></select></div>
			</td>
			<td width="6%" class="CONTROL">
			
			<center><img src="../../hisglobal/images/forward3.gif" width="35" style="cursor:hand;pointer:hand"
				height="31" 
				onclick ="right();"></center>
				<br/>
				<center><img src="../../hisglobal/images/back3.gif" width="35"  style="cursor:hand;pointer:hand"
				height="31" 
				onclick="rightbed();"/></center>
			</td>
			
			<td colspan="2" class="CONTROL" >
			<div id="rBedId" align="left"><select name="strRBed" size="8" multiple style="width: 280px" id="idRBed">
			</select></div>
			</td>
						
			
		</tr> 
		<tr>
		<td class="LABEL" colspan="2"><font color="red">*</font>Effective From</td>
			<td class="CONTROL" colspan="3"><date:date name="strEffectiveFrom"
				value="${duwrbedBean.strCtDate}"></date:date></td>
				</tr>
		
  <tr>
  <td width="50%" colspan="2"  class="LABEL">Remarks</td>
    <td width="50%" colspan="3" class="CONTROL"> <textarea onkeyup="lTrim(this);" onblur="Trim(this);" rows="2" cols="20" name="strRemarks"></textarea></td> 
  </tr>
   <tr class="FOOTER"> 
    <td colspan="5"><font size="2" color="red">*</font> Mandatory Fields</td>
  </tr>
</table>
		
		<table border="0" class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>

			<td align="center"><!-- <img
				src="../../hisglobal/images/btn-sv.png" style="cursor:pointer"
				onClick=" return validate1();" />
				<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" 
				onClick="clear1();document.forms[0].reset();">
				<img src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer"
				onClick="cancel('LIST');" /> -->
				
				
				<br><a href="#" class="button" id="" onClick="return validate1();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
				</td>
		</tr>
	</table>

	<input type="hidden" name="hmode" />
	<input type="hidden" name="p1">
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html> 
  