/*
function (obj1)
{	
	var itemTypeId=obj1.value.split("#")[1];
	var ivfluidFlag=obj1.value.split("#")[2];
	//alert("ivfluidFlag "+ivfluidFlag);
	showIvfluid(ivfluidFlag);
	var url='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=DRUGROUTE&itemTypeId='+itemTypeId;
	httpDrugRouteListRequest("GET",url,true);
}
*/
function showIvfluid(ivfluidFlag)
{
	if(ivfluidFlag!=0)
	{
		document.getElementsByName("selEndExecutionTimeHrs")[0].disabled=false;
		document.getElementsByName("selEndExecutionTimeMin")[0].disabled=false;
		/*
		document.getElementById("ivfluidFlagId").style.display="";
		document.getElementById("ivfluidFlagLableId").style.display="";
		*/
	}
	else
	{
		document.getElementsByName("selEndExecutionTimeHrs")[0].disabled=true;
		document.getElementsByName("selEndExecutionTimeMin")[0].disabled=true;
		/*
		document.getElementById("ivfluidFlagId").style.display="none";
		document.getElementById("ivfluidFlagLableId").style.display="none";
		*/
	}
}



function findIndex(obj)
{
	var ctrlArr = document.getElementsByName(obj.name);
	var index=-1;
	for(var i=0;i<ctrlArr.length;i++)
	{
		if(ctrlArr[i]==obj)
		{
			index=i;
			break;
		}
	}
	return index;
}

function showCompleteTreatment(event)
{
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=SHOWCOMPLETETREATMENT';
	openPopup(createFHashAjaxQuery(path),event,500,700);
}

function sendDataForDrugRouteList(itemTypeIdAndIndex,indexzz)
{
	
	var itemTypeId=itemTypeIdAndIndex.title.split("#")[0];
	var index=itemTypeIdAndIndex.title.split("#")[1];
	
	handleGetSetDrugRouteList(itemTypeId,index);
	
}

