<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
 	<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<head>
   <link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/fb.css">
 <link rel="stylesheet" type="text/css" href="/HISDRDESK/hisglobal/template/css/jqui_1_9_2.css"/>
 <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/bs_45.css">
 <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/fa_5.css">
 <link rel="stylesheet" href="../../hisglobal/template/js/swal.css">

   <style>
   .addedElGrpIn input{
   	margin:5px;
   }
   </style>
  
 
 <script src="/HISDRDESK/hisglobal/template/js/jq_3_5_1.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/jqui_1_12_1.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/poper.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/bs_45.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/fa_5.min.js"></script>
 <script type="text/javascript" src="../../hisglobal/js/util.js"></script>
 <script src="../../hisglobal/template/js/swal.js"></script>
 <script type="text/javascript" src="/HISDRDESK/new_opd/assests/jqte.js"></script>
 <script type="text/javascript" src="/HISDRDESK/new_opd/js/fb.js" id="tmplJs"></script>
 <script src="../../hisglobal/js/validation.js"></script>
 
   <script type="text/javascript">
   
	function fullScreenDesk(){
		console.log("fullScreen");
		var win = window.open(location.href+"?hmode=MODIFY","_blank");
		if(localStorage.getItem('isFullScreen1') != undefined )
		localStorage.setItem('isFullScreen1','1'); 
	
	}	
	$(document).ready(function(){ 
		if(localStorage.getItem('isFullScreen1')=='1')
		{
			$('.fullScreenDeskBtn').hide();
		}
	
		$("#defChk").on("click",function(){
	
	
			if ($("#defChk").is(':checked')) 
					$("#defChk").val("1");
			else
					$("#defChk").val("0");
		});
	});
	</script>
<script>
function getAjaxResponse(res, mode) 
{	
	if(mode=="2"){
		el_autoc_data=res;
	}
}
function getAutoCompleteData(){
	
	let url="OPDTemplateMstAction.cnt?hmode=GETAUTOCOMPLETEDATA"
	ajaxFunction(url, "2");
}


function getAutoCompleteData(){
	
	let url="OPDTemplateMstAction.cnt?hmode=GETAUTOCOMPLETEDATA"
	ajaxFunction(url, "2");
}

function proceed(ob){
	
	
	
	
	if($("#strTemplateNameId").val().match(/\w/) !=null){
	
setTimeout(function(){
			
			$('#tempDtlTile').css("display","none");
			$('#backbtn1').css("display","none");
			
			$("#tNameLab").html( $("#strTemplateNameId").val() );
			$("#catLab").html( $("#strTempCatId option:selected").text() );


			let deptTxt="";
			for( let i=0;i<$("#strDeptCodeId option:selected").length;i++ )
			{
				deptTxt +="<p class='depPop'>"+(i+1)+". "+ $($("#strDeptCodeId option:selected")[i]).text()+	"</p>";
		
			}
			
			
			$("#depLab").html( $($("#strDeptCodeId option:selected")[0]).text() );
			$("#TempColor").html('hello' );

			$("#depLab").attr( "data-content",deptTxt );
			
			
			if( $("#defChk").val()=="1")
				$("#DefLab").html("YES");
			else
				$("#DefLab").html("NO");
			
		//	$("#tmplJs").unload();
			
			$("#prevBtnDiv").css("display","")
			$("#contDiv").css("display","");
			$("#capturedModifyHtml").html("");
			 $('#delRow').trigger('click');
			 $('#delRow').trigger('click');
			$("#rowButtons").css("visibility","");
			
			
		}, 1500);
		
		
		
setTimeout(function(){
	   $("#spinGo").removeClass("spinner-border spinner-border-sm");
		}, 1600);
		$("#spinGo").addClass("spinner-border spinner-border-sm");
	}
	else{
		$(ob).attr("data-target","");
		$('#tempDtlTile').css("display","");
		alert("Enter Template Name");
		$("#contDiv").css("display","none");
	}
		
}

function dynamicTempNote(){
	
	if( $('#tempDtlTile').css("display") =="none" )
		$('#tempDtlTile').css("display","");
	else
		$('#tempDtlTile').css("display","none");
}


