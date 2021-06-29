function validate1()
{     
         selectListRecords("strRightItemIds");
         var hisValidator = new HISValidator("programmeItemMapBean");
      
            hisValidator.addValidation("strProgrammeName", "dontselect=0","Please Select Programme Name");  
			hisValidator.addValidation("strItemCat", "dontselect=0","Please Select Category");
			hisValidator.addValidation("strExGroupId", "dontselect=0","Please Select Group Name");
			hisValidator.addValidation("strEffectiveFrom", "req", "Effective from is a Mandatory Field" );
			hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" ); 
			var retVal = hisValidator.validate();
          	if(retVal)
          	{
          		 var count = selectListRecords("strRightItemIds");
          		
          		//alert("count"+count);
        		 if(count==0)
        		 {
        		 	alert("Please Select Items in Right List");
        		 	return false;
        		 }        		 
        		 document.forms[0].hmode.value = "SAVE";
                 document.forms[0].submit();            	 
			}
			else
            {
				return false;
			}
}


 function cancel()
 {
 		document.forms[0].strReportChk.checked = false;
 	 	document.forms[0].hmode.value = "ADD";
	 	//showMenuFrame();
  	    document.forms[0].submit();
 }
 

/*function cancel(mode)
{

	document.forms[0].hmode.value=mode;
	//showMenuFrame();
	document.forms[0].submit();
}*/

function getAjaxResponse(res,mode)
{
		var objVal;
		var objVal2;
		
	    if(mode=='1')
	    {   
				objVal= document.getElementById("LeftItemIds");
				objVal.innerHTML = "<select name='strLeftItemIds' size='6' multiple style='width: 280px'></select>";
				objVal= document.getElementById("RightItemIds");
				objVal.innerHTML = "<select name='strRightItemIds' size='6' multiple style='width: 280px'></select>";
				var err = document.getElementById("errMsg");
		 		var temp1 = res.split("####");
	       		if(temp1[0] == "ERROR")
	       		{
         			err.innerHTML = temp1[1];
        		}
				else
				{
					objVal= document.getElementById("exGrp");
					objVal.innerHTML = "<select name='strExGroupId' class='comboNormal' onchange='getNextCombo(2,this)'>"+res+"</select>";				
					
				}
		}
		if(mode=='2')
		{   				
				//alert(res);
				var err = document.getElementById("errMsg");
		 		var temp1 = res.split("####");
        		if(temp1[0] == "ERROR")
        		{
         			err.innerHTML = temp1[1];	
        		}
				else
				{
					objVal= document.getElementById("LeftItemIds");
					objVal2 = document.getElementById("RightItemIds");
					objVal.innerHTML = "<select name='strLeftItemIds' size='6' multiple style='width: 280px' >"+res.split('^')[0]+"</select>";
					objVal2.innerHTML = "<select name='strRightItemIds' size='6' multiple style='width: 280px' >"+res.split('^')[1]+"</select>";
					//getNextCombo(3,document.forms[0].strExGroupId);
				}
		 }
		 
		  if(mode=='3')
	    {   
				
		 		var temp1 = res.split("####");
	       		if(temp1[0] == "ERROR")
	       		{
         			err.innerHTML = temp1[1];
        		}
				else
				{
					objVal= document.getElementById("exGrp");
					objVal.innerHTML = "<select name='strExGroupId' class='comboNormal' onChange = 'getProgrammeReport(this)'>"+res+"</select>";				
					
				}
		}
		
		
		 if(mode=='4')
		{   				
				
				var err = document.getElementById("errMsg");
		 		var temp1 = res.split("####");
        		if(temp1[0] == "ERROR")
        		{
         			err.innerHTML = temp1[1];	
        		}
				else
				{
					objVal= document.getElementById("programmeItemListDivId");					
					objVal.innerHTML = res;				
				
				}
		 }
		 
		 if(mode=='5')
		{   				
				alert(res);
				var err = document.getElementById("errMsg");
		 		var temp1 = res.split("####");
        		if(temp1[0] == "ERROR")
        		{
         			err.innerHTML = temp1[1];	
        		}
				else
				{
					objVal= document.getElementById("effDt");
					objVal2 = document.getElementById("remarkId");
					document.forms[0].strEffectiveFrom=res.split('^')[0];
					/*alert(objVal.innerHTML);
					objVal.innerHTML = "<dateTag:date name='strEffectiveFrom' value='"+res.split('^')[0]+"'></dateTag:date>";*/
					objVal2.innerHTML = "<textarea name='strRemarks' cols='25' rows='2'>"+res.split('^')[1]+"</textarea>";
				
				}
		 }
		
}

function getProgrammeReport(obj)
{
	var url;	
	
	//Get Programmes
	{    
		var strProgrammeId = document.forms[0].strProgrammeName.value;
		var strItemCat = document.forms[0].strItemCat.value;
	     url="ProgrammeItemMapMstCNT.cnt?hmode=PROGRAMMEREPORT&strExGroupId="+obj.value+"&strProgrammeId="+strProgrammeId+"&strItemCat="+strItemCat; 
		 ajaxFunction(url,"4");		
		 //alert(url);
	}
	
}

function getGroupCombo(obj)
{
	var url;
	var mode = '';   
	
	
		 url="ProgrammeItemMapMstCNT.cnt?hmode=EXISTGROUP&strItemCat="+obj.value; 
		 ajaxFunction(url,"3");
	
	
}



function getNextCombo(flag,obj)
{
	var url;
	var mode = '';   
	if(flag == '1')//Get Existing Group
	{
		 url="ProgrammeItemMapMstCNT.cnt?hmode=EXISTGROUP&strItemCat="+obj.value; 
		 ajaxFunction(url,"1");
	}
	if(flag == '2') //Get Programme Item Map
	{    
	     var strProgrammeId = document.forms[0].strProgrammeName.value;
		 url="ProgrammeItemMapMstCNT.cnt?hmode=EXISTINGITEMS&strExGroupId="+obj.value+"&strProgrammeId="+strProgrammeId; 
		 ajaxFunction(url,"2");
	}
	if(flag == '3') //Get Other Detail
	{    
		alert("combo value::"+obj.value);
	     var strProgrammeId = document.forms[0].strProgrammeName.value;
		 url="ProgrammeItemMapMstCNT.cnt?hmode=getOtherDetails&strExGroupId="+obj.value+"&strProgrammeId="+strProgrammeId; 
		 ajaxFunction(url,"5");
	}
	
}
function searchInList()
{ 

	var valObj = document.forms[0].searchVal;

	if(valObj.value.length == 0 ){
	
		alert("Please Enter a Value to Search from List");
		valObj.focus();
		return false;
	}
	
	searchInListBox("strLeftItemIds",document.forms[0].searchVal.value);  
}
function LeftListTransfer()
{
	var ob1=document.forms[0].strLeftItemIds.value;
	var ob=document.getElementById("LeftItemIds");
	shiftToRight("strLeftItemIds","strRightItemIds",1);
}

function transferToReportPage()
{
	if (document.getElementsByName("strReportChk")[0].checked) 
	{
		document.forms[0].hmode.value = "REPORTPAGE";
		document.forms[0].submit();
	}
	else
	{
		document.forms[0].hmode.value = "unspecified";
		document.forms[0].submit();
	}
}
