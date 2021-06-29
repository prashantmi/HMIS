var chkValue	=	"";


function userDefinedOnLoadFunc()
{

}

function buttonLogicsOnRecordCheck(these)
{	//alert("haha");
	var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");
	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	try
	{
		//alert("checkCount"+checkCount);
		if(checkCount==1)
		{
			enableButton("View Detail");
			var tmp = document.getElementById("comboid3").value;
			if(tmp==0) {
				enableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==1) {
				disableButton("Identify");
				enableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==2) {
				disableButton("Identify");
				disableButton("Generate Order");
				enableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==3) {
				disableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				enableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==4) {
				disableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				enableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==5) {
				disableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				enableButton("Issue Certificate");
				disableButton("Print Certificate");
				return;
			}
			else if(tmp==6) {
				disableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				enableButton("Print Certificate");
				return;
			}
			else {
				disableButton("Identify");
				disableButton("Generate Order");
				disableButton("Committee Schedule");
				disableButton("Recommend");
				disableButton("Approval");
				disableButton("Issue Certificate");
				disableButton("Print Certificate");
			}
		}
		
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			if(checkCount!='0')
			{
				alert("Please Select Single Record at a time!!!!!");	
			}

			disableButton("View Detail");
			disableButton("Identify");
			disableButton("Generate Order");
			disableButton("Committee Schedule");
			disableButton("Recommend"); 	 
			return false;
		}


	}
	catch(Err)
	{
		alert(Err);
	}
}


function buttonLogicsOnClick1(modeNo, mode , display)
{
	var checkCount = parseInt("0");
	var check = document.getElementsByName("chk");


	for(i=0;i<check.length;i++)
	{
		if(check[i].checked==true)
		{
			checkCount++;
		}			
	}
	try
	{
		//alert("mode of install::"+mode)
		if(mode=='View') {
			if(checkCount==0)
			{
				alert("Please select a record!!")
				return;
			}
			else
				if(Child!=null){
					Child.close();
				}
				var URL="EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode="+mode+"&chk="+document.forms[0].chkValue.value;
				Child = window.open(URL,'popupWindow','channelmode=yes,status=no,titlebar=no,scrollbars=yes,fullscreen=yes,height=620,width=1050,left=0,top=30');
		}
		
		if(mode=='Identify' || mode=='Identify') {

			/*if(document.getElementById("comboid0").value =="0")
			{
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				return;
			}
			else

				if(document.getElementById("comboid1").value =="0")
				{
					alert("Please Select A Lab Name");
					document.getElementById("comboid1").focus();
					return;
				}
				else*/
					if(checkCount==0)
					{
						alert("Please select a record to proceed!!")
					}
					else
						add(mode);

		}

		if(mode=='OrderGeneration' || mode=='OrderGeneration'){

			/*if(document.getElementById("comboid0").value =="0")
			{
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				return;
			}
			else

				if(document.getElementById("comboid1").value =="0")
				{
					alert("Please Select A Lab Name");
					document.getElementById("comboid1").focus();
					return;
				}*/

			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
			}
			else
				add(mode);
		}
		if(mode=='committeeSchedule' || mode=='committeeSchedule'){
			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
			}
			else
				add(mode);

		}
		if(mode=='Recommendation' || mode=='Recommendation'){

			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
				return;
			}

			add(mode);

		}
		
		if(mode=='Approval' || mode=='Approval'){

			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
				return;
			}

			add(mode);

		}

		if(mode=='IssueCertificate' || mode=='IssueCertificate'){

			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
				return;
			}

			add(mode);

		}
		if(mode=='PrintCertificate' || mode=='PrintCertificate'){

			if(checkCount==0)
			{
				alert("Please select a record to proceed!!")
				return;
			}
			else {
				var strChk = document.forms[0].chkValue;
				strChk = strChk.value.replace("$", "@");
				var arrTemp = strChk.split("@");
				var URL="EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode=printCertificate&checkedVal="+arrTemp[7] 
				+'^' + arrTemp[5] +'^' + arrTemp[4];  
				var Child = window.open(URL,'popupWindow','status=yes,scrollbars=yes,height=1000,width=1000,left=0,top=0');
			}

		}
		if(mode=='AuctionDtl' || mode=='AuctionDtl'){

			if(document.getElementById("comboid0").value =="0")
			{
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				return;
			}

			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Lab Name");
				document.getElementById("comboid1").focus();
				return;
			}
			if(checkCount==0)
			{
				alert("select a record!!")
				return;
			}

			add(mode);

		}
		if(mode=='RevenueHeadDtl' || mode=='RevenueHeadDtl'){

			if(document.getElementById("comboid0").value =="0")
			{
				alert("Please Select A Hospital Name");
				document.getElementById("comboid0").focus();
				return;
			}

			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Lab Name");
				document.getElementById("comboid1").focus();
				return;
			}
			if(checkCount==0)
			{
				alert("select a record!!")
				return;
			}

			add(mode);

		}


		/*	else if(mode=='ComplaintEnquiry' || mode=='ComplaintEnquiry'){
            Allocation();
		    var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chkValue;//+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;
			alert(" view :  "+url);	
			openPopUp_master(url,'600','300','1');		}

		else if(mode=='SHOWREPORT' || mode=='SHOWREPORT'){
	     	add(mode);
		}




		else if(mode=='SHOWRPT'){
		if(document.getElementById("comboid0").value =="0")
		   {
				alert("Please Select A Store Name");
				document.getElementById("comboid0").focus();
				return;
			}
			if(document.getElementById("comboid3").value =="0")
			{
				alert("Please Select Process Type");
				document.getElementById("comboid3").focus();
				return;
			}
			add(mode);
            }

		else{
		if(checkCount=='1')
		{
			if(document.getElementById("comboid3").value =="1" && mode=='VIEWWARRANTY')
			{
				alert("Please Select a Warranty type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			}
			else if(document.getElementById("comboid3").value =="2" && mode=='VIEW')
			 {
			 	alert("Please Select a Maintenance type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			 }

			 if(document.getElementById("comboid3").value =="1" && mode=='ARCHIEVEWARRANTY')
			{
				alert("Please Select a Warranty type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			}
			else if(document.getElementById("comboid3").value =="2" && mode=='ARCHIEVE')
			 {
			 	alert("Please Select a Maintenance type record to view detail.");
				document.getElementById("comboid3").focus();
				return;
			 }
			 else
			 	add(mode); 	
		}
		else
		{
			for(i=0;i<check.length;i++)
			{
				check[i].checked=false;
			}
			alert("Please Select One Record!!!");
			return false;
		}
		}*/
	}
	catch(Err)
	{
		alert(Err);
	}



}

