/**
 * @author Aadil
 */
// to generate unique id for each row..multirow..
var globalUniqueId =new Date().getTime();
var varArrRenewalTypeMode = ["none", "Gen", "Emg","Spl"];
var varArrRenewalTypeMode = ["none", "Gen", "Emg","Spl"];
var monthOptions= "<option value=''>Select Value</option>"+
				    "<option value='Jan'>Jan</option>"+
				    "<option value='Feb'>Feb</option>"+
				    "<option value='Mar'>Mar</option>"+
				    "<option value='Apr'>Apr</option>"+
				    "<option value='May'>May</option>"+
				    "<option value='Jun'>Jun</option>"+
				    "<option value='Jul'>Jul</option>"+
				    "<option value='Aug'>Aug</option>"+
				    "<option value='Sep'>Sep</option>"+
				    "<option value='Oct'>Oct</option>"+
				    "<option value='Nov'>Nov</option>"+
				    "<option value='Dec'>Dec</option>";
				    
var renewalTypeOptions ="<option value='0'>No Renewal</option>"+
					    "<option value='2'>Hospital-Wise For Particular Group</option>"+
					    "<option value='3'>Department Unit-Wise Common To All</option>"+
					    "<option value='4'>Department Unit-Wise Unit Specific From Unit Master</option>";

var dayOrMonthWiseOptions= "<option value='1'>Day-Wise</option>"+
							"<option value='2'>Month-Wise</option>";
var multipleMonthOptions = "<option value=''>Select Value</option>"+
						    "<option value='0'>No</option>"+
						    "<option value='1'>Bi-Annually</option>"+
						    "<option value='2'>Tri-Annually</option>";
var categoryOptions="<option value='-1'>Select Value</option>";
var msgToAppend="";

function getUniqueId()
{
    var uniqueId = ++globalUniqueId;
     return uniqueId;
}

// to delete row from multirow..
function DeleteRows(rowId)
{ 			
	var tr1;
 	tr1=document.getElementById(rowId);
 	tr1.parentNode.removeChild(tr1);	
}
	
	

	//to make div table row
function createTableRow(tableId, rowName, rowclass,uniqueId){
	
	//alert("tableId :"+tableId+", rowName :"+rowName+", rowclass :"+rowclass+", uniqueId :"+uniqueId);
 	var rId=rowName + uniqueId;
 	//alert("createTableRow() --> rId :"+rId); 	    	
 	var $div = $("<div>", {id: rId, class: rowclass});
 	$("#"+ tableId).append($div);
 	return rId; 	
}

function populateFormOnLoad(){
	console.log("document.forms[0].flagAddMod.value :"+document.forms[0].flagAddMod.value);
	
	categoryOptions= $("#strPatCatOptionsId").html();
	//if (document.forms[0].flagAddMod.value == 2) {
		var iVal=0;
		var strRenewalTypeCommonToAll = document.forms[0].strRenewalTypeCommonToAll.value;
		if( strRenewalTypeCommonToAll == "1"){
			document.forms[0].chkCommonCode.checked = true;
		}else{
			document.forms[0].chkCommonCode.checked = false;
			iVal=1;
		}
		
		showHidedivHospitalWiseCommon();
		
		console.log("renewalTypeMode.Length :"+varArrRenewalTypeMode.length);
		for(var i=iVal; i<varArrRenewalTypeMode.length; i++){
			var renewalTypeMode= varArrRenewalTypeMode[i];
			console.log("renewalTypeMode :"+renewalTypeMode);
			if(renewalTypeMode=="none")
				renewalTypeMode="";
			
			console.log(renewalTypeMode+"IndexId :"+ renewalTypeMode+"IndexId");
			var objIndex= document.getElementsByName(renewalTypeMode+"IndexId") ;
			console.log("objIndex.length :"+ objIndex.length);
			for(var j=0; j<objIndex.length; j++){
				var indexId = document.getElementsByName(renewalTypeMode+"IndexId")[j].value ;
				//console.log("indexId :"+indexId);
				var fn = "renewalTypeDayOrMonth"+renewalTypeMode+"(document.getElementsByName('strRenewalType"+renewalTypeMode+"')["+j+"],"+indexId+")";
				//console.log("fn :"+fn);
				eval(fn);
			}
			if(strRenewalTypeCommonToAll == "1"){
				break;
			}
		}
		
		
		if(document.getElementsByName("sizeOfHospWiseCommonRenewalConfig")[0].value==0)
			createMultiRow1('none');
		if(document.getElementsByName("sizeOfNotHospWiseCommonRenewalConfigGen")[0].value==0)
			createMultiRow1('Gen');
		if(document.getElementsByName("sizeOfNotHospWiseCommonRenewalConfigEmg")[0].value==0)
			createMultiRow1('Emg');
		if(document.getElementsByName("sizeOfNotHospWiseCommonRenewalConfigSpl")[0].value==0)
			createMultiRow1('Spl');
		
	//}
}



