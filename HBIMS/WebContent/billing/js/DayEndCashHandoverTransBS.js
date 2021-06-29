
function onChangeSessionCmb(){

	if(document.forms[0].strSessionId.value  != 0) {
		
		var obj = document.forms[0].strSessionId;
		var temp = obj.value.split('^');
		
		document.getElementById('fromSessionTime').innerHTML = temp[1];
		document.getElementById('toSessionTime').innerHTML = temp[2];
		
		document.getElementsByName('strSessionFromTime')[0].value = temp[1];
		document.getElementsByName('strSessionToTime')[0].value = temp[2];
		
	}else{
	
		document.getElementById('fromSessionTime').innerHTML = "";
		document.getElementById('toSessionTime').innerHTML = "";
	
	}

}
function sumAmount()
{
	var total=parseInt(document.getElementsByName("strTwoThousandNotes")[0].value)*parseInt(2000)+
	          parseInt(document.getElementsByName("strThousandNotes")[0].value)*parseInt(1000)+
			  parseInt(document.getElementsByName("strFiveHundNotes")[0].value)*parseInt(500)+
			  parseInt(document.getElementsByName("strTwoHundNotes")[0].value)*parseInt(200)+
			  parseInt(document.getElementsByName("strHundNotes")[0].value)*parseInt(100)+
			  parseInt(document.getElementsByName("strFiftyNotes")[0].value)*parseInt(50)+
			  parseInt(document.getElementsByName("strTwentyNotes")[0].value)*parseInt(20)+
			  parseInt(document.getElementsByName("strTenNotes")[0].value)*parseInt(10)+
			  //parseInt(document.getElementsByName("strFiveNotes")[0].value)*parseInt(5)+
			//  parseInt(document.getElementsByName("strTwoNotes")[0].value)*parseInt(2)+
			 // parseInt(document.getElementsByName("strOneNotes")[0].value)*parseInt(1)+
			  parseInt(document.getElementsByName("strtenCoins")[0].value)*parseInt(10)+
			  parseInt(document.getElementsByName("strFiveCoins")[0].value)*parseInt(5)+
			  parseInt(document.getElementsByName("strTwoCoins")[0].value)*parseInt(2)+
			  parseInt(document.getElementsByName("strOneCoins")[0].value)*parseInt(1);
			  
	//alert("total"+total);
	document.getElementsByName("strTotalSum")[0].value=total;
}
function checkFieldForCash(obj)
{
	if(obj.value.length==0)
	{
		obj.value = "0"; 
	}
}

function clearvalues()
{
	//alert("Inside clear values");
	document.getElementsByName("strTwoThousandNotes")[0].value=0;
	document.getElementsByName("strThousandNotes")[0].value=0;
	document.getElementsByName("strFiveHundNotes")[0].value=0;
	document.getElementsByName("strTwoHundNotes")[0].value=0;
	document.getElementsByName("strHundNotes")[0].value=0;	
	document.getElementsByName("strFiftyNotes")[0].value=0;
	document.getElementsByName("strTwentyNotes")[0].value=0;
	document.getElementsByName("strTenNotes")[0].value=0;
	//document.getElementsByName("strFiveNotes")[0].value=0;
	//document.getElementsByName("strTwoNotes")[0].value=0;
	//document.getElementsByName("strOneNotes")[0].value=0;
	document.getElementsByName("strtenCoins")[0].value=0;
	document.getElementsByName("strFiveCoins")[0].value=0;
	document.getElementsByName("strTwoCoins")[0].value=0;
	document.getElementsByName("strOneCoins")[0].value=0;
	document.getElementsByName("strTotalSum")[0].value=0;
	
}
function makingEmptyFieldForCash(obj)
{
	if(obj.value=="0")
	{
		obj.value = ""; 
	}
}

	function getVisibilityDetails(chkObj){

		document.forms[0].strIsPayModeRequired.checked = false;
		document.forms[0].strIsPayDetailRequired.checked = false;

		if(chkObj.checked){
			
			document.getElementById("paymentDetailsRequiredDivId").style.display = "block";			
			
		}else{
		
			document.getElementById("paymentDetailsRequiredDivId").style.display = "none";
		
		}
	

	}


