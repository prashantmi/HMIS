function showHelpDetails()
{
var helpid= document.getElementById("HelpId");
var minusid=document.getElementById("minusHelpId");
var plusid=document.getElementById("plusHelpId");
helpid.style.display='block';
minusid.style.display='block';
plusid.style.display='none';
}

function hideHelpDetails()
{
var minusid=document.getElementById("minusHelpId");
var plusid=document.getElementById("plusHelpId");
var helpid= document.getElementById("HelpId");
helpid.style.display='none';
minusid.style.display='none';
plusid.style.display='block';
}
/*
function Test()
{
 ("Test");
}


function IpdMSRecordStatus(obj)
{
 
 

  comValue = obj.value;
  com = obj.id;
 

// alert("Selected Combo Value -->"+comValue);

// alert("Combo Id-->"+obj.id);

  var len = com.length;
  var ch = com.charAt(len-1);
   alert( "comValue ====="+ comValue );

  if(comValue==1)
  {
  
  alert("inside 1");
  
   document.getElementById("acceptId").style.color = "blue";
   document.getElementById("modifyId").style.color = "blue";
   document.getElementById("lamaId").style.color = "blue";
   document.getElementById("transferId").style.color = "red";
   document.getElementById("investigationId").style.color = "red";
   document.getElementById("pacId").style.color = "red";
   document.getElementById("medeqpId").style.color = "red";
   document.getElementById("bloodbankId").style.color = "red";
   document.getElementById("doctorvisitId").style.color = "red";
   document.getElementById("dietsheetId").style.color = "red";
   document.getElementById("refopdId").style.color = "red";
   
  }
   if(comValue==2)
  {
  
  alert("inside 2");
   document.getElementById("acceptId").style.color = "red";
   document.getElementById("modifyId").style.color = "red";
   document.getElementById("lamaId").style.color = "red";
   document.getElementById("transferId").style.color ="blue";
   document.getElementById("investigationId").style.color ="blue";
   document.getElementById("pacId").style.color = "blue";
   document.getElementById("medeqpId").style.color = "blue";
   document.getElementById("bloodbankId").style.color = "blue";
   document.getElementById("doctorvisitId").style.color = "blue";
   document.getElementById("dietsheetId").style.color = "blue";
   document.getElementById("refopdId").style.color = "blue";
  }
    
  
}


function IpdMscheckColor(mode,display)
{

if( mode == "ACCEPT" && document.getElementById("acceptId").style.color == "blue")
{
add(mode);
}

if(mode == "MODIFY" && document.getElementById("modifyId").style.color == "blue")
{

 add(mode);
}
if( mode == "LAMA" && document.getElementById("lamaId").style.color == "blue")
{

 add(mode);
} 

if( mode == "TRANSFER" && document.getElementById("transferId").style.color == "blue")
{

 add(mode);
} 
if(mode == "INVESTIGATION" && document.getElementById("pacId").style.color == "blue")
{ 
 add(mode);
}
if(mode == "PACLIST" && document.getElementById("medeqpId").style.color == "blue")
{ 

 add(mode);
 
}

if(mode == "MEDEQP" && document.getElementById("bloodbankId").style.color == "blue")
{ 

 add(mode);
}
if(mode == "BLOODBANK" && document.getElementById("doctorvisitId").style.color == "blue")
{ 

 add(mode);
}
if(mode == "DOCTORVISIT" && document.getElementById("dietsheetId").style.color == "blue")
{ 

 add(mode);
}
if(mode == "DIETSHEET" && document.getElementById("refopdId").style.color == "blue")
{ 

 add(mode);
}

}
*/


function showSharableBed(obj,index)
{
	
	var conf=confirm("You are going to select Sharable Occupied Bed. Do you want to continue ?");
	if(conf==true)
	{
	
	    document.getElementsByName("strIsSharableFlag")[0].value="1";
		var wardCode =document.forms[0].strWard.value;
		var deptUnitCode =document.forms[0].strUnit.value;
		var roomCode =document.forms[0].strRoom.value;
		var minusid=document.getElementById("minusId-"+index);
		var plusid=document.getElementById("plusId-"+index);
		document.forms[0].index.value=index;
		minusid.style.display='block';
		plusid.style.display='none';
		var mode="BED";
		var strFlag=0;
	    var url="NursingDeskTransCNT.cnt?hmode="+mode+"&wardCode="+wardCode+"&deptUnitCode="+deptUnitCode+"&roomCode="+roomCode+"&strFlag="+strFlag;
	    ajaxFunction(url,"111");
	
	    
	}

}

function hideSharableBed(obj,index)
{
	    document.getElementsByName("strIsSharableFlag")[0].value="0";
		var wardCode =document.forms[0].strWard.value;
		var deptUnitCode =document.forms[0].strUnit.value;
		var roomCode =document.forms[0].strRoom.value;
		var minusid=document.getElementById("minusId-"+index);
		var plusid=document.getElementById("plusId-"+index);
		document.forms[0].index.value=index;	
		minusid.style.display='none';
		plusid.style.display='block';
		var mode="BEDCOMBO";
		var strFlag=1;
		var url="NursingDeskTransCNT.cnt?hmode="+mode+"&wardCode="+wardCode+"&deptUnitCode="+deptUnitCode+"&roomCode="+roomCode+"&strFlag="+strFlag;
		ajaxFunction(url,"111");

}


function cancel(mode)
{
if(mode=="cancel"){

  document.forms[0].hmode.value = "cancel";
  document.forms[0].submit();
 
  }
  if(mode=="CLEAR"){

  document.forms[0].hmode.value = "clear";
  document.forms[0].submit();
 
  }
}

function reset()
{
	document.forms[0].strDepartment.selectedIndex='0';
}
function openPopup(mode)
{
	if(mode=="ARTICLE")
	{
		var url='NursingDeskTransCNT.cnt?hmode=ARTICLE'
		var featuresList = "width=600,height=380,ALIGN=CENTER,left=100,top=100,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList)
	}
	//alert(mode);
	if(mode=="BEDSTATUS")
	{
		var temp=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^0^"+document.forms[0].strUnit.value+"^"+document.forms[0].strchk.value;
		/*var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+temp;
		alert("url=="+url);
		var featuresList = "width=600,height=200,ALIGN=CENTER,left=100,top=100,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList)*/
		bedStatusChart(temp);
	}
	if(mode=="CHECKLIST")
	{
		//alert("deptUnit->"+document.forms[0].strUnit.value);
		var url="NursingDeskTransCNT.cnt?hmode=CHECKLIST&deptunit="+document.forms[0].strUnit.value;
		var featuresList = "width=600,height=200,ALIGN=CENTER,left=100,top=100,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList)	
	}
}



