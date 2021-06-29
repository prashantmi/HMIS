<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="/WEB-INF/otTags.tld" prefix="otTags"%>
<%@ page import="java.util.*" %>
<%@ page import="java.io.*" %>

<%@page import="login.CSRFTokenUtil"%>
<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>

<%
List lst = (List) request.getAttribute("previousValues");
String message = (String)request.getAttribute("message");
if(message==null)
	message = "";
%>

<HTML>
	<HEAD>
		<TITLE>Session Configuation</TITLE>
		
		<link href="../../css/ColorNew.css" 	 rel="stylesheet" type="text/css">
		<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
		<link href="../../css/master.css" 	 rel="stylesheet" type="text/css">
		<script language="javascript">
		
	window.history.forward();	
function focusOnLoad()
	{

	document.forms[0].timeOutTime.focus();	
	}

		
		
function  validateAndSubmit(e)
{
  if(e.type=="click" || e.keyCode==13)
     {	
		if(document.forms[0].timeOutTime.value=="")
			    {
				alert("Session Time-out Time is Blank");
				document.forms[0].timeOutTime.focus();
				}
			else
		 if(document.forms[0].timeOutTime.value > 1440)
		        {
				alert("Session Time-out Time should be less than 1440");
				document.forms[0].timeOutTime.value="";
				document.forms[0].timeOutTime.focus();
				}
			else 
		if(document.forms[0].blockAfter.value=="")
		        {  
				alert("Block user Id After is Blank ");
				document.forms[0].blockAfter.focus();
				}
		    else
	   if(document.forms[0].auditLogPathWindow.value=="")
	            {
				alert("Audit Log Path For Windows is Blank.");
				document.forms[0].auditLogPathWindow.focus();
				}
		    else 
	  if(document.forms[0].auditLogPathLinux.value=="")
	            {
				alert("Audit Log Path For Linux is Blank");
				document.forms[0].auditLogPathLinux.focus();
				}
			else
		if(document.forms[0].modificationTime.value=="")
	            {
				alert("Registration Desk Modification Time is Blank");
				document.forms[0].modificationTime.focus();
				}
				else
		if(document.forms[0].passwModificationTime.value=="")
	            {
				alert("Password Modification Time is Blank");
				document.forms[0].passwModificationTime.focus();
				}
				else
		if(document.forms[0].passwStrength.value=="-1")
	            {
				alert("Select Password Strength");
				document.forms[0].passwStrength.focus();
				}
			else
				submitPage();		
		}
}			
				
		
		
		
		function submitPage()
		{
		
				document.forms[0].hmode.value="SAVE";
				document.forms[0].submit();
						
		}
		function checkInput(varObj,validationIndex,e)
		{
		
			var returnValue = true;
			var applicableStr = "";
			var key;
			if (window.event)
				key = window.event.keyCode;
			else if (e)
				key = e.which;
			
			//control keys
			if ((key==null) || (key==0) || (key==8) ||
			(key==9) || (key==13) || (key==27) || (key==32)|| (key==44))			
			return true;			
			
			var keychar = String.fromCharCode(key);
			
				
			//Validating Length
			
			// if(varObj.value.length==length)
			//	returnValue = false;
				
			
			
				
			//Specifying the applicable character set
			if(validationIndex==1)
				applicableStr = "1234567890";
		    //Specifying the applicable Alphnumeric set
		    if(validationIndex==2)
		       applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
		    //Specifying the applicable path for windows
		    if(validationIndex==3)
		    applicableStr="/*?\"<>|";
		       //applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890\\:."
		       
			//Specifying the applicable path for Linux
		    if(validationIndex==4)
		    applicableStr="\\:*?\"<>|";
		       //applicableStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890?/."
			
			//alert(applicableStr);
			//alert(validationIndex);
			
	if(validationIndex==1 || validationIndex==2)
		{	  
		
			if(applicableStr.indexOf(keychar)== -1)
				{
			//	varObj.value="";	
				returnValue = false;
				}
		}	
				
	if(validationIndex==3 || validationIndex==4)
		{	  
		//alert(applicableStr.indexOf(keychar));
			if(applicableStr.indexOf(keychar)!= -1)
			 {
				returnValue = false;
			 }	
		}	
	
		//alert(returnValue);
	
				
				if(varObj.value.length==1 && varObj.value==0 && validationIndex==1 )
				{				
				varObj.value="";				
				}
				
			return returnValue;		
		}	
		
