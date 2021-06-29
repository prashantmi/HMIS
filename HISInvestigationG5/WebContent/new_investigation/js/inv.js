function deleteData(_these){
	var strChk = document.getElementsByName("chk");
	if(strChk.length==0){
		alert("Must select at lease one data.");
		return;
	}
	for(nTmpI = 0; nTmpI < strChk.length; nTmpI++){
		if(strChk[nTmpI].checked){
			if(strChk[nTmpI].value.replace("$","@").split("@")[5] == 1){
				alert("Selected Data can not be Deleted.");
				return;
			}
		}
	}
	document.forms[0].hmode.value = "DELETEDATA";
	document.forms[0].submit();
}

//used in global group master to delete the records
function deleteGlobalGroup(_param)
{
	var fFlag = true;
	var Flag = true;
	var temp;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("$")[0])<121)
		{
			fFlag=false;
		}
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("|")[1].split("$")[0])!=0)
		{		
			Flag=false;
		}	
	}	
	if(fFlag)
	{
		if(Flag)
			deleteRecords(_param);
		else
		alert("The Selected Group having Local Group can not be Deleted.");
		
	}
	else
	{
		if(Flag)
			alert("You Have Selected the System Defined Group which can not be Deleted.");
		else
			alert("You Have Selected the System Defined Group having Local Group which can not be Deleted.");
	}
}

//used in global group master to modify the records
function modifyGlobalGroup(_param)
{
	var fFlag = false;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("$")[0])<121)
		{
			fFlag=true;
		}
	}	
	if(fFlag)
	{
		alert("You Have Selected the System Defined Group which can not be Modified.");		
	}
	else
	{
		edit('MODIFY');
	}
}
//used in local group master to delete the records
function deleteGroup2(_param)
{
	var fFlag = true;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{		
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("$")[0])<121)
		{			
			fFlag=false;
		}		
	}
	
	if(fFlag)
		deleteRecords(_param);
	else
		alert("You Have Select the System Defined Group which can not be Deleted.");

}

//used in Local Tariff master to delete the records
function deleteTariff(_param)
{
	var fFlag = true;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++){
		
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("$")[0])==0)
			fFlag=false;
	}
	if(fFlag)
		deleteRecords(_param);
	else
		alert("You Have Selected the System Defined Tariff which can not be Deleted.");
}
//used in global tariff master to validate the Group Combo while adding the record 
function checkGlobalGroup(form1)
{
	var cmbVal = "";
	with(form1)
	{	
		if(combo[0].value == "0")
		{
			alert("Please Select Group Name");
			return;
		}
		else
		{
			if(parseInt(combo[0].value)<=120)
			{
				alert("Tariffs Can Not Be Added In System Defined Groups");
				return;
			}
			else
			{
				cmbVal = combo[0].options[combo[0].selectedIndex].text;
				comboValue.value = cmbVal;
				add("ADD");
			}
		}	
	}	
}
function deleteGlobalTariff(_param)
{
	var fFlag = true;
	var Flag = true;
	var temp;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("$")[0])<121)
		{
			fFlag=false;
		}		
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("@")[1].split("|")[1].split("$")[0])!=0)
		{
			Flag=false;
		}
	}
	if(fFlag)
	{
		if(Flag)
		deleteRecords(_param);
		else
		alert("The Selected Tariff having Local Tariff can not be Deleted.");		
	}
	else
	{
		if(Flag)
			alert("You Have Selected the System Defined Tariff which can not be Deleted.");
		else
			alert("You Have Selected the System Defined Tariff having Local Tariff which can not be Deleted.");
	}
}

//used in global group master to modify the records
function modifyGlobalTariff(_param)
{
	var fFlag = false;
	var checkBox = document.getElementsByName("chk");
	for(var nTmpI=0; nTmpI < checkBox.length; nTmpI++)
	{
		if(checkBox[nTmpI].checked && parseInt(checkBox[nTmpI].value.split("|")[0].split("@")[1].substr(0,3))<121)
		{
			fFlag=true;
		}
	}	
	if(fFlag)
	{
		alert("You Have Selected the System Defined Tariff which can not be Modified.");		
	}
	else
	{
		edit('MODIFY');
	}
}