function openPopupIPD(mode)
{	
	if(mode=="ARTICLE")
	{
		var url='NursingDeskTransCNT.cnt?hmode=ARTICLE';
		var featuresList = "width=600,height=380,ALIGN=CENTER,left=100,top=100,scrollbars=yes";
		myWindow = window.open(url,'popupWindow',featuresList);
	}
	if(mode=="BEDSTATUS")
	{
		var temp=document.forms[0].strWard.value+"^"+document.forms[0].strRoom.value+"^0^"+document.forms[0].strUnit.value+"^"+document.forms[0].strchk.value;
		/*var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+temp;
		var featuresList = "width=600,height=200,ALIGN=CENTER,left=100,top=100,scrollbars=yes"
		myWindow = window.open(url,'popupWindow',featuresList)*/
		bedStatusChartIPD(temp);
	}
	if(mode=="CHECKLIST")
	{
		var url="NursingDeskTransCNT.cnt?hmode=CHECKLIST&deptunit="+document.forms[0].strUnit.value;
		var featuresList = "width=600,height=200,ALIGN=CENTER,left=100,top=100,scrollbars=yes";
		myWindow = window.open(url,'popupWindow',featuresList);
	}
}
function bedStatusChart(WrdRoomBedtypUnt_code)
{
	
	$('.modal-container').css('display','block');
	
	var mode="BEDSTATUS1";
	//alert('1');
	var url="IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	$('#modalSpaceId').load(createFHashAjaxQuery(url));
	
	//openPopUp(url,"400","200","1");
	//openModalPopUp(url,"600","400","1");
}

function fununit(mode)
{ 
  
  if(mode=="UNIT")
  {
   var mode="UNIT";
// alert("dept =="+document.forms[0].strDepartment.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value;
  // alert("url=>"+url);
   ajaxFunction(url,"1");
  }
}



function bedStatusChartIPD(WrdRoomBedtypUnt_code)
{
	

	$('.modal-container').css('display','block');
	
	var mode="BEDSTATUS1";
	//alert('1');
	var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	$('#modalSpaceId').load(createFHashAjaxQuery(url));
	
	/*var mode="BEDSTATUS";
	var url="/HBIMS/ipd/transactions/IpdCNT.cnt?hmode="+mode+"&modPopUp="+WrdRoomBedtypUnt_code;
	openPopUp(createFHashAjaxQuery(url),"400","200","1");
	*/
}

function fununit(mode)
{ 
  
  if(mode=="UNIT")
  {
   var mode="UNIT";
// alert("dept =="+document.forms[0].strDepartment.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value;
  // alert("url=>"+url);
   ajaxFunction(url,"1");
  }
}



function funward(mode)
{ 


  if(mode=="WARD")
  {
   var mode="WARD";
 //alert("WARD =="+document.forms[0].strUnit.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modUnit="+document.forms[0].strUnit.value;
  // alert("url=>"+url);
   ajaxFunction(url,"2");
  }
}




function funroom(mode)
{ 


  if(mode=="ROOM")
  {
   var mode="ROOM";
 //alert("WARD =="+document.forms[0].strWard.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modWard="+document.forms[0].strWard.value;
  // alert("url=>"+url);
   ajaxFunction(url,"3");
  }
}



function funrecord(mode)
{ 


  if(mode=="RECORD")
  {
   var mode="RECORD";
 // alert("WARD =="+document.forms[0].strWard.value);
   
   
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value+"&modUnit="+document.forms[0].strUnit.value+"&modWard="+document.forms[0].strWard.value+"&modRoom="+document.forms[0].strRoom.value
  
   ajaxFunction(url,"4");
  }
}


var gblflag = false;

function openPopupajax(mode,index)
{ 
//gblparent = parent;

  if(mode=="CHECKLIST")
  {
    if(gblflag== false){
     if(document.getElementById("strchk"+index).checked==true){
  
     var mode="CHECKLIST";
     var url="NursingDeskTransCNT.cnt?hmode="+mode+"&deptunit="+document.forms[0].strUnit.value;
     ajaxFunction(url,"5");
    }
    else{
        alert("Popup will be open only for selected record!");
    }
  }  
   else{
   
    if(document.getElementById("strchk"+index).checked==true){
     document.getElementById("divChecklist").style.display="block";
     
     }else{
     
     alert("Popup will be open only for selected record!");
     
     }
      
   
     }
   
  }
}


