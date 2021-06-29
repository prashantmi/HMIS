
var arrPatDtlGlobalJsonObj=[];

var patientSearch={		
		fetchDefaultValues:function()
		{	
			//alert($('[name="isGlobal"]')[0].checked);
			document.getElementsByName("defaultpatAddCountryCode")[0].value="IND";
			document.getElementsByName("patAddCountryCode")[0].value="IND";
			$('[name="isGlobal"]')[0].checked=true;
			patientSearch.showSearchTile();		
			//document.getElementsByName("patAddStateCode")[0].value="-1";
			//patientSearch.onchange_patAddCountryCode();
			//patientSearch.onchange_patAddStateCode();
			
		},
		showSearchTile:function(){
			patientSearch.onclick_clear();
			//alert($('[name="uniqueIdType"]')[3].checked);
			//if($('[name="isDemographicSearch"]')[1].checked){
				$("#uniqueIdSearchTile").show();
				$('[name="uniqueIdType"]')[0].checked=true;
				$("#demographicSearchTile").hide();		
				$("#searchId").show();		
				$("#submitId").hide();	
				$("#divCatCardNoLabelId").html("<label>"+getFieldLabelBasedOnUniqueIdSearchBy($('[name="uniqueIdType"]')[0])+"</label>&nbsp;<font class='fontred'>*</font>");
				//patientSearch.validateUniqueIdTile();
				$("#divPatDetilId").html("<div class='div-table-row'></div>");
				

			//}
			//else{
				//$("#uniqueIdSearchTile").hide();
				//$("#demographicSearchTile").show();		
			//	$("#searchId").hide();		
			//	$("#submitId").show();	
				//patientSearch.validateDemographicTile();
				//$("#divPatDetilId").html("<div class='div-table-row'></div>");
			//}			
		},
		showHospitalTile:function(){
			if($('[name="isGlobal"]')[1].checked){
				$("#divHospitalAllComboId").show();	
				$("#divHospitalComboId").hide();		
			}
			else{
				$("#divHospitalComboId").show();	
				$("#divHospitalAllComboId").hide();
			}
		},
		validateDemographicTile:function(){
			$('[name="patFirstName"]').validatebox({required: true,validType: ['alphaWithSpace','minLength[3]']});
			$('[name="patMiddleName"]').validatebox({validType: ['alphaWithSpace']});
			$('[name="patLastName"]').validatebox({validType: ['alphaWithSpace']});
			$('[name="patGuardianName"]').validatebox({validType: ['alphaWithSpace']});
			$('[name="patHusbandName"]').validatebox({validType: ['alphaWithSpace']});
			$('[name="patMotherName"]').validatebox({validType: ['alphaWithSpace']});
			$('[name="patAddCity"]').validatebox({validType: ['alphaNumSpecialChar']});
			$('[name="patAddHNo"]').validatebox({validType: ['alphaNumSpecialChar']});
			$('[name="patAddStreet"]').validatebox({validType: ['alphaNumSpecialChar']});
			$('[name="patAddCityLoc"]').validatebox({validType: ['alphaNumSpecialChar']});
			$('[name="patAddPIN"]').validatebox({validType : [ 'numeric', 'equalLength[6]']});

			$('[name="patGenderCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
			$('[name="patAddCountryCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
			if($("#divStateTextId").is(':hidden')){
				//$('[name="patAddStateCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
			}
			else{
				$('[name="patAddStateCode"]').validatebox({required: false});
				$('[name="patAddState"]').validatebox({required: true,validType: ['alphaWithSpace']});
			}
			if($("#divDistrictTextId").is(':hidden')){
				//$('[name="patAddDistrictCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
			}
			else{
				$('[name="patAddDistrictCode"]').validatebox({required: false});
				$('[name="patAddDistrict"]').validatebox({required: true,validType: ['alphaWithSpace']});
			}
			$('[name="patIdNo"]').validatebox({required: false, validType: 'equalLength[13]'});

	
		},
		/*## 		Modification Log							
		  ##		Modify Date				:18thDec'14 
		  ##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
		  ##		Modify By				:Sheeldarshi  */
		showDivDate:function(){
			  $("#divFromDate").show();
			  $("#divToDate").show();
			   $(function() {
					//$("#patFromDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() ));
				//	$("#patToDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", new Date(new Date().getTime() ));
				 	/*$("#patFromDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate", null);
					$("#patToDateId").datepicker({dateFormat: 'dd-M-yy'}).datepicker("setDate",null);*/
				   $('#patFromDateId').DatePicker({	format: 'd-M-Y',default_position :'above', onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} });
				   $('#patToDateId').DatePicker({ format: 'd-M-Y',default_position :'above', onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} });

			  });
		  },
		  checkFromDateValid:function(){
			 
			 if( $('[name="fromDate"]')[0].value!="" && $('[name="fromDate"]')[0].value!="")
					 {
			 			  $("#patFromDateId").validatebox({required: false, validType: ['date[\'fromDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'From Date must be lesser than Today\']','dtltet[\'toDate\',\'FromDate should be less than or equal to ToDate\']']});
					  }
			  
		  },
		  checkToDateValid:function(){
			  if( $('[name="fromDate"]')[0].value!="" && $('[name="fromDate"]')[0].value!="")
				 { 
				  $("#patToDateId").validatebox({required: false, validType: ['date[\'toDate\',\'dd-mmm-yyyy\']','dtltetlastdt[\'To Date must be lesser than Today\']','dtgtet[\'fromDate\',\'ToDate should be greater than or equal to FromDate\']']});
				 }
		  },
		  //End:Sheeldarshi
		validateUniqueIdTile:function(){
			//alert("inside validateUniqueIdTile");
			$('[name="patFirstName"]').validatebox({required: false,validType: ['alphaWithSpace']});
			$('[name="patGenderCode"]').validatebox({required: false,validType: ['selectCombo[-1]']});
			$('[name="patIdNo"]').validatebox({required: true, validType: ['alphaNumeric','equalLength[12]']});
		},
		onchange_patAddCountryCode:function()
		  {
			var objCountry  =document.getElementsByName("patAddCountryCode")[0];
			var countryCode = objCountry.options[objCountry.selectedIndex].value;
			var action 		= "/HISRegistration/registration/transactions/getStatePatientSearch.action?countryCode="+countryCode;
			var flagStateNDIstComboReqd=true;
			document.getElementsByName("defaultpatAddCountryCode")[0].value="IND";
			document.getElementsByName("patAddCountry")[0].value=objCountry.options[objCountry.selectedIndex].text;

			if(countryCode!=document.getElementsByName("defaultpatAddCountryCode")[0].value)
			{
				$("#divStateComboId").hide();
				$("#divStateTextId").show();
				$('[name="patAddState"]')[0].value="";
				$("#divDistrictComboId").hide();
				$("#divDistrictTextId").show();
				$('[name="patAddDistrict"]')[0].value="";
				flagStateNDIstComboReqd=false;
			}
			else
			{
				$("#divStateTextId").hide();
				$("#divStateComboId").show();
				$("#patAddStateCodeId").html("<option value='-1'>Select Value</option>");
				$("#divDistrictTextId").hide();
				$("#divDistrictComboId").show();
				$("patAddDistrictCodeId").html("<option value='-1'>Select Value</option>");
				flagStateNDIstComboReqd=true;

			}
			
			
			
			//alert(action);
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							patientSearch.processGeneralNode(elementName,elementNode);
						}
						
						if(flagStateNDIstComboReqd)
						{
							$('[name="patAddStateCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
							$('[name="patAddDistrictCode"]').validatebox({required: true,validType: ['selectCombo[-1]']});
							//$("#patAddStateCodeId").bind("change",opdregistration.onchange_patAddStateCode);
						}
						else
						{
							$('[name="patAddStateCode"]').validatebox({required: false,validType: ['selectCombo[-1]']});
							$('[name="patAddDistrictCode"]').validatebox({required: false,validType: ['selectCombo[-1]']});
							$("#patAddStateId").validatebox({required: true,validType: 'alphaNumSpecialChar' });
							$("#patAddDistrictId").validatebox({required: true,validType: 'alphaNumSpecialChar' });

						}
							
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  onchange_patAddStateCode:function()
		  {
			var countryCode= document.getElementsByName("patAddCountryCode")[0].value;
			var objState = document.getElementsByName("patAddStateCode")[0];
			//alert(objState.value)
			var stateCode = ""//objState.options[objState.selectedIndex].value;
			
			if(objState.value==null||objState.value=="")
				stateCode = "-1";//objState.options[objState.selectedIndex].value;
			else
				stateCode = objState.options[objState.selectedIndex].value;
							
			var action 			= "/HISRegistration/registration/transactions/getDistrictPatientSearch.action?countryCode="+countryCode+"&stateCode="+stateCode;
			
			
			//alert(action);
			$.ajax({url: action,type:"POST",async:true,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						var rootNode=returnDocument.getElementsByTagName("root")[0];
						for(var i=0;i<rootNode.childNodes.length;i++ )
						{
							var elementNode=rootNode.childNodes[i];
							var elementName=elementNode.nodeName;
							
							patientSearch.processGeneralNode(elementName,elementNode);
							if($('[name="patAddStateCode"]')[0].value==$('[name="defaultpatAddStateCode"]')[0].value)
								$('[name="patAddDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
						}
							
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('onchange_patAddCountryCode '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }});
		  },
		  processGeneralNode:function(elementName,elementNode)
		  {
			var optionText="<option value='-1'>Select Value</option>";
			
			if(elementNode!=null){
				for(var i=0;i<elementNode.childNodes.length;i++ )
				{
					var optionNode=elementNode.childNodes[i];
					optionText+="<option value='"+(optionNode.getAttribute("value"))+"'>"+(optionNode.getAttribute("label"))+"</option>";
					
				}
			}
			if(elementName=="patRefGnctdHospitalDept"){
				optionText+="<option value='0'>Other</option>";
			}
			
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;
			
		  },
		  searchPatientDtl:function()
		  {
			
			  	//alert("hello");
			  	$('#searchId').hide();
			  	var patSearchID ="";var hospitalCode ="";
			  	var patUniqueSearchTypeID = $('[name="hiddenPatUniqueIdType"]')[0].value;
			  	hospitalCode = $('[name="hospitalCode"]')[0].value;
			  	if($('[name="isGlobal"]')[1].checked)
			  		hospitalCode="000";

			  	var patDocType="";
			  	//alert("patUniqueSearchTypeID..............."+patUniqueSearchTypeID);
			  	if(patUniqueSearchTypeID=="3")
			  		{
			  	        var patDocType= $('[name="hiddenPatDocType"]')[0].value;
			  	        patSearchID = $('[name="patCardNo"]')[0].value;
			  		}
			  	else
			  		{
			  		patSearchID = $('[name="patIdNo"]')[0].value;
			  		}
			  	
			  	var action 	= "/HISRegistration/registration/transactions/savePatientSearch.action?patSearchById="+patSearchID+"&patUniqueIdType="+patUniqueSearchTypeID+"&patDocType="+patDocType+"&hospitalCode="+hospitalCode;
				//alert(action);
			  	$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
					arrPatDtlGlobalJsonObj=data;
					//alert(data);
					createPatientEmpRow(data);
					$('#searchId').show();
				},error: function(errorMsg,textstatus,errorthrown) {
					$('#searchId').show();
			        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			        
			    }
				});
		  },
		  submitSearchPatientDtl:function()
		  {
			  	$('#submitId').hide();
				var patFirstName=$('[name="patFirstName"]')[0].value;
			  	var patMiddleName=$('[name="patMiddleName"]')[0].value;
			  	var patLastName=$('[name="patLastName"]')[0].value;
			  	var patGender=$('[name="patGenderCode"]')[0].value;
			  	var patFatherName=$('[name="patGuardianName"]')[0].value;
			  	var patSpouseName=$('[name="patHusbandName"]')[0].value;
			  	var patMotherName=$('[name="patMotherName"]')[0].value;
			  	var patAddCountryCode=$('[name="patAddCountryCode"]')[0].value;
			  	var patAddStateCode=$('[name="patAddStateCode"]')[0].value;
			  	var patAddHNo=$('[name="patAddHNo"]')[0].value;
			  	var patAddStreet=$('[name="patAddStreet"]')[0].value;
			  	var patAddCityLoc=$('[name="patAddCityLoc"]')[0].value;
			  	var patAddDistrictCode=$('[name="patAddDistrictCode"]')[0].value;
			  	var patAddCity=$('[name="patAddCity"]')[0].value;
			  	var patAddPIN=$('[name="patAddPIN"]')[0].value;
			  	var patAge=$('[name="patAge"]')[0].value;
			  	//var patAddLandMarks=$('[name="patAddLandMarks"]')[0].value;
			  	//var patAddLandMarks="";
			  	var patAddState="";
			  	var patAddDistrict="";
				var fromDate=$('[name="fromDate"]')[0].value;
				var toDate=$('[name="toDate"]')[0].value;
				var isUnknown=$('[name="isUnknown"]')[0].value;
			  	//$('#submitId').hide();
			  	if($("#divStateComboId").is(':hidden'))
			  	{
			  		patAddState=$('[name="patAddState"]')[1].value;
			  		patAddStateCode="-1";
			  	}
			  	if($("#divDistrictComboId").is(':hidden'))
			  	{
			  		patAddDistrict=$('[name="patAddDistrict"]')[1].value;	
			  		patAddDistrictCode="-1";
			  	}			  	
			  	var action 	= "/HISRegistration/registration/transactions/searchPatientSearch.action?patFirstName="+patFirstName+"&patMiddleName="+patMiddleName+"&patLastName="+patLastName+"&patGender="+patGender+"&patFatherName="+patFatherName+"&patSpouseName="+patSpouseName+"&patMotherName="+patMotherName+"&patAddCountryCode="+patAddCountryCode+"&patAddStateCode="+patAddStateCode+"&patAddState="+patAddState+"&patAddHNo="+patAddHNo+"&patAddStreet="+patAddStreet+"&patAddCityLoc="+patAddCityLoc+"&patAddDistrictCode="+patAddDistrictCode+"&patAddDistrict="+patAddDistrict+"&patAddCity="+patAddCity+"&patAddPIN="+patAddPIN+"&patAge="+patAge+"&fromDate="+fromDate+"&toDate="+toDate+"&isUnknown="+isUnknown;
			  	//alert(action);
				$.ajax({url: action,type:"POST",async:true,dataType:"json" ,success:function(data){
					arrPatDtlGlobalJsonObj=data;
					//alert(data);
					createPatientEmpRow(data);
					$('#submitId').show();
				},error: function(errorMsg,textstatus,errorthrown) {
					$('#searchId').show();
			        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
			        
			    }
				});
			
		  },
		  onchange_patDocType:function()
		  {
			  	var docTypeObj = $('[name="patDocType"]')[0];
				var docType= docTypeObj.options[docTypeObj.selectedIndex].value;
				//alert("docType....."+docType);
				//alert("docType.after splitting...."+docType.split('#')[1]);
				$('[name="patCardNo"]')[0].value="";
				if(docType!="-1"){
					$("#divCardNoId").show();
					var splittedDocType=docType.split('#');
					$('[name="hiddenPatDocType"]')[0].value=splittedDocType[0];					
					$('[name="patCardNo"]').attr('maxlength', splittedDocType[1]);				
					docValidType= patientSearch.getDocValidtype(splittedDocType[2]);							
					var isize = docType.split("#")[1];
					var docValidTypeNLength='equalLength['+isize+']';
					$('[name="patCardNo"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength]});
					$('[name="patIdNo"]').validatebox({required: false});

				    //$('[name="patCardNo"]').validatebox({required: true, validType: docValidType});

				}else{
					$('[name="patCardNo"]').validatebox({required: false});
					$("#divCardNoId").hide();
				}
			
				
		
				
		  },
		  getDocValidtype:function(idValidTypeCode)
		  {
			var varDocValidType='alphaNumSpecialChar';
			if(idValidTypeCode=="0"){
				varDocValidType = 'alphaNumSpecialChar';
			}else if(idValidTypeCode=="1"){
				varDocValidType = 'numeric';
			}else if(idValidTypeCode=="2"){
				varDocValidType = 'alphaNumeric';
			}else if(idValidTypeCode=="3"){
				varDocValidType = 'alpha';
			}
			return varDocValidType;
		  },
		  onclick_clear:function(){
			  $('[name="patIdNo"]')[0].value="";
			  $('[name="patFirstName"]')[0].value="";
			  $('[name="patMiddleName"]')[0].value="";
			  $('[name="patLastName"]')[0].value="";
			  $('[name="patGenderCode"]')[0].value="-1";
			  $('[name="patGuardianName"]')[0].value="";
			  $('[name="patHusbandName"]')[0].value="";
			  $('[name="patMotherName"]')[0].value="";
			  $('[name="patAddCountryCode"]')[0].value="IND";
			  $('[name="patAddStateCode"]')[0].value=$('[name="defaultpatAddStateCode"]')[0].value;
			  patientSearch.onchange_patAddStateCode();			  
			  $('[name="patAddDistrictCode"]')[0].value=$('[name="defaultpatAddDistrictCode"]')[0].value;
			  $('[name="patAddState"]')[1].value="";
			  $('[name="patAddHNo"]')[0].value="";
			  $('[name="patAddStreet"]')[0].value="";
			  $('[name="patAddCityLoc"]')[0].value="";
			  $('[name="patAddDistrict"]')[1].value="";
			  $('[name="patAddCity"]')[0].value="";
			  $('[name="patAddPIN"]')[0].value="";
			  $('[name="patCardNo"]')[0].value="";
			  $('[name="patDocType"]')[0].value="-1";			  
			  //$('[name="patAddLandMarks"]')[0].value="";
			  $("#divStateTextId").hide();
			  $("#divStateComboId").show();
			  $("#divDistrictTextId").hide();
			  $("#divDistrictComboId").show();
			 // $('[name="patAddDistrictCode"]')[0].value="519";
			  $("#divPatDetilId").html("<div class='div-table-row'></div>");
			
			  /*## 		Modification Log							
			  ##		Modify Date				:18thDec'14 
			  ##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
			  ##		Modify By				:Sheeldarshi  */
			  /*$("#patFromDateId").datepicker("setDate",null);
			  $("#patToDateId").datepicker("setDate",null);*/
			 /* $('#patFromDateId').DatePicker({	format: 'd-M-Y',default_position :'above', onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} });
    		  $('#patToDateId').DatePicker({ format: 'd-M-Y',default_position :'above', onSelect: function(d,i){if(d !== i.lastVal){$(this).change();}} });*/

			//End:sheeldarshi	
		  }

};

