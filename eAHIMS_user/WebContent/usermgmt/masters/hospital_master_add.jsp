<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>
 <%@page import="usermgmt.masters.umgmtSessionMgtConfigUtil"%>
<jsp:useBean id="hospitalMst" class="usermgmt.masters.HospitalConfigMasterBEAN" 	scope="request" >
<jsp:setProperty name="hospitalMst" property="*" />
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

</jsp:useBean>

 <%
	 ///For getting the password configuration Strength
	 umgmtSessionMgtConfigUtil myUtil = new umgmtSessionMgtConfigUtil();
	 List lst = myUtil.getPreviousValues();
	 String passwordStrengthMsg="";
	
	 
	 if(lst.get(7).equals("2"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet and one number";
	 	else
	 if(lst.get(7).equals("3"))
		 passwordStrengthMsg="Password Must Contain atleast one Alphabet,one number and one Special Character";
	 
	 
	 
	 %>
<%

String message = request.getParameter("message");
if(message==null)
	message = "";

//String hcode = (String) session.getAttribute("HOSPITAL_CODE");
//hospitalMst.setHospitalcode(hcode);
%>

<%@page import="usermgmt.masters.HospitalMstBean"%>
<%@page import="org.w3c.dom.Document"%>
<HTML>
	<HEAD>
		<TITLE>Session Configuation</TITLE>

		
		<link href="../../css/Color.css" 	 rel="stylesheet" type="text/css">
		<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="../../css/master.css" 	 rel="stylesheet" type="text/css">
		<script language="JavaScript" src="../js/sha.js"></script>
		
		<script language="javascript"><!--
window.history.forward() ;
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
				
function  validateAdd(e,formObj)
{
	var check=true;
	if(e.type=="click" || e.keyCode==13)
 { 
     var weekDayFrom=document.forms[0].weekHourFrom.value+document.forms[0].weekMinFrom.value;
     var weekDayTo=document.forms[0].weekHourTo.value+document.forms[0].weekMinTo.value;
     
     var satDayFrom=document.forms[0].satHourFrom.value+document.forms[0].satMinFrom.value;
     var satDayTo=document.forms[0].satHourTo.value+document.forms[0].satMinTo.value;
     
     var lunchDayFrom=document.forms[0].lunchHourFrom.value+document.forms[0].lunchMinFrom.value;
     var lunchDayTo=document.forms[0].lunchHourTo.value+document.forms[0].lunchMinTo.value;
      var userPwd	=	document.form1.password.value;
		var conPwd	=	document.form1.conPwd.value;
		
 

	if(formObj.hospitalName.value=="")
		{
			alert("Please Enter the Hospital Name");
			formObj.hospitalName.focus();
			return false;
		}
		if(formObj.userId.value=="")
		{
			alert("Please Enter the UserId");
			formObj.userId.focus();
			return false;
		}
		if(formObj.password.value=="")
		{
			alert("Please Enter Password");
			formObj.password.focus();
			
			return false;
		}
		if(userPwd != conPwd)
		{
			alert("Check Your Confirm PASSWORD,Confirm Password Must Be Equal To Password");
			document.form1.conPwd.select();
			return false;
		}
		if(formObj.state.value=="0")
		{
			alert("Please Select State");
			formObj.state.focus();
			
			return false;
		}
		if(formObj.district.value=="0")
		{
			alert("Please Select District");
			formObj.district.focus();
			
			return false;
		}		
		if(weekDayFrom > weekDayTo)
		{
       alert("WeekDay From Timings Cannot be Greater than the To Timings");
       return false;
       }
     if(satDayFrom > satDayTo)
     {
       alert("Saturday From Timings Cannot be Greater than the To Timings");
       return false;
       }
     if(lunchDayFrom > lunchDayTo)
     {
       alert("Lunch From Timings Cannot be Greater than the To Timings");
       return false;
       }
       
      		var saltedPass =  formObj.password.value + formObj.userId.value;
			//alert(saltedPass+"hihi")
			var hashObj = new jsSHA(saltedPass, "ASCII");
			try 
			{
				hashedPass = hashObj.getHash("SHA-1", "HEX");	
			}
			catch(e)
			{
				isSuccess = false;	
			}
			//alert(hashedPass)
	formObj.password.value = hashedPass;
      document.form1.conPwd.value = hashedPass;
	  	return true;
		}
 

}	

function  validateModify(e,formObj)
{
	var check=true;
	if(e.type=="click" || e.keyCode==13)
 { 
     var weekDayFrom=document.forms[0].weekHourFrom.value+document.forms[0].weekMinFrom.value;
     var weekDayTo=document.forms[0].weekHourTo.value+document.forms[0].weekMinTo.value;
     
     var satDayFrom=document.forms[0].satHourFrom.value+document.forms[0].satMinFrom.value;
     var satDayTo=document.forms[0].satHourTo.value+document.forms[0].satMinTo.value;
     
     var lunchDayFrom=document.forms[0].lunchHourFrom.value+document.forms[0].lunchMinFrom.value;
     var lunchDayTo=document.forms[0].lunchHourTo.value+document.forms[0].lunchMinTo.value;
     var userPwd	=	document.form1.password.value;
		var conPwd	=	document.form1.conPwd.value;
		

	if(formObj.hospitalName.value=="")
		{
			alert("Please Enter the Hospital Name");
			formObj.hospitalName.focus();
			return false;
		}
		
		/*if(formObj. strNoOfLicences.value=="")
		{
			alert("Please Enter the User Licenses Allowed");
			formObj.strNoOfLicences.focus();
			return false;
		}*/
		if(formObj.userId.value=="")
		{
			alert("Please Enter the UserId");
			formObj.userId.focus();
			return false;
		}
		if(formObj.password.value=="")
		{
			alert("Please Enter Password");
			formObj.password.focus();
			return false;
		}
		if(userPwd != conPwd)
		{
			alert("Check Your Confirm PASSWORD,Confirm Password Must Be Equal To Password");
			document.form1.conPwd.select();
			return false;
		}
 		if(formObj.state.value=="0")
		{
			alert("Please Select State");
			formObj.state.focus();
			
			return false;
		}
		if(formObj.district.value=="0")
		{
			alert("Please Select District");
			formObj.district.focus();
			
			return false;
		}
		if(weekDayFrom > weekDayTo)
		{
       alert("WeekDay From Timings Cannot be Greater than the To Timings");
       return false;
       }
     if(satDayFrom > satDayTo)
     {
       alert("Saturday From Timings Cannot be Greater than the To Timings");
       return false;
       }
     if(lunchDayFrom > lunchDayTo)
     {
       alert("Lunch From Timings Cannot be Greater than the To Timings");
       return false;
       }
       
       var saltedPass =  formObj.password.value + formObj.userId.value;
			var hashObj = new jsSHA(saltedPass, "ASCII");
			try 
			{
				hashedPass = hashObj.getHash("SHA-1", "HEX");	
			}
			catch(e)
			{
				isSuccess = false;	
			}
			formObj.password.value = hashedPass;
			document.form1.conPwd.value= hashedPass
	  	return true;
		}
 

}			
				
function submitForm(mode)
{
  	document.getElementsByName("hmode")[0].value=mode;
	 document.forms[0].submit();
	 
}
		
function submitPage()
	{
	alert("");
 		document.form1.hmode.value = hmode;		
		document.form1.submit();
			
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
		document.forms[0].district.value="0";
		document.forms[0].state.value="0"; 
		document.forms[0].phoneNo.value="";
		document.forms[0].faxNo.value="";
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
	
	function resetForm(e,form1,mode)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			if(mode == "RESET")
				document.form1.reset();
			else
			{
				document.form1.hmode.value = mode;			
				document.form1.submit();
			}	
		}	
	}	
	
	function matchPassword()
	{
		var userPwd	=	document.form1.password.value;
		var conPwd	=	document.form1.conPwd.value;
		if(userPwd != conPwd)
		{
			alert("Check Your Confirm PASSWORD,Confirm Password Must Be Equal To Password");
			document.form1.conPwd.select();
			return false;
		}
		return true;
	}	
	function minCheck()
	{
		var o=document.getElementsByName("password");
		var passLen=(o[o.length-1].value).length;
		if(passLen<6)
		{
			alert('Your password is less than 6 digit');
			document.form1.password.value="";
			document.form1.password.focus();
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
 	 		if(document.form1.hmode.value=="UPDATE")
 	 		{
 	 			document.form1.hmode.value="CMBU";
 	 		}
 	 		else
 				document.form1.hmode.value="CMB";
			document.form1.submit();
 }
 	
	--></script>
</HEAD>
	<!-- 
	
	<BODY background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="document.forms[0].hospitalName.focus();"> -->
	<BODY  width="100%" topmargin="0" onload="document.forms[0].hospitalName.focus();">
		
<form name="form1" method="post" action="hospital_master_cnt.jsp">
<%
String hmode = "";
String title = "";
String userSeatId	=	"";
String hoscode = "";
hmode = request.getParameter("hmode");
if(hmode==null)
	hmode="SAVE";

if(hmode.equals("SAVE"))
{
	title="Add";
	System.out.println("I m inside add");	
}
if(hmode.equals("UPDATE"))
{
	title = "Modify";
	System.out.println("I m inside modify");
}
%>
	<table width="97%" align="center">		
		<tr>
			<td  width="97%" class="ShadedSubTitleTagImage" colspan="8">Hospital Master >> <%=title%> Page</td>
		</tr>
		<!--  
		<tr>
			<td class="ShadedSubTitleTagImage" align="right" width="95%">Status</td>
			<td class="ShadedSubTitleTagImage" width="5%"><select name='isvalid' tabindex="-1">
				<option value='1' <%=hospitalMst.getIsvalid().equals("1")?"selected":""%>>Active</option>
				<option value='2' <%=hospitalMst.getIsvalid().equals("2")?"selected":""%>>Inactive</option></select>
			</td>
		</tr>
		-->
		
	</table>
	<table class="formbg" width="97%" align="center">
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4" ><div align="right">Hospital Name<font color='red'>*</font></td>
			<td  width='50%'  class="CONTROL" 	align="left" colspan="4">
				<input type="text"  name="hospitalName"  style="width: 300px;" value="<%=hospitalMst.getHospitalName()%>" maxlength="100"   onkeypress="return checkInput(this,2,100,event);"  value="<%=hospitalMst.getHospitalName()%>">
			</td>
		</tr>
		<tr>
			<td  width='50%' class="LABEL" colspan="4"><div align="right">Hospital Short Name</td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<input type="text" name="hospitalShortName"   value="<%=hospitalMst.getHospitalShortName()%>" maxlength="10"  onkeypress="return checkInput(this,2,100,event);"  value="<%=hospitalMst.getHospitalShortName()%>">
			</td>
		</tr>
		<tr>
			 <td  width="50%" class="LABEL" colspan="4"><div align="right">User Id<font color='red'>*</font></td>
		 	 <td  width='50%' class="CONTROL"     colspan="4">
		 		<input type="text" name="userId" value="<%=hospitalMst.getUserId()%>" onkeypress = "return checkInput(this,3,20,event);"></td></tr>   
		<tr>
	    	<td  width="50%"  class="LABEL" colspan="4"><div align="right">Password<font color='red'>*</font></td>
	   		<td  width="50%" class="CONTROL"  colspan="4">
	    		<input type="password" name="password"   value="<%=hospitalMst.getPassword()%>" maxlength="10"  onchange="minCheck();" > <font color='red'>(6 to 10 characters)<br>
		     		The Password is case sensitive.<br> <%=passwordStrengthMsg %>
		   	</td>
      	</tr>
      	<tr>
	  		<td  width="50%" class="LABEL" colspan="4"><div align="right">Confirm Password<font color='red'>*</font></td>
			<td  width='50%' class="CONTROL"     colspan="4">
		 		<input type="password" name="conPwd" maxlength="10" value="<%=hospitalMst.getConPwd() %>" >
			</td>
		</tr>
		<tr>
			<td  width='50%' class="LABEL" colspan="4"><div align="right">Address 1</td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<textarea rows="1" cols="17" name="address1" onkeypress="return checkInput(this,12,40,event);"><%=hospitalMst.getAddress1()%></textarea>
			</td>
		</tr>
		<tr>
			<td  width='50%' class="LABEL" colspan="4"><div align="right">Address 2</td>
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
			<td  width='50%' class="LABEL" colspan="4"><div align="right">PIN Code</td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<input type="text" name="pinCode"   value="<%=hospitalMst.getPinCode().trim()%>" onkeypress="return checkInput(this,1,6,event);">
			</td>
		</tr>
		<tr>
			<td  width='50%' class="LABEL" colspan="4"><div align="right">State<font color='red'>*</font></td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<select name="state" onchange="getDistrict(this)" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:112px;" >
				<%=hospitalMst.getStateCombo()%></select>
			</td>
		</tr>
		<%
			String stateCode=request.getParameter("state");
			String state=hospitalMst.getState();
			if(hmode.equals("UPDATE"))
				stateCode=state;
			System.out.println("--State--"+stateCode+"----------");
		%>
		<tr id="district">
			<td  width='50%' class="LABEL" colspan="4"><div align="right">District<font color='red'>*</font></td>
			<td  width='50%' class="CONTROL" 	colspan="4">
				<select name="district" style="font-family:Verdana; text-decoration:none; font-size:12px; font-style:normal; height:20px; width:112px;" >
				<%=hospitalMst.getDistrictCombo(stateCode)%></select>
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Phone No.</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="phoneNo"  value="<%=hospitalMst.getPhoneNo()%>" maxlength="40" onkeypress="return checkInput(this,11,40,event);" >	
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Fax No.</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="faxNo"  onkeypress="return checkInput(this,11,20,event);"  maxlength="20" value="<%=hospitalMst.getFaxNo()%>">
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Email Id</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="emailId"  onkeypress="return checkInput(this,10,50,event);" value="<%=hospitalMst.getEmailId()%>">	
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Contact Person</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="contactPerson" onkeypress="return checkInput(this,2,50,event);" value="<%=hospitalMst.getContactPerson()%>">
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Hospital Type</td>
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
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Bus Route No.</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="busRouteNo" onkeypress="return checkInput(this,8,50,event);" value="<%=hospitalMst.getBusRouteNo()%>">
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Bed Capacity</td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="bedCapacity"  onkeypress="return checkInput(this,1,20,event);" value="<%=hospitalMst.getBedCapacity()%>">	
			</td>
		</tr>
		<tr>
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">WeekDay Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
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
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Saturday Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
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
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">Lunch Break Timings&nbsp;&nbsp;( HH:MM 24 Hrs )</td>
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
			<td  width='50%'  class="LABEL" 	colspan="4"><div align="right">User Licenses Allowed<font color='red'>*</font></div></td>
			<td  width='50%'  class="CONTROL" 		colspan="4">
				<input type="text" name="strNoOfLicences" onkeypress="return checkInput(this,1,5,event);" value="<%=hospitalMst.getStrNoOfLicences()%>">
			</td>
		</tr>
		
		--><tr>
			<td width="100%" class="ShadedSubTitleTagImage" colspan="8" ></td>
		</tr>
		</table>
	<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">
		<td colspan=2>
	</tr>	
	<tr>
		<td colspan=2>
				<div align="center" class="control_button2"> 
	  				<%if (hmode.equals("SAVE"))
					{%>
						<a style="cursor:pointer;" class="button" tabindex="0" onClick="validateAdd(event,form1) && submitForm('SAVE')" 	onkeyPress='validateAdd(event,form1) && submitForm('SAVE')'><span class="Save">Save</span></a> 
						<a style="cursor:pointer;" class="button" tabindex=0 onClick='document.form1.reset();'><span class="reset">Reset</span></a> 
					<%}
					else if(hmode.equals("UPDATE"))
					{%>
						<a style="cursor:pointer;"  class="button"  tabindex=0 onClick="validateModify(event,form1) && submitForm('UPDATE')"  onkeyPress='"validateModify(event,form1) && submitForm('UPDATE')" '><span class="Save">Save</span></a> 
					<% }%>
        				<a style="cursor:pointer;"  class="button" tabindex=0 onClick='resetForm(event,form1,"DEFAULT")' onkeyPress='resetForm(event,form1,"DEFAULT")'><span class="cancel">Cancel</span></a></div>
        		
			</td>
		</tr>
		<tr>
			<td  width="100%"  colspan="8" align='center'><font color='red' align='center'><%=message%></font></td>
		</tr>
	</table>		
		
	<input type="hidden" name="hmode" value="<%=hmode%>">
	<input type="hidden" name="hospitalcode" value="<%=hospitalMst.getHospitalcode()%>">
	<input type="hidden" name="strNoOfLicences" value="0">
	 <anticsrf:csrftoken/>
	
</form>
</BODY>
</HTML>