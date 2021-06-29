/*********######## FUNCTIONS USED FOR AUTO TAB OPENING AT UNIPAGE PRESCIPTION ######****/

function VRSetDataExist() // VISIT TRESAON TAB
{
	//alert(document.getElementsByName("isSet_OPDNEXTVISITDETAIL")[0].value)
	if(document.getElementsByName("isSet_OPDNEXTVISITDETAIL")[0].value=="1")
	{
	//alert("inside")
	$("#visitDetailBody").removeClass("collapse");
	$("#visitDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#visitDetailBody").addClass("collapse");
		$("#visitDetailBody").addClass("out");
		}
}

function FUSetDataExist() // FOLLOWUP TAB
{
	//alert(document.getElementsByName("isSetFOLLOWUP")[0].value)
	if(document.getElementsByName("isSetFOLLOWUP")[0].value=="1")
	{
	//alert("inside")
	$("#followupDetails").removeClass("collapse");
	$("#followupDetails").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#followupDetails").addClass("collapse");
		$("#followupDetails").addClass("out");
		}
}

function TDSetDataExist() //AUTO OPEN TREATMENT TAB
{
	//alert(document.getElementsByName("isSetTREATMENT")[0].value)
	if(document.getElementsByName("isSetTREATMENT")[0].value=="1")
	{
	//alert("inside")
	$("#treatmentDetailBody").removeClass("collapse");
	$("#treatmentDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#treatmentDetailBody").addClass("collapse");
		$("#treatmentDetailBody").addClass("out");
		}
}

function DIAGSetDataExist()  //AUTO OPEN DIAGNOSIS TAB
{
	//alert(document.getElementsByName("isSetDIGANOSIS")[0].value)
	if(document.getElementsByName("isSetDIGANOSIS")[0].value=="1")
	{
	//alert("inside")
	$("#diagnosisDetailBody").removeClass("collapse");
	$("#diagnosisDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#diagnosisDetailBody").addClass("collapse");
		$("#diagnosisDetailBody").addClass("out");
		}
}