function deleteAdvanceRecords()
{
	var combo1;
	var prevNext	=	null;
	var totalChk	=	0;
	var primarykey	=	"";
	var search		=	"";
	var str			=	"";
	var str			=	"";
			
	var mode = "DELETEDATA";
	var len			= 	document.forms[0].chk.length;
	var no_of_combo	=	document.forms[0].no_of_combo.value;
		
	var strChk = document.getElementsByName("chk");
	if(strChk.length==0){
		alert("Must select at lease one data.");
		return;
	}
	for(nTmpI = 0; nTmpI < strChk.length; nTmpI++){
		if(strChk[nTmpI].checked){
			if(strChk[nTmpI].value.replace("$","@").split("@")[5] == 1){
				alert("Selected Data can not be Deleted.");
				return;
			}
		}
	}
	if(document.forms[0].combo !=null)
		combo1 		=	document.forms[0].combo;	
	
	//call in this file	 
	if(checkForDelete()==false) return ;
		 
	var divisionId		= document.forms[0].divisionId.value;
	var rec_per_page	= document.forms[0].record_per_page.value;
	var prevDivIndex 	= divisionId.substring(1,divisionId.length);
	var min_rec_len 	= parseInt(rec_per_page)*(parseInt(prevDivIndex) -1); 
	var max_rec_len 	= parseInt(rec_per_page) * parseInt(prevDivIndex);
	
	if(!isNaN(document.forms[0].chk.length))
	{
		for(var i=min_rec_len;i< max_rec_len && i < document.forms[0].chk.length;i++)
		{
			if(document.forms[0].chk[i].checked==true)
			{
				totalChk+=1;
				primarykey +=document.forms[0].chk[i].value;
		  		primarykey +="~"; // concatenating all chk value with @ symbols to a single variable to for easy manipulations like deleting the records and update
		  	}
		  }
		  
	}
	else
		primarykey=document.forms[0].chk.value;
		
		
	
    var retValue=confirm("Selected Record (s) are being deleted\n\nAre You Sure");	 	
	if(retValue==false) return;
	

	if(prevNext	==	null) prevNext='1';
	
	if(no_of_combo >1)
		{
			for(i=0;i<combo1.length;i++)
				str += "&combo="+combo1[i].value;
		}
	else
		if(no_of_combo==1) 
			str += "&combo="+combo1.value;		
	
	
	var minrow		=	document.forms[0].minrow.value;	
	var max_rownum	=	document.forms[0].max_rownum.value;	
	var blockNo		=	document.forms[0].blockNo.value;  
	var divisionId	=	document.forms[0].divisionId.value;
	var search			=	"";
	var searchColumn	=	null;
	var rowNum			=	minrow;
	var params="CNTAdvanceMst.cnt?hmode="+mode+str+"&divisionId="+divisionId+"&chk="+primarykey+"&prevNext="+prevNext
					+"&rowNum=0&minrow="+minrow+"&blockNo="+blockNo+"&max_rownum="+max_rownum+"&search="+search+"&searchColumn="+searchColumn;
	
	xmlHttp.open("GET",params,true);
	document.getElementById("message").style.display='block';	
	document.getElementById('start').innerHTML ='<table align=center bgcolor="RED"><tr><td> <FONT size=3 color=\"white\">Wait Deleting Records...</FONT></td></tr></table>';
	
		
	xmlHttp.onreadystatechange = function()
	{ 
		if(xmlHttp.readyState ==4 )
		{
			if(xmlHttp.status == 200 || xmlHttp.status=='complete' )
			{
				var response 		= 	xmlHttp.responseText;
				
							
				var responseData	=	new Array();
				responseData	=	response.split("####");
				document.getElementById('message').innerHTML 	= 	responseData[6];
	        	document.forms[0].record_per_page.value			=	responseData[0];
				document.forms[0].no_of_combo.value				=	responseData[1];
				document.forms[0].actual_page_block.value		=	responseData[2];
				document.forms[0].totalpage.value				=	responseData[3];
				document.forms[0].divisionId.value				=	responseData[7];
				document.getElementById("start").innerHTML 		= 	responseData[4];
				document.getElementById("searchid").innerHTML	= 	responseData[5];
				document.getElementById("footer").style.display	=	'block';
				//document.getElementById("a1").style.display		=	'none';
					
				var	curr_block		=	responseData[7];
			  	
			   	 if(document.getElementById("bb"+curr_block) !=null)
			   	{
			   		document.getElementById(curr_block).style.display='block';
			   		document.forms[0].divisionId.value=curr_block;
			   		changeDiv(curr_block);
			   		document.forms[0].divisionId.value=curr_block;
					document.getElementById("bb"+curr_block).style.color='red';
				}
			else
			    if(document.getElementById("bba1") !=null)
			   	{
			   	document.getElementById("a1").style.display='block';
			   	document.forms[0].divisionId.value="a1";
			 	document.getElementById("bba1").style.color='red';
				}						
			  	
			 }			
		}
	}
	
	xmlHttp.send(params);
	
}	
/*reset the multirow*/

function showAlert(){
	
	if(document.getElementById("dropdown1") != null && document.getElementById("dropdown1").style.display!="none"){
		alert("Please select the tariff");
		document.getElementById("realitems1").focus();
		return false;
	} else
		return true;
}
	function enableDisableMinusAndSaveButton(_these,_delIndex){
		if(document.getElementById("strOfflineTariffName"+_delIndex).value.length<1){
			setDropDownSelectedTariff(_delIndex,'0^0^0^0^0^0^0^0^0^0^0^0^0^0^0^0^0');
			document.getElementById("dropdown1").style.display="none";
		}
	}	
	function resetTariffRows(){
		resetMultiRow('2');
		addRows(new Array('strOfflineTariffName','strOfflineTariffDetailsHidden','strOfflineTariffRateUnit','strOfflineTariffQty','strOfflineTariffUnit','strOfflineTariffServiceTax', 'strOfflineTariffServiceTaxAmtVal','strOfflineTariffDiscount','strOfflineTariffDiscountAmtVal' ,'strOfflineTariffNetAmount','strOfflineTotalActualTariffAmtVal'),new Array('t','t','t','t','s','t','t','t','t','t','t'),'2','1','R');
	}

function advanceMstCheckHospitalService(form1)
{
	
	var cmbVal = "";
	with(form1)
	{
		if(combo.value == "0")
		{
			alert("Please Select Hospital Service");
			return;
		}
		else
		{
			cmbVal = combo.options[combo.selectedIndex].text;
			comboValue.value = cmbVal;
			add("ADD");
		}	
	}
	
}

/** function called on onLoad to initialize the multirow layers
	*/
	
		function initilizeValues(){
	var no_Rows = "<%=noOfRows%>";
	for(i=1; i<=no_Rows; i++ )
		initilizeValue(i);
	}

	/** called before submitting the form to destroy the useless multirow indexs
	*/ 
	function destroyMultiRowvalues(){
	alert("inside destroy----");
	var no_Rows = "<%=noOfRows%>";
	for(i=1; i<=no_Rows; i++ )
		destroyMultiRow(i);
	}
	
	/** called to add row in multi row
	* first check the checkbox tich then add the row
	* @param: idx- layerIndex
	*/
	function insert_row(idx){
	var chkbox = document.getElementById("checkbox"+idx);
		if(chkbox.checked){
			addRows(new Array("combo","name"),new Array("s","t"),idx,"1","R");
		}else
			alert("Please tick the checkbox");
	}

