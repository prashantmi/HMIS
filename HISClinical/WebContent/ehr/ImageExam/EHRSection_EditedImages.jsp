<!-- 
/**
 * @author CDAC
 */
-->
<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his" %>

<%@page import="opd.*"%>
<%@page import="hisglobal.utility.servlets.ServletsUtilityConfig"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.tools.imageeditor.HisImageEditorConfig"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.io.*"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<his:javascript src="/opd/js/imageEditorFunctions.js"/>
<his:javascript src="/hisglobal/js/utilityFunctions.js" />
<his:javascript src="/hisglobal/js/popup.js" />
<his:javascript src="/hisglobal/utility/dynamicdesk/js/deskNew.js" />
<his:javascript src="/opd/js/opd_desk_new.js"/>
<his:javascript src="/registration/js/popup.js" />
<his:javascript src="/registration/js/registration.js" />
<his:javascript src="/opd/transaction/miniPaint-master/js/file.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>

<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery.js"></script>
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script>

<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery-migrate.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	alert("Hiiiiiiii");
	
});
function appendImage(json){
	alert("Hiiiii Append");
	alert(json.imgDt);
	/* for(var i = 0; i < json.length; i++){
		alert(json[i].imgDt);} */
	var strRow='<tr><td> <img  id="editedimg" src="'+json.imgDt+'" class="editimg" width="70" height="50" class="li-modal" onclick="openEditor12(event,this)" alt="'+json.vr+'"></td>'
	+'<td>Details</td>'
	+'<td><input class="form-control" type="text" placeholder="Remark" id="rmk1" name="rmk1" ></td><td><button type="button" class="btn  btn-xs btn-Info delete"   style="align:left;" >Delete</button></td></tr>';

	$('#imgrows').append(strRow);
	
	//var imgsrcfinal=imgsrc43;
	//alert(imgsrc43);
	//$('.editimg').attr("src",json.imgDt);
}
function openEditor12(event,img){
	alert("Hi editor....");
	//var imgsrc43=$('.li-modal').attr('src');
	alert(img.src);
	$('#editedimgSrc123').val(img.src);
	var crNo=document.getElementsByName('patCrNo')[0].value;
	var url="/HISClinical/opd/transaction/miniPaint-master/index.jsp?imgedit="+img;
	// targetmodal = '#myModal_portal'; 
	// $('#myModal_portal').modal('show').find('.modal-body').load(url);
	openPopup(url,event,300,600); 
    // $('#myFrameEditor').attr('src', url); 
	// $("#myFrameEditor").load(url);
}
$(document).on("click", ".delete", function () {
    $(this).closest("tr").remove();
});
function saveImageExamData()
{
alert("Inside Save func123");
	//var TableData = new Array();
	var key;
	var TableData
	var datas1;
	var patCrNo = $("[name='patCrNo']").val();
	var episodeCode = $("[name='episodeCode']").val();
	var episodeVisitNo = $("[name='episodeVisitNo']").val();
	var addmNo = $("[name='admissionNo']").val();

	alert(patCrNo);
	$('#imgrows tr').each(function(row,tr){

		alert("row");
		 var $inp = $('input', tr);
		 var $inpImg = $('img', tr);
		 datas1 = {   
				 imgSrc: $inpImg.attr('src'),
				 remark: $inp.eq(0).val(),
				 imageFileName:$inpImg.attr('alt')
              }  
		
		TableData = JSON.stringify(datas1);  
         alert(TableData);
         var result="";
			for( key in datas1){
			  result+= datas1[key];
			  //result+= "%^delimiter^%";
			  result+= "%";
			}
			alert(result);
         $.ajax({
    		 type: "POST",
            
             url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=SAVEIMAGE&&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&admissionNo="+addmNo,
             data:result,
             dataType : "text",
			    contentType: "application/json",
             success: function (data) {
                // alert("saved");
             /*  $('#headerDiv').css('background-color','green'); */
                 //$('#save_box').text('Service Procedure Saved Successfully!');
            	
            		
                 }, 
    	  error: function(data)
          {
    	     
       		}
          });   
         
         
           });    
}
</script>

	
<body onload="isOnLoad(event)" >

<input type="hidden" id="editedimgSrc123" >
<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12" style="font-size:1.2em;font-weight:bold;" align="left">

 </div>
	<div id="img12" ></div>
	
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12" id ="editdiv" >
<table class="table table-borderless table-condensed" id ="editdiv_details" style="border:1px black;">
<tr style="background: linear-gradient(to bottom, #74a9d8 , white);" >
<th>Observations</th>
<th>Details</th>
<th>Remarks</th>
<th>-</th>
</tr>
<tbody id="imgrows">
</tbody >
</table>
<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12" style="margin-top: 15px;"></div>
</div>
<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12"  id="btn_id1" style="padding-top:5px;" >
	<button type="button" class="btn  btn-xs btn-success save_btn" id="idButSave" onClick ="saveImageExamData();"  style="align:center;" > <span class="glyphicon glyphicon-floppy-disk" style=" font-size:15px;"></span></button>
</div>	
	
	
	
</body>	

<html:hidden name="EHRSection_ImageExamFB" property="hmode"/>
<html:hidden name="EHRSection_ImageExamFB" property="userSeatId"/>
<html:hidden name="EHRSection_ImageExamFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_ImageExamFB" property="patCrNo"/>
<html:hidden name="EHRSection_ImageExamFB" property="episodeCode"/>
<html:hidden name="EHRSection_ImageExamFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_ImageExamFB" property="admissionNo"/>
<html:hidden name="EHRSection_ImageExamFB" property="remark"/>

