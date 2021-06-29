// For Bill Approval transaction


/*

function rasieBySuppAmt(obj,event)
{
	var retVal=validateData(event,5);
	alert(retVal);
	var raiseAmtDivId=document.getElementById("strRaiseBySuppTotAmt");
	if(retVal && obj.value!="")
	{
	  var cost=roundValue(parseFloat(document.forms[0].strBillRaisebySuppAmt.value)+parseFloat(document.forms[0].strMiscllRaisebySuppAmt.value),2);
	  raiseAmtDivId.innerHTML="<font color='red'>"+cost+"</font>";
	}
	
}


function sancAmt(obj,event)
{
	var retVal=validateData(event,5);
	alert(retVal);
	var sancAmtDivId=document.getElementById("strSancTotAmt");
	
	if(retVal && obj.value!="")
	{
	  var cost=roundValue(parseFloat(document.forms[0].strBillSancAmt.value)+parseFloat(document.forms[0].strMiscllSancAmt.value),2);
	  sancAmtDivId.innerHTML="<font color='red'>"+cost+"</font>";
	}
}
*/
/*
 * Global variables Declaration Here
 */
var strLatePenelty=0.0;
var strRejectedPenelty=0.0;
var totalPeneltyImposed=0;
var scheduleNos="";
var flag=false;
var peneltyFlag=false;
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
        var objVal = document.getElementById("scheduleNoSelectHLPDivId");
        objVal.innerHTML =res;
        document.getElementById("otherDetailsDIV").style.display="block";
      
        totalPeneltyImposed=manipulateValue(strLatePenelty,strRejectedPenelty,0);
        objVal =document.getElementById("peneltyDivId");
        var strPenaltyDtl="<a style='color: blue;cursor: pointer; ' onClick='getPeneltyDtl();'>"+totalPeneltyImposed+"</a>";
        objVal.innerHTML=""+strPenaltyDtl;
        calculateNetCost();
        
     }
     if(mode=="2"){
     		document.getElementById("peneltyItemListDtl").innerHTML =res;
     		document.getElementById("peneltyListDtlId").style.display="block";
     }
}
/*
 * THIS FUNCTION IS USED TO DISPLAY lIST oF ITEMS ON WHICH PENELTY 
 */
function showPeneltyItemDtl(){
	if(!flag){
			document.getElementById("itemDtl").style.display="block";
			flag=true;
	}else{
		document.getElementById("itemDtl").style.display="none";
		flag=false;
	}
}
function closePeneltyItemDtl(){
	hide_popup_menu('peneltyListDtlId');
	
}
/*
 * This function is used to fetch penelty Item details
 */
function getPeneltyDtl(){
	
	var strPoStoreId=document.forms[0].strStoreId.value;
	var mode="PENELTYDTL";
	var poNo=document.forms[0].strPONo.value;
	var url="BillApprovalTransCNT.cnt?hmode="+mode+"&scheduleNos="+scheduleNos+"&poStoreId="+strPoStoreId
	 +"&poNo="+poNo+"&latePenelty="+strLatePenelty+"&rejectedPenelty="+strRejectedPenelty;
    ajaxFunction(url,"2");
}
function calculateNetCost()
{
	    var retVal=true;
	    var totItemCost=document.forms[0].strTotalItemCost.value;
        var overalltaxPercent=document.forms[0].strOverallPOTax.value;
        var balanceAdvance=document.forms[0].strBalanceAdvance.value;
        document.forms[0].strNetPenalty.value=totalPeneltyImposed;
        var netpenalty=/*totalPeneltyImposed;*/document.forms[0].strNetPenalty.value;
        var netCost=0;
        var waiveOffamt=0;
        var advanceAdjust=document.forms[0].strAdvanceAdjusted.value;
        if(advanceAdjust=="")
          advanceAdjust="0";
        var tax=parseFloat(overalltaxPercent)*parseFloat(totItemCost)/100;
        if(parseFloat(advanceAdjust)>parseFloat(balanceAdvance))
        {
        	alert("Adjusted Advance cannot be greater than Balance Advance");
        	document.forms[0].strAdvanceAdjusted.value="0";
        	retVal=false;
        }
          if(parseFloat(netpenalty)==0)
         {
         	document.forms[0].strWaiveOffChk.checked=false;
         	document.forms[0].strWaiveOffChk.disabled=true;
         }
        if(retVal && parseFloat(netpenalty)>0)
        {
        	
          	waiveOffamt=document.forms[0].strWaiveOffAmt.value;
          	if(waiveOffamt=="")
          	  waiveOffamt="0";
          	if(parseFloat(waiveOffamt)>parseFloat(netpenalty))
          	{
          		alert("Waive Off Amount cannot be greater than Net Penalty");
          		document.forms[0].strWaiveOffAmt.value="0";
          		retVal=false;
          	}
          	
        }
        if(retVal)
        {
           netCost=parseFloat(totItemCost)+parseFloat(tax)-parseFloat(netpenalty)+parseFloat(waiveOffamt)-parseFloat(advanceAdjust);
           netCost=roundValue(parseFloat(netCost),2);
           var objOverallTax=document.getElementById("overallTaxDIV");
           var objNetCost=document.getElementById("netCostDIV");
           tax=roundValue(tax, 2); 
           netCost=roundValue(netCost, 2); 
           objOverallTax.innerHTML=tax;
           objNetCost.innerHTML=netCost;
           document.forms[0].strNetItemCost.value=netCost;
        }   
}

