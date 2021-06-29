function submitPage(mode)
{
	MoveToSelected();
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	
	if(document.getElementsByName("groupCode")[0].value=="-1")
	{
		alert("Select Group ");
		document.getElementsByName("groupCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("userGroupCode")[0].value=="")
	{
		alert("Please Enter Group Code ");
		document.getElementsByName("userGroupCode")[0].focus();
		return false;                          
	}
	

	if(document.getElementsByName("labCode")[0].value=="-1")
	{
		alert("Select Laboratory");
		document.getElementsByName("labCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("mappedList")[0].length==0)
	{
		alert("Please Select at least 1 Test");
	  	document.getElementsByName("mappedList")[0].focus();
		return false;                          
	}
	
	
	if(document.getElementsByName("isDynamicResult")[0].checked && document.getElementsByName("isDynamicTemplate")[0].checked)
   {
		if(document.getElementsByName("printingTemplateCode")[0].value=="-1")
			{
			
			alert("Please Select Printing Template");
		  	document.getElementsByName("printingTemplateCode")[0].focus();
			return false;                          

			
			
			}
		
		
		
   }
	
	
	for(var i=0;i<document.getElementsByName("testSeqNo").length;i++)
	{
	
	var k=0;
	for(k=i+1;k<document.getElementsByName("testSeqNo").length;k++)
		if(document.getElementsByName("testSeqNo")[i].value.toUpperCase()==document.getElementsByName("testSeqNo")[k].value.toUpperCase())
		{	alert("Enter Unique Sequence No.");
			document.getElementsByName("testSeqNo")[k].focus();
			return false;
		}
	
	
	
	if(document.getElementsByName("testSeqNo")[i].value=="")
{
		  alert("Enter the Sequence No.");
          document.getElementsByName("testSeqNo")[i].focus();
          return false;

}		
	
	}

	
	
	
	
   return true;
 } 	
	
function finalSubmit(mode)
{
	if (!validateFinalSubmit()) return;
	if(mode=="SAVE")
		document.getElementById("seqNumber").style.display="none";
	submitPage(mode);
	
}

function clearForm()
 {
     submitPage('CLEAR');
 }

function moveRightSingle()
{
	
	document.getElementById("seqNumber").style.display="";
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
		
			AddRowToTable(val,txt);
			
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

	document.getElementById("seqNumber").style.display="";
	
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
		
			deleteRow(document.getElementById(""+val+""),txt);
			
			
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
			document.getElementsByName("unmappedList")[0].options[i].selected=false;
	}
	

}


function moveRightAll()
{
	
	document.getElementById("seqNumber").style.display="";
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
		
			AddRowToTable(val,txt);
			
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

	document.getElementById("seqNumber").style.display="";
	
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
		
			deleteRow(document.getElementById(""+val+""),txt);
			
			targetlen = target.length;							
			target.length = (targetlen+1);				
			
			target.options[targetlen].value = val;
			target.options[targetlen].text  = txt;													
		
	}
	deleteThis(target,source);
}




function AddRowToTable(testCode,testName)
{ 
		
	    var nRow=0;
	  	var tableObj=document.getElementById('seqNumber');
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
	  	tabRow.id=testCode;

	  	var indexVolSpecific=numRows-1;

	  	var index=parseInt(numRows-1);
	  	

	  	var td1=document.createElement("TD");
	  	var td2=document.createElement("TD");
	 
	 	
	  	  	
		td1.innerHTML="<div align='center'><font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'>"+testName+"<bean:message key=MandatoryValue />&nbsp; </font> </div>";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	
	  	td2.innerHTML="<div align='center'>"+" <input type='text' name='testSeqNo' id='testSeqNo"+index+"'  value='' maxlength='10' size='60' style='width:200px;' /></div>"
	  	td2.className='tdfont';
	  	td2.colspan="1";
	 		  	
	  
	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  
	  	
	  	document.getElementsByName("numberOfRow")[0].value=numRows;
}

function deleteRow(Str,txt)
{	
    
	
/*	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);*/
	var tableObj=document.getElementById('seqNumber');
    var temp=Str;
	var numRows=tableObj.rows.length;
    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);
        
    return true;
    
}
