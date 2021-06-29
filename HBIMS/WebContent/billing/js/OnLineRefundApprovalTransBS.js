



function showIndentData12()
{
    document.getElementById("indentMinusID112").style.display="block";
	document.getElementById("indentPlusID112").style.display="none";
	document.getElementById("showNewPriceBidDiv12").style.display="block";
}

function hideIndentData12()
{
	document.getElementById("indentMinusID112").style.display="none";
	document.getElementById("indentPlusID112").style.display="block";
	document.getElementById("showNewPriceBidDiv12").style.display="none";
}


function save(){
	
	  var hisValidator = new HISValidator("onLineRefundApprovalTransBean");  
 	 
 	  hisValidator.addValidation("strRefundedByComboVal", "dontselect=0","Please Select a Refund By Value");
 	   hisValidator.addValidation("strRemarks", "req","Remarks is a Mandatory Field");
 	  
 	  
 	  var retVal = hisValidator.validate(); 
 	 
 	  hisValidator.clearAllValidations;
 	      
    	if(retVal){ 
 	    
 	    	var reqObj = document.getElementsByName("radioButton");
 	    	var cnt=0;
 	    
 	    	if(reqObj.length <= 0){
 	    	
 	    		alert("No Refund Request Available");
 	    		return false;
 	    	}
 	    	else
 	    	{
 	   // alert(reqObj.length);
 	    
 	    		
				
				for(var i=0; i<reqObj.length; i++) 
				{
				  	if(reqObj[i].checked)
				  	{
				  		var valList = reqObj[i].value;
						var tempVal = valList.split('^');
						
						
	 			if(tempVal[9]>0)
	 			{
	 			    alert("This Bill has already been Cancelled. The Refund Request will be Cancelled Automatically.")
	 			    var appFlg  = document.getElementsByName("tariffId");
	 			      
	 			      
	 			       
	 			       if(appFlg.length > 0){
	 			       	
	 			          for(var x=0;x<appFlg.length;x++){
	 			          	               
	 			             if(appFlg[x].checked){
	 			             	
	 			            	appFlg[x].checked=false;
	 			            	//alert("hi");
	 			             }
	 			              
	 			          }  
	 			}
	 			       cnt=1;
				  	}
				}
				}
    	}
 	    
		 	  var appFlg  = document.getElementsByName("tariffId");
		      
		      var count = parseInt("0");
		       
		       if(appFlg.length > 0){
		       	
		          for(var x=0;x<appFlg.length;x++){
		          	               
		             if(appFlg[x].checked){
		             	
		             	count = count +1;
		             }
		              
		          }    
		          if(cnt==0)
		          {
			      if(count == 0){
			      	
			      	var conf = confirm("No Tariff has been Selected, The Refund Request will be Cancelled Automatically, Are you sure");
			      	
			      	if(!conf){
			      		
			      		return false;	
			      	      		
			      	}
			      }
			      }    
		       		        
			}
			var chkTarrif=document.getElementsByName("tariffId");
			for(var index=0;index<chkTarrif.length;index++)
			{			
				var qty =document.getElementById("strApprovedQty"+index).value;
				var reqqty = document.getElementById("strRequestQty"+index).value;     
		 
				if(parseInt(qty) > parseInt(reqqty)){
				alert("Approved Quantity cannot be greater than Request Quantity");
				document.getElementById("strApprovedQty"+index).value = reqqty;
				Check(chkTarrif,index);
				return false; 	
			}
			}
			
			document.forms[0].hmode.value = "SAVE";
			document.forms[0].submit();
			

		}else{
			 
			return false;
		}

}

function getEnquiryDetails(Obj1,Obj2)
{
 		 var mode = "GO";   
		 var url="OnLineRefundApprovalBSCNT.cnt?hmode="+mode+"&strSearchMode="+Obj1.value+"&strSearchValue="+Obj2.value; 
		 ajaxFunction(url,"1");
 		  
}


