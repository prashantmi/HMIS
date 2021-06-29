/*
 Patient Attendance JS

 author: Debashis Sardar

 dated: 01/02/2012
*/
function getServiceName(obj)
{
	_id("blkHrsId").innerHTML="";
	if(obj.value==0)
	{
	var objVal = document.getElementsByName("strServiceName")[0];
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' ><option value='0'>Select Value</option></select>";
	var objVal = document.getElementsByName("strStatusCombo")[0];
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strStatusCombo' class='comboNormal' ><option value='0'>Select Value</option></select>";
	
	_id("ID_HEADER").style.display="";
	_id("ID_CAN_CLR").style.display="";
	_id("ID_PAT_LIST_HEADER").style.display="none";
	var objVal=_id("ID_PAT_LIST");
	       objVal.innerHTML = "";
	
	}
	else
	{
	   _id("ID_HEADER").style.display="";
	   _id("ID_CAN_CLR").style.display="";
	   _id("ID_PAT_LIST_HEADER").style.display="none";
	   var objVal=_id("ID_PAT_LIST");
	       objVal.innerHTML = "";
	
	var serviceTypeId=obj.value;
	var url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
	url+='hmode=GETSERVICENAME';
	url+='&serviceTypeId=' + serviceTypeId;
	ajaxFunction(url,"1");
	}
}


function serviceNameChange(obj)
{
if(obj.value==0)
{
	document.forms[0].strStatusCombo.selectedIndex=0;
	_id("ID_HEADER").style.display="";
	_id("ID_CAN_CLR").style.display="";
	_id("ID_PAT_LIST_HEADER").style.display="none";
	var objVal=_id("ID_PAT_LIST");
	objVal.innerHTML = "";
}
else
{
    var obj=document.forms[0].strStatusCombo;
    getPatList(obj);
}
}


function getPatList(obj)
{
if(document.forms[0].strServiceName.value==0)
{
document.forms[0].strStatusCombo.selectedIndex=0;
alert("Please Select Service Name!");
return false;
}
else
{
    if(obj.value==0)
	{
	   _id("ID_HEADER").style.display="";
	   _id("ID_CAN_CLR").style.display="";
	   _id("ID_PAT_LIST_HEADER").style.display="none";
	   var objVal=_id("ID_PAT_LIST");
	   objVal.innerHTML = "";
	 }
	 else
	 {
	var serviceTypeId=document.forms[0].strServiceType.value;
	var strServiceName=document.forms[0].strServiceName.value;
	var status=obj.value;
	
	
	for(var j=1;j<=(strServiceName.split("^").length);j++)
		{
		strServiceName=	strServiceName.replace("^","@");
		}
	
	
	var url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
	url+='hmode=GETPATIENTLIST';
	url+='&serviceTypeId=' + serviceTypeId;
	url+='&strServiceName=' + strServiceName;
	url+='&status=' + status;
	
	ajaxFunction(url,"2");
	}
}
}



function accept()
{
var i;
var control=0;
var count=document.forms[0].strCount.value;
for(i=0; i<count;i++)
{
var id="strchk-"+i;
if(_id(id).checked==true)
 {
 control=control+1;
 }
 }
 if(control==0)
 {
 alert("Please select a patient for Acceptance !");
 return false ;
 }
 
 for(i=0; i<count;i++)
{

 if(_id('strchk-'+i).checked==true)
 {
 control=control-1;
 var chkVaL=_id('strchk-'+i).value;
 for(var j=0;j<=(chkVaL.length);j++)
	 chkVaL=chkVaL.replace("^","@");
 document.forms[0].strControl.value=control;
 url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
 	 		url+='hmode=ACCEPT';
 	 		url+='&chk=' + chkVaL;
 	 		 url+='&control=' + control;
	 		ajaxFunction(url,"3");	 
	 		
	 		
 }
 }
 
 
}



function reject()
{
var i;
var control=0;
var count=document.forms[0].strCount.value;
for(i=0; i<count;i++)
{
var id="strchk-"+i;
if(_id(id).checked==true)
 {
 control=control+1;
 }
 }
 if(control==0)
 {
 alert("Please select a patient for Rejection !");
 return false ;
 }
 
 for(i=0; i<count;i++)
{

 if(_id('strchk-'+i).checked==true)
 {
 control=control-1;
 document.forms[0].strControl.value=control;
 url='/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?';
 	 		url+='hmode=REJECT';
 	 		url+='&chk=' + _id('strchk-'+i).value;
 	 		 url+='&control=' + control;
	 		ajaxFunction(url,"6");	 
 }
 }
}



