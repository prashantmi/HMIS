<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="date"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers" %>

<html>
<head><meta charset="utf-8" />
<title>Room Bed Master Add Page</title>
<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" 	type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" 	type="text/css">
<link href="../../hisglobal/css/popup.css" rel="stylesheet" type="text/css">
	
<script language="Javascript" src="../../hisglobal/js/validation.js"></script>
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/multirow.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>
<script language="JavaScript" src="../../hisglobal/js/popup.js"></script>
<script type="text/javascript">

function ajaxProperty(obj,searchIndex)
{
	if(document.getElementById("strCheckedHidden"+searchIndex).value=='')    
	{
		var hisValidator = new HISValidator("wardroombedBean"); 
		var retCheck;
		//hisValidator.addValidation("strWardCode","dontselect=0","Please select a value from ward Combo");
		hisValidator.addValidation("strRoomId","dontselect=0","Please select a value from Room Combo");
		retCheck = hisValidator.validate();
		hisValidator.clearAllValidations();
		if(retCheck)
		{
			var mode = "PROPERTY";
			var count=document.forms[0].strNoOfRow.value;
			var str="";
			var url="CNTWardRoomBedMst.cnt?hmode="+mode+"&roomNo="+document.forms[0].strRoomId.value+"&searchIndex="+searchIndex; 
			ajaxFunction(url,"4");		
			//document.getElementById("roomId").style.display="none";
			for(var i=1;i<=count;i++)
			{
				str='bedTypeId'+i;
			//	document.getElementById('bedTypeId'+i).style.display="none";
			//	document.getElementById('bedStatusId'+i).style.display="none";
			}		
			//display_popup_menu(obj,'divProperty'+searchIndex,'300','100'); 
		}
	}
	else
	{
		document.getElementById("divProperty"+searchIndex).style.display="block";   
	 }
}

function getAjaxNewBed()
{
	var objVal1=document.getElementById("id6");
	//obVal1.style.display="block";
	var mode ="NEWBEDENTRY";
	var room=document.forms[0].strRoomId.value;
	//alert(room);
   var url="CNTWardRoomBedMst.cnt?hmode="+mode+"&modName="+document.forms[0].strWardCode.value+"&roomNo="+room;
   ajaxFunction(url,"3");
}
function ajaxFunRoom(mode)
{ 
   var mode ="ROOM";
   var url="CNTWardRoomBedMst.cnt?hmode="+mode+"&modName="+document.forms[0].strWardCode.value;
   ajaxFunction(url,"1");
}
function getAjaxResponse(res,mode)
{
	var objVal1,objVal2,objVal3,objVal4;
	var a;
	if(mode=="1")
	{
		a=res;	
		var b=a.split('$');
		objVal1=document.getElementById("roomId");
		objVal2=document.getElementById("buildingblockId");
		objVal3=document.getElementById("id9");
	    objVal4=document.getElementById("id5");
	   	objVal1.innerHTML='<select name="strRoomId" class="comboNormal">'+ b[0]+'</select>';
		objVal2.innerHTML=b[1];
		objVal4.innerHTML=b[3]+' (in this ward)';
		objVal3.innerHTML=b[2]+' (already present in this ward)';
		document.forms[0].strMaxBed.value=b[3];
		document.forms[0].strAllotedBed.value=b[2];
		ajaxfunBed();
			if(b[3]==b[4])
			{
				document.getElementById("hlpid1").style.display="none";
			}
	}
	if(mode=="2")
	{
		var a=res;
		var b=a.split('^');
		var objVal1=document.getElementById("id4");
		var objVal2=document.getElementById("id5");
		objVal1.innerHTML=b[0];
		//document.getElementById("id3").innerHTML="none";
		view1();
		//objVal2.innerHTML=b[1]+"&nbsp;&nbsp;(max beds in a ward)";
		getAjaxNewBed();
	}
	if(mode=="3")
	{
		var objVal1=document.getElementById("id6");
		var objVa2=document.getElementById("id7");
		objVal1.innerHTML=res;
		//obVal1.style.display="none";
		//var count=document.forms[0].count.value;
		//for(var i=0;i<count;i++)
		//{objVal2.innerHTML='<a>'+count+1+'</a>'};
	}
	if(mode=="4")
	{
		var temp =res.split('^'); 
		var searchIndex = temp[1];
		var obj = document.getElementById("divProperty"+searchIndex);
		obj.innerHTML = temp[0];
		display_popup_menu(obj,'divProperty'+searchIndex,'300','100'); 
	}
}
function view1()
{
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("id3");
	o1.style.display="none";
	o2.style.display="block";
	o3.style.display="block";	
}
function view2()
{
	var o1=document.getElementById("id1");
	var o2=document.getElementById("id2");
	var o3=document.getElementById("id3");
	o1.style.display="block";
	o2.style.display="none";
	o3.style.display="none";
}
function ajaxfunBed()
{
	var mode="BED"
	var room=document.forms[0].strRoomId.value;
	var url="CNTWardRoomBedMst.cnt?hmode="+mode+"&modName2="+document.forms[0].strWardCode.value+"&roomNo="+room;
	ajaxFunction(url,"2");
}

