function submitPage(mode)
{
	document.forms[0].hmode.value=mode;
	document.forms[0].submit();
}

/*function AddRowToTable()
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

		td1.innerHTML="<div align='right'><font color='RED' size='1' face='Verdana, Arial, Helvetica, sans-serif'> * </font> <font color='#000000' size='2' face='Verdana, Arial, Helvetica, sans-serif'> Test Parameter Value<bean:message key=TestParaValue />&nbsp; </font> </div>";
	  	td1.className='tdfont';
	  	td1.colspan="1";


	  	td2.innerHTML="<div align='left'>"+" <input type='text' name='testparaValue' id='testparaValue"+(parseInt(indexVolSpecific)+1)+"'  value='' maxlength='25' size='30' /></div>"
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
 */



/*function deleteRow(Str)
{	


	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);
	var tableObj=document.getElementById('tableComponentDetailId');
    var temp=Str;

    tableObj.deleteRow(temp.rowIndex);
    document.forms[0].numberOfRow.value=(numRows-1);

    return true;

}
 */

function deleteRow(Str)
{
	var numRows=parseInt(document.getElementsByName('numberOfRow')[0].value);

	var tableObj=document.getElementById('refValueTable');
	var temp=Str;
	tableObj.deleteRow(temp.rowIndex);
	  
	document.forms[0].numberOfRow.value=(numRows-1);
	
	return true;

}