function createMultiRow1(mode,visibility){
	console.log("inside createMultiRow1()");
	if(!validateAllForSave(mode))
		return false;
	
	var notText="Not";
	var DayOrMonthWiseIdWidth;
	var DayOrMonthWiseIdVisibility="";
	var modeIndex=varArrRenewalTypeMode.indexOf(mode);
	if(mode=="none"){
		mode="";
		DayOrMonthWiseIdWidth=75;
		DayOrMonthWiseIdVisibility="";
		notText="";
	}else{
		DayOrMonthWiseIdWidth=50;
		DayOrMonthWiseIdVisibility="style='display: none;'";
	}
	var rId="div"+notText+"HospWiseCommon"+mode+"RowId";
 	var tableId="div"+notText+"HospWiseCommon"+mode+"Id";
 	var rowclass="div-table-row";
	var uniqueId=getUniqueId();
 	var newRowId= createTableRow(tableId,rId,rowclass,uniqueId);  
 	var appendedRowHTML=
		"<input type='hidden' name='strRenewalGroupGen' value='1' />"+
		"<input type='hidden' name='"+mode+"IndexId' value="+uniqueId+">"+
		"<div class='div-table-col width10 label'>Category</div>"+
		"<div class='div-table-col width10 control'>"+
			"<select name='strRenewalPatCat"+mode+"' id='strRenewalPatCat"+mode+ uniqueId+"' class='select90prcnt' onchange='validateCategory(this,"+modeIndex+","+uniqueId+");' >"+categoryOptions+"</select>"+
		"</div>";
	if(mode!=""){
		appendedRowHTML=appendedRowHTML+
		"<div class='div-table-col width10 label'>Renewal Type</div>"+
		"<div class='div-table-col width15 control'>"+
			"<select name='strRenewalType"+mode+"' id='strRenewalType"+mode+ uniqueId+"' class='select90prcnt' onchange='renewalTypeDayOrMonth"+mode+"(this,"+uniqueId+");' >"+renewalTypeOptions+"</select>"+
		"</div>";
	}
	
	appendedRowHTML=appendedRowHTML+
		"<div class='div-table-col width"+DayOrMonthWiseIdWidth+" label' >"+
			"<div class='div-table' id='div"+notText+"HospWiseCommon"+mode+"DayOrMonthWiseId"+uniqueId+"' "+DayOrMonthWiseIdVisibility+">"+
				"<div class='div-table-row'>"+
					"<div class='div-table-col width40'>"+
						"<div class='div-table'>"+
							"<div class='div-table-row'>"+
								"<div class='div-table-col width60 label' >Day/Month Wise</div>"+
								"<div class='div-table-col width40 control' >"+
									"<select name='strRenewalMode"+mode+"' id='strRenewalMode"+mode+"Id"+ uniqueId+"' class='select100prcnt' onchange='showdivDayWise"+mode+"(this,"+uniqueId+");' >"+dayOrMonthWiseOptions+"</select>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class='div-table-col width60' id='div"+notText+"HospWiseCommon"+mode+"DayWiseId"+uniqueId+"'>"+
						"<div class='div-table'>"+
							"<div class='div-table-row'>"+
								"<div class='div-table-col width65 label' >No of Days</div>"+
								"<div class='div-table-col width35 control' >"+
									"<input type='text' name='strNoOfDays"+mode+"' class='input70prcnt' maxlength='3' onkeypress='return validateNumericOnly(this,event);' >"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</div>"+
					"<div class='div-table-col width60' id='div"+notText+"HospWiseCommon"+mode+"MonthWiseId"+uniqueId+"' style='display: none;'>"+
						"<div class='div-table'>"+
							"<div class='div-table-row'>"+
								"<div class='div-table-col width50' >Is Multiple month</div>"+
								"<div class='div-table-col width50' >"+
									"<select name='strIsMultipleMonth"+mode+"' id='strIsMultipleMonth"+mode+ uniqueId+"' class='select90prcnt' onchange='showDvMultipleMonth"+mode+"(this,"+uniqueId+");' >"+multipleMonthOptions+"</select>"+
								"</div>"+
							"</div>"+
							"<div class='div-table-row' id='divMultipleNo"+mode+""+uniqueId+"' style='display: none;'>"+
								"<div class='div-table-col width35 label' >DD</div>"+
								"<div class='div-table-col width15 control' >"+
									"<input type='text' name='strDay1"+mode+"' class='input90prcnt' maxlength='2' onkeypress='return validateNumericOnly(this,event);'>"+
								"</div>"+
								"<div class='div-table-col width15 label' >MM</div>"+
								"<div class='div-table-col width35 control' >"+
									"<select class='select100prcnt' name='strMonth1"+mode+"'>"+monthOptions+" </select>"+
								"</div>"+
							"</div>"+
							"<div class='div-table-row' id='divMultipleBi"+mode+""+uniqueId+"' style='display: none;'>"+
								"<div class='div-table-col width35 label' >DD</div>"+
								"<div class='div-table-col width15 control' >"+
									"<input type='text' name='strDay2"+mode+"' class='input90prcnt' maxlength='2' onkeypress='return validateNumericOnly(this,event);' />"+
								"</div>"+
								"<div class='div-table-col width15 label' >MM</div>"+
								"<div class='div-table-col width35 control' >"+
									"<select class='select100prcnt' name='strMonth2"+mode+"'>"+monthOptions+" </select>"+
								"</div>"+
							"</div>"+
							"<div class='div-table-row' id='divMultipleTri"+mode+""+uniqueId+"' style='display: none;'>"+
								"<div class='div-table-col width35 label' >DD</div>"+
								"<div class='div-table-col width15 control' >"+
									"<input type='text' name='strDay3"+mode+"' class='input90prcnt' maxlength='2' onkeypress='return validateNumericOnly(this,event);' />"+
								"</div>"+
								"<div class='div-table-col width15 label' >MM</div>"+
								"<div class='div-table-col width35 control' >"+
									"<select class='select100prcnt' name='strMonth3"+mode+"'>"+monthOptions+" </select>"+
								"</div>"+
							"</div>"+
						"</div>"+
					"</div>"+
				"</div>"+
			"</div>"+
		"</div>"+
		"<div class='div-table-col width5'>"+
			"<img src='/HIS/hisglobal/images/avai/minus.gif' style='cursor: pointer;' onclick='DeleteRows(\"div"+notText+"HospWiseCommon"+mode+"RowId"+uniqueId+"\");'>"+
		"</div>";
	
	//console.log("appendedRowHTML :"+appendedRowHTML);
	if(visibility=="hide")
		$("#"+ newRowId).hide();
	else
		$("#"+ newRowId).show();
		
	$("#"+ newRowId).html(appendedRowHTML);
}