function goFunc()
{
	var hisValidator = new HISValidator("billApprovalTransBean");
    hisValidator.addValidation("strPONoCmb","dontselect=0","Please select PO No" );
    var retVal = hisValidator.validate();
	if(retVal)
	{
		document.getElementById("onGOClickDtlsDivId").style.display="block";
	 	document.forms[0].hmode.value="GET_PO_DETAILS";
	 	document.forms[0].submit();
	}
	 else
	{
		return false;
	}
}

function initPage()
{
	if(document.forms[0].strPONo.value!="")
	{
		document.getElementById("onGOClickDtlsDivId").style.display="block";
		if(document.forms[0].strPOPrefix.value!="")
		   document.getElementById("poNoWithPrefixDIV").innerHTML="<font color='blue'>"+document.forms[0].strPOPrefix.value+"/"+document.forms[0].strPONo.value+"</font>";
		else
		   document.getElementById("poNoWithPrefixDIV").innerHTML="<font color='blue'>"+document.forms[0].strPONo.value+"</font>";
	    if(document.forms[0].strAgentNameShow.value=="1")
	       document.getElementById("agentNameDivId").style.display="block";
	    else
	       document.getElementById("agentNameDivId").style.display="none";     
	}
	else
	{
		document.getElementById("onGOClickDtlsDivId").style.display="none";
	}
 
}
var compileStat=true;
function scheduleCheckCompile()
{
	if(compileStat)
	{
	  var obj=document.getElementsByName("strScheduleChk");
	  var obj1=document.getElementsByName("strPeneltyLateSchduleWise");
	  var obj2=document.getElementsByName("strPenltyRejejectedSchduleWise");
	  var checkedStat=false;
	  var count=0;	
	  for(var i=0;i<obj.length ;i++)
	  {
		if(obj[i].checked)
		{
			checkedStat=true;	
			count=count+1;
			strLatePenelty=manipulateValue(strLatePenelty,parseFloat(obj1[i].value),0);
			strRejectedPenelty=manipulateValue(strRejectedPenelty,parseFloat(obj2[i].value),0);
			if(count==1)
			{
				
				scheduleNos=obj[i].value;
			} 
			else
			{
				scheduleNos=scheduleNos+"^"+obj[i].value;
			}
		}
	  }
	  if(checkedStat==true)
	  {
		for(var i=0;i<obj.length;i++)
	    {
	    	obj[i].disabled=true;
	    }
	    document.forms[0].compile.disabled=true;
	    compileStat=false;
		var mode ="ScheduleItemDtls";
		var po_StoreId=document.forms[0].strPOStoreId.value;
		var poNo=document.forms[0].strPONo.value;
        var url="BillApprovalTransCNT.cnt?hmode="+mode+"&scheduleNoList="+scheduleNos+"&poStoreId="+po_StoreId+"&poNo="+poNo;
        ajaxFunction(url,"1");
	  }
	  else
	  { 
		alert("Please Select a Schedule!!");
	  }
	}
	else
	{
		alert("Complile Disabled.Press Clear To Compile Again.");
	} 
}


function waiveOffdtl(obj)
{
	if(obj.checked)
	{
		document.getElementById("waiveOffDtlDIV").style.display="block";
	}
	else
	{
		document.getElementById("waiveOffDtlDIV").style.display="none";
	}
	
}

