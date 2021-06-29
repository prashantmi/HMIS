<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>

<script type='text/javascript' src='/HIS/hisglobal/js/commonFunctions.js'></script>


<style type="text/css">
.border .div-table-col{
border: 1px solid black;
}
#fade {
    display: none;
    position:absolute;
    top: 0%;
    left: 0%;
    width: 100%;
    height: 100%;
    background-color: #ababab;
    z-index: 1001;
    -moz-opacity: 0.8;
    opacity: .70;
    filter: alpha(opacity=80);
}

#modal {
    display: none;
    position: absolute;
    top: 45%;
    left: 45%;
    width: 64px;
    height: 64px;
    padding:30px 15px 0px;
    border: 3px solid #ababab;
    box-shadow:1px 1px 10px #ababab;
    border-radius:20px;
    background-color: white;
    z-index: 1002;
    text-align:center;
    overflow: auto;
}
</style>

<title>Patient Registration</title>
<script type='text/javascript'>


var arrPatGlobalJsonObj=[];
var alreadyRegisteredFlag1 = "0";
function initializePopUp(){
	$('[name="aadhaarId"]')[0].value=$('[name="aadhaarId"]')[1].value;
	if($('[name="aadhaarId"]')[0].value!=null&&$('[name="aadhaarId"]')[0].value!="")
		go();
	
}
function go()
{	
		var aadhaarId = $('[name="aadhaarId"]')[0].value;
		//alert(adhaarId);
		
		var action 	= "/HISRegistration/registration/transactions/getPatDtlOnAadhaarIdAadhaarFeed.action?"+
					"aadhaarId="+aadhaarId;

		if($('[name="aadhaarId"]')[0].value!="" & $('[name="aadhaarId"]')[0].value.length==12){
			$.ajax({
				url: action,
				type:"POST",async:true,
				dataType:"json" ,
				beforeSend: function() {
					$('#modal').show();$('#fade').show();
		        }, 
		        complete: function() {
		       	 	$('#modal').hide();$('#fade').hide();  
		       	},
				success:function(data){
				arrPatGlobalJsonObj=data;
				if($('[name="aadhaarId"]')[0].value!="")
					createAadhaarDtlRow(data);	
				else
					alert("Please Enter Adhaar Id..!");
					
			},error: function(errorMsg,textstatus,errorthrown) {
		        alert('go '+errorMsg+" textstatus::"+textstatus+" errorthrown::"+errorthrown);
		        
		    }
			});
		}
		else{
			if($('[name="aadhaarId"]')[0].value=="")
				alert("Please Enter Adhaar Id..!");		
			else if($('[name="aadhaarId"]')[0].value.length!=12)
				alert("Adhaar Id must be 12 digits..!");
		}
		
}

 $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
 
 
function cancelPopup(){

	var aadhaarRegisteredFlag = $('[name="aadhaarRegisteredFlag"]')[0].value;
	if($('#aadhaarRegisteredFlagId').is(':checkbox'))
		parent.document.getElementsByName("aadhaarRegisteredFlag")[0].checked=false;
	parent.closeModal();
	
}

function callOpenerFetchPatDtlBasedOnAadhaarId(patIndexId){

	var process=document.getElementsByName("fromProcess")[0].value;
	if(process=='opdregistration')	
	window.parent.opdregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
	if(process=='emgregistration')
	window.parent.emgregistration.fetchPatDtlBasedOnPatCatCardNo(arrPatGlobalJsonObj[patIndexId.value]);
		
	//parent.document.getElementsByName("aadhaarRegisteredFlag")[0].checked=false;
	parent.closeModal();
}