function validateFinalSubmit(){

	// These All Fields are Mandatory

	
	if(document.getElementsByName("rangeTyp")[0].value=="-1")
	{
		alert("Select Range Type ");
		document.getElementsByName("rangeTyp")[0].focus();
		return false;                          
	}
	
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

	if(document.getElementsByName("numberOfRow")[0].value=="NaN" && document.getElementsByName("lowValueUom").length==0)
	{
		alert("Can't Remove All Records ");
		return false;    
	}

	
	
	var numRows=document.getElementsByName("lowValueUom").length;
	var i=0;
	for(i=0;i<numRows;i++)
	{	
		var fromValue=document.getElementsByName("lowValue")[0].value;
		var toValue=document.getElementsByName("highValue")[0].value;
		
		//alert(Math.floor(toValue));
		//alert(Math.floor(fromValue));

		if(toValue==Math.floor(toValue))
	     toValue=parseInt(toValue);
	
		if(fromValue==Math.floor(fromValue))
		fromValue=parseInt(fromValue);
		
		//	alert(fromValue);
		if(toValue<=fromValue)
		{
			alert("Range To Value Should Be Greater Than Range From Value");
		return false;
		
		}
		/*if(toValue>=125)
		{
			alert("Please Enter Valid Range To  Less Than 125");
		return false;
		
		}
		if(fromValue>=125 || fromValue<=0)
		{
			alert("Please Enter Valid Range From  Between 1 To 125");
		return false;
		
		}*/
		
		/**************************************Mandatory Value Check*********************************************************/
		
		/*Lab Values are Mandatory*/
		if(document.getElementsByName("criteriaCode")[0].value>="22" && document.getElementsByName("criteriaCode")[0].value<="31" && document.getElementsByName("labCode")[i].value=="-1")
		{
			alert("Select Lab");
			document.getElementsByName("labCode")[i].focus();
			return false;
		}
		
		
		/*Sample Values are Mandatory*/
		if(document.getElementsByName("criteriaCode")[0].value>="18" && document.getElementsByName("criteriaCode")[0].value<="27" && document.getElementsByName("sampleCode")[i].value=="-1")
		{
			alert("Select Sample");
			document.getElementsByName("sampleCode")[i].focus();
			return false;
		}

		
		/*Sample Values are Mandatory*/
		if(document.getElementsByName("criteriaCode")[0].value=="31" && document.getElementsByName("sampleCode")[i].value=="-1")
		{
			alert("Select Sample");
			document.getElementsByName("sampleCode")[i].focus();
			return false;
		}
		
		/*Gender Values are Mandatory*/
		if(document.getElementsByName("criteriaCode")[0].value=="29" || document.getElementsByName("criteriaCode")[0].value=="28" || document.getElementsByName("criteriaCode")[0].value=="27" || document.getElementsByName("criteriaCode")[0].value=="25" || document.getElementsByName("criteriaCode")[0].value=="21" || document.getElementsByName("criteriaCode")[0].value=="20" || document.getElementsByName("criteriaCode")[0].value=="17" || document.getElementsByName("criteriaCode")[0].value=="15" || document.getElementsByName("criteriaCode")[0].value=="13" || document.getElementsByName("criteriaCode")[0].value=="12")
			if(document.getElementsByName("gender")[i].value=="-1")
			{
				
			alert("Select Gender");
			document.getElementsByName("gender")[i].focus();
			return false;
			}
		
		/*Age Values are Mandatory*/
		if(document.getElementsByName("criteriaCode")[0].value=="29" || document.getElementsByName("criteriaCode")[0].value=="30" || document.getElementsByName("criteriaCode")[0].value=="31" || document.getElementsByName("criteriaCode")[0].value=="27" || document.getElementsByName("criteriaCode")[0].value=="24" || document.getElementsByName("criteriaCode")[0].value=="21" || document.getElementsByName("criteriaCode")[0].value=="19" || document.getElementsByName("criteriaCode")[0].value=="17" || document.getElementsByName("criteriaCode")[0].value=="16" || document.getElementsByName("criteriaCode")[0].value=="13" || document.getElementsByName("criteriaCode")[0].value=="11")
		{

			if(document.getElementsByName("lowAge")[i].value=="")
			{alert("Enter Low Age");
			document.getElementsByName("lowAge")[i].focus();
			return false;}

			if(document.getElementsByName("lowAgeUom")[i].value=="-1")
			{alert("Enter Low Age Uom");
			document.getElementsByName("lowAgeUom")[i].focus();
			return false;}

			if(document.getElementsByName("highAge")[i].value=="")
			{alert("Enter High Age");
			document.getElementsByName("highAge")[i].focus();
			return false;}

			if(document.getElementsByName("highAgeUom")[i].value=="-1")
			{alert("Enter High Age Uom");
			document.getElementsByName("highAgeUom")[i].focus();
			return false;}

		}
		
		
		/*Age Validation Check*/
		if(document.getElementsByName("criteriaCode")[0].value=="29" || document.getElementsByName("criteriaCode")[0].value=="30" || document.getElementsByName("criteriaCode")[0].value=="31" || document.getElementsByName("criteriaCode")[0].value=="27" || document.getElementsByName("criteriaCode")[0].value=="24" || document.getElementsByName("criteriaCode")[0].value=="21" || document.getElementsByName("criteriaCode")[0].value=="19" || document.getElementsByName("criteriaCode")[0].value=="17" || document.getElementsByName("criteriaCode")[0].value=="16" || document.getElementsByName("criteriaCode")[0].value=="13" || document.getElementsByName("criteriaCode")[0].value=="11")
		{
			var lowAgei=0,highAgei=0;
		
			/*converting age to years to check for redundant values*/
			
			//low age i value
			if(document.getElementsByName("lowAgeUom")[i].value=="1")
				lowAgei=document.getElementsByName("lowAge")[i].value;
			else if(document.getElementsByName("lowAgeUom")[i].value=="2")
				lowAgei=document.getElementsByName("lowAge")[i].value/12;
			else
				lowAgei=document.getElementsByName("lowAge")[i].value/365;
			
			if(document.getElementsByName("highAge")[i].value=="-1")
			{alert("Enter High Age");
			document.getElementsByName("highAge")[i].focus();
			return false;}
			else if(parseInt(document.getElementsByName("highAge")[i].value) >=parseInt("125"))
			{
				alert("Enter Valid Higher Age Less Than 125");
				return false;
			}
			
				
			//high age i value
			if(document.getElementsByName("highAgeUom")[i].value=="1")
				highAgei=document.getElementsByName("highAge")[i].value;
			else if(document.getElementsByName("highAgeUom")[i].value=="2")
				highAgei=document.getElementsByName("highAge")[i].value/12;
			else
				highAgei=document.getElementsByName("highAge")[i].value/365;
						

			lowAgei=parseFloat(lowAgei);
		
			highAgei=parseFloat(highAgei);
			
			
			
			if(lowAgei>=highAgei)
				{
				
				alert("Enter Valid Range. High Age should be greater than Low Age");
				document.getElementsByName("highAge")[i].focus();
				return false;
				
				}
		}
		
		
		/**************************************************Duplicate Values Check**********************************************************/
		
		
		/*Duplicate Lab Values Should not Exist*/
		if(document.getElementsByName("criteriaCode")[0].value=="22")
		{
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
				{	alert("Duplicate Lab Exists. Select different lab.");
					document.getElementsByName("labCode")[k].focus();
					return false;
				}
		}

		
	

		/*Duplicate Sample Values Should not Exist*/
		if((document.getElementsByName("criteriaCode")[0].value=="18") )
		{
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
				{	alert("Duplicate Sample Exists. Select different sample.");
					document.getElementsByName("sampleCode")[k].focus();
					return false;
				}
		}
		

	
		/*Sample Age Duplicate Check*/
		if((document.getElementsByName("criteriaCode")[0].value=="19") )
		{
			
			
				var k=0;
				for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
				{	
					var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
					
					/*converting age to years to check for redundant values*/
					
					//low age i value
					if(document.getElementsByName("lowAgeUom")[i].value=="1")
						lowAgei=document.getElementsByName("lowAge")[i].value;
					else if(document.getElementsByName("lowAgeUom")[i].value=="2")
						lowAgei=document.getElementsByName("lowAge")[i].value/12;
					else
						lowAgei=document.getElementsByName("lowAge")[i].value/365;
					
					//low age k value
					if(document.getElementsByName("lowAgeUom")[k].value=="1")
						lowAgek=document.getElementsByName("lowAge")[k].value;
					else if(document.getElementsByName("lowAgeUom")[k].value=="2")
						lowAgek=document.getElementsByName("lowAge")[k].value/12;
					else
						lowAgek=document.getElementsByName("lowAge")[k].value/365;
						
					//high age i value
					if(document.getElementsByName("highAgeUom")[i].value=="1")
						highAgei=document.getElementsByName("highAge")[i].value;
					else if(document.getElementsByName("highAgeUom")[i].value=="2")
						highAgei=document.getElementsByName("highAge")[i].value/12;
					else
						highAgei=document.getElementsByName("highAge")[i].value/365;
								

					//high age k value
					if(document.getElementsByName("highAgeUom")[k].value=="1")
						highAgek=document.getElementsByName("highAge")[k].value;
					else if(document.getElementsByName("highAgeUom")[k].value=="2")
						highAgek=document.getElementsByName("highAge")[k].value/12;
					else
						highAgek=document.getElementsByName("highAge")[k].value/365;
					
					lowAgei=parseFloat(lowAgei);
					lowAgek=parseFloat(lowAgek);
					highAgei=parseFloat(highAgei);
					highAgek=parseFloat(highAgek);
					
					if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
							|| (lowAgei<lowAgek && highAgei>highAgek )
							|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
					
					
					{	alert("Age range already mapped with the sample. Enter new range.");
						document.getElementsByName("lowAge")[k].focus();
						return false;
					}
					
				}
		}
		
		/*Sample Gender Duplicate Check*/
		if((document.getElementsByName("criteriaCode")[0].value=="20") )
		{
			
			
				var k=0;
				for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
				{	
					if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
					{	alert("Sample Gender value already exists. Select different Gender.");
						document.getElementsByName("gender")[k].focus();
						return false;
					}
					
				}
		}
		
		/*Sample Gender Duplicate Check*/
		if((document.getElementsByName("criteriaCode")[0].value=="21") )
		{
			
			
				var k=0;
				for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
				{	
					if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
					{	var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
					
					/*converting age to years to check for redundant values*/
					
					//low age i value
					if(document.getElementsByName("lowAgeUom")[i].value=="1")
						lowAgei=document.getElementsByName("lowAge")[i].value;
					else if(document.getElementsByName("lowAgeUom")[i].value=="2")
						lowAgei=document.getElementsByName("lowAge")[i].value/12;
					else
						lowAgei=document.getElementsByName("lowAge")[i].value/365;
					
					//low age k value
					if(document.getElementsByName("lowAgeUom")[k].value=="1")
						lowAgek=document.getElementsByName("lowAge")[k].value;
					else if(document.getElementsByName("lowAgeUom")[k].value=="2")
						lowAgek=document.getElementsByName("lowAge")[k].value/12;
					else
						lowAgek=document.getElementsByName("lowAge")[k].value/365;
						
					//high age i value
					if(document.getElementsByName("highAgeUom")[i].value=="1")
						highAgei=document.getElementsByName("highAge")[i].value;
					else if(document.getElementsByName("highAgeUom")[i].value=="2")
						highAgei=document.getElementsByName("highAge")[i].value/12;
					else
						highAgei=document.getElementsByName("highAge")[i].value/365;
								

					//high age k value
					if(document.getElementsByName("highAgeUom")[k].value=="1")
						highAgek=document.getElementsByName("highAge")[k].value;
					else if(document.getElementsByName("highAgeUom")[k].value=="2")
						highAgek=document.getElementsByName("highAge")[k].value/12;
					else
						highAgek=document.getElementsByName("highAge")[k].value/365;
					
					lowAgei=parseFloat(lowAgei);
					lowAgek=parseFloat(lowAgek);
					highAgei=parseFloat(highAgei);
					highAgek=parseFloat(highAgek);
					
					if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
							|| (lowAgei<lowAgek && highAgei>highAgek )
							|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
					
					
					{	alert("Sample Gender Age range Combination should be unique");
						document.getElementsByName("lowAge")[k].focus();
						return false;
					}
					}
					
				}
		}
		
		
		
		
		
		
		/*Lab Sample mapping validation*/
		if(document.getElementsByName("criteriaCode")[0].value=="23" || document.getElementsByName("criteriaCode")[0].value=="26")
			{
			
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
				{	
					if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
					{alert("Lab Sample Values Already Mapped. Select Unique Lab Sample Combination.");
					document.getElementsByName("sampleCode")[k].focus();
					return false;
					}
				}
			}
		
		/*LAB SAMPLE AGE RANGE UNIQUE COMBINATION*/
		if(document.getElementsByName("criteriaCode")[0].value=="24" || document.getElementsByName("criteriaCode")[0].value=="31")
		{
		
		var k=0;
		for(k=i+1;k<numRows;k++)
			if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
			{	
				if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
				{var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
				
				/*converting age to years to check for redundant values*/
				
				//low age i value
				if(document.getElementsByName("lowAgeUom")[i].value=="1")
					lowAgei=document.getElementsByName("lowAge")[i].value;
				else if(document.getElementsByName("lowAgeUom")[i].value=="2")
					lowAgei=document.getElementsByName("lowAge")[i].value/12;
				else
					lowAgei=document.getElementsByName("lowAge")[i].value/365;
				
				//low age k value
				if(document.getElementsByName("lowAgeUom")[k].value=="1")
					lowAgek=document.getElementsByName("lowAge")[k].value;
				else if(document.getElementsByName("lowAgeUom")[k].value=="2")
					lowAgek=document.getElementsByName("lowAge")[k].value/12;
				else
					lowAgek=document.getElementsByName("lowAge")[k].value/365;
					
				//high age i value
				if(document.getElementsByName("highAgeUom")[i].value=="1")
					highAgei=document.getElementsByName("highAge")[i].value;
				else if(document.getElementsByName("highAgeUom")[i].value=="2")
					highAgei=document.getElementsByName("highAge")[i].value/12;
				else
					highAgei=document.getElementsByName("highAge")[i].value/365;
							

				//high age k value
				if(document.getElementsByName("highAgeUom")[k].value=="1")
					highAgek=document.getElementsByName("highAge")[k].value;
				else if(document.getElementsByName("highAgeUom")[k].value=="2")
					highAgek=document.getElementsByName("highAge")[k].value/12;
				else
					highAgek=document.getElementsByName("highAge")[k].value/365;
				
				lowAgei=parseFloat(lowAgei);
				lowAgek=parseFloat(lowAgek);
				highAgei=parseFloat(highAgei);
				highAgek=parseFloat(highAgek);
				
				if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
						|| (lowAgei<lowAgek && highAgei>highAgek )
						|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
				
				
				{	alert(" Lab Sample Age range Combination should be unique");
					document.getElementsByName("lowAge")[k].focus();
					return false;
				}
				}
			}
		}
		
		
		
		/*Lab Sample Gender mapping validation*/
		if(document.getElementsByName("criteriaCode")[0].value=="25")
			{
			
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
				{	
					if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
					{if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
				{	alert("Lab Sample Gender combination should be unique");
					document.getElementsByName("gender")[k].focus();
					return false;
				}
					}
				}
			}
		
		
		/*Lab Sample Gender Age range mapping validation*/
		if(document.getElementsByName("criteriaCode")[0].value=="27")
			{
			
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
				{	
					if(document.getElementsByName("sampleCode")[i].value==document.getElementsByName("sampleCode")[k].value)
					{if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
				{	var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
				
				/*converting age to years to check for redundant values*/
				
				//low age i value
				if(document.getElementsByName("lowAgeUom")[i].value=="1")
					lowAgei=document.getElementsByName("lowAge")[i].value;
				else if(document.getElementsByName("lowAgeUom")[i].value=="2")
					lowAgei=document.getElementsByName("lowAge")[i].value/12;
				else
					lowAgei=document.getElementsByName("lowAge")[i].value/365;
				
				//low age k value
				if(document.getElementsByName("lowAgeUom")[k].value=="1")
					lowAgek=document.getElementsByName("lowAge")[k].value;
				else if(document.getElementsByName("lowAgeUom")[k].value=="2")
					lowAgek=document.getElementsByName("lowAge")[k].value/12;
				else
					lowAgek=document.getElementsByName("lowAge")[k].value/365;
					
				//high age i value
				if(document.getElementsByName("highAgeUom")[i].value=="1")
					highAgei=document.getElementsByName("highAge")[i].value;
				else if(document.getElementsByName("highAgeUom")[i].value=="2")
					highAgei=document.getElementsByName("highAge")[i].value/12;
				else
					highAgei=document.getElementsByName("highAge")[i].value/365;
							

				//high age k value
				if(document.getElementsByName("highAgeUom")[k].value=="1")
					highAgek=document.getElementsByName("highAge")[k].value;
				else if(document.getElementsByName("highAgeUom")[k].value=="2")
					highAgek=document.getElementsByName("highAge")[k].value/12;
				else
					highAgek=document.getElementsByName("highAge")[k].value/365;
				
				lowAgei=parseFloat(lowAgei);
				lowAgek=parseFloat(lowAgek);
				highAgei=parseFloat(highAgei);
				highAgek=parseFloat(highAgek);
				
				if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
						|| (lowAgei<lowAgek && highAgei>highAgek )
						|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
				
				
				{	alert(" Lab Sample Gender Age range Combination should be unique");
					document.getElementsByName("lowAge")[k].focus();
					return false;
				}
				}
					
				}
			}
			}
		

			/*Lab Gender mapping validation*/
			if(document.getElementsByName("criteriaCode")[0].value=="28")
				{
				
				var k=0;
				for(k=i+1;k<numRows;k++)
					if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
					{	
						if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
					{	alert("Lab Gender combination should be unique.");
						document.getElementsByName("gender")[k].focus();
						return false;
					}
						
					}
				}
			
			
			/*Lab Gender age range mapping validation*/
			if(document.getElementsByName("criteriaCode")[0].value=="29")
				{
				
				var k=0;
				for(k=i+1;k<numRows;k++)
					if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
					{	
						if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
					{	var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
					
					/*converting age to years to check for redundant values*/
					
					//low age i value
					if(document.getElementsByName("lowAgeUom")[i].value=="1")
						lowAgei=document.getElementsByName("lowAge")[i].value;
					else if(document.getElementsByName("lowAgeUom")[i].value=="2")
						lowAgei=document.getElementsByName("lowAge")[i].value/12;
					else
						lowAgei=document.getElementsByName("lowAge")[i].value/365;
					
					//low age k value
					if(document.getElementsByName("lowAgeUom")[k].value=="1")
						lowAgek=document.getElementsByName("lowAge")[k].value;
					else if(document.getElementsByName("lowAgeUom")[k].value=="2")
						lowAgek=document.getElementsByName("lowAge")[k].value/12;
					else
						lowAgek=document.getElementsByName("lowAge")[k].value/365;
						
					//high age i value
					if(document.getElementsByName("highAgeUom")[i].value=="1")
						highAgei=document.getElementsByName("highAge")[i].value;
					else if(document.getElementsByName("highAgeUom")[i].value=="2")
						highAgei=document.getElementsByName("highAge")[i].value/12;
					else
						highAgei=document.getElementsByName("highAge")[i].value/365;
								

					//high age k value
					if(document.getElementsByName("highAgeUom")[k].value=="1")
						highAgek=document.getElementsByName("highAge")[k].value;
					else if(document.getElementsByName("highAgeUom")[k].value=="2")
						highAgek=document.getElementsByName("highAge")[k].value/12;
					else
						highAgek=document.getElementsByName("highAge")[k].value/365;
					
					
					lowAgei=parseFloat(lowAgei);
					lowAgek=parseFloat(lowAgek);
					highAgei=parseFloat(highAgei);
					highAgek=parseFloat(highAgek);
					
					
					if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
							|| (lowAgei<lowAgek && highAgei>highAgek )
							|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
					
					
					{	alert(" Lab Gender Age range Combination should be unique");
						document.getElementsByName("lowAge")[k].focus();
						return false;
					}
					}
						
					}
				}
			
			
			/*Duplicate Lab Values and age range  Should not Exist*/
			if(document.getElementsByName("criteriaCode")[0].value=="30")
			{
				var k=0;
				for(k=i+1;k<numRows;k++)
					if(document.getElementsByName("labCode")[i].value==document.getElementsByName("labCode")[k].value)
					{	var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
					
					/*converting age to years to check for redundant values*/
					
					//low age i value
					if(document.getElementsByName("lowAgeUom")[i].value=="1")
						lowAgei=document.getElementsByName("lowAge")[i].value;
					else if(document.getElementsByName("lowAgeUom")[i].value=="2")
						lowAgei=document.getElementsByName("lowAge")[i].value/12;
					else
						lowAgei=document.getElementsByName("lowAge")[i].value/365;
					
					//low age k value
					if(document.getElementsByName("lowAgeUom")[k].value=="1")
						lowAgek=document.getElementsByName("lowAge")[k].value;
					else if(document.getElementsByName("lowAgeUom")[k].value=="2")
						lowAgek=document.getElementsByName("lowAge")[k].value/12;
					else
						lowAgek=document.getElementsByName("lowAge")[k].value/365;
						
					//high age i value
					if(document.getElementsByName("highAgeUom")[i].value=="1")
						highAgei=document.getElementsByName("highAge")[i].value;
					else if(document.getElementsByName("highAgeUom")[i].value=="2")
						highAgei=document.getElementsByName("highAge")[i].value/12;
					else
						highAgei=document.getElementsByName("highAge")[i].value/365;
								

					//high age k value
					if(document.getElementsByName("highAgeUom")[k].value=="1")
						highAgek=document.getElementsByName("highAge")[k].value;
					else if(document.getElementsByName("highAgeUom")[k].value=="2")
						highAgek=document.getElementsByName("highAge")[k].value/12;
					else
						highAgek=document.getElementsByName("highAge")[k].value/365;
					
				
					lowAgei=parseFloat(lowAgei);
					lowAgek=parseFloat(lowAgek);
					highAgei=parseFloat(highAgei);
					highAgek=parseFloat(highAgek);
					
					
					if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
							|| (lowAgei<lowAgek && highAgei>highAgek )
							|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
					
					
					{	alert(" Lab Sample Age range Combination should be unique");
						document.getElementsByName("lowAge")[k].focus();
						return false;
					}
					}
			}	
			

		/*Duplicate Gender Values SHould not Exist*/
		if(document.getElementsByName("criteriaCode")[0].value=="12" || document.getElementsByName("criteriaCode")[0].value=="15")
			{
		
			var k=0;
			for(k=i+1;k<numRows;k++)
				if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
				{	alert("Single entry for a gender is allowed");
					document.getElementsByName("gender")[k].focus();
					return false;
				}
                			
		}
		
		
		
		
		
		
		
		
		/*Duplicate Age ranges should not be present*/
		if(document.getElementsByName("criteriaCode")[0].value=="11" || document.getElementsByName("criteriaCode")[0].value=="16")
		{
		
		var k=0,l=0;
		for(k=i+1;k<numRows;k++,l++)
		{	
			var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
		
		/*converting age to years to check for redundant values*/
		
		//low age i value
		if(document.getElementsByName("lowAgeUom")[i].value=="1")
			lowAgei=document.getElementsByName("lowAge")[i].value;
		else if(document.getElementsByName("lowAgeUom")[i].value=="2")
			lowAgei=document.getElementsByName("lowAge")[i].value/12;
		else
			lowAgei=document.getElementsByName("lowAge")[i].value/365;
		
		//low age k value
		if(document.getElementsByName("lowAgeUom")[k].value=="1")
			lowAgek=document.getElementsByName("lowAge")[k].value;
		else if(document.getElementsByName("lowAgeUom")[k].value=="2")
			lowAgek=document.getElementsByName("lowAge")[k].value/12;
		else
			lowAgek=document.getElementsByName("lowAge")[k].value/365;
			
		//high age i value
		if(document.getElementsByName("highAgeUom")[i].value=="1")
			highAgei=document.getElementsByName("highAge")[i].value;
		else if(document.getElementsByName("highAgeUom")[i].value=="2")
			highAgei=document.getElementsByName("highAge")[i].value/12;
		else
			highAgei=document.getElementsByName("highAge")[i].value/365;
					

		//high age k value
		if(document.getElementsByName("highAgeUom")[k].value=="1")
			highAgek=document.getElementsByName("highAge")[k].value;
		else if(document.getElementsByName("highAgeUom")[k].value=="2")
			highAgek=document.getElementsByName("highAge")[k].value/12;
		else
			highAgek=document.getElementsByName("highAge")[k].value/365;
		
		//alert("lowage i: "+lowAgei+" lowage k: "+lowAgek+"  highage i: "+highAgei+"highAge k: "+highAgek);
		
		
		lowAgei=parseFloat(lowAgei);
		lowAgek=parseFloat(lowAgek);
		highAgei=parseFloat(highAgei);
		highAgek=parseFloat(highAgek);
		
		
		if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
				|| (lowAgei<lowAgek && highAgei>highAgek )
				|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
			
			{	alert("Select new age range to avoid ambiguity");
				document.getElementsByName("lowAge")[k].focus();
				return false;
			}
		
		}
		
		
		
		}
		
		
		/*Single Gender Age range mapping only*/
		if(document.getElementsByName("criteriaCode")[0].value=="13" || document.getElementsByName("criteriaCode")[0].value=="17")
		{
	
		var k=0;
		for(k=i+1;k<numRows;k++)
			if(document.getElementsByName("gender")[i].value==document.getElementsByName("gender")[k].value)
			{	
				var lowAgei=0,highAgei=0,lowAgek=0,highAgek=0;
				
				/*converting age to years to check for redundant values*/
				
				//low age i value
				if(document.getElementsByName("lowAgeUom")[i].value=="1")
					lowAgei=document.getElementsByName("lowAge")[i].value;
				else if(document.getElementsByName("lowAgeUom")[i].value=="2")
					lowAgei=document.getElementsByName("lowAge")[i].value/12;
				else
					lowAgei=document.getElementsByName("lowAge")[i].value/365;
				
				//low age k value
				if(document.getElementsByName("lowAgeUom")[k].value=="1")
					lowAgek=document.getElementsByName("lowAge")[k].value;
				else if(document.getElementsByName("lowAgeUom")[k].value=="2")
					lowAgek=document.getElementsByName("lowAge")[k].value/12;
				else
					lowAgek=document.getElementsByName("lowAge")[k].value/365;
					
				//high age i value
				if(document.getElementsByName("highAgeUom")[i].value=="1")
					highAgei=document.getElementsByName("highAge")[i].value;
				else if(document.getElementsByName("highAgeUom")[i].value=="2")
					highAgei=document.getElementsByName("highAge")[i].value/12;
				else
					highAgei=document.getElementsByName("highAge")[i].value/365;
							

				//high age k value
				if(document.getElementsByName("highAgeUom")[k].value=="1")
					highAgek=document.getElementsByName("highAge")[k].value;
				else if(document.getElementsByName("highAgeUom")[k].value=="2")
					highAgek=document.getElementsByName("highAge")[k].value/12;
				else
					highAgek=document.getElementsByName("highAge")[k].value/365;
				
				
				lowAgei=parseFloat(lowAgei);
				lowAgek=parseFloat(lowAgek);
				highAgei=parseFloat(highAgei);
				highAgek=parseFloat(highAgek);
				
				
				if((lowAgei>=lowAgek && highAgei>=highAgek && lowAgei<=highAgek)
						|| (lowAgei<lowAgek && highAgei>highAgek )
						|| (lowAgei<=lowAgek && highAgei<=highAgek && highAgei>=lowAgek))
					
			
			{	alert("Select new age range for the gender to avoid ambiguity");
				document.getElementsByName("lowAge")[k].focus();
				return false;
			}
				
			}
		
		
		
		}
		
		
		
		
		
		
		
		
		
		
		
		
		/********************************************Ref ranges are mandatory********************************************************/
		
		if(document.getElementsByName("lowValue")[i].value=="")
		{alert("Enter Low Value");
		document.getElementsByName("lowValue")[i].focus();
		return false;}

		if(document.getElementsByName("lowValueUom")[i].value=="")
		{alert("Enter Low Value Uom");
		document.getElementsByName("lowValueUom")[i].focus();
		return false;}

		if(document.getElementsByName("highValue")[i].value=="")
		{alert("Enter High Value");
		document.getElementsByName("highValue")[i].focus();
		return false;}

		if(document.getElementsByName("highValueUom")[i].value=="")
		{alert("Enter High Value Uom");
		document.getElementsByName("highValueUom")[i].focus();
		return false;}



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
