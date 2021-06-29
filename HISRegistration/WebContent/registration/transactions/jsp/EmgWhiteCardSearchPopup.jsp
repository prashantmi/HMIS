<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@page %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/layout.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" href="../../hisglobal/css/basic.css">

<script language="JavaScript" type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery-2.0.3.min.js"></script>
<script type="text/javascript" src="../../hisglobal/masterutil/js/jquery/jquery.easyui.js"></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/jquery.simplemodal.js'></script>
<script type='text/javascript' src='../../hisglobal/masterutil/js/jquery/js/basic.js'></script>

<script type='text/javascript' src='/HIS/hisglobal/js/commonFunctions.js'></script>
<script type='text/javascript' src='/HISRegistration/registration/transactions/js/regValidation.js'></script>


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

function go(){
	
	var cardId = $('[name="CardId"]')[0].value;
	//var seqNo = $('[name="seqNo"]')[0].value;
	var selIndex=document.getElementById('cardTypeId').selectedIndex;
	var cardType = document.getElementById('cardTypeId').options[selIndex].text;
	var action 	= "/HISRegistration/registration/transactions/getPatDtlOnWhiteCardIdWhiteCardService.action?"+
				"wapId="+cardId+"&cardType="+cardType;

	//alert("action"+action);
	if($('[name="CardId"]')[0].value!=""  && $('[name="cardType"]')[0].value != "-1"){
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
				//alert(data);
			arrPatGlobalJsonObj=data;
			createAadhaarDtlRow(data);	
							
		},error: function(errorMsg,textstatus,errorthrown) {
	        alert('Sorry ! Not Able to Communicate with White Card Service !!');
	        
	    }
		});
	}
	else{
		if($('[name="CardId"]')[0].value=="")
			alert("Please Enter Card Id..!");
		//else if($('[name="seqNo"]')[0].value == "-1")
			//alert("Please enter seq no..!");
		else if($('[name="cardType"]')[0].value == "-1")
			alert("Please enter card type..!");
	}
	
	
}
 $('.simplemodal-close').click(function(){
	//alert("hello");
}); 
 
 
function cancelPopup(){

	parent.closeModal();
}

function callOpenerFetchPatDtlBasedOnAadhaarId(patIndexId){
	
	window.parent.emgregistration.fetchPatDtlBasedOnPatCatWhiteCardNo(arrPatGlobalJsonObj[patIndexId.value]);
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
		{//alert(arrPatJsonObj[i]["name"]);
			var disabled="";
			empDtlRow +=	"<b><div class='div-table-row listData'>"+
								"<div class='div-table-col width5 alignCenter'>"+
									"<input type='radio' name='patIndex' value='"+i+"' onclick='callOpenerFetchPatDtlBasedOnAadhaarId(this);'"+disabled+" />"+
								"</div>"+
								"<div class='div-table-col width25 alignCenter'>"+arrPatJsonObj[i]["patFirstName"]+" "+arrPatJsonObj[i]["patMiddleName"]+" "+arrPatJsonObj[i]["patLastName"]+"</div>"+
								"<div class='div-table-col width10 alignCenter'>";
								
								if(arrPatJsonObj[i]["patAge"]!=null && arrPatJsonObj[i]["patAge"]!="")
									empDtlRow += arrPatJsonObj[i]["patAge"]+" "+arrPatJsonObj[i]["patAgeUnit"]
								else 
									empDtlRow +="--";
									
								empDtlRow += "</div>";
                            
								empDtlRow +="<div class='div-table-col width20 alignCenter' >";
								
								if(arrPatJsonObj[i]["patGenderCode"]!=null && arrPatJsonObj[i]["patGenderCode"]!="" && arrPatJsonObj[i]["patGenderCode"]!="-N.A-")
									empDtlRow += arrPatJsonObj[i]["patGenderCode"];
								else
									empDtlRow +="NA";
								empDtlRow +="</div>";
								
								 empDtlRow+="<div class='div-table-col width40 alignCenter' >";
								 var address="";
								if(arrPatJsonObj[i]["patAddHNo"]!=null && arrPatJsonObj[i]["patAddHNo"]!="" && arrPatJsonObj[i]["patAddHNo"]!="-N.A-")
									address += arrPatJsonObj[i]["patAddHNo"];
								if(arrPatJsonObj[i]["patAddStreet"]!=null && arrPatJsonObj[i]["patAddStreet"]!="" && arrPatJsonObj[i]["patAddStreet"]!="-N.A-")
									{
									if(address!="")
									  address += " , "+arrPatJsonObj[i]["patAddStreet"];
									else
										address+=arrPatJsonObj[i]["patAddStreet"];
									}
								if(arrPatJsonObj[i]["patAddCity"]!=null && arrPatJsonObj[i]["patAddCity"]!="" && arrPatJsonObj[i]["patAddCity"]!="-N.A-")
									{
									if(address!="")
									address += " , "+arrPatJsonObj[i]["patAddCity"];
									else
										address+=arrPatJsonObj[i]["patAddCity"];
									}

								if(address!=null && address !="")
									empDtlRow +=address;
								else
									empDtlRow +="NA";
								empDtlRow +="</div>";
								/* "<div class='div-table-col width20 alignCenter' >"+arrPatJsonObj[i]["patGenderCode"]+"</div>"+
								"<div class='div-table-col width40 alignCenter' >"+arrPatJsonObj[i]["patAddHNo"]+" , "+arrPatJsonObj[i]["patAddStreet"]+" , "+arrPatJsonObj[i]["patAddCity"]+"</div>"+ */							
								empDtlRow+="</b></div>";
			
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



</script>

</head>

<body >
<center>

<s:form action="NewRegistration">
    <%-- <s:set name="theme" value="simple" scope="page" /> --%>
    <div class="wrapper rounded">
	    <!-- <h1>Patient Registration</h1> -->
		<div class="div-table">
			<div class="div-table-row ">
				<div id="divTitleId" class="div-table-col title width100 ">
					White Card Search	
				</div>
			</div>
			<div class="div-table-row ">
			<div class="div-table-col" style="width: 40%;" align="center"><s:text name="Card No."/></div>
			<%-- <div class="div-table-col" style="width: 30%;display: none;" align="center"><s:text name="Sequence No."/></div> --%>
			<div class="div-table-col" style="width: 30%;" align="center"><s:text name="Card Type"/></div>
			</div>
			<div class="div-table-row ">
			<div class="div-table-col control" style="width: 40%;">
			<input type="text" name="CardId"  tabindex="1" size="30" maxlength="20" onkeypress="return validateAlphaNumericOnly(event,this)" placeholder="Card No.">
			</div>
			<%-- <div class="div-table-col control" style="width: 30%;display: none;">
					<select name="seqNo" class="select77prcnt"  tabindex="2" >
						<option value="-1">Select Value</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
					</select>
				</div> --%>
			<div class="div-table-col control" style="width: 30%;">
					<select name="cardType" class="select77prcnt"  tabindex="2" id="cardTypeId">
						<option value="-1">Select Value</option>
						<option value="1">WAP</option>
						<option value="2">TAP</option>
						<option value="3">RAP</option>
					</select>
				</div>
				<div class="div-table-col Control" style="width: 30%;"  align="center">
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				</div>
			</div>
			<!-- <div class="div-table-row ">
				
				<div class="div-table-col Control width100"  align="center">
						<input type="button" name="Go" id="goId" tabindex="1"  value="Go" onclick="go();">
				</div>
			
			</div> -->
					
	
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
	<s:hidden name="fromProcess" value="%{fromProcess}"/>
</s:form>
</center>
</body>
</html>