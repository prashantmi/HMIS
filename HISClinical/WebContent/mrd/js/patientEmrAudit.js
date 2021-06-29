function toggleOption(obj){

	var selection;
	//alert("case :"+parseInt(obj.value));
	switch(parseInt(obj.value)){
		case 0: 
			//alert("inside case0");
			document.getElementById("patientWiseDivId").style.display="block";
			document.getElementById("userWiseDivId").style.display="none";
			document.getElementById("showCancelOnly").style.display="block";
			document.getElementById("showCancelGO").style.display="none";
			//document.getElementsByName("searchMode")[0].value="0";
			document.getElementsByName("hmode")[0].value="NEW";
			break;
		case 1:
			//alert("inside case1");
			document.getElementById("userWiseDivId").style.display="block";
			document.getElementById("patientWiseDivId").style.display="none";
			document.getElementById("showCancelOnly").style.display="none";
			document.getElementById("showCancelGO").style.display="block";
			//document.getElementsByName("searchMode")[0].value="1"
			document.getElementsByName("hmode")[0].value="GETAUDITUSER";
			break;
	}
	
	//document.forms[0].submit();
}

function submitMode(mode){
	//toggleOption(obj);
	//alert("hmode :"+mode);
	document.getElementsByName("hmode")[0].value=mode;
	document.forms[0].submit();
}

function resetForm(mode)
{
	/*alert("hello");
	alert("world");
	alert("abcdefghijklmnopqrstuvwxyz");
	alert("Inside resetForm(), mode="+mode);*/
	if(mode==0){
		document.forms[0].reset();
	}
	if(mode==1){
		//alert("Inside resetForm()->>mode");
		document.getElementsByName("strEmrAuditTypeId")[0].value=-1;
		submitMode("NEW");
	}
	if(mode==2){
		//alert("Inside resetForm()->>mode");
		//document.getElementsByName("strEmrAuditTypeId")[0].value=-1;
		submitMode("GETPATDTL");
	}
	if(mode==3){
		//alert("Inside resetForm()->>mode");
		document.getElementsByName("strEmrAuditTypeId")[0].value=-1;
		document.getElementsByName("patCrNo")[0].value="10112";
		submitMode("NEW");
	}
}

function validate2(){
	if(validate1()){
		submitMode("GETPATDTL");
	}else{
		return false;
	}
}

function validate1()
{
	//alert("inside validate1");
	var hisValidator = new HISValidator("patientEmrAuditFB");	
	
	hisValidator.addValidation("strEmrAuditTypeId","dontselect=-1", "Audit Type is a Mandatory Field" );
    hisValidator.addValidation("strFromDate", "date","From Date is a mandatory field");
    hisValidator.addValidation("strToDate", "date","To-Date is a mandatory field");
    
    hisValidator.addValidation("strToDate", "dtgtet="+document.forms[0].strFromDate.value,"From Date should not be less than To-Date");
    hisValidator.addValidation("strToDate", "dtltet="+document.forms[0].sysDate.value,"To-Date should be less than Current Date");
    //hisValidator.addValidation("strRemarks", "maxlen=100", "Remarks should have less than or equal to 100 Characters" );
    if(document.getElementsByName("strLitingType")[1].checked==true){
    	hisValidator.addValidation("strEmrAuditUserId","dontselect=-1", "Audit User is a Mandatory Field" );
    }
     
     
     
    var retVal = hisValidator.validate(); 
 	hisValidator.clearAllValidations();	    
	if(retVal)
	{		      
			return true;
	}
	else
	{
	  return false;
	}
}

function validateSave()
{
	var hisValidator = new HISValidator("patientEmrAuditFB");	
	hisValidator.addValidation("strAuditStatusId","dontselect=-1", "Please Enter Audit Status" );
    hisValidator.addValidation("strRemark", "req","Please Enter Remarks");
     
     
    var retVal = hisValidator.validate(); 
 	hisValidator.clearAllValidations();	    
	if(retVal)
	{		      
			document.getElementsByName("hmode")[0].value="SAVEAUDITDETAIL";
			document.forms[0].submit();
	}
	else
	{
	  return false;
	}
}
function setEmrAuditUserList()
{
	//var divKitchenLabel = document.getElementById("divKitchenLabel");
	var emrAuditUserDivId = document.getElementById("emrAuditUserDivId");
	
	var strEmrAuditUserId = document.getElementsByName("strEmrAuditUserId")[0];
	
	// Clear Kitchen List
	PragyaDesigner.clearCombo(strEmrAuditUserId);
	//divKitchenLabel.style.display = "none";
	emrAuditUserDivId.style.display = "none";
	
	var strEmrAuditTypeId = document.getElementsByName("strEmrAuditTypeId")[0].value;

	if(strEmrAuditTypeId!="-1")
	{ 
		var objAuditUserList = getAuditUserList(strEmrAuditTypeId);
		if(objAuditUserList!=null)
		{
			for(var i=0;i<objAuditUserList.length;i++)
			{
				opt = document.createElement("option");
				opt.value = objAuditUserList[i].strEmrAuditUserId;
				opt.innerHTML = objAuditUserList[i].strEmrAuditUserName;
				strEmrAuditUserId.appendChild(opt);
			}
			//divKitchenLabel.style.display = "block";
			emrAuditUserDivId.style.display = "block";
		}
	}
}