/** This is the validate function for day end transaction jsp file
 *  i.e:- dayend_billtrans.jsp and 
 *  transfer control to the controller 'PROCESS' method.  
 */
 
 function validateFunc()
 {	

	
	var table = document.getElementById("POITable");
	var rowLength = table.rows.length;

	
	var dataLength=document.getElementsByName("strCashHandoverTo").length;
	
	
	for(var j=1;j<=dataLength-1; j++)
	{
		if($(("#tbRow"+j)+" select[name=strCashHandoverTo]").val()== "0")
			{
			alert("Please select a value from Handover To By Combo");
			return false;
			}
		
		for(var i=j+1;i<=dataLength; i++)
		{
			if($(("#tbRow"+j)+" select[name=strCashHandoverTo]").val()== $(("#tbRow"+i)+" select[name=strCashHandoverTo]").val())
			{			
				alert("Combo Box Value Should Not Be Same");
				return false;		
			}	
		}

	}
	
	var el = document.getElementById('total');
	text = (el.innerText || el.textContent);	
	//alert(document.getElementById('res').value);
	if(parseInt(text)!=parseInt(document.getElementById('res').value))		
	{
		
		alert("Total Amount Should Be Same....")
		return false;
	}
/*	var e = document.getElementsByName("strCashHandoverTo")[1];
	var strUser = e.options[e.selectedIndex].value;
	if(strUser==0)
	{
	alert("Please select a value from Handover To By Combos");
	return false;
	}*/
	var retVal =true;
	if(retVal)
	{
			  document.forms[0].hmode.value="DAYENDREPORT";
			  document.forms[0].submit();					
	}
	else
	{
		return false;
	}
}
	 
	 function cancelPage()
	 {
	 	document.forms[0].hmode.value="INITIALPAGE";
	 	document.forms[0].target = "_self";
		document.forms[0].submit();
	 }
 
 var gblPrintLimitVal = 0;
 


/**
	 * reprintContents
	 */
	 function reprintContents() {
	 	
	 		 	
	 		if(gblPrintLimitVal != 0){
	 			
	 			var hmode = "REPRINT"; 
				var url = "DayEndCashHandoverTransBSCNT.cnt?hmode="+hmode;
											
				ajaxFunction2(url,"1","rePrintStatus");	
	 			
	 		} else{
	 		
	 			alert("Sorry, No More Re-Print");
	 		
	 		}
	 		
	 		
	 	
	 }		
				
	
	/**
	 * rePrintStatus 
	 */
	 

/** This function is used to open the report and
 *  transfer control to the controller 'DAYENDREPORT' method.
*/
 
 function reportFunc()
 {
	 if(document.forms[0].strReportFormat[document.forms[0].strReportFormat.selectedIndex].value == "html")
	 {
		 document.forms[0].target = "_self";
	 }
	 else
	 {
		 document.forms[0].target = "_self";
	 }	 
	 document.forms[0].hmode.value="DAYENDREPORT";
	 document.forms[0].submit();		
 }

      
   function save()
   {
   	if(document.forms[0].strCounterId.value==""||document.forms[0].strCounterId.value=="/"){
   	alert("Counter Name does not exist \n Day End can not be performed"); 
   } else {
   	reportFunc();
   	}}
 
 
 
 

function setDayEndDate(index)
{
	
	document.forms[0].strDayEndDate.value	=	document.getElementsByName("strRadioButton")[index].value;
	getDayEndAmount();
}


function getPendingDayEndDetails()
{

        var mode = "pendingDayEndDetails";
        var url ="DayEndCashHandoverTransBSCNT.cnt?hmode="+mode+"&billModuleId="+document.forms[0].billModuleId.value+"&counterId="+document.forms[0].strCounterId.value
        			+"&userMode="+document.forms[0].userMode.value;
		
		if(document.forms[0].strUsrId)
		{
			url = url+"&userId="+document.getElementsByName("strUsrId")[0].value;
		}
        			
        			
 		ajaxFunction(url,"1");		
}

function getDayEndAmount()
{
		//alert("getDayEndAmount"+document.forms[0].strDayEndDate.value+"::"+document.forms[0].strCounterId.value);
		//clerk mode..
		if(document.getElementsByName('userMode')[0].value == 1)
		{
			var mode = "getDayEndAmountAjax";
        	var url ="DayEndCashHandoverTransBSCNT.cnt?hmode="+mode+"&counterId="+document.forms[0].strCounterId.value
        			+"&dayEndDate="+document.forms[0].strDayEndDate.value;
       
		
			ajaxFunction(url,"2");	
		}
		
		//supervisor mode..
		if(document.getElementsByName('userMode')[0].value == 2)
		{
			getDayEndAmountUserNCounterWise();
		}
        	
}

