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
     	   url: "/HISPis/pis/common/transactions/findRegEmpWithRmEmployeeInfo.action?condition=30111",
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

function fieldValidate()
{
	
	//Textbox Fields
	applyEmpNoCheck("strEmpNo", empNoLen, empNoType, '');
	//$('[name="strEmpNo"]').validatebox({required: true, noSpace: true, validType: ['numeric','minLength[12]']});
//	$('[name="strEmpName"]').validatebox({required: true, noSpace: true, validType: ['alphaNumericWithSpaces','minLength[1]']});
	
	
}


$('#cancelId').click(function(e){	
	Cancel(e);
});

$('#clearId').click(function(e){
	Clear(e);
}); 

$('#reportId').click(function(e){
	
	Print(e);		
});




$('#configId').click(function(e){
	
	//$('#divConfigId').slideToggle('fast');
	var options = {};
	var selectedEffect = "fade";
	$( "#divConfigId" ).toggle( selectedEffect, options, 800);
	
});






function Print(event)
{ 
	removeAllValidations();
	applyEmpNoCheck("strEmpNo", empNoLen, empNoType, 'globalChkEmpName[\'strEmpName\',\'Press Go Button to Populate Employee Details.\']');
	
	if($('#RegisteredEmpDtlRpt').form('validate')){
		$("#RegisteredEmpDtlRpt").attr('action',"/HISPis/pis/pr/reports/printRegisteredEmpDtlRpt.action");
		$('#RegisteredEmpDtlRpt').submit();
	}
}
 
function Clear(event)
{
	var url = "/HISPis/pis/pr/reports/RegisteredEmpDtlRpt.action?rptMode="+$("#intRptMode").val();
	$("#RegisteredEmpDtlRpt").attr('action',url);
	$('#RegisteredEmpDtlRpt').submit();	
	
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
	
	
	if($('#RegisteredEmpDtlRpt').form('validate')){
		afterClickEmpGo();
	}	
}

function removeAllValidations()
{
	$('[name="strEmpNo"]').validatebox({required: false, validType:[]});
}

function afterClickEmpGo()
{	
	var whereClause="30111";
	
    	var emp_no = document.getElementsByName("strEmpNo")[0].value; 	   
    	var action 	= "/HISPis/pis/common/transactions/goRegEmpDataWithRmEmployeeInfo.action?&empNo="+emp_no+"&calMode=1&condition="+whereClause;
   			
		$.ajax({url: action,type:"POST",async:false, dataType:"json",data:$("#RegisteredEmpDtlRpt").serialize(), success:function(data)
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
				}	*/	
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

function searchEmpNo()
{
	clearEssentialDivs();
	
	var whereClause="30111";
	
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
		
	var action 	= "/HISPis/pis/common/transactions/regEmpListWithRmEmployeeInfo.action?condition="+whereClause;
				
	$.modal('<iframe src="' + action + '" style="border:0;z-index: 10000;" width="'+"100%"+'" height="'+"100%"+'">');
	
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

function setEmpInfo1(emp_no,emp_name,emp_desig_id,emp_desig,emp_dept_id,emp_dept)
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


$(window).bind('resize', function() {
	 $("#list1").setGridWidth($(window).width()*0.99); 
	// $("#list2").setGridWidth($(window).width()*0.99); 
	// $("#list3").setGridWidth($(window).width()*0.99);
}).trigger('resize');