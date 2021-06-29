
function compareDateCallForPreviousDonation(d1,d2,mode,l1,l2){
 // alert("compare called    "+l1 +"      " +l2);
var valid=true;
// alert("di "+d1.value+" d2 "+d2.value)
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert("Donor Cannot Give Donation Before "+l1+" Days");
	valid = false;
	}
}

else
valid=false;

return valid;
}

function compareDateCallForCheckLess(d1,d2,mode,l1,l2,age){
//alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" Cannot Be Less Than "+age+" Years");
	valid = false;
	}
}

else
valid=false;

return valid;
}

function compareDateCallForCheckGreater(d1,d2,mode,l1,l2,age){
// alert("compare called    "+l1 +"      " +l2);
var valid=true;
if(isEmpty(d1,l1) && isEmpty(d2,l2)){
 if(compareDate(d1,d2, mode)){
		valid = true;
	}
 else {
	alert(l1+" Cannot Be Greater Than "+age+" Years");
	valid = false;
	}
}

else
valid=false;

return valid;
}

function validateDobAndAge()
{

	var flagLess=false;
	var flagGreater=false;
	var minAge=document.getElementsByName("minAge")[0].value;
	var maxAge=document.getElementsByName("maxAge")[0].value;
	
	if(document.getElementsByName("isActualDob")[0].checked==true)
	{	
		 var donorAge=parseInt(document.getElementsByName("donorAge")[0].value);
		if(donorAge<minAge)
		{
			alert("Age Cannot Be Less Than "+minAge+" Years :: 5");
			flagLess=false;
		}
		else
		{
			flagLess=true;
		}
		
		if(donorAge>maxAge)
		{
			alert("Age Cannot Be Greater Than "+maxAge+" Years");
			flagGreater=false;
		}
		else
		{
			flagGreater=true;
		}
	
	}
	else
	{
		var sysdate=document.getElementsByName("sysDate")[0];
		var donorDob=document.getElementsByName("donorDob")[0].value;
		// alert("donorDobDate "+donorDob);
		
		var value= new Array(3);
		value=donorDob.split('-');
		//alert(value);
		var datStr=value[1]+','+value[0]+','+value[2];
		var dateToCheckLess=value[0]+'-'+value[1]+'-'+(parseInt(value[2])+parseInt(minAge));
		document.getElementsByName("dobToCompare")[0].value=dateToCheckLess;
		// alert("dateToCheckLess "+dateToCheckLess);
		flagLess=compareDateCallForCheckLess(document.getElementsByName("dobToCompare")[0],sysdate,2,'Age','',minAge)
		var dateToCheckGreater=value[0]+'-'+value[1]+'-'+(parseInt(value[2])+parseInt(maxAge));
		document.getElementsByName("dobToCompare")[0].value=dateToCheckGreater;
	if(flagLess)
		flagGreater=compareDateCallForCheckGreater(sysdate,document.getElementsByName("dobToCompare")[0],2,'Age','',maxAge)
		document.getElementsByName("donorDob")[0].value=donorDob;
	}
	// 	alert("flagLess "+flagLess+" flagGreater "+flagGreater)
	return 	(flagLess && flagGreater);
}


function validateAgeOldDonor()
{

	var flagLess=false;
	var flagGreater=false;
	var minAgeArray=(document.getElementsByName("donorTypeMinAge")[0].value).split('-');
	var maxAgeArray=(document.getElementsByName("donorTypeMaxAge")[0].value).split('-');
	var dataFetchedForAutologous=document.getElementsByName("dataFetchedForAutologous")[0].value;
	var donorAutologousAge=document.getElementsByName("donorAutologousAge")[0].value;
	//alert("minAgeArray "+minAgeArray);
	//alert("maxAgeArray "+maxAgeArray);
	
	var index=(document.getElementsByName("donorTypeCode")[0]).selectedIndex;
	index=index-1;
	//alert("index "+index)
	var minAge=minAgeArray[index];
	var maxAge=maxAgeArray[index];
	// alert("is asctual dob "+document.getElementsByName("isActualDob")[0].checked)
	
	
		 var donorAge=parseInt(document.getElementsByName("donorAge")[0].value);
		if(donorAge<minAge)
		{
			alert("Age Cannot Be Less Than "+minAge+" Years ::6");
			flagLess=false;
		}
		else
		{
			flagLess=true;
		}
		
		if(donorAge>maxAge)
		{
			alert("Age Cannot Be Greater Than "+maxAge+" Years");
			flagGreater=false;
		}
		else
		{
			flagGreater=true;
		}
	
	
	// 	alert("flagLess "+flagLess+" flagGreater "+flagGreater)
	return 	(flagLess && flagGreater);
}


