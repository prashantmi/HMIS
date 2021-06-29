<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@page import="hisglobal.hisconfig.Config"%>
<his:css src="/hisglobal/css/Color.css"/>
	<his:css src="/hisglobal/css/master.css"/>
	<his:css src="/hisglobal/css/hisStyle.css"/>
	<his:css src="/hisglobal/css/hisStyleExt.css"/>
	<his:javascript src="/registration/js/popup.js"/>
<%@ page import ="registration.*,hisglobal.presentation.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function reflectDiagnosis(){
// alert("sdsdsdSDSD");
arrSelection=new Array();
elem = document.getElementsByName("checkControl");
// alert("length..."+elem.length);

for(i=0;i<elem.length;i++){
	if(elem[i].checked){	    
	  arrSelection[arrSelection.length]=elem[i].value;					
		}
}

// alert("after setting array values");
if(arrSelection.length!=0){
self.close();
callToPopulate(arrSelection);
}
else
alert("Select Provisional Diagnosis");
}

function submitform(){    

	// alert("field name"+document.getElementsByName("fieldName")[0].value)
	var fileName = document.getElementsByName("uploadedFileName")[0].value;
	var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
	//alert(ext);
	if(fileName==""){
		alert('Please Select A File');		
	}	
	else if(ext==".JPG" || ext==".JPEG" || ext==".GIF" || ext==".PDF"){
		elem = document.getElementsByName('hmode')[0];
		elem.value = "SAVE" ;
	// 	alert(elem.value);		
		document.forms[0].submit();	
		}
	else
	    alert('Please Select Files With Following Formats:GIF/JPG/JPEG/PDF');
}		

function isFormClose(){
  var formclose=true;
  <%     
	Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    if(objStatus.contains(Status.NEW)){
    	%>    	
    	formclose=false;    	   	
    	<%
    }    
    %>
    <%
    if(objStatus.contains(Status.ERROR_DA))
    {
    %>
    alert("File size is greater than 4 MB please upload file upto 4 MB");
    <%	
    }
    %>
 //    alert("formclose"+formclose)
    if(formclose){
   // alert("close")
       if(!window.opener.closed){
        //window.opener.location.reload();
        //elem = document.getElementsByName('hmode')[0];
		//elem.value = "REFRESHFORIMAGE";\		
		opener.submit4image();
	    self.close();
        }
    }
}
</script>


<body onload="isFormClose()"> 

	<html:form enctype="multipart/form-data" action="/uploadFile.cnt" method="post">	
	<%
	   System.out.println("inside upload file .jsp");
	%> 
	   <%
	   System.out.println("inside layout---- displayed Header"); %>
	   <his:TransactionContainer>
	   
	   <his:ContentTag>
	   <table>
	   <INPUT TYPE="hidden" name="fieldName" VALUE='<%=request.getParameter("fieldName")%>' >
	   <tr>
	   <td class="tdfont">
	   <B>
			<bean:message key='fileName'/>
		</B>
		<html:file name="FileUploadFB" property="uploadedFileName" tabindex="1"/>		
	  </td>
	  </tr>
	  </table>
	  </his:ContentTag>
    <%--<html:file name="commonTransactionFB" property="uploadedFileName" size="50" maxlength="255" styleId="fname"/>	--%>
    
    <his:ButtonToolBarTag>
	<html:button value="Attach"  property="mybutton" onclick="submitform()" tabindex="1"/>		
    <input type= "hidden" name="hmode"/>
    </his:ButtonToolBarTag>
   
   </his:TransactionContainer>
  
	</html:form>
	
</body>