var elemSelText = null;
var popupList =null;
var elemDiagnosisCode = null;
var elemDiagnosisName = null;
var selectIndexVal = -1;
var selectedRowIndex=null;

var elemDiseaseNameCodeList = null;
var elemDiseaseCodeNameList = null;
 function diagnosisOnload()
{	
	 //alert("inside body on load");
	        //setDIagnosticTypeCode1();
	        load1('2','BODY_STRUCTURE');
			load1('1','DISORDER');
			elemDiagnosisCode = "snomedCTDiagnosisSiteCode";
			elemDiagnosisName = "txt-snomed-ct-search_1";
			document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
			document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";
		
			
			
			
 }


 
 function ShowOtherSnomedDiv(flg)
 {
	
	// alert(flg);
	 	if (flg=="1" || flg==undefined || flg==" ") 
		{
	 		document.getElementById("otherDiagDiv").style.display="none";
	 		document.getElementById("dialog-form_1").style.display="";
	 		document.getElementsByName("icdNHospitalFlagValue")[0].value = "1";
	 		document.getElementById("divMappedOtherICD").style.display="none";
	 		document.getElementById("divMappedICD").style.display="";
		}
	 
	 	if (flg=="2") 
			{
			document.getElementById("otherDiagDiv").style.display="";
			document.getElementById("dialog-form_1").style.display="none";
			document.getElementsByName("icdNHospitalFlagValue")[0].value = "2";
			document.getElementById("divMappedICD").style.display="none";
			document.getElementById("divMappedOtherICD").style.display="";
			}
	 	
	 //	 alert(document.getElementsByName("icdNHospitalFlagValue")[0].value);
 }


function setValue1(selectedSNOMEDTerm)
{
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;
	
	
	document.getElementsByName("snomedCTDiagnosisName")[0].value=str;
	document.getElementsByName("snomedCTDiagnosisCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_1")[0].value=str;
	document.getElementById("somectIddata").innerHTML =str1;
	snomedtoICDMapping(str1);
	
	
		}
	else
		{
		document.getElementsByName("snomedCTDiagnosisName")[0].value="";
		document.getElementsByName("snomedCTDiagnosisCode")[0].value="";
		 document.getElementById("somectIddata").innerHTML ="";
		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
}


function setValue2(selectedSNOMEDTerm)
{
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;

	
	document.getElementsByName("snomedCTDiagnosisSiteName")[0].value=str;
	document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value=str1;
	document.getElementsByName("txt-snomed-ct-search_2")[0].value=str;
	//document.getElementById("somectIddata").innerHTML =str1;
	//findingSiteForDisease(str1);
		}
	else
		{
		document.getElementsByName("snomedCTDiagnosisSiteName")[0].value="";
		document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	
}


function setValue4(selectedSNOMEDTerm)
{
	if(selectedSNOMEDTerm !="undefined" && selectedSNOMEDTerm!=null && selectedSNOMEDTerm!="")
		{
	
	var str=selectedSNOMEDTerm.term;
	var str1=selectedSNOMEDTerm.conceptId;

	 document.getElementsByName("txt-snomed-ct-search_4")[0].value=str;
	
	 targetPrefferedTerm=document.getElementsByName("snomedPTRemarks")[0];  //preffered term
	 targetConceptId=document.getElementsByName("snomedCIdRemarks")[0];   //concept Id
	 document.getElementsByName("remarks")[0].value=document.getElementsByName("remarks")[0].value + ' ' + str;
	 

 
    if(targetPrefferedTerm.value=="")  targetPrefferedTerm.value = str;
	else targetPrefferedTerm.value = targetPrefferedTerm.value + "#" + str;
	
    if(targetConceptId.value=="")  targetConceptId.value = str1;
	else targetConceptId.value = targetConceptId.value + "#" + str1;
	
    targetPrefferedTerm="";
    targetConceptId="";

		}
	else
		{
		document.getElementsByName("snomedPTRemarks")[0].value="";
		document.getElementsByName("snomedCIdRemarks")[0].value="";
		// document.getElementById("somectIddata").innerHTML ="";
		}
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide();
	
}



