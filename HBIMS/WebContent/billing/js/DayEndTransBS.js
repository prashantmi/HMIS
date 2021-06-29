
function onChangeSessionCmb(){

	if(document.forms[0].strSessionId.value  != 0) {
		
		var obj = document.forms[0].strSessionId;
		var temp = obj.value.split('^');
		
		document.getElementById('fromSessionTime').innerHTML = temp[1];
		document.getElementById('toSessionTime').innerHTML = temp[2];
		
		document.getElementsByName('strSessionFromTime')[0].value = temp[1];
		document.getElementsByName('strSessionToTime')[0].value = temp[2];
		
	}else{
	
		document.getElementById('fromSessionTime').innerHTML = "";
		document.getElementById('toSessionTime').innerHTML = "";
	
	}

}
function sumAmount()
{
	var total=parseInt(document.getElementsByName("strTwoThousandNotes")[0].value)*parseInt(2000)+
	          parseInt(document.getElementsByName("strThousandNotes")[0].value)*parseInt(1000)+
			  parseInt(document.getElementsByName("strFiveHundNotes")[0].value)*parseInt(500)+
			  parseInt(document.getElementsByName("strTwoHundNotes")[0].value)*parseInt(200)+
			  parseInt(document.getElementsByName("strHundNotes")[0].value)*parseInt(100)+
			  parseInt(document.getElementsByName("strFiftyNotes")[0].value)*parseInt(50)+
			  parseInt(document.getElementsByName("strTwentyNotes")[0].value)*parseInt(20)+
			  parseInt(document.getElementsByName("strTenNotes")[0].value)*parseInt(10)+
			  //parseInt(document.getElementsByName("strFiveNotes")[0].value)*parseInt(5)+
			//  parseInt(document.getElementsByName("strTwoNotes")[0].value)*parseInt(2)+
			 // parseInt(document.getElementsByName("strOneNotes")[0].value)*parseInt(1)+
			  parseInt(document.getElementsByName("strtenCoins")[0].value)*parseInt(10)+
			  parseInt(document.getElementsByName("strFiveCoins")[0].value)*parseInt(5)+
			  parseInt(document.getElementsByName("strTwoCoins")[0].value)*parseInt(2)+
			  parseInt(document.getElementsByName("strOneCoins")[0].value)*parseInt(1);
			  
	//alert("total"+total);
	document.getElementsByName("strTotalSum")[0].value=total;
}
function checkFieldForCash(obj)
{
	if(obj.value.length==0)
	{
		obj.value = "0"; 
	}
}

function clearvalues()
{
	//alert("Inside clear values");
	document.getElementsByName("strTwoThousandNotes")[0].value=0;
	document.getElementsByName("strThousandNotes")[0].value=0;
	document.getElementsByName("strFiveHundNotes")[0].value=0;
	document.getElementsByName("strTwoHundNotes")[0].value=0;
	document.getElementsByName("strHundNotes")[0].value=0;	
	document.getElementsByName("strFiftyNotes")[0].value=0;
	document.getElementsByName("strTwentyNotes")[0].value=0;
	document.getElementsByName("strTenNotes")[0].value=0;
	//document.getElementsByName("strFiveNotes")[0].value=0;
	//document.getElementsByName("strTwoNotes")[0].value=0;
	//document.getElementsByName("strOneNotes")[0].value=0;
	document.getElementsByName("strtenCoins")[0].value=0;
	document.getElementsByName("strFiveCoins")[0].value=0;
	document.getElementsByName("strTwoCoins")[0].value=0;
	document.getElementsByName("strOneCoins")[0].value=0;
	document.getElementsByName("strTotalSum")[0].value=0;
	
}
function makingEmptyFieldForCash(obj)
{
	if(obj.value=="0")
	{
		obj.value = ""; 
	}
}

	function getVisibilityDetails(chkObj){

		document.forms[0].strIsPayModeRequired.checked = false;
		document.forms[0].strIsPayDetailRequired.checked = false;

		if(chkObj.checked){
			
			document.getElementById("paymentDetailsRequiredDivId").style.display = "block";			
			
		}else{
		
			document.getElementById("paymentDetailsRequiredDivId").style.display = "none";
		
		}
	

	}


