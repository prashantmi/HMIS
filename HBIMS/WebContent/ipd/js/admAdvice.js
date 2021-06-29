var child = null;
var popIndex = 0;
var gblCntrlObj = null;
function setCode(objVal,index)
{
	if(objVal.value.toUpperCase()!="")
	{
		document.getElementById("strProvisionDiagnosis"+index).value=objVal.value.toUpperCase();
		if(document.getElementById("strProvisionDiagnosis"+index).value=="")
			document.getElementById("strProvisionDiagnosis"+index).value="0";
	}
	else
	{
		document.getElementById("strProvisionDiagnosis"+index).value="0";
	}
}

var myWindow;

function showList()
{
	var hmode = "LIST"; 
	var url='/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode='+hmode+'&deptname='+document.forms[0].strDepartment.value+'&unitname='+""+'&deptvalue='+document.forms[0].strDepartmentValue.value+'&unitvalue='+document.forms[0].strUnitValue.value+'&crNo='+document.getElementsByName("patCrNo")[0].value;
	 var featuresList = "width=700,height=300,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		 myWindow = window.open(url,'popupWindow',featuresList);
	 	myWindow.focus();
		
			if(! myWindow.opener)
			{
				myWindow.opener = window;
			}
		return false;
				
}

function showBedStatus()
{
	if(document.forms[0].strWard.selectedIndex != 0)
	{
		var hmode = "BEDSTATUS"; 
		var mode = "ADMISSIONADVICE";
	    var treatmentcategory=document.forms[0].strPrimaryCategory.value;
		//alert('here is the ward combo');
		var url='/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode='+hmode+'&wardCode='+document.forms[0].strWard.value+'&bDate='+document.forms[0].strPropAdmissionDate.value+'&deptname='+document.forms[0].strDepartment.value+'&sexCode='+document.forms[0].strSex.value+'&age='+document.forms[0].strAge.value+'&treatmentCategory='+document.forms[0].strPrimaryCategory.value
		         +'&unitvalue='+document.forms[0].strUnitValue.value+'&deptcode='+document.forms[0].strDepartmentValue.value+'&deptUnitCode='+document.forms[0].strUnitValue.value+'&crNo='+document.getElementsByName("patCrNo")[0].value+"&ageCode="+document.forms[0].strUnitCode.value;
		//alert(url);
		var featuresList = "width=400,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes";
		 myWindow = window.open(url,'popupWindow',featuresList); 
		myWindow.focus();
		if(! myWindow.opener)
		{
				myWindow.opener = window;
		}
	}
	else
	{
			   alert('Please Select Ward');
			   document.forms[0].strWard.focus();
			
	}
			return false;
}

function admissionAdviceList()
{
		var hmode="LIST";
		var mode = "ADMISSIONADVICE";
		var url='/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode='+hmode+'&wardCode='+wardCodeVal[0]+'&bDate='+document.forms[0].strPropAdmissionDate.value+'&sexCode='+document.forms[0].strSex.value+'&age='+document.forms[0].strAge.value;
		var featuresList = "width=300,height=120,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList);
		myWindow.focus();
		if(! myWindow.opener)
		{
			myWindow.opener=window;
		}	
	}
	
function closeChild()
{
		if(!myWindow.closed)
			myWindow.close();
}
	
function initBedAndRoom()
{
		
		//document.getElementById("roomTypeDiv").innerHTML = "<input type='hidden' name='strRoomType' value='0'>";
		//document.getElementById("bedTypeDiv").innerHTML = "<input type='hidden' name='strBedType' value='0'>";
}
	
function setBedStatus( rVal, bVal ,sDate)
{
		var rId = document.getElementById("roomTypeDiv");
			rId.innerHTML = rVal;	
			var bId = document.getElementById("bedTypeDiv");
			bId.innerHTML = bVal;
			document.forms[0].strPropAdmissionDate.value = sDate;
}
	
/*function clearData(){
				
			var rId = document.getElementById("roomTypeDiv");
			rId.innerHTML = "";	
			
			var bId = document.getElementById("bedTypeDiv");
			bId.innerHTML = "";	
			
	}
*/
function changeMultiRows(obj)
{
	resetMultiRow('1');
	multiRowFunc();
}


