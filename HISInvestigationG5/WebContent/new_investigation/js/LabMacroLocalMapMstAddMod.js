function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	
	if(document.getElementsByName("labCode")[0].value=="-1")
	{
		alert("Select Laboratory ");
		document.getElementsByName("labCode")[0].focus();
		return false;                          
	}
	if(document.getElementsByName("localLaboratoryName")[0].value=="")
    {
          alert("Write Local Laboratory Name");
          document.getElementsByName("localLaboratoryName")[0].focus();
          return false;                          
    }
	if(document.getElementsByName("mappedList")[0].length==0)
    {
          alert("Select atleast one Macro");
          document.getElementsByName("mappedList")[0].focus();
          return false;                          
    }
	
	for(var i=0;i<document.getElementsByName("userMacroCode").length;i++)
	{
		
		
		var k=0;
		for(k=i+1;k<document.getElementsByName("userMacroCode").length;k++)
			if(document.getElementsByName("userMacroCode")[i].value.toUpperCase()==document.getElementsByName("userMacroCode")[k].value.toUpperCase())
			{	alert("Duplicate Macro Code Exists. Enter Unique Values.");
				document.getElementsByName("userMacroCode")[k].focus();
				return false;
			}
	
	if(document.getElementsByName("userMacroCode")[i].value=="")
{
		  alert("Enter the Code Value");
          document.getElementsByName("userMacroCode")[i].focus();
          return false;

}		
	
	}

   
   return true;
 } 	
	
function finalSubmit(mode)
{
	
	
	
	
	
	if (!validateFinalSubmit()) return;
	if(mode=="SAVE")
		document.getElementById("macroCodeInput").style.display="none";
	submitPage(mode);
	
}

function clearForm()
 {
     submitPage('CLEAR');
 }

function moveRightSingle()
{
	document.getElementById("macroCodeInput").style.display="";
	
	var source  = document.forms[0].unmappedList;
	var target = document.forms[0].mappedList;	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;		
			
			AddRowToTable(txt);
			
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function deleteThis(source,target)
{	
	var tarlen = target.length;
	var srclen = source.length;

	var a1 = new Array(tarlen);
	var a2 = new Array(tarlen);
	var a3 = new Array(srclen);

	for(var i=0;i<tarlen;i++)
	{		
		a1[i]= target.options[i].value;
		a2[i]= target.options[i].text;	
	}
	for( i=0;i<srclen;i++)		
		a3[i]= source.options[i].value;

	target.length = 0;
	var counter = 0;
	var k = 0;
	var flag = 0;

	for(i=0;i<tarlen;i++)
	{		
		flag = 0;
		for(k=0;k<srclen;k++)
			if(a1[i]==a3[k])
				flag = 1;					
		if(flag==0)
		{
			target.length = (counter+1);
			target.options[counter].value = a1[i];
			target.options[counter].text  = a2[i]; 
			counter++;			
		}		
	}
}

function moveLeftSingle()
{
	var source;
	var target;

	document.getElementById("macroCodeInput").style.display="";
	
		source  = document.forms[0].mappedList;
		target = document.forms[0].unmappedList;	
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		if(source.options[i].selected)
		{
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			deleteRow(document.getElementById(""+txt+""),txt);
			
			
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		}
	}
	deleteThis(target,source);
}

function MoveToSelected()
{
	// Select All Shift in Selected
	if(document.forms[0].mappedList)
	{	
		for(var i=0;i<document.getElementsByName("mappedList")[0].length;i++)
		{
			document.getElementsByName("mappedList")[0].options[i].selected=true;
		}
	}
	// Unselect Remaining 
	if(document.forms[0].unmappedList)
	{	
		for(var i=0;i<document.forms[0].unmappedList.options.length;i++)
			document.forms[0].unmappedList.options[i].selected=false;
	}
	

}



function moveRightAll()
{
	document.getElementById("macroCodeInput").style.display="";
	
	var source  = document.forms[0].unmappedList;
	var target = document.forms[0].mappedList;	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			AddRowToTable(txt);
			
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		
	}
	deleteThis(target,source);
}


function moveLeftAll()
{
	var source;
	var target;

	document.getElementById("macroCodeInput").style.display="";
	
		source  = document.forms[0].mappedList;
		target = document.forms[0].unmappedList;	
	
	

	var totalElement = source.length;
	var val = "";
	var txt = "";
	var targetlen = 0;

	for(var i=0;i<totalElement;i++)
	{
		
			val = source.options[i].value;
			txt = source.options[i].text;			
		
			deleteRow(document.getElementById(""+txt+""),txt);
			
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		
	}
	deleteThis(target,source);
}




function AddRowToTable(macroName)
{ 
		
	    var nRow=0;
	  	var tableObj=document.getElementById('macroCodeInput');
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
	  	tabRow.id=macroName;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
	  	

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	 
	 	
	  	  	
		td1.innerHTML="<div align='center'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+macroName+"&nbsp; </font> </div>";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='center'>"+" <input type='text' name='userMacroCode' id='userMacroCode"+index+"'  value='' maxlength='10' size='60' style='width:200px;'/></div>"
	  	td2.className='tdfont';
	  	td2.colspan="1";
	 		  	
	  
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  
	  	
	  	document.getElementsByName("numberOfRow")[0].value=numRows;
}

function deleteRow(Str,txt)
{	
    
	//alert(Str);
	/*var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);*/
	
	var tableObj=document.getElementById('macroCodeInput');
    var temp=Str;
	var numRows=tableObj.rows.length;
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);
        
    return true;
    
}