function resetForm(event)
{
	if(event.type=="click" || event.keyCode==13)
	{
		document.forms[0].timeOutTime.value="";
		document.forms[0].blockAfter.value="";
		document.forms[0].auditLogPathWindow.value="";
		document.forms[0].auditLogPathLinux.value="";
		document.forms[0].modificationTime.value="";
		document.forms[0].passwModificationTime.value="";
		document.forms[0].passwStrength.value="-1";		
		document.getElementsByName("loginCount")[0].checked=true
		
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
		
		</script>
		 <%
         
	    String fl;
         try
         {
	         
	         
		      fl=System.getProperties().getProperty("os.name");
	          System.out.println(fl);
	       
         }
         catch(Exception e)
         {
	        fl="0";
          }
      %>

		
	</HEAD>
	
	<!-- 
	<BODY background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()"> -->
	<BODY  width="100%" topmargin="0" onload="focusOnLoad()">
		<html:form  action="/usermgmt/masters/userMgtConfigController" name="sessionConfigForm" type="usermgmt.masters.umgmtSessionMgtConfigActionForm" method="POST">

		
		<table class="topmargin" width="97%" align="center">		
		
		<tr>
		<td  width="100%" class="ShadedSubTitleTagImage" colspan="8">Configuration Master</td>
		</tr>
		</table>
		
		<table class="formbg" width="97%" align="center">		
		
		<tr>
		<td  width='50%'  class="label" 	colspan="4">Session Time-out Time</td>
		<td  width='50%'  class="control" 		colspan="4">
			<html:text property='timeOutTime' name="sessionConfigForm" maxlength="4" onkeypress="return checkInput(this,1,event);"  tabindex="0" />&nbsp;&nbsp;Minutes
		</td>
		</tr>
		
		<tr>
		<td  width='50%' class="label" colspan="4">Login by one user Name</td>
		<td  width='50%' class="control" 	colspan="4">
		<html:radio property='loginCount' name="sessionConfigForm" value='1' tabindex="0"/>Single
		<html:radio property='loginCount' name="sessionConfigForm" value='2' tabindex="0"/>Multiple
		</td>

		</tr>
		
		<tr>
		<td  width='50%' class="label" colspan="4">Block User Id After</td>
		<td  width='50%' class="control" 	colspan="4">
			<html:text property='blockAfter' name="sessionConfigForm" maxlength="1" onkeypress="return checkInput(this,1,event);" tabindex="0" />&nbsp;&nbsp;Unsuccessful Login
		</td>
		</tr>
		
		
		
		<tr>
		<td  width='50%' class="label" colspan="4">Audit Log Path For Windows</td>
		<td  width='50%' class="control" 	colspan="4">
			<html:text property='auditLogPathWindow' name="sessionConfigForm" onkeypress="return checkInput(this,3,event);" tabindex="0"/>
		</td>
		</tr>
		
		<tr>
		<td  width='50%' class="label" colspan="4">Audit Log Path For Linux</td>
		<td  width='50%' class="control" 	colspan="4">
			<html:text property='auditLogPathLinux'  name="sessionConfigForm"  onkeypress="return checkInput(this,4,event);"  tabindex="0"/>
		</td>
		</tr>
		<tr>
		<td  width='50%'  class="label" 	colspan="4">Registration Desk Modification Time</td>
		<td  width='50%'  class="control" 		colspan="4">
			<html:text property='modificationTime' name="sessionConfigForm" maxlength="3" onkeypress="return checkInput(this,1,event);" tabindex="0" />&nbsp;&nbsp;Minutes
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="label" 	colspan="4">Password Modification Time</td>
		<td  width='50%'  class="control" 		colspan="4">
			<html:text property='passwModificationTime' name="sessionConfigForm" maxlength="3"  onkeypress="return checkInput(this,1,event);" tabindex="0" />&nbsp;&nbsp;Days
		</td>
		</tr>
		
		<tr>
		<td  width='50%'  class="label" 	colspan="4">Password Strength</td>
		<td  width='50%'  class="control" 		colspan="4">


<html:select property="passwStrength" name="sessionConfigForm" style="font-family:Verdana; font-size:12px;font-style:normal;text-decoration:none;height:20px;width: 114px;">
	<html:option value="-1">----Select----</html:option>
	<html:option value="1">No Constraint</html:option>
	<html:option value="2">Alphabets/Numbers</html:option>
	<html:option value="3">Alphabets/Numbers/Special Characters</html:option>
	
</html:select>
	
		</td>
		</tr>
		</table>
		

	 <table width="97%" cellspacing="0" cellpadding="0" align="center">
		<tr class="FOOTER">
			<td colspan=2>
		</tr>
	
		<tr>
			<td colspan=2>
				<div align="center" class="control_button2">
		<a 	style=cursor:pointer; class="button" onClick='validateAndSubmit(event);' onkeypress="validateAndSubmit(event);" tabindex="0"><span class="save">Save</span></a>
		<a 	style=cursor:pointer; class="button" class="link" tabindex=0 onClick='resetForm(event)' onkeyPress='resetForm(event)'><span class="clear">Clear</span></a>
        <a 	style=cursor:pointer; class="button" class="link" tabindex=0 onClick='cancelForm(event)' onkeyPress='cancelForm(event)'><span class="cancel">Cancel</span></a> 
    </div>
		
		
		</td>
		</tr>
		<tr>
		<td  width="100%"  colspan="8" align='center'><font color='red' align='center'><%=message%></font></td>
		</tr>
		</table>		
		<html:hidden property="hmode"/>
		<anticsrf:csrftoken/>
		</html:form>
		
	</BODY>
</HTML>