var keyupTimer;
var searchMode1;
var parentObj1
var eve1;
var vindex1;
function getIcdCodeValues1(searchMode , parentObj, eve , vindex)
{
   clearTimeout(keyupTimer);
   searchMode1=searchMode;
   parentObj1=parentObj;
   eve1=eve;
   vindex1=vindex;   
   // will activate when the user has stopped typing for 1 second
   keyupTimer = setTimeout("getIcdCodeValues2()",500); 
} 
function getIcdCodeValues2()
{
   var temp=vindex1.split("-")[1]-1;
   var tempId="strIcdCode1-"+temp;
   if(vindex1.split("-")[1]-(temp+1)=='0')
   {
   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
   }
   else
   {  
	   if(document.getElementById(tempId).value=="")
	   alert("Please Enter Data in Previous Row");
	   else
	   {
	   		getIcdCodeValues(searchMode1 , parentObj1, eve1 , vindex1);
	   }
   }
}
var tempCode = "";
var gblParentObj = "";
var gblIndex = "";
var child = null;
var popIndex = 0;
var gblCntrlObj = null;
function getIcdCodeValues(searchMode , parentObj , eve , vindex)
{	
	gblParentObj = parentObj;
	gblIndex = vindex;	
	var key;
	if(window.event)
		key = window.event.keyCode;
	else
	{
		if (eve)
			key = eve.which;		 
	}	
	tempCode = key;//single quotes
 		if(tempCode == 222)
 		{ 		
 			parentObj.value = parentObj.value.substring(0,parentObj.value.length-1);
 			return false;
 		}		
		var url="";
		var hmode="";		
		var searchContent = parentObj.value;
		if(parentObj.value.length>1)
		{
 			searchContent = parentObj.value.substring(0,1);
 		}
		if(searchContent.length == 1)
		{
		 	//alert(document.getElementsByName("strDiagnosisType")[0].checked+":: "+document.getElementsByName("strDiagnosisType")[0].value);
		 	if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		 	{	
				hmode = "ICDDIAGNOSIS"; 
				url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{	
				hmode = "HOSITALPDIAGNOSIS";
				url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
			}
			if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
			{
				if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
			 	{	
					hmode = "ICDDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
				if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
			 	{	
					hmode = "HOSITALPDIAGNOSIS"; 
					url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode+"&strSearchMode="+searchMode+"&strSearchString="+searchContent;
				}
			}		
			//alert("url :"+url);			
			ajaxFunction(url,"2");
	}
	else
	{
		if(document.getElementById("dropdown1").innerHTML.length <=0)
		{
			var input = document.getElementById(parentObj.name+""+gblIndex).value;
			document.getElementById(parentObj.name+""+gblIndex).value = input.substring(0,input.length-1);
 			return false;
		}
		searchSel(eve,parentObj.name+""+vindex,'1',parentObj);		
		
	} 		
}
function multiRowFunc()
{
	var objTableId = document.getElementById('icd10Id');
	while(objTableId.rows.length>1)
		deleteLastRow('icd10Id',1,0);
}

function myFunc(mode)
{
		if(mode == '1')
		{
			var hmode = "HOSITALPDIAGNOSIS"; 
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"1");
		}
		else if(mode == '2')
		{
			var hmode = "ICDDIAGNOSIS"; 
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"2");
		}
	}
function setIcdCodes(userValue , resultValue)
{
		var resVals = resultValue.split('^');		
		document.getElementById("strProvisionDiagnosis"+userValue).value = resVals[0];
		document.getElementById("strIcdCode"+userValue).value = resVals[1];
		document.getElementById("strICD10CodeHidden"+userValue).value = resVals[0];
		
}	
function getAjaxResponse(res,mode)
{
		if(mode == '1')
		{
			var objEle = document.getElementById("diagnosisCombo");
			objEle.innerHTML = "<select class='form-control custom-select' name='strProvisionDiagnosis' id='strProvisionDiagnosis#delIndex#' >"+res+"</select>";
		}
		else if(mode == '2')
		{
			document.getElementById("dropdown1").innerHTML = res;
			searchSelWithCode(tempCode, gblParentObj.name+""+gblIndex ,'1',gblParentObj);
			//var objEle = document.getElementById("diagnosisCombo");
			//objEle.innerHTML = "<select class='comboBig' name='strProvisionDiagnosis' id='strProvisionDiagnosis#delIndex#'>"+res+"</select>";
		}
		else if(mode=='3')
		{
			try
			{
				var objWard=document.getElementById("wardCombo");
				objWard.innerHTML="<select class='form-control custom-select' name='strWard' onChange='checkDuplicate();'>"+res+"</select>";
				multiRowFunc();
			}
			catch(e)
			{
				//alert(e);
			}
		}
		else if(mode=='4')
		{
			var a=parseInt(res);
			if(a>=1)
			{
				alert('Advice already raised');
				var b=confirm("All the asscociated details corresponding to previous advice will be inactive,are you sure?");
				if(b==true)
				{
					document.forms[0].flag.value='1';
				}
				else
				{
					document.forms[0].flag.value='0';
				}
			}
			else 
			{
				document.forms[0].flag.value='1';
			}
			
			getAdvanceAmount();
		}
		
		else if(mode=='5')
		{
		    document.getElementsByName("strAdvanceAmount")[0].value=res;
		    document.forms[0].advamt.value=res;
		    getPackageAmount();
		    if(document.forms[0].strPackageApply.value!="" && document.forms[0].strPackageApply.value!="0") 
			    alert("Applied Package has been reset. You need to re-apply");
		}
		else if(mode=='6')
		{
			var packapp=document.getElementById("pack");
			packapp.style="display:block";
			packapp.innerHTML="<select name='strPackageApply' class='form-control custom-select' onChange='setAdvAmt();' >"+res+"</select>";
			//setAdvAmt();
		}
}
	
