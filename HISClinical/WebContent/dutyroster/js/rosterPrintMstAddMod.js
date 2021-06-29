function submitDutyArea()
{
	document.forms[0].submit();
}

function submitPage(mode)
{
var flag=true;

if(mode=="GET_ROSTER_TYPES")
	document.forms[0].rosterType.value="-1";
	
if(mode=="SAVE_CHANGE_ORDER")
	changeDisplayOrder();	
	
	
	
if(mode=="CHANGE_ORDER")	
{
flag=false;

if(
       comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")	
	&& comboValidation(document.getElementsByName('rosterType')[0],"Roster Type")
  )
   {
   flag=true;
   }

}

if(flag==true)
	{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
	}
	
	
}

function validateFinalSubmit(){
     
 //   These All Fields are Mandatory
 var flag=false;
 
 var lengthOfInstructions=document.getElementsByName("displayInstruction").length;
 var lengthOfCopyTo=document.getElementsByName("displayCopyTo").length;
 
		
		
if(
       comboValidation(document.getElementsByName('rosterCategory')[0],"Roster Category")	
	&& comboValidation(document.getElementsByName('rosterType')[0],"Roster Type")
   )
   {
   flag=true;
   }	
		
		
if( flag==true && lengthOfInstructions > 1)
		{
	
	for(var i=0 ; i < lengthOfInstructions ; i++)
			{
		
		
	if(document.getElementsByName("displayInstruction")[i].value=="")
				 {
			alert("Please Enter Display Value of Instructions");
			document.getElementsByName("displayInstruction")[i].focus();
		
			flag=false;
			break;		
				 }
	
			}
	
	
		}
		
if(
	 flag==true
&& 	 isEmpty(document.getElementsByName('displayRosterBy')[0],"Roster By")
  )
  {
	flag=true;	  
  }
  else
    flag=false;
  
   

		
  	
if(lengthOfCopyTo > 1 & flag!=false)
		{
	
	for(var i=0 ; i < lengthOfCopyTo ; i++)
			{
			
	if(document.getElementsByName("displayCopyTo")[i].value=="")
				  { 
	alert("Please Enter Display Value of Copy To ");
	document.getElementsByName("displayCopyTo")[i].focus();
			
			flag=false;
			break;		
				  }	
	
	
			}
	
	
		}
		
  
    return flag;
} 	
	
function finalSubmit(mode)
{
var flag=true;
	if (validateFinalSubmit()) 
	{
		  concateDataToBeSaved();
 	     submitPage(mode);
    	
	}
}




function clearForm()
 {
  	document.getElementsByName('rosterCategory')[0].value="-1";
    document.getElementsByName('rosterType')[0].value="-1";
     submitPage('NEW'); 
        
 }


function AddRowToTable(tableId,textBoxId)
{
//	alert('fn called====='+tableId);
	
	var nRow=0;
	var tableObj=document.getElementById(tableId);
	var numRows=tableObj.rows.length;
//alert(document.getElementsByName("tableId").length);
	
	
		nRow=numRows;
	
//nRow=document.getElementsByName("tableId").length;

//	alert(nRow);
//	alert(tableObj.rows[numRows-1].id)
	
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=tableId+(parseInt(nRow)+1);
	
	var indexVolSpecific=numRows-1;
 //alert("indexVolSpecific "+indexVolSpecific)

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");

	td1.innerHTML="<div align='center'>"+tabRow.rowIndex+"</div>";
//	td1.innerHTML="<div align='center'></div>";
	td1.className='tdfont';
	td1.colspan="1";
	
	
	td2.innerHTML="<div align='center'><input type='text' name='"+textBoxId+"' size='50' tabindex='1' ></div>";
	td2.className='tdfont';
	td2.colspan="1";
		
		

	td3.className='tdfont';
	td3.colspan="1";
	td3.innerHTML="<div align='center'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteDiagRow(document.getElementById(\""+tabRow.id+"\"),"+tableId+")'></div>";
   
 // alert("<div align='center'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteDiagRow(document.getElementById(\""+tabRow.id+"\"),"+tableId+")'></div>")
   
   td4.innerHTML="<div align='center'></div>";
	td4.className='tdfont';
	td4.colspan="1";
   
   
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
	
	
if(tableId=="InstructionTableId")
{	
	 document.forms[0].noOfInstructionRow.value=numRows;
}

if(tableId=="copyToByTableId")
{
	 document.forms[0].noOfCopyToRow.value=numRows;
}
	 //alert(numRows);
	 
	
}

