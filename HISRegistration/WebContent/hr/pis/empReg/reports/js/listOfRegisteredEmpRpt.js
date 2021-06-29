
$("#strEmpNo").autocomplete({
	 width: 1000,
	 delay:500,
	 minLength: 2,
	 autoFocus: true,
   scroll: true,
   highlight: false,  
	 source:  //availableTags
     function(request, response) {
        $.ajax({
            //url: "/HISPis/pis/pr/transactions/getEmpListEmpDtl1.action",
     	   url: "/HISPis/pis/common/transactions/findValidEmpEmployeeInfo.action?condition=30110",
            dataType: "json",
            //data: request,
            data: {searchStr: request.term},
            success: 
            function( data, textStatus, jqXHR) {
                //console.log( data);
               var items = data;
               response(items);	                   
            } ,
            error: function(jqXHR, textStatus, errorThrown){
                 //console.log(textStatus);
            }
        });
	 },
	 open: function(){
	        setTimeout(function () {
	            $('.ui-autocomplete').css('z-index', 99999999999999);
	        }, 0);
	    },
	 select: function( event, ui ) {
		 //ui.item.label="";
		 ui.item.value=(ui.item.value).substring(0,12);		 
	 },
	 focus: function( event, ui ) {
		 //ui.item.label="";
		 ui.item.value=(ui.item.value).substring(0,12);		 
	 },
	 messages: {
	        noResults: '',
	        results: function() {}
	    }
});
$.datepicker.setDefaults( 
	    {	showOn: 'both',
	    	defaultDate: new Date(),
	    	buttonImageOnly: true, 
	    	buttonText : "Select Date",
	    	buttonImage: "/HIS/hisglobal/images/nonclinical/nc_calendar_icon.gif"
	    	});


var _func={
		
		fetchDefaultValues:function ()
		{
			//For Multilingual Initialization
			//initMultilingual();
						
			$("#dtPeriodFrom").datepicker({changeMonth: true,	changeYear: true, dateFormat: 'dd-M-yy' });
			$("#dtPeriodTo").datepicker({changeMonth: true,	changeYear: true, dateFormat: 'dd-M-yy' });
			
			$('[name="intNatureOfJob"]').validatebox({validType: ['selectCombo[-1]'] });
			
			var action 	= "/HISRegistration/registration/reports/populateFormValuesListOfRegisteredEmpRpt.action";
			
			
			//For Submission
			$.ajax({url: action,type:"POST",async:false,dataType:"xml" ,success:function(data)
				{
						var returnDocument=data;
						if(returnDocument != null) {
							var rootNode=returnDocument.getElementsByTagName("root")[0];
							for(var i=0;i<rootNode.childNodes.length;i++ )
							{
								var elementNode=rootNode.childNodes[i];
								var elementName=elementNode.nodeName;
								if(elementName=='')
								{
									//_func.processGeneralNode(elementName,elementNode);
								}
								else if(elementName=='defaults')
								{
									
								}
								else if(elementName=='filters')
								{
									_func.processFilterNode(elementName,elementNode);
								}
								else
								{
									_func.processGeneralNode(elementName,elementNode);
								}
							}
							
							_func.setdefaultVariables(rootNode.getElementsByTagName("defaults")[0]);
						}
				},error: function(errorMsg,textstatus,errorthrown) {
		            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		            
		        }
			});
			
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
			
			if(document.getElementsByName(elementName).length!=0)
					document.getElementsByName(elementName)[0].innerHTML=optionText;
			
		  },
		  processFilterNode:function(elementName,elementNode)
		  {
			  for(var i=0;i<elementNode.attributes.length;i++ )
				{
					eval("var "+elementNode.attributes[i].name+"='"+elementNode.attributes[i].value+"'");
//					if(elementNode.attributes[i].name=="filterIncWhReason") {
//						globalIncTypeFilter=eval(elementNode.attributes[i].name);
//					}
					
				}
			
		  },
		  setdefaultVariables:function(elementNode)
		  {
			for(var i=0;i<elementNode.attributes.length;i++ )
			{
				eval("var "+elementNode.attributes[i].name+"='"+elementNode.attributes[i].value+"'");
				//document.getElementsByName(elementNode.attributes[i].name.substring(7))[0].value=eval(elementNode.attributes[i].name);
				/*if(elementNode.attributes[i].name=="defaultIntSalaryTypeId"){
					document.getElementsByName("intSalaryTypeId")[0].value=eval(elementNode.attributes[i].name);
				}*/
				
			}
			
			
		  },
		  
		 
		  
};
		  
		  
	  