function showPatientEmrDialTile(i)
{
	var strPrimaryKey=document.getElementsByName('strPrimaryKey')[i];
	document.forms[0].strPrimaryKey=strPrimaryKey;
	submitMode("SHOWPATIENTEMRDIALTILE");
}

/*function doPagination(e,p)
{
	alert("insde doPagination");
	document.getElementsByName('currentPage')[0].value=p;
	var radioClick =document.getElementsByName('strPrimaryKey');
	//var flag=parseInt(document.getElementsByName('flag')[0].value);
	//alert("parseInt(document.getElementsByName('flag')[0].value)"+parseInt(document.getElementsByName('flag')[0].value))

	//document.getElementsByName('flag')[0].value=flag;
	//alert("document.getElementsByName('flag')[0].value"+document.getElementsByName('flag')[0].value)
	document.getElementsByName('hmode')[0].value='PAGINATION';
	document.forms[0].submit();
}*/

function onclickImage(imgObj)
{
	var divObj=document.getElementById("div"+imgObj.id.substr(3));
	/*alert("imgObj :"+imgObj);
	alert("divObj :"+("div"+imgObj.id.substr(3).Text));
	alert("divObj.style.display :"+divObj.style.display);*/
	if(divObj.style.display=="none")
	{
		//alert("none");
		divObj.style.display="block";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-up.png";
	}
	else if(divObj.style.display=="block")
	{
		//alert("block");
		divObj.style.display="none";
		imgObj.src = "/HIS/hisglobal/images/avai/arrow-down.png";
	}
}

//function getScanDoc(patCRNo, episodeCode, episodeVisitNo, episodeVisitDocDate)
function getScanDoc()
{
	var tdScannedDocs = document.getElementById("tdScannedDocs");
	var patCRNo = document.getElementsByName("patCrNo")[0].value;		
	var episodeCode = document.getElementsByName("strEpisodeCode")[0].value;
	var episodeVisitNo = document.getElementsByName("strVisitNo")[0].value;
	var episodeVisitDocDate = document.getElementsByName("strVisitDate")[0].value;
	var divScannedDocs = document.getElementById("divScanDocDtl");
	tdScannedDocs.width = "50%";
	divScannedDocs.innerHTML="";
	//alert("url :"+"/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&strDocumentDate="+episodeVisitDocDate+"&strDocTypeId=10&strIsPopupClose=0");
	try
	{
		var frame = PragyaDesigner.createElement("iframe",divScannedDocs);
		frame.id = "ifrmDoc";
		frame.width = 750;//frame.width = 950;
		frame.height = 400;
		frame.src = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&strDocumentDate="+episodeVisitDocDate+"&strDocTypeId=10&strIsPopupClose=0"; 
		//frame.src = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&strDocTypeId=10&strIsPopupClose=0"; 
		//"/HISClinical/scanning/viewDocWithFTP?strFileName="+objDoc.strDocumentId+"&strFileExt="+objDoc.strFileExt;--------------------
	}
	catch(e)
	{
		alert(e);
		alert("Scan Document not Avalaible!!!!");
		divScannedDocs.innerHTML ="";
	}
	
	/*var url = "/HISClinical/scanning/viewScannedTile.cnt?patCrNo="+patCRNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&strDocTypeId=10";
	var child = window.open(url,'popupWindow','status=yes,scrollbars=yes,height=800,width=350,left=10,top=10');  
	child.moveTo(2,2);
	child.opener.focus();*/
		
}

/*************************************** AJAX Functions ***************************/
//Gettting List of Audit User
function getAuditUserList(strEmrAuditTypeId)
{	//alert("inside getAuditUserList");
	var flg = false;
	var objAuditUserList = null;
	var _mode = "GETAUDITUSER_AJAX";
	var objXHR = {url: "/HISClinical/mrd/patientEmrAudit.cnt?hmode="+_mode+"&strEmrAuditTypeId="+strEmrAuditTypeId, sync:true, postData: "", handleAs: "json",
		load: function(data) 
		{
			objAuditUserList = data;
			flg = true;
		},
     error: function(error)
     {
         objAuditUserList = null;
         flg = false;
     }};

	var objDojoAjax = dojo.xhrPost(objXHR);
	return objAuditUserList;
}