/*******************************  SHow Hide Divs  ****************************/
function showdivDayWise(obj,index) {
		if (obj.value == 1) {
			document.getElementById("divHospWiseCommonDayWiseId"+index).style.display = "";
			document.getElementById("divHospWiseCommonMonthWiseId"+index).style.display = "none";
			//showDvMultipleMonth(document.forms[0].temp.value,index);

		} else {
			document.getElementById("divHospWiseCommonDayWiseId"+index).style.display = "none";
			document.getElementById("divHospWiseCommonMonthWiseId"+index).style.display = "";
			//showDvMultipleMonth(document.forms[0].strIsMultipleMonth,index);
		}
		var objHospWiseCommonMultipleMonth = document.getElementById("strIsMultipleMonth"+index);
		showDvMultipleMonth(objHospWiseCommonMultipleMonth,index);
	}
	function showHidedivHospitalWiseCommon() {
		if (document.forms[0].chkCommonCode.checked == false) {
			document.getElementById("divHospWiseCommonId").style.display = "none";
			document.getElementById("divNotHospWiseCommonGenId").style.display = "";
			document.getElementById("divNotHospWiseCommonEmgId").style.display = "";
			document.getElementById("divNotHospWiseCommonSplId").style.display = "";
			document.getElementsByName("strRenewalTypeCommonToAll")[0].value="0";
		}
		if (document.forms[0].chkCommonCode.checked == true) {
			document.getElementById("divHospWiseCommonId").style.display = "";
			document.getElementById("divNotHospWiseCommonGenId").style.display = "none";
			document.getElementById("divNotHospWiseCommonEmgId").style.display = "none";
			document.getElementById("divNotHospWiseCommonSplId").style.display = "none";
			document.getElementsByName("strRenewalTypeCommonToAll")[0].value="1";
		}
	}

	function showDvMultipleMonth(obj,index) {
		if (obj.value == '')
		{
			document.getElementById("divMultipleNo"+index).style.display = "none";
			document.getElementById("divMultipleBi"+index).style.display = "none";
			document.getElementById("divMultipleTri"+index).style.display = "none";
		}
		else if (obj.value == 0) {//no
			document.getElementById("divMultipleNo"+index).style.display = "";
			document.getElementById("divMultipleBi"+index).style.display = "none";
			document.getElementById("divMultipleTri"+index).style.display = "none";
		} else if (obj.value == 1) {//bi
			document.getElementById("divMultipleNo"+index).style.display = "";
			document.getElementById("divMultipleBi"+index).style.display = "";
			document.getElementById("divMultipleTri"+index).style.display = "none";
		} else if (obj.value == 2) {//tri
			document.getElementById("divMultipleNo"+index).style.display = "";
			document.getElementById("divMultipleBi"+index).style.display = "";
			document.getElementById("divMultipleTri"+index).style.display = "";
		} else 
		{//else
			document.getElementById("divMultipleNo"+index).style.display = "none";
			document.getElementById("divMultipleBi"+index).style.display = "none";
			document.getElementById("divMultipleTri"+index).style.display = "none";
		}
	}

	function renewalTypeDayOrMonth(obj,index) {
		//alert("inside renewalTypeDayOrMonth()-- index :"+index);
		if (obj.value != 0 && obj.value != 4) {//no renewal
			document.getElementById("divHospWiseCommonDayOrMonthWiseId"+index).style.display = "";
		} else {
			document.getElementById("divHospWiseCommonDayOrMonthWiseId"+index).style.display = "none";
		}
		var objHospWiseCommonRenewalMode = document.getElementById("strRenewalModeId"+index);
		showdivDayWise(objHospWiseCommonRenewalMode,index);
	}

	function renewalTypeDayOrMonthGen(obj,index) {
		console.log("inside renewalTypeDayOrMonthGen() --> divNotHospWiseCommonGenDayOrMonthWiseId"+index);
		if (obj.value != 0 && obj.value != 4) {//no renewal
			document.getElementById("divNotHospWiseCommonGenDayOrMonthWiseId"+index).style.display = "";
		} else {
			document.getElementById("divNotHospWiseCommonGenDayOrMonthWiseId"+index).style.display = "none";
		}
		var objRenewalModeGen = document.getElementById("strRenewalModeGenId"+index);
		showdivDayWiseGen(objRenewalModeGen,index);

	}
	function renewalTypeDayOrMonthEmg(obj,index) {
		console.log("inside renewalTypeDayOrMonthEmg()");
		if (obj.value != 0 && obj.value != 4) {//no renewal
			document.getElementById("divNotHospWiseCommonEmgDayOrMonthWiseId"+index).style.display = "";
		} else {
			document.getElementById("divNotHospWiseCommonEmgDayOrMonthWiseId"+index).style.display = "none";
		}
		var objRenewalModeEmg = document.getElementById("strRenewalModeEmgId"+index);
		showdivDayWiseEmg(objRenewalModeEmg,index);

	}
	function renewalTypeDayOrMonthSpl(obj,index) {
		console.log("inside renewalTypeDayOrMonthSpl()");
		if (obj.value != 0 && obj.value != 4) {//no renewal
			document.getElementById("divNotHospWiseCommonSplDayOrMonthWiseId"+index).style.display = "";
		} else {
			document.getElementById("divNotHospWiseCommonSplDayOrMonthWiseId"+index).style.display = "none";
		}
		var objRenewalTypeSpl = document.getElementById("strRenewalModeSplId"+index);
		showdivDayWiseSpl(objRenewalTypeSpl ,index);

	}


	function showDvMultipleMonthGen(obj,index) {
		console.log("inside showDvMultipleMonthGen");
		if (obj.value == '') {//else
			document.getElementById("divMultipleNoGen"+index).style.display = "none";
			document.getElementById("divMultipleBiGen"+index).style.display = "none";
			document.getElementById("divMultipleTriGen"+index).style.display = "none";
		}
		else if (obj.value == 0) {//no
			document.getElementById("divMultipleNoGen"+index).style.display = "";
			document.getElementById("divMultipleBiGen"+index).style.display = "none";
			document.getElementById("divMultipleTriGen"+index).style.display = "none";
		} else if (obj.value == 1) {//bi
			document.getElementById("divMultipleNoGen"+index).style.display = "";
			document.getElementById("divMultipleBiGen"+index).style.display = "";
			document.getElementById("divMultipleTriGen"+index).style.display = "none";
		} else if (obj.value == 2) {//tri
			document.getElementById("divMultipleNoGen"+index).style.display = "";
			document.getElementById("divMultipleBiGen"+index).style.display = "";
			document.getElementById("divMultipleTriGen"+index).style.display = "";
		} else {//else
			document.getElementById("divMultipleNoGen"+index).style.display = "none";
			document.getElementById("divMultipleBiGen"+index).style.display = "none";
			document.getElementById("divMultipleTriGen"+index).style.display = "none";
		}
	}

	function showdivDayWiseGen(obj,index) {

		//console.log("inside showdivDayWiseGen()");
		//alert("obj :"+obj);
		//console.log("obj.value :"+obj.value);
		if (obj.value == 1) {
			document.getElementById("divNotHospWiseCommonGenDayWiseId"+index).style.display = "";
			document.getElementById("divNotHospWiseCommonGenMonthWiseId"+index).style.display = "none";
			//showDvMultipleMonthGen(document.forms[0].temp.value, index);
		} else {
			document.getElementById("divNotHospWiseCommonGenDayWiseId"+index).style.display = "none";
			document.getElementById("divNotHospWiseCommonGenMonthWiseId"+index).style.display = "";
			//showDvMultipleMonthGen(document.forms[0].strIsMultipleMonthGen, index);
		}
		var objMultipleMonthGen = document.getElementById("strIsMultipleMonthGen"+index);
		//console.log("objMultipleMonthGen :"+objMultipleMonthGen);
		showDvMultipleMonthGen(objMultipleMonthGen,index);
	}

	function showDvMultipleMonthEmg(obj,index) 
	{
		console.log("showDvMultipleMonthEmg()")
		if (obj.value == '') {//no
			document.getElementById("divMultipleNoEmg"+index).style.display = "none";
			document.getElementById("divMultipleBiEmg"+index).style.display = "none";
			document.getElementById("divMultipleTriEmg"+index).style.display = "none";
		}
		else if (obj.value == 0) {//no
			document.getElementById("divMultipleNoEmg"+index).style.display = "";
			document.getElementById("divMultipleBiEmg"+index).style.display = "none";
			document.getElementById("divMultipleTriEmg"+index).style.display = "none";
		} else if (obj.value == 1) {//bi
			document.getElementById("divMultipleNoEmg"+index).style.display = "";
			document.getElementById("divMultipleBiEmg"+index).style.display = "";
			document.getElementById("divMultipleTriEmg"+index).style.display = "none";
		} else if (obj.value == 2) {//tri
			document.getElementById("divMultipleNoEmg"+index).style.display = "";
			document.getElementById("divMultipleBiEmg"+index).style.display = "";
			document.getElementById("divMultipleTriEmg"+index).style.display = "";
		} else {//else
			document.getElementById("divMultipleNoEmg"+index).style.display = "none";
			document.getElementById("divMultipleBiEmg"+index).style.display = "none";
			document.getElementById("divMultipleTriEmg"+index).style.display = "none";
		}
	}

	function showdivDayWiseEmg(obj,index) {
		if (obj.value == 1) {
			document.getElementById("divNotHospWiseCommonEmgDayWiseId"+index).style.display = "";
			document.getElementById("divNotHospWiseCommonEmgMonthWiseId"+index).style.display = "none";
			//showDvMultipleMonthEmg(document.forms[0].temp.value,index);
			
		} else {
			document.getElementById("divNotHospWiseCommonEmgDayWiseId"+index).style.display = "none";
			document.getElementById("divNotHospWiseCommonEmgMonthWiseId"+index).style.display = "";
			//showDvMultipleMonthEmg(document.forms[0].strIsMultipleMonthEmg.value,index);
		}
		var objMultipleMonthEmg = document.getElementById("strIsMultipleMonthEmg"+index);
		//alert("objMultipleMonthEmg :"+objMultipleMonthEmg);
		showDvMultipleMonthEmg(objMultipleMonthEmg,index);
	}

	function showDvMultipleMonthSpl(obj,index) {
	 if (obj.value == '') {//else
			document.getElementById("divMultipleNoSpl"+index).style.display = "none";
			document.getElementById("divMultipleBiSpl"+index).style.display = "none";
			document.getElementById("divMultipleTriSpl"+index).style.display = "none";
		}
		else if (obj.value == 0) {//no
			document.getElementById("divMultipleNoSpl"+index).style.display = "";
			document.getElementById("divMultipleBiSpl"+index).style.display = "none";
			document.getElementById("divMultipleTriSpl"+index).style.display = "none";
		} else if (obj.value == 1) {//bi
			document.getElementById("divMultipleNoSpl"+index).style.display = "";
			document.getElementById("divMultipleBiSpl"+index).style.display = "";
			document.getElementById("divMultipleTriSpl"+index).style.display = "none";
		} else if (obj.value == 2) {//tri
			document.getElementById("divMultipleNoSpl"+index).style.display = "";
			document.getElementById("divMultipleBiSpl"+index).style.display = "";
			document.getElementById("divMultipleTriSpl"+index).style.display = "";
		} else {//else
			document.getElementById("divMultipleNoSpl"+index).style.display = "none";
			document.getElementById("divMultipleBiSpl"+index).style.display = "none";
			document.getElementById("divMultipleTriSpl"+index).style.display = "none";
		}
	}

	function showdivDayWiseSpl(obj,index) {
		if (obj.value == 1) {
			document.getElementById("divNotHospWiseCommonSplDayWiseId"+index).style.display = "";
			document.getElementById("divNotHospWiseCommonSplMonthWiseId"+index).style.display = "none";
			//showDvMultipleMonthSpl(document.forms[0].temp.value,index);
		} else {
			document.getElementById("divNotHospWiseCommonSplDayWiseId"+index).style.display = "none";
			document.getElementById("divNotHospWiseCommonSplMonthWiseId"+index).style.display = "";
			//showDvMultipleMonthSpl(document.forms[0].strIsMultipleMonthSpl.value,index);
		}
		var objMultipleMonthSpl = document.getElementById("strIsMultipleMonthSpl"+index);
		showDvMultipleMonthSpl(objMultipleMonthSpl,index);
	}