</script>

</head>
<body onload="getAutoCompleteData()">

<html:form action="/transaction/OPDTemplateMstAction.cnt"  name="OPDTemplateMstFb" type="new_opd.transaction.controller.fb.OPDTemplateMstFb" method="post" >

<div class="errMsg" id="errMsg"></div>
	<div class="normalMsg" id="normalMsg"></div>
	<div class="warningMsg"></div>
	
		<div class="container-fluid prescriptionTile">

			<div class="row" style="margin-bottom: -20px">

				<div class="col-sm-8">
					<h6>
						<i style="color: #17a2b8" class="fas fa-notes-medical"
							onclick="dynamicTempNote()"> </i> Dynamic Template Design Master
						<span class="badge badge-info">MODIFY</span> &nbsp; <i
							style="color: #2c99b3" onclick="fullScreenDesk()"
							class="fas fa-compress fullScreenDeskBtn"></i>
					</h6>
				</div>
				<div class="col-sm-4" align="right" id="backbtn1"
					style="text-align: right">

					<button type="button" class="btn btn-link btn-sm backbtn">
						<i class="fas fa-arrow-alt-circle-left"></i>&nbsp;Back &nbsp;
					</button>
				</div>

				<div class="col-sm-4" id="prevBtnDiv"
					style="display: none; text-align: right">

					<button type="button" id="preview" class="btn btn-primary btn-sm">
						preview &nbsp;<i class="fas fa-camera-retro"></i>
					</button>

					<button type="button" id="tmpColor" onclick="$('#colPicker').trigger('click')" class="btn btn-warning btn-sm">
						<a data-toggle="tooltip" data-placement="top" title="Template Color"><i class="fas fa-palette"> </i> </a>
					</button>
					
<!-- 					<button type="button" id="tmpColor" data-toggle="modal"
						data-target="#tempColorModal" class="btn btn-warning btn-sm">
						<a data-toggle="tooltip" data-placement="top"
							title="Template Color"><i class="fas fa-palette"> </i> </a>
					</button> -->

					<button type="button" id="clear" data-toggle="tooltip"
						data-placement="top" title="clear" class="btn btn-secondary">
						<i class="far fa-trash-alt"></i>
					</button>

					<button type="button" id="helpTemplate" data-toggle="modal"
						data-target="#helpModal" class="btn btn-default">
						<i class="fas fa-camera-ret fa-2x"></i>
					</button>

					<button type="button" id="backbtn"
						class="btn btn-link btn-sm backbtn">
						<i class="fas fa-arrow-alt-circle-left"></i>&nbsp;Back &nbsp;
					</button>

				</div>




			</div>
<hr>

<div class="container" id="tempDtlTile">
	<div class="row" style="margin-top:-12px;margin-bottm:-5px;">
		<div class="col-sm-2"><b>Template Category</b> <font color="red">*</font></div>
		<div class="col-sm-4">
			<select name="strTempCatCode" id="strTempCatId" class="form-control tmpSelect" >
					<bean:write name="OPDTemplateMstFb" property="strTemplateCatValues" filter="false"/>
				</select>
		</div>
		<div class="col-sm-2"><b>Template Type</b> <font color="red">*</font></div>
		<div class="col-sm-4"> 
			<select class="form-control tmpSelect" id="strTemplateType" >
					<option value="0">Select Value</option>
					<option value="1" selected="selected">Opd</option>
					<option value="2">Service Area</option>
					<option value="3">OT</option>
				</select>
		</div>
		
		
	</div>
	<br>
		<div class="row">
		<div class="col-sm-2"><b>Department</b> <font color="red">*</font></div>
		<div class="col-sm-4">
			<select name="strDeptCode" id="strDeptCodeId" multiple="multiple" class="form-control" style="color: #082b71;" onchange="" >
            		<bean:write name="OPDTemplateMstFb" property="strDeptValues" filter="false"/>
             </select>
		</div>
		
		<div class="col-sm-6">
			<div class="row">
				<div class="col-sm-4"><b>Default</b></div>
				<div class="col-sm-8" style="text-align:left"> 
				<input type="checkbox"  id="defChk" value="1">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-sm-9">
					<div class="input-group">
				        <div class="input-group-prepend">
				          <span class="input-group-text" id="inputGroupPrepend">Template Name<font color="red">*</font></span>
				        </div>
				        <input type="text" class="form-control" name="strTemplateName" value="${OPDTemplateMstFb.strTemplateName}" id="strTemplateNameId"  placeholder="Enter Template Name" aria-describedby="inputGroupPrepend" required="">
				        <div class="invalid-feedback">
				          Please Enter Template Name.
				        </div>
			        </div>
			      </div>
			      
			      <div class="col-sm-3">
			      		<button type="button" class="btn btn-info" onclick="proceed(this)">Go &nbsp;<span id="spinGo" ></span></button>
			      </div>
			</div>
		</div>
		
	</div>
	
