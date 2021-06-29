






var myWindow;
function showList()
{
	    
		var hmode = "LIST"; 
		var url='/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode='+hmode+'&deptname='+document.forms[0].strDepartment.value+'&unitname='+""+'&deptvalue='+document.forms[0].strDepartmentValue.value+'&unitvalue='+document.forms[0].strUnitValue.value+'&crNo='+document.forms[0].patCrNo.value;
		var featuresList = "width=700,height=300,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		/*myWindow = window.open(url,'popupWindow',featuresList); 
				myWindow.focus();			
				if(! myWindow.opener)
				{
					myWindow.opener = window;
				}*/
				
				openModalPopUp(url,"750","400","1");
			return false;
}
function showBedStatus()
{
		if(document.forms[0].strWard.selectedIndex != 0)
		{
			var hmode = "BEDSTATUS"; 
			var mode = "ADMISSIONADVICE";
		    var treatmentcategory=document.forms[0].strPrimaryCategory.value;
			
			var url='AdmissionAdviceTransCNT.cnt?hmode='+hmode+'&wardCode='+document.forms[0].strWard.value+'&bDate='+document.forms[0].strPropAdmissionDate.value+'&deptname='+document.forms[0].strDepartment.value+'&sexCode='+document.forms[0].strSex.value+'&age='+document.forms[0].strAge.value+'&treatmentCategory='+document.forms[0].strPrimaryCategory.value
			         +'&unitvalue='+document.forms[0].strUnitValue.value+'&deptcode='+document.forms[0].strDepartmentValue.value+'&deptUnitCode='+document.forms[0].strUnitValue.value+'&crNo='+document.getElementsByName("patCrNo")[0].value+"&ageCode="+document.forms[0].strUnitCode.value;
			
			var featuresList = "width=400,height=380,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
			/*myWindow = window.open(url,'popupWindow',featuresList); 
			myWindow.focus();
			if(! myWindow.opener)
			{
					myWindow.opener = window;
			}*/
			openModalPopUp(url,"400","350","1");
		}
		else
		{
			   alert('Please Select Ward...');
			   document.forms[0].strWard.focus();
			
		}
			return false;
}


	


function admissionAdviceList()
{
		var hmode="LIST";
		var mode = "ADMISSIONADVICE";
		var url='/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode='+hmode+'&wardCode='+wardCodeVal[0]+'&bDate='+document.forms[0].strPropAdmissionDate.value+'&sexCode='+document.forms[0].strSex.value+'&age='+document.forms[0].strAge.value;
		var featuresList = "width=300,height=120,ALIGN=CENTER,left=300,top=300,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList);
		myWindow.focus();
		if(! myWindow.opener){
		myWindow.opener=window;
		}	
}
	
function closeChild()
{
		if(!myWindow.closed)
			myWindow.close();
}
	
function initBedAndRoom(){
		
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
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"1");
		}
		else if(mode == '2')
		{
			var hmode = "ICDDIAGNOSIS"; 
			var url = "/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+hmode;
			ajaxFunction(url,"2");
		}
}
	 
/*function createArray(_tableId)
{
		var array1 = new Array('<input type=text onkeypress="return setSelectValue1(\"strProvisionDiagnosis\",1,event,\"setIcdCodes\",\"\");"  onkeyup="getIcdCodeValues(1,this , event);" tabIndex=1 id=\"strProvisionDiagnosis\"  name=\"strProvisionDiagnosis\" >(ICD10 Code) <input tabIndex=1 type=text onkeypress="return setSelectValue1(\"strProvisionDiagnosis\",1,event,\"setIcdCodes\",\"\");"  onkeyup="getIcdCodeValues(1,this , event);" name=\"strIcdCode\" id=\"strIcdCode\" style="width: 300px" title=\'Enter ICD10 Code\'  ><INPUT TYPE=HIDDEN NAME=\'strICD10CodeHidden\'>');
		var array = array1;
		multiRow(_tableId,array, 1, 0, true);
		  
}
*/
	