/** This is the validate function for day end transaction jsp file
 *  i.e:- dayend_billtrans.jsp and 
 *  transfer control to the controller 'PROCESS' method.  
 */
 
 function validateFunc()
 {	
	document.forms[0].strDayEndAmount.value=document.forms[0].strDayEndAmount.value.split(" [")[0];
	var hisValidator = new HISValidator("dayEndTransBean");  
	hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
	//day end supervisor
	if(document.forms[0].userMode.value != 1)
	{
			hisValidator.addValidation("strUsrId","dontselect=0","Please select a value from User Combo" );
			hisValidator.addValidation("strCountId","dontselect=0","Please select a value from Counter Combo" );
	}
	var retVal = hisValidator.validate();	
	if(retVal)
	{
		if (document.forms[0].strNegativeDayEndAllowed.value=="0")//Not allowed
		{
			if((document.getElementsByName("strDayEndAmount")[0].value=="0" || parseInt(document.getElementsByName("strDayEndAmount")[0].value)<0)  
					&& document.getElementsByName("strDayEndCreditCol")[0].value=="0" && (document.getElementsByName("strDayEndInstAmt")[0].value=="0" || parseInt(document.getElementsByName("strDayEndInstAmt")[0].value)<0))
					{
						alert("Scroll Generation Not Allowed for Zero Amount/Negative Amount");
						return false;
					}
		}
		
		if(document.forms[0].strDenominationAllowed.value=="1" && parseInt(document.getElementsByName("strDayEndAmount")[0].value)>0) //Denomination allowed and cash>0
		{
			// validate denomination amount entered	
			if((document.getElementsByName('strTwoThousandNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strThousandNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strFiveHundNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strTwoHundNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strHundNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strFiftyNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strTwentyNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strTenNotes')[0].value.trim().length >0)
			//&& (document.getElementsByName('strFiveNotes')[0].value.trim().length >0)
			//&& (document.getElementsByName('strTwoNotes')[0].value.trim().length >0)
			//&& (document.getElementsByName('strOneNotes')[0].value.trim().length >0)
			&& (document.getElementsByName('strtenCoins')[0].value.trim().length >0)
			&& (document.getElementsByName('strFiveCoins')[0].value.trim().length >0)
			&& (document.getElementsByName('strTwoCoins')[0].value.trim().length >0)
			&& (document.getElementsByName('strOneCoins')[0].value.trim().length >0)
			)
			{
				//some numeral is entered in respective TIs
			}
			else
			{
				alert("Denomination value(s) cannot be blank.");
				return false;
			}
			
			var denominationAmt=(parseInt(document.getElementsByName('strTwoThousandNotes')[0].value)* parseInt(2000))
            +(parseInt(document.getElementsByName('strThousandNotes')[0].value)* parseInt(1000))
			   +(parseInt(document.getElementsByName('strFiveHundNotes')[0].value)* parseInt(500))
			   +(parseInt(document.getElementsByName('strTwoHundNotes')[0].value)* parseInt(200))
			   +(parseInt(document.getElementsByName('strHundNotes')[0].value)* parseInt(100))
			   +(parseInt(document.getElementsByName('strFiftyNotes')[0].value)* parseInt(50))
			   +(parseInt(document.getElementsByName('strTwentyNotes')[0].value)* parseInt(20))
			   +(parseInt(document.getElementsByName('strTenNotes')[0].value)* parseInt(10))
			  // +(parseInt(document.getElementsByName('strFiveNotes')[0].value)* parseInt(5))
			 //  +(parseInt(document.getElementsByName('strTwoNotes')[0].value)* parseInt(2))
			 //  +(parseInt(document.getElementsByName('strOneNotes')[0].value)* parseInt(1))
			   +(parseInt(document.getElementsByName('strtenCoins')[0].value)* parseInt(10))
			   +(parseInt(document.getElementsByName('strFiveCoins')[0].value)* parseInt(5))
			   +(parseInt(document.getElementsByName('strTwoCoins')[0].value)* parseInt(2))
			   +(parseInt(document.getElementsByName('strOneCoins')[0].value)* parseInt(1));
			   
//alert("Denomination amount is::"+denominationAmt);
			
			//check if denomination amt = day end cash (only non credit) total
			if(parseInt(denominationAmt) == Math.abs(parseInt(document.getElementsByName('strDayEndAmount')[0].value)))
			{
				//alert("check passed........");
			}
			else
			{
				alert("Denomination Total Amount Should Be Equal To Day End Amount.");
				return false;
			}

		}
		
		
		//alert(document.getElementsByName('strDayEndAmount')[0].value);
		
		
		
			  document.forms[0].hmode.value="DAYENDREPORT";
			  if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value != "pdf")
				{				
					document.forms[0].target = "_self";
					document.getElementById("saveid").style.display = "none";
					document.forms[0].submit();				
				}
				else
				{
					document.forms[0].target = "_self";
					document.getElementById("saveid").style.display = "none";
					document.forms[0].submit();
				}			
	}
	else
	{
		return false;
	}
}
	 
	 function cancelPage()
	 {
	 	document.forms[0].hmode.value="INITIALPAGE";
	 	document.forms[0].target = "_self";
		document.forms[0].submit();
	 }
 
 var gblPrintLimitVal = 0;
 
