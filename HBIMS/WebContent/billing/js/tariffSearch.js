
	var gblUserFunc ="";
	var gblUserArg = "";
	
	var defaultColor = "blue";
	var setColor = "red";

function tariffSearchPopUp(evt,textId,chargeTypeId,categoryCode,wardCode,groupId,userFunctionName,userArgument)
{		
	var 	strCategoryCode=categoryCode.split("^")[0];
	evt = (evt) ? evt : ((window.event) ? event : null);
		 if (evt) 
		 {
		 	
			 //if ((evt.keyCode == 83 && evt.ctrlKey && evt.altKey) || (evt.keyCode == 13 && document.getElementById(textId).value=="") ) //Alt+Ctr+S or Enter Key 
			 if ((evt.keyCode == 83 && evt.ctrlKey && evt.altKey)) //Alt+Ctr+S or Enter Key
        	 {
				
				 if(document.getElementById("dropdown1") != null)
				 {
					 	document.getElementById("dropdown1").style.display = "none";
						document.getElementById(textId).focus();
					 	
					 	if(document.getElementById("realitems1") != null)
					 	{	  
					 		 var output= document.getElementById("realitems1").options; 
		 					 output.selectedIndex = -1;
					 	}
				}
				
				gblUserFunc = userFunctionName;
				gblUserArg = userArgument;
								
				var hmode = "POPUP"; 
				var searchText = document.getElementById(textId).value;
				var featuresList = "method=post,width=600,height=400,align=center,left=300,top=300,scrollbars=yes";
				
				var myWindow = window.open(createFHashAjaxQuery("BillingCNT.cnt?hmode="+hmode+"&searchText="+searchText+"&searchType=1&chargeTypeId="+chargeTypeId+"&categoryCode="+strCategoryCode+"&wardCode="+wardCode+"&groupId="+groupId+"&usrFuncName="+userFunctionName+"&usrArg="+userArgument,"popupWindow",featuresList)); 
		
				/*if(! myWindow.opener){
					myWindow.opener = window;
				}*/
			
			}
		}
	}
	
	
	function tariffSearchPopUpWithoutEvent(textId,chargeTypeId,categoryCode,wardCode,groupId,userFunctionName,userArgument){
		
		var 	strCategoryCode=categoryCode.split("^")[0];
				
				if(document.getElementById("dropdown1") != null){
						document.getElementById("dropdown1").style.display = "none";
						document.getElementById(textId).focus();
					 	
					 	if(document.getElementById("realitems1") != null){	  
	  						var output= document.getElementById("realitems1").options; 
		 					output.selectedIndex = -1;
					 	}
				}
				
				gblUserFunc = userFunctionName;
				gblUserArg = userArgument;
								
				var hmode = "POPUP"; 
				var searchText = document.getElementById(textId).value;
				var featuresList = "method=post,width=600,height=400,align=center,left=300,top=300,scrollbars=yes"
					
				var myWindow = window.open(createFHashAjaxQuery("BillingCNT.cnt?hmode="+hmode+"&searchText="+searchText+"&searchType=1&chargeTypeId="+chargeTypeId+"&categoryCode="+strCategoryCode+"&wardCode="+wardCode+"&groupId="+groupId+"&usrFuncName="+userFunctionName+"&usrArg="+userArgument,"popupWindow",featuresList)); 
		
							
		
	}
	
		
	function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		initObj = document.getElementsByName("linId");
		
		if(initObj.length > 0){
			
			for(var i = 0 , len = initObj.length ; i < len ; i ++ ){
				
				initObj[i].style.color = defaultColor;
			}
			
	}
		
		
		
		obj = document.getElementById("linId"+index);
		if(obj != null) obj.style.color = setColor;
		
		document.getElementById("tariffDivId"+index).style.display="block";
	}
   
  /* function layerIndexNavigator(index,endVal){
		
			hideAll(endVal);
		
		document.getElementById("tariffDivId"+index).style.display="block";
	}
   */
function hideAll(endVal){
	
		for(var i = 1; i <= endVal ; i++){
			document.getElementById("tariffDivId"+i).style.display="none";
		}
		
	}
	
	var gblTempFlag = false;

function setTariffName(valObj,textVal){
	
	var val = document.forms[0].usrArg.value;
	
	if(gblTempFlag){
				
		var temVal = val.split("-");
		
		var newIndex = parseInt(temVal[1]) + 1;
		
		val = temVal[0]+"-"+newIndex;
			
		
		if(window.opener.document.getElementById("strOfflineTariffName"+val) == null){
					
			newIndex = newIndex - 1;
			
			val = temVal[0]+"-"+newIndex;
		}
		
	}
	gblTempFlag = true;
	
	document.forms[0].usrArg.value = val;
	
//	window.close();
	window.opener.eval(document.forms[0].usrFuncName.value+"('"+document.forms[0].usrArg.value+"','"+textVal+"','"+valObj.value+"')");
	
}


	function getSearchContent(eve)
	{
		var retValue= validateData(eve,19);
		if(eve.keyCode == 13)
		{
			searchValidate();
			return false;
		}
		return false && retValue;	
	}
	
	function isSearchRequired(){
	
		if(document.forms[0].strTariffName.value.length > 0){
		
			myFunc('1');
		
		}
			
	}
	

function searchValidate()
{
	var hisValidator = new HISValidator("billingBean");  
		
	var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
	
	if(searchType == 2)
	{
		hisValidator.addValidation("strTariffName", "req", "Tariff Code is a Mandatory Field" );
	}
	else
		hisValidator.addValidation("strTariffName", "req", "Tariff Name is a Mandatory Field" );
	

	var retVal = hisValidator.validate();		 
	hisValidator.clearAllValidations();
	
	if(retVal)
	{				
			myFunc('1');
	}
	else
	{
		return false;
	}			
}


function myFunc(mode)
{
	if(mode == '1')
	{
		var convString = "";
  		var searchString = document.forms[0].strTariffName.value;
  		var searchType = document.forms[0].strSearchType[document.forms[0].strSearchType.selectedIndex].value;
  		convString = searchString;
  		
  		if(searchString.indexOf('%') != -1)
  		{  			
  			var searchArr = searchString.split("%");
  			for(var i=0;i<searchArr.length;i++)
  				if(i == 0)
  					convString = searchArr[i];
  				else
  					convString = convString + "$" + searchArr[i];
  		}
		
		var hmode = "TARIFFLIST"; 
		var url = "BillingCNT.cnt?hmode="+hmode+"&chargeTypeId="+document.forms[0].chargeTypeId.value+"&categoryCode="+document.forms[0].categoryCode.value+"&wardCode="+document.forms[0].wardCode.value+"&groupId="+document.forms[0].groupId.value+"&searchText="+convString+"&searchType="+searchType;
		ajaxFunction2(url,"1","getTariffDetailAjaxResponse");
	}
	
	
	}
	
	function getTariffDetailAjaxResponse(res,mode)
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
			$('#table_id').DataTable({"ordering": false,"pageLength":25});
		}
		
	
	}


	function showHelp(divId){
		
		document.getElementById(divId).style.display="block";
		
		document.getElementById('minusId').style.display="block";
		document.getElementById('plusId').style.display="none";
		
	}
	
	function hideHelp(divId){
		document.getElementById(divId).style.display="none";
		
		document.getElementById('minusId').style.display="none";
		document.getElementById('plusId').style.display="block";
	}
	
	function ESCclose(evt) 
	{
	  if (evt.keyCode == 27) 
	   window.close();
 	}