function transfer()
{
document.forms[0].strDepartment.disabled=false;
document.forms[0].strUnit.disabled=false;
document.forms[0].strWard.disabled=false;
document.forms[0].strRoom.disabled=false;
var i;
var control=0;
var count=document.forms[0].strCount.value;

checkBlkhrsOnSave();

for(i=0; i<count;i++)
{
var id="strchk-"+i;
if(_id(id).checked==true)
 {
 control=control+1;
 }
 }
 if(control==0)
 {
 alert("Please select a patient for Transfer !");
 return false ;
 }
 if(document.forms[0].strDepartment.value==0)
 {
 alert("Please Select Department !");
 return false;
 }
 if(document.forms[0].strUnit.value==0)
 {
 alert("Please Select Unit !");
 return false;
 }
 if(document.forms[0].strWard.value==0)
 {
 alert("Please Select Ward !");
 return false;
 }
 if(document.forms[0].strRoom.value==0)
 {
 alert("Please Select Room !");
 return false;
 }
 for(i=0; i<count;i++)
{

 if(_id('strchk-'+i).checked==true)
 {
	 		document.forms[0].strChk.value=_id('strchk-'+i).value;
	 		document.forms[0].hmode.value="TRANSFER";
            document.forms[0].submit();
 }
 }
        
}


function formClear()
{

document.forms[0].strAcceptedFlag.value=0;
	 document.forms[0].hmode.value="CLEAR";
     document.forms[0].submit();
}


function cancel()
{
 document.forms[0].hmode.value="CANCEL";
     document.forms[0].submit();
}


function fununit()
{ 
	if(document.forms[0].strDepartment.value==0)
	{
	 	 var objVal = _id("unitId");
	     objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='form-control' onChange='funward();'><option value='0'>Select Value</option></select>";
	 	 var objVal2 = _id("wardId");
	     objVal2.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='form-control' onChange='' ><option value='0'>Select Value</option></select>";
	}
	else
	{
		var mode="UNIT";
		var url="/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt"+"?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value;
		ajaxFunction(url,"4");
	}
}



function funward()
{
	if(document.forms[0].strUnit.value==0)
	{
	  var objVal = _id("wardId");
	  objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='form-control' onChange='funroom()' ><option value='0'>Select Value</option></select>";
	}
	else
     {	
		var mode="WARD";
		var temp=document.forms[0].strDepartment.value+"^"+document.forms[0].strUnit.value;
		var url="/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt"+"?hmode="+mode+"&modWard="+temp+'&ageCode='+document.forms[0].strAgeCode.value+'&sexCode='+ document.forms[0].strGenderCode.value+'&age='+document.forms[0].strAge.value+'&treatmentCategCode='+document.forms[0].strTreatmentCat.value+'&crNo='+document.forms[0].strCrNo.value;
	
		 ajaxFunction(url,"5");
	}
}


function funroom()
{
	if(document.forms[0].strWard.value==0)
	{
	  var objVal = _id("roomId");
	  objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='form-control'><option value='0'>Select Value</option></select>";
	}
	else
     {	
		var mode="ROOM";
		var temp=document.forms[0].strDepartment.value+"^"+document.forms[0].strUnit.value+"^"+document.forms[0].strWard.value;
		var url="/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt"+"?hmode="+mode+"&modWard="+temp+'&ageCode='+document.forms[0].strAgeCode.value+'&sexCode='+ document.forms[0].strGenderCode.value+'&age='+document.forms[0].strAge.value+'&treatmentCategCode='+document.forms[0].strTreatmentCat.value+'&crNo='+document.forms[0].strCrNo.value;
	
		 ajaxFunction(url,"7");
	}
}


