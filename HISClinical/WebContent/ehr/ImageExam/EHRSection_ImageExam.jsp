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



<%-- 
<script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery.js"></script>
<his:javascript src="/hisglobal/js/jquery/jquery-1.10.2.js" />
<script type="text/javascript" src="/HIS/hisglobal/js/jquery/jquery-ui.js"></script> --%>

<!-- <script src="/HISClinical/opd/transaction/miniPaint-master/vendor/jquery/jquery-migrate.js"></script> -->
 <!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script> -->
<script type="text/javascript">


$(document).ready(function(){

	 $('[data-toggle="tooltip"]').tooltip();
		
		var deptCode=document.getElementsByName("departmentUnitCode")[0].value; 
		//var crNo=document.getElementsByName('patCrNo')[0].value;
		//alert(crNo);
		getPrevImgList();
		 var form = $('#patInfo');
	  var patCrNo = form.find("input[name='patCrNo']").val();
	 // alert(patCrNo);
		  var str='';
	 
		 $.ajax({
			 type: "GET",
	         url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=AJAX_GETIMAGELIST&departmentUnitCode="+deptCode+"&patCrNo="+patCrNo ,
	         contentType: "application/json",
	         dataType: "json",
	         success: function (data) {
		     //   alert(data);
	        	   for (var i = 0; i < data.length; i++) {
		        	   var imageName=data[i].imageName;
							//alert(imageName);
			                 /* str+='<div class="item active row"><a data-toggle="tooltip"  title="Click to Edit!"><img src="'+data[i].imageFileName+'" width="50" class="img-responsive zoom" data-toggle="modal"  data-target="#myModal_IMG" height="30"  alt="'+imageName+'" style="align:center;" name="'+imageName+'" onclick="openEditor(event,\''+imageName+'\')"></a>'
	        					+'</div>'; */
	        					str+='	<div class="item active"><div class="col-md-2 col-sm-6 col-xs-12"><a data-toggle="tooltip"  title="Click to Edit!"><img src="'+data[i].imageFileName+'" width="50" class="img-responsive zoom" data-toggle="modal"  data-target="#myModal_IMG" height="30"  alt="'+imageName+'" style="align:center;" name="'+imageName+'" onclick="openEditor(event,\''+imageName+'\')"></a></div></div>';
	                 
	              } 
	             
	        	$('#img12').append(str);
	             }, 
		  error: function(data)
	      {
		   
	    	}
	      });  
	      
		// var crNo=document.getElementsByName('patCrNo')[0].value;
	//	 var deptCode=form.find('[name="departmentUnitCode"]').val();
		 
		 var str432;
		
		 $.ajax({
			 type: "GET",
	         url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=AJAX_GETOTHERIMAGELIST&departmentUnitCode="+deptCode+"&patCrNo="+patCrNo ,
	         contentType: "application/json",
	         dataType: "json",
	         success: function (data) {
		
		         for (var i = 0; i < data.length; i++) {
		        	   var imageName=data[i].imageName;
	             
	               str432+='<tr class="otherimg-select">'
		               	+'<td><input class="checkbox" type="checkbox" style="vertical-align:bottom;"></td>'
						+'<td><div class="imgNameOt" style="align:center;padding-top:3px;">'+data[i].imageName+'</div></td>'
						
						+'</tr>';
	      
	            } 
	           
	      	$('#otherImgL').append(str432); 
		        	
	             }, 
		  error: function(data)
	      {
		   
	    	}
	      });  
		  		
	      
});