/** called on the onChange of combo values
	*@param: obj- object of combo
	*@param: gid- this is #delIndex# , unique row id
	*/
	function changeValue(obj,gid){
	alert("gid id "+gid);
	document.getElementById("name"+gid).value = gid;
	}

	/** called on onClick from submit button  to submit form 
	*/
	function submitPage(){
		destroyMultiRowvalues();
		document.form1.hmode.value = "done";
		document.form1.submit();
	}


/////////////////function to display selected combo value on next page
function callMe(form1)
{
	
	var cmbVal = "";
	with(form1)
	{
	
		if(combo[0].value == "0")
		{
		
			//alert("Please Select Package Name");
			
			 alert("Please Select Module Name");
			return;
		}
		else
		{
			cmbVal = combo[0].options[combo[0].selectedIndex].text;
			comboValue.value = cmbVal;
			
			
		//	cmbVal2 = combo[1].options[combo[1].selectedIndex].text;
		//	comboValue2.value = cmbVal;
			
			 add("ADD");
			 
			
		}	
	}
}
	
/////////////////////////////////////////////////////////////////////////////////////////////
	function callMePack1(form1)
{
	//alert("callme func is calling");
	var cmbVal = "";
	with(form1)
	{
	
		if(combo[0].value == "0")
		{
			alert("Please Select Package Name");
			return;
		}
		else
		{
			cmbVal = combo[0].options[combo[0].selectedIndex].text;
			comboValue.value = cmbVal;
			
			
		//	cmbVal2 = combo[1].options[combo[1].selectedIndex].text;
		//	comboValue2.value = cmbVal;
			
			 add("ADD");
			 
			
		}	
	}	
}
	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

function callMePack2(form1)
{
	//alert("callme func is calling");
	var cmbVal = "";
	with(form1)
	{
	
		if(combo[0].value == "0")
		{
			alert("Please Select Package Name");
			return;
		}
		else
		{
			cmbVal = combo[0].options[combo[0].selectedIndex].text;
			comboValue.value = cmbVal;
			
			
		//	cmbVal2 = combo[1].options[combo[1].selectedIndex].text;
		//	comboValue2.value = cmbVal;
			
			 edit("MODIFY");
			 
			
		}	
	}		
	
	}
	
	/*
	 * This function will calculate the maximum client amount. In case of amount paid by patient, net amount will be 
	 * checked with balance amount. In case of amount refunded to the patient, net amount will be checked with paid amount
	 * 
	 * balanceAmt -> Client Balance Amount
	 * paidAmt -> Payied by the Client
	 * netExpenseAmt -> Expense incurred on patient.
	 * 
	 */
	function calClientAmount(balanceAmt,paidAmt,netExpenseAmt)
	{
	
		var patAmt = 0.0;
		var clientAmt = 0.0;
		var tempBalAmt = 0.0;
		var tempPaidAmt = 0.0;
		var tempExpAmt = 0.0;
		
		tempBalAmt = parseFloat(balanceAmt);
		tempPaidAmt = parseFloat(paidAmt);
		tempExpAmt = parseFloat(netExpenseAmt);
		
		//case of refund
		if(tempExpAmt <=0)
		{
			tempExpAmt = Math.abs(tempExpAmt);
			//
			if(tempExpAmt >= tempPaidAmt)
			{
				clientAmt = tempPaidAmt;
				patAmt = manipulateValue(tempExpAmt,clientAmt,1);
				
				clientAmt = manipulateValue(0,clientAmt,1);
				patAmt = manipulateValue(0,patAmt,1);
			}
			else
			{
				patAmt = 0;
				clientAmt = manipulateValue(0,tempExpAmt,1);
			}	
		}
		else
		{
			//case of receipt
			if(tempExpAmt >= tempBalAmt)
			{
				clientAmt = tempBalAmt;
				patAmt = manipulateValue(tempExpAmt,clientAmt,1);
			}
			else
			{
				patAmt = 0;
				clientAmt = tempExpAmt;
			}
		}
		
		patAmt = roundValue(patAmt,2);
		clientAmt = roundValue(clientAmt,2);
		
		return {oPatAmt:patAmt,oClientAmt:clientAmt};
	}
	
/*For Amount Calculation, please for use the below function, include util.js file also*/

