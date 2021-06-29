/**/
function tableShow(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/AHIMS/hisglobal/images/minus.gif");

}

function tableHide(strTableId, imageElement) {

	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/AHIMS/hisglobal/images/plus.gif");

}

function validateCancel(mode) {
	var hisValidator = new HISValidator("complaintMaintenanceStatusBean");

	
	hisValidator.addValidation("strCancelTypeId", "dontselect='0'",
	"Please Select a Cancel Type.");
	hisValidator.addValidation("strCancelRemarks", "req",
	"Cancel Remarks is a mandatory field.");
	hisValidator.addValidation("strCancelRemarks", "maxlen=100",
			"Cancel Remarks cannot exceeds 100 characters.");
	
	

	var retVal = hisValidator.validate();

	hisValidator.clearAllValidations();

	if (retVal) {
		document.forms[0].hmode.value = mode;
		document.forms[0].submit();
	}
}


function cancelScheduleCancel() {
	
	document.forms[0].hmode.value = "unspecified";
	document.forms[0].submit();
	
}
function onUploadedFileName(obj,index,fileId)
{
	var file=fileId;
	//alert(document.forms[0].strUploadFileId.value);
		if(fileId!='0')
		{
			document.forms[0].hmode.value="GETUPLOADEDFILE"; 
			document.forms[0].strUploadFileId.value=file;
			document.forms[0].target = "_blank";
			document.forms[0].submit();
		}
		else
		{
			alert("No File to Up-Load!!");			
		}
	
}
