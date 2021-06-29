

function validateOnAddDoctor()
{
//alert("OnAdd")
    var valid=false;
   if( comboValidation(document.getElementsByName('empID')[0],"Doctor")
       && comboValidation(document.getElementsByName('roleID')[0],"Role")
	  )
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }
  
  
  
  function validateOnAddEscort()
{
    var valid=false;
   if(comboValidation(document.getElementsByName('escortedEmpID')[0],"Escorted By"))
		 	 {
			  valid=true;
			 }
			 else { valid=false;}
	return 	valid;	      
  }


 function validateOnSave()
{
 //alert("document.getElementsByName('numberOfRow')[0].value"+document.getElementsByName('numberOfRow')[0].value)
 
   if(document.getElementsByName('boardName')[0].value=="")
		{
			alert("Please Enter Board Name ");
			document.forms[0].boardName.focus();
			return false;                          
		}
 
  if(document.getElementsByName('numberOfRow')[0].value==0)
	 {
		alert("Add at Least One Doctor");
		return false;
	 }
  if(document.getElementsByName('numberOfRowEscorted')[0].value==0)
	 {
		alert("Add at Least One Escorted By");
		return false;
	 } 
	 
	 
	 
	return 	true;	      
  }

function finalSubmit(mode)
{
  if(validateOnSave())
  {
    submitPage(mode);
  }

}

function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}