function showPatientDetails(divId)
{
		document.getElementById(divId).style.display="block";
		document.getElementById('minusId').style.display="block";
		document.getElementById('plusId').style.display="none";
}
	
function hidePatientDetails(divId)
{
		document.getElementById(divId).style.display="none";
		document.getElementById('minusId').style.display="none";
		document.getElementById('plusId').style.display="block";
}

function isDeptNameFound()
{
		var temp=document.forms[0].strDepartment.value;
		if(temp=='')
		{
			showBedStatus();
		}
		else
		{
			showBedStatus();
		}
}
	
function getWard()
{
	 //alert("gd");
	 getAgeSex();
	 var mode ="MODEWARD";
	 try
	 { 
		 //alert("ok");
		document.getElementsByName("strAge")[0].value=document.getElementsByName("strAge")[0].value.replace(/[^0-9]/g,"");
		//alert(document.getElementsByName("strAge")[0].value);
         var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&treatmentCategCode="+document.getElementsByName("strPrimaryCategory")[0].value+"&deptCode="+document.getElementsByName("strDepartmentValue")[0].value+"&deptUnitCode="+document.getElementsByName("strUnitValue")[0].value+"&unitCode="+document.getElementsByName("strUnitCode")[0].value+"&age="+document.getElementsByName("strAge")[0].value+"&sex="+document.getElementsByName("strSex")[0].value+"&diseaseType="+document.getElementsByName("strDiseaseType")[0].value;
       //  alert(url);
         ajaxFunction(url,"3");        
     }
     catch(e)
     {
        // alert(e);
     }
}

function closeAdmissionAdvice()
{
		document.getElementsByName("mode")[0].value="unspecified";
		document.forms[0].hmode.value="NEW";
		document.forms[0].submit();
}

function checkDuplicate()
{
	
		var mode="DUPLICATE";
		var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&wardCode="+document.forms[0].strWard.value+"&deptUnitCode="+document.forms[0].strUnitValue.value+"&crno="+document.getElementsByName("patCrNo")[0].value;
		ajaxFunction(url,"4");
}

function invokeOnLoad()
{
	try
	{
		//alert((document.getElementById('errMsg').innerHTML==""));
		
		var mode=document.getElementsByName("strMode")[0].value;
		//alert((document.getElementById('errMsg').innerHTML==""));
		if(mode==1)
		{
			document.getElementById('diseaseTypeLabel').className="CONTROL";
			document.getElementById('diseaseTypeWithoutLabel').className="CONTROL";
			//document.getElementById('icdDiagnosisId').style.display="block";
			//document.getElementsByName("strDiagnosisType")[1].checked=true;
			
		}
		else
		{
			document.getElementById('diseaseTypeValuesId').style.display="block";
			document.getElementById('diseaseTypeId').style.display="block";
			//document.getElementById('icdDiagnosisId').style.display="block";
			//document.getElementById('hospitalDiagnosisId').style.display="block";
			document.getElementById("checkid").style.display="block";
			
			
		}
		//alert((document.getElementById('errMsg').innerHTML==""));
		if(document.getElementById('errMsg').innerHTML=="")
		{
			//alert("X");
			getWard();
		}
		//alert((document.getElementById('errMsg').innerHTML==""));
		view();
		}catch(e){}
}