function handleGetSetDrugRouteList(_itmeId,index)
{
	
	var _routeid=document.getElementsByName("prevDrugRouteId")[index].value;
	//alert(_routeid);
	//alert(_itmeId);
	var elemRouteCombo = document.getElementsByName("selDrugRouteIdArray")[index];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("drugRouteList")[0].options;
	//alert(opts.length);
	for(var i=0; i<opts.length; i++)
	{
		//alert(opts[i].value);
		
		var routeId = opts[i].value.split("#")[0];
		var itemid= opts[i].value.split("#")[1];
		//alert(routeId); 
		//alert(itemid);
		if(itemid == _itmeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
	elemRouteCombo.value = _routeid;
	showDetail();
	
	if(elemRouteCombo.length > 1)
		elemRouteCombo.options.selectedIndex="1";
}

function setDrugRouteName(index)
{
	var drugRouteId=document.getElementsByName('selDrugRouteId')[index];
	var drugRouteName=document.getElementsByName('selDrugRouteName')[index];
	drugRouteName.value=drugRouteId.options[drugRouteId.selectedIndex].text;
}

function setDrugBrandName(index, flagfromstore, flagSOS)
{
	//alert("hi");
	if(flagfromstore)
		{
		var drugBrandId=document.getElementsByName('selDrugBrandArrayFromStore')[index];
		var drugBrandName=document.getElementsByName('selDrugBrandName')[0];
		}
	if(flagSOS)
		{
		var drugBrandId=document.getElementsByName('selDrugBrandArraySOS')[0];
		var drugBrandName=document.getElementsByName('selDrugBrandNameSOS')[0];
		}
	else
	{
		var drugBrandId=document.getElementsByName('selDrugBrandArray')[index];
		var drugBrandName=document.getElementsByName('selDrugBrandName')[0];
	}
	
	
	
	//alert(drugBrandId.options[drugBrandId.selectedIndex].text);
	
	drugBrandName.value=drugBrandId.options[drugBrandId.selectedIndex].text;
//	alert(drugBrandName.value); alert(drugBrandId.value);
	//alert("Old"+drugBrandName.value);
	//alert("New"+document.getElementsByName('selDrugBrandName')[0].value); 
}




function sendDataForDrugDoseList(itemTypeId,index,itemId,itemname)
{
	
	//handleGetSetDrugDoseList(itemTypeId,index);
	//showDetail();
	//alert(itemname);
	var SOSFlag=false; var storeFlag=true;
	handleGetSetDrugRouteList(itemTypeId,index);
	handleGetSetBrandListFromGeneric(itemId,index);
	handleGetSetBrandListFromGenericInStore(itemId,index);
	sendDataForDrugAlerts(itemTypeId,itemId,itemname,index,SOSFlag);	
	handleGetSetBrandId(index,storeFlag);
	
	/*var reactionStatus=document.getElementsByName("reactionStatusArray")[index].value;
	var allergyStatus=document.getElementsByName("allergyStatusArray")[index].value;
	if(document.getElementsByName("selIndexArray")[index].checked)
	{	
		//alert("index "+index);
		//alert("reactionStatus "+reactionStatus);
		//alert("allergyStatus "+allergyStatus);
		if(reactionStatus!="0")
		{
			alert("This drug has been  reacted to patient");
		}
	
		if(allergyStatus!=0)
		{
			alert("Patient has allergy from this drug");
		}
	}*/
	
}


function sendDataForDrugAlerts(itemType,itemId,drugName,index,SOSFlag)
{
	//alert("hi");
	var	crNo=document.getElementsByName('patCrNo')[0];
	var	deptUnitCode=document.getElementsByName('departmentUnitcode')[0];
//	alert(crNo.value); 
	//alert(deptUnitCode.value);
	//alert("hi2");
	$.ajax({
		url : createFHashAjaxQuery('/HISClinical/opd/patTreatmentDetailTile.cnt?hmode=GETDRUGDOSELIST'),
		type : 'GET',
		data : {
			drugItemType : itemType,
			drugId : itemId,
			drugName : drugName,
			patcrNo : crNo.value,
			departmentUnitCode : deptUnitCode.value
			
		},
		 datatype: "json",
		 async: false,
		  success : function(data) {
			//  alert(data);
					var obj = eval(data);
		//   alert(obj);
			var elemDrugId = document.getElementsByName("selDrugId")[index];
			
			var elemDrugName = document.getElementsByName("selDrugName")[index];
			
			if(!SOSFlag)
			{
			//	alert("hi1");
			if (obj.length > 0) {
				for (var i = 0; i < obj.length; i++) {
			
					var summary=document.getElementById('divSummary');
				    
					var innerHtml="";	
								
					
			
			var searchText=obj[i].msg;
			var fontcolor="";
			if(i==0 && obj[i].msg!='' )  fontcolor="#FF0000";    //for allergies
			if(i==1 && obj[i].msg!='' )  fontcolor="#0000A0";    //for prev drug duplicate
			if(i==2 && obj[i].msg!='' )  fontcolor="#0000B0";    // for drug Reaction
			if(i==3 && obj[i].msg!='' )  fontcolor="#FF0000";     // for drug contradiction
			if(i==4 && obj[i].msg!='' )  fontcolor="#FF0000";     //for pregnant alert
			
			if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
			{	
				//alert("hi");
				innerHtml+=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
				summary.innerHTML=innerHtml;
				document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
			}
				}}
			if(document.getElementsByName("summary")[0].value !="")
			$('#divAlert'+index).show();
			
			}
			
			
			if(SOSFlag)
				{
				//alert("HI SOS");
				if(obj!=undefined)
					{
				if (obj.length > 0 ) {
							for (var i = 0; i < obj.length; i++) {
						
								var summary=document.getElementById('divSummarySOS');
							
								var innerHtml="";	
								var elemDrugId = document.getElementsByName("sosSelDrugId")[index];	
								//alert(elemDrugId.value);
								var tr = getCorrespondingTR(elemDrugId);
								for(var j=0;j<tr.childNodes.length;j++)
									if(tr.childNodes[j].tagName=="TD")
									{	
										
										if(obj[i].statusDrugAllergy > 0)
										{
											
											tr.childNodes[j].style.backgroundColor = "#FF0000";
											
										}
										else
										{
											tr.childNodes[j].style.backgroundColor = "";
										}
										if(obj[i].statusDrugDupAssign > 0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000A0";
										}
										if(obj[i].statusDrugReaction > 0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000B0";
										}
										
										if(obj[i].statusDrugContradiction >0)
										{
											tr.childNodes[j].style.backgroundColor = "#0000C0";
										}
									}
								         
										
										var searchText=obj[i].msg;
										var fontcolor="";
										if(i==0 && obj[i].msg!='' )  fontcolor="#FF0000";    //for allergies
										if(i==1 && obj[i].msg!='' )  fontcolor="#0000A0";    //for prev drug duplicate
										if(i==2 && obj[i].msg!='' )  fontcolor="#0000B0";    // for drug Reaction
										if(i==3 && obj[i].msg!='' )  fontcolor="#FF0000";     // for drug contradiction
										
								
										
										if (document.getElementsByName("summary")[0].value.indexOf(searchText)==-1)
										{	
											//alert("hi");
											innerHtml+=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
											summary.innerHTML=innerHtml;
											document.getElementsByName("summary")[0].value=document.getElementsByName("summary")[0].value+"*<font color="+fontcolor+" size='2'>"+obj[i].msg+"</font><br>";
										}
										
										
									}
								
														
							}}
				//if(document.getElementsByName("divSummarySOS").value !="")
				//	document.getElementById('divSummarySOS').style.display="";
						
			
				}
			
			
				
		},
		error: function(data)
          {
              alert('request failed :');
          }
		
	});
	


}


function getCorrespondingTR(elem)
{
	var elemTR = elem;
	while(elemTR.tagName!="TR")
		elemTR=elemTR.parentNode;
	return elemTR;
}



function handleGetSetDrugDoseList(_itmeTypeId,index)
{
	var elemDoseCombo = document.getElementsByName("selDoseIdArray")[index];
	elemDoseCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select";
	elemDoseCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("drugDoseList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var doseId = opts[i].value.split("#")[0];
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=doseId;
			op.innerHTML=opts[i].text;
			elemDoseCombo.appendChild(op);
		}		
	}
	elemDoseCombo.value = document.getElementsByName("prevDoseId")[index].value;
	showDetail();
}



function handleGetSetBrandListFromGenericInStore(_itemId,index)
{
	
	//alert("_itemId "+_itemId);
	//alert("inside brand from storedfgdfg");
	var elemBrandStorecombo = document.getElementsByName("selDrugBrandArrayFromStore")[index];
	elemBrandStorecombo.innerHTML="";
	var opp=document.createElement("option");
	opp.value="";
	opp.innerHTML="Select";
	elemBrandStorecombo.appendChild(opp);

	// Filling Brand Data
	var opts = document.getElementsByName("batchNoList")[0].options;
//	alert("opts "+opts.length);
	for(var i=0; i<opts.length; i++)
	{
	//	alert("inside loop");
		var itemId = opts[i].value.split("#")[4];
		var branditemid= opts[i].value.split("#")[2];
		//alert("_itemId ----> "+_itemId);
	//	alert("branditemId "+itemId);
	//	alert("genericitemId "+_itemId);
	//	alert("itemId "+itembrand);
		
		if(itemId==_itemId)
		{
			//alert("matched");
			opp=document.createElement("option");
			opp.value=branditemid;
			opp.innerHTML=opts[i].value.split("#")[3];
			var flag=true;
			for(var k=0;k<elemBrandStorecombo.length;k++)
				{
				if(elemBrandStorecombo.options[k].value==branditemid)
					{
					flag=false;
					break;
				    }
				}
				if(flag)
					elemBrandStorecombo.appendChild(opp);
			}
			
				
	    }
	
	/*var x = {};
	$("select[name='selDrugBrandArrayFromStore'] > option").each(function () {
	    if(x[this.text]) {
	        $(this).remove();
	    } else {
	        x[this.text] = this.value;
	    }
	});*/
	
	

}



function handleGetSetBrandListFromGeneric(_itemId,index)
{
	
	//alert("_itemId "+_itemId);
	//alert("inside brand");
	var elemBrandcombo = document.getElementsByName("selDrugBrandArray")[index];
	elemBrandcombo.innerHTML="";
	var opp=document.createElement("option");
	opp.value="";
	opp.innerHTML="Select";
	elemBrandcombo.appendChild(opp);

	// Filling Brand Data
	var opts = document.getElementsByName("drugBrandList")[0].options;
	//alert("opts "+opts.length);
	for(var i=0; i<opts.length; i++)
	{
	//	alert("inside loop");
		var itemId = opts[i].value.split("#")[1];
		var branditemid= opts[i].value.split("#")[0];
		//alert("_itemId ----> "+_itemId);
	//	alert("branditemId "+itemId);
	//	alert("genericitemId "+_itemId);
	//	alert("itemId "+itembrand);
		
		if(itemId==_itemId)
		{
		//	alert("matched");
			opp=document.createElement("option");
			opp.value=branditemid;
			opp.innerHTML=opts[i].text;
			elemBrandcombo.appendChild(opp);
		}		
	}
	
	

}


function handleGetSetSOSBrandListFromGeneric(_itemId,index)
{
	
	//alert("_itemId "+_itemId);
	//alert("inside brand");
	var elemBrandcombo = document.getElementsByName("selDrugBrandArraySOS")[index];
	elemBrandcombo.innerHTML="";
	var opp=document.createElement("option");
	opp.value="";
	opp.innerHTML="Select";
	elemBrandcombo.appendChild(opp);

	// Filling Brand Data
	var opts = document.getElementsByName("drugBrandList")[0].options;
	//alert("opts "+opts.length);
	for(var i=0; i<opts.length; i++)
	{
	//	alert("inside loop");
		var itemId = opts[i].value.split("#")[1];
		var branditemid= opts[i].value.split("#")[0];
	//	alert("_itemId ----> "+itemId);
	//	alert("branditemId "+branditemid);
	//	alert("genericitemId "+_itemId);
	//	alert("itemId "+itembrand);
		
		if(itemId==_itemId)
		{
			//alert("matched");
			opp=document.createElement("option");
			opp.value=branditemid;
			opp.innerHTML=opts[i].text;
			elemBrandcombo.appendChild(opp);
		}		
	}
	
	

}

function handleGetSetBatchNoList(obj,index)
{
//	alert("hi");
	var flagfromstore=true;
	var flagSOS=false;
	setDrugBrandName(index,flagfromstore,flagSOS);
//	alert("_itemId "+_itemId);
	//alert(obj.value);
	var _itembrandId= obj.value;
	//alert(_itembrandId);
	
	var elemBatchNoCombo = document.getElementsByName("selBatchNoArray")[index];
	elemBatchNoCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select";
	elemBatchNoCombo.appendChild(op);

	// Filling Batch No Data
	var opts = document.getElementsByName("batchNoList")[0].options;
//	alert("opts "+opts.length);
	for(var i=0; i<opts.length; i++)
	{
	//	alert("inside loop");
		var itembrandid= opts[i].value.split("#")[2];
		//alert("_itemId ----> "+_itemId);
		//alert("itemId "+itemId);
		//alert("itemId "+itembrand);
		
		if(itembrandid==_itembrandId)
		{
			op=document.createElement("option");
			//op.value=itemId;
			op.value=opts[i].value;
			op.innerHTML=opts[i].text;
			elemBatchNoCombo.appendChild(op);
		}		
	}
 //elemDoseCombo.value = document.getElementsByName("prevDoseId")[index].value;
//showDetail();
	
	
	}

//getting sos drugdoseList and sos drug route list for compo

function sendDataForSOSDrugDoseList(ItemIdAndItemTypeId)
{
	//alert("hi");
	/*
	if(ItemIdAndItemTypeId=="")
	{
		document.getElementsByName("sosSelEndExecutionTimeHrs")[0].disabled=true;
		document.getElementsByName("sosSelEndExecutionTimeMin")[0].disabled=true;
	}
	*/
	
	//alert(ItemIdAndItemTypeId.value);
	//alert(ItemIdAndItemTypeId);
	var ItemId=ItemIdAndItemTypeId.value.split("#")[0];
	//alert(ItemId);
	var itemTypeId=ItemIdAndItemTypeId.value.split("#")[3];
	//alert(itemTypeId);
	//var index=itemTypeIdAndIndex.title.split("#")[1];
	var ivFluidFlag=ItemIdAndItemTypeId.value.split("#")[4];
	//alert(ivFluidFlag);
	//alert("ivFluidFlag "+ivFluidFlag);
	if(ivFluidFlag!="0")
	{
		document.getElementsByName("sosSelEndExecutionTimeHrs")[0].disabled=false;
		document.getElementsByName("sosSelEndExecutionTimeMin")[0].disabled=false;
		document.getElementsByName("sosSelVolume")[0].disabled=false;
	}
	else
	{
		document.getElementsByName("sosSelEndExecutionTimeHrs")[0].disabled=true;
		document.getElementsByName("sosSelEndExecutionTimeMin")[0].disabled=true;
		document.getElementsByName("sosSelVolume")[0].disabled=true;
	}
	
	var elemDrugId = document.getElementsByName("sosSelDrugId")[0];	
	//alert(elemDrugId);
	var index=elemDrugId.selectedIndex;
	var itemname=elemDrugId.options[index].text;
	var _itemid=elemDrugId.options[index].value.split("#")[0];
	//alert(_itemid);
	var patpregcode=elemDrugId.options[index].value.split("#")[3];
	var _routeId=elemDrugId.options[index].value.split("#")[4];
	var _doseId=elemDrugId.options[index].value.split("#")[2];
	//alert(_doseId);
//	alert(elemDrugId.options[index].value);
//	alert(_routeId);
	handleGetSetSOSDrugDoseList(itemTypeId,_doseId);
	handleGetSetSOSDrugRouteList(itemTypeId,_routeId);
	handleGetSetSOSBrandListFromGeneric(_itemid,0);
	//alert(ItemIdAndItemTypeId.text);
	var SOSFlag=true;
	sendDataForDrugAlerts(0,_itemid,itemname,0,SOSFlag);	
	
	
	
//	var elemDropDownExt = document.getElementById("cboDropDownExt");
//	
//	selectIndexValExt = elemDropDownExt.selectedIndex;
//	elemExtTretId.value=elemDropDownExt.options[selectIndexValExt].value;
//	elemExtTretName.value=elemDropDownExt.options[selectIndexValExt].text;

}


function handleGetSetBrandId(index,flagStore)
{
	//alert("hi");
	var drugbrandIdArray;
	
	
	if (flagStore)		
	drugbrandIdArray = document.getElementsByName("selDrugBrandArrayFromStore")[index].options;
	else
	drugbrandIdArray = document.getElementsByName("selDrugBrandArray")[index].options;
	
	var selBrandId= document.getElementsByName("selItemBrandIdArray")[index].value;
	
	for(var i=0; i<drugbrandIdArray.length; i++)
	{
		
		if(drugbrandIdArray[i].value ==selBrandId)
		{
			drugbrandIdArray.selectedIndex = i;
           break;
        }
    }
	
	
	
	
	
	
	
	


}



function handleGetSetSOSDrugRouteList(_itmetypeId,_routeId)

{
	//alert("hi2");  
	//var _routeid=document.getElementsByName("prevDrugRouteId")[0].value;
	//alert(document.getElementsByName("prevDrugRouteId").length);
	//alert(_routeId);
	//alert(_itmeId);
	var drugRouteId=document.getElementsByName('sosSelDrugRouteId')[0];
	drugRouteId.value=_routeId;
	var elemRouteCombo = document.getElementsByName("sosSelDrugRouteId1")[0];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("sosDrugRouteList")[0].options;
	//alert(opts.length);
	
	for(var i=0; i<opts.length; i++)
	{
	//	alert(opts[i].value);
		
		var routeId = opts[i].value.split("#")[0];
		var itemtypeid= opts[i].value.split("#")[1];
		
		//alert(routeId); 
		//alert(itemtypeid);
		if(itemtypeid == _itmetypeId)
		{	
			//alert("if");
			document.getElementById("drugRoutesosTDId").style.display="none";
			document.getElementById("drugRouteTDSosId").style.display="block";



			op=document.createElement("option");
			op.value=routeId;
			//alert(routeId);
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);

		}			
	}
	elemRouteCombo.value = _routeId;
	
	if(elemRouteCombo.length > 1)
	{
		
	elemRouteCombo.options.selectedIndex="1";
	var drugRouteName=document.getElementsByName('sosSelDrugRouteName')[idx];
	drugRouteName.value=elemRouteCombo.options[1].text;
		//alert(drugRouteName.value);
	}
	else
		{
		document.getElementById("drugRouteTDSosId").style.display="none";

		document.getElementById("drugRoutesosTDId").style.display="block";


		var elemRouteCombo = document.getElementsByName("sosSelDrugRouteId2")[0];
		
		var opts = document.getElementsByName("sosDrugRouteList")[0].options;
		
		//alert(opts.length);
		
		for(var i=0; i<opts.length; i++)
		{
		//	alert(opts[i].value);
			
			var routeId = opts[i].value.split("#")[0];
			var itemtypeid= opts[i].value.split("#")[1];
			
			//alert(routeId); 
			//alert(itemtypeid);
			//if(itemtypeid == _itmetypeId)
			
				op=document.createElement("option");
				op.value=routeId;
				//alert(routeId);
				op.innerHTML=opts[i].text;
				elemRouteCombo.appendChild(op);
			
			}
		}
	
}