function callchk(obj,index)
{
	
	

	if(document.forms[0].strsamewardchk.checked)
	{	
		var chk=obj;
		var id=chk.split("^")[3];
		document.forms[0].strCrNo.value=chk.split("^")[0];
		if((id!= "") ||(id != null) )
		{
			document.forms[0].strAge.value=id.split("$")[1];
			document.forms[0].strGenderCode.value=id.split("$")[0];
			document.forms[0].strAgeCode.value=id.split("$")[2];
			document.forms[0].strTreatmentCat.value=id.split("$")[3];
	    }
	    //alert(chk.split("^")[4]);
	   
	    var deptUnitWard=chk.split("^")[4];
	    var deptCode=deptUnitWard.split("$")[0];
	    var unitCode=deptUnitWard.split("$")[1];
	    var wardCode=deptUnitWard.split("$")[2];
	    var roomCode=deptUnitWard.split("$")[3];
	    document.getElementsByName("strBed")[0].value =deptUnitWard.split("$")[4];
	    document.forms[0].strDefaultUnit.value=unitCode;
	    document.forms[0].strDefaultWard.value=wardCode;
	    document.forms[0].strDefaultRoom.value=roomCode;
	    document.forms[0].strDepartment.value=deptCode;
	    document.forms[0].strDefaultUnitFlag.value=1;
	    fununit();
	   
	    var isVacant=chk.split("^")[5];
	   	document.forms[0].strIsVacant.value=isVacant;
	   
	  	if(document.forms[0].strIsVacant.value	==	"0")
	   	document.getElementsByName("strsamewardchk")[0].disabled="true";
	   
	   
   }
}


function view()
{

 document.forms[0].hmode.value="VIEW";
 document.forms[0].submit();

}