</div> 

	<div class="container-fluid">
	
			<div class="row">
				<div class="col"><b>Template Name:</b></div>
				<div class="col"><label id="tNameLab">---</label></div>
				
				<div class="col"><b>Category:</b></div>
				<div class="col"><label id="catLab">---</label></div>
				
				<div class="col"><b>Department</b></div>
				<div class="col-sm-2"><label >
					 <a href="#" id="depLab" data-toggle="popover" title="Department" data-content="---"></a>
				</label></div>
				
				<div class="col-sm-2" id="rowButtons" style="visibility:hidden;">
						<div id="rowAdHelper"
							style="position: absolute; top: 22px; left: -25px; display: none;">
							<i class="fas fa-location-arrow fa-3x blink"
								style="color: orange"></i>
						</div>
						<button type="button" onclick="$('#addRow').trigger('click')" style="margin-right:2%" class="btn btn-info btn-sm adBtn">Add Row&nbsp;<i class="fas fa-plus"></i></button>
				
				<button type="button"  onclick="$('#delRow').trigger('click')" class="btn btn-danger btn-sm dlBtn">Del Row&nbsp;<i class="far fa-trash-alt"></i> </button>
			
				</div>
			</div>
	</div>	
</div>

<br>

<div id="contDiv" style="display:none" class="container-fluid"></div> <!-- Template Container -->



						<div id="helpModal" class="modal">
							<div class="modal-dialog">
								<div class="modal-content">
									<div class="modal-header">Help <button type="button" class="btn btn-success" data-dismiss="modal">ok</button></div>
									<div class="modal-body">
										#Template Help Text
									</div> 
									
								</div>
							</div>
						</div>
						
						
						
						
							
						
						
<div id="capturedModifyHtml" style="display:none">

${OPDTemplateMstFb.strTemplateModifyHtml}
</div>   
<%-- <input type='hidden' name='strTemplateModifyHtml' value='${OPDTemplateMstFb.strTemplateModifyHtml}' />  --%>  
<input type="color" id="colPicker" style="cursor:pointer;display:none;" value="#ff0000" onchange="chgTempColor(this);">
<input type="hidden" name="hmode" value="" /> 
<input type="hidden" name="strEssentilaJson" value="${OPDTemplateMstFb.strEssentilaJson}"/>
<input type="hidden" name="strJsonParaMetereIdString" value="${OPDTemplateMstFb.strJsonParaMetereIdString}"/>
<input type="hidden" name="strHtmlString" value="${OPDTemplateMstFb.strHtmlString}"/>
<input type="hidden" name="strModifyData" value="${OPDTemplateMstFb.strModifyData}"  >
<input type="hidden" name="strChk" value="${OPDTemplateMstFb.strChk}"  >



   </html:form> 
   
   <script>
   function chgTempColor(obj){
		$(obj).val($(obj).val());		
		document.getElementById("subContDivLeft").style.backgroundColor=$(obj).val();
}
  
   </script>
   <script>
   $(document).ready(function(){
	   $("#prescSaveBtnId").click(function(){
		   	 document.forms[0].hmode.value="MODIFYSAVE" ;
				 document.forms[0].submit(); 
		   	}); 
		});</script>
	
</body>

 </html>