function validate()
{
	var hisValidator = new HISValidator("wardroombedBean"); 
	var retCheck;
	var count=0;
	var len2;
	// hisValidator.addValidation("strWardCode","dontselect=0","Please select a value from ward Combo");
	hisValidator.addValidation("strRoomId","dontselect=0","Please select a value from Room Combo");
	
	retCheck = hisValidator.validate();
	hisValidator.clearAllValidations();
	
	if(retCheck)
	{
		len2=document.getElementsByName('bedName').length;
		if(len2==0)
		{
			alert("All Beds Already Mapped.No data to save");
			return;
		}
		/* for(var i=0;i<document.getElementsByName("bedFirstName").length;i++)
		{
			if(document.getElementsByName("bedFirstName")[i].value=='' || document.getElementsByName("bedNo")[i].value=='')
			{
				//do nothing
			}
			else
			{
				document.getElementsByName("bedName")[i].value=document.getElementsByName("bedFirstName")[i].value+""+document.getElementsByName("bedNo")[i].value;
			}
		}*/
		retCheck=validate1(); 
	}
	if(retCheck)
	{
			
			len2=document.getElementsByName('bedName').length;
			if(len2==0)
			{
				//hisValidator.addValidation("bedName", "req", "Bed Name  is a Mandatory Field" );
				retCheck = hisValidator.validate();
				hisValidator.clearAllValidations();
			}
			else 
			{
				for(var i=0;i<document.forms[0].bedName.length;i++)
				{
					if(document.forms[0].bedName[i].value=='')
					{
						retCheck=false;
					}
					else
					{
						retCheck=true;
						break;
					}
				}
				if(retCheck==false)
				{
					alert('Please Enter atleast one bed');
				}
			}
			
	 }
	if(retCheck)
	{
		hisValidator.addValidation("strEffectiveFrom", "req","Effective From is a Mandatory Field");
		hisValidator.addValidation("strEffectiveFrom", "date=${wardroombedBean.strCtDate}","Effective From Date should be a Valid Date");
		hisValidator.addValidation("strEffectiveFrom", "dtgtet=${wardroombedBean.strCtDate}","Effective From Date should be Greater than or Equal to Today's Date");
		retCheck=hisValidator.validate();
		hisValidator.clearAllValidations();
	}
	if(retCheck)
	{
		hisValidator.addValidation("strRemarks", "maxlen=50","strRemarks Cannot be More than 50 Characters");
		retCheck=hisValidator.validate();
		hisValidator.clearAllValidations();
	}
	if(retCheck)
	{
		document.forms[0].hmode.value = "SAVE";
		document.forms[0].submit();
	}
	else
	{
		return false;
	}
}
//validation to check entered data with previously mapped data 
function validate1()
{
	var flag=false;
	var len1,len2,val1,val2;
	if(document.forms[0].bedd == null)//Previously Mapped Beds
	{
		var len=document.getElementsByName('bedName').length;
		flag=validate2(len);
	}
	else
	{
		len1 = document.forms[0].bedd.length;
		len2=document.getElementsByName('bedName').length;
		if(!len2)
		{
				for( var i=0;i<len1;i++)
				{
					val1=document.forms[0].bedd[i].value;
					if(trim(val1.toUpperCase())==trim((document.forms[0].bedName.value).toUpperCase()))
					{
						alert('Data already exist for Bed No'+val1);
						return false;
						break;
					}
					else
					 continue;					
				}
				flag=true;
		}
		else
		{
			if(!len1)
			{
				val1=document.forms[0].bedd.value
				for(var j=0;j<len2;j++)
				{
					val2=document.getElementsByName("bedName")[j].value;
					if(trim(val1.toUpperCase())==trim(val2.toUpperCase()))
					{
						alert('Data already exist for Bed No'+val1);
						return false;
						break;
					}
					else
						continue;
				}
				flag=validate2(len2);
			}
			else
			{				
				for( var i=0;i<len1;i++)
				{				
					val1=document.forms[0].bedd[i].value;
					for(var j=0;j<len2;j++)
					{
						val2=document.getElementsByName("bedName")[j].value;
						
						if(trim(val1.toUpperCase())==trim(val2.toUpperCase()))
						{
							alert('Data already exist for Bed No'+val1);
							return false;
							break;
						}
						else
						continue;
					}
				}
				flag=validate2(len2);
			}
		}
	}
	return flag;
} 
// validation for duplication
function validate2(len2)
{
	var flag=false;
	try
	{
		for(var j=0;j<len2;j++)
		{
			val1=document.forms[0].bedName[j].value;
			for(var k=j+1;k<len2;k++)
			{
				var flag=validateThroughRegExp(document.forms[0].bedName[j],6);
				if(flag)
				{
					val2=document.forms[0].bedName[k].value;
					if(trim(val1.toUpperCase())==trim(val2.toUpperCase()) && val2!='')
					{
						if(trim(val1.toUpperCase())==trim(val2.toUpperCase()))
						{
							alert('You already entered this data in previous record');
							return false;
						}
					}
					else
			  			continue;
				}
				else
				{
					return;
				}
				
			}
		}
	}
	catch(e)
	{
		//alert(e);
		
	}
	return true;

//alert('Hello');
}
function clean()
{
	var len2=document.forms[0].bedName.length;
	for(var i=0;i<len2;i++)
	{
		document.forms[0].bedName[i].value="";
	}
	document.forms[0].bedName[0].focus();
}
function displayList(obj,index)
{
	//this below give the name of the invoking object
	obj.style.color='red';
	var totPage = 0;
	var i = 0;
	var divObj;
	totPage = document.forms[0].totPage.value;
	for(i=0;i<totPage;i++) 
	{
		var div=document.getElementById("aa" + (i + 1));
		if(i+1==index)
			div.style.color='red';
		else
			div.style.color='white';
		divObj = document.getElementById("hlpid" + (i + 1));
		if((divObj.style.display).toUpperCase() == 'BLOCK') {
			divObj.style.display='none';
		}
	}	
	divObj = document.getElementById("hlpid" + index);
	divObj.style.display='block';
}
function okProperty(searchIndex)
{
	var objProperty = "";
	var propertyId = document.getElementsByName("strPropertyId"+searchIndex); 
	//document.getElementById("roomId").style.display="block";
	var count=document.forms[0].strNoOfRow.value;
	var length = propertyId.length;
	if(length>1)
	{
		for(i=0;i<length;i++)
		{
		  	if(propertyId[i].checked)
	  	 objProperty+= propertyId[i].value+'^';  
		}
	}
	if(length==1)
	{		
		if(propertyId[0].checked)  
		objProperty= propertyId[0].value;  
	}
	
	var obj = document.getElementById("strCheckedHidden"+searchIndex);
	obj.value=objProperty; 
	//document.getElementById("divProperty"+searchIndex).style.display="none";
	styledPopupClose("divProperty"+searchIndex);  
	for(var i=1;i<=count;i++)
	{
			str="bedTypeId"+i;
			//document.getElementById(str).style.display="block";
			str="bedStatusId"+i;
			//document.getElementById(str).style.display="block";
	}				
}

