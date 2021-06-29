window.onload=function(){
	showFacilityDesc(document.getElementsByName('facilityDescType')[0])
	
}
function submitPage(hmode)
{
 //alert(hmode)
 if(hmode=="MODIFY" || hmode=="VIEW" || hmode=="DELETE"){
 	if(!validateSelection(hmode)) return
 }
 if(hmode=="DELETE"){
 	if(! window.confirm("Are you sure want to delete ?"))
 	return false;
 }
 document.getElementsByName('hmode')[0].value=hmode
 document.forms[0].submit();
}
function submitForm(hmode){
	var facilityDescType=document.getElementsByName('facilityDescType')[0].value
	var facilityDesc="";
	var facilityDescMultiple=document.getElementsByName('facilityDescMultiple')
	
	if(document.getElementsByName('facilityName')[0].value==""){
		alert("Please enter Facility Name");
		document.getElementsByName('facilityName')[0].focus();
		return false;
	}
	if(facilityDescType==2){
	    //alert(facilityDescMultiple.length)
		for(var i=0;i<facilityDescMultiple.length;i++){
			if(document.getElementsByName('facilityDescMultiple')[i].value==""){
				alert("Please enter Facility Desc");
				document.getElementsByName('facilityDescMultiple')[i].focus();
				return false;
			}
			else{	
				facilityDesc=facilityDesc + facilityDescMultiple[i].value + "#";
				if(facilityDesc.length>500){
					alert("Facility Description cannot be more than 500 characters")
					return false;
				}
				document.getElementsByName('facilityDesc')[0].value=facilityDesc;	
			}	
		}
	}
	else{
		if(document.getElementsByName('facilityDesc')[0].value==""){
		alert("Please enter Facility Desc");
		document.getElementsByName('facilityDesc')[0].focus();
		return false;
		}
	}
	if(document.getElementsByName('isValid')[0].value=="-1"){
		alert("Please select Active Status");
		document.getElementsByName('isValid')[0].focus();
		return false;
	}

	submitPage(hmode)
}

function showFacilityDesc(obj)
{
	//alert(obj.value)
	if(obj){
		 if(obj.value==1){
			document.getElementById('descArea').style.display="block"
			document.getElementById('descText').style.display="none"
			var tabObj=document.getElementById('facilityMasterAdd')
			
			for(var i=0;i<tabObj.rows.length;i++){
				tabObj.deleteRow(i);
			}	
			tabObj.deleteRow(tabObj.rows.length-1)
			document.getElementsByName('noOfRows')[0].value="0";
		 }	
		 else{
			document.getElementById('descArea').style.display="none"
			document.getElementById('descText').style.display="block"
		 }	
	 }
}

function setEmergencyServiceFlag(obj)
{
	 //alert(obj.checked)
	 if(obj.checked){
		document.getElementsByName('isEmergencyService')[0].value="1"
	 }	
	 else{
		document.getElementsByName('isEmergencyService')[0].value="0"
	 }	
}
function setSelectedFacilityId(obj)
{
	 //alert(obj.checked)
	 //if(obj.checked){
	 ///for(var i=0;i<selectedFaclityId.length)
		//document.getElementsByName('isEmergencyService')[0].value="1"
	// }	
	 	
}
function addFacilityDesc()
{
  var index=parseInt(document.getElementsByName('noOfRows')[0].value);
	var nRow=0;
	var tableObj=document.getElementById('facilityMasterAdd');
	var numRows=tableObj.rows.length;
	//alert(index)
	numRows=index;
	nRow=index
	//if(index==4){
		//alert("Cannot add more than 5 rows");
	//}
	//else{
		var tabRow=tableObj.insertRow(numRows);
		tabRow.id=parseInt(nRow)+1;
	
		var td1=document.createElement("TD");
		var td2=document.createElement("TD");
		
		td1.innerHTML="";
		td1.className='tdfonthead';																													
		td1.colspan="1";
		td1.width="50%";
		
		var imagePath="<img class=\"button\" src='/AHIMS/hisglobal/images/icn-min.png' tabindex='1' style='cursor: pointer' tabindex='1' onclick=\"removeFacilityDesc(" + (nRow) + ")\"> ";
		
		td2.innerHTML=" <input type='text' name='facilityDescMultiple' maxlength='99' size='25' tabindex='1' />&nbsp;"
			+ imagePath ;
		td2.className='tdfont';
		td2.colspan="1";
		td2.width="50%";
		
		tabRow.appendChild(td1);
		tabRow.appendChild(td2);
		document.getElementsByName('noOfRows')[0].value=numRows+1;
	//}
}
function removeFacilityDesc(rowid){
	//alert(rowid);
	var index=parseInt(document.getElementsByName('noOfRows')[0].value);
	var tableObj=document.getElementById('facilityMasterAdd');
	var len=tableObj.rows.length
	//alert("len "+ len)
	if(rowid >= len){
		rowid=len-1
	}
	tableObj.deleteRow(rowid);
	document.getElementsByName('noOfRows')[0].value=index-1;
}

function setFacilityId(obj){
	//alert(obj.value)
	if(obj.checked){
		document.getElementsByName('facilityId')[0].value=obj.value
	}
	//alert(document.getElementsByName('facilityId')[0].value)
}

function validateSelection(hmode){
	var count=0;
	var selectedId=document.getElementsByName('selectedFaclityId');
	//alert(document.getElementsByName('selectedFaclityId')[0].value)
	for(var i=0;i<selectedId.length;i++){
		if(selectedId[i].checked){
			count++;
		}
	}
	//alert(count)
	if(hmode=="DELETE"){
		if(count==0){
			alert("Please Select at Least One Record ");
			return false
		}
	}
	else{
		if(count>1 || count==0){
			alert("Please Select One Record For Modify or View");
			return false
		}
	}	
	 return true;
	 
}

function clearForm(){
	document.getElementsByName('facilityName')[0].value="";
	document.getElementsByName('facilityDesc')[0].value="";
	document.getElementsByName('facilityDescType')[0].value="1";
	document.getElementsByName('facilityDescMultiple')[0].value="";
	document.getElementsByName('isEmergencyService')[0].checked=false;
	document.getElementsByName('isLocationSpecific')[0].value="-1";
	document.getElementsByName('locationQuery')[0].value="";
	
	//alert(document.getElementsByName('facilityDescType'))
	showFacilityDesc(document.getElementsByName('facilityDescType')[0]);
}	
function getFacilityList(obj){
	submitPage('NEW');
}
function cancel(){
	document.getElementsByName('isValid')[0].value="1";
	submitPage('NEW');
}
