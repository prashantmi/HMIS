<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/dateTag.tld" prefix="dateTag"%>
<%@ taglib uri="/WEB-INF/hisTab.tld" prefix="tag"%>
<%@ taglib uri="/WEB-INF/comboPersistence.tld" prefix="cmbPers"%>

<html>
<head><meta charset=utf-8>

<title>Patient Category And Payment Mode  Master Add Page</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/css/bootstrap-theme.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/datatable/css/dataTables.bootstrap.css" rel="stylesheet" />
<link href="../../hisglobal/bootstrap4.0_newgui/font-awesome/css/all.css" rel="stylesheet">
<link href="../../hisglobal/bootstrap4.0_newgui/swAl/swal.css" rel="stylesheet">
<link href="../../ipd/css/newlayout.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/bootstrap4.0_newgui/datetimepicker/css/gijgo.min.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/swAl/swal.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/jquery-3.3.1.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/jquery.dataTables.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datatable/js/dataTables.bootstrap4.js"></script>
<script type="text/javascript" src="../../hisglobal/bootstrap4.0_newgui/datetimepicker/js/gijgo.min.js"></script>

<link href="../css/master.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/tab.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/calendar-tas.css" rel="stylesheet" type="text/css">
<link href="../../hisglobal/css/buttons.css" rel="stylesheet" type="text/css">
	
<script language="JavaScript" src="../../hisglobal/masterutil/js/master.js"></script>
<script language="JavaScript" src="../../hisglobal/js/tab.js"></script>
<script language="JavaScript" src="../../hisglobal/js/calendar.js"></script>
<script language="Javascript" src="../../hisglobal/js/validationBootstrap.js"></script>
<script language="Javascript" src="../../hisglobal/js/util.js"></script>

<style type="text/css">
.card {
    z-index: 0;
    background-color: white;
    padding-bottom: 20px;
    margin-bottom: 90px;
    border-radius: 10px;
    color: black;
}
.legend2 {
    position: absolute;
    right: 100px;
    line-height: 1.2em;
    top: -1.8em;
}


.btn-circle1 {
    width: 25px;
    height: 25px;
    text-align: center;
    padding: 6px 0;
    font-size: 10px;
    line-height: 1.428571429;
    border-radius: 24px;
    color: white;
    
}

.newrow1 {
	
	border-bottom:solid 0.3rem #02629CBA;
	padding:1rem;
	border-radius: 5px;
}
</style>

<script type="text/javascript">

	function validate1(){	
	
		var hisValidator = new HISValidator("PatientCategoryAndPaymentModeBean");  
		
		hisValidator.addValidation("strCategory","dontselect=0","Please select a value from Category");
		//hisValidator.addValidation("strRecieptPaymentMode","dontselect=0","Reciept Payment Mode is a Mandatory Field");
		//hisValidator.addValidation("strRefundPaymentMode","dontselect=0","Refund Payment Mode is a Mandatory Field");
	
		var retVal = hisValidator.validate(); 
	
		if(retVal){
				document.forms[0].hmode.value = "INSERT";
				document.forms[0].submit();
		}else{
		
		return false;
		}
		
	}

	function enterAmount(){
		
			var retVal = false;
		
			var adv = document.PatientCategoryAndPaymentModeBean.strAdvanceAmount.value;	
			var sec = document.PatientCategoryAndPaymentModeBean.strAdvanceSecurity.value;
			var total  = 0;
			
			if(isNaN(adv) || adv=="") adv = "0";
			if(isNaN(sec) || sec=="") sec = "0";
			
					total = manipulateValue(parseFloat(adv),parseFloat(sec),0);
					document.forms[0].strAdvanceTotal.value = total;
					retVal = true;
				
		
		return retVal;
	}




	function checkWardVisible(){
		
		/* 	if(document.forms[0].strHospitalServiceId.value == 2){
				document.getElementById("wardDivId").style.display = "block";
				document.forms[0].strWardType.focus();
			}else{
			document.getElementById("wardDivId").style.display = "none";
			document.forms[0].strCategory.focus();
			} */
		//	document.getElementsByName("strRefundPaymentMode")[1].disabled=true;

	}

	function checkMsg()
	{
		var err=document.getElementById("errMsg");
		var nor=document.getElementById("normalMsg");
		var warn=document.getElementById("wrnID");
		if (err.innerHTML != "") {
			
			err.innerHTML="<i class='fas fa-exclamation-triangle'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Error!</strong>&nbsp;&nbsp;"+err.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			err.style.display = "";
			
		}
		if (nor.innerHTML != "") {
			nor.innerHTML="<i class='far fa-thumbs-up'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+nor.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			nor.style.display = "";
		}
		if (warn.innerHTML != "") {
			warn.innerHTML="<i class='fas fa-bell'></i>"+"&nbsp;&nbsp;&nbsp;<strong>Success!</strong>&nbsp;&nbsp;"+warn.innerHTML+"<button type='button' class='close' data-dismiss='alert'>&times;</button>";
			warn.style.display = "";
		}

		
	}