/**
 * getRefundRequestDtls
 * @param {}  
 */
 function getRefundRequestDtls() {
 	
 	 var mode = "SEARCH";   
				 var url="OnLineRefundApprovalBSCNT.cnt?hmode="+mode+"&strSearchMode=&strSearchValue="; 
				 ajaxFunction(url,"1");
 	
 }

function getAjaxResponse(res,mode){
		
		   	
	 var temp1 = res.split("####");
	   if(temp1[0] == "ERROR"){
	   	 var err = document.getElementById("errMsg");
	         err.innerHTML = temp1[1];
	         return false;	
       }
     
	    if(mode == 1)
	    {
	    	
	    	document.getElementById("refundCRNODivId").innerHTML =  res;
	    	
	    	checkRadio();
	    	
	    }
	    
	    if(mode == 2)
	    {
	    	
	    	document.getElementById("tariffDivId").innerHTML =  res;
	    	document.getElementById("tariffDivId").style.display = "block";
	    	
	    	var reasonObj = document.getElementsByName("strTempRefundReason");
	    	
	    	if(reasonObj.length > 0){
	    	
	    		document.forms[0].strRemarks.value =reasonObj[0].value;
	    	
	    	}
	    	
	    	
	    	
	    }
	    
	      
}

//------------If Enter Key Press--------------//
function goFuncOnEnter(e)
{
   if(e.keyCode == 13)
   {
	 goFunc(document.forms[0].strSearchMode);
	}
	else
	{
	 return false;
	}
}
function goFunc()                
{
             	var obj1 = document.forms[0].strSearchMode;
	           	var obj2 = document.forms[0].strSearchValue;
	           	if(obj2.value!='')
	           	{
	           	 var mode = "SEARCH";   
				 var url="OnLineRefundApprovalBSCNT.cnt?hmode="+mode+"&strSearchMode="+obj1.value+"&strSearchValue="+obj2.value; 
				 ajaxFunction(url,"1");
	           	}
	           	else
	           	{
	           		alert("Please Enter Search Value !!!!!");
	           	} 
	
}


function Check(obj,index)
{		
	if(document.getElementById("tariffId"+index).checked)
	{
		var refAmt;
		var hidObj    = document.getElementById("strHiddenVal"+index);
		var temp      = hidObj.value.split("^");
		var rate      = temp[1];
		var baseVal   = temp[2];
		var disUnit   = temp[3];
		var dis       = temp[4];
		var serTax    = temp[5];
		var penalty   = temp[6];
		var unit      = "1";
		
				
		  document.getElementById("strApprovedQty"+index).disabled= false;
		
		  document.getElementById("strHiddenVal"+index).disabled= false;
		   // document.getElementById("tariffId"+index).disabled= false;
		     document.getElementById("strRequestQty"+index).disabled= false;
		      document.getElementById("strRefundCost"+index).disabled= false;
		
		var qty = document.getElementById("strApprovedQty"+index).value;
		var reqqty = document.getElementById("strRequestQty"+index).value;     
		
		 
		if(parseInt(qty) > parseInt(reqqty)){
		
			alert("Approved Quantity cannot be greater than Request Quantity");
			document.getElementById("strApprovedQty"+index).value = reqqty;
			Check(obj,index);
			return false; 	
		}
		
		
		refAmt = calTrfNetAmount(rate,baseVal,dis,disUnit,qty,unit,serTax,penalty,1);
		
			document.getElementById("costDivId"+index).innerHTML =  refAmt.oNetTrfAmt;
			document.getElementById("strRefundCost"+index).value =  refAmt.oNetTrfAmt;
		
		
	}
	else
	{
		
		  //document.getElementById("strApprovedQty"+index).value = 0;
		  document.getElementById("strApprovedQty"+index).disabled= true;
		  
		   
		  document.getElementById("strHiddenVal"+index).disabled= true;
		   // document.getElementById("tariffId"+index).disabled= true;
		     document.getElementById("strRequestQty"+index).disabled= true;
		      document.getElementById("strRefundCost"+index).disabled= true;
		  
		  
		  document.getElementById("costDivId"+index).innerHTML =  "0.00";
		  document.getElementById("strRefundCost"+index).value =  "0.00";         
		 
	}	
	  
		var netCostObj = document.getElementById("totalExpAmtDivId"); //div 	
	 	var netCost = calAllTariffNetCost("strRefundCost");	  
	 	netCostObj.innerHTML =  netCost + "<input name='strTotalExpAmt' id='strTotalExpAmt' type='hidden' class='txtFldMin' value='"+ netCost + "' disabled='disabled'>&nbsp;Rs";
	
}
 