function setIcdCodes(userValue , resultValue)
{
		//alert("setIcdCodes"+userValue+">>"+resultValue);
		var resVals = resultValue.split('^');		
		document.getElementById("strProvisionDiagnosis"+userValue).value = resVals[0];
		document.getElementById("strIcdCode"+userValue).value = resVals[1];
		document.getElementById("strICD10CodeHidden"+userValue).value = resVals[0];
		//ICD
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="0")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "0";
		}
		//Hospital
		if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		{
			document.getElementById("strDiagCodeType"+userValue).value = "1";
		}
		//Both
		if(document.getElementsByName("strDiagnosisType")[1]!=undefined)
		{
			//ICD
			if(document.getElementsByName("strDiagnosisType")[1].checked && document.getElementsByName("strDiagnosisType")[1].value=="0")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "0";	
			}
			//Hospital
			if(document.getElementsByName("strDiagnosisType")[0].checked && document.getElementsByName("strDiagnosisType")[0].value=="1")
		 	{
		 		document.getElementById("strDiagCodeType"+userValue).value = "1";
			}
		}
		//defined in multirow jsp
		document.getElementById("tariffFullNameDiv").style.display="none";
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
function reenter(index)
{
		if(document.getElementById('strIcdCode'+index).value=="")
		{
			document.getElementById('strIcdCode'+index).maxlength=25;
			document.getElementById('strIcdCode'+index).style.color="#9d9d9d";
			document.getElementById('strIcdCode'+index).value="Enter ICD10 Code Here";
			document.getElementById('strIcdCode'+index).className=""
			document.getElementById("strProvisionDiagnosis"+index).value="0";
		}
		else if(document.getElementById('strIcdCode'+index).maxlength==6)
		{
		}
		else
		{
			document.getElementById('strIcdCode'+index).maxlength=6;
			document.getElementById('strIcdCode'+index).style.color="black";
			document.getElementById('strIcdCode'+index).value="";
			document.getElementById('strIcdCode'+index).className="txtFldMin"
		}
}
function getWard()
{
	 
		 //getAgeSex();
		 var mode ="MODEWARD";
		 if(document.forms[0].strDepartmentValue.value!="0")
		 {
			 try
			 {
			  	document.forms[0].strAge.value=document.forms[0].strAge.value.replace(/[^0-9]/g,"");//replace all those character those are not number
		        var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&treatmentCategCode="+document.forms[0].strPrimaryCategory.value+"&deptCode="+document.forms[0].strDepartmentValue.value+"&deptUnitCode="+document.forms[0].strUnitValue.value+"&unitCode="+document.forms[0].strUnitCode.value+"&age="+document.forms[0].strAge.value+"&sex="+document.forms[0].strSex.value+"&diseaseType="+document.forms[0].strDiseaseType.value;
		        ajaxFunction(url,"3");
		     }
		     catch(err)
		     {
		         	alert(err);
		     }
	     }
}
/*
 * This function set Age, Age Unit Code and Sex Code
 */