function buttonLogicsOnClickCancel(modeNo, mode , display)
{
	if(document.getElementById("comboid3").value !="0")
	{
		alert("Please Select Status");
		document.getElementById("comboid3").focus();
		return;
	}
	var chkObj = document.getElementsByName("chk");

	var count = parseInt("0");

	for(var i=0; i<chkObj.length; i++) 
	{

		if(chkObj[i].checked)
		{

			count = count + 1;

		}		

	}
	if(count!=0 && count == '1') 
	{
		res=prompt("ENTER REMARKS FOR CANCELATION!","");
		if(!res=="")
		{
			var conf = confirm("Are you sure !!!");
			if(conf == true)
			{
				var chkObj = document.getElementsByName("chk");  
				for(var i=0; i<chkObj.length; i++) 
				{
					if(chkObj[i].checked)
					{
						chkObj[i].value = chkObj[i].value+"^"+res;
					}		
				}
				add(mode);
			}
			else
			{
				return false;
			}

		}
		else
		{
			if(res=="")
			{ 
				alert("Enter remarks for rejection");
			} 
		}
	}
	else
	{
		if(res=="")
		{ 
			alert("Enter remarks for rejection");
		} 
		else
		{
			if(count>1)
				alert("Please Select Single Record at a Time!!!");
			else
				alert("Please Select Record !!!!!");

		} 
		return false;
	}


}		




function buttonLogicsOnClickReturn(modeNo, mode , display)
{
	if(modeNo != 7)
	{
		if(document.getElementById("comboid0").value =="0")
		{
			alert("Please Select A Store Name");
			document.getElementById("comboid0").focus();
			return;
		}

		if(document.getElementById("comboid1").value =="0")
		{
			alert("Please Select A Item Category");
			document.getElementById("comboid1").focus();
			return;
		}

		if(document.getElementById("comboid3").value =="0")
		{
			alert("Please Select A Indent Type");
			document.getElementById("comboid3").focus();
			return;
		}

		if(document.getElementById("comboid3").value =="0")
		{
			alert("Please Select Status");
			document.getElementById("comboid3").focus();
			return;
		}
		var chkObj = document.getElementsByName("chk");

		var count = parseInt("0");

		for(var i=0; i<chkObj.length; i++) 
		{

			if(chkObj[i].checked)
			{

				count = count + 1;

			}		

		}

		if(count > 1)
		{
			alert("Please Select A Single Record");
			return false;
		}
		else
		{
			add(mode);
		}

	}
	else
	{
		return false;
	}

} 	    

