
	var gblTrfCodeUserFunc ="";
	var gblTrfCodeUserArg = "";
	
	var defaultTrfCodeColor = "white";
	var setTrfCodeColor = "red";

function tariffCodeSearchPopUp(evt,textId,chargeTypeId,categoryCode,wardCode,groupId,userFunctionName,userArgument)
{		
	var 	strCategoryCode=categoryCode.split("^")[0];
	evt = (evt) ? evt : ((window.event) ? event : null);
		
		 if (evt) {
		 	
        	if ((evt.keyCode == 83 && evt.ctrlKey && evt.altKey) || (evt.keyCode == 13 && document.getElementsByName(textId)[0].value=="") ) //Alt+Ctr+S or Enter Key 
        	{
        		//If Enter Key Pressed When Alert or Prompt or Confirm Then Do Not Open Tariff Code Search Popup 
        		if(document.forms[0].strAlertPromptConfirmFlag!=undefined && document.forms[0].strAlertPromptConfirmFlag.value != null && document.forms[0].strAlertPromptConfirmFlag.value != "" && document.forms[0].strAlertPromptConfirmFlag.value=="1")
        		{
        			document.forms[0].strAlertPromptConfirmFlag.value="0";
        			return;
        		}        		
				gblTrfCodeUserFunc = userFunctionName;
				gblTrfCodeUserArg = userArgument;
								
				var hmode = "TRFCODEPOPUP"; 
				var searchText = document.getElementById(textId).value;
				var featuresList = "method=post,width=600,height=400,align=center,left=300,top=300,scrollbars=yes";
					
				var myWindow = window.open(createFHashAjaxQuery("BillingCNT.cnt?hmode="+hmode+"&searchText="+searchText+"&searchType=1&chargeTypeId="+chargeTypeId+"&categoryCode="+strCategoryCode+"&wardCode="+wardCode+"&groupId="+groupId+"&usrFuncName="+userFunctionName+"&usrArg="+userArgument,"popupWindow",featuresList)); 
		
				/*if(! myWindow.opener){
					myWindow.opener = window;
				}*/
			
			}
		}
	}

function tariffCodeSearchPopUp1(evt,textId,chargeTypeId,categoryCode,wardId,groupId,userFunctionName,userArgument,wardCode)
{	
	console.log("method called");
	var 	strCategoryCode=categoryCode.split("^")[0];
	evt = (evt) ? evt : ((window.event) ? event : null);
		
		 if (evt) {
		 	
        	if ((evt.keyCode == 83 && evt.ctrlKey && evt.altKey) || (evt.keyCode == 13 && document.getElementsByName(textId)[0].value=="") ) //Alt+Ctr+S or Enter Key 
        	{
        		//If Enter Key Pressed When Alert or Prompt or Confirm Then Do Not Open Tariff Code Search Popup 
        		if(document.forms[0].strAlertPromptConfirmFlag!=undefined && document.forms[0].strAlertPromptConfirmFlag.value != null && document.forms[0].strAlertPromptConfirmFlag.value != "" && document.forms[0].strAlertPromptConfirmFlag.value=="1")
        		{
        			document.forms[0].strAlertPromptConfirmFlag.value="0";
        			return;
        		}        		
				gblTrfCodeUserFunc = userFunctionName;
				gblTrfCodeUserArg = userArgument;
								
				var hmode = "TRFCODEPOPUP1"; 
				var searchText = document.getElementById(textId).value;
				var featuresList = "method=post,width=800,height=400,align=center,left=300,top=300,scrollbars=yes";
					
				console.log("BillingCNT.cnt?hmode="+hmode+"&searchText="+searchText+"&searchType=1&chargeTypeId="+chargeTypeId+"&categoryCode="+strCategoryCode+"&wardId="+wardId+"&groupId="+groupId+"&usrFuncName="+userFunctionName+"&usrArg="+userArgument+"&wardCode="+wardCode,"popupWindow",featuresList);
				var myWindow = window.open(createFHashAjaxQuery("BillingCNT.cnt?hmode="+hmode+"&searchText="+searchText+"&searchType=1&chargeTypeId="+chargeTypeId+"&categoryCode="+strCategoryCode+"&wardId="+wardId+"&groupId="+groupId+"&usrFuncName="+userFunctionName+"&usrArg="+userArgument+"&wardCode="+wardCode,"popupWindow",featuresList)); 
		
				/*if(! myWindow.opener){
					myWindow.opener = window;
				}*/
			
			}
		}
	}