function getPrevImgList(){
	//var crNo=document.getElementsByName('patCrNo')[0].value;
 //	var form = $('#patInfo');
	//  var patCrNo = form.find("input[name='patCrNo']").val();
	var patCrNo=$("input[name='patCrNo']").val();
	//  alert(patCrNo);
	var strRow='';
	  $.ajax({
			 type: "GET",
	         url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=AJAX_GETPREVIMGEXM&patCrNo="+patCrNo ,
	         contentType: "application/json",
	         dataType: "json",
	         success: function (data) {
		         
		         if(data!=''){
	        	   for (var i = 0; i < data.length; i++) {
		        	   var imageName=data[i].imageName;
	              
	               	strRow+='<tr >'
		               	+'<td>'+data[i].imageName+'</td>'
						+'<td><img  id="editedimg" src="'+data[i].imageFileName+'" class="editimg zoom" width="50" height="30" data-toggle="tooltip" title="'+data[i].imageName+'" class="li-modal"  alt="'+data[i].imageName+'"><input type="hidden" class="imgcode" value="'+data[i].imageCode+'"></td>'
						+'<td>'+data[i].entryDate+'</td>'
						/* +'<td>-</td>' */
						+'<td>'+data[i].remarks+'</td>'
						/* +'<td><button type="button" class="btn btn-info btn-sm deletedb"   style="align:left;" >Revoke</button></td>' */
						+'</tr>'; 
	              } 
		              $('#imgrows12').append(strRow);
		              $('#prevdiv').show();
		         }
	         },
	        	
		  error: function(data)
	      {
		
	    	}
	      }); 
}