</script>

</head>
<body onLoad="checkWardVisible();checkMsg();">
<html:form action="/masters/CNTPatientCategoryAndPaymentModeMst.cnt"
	type="billing.masters.controller.fb.VOPatientCategoryAndPaymentModeMst" name="PatientCategoryAndPaymentModeBean">
	
	<%-- <div class="errMsg"><bean:write name="PatientCategoryAndPaymentModeBean" property="strErr"/></div>
	<div class="warningMsg"><bean:write name="PatientCategoryAndPaymentModeBean" property="strWarning"/></div>
	<div class="normalMsg" id='normalMsg'><bean:write name="PatientCategoryAndPaymentModeBean" property="strMsg"/></div>
	 --%>
	
	                <div class="alert alert-danger alert-dismissible fade show" id="errMsg"  style="display:none;"><bean:write name="PatientCategoryAndPaymentModeBean" property="strErr"/></div>
					<div class="alert alert-success alert-dismissible fade show" id="normalMsg" style="display:none;"><bean:write name="PatientCategoryAndPaymentModeBean" property="strMsg"/></div>
					<div class="alert alert-warning alert-dismissible fade show"  id="wrnID" style="display:none;"><bean:write name="PatientCategoryAndPaymentModeBean" property="strWarning"/></div>			  	<div class="row justify-content-center" >
					
					
					
	<div class="container-fluid">
			<br>
						<div class="card">
							<div class="row rowFlex reFlex">
								<div class="legend2" id='nonPrintableLegend2'>
									<button type="button"
										class="float-right btn btn-outline-danger mt-1 btn-circle cancelbtn"
										onclick="cancelFunc();">
										<i class="fas fa-ban iround" title="Cancel"></i>
									</button>
									<button type="button" class=" btn btn-secondary btn-circle" 
										style="background: #b9b9b9; border-color: #b9b9b9; margin-top: 0.25rem !important;">
										<img src="/HIS/hisglobal/images/clear3.png" title="Clear"
											style="width: 23px; color: #fff;">
									</button>
									<button type="button" id="savebutton" onclick=" return validate1();"
										class="float-right btn btn-outline-success mt-1 btn-circle"
										tabindex='2' style="background-color: #5cb85c;">
										<i class="fa fa-save iround" title="Save"></i>
									</button>
								</div>
							</div>
							<div class="row rowFlex reFlex">
								<div class="col-sm-7">
									<p class="subHeaders"> <i class="fas fa-university" style="font-size: 26px"></i>
										&nbsp;Patient Category And Payment Mode Master>>Add
									</p>
									<!-- <button  type="button" class="btn btn-outline-success mt-1  savebtn" onclick="cancelFunc();">
		                                        <i class="fas fa-file-alt iround"  title="Cancel"></i>
	                                      </button> -->
								</div>
								<div class="col-sm-5"></div>
								</div>
    
	
	<div class="row ">
		<div class="col-sm-1"></div>
	<div class="col-sm-3"><label>Patient Category</label><font color="red">*</font></div>
	<div class="col-sm-8"></div>
	</div>
	
	<div class="row">
	<div class="col-sm-1"></div>
	<div class="col-sm-3">
	<select name="strCategory" id="strCategory" class="form-control" >
        <bean:write name="PatientCategoryAndPaymentModeBean" property="strCategoryValues" filter="false"/> </select> 
	</div>
	<div class="col-sm-8"></div>
	</div>
	
	<div class="row newrow1">
	<div class="col-sm-2"></div>
	<div class="col-sm-4">

	</div>
	<div class="col-sm-2"></div>
	
	</div>
	
	<!-- <div class='newrow1'></div> -->
<div class="row" id="row-header" style="padding-top: 5px;">
	<div class="col-sm-1"></div>
		<div class="col-md-3">Reciept Payment Mode<font color="red">*</font></div>
		<div class="col-md-2" align="center">
			<button type="button" id="addRecPayMode" class="float-right btn btn-outline-success mt-1 btn-circle1" tabindex='2' 
			style="background-color: #5cb85c;" > <i class="fas fa-plus" title="add"></i>
			 </button>
		</div>
		<div class="col-md-4">Refund Payment Mode<font color="red">*</font></div>
	</div>