function checkAgeOrDob()
{
	if(document.getElementsByName("isActualDob")[0].checked){
	 return isEmpty(document.getElementsByName("donorAge")[0],"Age"); 
	 	 }
	 else{
	return isEmpty(document.getElementsByName("donorDob")[0],"Date of Birth");
	}
}




function isEmptyCheck(obj,name)
{

		if(obj!=null && obj!='undefined')
		{
			if(obj.value=="")
			{
				alert("Please Enter : "+name);
				obj.focus();
				return false;
				
			}
			else
			{
				return true;
			}
		}
}


function disableFieldForAlreadyRegisteredDonor(obj)
{
	//  alert("disableFieldForAlreadyRegisteredDonor "+obj.checked)
	if(obj.checked==true)
	{		 
		document.getElementsByName("donorFirstName")[0].disabled=true;
		document.getElementsByName("donorMiddleName")[0].disabled=true;
		document.getElementsByName("donorLastName")[0].disabled=true;
		document.getElementsByName("isActualDob")[0].disabled=true;
		document.getElementsByName("donorAgeUnit")[0].disabled=true;
		document.getElementsByName("donorAge")[0].disabled=true;
		document.getElementsByName("donorDob")[0].disabled=true;
		document.getElementsByName("donorGenderCode")[0].disabled=true;
		document.getElementsByName("donorCasteId")[0].disabled=true;
		document.getElementsByName("donorFatherName")[0].disabled=true;
		document.getElementsByName("donorMaritalStatusCode")[0].disabled=true;
		document.getElementsByName("donorSpouseName")[0].disabled=true;
		document.getElementsByName("donorNationality")[0].disabled=true;
		//document.getElementsByName("nationalID")[0].disabled=true;    
		document.getElementsByName("donorOccupationCode")[0].disabled=true;
		document.getElementsByName("donorMobileNo")[0].disabled=true;
		document.getElementsByName("donorEmailId")[0].disabled=true;
		document.getElementsByName("employeeStudentCode")[0].disabled=true;
		document.getElementsByName("designationClass")[0].disabled=true;
		document.getElementsByName("officeCollege")[0].disabled=true;
		document.getElementsByName("donorIdMark1")[0].disabled=true;
		document.getElementsByName("donorIdMark2")[0].disabled=true;
		document.getElementsByName("donorMonthlyIncome")[0].disabled=true;
		document.getElementsByName("donorReligionCode")[0].disabled=true;
		
			var dobDatePickerId=document.getElementById("divDob");
		//  alert("dobDatePickerId "+dobDatePickerId.innerHTML);
		 var value=document.getElementsByName("donorDob")[0].value;
		dobDatePickerId.innerHTML="<input type='text' style='width:114px' name='donorDob' id='patDOB' readonly=true; value='"+value+"' >";
			document.getElementsByName("donorDob")[0].disabled=true;
			
			
			
	}
	else{}
	
}