function openPackageHLP(id)
{
	
	packageId=id;
	var strCrNo=document.forms[0].strComboVal.value;
	var strAcctNo=document.forms[0].strAcctNo.value;
	var hmode = "PACKAGE"; 
	var featuresList = "method=post,width=700,height=300,align=center,left=300,top=300,scrollbars=yes,location=0";
	var myWindow = window.open(createFHashAjaxQuery("BillingCNT.cnt?hmode="+hmode+"&strCrNo="+strCrNo+"&strAcctNo="+strAcctNo+"&strPackageId="+packageId,"popupWindow",featuresList));
}
		
function trfCodelayerIndexNavigator(index,endVal)
{
	trfCodehideAll(endVal);
	initObj = document.getElementsByName("linId");
	if(initObj.length > 0)
	{
		for(var i = 0 , len = initObj.length ; i < len ; i ++ )
		{				
			initObj[i].style.color = defaultTrfCodeColor;
		}			
	}
	obj = document.getElementById("linId"+index);
	if(obj != null) obj.style.color = setTrfCodeColor;
	document.getElementById("tariffDivId"+index).style.display="block";
}
   
  /* function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		document.getElementById("tariffDivId"+index).style.display="block";
	}
   */
function trfCodehideAll(endVal){
	
		for(var i = 1; i <= endVal ; i++){
			document.getElementById("tariffDivId"+i).style.display="none";
		}
		
	}

function setTariffCode(valObj,textVal){
	//alert(valObj.value);
	//alert(textVal);
	window.close();
	window.opener.eval(document.forms[0].usrFuncName.value+"('"+document.forms[0].usrArg.value+"','"+textVal+"','"+valObj.value+"')");
	
}
function setTariffCode1(valObj,textVal){
	//alert(valObj.value);
	//alert(textVal);
	window.close();
	window.opener.eval(document.forms[0].usrFuncName.value+"('"+document.forms[0].usrArg.value+"','"+textVal+"','"+valObj+"')");
	
}


	function getTariffCodeSearchContent(eve){
		var retValue= validateData(eve,19);
		//alert(eve.keyCode);
	//	if(eve.keyCode == 13){
		if(eve.keyCode == 40){
			
			document.forms[0].strTariffVal.focus();
		}
			
			searchTariffCodeValidate();
		//}
		
		
		//return false && retValue;
	
	}
	function getTariffCodeSearchContent1(eve){
		document.getElementById("tariffSearchDivID").style.display="none";
		document.getElementById("id1").style.display="block";
		var retValue= validateData(eve,19);
		//alert(eve.keyCode);
	//	if(eve.keyCode == 13){
		if(eve.keyCode == 40){
			
			document.forms[0].strTariffVal.focus();
		}
			
			searchTariffCodeValidate1();
		//}
		
		
		//return false && retValue;
	
	}
	function isTariffCodeSearchRequired(){
	
		if(document.forms[0].strTariffName.value.length > 0){
		
			myTariffCodeFunc('1');
		
		}
			
	}
	

	function searchTariffCodeValidate(){
		
		
		if(document.forms[0].strTariffName.value.length == 0){
			
			return false;
		
		}
		
		var hisValidator = new HISValidator("billingBean");  
			
		hisValidator.addValidation("strTariffName", "req", "Tariff Name is a Mandatory Field!!!!" );
	
		var retVal = hisValidator.validate();
		 
		hisValidator.clearAllValidations();
		
		if(retVal){
				
				myTariffCodeFunc('1');
				
		}else{
		
		return false;
		}
			
			
	}
	
	function searchTariffCodeValidate1(){
		
		
		/*if(document.forms[0].strTariffName.value.length == 0){
			
			return false;
		
		}
		
		var hisValidator = new HISValidator("billingBean");  
			
		hisValidator.addValidation("strTariffName", "req", "Tariff Name is a Mandatory Field!!!!" );
	
		var retVal = hisValidator.validate();
		 
		hisValidator.clearAllValidations();*/
		
		if(true){
				//alert(document.getElementsByName("strSearchType")[0].value);
				
				myTariffCodeFunc1(document.getElementsByName("strSearchType")[0].value);
				
		}else{
		
		return false;
		}
			
			
	}