<div  id="container">
	
	 
 	 <div class="row newrow1" id="mapping-row-0" style="display:none;">
 	 <div class="col-sm-1"></div>
		<div class="col-md-3" id="recPayMode-row-0">   
			<select class="form-control" name='strRecieptPaymentMode'>
				<bean:write name="PatientCategoryAndPaymentModeBean" property="strRecieptPaymentModeValues" filter="false"/>
			</select>
		</div>
		<div class="col-md-2">
			<div class="checkbox">
              <label><input type="checkbox" value="" style="margin-top: 10px;"></label>
            </div>
		</div>
		<div class="col-md-4" id="refPayMode-row-0">
			<div class="row">
			<div class="col-sm-8">
					<select class="form-control" name='strRefundPaymentMode'>
					<bean:write name="PatientCategoryAndPaymentModeBean" property="strRefundPaymentModeValues" filter="false"/>
				</select>
				</div>
				<div class="col-md-2">
			<div class="checkbox">
              <label><input type="checkbox" value="" style="margin-top: 10px;"></label>
            </div>
		</div>
				<div class="col-sm-2">
				<button type="button" id='displaybtn' class="float-right btn btn-outline-danger mt-1 btn-circle1 cancelbtn remove" style="display: none;"><i class="fas fa-minus " title="remove"></i> </button>
			</div></div>
		
		</div>
				
			<div class="col-md-2" align="left">
				<button type="button" id="addRefPayMode-row-0" onclick="addRefPayMode(this);" class=" btn btn-outline-success mt-1 btn-circle1" tabindex='2' 
					style="background-color: #5cb85c;" > <i class="fas fa-plus " title="add"></i> 
				</button>
			</div>
			
	 </div> 
	<!-- <div id='PaymentModeMappingId'></div> -->
	
	<%--  <div class="row" id="mapping-row-1">
	 <div class="col-sm-1"></div>
		<div class="col-md-3" id="recPayMode-row-1">   
			<select class="form-control" name='strRecieptPaymentMode'>
				<bean:write name="PatientCategoryAndPaymentModeBean" property="strRecieptPaymentModeValues" filter="false"/>
			</select>
		</div>
		<div class="col-md-2">
			<div class="checkbox">
            <label><input type="checkbox" value="" style="margin-top: 10px;"></label>
            </div>
		</div>
		<div class="col-md-4" id="refPayMode-row-1">
			<div class="row">
			<div class="col-sm-8">
					<select class="form-control" name='strRefundPaymentMode'>
					<bean:write name="PatientCategoryAndPaymentModeBean" property="strRefundPaymentModeValues" filter="false"/>
				</select>
				</div>
				<div class="col-md-2">
			<div class="checkbox">
              <label><input type="checkbox" value="" style="margin-top: 10px;"></label>
            </div>
		</div>
				<div class="col-sm-2">
				<button type="button" class="float-right btn btn-outline-danger mt-1 btn-circle1 cancelbtn remove" style="display: none;"><i class="fas fa-minus " title="remove"></i> </button>
			</div>
			</div>
		
		</div>
				
			<div class="col-md-2" align="left">
				<button type="button" id="addRefPayMode-row-1" onclick="addRefPayMode(this);" class=" btn btn-outline-success mt-1 btn-circle1" tabindex='2' 
					style="background-color: #5cb85c;" > <i class="fas fa-plus" title="add"></i> 
				</button>
			</div>
	 </div> --%>
	 
	 
</div>
 
</div>
</div>
	

<input type="hidden" name="hmode"/>
<input type="hidden" name="strRefQty" id="refQty" value="1"/>
<input type="hidden" name="ctDate" value="${PatientCategoryAndPaymentModeBean.strCtDate }"/>
<input type="hidden" name="combo" value="${PatientCategoryAndPaymentModeBean.combo[0]}" />
<input type="hidden" name="comboValue" value="${PatientCategoryAndPaymentModeBean.comboValue}" />
<input type="hidden" name="strHospitalServiceId" value="${PatientCategoryAndPaymentModeBean.combo[0]}"/>
<input type="hidden" name="strHospitalService" value="${PatientCategoryAndPaymentModeBean.comboValue}" />

	<cmbPers:cmbPers/>
</html:form>
<tag:autoIndex></tag:autoIndex>