function validatePreviousDonation(genderFemaleCode,genderMaleCode)
{
 //alert("validatePreviousDonation");
	var valid=false;
	var donorTypeMatchFlag=false;
	if( (document.getElementsByName("lastDonationDate")[0].value!="") && (compareDateWithSysDate(document.getElementsByName("lastDonationDate")[0],"Last Donation Date")) )
	{
		if(document.getElementsByName("lastDonationTypeCode")[0].value!="")
		{		var typeCodeArray=(document.getElementsByName("donationTypeCodeArray")[0].value).split('-');
				var maleMinBlocks=(document.getElementsByName("donationTypeMaleMinBlocksArray")[0].value).split('-');
				var femaleMinBlocks=(document.getElementsByName("donationTypeFemaleMinBlocksArray")[0].value).split('-');
				var validateDays;
			 	var gender=document.getElementsByName("donorGenderCode")[0].value;
			 	var donationType=document.getElementsByName("lastDonationTypeCode")[0].value;
			 	var i=0;
			 	for(;i<typeCodeArray.length;i=i+1)
				{
					if(typeCodeArray[i]==donationType)
					{
					    donorTypeMatchFlag=true;
						if(gender==genderFemaleCode)
						{
							
							validateDays=femaleMinBlocks[i]
							break;
						}
						else
						{
							validateDays=maleMinBlocks[i]
							break;
						}
					}
				}
				
				if(!donorTypeMatchFlag)
				{
					validateDays="0";
				}
				
				// alert("validateDays "+validateDays);
				lastDonationDate=document.getElementsByName("lastDonationDate")[0].value;
				var value= new Array(3);
				value=lastDonationDate.split('-');
				// alert("month "+value[1]+" day "+value[0]+" year "+value[2])
				var dateArgument=value[1]+","+value[0]+","+value[2];
				var dateToValidate=new Date(dateArgument);
			//	 alert("dateToValidate "+dateToValidate);
				var intDate=parseInt(value[0])+parseInt(validateDays);
				dateToValidate.setDate(intDate);
				var dateValue=new Array(4);
				dateValue=dateToValidate.toDateString().split(' ');
				document.getElementsByName("validatePreviousDonationDate")[0].value=dateValue[2]+"-"+dateValue[1]+"-"+dateValue[3];
				var sysdate=document.getElementsByName("sysDate")[0];
			 //	alert("validatePreviousDonationDate "+document.getElementsByName("validatePreviousDonationDate")[0].value);
				valid=compareDateCallForPreviousDonation(document.getElementsByName("validatePreviousDonationDate")[0],sysdate,2,validateDays,'')
		}
		else
		{
			alert("Please Select Last Donation Type");
		}	
	}
	if(valid==false)
		document.getElementsByName("validatePreviousDonationDate")[0].focus();
// 	alert("valid "+valid)
	return valid;
}

////////////////////////////for unit selection on age and dob///////////////
function donorAgeSelection()
 
 {
	// alert("function 1 ");
	 if(document.getElementsByName("isActualDob")[0].checked){
	  //alert("function if ");
	 document.getElementById("divAge").style.display="";
	  document.getElementById("divDob").style.display="none";
	  document.getElementsByName("donorDob")[0].value="";
	  }
	else {
//	alert("else checked");
	  document.getElementsByName("donorAge")[0].value="";
	  document.getElementsByName("donorAgeUnit")[0].selectedIndex=0;
	  document.getElementById("divAge").style.display="none";
	  document.getElementById("divDob").style.display="block";
	 } 
 }

 ////////////////////////////////////////////////////////////////////////////////
 
//////////////////////////Set Combo Value To Empty If Value Is -1 ////////////////////////////////////
function setComboValueEmpty(obj)
{// alert("obllllfdf "+obj.value)
	if(obj.value=='-1')
	{// alert("value between setting"+obj.value)
		obj.value='dfdf';
	}
	//alert("value after setting"+obj.value)
}
//////////////////////////////////////////////////////////// 
/////////////////////////Show Requisition Field////////////
function  clearAllField()
{
		document.getElementsByName("donorFirstName")[0].value="";
		document.getElementsByName("donorMiddleName")[0].value="";
		document.getElementsByName("donorLastName")[0].value="";
		document.getElementsByName("isActualDob")[0].value="0";
		document.getElementsByName("isActualDob")[1].value="";
		document.getElementsByName("donorAgeUnit")[0].value="";
		document.getElementsByName("donorAge")[0].value="";
		document.getElementsByName("donorDob")[0].value="";
		document.getElementsByName("donorGenderCode")[0].value="-1";
		document.getElementsByName("donorFatherName")[0].value="";
		document.getElementsByName("donorMaritalStatusCode")[0].value="-1";
		document.getElementsByName("donorSpouseName")[0].value="";
		document.getElementsByName("donorAddress1")[0].value="";
		document.getElementsByName("donorAddress2")[0].value="";
		document.getElementsByName("donorPhoneNo")[0].value="";
		document.getElementsByName("initialBloodABO")[0].value="-1";
		document.getElementsByName("initialRH")[0].value="-1";
		var dobDatePickerId=document.getElementById("divDob");
		dobDatePickerId.innerHTML=document.getElementsByName("datePickerHtml")[0].value;
}
///////////////////////////////////////////////////////////