function load1(elmtId,semantictag)
{

	
	//alert("inside load");
	//alert(elmtId+','+semantictag);
	if(elmtId=="4")
	{
	var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};
	selectSNOMEDCTmulti('ACTIVE','','SYNONYMS','10','null','4',callbck_index4);
	}
	else
		{
	if(elmtId=="1")
		{
		var callbck_index_DA =function(ret_OUT){setValue1(ret_OUT);};
		}
	if(elmtId=="2")
		{
		var callbck_index_DA =function(ret_OUT){setValue2(ret_OUT);};
		}
	
		selectSNOMEDCTauto('ACTIVE',semantictag,'SYNONYMS','10','null', elmtId,callbck_index_DA);

		}
	
	$("#conceptdiv_1").hide();
	$("#norecorddiv_1").hide();
	$("#conceptdiv_2").hide();
	$("#norecorddiv_2").hide();
	$("#conceptdiv_4").hide();
	$("#norecorddiv_4").hide();
	

	
}
	
	
var callbck_index4 =function(ret_OUT){setValue4(ret_OUT);};


function findingSiteForDisease(snmdctid)
{
	alert("hi");
	var flag="notfound";
	var elemSiteCombo = document.getElementById("seldiagnosticSiteId");
	elemSiteCombo.innerHTML="";
	var op=document.createElement("option");
	op.value="";
	op.innerHTML="Select Value";
	elemSiteCombo.appendChild(op);

	
  var prefTermArray="";
  var obj="";
	// SCTID: 609328004, Allergic disposition (disorder)
	// SCTID: 418634005, Allergic reaction to substance (disorder)
	// SCTID: 282100009, Adverse reaction to substance (disorder)to substance (disorder)
	// SCTID: 421961002  Hypersensitivity reaction (disorder) 
	// SCTID: 419511003  Propensity to adverse reactions to drug (disorder) 
		
			
		$.ajax({
			
			url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/lookup/relationship?id="+snmdctid+"&relation=finding site&direction=source"),
			type : 'GET',
			
			 datatype: "json",
			 async: false,
			  success : function(data) {
				 // alert(data);
				//obj=JSON.parse(data);
						obj = eval(data);
						/*if(obj != null && obj !='undefined')
						{
							if (obj.length > 0) 
							{
						document.getElementById("divfindingsitesnomed").style.display="none";
						document.getElementById("divfindingsite").style.display="";
							}
							else
								{
								document.getElementById("divfindingsitesnomed").style.display="";
								document.getElementById("divfindingsite").style.display="none";
								}
						}
			//	alert(obj.length); 
			//	alert(obj); 
				//alert(obj[0].fullySpecifiedName);
				
				
	},*/

		if(obj != null && obj !='undefined')
		{
			if (obj.length > 0) 
			{
				for(var i=0;i<obj.length;i++)
					{
					//alert("inside snomed");
				//	alert(obj[i]);
					prefTermArray=getSnomedTerm(obj[i].id);
					op=document.createElement("option");
					op.value=obj[i].id;
					op.innerHTML=prefTermArray;
					elemSiteCombo.appendChild(op);
					//alert(prefTermArray);
					}
				document.getElementById("divfindingsitesnomed").style.display="none";
				document.getElementById("divfindingsite").style.display="";
				flag="found";
			} 
			
		}
		else
		{
		document.getElementById("divfindingsitesnomed").style.display="";
		document.getElementById("divfindingsite").style.display="none";
		}
		},
	error: function(data)
	{
	    alert('request failed :');
	}

	});
		
		if(flag=="found")
			setDIagnosticSiteCode1();


			
 
			
}


function  setDIagnosticSiteCode1()
{
	
	var elemSiteCombo = document.getElementById("seldiagnosticSiteId");
	elemSiteCombo.selectedIndex=1;
	setSnomedSiteName();
    
    
}



