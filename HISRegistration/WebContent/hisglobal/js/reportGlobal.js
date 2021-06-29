function setReportOpenAsOption(calFlag)
{	
	if($('[name="strReportType"]')[0].value=='1')
	{
		$('#imgHtmlIconId').show();
		$('#imgPdfIconId').hide();
		$('#imgExcelIconId').hide();
	}
	else if($('[name="strReportType"]')[0].value=='2')
	{
		//alert("insdie pdf option");
		$('#imgHtmlIconId').hide();
		$('#imgPdfIconId').show();
		$('#imgExcelIconId').hide();
	}
	else if($('[name="strReportType"]')[0].value=='3')
	{
		$('#imgHtmlIconId').hide();
		$('#imgPdfIconId').hide();
		$('#imgExcelIconId').show();
	}	
	setLabelLangOptions(calFlag);
}

function setLabelLangOptions(calFlag)
{
	
	if(calFlag=='2'){
		if($('[name="strReportType"]')[0].value=='1')
		{
			$("#langEng").css('display','');
			$("#langTelgu").css('display','');
			$("#langBoth").css('display','');		
			//$('[name="strLabelLang"]')[0].value=1;
		}
		else if($('[name="strReportType"]')[0].value=='2')
		{
			//alert("insdie pdf option");
			$("#langEng").css('display','');
			$("#langTelgu").css('display','none');
			$("#langBoth").css('display','none');
			$('[name="strLabelLang"]')[0].value=1;
			
		}
		else if($('[name="strReportType"]')[0].value=='3')
		{
			$("#langEng").css('display','');
			$("#langTelgu").css('display','');
			$("#langBoth").css('display','');
			//$('[name="strLabelLang"]')[0].value=1;
		
		}
	}
	else{
		$("#langEng").css('display','');
		$("#langTelgu").css('display','none');
		$("#langBoth").css('display','none');
		$('[name="strLabelLang"]')[0].value=1;
	}
	
}

	function onChngHeaderReq(calFlag)
	{
		if($('#strIsHeaderReq').prop("checked")) {			
			 $("#strIsLogoReq").prop("disabled", false);
			 //$('#strIsLogoReq').prop('checked', true);
		} else {
			 $("#strIsLogoReq").prop("disabled", true);
			 $('#strIsLogoReq').prop('checked', false);
		}
	}
	
	function onChngFooterReq(calFlag)
	{
		
	}
	
	function onChngLogoReq(calFlag)
	{
		
	}
	
	function onChngWatermarkReq(calFlag)
	{
		
	}
	
	