/*
	Description >> This JS function is used to calculate the tariff amount, discount amount, service tax amount,
					penelty amount and tariff net amount
	
	Parameter >> It accepts value 
			  >> qty_unit either could be unitId^base_value or base_value 
			  >> rate should be rate without any manipulation with base value 
			  >> dis_unit and dis_type (1 --> fixed, 2-->%)
			  >> mode indicates the returned net amount, 0 --> Returned amount in +, 1--> Returned Net Amount in -
	
	Mandatory Parameter >>  rate, rate_base_value,qty,qty_unit
			   	 
	Return Value >> tariff amount (qty * rate),discount amount, service tax amount, penelty amount and Net tariff Amount
	Return Parameter >> oTrfAmt for tariff amount [rate * qty] for a tariff
					 >> oDisAmt for discount amount for a tariff
					 >> oSerAmt for service tax amount for a tariff
					 >> oPenAmt for penelty amount for a tariff
					 >> oNetTrfAmt for Net Amount for a tariff
*/
function calTrfNetAmount(rate,rate_base_value,dis_unit,dis_type,qty,qty_unit,ser_tax,penelty,mode)
{
	//initilize variable
	var trfAmt = 0.0;
	var disAmt = 0.0;
	var serAmt = 0.0;
	var penAmt = 0.0;
	var netTrfAmt = 0.0;
	var totNetTrfAmt = 0.0;
		
	var qty_base_value = 0.0;
	var qty_arr;
		
	//business logic
	//whether only qty base value is provided or with unit_id 
	qty_arr = qty_unit.split("^");
	if(qty_arr.length > 1) {	//base value with unit id [unit_id^base_value]
		qty_base_value = parseFloat(qty_arr[1]);
	}
	else {		//only base value
		qty_base_value = qty_unit;
	}
	
	//calculate total cost for a tariff
	trfAmt = calTariffCost(qty,qty_base_value,rate,rate_base_value,mode);
		
	//calculate discount cost for a tariff
	disAmt = calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value);
	
	//calculate service cost for a tariff
	serAmt = calTrfServiceCost(trfAmt,disAmt,ser_tax,mode);
	
	//calculate net cost for a tariff on which penelty will be calculated
	var val1 = manipulateValue(trfAmt,serAmt,0);
	var val2 = manipulateValue(val1,disAmt,0);
	
	/*
		calculate penelty cost for a tariff [used only in case of refund]
		Now we are assuming that on penelty cost, no services tax will be calculate, it will be substracted from the net amount
	*/
	penAmt = calTrfPeneltyCost(val2,penelty);
	netTrfAmt = manipulateValue(val2,penAmt,0);
	
	
	
	netTrfAmt = roundValue(netTrfAmt,2);
	
	//return value
	return {oTrfAmt:trfAmt,oDisAmt:disAmt,oSerAmt:serAmt,oPenAmt:penAmt,oNetTrfAmt:netTrfAmt};
}



/*For Amount Calculation, please for use the below function, include util.js file also*/

/*
	Description >> This JS function is used to calculate the tariff amount, discount amount, service tax amount,
					penelty amount and tariff net amount
	
	Parameter >> It accepts value 
			  >> qty_unit either could be unitId^base_value or base_value 
			  >> rate should be rate without any manipulation with base value 
			  >> dis_unit and dis_type (1 --> fixed, 2-->%)
			  >> mode indicates the returned net amount, 0 --> Returned amount in +, 1--> Returned Net Amount in -
	
	Mandatory Parameter >>  rate, rate_base_value,qty,qty_unit
			   	 
	Return Value >> tariff amount (qty * rate),discount amount, service tax amount, penelty amount and Net tariff Amount
	Return Parameter >> oTrfAmt for tariff amount [rate * qty] for a tariff
					 >> oDisAmt for discount amount for a tariff
					 >> oSerAmt for service tax amount for a tariff
					 >> oPenAmt for penelty amount for a tariff
					 >> oNetTrfAmt for Net Amount for a tariff
*/
function calTrfNetAmountWithActTrfAmt(rate, act_rate , rate_base_value,dis_unit,dis_type,qty,qty_unit,ser_tax,penelty,mode)
{
	//initilize variable
	var trfAmt = 0.0;
	var actTrfAmt = 0.0;
	var disAmt = 0.0;
	var serAmt = 0.0;
	var penAmt = 0.0;
	var netTrfAmt = 0.0;
	var totNetTrfAmt = 0.0;
		
	var qty_base_value = 0.0;
	var qty_arr;
		
		
	//business logic
	//whether only qty base value is provided or with unit_id 
	qty_arr = qty_unit.split("^");
	if(qty_arr.length > 1) {	//base value with unit id [unit_id^base_value]
		qty_base_value = parseFloat(qty_arr[1]);
	}
	else {		//only base value
		qty_base_value = qty_unit;
	}
	
	//calculate total cost for a tariff
	trfAmt = calTariffCost(qty,qty_base_value,rate,rate_base_value,mode);
		
	actTrfAmt =  calActualTariffCost(qty,qty_base_value,act_rate,rate_base_value,mode);
		
	//calculate discount cost for a tariff
	disAmt = calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value);
		
	//calculate service cost for a tariff
	serAmt = calTrfServiceCost(trfAmt,disAmt,ser_tax,mode);
	
		
	//calculate net cost for a tariff on which penelty will be calculated
	var val1 = manipulateValue(trfAmt,serAmt,0);
	var val2 = manipulateValue(val1,disAmt,0);
	
	/*
		calculate penelty cost for a tariff [used only in case of refund]
		Now we are assuming that on penelty cost, no services tax will be calculate, it will be substracted from the net amount
	*/
	penAmt = calTrfPeneltyCost(val2,penelty);
		
	netTrfAmt = manipulateValue(val2,penAmt,0);
	
	netTrfAmt = roundValue(netTrfAmt,2);

	
	//return value
	return {oTrfAmt:trfAmt,oDisAmt:disAmt,oSerAmt:serAmt,oPenAmt:penAmt,oNetTrfAmt:netTrfAmt , oActTrfAmt:actTrfAmt};
}