function getSnomedTerm(id)
{
	var pt = ""; 
	var obj = "";
	
	
	$.ajax({
		url : createFHashAjaxQuery("/AHIMSG5/snomedct/csnoserv/api/lookup/concept?id="+id+"&langrefset=us"),
		type : 'GET',
		 datatype: "json",
		 async: false,
		  success : function(data) {
			  
			  try{
				obj=JSON.parse(data);
				if(obj.descriptions.length != null && obj.descriptions.length !='undefined')
		    {
			if (obj.descriptions.length > 0) 
			{
				//alert("inside");
			for(var j=0;j<obj.descriptions.length;j++)
			{	
				//alert(obj.descriptions[j].isPreferredTerm);
			if(obj.descriptions[j].isPreferredTerm=='1' && obj.descriptions[j].typeId=='SYNONYM')
			{
		       pt=obj.descriptions[j].term;
		       break;
			}
			}
			}}
			  }
			  catch(e)
			  {
				  alert(e.message);
			  }
			
			
},
error: function(data)
{
alert('request failed :');
}

	});
	

	return pt;

}

function setSnomedSiteName()
{
	var obj=document.getElementById("seldiagnosticSiteId");
	document.getElementsByName("snomedCTDiagnosisSiteName")[0].value=obj.options[obj.selectedIndex].text;
	document.getElementsByName("snomedCTDiagnosisSiteCode")[0].value=obj.options[obj.selectedIndex].value;

}
		




function tog(v) { return v ? 'addClass' : 'removeClass'; }
$(document).on('mouseover', '.clearable', function () {
    $(this)[tog(this.value)]('x');
}).on('mousemove', '.x', function (e) {
    $(this)[tog(this.offsetWidth - 18 < e.clientX - this.getBoundingClientRect().left)]('onX');
}).on('touchstart click', '.onX', function (ev) {
    ev.preventDefault();
    $(this).removeClass('x onX').val('').change();
    var id = this.id;
    //alert("hi");
   
   // txt-snomed-ct-search_4
   document.getElementsByName("snomedPTRemarks")[0].value="";  //preffered term
	document.getElementsByName("snomedCIdRemarks")[0].value="";  //concept Id
	document.getElementsByName("remarks")[0].value="";

});
//Desk Episode Diagnosis Tab Javascript

function validateAddty()
{
	var valid=true;
	//document.getElementsByName("icdNHospitalFlagValue")[0].value="4"
	if(!validateRow(0))
			valid=false;
		
	
	//alert(valid);
	return valid;
}


//setting diagnostic type "Provisional" as default selected date : 18.7.2016 by Manisha gangwar
function  setDIagnosticTypeCode1()
{
	
	var elmt=document.getElementsByName('diagonisticTypeCode')[0];
	//alert(elmt);
    for(var i=0;i<elmt.length;i++)
    	{
    	if(elmt.options[i].text=="Provisional")
    		{
    		elmt.selectedIndex=i;
    	break;
    		}
           
    	}
    
}


function submitDiagnosisOnValidate()
{
	AddRowToTableDiag();

}

function validateDiagnosisRow()
{
	var Code,name;
	var valid="true";
	var tableObj=document.getElementById('diagnosisTable');
	rowcount=tableObj.rows.length;
	row =0;
	//alert(rowcount);
		elemDiagnosisName = "txt-snomed-ct-search_1";
		name = document.getElementsByName(elemDiagnosisName)[0];
		elemDiagnosisCode="snomedCTDiagnosisCode";
		
		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
		
		//alert(document.getElementsByName('snomedCTICDCode')[0].value);
		
		if(isEmpty(name,"Diagnosis Name") && comboValidation(diaTypeCode,"Diagnosis Type"))
			{
			if(rowcount>2)
			{
				for(var i=1;i<rowcount-1;i++)
				{
					if((trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value)) && (document.getElementsByName('diagnosisRecordStatus')[i].value!="4"))
					{
						
						alert(trimData(document.getElementsByName(elemDiagnosisName)[row].value)+" Already Added ");
						document.getElementById('somectIddata').innerHTML="";
						document.getElementsByName('txt-snomed-ct-search_1')[0].value="";
						document.getElementsByName('snomedCTDiagnosisCode')[0].value="";
						document.getElementsByName('snomedCTDiagnosisName')[0].value="";
						document.getElementsByName('txt-snomed-ct-search_4')[0].value="";
						
						valid="false";
						break;
					}
				}
			}

		}
		
		var diagremarks=document.getElementsByName('txt-snomed-ct-search_4')[0].value;
		//alert(diagremarks);
		 var RemarksLength = diagremarks.length;
		 //alert(RemarksLength);
		 if(RemarksLength>100)
			 {
	            alert("Maximum 100 characters are allowed in Diagnosis remarks");
	            document.getElementsByName("txt-snomed-ct-search_4")[0].focus();
	            document.getElementsByName('txt-snomed-ct-search_4')[0].value="";
	            valid="false";
			 }
	//	alert(valid);
		return valid;
	
}