function checkDuplicateDiagnosis()
{
		/*var obj=document.getElementsByName("strDiagnosis");
		var objDianosis=document.getElementsByName("strProvisionDiagnosis");
		var strTemp;
		//var hospDiagType=document.forms[0].strDiagnosisType[0].value;
		//var ICDDiagType=document.forms[0].strDiagnosisType[0].value;
		
		//alert(objDianosis.length);
		alert(obj.length  +" "+ objDianosis.length);
		if(obj.length!=0 && objDianosis.length!=1)
		{
			for(var i=0;i<obj.length;i++)
			{
				strTemp=obj[i].value.split("^");
				alert(obj[i].value +" "+objDianosis[i].value);
				for(var j=0;j<objDianosis.length-1;j++)
				{
					
					if(trim(objDianosis[j].value)==trim(strTemp[0]))
					{
						
						alert("Diagnosis can't be duplicate");
						return false;
					}
				}	
			}
		}
		return true;*/
	
	
	var obj=document.getElementsByName("strDiagnosis");
	var objDianosis=document.getElementsByName("strProvisionDiagnosis");
	var strTemp;
	//var hospDiagType=document.forms[0].strDiagnosisType[0].value;
	//var ICDDiagType=document.forms[0].strDiagnosisType[0].value;
	
	if(obj.length!=0 && objDianosis.length!=1)
	{
		for(var i=0;i<obj.length;i++)
		{
			strTemp=obj[i].value.split("^");
			for(var j=0;j<objDianosis.length;j++)
			{
				if((trim(objDianosis[j].value)==trim(strTemp[0])) && (objDianosis[j].type!='hidden'))
				{
					alert("Diagnosis can't be duplicate");
					return false;
				}
			}	
		}
	}
	return true;
		
}
/*
 * This function set Age, Age Unit Code and Sex Code
 */
function getAgeSex()
{
	var temp1=document.getElementsByName("strHiddenPatDtl")[0].value.split("^");
	var sexCode="3";
	var strTemp=temp1[3];
	var strAgeSign="";
	var temp2=strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for(var i=0;i<strTemp.length;i++)
	{
		 //alert("gd1");
		if(strTemp.charAt(i)=='Y' || strTemp.charAt(i)=='y'){
			strAgeSign=strTemp.charAt(i);
		strUnitCode="3";
		break;
		}
		if(strTemp.charAt(i)=='M' || strTemp.charAt(i)=='m')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="2";
			break;
		}
		if(strTemp.charAt(i)=='D' || strTemp.charAt(i)=='d')
		{
			strAgeSign=strTemp.charAt(i);
			strUnitCode="1";
			break;
		}
		//alert("dwdhwd");
	 }
	 /**
	  * This loop is for generating Sex Code
	  */
	for(var i=0;i<temp2[1].length;i++)
	{
		if(temp2[1].charAt(i)=='M' || temp2[1].charAt(i)=='m'){
		sexCode="1";
		break;
		}
		if(temp2[1].charAt(i)=='F' || temp2[1].charAt(i)=='f')
		{
			sexCode="2";
			break;
		}
	 }
	 var temp3=temp2[0].split(strAgeSign);
	  document.getElementsByName("strAge")[0].value=temp3[0];
	  // document.forms[0].strUnitCode.value=strUnitCode; 
	   document.getElementsByName("strSex")[0].value=sexCode;
	   
	   
}

function view1(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="none";
	document.getElementById(obj2).style.display="";
	document.getElementById(obj3).style.display="";
}

function view2(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="";
	document.getElementById(obj2).style.display="none";
	document.getElementById(obj3).style.display="none";
}



function cancelPage()
{
		document.forms[0].hmode.value="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode=INITIALPAGE";
		document.forms[0].submit();
}