/*
	Description >> This JS function will be used to calculate the net amount. It will be generally used in IPD Bill Settlement 
	Parameters >> trfNetAmt :: Total Tariff Net Amount
				  dis_unit :: Discount/Unit	
				  dis_type :: 1 for Fixed, 2 for %
				  ser_tax :: Service Tax in %
				  max_client_benefit :: Client Benefit
				  recAmt :: Amount that is received from the patient
	
	Mandatory Input Parametr >> trfNetAmt, all other parameter could be zero
					
	Return Value >> Discount Amount, Service Tax Amount and Net Amount
	Return Parameter >> oDisAmt for Discount Amount
					 >> oSerAmt for Service Tax Amount
					 >> oNetAmt for Net Amount
*/
function calNetAmount(trfNetAmt,dis_unit,dis_type,ser_tax,max_client_benefit,recAmt) {
	//Initilize value
	var disAmt = 0.0;
	var serAmt = 0.0;
	var netAmt = 0.0;
	
	//business logic here

	//discount amount
	disAmt = calTrfDiscountCost(trfNetAmt,1,1,dis_unit,dis_type , 1);
	
	//Service Tax Amount
	serAmt = calTrfServiceCost(trfNetAmt,disAmt,ser_tax,0);
	
	//net amount
	var val1 = manipulateValue(trfNetAmt,serAmt,0);
	var val2 = manipulateValue(disAmt,recAmt,0);
	
	netAmt = roundValue(manipulateValue(val1,val2,0),2);
	
	//Return Value
	return {oDisAmt:disAmt,oSerAmt:serAmt,oNetAmt:netAmt};
}




/*
	Description >> This JS function is used to calculate net amount for all the tariffs
	Parameters >> Control Name for tariff net amount
	Return Value >> Returns Total Net Amount for all the tariffs
*/
function calAllTariffNetCost(tariff_amt_field_name) {
	//Initilize Value
	var allTrfNetCost = 0.0;
	var counter = 0;
	var amtValue = 0.0;
	var amtObj;
		
	//Business Logic
	if(tariff_amt_field_name != '') {
		amtObj = document.getElementsByName(tariff_amt_field_name);
		//length > 0
		if(amtObj.length >0) {
			for(counter = 0; counter < amtObj.length; counter++) {
				amtValue = amtObj[counter].value;
				if(isNaN(amtValue) || amtValue == '') amtValue = 0.0;
				allTrfNetCost = manipulateValue(parseFloat(allTrfNetCost),parseFloat(amtValue),0);
			}
		}
	}
	
	//Return Value
	return roundValue(allTrfNetCost,2);
}

/*
	Description >> This JS function is used to calculate tariff cost for a single tariff
	Return Value >> Returns Tariff Cost
*/
function calTariffCost(qty,qty_base_value,rate,rate_base_value,mode) {
	//initilize variavles
	var totCost = 0.0;
	
	//business logic
	if(rate_base_value > 0) {		//if it is 0 then result will be infinite
		totCost = parseFloat((qty * qty_base_value) * (rate/rate_base_value));
	}
	
	if(mode == 1) totCost = manipulateValue(0,totCost,1);
	
	//return value;
	return roundValue(totCost,2);
}



/*
	Description >> This JS function is used to calculate actual tariff cost for a single tariff
	Return Value >> Returns Actual Tariff Cost
*/
function calActualTariffCost(qty,qty_base_value,actualrate,rate_base_value,mode) {
	//initilize variavles
	var totCost = 0.0;
		
	//business logic
	if(rate_base_value > 0) {		//if it is 0 then result will be infinite
		totCost = parseFloat((qty * qty_base_value) * (actualrate/rate_base_value));
	}
	
	if(mode == 1) totCost = manipulateValue(0,totCost,1);
	
	//return value;
	return roundValue(totCost,2);
}


/*
	Description >> This JS function is used to calculate tariff discount cost for a single tariff. 
					Discount will be calculated on Total Tariff Amount
	Parameter >> trfAmt :: Tariff Cost (qty * rate)
	Return Value >> Returns Discount Amount
*/
function calTrfDiscountCost(trfAmt,qty,qty_base_value,dis_unit,dis_type,rate_base_value) {
	//initilize value
	var disCost = 0.0;
	var v_qty = 0.0;
	//business logic
	if(Math.abs(trfAmt) > 0 && dis_unit > 0 && (dis_type == 1 || dis_type == 2)) {
		
		v_qty = qty;
		if(trfAmt < 0 ) v_qty = 0 - (qty); 
		
		if(dis_type == 1) {		//if discount is fixed [discount will be on base unit. e.g. Rs. 5 on 1 kg, if user takes 500gm then discount = 2.5 ]
			disCost = parseFloat((v_qty * qty_base_value * dis_unit) / rate_base_value); 
			  //qty = 500gm then 500 * .001 * 5 (discount/unit = 5)
		}
		else {					//if discount is in %
			disCost = parseFloat(trfAmt * (dis_unit/100));
		}
	}
	
	//return value
	return (roundValue((0 - (disCost)),2));
}

/*
	Description >> This JS function is used to calculate Penelty Amount
					Penelty Amount will be calculated on Total tariff cost
	Parameters >> trfAmt :: Tariff Cost
				  penelty :: Penelty amount in %	
	Return Values >> Returns penelty amount
*/
function calTrfPeneltyCost(trfAmt,penelty) {
	//Initilize Value
	var penCost = 0.0;
	
	//Business Logic
	if(penelty > 0 ) {
		penCost = parseFloat(trfAmt * (penelty/100)); 
	}
	
		
	//Return value
	return (roundValue((0-(penCost)),2));
}

/*
	Description >> This JS function is used to calculate Service Cost for a single tariff
	Parameters >>trfAmt :: Tariff Cost
				disAmt :: Total Discount Amount on tariff
				ser_tax	:: tax that has to be imposed, it will be always in %
	Return Value >> Service Tax Amount [Service tax will be calculated on net (tariff cost - discount + penelty] 
*/
function calTrfServiceCost(trfAmt,disAmt,ser_tax,mode) {
	//initilize value
	var serCost = 0.0;
	
	var balAmt = parseFloat(manipulateValue(trfAmt,disAmt,0));
	
	//business logic
	if(ser_tax > 0) {
		if((mode == 0 && balAmt > 0) || mode == 1)
			serCost = parseFloat((balAmt) * (ser_tax/100));
	}
	
	//return value
	return roundValue(serCost,2);
}