function cancelPopUP(searchIndex)
{
	var propertyId = document.getElementsByName("strPropertyId"+searchIndex);  
	//document.getElementById("roomId").style.display="block";
	var count=document.forms[0].strNoOfRow.value;
	var length = propertyId.length;
	if(length>1)
	{
		for(i=0;i<length;i++)
		{
	   		propertyId[i].checked = false;
		}
	}
	if(length==1)
	{
		propertyId[0].checked = false;
	}
	document.getElementById("strCheckedHidden"+searchIndex).value=""; 
		//document.getElementById("divProperty"+0).style.display="none";
		styledPopupClose("divProperty"+0); 
		for(var i=1;i<=count;i++)
		{
			str="bedTypeId"+i;
		//	document.getElementById(str).style.display="block";
			str="bedStatusId"+i;
		//	document.getElementById(str).style.display="block";
		}
}

function closeProperties(searchIndex)
{
	styledPopupClose("divProperty"+searchIndex); 
	//document.getElementById("divProperty"+searchIndex).style.display="none"; 
}
function roomChange(obj)
{
	if(obj.value==0)
	{
		objVal1=document.getElementById("id9");
		objVal2=document.getElementById("id5");
		
		objVal4=document.getElementById("id4");
		objVal5=document.getElementById("id6");
		
		objVal1.innerHTML="";
		objVal2.innerHTML="";
		
		objVal4.innerHTML="";
		objVal5.innerHTML="";	
	}
	else
	{
		var roomVal=document.forms[0].strRoomId.value;
		var id9=roomVal.split('^')[1];
		var id5=roomVal.split('^')[2];
		objVal1=document.getElementById("id9");
		objVal2=document.getElementById("id5");
		objVal1.innerHTML=id9+' (in this room)';
		objVal2.innerHTML=id5+' (already present in this room)';
		document.forms[0].strMaxBed.value=id9;
		document.forms[0].strAllotedBed.value=id5;
		ajaxfunBed();
	}
}

