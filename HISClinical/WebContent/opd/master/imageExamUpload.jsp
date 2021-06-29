
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<%@page import="hisglobal.hisconfig.Config"%>
<%-- <his:css src="/hisglobal/css/Color.css"/>
<his:css src="/hisglobal/css/master.css"/>
<his:css src="/hisglobal/css/hisStyle.css"/>
<his:css src="/hisglobal/css/hisStyleExt.css"/>
<his:css src="/hisglobal/css/calendar-blue2.css"/> --%>
	<his:javascript src="/registration/js/popup.js"/>
	<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/masterutil/js/master.js"/>  
<his:javascript src="/hisglobal/js/util.js"/>
<%@ page import ="registration.*,hisglobal.presentation.*" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<script>
function submitform(event)
{
	//alert("helloo");
	var imageName = document.getElementsByName("uploadImageName")[0].value;
	//var imgExtName=document.getElementsByName("uploadImageName")[0].text;
	//alert(imageName);
	var ext = imageName.substring(imageName.lastIndexOf("."),imageName.length).toUpperCase();
	document.getElementsByName("imageName")[0].value=imageName;
	if(imageName==""){
		alert('Please Select A File');		
	}
	//else if(ext==".JPG" || ext==".JPEG" || ext==".GIF" || ext==".PNG" || ext==".BMP")
	else if(ext==".JPG" || ext==".JPEG")
	{
		var finalfile="";
		var file="";
		var strfile=document.getElementById('uploadImageName');
		var str = document.getElementById('uploadImageName').files[0];
		//alert(str.size);
		if(str.size<1000000){
		var reader = new FileReader();
		
		 reader.readAsDataURL(str);
		 reader.onload = function () {
		    // alert(reader.result);
		     finalfile=reader.result;
		    
		   
		  // var xyz= reader.readAsDataURL(str);
		//alert(str);
		
		//alert(finalfile.length);
		//finalfile= finalfile.split(",")[1];
		document.getElementsByName("uploadedFile")[0].value= finalfile;
		submit4image(finalfile,imageName.split('\\')[2]);
		 };}
		 else
			 {
			alert("Please upload file less than 1MB");
			 }
		//console.log($('[name="uploadedFile"]').val());
		//var form=$('#imageExamUpload')
		//var form = $('#imageExamUpload')[0];
		//var imgData=new FormData(form);
		//alert(form.serialize());
			/*  $.ajax({
		 type: "GET",
         url: '/HISClinicalWB/opd/master/imageUpload.cnt?transactionMode=SAVEImageUpload',
          */
         /* data: form.serialize(), */
        /*  success: function (data) {
	       alert("inside sucess");
        	 parent.submit4image();
         	$('#myModalUpload').modal('toggle');
        	
             }, 
	  error: function(data)
      {
	   alert("jhsdjk");
    	}
      });  */
		 
		/* elem = document.getElementsByName('transactionMode')[0];
		elem.value = "SAVE" ;
		document.forms[0].submit();	 */
	}
	else
	   // alert('Please Select Files With Following Formats:GIF/JPG/JPEG/PNG/BMP');
		 alert('Please Select Files With Following Formats:JPG/JPEG');
}

function isFormClose()
{
	var formclose=true;
  	<%     
		Status objStatus=(Status)request.getAttribute(Config.STATUS_OBJECT);
    	if(objStatus.contains(Status.NEW)){
    	%>    	
    		formclose=false;    	   	
    	<%
    }    
    %>
    if(formclose)
    {
        alert("hiii");
    	/* if(!parent.closed)
    	{ */
    		
    		
    	
	    	//self.close();
    	/* } */
    }
}
</script>

<body onload="isFormClose()">
	<form enctype="multipart/form-data" action="/master/imageUpload.cnt" method="post" id="imageExamUpload">
	
				<table class="table-condensed table-responsive">
					<tr>
						<td style="FONT-FAMILY: 'MS Sans Serif', Arial, Helvetica, 'sans-serif';font-size: 0.8em;">
							<b>
								Select a Image To Upload
							</b>
							</td>
							<td>
							<input type="file" class="form-control" name="uploadImageName" id="uploadImageName" ></input></td>
							<td>
					<button type="button" value="Attach" data-dismiss="modal" onclick="submitform(event)" class="btn btn-info btn-sm">Attach</button>
						</td>
					</tr>
				</table>
		
			
		
				<!-- <button value="Attach" data-dismiss="modal" onclick="submitform(event)">Attach</button> -->
		
		<html:hidden name="ImageUploadFB" property="uploadedFile"/>
		<html:hidden name="ImageUploadFB" property="transactionMode"/>
		<html:hidden name="ImageUploadFB" property="imageName"/>
		<html:hidden name="ImageUploadFB" property="hmode"/>
	</form>

</body>