function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	//alert(mode);
	document.forms[0].submit();
}


function setdefaultvariable(mode){
	if (mode=="MODIFYSAVE"){ return true;}
	else{
	  	var tableObj=document.getElementById('tableComponentDetailId');
	  	var numRows1=tableObj.rows.length;
	  	var numRows1=numRows1-1;
	  	var df;
	for(i=0;i<numRows1;i++)
	{
	 if(document.getElementsByName("setdefaultradio")[i].checked==true)
	  {
	   if(i==0){df="1";}
	   else{ df=df+",1"}
     }else{			
	   if(i==0){df="0"}
	   else{df=df+",0"}
      }
	}
	//alert(df);
	document.getElementsByName("setdefault")[0].value=df;
	return true;
	}
}

function setlabel(){

	  	var tableObj=document.getElementById('tableComponentDetailId');
	  	var numRows1=tableObj.rows.length;
	  	var numRows1=numRows1-1;
	  
	for(i=0;i<numRows1;i++)
	{
	 if(document.getElementsByName("setdefaultradio")[i].checked==true)
	  {	$("#defaultestparalabel").css({"display":"none"});
		var dflabel="#defaultlabel"+i; $(dflabel).text('Default');
      }else{
	    var dflabel="#defaultlabel"+i; $(dflabel).text('');
       }
	}
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
	  	var td4=document.createElement("TD");
	  	
		td1.innerHTML="<div align='right'><font color='RED' size='1' face='Verdana, Arial, Helvetica, sans-serif'> * </font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Test Parameter Value<bean:message key=TestParaValue />&nbsp; </font> </div>";
	  	td1.className='tdfont';
	  	td1.colspan="1";
	  	
	  	td2.innerHTML="<div align='left'>"+" <input type='text' name='testparaValue' id='testparaValue"+(parseInt(indexVolSpecific)+1)+"'  value=''  size='30' /></div>";
	  	td2.className='tdfont';
	  	td2.colspan="1";
	  	
	  	td3.className='tdfont';
	  	td3.colspan="1";
	  	td3.innerHTML="<div align='center'><input  type='radio' name='setdefaultradio' onclick='setlabel();' id='defaultradio"+(parseInt(indexVolSpecific))+"' value='0'/> <label id='defaultlabel"+(parseInt(indexVolSpecific))+"' ></label> </div>";
   		  	
	  	td4.className='tdfont';
	  	td4.colspan="1";
	  	td4.innerHTML="<div align='center'><img src='/HISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
	  	

	  	tabRow.appendChild(td1);  
	  	tabRow.appendChild(td2);
	  	tabRow.appendChild(td3);
	  	tabRow.appendChild(td4);
	  	
	  	
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

function showdefaultradio(str1)
{		
	$("#"+str1).css({"display":"block"});
}

function hidedefaultradio(str2)
{		
	$("#"+str2).css({"display":"none"});    
    
}





function validateFinalSubmit(){
     
     // These All Fields are Mandatory
    
	if(document.getElementsByName("testCode")[0].value=="-1")
	{
		alert("Select Test Name ");
		document.getElementsByName("testCode")[0].focus();
		return false;                          
	}
	
	if(document.getElementsByName("parameterCode")[0].value=="-1")
	{
		alert("Select Parameter Name ");
		document.getElementsByName("parameterCode")[0].focus();
		return false;                         
	}
	
	
	
	if(document.getElementsByName("testparaValue")[0].value=="" && document.getElementsByName("testParameterValue")[0].value=="")
	{
		alert("Enter Test Parameter Value");
		document.getElementsByName("testparaValue")[0].focus();
		document.getElementsByName("testParameterValue")[0].focus();

		return false;
	}
	
	
	var tableObj=document.getElementById('tableComponentDetailId');
  	var numRows=document.getElementsByName("numberOfRow")[0].value;

  	var i=0;
  	
  	for(i=0;i<numRows;i++)
  		{
  		

  		
  		if(document.getElementsByName("testparaValue")[i].value=="")
  		{
  			alert("Enter Test Parameter Value");
  			document.getElementsByName("testparaValue")[i].focus();

  			return false;
  		}
	
  		
  	
  		}
		
	return true;
 } 	
	
function finalSubmit(mode)
{   
    setdefaultvariable(mode);
	if (!validateFinalSubmit()) return;
	//alert("finalsubmit setdefault value= "+document.getElementsByName("setdefault")[0].value+"");
	submitPage(mode);
}

function clearForm()
 {  

    submitPage('CLEAR');

 }
