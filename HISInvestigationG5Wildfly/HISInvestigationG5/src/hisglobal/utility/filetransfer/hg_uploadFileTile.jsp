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

<s:head/>

</head>

<body onload="isFormClose()"> 
<center>

<div class="wrapper rounded">

<s:form action="Upload" namespace="/" method="post" enctype="multipart/form-data">

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
				<s:file name="uploadedFile" tabindex="1"/>
			</div>
		</div>
		<div class="div-table-row ">
			<div class="div-table-col width90 label" >
				<font style="text-align: center">( Supported Formats: jpg, png, doc, docx, rtf, pdf. Max file size: 2MB )</font>
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
			opener.submitForFileUpload();
		    self.close();
		}
	}
}

var uploadFile = {
		validateOnAttach : function()
		{
			var fileName = document.getElementsByName("uploadedFile")[0].value;
			$('[name="strFileName"]')[0].value=fileName;
			var ext = fileName.substring(fileName.lastIndexOf("."),fileName.length).toUpperCase();
			if(fileName=="")
			{
				alert('Please Select File');
				return false;		
			}	
			else 
			{
			if(ext==".PNG" || ext==".JPG" || ext==".JPEG" || ext==".GIF" || ext==".PDF" || ext==".DOC" || ext==".DOCX"|| ext==".RTF")
			{
				//elem.value = "SAVE" ;
				//document.forms[0].submit();
				return true;
			}
			else
			{
				alert('Please Select Files With Following Formats:GIF/JPG/JPEG/PDF/RTF/DOC');
				return false;		
			}
			}
			return true;
		},
		submitOnAttach : function()
		{
			if(!uploadFile.validateOnAttach())
			{
				return;
			}
			else
			{
				//For Submission
			  	submitForm("attachUploadFile");
			}
		}
	};

	$('[name="uploadedFile"]').validatebox({
		required: true
	});

	// On Click of Attach
	$('#idAttach').click(function(e){
		uploadFile.submitOnAttach();
	});

	// On Click of Close
	$('#idClose').click(function(e){
		parent.closeModal();
		e.preventDefault();
	});

</script>	

</div>
</center>
</body>
</html>