function getAjaxResponse(res,mode)
{
  
  if(mode=="1")
  {
   	var temp = res.split("####");
  	if(temp[0]=="ERROR")
  	{	
  		var errDiv = document.getElementById("errMsg");
  		errDiv.innerHTML = temp[1]; 
  	}
  	else{
    var objVal = document.getElementById("unitId");
   
    objVal.innerHTML = "<select name = 'strUnit' onChange=\"funward('WARD');setRoom();\" >" + res + "</select>";
   
    var objVal1 = document.getElementById("wardId");
    objVal1.innerHTML = "<select name = 'strWard' onChange=\"funroom('ROOM');\" ><option value='0'>Select Value</option></select>";
   
 	  var objVal2 = document.getElementById("roomId");
   objVal2.innerHTML = "<select name = 'strRoom' onChange=\"funrecord('RECORD');radioIndx();\" ><option value='0'>Select Value</option></select>";
 	
 	/* var objVal3 = document.getElementById("admitdetail");
    objVal3.innerHTML ="" ;
    objVal3.style.display="block";*/
    }
 	
 
  }
  
  if(mode=="2")
  {
   
  var temp = res.split("####");
  	if(temp[0]=="ERROR")
  	{	
  		var errDiv = document.getElementById("errMsg");
  		errDiv.innerHTML = temp[1]; 
  	}
  	else{
    var objVal = document.getElementById("wardId");
    objVal.innerHTML = "<select name = 'strWard' onChange=\"funroom('ROOM');\" >" +res + "</select>";
  
   var objVal2 = document.getElementById("roomId");
   objVal2.innerHTML = "<select name = 'strRoom' onChange=\"funrecord('RECORD');radioIndx();\" ><option value='0'>Select Value</option></select>";
 	
 	/* var objVal3 = document.getElementById("admitdetail");
    objVal3.innerHTML ="" ;
    objVal3.style.display="block";*/
    }
 
  }
  
  
  if(mode=="3")
  {
   
 var temp = res.split("####");
  	if(temp[0]=="ERROR")
  	{	
  		var errDiv = document.getElementById("errMsg");
  		errDiv.innerHTML = temp[1]; 
  	}
  	else{
    var objVal = document.getElementById("roomId");
   objVal.innerHTML = "<select name = 'strRoom' onChange=\"funrecord('RECORD');radioIndx();\" >" + res + "</select>";
          
           var objVal3 = document.getElementById("admitdetail");
    objVal3.innerHTML ="" ;
    objVal3.style.display="block";
    }
 
  }
  
  if(mode=="4")
  {
  
    var temp = res.split("####");
  	if(temp[0]=="ERROR")
  	{	
  		var errDiv = document.getElementById("errMsg");
  		errDiv.innerHTML = temp[1]; 
  	}
  	else{
    var objVal = document.getElementById("admitdetail");
    objVal.innerHTML = res ;
    }
  }
  
  if(mode=="5")
  {
   
    var temp = res.split("####");
  	if(temp[0]=="ERROR")
  	{	
  		var errDiv = document.getElementById("errMsg");
  		errDiv.innerHTML = temp[1]; 
  	}
  	else{
     var objVal = document.getElementById("divChecklist");
     objVal.innerHTML = res ;
     gblflag = true;
     document.getElementById("divChecklist").style.display="block";
    }
  }
    if(mode=="6")
  	{ 
  		var i=document.getElementById("idvalue").value;
	    var objVal = document.getElementById("divbed"+i);
	    objVal.innerHTML ="<select name='strbed' class='comboMin' title='_div_popup_cntl' id='strbed"+i+"' >"+ res+ "</select>";
	    getConsultantValue(i);
  	}  
    if(mode=="7")
  	{ 
    //	alert(res);
  		var i=document.getElementById("idvalue").value;
	    var objVal = document.getElementById("consultantDiv"+i);
	    objVal.innerHTML ="<select name='strConsultantCode' class='comboMin' title='_div_popup_cntl' id='strConsultantCodeId"+i+"' >"+ res+ "</select>";
  	} 

   if(mode=="111")
   {
   		var temp = res.split("####");
  		if(temp[0]=="ERROR")
  		{	
  			var errDiv = document.getElementById("errMsg");
  			errDiv.innerHTML = temp[1]; 
  		}
  	    else
  	    {
  	    
  			var id=document.forms[0].index.value;
  			var objVal = document.getElementById("strbed"+id);
   			objVal.innerHTML = res;
   			var l=objVal.length;
    		if(l=="2")
      		objVal.selectedIndex=l-1;
    	}
 
  	}
  	
  	
  	
  		
}
function funbed(obj,id1,id2,id3)
{
	var i=document.getElementById("idvalue").value;
	//i=i-2;
	//alert(i);
	var shr_chk=document.getElementById("sharableChkid"+i).value;
	var bedDivId=id2;
	var bedId=id3;
	var mode="BEDCOMBO";
	var deptCode=document.forms[0].strDepartment.value;
	var deptUnitCode=document.forms[0].strUnit.value;
	var wardCode=document.forms[0].strWard.value;	
	var ob=document.getElementsByName("strchk");
	for(var i=0;i<ob.length;i++)
	{
	 	if(ob[i].checked)
	 	{
		 	if(ob[i].value.split('^')[2]!='0')
		 	{
		 		document.forms[0].strRoom.value=ob[i].value.split('^')[2];
		 	}
	 	}
	}
	var roomCode=document.forms[0].strRoom.value;
	if(deptUnitCode!='0') 
	{
		var url="NursingDeskTransCNT.cnt?hmode="+mode+"&deptCode="+deptCode+"&deptUnitCode="+deptUnitCode+"&wardCode="+wardCode+"&roomCode="+roomCode+"&previousBedCode="+document.forms[0].strPreviousOccupiedbed.value+"&shr_chk="+shr_chk;
		//alert(url);
		ajaxFunction(url,"6");
	}
	else
	{
		var objVal = document.getElementById(bedDivId);
	    objVal.innerHTML ="<select name='strbed' class='comboMin' title='_div_popup_cntl' id='"+bedId+"'><option value='0'>Select Value</option></select>";
	}
	
} 


function funbedIPD(obj,id1,id2,id3)
{
	bedDivId=id2;
	bedId=id3;
	var mode="BEDCOMBO";
	var i=document.getElementById("idvalue").value;
	var deptCode=document.forms[0].strDepartment.value;
	var shr_chk=document.getElementById("sharableChkid"+i).value;
	var deptUnitCode=document.forms[0].strUnit.value;
	var wardCode=document.forms[0].strWard.value;	
	var ob=document.getElementsByName("strchk");
	var wardDblOcuup=document.getElementById("wardDblOcuup");
	
	for(var i=0;i<ob.length;i++)
	{
	 	if(ob[i].checked)
	 	{
		 	if(ob[i].value.split('^')[2]!='0')
		 	{
		 		document.forms[0].strRoom.value=ob[i].value.split('^')[2];
		 	}
	 	}
	}
	var roomCode=document.forms[0].strRoom.value;
	
	
	if(deptUnitCode!='0') 
	{
		var url="/HBIMS/ipd/transactions/NursingDeskTransCNT.cnt?hmode="+mode+"&deptCode="+deptCode+"&deptUnitCode="+deptUnitCode+"&wardCode="+wardCode+"&roomCode="+roomCode+"&previousBedCode="+document.forms[0].strPreviousOccupiedbed.value+"&shr_chk="+shr_chk;
		if(wardDblOcuup!=undefined )
			{
			//alert(wardDblOcuup.value);
					if( wardDblOcuup.value !="2")
							ajaxFunction(url,"6");
					else
						document.getElementById("normalMsg").innerHTML="<h3><font color='green'>BED IS ALREADY BLOCKED</h3></strong>";
			}else
				ajaxFunction(url,"6");
	}/*else
	{
		var objVal = document.getElementById(bedDivId);
	    objVal.innerHTML ="<select name='strbed' class='comboMin' title='_div_popup_cntl' id='"+bedId+"'><option value='0'>Select Value</option></select>";
	}*/
} 

function addArticleRows(obj,e) 
{
           if(e.keyCode == 13){
                 if(obj.value > 20){
                   alert("Please Enter No. of Rows Less than 20");
                             

                        }
                  addRows(new Array('strArticleName','strQuantity'),new Array('t','t'),'1',obj.value,'R');
					obj.value = ""
                   }
                  return false;
 }
  
function beforedisplayBelogingPopup(parent,index){
     
   var wardCode =document.forms[0].strWard.value;
   var deptUnitCode =document.forms[0].strUnit.value;
   var bedTypeCode ="0";
   var roomCode =document.forms[0].strRoom.value;
  // alert("wardCode="+wardCode);
 //  alert("deptUnitCode="+deptUnitCode);
  // alert("roomCode="+roomCode);
   
     
if(document.getElementById("strchk"+index).checked==false)
{
alert("Popup will be open only for selected record!");

}else{
 displayBelogingPopup(parent);

}
}   

 
   
   
  /* alert("inside beforedisplaybelonging");
   var ob=document.getElementsByName("strbed");
    for(var i=0;i<ob.length;i++){
 
  alert("obj.length=="+ob.length);
 
 
  alert("i="+i);
  alert("ob[i].value="+ob[i].value);
  
   if(ob[i].disabled==true){
   alert("inside if");
   alert("Can not openpopup");
   
   }
  else{
       alert("else");
       alert(" openpopup");
     
   //displayBelogingPopup(parent);
 
  }
 
  }
}  */
  
  
   function  displayBelogingPopup(parent){
 display_popup_menu(parent,'divBelonging','100','180');
  }

