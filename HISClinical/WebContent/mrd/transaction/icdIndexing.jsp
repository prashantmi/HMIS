<%
	try{

	
%>


<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
 <%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="java.util.ArrayList"%>
<%@page import="hisglobal.presentation.WebUTIL"%>
<%@page import="mrd.transaction.controller.fb.IcdIndexingFB"%>
<%@page import="java.util.Map"%> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<his:css src="/hisglobal/css/calendar-blue2.css" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/calendar.js" />
<his:javascript src="/registration/js/validationCommon.js" />
<his:javascript src="/registration/js/validationCalls.js" />
<his:javascript src="/registration/js/commonFunctions.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/dateFunctions.js" />
<his:css src="/hisglobal/css/Color.css" />
<script type="text/javascript" src="/HISClinical/hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>

<his:css src="/hisglobal/css/master.css" />
<his:css src="/hisglobal/css/hisStyle.css" />
<his:javascript src="/hisglobal/js/utilityFunctions.js" /> 

<script type="text/javascript">
function validateGO()
{	
	
	

		
	if(	document.getElementsByName("addmissionNo")[0].value=="")
		{
		
		alert("please enter Admission Numder!");
		return false;
		}
	

	var sNumber =document.getElementsByName("addmissionNo")[0].value;
	
	    output = [];
	    

	for (var i = 0, len = sNumber.length; i < len; i += 1) {
	    output[i]= sNumber.charAt(i);
	}

	    
	if(output.length<15){
		
		alert("invalid Admission No.");
		return false;
	}
	
	
	
	
	
	submitForm21('GOGETDATA');
}

function cancelFunction(){
	
	submitForm21('CANCEL');
}






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

}



function getValidateStr(index)
{
        var str = "";
        
        switch(index)
        {
                case 1:                //for validating email
                        str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-_@.";
                        break;
                        
                case 2:                //for validating telephone no
                        str = "1234567890-";
                        break;
                        
                case 3:                //for validating address
                        str = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-/,.#$()';: ";
                        break;
                        
                case 4:                //for validating name
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .";
                        break;
                        
                case 5:                //numeric only
                        str = "1234567890";
                        break;
                        
                case 6:                //numeric with space
                        str = "1234567890 ";
                        break;
                        
                case 7:                //for validating amount
                        str = "1234567890.";
                        break;
                         
                case 8:                //alphanumeric
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
                        break;
                        
                case 9:                //alphanumeric with space
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 ";
                        break;
                        
                case 10:        //Character only
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        break;
                        
                case 11:        //Character with space
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ ";
                        break;
                        
                case 12:        //Upper character only
                        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        break;
                        
                case 13:        //Upper character with space
                        str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ ";
                        break;
                        
                case 14:        //Lower character
                        str = "abcdefghijklmnopqrstuvwxyz";
                        break;
                        
                case 15:        //Lower character with space
                        str = "abcdefghijklmnopqrstuvwxyz ";         
                        break;
                        
                case 16:   //for entering the test name
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-().1234567890+'/&$ ";
                        break;
                
                case 17:        //for validating batchno
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890-/()*<>.;:{}[]%";
                        break;
                        
                case 18:                //alphanumeric with space and some special characters
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 (){}_-/";
                        break;        
                
                case 19:                //alphanumeric with space and some special characters
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 %_/";
                        break;        
                case 20:                //Folder Name
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 (){}[]_-!@#$%^&',.;";
                        break;        
                case 21:                //Alphabet with space and special characters ( .,-()[] )
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ .,-()[]";
                        break;        
                case 22:                //Alphabet with space and special characters ( .,-()[] )
                        str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890 .,-()[]";
                        break;        
        }        //end of Switch statement
        return str;
}




</script>

<style>
 .table td {
   font-size: 200px;
 }
</style>

<body>
<html:form action="/IcdIndexing">

	<his:TransactionContainer>
				<his:SubTitleTag name="ICD Indexing">
				</his:SubTitleTag>
	<his:ContentTag>
	
	<table>
	<tr>
	
	<td width="13%" class="tdfonthead" >
					<div align="left"  >
						<font color="#ff0000" face="Verdana, Arial, Helvetica, sans-serif" size="1">*</font>
						<font color="#000000" size="2" face="Verdana, Arial, Helvetica, sans-serif">
						
						<B><bean:message key="admissionNo"/></B>
						</font>
					</div>
				</td>
				<td width="10%" class="tdfonthead">
					<div align="left">
					
										
						<html:text 
							name="IcdIndexingFB" property="addmissionNo"
							tabindex="1" size="25"  maxlength="15" 
							onkeypress="return validateData(event,5);"
							onchange="return validateData(event,5);" />
					</div>
				</td>
		<td width="100%" class="tdfonthead" colspan="20" >
		
                  <img class="button"
					src='<his:path src="/hisglobal/images/GoNew.png"/>'
					style="cursor: pointer" align="left"
					onkeypress="if(event.keyCode==13) validateGO();" tabindex="1"
					onclick="validateGO();">				</td>
					<td class="tdfont" width="49%" colspan="10"  align="left">
					<div align="left">
					</div>
					</td>
					
		
			
			</table>
			<font size="2" face="Courier New" >
	</his:ContentTag>						
	<his:ButtonToolBarTag>
					<img class="button" src='<his:path src="/../HIS/hisglobal/images/buttons/btn-ccl.png"/>'  style=cursor:pointer tabindex="1" onclick =" cancelFunc()" onkeypress="if(event.keyCode==13) cancelFunc()">
				</his:ButtonToolBarTag>
				
				<div align="left" >
				<logic:notEqual name="IcdIndexingFB" property="statusMessage" value="">
					<font  color="red" ><b>	<label ><bean:write name="IcdIndexingFB"  property="statusMessage" /> </label> </b></font>
			
			</logic:notEqual>
			
			</div>
				
</his:TransactionContainer>
<html:hidden name="IcdIndexingFB" property="hmode" />
<html:hidden name="IcdIndexingFB" property="patCrNo" value=""/>
<html:hidden name="IcdIndexingFB" property="addmissionNo" />
 
</html:form>
</body>
</html>
<%
	} catch(Exception e) 
	{e.printStackTrace();}
%>