function deleteDiagRow(Str,tableId)
{	
//alert('delete fn called----'+tableId);
//	 alert(Str.rowIndex);
	
	//var tableObj=document.getElementById('"+tableId+"');
	
	var tableObj=tableId;
	
	var temp=Str;
	var deleteIndex=temp.rowIndex;
	
	
//	alert("temp.rowIndex="+temp.rowIndex)
	tableObj.deleteRow(temp.rowIndex);
	
	
if(tableId==document.getElementById("InstructionTableId"))
{	

//total no of rows are put into  noOfInstructionRow
//after substracting a row

	var index=parseInt(document.getElementsByName('noOfInstructionRow')[0].value);
	document.getElementsByName('noOfInstructionRow')[0].value=index-1;
	

//changing the display order of the rows from the deleted index
	
	for(i=deleteIndex ; i < index ; i++)	
	tableId.rows[i].cells[0].innerHTML="<div align='center'>"+i+"</div>"
	

}
	
	
	
if(tableId==document.getElementById("copyToByTableId"))
{	

//total no of rows are put into  noOfInstructionRow
//after substracting a row


	var index=parseInt(document.getElementsByName('noOfCopyToRow')[0].value);
	document.getElementsByName('noOfCopyToRow')[0].value=index-1;
	
			
//changing the display order of the rows from the deleted index
			
for(i=deleteIndex ; i < index ; i++)	
		tableId.rows[i].cells[0].innerHTML="<div align='center'>"+i+"</div>"
		


}

	
	
	return true;
}


   function concateDataToBeSaved()
{
var noOfInstructionRow=document.getElementsByName("noOfInstructionRow")[0].value
var noOfCopyToRow=document.getElementsByName("noOfCopyToRow")[0].value

var valueTobeSaved1="";
var concatedValueToBeSaved1="";

for(var i=1 ; i <= noOfInstructionRow ; i++)
	{
	
	valueTobeSaved1=document.getElementsByName("displayInstruction")[i-1].value	

if(valueTobeSaved1!="")
		concatedValueToBeSaved1+=1+"@"+i+"@"+valueTobeSaved1+"#"	
		else
		concatedValueToBeSaved1="NA";
		
	
	}
	   
	  // alert("concatedValueToBeSaved1---------"+concatedValueToBeSaved1)
document.getElementsByName("concatedValueOfInstruction")[0].value=concatedValueToBeSaved1;	   
	   

var valueTobeSaved2="";
var concatedValueToBeSaved2="";


for(var i=1 ; i <= noOfCopyToRow ; i++)
	{
	valueTobeSaved2=document.getElementsByName("displayCopyTo")[i-1].value	
	
if(valueTobeSaved2!="")
		concatedValueToBeSaved2+=3+"@"+i+"@"+valueTobeSaved2+"#"	
		else
		concatedValueToBeSaved2="NA";
			
	}
	   
	//   alert("concatedValueToBeSaved2---------"+concatedValueToBeSaved2)
	   
document.getElementsByName("concatedValueOfCopyTo")[0].value=concatedValueToBeSaved2;	  


document.getElementsByName("concatedValueOfRosterBy")[0].value=2+"@"+1+"@"+document.getElementsByName("displayRosterBy")[0].value;

}  
      

//////////////////////////////////function to move element up in list ////////////////////////////////
function moveUP(Obj)
{
	var list=Obj;	
	var len=list.length;
	for(var i=0;i<len;i++)
	{
		if(list.options[i].selected)
		{
			if(i==0) return;
			
			var temp;
			temp=list.options[i-1].value;
			list.options[i-1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i-1].text;
			list.options[i-1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i-1].selected=true;;
			list.options[i].selected=false;
		}
	}
}


//////////////////////////////////function to move element down in list ////////////////////////////////
function moveDOWN(Obj)
{
	
	var list=Obj;	

	var len=list.length;
	for(var i=len-1;i>=0;i--)
	{
		if(list.options[i].selected)
		{
			if(i==(len-1)) return;
			
			var temp;
			temp=list.options[i+1].value;
			list.options[i+1].value=list.options[i].value;
			list.options[i].value=temp;
			
			temp=list.options[i+1].text;
			list.options[i+1].text=list.options[i].text;
			list.options[i].text=temp;
			
			list.options[i+1].selected=true;;
			list.options[i].selected=false;
		}
	}
}
       
  function changeDisplayOrder()
{


var noOfInstructionRow=document.forms[0].displayInstruction.length
var noOfCopyToRow=document.forms[0].displayCopyTo.length

var valueTobeSaved1="";
var concatedValueToBeSaved1="";


for(var i=1 ; i <= noOfInstructionRow ; i++)
	{
	
	valueTobeSaved1=document.forms[0].displayInstruction.options[i-1].value	
	
//	alert("valueTobeSaved1---"+valueTobeSaved1)
	concatedValueToBeSaved1+=i+"@"+valueTobeSaved1+"#"	
			
	}
	   
//	   alert("concatedValueToBeSaved1---------"+concatedValueToBeSaved1)
document.getElementsByName("concatedValueOfInstruction")[0].value=concatedValueToBeSaved1;	   
	   

var valueTobeSaved2="";
var concatedValueToBeSaved2="";


for(var i=1 ; i <= noOfCopyToRow ; i++)
	{
	   valueTobeSaved2=document.forms[0].displayCopyTo.options[i-1].value	
	   
	//   alert("valueTobeSaved2---"+valueTobeSaved2)
	   
		concatedValueToBeSaved2+=i+"@"+valueTobeSaved2+"#"	
		
			
	}
	   
	//   alert("concatedValueToBeSaved2---------"+concatedValueToBeSaved2)
	   
document.getElementsByName("concatedValueOfCopyTo")[0].value=concatedValueToBeSaved2;	  



}  

function focusOnLoad()
{

if(document.forms[0].elements[0].value=="-1")
  	document.forms[0].elements[0].focus();
  	else
if(document.forms[0].elements[1].value=="-1")
  	document.forms[0].elements[1].focus();
  	else
  	document.forms[0].elements[2].focus();
	


}  
       