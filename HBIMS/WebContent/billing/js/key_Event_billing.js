	function moveUpDown(e,obj,index)
	{
		//alert(e.keyCode);
		if(e.keyCode == 38 )//Up Arrow
		{
			var newIndex=--index;
			var nextElementId=obj.name+""+newIndex;
			var objLen=document.getElementsByName("deleteFlag").length;
			//alert(objLen);
			//alert(index);
			for(var i=index;i>=0;i--)
			{
				//alert(document.getElementById("deleteFlag"+i).value);
				//if(document.getElementById("deleteFlag"+i).value==0)//Not Deleted
				//{
					/*if(document.getElementById(nextElementId)!=null || document.getElementById(nextElementId)!=undefined)
					{
						document.getElementById(nextElementId).focus();
						document.getElementById(nextElementId).select();
						return;
					}*/
					if(document.getElementById(obj.name+""+i)!=null || document.getElementById(obj.name+""+i)!=undefined)
					{
						document.getElementById(obj.name+""+i).focus();
						//document.getElementById(obj.name+""+i).select();
						return;
					}
				//}
			}
		}
		if(e.keyCode == 40 )//Down Arrow
		{ 			
			var newIndex=++index;
			var nextElementId=obj.name+""+newIndex;
			//alert(nextElementId);
			//alert(document.getElementById(nextElementId));
			var objLen=document.getElementsByName("deleteFlag").length;
			//alert('length'+objLen);
			//alert('index'+index);
			for(var j=index;j<objLen;j++)
			{
				//alert("sasd"+document.getElementById("deleteFlag"+j).value);
				//if(document.getElementById("deleteFlag"+j).value==0)//Not Deleted
				//{
					if(document.getElementById(obj.name+""+j)!=null || document.getElementById(obj.name+""+j)!=undefined)
					{
						//alert(obj.name+""+j);
						document.getElementById(obj.name+""+j).focus();
						//document.getElementById(obj.name+""+j).select();
						return;
					}
				//}
			}
		}
		if(e.keyCode == 13 )//Down Arrow
		{
			//alert(index);
			//alert(document.getElementById("strTariffVal"+index).value);
			setTariffCode1(document.getElementById("strTariffVal"+index).value,document.getElementById("strtariffcode"+index).value+"^"+document.getElementById("grpid"+index).value);
		}
		
	}

	var masterHotKeyCode=18; // Key :: ALT 
	var cntrlKey=false;
	var exceptionKeysCombo=false; // ALT[17] + CTRL[18] + R[82]/S[83] In case of Billing
	var _helpKeyCode=112; // Key :: F1
	var enableShortCutKey=true;


	//Function <checkFirstKey> is where Hot Key is tracked
	
	function checkFirstKey(e)
	{
		//alert(e.keyCode);
		if(e.keyCode==masterHotKeyCode)
		{
		    first_key_Down=true;	    
		}  
		if(first_key_Down && cntrlKey==false)
		{
			if(e.keyCode==18)
			{
				cntrlKey=true;
			}
		}
		if(first_key_Down && cntrlKey)
		{
			if(e.keyCode==82 || e.keyCode==83)
			{
			   exceptionKeysCombo=true;
			}   
		}		
	}

	function deleteRow(e,obj,index,trIndex)
	{
		//alert(e.keyCode);
		//alert(masterHotKeyCode);
		//alert(first_key_Down);
		//alert(e.keyCode);
		
		if(e.keyCode==masterHotKeyCode || exceptionKeysCombo)
		{
		    first_key_Down  = false;	    
		    exceptionKeysCombo = false;
		}
		else
		{
			 if(first_key_Down == true && enableShortCutKey)
		 	 {
		 		if(e.keyCode == 110 )//Delete Numpad . Key
		 		{
		 			 if(document.getElementById("strServTypeId"+index).value!="0")//Online or Reverse Entry
		 		 	 {
		 		 		 alert("Online Tariffs Can Not Be Deleted...");
		 		 		 return false;
		 		 	 }
		 			else
		 			{
			 			document.getElementById("deleteFlag"+index).value=1; 
			 			//document.getElementById(trIndex).style.display="none";
			 			document.getElementById(trIndex).className ="strikeout";
			 			
			 			var newIndex=++index;
			 			var nextElementId=obj.name+""+newIndex;
			 			var objLen=document.getElementsByName("deleteFlag").length;
			 			//alert(objLen);
			 			//alert(index);
			 			for(var i=index;i<objLen;i++)
			 			{
			 				//alert(document.getElementById("deleteFlag"+i).value);
			 				if(document.getElementById("deleteFlag"+i).value==0)
			 				{
			 					if(document.getElementById(nextElementId)!=null || document.getElementById(nextElementId)!=undefined)
			 					{
			 						document.getElementById(nextElementId).focus();
			 						document.getElementById(nextElementId).select();
			 						return;
			 					}
			 				}			
			 			}
		 			}
		 		}
		 		if(e.keyCode == 85 )//U
		 		{
		 			 if(document.getElementById(trIndex).className =="strikeout")//Already Deleted
		 		 	 {
		 				document.getElementById("deleteFlag"+index).value=0; 
			 			document.getElementById(trIndex).className ="";
		 		 	 }
		 		}
		 		if(e.keyCode == 107 )//+Focus Trf Code
		 		{
		 			document.forms[0].strTariffCode.focus();	 			
		 		}
		 	 }	 		 
		 }
		//calcOffLineTariffNetAmountNew(obj,index) ;
		first_key_Down=false;		
	} 