function getDayEndAmountUserNCounterWise()
{
	   if(document.forms[0].strUsrId.value == 0)
       {
       		alert("User Name is not selected.");
       		objVal.value="0";
			document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
       		return;	
       }
	   else if(document.forms[0].strCountId.value == 0)
       {
       		alert("Counter Name is not selected.");
       		objVal.value="0";
			document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
			document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
       		return;	
       }
       else
       {
       		var mode = "getDayEndAmountAjaxUserNcounterWise";
        	var url ="DayEndCashHandoverTransBSCNT.cnt?hmode="+mode+"&counterId="+document.forms[0].strCountId.value
        			+"&dayEndDate="+document.forms[0].strDayEndDate.value
        			+"&userIdd="+document.forms[0].strUsrId.value;
		
			ajaxFunction(url,"3");       		
       }
        	
}

function getAjaxResponse(res,mode)
{
      var err = document.getElementById("errMsg");
      var temp = res.split("####");
      if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 

	if(mode=="1")
	   {
	   	 var objVal= document.getElementById("pendingDayEndDetailsTable");
     
			if(res=="")
			{
				objVal.innerHTML="";
			}
			else
			{
				//alert("Hello"+res);
				objVal.innerHTML = res;
				
				if(document.forms[0].strRadioButton)
				{
					//document.getElementsByName("strRadioButton")[0].checked=true;
					document.getElementsByName("strDayEndDate")[0].value=document.getElementsByName("strRadioButton")[0].value;
				}
			}
			
			callPendingDayEndDetailsTable();
		//	toatlSum();
						
		}
		
		if(mode=="2")//get day end amount on date change
	    {
	   	 	var objVal= document.getElementsByName("strDayEndAmount")[0];
	   	    var objVal1= document.getElementsByName("strDayEndCreditCol")[0];
	   	    var objVal2= document.getElementsByName("strDayEndInstAmt")[0];
			if(res=="")
			{
				objVal.value="0";
				objVal1.value="0";
				objVal2.value="0";
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
			}
			else
			{
				var ca=res.split('#')[0];
				var cra=res.split('#')[1];
				var ia=res.split('#')[2];
				objVal.value= ca;
				objVal1.value= cra;
				objVal2.value= ia;
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>"+ca+"</b></font>";	
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>"+cra+"</b></font>";	
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>"+ia+"</b></font>";	
			}
		}		
		if(mode=="3")//get day end amount user n counter wise using selected value from drop down..
	    {
	   	 	var objVal= document.getElementsByName("strDayEndAmount")[0];
     
			if(res=="")
			{
				objVal.value="0";
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv1').innerHTML="<font color='red'><b>0</b></font>";
				document.getElementById('dayEndAmtDiv2').innerHTML="<font color='red'><b>0</b></font>";
			}
			else
			{
				objVal.value= res;
				document.getElementById('dayEndAmtDiv').innerHTML="<font color='red'><b>"+res+"</b></font>";
			}
		}
		if(mode=="4")//get day end amount user n counter wise using selected value from drop down..
	    {
	   	 	document.getElementById("pendingDayEndDetailsTable").innerHTML=res;
		}
}		

function hidePendingDayEndDetails()
{
//	document.getElementById("pendingDayEndDetailsTable").innerHTML="";
//	document.getElementById("test").src="../../hisglobal/images/plus.gif";
	
	var imgElt     =document.getElementById("showPendingDayEndTableId");
	//alert("document.getElementById('test'):::"+document.getElementById("test"));
	tableHide('pendingDayEndDetailsTable',imgElt);
}

/* showBreakupDiv()
{
	if(document.getElementById("DenominationDivId").style.display=="")
	{
		document.getElementById("DenominationDivId").style.display="none";
		document.getElementById("minusign").style.display="none";
		document.getElementById("plussign").style.display="";
	}
	else
	{
		document.getElementById("DenominationDivId").style.display="";
		dfunctionocument.getElementById("minusign").style.display="";
		document.getElementById("plussign").style.display="none";
	}
}*/


function checkall(obj)
{
	if(obj.checked)
	{
		for(var i=0;i<document.getElementsByName("strChk-1").length;i++)
		{
			document.getElementsByName("strChk-1")[i].checked=true;		
		}
	}
	else
	{
		for(var i=0;i<document.getElementsByName("strChk-1").length;i++)
		{
			document.getElementsByName("strChk-1")[i].checked=false;		
		}
	}
}

function checkallevent(eve,obj)
{
	if(eve.keyCode='32' && obj.checked)
	{
		for(var i=0;i<document.getElementsByName("chk-1").length;i++)
		{
			document.getElementsByName("chk-1")[i].checked=true;		
		}
	}
	else
	{
		for(var i=0;i<document.getElementsByName("chk-1").length;i++)
		{
			document.getElementsByName("chk-1")[i].checked=false;		
		}
	}
}