function AddRowToTable()
 {
 	//-------- Validations
 	
 	
    var doctorName=document.getElementsByName('empID')[0];
    var roleName=document.getElementsByName('roleID')[0];
    
    //alert("doctorID"+doctorName.value);
     // alert("doctorName"+doctorName.options[doctorName.selectedIndex].text);
 //   var finalComboValue=document.getElementsByName('comboValue')[0];
   // var comboArray=document.getElementsByName('comboValueArray')[0].value; 
  
      
   /*  if(finalComboValue.value==""){
     finalComboValue.value=singleCombo.value;
   }else{
     finalComboValue.value=finalComboValue.value+"#"+singleCombo.value;
   }
   alert("finalComboValue"+finalComboValue.value);
    */
	
	
	
	
	// Adding
	var nRow=0;
	var tableObj=document.getElementById('idComboValue');
	var numRows=tableObj.rows.length;
	
	//alert("tabel Length"+numRows);
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
	}
	else
	{
		nRow=numRows;
	}

    //alert("nRow"+nRow);
    
	
	var tabRow=tableObj.insertRow(numRows);
	tabRow.id=parseInt(nRow)+1;
	
	var indexVolSpecific=numRows-1;
	var index=parseInt(numRows-1);

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");


	td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='empIDArray' value='"+doctorName.value+"' > "
	              +doctorName.options[doctorName.selectedIndex].text+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
		
	
	td2.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='roleIDArray' value='"+roleName.value+"'> "
	              +roleName.options[roleName.selectedIndex].text+"</div>";
	td2.className='tdfont';
	td2.colspan="1";
    td2.width='45%';
	
	td3.className='tdfont';
	td3.colspan="1";
	td3.innerHTML="<div align='left'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteDocRolRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"),\""+doctorName.value+"\",\""+doctorName.options[doctorName.selectedIndex].text+"\")'></div>";
    td2.width='27%';
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	
	 document.getElementsByName('numberOfRow')[0].value=numRows-1;
	 
	 //alert("document.getElementsByName('numberOfRow')[0].value"+document.getElementsByName('numberOfRow')[0].value)
	//doctorName.value="-1";
	//escortName.value="-1";
	
	
	// Removing from Emp Main List
	var len = doctorName.length;
	var tempVal = new Array(len-1);
	var tempText = new Array(len-1);
	var k=0;
	for(var i=0;i<len;i++)
	{
		if(i != doctorName.selectedIndex)
		{			
			tempVal[k]= doctorName.options[i].value;
			tempText[k++]= doctorName.options[i].text;
		}
	}
	doctorName.length = 0;
	for(i=0;i<len-1;i++)
	{
		doctorName.length = i+1;
		doctorName.options[i].value = tempVal[i];
		doctorName.options[i].text  = tempText[i]; 
	}
	
	// Resetting
	doctorName.selectedIndex=0;
	roleName.selectedIndex=0;	
}
 
 
 
  function deleteDocRolRow(Str,val,txt)
  {	
   //alert("insideeeeeeeeee");
   //alert("Str"+Str.rowIndex)
    var tableObj=document.getElementById('idComboValue');
    var temp=Str;
  //  var row=tableObj.rows[Str.rowIndex];
 //   var cell=row.cells[1];
   // alert("comboValueArray"+document.getElementsByName('comboValueArray')[Str.rowIndex].value)
    tableObj.deleteRow(temp.rowIndex);
  //  document.getElementsByName('comboValueArray')[Str.rowIndex].value="";

   document.getElementsByName('numberOfRow')[0].value=document.getElementsByName('numberOfRow')[0].value-1;

//alert("document.getElementsByName('numberOfRow')[0].valuedelete"+document.getElementsByName('numberOfRow')[0].value);

	// Adding Back from Emp Main List
	var doctorName=document.getElementsByName('empID')[0];
	/*var len = doctorName.length;
	doctorName.length = len+1;
	doctorName.options[len].value = val;
	doctorName.options[len].text  = txt;*/ 


	var len = doctorName.length;
	var tempVal = new Array(len+1);
	var tempText = new Array(len+1);
	var k=0, flag = true;
	tempVal[k]= doctorName.options[0].value;
	tempText[k++]= doctorName.options[0].text;
	for(var i=1;i<len;i++)
	{
		if(txt.toUpperCase() < doctorName.options[i].text.toUpperCase() && flag)
		{			
			tempVal[k]= val;
			tempText[k++]= txt;
			flag=false;
		}
		tempVal[k]= doctorName.options[i].value;
		tempText[k++]= doctorName.options[i].text;
	}
	doctorName.length = 0;
	for(i=0;i<k;i++)
	{
		doctorName.length = i+1;
		doctorName.options[i].value = tempVal[i];
		doctorName.options[i].text  = tempText[i]; 
	}
	doctorName.selectedIndex=0;
	
     return true;
  
  
  
  
  }
 
 
 
 
 
 
 
 function AddEscortedRowToTable()
 {
 
    
    var escortedByName=document.getElementsByName('escortedEmpID')[0];
    
    //alert("escortedEmpID"+escortedByName.value);
     // alert("escortedEmpID"+escortedByName.options[escortedByName.selectedIndex].text);
 //   var finalComboValue=document.getElementsByName('comboValue')[0];
   // var comboArray=document.getElementsByName('comboValueArray')[0].value; 
  
      
   /*  if(finalComboValue.value==""){
     finalComboValue.value=singleCombo.value;
   }else{
     finalComboValue.value=finalComboValue.value+"#"+singleCombo.value;
   }
   alert("finalComboValue"+finalComboValue.value);
    */
	var nRow=0;
	var tableObj=document.getElementById('idEscortedBy');
	var numRows=tableObj.rows.length;
	//alert("tabel Length"+numRows);
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
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


	td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='escortedEmpIDArray' value='"+escortedByName.value+"' > "
	              +escortedByName.options[escortedByName.selectedIndex].text+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
	
	
	td2.className='tdfont';
	td2.colspan="1";
	td2.innerHTML="<div align='left'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteEscortedByRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"),\""+escortedByName.value+"\",\""+escortedByName.options[escortedByName.selectedIndex].text+"\")'></div>";
    td2.width='10%';
   
   
    td3.innerHTML="";
	td3.className='tdfont';
	td3.colspan="1";
    td3.width='45%';
   
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	 document.getElementsByName('numberOfRowEscorted')[0].value=numRows-1;
	
	// Removing from Emp Main List
	var len = escortedByName.length;
	var tempVal = new Array(len-1);
	var tempText = new Array(len-1);
	var k=0;
	for(var i=0;i<len;i++)
	{
		if(i != escortedByName.selectedIndex)
		{			
			tempVal[k]= escortedByName.options[i].value;
			tempText[k++]= escortedByName.options[i].text;
		}
	}
	escortedByName.length = 0;
	for(i=0;i<len-1;i++)
	{
		escortedByName.length = i+1;
		escortedByName.options[i].value = tempVal[i];
		escortedByName.options[i].text  = tempText[i]; 
	}
	
	// Resetting
	
	
	
	escortedByName.selectedIndex=0;
	
}
 
 
 
  function deleteEscortedByRow(Str,val,txt)
  {	
   //alert("Str"+Str.rowIndex)
    var tableObj=document.getElementById('idEscortedBy');
    var temp=Str;
  //  var row=tableObj.rows[Str.rowIndex];
 //   var cell=row.cells[1];
   // alert("comboValueArray"+document.getElementsByName('comboValueArray')[Str.rowIndex].value)
    tableObj.deleteRow(temp.rowIndex);
  //  document.getElementsByName('comboValueArray')[Str.rowIndex].value="";
  
  
  document.getElementsByName('numberOfRowEscorted')[0].value=document.getElementsByName('numberOfRowEscorted')[0].value-1;
  
   var escortedByName=document.getElementsByName('escortedEmpID')[0];
	/*var len = doctorName.length;
	doctorName.length = len+1;
	doctorName.options[len].value = val;
	doctorName.options[len].text  = txt;*/ 


	var len = escortedByName.length;
	var tempVal = new Array(len+1);
	var tempText = new Array(len+1);
	var k=0, flag = true;
	tempVal[k]= escortedByName.options[0].value;
	tempText[k++]= escortedByName.options[0].text;
	for(var i=1;i<len;i++)
	{
		if(txt.toUpperCase() < escortedByName.options[i].text.toUpperCase() && flag)
		{			
			tempVal[k]= val;
			tempText[k++]= txt;
			flag=false;
		}
		tempVal[k]= escortedByName.options[i].value;
		tempText[k++]= escortedByName.options[i].text;
	}
	escortedByName.length = 0;
	for(i=0;i<k;i++)
	{
		escortedByName.length = i+1;
		escortedByName.options[i].value = tempVal[i];
		escortedByName.options[i].text  = tempText[i]; 
	}
	escortedByName.selectedIndex=0;
	
  
     return true;
  }
 
 
 
 
 
 
 function AddRowToTableOnModify()
 {
 
    var empId=document.getElementsByName('empIDValue')[0].value;
    var empName=document.getElementsByName('empName')[0].value;
    
    var roleId=document.getElementsByName('roleIDValue')[0].value;
    var roleName=document.getElementsByName('roleName')[0].value;
    
     empIdArray=empId.split("@");
     empNameArray=empName.split("@");
     roleIdArray=roleId.split("@");
     roleNameArray=roleName.split("@");
     
  
	var nRow=0;
	var tableObj=document.getElementById('idComboValue');
	
	var numRows=tableObj.rows.length;
	//alert("empIdArray on Modify"+empIdArray[0]);
	//alert("empIdArray.length"+empIdArray.length)
 
 for(var i=0;i<empIdArray.length;i++){
   // alert("i"+i);
 
  // alert("numRows"+numRows);
  
  
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
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


	td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='empIDArray' value='"+empIdArray[i]+"' > "
	              +empNameArray[i]+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
		
	
	td2.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='roleIDArray' value='"+roleIdArray[i]+"'> "
	              +roleNameArray[i]+"</div>";
	td2.className='tdfont';
	td2.colspan="1";
    td2.width='45%';
	
	td3.className='tdfont';
	td3.colspan="1";
	td3.innerHTML="<div align='left'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteDocRolRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"),\""+empIdArray[i]+"\",\""+empNameArray[i]+"\")'></div>";
    td2.width='27%';
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	
	 numRows++;
	}
	document.getElementsByName('numberOfRow')[0].value=numRows-2;
	
 }
 
 
  function AddEscortedByRowToTableOnModify()
 {
    var escortedById=document.getElementsByName('escortedEmpIDValue')[0].value;
    var escortedByName=document.getElementsByName('escortedEmpName')[0].value;
    
  
    
     escortedByIDArray=escortedById.split("@");
     escortedNameByArray=escortedByName.split("@");
  
	var nRow=0;
	var tableObj=document.getElementById('idEscortedBy');
	
	var numRows=tableObj.rows.length;
	//alert("escortedByIDArray on Modify"+ escortedByIDArray[0]);
	//alert("escortedByName on Modify"+    escortedNameByArray[0]);
	//alert("escortedByIDArray.length"+    escortedByIDArray.length);
 
 for(var i=0;i<escortedByIDArray.length;i++){
   // alert("i"+i);
 
  // alert("numRows"+numRows);
  
  
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
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


    td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='escortedEmpIDArray' value='"+escortedByIDArray[i]+"' > "
	              +escortedNameByArray[i]+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
	
	
	td2.className='tdfont';
	td2.colspan="1";
	td2.innerHTML="<div align='left'><img src='/HIS/hisglobal/images/avai/Minus.png' onClick='deleteEscortedByRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"),\""+escortedByIDArray[i]+"\",\""+escortedNameByArray[i]+"\")'></div>";
    td2.width='10%';
   
   
    td3.innerHTML="";
	td3.className='tdfont';
	td3.colspan="1";
    td3.width='45%';
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	 numRows++;
	}
	 
	 document.getElementsByName('numberOfRowEscorted')[0].value=numRows-2;
	
}
 
 
 
 
 
 
 
 function AddRowToTableOnView()
 {
    var empId=document.getElementsByName('empIDValue')[0].value;
    var empName=document.getElementsByName('empName')[0].value;
    
    var roleId=document.getElementsByName('roleIDValue')[0].value;
    var roleName=document.getElementsByName('roleName')[0].value;
    
     empIdArray=empId.split("@");
     empNameArray=empName.split("@");
     roleIdArray=roleId.split("@");
     roleNameArray=roleName.split("@");
     
  
	var nRow=0;
	var tableObj=document.getElementById('idComboValue');
	
	var numRows=tableObj.rows.length;
	//alert("empIdArray on Modify"+empIdArray[0]);
	//alert("empIdArray.length"+empIdArray.length)
 
 for(var i=0;i<empIdArray.length;i++){
   // alert("i"+i);
 
  // alert("numRows"+numRows);
  
  
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
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


	td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='empIDArray' value='"+empIdArray[i]+"' > "
	              +empNameArray[i]+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
		
	
	td2.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='roleIDArray' value='"+roleIdArray[i]+"'> "
	              +roleNameArray[i]+"</div>";
	td2.className='tdfont';
	td2.colspan="1";
    td2.width='45%';
	
	td3.className='tdfont';
	td3.colspan="1";
	td3.innerHTML="";
    td2.width='27%';
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	 numRows++;
	}
	// document.forms[0].numberOfRow.value=numRows;
	
}
 
 
  function AddEscortedByRowToTableOnView()
 {
    var escortedById=document.getElementsByName('escortedEmpIDValue')[0].value;
    var escortedByName=document.getElementsByName('escortedEmpName')[0].value;
    
  
    
     escortedByIDArray=escortedById.split("@");
     escortedNameByArray=escortedByName.split("@");
  
	var nRow=0;
	var tableObj=document.getElementById('idEscortedBy');
	
	var numRows=tableObj.rows.length;
	//alert("escortedByIDArray on Modify"+ escortedByIDArray[0]);
	//alert("escortedByName on Modify"+    escortedNameByArray[0]);
	//alert("escortedByIDArray.length"+    escortedByIDArray.length);
 
 for(var i=0;i<escortedByIDArray.length;i++){
    // alert("i"+i);
 
  // alert("numRows"+numRows);
  
  
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id;
		//alert("tableObj.rows[numRows-1].id"+tableObj.rows[numRows-1].id)
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


    td1.innerHTML="<div  align='center'>"
	              +"<input type='hidden' name='escortedEmpIDArray' value='"+escortedByIDArray[i]+"' > "
	              +escortedNameByArray[i]+"</div>";
	td1.className='tdfont';
	td1.width='45%';
	td1.colspan="1";
	
	
	td2.className='tdfont';
	td2.colspan="1";
	td2.innerHTML="";
    td2.width='10%';
   
   
    td3.innerHTML="";
	td3.className='tdfont';
	td3.colspan="1";
    td3.width='45%';
   
	tabRow.appendChild(td1);  
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	
	 numRows++;
	}
	// document.forms[0].numberOfRow.value=numRows;
	}
 
 
 
 
 