function validateRow(row)
{
	var Code,name;
	
		elemDiagnosisName = "txt-snomed-ct-search_1";
		name = document.getElementsByName(elemDiagnosisName)[0];
		elemDiagnosisCode="snomedCTDiagnosisCode";
		
		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row]
		
		if( isEmpty(name,"Diagnosis Name") && comboValidation(diaTypeCode,"Diagnosis Type") )
		{
			
			if(row=0)
			{
				for(var i=1;i<row;i++)
				{
					if(trimData(document.getElementsByName(elemDiagnosisCode)[i].value)==trimData(document.getElementsByName(elemDiagnosisCode)[row].value))
					{
						alert(trimData(document.getElementsByName(elemDiagnosisCode)[row].value)+" Already Added ");
						return false;
					}
				}
			}

		}
		
		return true;
		
	
	

}

function deleteDiagRow(Str)
{	
	//alert(Str);
	//document.getElementsByName('diagnosisCount')[0].value=(parseInt(document.getElementsByName('diagnosisCount')[0].value) - 1).toString();
	var tableObj=document.getElementById('diagnosisTable');
	var temp=document.getElementById("diagnosisRow#"+Str);
	//alert(temp);
	tableObj.deleteRow(temp.rowIndex);
	var index=parseInt(document.getElementsByName('numberOfRow')[0].value);
	document.getElementsByName('numberOfRow')[0].value=index-1;
	return true;
}

function hideDiagRow(idx)
{	//alert(idx);
	var tableObj=document.getElementById('diagnosisTable');
	var temp=document.getElementById("diagnosisRow#"+(parseInt(idx)+2));
	temp.style.display ="none";
	document.getElementsByName('diagnosisRecordStatus')[parseInt(idx)+1].value="4";
	return true;
}


function setDiagnosticTypeName()
{
var t=document.getElementById('diagnostictypeId');
var diagtype = t.options[t.selectedIndex].text;
document.getElementsByName('diagnosticTypeName')[0].value=diagtype;
}

