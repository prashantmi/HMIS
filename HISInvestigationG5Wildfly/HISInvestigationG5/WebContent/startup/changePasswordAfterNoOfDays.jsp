<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>

<%@ page import="java.util.*,startup.*" %>

<%
UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
int paswStrength=configHandler.getPasswStrength();

String passwordStrengthMsg="";


if(paswStrength==2)
	 passwordStrengthMsg="Password Must Contain atleast one Alphabet and one number";
	else
if(paswStrength==3)
	 passwordStrengthMsg="Password Must Contain atleast one Alphabet,one number and one Special Character";


%>

<html>

<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
<his:javascript src="/registration/js/registration.js"/>
<%
String uid=request.getParameter("uid");
String pwd=request.getParameter("pwd");

//System.out.println("uid in change pasword-----"+uid+"------"+pwd);
request.setAttribute("uid",uid);
request.setAttribute("pwd",pwd);


String messageToAlert1="";
int time_left=((Integer)session.getAttribute("TIME_TO_EXPIRE")).intValue();


messageToAlert1="Your Password will Expire in "+time_left+" day (s) ";


String messageToAlert2="Want To Change it Now";

//System.out.println(messageToAlert1);

String oldPassword=(String)request.getAttribute("PASSWORD");

String password_change=(String)session.getAttribute("PASSWORD_CHANGE");
//System.out.println("password_change---------"+password_change);

String UserFullName="Hello, "+session.getAttribute("UserFullName");
String messageToShow=UserFullName;

if(password_change==null) password_change="false";

if(!password_change.equals("true"))
	 messageToShow+=" Your Password has been expired Change it to login";

 
%>

<script type="text/javascript">


function checkForPasswordChange()
{
//alert("password_change------"+<%=password_change%>);

if(<%=password_change%>==true)
	{
	hisConfirm('<%=messageToAlert1%>','<%=messageToAlert2%>');
	
	document.forms[0].newPassword.disabled=true;
	document.forms[0].confirmPassword.disabled=true;
	document.getElementById("allButtons").style.display="none";
	//document.getElementById("confirm").style.display="block";
	document.getElementById("yesButton").focus();
 	}

}
function confirmYesNo(mode,e)
{

var keyCode=e.keyCode
//alert(keyCode);
if(keyCode=='13' || e.type=='click')
	{	
	
	
if(mode==true)
	changePassword();
else
if(mode==false)
	dontChangePassword();

	}

}

function changePassword()
{
	
	//document.getElementById("hisConfirm").style.display="none";
	
	closehisConfirm();
	document.forms[0].newPassword.disabled=false;
	document.forms[0].confirmPassword.disabled=false;
	document.getElementById("allButtons").style.display="block";
	document.forms[0].newPassword.focus();
}

function dontChangePassword()
{
			document.forms[0].hmode.value="DONT_CHANGE_PASSWORD";
			document.forms[0].action="../startup/loginAction";
			document.forms[0].submit();
}

function getValidateStr(index)
{
	var str = "";
	
	switch(index)
	{
		case 1:		//for validating email
			str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_@.";
			break;
			
		case 2:		//for validating telephone no
			str = "1234567890-";
			break;
			
		case 3:		//for validating address
			str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-/,.#$()';: ";
			break;
			
		case 4:		//for validating name
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .";
			break;
			
		case 5:		//numeric only
			str = "1234567890";
			break;
			
		case 6:		//numeric with space
			str = "1234567890 ";
			break;
			
		case 7:		//for validating amount
			str = "1234567890.";
			break;
			 
		case 8:		//alphanumeric
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
			break;
			
		case 9:		//alphanumeric with space
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";
			break;
			
		case 10:	//Character only
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
			
		case 11:	//Character with space
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			break;
			
		case 12:	//Upper character only
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
			break;
			
		case 13:	//Upper character with space
			str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
			break;
			
		case 14:	//Lower character
			str = "abcdefghijklmnopqrstuvwxyz";
			break;
			
		case 15:	//Lower character with space
			str = "abcdefghijklmnopqrstuvwxyz ";	 
			break;
			
		case 16:	//Company Names
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .()";
			break;	
			
	}	//end of Switch statement
	return str;
}//1

//this function accepts index, based on index it validates the specified character within the string
//defined in getValidateStr function. it validates single character at a time.
function validateData(e,index)
{
	var key,keychar,str;
		
	if (window.event)
		key = window.event.keyCode;
	else
	{
		if (e)
			key = e.which;
		else
		   return true;
	}
	 
	keychar = String.fromCharCode(key);
		
	// control keys
	if ((key==null) || (key==0) || (key==8) ||
		(key==9) || (key==13) || (key==27) )
		return true;
	else 
	{
		str = getValidateStr(index)
		if (((str).indexOf(keychar) > -1))
		   return true;
		else
		   return false;
	}

}//2



	function minCheck(obj)
	{
		var o=document.getElementsByName("obj.value");
		var passLen=(o[o.length-1].value).length;
		if(passLen<6)
		{
			alert('Your password is less than 6 digit');
			document.forms[0].userPwd.value="";
			document.forms[0].userPwd.focus();
		}
	}
	
	function validateMinLength(elem,minlen) {
	 var isValid =false;
     if(elem)
		value=elem.value;
     else
		value="";
				     
       if ((value.length<minlen))
				               {
				                isValid = true;
				              }
   return isValid;
 } 
 
