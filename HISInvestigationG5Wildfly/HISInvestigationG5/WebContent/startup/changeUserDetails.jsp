<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,startup.*" %>
<jsp:useBean id="loginObj" class="startup.login" scope="request" >
<jsp:setProperty property="*" name="loginObj" />
</jsp:useBean>

<%
  String empid=(String)session.getAttribute("EMPID");	
 // 	System.out.println("empid from session in change user details -----"+empid);
  	
  	String readOnly="";
  	
  	if(empid==null || empid.equals(""))
  	{
  		readOnly="false";
  	}else
  	if((!empid.equals("")) || empid!=null)
  	{
  		readOnly="true";
  	}	

if(loginObj.getUserFullName()==null || loginObj.getUserFullName().equals(""))
loginObj.setUserFullName((String)session.getAttribute("UserFullName"));
else
session.setAttribute("UserFullName",loginObj.getUserFullName());


if(loginObj.getHintQuestion()==null || loginObj.getHintQuestion().equals(""))
loginObj.setHintQuestion((String)session.getAttribute("QUESTION_ID"));
else
session.setAttribute("QUESTION_ID",loginObj.getHintQuestion());

if(loginObj.getAnswer()==null || loginObj.getAnswer().equals(""))
loginObj.setAnswer((String)session.getAttribute("ANSWER"));
else
session.setAttribute("ANSWER",loginObj.getAnswer());

//System.out.println("mobile no---"+loginObj.getMobileNo());

if(loginObj.getMobileNo()==null || loginObj.getMobileNo().equals(""))
loginObj.setMobileNo((String)session.getAttribute("MOBILE_NO"));
//else
//session.setAttribute("MOBILE_NO",loginObj.getMobileNo());



if(loginObj.getEmailId()==null || loginObj.getEmailId().equals(""))
loginObj.setEmailId((String)session.getAttribute("EMAIL_ID"));
//else
//session.setAttribute("EMAIL_ID",loginObj.getEmailId());

//System.out.println("email id before -----"+loginObj.getEmailId());


if(loginObj.getMobileNo()==null || loginObj.getMobileNo()=="null" || loginObj.getMobileNo().equals("null")  )
   	  loginObj.setMobileNo("");
  
if(loginObj.getEmailId()==null || loginObj.getEmailId()=="null" || loginObj.getEmailId().equals("null") )
	loginObj.setEmailId("");
	

//System.out.println("email id after -----"+loginObj.getEmailId());



String hintQuest="";
  hintQuest=loginObj.getHintQuestion();
 //System.out.println("hintQuest......"+hintQuest);
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
	
	if(document.forms[0].userFullName.value=="") 
     {
     flag=false;
     alert("Enter  Name");
     document.forms[0].userFullName.focus();
     }else
	if(document.forms[0].hintQuestion.value==0) 
     {
     flag=false;
     alert("Select Hint Question");
     document.forms[0].hintQuestion.focus();
     }else
     if(document.forms[0].answer.value==0) 
     {
     flag=false;
     alert("Enter Answer");
     document.forms[0].answer.focus();
     }	
	if(flag==true &&  validateEmail())
		{
		document.forms[0].hmode.value="CHANGE_USER_DETAILS_CHECK";
		document.forms[0].submit();
		}
		
	}
}