/************************  Validate Method **********************/
	
	function validateCategory(objThis, modeIndex, index){
		
		if(objThis.value=="-1")
			return true;
		
		var mode= varArrRenewalTypeMode[modeIndex];
		if(mode=="none")
			mode="";
		
		var modeFullName=getModeFullName(mode);
		var catText= $("#strRenewalPatCat"+mode+index+" option:selected").text();
		var objRenewalPatCat =document.getElementsByName("strRenewalPatCat"+mode);
		for(var i=0; i<objRenewalPatCat.length; i++ ){
			console.log("strRenewalPatCat"+mode+"["+i+"].id :"+objRenewalPatCat[i].id);
			console.log(catText+".value :"+objThis.value);
			console.log("strRenewalPatCat"+mode+"["+i+"].value :"+objRenewalPatCat[i].value);
			if(objRenewalPatCat[i].id != ("strRenewalPatCat"+mode+index) && objThis.value==objRenewalPatCat[i].value){
				alert("'"+catText+"' is already added at record "+(i+1)+" For "+modeFullName+". Kindly Select Another Category");
				objThis.value="-1";
				objThis.focus();
				return false;
			}
		}
		return true;
	}
	function getModeFullName(mode){
		var name="";
		switch (mode) {
		    case "":
		    	name = '"Hospital Wise Common Renewal"';
		        break;
		    case "Gen":
		    	name = '"General Renewal"';
		        break;
		    case "Emg":
		    	name = '"Emergency Renewal"';
		        break;
		    case "Spl":
		    	name = '"Special Renewal"';
		        break;
		} 
		return name;
	}
	function validateAllForSave(mode) 
	{
		if(mode=="none")
			mode="";
		
		var modeFullName=getModeFullName(mode);
		
		console.log("mode :"+mode+", modeFullName :"+modeFullName);
		//console.log("strRenewalPatCat"+mode+" :"+document.getElementsByName("strRenewalPatCat"+mode).length);
		var objRenewalPatCat =document.getElementsByName("strRenewalPatCat"+mode);
		for(var i=0; i<objRenewalPatCat.length; i++ )
		{
			if(objRenewalPatCat[i].value=="-1"){
				alert("Please Select Category "+modeFullName);
				objRenewalPatCat[i].focus();
				return false;
			}
			if (mode=="" || document.getElementsByName("strRenewalType"+mode)[i].value == 2
					|| (document.getElementsByName("strRenewalType"+mode)[i].value == 3)) {
				
					var Month1 = document.getElementsByName("strMonth1"+mode)[i].value;
					var day1 = document.getElementsByName("strDay1"+mode)[i].value;
					var Month2 = document.getElementsByName("strMonth2"+mode)[i].value;
					var day2 = document.getElementsByName("strDay2"+mode)[i].value;
					var Month3 = document.getElementsByName("strMonth3"+mode)[i].value;
					var day3 = document.getElementsByName("strDay3"+mode)[i].value;
					
					//console.log("day1 :"+day1+", Month1 :"+Month1);
					//console.log("day2 :"+day1+", Month1 :"+Month2);
					//console.log("day3 :"+day1+", Month1 :"+Month3);
					console.log("strRenewalMode"+mode+"["+i+"] :"+document.getElementsByName("strRenewalMode"+mode)[i].value);
					
					msgToAppend= ", For "+modeFullName+" for Record No-"+(i+1);
					
					if (document.getElementsByName("strRenewalMode"+mode)[i].value=="2") {
						console.log("strIsMultipleMonth"+mode+"["+i+"] :"+document.getElementsByName("strIsMultipleMonth"+mode)[i].value);
						if (document.getElementsByName("strIsMultipleMonth"+mode)[i].value == '') {
							document.getElementsByName("strIsMultipleMonth"+mode)[i].focus();
							alert('Please Select Is Multiple Month For '+modeFullName);
							return false;
						}
						else {
		
							if (validatedayMonth(day1, Month1)) {
								if (document.getElementsByName("strIsMultipleMonth"+mode)[i].value == '1') {
									if (!validatedayMonth(day2, Month2)) {
										return false;
									}
									else{
										if(!validateMonCompare(Month1,Month2,day1,day2)){
											return false;
										}
									}
								} else if (document.getElementsByName("strIsMultipleMonth"+mode)[i].value == '2') {
									if (validatedayMonth(day2, Month2)) {
										if (!validatedayMonth(day3, Month3)){
											return false;
										}
										else{
											if(!validateMonCompare(Month1,Month2,day1,day2))
												return false;
											else
												if(!validateMonCompare(Month2,Month3,day2,day3))
													return false;
										}	
									} else {
										return false;
									}
								}
							} else {
								return false;
							}
						}
					}
		
					else {
						if (document.getElementsByName("strNoOfDays"+mode)[i].value == '') {
							document.getElementsByName("strNoOfDays"+mode)[i].focus();
							alert('Please Enter No. Of Days'+msgToAppend);
							return false;
						} else if (document.getElementsByName("strNoOfDays"+mode)[i].value > 365) {
							alert('No. Of Days Cannot Be Greater Than 365'+msgToAppend);
							return false;
						}
					}
		
		
			}
		}
		msgToAppend="";
		return true;
	} 
	
	function submitSaveAction(cnt) {
		console.log("inside submitSaveAction()");
		var flagValidate=false;
		if(document.forms[0].strRenewalTypeCommonToAll.value == "1"){
			flagValidate=validateAllForSave("none");
		}else{
			for(var i=1; i<varArrRenewalTypeMode.length; i++){
				var renewalTypeMode= varArrRenewalTypeMode[i];
				console.log("renewalTypeMode :"+renewalTypeMode);
				if(renewalTypeMode=="none")
					renewalTypeMode="";
				
				flagValidate=validateAllForSave(renewalTypeMode);
				if(flagValidate==false)
					break;
			}
		}
		
		if(flagValidate){
			document.forms[0].action = "save" + cnt + ".action";
			document.forms[0].submit();
		}
		
	}
	
	function submitCancelAction(cnt) {
		document.forms[0].action = "cancel" + cnt + ".action";
		document.forms[0].submit();
	}
	

	