function setAllRoute()
{
	
	
	var t = document.getElementsByName("sosSelDrugRouteId2")[0];
	//alert(t);
	//var t= document.getElementById('deptUnitComoboId');
	//alert(t);
	
	var z=t.options[t.selectedIndex].text;
	//alert(z);
	var v=t.options[t.selectedIndex].value;
	//alert(v);
	
	//alert(z);
	var drugRouteName=document.getElementsByName('sosSelDrugRouteName')[0];
	drugRouteName.value=z;
	//alert(drugRouteName);
	var drugRouteId=document.getElementsByName('sosSelDrugRouteId')[0];
	drugRouteId.value=v;
	//(drugRouteId);
	
	
	//showDetail();

	}

/*function handleGetSetSOSDrugRouteList1(_itmeTypeId)
{
	var elemRouteCombo = document.getElementsByName("sosSelDrugRouteId")[0];
	elemRouteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select";
	elemRouteCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("sosDrugRouteList")[0].options;
	for(var i=0; i<opts.length; i++)
	{
		var routeId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		if(itmeTypeId==_itmeTypeId)
		{
			op=document.createElement("option");
			op.value=routeId;
			op.innerHTML=opts[i].text;
			elemRouteCombo.appendChild(op);
		}		
	}
}*/

function handleGetSetSOSDrugDoseList(_itmeTypeId,_doseId)
{
	//alert("hi");
/*//	alert("hi2");
	var elemDoseCombo = document.getElementsByName("sosSelDoseId")[0];
	elemDoseCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="-1";
	op.innerHTML="Select";
	elemDoseCombo.appendChild(op);

	// Filling Route Data
	var opts = document.getElementsByName("sosDrugDoseList")[0].options;
	//alert(opts.length);
//	alert("_itmeTypeId --"+_itmeTypeId);
	for(var i=0; i<opts.length; i++)
	{
		//alert(opts[i].value);
		var doseId = opts[i].value.split("#")[0]
		var itmeTypeId = opts[i].value.split("#")[1];
		//alert(doseId);
		//alert(itmeTypeId); 
		//alert(_itmeTypeId);
		
		
		//if(itmeTypeId==_itmeTypeId)
		//	if(itmeTypeId== "0")
		{
		//	alert("matched");
			op=document.createElement("option");
			op.value=doseId;
			op.innerHTML=opts[i].text;
			elemDoseCombo.appendChild(op);
		}		
	}
	
*/	//var textbox3 = _doseId;
	//alert("hi");
	var textbox3 = document.getElementsByName('sosSelDoseId')[0];
	textbox3.value=_doseId;
	
	
	
	
	}