<script type="text/javascript">
/* 
$('#container').on('click','.newField', function () {
    var newthing=$('div.addNew:first').clone().find('.newField').removeClass('newField').addClass('remove').val('Remove Field!').end();

   $('#container').append(newthing);
});

$('#container').on('click','.remove', function () {
  
  $(this).parent().remove();
});


$('#add').on('click', function () {
  
  var thing=$('div.addNew:first').clone();
  $('#container').append(thing);
});

$('#container').on('click','.newField', function () {
    var newthing=$('div.addNew1:first').clone().find('.newField').removeClass('newField').addClass('remove').val('Remove Field!').end();

   $('#container').append(newthing);
});

$('#container').on('click','.remove', function () {
  $(this).parent().remove();
});


$('#add1').on('click', function () {
  var thing=$('div.addNew1:first').clone();
  $('#container').append("<div class='row newRefPayMode'><div class='col-sm-6'></div>");
  $('#container .newRefPayMode').last().append(thing);
  $('#container').append("<button type='button' class='float-right btn btn-outline-danger mt-1 btn-circle1 cancelbtn remove'><i class='fas fa-minus iround' title='Remove'></i></button></div>");
}); */

$('#container').on('click','.remove', function () {
	  
	  $(this).parent().parent().remove();
	});
$("#addRecPayMode").on('click',function(){
		let currId=$('[id^=mapping-row]').last().attr('id');
		let nextid=currId.split("-");
		nextid="-"+nextid[1]+"-"+(parseInt(nextid[2])+1);
		 let $div=$("#mapping-row-0").clone().prop('id',"mapping"+nextid);
		 $div.css("display","");
		 $div.find('[id^=recPayMode-row-]').prop('id',"recPayMode"+nextid);
		 $div.find('[id^=refPayMode-row-]').prop('id',"refPayMode"+nextid);
		 $div.find('[id^=addRefPayMode-row-]').prop('id',"addRefPayMode"+nextid);
		 
	//$("#container").append("<div class='row newrow1'></div><br>");	
	$("#container").append($div);
	document.getElementById("refQty").value=document.getElementById("refQty").value+"@"+1;
	
});

function addRefPayMode(add){
	
	let currId=add.id.split("-");
	let refQty=document.getElementById("refQty");
	refQty=refQty.value.split("@");
	let len =refQty.length;
	let temp=0;
	temp=refQty[parseInt(len)-1];

	if(len==currId[2])
			refQty[parseInt(len)-1]=parseInt(temp)+1;
	else
	{
		for(let i=0;i<len;i++)
			{
				if(currId[2]==(i+1)){
					refQty[i]=parseInt(refQty[i])+1;
					
			}
			
			}
		
	}
	document.getElementById("refQty").value=refQty.join("@");
	
	
	
	
	currId="-"+currId[1]+"-"+currId[2];
	$("#refPayMode"+currId).append($("#refPayMode"+currId).find(".row").last().clone());
	$("#refPayMode"+currId).find(".remove").css("display","");
	$("#refPayMode"+currId).find(".remove").first().css("display","none");
	
	
	

	
}

/* $("#strCategory").on("change",function(){
	url = "CNTPatientCategoryAndPaymentModeMst.cnt?hmode=GETPAYMENTMODEMAPPING&catCode="+$(this).val();
	ajaxFunction(url,"1");
}); */

$("#strCategory").on("change",function(){	
let	url = "CNTPatientCategoryAndPaymentModeMst.cnt?hmode=GETPAYMENTMODEMAPPINGJSON&catCode="+$(this).val();
var save = $('#mapping-row-0').detach();
$('#container').empty().append(save);
ajaxFunction(url,"1");
});


function getAjaxResponse(res,mode)
{
	//alert(res);
	//document.getElementById("PaymentModeMappingId").innerHTML=res;
	
    if(mode == '1')
    {	
    	onLoadLogicPMC(res);
    } 
}

function onLoadLogicPMC(res){
	
	let obj=JSON.parse(res);	
	let x=Object.keys(obj);
if(x.length>0){
	for(let key in x )
	{
	$("#addRecPayMode").trigger("click");
	document.getElementsByName("strRecieptPaymentMode")[(parseInt(key)+1)].value=x[(parseInt(key))];
	
						for(let i in obj[x[key]]){
							if(Array.isArray(obj[x[key]])){
								if(i!=0)
									addRefPayMode(document.getElementById("addRefPayMode-row-"+(parseInt(key)+1)));
							}
							$("#refPayMode-row-"+(parseInt(key)+1)+" select[name='strRefundPaymentMode']").eq( (parseInt(i)) ).val(obj[x[key]][i]);					 
						}

	}
}else{
	$("#addRecPayMode").trigger("click");
}

}
</script>
</body>
</html>