function openEditor(event,img){
	//alert(img);
	
	$('#imgCdFrIndxId').val(img)
	var crNo=document.getElementsByName('patCrNo')[0].value;
	var url="/HISClinical/opd/transaction/miniPaint-master/index.jsp?imgsrc413="+img;
	var url12 = "/HISClinical/opd/opdTemplateTab.cnt?hmode=NEW&deskMenuId=3&isCallFromSinglePage=1&patCrNo="+crNo;

	//targetmodal = '#myModalIMGTEMP'; 
   

		
       targetmodal = '#myModal_IMG'; 
       $('#myFrameIMG').attr("src", url);   
    /*    $('#myFrameIMG12').load(url12);  */
   
	
}

 function editedImage(json)
{

  var strRow='<tr>'
	  +'<td>'+json.vr+'</td>'
	  +'<td> <img  id="editedimg" src="'+json.imgDt+'" class="editimg zoom" width="50" height="30" class="li-modal" data-toggle="modal"  data-target="#myModal_RE-IMG" onclick="openEditor12(event,this)" alt="'+json.vr+'"></td>'
	+'<td><div class="dateClass"></div></td>'
	/* +'<td> <button type="button" class="btn btn-info btn-sm" id="imageExamTemp" onClick ="" align="right" data-toggle="modal"  data-target="#myModalIMGTEMP" >	Details	</button> </td>' */
	+'<td><input class="form-control" type="text" placeholder="Remark" id="rmk1" name="rmk1" ></td><td><button type="button" class="btn btn-info btn-sm delete"   style="align:left;" >Delete</button></td></tr>';

	$('#imgrows').append(strRow);
  $("#editdiv").show();
  $('.dateClass').text(setCurrentDate());
  $('#idButSave').prop('disabled', false);

  
} 
function srcReplace(json1){

		    var divid=$('#imgrows')
		    var imgdiv=divid.find('img');
		    var oldsrc=$('#editedimgSrc123').val();
		    console.log(oldsrc);
		    imgdiv.each(function(){
			    var currsrc=$(this).attr('src');
			    console.log(currsrc);
			    if(currsrc==oldsrc){
				    //alert("hiiiiii");
			    	$(this).attr("src", json1.imgDt);
			    }

				    });
		    
		
}

 function openEditor12(event,img){
		
		
		$('#editedimgSrc123').val(img.src);
		//alert($('#editedimgSrc123').val());
		//var crNo=document.getElementsByName('patCrNo')[0].value;
		var url12="/HISClinical/opd/transaction/miniPaint-master/index.jsp?flag=1";

		targetmodal = '#myModal_RE-IMG'; 
	       $('#myFrameREIMG').attr("src", url12);  
	  
	}

 function submitToUpload(event)
 {
 	//openPopFileUpload(event);
 	url='/HISClinical/opd/master/imageUpload.cnt?transactionMode=NewImageExam';
	 targetmodal = '#myModalUpload'; 
     $('#myFrameUploadIMG').load(url); 

     }

 function openPopFileUpload(event)

 {
 	openPopup('<his:path src='/opd/master/imageUpload.cnt'/>',event,300,600);
 }
 
 function submit4image(finalfile,imageName)
 {
	// alert(finalfile);
	// alert("inside submit4image");
	/*  $.ajax({
		 type: "GET",
         url: "/HISClinicalWB/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=Image",
         contentType: "application/json",
         dataType: "json",
         success: function (data) { */
	       
	         strRow12='<tr>'
		         +'<td>'+imageName+'</td>'
	         +'<td> <img  id="editedimg" src="'+finalfile+'" class="editimg zoom" width="50" height="30" data-toggle="modal"  data-target="#myModal_RE-IMG" class="li-modal" onclick="openEditor12(event,this)" alt="'+imageName+'"></td>'
	     	+'<td><div class="dateClass"></div></td>'
	     /* 	+'<td><button type="button" class="btn btn-info btn-sm" id="imageExamTemp" onClick ="" align="right" data-toggle="modal"  data-target="#myModalIMGTEMP" >	Details	</button> </td>' */
	    	+'<td><input class="form-control" type="text" placeholder="Remark" id="rmk1" name="rmk1" ></td><td><button type="button" class="btn btn-info btn-sm delete"   style="align:left;" >Delete</button></td></tr>';

	    	$('#imgrows').append(strRow12);
	         $("#editdiv").show();
	         $('.dateClass').text(setCurrentDate());
	         $('#idButSave').prop('disabled', false);
             /* }, 
	  error: function(data)
      {
	   
    	}
      });  */
 	
 }


 
 $(document).on("click", "#idButSave", function () {
 
 
 	var key;
 	var TableData
 	var datas1;
 	//var patCrNo = $("[name='patCrNo']").val();
 	 var form = $('#patInfo');
	  var patCrNo = form.find("input[name='patCrNo']").val();
 	var episodeCode = $("[name='episodeCode']").val();
 	var episodeVisitNo = $("[name='episodeVisitNo']").val();
 	var addmNo = $("[name='admissionNo']").val();
	var res='';
	var flag=1;
 	
 	$('#imgrows tr').each(function(row,tr){

 		
 		 var $inp = $('input', tr);
 		 var $inpImg = $('img', tr);
 		console.log($inpImg.attr('src'));
 		
 		 datas1 = {   
 				 imgSrc: $inpImg.attr('src'),
 				 remark: $inp.eq(0).val(),
 				 imageFileName:$inpImg.attr('alt'),
 				 
               }  
 		
 		
          var result="";
 			for( key in datas1){
 			  result+= datas1[key];
 		
 			  result+= "%";
 			}
// alert(result);
          $.ajax({
     		 type: "POST",
             
              url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=SAVEIMAGE&patCrNo="+patCrNo+"&episodeCode="+episodeCode+"&episodeVisitNo="+episodeVisitNo+"&admissionNo="+addmNo,
              async:false,
              data:result,
              beforesend :
                   $('#imgBody').append('<p id="prescriptionListMsg"><i class="fa fa-spinner fa-spin"></i> Loading</p>'),

 			    contentType: "application/json",
              success: function (data) {
                $('#prescriptionListMsg').remove();
               
            	  res = data;
                  
                
            
             		
                  }, 
     	  error: function(data)
           {
              
        	   alert("error while saving data");
     		  return;
     	     
        		}
           });   
          
          if(res=="Image Not Saved")
  	  	{
  	  	flag=flag+1;
  	  	err1 = tabString1[2];
  	  	err2 = tabString1[0];
  	  	}
  	  
  	else{
  	flag=0;
  	  	}
          
          
            });   
  

 	

 	if(flag==0)
	{

 		
	$('#savedmsgid').text('Data Saved Successfully!');
	$( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
	//$('#savedmsgid').show();
	$(".imgrows > tr").remove(); 
	$('#prescriptionListMsg').remove();
	 $(".editdiv").hide();
	 $("#imgrows12 > tr").remove();
	 $('#idButSave').prop('disabled', true);
	 getPrevImgList();
	
	}
else{
	$('#savedmsgid').text('Error while Saving data');
	$('#savedmsgid').show();
	}
     
 });
 
 $(document).on("click", ".delete", function () {

	 if ($(".imgrows > tr").length == null || $(".imgrows > tr").length == 0){
		    $(".editdiv").hide();
		  }
	 else if($(".imgrows > tr").length == 1){
		 $(this).closest("tr").remove();
		 
		 $(".editdiv").hide();
		 $('#idButSave').prop('disabled', true);
		 
		 }
	 
		else{
			 $(this).closest("tr").remove();
			
		}
	    
	});

 $(document).on("click", ".add", function () {
	 
	 $(".otherimg-select input:checked").each(function() { 

		 
		 var otheImgName=$(this).closest('tr').find('.imgNameOt').text();
		
		 $.ajax({
     		 type: "POST",
             
              url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=GetOtherImageSrc&imageName="+otheImgName,
              
              dataType : "json",
 			    contentType: "application/json",
              success: function (data) {
                 
                  strRow123='<tr>'
     		         +'<td>'+data[0].imageName+'</td>'
     	         +'<td> <img  id="editedimg" src="'+data[0].imageFileName+'" class="editimg zoom" width="50" height="30" data-toggle="modal"  data-target="#myModal_RE-IMG" class="li-modal" onclick="openEditor12(event,this)" alt="'+data[0].imageName+'"></td>'
     	     	+'<td><div class="dateClass"></div></td>'
     	     	/* +'<td><button type="button" class="btn btn-info btn-sm" id="imageExamTemp" onClick ="" align="right" data-toggle="modal"  data-target="#myModalIMGTEMP" >	Details	</button> </td>' */
     	    	+'<td><input class="form-control" type="text" placeholder="Remark" id="rmk1" name="rmk1" ></td><td><button type="button" class="btn btn-info btn-sm delete"   style="align:left;" >Delete</button></td></tr>';

     	    	$('#imgrows').append(strRow123);
     	         $("#editdiv").show();
     	         if($('.checkbox:checked')){
         	        
         	        $('input:checkbox').removeAttr('checked');
         	       
         	         }
             	
     	        $('.dateClass').text(setCurrentDate());
     	       $('#idButSave').prop('disabled', false);
                  }, 
     	  error: function(data)
           {
     	     
        		}
           });   


		 });
	
	 
	 
	    
	});
	
 function setCurrentDate()
 {
	 var months    = ['Jan','Feb','March','April','May','June','July','Aug','Sept','Oct','Nov','Dec'];
	 var today = new Date();
	 var dd = String(today.getDate()).padStart(2, '0');
	 var mm = months[today.getMonth()]; //January is 0!
	 var yyyy = today.getFullYear();

	var today1 = dd + '-' + mm + '-' + yyyy;
	 return today1;
 }
 
	
 $(document).on("click", ".deletedb", function () {
	 
	 //alert($(this).closest("tr").find('.imgcode').val());
	 
	 var imgFileNo=$(this).closest("tr").find('.imgcode').val();
	 //var crNo=document.getElementsByName('patCrNo')[0].value;
	  //var form = $('#patInfo');
	//  var patCrNo = form.find("input[name='patCrNo']").val();
	 var patCrNo = $("input[name='patCrNo']").val();
	 
	 $.ajax({
		 type: "GET",
         url: "/HISClinical/emr/ehrComposition_PAT_IMG_EXAM.cnt?hmode=AJAX_REVOKEIMAGE&patCrNo="+patCrNo+"&imageFileName="+imgFileNo ,
         
         success: function () {
	         
        	   $('#savedmsgid').text("Image Revoked!");
        	   $( "div.success" ).fadeIn( 300 ).delay( 1500 ).fadeOut( 400 );
        	 // $('#savedmsgid').show();
        	   if($("#imgrows12 > tr").length == 1){
        			 
        			 
        			 $(".prevdiv").hide();
        			 
        			 }
        		 
        			else{
        				$("#imgrows12 > tr").remove();
        				getPrevImgList();
        				
        			}
        	   
        	   
	        	
             }, 
	  error: function(data)
      {
	    
    	}
      });  

	    
	});
 $(document).on("click", "#imageExamTemp", function () {
 var PatCrNo=document.getElementsByName("patCrNo")[0].value;
	//alert(PatCrNo);
	var url = "/HISClinical/opd/opdTemplateTab.cnt?hmode=NEW&deskMenuId=3&isCallFromSinglePage=1&patCrNo="+PatCrNo;

	targetmodal = '#myModalIMGTEMP'; 
    $('#myIMGTEMP').load(url); 
});


</script>
<style>
.zoom:hover {
    transform: scale(2.5);
}	

@media (min-width: 992px) {
  .container-scroll > .row {
    overflow-x: auto;
    white-space: nowrap;
  }
  .container-scroll > .row > .col-md-2 {
    display: inline-block;
    float: none;
  }
}
.callout {
	padding: 5px 2px 2px 2px;
}

.success {
    color: #3c763d;
    background-color: #dff0d8;
    border-color: #d6e9c6;
    display: none;
}
.alert-box {
	padding: 15px;
    margin-bottom: 20px;
    border: 1px solid transparent;
    border-radius: 4px;  
}

/* .modal-body12 {
  width: 50%;
  float: right;
}

.modal-body2 {
  width: 50%;
  float: left;
} */
/* .container {    display: flex;    overflow-x: auto;}
.container img {    margin-right: 15px;} */
</style>
<body onload="isOnLoad(event)" >

<input type="hidden" id="editedimgSrc123" >



  
<!--   <div class="card card-primary card-outline collapsed-card" > 

  
      	<div class="card-header" id="imgBody">
      	<h3 class="card-title">Image Examination</h3>
      	
       <div class="card-tools">
				<button type="button" class="btn" onclick="">
					<i class="fas fa-sync-alt"></i>
				</button>
				<button type="button" class="btn" id="idButSave" onClick ="" disabled>
					<i class="fas fa-save"></i>
				</button>
				<button type="button" class="btn btn-tool" data-card-widget="collapse">
					<i class="fas fa-plus"></i>
				</button>
			</div>
       </div>   -->
        <div class="col-sm-10 col-xs-10 col-md-10 col-lg-10 col-xl-10" style="font-size:1.2em;font-weight:bold;" align="left">

Image Examination&nbsp; 
  </div>
   <div class="col-sm-2 col-xs-2 col-md-2 col-lg-2 col-xl-2">
       <button type="button" class="btn btn-info btn-sm" id="idButSave" onClick ="" align="right" disabled >
					SAVE
				</button>
				</div>
			
   <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12" ></div>
   <div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12 alert-box success" style=" display:none; " align="center" id="savedmsgid">
   <!--  <button type="button" class="close" data-dismiss="alert">&times;</button> -->
  			</div>
<!-- 
  <div class="card-body"> -->
  
  <div id ="prevdiv" class="prevdiv container-fluid" style="display:none;">
  <table class="table table-borderless table-condensed" id ="prev_img_exm_details" style="border:1px black;">
<tr style="background: linear-gradient(to bottom, #74a9d8 , white);" >
<th>Image Name</th>
<th>Image</th>
<th>Entry Date</th>
<!-- <th>Details</th> -->
<th>Remarks</th>
<th></th>

</tr>
<tbody id="imgrows12" class="imgrows12">
</tbody >
</table>

</div>

<!-- <div class="callout callout-info col">
<div class="container-fluid container-scroll"> -->
	<div id="img12" class="container  imageContainer">
	
				
	</div>
<!-- </div>
</div>
 -->	 
      <div class="col-md-2 col-sm-6 col-xs-12">	
      
      <button type="button" class="btn btn-info btn-sm"   data-toggle="modal" data-target="#myModalUpload"  onClick="submitToUpload(event);" >Upload</button>
      
      <button type="button" class="btn btn-info btn-sm"  data-toggle="modal" data-target="#myModal" style="align:left;" >ADD</button>
      </div>

	
	
      <!-- <div class="col-md-2 col-sm-6 col-xs-12"></div> -->
	
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12" ></div>
	<div class="col-sm-12 col-xs-12 col-md-12 col-lg-12 col-xl-12 editdiv" id="editdiv" style="display:none;">
	
	<table class="table table-borderless table-condensed" id ="editdiv_details" style="border:1px black;">
<tr style="background: linear-gradient(to bottom, #74a9d8 , white);" >
<th>Image Name</th>
<th>Image</th>
<th>Entry Date</th>
<th>Details</th>
<th>Remarks</th>
<th></th>
</tr>
<tbody id="imgrows" class="imgrows">
</tbody >
</table>


<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-xs-12"  id="btn_id1" style="padding-top:5px;" >
	<!-- <button type="button" class="btn  btn-xs btn-success save_btn" id="idButSave"  onClick =""  style="align:center;" > SAVE</button> -->
</div>	
</div>
	
<!-- </div>
</div> -->

						<!-- ADD Image MASTER MODAL -->
	<div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog modal-sm">
      <div class="modal-content">
        <div class="modal-header">
          
          <h5 class="modal-title">Add Other Images</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" id="otherImgList">
        
       <table class="table  table-rsponsive table-borderless" id ="otherImg" style="border:1px black;">
<tr style="background: linear-gradient(to bottom, #74a9d8 , white);" >
<th></th>
<th>Image Name</th>

</tr>
<tbody id="otherImgL">
</tbody >
</table>
        
          
        </div>
        <div class="modal-footer">
          <button type="button" style="align:center"class="btn btn-default btn-xs add" data-dismiss="modal">ADD</button>
        </div>
      </div>
      
    </div>
  </div>
  
  
  						<!-- END ADD MASTER MODAL -->
  						
  						<!-- EDITOR MODAL -->

	<div class="modal fade" id="myModal_IMG" style="margin-top:1px;" >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
      
        
         <div class="modal-header" style="height:25px;padding-top:1px;text-align:center;">
         
         <h4>Image Editor</h4>
          <button type="button" id="" class="close" data-dismiss="modal">&times;</button>
        </div>
        
     
        <div class="modal-body12"  style="height:350px;">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameIMG"> </iframe>
        </div>
        <!--  <div class="modal-body2"  style="height:350px;" id="myFrameIMG12">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameIMG12"> </iframe>
        </div> -->
        
        </div>
        
    </div>
  </div> 
	
						<!-- END EDITOR MODAL -->
						
							<!-- RE-EDITOR MODAL -->

	<div class="modal fade" id="myModal_RE-IMG" style="margin-top:1px;" >
    <div class="modal-dialog modal-xl">
      <div class="modal-content">
      
        
         <div class="modal-header" style="height:25px;padding-top:1px;text-align:center;">
         
        <b> Image Editor</b>
          <button type="button" id="" class="close" data-dismiss="modal">&times;</button>
        </div>
        
     
        <div class="modal-body"  style="height:350px;">
          <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameREIMG"> </iframe>
        </div>
        
        </div>
        
    </div>
  </div> 
	
						<!-- END RE-EDITOR MODAL -->
						
						<!-- UPLOAD MODAL -->	
	
	<div class="modal fade" id="myModalUpload" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          
          <h5 class="modal-title">Upload Images</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" id="myFrameUploadIMG">
        
        <!-- <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameUploadIMG"> </iframe> -->
          
        </div>
       <!--  <div class="modal-footer">
          <button type="button" style="align:center"class="btn btn-default btn-xs add" data-dismiss="modal">ADD</button>
        </div> -->
      </div>
      
    </div>
  </div>
  
  							<!-- END UPLOAD MODAL -->
  
  
  							<!-- IMG-TEMPLATE MODAL -->	
	
	<div class="modal fade" id="myModalIMGTEMP" role="dialog">
    <div class="modal-dialog modal-lg">
      <div class="modal-content">
        <div class="modal-header">
          
          <h5 class="modal-title">Image Template</h5>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        <div class="modal-body" id="myIMGTEMP">
        
        <!-- <iframe style="border: none; width: 100%; height: 100% !important; " id="myFrameUploadIMG"> </iframe> -->
          
        </div>
       <!--  <div class="modal-footer">
          <button type="button" style="align:center"class="btn btn-default btn-xs add" data-dismiss="modal">ADD</button>
        </div> -->
      </div>
      
    </div>
  </div>
  
  							<!-- END IMG-TEMPLATE -->
  
</body>	
<html:hidden name="EHRSection_ImageExamFB" property="departmentUnitCode"/>	
<input type="hidden" name="imgCdFrIndx" id="imgCdFrIndxId"/>


