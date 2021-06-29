<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,startup.*" session="false"%>
<jsp:useBean id="loginObj" class="startup.login" scope="request" />
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
<%
String status=(String)request.getAttribute("STATUS");
//out.println("status- in jsp--"+status);
if(status==null)
  status="INITIAL";
//out.println("status---"+status);

request.setAttribute("USER_ID",request.getAttribute("USER_ID"));
request.setAttribute("HOSPITAL_CODE",request.getAttribute("HOSPITAL_CODE"));
request.setAttribute("PASSWORD",request.getAttribute("PASSWORD"));
request.setAttribute("USER_NAME",request.getAttribute("USER_NAME"));
request.setAttribute("USER_SEATID",request.getAttribute("USER_SEATID"));
 %>
<his:css src="/hisglobal/css/Color.css" />
<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:css src="/hisglobal/css/hisStyleExt.css" />
<his:javascript src="/registration/js/registration.js"/>
<style type="text/css">
	@import url(../css/calendar-blue2.css);
</style>
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
			
		case 9:		//alphanumeric with underscore
			str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_";
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
	
	if(!copyPasteCheck(document.forms[0].userName[0].value,9))
      {
      alert("You Cannot Copy Paste Special Characters in User Id Field")
      document.forms[0].userName[0].value="";
      document.forms[0].userName[0].focus();
      }    
    else
   	if(document.forms[0].userName[0].value=="") 
     {
     flag=false;
     alert("Enter User Id");
     document.forms[0].userName[0].focus();
     }else
	if(document.forms[0].hintQuestion.value==0) 
     {
     flag=false;
     alert("Select Hint Question");
     document.forms[0].hintQuestion.focus();
     }else
     if(document.forms[0].answer.value=="") 
     {
     flag=false;
     alert("Enter Answer");
     document.forms[0].answer.focus();
     }else
    if(flag==true)
		{
		document.forms[0].hmode.value="FORGOT_PASSWORD";
		document.forms[0].submit();
		}
		
	}
}

function changePassword(e)
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
if(validateMinLength(document.forms[0].newPassword,6 ))
     {
     flag=false;
     alert("New Password length should be minimum 6");
     document.forms[0].newPassword.focus();
     
     }
     else
if(document.forms[0].confirmPassword.value=="") 
     {
     flag=false;
     alert("Enter Confirm Password");
     document.forms[0].confirmPassword.focus();
     }else
     