/*End Amount Calculation function*/	

/**
	It is used to check duplicate the value. 
	Call this function when form is submitted.
	
	cntrlActualName : Control Actual Name that you want to show in alert e.g. Tariff Name
						It could be blamk
*/
function checkForDuplicate_submit(cntrlName,cntrlActualName)
{
	var cntrlObj;
	var cntrlLen = 0;
	var i = 0;
	var j = 0;
	var cntrlValue = "";
	var tempValue = "";
	var tempName = "";
	var msg = "";
	var retValue = true;
	
	//user defined control name
	if(cntrlActualName == null || cntrlActualName == "")  
		tempName = "Control Name";
	else
		tempName = cntrlActualName;
			
	if(cntrlName == null || cntrlName == "") {
		msg = "Provided Control Name is empty !!";
		retValue = false;
	}
	else {
		//get control name object
		cntrlObj = document.getElementsByName(cntrlName);
		cntrlLen = cntrlObj.length;
		if(cntrlLen > 0) {
			for(i = 0; i<cntrlLen;i++) {
				cntrlValue = cntrlObj[i].value;
				if(cntrlValue == null || cntrlValue == "") {
					msg = tempName + " value is empty !! \nPlease Select Value";
					cntrlObj[i].focus();
					retValue = false;
					break;
				}
				else {
					//inner for loop
					for(j = i+1;j<cntrlLen;j++) {
						tempValue = cntrlObj[j].value;
						if(tempValue == null || tempValue == "") {
							msg = tempName + " value is empty !! \nPlease Select Value";
							cntrlObj[j].focus();
							retValue = false;
							break;
						}
						else {
							//check for duplicacy
							if(cntrlValue.toUpperCase() == tempValue.toUpperCase()) {
								msg = "Duplicate " + tempName + " value exists !!";
								cntrlObj[j].focus();
								retValue = false;
								break;
							}
						}
					} //end inner for loop
					
					if(!retValue) break;
				}	
			}
		}
		else {
			msg = "No Record exists !!";
			retValue = false;
		}
	}
	
	if(!retValue) {
		alert(msg);
	}
	
	return retValue;
}

/**
	It is used to check duplicate the value. 
	Call this function when value is selected.
	
	cntrlActualName : Control Actual Name that you want to show in alert e.g. Tariff Name
						It could be blamk
	
	selCntrlObj : object of the control whose value is being selected					
*/
function checkForDuplicate_select(cntrlName,selCntrlObj,cntrlActualName)
{
	var cntrlObj;
	var cntrlLen = 0;
	var i = 0;
	var cntrlValue = "";
	var tempValue = "";
	var tempName = "";
	var msg = "";
	var counter = 0;
	var retValue = true;
	
	//user defined control name
	if(cntrlActualName == null || cntrlActualName == "")  
		tempName = "Control Name";
	else
		tempName = cntrlActualName;
			
	if(cntrlName == null || cntrlName == "") {
		msg = "Provided Control Name is empty !!";
		retValue = false;
	}
	else {
		//get control name object
		cntrlObj = document.getElementsByName(cntrlName);
		cntrlLen = cntrlObj.length;
		if(cntrlLen > 0) {
			//provided value
			tempValue = selCntrlObj.value;
			if(tempValue == null || tempValue == "") {
				//msg = tempName + " value is empty !! \nPlease Select Value";
				selCntrlObj.focus();
				retValue = false;
			}
			else {
				for(i = 0; i<cntrlLen;i++) {
					cntrlValue = cntrlObj[i].value;
					//inner for loop
					if(cntrlValue.toUpperCase() == tempValue.toUpperCase()) {
						//don't have index, so it is mandatory that one record will always match
						counter++;
						if(counter > 1) {
							msg = "Duplicate " + tempName + " value exists !!";
							selCntrlObj.value = "";
							cntrlObj[i].focus();
							retValue = false;
							break;
						}
					}
				}
			}
		}
		else {
			msg = "No Record exists !!";
			retValue = false;
		}
	}
	
	if(!retValue) {
		if(msg !="")
		alert(msg);
	}
	
	return retValue;
}