function myTariffCodeFunc(mode){

	if(mode == '1')
	{
		
			var convString = "";
  		
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
		
		var hmode = "TARIFFCODELIST"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardCode.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(url,"3","getTariffCodeDetailAjaxResponse");
	}
	if(mode == '2')
	{
		
			var convString = "";
  		
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
		
		var hmode = "TARIFFCODELIST"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardCode.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(createFHashAjaxQuery(url),"3","getTariffCodeDetailAjaxResponse");
	}
	if(mode == '3')
	{
		
			var convString = "";
  		
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
  		
		var hmode = "TARIFFCODELIST3"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardCode.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(createFHashAjaxQuery(url),"3","getTariffCodeDetailAjaxResponse");
	}
	
	
	}
	
function myTariffCodeFunc1(mode){

	if(mode == '1')
	{
		
			var convString = "";
  		
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
		
		var hmode = "TARIFFCODELIST1"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardId.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(createFHashAjaxQuery(url),"1","getTariffCodeDetailAjaxResponse");
	}
	if(mode == '2')
	{
		
			var convString = "";
  		
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1){
  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
  		
		var hmode = "TARIFFCODELIST2"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardCode.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(createFHashAjaxQuery(url),"2","getTariffCodeDetailAjaxResponse");
	}
	
	
	}
	function getTariffCodeDetailAjaxResponse(res,mode)
	{
		var err = document.getElementById("errMsg");
     
        var temp = res.split("####");
        
       if(temp[0] == "ERROR")
	   {
          	err.innerHTML = temp[1];
          	return;
       } 
		
		if(mode == '1')
		{

			var objEle = document.getElementById("tariffSearchDivID");
			objEle.innerHTML = res;
			document.getElementById("id1").style.display="none";
			document.getElementById("tariffSearchDivID").style.display="block";
			$('#table_id').DataTable({"ordering": false,"pageLength":25});
		}
		if(mode == '2')
		{

			var objEle = document.getElementById("tariffSearchDivID");
			objEle.innerHTML = res;
			document.getElementById("id1").style.display="none";
			document.getElementById("tariffSearchDivID").style.display="block";
			$('#table_id').DataTable({"ordering": false,"pageLength":25});
		}
		if(mode == '3')
		{

			var objEle = document.getElementById("tariffSearchDivID");
			objEle.innerHTML = res;
			//document.getElementById("id1").style.display="none";
			document.getElementById("tariffSearchDivID").style.display="block";
			$('#table_id').DataTable({"ordering": false,"pageLength":25});
		}
		
	
	}


	function trfCodeshowHelp(divId){
		
		document.getElementById(divId).style.display="block";
		
		document.getElementById('minusId').style.display="block";
		document.getElementById('plusId').style.display="none";
		
	}
	
	function trfCodehideHelp(divId){
		document.getElementById(divId).style.display="none";
		
		document.getElementById('minusId').style.display="none";
		document.getElementById('plusId').style.display="block";
	}
	function ESCclose(evt) 
	{
	  if (evt.keyCode == 27) 
	   window.close();
 	}
	
	