function changeValues()
{
	var bedPrefix=document.forms[0].preFixBedName.value;
	var startNo=document.forms[0].startBedNo.value;
	var x = document.getElementsByName("bedName"); 
	var i;
	for (i = 0; i < x.length; i++) 
	{
		x[i].value = bedPrefix+" "+startNo;
		startNo++;
	} 
}
</script>

</head>
<body onLoad="">
<html:form action="/masters/CNTWardRoomBedMst">
	<div class="errMsg"><bean:write name="wardroombedBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="wardroombedBean" property="strWarning"/></div>
	<div class="normalMsg" id="normalMsg"><bean:write name="wardroombedBean" property="strMsg"/></div>
	<tag:tab tabLabel="Add Room Bed" selectedTab="FIRST" align="center" width="TABLEWIDTH"></tag:tab>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">	
		<tr class="HEADER">
			<td colspan="2">Room Bed Master&gt;&gt;Add</td>
		</tr>
		<!--<tr>
			<td class="LABEL" width="45%">
			<font color="red">*</font>Ward Name</td>
			<td class="CONTROL" width="45%">
			<select name="strWardCode" class='comboNormal' onChange="ajaxFunRoom('ROOM')">
				<bean:write name="wardroombedBean" property="strWardNameCombo" filter="false"/>
			</select>
			</td>
		</tr>-->
		<tr>
			<td class="LABEL">
			<div align="right"><font color="red">*</font>Room Desc.</div>
			</td>
			<td class="CONTROL">
			<div align="left" id="roomId"><select name="strRoomId" class='comboNormal' onChange="roomChange(this)">
			<bean:write name="wardroombedBean" property="strRoomCombo" filter="false"/>
			</select></div>
			</td>
		</tr>
           <tr>
			<td class="LABEL" nowrap>No. Of Beds in Room</td>
			<td class="CONTROL">
			<div id="id5">
           	</div>
			</td>
		</tr>
		<tr>
			<td class="LABEL" nowrap>Total No. Of Beds</td>
			<td class="CONTROL">
			<div id="id9">
           	</div>
			</td>
		</tr>		
		 <!--<tr>
			<td class="LABEL" nowrap>Building/Block</td>
			<td class="CONTROL">
			<div id="buildingblockId">
            </div>
			</td>
		</tr>-->
		
	</table>
	<table class="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td class="LABEL">
				<div id="id1" align="left" style="display:  block;;">
					<img src="../../hisglobal/images/plus.gif"   style="cursor: pointer;position: absolute;"
									onClick="view1();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Details of beds in a ward</div>
				<div id="id2" style="display: none;" align="left">
					<img src="../../hisglobal/images/minus.gif"   style="cursor: pointer;position: absolute;"
						onClick="view2();"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Details of beds in a ward</div>
			</td>
		</tr>
	</table>
	<div id="id3" style="display: none">
	<div id="id4"></div>
	</div>
	<div id="id7"></div>
	<div id="id6"></div>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">		
		<tr>
			<td class="LABEL" nowrap><font color="red">*</font>Effective From</td>
			<td class="CONTROL"><date:date name="strEffectiveFrom" value="${wardroombedBean.strEffectiveFrom}"></date:date></td>
		</tr>		
		<tr>
			<td class="LABEL" width=45%>
			<div align="right">Remarks</div></td>
			<td class="LABEL">
			<div align="left"><textarea onkeyup="lTrim(this);" onblur="Trim(this);" name="strRemarks" rows="2"></textarea>
			</div>
			</td>
		</tr>
		<tr class="FOOTER">
			<td colspan="1"><div align='left'><font size="1" color="red">^</font>Enter Prefix Only-Will be appied to all Bed Names Fields in all pages</div></td>
			<td colspan="1"><div align="right"><font size="2" color="red">*</font> Mandatory Fields</div></td>
		</tr>
	</table>
	<table CLASS="TABLEWIDTH" align="center" cellspacing="1px" cellpadding="1px">
		<tr>
			<td align="center">
			<!--<img name="save" src="../../hisglobal/images/btn-sv.png" 
			style="cursor:pointer" onClick="return validate();" title="Save the Data"/> 
			<img src="../../hisglobal/images/btn-clr.png"  style="cursor:pointer" 
			onClick="document.forms[0].reset();" title="Reset All Values">
			<img  src="../../hisglobal/images/btn-ccl.png" style="cursor:pointer" 
			onClick="cancel('LIST');" title="Go to list page"/>
			-->
			<br><a href="#" class="button" id="" onClick="return validate();" ><span class="save">Save</span></a>
							<a href="#" class="button"	onClick="document.forms[0].reset();"><span class="clear">Clear</span></a> 
							<a href="#" class="button" onclick="cancel('LIST');"><span class="cancel">Cancel</span></a>
			</td>
		</tr>
	</table>
	<input type="hidden" name="hmode" value=""/>
	<input type="hidden" name="strMaxBed" value=""/>
	<input type="hidden" name="strAllotedBed" value=""/>
	<input type="hidden" name="strWardCode" value="999"/>
	<div id='checkIdHidden'></div>	
<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>
</body>
</html>