///////////////////////////////Donation Schedule Grid////////////////////////////////////
function deleteRow(Str)
	{	
      var tableObj=document.getElementById('scheduleTableId');
      var temp=Str;
      tableObj.deleteRow(temp.rowIndex);
      return true;
	}

function AddRowToTable()
{
	var nRow=0;
	var tableObj=document.getElementById('scheduleTableId');
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
	// alert("indexVolSpecific "+indexVolSpecific)

	var td1=document.createElement("TD");
	var td2=document.createElement("TD");
	var td3=document.createElement("TD");
	var td4=document.createElement("TD");
	
	var monthsComboStr=document.getElementsByName("monthsComboStr")[0].value;
	var daysComboStr=document.getElementsByName("daysComboStr")[0].value;

	td1.innerHTML="<div align='center'>"+"<select name='donationDay' tabindex='1'  >"+daysComboStr+"</select></div>";
	td1.className='tdfont';
	td1.colspan="1";
		
	td2.innerHTML="<div align='center'>"+"<select name='donationMonth' tabindex='1'  >"+monthsComboStr+"</select></div>";
	td2.className='tdfont';
	td2.colspan="1";
	
	
	td3.innerHTML="<div align='center'>"+"<textarea name='message' style='width: 255px;font-family: Verdana, Arial, Helvetica, sans-serif;	font-size: 12;height:30px;	color: #000000;	text-decoration: none;'	onkeypress='return validateTextArea(event,this,100)' ></textarea></div>";
	td3.className='tdfont';
	td3.colspan="1";
	
	td4.className='tdfont';
	td4.colspan="1";
	td4.innerHTML="<div align='center'><img src='/BLDHISInvestigationG5/hisglobal/images/minus.gif' onClick='deleteRow(document.getElementById(\""+(parseInt(nRow)+1)+"\"))'></div>";
   
   
	tabRow.appendChild(td1);
	tabRow.appendChild(td2);
	tabRow.appendChild(td3);
	tabRow.appendChild(td4);
	
	
	 document.forms[0].numberOfRow.value=numRows;
	
}

function deleteGenratedRow(obj)
{
	 var index=parseInt(obj.title)
	 var tRId="tr"+obj.title;
	//  alert("tr "+tRId)
	  
	 var trObj=document.getElementById(tRId);
	 // alert("trObj "+trObj)
	  var tableObj=document.getElementById('scheduleTableId');
	  tableObj.deleteRow(trObj.rowIndex);
	  
	// alert("TR index "+tRId.rowIndex)
	 //alert("index "+index)
	//  tRId.remove();
	// var tableObj=document.getElementById('scheduleTableId');
	// tableObj.deleteRow(index);
}
/////////////////////////////////////////////////////////////////

function showIsVoluntry(obj)
{
	if(obj.checked)
	{
		document.getElementById("voluntryId").style.display="";
	}
	else
	{
		document.getElementById("voluntryId").style.display="none";
	}
	
}

function showDonationSchedule(obj)
{
	if(obj.checked)
	{
		document.getElementById("donationScheduleId").style.display="";
	}
	else
	{
		document.getElementById("donationScheduleId").style.display="none";
	}
}
