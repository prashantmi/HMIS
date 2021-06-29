
function onEnterDiscount(index,strTrfRate,strReqQty,strQtyBaseVal,strPrevDiscountUnit,strPrevDiscountType,strRateBaseVal)
{
	var flag = 1;
	
	var disUnitObj = document.getElementById("opd_discount"+index);
 	var disTypeObj = document.getElementById("opd_discountType"+index); 
 	
	var strDiscountUnit = disUnitObj.value;
 	var strDiscountType = disTypeObj.value; 
 	
 	if(strDiscountType == "" || strDiscountType == null) strDiscountType = "0";
 	strDiscountType = parseInt(strDiscountType,10);
 	
 	if(strDiscountUnit == "" || strDiscountUnit == null) strDiscountUnit = "0";
 	strDiscountUnit = parseFloat(strDiscountUnit);
 	
 	var objDisUnit = document.getElementById("strDiscountUnit"+ index); //previous disc/unit
    var objDisType = document.getElementById("strDiscountType"+ index);	//previous dis type
    
    var objDisAdd = document.getElementById("trfAdd_Dis"+ index);
    var objPrevDisAdd = document.getElementById("strhiddTrfAddDis"+ index); //hiddent field for add with prev dis
     
    if(parseFloat(objDisUnit.value) > 0 && objDisAdd.disabled == false && objDisAdd.checked == true)
    {
     	if(parseInt(objDisType.value) != parseInt(disTypeObj.value))
     	{
     		alert("Previous Discount Type should be same with current discount type if Addition flag is true !!");
     		disUnitObj.value = "0.00";
     		disTypeObj.selectedIndex = parseInt(objDisType.value) - 1;
     		objDisAdd.checked = false;
     		flag = 0;
     	}
     	else
     		strDiscountUnit = parseFloat(strDiscountUnit) + parseFloat(objDisUnit.value);
     		
    }
    
 	if (strDiscountType == 2)
 	{
 		if (strDiscountUnit > 100)
 		{
 			alert("Discount can not be greater than 100%");
 			disUnitObj.value = "0.00";
 			flag = 0;
 		}
 	}
 	
 	if (flag == 1)
 	{
	 	var strTrfAmt = calTariffCost(strReqQty,strQtyBaseVal,strTrfRate,strRateBaseVal,0); 
	 	var discountAmt = calTrfDiscountCost(strTrfAmt,strReqQty,strQtyBaseVal,strDiscountUnit,strDiscountType,strRateBaseVal);
	 	
	 	if (parseFloat(strTrfAmt) - Math.abs(parseFloat(discountAmt)) < 0)
	 	{
	 		alert("Discount Amt can not be greater than tariff total amount !!");
	 		disUnitObj.value = "0.00";
	 		discountAmt = 0;
	 	}
	 }
 	 else
 	 	discountAmt = 0;
 	
 	//set hiddent value for add with previous amount
 	if(objDisAdd.checked == true)
 		objPrevDisAdd.value = "1";
 	else
 		objPrevDisAdd.value = "0";
 			 	
 	document.getElementById("disAmt"+index).value=discountAmt;
	document.getElementById("disAmtDivId"+index).innerHTML = discountAmt;
	
	DiscountCost();
 	
}

//-----------JS FUNCTIONS from JSP file-------------------------//