// function called on cancel button on belonging pop-up
function delRow(){
	var delRow=parseInt(document.getElementById("savedRowId").value)+1;
	if(parseInt(document.getElementsByName("rowIndex1")[0].value)!=1){
		for(var i=delRow;i<=parseInt(document.getElementsByName("rowIndex1")[0].value);i++){
			document.getElementById("id1").removeChild(document.getElementById("id1-"+i));
			deleteRow("1-"+i,'1','0');
		}
		document.getElementsByName("rowIndex1")[0].value=document.getElementById("savedRowId").value;
	}
	
	// alert("length"+document.getElementsByName('rowIndex1')[0].value);
	if(document.getElementsByName('rowIndex1')[0].value=='0')
	addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t','t'),'1','1','R');
	
	document.getElementById("divBelonging").style.display="none";
	
	
} 

// not in use
function cancelBelongingPopUp()
{
var delRowsNo = document.getElementById("delRowsNo").value;
//alert("delRowsNo="+delRowsNo);
var MultiRowMode = document.getElementsByName("strMultiRowMode");
var count=0;
var flag =true;
var MultiRowlength = MultiRowMode.length-1;
//MultiRowlength = MultiRowlength + parseInt(delRowsNo);
//alert("MultiRowlength-"+MultiRowlength);
// alert("MultiRowlength-"+MultiRowlength);
for(i=MultiRowlength;i>0;i--)
{
	 // alert("MultiRowMode[i]."+i+"value-"+MultiRowMode[i-1].value);
	if(MultiRowMode[i-1].value=="")
	{
	deleteRow("1-"+i,'1','0');
	document.getElementsByName("rowIndex1")[0].value=document.getElementsByName("rowIndex1")[0].value-1;
	count++;
	// alert("inside delete");
	}
	if(MultiRowMode[i-1].value == 1)
	{
	flag=false;
	// alert("flag false");
	 break;
	 }
}
document.getElementById("delRowsNo").value = parseInt(document.getElementById("delRowsNo").value) + count;
var NowMultiRowMode = document.getElementsByName("strMultiRowMode");
var lengthAfterDelete = NowMultiRowMode-1;
//alert("lengthAfterDelete"+lengthAfterDelete);
//if(lengthAfterDelete=='NaN')                                                 
//addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t'),'1','1','R');
//document.getElementById("divBelonging").style.display="none";
}

// function called on cross sign on belonging pop-up
function closebelonging(){

document.getElementById("divBelonging").style.display="none";
}

// function called on ok button on belonging pop-up
function OkBelonging(){
var Name = document.getElementsByName("strArticleName");
var Qty = document.getElementsByName("strQuantity");
var MultiRowMode = document.getElementsByName("strMultiRowMode");
var MultiRowlength = MultiRowMode.length-1;
var flag =0;

for(i=0;i<MultiRowlength;i++)
{

	if(Name[i].value=="" || Qty[i].value=="")
		flag=1;
	else
		MultiRowMode[i].value=1;
	
	
	for(j=i+1;j<MultiRowlength;j++)
	{
		if(Name[i].value == Name[j].value)
		{
		flag=2;
		break;
		}
	}
}
if(flag==1)
		alert("Name and Quantity cannot be blank");
else if(flag==2)
	alert("Name cannot be same in two rows");
else
document.getElementById("divBelonging").style.display="none";

//alert("rowIndex="+document.getElementsByName("rowIndex1")[0].value);
document.getElementById("savedRowId").value=document.getElementsByName("rowIndex1")[0].value;
}



function closeCheckList(){
var retValue=true;
var chklist = document.getElementsByName("strchklistCode");
var flag=0;

for(i=0;i<chklist.length;i++)
{
	if(chklist[i].checked==true )
	{
		flag=1;
	}
	if(chklist[i].checked)
	{
		if(document.getElementsByName("strChecklistRemark")[i].value=="")
		{
			alert("Please Enter Value");
			document.getElementsByName("strChecklistRemark")[i].focus();
			return;
		}
	}
}
if(flag==0)
{
alert("Atleast one record should be selected");
retValue= false;
}
if(retValue)
document.getElementById("divChecklist").style.display="none";

}

function cancelCheckList(){


var chklist = document.getElementsByName("strchklistCode");
var chklistRemarks = document.getElementsByName("strChecklistRemark");

for(i=0;i<chklist.length;i++)
{
chklist[i].checked=false;
chklistRemarks[i].value="";
chklistRemarks[i].disabled=true;
}
 document.getElementById("divChecklist").style.display="none";

}


