<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ page import="java.util.*,startup.*" %>
<%
String hmode=request.getParameter("hmode");
//System.out.println("hmode in jsp--->"+hmode);
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
			document.forms[0].oldPassword.value="";
			document.forms[0].oldPassword.focus();
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



	if(e.type=="click" || e.keyCode==13)
  {
  
	 if(document.form1.oldPassword.value=="") 
     {

     alert("Enter Your Password");
     document.getElementsByName("oldPassword")[0].focus();
      }
     else    
		{
	
		
		document.form1.hmode.value="AUTHENTICATION_CHECK";
		document.form1.submit();
		}
		
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

 <body onload="document.form1.oldPassword.focus()">

  <form name="form1" action="UpdateUser" method="post">
  
 <his:TransactionContainer>
  <his:TitleTag name="Authenticate Password ">
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
								 Password&nbsp;
				</font>
			  </div>
			</td>
			<td width="50%" class="tdfonthead">
			  <div align="left">
			    	  <input type="password" name="oldPassword"  tabindex="1"  onkeypress="finalSubmit(event)" >
			    	  <img class="button" 	src="../hisglobal/images/GoNew.png" tabindex="1" 	style="cursor:pointer; position:absolute;  height:22px; " 	onkeypress="finalSubmit(event)" tabindex="2" onclick="finalSubmit(event)">
			    	  </div>
			 </td>  
		    </tr>
		   
		     <tr>
			
			  
		    </tr>
		        <tr>
			
			  
		    </tr>
		</table>
			   
		 </his:ContentTag>
			  
		 </his:TransactionContainer> 
		 <his:ButtonToolBarTag>
			<div align="center" >  
			
			<img class="button" 	src='<his:path src="/hisglobal/images/btn-ccl.png"/>' tabindex="1" 	style=cursor:pointer 	onkeypress=" cancelPage(event)" tabindex="1" onclick="cancelPage(event)"> 
			
			</div>  
			</his:ButtonToolBarTag>
		
  	<font color="red" ><strong><%=(String)request.getAttribute("message") %></strong></font>
<input type="hidden" name="hmode" value="CHANGE_USER_DETAILS" />
  </form>
 </body>
</html>