function createPatientEmpRow(arrStrPatJsonObj)
{
	//alert("Inside createPatientEmpRow");
	//alert(arrStrPatJsonObj);
	var empHeaderRow =	"<div class='div-table-listing rounded width100'><div class='row inline-flex title' style='background: #c6c6c6; margin-top: 10px; width: 100%;'>"+
							"<div class='col-sm'>Select</div>"+
							"<div class='col-sm'>CR No.</div>"+
							"<div class='col-sm'>Name/Age</div>"+
							/*"<div class='col-sm'>Age/Gender</div>"+*/
							"<div class='col-sm'>Father Name</div>"+
							"<div class='col-sm'>Address</div>"+
							
						"</div>";
	
	var empDtlRow="";
	//var arrPatJsonObj = eval('(' + arrStrPatJsonObj + ')');
	var arrPatJsonObj = arrStrPatJsonObj;
	if(arrPatJsonObj!="")
	{
	for (i in arrPatJsonObj)
	{
		var disabled="";
		if(arrPatJsonObj[i]["patCrNo"]!="" && arrPatJsonObj[i]["patCrNo"]!="0" && arrPatJsonObj[i]["patCrNo"]!="-" && arrPatJsonObj[i]["patCrNo"]!="--" && arrPatJsonObj[i]["patCrNo"]!="---"){
			disabled="disabled='disabled'";
		}
		/* Start:  Sheeldarshi
		 * Reason: Bug 10480 - Address details displays under column father name for that patient at search window.*/
		var guardianName= arrPatJsonObj[i]["patGuardianName"];
		if(arrPatJsonObj[i]["patGuardianName"]=="")
			{
			guardianName="--";
			}
			//End
			var objPatAge = arrPatJsonObj[i]["patAgeWithUnit"].split(" ");
			var patAge	  =	objPatAge[0];
			var patAgeUnit= objPatAge[1];
			//alert("patCrNo :"+ arrPatJsonObj[i]["patCrNo"]);
			
			/*patGenderCode
			
			else if(voObj.getPatGenderCode().equalsIgnoreCase("M"))
				sb.append("<i class='fas fa-user' style='font-size: 26px; color: rgba(33, 32, 32, 0.7);'></i>");
				else if(voObj.getPatGenderCode().equalsIgnoreCase("F"))
					sb.append("<i class='fa fa-female'aria-hidden='true' style='font-size: 26px; color: rgba(33, 32, 32, 0.7);'></i>");

				else
				sb.append("<img src='/HISRegistration/hisglobal/images/patIcon.png' style=' height: 26px;vertical-align: text-bottom;padding-right: 10px;'> ");*/
		
		
			empDtlRow +=	"<div class='row listData'>"+
							"<div class='col-sm'>";
			
			if(arrPatJsonObj[i]["patGenderCode"]=="M")
				empDtlRow +=	"<i class='fas fa-user' title='Male' style='font-size: 20px; color: #6363ff; float:left'></i>";
			else if(arrPatJsonObj[i]["patGenderCode"]=="F")
				empDtlRow +=	"<i class='fa fa-female'aria-hidden='true' title='Female' style='font-size: 20px; width: 18px; color: #dd8d9b; float:left;'></i>";
			else
				empDtlRow +=	"<img src='/HISRegistration/hisglobal/images/patIcon.png' style=' height: 20px;vertical-align: text-bottom;padding-right: 10px;float:left'>";
			
			empDtlRow += "<input type='radio' name='patIndex' value='"+arrPatJsonObj[i]["patCrNo"]+"' onclick='callOpenerFetchPatDtlBasedOnPatCatCardNo(this);' />"+
			"<input type='hidden' name='patIdNo' value='"+arrPatJsonObj[i]["patCrNo"]+"' />"+
			"<input type='hidden' name='patAge' value='"+patAge+"' />"+
			"<input type='hidden' name='patAgeUnit' value='"+patAgeUnit+"' />";
			
			
			
			
			empDtlRow +=		
							"</div>";
						
			empDtlRow +=	"<div class='col-sm'>"+arrPatJsonObj[i]["patCrNo"]+"</div>"+
							"<div class='col-sm'>"+arrPatJsonObj[i]["patfirstname"]+" "+arrPatJsonObj[i]["patmiddlename"]+" "+arrPatJsonObj[i]["patlastname"]+ " ( "+arrPatJsonObj[i]["patAgeWithUnit"]+ " )"+"</div>"+
							//"<div class='col-sm'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"/ "+arrPatJsonObj[i]["patgender"]+"</div>"+
							//"<div class='col-sm'>"+arrPatJsonObj[i]["patAgeWithUnit"]+"/ "+arrPatJsonObj[i]["patgender"]+"</div>"+
							/* Start:  Sheeldarshi
							 * Reason: Bug 10480 - Address details displays under column father name for that patient at search window.
							 * "<div class='div-table-col width20  alignCenter'>"+arrPatJsonObj[i]["patGuardianName"]+"</div>"+*/
							"<div class='col-sm'>"+guardianName+"</div>"+
							"<div class='col-sm'>"+arrPatJsonObj[i]["patadddistrict"]+" "+arrPatJsonObj[i]["pataddstate"]+" "+arrPatJsonObj[i]["pataddcountry"]+"</div>"+
						"</div>";
	}	
			empDtlRow +="</div>";
			$("#divPatDetilId").html(empHeaderRow+empDtlRow);
	}
	else{
		empDtlRow +=	"<div class='listData' style='width: 90%;'><class='col-sm-12'><img id='imgNoDataId' src='../../hisglobal/images/NoData.png' /></div></div>";
		$("#divPatDetilId").html(empDtlRow);
	}
	
	
	
	
}