function validate1(mode)
{	
	 var hisValidator = new HISValidator("nursingDeskBean");
	 hisValidator.addValidation("strUnit", "dontselect=0","Please Select A Unit");
	 var retVal = hisValidator.validate(); 
	          
	if(retVal)
	{
		if(document.getElementsByName("strUnit")[0].value == "0")
		{
		   alert("Please Select Unit");	
		}
		var flg=0;
		
		var ob=document.getElementsByName("strchk");
		var ob1=document.getElementsByName("hstrcrno");
		var ob2=document.getElementsByName("hstradmno");
		var ob3 = document.getElementsByName("strbed");
		var ob4 = document.getElementsByName("hstrchklistCode");
		var ob5 = document.getElementsByName("strChecklistRemark");
		var ob6  = document.getElementsByName("strArticleName");
		var ob7  = document.getElementsByName("strQuantity");
		var ob8   = document.getElementsByName("strBelongingRemark");
		var ob9 = document.getElementsByName("strchklistCode");
		var ob10 = document.getElementsByName("strhtransinflg");
		var ob11 = document.getElementsByName("strhmno");
		var ob12 = document.getElementsByName("strhtransinflg");
		
		
		for(var i=0;i<ob.length;i++)
		{
			 if(ob[i].checked)
			 {
				 flg=i;
				 document.forms[0].strhmoveno.value=ob11[i].value;
				 if(ob[i].value.split('^')[2]!='0')
				 {
					 document.forms[0].strRoom.value=ob[i].value.split('^')[2];
				 }
			 }
		}
		document.forms[0].hiddencrno.value = ob1[flg].value;
		
		document.forms[0].hiddenadmno.value = ob2[flg].value;
		document.forms[0].hiddenbed.value = ob3[flg].value;
		document.forms[0].strhtransinflag.value = ob10[flg].value;
		document.forms[0].strhmoveno.value = ob11[flg].value;
		
		var val = "";
		
		var appendFlag = false;
		
		for(var i=0;i<ob9.length;i++)
		{		 
			 if(ob9[i].checked)
			 {		
				 if(appendFlag)
				 {
					 val = val +","+ ob4[i].value+"^"+ob5[i].value;
				 }
				 else
				 {		
					 val = ob4[i].value+"^"+ob5[i].value;
					 appendFlag = true;		
				 }
			 }		
		}		 
		var val1 =""; 
		var appendFlag1 = false;
		if(!isNaN(ob6.length))
		{
		for(var j=0;j<ob6.length-1;j++)
		{		
			if(appendFlag1)
			{
				val1 = val1 +","+ ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;		
			}
			else
			{		
				val1 = ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;
				appendFlag1 = true;		
			}
		}
		
		}
		//alert("val1="+val1);
		
		
		//alert(document.getElementsByName("hiddenbed")[0].value);
		
		document.forms[0].hiddenchkremark.value = val;
		//alert("val=="+document.forms[0].hiddenchkremark.value);
		document.forms[0].hiddenbelonging.value = val1;
		
		 
		 document.forms[0].strhiddendepartment.value  =document.forms[0].strDepartment.value; 
		 document.forms[0].strhiddendunit.value = document.forms[0].strUnit.value; 
		 document.forms[0].strhiddenward.value  = document.forms[0].strWard.value; 
		 document.forms[0].strhiddenRoom.value  = document.forms[0].strRoom.value; 
		 
		 
		 if(mode=="SAVE")
		 {		 	
			var strItemNames = document.getElementsByName("strItemName");
			var strBelongingItems=document.getElementsByName("strBelongingItem");
			var strItemQtys = document.getElementsByName("strItemQuantity");
			var strItemRemarks;
			if(document.getElementsByName("strRemarksMandatoryFlag")[0].value=="1")
			{
				strItemRemarks = document.getElementsByName("strItemRemarks");
			}
			var nCountIssuedItem=0;
			for(var nTmpI = 0; nTmpI < strItemNames.length; nTmpI++)
			{
				if(document.getElementsByName("strIssuedItemRequired")[0].value=="1")
				{
					if(document.getElementsByName("strIsCancelMode")[0].value!="CANCEL")
					{
						if(document.getElementsByName("strItemType")[nTmpI].value==1)
							nCountIssuedItem++;
					} 
					else 
					{
						nCountIssuedItem = 1;
					}
				}
				if(strBelongingItems[nTmpI].value==0)
				{
					alert("Item Name Field is Mandatory for Belonging Details.");
					return false;
				} 
				else if(strItemNames[nTmpI].value.length==0)
				{
					alert("Item Name Field is Mandatory for Belonging Details.");
					return false;
				} 
				else if(strItemQtys[nTmpI].value.length==0)
				{
					alert("Item Qty. Field is Mandatory for Belonging Details.");
					return false;
				} 
				try
				{
					if(strItemRemarks[nTmpI].value.length==0)
					{
						alert("Item Remarks Field is Mandatory for Belonging Details.");
						return false;
					}
				}
				catch(_Err)
				{					
				}
			}
			if(nCountIssuedItem==0 && document.getElementsByName("strIssuedItemRequired")[0].value=="1")
			{
				alert("Issued Item Details is Mandatory.");
				return false;
			}		
			if(retVal)
			{
		                 	  
		         			var bed = document.getElementsByName("strbed");
		         			//alert(bed.length);
		 					var len = bed.length;
		 					var rval=false;
		 					for(i=0;i<len;i++)
		 					{
		 						
		 						if(bed[i].selectedIndex!='0')
		 						{
		 							rval=true;
		 							break;
		 						}
		 					}
		 					if(rval)
		 					{
		 						if(document.forms[0].strNursCheckListMandatoryFlag.value==1)
		 						{
			 						var chklist = document.getElementsByName("strchklistCode");
			 						var len = chklist.length;
			 						
			 						var rchkval=false;
			 						for(i=0;i<len;i++)
			 						{
			 							if(chklist[i].checked ==true )
			 							{
			 								rchkval=true;
			 								break;
			 							}
			 							
			 					    }
			 						if(rchkval== false)
			 						{
			 							alert("Please select atleast one name from Nurse CheckList");
			                    		retVal=false;
			            				 return false;
			 						}
		 						}
		 				    }
		                  	else
		                    {
		                    	alert("Please select a Bed");
		                    	retVal=false;
		            			 return false;
		            		}		            			 
		         }
			    if((document.forms[0].strWadType.value==document.forms[0].strIcuWardType.value || document.forms[0].strWadType.value==document.forms[0].strPvtWardType.value) && document.forms[0].billcount.value==0)
			    {
					//alert("ICU and Private Ward Type Patients cannot be accepted without Billing!!");
				    //return false;			    	
			    }
				if(retVal)
				{
     		   	    document.getElementsByName("strhiddenRoom")[0].value=document.getElementsByName("strchk")[0].value.split("^")[2];
					//alert(document.getElementsByName("strhiddenRoom")[0].value);
             	   	document.forms[0].hmode.value = "SAVE";
             	   	document.forms[0].strDepartment.disabled=false;
					document.forms[0].strUnit.disabled=false;
					document.forms[0].strWard.disabled=false;
					document.forms[0].strRoom.disabled=false;
					document.getElementById("saveid").style.display = "none";
                   	document.forms[0].submit();
                }
				else
					return false;		        
		  }
		  
		 else  if(mode=="NOTREPORT")
		 {
			for(var p=0;p<ob12.length;p++)
			{
				 if(ob[p].checked)
				 {		
					  if(ob12[p].value =="0")
					  {
					
						  document.forms[0].hmode.value = "NOTREPORT";
						  document.forms[0].submit();
					  }
					  else  if(ob12[p].value =="2")
					  {		
						  alert("Not Applicable");
					  }
				  }
			//alert("mode=="+document.forms[0].hmode.value);
			
			}
		}	 
	 }
	 else
	 {
		return false;
	 }
}
function validate2(mode)
{ 
	
 /*var hisValidator = new HISValidator("nursingDeskBean");
 hisValidator.addValidation("strUnit", "dontselect=0","Please Select A Unit");
 var retVal = hisValidator.validate();*/
	
if(document.getElementsByName("strUnit")[0].value == "0")
{
   alert("Please Select Unit...");
   return false;
}
var retVal=true;
          
 if(retVal){
	
if(document.getElementsByName("strUnit")[0].value == "0")
{
   alert("Please Select Unit");	
}
var flg=0;

var ob=document.getElementsByName("strchk");
var ob1=document.getElementsByName("hstrcrno");
var ob2=document.getElementsByName("hstradmno");
var ob3 = document.getElementsByName("strbed");
var ob4 = document.getElementsByName("hstrchklistCode");
var ob5 = document.getElementsByName("strChecklistRemark");
var ob6  = document.getElementsByName("strArticleName");
var ob7  = document.getElementsByName("strQuantity");
var ob8   = document.getElementsByName("strBelongingRemark");
var ob9 = document.getElementsByName("strchklistCode");
var ob10 = document.getElementsByName("strhtransinflg");
var ob11 = document.getElementsByName("strhmno");
var ob12 = document.getElementsByName("strhtransinflg");


for(var i=0;i<ob.length;i++)
{
 if(ob[i].checked)
 {
  flg=i;
 document.forms[0].strhmoveno.value=ob11[i].value;
 if(ob[i].value.split('^')[2]!='0')
 {
 document.forms[0].strRoom.value=ob[i].value.split('^')[2];
 }
 }
}
document.forms[0].hiddencrno.value = ob1[flg].value;

document.forms[0].hiddenadmno.value = ob2[flg].value;
document.forms[0].hiddenbed.value = ob3[flg].value;
document.forms[0].strhtransinflag.value = ob10[flg].value;
document.forms[0].strhmoveno.value = ob11[flg].value;

var val = "";

var appendFlag = false;

for(var i=0;i<ob9.length;i++)
{
 
 if(ob9[i].checked){

if(appendFlag){
 val = val +","+ ob4[i].value+"^"+ob5[i].value;

 
}
else{

val = ob4[i].value+"^"+ob5[i].value;
appendFlag = true;

}
}
 

} 

 
 
var val1 =""; 
var appendFlag1 = false;
if(!isNaN(ob6.length)){
for(var j=0;j<ob6.length-1;j++){


if(appendFlag1){
val1 = val1 +","+ ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;

}
else{

val1 = ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;
appendFlag1 = true;

}
}

}
//alert("val1="+val1);


//alert(document.getElementsByName("hiddenbed")[0].value);

document.forms[0].hiddenchkremark.value = val;
//alert("val=="+document.forms[0].hiddenchkremark.value);
document.forms[0].hiddenbelonging.value = val1;

 
 document.forms[0].strhiddendepartment.value  =document.forms[0].strDepartment.value; 
 document.forms[0].strhiddendunit.value = document.forms[0].strUnit.value; 
 document.forms[0].strhiddenward.value  = document.forms[0].strWard.value; 
 document.forms[0].strhiddenRoom.value  = document.forms[0].strRoom.value; 
 
 
 if(mode=="SAVE"){
 	
 	
	var strItemNames = document.getElementsByName("strItemName");
	var strBelongingItems=document.getElementsByName("strBelongingItem");
	var strItemQtys = document.getElementsByName("strItemQuantity");
	var strItemRemarks;
	if(document.getElementsByName("strRemarksMandatoryFlag")[0].value=="1"){
		strItemRemarks = document.getElementsByName("strItemRemarks");
	}
	var nCountIssuedItem=0;
	for(var nTmpI = 0; nTmpI < strItemNames.length; nTmpI++){
		if(document.getElementsByName("strIssuedItemRequired")[0].value=="1"){
			if(document.getElementsByName("strIsCancelMode")[0].value!="CANCEL"){
				if(document.getElementsByName("strItemType")[nTmpI].value==1)
					nCountIssuedItem++;
			} else {
				nCountIssuedItem = 1;
			}
		}
		if(strBelongingItems[nTmpI].value==0){
			alert("Item Name Field is Mandatory for Belonging Details.");
			return false;
		} 
		else if(strItemNames[nTmpI].value.length==0){
			alert("Item Name Field is Mandatory for Belonging Details.");
			return false;
		} else if(strItemQtys[nTmpI].value.length==0){
			alert("Item Qty. Field is Mandatory for Belonging Details.");
			return false;
		} 
		try{
			if(strItemRemarks[nTmpI].value.length==0){
				alert("Item Remarks Field is Mandatory for Belonging Details.");
				return false;
			}
		}catch(_Err){
			
		}
	}
	if(nCountIssuedItem==0 && document.getElementsByName("strIssuedItemRequired")[0].value=="1"){
		alert("Issued Item Details is Mandatory.");
		return false;
	}
 	

	if(retVal){
                 	  
         			var bed = document.getElementsByName("strbed");
         			//alert(bed.length);
 					var len = bed.length;
 					var rval=false;
 					for(i=0;i<len;i++)
 					{
 						
 						if(bed[i].selectedIndex!='0')
 						{rval=true;
 						break;
 						}
 					}
 					if(rval)
 					{	if(document.forms[0].strNursCheckListMandatoryFlag.value==1){
 						var chklist = document.getElementsByName("strchklistCode");
 						var len = chklist.length;
 						
 						var rchkval=false;
 						for(i=0;i<len;i++)
 						{
 							if(chklist[i].checked ==true )
 							{rchkval=true;
 							break;
 							}
 							
 					    }
 						if(rchkval== false)
 						{
 							alert("Please select atleast one name from Nurse CheckList");
                    		retVal=false;
            				 return false;
 						}
 					}
 				    }
                  	else
                    {
                    	alert("Please select a Bed");
                    	retVal=false;
            			 return false;
            		}
 					 
            			 
         		 }
	    if((document.forms[0].strWadType.value==document.forms[0].strIcuWardType.value || document.forms[0].strWadType.value==document.forms[0].strPvtWardType.value) && document.forms[0].billcount.value==0)
	    {
			//alert("ICU and Private Ward Type Patients cannot be accepted without Billing!!");
		    //return false;	    	
	    }
         		 
 					if(retVal){
         		   	    document.getElementsByName("strhiddenRoom")[0].value=document.getElementsByName("strchk")[0].value.split("^")[2];
						//alert(document.getElementsByName("strhiddenRoom")[0].value);
         		   	    document.forms[0].hmode.value = "SAVEIPD";  	    
         		   	    document.forms[0].action="/HBIMS/ipd/transactions/NursingDeskTransCNT.cnt";
         		   	   	document.forms[0].strDepartment.disabled=false;
  						document.forms[0].strUnit.disabled=false;
  						document.forms[0].strWard.disabled=false;
  						document.forms[0].strRoom.disabled=false;
  						document.getElementById("saveid").style.display = "none";
                       	document.forms[0].submit();
                       }
                   else
                   return false;
        
  }
  
 else  if(mode=="NOTREPORT"){

for(var p=0;p<ob12.length;p++){
 if(ob[p].checked){

  if(ob12[p].value =="0")
  {

  document.forms[0].hmode.value = "NOTREPORT";
  document.forms[0].submit();
  }
  else  if(ob12[p].value =="2"){

  alert("Not Applicable");
  }
  }
//alert("mode=="+document.forms[0].hmode.value);

  }
}
 
 
 
}else{
	return false;
}
}