if(document.forms[0].confirmPassword.value!=document.forms[0].newPassword.value) 
     {
     flag=false;
     alert(" New Password and Confirm Password are not same");
     document.forms[0].confirmPassword.value="";
     document.forms[0].confirmPassword.focus();
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
		document.forms[0].hmode.value="NEW_PASSWORD";
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

function clearForm()
{
document.forms[0].reset();
}
function cancelPage()
{
window.close();
}


function passwordChanged()
{



<%if(status.equals("INITIAL") || status==null){ %> 
 document.forms[0].userName[0].focus();
  window.opener.document.getElementById("blanket").style.display='block';
 <%} else %>
<%if(status.equals("NEW_PASSWORD")){ %> 
 document.forms[0].newPassword.focus();
 window.opener.document.getElementById("blanket").style.display='block';
 <%} else %>

<%if(status.equals("PASSWORD_CHANGED")){ %> 
alert("Your Password Has Been Changed");
window.close();
 <%}%>
}


function unloadPage()
{
window.opener.document.getElementById("blanket").style.display='none';

window.opener.document.getElementById("goButton").style.visibility="visible";
window.opener.document.getElementById("uid").disabled=false;
window.opener.document.getElementById("pwd").disabled=false;


}
function copyPasteCheck(value,str)
{
//alert(value+"----------"+str);
var status=true;
var valString=getValidateStr(str)
for(i=0 ; i < value.length ; i++ )
	{
   var char=value.charAt(i)
   
 //  alert(char+"---------"+valString)
   var index=valString.indexOf(char)
   
 //  alert(index)
   
   		if (index > -1)
   		       {
   		       status=true;
   		       }
   		       else
   			   {
   			   status=false;
   			   break;
             
               }
    }
    
   // alert(status)
    
    return status;
}

</script>
<his:css src="/css/calendar-blue2.css" />

 <body  onload="passwordChanged()"  onunload="unloadPage()">

  <form action="loginAction" method="post" >
  <%
  loginObj.hintQuestions(request);
  String message=(String)request.getAttribute("message");
  if(message==null)
  message="";
   %>
   <% String displayDetails="";
		if(status.equals("INITIAL") || status==null)
			{
			 displayDetails=" Forgot Password Details ";
			}
			else
		if(status.equals("NEW_PASSWORD"))
			{
			 displayDetails=" New Password Details ";
			}
  %>
			 
 <his:TransactionContainer>
  <his:TitleTag name="<%=displayDetails%>" >
  </his:TitleTag>
   
  

	 <his:ContentTag>
		<table width="100%" border="0" cellspacing="1" cellpadding="0">
			<%
			if(status.equals("INITIAL") || status==null)
			{
			 %>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right"><font color="RED" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> 
				User Id</font></div>
				</td>
				<td width="50%" class="tdfonthead">
				<div align="left"><input type="text" name="userName"
					maxlength="20" tabindex="1" 
					onkeypress="return validateData(event,9);" onblur="return validateData(event,9);"></div>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right"><font color="RED" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> Hint Question
				</font></div>
				</td>
				<td width="50%" class="tdfonthead">
				<div align="left">
				 <select name="hintQuestion" style="width: 111px"  tabindex="1">
			    <option value=0>Select    Question</option>
			    <%
			     ArrayList questionCollection=new ArrayList();
   				 questionCollection=(ArrayList)request.getAttribute("QUESTION_COLLECTION");
   				
   				 
   
			  for(int i=0; i < questionCollection.size() ; i=i+2)
			     {
			    	    
			     %>
			     <option value="<%=questionCollection.get(i) %>" ><%=questionCollection.get(i+1)%></option>
			     <%
			     }
			      %>
			    </select>
			    </div>
				</td>
			</tr>

			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right"><font color="RED" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> Answer&nbsp; </font>
				</div>
				</td>
				<td width="50%" class="tdfonthead">
				<div align="left">
				<input type="password" name="answer" maxlength="10" tabindex="1" onkeypress="return validateData(event,8);" onkeydown="finalSubmit(event)" ><img class="button" tabindex="1" src="../hisglobal/images/GoNew.png" style="cursor: pointer; position: absolute; height: 22px" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)"></div>
				</td>
			</tr>
			<%
			}
			
			if(status.equals("NEW_PASSWORD"))
			{
			 %>
				<tr>
				<td width="50%" class="tdfonthead">
				<div align="right"><font color="RED" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> New Password&nbsp; </font>
				</div>
				</td>
				<td width="50%" class="tdfonthead">
				<div align="left">
				<input type="password" name="newPassword" 	maxlength="10" tabindex="1"	>
				<font color='red'>(6 to 10 characters)<br>
		     The Password is case sensitive. 
		     <br>
		     <%=passwordStrengthMsg%>		     
		     </font></div>
				</td>
			</tr>
			<tr>
				<td width="50%" class="tdfonthead">
				<div align="right"><font color="RED" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> * </font> <font
					color="#000000" size="2"
					face="Verdana, Arial, Helvetica, sans-serif"> Confirm Password&nbsp; </font>
				</div>
				</td>
				<td width="50%" class="tdfonthead">
				<div align="left"><input type="password" name="confirmPassword"  maxlength="10" tabindex="1"  onkeydown="changePassword(event);"></div>
				</td>
			</tr>
			<%
			}
			 %>
		</table>
			   
		 </his:ContentTag>
			  
		 <%
		if(status.equals("INITIAL") || status==null)
			{
		 %>
		  <his:ButtonToolBarTag>
			   <div align="center" >   
			    
			<img class="button" 	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick="clearForm()" 	onkeypress="if(event.keyCode==13) clearForm()">
			<img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelPage()" onclick="cancelPage()">  
			   </div>
			  </his:ButtonToolBarTag> 
			<%
			}
			
			if(status.equals("NEW_PASSWORD"))
			{
			 %>
		 <his:ButtonToolBarTag>	 
		 <div align="center" >  
		  <img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="changePassword(event)" onclick="changePassword(event)">
		  <img class="button" tabindex="1"	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style='cursor:pointer'  onclick="clearForm()" 	onkeypress="if(event.keyCode==13) clearForm()">
		  <img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-ccl.png"/>' style="cursor: pointer" onkeypress="if(event.keyCode==13) cancelPage()" onclick="cancelPage()">
		</div>
		</his:ButtonToolBarTag>
			 <%
			 }
			  %>  
  	<font color="red"><%=message%></font>
  	<input type="hidden" name="hmode"/>
  	
  	<input type="hidden" name="userId"       value="<%=request.getAttribute("USER_ID") %>"/>
  	<input type="hidden" name="hospitalCode" value="<%=request.getAttribute("HOSPITAL_CODE") %>"/>
  	<input type="hidden" name="pwd"          value="<%=request.getAttribute("PASSWORD") %>"/>
  	<input type="hidden" name="userName"     value="<%=request.getAttribute("USER_NAME") %>"/>
  	<input type="hidden" name="seatId"       value="<%=request.getAttribute("USER_SEATID") %>"/>
 </his:TransactionContainer>
  </form>
 </body>
</html>