function callOpenerFetchPatDtlBasedOnPatCatCardNo(obj)
{
	//alert(obj.value);
	//swal("inside////");
	//swal(window.parent.$('[name="patCrNo"]')[0].value);
	window.parent.$('[name="patCrNo"]')[0].value=obj.value;
	window.parent.submitForm('GETPATDTL');
	//window.close();
	//$("#catChangeLogPopupModalID").find(".close").trigger("click");
}

function getFieldLabelBasedOnUniqueIdSearchBy(obj)
{
	//alert(obj.value);
	console.log("getFieldLabelBasedOnUniqueIdSearchBy: "+obj.value);
	var selectedVal=obj.value;
	$('[name="patIdNo"]')[0].value="";
	$('[name="patCardNo"]')[0].value="";
	if(selectedVal=="1")
		{
		labelName="CR No";
		$('[name="hiddenPatUniqueIdType"]')[0].value="1";
		docValidType= patientSearch.getDocValidtype($('[name="hiddenPatUniqueIdType"]')[0].value);							
		var docValidTypeNLength='equalLength['+13+']';
		$("#patDocTypeId").removeClass('validatebox-invalid');
		$('[name="patCardNo"]').validatebox({required: false});
		
		$("#divAlternateSearchDtlId").hide();
		$("#divCatCardId").show();
		/*$("#goPatDtl").show();*/
		$('[name="patIdNo"]').validatebox({required: true, validType: [docValidType,docValidTypeNLength]});
		$('input[name="patIdNo"]').attr('maxlength', docValidTypeNLength);
		$("#demographicSearchTile").hide();
		$("#searchId").show();		
		$("#submitId").hide();	
		}
	else if(selectedVal=="2"){
		labelName="Aadhar No";
		/*$("#patDocTypeId").removeClass('validatebox-invalid');
		$('[name="patCardNo"]').validatebox({required: false});*/
		$('[name="hiddenPatUniqueIdType"]')[0].value="2";
		
		$("#divAlternateSearchDtlId").hide();
		$("#divCardNoId").hide();
		$("#divCatCardId").show();
		/*$("#goPatDtl").show();*/
		$('[name="patIdNo"]').validatebox({required: true, validType: ['alphaNumeric','equalLength[12]']});
		$('input[name="patIdNo"]').attr('maxlength', 12);
		$("#demographicSearchTile").hide();	
		$("#searchId").show();		
		$("#submitId").hide();	
	}
	else if(selectedVal=="3"){
		labelName="Aadhar No";
		//document.getElementById("divCardNoId").style.display="";
		$('[name="patDocType"]')[0].value="-1";
		$('[name="hiddenPatUniqueIdType"]')[0].value="3";

		$('[name="patIdNo"]').validatebox({required: false});
		$("#divCatCardId").hide();
		/*$("#goPatDtl").show();*/
		$("#divAlternateSearchDtlId").show();
		$("#patDocTypeId").validatebox({validType:  ['selectCombo[-1]']});
		$("#demographicSearchTile").hide();	
		$("#searchId").show();		
		$("#submitId").hide();	
	}
	else if(selectedVal=="4"){
		
		
		labelName="Employee No";
		/*$("#patDocTypeId").removeClass('validatebox-invalid');
		$('[name="patCardNo"]').validatebox({required: false});*/
		$('[name="hiddenPatUniqueIdType"]')[0].value="4";

		$("#divCardNoId").hide();
		$("#divAlternateSearchDtlId").hide();
		$("#divCatCardId").show();
		/*$("#goPatDtl").show();*/
		$('[name="patIdNo"]').validatebox({required: true, validType: ['alphaNumeric','maxLength[18]']});
		$('input[name="patIdNo"]').attr('maxlength', 18);
		$("#demographicSearchTile").hide();	
		$("#searchId").show();		
		$("#submitId").hide();	

	}
	else if(selectedVal=="5"){
		
		
		labelName="Mobile No";
		/*$("#patDocTypeId").removeClass('validatebox-invalid');
		$('[name="patCardNo"]').validatebox({required: false});*/
		$('[name="hiddenPatUniqueIdType"]')[0].value="5";
		
		$("#divAlternateSearchDtlId").hide();
		$("#divCardNoId").hide();
		$("#divCatCardId").show();
		/*$("#goPatDtl").show();*/
		//$('[name="patIdNo"]').validatebox({required: true, validType: 'mobile'});
		$('[name="patIdNo"]').validatebox({required : true,validType : [ 'numeric', 'equalLength[10]']});
		$('input[name="patIdNo"]').attr('maxlength', 10);
		$("#demographicSearchTile").hide();	
		$("#searchId").show();		
		$("#submitId").hide();	

	}
	else if(selectedVal=="6"){
		
		
		
		labelName="Email ID";
		/*$("#patDocTypeId").removeClass('validatebox-invalid');
		$('[name="patCardNo"]').validatebox({required: false});*/
		$('[name="hiddenPatUniqueIdType"]')[0].value="6";
		
		$("#divAlternateSearchDtlId").hide();
		$("#divCardNoId").hide();
		$("#divCatCardId").show();
		/*$("#goPatDtl").show();*/
		$('[name="patIdNo"]').validatebox({required: true, validType: 'email'});
		$('input[name="patIdNo"]').attr('maxlength', 100);
		$("#demographicSearchTile").hide();
		$("#searchId").show();		
		$("#submitId").hide();	
	}
	else if(selectedVal=="7"){
		//for demographics details
		console.log("for value 7");
		labelName="Demo Dtls" 
		$("#uniqueIdSearchTileControl").hide();
		$("#demographicSearchTile").show();	
		$("#divCardNoId").hide();
		$("#divCatCardId").hide();
		$("#divAlternateSearchDtlId").hide();
		/*$("#goPatDtl").hide();*/
		patientSearch.validateDemographicTile();
		$("#searchId").hide();		
		$("#submitId").show();	
	}
	
	
	return labelName;
}