function goFunc()                //  for CR No. field validation
{

  
		var hisValidator = new HISValidator("discountApprovalTransBean");  
	 	hisValidator.addValidation("strCrNo", "req", "CR No. is a Mandatory Field" );
		 hisValidator.addValidation("strCrNo", "minlen="+document.forms[0].strCrNo.maxLength,"CR No. must be "+document.forms[0].strCrNo.maxLength+" Digits" );
		 var retVal = hisValidator.validate(); 
	    document.forms[0].strTempVal.value = document.forms[0].strCrNo.value;
	    if(retVal)
	    {
	    	if(validateFunc(document.forms[0].strCrNo,5))
			{
	    		document.forms[0].hmode.value="GO";
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
//------------------This Function Call When We Get Cr No from Patient Listing------------------------//
function hlpOnLoad()
{   
	
	      if(document.forms[0].CrNo.value!="")
          {
	    	if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered  
		    {  
               document.getElementById("disBnR").style.display="block";
               document.getElementById("patdtltld").style.display="block";
               document.getElementById("tldglbdiv").style.display="block";
               document.getElementById("id1").style.display="block";
	           var mode="BId";
	           var url="DiscountApprovalTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strChk[0].value;
		       ajaxFunction(url,"1");
		     } 
		    else//predefined cr no entered
		    {
		       
		       document.getElementById("tldglbdiv").style.display="none";
               document.getElementById("patdtltld").style.display="none";
               document.getElementById("id1").style.display="none";
               document.getElementById("onLinetld").style.display="none";
               document.getElementById("disBnR").style.display="none";
               SetCursorToTextEnd('strCrNoId');
               /*document.getElementById("errMsg").style.display="block";
               document.forms[0].hmode.value="CANCEL";
               document.forms[0].submit();*/
		    }
		  }
		  else
		  {
			 //SetCursorToTextEnd('strCrNoId');
			  document.getElementById("tldglbdiv").style.display="none";
              document.getElementById("patdtltld").style.display="none";
              document.getElementById("id1").style.display="none";
              document.getElementById("onLinetld").style.display="none";
		  }
		  
}



     /**
	   * initGoFunc
	   * @param {Event} eve 
	   */
      function initGoFunc(eve)
      {
      	 var flag=validateData(eve,5);
  	if(flag){
	   	if(eve.keyCode == 13)
	   	{
		   goFunc();
		   return false;
		}	   	
	  }else{
	   		return false;
	   }
	  }
//------------If Enter Key Press--------------//
function goFuncOnEnter(e)
{
   if(e.keyCode == 13)
   {
	 goFunc();
	}
	else
	{
	 return false;
	}
}  
//           Tariff Dtl              //
function tariffDtl(parent) //(this,AdmnNo,ClientPatNo,CltAmt,NetAmt)
{        
     //Appended Value->HBLDT_APPROVAL_DATE^PSTR_EMPLOYEE_NUMBER
	 //^fun_emp_name^HBLNUM_USER_LEVEL^HBLSTR_REMARKS
        var approvalDtl = document.getElementById("strApprovDtl").value;        
        myArray=approvalDtl.split("^");
        var objVal1 = document.getElementById("1");
       
        if(myArray[2]!='null' || myArray[2]!='')
        {
          objVal1.innerHTML = myArray[2];
        }
        else
        {
          objVal1.innerHTML = "  ----";
        }  
              
        var objVal2 = document.getElementById("2");
        
        if(myArray[0]!='null')
        {
          objVal2.innerHTML = myArray[0]; 
        }
        else
        {
          objVal2.innerHTML = "  ----";
        }  
        var objVal3 = document.getElementById("3");
        
        if(myArray[4]!= 'null')
        {
          objVal3.innerHTML = myArray[4];  
        }
        else
        {
          objVal3.innerHTML = "  ----";
        }  
                       
		display_popup_menu(parent,'tariffDtl','300','');
}

//---------------This is for Pop-Up-----------------------//
function discountDtl2(parent, val) //(DiscBy^DisDate^DiscountReson)
{        
    //Appended Value->HBLDT_APPROVAL_DATE^PSTR_EMPLOYEE_NUMBER
	//       ^fun_emp_name^HBLNUM_USER_LEVEL^HBLSTR_REMARKS

        myArray=val.split("^"); 
        var objVal1 = document.getElementById("11");
        
        if(myArray[2]!='null' || myArray[2]!='')
        {
          objVal1.innerHTML = myArray[2];
        }
        else
        {
          objVal1.innerHTML = "  ----";
        }  
        var objVal2 = document.getElementById("22");
        if(myArray[0]!='null')
        {
          objVal2.innerHTML = myArray[0]; 
        }
        else
        {
          objVal2.innerHTML = "  ----";
        }  
        var objVal3 = document.getElementById("33");
        if(myArray[4]!= 'null')
        {
          objVal3.innerHTML = myArray[4];  
        }
        else
        {
          objVal3.innerHTML = "  ----";
        }  
        display_popup_menu(parent,'discountDtl2','300','');
}

//----------------AJAX-------------------------//
function groupCheck(chkObj,index)
{ 
  // document.getElementById("save").disabled=false;
  
   document.forms[0].strTempVal.value=chkObj.value;
  // alert("groupCheck document.forms[0].strTempVal.value-"+document.forms[0].strTempVal.value);
  // alert("index is::"+index);
  // alert("REQ_NO+SBLNUM_BSERVICE_ID+HBLNUM_PATACCOUNT_NO+HiddenValue::"+document.forms[0].strTempVal.value);
   var j;
   var mode="BId";
   i=document.forms[0].strChk_values.length;
   var url="DiscountApprovalTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strTempVal.value;
   ajaxFunction(url,"1");
}
var pWindow ="";


function myFunc1(obj,index)
{
	        pWindow = obj;
			var o1 = document.getElementsByName("strApprovDtl");
    		//alert("o1-->"+o1[index].value);  Here We Get Value For Pop=Up window
    		var objEle = document.getElementById("menu1");
		    discountDtl2(pWindow,o1[index].value);
}


function myFunc(obj,index)
{
	        pWindow = obj;
			var mode = "PopUp"; 
			var objVal1 = document.getElementById("menu1");
			var o = document.getElementsByName("trfDisApproval");
			var o1 = document.getElementsByName("strApprovDtl");
			//alert("o1-->"+o1[index].value);
			
		    document.forms[0].strTempVal.value=o[index].value;
	        // alert("strTempVal for Tariff Dtl::"+o[index].value);
	   //    alert("Popup_Id for Tariff Dtl::"+document.forms[0].strPopUpId.value);
		    document.forms[0].strPopUpId.value = 1;
	        var url="DiscountApprovalTransCNT.cnt?hmode="+mode+"&modName="+document.forms[0].strTempVal.value+"&popUpId="+document.forms[0].strPopUpId.value;
	    	ajaxFunction(url,"2"); 
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
       if(mode=="1" || mode=="3" )
 	   {
		   var objVal = document.getElementById("id1");
		   objVal.innerHTML = res; 
           if(mode=="3")
            {
		       document.forms[0].strCancelRmk.focus();
		    }   
       }
       else 
       if(mode == '2')
       {
		   var objEle = document.getElementById("menu1");
		   discountDtl2(pWindow,res);
		   
	   }
	   if(mode == '4')
       {
		   var objEle = document.getElementById("menu1");
		   objEle.innerHTML = res;
		   objEle.style.display="none";
		   display_popup_menu(pWindow,"menu1","365","");
	   }
	     
}  
//----------------------This Function Call When Disc Detail Chk Box Selected---------------------------// 
function new_dis_add(obj,index,app_id)
{
/*
  document.getElementById("strTempCkhBox"+index).value="0"; //discount type
  
  if(document.forms[0].trfDisApproval[index].value == "0")
  {
    document.forms[0].trfDisApproval[index].value = "1";
  }
  else
  {
     document.forms[0].trfDisApproval[index].value = "0";
  }
  */
   /*
  if(document.getElementById("strPrevDisType"+index).value == "1")
  {
      document.getElementById("opd_discountType"+index).value = "1";
      
  }
  else
  {
      document.getElementById("opd_discountType"+index).value = "2";    
  }
  */
  /*
  if(document.getElementById("strhiddTrfAddDis"+index).value == "0")
  {
    document.getElementById("strhiddTrfAddDis"+index).value = "1";
  }
  else
  {
     document.getElementById("strhiddTrfAddDis"+index).value = "0";
  } 
  */
  
  //alert("Hidden Value After Change:::->"+document.getElementById("strhiddTrfAddDis"+index).value);
  //document.forms[0].opd_discount[index].value="";
  
  var disObj = document.getElementById("opd_discount" + index);
  var disTypeObj = document.getElementById("opd_discountType" + index);
  var objDisAmt = document.getElementById("disAmt"+ index);
  var objDisAdd = document.getElementById("trfAdd_Dis"+ index);
  
  disObj.value="0.00";
  objDisAmt.value = "0.00";
  document.getElementById("disAmtDivId"+index).innerHTML = "0.00";
     	 
  if(obj.checked)
  {
  	 disObj.readOnly=false;
     disTypeObj.disabled=false;
     
     var objDisUnit = document.getElementById("strDiscountUnit"+ index);
     var objDisType = document.getElementById("strDiscountType"+ index);
     
     if(parseFloat(objDisUnit.value) > 0)
     {
     	objDisAdd.disabled = false;
     	if(parseInt(objDisType.value) == 1)
     		disTypeObj.selectedIndex="0";
     	else
     		disTypeObj.selectedIndex="1";	
     }
     /*
     var myArray=new Array();
     var cmb=document.forms[0].opd_discountType[index];
    
     var objDisAdd=document.getElementsByName("trfAdd_Dis");
     myArray=(objDisAdd[index].value).split("^");
 	if(myArray[0]=="1" && cmb[cmb.selectedIndex].text!="Fixed")
    {
       cmb.selectedIndex="0";
    
    }
    else 
    if(myArray[0]=="2" && cmb[cmb.selectedIndex].text!="% age")
    {
  
      cmb.selectedIndex="1";
     }
     */
   	disObj.focus();
 }
 else
 {
  	
  	 
  //if(document.forms[0].opd_discount[index].value=="")
     
     
     
     //document.getElementById("disAmtDivId"+index).innerHTML = "0";
     
   //document.forms[0].strCaldisAmt[index].value="0";
     //document.forms[0].opd_discountType[index].value="1";
     //document.forms[0].totDisAmt.value="0";
     //document.getElementById("totDisAmtDivId").innerHTML="0";
     
     disObj.readOnly=true;
     disTypeObj.disabled=true;
     objDisAdd.checked=false;
     objDisAdd.disabled=true;
 }
 
 DiscountCost();
 
 //alert("At Last TempChkBox::"+document.getElementById("strTempCkhBox"+index).value);
}

/*
	Description >> This JS function is used to calculate tariff discount cost for a single tariff. 
					Discount will be calculated on Total Tariff Amount
	Parameter >> trfAmt :: Tariff Cost (qty * rate)
	Return Value >> Returns Discount Amount
*/
function DiscountCost() 
{
	var netAmt = calAllTariffNetCost("disAmt");
	
	document.forms[0].totDisAmt.value = netAmt;
	document.getElementById("totDisAmtDivId").innerHTML = netAmt;
 }
  

  
//////////////////////////////////////////
function testFunction(obj)
{
	// alert("Event val...." + obj.value);
}
//////////////////////JS Functions for list page/////////////////////

	function test_cancelled()
	{
	  add("CANCELLED");
	}
	
///////////////////for selecting a record to cancel/////////////////
	
	function cancel_body_load()
	{
	   var mode="Cancel_hlp";
	   var url="DiscountApprovalTransCNT.cnt?hmode="+mode;
	   ajaxFunction(url,"3");
	}

   function changeRecordStatus2(obj)
	{
		comValue = obj.value;
  		if(comValue == 2)
		{
		comb=1;
		document.getElementById("Approved").style.color = "blue";
		document.getElementById("buttonL1Id2").disabled=true;
		}
		else
		 document.getElementById("buttonL1Id2").disabled=false;
	}
	
	function checkColor2()
	{
	  if(document.getElementById("buttonL1Id2").disabled)
	  {
	    return false;
	    }
	    else
	      {
	      var chkVal = document.forms[0].chkLength.value;
	       if(chkVal !=1 )
	       {
	        alert("Please Select One Record");
	        return false;
	       }
	       else
	         add("CANCELLED");
	      }
	 }
// -----------ERROR CHECK FUNCTION ----------------------------//
function CheckError()	 
{ 
   var err = document.forms[0].strGlbErrMsgTLD.value;
   if(document.forms[0].strCrNo.value==null || document.forms[0].strCrNo.value=="")
   {
    
   }   
   else
   {
    if( err != null || err!="") 
    {
   //  alert("Error is::"+document.forms[0].strGlbErrMsgTLD.value);
     document.forms[0].errMsg.value  = document.forms[0].strGlbErrMsgTLD.value;
    }
   }
}	 

	
 function validateFunc1()  
 {
	
 	//alert("validateFunc");
	if(uploadValidateFileType("uploadedFile","20000"))
	{	 
	    if(document.getElementById("hlp_opt_sel").value=='2')
		{	  
		    var retVal = ValidateFinalAdjustment();
	    	if(retVal)
			{
			        document.getElementById("patdtltld").style.display="none";
	                document.getElementById("disBnR").style.display="none";
	                document.forms[0].strCrNo.value="";  
			    	document.forms[0].hmode.value="INSERT";
			    	document.forms[0].submit(); 
			    	
			}
			else
			{
				return false;
			}
		}
		else
		{
		    var retVal = ValidateServcie();	 
		    if(retVal)
			{
			        document.getElementById("patdtltld").style.display="none";
	                document.getElementById("disBnR").style.display="none";
	                document.forms[0].strCrNo.value="";
			    	document.forms[0].hmode.value="INSERT";
			    	document.forms[0].submit();
			}
			else
			{
			    //alert("Please Select Atleast one Tariff Name");
				return false;
			}   
		}
	}	
 	
 }
 
 function ValidateFinalAdjustment()
 {    
      var hisValidator = new HISValidator("discountApprovalTransBean");  
 	  var retValue = false;
 	  hisValidator.clearAllValidations;
 	  if(parseInt(document.getElementById("strIpdBillCurrDis").value) !='0')
 	  {
       hisValidator.addValidation("strIpdBillCurrDis", "req","Discount Amt / Unit is a mandatory field");
       hisValidator.addValidation("strRmk", "dontselect=0","Discount By is a mandatory field");
       hisValidator.addValidation("strDrt", "req","Discount Reason is a mandatory field");
       var retVal = hisValidator.validate(); 
    	if(retVal)
		{
		   	return true;
		}
		else
		{
			return false;
		}
	  }
	  else
	  {
	   alert("Please Enter Discount Value Greater than Zero!!!"); 
	   return false;
	  }		
 }
 function ValidateServcie()
 {
      var retVal = true;
      
 	     var chkObj  = document.getElementsByName("trfDisApproval");
 	   //  var opdDis  = document.getElementsByName("opd_discount");
 	      var length = chkObj.length;
 	     // alert("chkObj.length="+chkObj.length)
 	     if(chkObj.length > 0)
 	     {
 	     // alert("inside if chkObj.length="+chkObj.length)
 	     	var flag = "0";
 	      	for(var i = 0 ; i < length; i++)
 	     	{
 	     		
 	     	        var chkObj = document.getElementById("trfDisApproval"+i);
 	     	        var discountAmt = document.getElementById("opd_discount"+i).value; 
					if(chkObj.checked ==  true )
					{
						flag = "1";
						if(parseInt(discountAmt) <= 0)
						{
						alert("Discount Amount should be greater than Zero!!");
						retVal = false;
						}
						/*if(retVal)
						{
						retVal = checkAmount(discountAmt,'10,2'); 
						}*/
						
						//alert("flag="+flag);
					}
 	       	}
 	       //	alert("flag="+flag);
 	       	if(flag == "0") 
 	       	{
 	       	alert("Please Select Atleast one Tariff Name !!!"); 
 	       	retVal = false;
 	       	}
 	     }
 	     
 	     if(retVal)
 	     {
 	//alert("ValidateServcie");
       var hisValidator = new HISValidator("discountApprovalTransBean");  
 	// var retValue = false;
 	   hisValidator.clearAllValidations;
 	   hisValidator.addValidation("strRmk", "dontselect=0","Discount By is a mandatory field");
       hisValidator.addValidation("strDrt", "req","Discount Reason is a mandatory field");
       hisValidator.addValidation("opd_discount", "amount=10,2","Discount Amount should be in correct format(00000000.00)");
        
       retVal = hisValidator.validate();
       }
       
 	   
 	 return retVal;
 }
 
 function initPage()
{
  document.forms[0].hmode.value="APPROVED";
  document.getElementById("patdtltld").style.display="none";
  document.getElementById("disBnR").style.display="none";
  document.forms[0].strCrNo.value="";
  document.forms[0].submit();
}

 function cancel()
 {
        document.getElementById("errMsg").innerHTML = "";
	 	document.forms[0].hmode.value = "CANCEL";
  	    document.forms[0].submit();
 }

function cancel_dtl_sub()
{
  if(document.forms[0].strCancelRmk.value=="")
   {
    alert("Cancellation Remarks is Mandatory");
    return false;
   }
   else
    {
     return true;
    }
}
//-----------------This is For Discount Reson Combo-------------------//
function groupCombo()
{

   var l =document.forms[0].strRsn.length-1;
  // alert("document.forms[0].strRsn."+document.forms[0].strRsn.value);
   if(document.forms[0].strRsn.value == '0')
   {
    document.forms[0].strDrt.value = "";
    document.forms[0].strDrt.focus();
    document.forms[0].strDrt.readOnly=false;
   }
   else
   {
    document.forms[0].strDrt.value=document.forms[0].strRsn[document.forms[0].strRsn.selectedIndex].text;
    document.forms[0].strDrt.readOnly=true;
   }
}

function butdis()
{
  //document.getElementById("saveImg").disabled=true;
  if(checkCrdef(document.getElementById("strCrNoId"))==false)//Complete CR No Entered  
  {
   document.forms[0].strCrNo.readOnly=true;
   document.getElementById("lastbuttons").style.display="block"; 
 	document.getElementById("onlyClearbutton").style.display="none"; 
  }
  document.forms[0].strMsgString.value="";
  
  //make other ti enabled only when there is jz 'others' in discount reason combo..
   if((document.forms[0].strRsn.value == '0')&&(document.forms[0].strRsn.options.length == 1))
   {
    document.forms[0].strDrt.value = "";
    document.forms[0].strDrt.focus();
    document.forms[0].strDrt.readOnly=false;
   }
   else
   {
   		document.forms[0].strDrt.readOnly=true;
   }
}
function opd_Dis()
{
    document.forms[0].opd_discount.value="";
}
function trfAddDis(obj,index)
{
 var myArray=new Array();
 var cmb=document.forms[0].opd_discountType[index];
   
 myArray=(obj.value).split("^");
 
 if(myArray[0]=='2' && cmb[cmb.selectedIndex].text=="Fixed")
 {
   alert("Discount Entered should be in percentage!!!");
   document.forms[0].opd_discount.value="";
   
 } 
 else if(myArray[0]=="1" && cmb[cmb.selectedIndex].text=="% age")
 {
   alert("Discount Entered should be Fixed type!!!");
   document.forms[0].opd_discount.value="";
    }
  }
function hideDetails()
{
 document.getElementById("minusonLineId").style.display="none";
 document.getElementById("plusonLineId").style.display="block";
 document.getElementById("patdtltld").style.display="none";
}
function showDetails()
{
 document.getElementById("plusonLineId").style.display="none";
 document.getElementById("minusonLineId").style.display="block";
 document.getElementById("patdtltld").style.display="block";
}

function hidePayDetails(divId)
{
      hide_popup_menu(divId);
}

/**
   * setSelectedCrNo
   * @param {String} crNo 
   */
 
  function setSelectedCrNo(crNo) 
  {   	
	document.forms[0].strCrNo.value = crNo;
	goFunc();
  }
 
 function addDisCol(i)
  {
    var k = 0 ;
  	
  	var chk = document.getElementById("trfAdd_Dis"+i).value;
  	
  //	alert("Chk Value"+document.getElementById("trfAdd_Dis"+i).value);
 	if(chk != 0)
 	{
 	k++;
 	var a = document.getElementById("hideLstDis"+i).value;
 	var b = document.getElementById("opd_discount"+i).value;
    var c = parseInt(a);
    var d = parseInt(b);
   
 	document.getElementById("disAmt"+i).value  = c+d;
 	document.getElementById("disAmtDivId"+i).innerHTML = c+d; 
  	document.getElementById("trfAdd_Dis"+i).value  = "0";
  	document.getElementById("strTempCkhBox"+i).value  = "0";
 	
 	}
 	else
 	{
 	 document.getElementById("disAmt"+i).value = "0";
 	 document.getElementById("disAmtDivId"+i).innerHTML = "0";
 	 
 	}
 }
 /************************Fianl Adjustment************************************/
 
 /*
 function calDiscountAmtFinalAdjust()
 {
    alert("Inside calDisFA");
    document.getElementById("checkbox").value ="0";
    var LastDis  = parseFloat(document.getElementById("LstDis").value);
    var EnterDis = parseFloat(document.getElementById("strIpdBillCurrDis").value);
    var TotalDis = 0;
    if(document.getElementById("checkbox").value =="0")
    {
       TotalDis =  EnterDis;
       //alert("TotalDis:::0:"+TotalDis);
    }  
    if(document.getElementById("checkbox").value =="1")
    {
       TotalDis =  LastDis + EnterDis;
       //alert("TotalDis:::1:"+TotalDis);
    }
    if(document.getElementById("discountType").value =="2")
    {
     if(TotalDis >= "100")
     {
      alert("Dis Never Cross 100% Limit!!!");
      }
      else
      {
         calDiscountAmtFinalAdjustment();
      }
    } 
    if(document.getElementById("discountType").value =="1")
    {
      var trfAmt = parseFloat(document.getElementById("Cost").value);
      if(TotalDis >= trfAmt)
      {
          alert("Dis Never greater than Rate <"+trfAmt+">in case of Fixed!!!");
          document.getElementById("strIpdBillCurrDis").value = "0";
          document.getElementById("strIpdBillFianlDis").value = "0";
          
      }
      else
      {
         calDiscountAmtFinalAdjustment();
      }
     }// 
  }
  */
  
  function calDiscountAmtFinalAdjust(obj)
  {
  	var flag = 1;
	
	var disUnitObj = document.getElementById("strIpdBillCurrDis");
 	var disTypeObj = document.getElementById("discountType"); 
 	
 	var strDiscountUnit = disUnitObj.value;
 	var strDiscountType = disTypeObj.value; 
 	
 	if(strDiscountType == "" || strDiscountType == null) strDiscountType = "0";
 	strDiscountType = parseInt(strDiscountType,10);
 	
 	if(strDiscountUnit == "" || strDiscountUnit == null) strDiscountUnit = "0";
 	strDiscountUnit = parseFloat(strDiscountUnit);
 	
 	var objDisUnit = document.getElementById("strDiscountUnit"); //previous disc/unit
    var objDisType = document.getElementById("strDiscountType");	//previous dis type
    
    var objDisAdd = document.getElementById("checkbox"); //whether prev dis add
    var objDisAddHidd = document.getElementById("strhiddTrfFADis"); //hidden for prev dis add
    
    if(parseFloat(objDisUnit.value) > 0 && objDisAdd.disabled == false && objDisAdd.checked == true)
    {
     	if(parseInt(objDisType.value) != parseInt(disTypeObj.value))
     	{
     		alert("Previous Discount Type should be same with current discount type if Addition flag is true !!");
     		disUnitObj.value = "0.00";
     		disTypeObj.selectedIndex = parseInt(objDisType.value) - 1;
     		objDisAdd.checked = false;
     		flag = 0;
     	}
     	else
     		strDiscountUnit = parseFloat(strDiscountUnit) + parseFloat(objDisUnit.value);
     		
    }
    else
    	objDisAdd.checked = false;
    	
 	if (strDiscountType == 2)
 	{
 		if (strDiscountUnit > 100)
 		{
 			alert("Discount can not be greater than 100%");
 			disUnitObj.value = "0.00";
 			flag = 0;
 		}
 	}
 	
 	if (flag == 1)
 	{
 		var strTrfRate = document.getElementById("Cost").value;
 	 	var strTrfAmt = calTariffCost("1","1",strTrfRate,"1",0); 
	 	var discountAmt = calTrfDiscountCost(strTrfAmt,"1","1",strDiscountUnit,strDiscountType,"1");
	 	
	 	//changed on 12Sep2014..
	 	if (Math.abs(parseFloat(strTrfAmt)) - Math.abs(parseFloat(discountAmt)) < 0)
	 	{
	 		alert("Discount Amt can not be greater than tariff total amount !!");
	 		disUnitObj.value = "0.00";
	 		discountAmt = 0;
	 	}
	 	
	 	//alert("amt is::"+Math.abs(parseFloat(strTrfAmt)));
	 	//alert("discount is::"+Math.abs(parseFloat(discountAmt)));
	 }
 	 else
 	 	discountAmt = 0;
 	
 	if(objDisAdd.checked == true)
 		objDisAddHidd.value = "1";
 	else	
 		objDisAddHidd.value = "0";
 	 	
 	document.getElementById("strIpdBillFianlDis").value=discountAmt;
	document.getElementById("disAmtDivId").innerHTML = discountAmt;
	
  }
  
  
  function calcDiscFA()
  {
      var trfAmt = parseFloat(document.getElementById("Cost").value);
	  var qty = parseFloat(document.getElementById("Qty").value);
	  var qty_base_value = parseFloat(document.getElementById("qtyBaseValue").value);
	  var current = parseFloat(document.getElementById("strIpdBillCurrDis").value);
	  var lastDis = parseFloat(document.getElementById("LstDis").value);  
	  var dis_unit =  parseFloat(current);
	  
	  var dis_type = parseFloat(document.getElementById("discountType").value);
	//  document.getElementById("strDiscountType").value = dis_type;
	  
	  var rate_base_value = parseFloat(document.getElementById("strRateBaseValue").value); 
	/*
	 alert("trfAmt----->"+trfAmt);
	 alert("qty----->"+qty);
	 alert("qty_base_value----->"+qty_base_value);
	 alert("current--->"+current);
	 alert("lastDis--->"+lastDis);
	 alert("dis_unit----->"+dis_unit);
	 alert("dis_type----->"+dis_type);
	 alert("rate_base_value-->"+rate_base_value);
	*/
	/*
	Description >> This JS function is used to calculate tariff discount cost for a single tariff. 
					Discount will be calculated on Total Tariff Amount
	Parameter >> trfAmt :: Tariff Cost (qty * rate)
	Return Value >> Returns Discount Amount
    */
    var caldisAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	//alert("Calculated Dis Type---->"+caldisAmt);	
	document.getElementById("strIpdBillFianlDis").value = caldisAmt;
  
  
  }
  
  /*
  function  addDiscountAmtFinalAdjust()
  {
  // alert("Hidden Field Value Before Change:::"+document.getElementById("strhiddTrfFADis").value);
   var chkBoxValue = document.getElementById("checkbox").value;
   if(chkBoxValue == "0")
   {
     document.getElementById("checkbox").value = "1";
     document.getElementById("strhiddTrfFADis").value ="1";
    if(parseFloat(document.getElementById("strIpdBillCurrDis").value)== "null" ||  parseFloat(document.getElementById("strIpdBillCurrDis").value)=="0")
    {
      alert("Please Enter Discount Amt For Further Pocessing !!!");
    }
    else
    {
      var LastDis  = parseFloat(document.getElementById("LstDis").value);
      var EnterDis = parseFloat(document.getElementById("strIpdBillCurrDis").value);
      var TotalDis = LastDis + EnterDis;
 
    if(document.getElementById("discountType").value =="2")
    {
      if(TotalDis >= "100")
      {
       alert("Inside Method!!!!!! 11");           
        var objDisAmt=document.getElementsByName("disAmt");
	    var s=0;
	    for(var j=0;j<objDisAmt.length;j++)
	    {
		if(objDisAmt[j].value=="")
		  s=parseFloat(s);
		else
	      s=parseFloat(s)+parseFloat(objDisAmt[j].value);
	    }
	   // alert("sum of Dis:::"+s);
	    var sumofDis = s;
               
       alert("Discount Never Cross 100% Limit!!!");
      }
      else
      {
         calDiscountAmtFinalAdjustment();
      }
    } 
    if(document.getElementById("discountType").value =="1")
    {
      var trfAmt = parseFloat(document.getElementById("Cost").value);
      if(TotalDis >= trfAmt)
      {
          document.getElementById("checkbox").value = "0";
          document.getElementById("checkbox").checked = false;
          document.getElementById("strhiddTrfFADis").value ="0";
          document.getElementById("strIpdBillCurrDis").value = "0";
          document.getElementById("strIpdBillFianlDis").value = "0";
          alert("Discount Never greater than Rate <"+trfAmt+"> in case of Fixed!!!");
      }
      else
      {
         calDiscountAmtFinalAdjustment();
      }
     }// 
    }
   }
   else
   {
      document.getElementById("checkbox").value = "0";
      document.getElementById("strhiddTrfFADis").value ="0";
      document.getElementById("strhiddTrfFADis").value ="0";
      var trfAmt = parseFloat(document.getElementById("Cost").value);
	  var qty = parseFloat(document.getElementById("Qty").value);
	  var qty_base_value = parseFloat(document.getElementById("qtyBaseValue").value);
	  var current = parseFloat(document.getElementById("strIpdBillCurrDis").value);
	  var lastDis = parseFloat(document.getElementById("LstDis").value);  
	  var dis_unit =  parseFloat(current);
	  
	  var dis_type = parseFloat(document.getElementById("discountType").value);
	  
	  var rate_base_value = parseFloat(document.getElementById("strRateBaseValue").value); 
	
    var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	alert("Calculated Discount Type---->"+disAmt);	
	document.getElementById("strIpdBillFianlDis").value = disAmt;
   }
   alert("After Change Value is:::"+document.getElementById("strhiddTrfFADis").value);
  }
  */
  function calDiscountAmtFinalAdjustment()
  {
      var trfAmt = parseFloat(document.getElementById("Cost").value);
	  var qty = parseFloat(document.getElementById("Qty").value);
	  var qty_base_value = parseFloat(document.getElementById("qtyBaseValue").value);
	  var current = parseFloat(document.getElementById("strIpdBillCurrDis").value);
	  var lastDis = parseFloat(document.getElementById("LstDis").value);  
	  var dis_unit =  parseFloat(current);
	  
	  var dis_type = parseFloat(document.getElementById("discountType").value);
	  
	  var rate_base_value = parseFloat(document.getElementById("strRateBaseValue").value); 
	
	if(document.getElementById("checkbox").value =="0")
    {
       var dis_unit = parseFloat(current);
      
    }  
    if(document.getElementById("checkbox").value =="1")
    {
       var dis_unit =  parseFloat(lastDis)+ parseFloat(current);
      
    }
	    
	/*
	 alert("trfAmt----->"+trfAmt);
	 alert("qty----->"+qty);
	 alert("qty_base_value----->"+qty_base_value);
	 alert("current--->"+current);
	 alert("last--->"+last);
	 alert("dis_unit----->"+dis_unit);
	 alert("dis_type----->"+dis_type);
	 alert("rate_base_value-->"+rate_base_value);
	*/
	/*
	Description >> This JS function is used to calculate tariff discount cost for a single tariff. 
					Discount will be calculated on Total Tariff Amount
	Parameter >> trfAmt :: Tariff Cost (qty * rate)
	Return Value >> Returns Discount Amount
*/
     var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	// alert("Calculated Discount Type---->"+disAmt);	
	 document.getElementById("strIpdBillFianlDis").value = disAmt;
    
      
  }
  
 
 /****************************************************************************/
 function addDiscountAmt(i)
 {
  //alert("CheckBox value:::->"+document.getElementById("trfAdd_Dis"+i).value);
  var chkBoxValue = document.getElementById("trfAdd_Dis"+i).value;
  document.getElementById("strTempCkhBox"+i).value  = "0";
  document.getElementById("strhiddTrfAddDis"+i).value ="1";
  if(chkBoxValue == "0")
  {
    document.getElementById("trfAdd_Dis"+i).value = "1";
    document.getElementById("strTempCkhBox"+i).value  = "1";
   
     if(parseFloat(document.getElementById("opd_discount"+i).value)== "null" ||  parseFloat(document.getElementById("opd_discount"+i).value)=="0")
     {
         document.getElementById("trfAdd_Dis"+i).checked =false;
         document.getElementById("trfAdd_Dis"+i).value ="0"
         document.getElementById("strTempCkhBox"+i).value  = "0";;
         document.getElementById("strhiddTrfAddDis"+i).value ="0";
         alert("Please Enter Discount Amt For Further Pocessing !!!");
     }
     else
     {
         var LastDis  = parseFloat(document.getElementById("hideLstDis"+i).value);
         var EnterDis = parseFloat(document.getElementById("opd_discount"+i).value);
         var TotalDis = LastDis + EnterDis;
      //	 alert("Total Discount::->"+TotalDis);
      	 if(document.getElementById("opd_discountType"+i).value =="2")
      	 {
     	  if(TotalDis >= "100")
     	   {
            	 alert("Discount Never Cross 100% Limit!!! in Method 22");
     	   }
      	   else
      	   {
        	     calDiscountAmt(i);
      	   }
         } 
         if(document.getElementById("opd_discountType"+i).value =="1")
         {
           var trfAmt = parseFloat(document.getElementById("Cost"+i).value);
           if(TotalDis >= trfAmt)
           {
          	   document.getElementById("trfAdd_Dis"+i).checked =false;
          	   document.getElementById("trfAdd_Dis"+i).value ="0";
          	   document.getElementById("strTempCkhBox"+i).value  = "0";
         	   document.getElementById("strhiddTrfAddDis"+i).value ="0";
               document.getElementById("opd_discount"+i).value = "0";
          
               document.getElementById("strDiscountUnit"+i).value = "0";
               document.getElementsByName("disAmt")[i].value = "0";
               document.getElementById("disAmtDivId"+i).innerHTML = "0"; 
               document.forms[0].totDisAmt.value = "0";
               document.getElementById("totDisAmtDivId").innerHTML="0"; 
               alert("Add Disc Never greater than Rate:<" +trfAmt + ">in case of Fixed!!!");
           }
           else
           {
              calDiscountAmt(i);
           }
         } //
       }
    }
    else
    {
   //  alert("Injsdie Else If Value is 1");
    
     // var TotalDisAmt = parseFloat(document.getElementById("totDisAmt").value);
     // var current = parseFloat(document.getElementById("opd_discount"+i).value);
      document.getElementById("trfAdd_Dis"+i).value = "0";
      document.getElementById("strhiddTrfAddDis"+i).value ="0";
      
      var trfAmt         = parseFloat(document.getElementById("Cost"+i).value);
	  var qty            = parseFloat(document.getElementById("qty"+i).value);
	  var qty_base_value = parseFloat(document.getElementById("qtyBaseValue"+i).value);
	  	  
	  var current        = parseFloat(document.getElementById("opd_discount"+i).value);
      
      document.getElementById("strDiscountUnit"+i).value = current;
      
	  var last     = parseFloat(document.getElementById("hideLstDis"+i).value);
	  var dis_unit = parseFloat(current);
	  
	  var dis_type = parseFloat(document.getElementById("opd_discountType"+i).value);
	  
	  document.getElementById("strDiscountType"+i).value = dis_type;
	  
	  var rate_base_value = parseFloat(document.getElementById("strRateBaseValue"+i).value); 
	 
      var disAmt          = calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	
	  document.getElementsByName("disAmt")[i].value = disAmt;
	  document.getElementById("disAmtDivId"+i).innerHTML = disAmt; 
	  addTotDisAmt();
     
    
   }
   
  }
  function calDiscountAmt(i)
  {
  // document.getElementById("trfAdd_Dis"+i).value = "1";
      var trfAmt         = parseFloat(document.getElementById("Cost"+i).value);
	  var qty = parseFloat(document.getElementById("qty"+i).value);
	  var qty_base_value = parseFloat(document.getElementById("qtyBaseValue"+i).value);
	 
	  var current        = parseFloat(document.getElementById("opd_discount"+i).value);
      
      document.getElementById("strDiscountUnit"+i).value = current;
	  
	  
	  var last           = parseFloat(document.getElementById("hideLstDis"+i).value);
	  var dis_unit       =  parseFloat(last)+ parseFloat(current);
	  var dis_type       = parseFloat(document.getElementById("opd_discountType"+i).value);
	  
	  document.getElementById("strDiscountType"+i).value = dis_type;
	  var rate_base_value = parseFloat(document.getElementById("strRateBaseValue"+i).value); 
	
	/*
	 Description >> This JS function is used to calculate tariff discount cost for a single tariff. 
					Discount will be calculated on Total Tariff Amount
	 Parameter >> trfAmt :: Tariff Cost (qty * rate)
	 Return Value >> Returns Discount Amount
    */
    var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	  //alert("Calculated Discount Type---->"+disAmt);	
	 document.getElementsByName("disAmt")[i].value = disAmt;
	 document.getElementById("disAmtDivId"+i).innerHTML = disAmt; 
	 addTotDisAmt();
    
      
  }
  
 function addTotDisAmt()
 {
 
 	var totalDisAmt = calAllTariffNetCost("disAmt");
 	document.forms[0].totDisAmt.value =totalDisAmt;
 	document.getElementById("totDisAmtDivId").innerHTML= totalDisAmt; 
 	
 	// document.forms[0].totDisAmt1.value =totalDisAmt;
 	
 }
 
 /*
 function FinalAdjust()
 {
    
    if(document.getElementById("strPrevDisType").value == "2")
    {
      alert("Please Select Same Discount Type!!!");
      document.getElementById("discountType").value = "2";
    }
    else
    {
      alert("Please Select Same Discount Type!!!");
      document.getElementById("discountType").value = "1";    
    }
 
 }
 */
 
 function clickCmb(index)
 {
 
    
// alert("prev discount="+document.getElementById('strPrevDisType'+index).value);
 
  document.forms[0].trfAdd_Dis[index].disabled=false;
  document.forms[0].trfAdd_Dis[index].checked=false;
                    
  if(document.getElementById("trfAdd_Dis"+index).value =="1")
  {
     document.getElementById("trfAdd_Dis"+index).value ="0";
     document.getElementById("strTempCkhBox"+index).value  = "0";
  }
  if(document.getElementById("strPrevDisType"+index).value == "0")
  {
     var current = parseFloat(document.getElementById("opd_discount"+index).value);
     document.getElementById("strDiscountUnit"+index).value = current;
     var cmb=document.forms[0].opd_discountType[index];
     var objDisAdd=document.getElementsByName("trfAdd_Dis");
     var o=document.getElementsByName("trfAdd_Dis");
     var trfAmt = parseFloat(document.getElementById("Cost"+index).value);
	 var qty = parseFloat(document.getElementById("qty"+index).value);
	 var qty_base_value = parseFloat(document.getElementById("qtyBaseValue"+index).value);
	 
	 var last = parseFloat(document.getElementById("hideLstDis"+index).value);
	 var dis_unit =  parseFloat(current);
	 
	 var dis_type = parseFloat(document.getElementById("opd_discountType"+index).value);
	 document.getElementById("strDiscountType"+index).value = dis_type;
	 
	 var rate_base_value = parseFloat(document.getElementById("strRateBaseValue"+index).value); 
	 var LastDis  = parseFloat(document.getElementById("hideLstDis"+index).value);
     var EnterDis = parseFloat(document.getElementById("opd_discount"+index).value);
     var TotalDis = EnterDis;
    if(document.getElementById("opd_discountType"+index).value =="2")
    {
     if(TotalDis >= "100")
     {
        var objDisAmt=document.getElementsByName("disAmt");
	    var s=0;
	    for(var j=0;j<objDisAmt.length;j++)
	    {
		if(objDisAmt[j].value=="")
		  s=parseFloat(s);
		else
	      s=parseFloat(s)+parseFloat(objDisAmt[j].value);
	    }
	    
	    var sumofDis = roundValue(s,1);
        var FinalAmt = sumofDis - parseFloat(document.getElementsByName("disAmt")[index].value);
        
        document.getElementById("opd_discount"+index).value = "0";
        document.getElementById("strDiscountUnit"+index).value = "0";
        document.getElementsByName("disAmt")[index].value = "0";
        document.getElementById("disAmtDivId"+index).innerHTML = "0"; 
          
        //alert("After Final Calculation::::"+FinalAmt);
        
        document.forms[0].totDisAmt.value  =roundValue(FinalAmt,1);
 	   // document.forms[0].totDisAmt1.value =roundValue(FinalAmt,1);
 	   document.getElementById("totDisAmtDivId").innerHTML=roundValue(FinalAmt,1);
        
       /* 
        alert("Inside Method!!!!!! 33");  
        document.getElementById("opd_discount"+index).value = "0";
        document.getElementById("strDiscountUnit"+index).value = "0";
        document.getElementsByName("disAmt")[index].value = "0";
       */ 
        alert("Discount Never Cross 100% Limit!!!");
      }
      else
      {
          var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	      document.getElementsByName("disAmt")[index].value = disAmt;
	      document.getElementById("disAmtDivId"+index).innerHTML = disAmt; 
          addTotDisAmt();
      }
    } 
    if(document.getElementById("opd_discountType"+index).value =="1")
    {
      var trfAmt = parseFloat(document.getElementById("Cost"+index).value);
      if(TotalDis >= trfAmt)
      {
         
          document.getElementById("trfAdd_Dis"+index).checked =false;
         
          document.getElementById("trfAdd_Dis"+index).value ="0";
          document.getElementById("strTempCkhBox"+index).value  = "0";
          document.getElementById("opd_discount"+index).value = "0";
          document.getElementById("strDiscountUnit"+index).value = "0";
          document.getElementsByName("disAmt")[index].value = "0";
          document.getElementById("disAmtDivId"+index).innerHTML = "0";
          document.forms[0].totDisAmt.value = "0";
          document.getElementById("totDisAmtDivId").innerHTML="0";
          alert("Discount Never greater than Rate<"+trfAmt+">in case of Fixed!!!");
      }
      else
      {
          var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	      document.getElementsByName("disAmt")[index].value = disAmt;
	      document.getElementById("disAmtDivId"+index).innerHTML = disAmt; 
          addTotDisAmt();
      }
     } //
  }
  else
  {
  //alert("document.getElementById('LstDisService'+index).value"+document.getElementById('LstDisService'+index).value);
  //alert("document.getElementById('strPrevDisType'+index).value"+document.getElementById('strPrevDisType'+index).value);
 // alert("document.getElementById('opd_discountType'+index).value"+document.getElementById('opd_discountType'+index).value);
   if(document.getElementById("LstDisService"+index).value != '0' && (document.getElementById("strPrevDisType"+index).value == document.getElementById("opd_discountType"+index).value))
   {
   
     var current = parseFloat(document.getElementById("opd_discount"+index).value);
      document.getElementById("strDiscountUnit"+index).value = current;
      
     var cmb=document.forms[0].opd_discountType[index];
     var objDisAdd=document.getElementsByName("trfAdd_Dis");
     var o=document.getElementsByName("trfAdd_Dis");
     var trfAmt = parseFloat(document.getElementById("Cost"+index).value);
	 var qty = parseFloat(document.getElementById("qty"+index).value);
	 var qty_base_value = parseFloat(document.getElementById("qtyBaseValue"+index).value);
	 
	 
	 var last = parseFloat(document.getElementById("hideLstDis"+index).value);
	 var dis_unit =  parseFloat(current);
	 var dis_type = parseFloat(document.getElementById("opd_discountType"+index).value);
	 document.getElementById("strDiscountType"+index).value = dis_type;
	 var rate_base_value = parseFloat(document.getElementById("strRateBaseValue"+index).value); 
	 var LastDis  = parseFloat(document.getElementById("hideLstDis"+index).value);
     var EnterDis = parseFloat(document.getElementById("opd_discount"+index).value);
     var TotalDis = EnterDis;
    if(document.getElementById("opd_discountType"+index).value =="2")
    {
     if(TotalDis >= "100")
     {
        var objDisAmt=document.getElementsByName("disAmt");
	    var s=0;
	    for(var j=0;j<objDisAmt.length;j++)
	    {
		if(objDisAmt[j].value=="")
		  s=parseFloat(s);
		else
	      s=parseFloat(s)+parseFloat(objDisAmt[j].value);
	    }
	    
	    var sumofDis = roundValue(s,1);
	    //alert("Inside Method 4!!!!!!!!!! TotalDis-->>>"+sumofDis);
        //alert("Last Disc Amt::::::"+document.getElementsByName("disAmt")[index].value);      
        var FinalAmt = sumofDis - parseFloat(document.getElementsByName("disAmt")[index].value);
        
        
        document.getElementById("opd_discount"+index).value = "0";
        document.getElementById("strDiscountUnit"+index).value = "0";
        document.getElementsByName("disAmt")[index].value = "0";
        document.getElementById("disAmtDivId"+index).innerHTML ="0";
          
        //alert("After Final Calculation::::"+FinalAmt);
        
        document.forms[0].totDisAmt.value =FinalAmt;
 	  //  document.forms[0].totDisAmt1.value =FinalAmt;
 	  document.getElementById("totDisAmtDivId").innerHTML=FinalAmt; 
        
        
        alert("Discount Never Cross 100% Limit!!!");
      }
      else
      {
          var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	      document.getElementsByName("disAmt")[index].value = disAmt;
	      document.getElementById("disAmtDivId"+index).innerHTML = disAmt; 
          addTotDisAmt();
      }
    } 
    if(document.getElementById("opd_discountType"+index).value =="1")
    {
      var trfAmt = parseFloat(document.getElementById("Cost"+index).value);
      if(TotalDis >= trfAmt)
      {
         
          document.getElementById("trfAdd_Dis"+index).checked =false;
         
          document.getElementById("trfAdd_Dis"+index).value ="0";
          document.getElementById("opd_discount"+index).value = "0";
           document.getElementById("strDiscountUnit"+index).value = "0";
          document.getElementsByName("disAmt")[index].value = "0";
          document.getElementById("disAmtDivId"+index).innerHTML ="0";
          document.forms[0].totDisAmt.value = "0";
          document.getElementById("totDisAmtDivId").innerHTML="0"; 
          alert("Discount Never greater than Rate<"+trfAmt+">in case of Fixed!!!");
      }
      else
      {
          var disAmt =  calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value)
	      document.getElementsByName("disAmt")[index].value = disAmt;
	      document.getElementById("disAmtDivId"+index).innerHTML = disAmt; 
          addTotDisAmt();
      }
     } //
   }
   else
   {
    alert("Please Select Same Discount Type!!!");
    if(document.getElementById("strPrevDisType"+index).value == "1")
    {
      document.getElementById("opd_discountType"+index).value = "1";
    }
    else
    {
      document.getElementById("opd_discountType"+index).value = "2";    
    }
   }
  } 
  // alert("Check Box::::"+document.forms[0].trfAdd_Dis[index].value);
  //alert("Check Box Checked::::"+document.forms[0].trfAdd_Dis[index].checked);
  //alert("Check Box::Disable ::"+document.forms[0].trfAdd_Dis[index].disabled);
   
}
function checkClick(i)
{
//	alert("final index------>"+i);
	var a = document.getElementsByName("trfAdd_Dis");
	if(a[i].checked)
	{
//		alert("set 1--->")
		a[i].value="1";
		
	}else{
		a[i].value="0";
	}
	
}

 function testFunc(){
 
 	alert("test func");
 }
 