$('#configId').click(function(e){
	
	//$('#divConfigId').slideToggle('fast');
	var options = {};
	var selectedEffect = "fade";
	$( "#divConfigId" ).toggle( selectedEffect, options, 800);
	
});





$('#cancelId').click(function(e){	
	Cancel(e);
});

$('#clearId').click(function(e){
	Clear(e);
});

function Clear(event)
{
	//alert("Clear");
	$("#ListOfRegisteredEmpRpt").attr('action',"/HISRegistration/registration/reports/ListOfRegisteredEmpRpt.action");
	$('#ListOfRegisteredEmpRpt').submit();	
}
 
function Cancel(event)
{
	goToDefaultPage('1');
}

$('#goButtonId').click(function(e){
	
	funcForClickingGo();
	
});

function funcForClickingGo()
{
	clearEssentialDivs();
	removeAllValidations();
	applyEmpNoCheck("strEmpNo", empNoLen, empNoType, '');
	
	$('[name="intNatureOfJob"]').validatebox({required: false, validType:[]});
	
	if($('#ListOfRegisteredEmpRpt').form('validate')){
		afterClickEmpGo();
	}	
}

function removeAllValidations()
{
	$('[name="strEmpNo"]').validatebox({required: false, validType:[]});
	$('[name="intNatureOfJob"]').validatebox({validType: [] });
	$('[name="dtPeriodFrom"]').validatebox({required: false});
	$('[name="dtPeriodTo"]').validatebox({required: false, validType :[]});
	 
}


