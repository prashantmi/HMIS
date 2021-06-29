<!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2008-2009 
 ## Client's Name						: Delhi
 ## Project Name						: AHIMS
 ## Phase								: Development
 ## Name of Developer		 			: Renuka Singh
 ## Module Name							: User Management
 ## Date of creation					: 12-11-08
 ## Purpose								: Admin change password
 ## Previous Form(Calling)				: login.jsp
 ## Functions Used						: getField()
 ## Name of Tables used for reference 	: 
 ## Name of Tables used for data updation/insertion	: GBLT_HOSPITAL_MST
 ## Next Form	(Called)				: ChangeAdminPassword_cnt.jsp
 ## Date of Modification				: 
 ## Unit Tested By	& Date				: Renuka Singh & 17-11-2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :Y
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :Y
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :N
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :Y
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:Y
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :Y
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 
-->

<%@ page language="java" import="java.util.*,java.sql.*"
	contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	<%@taglib uri="/WEB-INF/anti_csrf.tld" prefix="anticsrf"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<jsp:useBean id="group" class="usermgmt.masters.ChangeAdminPassBean"
	scope="request" />
<jsp:setProperty name="group" property="*" />
<jsp:useBean id="connect" class="HisGlobal.HisMethods" scope="request" />
<jsp:useBean id="securePwd" class="login.SecurityUtil" scope="request" />


<html>
<head>
<link href="../../css/hisStyle.css" rel="stylesheet" type="text/css">
<link href="../../css/ColorNew.css" rel="stylesheet" type="text/css">
<title>Welcome... you can change your Password here...!!</title>
</head>

<%
	//username from the session.
	String username = (String) session.getAttribute("username");
	String hospitalCode = (String)session.getAttribute("HOSPITAL_CODE");
%>
<script language="JavaScript" src="../js/sha.js"></script>

<script language="JavaScript" src="../js/Validation.js" type="text/JavaScript"></script>
<script>
	function focusOnLoad()
	{
	document.forms[0].passwrd.focus();	
	}

window.history.forward();

//function for updating changed password with proper validations.

	function changePass(e,change_pass,mode)
		{
	
			var strNewPass=document.change_pass.newPasswrd.value;
			var strConfirmPass=document.change_pass.confirmPasswrd.value;
			var strCurrPass=document.change_pass.passwrd.value;
			var strLoginPass=document.change_pass.pwd.value;
			document.change_pass.hmode.value=mode;
				if((e.type=="click" || e.keyCode == 13))
					{
					
					var saltedPass =  strCurrPass+ document.forms[0].user.value;
					var hashObj = new jsSHA(saltedPass, "ASCII");
					hashedPass = hashObj.getHash("SHA-1", "HEX");	
					
					if ( strCurrPass =="")
						{
							alert("Enter Old Password "); 
							document.change_pass.passwrd.focus(); 
							return false ; 
						}
						else
					if (strNewPass=="")
						{
							alert("Enter New Password ");
							document.change_pass.newPasswrd.focus();  
							return false ; 
						}else
					if (strNewPass.length < 6)
						{
							alert("New Password should not be less than 6 digits");
							document.change_pass.newPasswrd.focus(); 
							return false ; 
						}else
					if (strConfirmPass=="")
						{
							alert("Enter Confirm Password "); 
								document.change_pass.confirmPasswrd.focus();  
							return false ; 
						}else		
					if (strConfirmPass.length < 6)
						{
							alert("Confirm Password should not be less than 6 digits"); 
							document.change_pass.confirmPasswrd.focus();
							return false ; 
						}else
					if (strNewPass!=strConfirmPass)
						{
							alert("New Password and Confirm Password are not Same"); 
							document.change_pass.confirmPasswrd.value="";
							document.change_pass.confirmPasswrd.focus();
							return false; 
						}
						
					if (hashedPass!=strLoginPass)
						{
							alert("Wrong Old Password"); 
							document.change_pass.passwrd.value="";
							document.change_pass.passwrd.focus();
							return false ; 
						}			
					else
						{
							var saltedPass =   strConfirmPass+document.forms[0].user.value;
							var hashObj = new jsSHA(saltedPass, "ASCII");
							hashedPassneW = hashObj.getHash("SHA-1", "HEX");	
							
							//alert("hashedPassneW....."+hashedPassneW);
							document.change_pass.newPasswrd.value=hashedPassneW;
							document.change_pass.confirmPasswrd.value=hashedPassneW;
							document.change_pass.action="ChangeAdminPassword_cnt.jsp";
							document.change_pass.submit();
					}
				}	
		}	