function getAjaxResponse(res,mode)
{
 //alert(mode);
 
 	if(mode=="1")
    {
       var serviceName=res.split("@")[0];
	   var status=res.split("@")[1];
	   var objVal = document.getElementsByName("strServiceName")[0];
	   if(serviceName=="" ||serviceName==null)
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' ><option value='0'>Select Value</option></select>";
	    else
	       objVal.innerHTML = "<select style='cursor:pointer' name = 'strServiceName' class='comboNormal' >"+serviceName+"</select>";
	   if(status==1)
	   {
	  		var objVal = document.getElementsByName("strStatusCombo")[0];
	        objVal.innerHTML = "<select style='cursor:pointer' name = 'strStatusCombo' class='comboNormal' ><option value='0'>Select Value</option><option value='1'>Pending</option><option value='2'>Accepted</option><option value='5'>To be Transfered</option></select>";
	   }
	   else
	   {
	 		var objVal = document.getElementsByName("strStatusCombo")[0];
	        objVal.innerHTML = "<select style='cursor:pointer' name = 'strStatusCombo' class='comboNormal' ><option value='0'>Select Value</option><option value='1'>Pending</option><option value='2'>Accepted</option></select>";
	   }
	   _id("ID_MSG").innerHTML="";
	 }
  if(mode=="2")
   {
	    _id("ID_HEADER").style.display="none";
	    _id("ID_CAN_CLR").style.display="none";
	   _id("ID_PAT_LIST_HEADER").style.display="block";
	   var objVal=_id("ID_PAT_LIST");
	       objVal.innerHTML = res;
	
  }
  if(mode=="3")
   {
	   if(res=="ERROR")
	   {
	   document.forms[0].strAcceptedFlag.value=3; // error
	  document.forms[0].hmode.value="CLEAR";
	  alert('1');
      document.forms[0].submit();
	   }
	 else if(res=="1")	
	 {
	 if(document.forms[0].strControl.value=="0")
	 {
	 document.forms[0].strAcceptedFlag.value=1; // accepted
	 document.forms[0].hmode.value="CLEAR";
	 alert('2');
	 document.forms[0].submit();
     }
	 }    
	 
  }
  if(mode=="4")
  {
    
      var objVal = _id("unitId");
      if(res=="" ||res==null)
       objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='form-control' onChange='funward();'><option value='0'>Select Value</option></select>";
      else
       objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='form-control' onChange='funward();'>" + res + "</select>";
      if(document.forms[0].strDefaultUnitFlag.value==1)
      {
        document.forms[0].strUnit.value=document.forms[0].strDefaultUnit.value;
        document.forms[0].strDefaultUnitFlag.value=0;
        document.forms[0].strDefaultWardFlag.value=1;
       	funward();
      }
   }
   if(mode=="5")
   {
   	  var objVal = _id("wardId");
      if(res=="" ||res==null)
       objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='form-control' onChange='funroom()' ><option value='0'>Select Value</option></select>";
      else
      objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='form-control' onChange='funroom()' >" + res + "</select>";
      if(document.forms[0].strDefaultWardFlag.value==1)
      {
       	document.forms[0].strWard.value=document.forms[0].strDefaultWard.value;
       	document.forms[0].strDefaultWardFlag.value=0;
    	document.forms[0].strDefaultRoomFlag.value=1;
    	funroom();
   }
  }
  if(mode=="6")
   {
	  if(res=="ERROR")
	   {
	  document.forms[0].strAcceptedFlag.value=3; // error
	  document.forms[0].hmode.value="CLEAR";
      document.forms[0].submit();
	   }
	 else if(res=="1")	
	 {
	 if(document.forms[0].strControl.value=="0")
	 {
	 document.forms[0].strAcceptedFlag.value=2; // rejected
	 document.forms[0].hmode.value="CLEAR";
     document.forms[0].submit();
     }
	 }    
	 
  }
  if(mode=="7")
  {
      var objVal = _id("roomId");
      if(res=="" ||res==null)
       	objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='form-control'><option value='0'>Select Value</option></select>";
      else
      	objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='form-control'>" + res + "</select>";
    	if(document.forms[0].strDefaultRoomFlag.value==1)
        {
	       document.forms[0].strRoom.value=document.forms[0].strDefaultRoom.value;
	       document.forms[0].strDefaultRoomFlag.value=0;
       		var isVacant= document.forms[0].strIsVacant.value;
        }
    	diableCheck();
  }
  
  
  
  if(mode=="8")
  {
		 var temp=res.split("-")[0];
		 var ntemp=res.split("^")[5];
		 var ind=ntemp.split("-")[1];
		 var ob=res.split("-")[1];
		 
	    	 if(temp.split("#")[0] >0 || temp.split("#")[1]>0)
	    		 {
	    		 	 _id("blkHrsId").style.display="";
	    		 	 if(temp.split("#")[0]>0)
	    		 		 _id("blkHrsId").innerHTML='<h3><font color="green">Blocked Bed Time Would Elapse in '+temp.split("#")[0]+' hrs '+temp.split("#")[1]+' mins</font></h3>';
	    		 	 else
	    		 		 _id("blkHrsId").innerHTML='<h3><font color="green">Blocked Bed Time Would Elapse in '+temp.split("#")[1]+' mins</font></h3>';

	    		 	 
	    
	    		 	if(ntemp.split("#")[1]==undefined)
	    		 		  _id("strchk-"+ind).value=ob+"#"+1;
	    		 	 else
	    		 		 _id("strchk-"+ind).value=_id("strchk-"+ind).value.split("#")[0]+"#"+1;	
					document.forms[0].strsamewardchk.checked =true;
	    		 	 		callchk(ob,ind);
	    		 }else{
	    			
	    		 	 if(ntemp.split("#")[1]==undefined)
	    		 		 _id("strchk-"+ind).value=ob+"#"+0;
	    		 	 else
	    		 		 _id("strchk-"+ind).value=_id("strchk-"+ind).value.split("#")[0]+"#"+0;	
	    		 	 
						document.forms[0].strsamewardchk.checked =false;
						callchk(res.split("-")[1],res.split("-")[2]);	
	    		 }

	  
  }
  if(mode=="9")
  {
	 var tottime=res.split("-")[0];
	 var ind=res.split("-")[2];
	 var hrs=tottime.split("#")[0];
	 var min=tottime.split("#")[1];
	 var timeFlg=_id("strchk-"+ind).value.split("#")[1];
	 
	 if(hrs>0 || min>0)
		 console.log("Bed Blk Time::::  "+hrs+":"+min);
	 else
		 {
		 if(timeFlg=="1")
			 {
			 	alert("Patient time exceeded,please refresh the page");
			 	return false;
			 }
		 	
		 }
  }
}