function afterClickEmpGo()
{	
	var whereClause="30110";
	
    	var emp_no = document.getElementsByName("strEmpNo")[0].value; 	   
    	var action 	= "/HISPis/pis/common/transactions/goRegEmpDataWithRmEmployeeInfo.action?&empNo="+emp_no+"&calMode=1&condition="+whereClause;
   			
		$.ajax({url: action,type:"POST",async:false, dataType:"json",data:$("#RegistrationRpt").serialize(), success:function(data)
			{
				/*var arrListJsonObj=data['Data'];	
				if(arrListJsonObj == null || arrListJsonObj == "" || arrListJsonObj[0] == null || arrListJsonObj[0] == "")
				{
					//alert("No Data");
					var msg="";
					if($("#intReqMode").val()=="1"){$('#EnterEmpNoDivId').hide();msg="This User is not an Employee.";}
					else{msg="This Emp. No. does not Exist.";}
					$("#divErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' />&nbsp;"+msg); 
					return false;
				}
				else
				{						
					document.getElementsByName("strEmpNo")[0].value=arrListJsonObj[0]['empNo'];
					document.getElementsByName("strEmpName")[0].value=arrListJsonObj[0]['empFullName'];	
				}				
				*/
			
			var rptMsgFlag=data['RptMsgFlag'];
   			var arrListJsonObj=data['Data'];
   			
   			if(rptMsgFlag=='1')
   			{
   				if(arrListJsonObj == null || arrListJsonObj == "" || arrListJsonObj[0] == null || arrListJsonObj[0] == "")
   				{
   					var msg="No Record Found - Error.";
   	   				$("#divErrorMsgId").html("&nbsp;<img src='/HIS/hisglobal/images/nonclinical/nc_warning.png' width='25' height='25' style='vertical-align:sub;' title='Warning' />&nbsp;"+msg); 
   	   				return false;
   				}
   				else
   				{
	   				document.getElementsByName("strEmpNo")[0].value=arrListJsonObj[0]['empNo'];
	   				document.getElementsByName("strEmpName")[0].value=arrListJsonObj[0]['empFullName'];
	   				//_func.onchange_EmpNo();
					showHtmlFields();
   				}	
   			}
   			else
   			{
   				globalMsgFuctionForEmpNoGo(rptMsgFlag);
   			}
			
			},error: function(errorMsg,textstatus,errorthrown) {
	            alert('fetchDefaultValues '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
	            
	        }
		});
	 
}


function onChngRptGenType()
{
	if(document.getElementsByName('strRptGenType')[0].checked==true)
	{
		removeAllValidations();
		$('[name="intNatureOfJob"]').validatebox({validType: ['selectCombo[-1]'] });
		
		$("#strEmpNo")[0].value = "";
		$("#strEmpName")[0].value = "";
		$("#empNoVal").html("");
		$("#empNameVal").html("");
		$("#showEmpDetailsDiv").hide();
		$("#enterEmpNoDiv").hide();
		
		$("#divEmpWise").hide();
		$("#divGeneral").show();
		
	}
	else if(document.getElementsByName('strRptGenType')[1].checked==true)
	{
	
		removeAllValidations();
		applyEmpNoCheck("strEmpNo", empNoLen, empNoType, '');
	
		$("#intEmpDesig")[0].value = "-1";
		$("#intEmpDept")[0].value = "-1";
		$("#intGender")[0].value = "-1";
		$("#intFinalStatus")[0].value = "-1";
		$("#intNatureOfJob")[0].value = "-1";
		
		
		
		$("#strEmpDesig")[0].value = "";
		$("#strEmpDept")[0].value = "";
		$("#strGender")[0].value = "";
		$("#strFinalStatus")[0].value = "";
		$("#strNatureOfJob")[0].value = "";
		
		$("#showEmpDetailsDiv").hide();
		$("#enterEmpNoDiv").show();
		$("#divEmpWise").show();
		$("#divGeneral").hide(); 
	}
	else
	{		
		//alert($("#strRptGenType").value);
		
		$("#strEmpNo")[0].value = "";
		$("#strEmpName")[0].value = "";
		
		$("#intEmpDesig")[0].value = "-1";
		$("#intEmpDept")[0].value = "-1";
		$("#intGender")[0].value = "-1";
		$("#intFinalStaus")[0].value = "-1";
		$("#intNatureOfJob")[0].value = "-1";
		
		
		$("#strEmpDesig")[0].value = "";
		$("#strEmpDept")[0].value = "";
		$("#strGender")[0].value = "";
		$("#strFinalStatus")[0].value = "";
		$("#strNatureOfJob")[0].value = "";
		
		$("#divEmpWise").hide();
		$("#divGeneral").hide(); 
		
	}
}

$('#strIsPeriodReq').click(function(e){	
	
	 if($('#strIsPeriodReq').prop('checked')==true){
		 $( "#divPeriod" ).show();
		 
		 $('[name="dtPeriodFrom"]')[0].value="";
		 $('[name="dtPeriodTo"]')[0].value="";
		
	 }
	 else
	 {
		 $('[name="dtPeriodFrom"]')[0].value="";
		 $('[name="dtPeriodTo"]')[0].value="";
		
		 $( "#divPeriod" ).hide();
	 }
	
});

$('#reportId').click(function(e){
	
	Print(e);		
});

function Print(event)
{ 
	
	var cont="Y";
		
		if(document.getElementsByName('strRptGenType')[0].checked==true)
		{
			
			
					
		}
		else if(document.getElementsByName('strRptGenType')[1].checked==true)
		{
			removeAllValidations();
			applyEmpNoCheck("strEmpNo", empNoLen, empNoType, 'globalChkEmpName[\'strEmpName\',\'Press Go Button to Populate Employee Details.\']');	
		}
		/*else
		{
			//alert("Please Select either option for Report Generation Type.");
			showAlert ("1", "Please Select One Report Generation Type Option");
			cont="N";		
		}*/
		
		
	
		document.getElementById("strEmpDesig").value= $("#intEmpDesig option:selected").text();
		document.getElementById("strEmpDept").value= $("#intEmpDept option:selected").text();
		document.getElementById("strGender").value= $("#intGender option:selected").text();
		document.getElementById("strFinalStatus").value= $("#intFinalStatus option:selected").text();
		document.getElementById("strNatureOfJob").value= $("#intNatureOfJob option:selected").text();
		
		 if($('#strIsPeriodReq').prop('checked')==true)
		 {
			 $('[name="dtPeriodFrom"]').validatebox({required: true, validType: ['date[\'dtPeriodTo\',\'dd-mmm-yyyy\']']});
			 $("#dtPeriodTo").validatebox({required: true, validType: ['date[\'dtPeriodTo\',\'dd-mmm-yyyy\']', 'dtgtet[\'dtPeriodFrom\',\'To period should be greater than equal to From period\']']});
		 }
		 
		if(cont!="N")
	     {
			
			if($('#ListOfRegisteredEmpRpt').form('validate'))
			{
				$("#ListOfRegisteredEmpRpt").attr('action',"/HISRegistration/registration/reports/printListOfRegisteredEmpRpt.action");
				$('#ListOfRegisteredEmpRpt').submit();
			}
	     }
	
}



function searchEmpNo()
{
	clearEssentialDivs();
	
	var whereClause="30110";
	//var whereClause="24";
	var windowWidth=window.innerWidth-100;
	var windowHeight=window.innerHeight-100;
	var iframeWidth= windowWidth-20;
	var iframeHeight=windowHeight-20;
	var blankHeight, blankWidth;
	if(!windowWidth)
		windowWidth =( $(window).width() * 0.8);
	if(!windowHeight)
		windowHeight = ($(window).height() * 0.8);
	

	//blankWidth = ($(window).width()-windowWidth);
	//blankHeight =($(window).height() - windowHeight)  ;
	blankWidth = (window.innerWidth-windowWidth);
	blankHeight =(window.innerHeight - windowHeight)  ;
	var windowLeft = Math.round((blankWidth / 2)-18)+ "px";
	var windowTop = Math.round((blankHeight / 2)-18 ) + "px";
	windowHeight = windowHeight + "px";
	windowWidth = windowWidth + "px";
		
	var action 	= "/HISPis/pis/common/transactions/validEmpListWithRmEmployeeInfo.action?condition="+whereClause;
//	var action 	= "/HISPis/pis/common/transactions/regEmpListWithRmEmployeeInfo.action";
				
	$.modal('<iframe src="' + action + '" style="border:0;z-index: 1000;" width="'+"100%"+'" height="'+"100%"+'">');

	var handler="";
	handler = function() {
		clearEssentialDivs();		
		//reloadList();
	};
	$( ".modalCloseImg" ).bind( "click", handler);
	//$('.modalCloseImg').hide();
	//$('#simplemodal-container').width(windowWidth).height(windowHeight);
	$('#simplemodal-container').width("90%").height("87%");
	$('#simplemodal-data').width("100%").height("100%");
	//$('#simplemodal-container').css({'left':windowLeft,'top':windowTop});
	$('#simplemodal-container').css({'left':"3%",'top':"3%"});		
	$('#simplemodal-container').css({'z-index':10000});
	//$("#simplemodal-container").resizable();
    //$("#simplemodal-container").draggable();
}


function setEmpInfo1(emp_no,emp_name,emp_desig,emp_dept)
{
	document.getElementById('strEmpNo').value = emp_no;
	document.getElementById('strEmpName').value = emp_name;
	showHtmlFields();
	closeModal();
}

function showHtmlFields()
{
	//alert("1");
	$("#empNoVal").html(document.getElementById('strEmpNo').value);
	$("#empNameVal").html(document.getElementById('strEmpName').value);
	
	$("#enterEmpNoDiv").hide();
	$("#showEmpDetailsDiv").show();
}
function clearEssentialDivs()
{
	$("#divNormalMsgId").html("");
	$("#divErrorMsgId").html("");
	$("#divPrintId2").html("");
		
}


$(window).bind('resize', function() {
	 $("#list1").setGridWidth($(window).width()*0.99); 	
}).trigger('resize');