function onBodyLoad()
{
	if(document.forms[0].strDenominationAllowed.value=="0")//Y-Yes,N-No
	{
		document.getElementById("DenominationDivId").style.display="none";
	}
	if(document.forms[0].strDayEndAllowedFlag.value=="N")//Y-Yes,N-No
	{
		document.getElementById("errMsg").innerHTML = "Day End Can Not Be Performed Before - "+document.forms[0].strDayEndAllowedTime.value+" Current Time Is-"+document.forms[0].strCurrentTime.value ;
		document.getElementById("saveid").style.display="none";
	}
		
	if(document.forms[0].userMode.value!=1)
	{
		document.forms[0].strUsrId.value = "0";
		document.forms[0].strCountId.value = "0";
		document.forms[0].strSessionId.value = "0";
	}
	/*else
	{
		document.forms[0].strSessionId.value = "0";
	}*/	
		var normalMsgVal = document.getElementById("normalMsg").innerHTML;	
				
			if(normalMsgVal.length > 1)
			{
				var confirmFlag = confirm("Whether Day End Report Printed Successfully.\nIf Not Click the Cancel button to Re-Print");
				
				if(!confirmFlag)
				{
					var printLimit = document.forms[0].strPrintCount.value;
					if(printLimit.length <0) printLimit = "0";									
						printLimit = parseInt(printLimit);
					if(printLimit > 0)
					{
						gblPrintLimitVal = printLimit;
						reprintContents();
					}										
				}
			}
}


/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "DayEndTransBSCNT.cnt?hmode="+hmode;
											
				ajaxFunction2(url,"1","rePrintStatus");	
	 			
	 		} else{
	 		
	 			alert("Sorry, No More Re-Print");
	 		
	 		}
	 		
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 function rePrintStatus() {
	 	
	 	gblPrintLimitVal = gblPrintLimitVal - 1;
	 	
	 			var confirmFlag = confirm("Whether Day End Report Printed Successfully.\nIf Not Click the Cancel button to Re-Print");
				
				if(!confirmFlag){
							 
					 	reprintContents();
										
				}

	 	
	 }			


/** This function is used to open the report and
 *  transfer control to the controller 'DAYENDREPORT' method.
*/
 
 function reportFunc()
 {
	 if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
	 {
		 document.forms[0].target = "_self";
	 }
	 else
	 {
		 document.forms[0].target = "_self";
	 }	 
	 document.forms[0].hmode.value="DAYENDREPORT";
	 document.forms[0].submit();		
 }

      
   function save()
   {
   	if(document.forms[0].strCounterId.value==""||document.forms[0].strCounterId.value=="/"){
   	alert("Counter Name does not exist \n Day End can not be performed"); 
   } else {
   	reportFunc();
   	}}
 
 
 
 function tableShow(strTableId, imageElement) 
 {
 	if(document.forms[0].strUsrId)
		{
			if(document.getElementsByName("strUsrId")[0].value=="0")
			{
			alert("Please Select User Name");
			document.getElementsByName("strUsrId")[0].focus();
			return false;
			}
		}		
		
		var elementTable = document.getElementById(strTableId);
		elementTable.style.display = "table";
		var strOnclick = "tableHide('" + strTableId + "',this);";
		imageElement.setAttribute("onclick", strOnclick);
		imageElement.setAttribute("src", "../../hisglobal/images/minus.gif");
	
		getPendingDayEndDetails();
			
}

function tableHide(strTableId, imageElement) 
{
//    alert("imageElement"+imageElement);
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "../../hisglobal/images/plus.gif");

}   

function setDayEndDate(index)
{
	
	document.forms[0].strDayEndDate.value	=	document.getElementsByName("strRadioButton")[index].value;
	var elementTable = document.getElementById("pendingDayEndDetailsTable");
	var imageElement = document.getElementById("showPendingDayEndTableId");
	
	elementTable.style.display = "table";
	var strOnclick = "tableShow('pendingDayEndDetailsTable',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "../../hisglobal/images/plus.gif");
	getDayEndAmount();
}
function tableHideNew()
{	
	var elementTable = document.getElementById("pendingDayEndDetailsTable");
	var imageElement = document.getElementById("showPendingDayEndTableId");
	
	elementTable.style.display = "table";
	var strOnclick = "tableShow('pendingDayEndDetailsTable',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "../../hisglobal/images/plus.gif");	
}


function getPendingDayEndDetails()
{

        var mode = "pendingDayEndDetails";
        var url ="DayEndTransBSCNT.cnt?hmode="+mode+"&billModuleId="+document.forms[0].billModuleId.value+"&counterId="+document.forms[0].strCounterId.value
        			+"&userMode="+document.forms[0].userMode.value;
		
		if(document.forms[0].strUsrId)
		{
			url = url+"&userId="+document.getElementsByName("strUsrId")[0].value;
		}
        			
        			
 		ajaxFunction(url,"1");		
}

