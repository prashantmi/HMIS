function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function AddRowToTable()
{ 
		
	    var nRow=0;
	  	var tableObj=document.getElementById('tableComponentDetailId');
	  	var numRows=tableObj.rows.length;
	  	
	  	
	  	if(numRows>2)
	  	{
	  		nRow=tableObj.rows[numRows-1].id;
	  	}
	  	else
	  	{
	  		nRow=numRows;
	  	}

	  	var tabRow=tableObj.insertRow(numRows);
	  	tabRow.id=parseInt(nRow)+1;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
	  	

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	  	var td3=document.createElement("TD");
	 	
	  	  	
		td1.innerHTML="<div align='right'><font color='RED' size='1' face='Verdana, Arial, Helvetica, sans-serif'> * </font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Mandatory Value<bean:message key=MandatoryValue />&nbsp; </font> </div>";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='left'>"+" <input type='text' name='mandatoryValue' id='mandatoryValue"+(parseInt(indexVolSpecific)+1)+"'  value='' maxlength='25' size='30' /></div>"
	  	td2.className='tdfont';
	  	td2.colspan="1";
	
   		  	
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	td3.innerHTML="<div align='center'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
	  	
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	
	  	document.forms[0].numberOfRow.value=numRows;
}



function deleteRow(Str)
{	
    
	
	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
	var tableObj=document.getElementById('tableComponentDetailId');
    var temp=Str;
 
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);
        
    return true;
    
}





function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	if(document.getElementsByName("mandatoryCode")[0].value=="-1")
	{
		alert("Select Mandatory Name ");
		document.getElementsByName("mandatoryCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("mandatoryValue")[0].value=="" && document.getElementsByName("storeValue")[0].value=="")
	{
		alert("Enter Mandatory Value ");
		document.getElementsByName("mandatoryValue")[0].focus();
		return false;                          
	}
	
	
	
	var tableObj=document.getElementById('tableComponentDetailId');
  	var numRows=document.getElementsByName("numberOfRow")[0].value;

  	var i=0;
  	
  	for(i=0;i<numRows;i++)
  		{
  		
  		if(document.getElementsByName("mandatoryValue")[i].value=="")
  		{
  			alert("Enter Mandatory Value");
  			document.getElementsByName("mandatoryValue")[i].focus();
  			

  			return false;
  		}
  		
  		
  		
  	
  		}
	
	
	return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	submitPage(mode);
}

function clearForm()
 {  
    submitPage('CLEAR');
    	
 }