function clearForm(e)
{
if(e.type=="click"||e.keyCode==13)
	{


if(<%=readOnly%>!=true)
	document.forms[0].userFullName.value="";

document.forms[0].hintQuestion.value="0";
document.forms[0].answer.value="";
document.forms[0].mobileNo.value="";
document.forms[0].emailId.value="";
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


function validateEmail(){
	var valid=true;
	var email=document.getElementsByName("emailId")[0].value;
	//alert(email.length)
	if(email.length>0){
		var s=email.split("@");
		var index=email.indexOf("@");
		if(s.length!=2){
			valid=false;
		}
		else{
			//alert(email.lastIndexOf(".")- email.indexOf(".",index))
			//alert(email.length - email.lastIndexOf("."))
			if(s[1].split(".").length>2){
				if((email.length - email.lastIndexOf("."))==3 && ((email.lastIndexOf(".")- (email.indexOf(".",index))==3))){
					valid=true;
				}
				else{
					valid=false;
				}
				
			}
			else{
				if((email.length - email.lastIndexOf("."))==4){
					valid=true
				}
				else{
					valid=false;
				}
			}	
			if((email.indexOf(".",index)- index)==1){
				valid=false;
			}
		}
	}
	if(!valid){
		alert("Enter valid email")
		document.getElementsByName("emailId")[0].focus();
	}	
	return valid;
}


</script>
<his:css src="/css/calendar-blue2.css" />

 <body onload="document.forms[0].userFullName.focus()">

  <form action="UpdateUser" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="Change User Details">
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
								User Name&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    		   <input type="text" name="userFullName" tabindex="1" <% if(readOnly.equals("true")) {%>readonly="true" <%}%> onkeypress="return validateData(event,9);" maxlength="50" value="<%=loginObj.getUserFullName()%>">
						   
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
								Hint Question
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    <select name="hintQuestion" style="width: 111px"  tabindex="1">
			    <option value=0 <%if(hintQuest.equals("0")){ %> selected <%}%> >Select  Question</option>
			    <%
			     ArrayList questionCollection=new ArrayList();
   				 questionCollection=(ArrayList)request.getAttribute("QUESTION_COLLECTION");
   				
   				
   
			  for(int i=0; i < questionCollection.size() ; i=i+2)
			     {
			    	    
			     %>
			     <option value="<%=questionCollection.get(i) %>" <%if(hintQuest.equals(questionCollection.get(i))){ %> selected <%}%>  ><%=questionCollection.get(i+1)%></option>
			     <%
			     }
			      %>
			    </select>  
						   
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
								Answer&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    		   <input type="text" name="answer" maxlength="10" tabindex="1" onkeypress="return validateData(event,8);" value="<%=loginObj.getAnswer()%>">
						   
			  </div>
			 </td>  
		    </tr>
		       
		        
		     <tr>
			<td width="50%" class="tdfonthead">
			  <div align="right">
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								 Mobile No.&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%"  class="tdfonthead">
			  <div align="left">
			    		  <input type="text" name="mobileNo" maxlength="10" tabindex="1" onchange="validateMinLength(this,6);" onkeypress="return validateData(event,5);" value="<%=loginObj.getMobileNo()%>"> 
						   
			  </div>
			 </td>  
		    </tr>
		     <tr>
			<td width="50%" class="tdfonthead">
			  <div align="right">
				
				<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif"> 
								  Email
				</font>
			  </div>
			</td>
			<td width="50%"  class="tdfonthead">
			  <div align="left">
			    		  <input type="text" name="emailId" maxlength="50" tabindex="1" onchange="validateMinLength(this,6);" onkeypress="return validateData(event,1);" value="<%=loginObj.getEmailId()%>"> 
						   
			  </div>
			 </td>  
		    </tr>
		</table>
			   
		 </his:ContentTag>
		 </his:TransactionContainer>	
			<his:ButtonToolBarTag>
			  <div align="center" >  
			   <img class="button" tabindex="1" src='<his:path src="/hisglobal/images/btn-sv.png"/>' style="cursor: pointer" onkeypress="finalSubmit(event)" onclick="finalSubmit(event)">
			    <img class="button" 	src='<his:path src="/hisglobal/images/btn-clr.png"/>' style='cursor:pointer' tabindex="1" onclick="clearForm(event)" 	onkeypress=" clearForm(event)">
			   <img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	 onkeypress="cancelPage(event)" onclick="cancelPage(event)"> 
			  </div>
			   </his:ButtonToolBarTag>
	<font color="red"><strong><%=request.getAttribute("message")%></strong></font>
<input type="hidden" name="hmode"/><input type="hidden" name="hmode_new"/>
  </form>
 </body>
</html>