function validate1()
{
	 var retVal=true;
	 if(compileStat)//Means Compilation Not Done
	 {
	 	alert("Please First Compile the Schedule List By Selecting One or More Records");
	 	retVal=false;
	 } 
	 var netpenalty=document.forms[0].strNetPenalty.value;
	 if(retVal && parseFloat(netpenalty)>0 && document.forms[0].strWaiveOffChk.checked==true)
	 {
	 	if(document.forms[0].strWaiveOffApprovedDate.value=="")
	 	{
	 	  alert("Please select WaiveOff Approved Date");
	 	  retVal=false;
	 	}
	 	else
	 	  retVal=true;   
	 }
	 if(retVal)
	 {
	 	if(document.forms[0].strBillDate.value=="")
	 	{
	 	  alert("Please select Supplier Bill Date");
	 	  retVal=false;
	 	}
	 	else
	 	  retVal=true;   
	 }
	 if(retVal)
	 {
	   var hisValidator = new HISValidator("billApprovalTransBean");
	   hisValidator.addValidation("strAdvanceAdjusted","req","Please enter Adjusted Advance Amount" );
	   if(parseFloat(netpenalty)>0 && document.forms[0].strWaiveOffChk.checked==true)
       {
     	  hisValidator.addValidation("strWaiveOffAmt","req","Please enter Waive Off Amount" );
          hisValidator.addValidation("strWaiveOffApprovedBy","dontselect=0","Please select Waive Off Approved By" );
          hisValidator.addValidation("strWaiveOffApprovedDate","dtltet="+document.forms[0].strCurrentDate.value,"Please select WaiveOff Approved Date Less Than Equal To Current Date" );
       }
       hisValidator.addValidation("strBillNo","req","Please enter Supplier Bill/Invoice No" );
       hisValidator.addValidation("strBillAmount","req","Please enter Supplier Bill/Invoice Amount" );
       hisValidator.addValidation("strBillDate","dtltet="+document.forms[0].strCurrentDate.value,"Please select Supplier Bill Date Less Than Equal To Current Date" );
       hisValidator.addValidation("strRemarks","req","Please enter Remarks");
       
       
       hisValidator.addValidation("strBillAmount","numltet="+document.forms[0].strNetItemCost.value,"Bill Amt should be Less Than Equal To Net Cost" );
       
       retVal = hisValidator.validate(); 
       hisValidator.clearAllValidations();
	 }  
     if(retVal)
     {
     	
			           
			           var conf = confirm("You Are Going To Save Records");
					                  if(conf == true)
					                  {
					                       var conf1 = confirm("Are you sure !!!");
					                       if(conf1 == true)
					                       {
					                          document.forms[0].hmode.value = "INSERT";
                                              document.forms[0].submit();
					                       }
					                      else
					                       {
					                         return false;
					                       }
					                   }
					                  else
					                   {
					                         return false;
					                   }
			                
			           
			           
			           
			           
			           
			           
			           
			           
			           
     	 
        	
     }
     else
     {
        return false;
	 }	
}



function cancel1()
{
	document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}
function cancelView()
{
	document.forms[0].hmode.value="LIST";
	document.forms[0].submit();
}
function clearDtl()
{
	document.forms[0].hmode.value="CLEAR";
	document.forms[0].submit();
}


// Function for Numeric(11,2)
function numericWithTwoDecimalPlaces(fld, milSep, decSep, e)
{
	var sep = 0;
	var key = '';
	var milSep="";
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? e.which : e.keyCode;
	//if (whichCode == 13) return true;  // Enter
	if (whichCode == 0)	return true; //tab-index
	//alert(whichCode);
	if (whichCode == 8) return true;  // Back-Space 
	key = String.fromCharCode(whichCode);  // Get key value from key code
		if (strCheck.indexOf(key) == -1) return false;  // Not a valid key
		//len = fld.value.length;
		len=11;
		for(i = 0; i < len; i++)
		if ((fld.value.charAt(i) != decSep)) break;
		aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(fld.value.charAt(i))!=-1) aux += fld.value.charAt(i);
	aux += key;
	len = aux.length;
	if (len == 0) fld.value = '';
	if (len == 1) fld.value = ''+ decSep + '' + aux;
	if (len == 2) fld.value = ''+ decSep + aux;
	if (len > 2) {
	aux2 = '';
	for (j = 0, i = len - 3; i >= 0; i--) {
	if (j == 3) {
	aux2 += milSep;
	j = 0;
	}
	aux2 += aux.charAt(i);
	j++;
	}
	fld.value = " ";
	len2 = aux2.length;
	for (i = len2 - 1; i >= 0; i--)
	fld.value += aux2.charAt(i);
	fld.value += decSep + aux.substr(len - 2, len);
	}
	return false;
}