function finalSubmit(e)
{
if(e.type=="click"||e.keyCode==13)
	{
	
	var flag=true;
	
	
     if(document.forms[0].newPassword.value=="") 
     {
     flag=false;
     alert("Enter New Password");
     document.forms[0].newPassword.focus();
     }else 
      if(document.forms[0].confirmPassword.value=="") 
     {
     flag=false;
     alert("Enter Confirm Password");
     document.forms[0].confirmPassword.focus();     
     }else 
     if(validateMinLength(document.forms[0].newPassword,6 ))
     {
     flag=false;
     alert("New Password length should be minimum 6");
     document.forms[0].newPassword.focus();
     }else		
      if(document.forms[0].newPassword.value!=document.forms[0].confirmPassword.value) 
     {
     flag=false;
     alert("New Password and Confirm Password are not Same");
     document.forms[0].confirmPassword.focus();
  
     }
     else		
      if(document.forms[0].newPassword.value=="<%=oldPassword%>") 
     {
     flag=false;
     alert("New Password and Old Password Cannot be Same");
     document.forms[0].newPassword.focus();
     }
     else
     if(!validatePasswordStrength())
		{
		var passwStrength="<%=paswStrength%>";
		document.forms[0].newPassword.focus();
		if(passwStrength=="2")
			alert("PassWord Should Be a Combination of Alphabets and Numbers");
			else
		if(passwStrength=="3")
			alert("PassWord Should Be a Combination of Alphabets,Numbers and a Special Character");
					
		return false;
		}
		else
	if(flag==true)
		{
		document.forms[0].hmode.value="CHANGE_PASSWORD";
		//alert("hi"+document.forms[0].hmode.value);
		document.forms[0].submit();
		}
		
	}
}

function validatePasswordStrength()
	{
 	var passwStrength="<%=paswStrength%>";
	
	var userPwd=document.getElementsByName("newPassword")[0].value;
	
	var	alphabetStr="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	var numericStr="0123456789";
	var specialStr="~`!@#$%^&*()_-+={}[];:'<>,.?/\|\"";
	
	var alphabetCheck=false;
	var numericCheck=false;
	var specialCharCheck=false;

if(passwStrength=="2" || passwStrength=="3" )
{	
	
	for(var i=0; i< userPwd.length; i++)
		{
		
		///////Checking for whetehr the password contains alphabets////////
		if(alphabetStr.indexOf(userPwd.charAt(i))!=-1)
			{
			alphabetCheck=true;
			}
		
		///////Checking for whetehr the password contains Numbers////////
			
		if(numericStr.indexOf(userPwd.charAt(i))!=-1)
			{
			numericCheck=true;
			}
			
			
			///////Checking for whetehr the password contains Special Characters or not ////////
			
		if(specialStr.indexOf(userPwd.charAt(i))!=-1)
			{
			specialCharCheck=true;
			}
			
				
			
		
		}//for closed 
}//if closed 
	


	
	//alert('alphabetCheck----'+alphabetCheck)
	//alert('numericCheck-----'+numericCheck)
	
	//Cases for Password Strength 
	
	
	//Case1:No constraint   						  	      passwStrength-->1
	//Case2:Alphabets && Numbers							  passwStrength-->2
	//Case3:Alphabets , Numbers && Special characters 		  passwStrength-->3
	
	
	if(passwStrength=="1")
			return true;
			else	
	if(passwStrength=="2" && alphabetCheck==true && numericCheck==true)
			return true;
			else
	if(passwStrength=="3" && alphabetCheck==true && numericCheck==true && specialCharCheck==true)
			return true;
			else	
			return false;
	
}

function clearForm(e)
{
if(e.type=="click"||e.keyCode==13)
	{
document.forms[0].reset();
    }
}
function cancelPage(e)
{
if(e.type=="click"||e.keyCode==13)
	{
document.forms[0].hmode.value="INIT";
document.forms[0].submit();
    }
}
--></script>
<his:css src="/css/calendar-blue2.css" />

 <body onload="document.forms[0].newPassword.focus(),checkForPasswordChange()" >

  <form name="form1" action="loginAction" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="<%=messageToShow%>" >
  </his:TitleTag>
    
  

	 <his:ContentTag>
	  <table width="100%" border="0" cellspacing="1" cellpadding="0">
		  
		        		   
		     <tr>
			<td width="50%" class="tdfonthead">
			  <div align="right">
				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
				</font> 
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								New Password&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    		  <input type="password" name="newPassword"  maxlength="10" tabindex="1" onchange="validateMinLength(this,6);" ><font color='red'>(6 to 10 characters)<br>
		     The Password is case sensitive. 
		     <br>
		     <%=passwordStrengthMsg%>
		     
		     </font>
						   
			  </div>
			 </td>  
		    </tr>
		        <tr>
			<td width="50%" class="tdfonthead">
			  <div align="right">
				<font color="RED" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								*
				</font> 
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 Confirm Password&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%"  class="tdfonthead">
			  <div align="left">
			    		  <input type="password" name="confirmPassword" maxlength="10" tabindex="1" onchange="validateMinLength(this,6);" > 
						   
			  </div>
			 </td>  
		    </tr>
		</table>
		
<his:ConfirmYesNoTag >
</his:ConfirmYesNoTag>		
			  

			   
		 </his:ContentTag>
			  
			  
		 <his:ButtonToolBarTag >
			   <div align="center" id="allButtons">
			      <img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">
			     <img class="button" 	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick="clearForm(event)" 	onkeypress="clearForm(event)">
			  	  <img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	onkeypress=" cancelPage(event)" tabindex="1" onclick="cancelPage(event)">
			   </div>
			</his:ButtonToolBarTag>
		
</his:TransactionContainer>		
  	<font color="red"><strong><%=(String)request.getAttribute("message") %></strong></font>
  	
  	<input type="hidden" name="hmode">
  	<input type="hidden" value="<%=oldPassword%>" name="oldPassword">
  	<input type="hidden" value="<%=uid%>" name="uid">
  	<input type="hidden" value="<%=pwd%>" name="pwd">
 
  </form>
 </body>
</html>