function deleteRow(obj) {
		var row_id = obj.parentNode.parentNode.rowIndex;
	  //document.getElementById('POITable').deleteRow(row_id);
	  row=document.getElementById("tbRow"+row_id);
	  row.style.display="none";
	  row.querySelectorAll("input")[0].value=0;
	  sum();

	}

/*function insRow() {
	  var x = document.getElementById('POITable');
	  var new_row = x.rows[1].cloneNode(true);
	  var len = x.rows.length;
	  new_row.cells[0].innerHTML = len;

	  var inp1 = new_row.cells[1].getElementsByTagName('input')[0];
	//  inp1.id += len;
	  
	  inp1.id=inp1.id.split("1")[0]+len;
	  inp1.value = '';
	  var inp2 = new_row.cells[2].getElementsByTagName('select')[0];
	  inp2.id +=   inp2.id.split("1")[0]+len;
	  inp2.value = '';
	  
	  var inp3 =  new_row.cells[3].getElementsByTagName('button')[0];
	  inp3.id +=   inp3.id.split("1")[0]+len;
	  inp3.value = '';
	  
	  

	  var y=x.querySelectorAll("tbody")[0];
	  y.appendChild(new_row);
	}*/



/*function insRow() {
	  var x = document.getElementById('POITable');
	  var y=x.querySelectorAll("tbody")[0];
	  var new_row = x.rows[1].cloneNode(true);
	  var len = x.rows.length;
	  new_row.style.display="";
	  new_row.id=x.rows[1].id.split("1")[0]+len;
	  y.appendChild(new_row);
	  new_row.style.display="";
	  new_row.querySelectorAll("input")[0].value=0;
	// new_row.querySelectorAll("input")[0].removeAttribute("readonly");

	}*/

function insRow() {
	  var x = document.getElementById('POITable');
	  var y=x.querySelectorAll("tbody")[0];
	  var new_row = x.rows[1].cloneNode(true);
	  var len = x.rows.length;
	  new_row.style.display="";
	  new_row.id=x.rows[1].id+(parseInt(len)-1);
	  y.appendChild(new_row);
	  new_row.style.display="";
	  new_row.querySelectorAll("input")[0].value=0;
	// new_row.querySelectorAll("input")[0].removeAttribute("readonly");

	}


function sum(obj) {
	
//alert(obj.value);
	var sum=0;
	var x = document.getElementById('POITable');
	var y=x.querySelectorAll("tbody")[0];
	
	for(var i=0;i<(x.rows.length-1);i++)
		sum=parseInt(sum)+parseInt(y.querySelectorAll("tr")[i].querySelectorAll("input")[0].value);	
	if(parseInt(sum)>0)
		document.getElementById('res').value = sum;
	/*var el = document.getElementById('total');
	totalsum = (el.innerText || el.textContent);
	var remain=parseInt(totalsum)-parseInt(obj.value);
	var j=obj.parentElement.parentElement.id.split("tbRow")[1];
	for(var i=1;i<(x.rows.length);i++)
		if(i==2){
			document.getElementById("tbRow"+(parseInt(j)+1)).querySelectorAll("input")[0].value=remain;
			break;
		}*/
}