function validateCancel(mode)
{ 

	
 /*var hisValidator = new HISValidator("nursingDeskBean");
 hisValidator.addValidation("strUnit", "dontselect=0","Please Select A Unit");
 var retVal = hisValidator.validate();*/
	
if(document.getElementsByName("strUnit")[0].value == "0")
{
   alert("Please Select Unit...");
   return false;
}
var retVal=true;
          
 if(retVal){
	
if(document.getElementsByName("strUnit")[0].value == "0")
{
   alert("Please Select Unit");	
}
var flg=0;

var ob=document.getElementsByName("strchk");
var ob1=document.getElementsByName("hstrcrno");
var ob2=document.getElementsByName("hstradmno");
var ob3 = document.getElementsByName("strbed");
var ob4 = document.getElementsByName("hstrchklistCode");
var ob5 = document.getElementsByName("strChecklistRemark");
var ob6  = document.getElementsByName("strArticleName");
var ob7  = document.getElementsByName("strQuantity");
var ob8   = document.getElementsByName("strBelongingRemark");
var ob9 = document.getElementsByName("strchklistCode");
var ob10 = document.getElementsByName("strhtransinflg");
var ob11 = document.getElementsByName("strhmno");
var ob12 = document.getElementsByName("strhtransinflg");


for(var i=0;i<ob.length;i++)
{
 if(ob[i].checked)
 {
  flg=i;
 document.forms[0].strhmoveno.value=ob11[i].value;
 if(ob[i].value.split('^')[2]!='0')
 {
 document.forms[0].strRoom.value=ob[i].value.split('^')[2];
 }
 }
}

if(ob[0].value.split('^')[3]!=undefined)
	{
		if(ob[0].value.split('^')[3]=="7")
			{
				alert("Patient transfer could not be canceled back to SA/OT,please accept From Acceptance Desk");
				return false;
			}
			
	}
document.forms[0].hiddencrno.value = ob1[flg].value;

document.forms[0].hiddenadmno.value = ob2[flg].value;
document.forms[0].hiddenbed.value = ob3[flg].value;
document.forms[0].strhtransinflag.value = ob10[flg].value;
document.forms[0].strhmoveno.value = ob11[flg].value;

var val = "";

var appendFlag = false;

for(var i=0;i<ob9.length;i++)
{
 
 if(ob9[i].checked){

if(appendFlag){
 val = val +","+ ob4[i].value+"^"+ob5[i].value;

 
}
else{

val = ob4[i].value+"^"+ob5[i].value;
appendFlag = true;

}
}
 

} 

 
 
var val1 =""; 
var appendFlag1 = false;
if(!isNaN(ob6.length)){
for(var j=0;j<ob6.length-1;j++){


if(appendFlag1){
val1 = val1 +","+ ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;

}
else{

val1 = ob6[j].value+"^"+ob7[j].value+"^"+ob8[j].value;
appendFlag1 = true;

}
}

}
//alert("val1="+val1);


//alert(document.getElementsByName("hiddenbed")[0].value);

document.forms[0].hiddenchkremark.value = val;
//alert("val=="+document.forms[0].hiddenchkremark.value);
document.forms[0].hiddenbelonging.value = val1;

 
 document.forms[0].strhiddendepartment.value  =document.forms[0].strDepartment.value; 
 document.forms[0].strhiddendunit.value = document.forms[0].strUnit.value; 
 document.forms[0].strhiddenward.value  = document.forms[0].strWard.value; 
 document.forms[0].strhiddenRoom.value  = document.forms[0].strRoom.value; 
 
 
 if(mode=="SAVE"){
 	
 	
	var strItemNames = document.getElementsByName("strItemName");
	var strBelongingItems=document.getElementsByName("strBelongingItem");
	var strItemQtys = document.getElementsByName("strItemQuantity");
	var strItemRemarks;
	if(document.getElementsByName("strRemarksMandatoryFlag")[0].value=="1"){
		strItemRemarks = document.getElementsByName("strItemRemarks");
	}
	var nCountIssuedItem=0;
	for(var nTmpI = 0; nTmpI < strItemNames.length; nTmpI++){
		if(document.getElementsByName("strIssuedItemRequired")[0].value=="1"){
			if(document.getElementsByName("strIsCancelMode")[0].value!="CANCEL"){
				if(document.getElementsByName("strItemType")[nTmpI].value==1)
					nCountIssuedItem++;
			} else {
				nCountIssuedItem = 1;
			}
		}
		if(strBelongingItems[nTmpI].value==0){
			alert("Item Name Field is Mandatory for Belonging Details.");
			return false;
		} 
		else if(strItemNames[nTmpI].value.length==0){
			alert("Item Name Field is Mandatory for Belonging Details.");
			return false;
		} else if(strItemQtys[nTmpI].value.length==0){
			alert("Item Qty. Field is Mandatory for Belonging Details.");
			return false;
		} 
		try{
			if(strItemRemarks[nTmpI].value.length==0){
				alert("Item Remarks Field is Mandatory for Belonging Details.");
				return false;
			}
		}catch(_Err){
			
		}
	}
	if(nCountIssuedItem==0 && document.getElementsByName("strIssuedItemRequired")[0].value=="1"){
		alert("Issued Item Details is Mandatory.");
		return false;
	}
 	

	if(retVal){
                 	  
         			var bed = document.getElementsByName("strbed");
         			//alert(bed.length);
 					var len = bed.length;
 					var rval=false;
 					for(i=0;i<len;i++)
 					{
 						
 						if(bed[i].selectedIndex!='0')
 						{rval=true;
 						break;
 						}
 					}
 					if(rval)
 					{	if(document.forms[0].strNursCheckListMandatoryFlag.value==1){
 						var chklist = document.getElementsByName("strchklistCode");
 						var len = chklist.length;
 						
 						var rchkval=false;
 						for(i=0;i<len;i++)
 						{
 							if(chklist[i].checked ==true )
 							{rchkval=true;
 							break;
 							}
 							
 					    }
 						if(rchkval== false)
 						{
 							alert("Please select atleast one name from Nurse CheckList");
                    		retVal=false;
            				 return false;
 						}
 					}
 				    }
                  	else
                    {
                    	alert("Please select a Bed");
                    	retVal=false;
            			 return false;
            		}
 					 
            			 
         		 }
         		 
 					if(retVal){
         		   	    document.getElementsByName("strhiddenRoom")[0].value=document.getElementsByName("strchk")[0].value.split("^")[2];
						//alert(document.getElementsByName("strhiddenRoom")[0].value);
         		   	    document.forms[0].hmode.value = "SAVECANCEL";
         		   	    document.forms[0].action="/HBIMS/ipd/transactions/NursingDeskTransCNT.cnt";
         		   	   	document.forms[0].strDepartment.disabled=false;
  						document.forms[0].strUnit.disabled=false;
  						document.forms[0].strWard.disabled=false;
  						document.forms[0].strRoom.disabled=false;
                       	document.forms[0].submit();
                       }
                   else
                   return false;
        
  }
  
 else  if(mode=="NOTREPORT"){

for(var p=0;p<ob12.length;p++){
 if(ob[p].checked){

  if(ob12[p].value =="0")
  {

  document.forms[0].hmode.value = "NOTREPORT";
  document.forms[0].submit();
  }
  else  if(ob12[p].value =="2"){

  alert("Not Applicable");
  }
  }
//alert("mode=="+document.forms[0].hmode.value);

  }
}
 
 
 
}else{
	return false;
}
}