function showSerachBox(Obj)
{
$("#divCatCardNoLabelId").html("<label>"+getFieldLabelBasedOnUniqueIdSearchBy(Obj)+"</label>&nbsp;<font class='fontred'>*</font>");	
}

$("#patDocTypeId").change(function(){
	patientSearch.onchange_patDocType();
});


//Submit form if valid
$('#searchId').click(function(e){
	
	//setMotherValidRule();
	
	
	if($('[name="uniqueIdType"]')[1].checked==false){
		//$("#patDocTypeId").validatebox({validType:  'selectComboNotRequired'});
		$("#patDocTypeId").validatebox({validType:  null});
		$('[name="patCardNo"]').validatebox({required: false});
	}
	//alert("Validate form"+$("#uniqueIdSearchTile").form('validate'));
	if($("#uniqueIdSearchTile").form('validate')){
		//alert("div validated");
		//$('#submitId').hide();
		patientSearch.searchPatientDtl();
		//$('#submitId').show();
	}else{
		//alert("Validation false");
		return false;
	}
});
	
/*## 		Modification Log							
  ##		Modify Date				:18thDec'14 
  ##		Reason	(CR/PRS)		:Add From Date and To Date logic in patient search
  ##		Modify By				:Sheeldarshi  */
$('#isUnknownId').click(function(){
    if (this.checked) {
    	document.getElementsByName("isUnknown")[0].value="1";
    	$("#divFirstRow :input").attr("disabled", true);
    	$("#divSecondRow :input").attr("disabled", true);
    	$("#divThirdRow :input").attr("disabled", true);
    	$("#divFourthRow :input").attr("disabled", true);
    	$("#divFifthRow :input").attr("disabled", true);
    				  }
    else{
    	document.getElementsByName("isUnknown")[0].value="0";
	  	$("#divFirstRow :input").attr("disabled", false);
	  	$("#divSecondRow :input").attr("disabled", false);
	  	$("#divThirdRow :input").attr("disabled", false);
	  	$("#divFourthRow :input").attr("disabled", false);
	  	$("#divFifthRow :input").attr("disabled", false);
    	}
    });
//End:sheeldarshi
$('#submitId').click(function(e){
	
	//setMotherValidRule();
	
	//if($('#PatientSearch').form('validate')){
	if($('#demographicSearchTile').form('validate')){
		//$('#submitId').hide();
		patientSearch.submitSearchPatientDtl();
		//$('#submitId').show();
	}else{
		//alert("Validation false");
		return false;
	}
	//e.preventDefault();
	
	
});

$('#clearId').click(function(e){	
	patientSearch.onclick_clear();
	
});
