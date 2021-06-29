function view1(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="none";
	
	document.getElementById(obj2).style.display="block";
	
	document.getElementById(obj3).style.display="block";
}
function view2(obj1,obj2,obj3)
{
	document.getElementById(obj1).style.display="block";
	
	document.getElementById(obj2).style.display="none";
	
	document.getElementById(obj3).style.display="none";
}

function cancelPage()
{
	document.forms[0].hmode.value="CANCEL";
	document.forms[0].submit();
}

function clearMsg()
{
	document.forms[0].hmode.value="unspecified";
	document.forms[0].submit();
	
}

function validate1()
{
	      var hisValidator = new HISValidator("issueDtlModificationTransFB");  
	      
		  
          hisValidator.addValidation("strStoreName","dontselect=0","Please Select DDW Name");
          hisValidator.addValidation("strIndentingStoreID","dontselect=0","Please Select Indenting Store");
		  hisValidator.addValidation("strVoucherNo","req", "Voucher Number is a Mandatory Field" );	
		  hisValidator.addValidation("strVoucherDate","req", "Voucher Date is a Mandatory Field" );		  
          hisValidator.addValidation("strIndentNumber","req", "Indent Number is a Mandatory Field" );
          if(document.forms[0].strIndentNumber.value=='0')
          {
          	alert("Indent Number Should not be 0 !!");
          	return false;
          }
          hisValidator.addValidation("strVoucherIndentDate","req", "Indent Date is a Mandatory Field" ); 
          hisValidator.addValidation("strVoucherDate", "dtltet="+document.forms[0].strCurrentDate.value, "Voucher Date Must be Less then Or Equal to Current Date." ); 
          
           hisValidator.addValidation("strVoucherIndentDate",  "dtltet="+document.forms[0].strCurrentDate.value, "Indent Date Must be Less than Or equal to Current Date." );
          hisValidator.addValidation("strVoucherIndentDate",  "dtltet="+document.forms[0].strVoucherDate.value, "Indent Date Must be Less than Or equal to Voucher Date." );
          
          hisValidator.addValidation("strRemarks", "req", "Remarks is a mandatory field");
	hisValidator.addValidation("strRemarks","maxlen=100", "Remarks should have less than or equal to 100 Characters" );
                              
 		  var retVal = hisValidator.validate(); 

          if(retVal)
          {
                   var drugName ;
			       var batchName ;   
			       var count; 
                   var drugID    = document.getElementsByName("strDrugId");         
	               var batchNo   = document.getElementsByName("strBatchNo")[0];    
	               var issueQty  = document.getElementsByName("strIssueQty")[0].value;  	 	
      	 	     
      	 	     if(issueQty=='')
      	 	     {
      	 	     	alert("Issue Qty is a mandatory field. ");
      	 	     	 document.getElementsByName("strIssueQty")[0].focus();
      	 	     	return false
      	 	     }
      	 	     
      	 	     if(issueQty>parseInt(document.getElementsByName("strBatchNo")[0].value.split("^")[2]))
      	 	     {
      	 	     	alert("Issue Qty="+issueQty+" cannot be Greater than Current Avl Qty ="+document.getElementsByName("strBatchNo")[0].value.split("^")[2]);
      	 	     	 document.getElementsByName("strIssueQty")[0].focus();
      	 	     	return false
      	 	     }
      	 	     
      	 	     count=0;
      	 	     
      	 	     var oldCost;	
      	 	     len = document.getElementsByName("strDrugDtls").length; 
      	 	     for(var k=0; k<len;k++ )
      	 	     {
      	 	     	if(document.getElementsByName("strDrugDtls")[k].checked==true)
      	 	     	{
      	 	     		 oldCost	=	parseFloat(document.getElementsByName("strOldCost")[k].value);		
      	 	     	}     	 	     	
      	 	     }
      	 	     
      	 	           	 	     
      	 	     var newCost = parseFloat(document.forms[0].strCost.value);
      	 	     
      	 	    // alert("oldCost"+oldCost);
      	 	     // alert("newCost"+newCost);
      	 	     
      	 	     if( (  newCost- oldCost )> parseFloat(document.forms[0].strCurrentBudgetAvl.value))
      	 	     {
      	 	     	alert("(New Cost="+newCost+" - Old Cost="+oldCost+") should be Less than equal to Current Budget Available");
      	 	     	
      	 	     	return false
      	 	     }
      	 	     
      	 	 
      	 	     
	      	 	     if(parseInt(issueQty)==0)
	      	 	     {
	      	 	     	if(confirm("This Item Will be deleted !!!"))
	      	 	     	{
	      	 	     		if(confirm("Are you sure you want to Save !!!"))
	      	 	     		{
	      	 	     			document.forms[0].strStoreName.disabled = false;
								  document.forms[0].strIndentingStoreID.disabled = false;
								  document.forms[0].strVoucherNo.disabled = false;
								  document.forms[0].strIndentNumber.disabled = false;
	      	 	     			
	      	 	     			document.forms[0].hmode.value = "SAVE";
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
	      	 	     	if(confirm("Are you sure you want to Save !!!"))
	      	 	     		{
	      	 	     			  document.forms[0].strStoreName.disabled = false;
								  document.forms[0].strIndentingStoreID.disabled = false;
								  document.forms[0].strVoucherNo.disabled = false;
								  document.forms[0].strIndentNumber.disabled = false;
	      	 	     			
	      	 	     			document.forms[0].hmode.value = "SAVE";
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



function checkValCombo(obj)
{
		var recievedByName=document.forms[0].strVoucherNo[document.forms[0].strVoucherNo.selectedIndex].text;
		if(document.forms[0].strVoucherNo[document.forms[0].strVoucherNo.selectedIndex].text=='New Voucher Number')
		{
			
			document.getElementById("labelId").className="LABEL";
			document.getElementById("labelNameId").innerHTML="";
			document.getElementById("nameOtherFld").style.display="block";
			if(document.forms[0].strNewVoucherNumber.readOnly)
				document.forms[0].strNewVoucherNumber.readOnly=false;
			document.forms[0].strNewVoucherNumber.value="";
			document.getElementById("labelNameId").innerHTML="<font color='red'>*</font>New Voucher No:";
			document.forms[0].strNewVoucherNumber.focus();
			
		
		}
		else 
		if(document.forms[0].strVoucherNo.value!="0" && document.forms[0].strVoucherNo.value!="1")
		{
			
			document.getElementById("labelId").className="LABEL";			
			document.getElementById("labelNameId").innerHTML="";
			document.getElementById("nameOtherFld").style.display="block";
			document.getElementById("labelNameId").innerHTML="New Voucher No:";
			document.forms[0].strNewVoucherNumber.value=recievedByName;
			if(!document.forms[0].strNewVoucherNumber.readOnly)
				document.forms[0].strNewVoucherNumber.readOnly=true;
			document.getElementsByName("strNewVoucherNumber")[0].focus();			
			
			
		}else{
			document.getElementById("labelId").className="CONTROL";
			document.getElementById("nameOtherFld").style.display="none";
			document.getElementById("labelNameId").innerHTML="";
			
		}
		
		
}

function getIndentStoreCombo() 
{

	if(document.forms[0].strStoreName.value=="0")
    {
   		alert("Please select DDW Name");
   		var objVal1 = document.getElementById("IndentingStore");
   	 	objVal1.innerHTML = "<select name = 'strIndentingStoreID' class='comboNormal'><option value='0'>Select Value</option></select>";
   	 	
   		document.forms[0].strStoreName.focus();
   		return false;
    }
	else
	{		
		var objValue = document.forms[0].strStoreName;
		var temp1 = objValue.options[objValue.selectedIndex].value;
		var mode = "IndentStoreCombo";
		var url = "IssueDtlModificationTransCNT.cnt?hmode=" + mode + "&modName="+ temp1 + "^0";
		ajaxFunction(url, "1");
		
	}

}

function getVoucherCombo() 
{

	if (document.getElementsByName("strStoreName")[0].value == "0") 
	{
		alert("Please Select DDW Name!!");
	} 
	else 
	if (document.getElementsByName("strIndentingStoreID")[0].value == "0") 
	{
		alert("Please Select Indenting Store From Combo!!");
	} 
	else 
	{
		var temp = document.forms[0].strStoreName.value;
		var mode = "GETVOUCHERCOMBO";
		var url = "IssueDtlModificationTransCNT.cnt?hmode=" + mode + "&storeId="
				+ temp + "&itemCatNo="+10+"&fromDate="
				+ document.forms[0].strCurrentDate.value + "&ToDate="
				+ document.forms[0].strCurrentDate.value+  "&IndentingStore="
				+ document.getElementsByName("strIndentingStoreID")[0].value;
		
		
		ajaxFunction(url, "2");
	}

}

function getVoucherDetail()
{
   if(document.forms[0].strStoreName[document.forms[0].strStoreName.selectedIndex].value!='0' && document.forms[0].strIndentingStoreID[document.forms[0].strIndentingStoreID.selectedIndex].value!='0')
   {

    if(document.forms[0].strVoucherNo.value!='0')
    {
    	document.forms[0].strStoreName.disabled = true;
	    document.forms[0].strIndentingStoreID.disabled = true;
	    document.forms[0].strVoucherNo.disabled = true;
	    
	    document.getElementById("showVoucherDiv1").style.display = "none";
	    document.getElementById("id1").innerHTML="";  
	    document.getElementById("voucherDrugDivId").innerHTML="";  	
	    
	    
    	document.forms[0].strIssueValue.value = document.forms[0].strVoucherNo[document.forms[0].strVoucherNo.selectedIndex].value;
    	
		var mode = "VoucherDetails";
		var url = "IssueDtlModificationTransCNT.cnt?hmode=" + mode + "&voucherNo="+ document.forms[0].strVoucherNo.value+"&strStoreId="+document.forms[0].strStoreName.value;
		ajaxFunction(url, "3");
    }
  }
  else
  {
     if(document.forms[0].strStoreName[document.forms[0].strStoreName.selectedIndex].value=='0')
     {
       alert("Please Select DDW Name!!!");
       return false;
     }
     else
     {
        alert("Please Select Indenting Store Name!!!");
       return false;
     }
  
  }	
} 
function getAjaxResponse(res, mode) 
{
	var err = document.getElementById("errMsg");
	var temp = res.split("####");
	if (temp[0] == "ERROR") {
		err.innerHTML = temp[1];
		return;
	}
	if (mode == "1") 
	{
		var objVal1 = document.getElementById("IndentingStore");
		objVal1.innerHTML = "<select name = 'strIndentingStoreID' onchange='getVoucherCombo();' class='comboMax'>"
				+ res + "</select>";
	}
	if (mode == "2") 
	{		
		var objVal = document.getElementById("IssueNumberComboId");
		objVal.innerHTML = "<select  name = 'strVoucherNo'  id='strVoucherNo' class='combo1Max'>"+ res.split("@@@@")[0]+" </select>";
		
			var objVal1 = document.getElementById("strReceivedByDivId");
		objVal1.innerHTML = "<select  name = 'strReceivedById'  id='strReceivedByDivId' class='combo1Max'>"+ res.split("@@@@")[1]+"</select>";
				
				
				
	}

	if (mode == "3") 
	{
		/*
		 * temp[0] 
		 * 
		HSTNUM_ISSUE_NO || ''^'' || 
		TO_CHAR(HSTDT_ISSUE_DATE,''DD-Mon-YYYY'') || ''^'' || 
        NVL(HSTNUM_INDENT_NO,'' '') || ''^'' || 
        TO_CHAR(NVL(HSTDT_INDENT_DATE,SYSDATE),''DD-Mon-YYYY'') || ''^'' || 
        NVL(HSTNUM_BUDGET_AVAILABLE,0) || ''^'' || 
        (SELECT NVL(HSTNUM_BUDGET_ALLOT,0) - NVL(HSTNUM_BUDGET_USED,0) || '^' || 
        NVL(HSTSTR_RECIEVE_BY,'-') 
       */  
		
	  //alert(res);	
	   var temp = res.split("@@@@"); 	
	   if(temp[0].split("^")[1]!='')
	   {
	     document.getElementById("strVoucherDate").value=temp[0].split("^")[1];
	   } 
	   else
	   {
	   	 document.getElementById("strVoucherDate").value="";
	   }
	   if(temp[0].split("^")[2]!='')
	   { 
	     document.getElementById("strIndentNumber").value=temp[0].split("^")[2];
	   }
	   else
	   {
	   	 document.getElementById("strIndentNumber").value="";
	   }
	   if(temp[0].split("^")[3]!='')
	   {
	     document.getElementById("strVoucherIndentDate").value=temp[0].split("^")[3];
	   }
	   else
	   {
	   	 document.getElementById("strVoucherIndentDate").value="";
	   }	   
	   if(temp[0].split("^")[4]!='')
	   {
	   	document.getElementsByName("strPrevBudgetAvl")[0].value=temp[0].split("^")[4];
	     document.getElementById("strPrevBudgetAvlDivId").innerHTML=temp[0].split("^")[4];
	   }
	   else
	   {
	   	 document.getElementById("strPrevBudgetAvlDivId").innerHTML="";
	   }
	   
	   if(temp[0].split("^")[5]!='')
	   {
	   	document.getElementsByName("strCurrentBudgetAvl")[0].value=temp[0].split("^")[5];
	     document.getElementById("strCurrentBudgetAvlDivId").innerHTML=temp[0].split("^")[5];
	   }
	   else
	   {
	   	 document.getElementById("strCurrentBudgetAvlDivId").innerHTML="";
	   }
	   
	   if(temp[0].split("^")[6]!='')
	   {
	   	//alert(temp[0].split("^")[6]);
	   	document.getElementsByName("strReceivedById")[0].value	=	temp[0].split("^")[6];
	    
	   }
	   else
	   {
	   	 	document.forms[0].strReceivedById.value='0';
	   }
	   
	   
	    document.getElementById("strPrevBudgetAvlDivId").style.display = "block";
	     document.getElementById("strCurrentBudgetAvlDivId").style.display = "block";
	   
	   document.getElementById("showVoucherDiv1").style.display = "block";
	  
	   document.getElementById("voucherDrugDivId").innerHTML=temp[1];
	   
	   document.getElementById("remarksAndReceivedByDivId").style.display = '';
	
	   
	   getModificationDtls('0');
	   
	}	
	
	if (mode == "13") 
	{
	   temp = res.split("$$");
	  
		  if(temp[2]=='101')
		  {
		  
			document.getElementById("drugDetailsDivId").innerHTML=temp[0];
			document.getElementById("drugDetailsDivId").style.display='';
			
			splitBatchValues(temp[1]);
		  }	
		  else
		  {
		  	document.getElementById("errMsg").innerHTML=temp[2];
		  }
		
	}
	
}
  


function totalCost()
{	   
       	 
       	    var costObj = document.getElementsByName("strCost");
       	        	           	           	
			var totalCost = parseFloat("0.00");
			
			//alert("Length:::"+costObj.length);
		   	if (costObj.length > 0) 
			{
		       
				for ( var i = 0; i <costObj.length-1; i++)
				{		
					//alert("Value:::"+costObj[i].value);
					if(costObj[i].value.length>0)
					{
					  totalCost = totalCost + parseFloat(costObj[i].value);	
					}
					else
					{
						totalCost = totalCost;
					}
						
		 		}
		
			}

//alert("totalQty"+totalQty);
//alert("totalCost"+totalCost);

	    totalCost = roundValue(totalCost, 2);
	    //alert("totalCost"+totalCost);
	    
	    //document.getElementsByName("strTotalTransferredQty")[0].value = totalQty;
	  //  document.getElementsByName("strTotalCost")[0].value = totalCost;
	    
	    //document.getElementsByName("strTotalCostTemp")[0].value= totalCost;
	    document.getElementById("totalIssuedCostDivId").innerHTML=totalCost;
	   //document.getElementsByName("strTotalCost")[0].value= totalCost;
	    
	    
//	    document.getElementById("totalTransferredCostDivId").innerHTML.fontcolor('red');
//	    document.getElementById("totalTransferredQtyDivId").innerHTML.fontcolor('red');
	    
	    
        //document.forms[0].strNewDemandFinalApproxAmt.value=total;
        //alert("strNewDemandFinalApproxAmt :"+document.forms[0].strNewDemandFinalApproxAmt.value);
       
}	 

/*For Hlp*/
function checkAvailQtyTwo(index) 
{      
		
/*						
	NVL(HSTSTR_BATCH_NO,'--') || '^' || 
	RATE_BASE || '^' || 
    INHAND_QTY || '^' || 
    UNIT_NAME || '^' || 
    HSTNUM_STOCK_STATUS_CODE AS DISP_ID,
    NVL(HSTSTR_BATCH_NO,'--') AS DISP_NAME
*/
	
	     var rateObj = document.getElementsByName("strBatchNo")[0].value.split("^")[1];
	     var qtyObj  = document.getElementsByName("strIssueQty")[0].value;
	           					
			var qty    = parseFloat(qtyObj);	
			var rate   = parseFloat(rateObj);
		
		//alert("index"+index);
	    //alert("qtyObj"+qtyObj);
	    //alert("rateObj"+rateObj);
		
			var total = parseFloat("0.00");
				
					if(isNaN(rate) || rate=="") rate = "0";
					if(isNaN(qty)  || qty=="") qty = "0";
				    if(qty=='0')		
					{
					  
					  total = parseFloat(qty * rate);
					}
					else
					{
					  total = parseFloat(qty * rate);
					} 			
					
					total = roundValue(total, 2)
		
		document.getElementById("strCostDivId").value=total;
		document.getElementsByName("strCost")[0].value=total;
		document.getElementById("strCostDivId").innerHTML=total;
		
	return true;
}

/*For Multi Row*/
function checkAvail(index)
{
	
}


function splitBatchValues(index)
{
//	alert(document.getElementsByName("strBatchNo")[0].value);
/*						
	NVL(HSTSTR_BATCH_NO,'--') || '^' || 
	RATE_BASE || '^' || 
    INHAND_QTY || '^' || 
    UNIT_NAME || '^' || 
    HSTNUM_STOCK_STATUS_CODE AS DISP_ID,
    NVL(HSTSTR_BATCH_NO,'--') AS DISP_NAME
*/
	
	var rate 	= document.getElementsByName("strBatchNo")[0].value.split("^")[1];
	var avlQty 	= document.getElementsByName("strBatchNo")[0].value.split("^")[2];
	var unit 	= document.getElementsByName("strBatchNo")[0].value.split("^")[3];
	
	
	document.getElementById("strAvlQtyDivId").innerHTML=avlQty+" "+unit;
	document.getElementById("strAvlQtyDivId").value=avlQty;
	document.getElementsByName("strAvlQty")[index].value=avlQty;
	
	checkAvailQtyTwo(index);
	
}



function getModificationDtls(index)
{
//	alert("index"+index);
	
//	alert("strDrugDtls"+document.getElementById("strDrugDtls"+index).value);
//	alert("strStoreId"+document.getElementsByName("strStoreName")[0].value);
	
		/*
						 * 1. HSTNUM_ITEMBRAND_ID, 
						 * 2. HSTSTR_BATCH_SL_NO,
						 * 3. HSTNUM_STOCK_STATUS_CODE,
						 * 4. ITEM_NAME,
						 * 5. ISSUE_QTY,
						 * 6. ITEM_COST,
						 * 7. CURRENT_AVL_QTY,
						 * 8. UNIT_NAME
						 */
						 
		var mode = "getModificationDtls";
		var url = "IssueDtlModificationTransCNT.cnt?hmode=" + mode 
					+ "&strDrugDtls="+ document.getElementsByName("strDrugDtls")[index].value
				    + "&strStoreId="+document.getElementsByName("strStoreName")[0].value
					+"&index="+index;
					
					 url = (url.replace(/%/g, '@@@@'));
//alert("url"+url);					
		ajaxFunction(url, "13");				 
						 
}


function GetIndex(index,endVal)
{
   // alert("index--.>"+index+"<-Total Recrd-->"+endVal);
          for(var i = 1; i <= endVal ; i++)
		  {
		  //  alert(i+"<---i-->"+document.getElementById("DivId"+i).style.display);
		    document.getElementById("DivId"+i).style.display="none";
		  }
		 // alert("before-->>"+document.getElementById("DivId"+index).style.display);
		  document.getElementById("DivId"+index).style.display="block";
		 // alert("before-->>"+index+"<div>"+document.getElementById("DivId"+index).style.display);
			 
		
	 if(index == document.forms[0].TotalLayer.value)
	 {
	      document.getElementById("totalDivId").style.display="block";
	  
	 }
	 else
	 {
	     document.getElementById("totalDivId").style.display="none";
	 }	 
			 
}
 