/*
* this function called when delete button is pressed in unit master. it will not show alert msg since it is already displayed
*
*
*/
function deleteRecords1()
{
	var combo1;
	var prevNext	=	null;
	var totalChk	=	0;
	var primarykey	=	"";
	var search		=	"";
	var str			=	"";
	var str			=	"";
			
	var mode = "DELETE";
	var len			= 	document.forms[0].chk.length;
	var no_of_combo	=	document.forms[0].no_of_combo.value;
		
	
	if(document.forms[0].combo !=null)
		combo1 		=	document.forms[0].combo;	
	
	//call in this file	 
	if(checkForDelete()==false) return ;
		 
	var divisionId		= document.forms[0].divisionId.value;
	var rec_per_page	= document.forms[0].record_per_page.value;
	var prevDivIndex 	= divisionId.substring(1,divisionId.length);
	var min_rec_len 	= parseInt(rec_per_page)*(parseInt(prevDivIndex) -1); 
	var max_rec_len 	= parseInt(rec_per_page) * parseInt(prevDivIndex);
	
	if(!isNaN(document.forms[0].chk.length))
	{
		for(var i=min_rec_len;i< max_rec_len && i < document.forms[0].chk.length;i++)
		{
			if(document.forms[0].chk[i].checked==true)
			{
				totalChk+=1;
				primarykey +=document.forms[0].chk[i].value;
		  		primarykey +="@"; // concatenating all chk value with @ symbols to a single variable to for easy manipulations like deleting the records and update
		  	}
		  }
	}
	else
		primarykey=document.forms[0].chk.value;
	
//	var retValue=confirm("Are You Sure Want To Delete Record(s)");	 	
//	if(retValue==false) return;
	
	if(prevNext	==	null) prevNext='1';
	
	if(no_of_combo >1)
		{
			for(i=0;i<combo1.length;i++)
				str += "&combo="+combo1[i].value;
		}
	else
		if(no_of_combo==1) 
			str += "&combo="+combo1.value;		
	
	
	var minrow		=	document.forms[0].minrow.value;	
	var max_rownum	=	document.forms[0].max_rownum.value;	
	var blockNo		=	document.forms[0].blockNo.value;  
	var divisionId	=	document.forms[0].divisionId.value;
	var search			=	"";
	var searchColumn	=	null;
	var rowNum			=	minrow;
	var params="CNTUnitMst.cnt?hmode="+mode+str+"&divisionId="+divisionId+"&chk="+primarykey+"&prevNext"+prevNext
					+"&rowNum=0&minrow="+minrow+"&blockNo="+blockNo+"&max_rownum="+max_rownum+"&search="+search+"&searchColumn="+searchColumn;
		
			
	xmlHttp.open("GET",params,true);
	document.getElementById("message").style.display='block';	
	document.getElementById('start').innerHTML ='<table align=center bgcolor="RED"><tr><td> <FONT size=3 color=\"white\">Wait Deleting Records...</FONT></td></tr></table>';
	
		
	xmlHttp.onreadystatechange = function()
	{ 
		if(xmlHttp.readyState ==4 )
		{
			if(xmlHttp.status == 200 || xmlHttp.status=='complete' )
			{
				var response 		= 	xmlHttp.responseText;
				
							
				var responseData	=	new Array();
				responseData	=	response.split("####");
				document.getElementById('message').innerHTML 	= 	responseData[6];
	        	document.forms[0].record_per_page.value			=	responseData[0];
				document.forms[0].no_of_combo.value				=	responseData[1];
				document.forms[0].actual_page_block.value		=	responseData[2];
				document.forms[0].totalpage.value				=	responseData[3];
				document.forms[0].divisionId.value				=	responseData[7];
				document.getElementById("start").innerHTML 		= 	responseData[4];
				document.getElementById("searchid").innerHTML	= 	responseData[5];
				document.getElementById("footer").style.display	=	'block';
				document.getElementById("a1").style.display		=	'none';
					
				var	curr_block		=	responseData[7];
			  	
			   	 if(document.getElementById("bb"+curr_block) !=null)
			   	{
			   		document.getElementById(curr_block).style.display='block';
			   		document.forms[0].divisionId.value=curr_block;
			   		changeDiv(curr_block);
			   		document.forms[0].divisionId.value=curr_block;
					document.getElementById("bb"+curr_block).style.color='red';
				}
			else
			    if(document.getElementById("bba1") !=null)
			   	{
			   	document.getElementById("a1").style.display='block';
			   	document.forms[0].divisionId.value="a1";
			 	document.getElementById("bba1").style.color='red';
				}						
			  	
			 }			
		}
	}
	
	xmlHttp.send(params);
}	



/** called ON DELETE IN UNIT MASTER TO SHOW AN ALERT MSG IF UNIT IS BASE UNIT 
	*/ 
	function isBaseUnitDelete(form1)
	{
		with(form1)
		{ 
			
					//alert("enter11"+document.forms[0].chk.length);
				for(i=0;i<=document.getElementsByName("chk").length;i++)
				{
					if(document.getElementsByName("chk")[i].checked==true)
					{
						var isBaseUnit = document.getElementsByName("chk")[i].value.split("$");
									
						if( isBaseUnit[1]=="0"){
						var con = confirm("Selected Unit is a Base Unit so all other Units \n " +
								"which are associated with this Unit will also be deleted \n" +
								"ARE YOU SURE YOU WANT TO DELETE THIS UNIT ?");
								if(con == true)
								{
									deleteRecords1("DELETE");
								}
								else{
									return false;
								}
							}else{
								deleteRecords("DELETE");
							}
					}
					
				}
		}
	}



/////////////////function to display view page of tariff master
function showViewTariff(form1)
{
	
	
		var chkObj = document.getElementsByName("chk");
		
		var len = chkObj.length;
	
		var countChk = 0;
			
				
				for(var i=0; i<len; i++)
					if(chkObj[i].checked)	
						countChk = countChk + 1;
					
			if(countChk != 1){
				alert("Please Select One Record");
				return false;
			}
			
				
		for(var i=0; i<len; i++){
			
			if(chkObj[i].checked){	
			url = 'CNTTrfMst.cnt?hmode=viewPage'+'&chk='+chkObj[i].value;
			window.open(url,"popupWindow","width=610,height=450,scrollbars=yes");
			}
		}
			
}



function checkModule(form1)
{
	
	var cmbVal = "";
	with(form1)
	{
	
		if(combo[0].value == "0")
		{
			alert("Please Select Module Name");
			return;
		}
		else
		{
			
			
			 add("ADD");
			 
			
		}	
	}	
}

