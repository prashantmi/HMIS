var chkValue	=	"";


function userDefinedOnLoadFunc()
{
	 
}
function buttonLogicsOnRecordCheck(these)
{  

     //  alert(document.forms[0].chk.checked);
       var chkObj  = document.getElementsByName("chk");
       var count = parseInt("0");
   	   for(var i=0; i<chkObj.length; i++) 
   	   {
   	   //  alert(chkObj[i].checked);
   	  		if(chkObj[i].checked)
   	  		{
   	 		  count = count + 1;
   	 	    }	
   	 	     	 	       	 	    	
   	   }
   	   
		 disableButton("Correction");	 	
   	  	 disableButton("Verification");
	     disableButton("Validation"); 	    	
   	   
}

function buttonLogicsOnRecordCheck1(these)
{  

     //  alert(document.forms[0].chk.checked);
       var chkObj  = document.getElementsByName("chk");
       var count = parseInt("0");
       chkValue = these.value;
   	   for(var i=0; i<chkObj.length; i++) 
   	   {
   	   //  alert(chkObj[i].checked);
   	  		if(chkObj[i].checked)
   	  		{
   	 		  count = count + 1;
   	 	    }	
   	 	     	 	       	 	    	
   	   }
   	   if(count > 1)
   	   {
   	     alert("Plz Select Single Record at a time!!!!!");
   	     for(var i=0; i<chkObj.length; i++) 
   	     {
   	       	chkObj[i].checked=false;
   	  	 }
		 disableButton("Correction");	 	
   	  	 disableButton("Verification");
	     disableButton("Validation"); 	    	
   	     return false;
   	   }
       else
       {  
       	//enableButton commented by paras jain on 05-06-2014 
         //enableButton("Correction");	 	
   	  	 enableButton("Verification");
	    // enableButton("Validation"); 
	   }
       
}
function buttonLogicsOnClick1(modeNo, mode , display)
{
	try
	{
		var chkObj = document.getElementsByName("chk");
		
   			 var count = parseInt("0");
   	 
   	 		 for(var i=0; i<chkObj.length; i++) 
   	 		 {
   	 			if(chkObj[i].checked)
   	 			{
   	 				count = count + 1;
   	 		    }		
   	 		  
   	 		 }
   	 		 
   	 
   	 	//alert("ok"+chkObj[0].value);
		if(mode=='TEST'){
		
		
		add(mode);
		}
		else if(mode=='PARAMETER'){
		
		
		add(mode);
		}
		else if(mode=='MAPPING'){
	
		
		add(mode);
		}
		else if(mode=='ENTRY')
		{
		add(mode);		
		
		}
		else if(mode=='SHOWRPT'){
		
		add(mode);
		}
		else if(mode=='VIEW'){
		
		add(mode);
		}
		else if(mode=='VERIFICATION')
		{
			if(count!='0' || count=='1')
   	 		 {
   	 		 	add(mode);
   	 		 }
   	 		 else
   	 		 {
   	 		 	alert("Please select single record!!");
   	 		 	return;
   	 		 }
		//add(mode);
		}
		else if(mode=="PRINT")
		{
				if(count=="1")
				{
					var absURL = document.URL;
					if(absURL.search("transactions")=="-1")
					{
						var URL="EquipmentTestMstCNT.cnt?hmode="+mode+"&checkedVal="+chkValue; 
					}
					else
					{
						var URL="EquipmentInspectionTestDtlsACTION.cnt?hmode="+mode+"&checkedVal="+chkValue;  
					}	 
					var Child = window.open(URL,'popupWindow','status=yes,scrollbars=yes,height=1000,width=1000,left=0,top=0');
					return;
				}
				else
				 {
				 	alert("Please select single record!!");
				 }
		}
	}
	catch(Err)
	{
		alert(Err);
	}
	function showReport(mode)
	{
		add(mode);
	}
	
}