function createAadhaarDtlRow(arrStrPatJsonObj)
{
	var empHeaderRow =	"<div class='div-table-row title' style='background:'blue''>"+
							"<div class='div-table-col width5 alignCenter'>Select</div>"+
							"<div class='div-table-col width25 alignCenter'>Name</div>"+
							"<div class='div-table-col width10 alignCenter'>Age</div>"+							
							"<div class='div-table-col width20 alignCenter'>Gender</div>"+							
							"<div class='div-table-col width40 alignCenter'>Address</div>"+							
							
						"</div>";
	var empDtlRow="";
	var arrPatJsonObj = arrStrPatJsonObj;
	var varId="";
	//alert(arrPatJsonObj);
	if(arrPatJsonObj!="" && arrPatJsonObj!=101 && arrPatJsonObj!=111 && arrPatJsonObj!=121)
	{
		for (i in arrPatJsonObj)
		{
			var disabled="";
			empDtlRow +=	"<b><div class='div-table-row listData'>"+
								"<div class='div-table-col width5 alignCenter'>"+
									"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnAadhaarId(this);'"+disabled+" />"+
								"</div>"+
								"<div class='div-table-col width25 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+"</div>"+
								"<div class='div-table-col width10 alignCenter'>"+arrPatJsonObj[i]["patAge"]+" "+arrPatJsonObj[i]["patAgeUnit"]+"</div>"+
								"<div class='div-table-col width20 alignCenter' >"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
								"<div class='div-table-col width40 alignCenter' >"+arrPatJsonObj[i]["patAddHNo"]+" , "+arrPatJsonObj[i]["patAddStreet"]+" , "+arrPatJsonObj[i]["patAddCity"]+"</div>"+							
							"</b></div>";
			
		}
	}
	else{
		if(arrPatJsonObj==101)
			empDtlRow +=	"<div class='div-table-row listData'><b>No Data Available for given UID or EID<b></div>";
		else if(arrPatJsonObj==111)
			empDtlRow +=	"<div class='div-table-row listData'><b>Unknown Error<b></div>";
		else if(arrPatJsonObj==121)
			empDtlRow +=	"<div class='div-table-row listData'><b>Cancelled or Rejected EID<b></div>";
		else
			empDtlRow +=	"<div class='div-table-row listData'><b>No Record Found<b></div>";
				
	}
	$("#divCatDetilId").html(empHeaderRow+empDtlRow);
	
}

function numbersonly(e){
    var unicode=e.charCode? e.charCode : e.keyCode
    if (unicode!=8){ //if the key isn't the backspace key (which we should allow)
        if (unicode<48||unicode>57)
        	{
        	alert ("Enter only numbers");
        	return false //if not a number
        	}
            //disable key press
    }
}

</script>

</head>

<body onload="initializePopUp();">
<center>

<s:form action="NewRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
						Aadhaar Search
				</div>
			</div>
			<div class="div-table-row separatorBar ">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar ">
					<div class="div-table-col"> </div>
			</div>
			
			<div class="div-table-row ">
				<div class="div-table-col label width10" >
				</div>
				<div class="div-table-col label width20" >
						Aadhaar Id
				</div>
				<div class="div-table-col Control width40" >
						<input type="text" name="aadhaarId"  tabindex="1" maxlength="12" size="30" onkeydown="return validateNumeric(event)" onkeypress="return numbersonly(event)">
				</div>
				<div class="div-table-col Control width30" >
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				</div>
			
			</div>
					
	
			<div id="divCatDetilId" class="div-table-listing rounded"></div>
		</div>
		<div class="div-table-button">
			<div class="div-table-row footerBar">
					<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row emptyBar">
				<div class="div-table-col"> </div>
			</div>
			<div class="div-table-row" align="center">
				<a href="#" class="button" id="cancelPopupId" onclick="cancelPopup();"><span class="cancel">Cancel</span></a>
			</div>
			
		</div>
	</div>
	<div id="fade"></div>
	<div id="modal">
     <img id="loader" src="/HIS/hisglobal/images/loading.gif" />
	</div>
	<s:hidden name="patPrimaryCatCode" value="%{patPrimaryCatCode}" />
	<s:hidden name="aadhaarRegisteredFlag" value="%{aadhaarRegisteredFlag}" />
	<s:hidden name="fromProcess" value="%{fromProcess}"/>
	<s:hidden name="aadhaarId" value="%{aadhaarId}"/>
	
</s:form>
<script type="text/javascript" src="./../../com/ecentric/js/aadhaarSearch.js" /></script>
</center>
</body>
</html>