function callchk2()
{
     if(document.forms[0].strsamewardchk.checked)
     {
     	for(var i=0;i<document.getElementsByName("strchk").length;i++)
      	{
      		if(document.getElementsByName("strchk")[i].checked)
      		{
		      	var obj=document.getElementsByName("strchk")[i];
		      	var chk=obj.value;
				var id=chk.split("^")[3];
				document.forms[0].strCrNo.value=chk.split("^")[0];
				
				
				
				if((id!= "") ||(id != null) )
				{
					document.forms[0].strAge.value=id.split("$")[1];
					document.forms[0].strGenderCode.value=id.split("$")[0];
					document.forms[0].strAgeCode.value=id.split("$")[2];
					document.forms[0].strTreatmentCat.value=id.split("$")[3];
			    }
			    var deptUnitWard=chk.split("^")[4];
			    var deptCode=deptUnitWard.split("$")[0];
			    var unitCode=deptUnitWard.split("$")[1];
			    var wardCode=deptUnitWard.split("$")[2];
			    var roomCode=deptUnitWard.split("$")[3];
			    document.forms[0].strDefaultUnit.value=unitCode;
			    document.forms[0].strDefaultWard.value=wardCode;
			    document.forms[0].strDefaultRoom.value=roomCode;
			    document.forms[0].strDepartment.value=deptCode;
			    document.forms[0].strDefaultUnitFlag.value=1;
	    		
	    		fununit();
	   
	    		var isVacant=chk.split("^")[5];
	   			document.forms[0].strIsVacant.value=isVacant;
      		}
     	}
    }
    else
    {
    	
    	document.forms[0].strDepartment.disabled=false;
    	document.forms[0].strUnit.disabled=false;
    	document.forms[0].strWard.disabled=false;
    	document.forms[0].strRoom.disabled=false;
    	var objVal = _id("unitId");
       	objVal.innerHTML = "<select style='cursor:pointer' name = 'strUnit' class='form-control' onChange='funward();'><option value='0'>Select Value</option></select>";
       	var objVal = _id("wardId");
        objVal.innerHTML = "<select style='cursor:pointer' name = 'strWard' class='form-control' onChange='funroom()' ><option value='0'>Select Value</option></select>";
      	var objVal = _id("roomId");
       	objVal.innerHTML = "<select style='cursor:pointer' name = 'strRoom' class='form-control'><option value='0'>Select Value</option></select>";
      
    	document.forms[0].strDepartment.value="0";
    	document.forms[0].strUnit.value="0";
    	document.forms[0].strWard.value="0";
    	document.forms[0].strRoom.value=0;
    }
}


function getBlkHrs(ob,i)
{

	_id("blkHrsId").style.display="none";
	var obj=ob.value.split("#")[0];
	var obj1=ob.value.split("#")[1];
	if(obj1!=undefined)
		{
			if(obj1=="0"){
				
				document.forms[0].strsamewardchk.checked=false;
				_names("strsamewardchk")[0].disabled=false;
				callchk2();
			}
				
			else
				{
				document.forms[0].strsamewardchk.checked=true;
			//	_names("strsamewardchk")[0].readOnly=true;
				_names("strsamewardchk")[0].disabled=true;
				}
				
		}
	var hmode="GETBLKHRS"
	var url="/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?hmode="+hmode+"&obj="+obj+"&index="+i;
	ajaxFunction(url,"8");
	
	
}

function checkBlkhrsOnSave()
{
	var hmode="GETBLKHRS";
	var url;
	
	var robj=document.getElementsByName("strchk");
	for(var i=0;i<robj.length;i++)
		{
			if(robj[i].checked)
				{
					url="/HBIMS/ipd/transactions/PatientAttendanceCNT.cnt?hmode="+hmode+"&obj="+robj[i].value.split("#")[0]+"&index="+i;
					ajaxFunction(url,"9");
				}			
		}
}

function _id(str)
{return document.getElementById(str);}

function _names(str){return document.getElementsByName(str);}


function diableCheck(){
	
	var robj=document.getElementsByName("strchk");
	for(var i=0;i<robj.length;i++)
		{
			if(robj[i].checked)
				{
				
					var radiotemp=robj[i].value.split("^")[5];
					var chkFlg=radiotemp.split("#")[1];
					document.forms[0].strDepartment.disabled=false;
					if(chkFlg!=undefined && chkFlg=="1")
						{
							document.forms[0].strDepartment.disabled=true;
							document.forms[0].strUnit.disabled=true;
							document.forms[0].strWard.disabled=true;
							document.forms[0].strRoom.disabled=true;	
							document.forms[0].strsamewardchk.checked =true;
							document.forms[0].strsamewardchk.disabled =true;
						}else{
							document.forms[0].strsamewardchk.checked =false;
							document.forms[0].strsamewardchk.disabled =false;
						}
					}	
		}
}

$(document).ready(function() {
    $('#datatable').DataTable( {
        select: true
    } );
} );