function buttonLogicsOnClickPrint(modeNo, mode , display)
{
	var retVal = false;

	if(modeNo == 5)
	{
		if(document.getElementById("comboid0").value =="0")
		{
			alert("Please Select A Store Name");
			document.getElementById("comboid0").focus();
			disableButton("Print");
			return;
		}
		else			
			if(document.getElementById("comboid1").value =="0")
			{
				alert("Please Select A Item Category");
				document.getElementById("comboid1").focus();
				disableButton("Print");
				return;
			}
			else
				if(document.getElementById("comboid3").value =="0")
				{
					alert("Please Select A Request Type");
					document.getElementById("comboid3").focus();
					disableButton("Print");
					return;
				}

		retVal = true;

	}

	if(retVal)
	{
		enableButton("Print");
		document.forms[0].target = "_blank";
		add(mode);
	}
	else
	{
		return false;
	}
} 	    

function cancelPage()
{

	document.forms[0].hmode.value="CANCELPAGE";
	document.forms[0].submit();
}

function getEnggItemSubTypeCombo()
{	   
	var itemParaObj = document.getElementById("serviceEnggNameID");
	itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' ><option value='0'>Select Value</option></select>";

	var itemParaObj1 = document.getElementById("esclationLevelId");
	itemParaObj1.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  ><option value='0'>Select Value</option></select>";


	var itemParaObj1 = document.getElementById("deptId");
	var itemParaObj2 = document.getElementById("contactNo");
	var itemParaObj3 = document.getElementById("emilaId");

	itemParaObj1.innerHTML = "";
	itemParaObj2.innerHTML = "";
	itemParaObj3.innerHTML = "";

	var url="HemDeskACTION.cnt?hmode=GETENGGITEMSUBTYPE&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value;
	ajaxFunction(url,"1");
} 

function getServiceEnggNameCmb()
{	   
	var url="HemDeskACTION.cnt?hmode=GETSERVICEENGG&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
	ajaxFunction(url,"2");
} 

function getEscLevelCmb()
{	   
	var url="HemDeskACTION.cnt?hmode=GETESCLEVEL&enggItemTypeId="+document.forms[0].strEnggItemTypeId.value+"&enggItemSubTypeId="+document.forms[0].strEnggItemSubTypeId.value;
	ajaxFunction(url,"3");
}


function getDetails()
{	   
	var url="HemDeskACTION.cnt?hmode=GETDETAILS&escalationLevelId="+document.forms[0].strEscalationLevelId.value;
	ajaxFunction(url,"4");

}


function getAjaxResponse(res,mode)
{
	var objVal;
	var err = document.getElementById("errMsg");
	var temp1 = res.split("####");
	if(temp1[0] == "ERROR")
	{
		err.innerHTML = temp1[1];	
		return;
	} 	    	    	    
	if(mode=="1")
	{	
		var itemParaObj = document.getElementById("engineeringItemSubTypeId");
		itemParaObj.innerHTML = "<select name = 'strEnggItemSubTypeId' class='COMBO_NORMAL' onChange='getServiceEnggNameCmb();'>" + res + "</select>";
	}

	if(mode=="2")
	{	
		var itemParaObj = document.getElementById("serviceEnggNameID");
		itemParaObj.innerHTML = "<select name = 'strServiceEnggId' class='COMBO_NORMAL' >" + res + "</select>";
		getEscLevelCmb();
	}

	if(mode=="3")
	{	
		var itemParaObj = document.getElementById("esclationLevelId");
		itemParaObj.innerHTML = "<select name = 'strEscalationLevelId' class='COMBO_NORMAL'  onChange='getDetails();'>" + res + "</select>";
	}

	if(mode=="4")
	{	
		var arrVal = res.split("^");
		var itemParaObj1 = document.getElementById("deptId");
		var itemParaObj2 = document.getElementById("contactNo");
		var itemParaObj3 = document.getElementById("emilaId");
		document.getElementById("strEscDtl").value=res;			            

		//            E-Mail                Mob No             Designation          Department
		if(arrVal[3]!=' '||arrVal[3]!='')
		{
			itemParaObj1.innerHTML = arrVal[3];
		} 
		else
		{
			itemParaObj1.innerHTML = "----";	
		}
		if(arrVal[1]!=' '||arrVal[1]!='')
		{ 
			itemParaObj2.innerHTML = arrVal[1];
		}
		else
		{
			itemParaObj2.innerHTML = "----";	
		}
		if(arrVal[0]!=' '||arrVal[0]!='')
		{  
			itemParaObj3.innerHTML = arrVal[0];
		} 
		else
		{
			itemParaObj3.innerHTML = "----";	
		} 



	}
}



