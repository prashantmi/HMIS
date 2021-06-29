<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
<%@page import="usermgmt.masters.umgmtSessionMgtConfigUtil"%>


<jsp:useBean id="hospitalMst" class="usermgmt.masters.HospitalMstBean" 	scope="request" >
<jsp:setProperty name="hospitalMst" property="*" />
</jsp:useBean>
<%
	 ///For getting the password configuration Strength
	 umgmtSessionMgtConfigUtil myUtil = new umgmtSessionMgtConfigUtil();
	 String hoscode = (String) session.getAttribute("HOSPITAL_CODE");
	 //List lst = myUtil.getPreviousValues();
	 List lst = myUtil.getPreviousValues(hoscode);
	 String passwordStrengthMsg="";
	
	 if(lst.size()>0)
	 {
	 if(lst.get(7).equals("2"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet and one number";
	 	else
	 if(lst.get(7).equals("3"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet,one number and one Special Character";
	 }
	 
	 
	 %>
<%

String message = request.getParameter("message");
if(message==null)
	message = "";

String hcode = (String) session.getAttribute("HOSPITAL_CODE");
hospitalMst.setHospitalcode(hcode);
%>

<HTML>
	<HEAD>
		<TITLE>Session Configuation</TITLE>
<META http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<META Http-Equiv="Cache-Control" Content="no-cache" />
<META Http-Equiv="Cache-Control" Content="no-store" />
<META Http-Equiv="Pragma" Content="no-cache" />
<META Http-Equiv="Expires" Content="0" />

<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
	response.setHeader("Cache-Control", "no-store");
%>
		
		
		<link href="../../css/ColorNew.css" 	 rel="stylesheet" type="text/css">
		<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="../../css/master.css" 	 rel="stylesheet" type="text/css">
		<script language="JavaScript" src="../js/sha.js"></script>
		<script>
	window.history.forward() ;
</script>
		<script language="javascript">

	function checkInput(varObj,validationIndex,length,e)
		{
		
		//onkeypress = "checkInput(this,which str to be used,length applicable,event);"
			var returnValue = true;
			var applicableStr = "";
			var key;
			if (window.event)
				key = window.event.keyCode;
				
			else if (e)
				key = e.which;
			
		
			//control keys
			
			if ((key==null) || (key==0) || (key==8) ||
				(key==9)  || (key==27) ) // Removed key == 47 By Pratichi Maheshwari
				return true; 
				
			if(key==13)
			{
			return false;///enter not allowed
			}
			var keychar = String.fromCharCode(key);
			
			//Validating Length
			if(varObj.value.length==length)
				returnValue = false;
				
				
			//Specifying the applicable character set
			if(validationIndex==1)
				applicableStr = "0123456789";
			if(validationIndex==2)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			if(validationIndex==3)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789_";
			if(validationIndex==4)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_";	
			if(validationIndex==5)
				applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ_ ";
			if(validationIndex==6)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789./:_?";
			if(validationIndex==7)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789 ";  //used for a-z,A-Z,0-9,space
			if(validationIndex==8)// Added By Pratichi Maheshwari
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789/ ";
			if(validationIndex==9)// Added By Ankur
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			if(validationIndex==10)
				applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@._";	// Added By Ankur for Email Id 
			if(validationIndex==11)
				applicableStr = "0123456789,";
			if(validationIndex==12)
			applicableStr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789@.,/ ";
				
			var aa = applicableStr.indexOf(keychar);
			
			if(applicableStr.indexOf(keychar)== -1)
				returnValue = false;
				
							
				return returnValue; 
		
		} 
				
function  validateAndSubmit(e)
{
  if(e.type=="click" || e.keyCode==13)
     {	
     
     		var saltedPass =  document.forms[0].password.value + document.forms[0].userId.value;
			var hashObj = new jsSHA(saltedPass, "ASCII");
			try 
			{
				hashedPass = hashObj.getHash("SHA-1", "HEX");	
			}
			catch(e)
			{
				isSuccess = false;	
			}
		
			 var o=document.getElementsByName("password");
			 var passLen=(o[o.length-1].value).length;
	
	  if(passLen>=6)
	  {		
     	document.forms[0].password.value = hashedPass
      }
     
     var weekDayFrom=document.forms[0].weekHourFrom.value+document.forms[0].weekMinFrom.value;
     var weekDayTo=document.forms[0].weekHourTo.value+document.forms[0].weekMinTo.value;
     
     var satDayFrom=document.forms[0].satHourFrom.value+document.forms[0].satMinFrom.value;
     var satDayTo=document.forms[0].satHourTo.value+document.forms[0].satMinTo.value;
     
     var lunchDayFrom=document.forms[0].lunchHourFrom.value+document.forms[0].lunchMinFrom.value;
     var lunchDayTo=document.forms[0].lunchHourTo.value+document.forms[0].lunchMinTo.value;
     
 	 
     if(document.getElementsByName("hospitalName")[0].value=="")
     	alert("Please Enter the Hospital Name");
     	 /*else
       if(document.getElementsByName("strNoOfLicences")[0].value==""||document.getElementsByName("strNoOfLicences")[0].value==null)
		{alert("Please Enter the User Licenses Allowed");}*/
	 else if(document.getElementsByName("state")[0].value=="0")
     	alert("Please Select State");
     else if(document.getElementsByName("district")[0].value=="0")
     	alert("Please Select District");
     else if(weekDayFrom > weekDayTo)
       alert("WeekDay From Timings Cannot be Greater than the To Timings");
     else if(satDayFrom > satDayTo)
       alert("Saturday From Timings Cannot be Greater than the To Timings");
     else if(lunchDayFrom > lunchDayTo)
       alert("Lunch From Timings Cannot be Greater than the To Timings");
     else if(passLen>=6)
       	{
       		//alert("Inside");     
       		submitPage();
 		}
 	else
 	{
 		alert("Please Enter Password");
 	} 		
 	   
	}
}			
				
		
		
		
		function submitPage()
		{
	
			//alert("Inside Submit");
			document.forms[0].hmode.value="SAVE";
			document.forms[0].submit();
			
		}
		
		
function resetForm(event)
{
	if(event.type=="click" || event.keyCode==13)
	{

		document.forms[0].hospitalName.value="";
		document.forms[0].hospitalShortName.value=""; 
		document.forms[0].address1.value="";
		document.forms[0].address2.value="";
		
		
		document.forms[0].city.value="";
		document.forms[0].state.value="0"; 
		document.forms[0].phoneNo.value="";
		document.forms[0].faxNo.value="";
		
		document.forms[0].district.value="0"; 
		document.forms[0].pinCode.value="";		
		
		document.forms[0].emailId.value="";
		document.forms[0].hospitalShortName.value=""; 
		document.forms[0].contactPerson.value="";
		document.forms[0].hospitalType.value="0";
		
		document.forms[0].busRouteNo.value="";
		document.forms[0].hospitalShortName.value=""; 
		document.forms[0].bedCapacity.value="";
		
		document.forms[0].weekHourFrom.value="";		
		document.forms[0].weekMinFrom.value="";
		document.forms[0].weekHourTo.value=""; 
		document.forms[0].weekMinTo.value="";
		
		document.forms[0].satHourFrom.value="";
		document.forms[0].satMinFrom.value="";
		document.forms[0].satHourTo.value=""; 
		document.forms[0].satMinTo.value="";
		
		document.forms[0].lunchHourFrom.value="";
		document.forms[0].lunchMinFrom.value="";
		document.forms[0].lunchHourTo.value="";
		document.forms[0].lunchMinTo.value="";
		//document.forms[0].strNoOfLicences.value="";
		
	}
}

function cancelForm(e)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			   document.forms[0].action="../../startup/content.jsp";
			   document.forms[0].submit();
			
		}		
	}		
		
function validateHr(these)
 {

if(these.value.length!=2 && these.value!="")
 		{
		alert("Enter the Hours in 00 format");
		document.getElementsByName(these.name)[0].focus();
		these.value="";
		}
if(these.value!="" && these.value!="00")
  {

 if(these.value < 24 && these.value >0 )
   {
        return true;
    
    } 
  else
   {
      alert("Hours Should be Less than 24");
      these.value="";
      return false;
     }
   }
 }
 /*function validateLic(these)
 {
 if(document.getElementsByName("strNoOfLicences")[0].value=="")
		alert("Please Enter the User Licenses Allowed");
 }*/
 
 function validateMin(these)
 {

if(these.value.length!=2 && these.value!="")
		{
		alert("Enter the Minutes in 00 format");
		document.getElementsByName(these.name)[0].focus();
		these.value="";
		}
if(these.value!="" && these.value!="00")
  {
  
 if(these.value < 60 && these.value >0 )
     {
     return true;
      }
   else
   	 {
    alert("Minutes Should be Less than 60");
    these.value="";
    return false;
     }
   } 
 }		
 
 
  function getDistrict(these)
 {
 	 	//alert("jgkfdjg");
 			document.forms[0].hmode.value="CMB";
			document.forms[0].submit();
 }
 function minCheck()
	{
		var o=document.getElementsByName("password");
		var passLen=(o[o.length-1].value).length;
		if(passLen<6)
		{
			alert('Your password is less than 6 digit');
			//document.forms[0].userPwd.value="";
			//document.forms[0].userPwd.focus();
			document.form1.password.value="";
			document.form1.password.focus();		
		}
		
		
	}
 
 
		</script>
		
		
	

		
	</HEAD>
	<!-- 
	
	<BODY background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="document.forms[0].hospitalName.focus();"> -->
	<BODY  width="100%" topmargin="0" onload="document.forms[0].hospitalName.focus();">
		
<form name="form1" method="post" action="HospitalMaster_cnt.jsp">
		
		<table width="97%" align="center">		
		
		<tr>
		<td  width="97%" class="ShadedSubTitleTagImage" colspan="8">Hospital Master</td>
		</tr>
		</table>
		<table   class="formbg" width="97%" border="0" cellspacing="1" cellpadding="0" align="center">
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Hospital Name<font color='red'>*</font></td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="hospitalName" maxlength="100"   style="width: 300px;" onkeypress="return checkInput(this,2,100,event);"  value="<%=hospitalMst.getHospitalName()%>">
		</td>
		</tr>
		
		<tr>
		<td  width='50%' class="LABEL" colspan="4">Hospital Short Name</td>
		<td  width='50%' class="CONTROL" 	colspan="4">
		<input type="text" name="hospitalShortName"  maxlength="10"  onkeypress="return checkInput(this,2,100,event);"  value="<%=hospitalMst.getHospitalShortName()%>">
		</td>

		</tr>
		
		<tr>
		 <td  width="50%" class="LABEL" colspan="4">User Id</td>
		 <td  width='50%' class="CONTROL"     colspan="4">
		 <input type="text" name="userId"  onkeypress = "return checkInput(this,3,20,event);" value="<%=hospitalMst.getUserId()%>"></td></tr>   
		<tr>
	    <td  width="50%"  class="LABEL" colspan="4">Password</td>
	   	<td  width="50%" class="CONTROL"  colspan="4">
	    <input type="password" name="password"    maxlength="10"  onchange="minCheck();" value=""> <font color='red'>(6 to 10 characters)<br>
		     The Password is case sensitive.<br> <%=passwordStrengthMsg %>
		     
		     
		   </td>
	      </tr>
		
		<tr>
		<td  width='50%' class="LABEL" colspan="4">Address 1</td>
		<td  width='50%' class="CONTROL" 	colspan="4">
		<textarea rows="1" cols="17" name="address1" onkeypress="return checkInput(this,12,40,event);" ><%=hospitalMst.getAddress1()%></textarea>
		</td>
		</tr>
		
		
		<tr>
		<td  width='50%' class="LABEL" colspan="4">Address 2</td>
		<td  width='50%' class="CONTROL" 	colspan="4">
		<textarea rows="1" cols="17" name="address2" onkeypress="return checkInput(this,12,40,event);"><%=hospitalMst.getAddress2()%></textarea>
		</td>
		</tr>
		
		<tr style="display: none">
			<td  width='50%' class="LABEL" colspan="4"><div align="right">City</td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<input type="text" name="city"   value="<%=hospitalMst.getCity()%>" onkeypress="return checkInput(this,2,40,event);" >
			</td>
		</tr>
		
		<tr>
		<td  width='50%' class="LABEL" colspan="4">PIN Code</td>
		<td  width='50%' class="CONTROL" 	colspan="4">
		<input type="text" name="pinCode"  onkeypress="return checkInput(this,1,6,event);" value="<%=hospitalMst.getPinCode().trim()%>">
		</td>
		</tr>
		
		<tr>
		<td  width='50%' class="LABEL" colspan="4">State<font color='red'>*</font></td>
		<td  width='50%' class="CONTROL" 	colspan="4">
			<select name="state" onchange="getDistrict(this)" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:112px;" >
			<%=hospitalMst.getStateCombo()%>		
			</select>
		</td>
		</tr>
		<%

			String state=hospitalMst.getState();
			
		%>
		<tr id="district">
			<td  width='50%' class="LABEL" colspan="4"><div align="right">District<font color='red'>*</font></td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<select name="district" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:112px;" >
				<%=hospitalMst.getDistrictCombo(state)%></select>
			</td>
		</tr>		
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Phone No.</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="phoneNo" maxlength="40" onkeypress="return checkInput(this,11,40,event);" value="<%=hospitalMst.getPhoneNo()%>">	
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Fax No.</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="faxNo"  onkeypress="return checkInput(this,11,20,event);"  maxlength="20" value="<%=hospitalMst.getFaxNo()%>">
		</td>
		</tr>
		
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Email Id</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="emailId"  onkeypress="return checkInput(this,10,50,event);" value="<%=hospitalMst.getEmailId()%>">	
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Contact Person</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="contactPerson" onkeypress="return checkInput(this,2,50,event);" value="<%=hospitalMst.getContactPerson()%>">
		</td>
		</tr>
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Hospital Type</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
	<select name="hospitalType" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:112px;">
		
		<option value="0" <%if(hospitalMst.getHospitalType().equals("0")){%>selected<%}%> >Select Value</option>
		<option value="1" <%if(hospitalMst.getHospitalType().equals("1")){%>selected<%}%>>Govt Hospital</option>
		<option value="2" <%if(hospitalMst.getHospitalType().equals("2")){%>selected<%}%>>Dispensary</option>
		<option value="3" <%if(hospitalMst.getHospitalType().equals("3")){%>selected<%}%>>Private Hospital</option>
		<option value="4" <%if(hospitalMst.getHospitalType().equals("4")){%>selected<%}%>>Nursing Home</option>
						
	</select>	
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Bus Route No.</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="busRouteNo" onkeypress="return checkInput(this,8,50,event);" value="<%=hospitalMst.getBusRouteNo()%>">
		</td>
		</tr>
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Bed Capacity</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		<input type="text" name="bedCapacity"  onkeypress="return checkInput(this,1,20,event);" value="<%=hospitalMst.getBedCapacity()%>">	
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">WeekDay Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
	
		<input type="text" name="weekHourFrom" onblur="validateHr(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getWeekHourFrom()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="weekMinFrom"  onblur="validateMin(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getWeekMinFrom()%>" size="3" maxlength="2">
		
		 <b>To</b>
		 
		<input type="text" name="weekHourTo" onblur="validateHr(this);"  onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getWeekHourTo()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="weekMinTo" onblur="validateMin(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getWeekMinTo()%>" size="3" maxlength="2">
		
		
		</td>
		</tr>
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Saturday Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		
		<input type="text" name="satHourFrom" onblur="validateHr(this);" onkeypress="return checkInput(this,1,2,event);"  value="<%=hospitalMst.getSatHourFrom()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="satMinFrom" onblur="validateMin(this);"  onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getSatMinFrom()%>" size="3" maxlength="2">
		
		 <b>To</b>
		 
		<input type="text" name="satHourTo" onblur="validateHr(this);" onkeypress="return checkInput(this,1,2,event);"  value="<%=hospitalMst.getSatHourTo()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="satMinTo" onblur="validateMin(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getSatMinTo()%>" size="3" maxlength="2">

		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="LABEL" 	colspan="4">Lunch Break Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
		<td  width='50%'  class="CONTROL" 		colspan="4">
		
		<input type="text" name="lunchHourFrom" onblur="validateHr(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getLunchHourFrom()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="lunchMinFrom" onblur="validateMin(this);" onkeypress="return checkInput(this,1,2,event);"  value="<%=hospitalMst.getLunchMinFrom()%>" size="3" maxlength="2">
		
		 <b>To</b>
		 
		<input type="text" name="lunchHourTo" onblur="validateHr(this);" onkeypress="return checkInput(this,1,2,event);"  value="<%=hospitalMst.getLunchHourTo()%>" size="3" maxlength="2">
				<b>:</b>
		<input type="text" name="lunchMinTo" onblur="validateMin(this);" onkeypress="return checkInput(this,1,2,event);" value="<%=hospitalMst.getLunchMinTo()%>" size="3" maxlength="2">
		
		
		</td>
		</tr>

		<!--<tr>
			<td  width='50%'  class="LABEL" 	colspan="4">User Licenses Allowed<font color='red'>*</font></td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="strNoOfLicences" onblur="validateLic(this);" onkeypress="return checkInput(this,1,5,event);" value="<%=hospitalMst.getStrNoOfLicences()%>">
			</td>
		</tr>
		
		--><tr>
		<td  class="ShadedSubTitleTagImage" colspan="8" width="100%"></td>
		</tr>
	  </table>
		
<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">

		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2>
				<div align="center" class="control_button2"> 
	  <a style="cursor:pointer;" class="button" tabindex="0" onClick='validateAndSubmit(event)' onkeyPress='validateAndSubmit(event)'><span class="Save">Save</span></a>
	  <a style="cursor:pointer;" class="button" tabindex="0" onClick='resetForm(event)' onkeyPress='resetForm(event)'><span class="reset">Reset</span></a>
	  <a style="cursor:pointer;" class="button" tabindex="0" onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a>
	  
	</div>
		
		
		</td>
		</tr>
		<tr>
		<td  width="100%"  colspan="8" align='center'><font color='red' align='center'><%=message%></font></td>
		</tr>
		
		</table>		
		
	<input type="hidden" name="hmode">
	<input type="hidden" name="strNoOfLicences" value="0">
	<anticsrf:csrftoken/>
	</form>
	
	</BODY>
</HTML>