////////////////////////// utility function /////////////////
function validateMonCompare(Month1,Month2,day1,day2){
	var Mon1int=MonthtoInt(Month1);
	var Mon2int=MonthtoInt(Month2);
	
	if(Mon2int<Mon1int){
		alert('Please Enter Renewal Months In Ascending Order'+msgToAppend);
		return false;
	}
	else if (Mon2int==Mon1int)
	{
		if(Number(day1)==Number(day2))
		{
			alert('Same Days And Months Are Selected For Renewal. Please Enter Proper Days and Months'+msgToAppend);
			return false;
		}
		if(Number(day1)>Number(day2))
		{
			alert('Please Enter Renewal Months In Ascending Order'+msgToAppend);
			return false;
		}
			
	}
	return true;
}

function  MonthtoInt(Mon){
	var abc='';
	if(Mon=='Jan')
		abc=1;	
	if(Mon=='Feb')
		abc=2;
	if(Mon=='Mar')
		abc=3;
	if(Mon=='Apr')
		abc=4;
	if(Mon=='May')
		abc=5;
	if(Mon=='Jun')
		abc=6;
	if(Mon=='Jul')
		abc=7;
	if(Mon=='Aug')
		abc=8;
	if(Mon=='Sep')
		abc=9;
	if(Mon=='Oct')
		abc=10;
	if(Mon=='Nov')
		abc=11;
	if(Mon=='Dec')
		abc=12;
	
	return abc;
		
		
}
	function validatedayMonth(day1, Month1) {
		if (day1 == '') {
			alert('Please Enter Days '+msgToAppend);
			return false;
		}
		if (Month1 == '') {
			alert('Please Select Month '+msgToAppend);
			return false;
		}
		if (Month1 == 'Jan' || Month1 == 'Mar' || Month1 == 'May'
				|| Month1 == 'Jul' || Month1 == 'Aug' || Month1 == 'Oct'
				|| Month1 == 'Dec') {
			if (day1 > 31) {
				alert('Days for the month of ' + Month1
						+ ' cannot be more than 31'+msgToAppend);
				return false;
			}
		} else if (Month1 == 'Apr' || Month1 == 'Jun' || Month1 == 'Sep'
				|| Month1 == 'Nov') {
			if (day1 >= 31) {
				alert('Days for the month of ' + Month1
						+ ' cannot be more than 30'+msgToAppend);
				return false;
			}
		} else if (Month1 == 'Feb') {
			if (day1 >= 29) {
				alert('Days for the month of ' + Month1
						+ ' cannot be more than 28'+msgToAppend);
				return false;
			}
		}
		return true
	}