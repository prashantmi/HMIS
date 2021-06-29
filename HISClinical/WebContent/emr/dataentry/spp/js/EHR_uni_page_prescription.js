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

function SDSetDataExist() // DISCHARGE TAB
{
	//alert(document.getElementsByName("isSetSD")[0].value)
	if(document.getElementsByName("isSetSD")[0].value=="1")
	{
	//alert("Added By Nilesh Gupta")
	$("#collapseDischarge").removeClass("collapse");
	$("#collapseDischarge").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#collapseDischarge").addClass("collapse");
		$("#collapseDischarge").addClass("out");
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



function EXAMSetDataExist()  //AUTO OPEN EXAMINATION TAB
{
	//alert(document.getElementsByName("isSetDIGANOSIS")[0].value)
	if(document.getElementsByName("isSetEXAMINATION")[0].value=="1")
	{
	//alert("inside")
	$("#examinationDetailBody").removeClass("collapse");
	$("#examinationDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#examinationDetailBody").addClass("collapse");
		$("#examinationDetailBody").addClass("out");
		}
}

function COMPLAINTSetDataExist()  //AUTO OPEN EXAMINATION TAB
{
	//alert(document.getElementsByName("isSetDIGANOSIS")[0].value)
	if(document.getElementsByName("isSetCOMPLAINTS")[0].value=="1")
	{
	//alert("inside")
	$("#complaintsDetailBody").removeClass("collapse");
	$("#complaintsDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#complaintsDetailBody").addClass("collapse");
		$("#complaintsDetailBody").addClass("out");
		}
}

function HISTORYSetDataExist()  //AUTO OPEN EXAMINATION TAB
{
	//alert(document.getElementsByName("isSetDIGANOSIS")[0].value)
	if(document.getElementsByName("isSetHISTORY")[0].value=="1")
	{
	//alert("inside")
	$("#historyDetailBody").removeClass("collapse");
	$("#historyDetailBody").removeClass("out");
	}
	else
		{
	//alert("after save")
		$("#historyDetailBody").addClass("collapse");
		$("#historyDetailBody").addClass("out");
		}
}
//Added by prachi on 26-apr-2019

$('input:radio').change(function(){
	//alert("hi");		
     Enable_Save();  
});

$('input:text').keyup(function(){

//alert("hi");
Enable_Save();  
});

$('input:text').change(function(){

	//alert("hi");
	Enable_Save();  
	});

$('input:checkbox').click(function(){
	//alert("hi");		
	 Enable_Save();     
});



/*$('select').mouseover(function(){

	//alert("hi");
	Enable_Save();  
	});*/

$('input:textarea').keyup(function(){

	//alert("hi");
	 Enable_Save();  	
	});
$('input:textarea').change(function(){

	//alert("hi");
	 Enable_Save();  	
	});


	 function Enable_Save(){
		// alert("hi"); // commented by manisha 9.1.20
	/*	if(document.getElementById("saveId").style.display=="none")   
	{
			
		document.getElementById("generatebtn").style.display="none";
	    document.getElementById("saveId").style.display="";
	  
	}   */       
	    }