function callchk(obj,index)
{
resetMultiRow('1');
addRows(new Array('strArticleName','strQuantity','strBelongingRemark'),new Array('t','t','t'),'1','1','R');
var ob=document.getElementsByName("strchk");
var ob1 = document.getElementsByName("strbed");
var ob2 = document.getElementsByName("strchklistCode");
var ob3  =  document.getElementsByName("strChecklistRemark");
var ob4  = document.getElementsByName("strQuantity");
var ob5 = document.getElementsByName("strArticleName");
var ob6 = document.getElementsByName("strBelongingRemark");



var c = confirm("Do You Want to Proceed...");

for(var i=0;i<ob.length;i++)
{
    if(ob[i].checked)
    {
    	ob1[i].disabled = false;
    	if(ob[i].value.split('^')[2]!='0')
    	{
    		document.forms[0].strRoom.value=ob[i].value.split('^')[2];
    	}
         if(c==true)
         {
	         document.forms[0].stroldradio.value =i;
	         ob1[i].disabled = false; 
	         for( var j =0;j<ob2.length;j++)
	         {	
	              if(ob2[j].checked)
	              {
		               ob2[j].checked = false;
		               ob3[j].value= "";  
		               document.getElementById("strChecklistRemark"+index).disabled = true
	              }
	              if(!isNaN(ob4.length))
	              {
			            for(var k= 0;k <ob4.length;k++)
			            {
				             document.forms[0].nNoOfRow.value ="";
				             ob4[k].value= "";
				             ob5[k].value= "";
				             ob6[k].value= "";		
			            }
	          	  }
	         } 
   	 	 }
		 else
		 {
			document.forms[0].strchk[document.forms[0].stroldradio.value].checked = true;
	   		document.forms[0].strbed[document.forms[0].stroldradio.value].disabled = false;   
	   		if(ob[i].checked==false)
	   		{   
	   			ob1[i].disabled = true;
	   		}  
	    }
	}
	else
	{
		ob1[i].disabled = true;
    }
}
}
function callnursechk(obj,index)
{
 //var o=document.getElementsByName("strchklistCode");
 if(obj.checked)
 {
 	document.getElementById("strChecklistRemark"+index).disabled = false;
	document.getElementById("strChecklistRemark"+index).focus();
 }
else{
document.getElementById("strChecklistRemark"+index).value = "";
document.getElementById("strChecklistRemark"+index).disabled = true;
//document.getElementById("strchklist"+index).checked=false;
}
}


