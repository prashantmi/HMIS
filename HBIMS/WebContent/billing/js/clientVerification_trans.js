    function callMe()
    {
	  window.print();
    }
    function backPage()
	{
	  document.form1.hmode.value="CLIENTVERIFICATION";
	  document.form1.submit();
	  
	//  document.form1.action="first.jsp";
	//  document.form1.submit();
	}
    function CalTax()
    {
     var serviceTax = document.forms[0].strTax.value;
        alert("Applied Service Tax:::"+serviceTax);
     var total = document.forms[0].strTotRs.value;
        alert("Total Amt::"+total);
     var calServiceTaxAmt = 0; 
            if(serviceTax > 0 && total > 0)
			{
				calServiceTaxAmt = total * (parseFloat(serviceTax) / 100);
				alert("Servcie Tax is::::"+calServiceTaxAmt);
			} 			 	
			else
			{
			    total = 0;
			    calServiceTaxAmt = 0; 		 		 		 				 		 		 		
			} 	
			   var netAmt = parseFloat(total)+parseFloat(calServiceTaxAmt);
	           alert("Final Amt::::"+netAmt); 
	           document.forms[0].strTotBill.value  =   netAmt;  
    }
    function Calculation1(index)
	{

	  var QtyFinal = document.getElementsByName("strQtyFinal"); 
	  var Amt      = document.getElementsByName("strAmt");
	  var TotalCalAmt = document.getElementsByName("strTotalCalAmt");
	  var s  = 0;
	  var s1 = 0;
	  var s2 = 0;
	  var s3 = 0;
	 for(var j=0;j<QtyFinal.length;j++)
	 {
	   //alert("Qty Final:::"+QtyFinal[j].value+":::Amt Final:::"+Amt[j].value);
		if(QtyFinal[j].value=="" || Amt[j].value=="")
		  s2=parseFloat(s2)*parseFloat(s2);
		else
	      s2=parseFloat(QtyFinal[j].value)*parseFloat(Amt[j].value);
	      TotalCalAmt[j].value = s2;   
	     // alert("After Multi:::"+s2);
	      s2=0;
	}
    var totalAmt = parseFloat(s3);
	document.forms[0].strTax.value=totalAmt;
	}
	/**
	   This Method is used to Calculate Total Amount when 
	   "Minus" button of multirow is pressed.
	*/
	function CalAmt(index)
	{
	     var str3  = "strFinalMul"+index;
	    
	     var objSum=document.getElementsByName("strFinalMul");
	     var s=0;
	     for(var j=0;j<objSum.length;j++)
	     {
	      objSum[j].disabled = false;
		  if(objSum[j].value=="")
		   {
		    s=parseFloat(s);
		   } 
		  else
		   { 
		     s=parseFloat(s)+parseFloat(objSum[j].value);
	       } 
	       objSum[j].disabled = true;
	    }
	     document.forms[0].strTotRs.value     = s;
	     document.forms[0].strTotHiddRs.value = s;

	   
	}
    /*
    function CalAmt1(index)
    {   
          var str1  = "strQtyFinal"+index;
	      var str2  = "strAmt"+index;
	      var totalAmt = 0;
          var QtyFinal = document.getElementById(str1);  // Final Quantity
	      var Amt      = document.getElementById(str2);  // Final Amount
	     // alert("Qty Final:::"+QtyFinal.value);
	     // alert("Amt is:::"+Amt.value);
	      var  tempQtyFinal = QtyFinal.value;   
	      var  tempAmt      = Amt.value;
	      
	      //alert("Amt is:::"+isNaN(Amt));
	      
	      if(isNaN(Amt)&& isNaN(QtyFinal))  
	      {
	          //alert("Total Rs:::"+document.forms[0].strTotRs.value);
	          totalAmt = parseFloat(tempQtyFinal)*parseFloat(tempAmt);
	          document.forms[0].strTotRs.value     = parseFloat(document.forms[0].strTotRs.value)- parseFloat(totalAmt);
	          document.forms[0].strTotHiddRs.value = document.forms[0].strTotRs.value;
	      }    
	      else
	      {
	          var FinalAmt = parseFloat(QtyFinal.value)*parseFloat(Amt.value);
	          totalAmt = totalAmt + parseFloat(FinalAmt);
	          
	      }
	    //  document.forms[0].strTotRs.value = parseFloat(document.forms[0].strTotRs.value)- parseFloat(totalAmt);   
    }
    */
    /**
      This Method is called when we enter  to Calculate total Amount   
      
    */
    function Calculation(index)
	{
	 
	 var str1  = "strQtyFinal"+index;
	 var str2  = "strAmt"+index;
	 var str3  = "strFinalMul"+index;
	
	 var totalAmt = 0;
	 var FinalAmt = 0;
	 
	
	 var QtyFinal  = document.getElementById(str1);
	 var Amt       = document.getElementById(str2);
	 var FinalMul  = document.getElementById(str3);
	 var HiddAmt   = document.forms[0].strTotHiddRs.value;
	 if(Amt.value == '')
	 {
	     alert("Plz Enter Amt Blank Value Not Allowed!!!!!");
	  	 FinalMul.value = "0";   
	     var objSum=document.getElementsByName("strFinalMul");
	     var s=0;
	     for(var j=0;j<objSum.length;j++)
	     {
	      objSum[j].disabled = false;
		  if(objSum[j].value=="")
		   {
		    s=parseFloat(s);
		   } 
		  else
		   { 
		    
	        s=parseFloat(s)+parseFloat(objSum[j].value);
	       } 
	       objSum[j].disabled = true;
	    }
	   document.forms[0].strTotRs.value     = s;
	   document.forms[0].strTotHiddRs.value = s;	   
	 }
	 else
	 {
	  // alert("Inside Calculation::QtyFinal::"+QtyFinal.value+":::Amt:::"+Amt.value+":::HiddenValue is:::"+HiddAmt);
	     
       if(Amt.value!= 0 )
       {
         FinalAmt = parseFloat(QtyFinal.value)*parseFloat(Amt.value); 
         FinalMul.value  =  FinalAmt;
       }
       else
       {
          totalAmt = parseFloat(QtyFinal.value)*parseFloat(Amt.value);
          document.forms[0].strTotRs.value     = parseFloat(document.forms[0].strTotHiddRs.value)- parseFloat(document.forms[0].strTotRs.value);
	      document.forms[0].strTotHiddRs.value = document.forms[0].strTotRs.value;	   
          
       } 
       totalAmt = totalAmt + parseFloat(FinalAmt);
	   if(HiddAmt =='0')
	   {
	     document.forms[0].strTotHiddRs.value = totalAmt;
	     document.forms[0].strTotRs.value     = totalAmt;
	   }
	   else
	   {
	     document.forms[0].strTotRs.value     = parseFloat(document.forms[0].strTotHiddRs.value)+totalAmt;
	     document.forms[0].strTotHiddRs.value = document.forms[0].strTotRs.value;
	   }
	 }
    }
	function goFunctionAddClientApproval()
    {
     var temp = document.forms[0].strGrName.value+"//"+document.forms[0].strGRDate.value;
     document.forms[0].strGrName.value = temp;
     var hisValidator = new HISValidator("clientverificTransBean");  
	 hisValidator.addValidation("strInvoice", "req", "Invoice is a Mandatory Field" );
	 hisValidator.addValidation("strNoc", "req", "NOC is a Mandatory Field" );
	 hisValidator.addValidation("strTotBill", "req", "Total Bill is a Mandatory Field" );
	
	 var retVal = hisValidator.validate(); 
	
		if(retVal)
		{
				document.forms[0].hmode.value="CLIENTVERIFICATION";
				document.forms[0].submit();
		}
		else
		{
		    	return false;
		}
		
     }
	
function addVat(){

    var tax = document.forms[0].strTax.value;
	var tot = document.forms[0].strTotRs.value;
	var strTot = eval(eval(eval(100+eval(tax))/100))* eval(tot);
	document.forms[0].strTotHidd.value = strTot;
}
	