function AddRowToTableDiag()
{
	/*if(document.getElementsByName("snomedCTDiagnosisCode")[0].value=="" || document.getElementsByName("snomedCTDiagnosisCode")[0].value==0)
		{
			alert("Please Enter Drug Name");
			//setToDesiredTab("tabDrugAdvice");
			document.getElementsByName("txt-snomed-ct-search_1")[0].focus();
			return false;
		}
	else
		{*/
	
	
	if(document.getElementsByName("icdNHospitalFlagValue")[0].value=="2")
		{
		
		document.getElementsByName('txt-snomed-ct-search_1')[0].value=document.getElementsByName('txt-other')[0].value;
		document.getElementsByName('snomedCTDiagnosisCode')[0].value=document.getElementsByName('txt-other')[0].value;
		document.getElementsByName('snomedCTDiagnosisName')[0].value=document.getElementsByName('txt-other')[0].value;
		document.getElementsByName('snomedCTICDCode')[0].value=document.getElementsByName('txt-other-icd')[0].value;
		
		}
	
		
	
	if(document.getElementsByName('diagnosisRecordStatus')[0].value!="1")
	{
		
	if(document.getElementsByName("snomedCTDiagnosisCode")[0].value!="")
	{
	if(validateDiagnosisRow()=="true")
	{
		
	var resp=document.getElementsByName('comboOptionString')[0].value;
	var respDiagnosisSite=document.getElementsByName('comboDiagnosisSite')[0].value;
	//alert(resp); alert(respDiagnosisSite);
	var nRow=0;
	var tableObj=document.getElementById('diagnosisTable');
	var numRows=tableObj.rows.length;
	
	if(numRows>2)
	{
		nRow=tableObj.rows[numRows-1].id.split("#")[1];
	}
	else
	{
		nRow=numRows;
	}
	
	   var tabRow1=tableObj.insertRow(numRows);
		tabRow1.id="diagnosisRow#"+(parseInt(nRow)+1);
	
		var td7=document.createElement("TD");
		var td8=document.createElement("TD");
		var td9=document.createElement("TD");
		var td10=document.createElement("TD");
		var td11=document.createElement("TD");
		var td12=document.createElement("TD");
		var td13=document.createElement("TD"); 
		
		var diagcode=document.getElementsByName('snomedCTDiagnosisCode')[0].value;
		var diagname=document.getElementsByName('snomedCTDiagnosisName')[0].value;
		var t=document.getElementById('diagnostictypeId');
		var diagtype = t.options[t.selectedIndex].text;
		var diagtypecode=document.getElementsByName('diagonisticTypeCode')[0].value;
		
		
		//var diagsitecode=document.getElementsByName('snomedCTDiagnosisSiteCode')[0].value;
		//var diagsitename=document.getElementsByName('snomedCTDiagnosisSiteName')[0].value;
		
		
		var diagremarks=document.getElementsByName('txt-snomed-ct-search_4')[0].value;
	
		var diagicdmappedcode=document.getElementsByName('snomedCTICDCode')[0].value;
		
		td8.innerHTML="<div align='center'>"
			+"<input name='snomedCTDiagnosisCode' value='"+diagcode+"' type='hidden'></input>"
			+"<input name='snomedCTDiagnosisName' value='"+diagname+"' type='hidden'></input>"
			+"<input name='diagnosisRecordStatus' value='2' type='hidden'></input>"
			+diagname +"</div>";
																														
		td8.colspan="1";
		
		//icd 10
		td13.innerHTML="<div align='center'>"  
			+"<input name='snomedCTICDCode' value='"+diagicdmappedcode+"' type='hidden'></input>"+diagicdmappedcode +"</div>";
																													
		td13.colspan="1";
		
		

		td9.innerHTML="<div align='center'>"
			+"<input name='diagonisticTypeCode' value='"+diagtypecode+"' type='hidden'></input>"
			+"<input name='diagnosticTypeName' value='"+diagtype+"' type='hidden'></input>"
			+diagtype +"</div>";
																											
		td9.colspan="1";

				
		/*td10.innerHTML="<div align='center'>"
			+"<input name='snomedCTDiagnosisSiteCode' value='"+diagsitecode+"' type='hidden'></input>"
			+"<input name='snomedCTDiagnosisSiteName' value='"+diagsitename+"' type='hidden'></input>"
			+diagsitename +"</div>";
		td10.className='tdfont';																													
		td10.colspan="1";*/

		td11.innerHTML="<div align='center'>"
			+"<input name='remarks' value='"+diagremarks+"' type='hidden'></input>"
			+diagremarks +"</div>";
																													
		td11.colspan="1";
		
		
		
		td12.colspan="1";
		
		td12.innerHTML="<div align='center'><button type='button' class='btn btn-info btn-sm' style='width:60px;height:28px;' onClick='deleteDiagRow("+(parseInt(nRow)+1)+")'>Delete</div>";
		
		
		
		//tabRow1.appendChild(td7);
		tabRow1.appendChild(td8);
		tabRow1.appendChild(td13);
		tabRow1.appendChild(td9);
		//tabRow1.appendChild(td10);
		tabRow1.appendChild(td11);
		tabRow1.appendChild(td12);
		document.getElementById('somectIddata').innerHTML="";
		document.getElementsByName('txt-snomed-ct-search_1')[0].value="";
		document.getElementsByName('snomedCTDiagnosisCode')[0].value="";
		document.getElementsByName('snomedCTDiagnosisName')[0].value="";
		document.getElementsByName('txt-snomed-ct-search_4')[0].value="";
		
		document.getElementsByName('txt-other')[0].value="";
		document.getElementsByName('txt-other-icd')[0].value="";
		
		var obj=document.getElementById("selMappedICD");
		obj.options[obj.selectedIndex].value="-1";
		document.getElementsByName("snomedCTICDCode")[0].value="";
		
		//document.getElementsByName("selSnomedCTICDCode")[0].value = "";
		//document.getElementsByName("selSnomedCTICDName")[0].value="";
		
		
		
		
		var elmt=document.getElementsByName('diagonisticTypeCode')[0];
//		alert(elmt);
	    for(var i=0;i<elmt.length;i++)
	    	{
	    	if(elmt.options[i].text=="Provisional")
	    		{
	    		elmt.selectedIndex=i;
	    	break;
	    		}
	           
	    	}
	    
	    //convertTableIntoDiv();

	  //  $("#selMappedICD").html("<option value='-1'>Select Value</option>");
	   // document.getElementsByName('snomedCTDiagnosisSiteCode')[0].value="";
		//document.getElementsByName('snomedCTDiagnosisSiteName')[0].value="";
		//document.getElementsByName('txt-snomed-ct-search_2')[0].value="";
	    
		//document.getElementById("seldiagnosticSiteId").value="";
	//	document.getElementById("divfindingsitesnomed").style.display="none";
		//document.getElementById("divfindingsite").style.display="none";
	
		
	    document.getElementsByName('snomedPTRemarks')[0].value="";
		document.getElementsByName('snomedCIdRemarks')[0].value="";
		document.getElementsByName('txt-snomed-ct-search_4')[0].value="";
		document.getElementsByName('diagnosisRecordStatus')[0].value="2";
					
		document.getElementsByName('remarks')[0].value="";
			
		
		load1('2','BODY_STRUCTURE');
		load1('1','DISORDER');
		
	}}
	
	//}
	//document.getElementsByName("selSnomedCTICDCode")[0].value = "";
}}
	