//fuction for cancelling task and removing control from change password page.	

	function resetForm(e,change_pass,mode)
	{
		if(e.type=="click"||e.keyCode==13)
		{
			if(mode == "DEFAULT")
			{
			   document.change_pass.action="../../startup/content.jsp";
			   document.change_pass.submit();
			}	
		}		
	}

//function for AlphaNumeric check of passwords.	

	function isAlphabetNumeric(thisField) 
	{
	for(var i = 0; i < thisField.value.length; i++) 
	{
		var chr = thisField.value.substring(i,i+1);
		if(chr>="A" && chr<="Z") { 			
		}else if(chr>="a" && chr<="z") {
		}else if(chr>=0 && chr<=9 ){
		}else if(chr == " ") {
		} else 
		{
			alert("Enter only Alphabets and Numbers.");
			thisField.value="";
			thisField.focus();
		}
	} 
}

//function for password to be between 6 - 10 digits.

function isDigit(these)
{
var strSize = these.value.length;
			
		if(strSize<=5)
		{
		
			alert("Password should not be less than 6 digits.");
			these.value="";
			these.focus();
		}		
		if(strSize>=11)
		{
		
			alert("Password should not be greater than 10 digits.");
			these.value="";
			these.focus();
		}
	

}
	
</script>
<!--  
<body background="../../images/cdac1.psd.gif" width="100%" topmargin="0" onload="focusOnLoad()">-->
<body width="100%" topmargin="0" onload="focusOnLoad()">
<form name="change_pass" method="post" class="topmargin">
<table width="97%" border="0" align='center' cellspacing="0" cellpadding="0">
	<tr width="100%"><td class="ShadedSubTitleTagImage">
		<div align="left">Change  Admin Password</div>
		</td></tr>
	</table>
	
	<table class="formbg" width="97%" border="0" align='center'>
	<tr>
		
	</tr>
	<tr>
		<td class="LABEL"><div align="right"><font color='red'>*</font>Old Password </div></td>
		<td class="CONTROL">
				<div align="left"><input type="password" name="passwrd" maxlength="10" tabindex="1"></div>
	    </td>
		
		</tr>
	<tr>
		<td class="LABEL"><div align="right"><font color='red'>*</font>New Password </div></td>
		<td class="CONTROL">
				<div align="left"><input type="password" name="newPasswrd" maxlength="10" tabindex="2"></div>
	    </td>
	    
		</tr>
	<tr>
		
		<td class="LABEL"><div align="right"><font color='red'>*</font>Confirm Password </div></td>
		<td class="CONTROL">
				<div align="left"><input type="password" name="confirmPasswrd" maxlength="10" tabindex="3"></div>
	    </td>
	
		</tr>

	</table>

	<table width="97%" cellspacing="0" cellpadding="0" align="center">
	<tr class="FOOTER">
		<td colspan=2>
	</tr>
	
	<tr>
		<td colspan=2 >
		<div class="control_button2" align="center"><a class="button" style="cursor: pointer;" 
		    onKeyPress='changePass(event,change_pass,"SAVE")' tabindex="4"
			onClick='changePass(event,change_pass,"SAVE")'><span class="save"> Save</span></a> <a
			class="button" style="cursor: pointer;"
			tabindex=5 onClick='document.change_pass.reset();'
			onkeyPress='resetForm(event,change_pass,"RESET")'><span class="clear"> Clear</span></a> <a
			class="button" style="cursor: pointer;"tabindex=6
			onClick='resetForm(event,change_pass,"DEFAULT")'
			onkeyPress='resetForm(event,change_pass,"DEFAULT")'><span class="cancel"> Cancel</span></a></div>
		</td>
	</tr>
</table>
<%
	//Get password according to the login user.

	String strQuery = " SELECT GSTR_PASSWORD FROM GBLT_HOSPITAL_MST WHERE "+
					  " GNUM_HOSPITAL_CODE='"+hospitalCode+ "' and " +
	                  " GSTR_USER_NAME ='"+username+ "' AND GNUM_ISVALID=1 ";
	
	System.out.println("password query------->"+strQuery);
				
	String strPwd = (String) connect.getField(strQuery);
	System.out.println("password ------->"+strPwd);
	//String strPwd1 = securePwd.decrypt(strPwd);
	//System.out.println("decrypted password ------->"+strPwd1);
	
%> <input type='hidden' name='pwd' value="<%=strPwd%>"> <input
	type='hidden' name='hmode'> <input type='hidden' name='user'
	value="<%=username%>">
	<anticsrf:csrftoken/>
	</form>
</body>
</html>