function setWardRoom(){

 document.forms[0].strWard.value ="0"; 
 document.forms[0].strRoom.value ="0"; 
// document.getElementById("divnormalmsg").style.display="none";
// document.getElementById("diverrormsg").style.display="none";

}

function setRoom(){

 document.forms[0].strRoom.value ="0"; 

}



function  setmsg() {
//alert("inside setmsg");

 document.getElementById("divmsg").style.display="none";
}



function radioIndx()
{
var ob=document.getElementsByName("strchk");

if(isNaN(ob.length))

var len=1;
else
var len=ob.length;

if(len!=1)

{

for(var i=0;i<len;i++)
{

if(ob[i].checked)
document.forms[0].stroldradio.value=i;


}

}

}

/*function unitwardroom(){

alert("inside unitwardroom");

 var mode="UNIT";
 //alert("UNIT =="+document.forms[0].strDepartment.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value;
 var mode="WARD";
 var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modUnit="+document.forms[0].strUnit.value;
 
  var mode="ROOM";
 //alert("WARD =="+document.forms[0].strWard.value);
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modWard="+document.forms[0].strWard.value;
 
  var mode="RECORD";
// alert("WARD =="+document.forms[0].strWard.value);
   
   
   var url="NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value+"&modUnit="+document.forms[0].strUnit.value+"&modWard="+document.forms[0].strWard.value+"&modRoom="+document.forms[0].strRoom.value


url = "NursingDeskTransCNT.cnt?hmode="+mode+"&modDept="+document.forms[0].strDepartment.value;
ajaxFunction(url,"1");




}*/

function addBelongingDtl(_tableId){
	var array1 = new Array("<select name=strBelongingItem onchange='itemChange(this)' title='_div_popup_cntl'>"+
	document.getElementsByName("strBelongingItemValues")[0].value+
	"<option value='others'>Others</option></select>"+
	'<center><input name="strItemName" style="display: none" type=text></center><input name="strItemId" type=hidden><input name="strItemType" value=2 type=hidden>',
	'<input class="txtFldNormal" name="strItemQuantity" onkeypress="return validateData(event,5);" maxlength="3" type="text">',
	'<input class="txtFldNormal" name="strItemRemarks" onkeypress="return validateData(event,9);" maxlength="100" type="text">');
	multiRow(_tableId,array1, 1, 0, true);
}

function addIssuedItem(_tableId){
	var array = new Array("<select name=strBelongingItem onchange='itemChange(this)' title='_div_popup_cntl'>"+
	document.getElementsByName("strIssuedItemValues")[0].value+
	"<option value='others'>Others</option></select>"+
	'<center><input name="strItemName" style="display: none" type=text></center><input name="strItemId" type=hidden><input name="strItemType" value=1 type=hidden>',
	'<input class="txtFldNormal" name="strItemQuantity" onkeypress="return validateData(event,5);" maxlength="3" type="text">',
	'<input class="txtFldNormal" name="strItemRemarks" onkeypress="return validateData(event,9);" maxlength="100" type="text">');
	multiRow(_tableId,array, 1, 0, true);
}

function itemChange(_these){
	if(_these.value=="others"){
		document.getElementsByName("strItemName")[getIndex(_these)].style.display = "block";
	} else {
		document.getElementsByName("strItemName")[getIndex(_these)].style.display = "none";
		document.getElementsByName("strItemName")[getIndex(_these)].value=_these.value.split("^")[1];
		document.getElementsByName("strItemId")[getIndex(_these)].value=_these.value.split("^")[0];
	}
}



function sharableChkBox(obj,id)
{	
	var id1=document.getElementById("strunit"+id);
	var id2=document.getElementById("divbed"+id);
	var id3=document.getElementById("strbed"+id);
	var strunit=document.getElementById("strunit"+id);
	if(obj.checked ==true){
		obj.value="1";
	 funbed(strunit,id1,id2,id3);
	}else
	{
		obj.value="0";
		funbed(strunit,id1,id2,id3);
	}
}
function sharableChkBoxIpd(obj,id)
{	
	var id1=document.getElementById("strunit"+id);
	var id2=document.getElementById("divbed"+id);
	var id3=document.getElementById("strbed"+id);
	var strunit=document.getElementById("strunit"+id);
	if(obj.checked ==true){
		obj.value="1";
	}else
	{
		obj.value="0";	
	}
	funbedIPD(strunit,id1,id2,id3);
}
function getConsultantValue(id)
{
	//alert('');
	var deptUnitCode = document.getElementById("strunit"+id);
	var url="NursingDeskTransCNT.cnt?hmode=GETCONSULTANTVALUE&deptUnitCode="+deptUnitCode.value;
	//alert(url);
	ajaxFunction(url,"7");
}