function clearPage()
{
	var strComplaintId = document.forms[0].strComplaintId.value;
	var strHemDesk = document.forms[0].strHemDesk.value;
	var strChk = document.forms[0].strChk.value;

	document.forms[0].reset();

	document.forms[0].hmode.value="GRIEVANCES";
	document.forms[0].strComplaintId.value=strComplaintId;
	document.forms[0].strHemDesk.value = strHemDesk;
	document.forms[0].strChk.value = strChk;

	document.forms[0].submit();
}


function  Allocation()
{
	//document.getElementById('message').style.display='none';

//	if(checkForAllocation()==false)

	//{alert("False");
	// return false;
	//}
	var chk_temp	=	document.forms[0].chk.length;
	var chk			=	"";

//	var mode = "VIEWDATA";	
	/*
	var temp_cnt	=	document.forms[0].cnt_page.value;
	var temp1		=	temp_cnt.split(".");
	var cnt_page	=	temp1[0];
	 */
	var cnt_page	=	document.forms[0].action;

	if(!isNaN(chk_temp))
	{
		for(var i=0;i<chk_temp;i++)
		{
			if(document.forms[0].chk[i].checked==true) 
			{
				chk =document.forms[0].chk[i].value;
				chkValue= chk;
				//	alert(chkValue);

			}

		}
	}
	else
	{
		chk =document.forms[0].chk.value;
		chkValue= chk;
	}

	var  heightValue = document.forms[0].view_row_length.value * 40;

//	var url = "../../hisglobal/masterutil/master/view_mstTemp_gbl.jsp?mode="+mode+"&chk="+chk+"&cnt_page="+cnt_page+"&masterName="+document.forms[0].masterName.value;

	//alert(" view :  "+url);	

	//alert("Hi I M checked!!!");
	//openPopUp_master(url,'700',heightValue,'1');
	//var myPopup     =  window.open(,'popupWindow',heg_width, 'scrollbars=yes,ALIGN=CENETER,left=100,top=100');
	try{
		autoTabIndexing();
	}catch(_Err){
		//	alert("Application Error[TabIndex not Included] Please Contact System Administrator");
	}
}

function chkRecordSaved()
{
	//alert(document.forms[0].combo.value[0]);
	if(document.forms[0].strMsgString.value.length>0)
	{
		alert(document.forms[0].strMsgString.value);
		document.forms[0].hmode.value = "LIST";
		document.forms[0].submit();
	}
}

function chkRecordSavedAndTakePrint()
{
	//alert(document.forms[0].combo.value[0]);
	if(document.forms[0].strMsgString.value.length>0)
	{
		var response=confirm(document.forms[0].strMsgString.value + ' Do you want to print the certificate?');
		if(response) {
			var URL="EquipmentAuctionAndCondemnationDeskCNT.cnt?hmode=printCertificate&checkedVal="+document.forms[0].strIndentificationNo.value 
			+'^' + document.forms[0].strEquipmentSerialNo.value +'^' + document.forms[0].strBatchNo.value;  
			var Child = window.open(URL,'popupWindow','status=yes,scrollbars=yes,height=1000,width=1000,left=0,top=0');
			}
		//else {
			document.forms[0].hmode.value = "LIST";
			document.forms[0].submit();
		//}
		
	}
}
function tableShow(strTableId, imageElement)
{
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "table";
	var strOnclick = "tableHide('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/minus.gif");
}

function tableHide(strTableId, imageElement) {
	var elementTable = document.getElementById(strTableId);
	elementTable.style.display = "none";
	var strOnclick = "tableShow('" + strTableId + "',this);";
	imageElement.setAttribute("onclick", strOnclick);
	imageElement.setAttribute("src", "/HEMMS_ODISHA/hisglobal/images/plus.gif");
}
function sparePartDtl(divId)
{
	var obj = document.getElementById(divId)
	if(obj.style.display == "none")
		obj.style.display = "";
	else
		obj.style.display = "none";
		//alert(divId.pageX);
}