function showDetail()
{
	var len=document.getElementsByName('selIndexArray').length;
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName('selIndexArray')[i].checked)
		{
			//document.getElementsByName("selDoseIdArray")[i].disabled=false;
			document.getElementsByName("selDrugRouteIdArray")[i].disabled=false;
			document.getElementsByName("selStartExecutionTimeHrs")[i].disabled=false;
			document.getElementsByName("selStartExecutionTimeMin")[i].disabled=false;
		
			document.getElementsByName("selScheduleDateArray")[i].disabled=false;
			document.getElementById('IselScheduleDateArray'+i).style.display="";
			
			
			
			document.getElementsByName("drugStoreSourceArray")[i].disabled=false;
			document.getElementsByName("selBatchNoArray")[i].disabled=false;			
			//document.getElementsByName("selPatientBatchNoArray")[i].disabled=false;
			
			document.getElementsByName("selDrugBrandArray")[i].disabled=false;	
		    document.getElementsByName("selDrugBrandArrayFromStore")[i].disabled=false;	
			
			
			document.getElementsByName("selRemarksArray")[i].disabled=false;
			document.getElementsByName("fromPatientcheckBox")[i].disabled=false;
									
			//document.getElementsByName("defaultQtyArray")[i].disabled=false;
			
			if(document.getElementsByName("selIvFluidFlagArray")[i].value!=0)
			{
				document.getElementsByName("selEndExecutionTimeHrs")[i].disabled=false;
				document.getElementsByName("selEndExecutionTimeMin")[i].disabled=false;
				document.getElementsByName("selVolumeArray")[i].disabled=false;
				/*
				document.getElementById("ivfluidFlagId").style.display="";
				document.getElementById("ivfluidFlagLableId").style.display="";
				*/
			}
			else
			{	
				document.getElementsByName("selEndExecutionTimeHrs")[i].disabled=false;
				document.getElementsByName("selEndExecutionTimeMin")[i].disabled=false;
				document.getElementsByName("selVolumeArray")[i].disabled=false;
				//alert("inside readOnly...");
				document.getElementsByName("selEndExecutionTimeHrs")[i].readOnly=true;
				document.getElementsByName("selEndExecutionTimeMin")[i].readOnly=true;
				document.getElementsByName("selVolumeArray")[i].readOnly=true;
			}
						
		}
		else
		{
			document.getElementsByName("selStartExecutionTimeHrs")[i].value="";
			document.getElementsByName("selStartExecutionTimeMin")[i].value="";
			document.getElementsByName("selScheduleDateArray")[i].disabled="";
			document.getElementsByName("selBatchNoArray")[i].value="";			
			document.getElementsByName("selPatientBatchNoArray")[i].value="";
			document.getElementsByName("drugStoreSourceArray")[i].disabled=true;
			document.getElementsByName("selBatchNoArray")[i].disabled=true;			
			//document.getElementsByName("selPatientBatchNoArray")[i].disabled=true;
			
			document.getElementsByName("selDrugBrandArray")[i].value="";			
			document.getElementsByName("selDrugBrandArrayFromStore")[i].value="";
			document.getElementsByName("selDrugBrandArray")[i].disabled=true;	
			document.getElementsByName("selDrugBrandArrayFromStore")[i].disabled=true;	
			
			document.getElementsByName("selRemarksArray")[i].value="";
			document.getElementsByName("selExpriryDateArray")[i].value="";
			document.getElementsByName("selEndExecutionTimeHrs")[i].value="";
			document.getElementsByName("selEndExecutionTimeMin")[i].value="";
			document.getElementsByName("fromPatientcheckBox")[i].disabled=true;
			//document.getElementsByName("selDoseIdArray")[i].disabled=true;
			document.getElementsByName("selDrugRouteIdArray")[i].disabled=true;
			document.getElementsByName("selStartExecutionTimeHrs")[i].disabled=true;
			document.getElementsByName("selStartExecutionTimeMin")[i].disabled=true;
			
			document.getElementsByName("selScheduleDateArray")[i].disabled=true;
			document.getElementById('IselScheduleDateArray'+i).style.display="none";
		//	document.getElementById('selScheduleDateInputArray'+i).value=document.getElementsByName('selScheduleDateArray')[i].value;
			
			
			//document.getElementsByName("selBatchNoArray")[i].disabled=true;
			//document.getElementById("selBatchNoArrayId")[i].disabled=true;
			document.getElementsByName("selRemarksArray")[i].disabled=true;
			
			if(document.getElementsByName("selIvFluidFlagArray")[i].value!=0)
			{
				document.getElementsByName("selEndExecutionTimeHrs")[i].disabled=true;
				document.getElementsByName("selEndExecutionTimeMin")[i].disabled=true;
				document.getElementsByName("selVolumeArray")[i].disabled=true;
				/*
				document.getElementById("ivfluidFlagId").style.display="";
				document.getElementById("ivfluidFlagLableId").style.display="";
				*/
			}
		}
	}
	
}