/*function toatlSum() 
{
	$('.chkclass').click(function() 
	{
	    var totalsum = 0;
	    var sumBank=0;
	    var sumThirdParty=0;
	    var sumOther=0;
	    var weigh=0;
	
	    $("#tbRow1").css("display","none");
	    $("#tbRow2").css("display","none");
	    $("#tbRow3").css("display","none");
	    $("#tbRow1").find("strCashHandOverAmount").val(0);
	    $("#tbRow2").find("strCashHandOverAmount").val(0);
	    $("#tbRow3").find("strCashHandOverAmount").val(0);
	    $('.chkclass:checked').each(function() 
	    {
	    	
	    	weigh=parseFloat($(this).closest('tr').find('.wagein').text());
	    	totalsum +=  weigh;
	    	
	    	var costType= $(this).val().split("^")[1];
	    	
	    	if(costType=="1")
	    	{	    		
	    		sumBank=sumBank+weigh;
	    		$(("#tbRow"+costType)+" input[name=strCashHandOverAmount]").val(sumBank);
	    		$(("#tbRow"+costType)+" select[name=strCashHandoverTo]").prop('selectedIndex', costType);
	    		$(("#tbRow"+costType)+" select[name=strCashHandoverTo]").val(costType);
	    		$("#tbRow1").css("display","");
	    	}	    	
	    		
    		if(costType=="2")
	    	{	    		
	    		sumThirdParty=sumThirdParty+weigh;
	    		if($("#tbRow"+costType).prop("tagName")==undefined)	
	    			insRow();
	    		$("#tbRow"+costType).css("display","");	
	    		$(("#tbRow"+costType)+" input[name=strCashHandOverAmount]").val(sumThirdParty);
	    		//$("#tbRow"+costType).find("input").val(sumThirdParty);
	    		$(("#tbRow"+costType)+" select[name=strCashHandoverTo]").prop('selectedIndex', costType);
	    	}	    
	    	
	    	if(costType=="3")
	    	{	  
	    		sumOther=sumOther+weigh;
	    		if($("#tbRow3").prop("tagName")==undefined)	
	    			insRow();
	    		$("#tbRow3").css("display","");	
	    		$(("#tbRow"+costType)+" input[name=strCashHandOverAmount]").val(sumOther);
	    		//$("#tbRow"+costType).find("input").val(sumThirdParty);
	    		$(("#tbRow"+costType)+" select[name=strCashHandoverTo]").prop('selectedIndex', costType);
	    	}
	    	
	    });
	   $("#res").val(parseFloat(sumBank+sumThirdParty+sumOther).toFixed(2));
	    
	    $('#total').html(parseFloat(totalsum).toFixed(2));
	});
}*/



function totalSum() 
{
	var costTypeName =document.getElementsByName("strCashHandoverTo")[0].options;
	var sum=0;
	var totSum=0;
	var map = {};

		
	    $('.chkclass:checked').each(function() 
	    {
	    	var costType= $(this).val().split("^")[1];
	    	sum=parseFloat($(this).closest('tr').find('.wagein').text());
	    	if(map.hasOwnProperty(costType))
	    			map[costType]=parseInt(map[costType])+parseInt(sum);
	    	else
	    		map[costType]=sum;	
	 	});

	
	var j=1;
  	for(key1 in map){
  		if($("#tbRow"+j).prop("tagName")==undefined)	{
  			insRow();
  	  		$(("#tbRow"+j)+" select[name=strCashHandoverTo]").prop('selectedIndex', key1);
  		}
  			 $(("#tbRow"+j)+" input[name=strCashHandOverAmount]").val(map[key1]);
  			totSum=totSum+map[key1];
   			
  		j++;
	}
  	
  	$("#res").val(parseFloat(totSum).toFixed(2));
  	 $('#total').html(parseFloat(totSum).toFixed(2));
  	
  
		  $('#nextModal').modal({
			    show: true
			  });

}


function callPendingDayEndDetailsTable(){
	$(document).ready(function() {
		$('#pendingDayEndDetailsTable').DataTable({
			  language: {
			    paginate: {
			      next: "<i class='fas fa-angle-right'>",
			      previous: "<i class='fas fa-angle-left'>"
			    }
			  },
		         
		          "scrollY":"330px",
		          "ordering": false,
		          "info":true,
		          "paging":true,
		          
		  
			});	
		             $(".dataTables_scrollHeadInner").css({"width":"100%"});
		             $(".table ").css({"width":"100%"});
		                $('#dtable_filter input').css('border-radius','5px');
		                
		                
			});
}
	function chkatleastone(){
		
		var counter=0;
		for(var i=0;i<document.getElementsByName("strChk").length;i++)
		{
				if(document.getElementsByName("strChk")[i].checked)
				counter++;			
		}
		if(counter==0){
			alert("Please Select At Least One ScrollNo.")
		}
		else{
			
				  $('#nextModal').modal({
				    show: true
				  });

				 
		}
	}
	
	function checkMsg()
	{
		var err=document.getElementById("errMsg");
		var nor=document.getElementById("normalMsg");
		var warn=document.getElementById("warningMsg");
		if (err.innerHTML != "") {
			err.innerHTML="<i class='fas fa-exclamation-triangle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			err.style.display = "";
		}
		if (nor.innerHTML != "") {
			nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			nor.style.display = "";
		}
		if (warn.innerHTML != "") {
			warn.innerHTML="<i class='fas fa-bell'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Warning!</strong>&nbsp;&nbsp;"+warn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			warn.style.display = "";
		}
	}
	
	/*function offline(){
		var hmode="OfflineMode";
		var url = "DayEndCashHandoverTransBSCNT.cnt?hmode="+hmode;
		
		ajaxFunction(url,"4");	
	}
	
	function online(){
		document.forms[0].hmode.value="PROCESS";
		document.forms[0].submit();
	}*/