function setValue(obj)
{
	
	document.getElementById("strTariffValue").value=obj.value;
	goFuncOne();
}
function goFuncOne()             
{   
           	if(document.getElementById("strTariffValue").value!='0')
           	{
	           	 var mode = "GO";   
				 var url="OnLineRefundApprovalBSCNT.cnt?hmode="+mode+"&strSearchMode="+document.getElementById("strTariffValue").value; 
				 ajaxFunction(url,"2");
           	}
           	else
           	{
           		alert("Please Select Request No to get tariff details!!!!!");
           		return false;
           	}	 
	
}
function checkRadio()
{
  	var radioObj = document.getElementsByName("radioButton");
  	radioObj[0].checked= true;
  	
	if(radioObj[0].checked)
	{
		
		document.getElementById("strTariffValue").value=radioObj[0].value;
		goFuncOne();
	}
}
function GetIndex(index,endVal)  // Pagenation  One
{
	
	
	    for (var page = 1; page <= endVal; page++)       // For Loop To Put Normal CSS in All No
        {
          document.getElementById('pg'+page).className = 'pg-normal';
        }
	    var oldPageAnchor = document.getElementById('pg'+index);   // Apply CSS
        oldPageAnchor.className = 'pg-selected';
        
          for(var i = 1; i <= endVal ; i++)
		  {
			  document.getElementById("DivId"+i).style.display="none";
		  }
		 
		  document.getElementById("DivId"+index).style.display="block";
		 
}

function GetIndex2(index,endVal)  // Pagenation  Two
{
	
	    for (var page = 1; page <= endVal; page++)       // For Loop To Put Normal CSS in All No
        {
          document.getElementById('mg'+page).className = 'pg-normal';      
        }
	     var PageAnchor = document.getElementById('mg'+index);
         PageAnchor.className = 'pg-selected';                    // Apply CSS
          for(var i = 1; i <= endVal ; i++)
		  {
			  document.getElementById("DivId2"+i).style.display="none";
		  }
		  document.getElementById("DivId2"+index).style.display="block";
			 
}


function printBill()                //  for CR No. field validation
{

		
		if(document.forms[0].strCrNo.value != "" ){
			
			var reqObj = document.getElementsByName("requestOption");	
	
		if(reqObj.length > 0){
		
				document.forms[0].hmode.value="PRINT";
	           	document.forms[0].submit();
		
		}else{
			
			alert("No Request Available");
			return false;
			
		}
			
		}

		
}
function clearData()
 {
        document.getElementById("errMsg").innerHTML = "";
        
        document.forms[0].strCrNo.value = "";
        
	 	document.forms[0].hmode.value = "APPROVED";
  	    document.forms[0].submit();
 }
	
	
 function cancel()
 {
        document.getElementById("errMsg").innerHTML = "";
	 	document.forms[0].hmode.value = "CANCEL";
  	    document.forms[0].submit();
 }
 
 
 function checkMsg()
 {
 	var err=document.getElementById("errMsg");
 	var nor=document.getElementById("normalMsg");
 	//var warn=document.getElementById("wrnID");
 	if (err.innerHTML != "") {
 		
 		err.innerHTML="<i class='fas fa-exclamation-triangle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
 		err.style.display = "";
 		
 	}
 	if (nor.innerHTML != "") {
 		nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
 		nor.style.display = "";
 	}
 	/*if (warn.innerHTML != "") {
 		warn.innerHTML="<i class='fas fa-bell'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+warn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
 		warn.style.display = "";
 	}*/	
}