function validateForm(mode)
{
	//validating ivFluids
	//alert("hi");
	var ivFluidLen=document.getElementsByName('ivFluidSelIndexArray').length;
	var len=document.getElementsByName('selIndexArray').length;
	var extLen=document.getElementsByName('extSelIndexArray').length;
	
	//alert("ivFluidLen "+ ivFluidLen);
	//alert("len "+ len);
	//alert("extLen "+extLen);
	var saveFlag=false;
	
	for(var i=0;i<ivFluidLen;i++)
	{
		if(document.getElementsByName('ivFluidSelIndexArray')[i].checked)
		{
			saveFlag=true;
		}
	}
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName('selIndexArray')[i].checked)
		{
			saveFlag=true;
		}
	}
	//alert(document.getElementsByName("sosSelDrugId")[0]);
	//alert(typeof document.getElementsByName("sosSelDrugId")[0]);
	
	if(typeof document.getElementsByName("sosSelDrugId")[0] != 'undefined')
	{ 
		if(document.getElementsByName("sosSelDrugId")[0].value!="")
		{
			saveFlag=true;
		}
	}
	
	for(var i=0;i<extLen;i++)
	{
		if(document.getElementsByName('extSelIndexArray')[i].checked)
		{
			saveFlag=true;
		}
	}
	
	
	if(typeof document.getElementsByName("activityExtId")[0] != 'undefined')
	{ 
		//alert("hiiiiiiiiiiiiii");
		if(document.getElementsByName("activityExtId")[0].value!="")
		{
			saveFlag=true;
		}
	}
	
	if(saveFlag==false)
	{
		alert("Please select Drug");
		return false;
	}
	
	for(var i=0;i<ivFluidLen;i++)
	{
		if(document.getElementsByName('ivFluidSelIndexArray')[i].checked)
		{
			if(document.getElementsByName("ivFluidSelEndExecutionTimeHrs")[i].value=="")
			{
				alert("Please enter end time hours");
				document.getElementsByName("ivFluidSelEndExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("ivFluidSelEndExecutionTimeHrs")[i].value>23)
			{
				alert("Hours can not be greator than 23");
				document.getElementsByName("ivFluidSelEndExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("ivFluidSelEndExecutionTimeMin")[i].value=="")
			{
				alert("Please enter end time minute");
				document.getElementsByName("ivFluidSelEndExecutionTimeMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("ivFluidSelEndExecutionTimeMin")[i].value>59)
			{
				alert("Minut can not be greator than 59");
				document.getElementsByName("ivFluidSelEndExecutionTimeMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("ivFluidSelVolumeArray")[i].value=="")
			{
				alert("Please enter the volume");
				document.getElementsByName("ivFluidSelVolumeArray")[i].focus();
				return false;
			}
		}
	}
	
	// validating drug execution	
	
	
	
	for(var i=0;i<len;i++)
	{
		if(document.getElementsByName('selIndexArray')[i].checked)
		{
			var strDate=document.getElementsByName('sysDate')[0].value;
			var strSchDate=document.getElementsByName('selScheduleDate')[i].value + ' ' + document.getElementsByName("selDoseTimeArray")[i].value;
			var strActDate=document.getElementsByName('selScheduleDateArray')[i].value + ' ' + document.getElementsByName("selStartExecutionTimeHrs")[i].value+':'+document.getElementsByName("selStartExecutionTimeMin")[i].value;
			
			
		//	alert(strDate);
		//	alert(strSchDate);
		//	alert(strActDate);
			
			
			var objSysDate = convertStrToDate(strDate, 'dd-Mon-yyyy hh:mm');
			var objScheduleDate = convertStrToDate(strSchDate, 'dd-Mon-yyyy hh:mm');
			var objActualDate = convertStrToDate(strActDate, 'dd-Mon-yyyy hh:mm');
				
			
		//	alert(objSysDate);
		//	alert(objScheduleDate);
		//	alert(objActualDate);
			
			var beforeTimeLimit=document.getElementsByName("beforeTimeLimitArray")[i].value;
			var afterTimeLimit=document.getElementsByName("afterTimeLimitArray")[i].value;
			
			//alert(afterTimeLimit);
		    //alert(beforeTimeLimit);
			
			var diff=dateDifference(objActualDate,objScheduleDate,'H');
		//	alert(diff);
			if(diff ==null)
			{
				diff=dateDifference(objScheduleDate,objActualDate,'H');
				if(diff > beforeTimeLimit)
				{
				alert("Drug can not be given before "+ beforeTimeLimit +" hours of schedule.");
				return false;
				}
				
			}
			
			else if(diff !=null)
			{
				
			//	alert(diff);
					if(diff > afterTimeLimit)
					{
						alert("Drug can not be given after "+ afterTimeLimit +" hours of schedule.");
						return false;
					}
			}
			
			
			
			/*else
				{
			return true;
				}
			
			*/
			var doseTime=document.getElementsByName("selDoseTimeArray")[i].value;
			var doseTimeHours=trimNum(doseTime.split(":")[0]);
			var doseTimeMinut=doseTime.split(":")[1];
			//alert("doseTimeHours "+doseTimeHours);
			//alert("doseTimeMinut "+doseTimeMinut);
			
			
			
			
			var sysHours=document.getElementsByName("sysHours")[0].value;
			var sysMinut=document.getElementsByName("sysMinut")[0].value;
			//alert("sysHours "+sysHours);
			//alert("sysMinut "+sysMinut);
			
			
			var timeLimit=document.getElementsByName("timeLimit")[0].value;
			//alert("timeLimit "+timeLimit);
			
			//commented on date: 20.10.2016
			var totalTimeLimitHours=parseInt(doseTimeHours)+parseInt(timeLimit);
			
			//var totalTimeLimitHours=document.getElementsByName("afterTimeLimit")[0].value;;
			
			var totalTimeLimitMinut=doseTimeMinut;
			//alert("totalTimeLimitHours "+totalTimeLimitHours);
			//alert("totalTimeLimitMinut "+totalTimeLimitMinut);
			
			//var beforeTimeLimit=document.getElementsByName("beforeTimeLimit")[0].value;
			
			var totalBefTimeLmtHour=parseInt(doseTimeHours)-parseInt(beforeTimeLimit);
			var totalBefTimeLmtMinut=doseTimeMinut;
			
			//alert(document.getElementsByName("selStartExecutionTimeHrs")[i].value);
			
			
			/*
			if(document.getElementsByName("selDoseIdArray")[i].value=="")
			{
				alert("Please select dose");
				document.getElementsByName("selDoseIdArray")[i].focus();
				return false;
			}
			*/
			if(document.getElementsByName("selDrugRouteIdArray")[i].value=="")
			{
				alert("Please select drug route");
				document.getElementsByName("selDrugRouteIdArray")[i].focus();
				return false;
			}
			if(document.getElementsByName("selStartExecutionTimeHrs")[i].value=="")
			{
				alert("Please enter start hours");
				document.getElementsByName("selStartExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("selStartExecutionTimeHrs")[i].value>23)
			{
				alert("Hours can not be greater than 23");
				document.getElementsByName("selStartExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("selStartExecutionTimeMin")[i].value=="")
			{
				alert("Please enter start Minut");
				document.getElementsByName("selStartExecutionTimeMin")[i].focus();
				return false;
			}
			if(document.getElementsByName("selStartExecutionTimeMin")[i].value>59)
			{
				alert("Minut can not be greater than 59");
				document.getElementsByName("selStartExecutionTimeMin")[i].focus();
				return false;
			}
			
              //////////////////////Commented date: 21.10.2016 changein timelimit now coming from databse  bym
			/*if(document.getElementsByName("selStartExecutionTimeHrs")[i].value<totalBefTimeLmtHour)
			{
				alert("Drug can not be given befor "+ document.getElementsByName("beforeTimeLimit")[0].value +" hours of schedule.");
				document.getElementsByName("selStartExecutionTimeHrs")[i].focus();
				return false;
			}
			
			if(document.getElementsByName("selStartExecutionTimeHrs")[i].value==totalBefTimeLmtMinut)
			{
				if(document.getElementsByName("selStartExecutionTimeMin")[i].value<totalBefTimeLmtMinut)
				{
					alert("Drug can not be given befor "+ document.getElementsByName("beforeTimeLimit")[0].value +" hours of schedule.");
					document.getElementsByName("selStartExecutionTimeMin")[i].focus();
					return false;
				}
			}
			
			
			if(document.getElementsByName("selStartExecutionTimeHrs")[i].value>totalTimeLimitHours)
			{
				alert("Drug can not be given after "+ document.getElementsByName("timeLimit")[0].value +" hours of schedule.");
				document.getElementsByName("selStartExecutionTimeHrs")[i].focus();
				return false;
			}
			if(document.getElementsByName("selStartExecutionTimeHrs")[i].value==totalTimeLimitHours)
			{
				if(document.getElementsByName("selStartExecutionTimeMin")[i].value>totalTimeLimitMinut)
				{
					alert("Drug can not be given after "+ document.getElementsByName("timeLimit")[0].value +" hours of schedule.");
					document.getElementsByName("selStartExecutionTimeMin")[i].focus();
					return false;
				}
				
			}*/  //////////////////////  Commented date: 21.10.2016 changein timelimit now coming from databse 
			
			/*
			if(document.getElementsByName("selIvFluidFlagArray")[i].value!=0)
			{
				document.getElementsByName("selEndExecutionTimeHrs")[i].disabled=false;
				document.getElementsByName("selEndExecutionTimeMin")[i].disabled=false;
				
				if(document.getElementsByName("selEndExecutionTimeHrs")[i].value=="")
				{
					alert("Please enter end time hours");
					document.getElementsByName("selEndExecutionTimeHrs")[i].focus();
					return false;
				}
				
				if(document.getElementsByName("selEndExecutionTimeMin")[i].value=="")
				{
					alert("Please enter end time minut");
					document.getElementsByName("selEndExecutionTimeMin")[i].focus();
					return false;
				}
			}
			*/
			
		/*	if(!(((document.getElementsByName("selDrugBrandArray")[i].value=="") || (document.getElementsByName("selDrugBrandArrayFromStore")[i].value==""))))
			{	
				alert("Please select Drug Brand");
				return false;
			}*/
			
	//		##########  commented for interimarrangement has to be undone later date: 17.10.2016
		/*	
			 if(!(((document.getElementsByName("selPatientBatchNoArray")[i].value=="") || (document.getElementsByName("selBatchNoArray")[i].value==""))))
				{	
					alert("Please enter batch number");
					return false;
				}
			
			if(document.getElementsByName("selExpriryDateArray")[i].value=="")
			{
				alert("Please select expiry date");
				document.getElementsByName("selExpriryDateArray")[i].focus();
				return false;
			}
			if(checkDate(document.getElementsByName("selExpriryDateArray")[i].value,document.getElementsByName("sysDate")[0].value)>0)
			{
				alert("This drug is expired");
				document.getElementsByName("selExpriryDateArray")[i].focus();
				return false;
			}*/  /// ###End
			
			
		}
	}
	// validating first row of SOS drug
	if(typeof document.getElementsByName("sosSelDrugId")[0] != 'undefined')
	{
		if(document.getElementsByName("sosSelDrugId")[0].value!="" || document.getElementsByName("sosSelDoseId")[0].value!="" || document.getElementsByName("sosSelDrugRouteId")[0].value!="" || document.getElementsByName("sosSelStartExecutionTimeHrs")[0].value!="" || document.getElementsByName("sosSelStartExecutionTimeMin")[0].value!="" || document.getElementsByName("sosSelBatchNo")[0].value!="" || document.getElementsByName("sosSelExpriryDate")[0].value!="" || document.getElementsByName("sosSelRemarks")[0].value!="")
		{
			if(document.getElementsByName("sosSelDrugId")[0].value=="")
			{
				alert("Please Select Drug");
				document.getElementsByName("sosSelDrugId")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelDoseId")[0].value=="")
			{
				alert("Please Select Doses");
				document.getElementsByName("sosSelDoseId")[0].focus();
				return false;
			}	
			if(document.getElementsByName("sosSelDrugRouteId")[0].value=="")
			{
				alert("Please Select Drug Route");
				document.getElementsByName("sosSelDrugRouteId")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelStartExecutionTimeHrs")[0].value=="" )
			{
				alert("Please Enter Start  Hours");
				document.getElementsByName("sosSelStartExecutionTimeHrs")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelStartExecutionTimeHrs")[0].value>23)
			{
				alert("Hours can not be greater than 24");
				document.getElementsByName("sosSelStartExecutionTimeHrs")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelStartExecutionTimeMin")[0].value=="" )
			{
				alert("Please Enter Start Minute");
				document.getElementsByName("sosSelStartExecutionTimeMin")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelStartExecutionTimeMin")[0].value>59)
			{
				alert("Minut can not be greater than 59");
				document.getElementsByName("sosSelStartExecutionTimeMin")[0].focus();
				return false;
			}
			
			// ########  interim arrangment has to undone date: 17.10.2016
			/*if(document.getElementsByName("sosSelBatchNo")[0].value=="")
			{
				alert("Please Enter Batch No.");
				document.getElementsByName("sosSelBatchNo")[0].focus();
				return false;
			}
			if(document.getElementsByName("sosSelExpriryDate")[0].value=="")
			{
				alert("Please Select Expiry Date");
				document.getElementsByName("sosSelExpriryDate")[0].focus();
				return false;
			}
		
			if(checkDate(document.getElementsByName("sosSelExpriryDate")[0].value,document.getElementsByName("sysDate")[0].value)>0)
			{
				alert("This drug is expired");
				document.getElementsByName("sosSelExpriryDate")[0].focus();
				return false;
			}*/
		
		}
	}
	
	//validating first row of activity
	if(typeof document.getElementsByName("activityExtId")[0] != 'undefined')
	{
		if(document.getElementsByName("activityExtId")[0].value!="" || document.getElementsByName("activitySelStartExecTimeHrs")[0].value!="" || document.getElementsByName("activitySelStartExecTimeMin")[0].value!="")
		{
			if(document.getElementsByName("activityExtId")[0].value=="")
			{
				alert("Please Select Activity");
				document.getElementsByName("activityExtId")[0].focus();
				return false;
			}
			if(document.getElementsByName("activitySelStartExecTimeHrs")[0].value=="")
			{
				alert("Please Enter Hours");
				document.getElementsByName("activitySelStartExecTimeHrs")[0].focus();
				return false;
			}
			if(document.getElementsByName("activitySelStartExecTimeMin")[0].value=="")
			{
				alert("Please Enter Minute");
				document.getElementsByName("activitySelStartExecTimeMin")[0].focus();
				return false;
			}
		}
	}
	// validate ext treatment exec
	
	for(var i=0;i<extLen;i++)
	{
		if(document.getElementsByName('extSelIndexArray')[i].checked)
		{
			if(document.getElementsByName("extSelStartExecutionTimeHrs")[i].value=="")
		{
			alert("Please Enter Start Time Hours");
			document.getElementsByName("extSelStartExecutionTimeHrs")[i].focus();
			return false;
		}
		
		if(document.getElementsByName("extSelStartExecutionTimeMin")[i].value=="")
		{
			alert("Please Enter Start Time Minute");
			document.getElementsByName("extSelStartExecutionTimeMin")[i].focus();
			return false;
		}
		if(document.getElementsByName("extSelIsDurationBound")[i].value=="1")
		{
			if(document.getElementsByName("extSelEndExecutionTimeHrs")[i].value=="")
			{
				alert("Please Enter End Time Hours");
				document.getElementsByName("extSelEndExecutionTimeHrs")[i].focus();
				return false;
			}
		
			if(document.getElementsByName("extSelEndExecutionTimeMin")[i].value=="")
			{
				alert("Please Enter End Time Minute");
				document.getElementsByName("extSelEndExecutionTimeMin")[i].focus();
				return false;
			}
		}
		}
	}
	
	submitPage(mode);
}


function trimNum(n)
{
	if(n.substr(0,1)=='0')	n=n.substr(1);
	return n;
}

function submitPage(mode)
{
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
}

function getDrugGivenDetail(event)
{
	var sysDate=document.getElementsByName("sysDate")[0].value;
	var patCrNo=document.getElementsByName("patCrNo")[0].value;
	var admissionDate=document.getElementsByName("admissionDate")[0].value;
	//alert("patCrNo "+patCrNo);
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=VIEW&treatFromDate='+sysDate+'&treatToDate='+sysDate+'&patCrNo='+patCrNo+'&admissionDate='+admissionDate+'&sysDate='+sysDate;
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,400,700,true);
}

function getInstructionDetail(event)
{
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=INSTRUCTION';
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,250,500,true);
}

function getOtherTreatDetail(event)
{
	var path='/HISClinical/inpatient/nurDrugAdministration.cnt?hmode=OTHERTREATMENT';
	//alert("path "+path);
	openDependentPopup(createFHashAjaxQuery(path),event,400,600,true);

}

function AddRowToTable(validate,mode)
{
	
	if(validate)
	{
		document.getElementsByName('hmode')[0].value=mode
		document.forms[0].submit();
	}
	
}

function AddRowToActivityTable(validate,mode)
{
	//alert(validate);
	if(validate)
	{
		//alert("inside");
		document.getElementsByName('hmode')[0].value=mode
		document.forms[0].submit();
	}
	/*
	document.getElementsByName('hmode')[0].value=mode
	document.forms[0].submit();
	*/
}
function deleteRow(mode,obj)
{
	document.getElementsByName('hmode')[0].value=mode
	document.getElementsByName('index')[0].value=obj
	document.forms[0].submit();
}

function deleteActivityRow(mode,obj)
{
	document.getElementsByName('hmode')[0].value=mode
	document.getElementsByName('index')[0].value=obj
	document.forms[0].submit();
}

function validateActivityRow()
{
	if(document.getElementsByName("activityExtId")[0].value=="")
	{
		alert("Please Select Activity");
		document.getElementsByName("activityExtId")[0].focus();
		return false;
	}
	if(document.getElementsByName("activitySelStartExecTimeHrs")[0].value=="")
	{
		alert("Please Enter Hours");
		document.getElementsByName("activitySelStartExecTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("activitySelStartExecTimeMin")[0].value=="")
	{
		alert("Please Enter Minute");
		document.getElementsByName("activitySelStartExecTimeMin")[0].focus();
		return false;
	}
	
	return true;
}

function validateRow()
{
	if(document.getElementsByName("sosSelDrugId")[0].value=="")
		{
			alert("Please Select Drug");
			document.getElementsByName("sosSelDrugId")[0].focus();
			return false;
		}
	if(document.getElementsByName("sosSelDoseId")[0].value=="")
	{
		alert("Please Select Doses");
		document.getElementsByName("sosSelDoseId")[0].focus();
		return false;
	}	
	if(document.getElementsByName("sosSelDrugRouteId")[0].value=="")
	{
		alert("Please Select Drug Route");
		document.getElementsByName("sosSelDrugRouteId")[0].focus();
		return false;
	}
	if(document.getElementsByName("sosSelStartExecutionTimeHrs")[0].value=="" )
	{
		alert("Please Enter Start  Hours");
		document.getElementsByName("sosSelStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("sosSelStartExecutionTimeHrs")[0].value>23)
	{
		//alert("Hours can not be greater than 24");
		document.getElementsByName("sosSelStartExecutionTimeHrs")[0].focus();
		return false;
	}
	if(document.getElementsByName("sosSelStartExecutionTimeMin")[0].value=="" )
	{
		alert("Please Enter Start Minute");
		document.getElementsByName("sosSelStartExecutionTimeMin")[0].focus();
		return false;
	}
	if(document.getElementsByName("sosSelStartExecutionTimeMin")[0].value>59)
	{
		//alert("Minut can not be greater than 59");
		document.getElementsByName("sosSelStartExecutionTimeMin")[0].focus();
		return false;
	}
	// added as interim arrangement needed to be undone later date: 19.10.2016
	/*if(document.getElementsByName("sosSelBatchNo")[0].value=="")
	{
		alert("Please Enter Batch No.");
		document.getElementsByName("sosSelBatchNo")[0].focus();
		return false;
	}
	if(document.getElementsByName("sosSelExpriryDate")[0].value=="")
	{
		alert("Please Select Expiry Date");
		document.getElementsByName("sosSelExpriryDate")[0].focus();
		return false;
	}
	
	
	if(document.getElementsByName("selDrugBrandArraySOS")[0].value=="")
	{
		alert("Please Select Drug Route");
		document.getElementsByName("selDrugBrandArraySOS")[0].focus();
		return false;
	}
	
	
	*/
	
	if(checkDate(document.getElementsByName("sosSelExpriryDate")[0].value,document.getElementsByName("sysDate")[0].value)>0)
	{
		alert("This drug is expired");
		document.getElementsByName("sosSelExpriryDate")[0].focus();
		return false;
	}
	
	return true;
}

function checkDate(a,b)
{
	//alert("inside check date");
	var valid=true;
	var days=0;
	//var a=document.getElementsByName("sosSelExpriryDate")[0].value;
	//alert("expiry date "+ a);
	var aArray=a.split("-");
	var aday=aArray[0];
	var amonth=aArray[1];
	var ayear=aArray[2];
	var adate=new Date(amonth +" "+ aday+" "+ayear);
	//var b=document.getElementsByName("sysDate")[0].value; 
	//alert("sysdate -> "+b);
	//var b=new Date;
	var bArray=b.split("-");
	var bday=bArray[0];
	var bmonth=bArray[1];
	var byear=bArray[2];
	var bdate=new Date(bmonth +" "+ bday+" "+byear);
	days=((bdate-adate)/86400000);
	//alert("days-------> "+days);
	return days;
}

function notAllowChangeDose(doseId,index)
{
	//alert("doseId "+doseId);
	//alert("index "+index);
	
	if(document.getElementsByName("selDoseIdArray")[index].value!=doseId)
	{
		alert("You can not change the dose");
		document.getElementsByName("selDoseIdArray")[index].value=doseId;
	}
}

function notAllowChangeRoute(routeId,index)
{
	
	if(routeId!=null)
	{
		if(document.getElementsByName("selDrugRouteIdArray")[index].value!=routeId)
		{
			alert("You can not change the route");
			document.getElementsByName("selDrugRouteIdArray")[index].value=routeId;
		}
	}
	
}

function showLegends()
{
document.getElementById("legendId").style.display="block";
}


function hideLegends()
{
document.getElementById("legendId").style.display="none";
}

/*************************for getting drug property********************************/
var modeGbl="";
var itemIdGbl="";
function myAjaxFunction(myurl,mode) {

	var result = "";
	modeGbl=mode;
	// checking browser
	if (navigator.userAgent.indexOf("Opera") >= 0) {
		alert("This example doesn't work in Opera");
		return;
	}
	if (navigator.userAgent.indexOf("MSIE") >= 0) {
		var strName = "Msxml2.XMLHTTP"
		if (navigator.appVersion.indexOf("MSIE 5.5") >= 0) {
			strName = "Microsoft.XMLHTTP"
		}
		try {
			objXmlHttp = new ActiveXObject(strName)
			objXmlHttp.onreadystatechange = function() {
				if (objXmlHttp.readyState == 4 || objXmlHttp.readyState == 200) {
					result = objXmlHttp.responseText;
					ajaxResponse(result,modeGbl);
				}

			}
		} catch (e) {
			alert("Error. Scripting for ActiveX might be disabled");
			return;
		}
	} else {
		if (navigator.userAgent.indexOf("Mozilla") >= 0) {
			objXmlHttp = new XMLHttpRequest();
			objXmlHttp.onload = function() {
				if (objXmlHttp.readyState == 4 || objXmlHttp.readyState == 200) {
					result = objXmlHttp.responseText;
					//alert(result);
					ajaxResponse(result,mode)
					
				}

			}
			//objXmlHttp.onerror=sendMyReq
		}
	}
	
	objXmlHttp.open("GET", myurl, false)
	
	objXmlHttp.send(null)
	
	return result;

}

function ajaxResponse(res,mode){
	if(mode==1){
		//alert("res"+res);
		//alert("mode "+mode);
		document.getElementById("drugProfileId").innerHTML=res;
			var mode="saftyData";
						
	        var url="/HISClinical/mms/transactions/DrugProfileCNT.cnt?hmode="+mode+"&strItemID="+itemIdGbl;
	        // alert("url "+url);
	         myAjaxFunction(url,"2");
	}
	if(mode==2){
		//alert("res"+res);
		
		document.getElementById("drugSafetyId").innerHTML=res;
		
	}
}
function getDrugDetail(itemId,index)
{
	//alert("itemId "+ itemId);
	document.getElementById("drugSafetyId").innerHTML="";
	document.getElementById("drugProfileId").innerHTML="";
	
	var mode="dosageData";
	itemIdGbl=itemId;
	var url="/HISClinical/mms/transactions/DrugProfileCNT.cnt?hmode="+mode+"&strItemID="+itemIdGbl;
	
	myAjaxFunction(url,"1");
	
	document.getElementById('blanket').style.display="block";
	document.getElementById('userIdDiv').style.display="block";
		
}

function cancel()
{
	document.getElementById('blanket').style.display="none";
	document.getElementById('userIdDiv').style.display="none";
}
/****************************************************************************************************/

function showExpiryDate(index)
{
	
	//alert("index "+index);
	var expiryDate=document.getElementsByName("selBatchNoArray")[index].value.split("#")[1];
	//alert("expiryDate "+expiryDate);
	
	if(typeof expiryDate!='undefined')
	{
		document.getElementsByName("selExpriryDateArray")[index].value=expiryDate;
	}
	else
	{
		document.getElementsByName("selExpriryDateArray")[index].value="";
	}
	
}


 function fromPatientValue(obj,index)
  {
	 var storeFlag=true;
    	var DivDrugRoouteConcatId="drugRouteTD"+index;
		var DivFromPatientConcatId="fromPatientDivId"+index;
		//alert("DivDrugRoouteConcatId::"+DivDrugRoouteConcatId);
		//alert("DivFromPatientConcatId::"+DivFromPatientConcatId);
     	 var fromPatientDiv= document.getElementsByName("fromPatientcheckBox")[index].checked;
	 	  if (document.getElementsByName("fromPatientcheckBox")[index].checked) {
	             	document.getElementById(DivDrugRoouteConcatId).style.display="none";
					document.getElementById(DivFromPatientConcatId).style.display="";
				   	document.getElementById("batch"+index).style.display="none";
					document.getElementById("batchFromPatient"+index).style.display="";
					document.getElementsByName("drugStoreSourceArray")[index].value='1';
					document.getElementsByName("selExpriryDateArray")[index].value='';
					document.getElementById('IselExpriryDateArray'+index).style.display="";
					//document.getElementById('selDrugBrandArray').style.display="";
				//	document.getElementById('selDrugBrandArrayFromStore').style.display="none";
					storeFlag=false;
					handleGetSetBrandId(index,storeFlag);
				
	            
	        } else {
             	document.getElementById(DivDrugRoouteConcatId).style.display="";
				document.getElementById(DivFromPatientConcatId).style.display="none";
				document.getElementById("batch"+index).style.display="";
				document.getElementById("batchFromPatient"+index).style.display="none";
				
				document.getElementsByName("drugStoreSourceArray")[index].value='0';
				document.getElementsByName("selExpriryDateArray")[index].value='';
				document.getElementById('IselExpriryDateArray'+index).style.display="none";
				storeFlag=true;
				handleGetSetBrandId(index,storeFlag);
	        }
       
	        
 }
 
 
 function showDivAlert(){
		$('#divSummary').show();
		
	}
 
 function hideDivALert(){
		$('#divSummary').hide();
	}
 
 
 
 
 
 
 
 