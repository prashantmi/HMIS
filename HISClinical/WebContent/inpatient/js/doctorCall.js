function doPagination(e,p)
{
	document.getElementsByName('currentPage')[0].value=p;
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}


function togglePeon(chkBox){

	if(chkBox.checked){
	  document.getElementById("showPeon").style.display="block";
	}
	else{
	    document.getElementById("showPeon").style.display="none";
	
	}
}
function hideFirst()
{
	//alert('hi');
	var t= document.getElementById('deptUnitComoboId');
	//alert(t);
	var z=t.options[t.selectedIndex].text;
	//alert(z);
	if(z=="All")
		{
		//alert("bye");
		
		$('#consultantId option[value=0]').hide();
		$('#consultantId').val('');
		
		}
	

}
function closeWindow()
{
window.close();
}

 

function togglePhone(chkBox){

	if(chkBox.checked){
	  document.getElementById("showPhone").style.display="block";
	}
	else{
	    document.getElementById("showPhone").style.display="none";
	
	}
}

function validate()
{

if(document.forms[0].unitCode && document.forms[0].unitCode.value=="-1")
	{
		alert("Please Select Unit");
		document.forms[0].unitCode.focus();
		return false;                          
	}

if(document.forms[0].empNo && document.forms[0].empNo.value=="-1")
	{
		alert("Please Select Consultant");
		document.forms[0].empNo.focus();
		return false;                          
	}
if(document.forms[0].callRemarks.value=="")
	{
		alert("Please Enter Call Remarks");
		document.forms[0].callRemarks.focus();
		return false;                          
	}
if(document.getElementsByName('isDocCallByPeon')[0].checked)
	{
	if(document.forms[0].peonEmpNo.value=="-1")
	{
		alert("Please Select Peon");
		document.forms[0].peonEmpNo.focus();
		return false;                          
	}
	}
	return true;
}

function finalSubmit(mode)
{
	if (!validate()) return;
	submitForm(mode);
	
}

function validateModifySelection()
{
	var count=0;
	for (var i=0;i<document.getElementsByName("selectedPatCrNo").length;i++)
	{
		if(document.getElementsByName("selectedPatCrNo")[i].checked==true)
		{
			count++;
		}
	}
	
	if(count<1)
	{
		alert("Please Select a Record To Modify")
		return false;
	}
	else
	{
		return true;
	}
}

function clearForm()
 {

  
   document.getElementsByName('callRemarks')[0].value="";
   document.getElementsByName('docCallByPeonRemarks')[0].value="";
   document.getElementsByName('docCallByPhoneRemarks')[0].value="";
   document.getElementsByName('empNo')[0].value="-1";
   document.getElementsByName('peonEmpNo')[0].value="-1";
   document.getElementsByName('callPriority')[0].value="1";   
   document.getElementsByName('docCallByPeonStatus')[0].checked=false;
   document.getElementsByName('isDocCallByPeon')[0].checked=false;
   document.getElementById("showPeon").style.display="none";
   document.getElementsByName('docCallByPhoneStatus')[0].checked=false;
   document.getElementsByName('isDocCallbyPhone')[0].checked=false;
  document.getElementById("showPhone").style.display="none";
 }
 
 function clearModifyForm()
 {

	if(!document.getElementsByName("callRemarks")[0].readOnly)
		document.getElementsByName('callRemarks')[0].value="";
  	
  	if(!document.getElementsByName("docCallByPeonRemarks")[0].readOnly)	
		document.getElementsByName('docCallByPeonRemarks')[0].value="";
   
   	if(!document.getElementsByName("docCallByPhoneRemarks")[0].readOnly)
		document.getElementsByName('docCallByPhoneRemarks')[0].value="";
   
   	if(!document.getElementsByName("docCallByPeonStatus")[0].disabled)  
	{
		document.getElementsByName('docCallByPeonStatus')[0].checked=false;
		document.getElementsByName('docCallByPeonStatus')[1].checked=false;
		
	}	
  
	if(!document.getElementsByName("isDocCallByPeon")[0].disabled)  {
		document.getElementsByName('isDocCallByPeon')[0].checked=false;
		  document.getElementById("showPeon").style.display="none";
		  }
		
   	
   	if(!document.getElementsByName("docCallByPhoneStatus")[0].disabled)  
	{
		document.getElementsByName('docCallByPhoneStatus')[0].checked=false;
		document.getElementsByName('docCallByPhoneStatus')[1].checked=false;
	}
	document.getElementsByName('docCallByPhoneStatus')[0].checked=false;
   	
   	if(!document.getElementsByName("isDocCallbyPhone")[0].disabled) {  
		document.getElementsByName('isDocCallbyPhone')[0].checked=false;
		 document.getElementById("showPhone").style.display="none";
		 } 
 }
 
 window.onload= function()
 {
 	if(document.getElementsByName("hmode")[0].value=="MODIFY"){
 	if(document.getElementsByName("callRemarks")[0].value!="")
 	{
 		var ack= "<%=InpatientConfig.CALL_ACKNOLWEDGED %>";
 			//document.getElementsByName('sortBy')[0].value='<%=Config.SORT_TYPE_ASC %>'
 		if(document.getElementsByName("status")[0].value==ack)
 		document.getElementsByName("callRemarks")[0].readOnly=true;
 		else
 			document.getElementsByName("callRemarks")[0].readOnly=false;
 	}
 	
 	if(document.getElementsByName("isDocCallByPeon")[0].checked)
 	{
 		document.getElementsByName("isDocCallByPeon")[0].disabled=true;
 	}
 	
 	if(document.getElementsByName("isDocCallbyPhone")[0].checked)
 	{
 		document.getElementsByName("isDocCallbyPhone")[0].disabled=true;
 	}
 	
 	if(document.getElementsByName("docCallByPeonRemarks")[0].value!="")
 	{
 		document.getElementsByName("docCallByPeonRemarks")[0].readOnly=true;
 	}
 	
 	if(document.getElementsByName("docCallByPhoneRemarks")[0].value!="")
 	{
 		document.getElementsByName("docCallByPhoneRemarks")[0].readOnly=true;
 	}
 	
 	if(document.getElementsByName("docCallByPeonStatus")[0].checked || document.getElementsByName("docCallByPeonStatus")[1].checked)
 	{
 		document.getElementsByName("docCallByPeonStatus")[0].disabled=true;
 		document.getElementsByName("docCallByPeonStatus")[1].disabled=true;
 	}
 	
 	if(document.getElementsByName("docCallByPhoneStatus")[0].checked || document.getElementsByName("docCallByPhoneStatus")[1].checked)
 	{
 		document.getElementsByName("docCallByPhoneStatus")[0].disabled=true;
 		document.getElementsByName("docCallByPhoneStatus")[1].disabled=true;
 	}
 	}
 }
 
 function getRemarks(index)
 {
 document.getElementsByName("index")[0].value=index;
 submitForm('GETCALLREMARKS');
 } 
 
function openPop(height, width)
{
 var url=createFHashAjaxQuery("/HISClinical/inpatient/doctorCallBook.cnt?hmode=DOCTORCALLBOOKLOGREPORT"); 
 
   	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height='+height+',width='+width+',left=10,top=10');  
  	child.moveTo(250,250);
 	child.focus(); 

if(!child.opener)
   child.opener = self;
 
 return child
}
function addProgressNotes(event)
{
	var path='/HISClinical/inpatient/doctorCallBook.cnt?hmode=ADDNOTES&processId=7';
	openPopup(createFHashAjaxQuery(path),event,300,600);
}
