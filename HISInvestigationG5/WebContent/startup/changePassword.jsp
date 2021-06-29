<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,startup.*" %>

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
UserMgmtConfigXmlHandler configHandler=new UserMgmtConfigXmlHandler();
int paswStrength=configHandler.getPasswStrength();

String passwordStrengthMsg="";


if(paswStrength==2)
	 passwordStrengthMsg="Password Must Contain atleast one Alphabet and one number";
	else
if(paswStrength==3)
	 passwordStrengthMsg="Password Must Contain atleast one Alphabet,one number and one Special Character";


%>


<script type="text/javascript">
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
	
	 if(document.forms[0].oldPassword.value=="") 
     {
     flag=false;
     alert("Enter Old Password");
     document.forms[0].oldPassword.focus();
     
     }else
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
     }else		
      if(document.forms[0].newPassword.value!=document.forms[0].confirmPassword.value) 
     {
     flag=false;
     alert("New Password and Confirm Password are not Same");
     }else		
      if(document.forms[0].newPassword.value==document.forms[0].oldPassword.value) 
     {
     flag=false;
     alert("New Password and Old Password  Cannot be Same");
     }else	
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
		document.forms[0].hmode.value="CHANGE_PASSWORD_CHECK";
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
document.forms[0].action="../startup/initPage.jsp";
document.forms[0].submit();
    }
}
</script>
<his:css src="/css/calendar-blue2.css" />

 <body onload="document.forms[0].oldPassword.focus()">

  <form action="UpdateUser" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="Change Password Details">
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
								Old Password&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    	  <input type="password" name="oldPassword"  tabindex="1"  ></div>
			 </td>  
		    </tr>
		   
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
			    		  <input type="password" name="newPassword" maxlength="10" tabindex="1" onchange="validateMinLength(this,6);" ><font color='red'>(6 to 10 characters)<br>
		     The Password is case sensitive. <br>
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
			   
		 </his:ContentTag>
			  
		 <his:ButtonToolBarTag>
			   <div align="center" >
			   <img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">
			   <img class="button" 	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick="clearForm(event)" 	onkeypress="clearForm(event)">
			   <img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	onkeypress=" cancelPage(event)" tabindex="1" onclick="cancelPage(event)"> 
			   
			   </div>
			</his:ButtonToolBarTag>
</his:TransactionContainer>		
  	<font color="red"><strong><%=(String)request.getAttribute("message") %></strong></font>
  	
  	<input type="hidden" name="hmode">
 
  </form>
 </body>
</html>