function getDayEndAmount()
{
		//alert("getDayEndAmount"+document.forms[0].strDayEndDate.value+"::"+document.forms[0].strCounterId.value);
		//clerk mode..
		if(document.getElementsByName('userMode')[0].value == 1)
		{
			var mode = "getDayEndAmountAjax";
        	var url ="DayEndTransBSCNT.cnt?hmode="+mode+"&counterId="+document.forms[0].strCounterId.value
        			+"&dayEndDate="+document.forms[0].strDayEndDate.value;
       
		
			ajaxFunction(url,"2");	
		}
		
		//supervisor mode..
		if(document.getElementsByName('userMode')[0].value == 2)
		{
			getDayEndAmountUserNCounterWise();
		}
        	
}

function getDayEndAmountUserNCounterWise()
{
	   if(document.forms[0].strUsrId.value == 0)
       {
       		alert("User Name is not selected.");
       		objVal.value="0";
			document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtLabel').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
       		return;	
       }
	   else if(document.forms[0].strCountId.value == 0)
       {
       		alert("Counter Name is not selected.");
       		objVal.value="0";
			document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtLabel').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
       		return;	
       }
       else
       {
       		var mode = "getDayEndAmountAjaxUserNcounterWise";
        	var url ="DayEndTransBSCNT.cnt?hmode="+mode+"&counterId="+document.forms[0].strCountId.value
        			+"&dayEndDate="+document.forms[0].strDayEndDate.value
        			+"&userIdd="+document.forms[0].strUsrId.value;
		
			ajaxFunction(url,"3");       		
       }
        	
}

function getAjaxResponse(res,mode)
{
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 

	if(mode=="1")
	   {
	   	 	var objVal= document.getElementById("pendingDayEndDetailsTable");
     		if(res=="")
			{
				objVal.innerHTML="";
			}
			else
			{
				//alert("Hello"+res);
				objVal.innerHTML = res;
				
				
				/*if(document.forms[0].strRadioButton)
				{
					//document.getElementsByName("strRadioButton")[0].checked=true;
					document.getElementsByName("strDayEndDate")[0].value=document.getElementsByName("strRadioButton")[0].value;
				}*/
			}
		}
		
		if(mode=="2")//get day end amount on date change
	    {
	   	 	var objVal= document.getElementsByName("strDayEndAmount")[0];
	   	    var objVal1= document.getElementsByName("strDayEndCreditCol")[0];
	   	    var objVal2= document.getElementsByName("strDayEndInstAmt")[0];
			if(res=="")
			{
				objVal.value="0";
				objVal1.value="0";
				objVal2.value="0";
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtLabel').innerHTML="0";
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
			}
			else
			{
				var ca=res.split('#')[0];
				var cra=res.split('#')[1];
				var ia=res.split('#')[2];
				objVal.value= ca;
				objVal1.value= cra;
				objVal2.value= ia;
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>"+ca+"</b></font>";	
				document.getElementById('dayEndAmtLabel').innerHTML=ca;
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>"+cra+"</b></font>";	
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>"+ia+"</b></font>";	
			}
		}		
		if(mode=="3")//get day end amount user n counter wise using selected value from drop down..
	    {
	   	 	var objVal= document.getElementsByName("strDayEndAmount")[0];
     
			if(res=="")
			{
				objVal.value="0";
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtLabel').innerHTML="0";
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
			}
			else
			{
				objVal.value= res;
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>"+res+"</b></font>";
				document.getElementById('dayEndAmtLabel').innerHTML=res;
			}
		}
}		

function hidePendingDayEndDetails()
{
//	document.getElementById("pendingDayEndDetailsTable").innerHTML="";
//	document.getElementById("test").src="../../hisglobal/images/plus.gif";
	
	var imgElt     =document.getElementById("showPendingDayEndTableId");
	//alert("document.getElementById('test'):::"+document.getElementById("test"));
	tableHide('pendingDayEndDetailsTable',imgElt);
}

/* showBreakupDiv()
{
	if(document.getElementById("DenominationDivId").style.display=="")
	{
		document.getElementById("DenominationDivId").style.display="none";
		document.getElementById("minusign").style.display="none";
		document.getElementById("plussign").style.display="";
	}
	else
	{
		document.getElementById("DenominationDivId").style.display="";
		dfunctionocument.getElementById("minusign").style.display="";
		document.getElementById("plussign").style.display="none";
	}
}*/

function  showhideAmtDiv()
{
	var obj=document.getElementById("allAmountDiv");
	if(obj.style.display=="none")
	{
		obj.style.display="";
	}
	else
	{
		obj.style.display="none";
	}
}

