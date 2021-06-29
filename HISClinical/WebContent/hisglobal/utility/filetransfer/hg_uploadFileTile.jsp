<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/struts-tags" prefix="s" %>

<%@page import="hisglobal.hisconfig.Config"%>

<html>
<head>

<link href="/HIS/hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jquery-ui.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/jqueryExtValidationToolTip.css" rel="stylesheet" type="text/css">
<link href="/HIS/hisglobal/css/easyui.css" rel="stylesheet" type="text/css">

<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-2.0.3.min.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery.easyui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery-ui.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jqueryExtValidation.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/hashFunctions.js"></script>
<script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/commonFunctions.js"></script>
<%-- <script language="JavaScript" type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery.simplemodal.js"></script> --%>

<s:head/>

</head>

<body onload="isFormClose()"> 
<center>

<div class="wrapper rounded">

<s:form action="UploadFile" namespace="/" method="post" enctype="multipart/form-data">

<s:hidden name="fieldName"></s:hidden>
   
	<div class="div-table" >
		<div class="div-table-row " >
			<div class="div-table-col title width100 " >
					Upload File
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width40 label" >
				<font color="red">*</font>File Name 
			</div>
			<div class="div-table-col width60 control" >
				<s:file name="uploadedFile" id="uploadedFile" tabindex="1" accept="image/jpeg,image/jpg,application/pdf" onchange="verifyFileUpload(event)"/>
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width100 label" >
				<font style="text-align: center">(Supported Formats:JPG,JPEG(Min Resolution:1024px X 768px),PDF/A-2. Max file size:2MB)</font>
			</div>
		</div>
	</div>

	<div class="div-table-button">
		<div class="div-table-row footerBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row emptyBar">
			<div class="div-table-col"> </div>
		</div>
		<div class="div-table-row" align="center">
			<a href="#" class="button" tabindex="1" id="idAttach"><span class="add">Attach</span></a>
			<a href="#" class="button" tabindex="1" id="idClose"><span class="cancel">Close</span></a>
		</div>
	</div>

<s:hidden name="varStatus"></s:hidden>
<s:hidden name="strProcessId"></s:hidden>
<s:hidden name="strCRNoHospCode"></s:hidden>
<s:hidden name="strFileName"></s:hidden>
</s:form>
<div id="divElementErrorsId" class="alertMessage"><s:actionerror /></div>

<!--<script language="JavaScript" type="text/javascript" src="/hisglobal/utility/filetransfer/js/upload_file.js"></script>-->
<script type="text/javascript">

window.URL = window.URL || window.webkitURL;


var invalidFileTypeMessage="According to EHR Guidelines 2016, uploaded file can be only .jpg/.jpeg for images or .pdf for documents. Please upload valid file format!";
var imageResolutionErrorMessage="According to EHR Guidelines 2016, uploaded image file should have resolution of  1024px X 768px at 300dpi. Please upload image with the required specifications!";

var validation = true;

function verifyFileUpload(event)
{
	validation = true;
    //alert("JPEG file detected.");
	var flg = true;
	var file = event.target.files[0];
	var validFileTypes = ["image/jpg", "image/jpeg", "application/pdf"];
	var fileType = file["type"];
	if(file.type.match('image/jp.*'))
	{
        //alert("JPEG file detected.22");
        var file = document.getElementById("uploadedFile");
        
        if (file && file.files.length > 0) 
        {
              var img = new Image();
              //alert("Found Image File");
              img.src = window.URL.createObjectURL( file.files[0]);
              img.onload = function() 
              {
                  var width = img.naturalWidth,
                   height = img.naturalHeight;
                  
                  if (width<1024 || height<768){
                	  alert(imageResolutionErrorMessage);
                	  document.getElementsByName("uploadedFile")[0].value = "";
              		flg = false;
//                	  alert("Image Width: " + width + "Image Height: " +height);
                  }
              if(file.files.length>2097152){
            	  alert("Upload a file of size less than 2MB");
        	  document.getElementsByName("uploadedFile")[0].value = "";
              }
              };
          }
    }
	else if(file.type.match('application/pdf'))
	{
		 //alert("PDF file detected.");
	}
	else
	{
		alert(invalidFileTypeMessage);
		flg = false;
	}
	if(!flg)
		{
		//alert("FF");
		validation = false;
		document.getElementsByName("uploadedFile")[0].value = "";
		}
}

function isFormClose()
{
	var formclose=true;
	if(document.getElementsByName("varStatus")[0].value=="INITIAL")
	{
		formclose=false;    	   	
	}
	else if(document.getElementsByName("varStatus")[0].value=="SIZEXCEED")
	{
		formclose=false;    	   	
		alert("File size is greater than 4 MB please upload file upto 4 MB");
    }
	if(formclose)
	{
		if(!window.opener.closed)
		{
	        //window.opener.location.reload();
	        //elem = document.getElementsByName('hmode')[0];
			//elem.value = "REFRESHFORIMAGE";\		
			//opener.submitForFileUpload();
			if(opener.submit4image) opener.submit4image();
		    self.close();
		}
	}
}

var uploadFile = {
		validateOnAttach : function(event)
		{
			var fileName = document.getElementsByName("uploadedFile")[0].value;
			var fileSize=document.getElementsByName("uploadedFile")[0];
			//alert(fileName);
			$('[name="strFileName"]')[0].value=fileName;
			var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
			//alert(fileSize.files[0].size);
			
			
			if(fileName=="")
			{
				alert('Please Select File');
				return false;		
			}	
			else 
			{
				if(fileSize.files[0].size>2097152)
				{
				alert("Please Upload a file less than 2MB");
				return false;
				}
				else
					return true;
				if(!validation)
				{
					return false;
				}
				else
					return true;

				if( ext==".JPG" || ext==".JPEG" ||  ext==".PDF" ) //ext==".GIF" || ext==".DOC" || ext==".DOCX"|| ext==".RTF"
				{
					//elem.value = "SAVE" ;
					//document.forms[0].submit();
					return true;
				}
				else
				{
					alert('Please Select Files With Following Formats:JPG/JPEG/PDF');
					return false;		
				}
			}
			return true;
		},
		submitOnAttach : function(event)
		{
			if(!uploadFile.validateOnAttach(event))
			{
				return;
			}
			else
			{
				//For Submission
			  	submitForm("attachUploadFile.action");
			}
		}
	};

	$('[name="uploadedFile"]').validatebox({
		required: true
	});

	// On Click of Attach
	$('#idAttach').click(function(e){
		uploadFile.submitOnAttach(e);
	});

	// On Click of Close
	$('#idClose').click(function(e){
		window.close();
		//parent.closeModal();
		//e.preventDefault();
	});
	
</script>	

</div>
</center>
</body>
</html>