function convertTableIntoDiv()
{
	alert("called...");
	$('#diagnosisTable').replaceWith( 
			  $('table').html()
			   .replace(/<tbody/gi, '<div class="table"')
			   .replace(/<tr/gi, '<div class="row"')
			   .replace(/<\/tr>/gi, '</div>')
			   .replace(/<td/gi, '<div class="col"')
			   .replace(/<\/td>/gi, '</div>')
			   .replace(/<\/tbody/gi, '<\/div')
			);

}


 function load2()
 {
	//alert("inside plus");
	 
	 $("#divdiagnostictype :input").each(function () {
     
     this.outerHTML = "<div>" + this.options[this.selectedIndex].text+ "</div>";
      } ); 
	 
	 $("#divdiagnosticsite :input").each(function () {

	     this.outerHTML = "<div>" + this.options[this.selectedIndex].text+ "</div>";
	 } ); 
		 
	 $("#divremarks :input").each(function () {

	     this.outerHTML = "<div>" + this.value+ "</div>";
	 } ); 
	 
	 $("#dialog-form :input").each(function () {

	     this.outerHTML = "<div>" + this.value+ "</div>";
	 } ); 
	
 }
     
     
function validateIt()
{	
	if(typeof document.getElementsByName("chkPrev")[0] == 'undefined' || document.getElementsByName('numberOfRow')[0].value>1)
	{	
		for(var i=0;i<document.getElementsByName('numberOfRow')[0].value;i++)
		{
			if(!validateRow(i))
				return false;
		}
	}
	else
	{	
		var row=0;
		var Code,name;
		Code = document.getElementsByName(elemDiagnosisCode)[row];
		name = document.getElementsByName(elemDiagnosisName)[row];

		diaTypeCode	= document.getElementsByName("diagonisticTypeCode")[row];
		remark = document.getElementsByName("remarks")[row];
		
		//alert(Code.value+".."+name.value+".."+diaTypeCode!="-1");
		if(Code.value!="" || name.value!="" ||diaTypeCode.value!="-1")
		{
			if(!validateRow(row))
				return false;
		}
		
		var chks = document.getElementsByName("chkPrev");
		if(typeof document.getElementsByName("chkPrev")[0] != 'undefined')
		{
			for( var i=0;i<chks.length;i++)
			{
				if(!chks[i].checked && document.getElementsByName('addDiagnosisCancelRemarks')[i].value =="")
				{
					alert("Enter the Cancel Remark ");
					document.getElementsByName('addDiagnosisCancelRemarks')[i].focus();
					return false;
				}
			}
		}
	}
	return true;
}