function getAgeSex()
{
	alert(document.forms[0].strHiddenPatDtl.value);
	var temp1=document.forms[0].strHiddenPatDtl.value.split("^");
	var sexCode="3";
	var strTemp=temp1[3];
	var strAgeSign="";
	var temp2=strTemp.split("/");
	/**
	 * This loop is for generating age Code
	 */
	for(var i=0;i<strTemp.length;i++)
	{
		if(strTemp.charAt(i)=='Y' || strTemp.charAt(i)=='y')
		{
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
	 }
	 /**
	  * This loop is for generating Sex Code
	  */
	for(var i=0;i<temp2[1].length;i++)
	{
		if(temp2[1].charAt(i)=='M' || temp2[1].charAt(i)=='m')
		{
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
	  document.forms[0].strAge.value=temp3[0];
	  // document.forms[0].strUnitCode.value=strUnitCode; 
	   document.forms[0].strSex.value=sexCode;
}
function goFunc()
{
	 	if(!validateThroughRegExp(document.forms[0].strCrNo,1))
	 	{
	 		return;
	 	}
		var hisValidator = new HISValidator("advanceAdviceTransBean");  
		hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		hisValidator.addValidation("strCrNo","minlen="+document.getElementsByName("strCrNo")[0].maxLength,"CR No. must be "+document.getElementsByName("strCrNo")[0].maxLength+" Digits" );
		var retVal = hisValidator.validate(); 
		if(retVal)
		{
			document.forms[0].hmode.value="GO";
			document.forms[0].submit();
		}
		else
		{
			return false;
		}
}
function checkDuplicateDiagnosis()
{
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

 	
function goRetFunc(obj)
{
		var flag=validateData(obj,5);
		if(flag)
		{
				if(obj.keyCode==13)
				{
					var flag1=goFunc();
					if(flag1)
					{
						document.forms[0].hmode.value="GO";
						document.forms[0].submit();
					}
					else
					{
						return false;
					}
				}
			}
			else
			{
				return false;
			}
}
function showPatientDetails(divId)
{
	    
		document.getElementById(divId).style.display="block";		
		document.getElementById('minusId').style.display="";
		document.getElementById('plusId').style.display="none";
}
function hidePatientDetails(divId)
{
		document.getElementById(divId).style.display="none";
		document.getElementById('minusId').style.display="none";
		document.getElementById('plusId').style.display="";
}
function showEpisodeDetails(divId)
{
		document.getElementById(divId).style.display="block";		
		document.getElementById('minusEpId').style.display="block";
		document.getElementById('plusEpId').style.display="none";
}
function hideEpisodeDetails(divId)
{
		document.getElementById(divId).style.display="none";
		document.getElementById('minusEpId').style.display="none";
		document.getElementById('plusEpId').style.display="block";
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
	
	
function closeAdmissionAdvice()
{
		document.getElementsByName("mode")[0].value="unspecified";
		document.forms[0].hmode.value="NEW";
		document.forms[0].submit();
}
function cancelPage()
{
		document.forms[0].hmode.value="INITIALPAGE";
		document.forms[0].submit();
}
function checkDuplicate()
{
		if(document.forms[0].strWard.value!=0)
		{
			var mode="DUPLICATE";
			var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&wardCode="+document.forms[0].strWard.value+"&deptUnitCode="+document.forms[0].strUnitValue.value+"&crno="+document.getElementsByName("patCrNo")[0].value;
			ajaxFunction(url,"4");
			
		}
		

}
function getOfflineUnit()
{
		//if(document.forms[0].strDepartmentValue.value!="0")
		//{
			var mode ="UNITCOMBO";
			var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&strDepartmentCode="+document.forms[0].strDepartmentValue.value+"&puk="+document.forms[0].strCrNo.value;
		    ajaxFunction(url,"5");
	    //}
}
function getAdvanceAmountOnCatChange()
{
	        if(document.getElementsByName("strWard")[0].value != "0")
	        {
			var mode ="ADVANCEAMOUNT";
			var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&puk="+document.forms[0].strCrNo.value+"&catcode="+document.forms[0].strTreatment.value+"&wardCode="+document.forms[0].strWard.value;
			//alert(url);
		    ajaxFunction(url,"6");
	        }
}


function getSubmitPage()
{
		document.forms[0].hmode.value="GO";
		document.forms[0].submit();
}
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
		//var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&puk="+document.getElementsByName("patCrNo")[0].value+"&catcode="+document.forms[0].strTreatment.value+"&wardType="+document.getElementsByName("strWard")[0].value.split("^")[1];
	     var url="/HBIMS/ipd/transactions/AdmissionAdviceTransCNT.cnt?hmode="+mode+"&wardCode="+document.forms[0].strWard.value+"&catcode="+document.forms[0].strTreatment.value;
		ajaxFunction(url,"7");
}