/*
function copyCode(index)
{
	_these = document.getElementsByName("strIcdCode")[index];
	document.getElementsByName("strProvisionDiagnosis")[index].value=_these.value.replace("/ ","^").split("^")[1];
	document.getElementsByName("strICD10CodeHidden")[index].value=_these.value.replace("/ ","^").split("^")[1];
	document.getElementsByName("strIcdCode")[index].value=_these.value.replace("/ ","^").split("^")[0];
}

function copyText(index)
{
	_these = document.getElementsByName("strProvisionDiagnosis")[index];
	document.getElementsByName("strIcdCode")[getIndex(_these)].value=_these.value.replace("/ ","^").split("^")[1];
	document.getElementsByName("strProvisionDiagnosis")[getIndex(_these)].value=_these.value.replace("/ ","^").split("^")[0];
	document.getElementsByName("strICD10CodeHidden")[getIndex(_these)].value=_these.value.replace("/ ","^").split("^")[0];
}

function createArray(_tableId)
{
	var array1 = new Array('<input type=text onkeyup="document.getElementsByName(\'strIcdCode\')[getIndex(this)].value=\'\'";" tabIndex=1 name=strProvisionDiagnosis >(ICD10 Code) <input tabIndex=1 type=text  name=strIcdCode id=\'#strIcdCode#index#\' style="width: 300px" title=\'Enter ICD10 Code\'  ><INPUT TYPE=HIDDEN NAME=\'strICD10CodeHidden\'>');
	var array = array1;
	multiRow(_tableId,array, 1, 0, true);
	var objICD10Text = document.getElementsByName('strIcdCode');
	
	if(document.getElementsByName('strDiagnosisType')[1].checked==true)
	{
		var obj1 = new actb(document.getElementsByName('strIcdCode')[objICD10Text.length-1],arrValues);
		obj1.actb_userdefine_func = "copyCode";
		var strProvisionDiagnosis = document.getElementsByName('strProvisionDiagnosis');
		var obj2 = new actb(document.getElementsByName('strProvisionDiagnosis')[strProvisionDiagnosis.length-1],arrValues1);
		obj2.actb_userdefine_func = "copyText";
	} 
	else 
	{
		var obj1 = new actb(document.getElementsByName('strIcdCode')[objICD10Text.length-1],arrValues2);
		obj1.actb_userdefine_func = "copyCode";
		var strProvisionDiagnosis = document.getElementsByName('strProvisionDiagnosis');
		var obj2 = new actb(document.getElementsByName('strProvisionDiagnosis')[strProvisionDiagnosis.length-1],arrValues3);
		obj2.actb_userdefine_func = "copyText";
	}
}*/
function setAdvAmt()
{
	var temp=document.forms[0].strPackageApply.value.split("^");
	if(document.forms[0].strPackageApply.value!="0")
    {
	      if(temp[1]!="")
	          document.forms[0].strAdvanceAmount.value=temp[1];
	      else
	    	  document.forms[0].strAdvanceAmount.value=0;  
    }
	else
  	  document.forms[0].strAdvanceAmount.value=document.forms[0].advamt.value;
}
function getPackageAmount()
{    
		var mode ="PACKAGEAMOUNT";
		//var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardType="+document.getElementsByName("strWard")[0].value.split("^")[1];
	    // var url="/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="+mode+"&wardCode="+document.forms[0].strWard.value+"&catcode="+document.forms[0].strTreatment.value;
		var temp = document.forms[0].strWard.value.split("^");
		var temp1 = temp[0];
		for (var i = 0; i < temp.length - 1; i++)
			temp1 += "@" + temp[i + 1];

		// var
		// url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardType="+document.getElementsByName("strWard")[0].value.split("^")[1];
		var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransBSCNT.cnt?hmode="
				+ mode + "&wardCode=" + temp1 + "&catcode="
				+ document.forms[0].strTreatment.value;
		ajaxFunction(url,"6");
}
function checkMsg() {
	
	
	var err = document.getElementById("errMsg");
	var nor = document.getElementById("normalMsg");
	// var warn=document.getElementById("wrnID");
	if (err.innerHTML != "") {

		
		
		err.innerHTML = "<i class='fas fa-exclamation-circle'></i>"
				+ "&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"
				+ err.innerHTML
				+ "<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		err.style.display = "";

	}
	if (nor.innerHTML != "") {
		nor.innerHTML = "<i class='far fa-thumbs-up'></i>"
				+ "&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"
				+ nor.innerHTML
				+ "<button type='button' class='close' data-dismiss='alert'>&times;</button>";
		nor.style.display = "";
	}
	

}




function bedDetails() {
	
	if(document.getElementsByName("strWard")[0].value == "0") 
	{
		$('.modal-container').css('display', 'none');

			alert("Ward Not Selected!");

		 return false;
	}else{

		$('.modal-container').css('display', 'block');
		
		var mode = "BEDSTATUSPATADM";
		var WrdRoomBedtypUnt_code = document.forms[0].strWard.value.split('^')[0]
				+ ",0,0,0,0";

		url = "IpdCNT.cnt?hmode=" + mode + "&modPopUp=" + WrdRoomBedtypUnt_code;
		
		$('#modalSpaceId').load(url);
		
		$("#myModal").modal("show");


	}
	
}

$(document).ready(function(){
	
	var today=new Date();
	var dt=today.getDate();
	var mth=today.getMonth();
	mth=mth+1;
	if(dt<10)
		dt="0"+dt;
	if(mth<10)
		mth="0"+mth;
	
	
	$('#datepicker').datepicker({ uiLibrary: 'bootstrap4',modal: true,footer: true,format: 'dd-mm-yyyy'});
	$('#datepicker1').datepicker({uiLibrary: 'bootstrap4',modal: true,footer: true,format: 'dd-mm-yyyy'});
	$('#datepicker').val(dt+"-"+mth+"-"+today.getFullYear());
});