//logic for adding consumable charges in case of multi row
//format of strGroupIdForConsumableConcatenated field wiil be
// groupid@count^groupid2^count
function addConsumableCharge(groupid,groupConsumableCharge)
{
	 	var concatString=document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value
	 	var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value
	 	
	 	var groupAddedFlag=false;
	 	
	 	
	 	if(concatString!=null && concatString!="")
	 	{
	 		
	 		var strArrayOFGroup=concatString.split('^');
	 		var oldvalue=''
		 	var newvalue=''
	 		//when only one group is added strArrayOFGroup acts as a single string and not as array
	 		if(strArrayOFGroup.length!='undefined')
	 		{
		 		for(i=0;i<strArrayOFGroup.length;i++)
		 		{
		 		//alert("strArrayOFGroup[i]="+strArrayOFGroup[i]+"groupid="+groupid)
		 			if(strArrayOFGroup[i].split('@')[0]==groupid)
		 			{
		 			groupAddedFlag=true;
		 			//if group is added increase count
		 			strArrayOFGroup[i].split('@')[1]=parseInt(strArrayOFGroup[i].split('@')[1])+1;
		 			 oldvalue=strArrayOFGroup[i];
		 			 newvalue=groupid+'@'+(parseInt(strArrayOFGroup[i].split('@')[1])+1);
		 			}
		 		}
		 	}
		 	else{
		 	
		 		if(strArrayOFGroup.split('@')[0]==groupid)
		 			{
		 			groupAddedFlag=true;
		 			//if group is added increase count
		 			 oldvalue=strArrayOFGroup;
		 			 newvalue=groupid+'@'+(parseInt(strArrayOFGroup.split('@')[1])+1);
		 			
		 			}
		 	}
	 		
	 		if(!groupAddedFlag)//if group is not added 
	 		{
	 		
	 		document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value=concatString+'^'+groupid+'@'+1;
	 		document.getElementsByName("strConsumableCharge")[0].value=parseInt(consumableCharge)+parseInt(groupConsumableCharge);
	 		}else//replace old value with new value of increase count
	 		{
	 		document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value=document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value.replace(oldvalue,newvalue);
	 		}
	 		
	 	
	 	}
	 	else{
	 	
	 		document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value=groupid+'@'+1;
	 		document.getElementsByName("strConsumableCharge")[0].value=groupConsumableCharge;
	 	}
	 	
	 	//alert(document.getElementsByName('strGroupIdForConsumableConcatenated')[0].value)
}
	


//logic for removing consumale charge in case of multi roc
function removeConsumableAmount(groupid,groupConsumableCharge)
{
	 
	 //var temp = document.getElementById("strOfflineTariffDetailsHidden"+index).value.split('^');
	 //var groupid =temp[1];
	 //var groupConsumableCharge =temp[18];
	 
	 var concatString=document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value;
	 var consumableCharge=document.getElementsByName("strConsumableCharge")[0].value;
	 
	 var groupAddedFlag=false;
	 	
	 	
	 	if(concatString!=null && concatString!="")
	 	{
	 		
	 		var strArrayOFGroup=concatString.split('^');
	 		var oldvalue=''
		 	var newvalue=''
	 		//when only one group is added strArrayOFGroup acts as a single string and not as array
	 		if(strArrayOFGroup.length!='undefined')
	 		{
		 		for(i=0;i<strArrayOFGroup.length;i++)
		 		{
		 		//alert("strArrayOFGroup[i]="+strArrayOFGroup[i]+"groupid="+groupid)
		 			if(strArrayOFGroup[i].split('@')[0]==groupid)
		 			{
		 			groupAddedFlag=true;
		 			//if group is added decrease count
		 			//strArrayOFGroup[i].split('@')[1]=parseInt(strArrayOFGroup[i].split('@')[1])+1
		 			 oldvalue=strArrayOFGroup[i];
		 			 newvalue=groupid+'@'+(parseInt(strArrayOFGroup[i].split('@')[1])-1);
		 			}
		 		}
		 	}
		 	else{
		 	
		 		if(strArrayOFGroup.split('@')[0]==groupid)
		 			{
		 			groupAddedFlag=true;
		 			//if group is added decrease count
		 			 oldvalue=strArrayOFGroup;
		 			 newvalue=groupid+'@'+(parseInt(strArrayOFGroup.split('@')[1])-1);
		 			
		 			}
		 	}
	 		
	 		if(groupAddedFlag)//if group is  added 
	 		{
	 			if(newvalue.split('@')[1]==0)
	 			{
		 		document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value=document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value.replace(oldvalue,'');
		 		document.getElementsByName("strConsumableCharge")[0].value=parseInt(consumableCharge)-parseInt(groupConsumableCharge);
		 		}
		 		else{
		 		document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value=document.getElementsByName("strGroupIdForConsumableConcatenated")[0].value.replace(oldvalue,newvalue);
		 		}
	 		}
	 		
	 	
	 	}
	 	
	 
}
	
function showChargeDtl(xaxis,yaxis)
{
	var trfCharges=0;
	var consumableCharges=0;
	if(document.forms[0].strOfflineTotalPayAmountWithoutConsumable.value=="")
		trfCharges=0;
	else
		trfCharges=document.forms[0].strOfflineTotalPayAmountWithoutConsumable.value;
		
	if(document.forms[0].strConsumableCharge.value=="")
		consumableCharges=0;
	else
		consumableCharges=document.forms[0].strConsumableCharge.value;
	
	document.getElementById("tariffCost").innerHTML=roundValue(trfCharges,2);
	document.getElementById("consuambleCost").innerHTML=roundValue(consumableCharges,2);
	document.getElementById("chargeDtlDiv").focus();
	popup('chargeDtlDiv',xaxis,yaxis);	
}
function hideChargeDtl()
{
	 	hide_popup('chargeDtlDiv');
}
