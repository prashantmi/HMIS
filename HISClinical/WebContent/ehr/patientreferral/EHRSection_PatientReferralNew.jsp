<%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<%@page import="java.util.List"%>

<%@page import="java.util.Iterator"%>

<%@page import="hisglobal.utility.Entry"%>

<%@page import="hisglobal.utility.dynamicdesk.DynamicDeskConfig"%>
<%@page import="hisglobal.vo.DeskDetailVO"%>
<%@page import="opd.OpdConfig"%>
<%@page import="mrd.EMRConfig"%>
<%@page import="emr.dataentry.spp.presentation.fb.*"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/HIS/hisglobal/bbpublic/assets/elements/css/bootstrap.css" rel="stylesheet">
	<!--  <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> -->
	 
<his:javascript src="/ehr/js/sweetalert.min.js" />
</head>
<script>
$(document).ready(function(){
	var count1=0;
	var deptName;
	var deptCode;
	var referType;
	var referTypeCode;
	$('.referDtlAddRows').click(function(){
	    var referDept = document.getElementsByName("refferlPatientDept")[0];
		if(referDept.selectedIndex=="0")
		{
			deptName = "";
			deptCode = "";
		}
		else
		{
		deptName = referDept.options[referDept.selectedIndex].text;
		deptCode = document.getElementsByName("refferlPatientDept")[0].value;
		}
	    var referTypeCombo = document.getElementsByName("refferlType")[0];
		
	    referType = referTypeCombo.options[referTypeCombo.selectedIndex].text;
	    referTypeCode = document.getElementsByName("refferlType")[0].value;
		var referReason = document.getElementsByName('refferalReson')[0].value;
		var chkBoxId ={
	    		"strReferralDept" : deptName ,
	    		//"strReferralDeptCode" : deptCode,
	    		//"strRefType" : referType,
	    		//"refTypeCode" :referTypeCode,
	    		//"strRefReason" :referReason    		
	    };
		 console.log(chkBoxId);
		    var hiddenField =JSON.stringify(chkBoxId); 
		    console.log(JSON.parse(hiddenField));
		    var parsedata=JSON.parse(hiddenField);
		    
		    console.log('ParseData');
		    console.log(JSON.stringify(parsedata));

		    var tmp = 0;
		    $('#referDtlListTable tbody').find('tr').each(function(index){
		        if($(this).find('td').eq(0).children('input').val()==hiddenField.trim()) 
		        { tmp = 1; 
		          return false;  }
		      });

		    $('#PrevReferDtlListTable tbody').find('tr').each(function(index){
			    //alert($(this).find('td').eq(1).children('input').val());
			   // alert(deptName);
		        if($(this).find('td').eq(1).children('input').val()==deptName) 
		        { tmp = 2; 
		          return false;  }
		      });
		      
		    if(tmp==1)
		    { 
		       swal(deptName+":Already Added!!");
		       document.getElementsByName("refferlPatientDept")[0].value ="0#0#0#0#0#0#0#0";
		       document.getElementsByName("refferalReson")[0].value = "";
		       //$(this).parent().parent().parent().find('input[name="refferlPatientDept"]').val('0');
		      /*   $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val('');
		        $(this).parent().parent().find('select[name="allergiesSensitivityCode"]').val('-1');
		        $(this).parent().parent().parent().find('input[name="allergiesDuration"]').val('');
		        $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val('');
		        $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val('');
		        $(this).parent().parent().parent().find('#allergiesDtlRemarksId').val(''); */
		      return false;
		    } 

		    if(tmp==2)
		    { 
		       swal(deptName+":Already Referrred!!");
		       document.getElementsByName("refferlPatientDept")[0].value ="0#0#0#0#0#0#0#0";
		       document.getElementsByName("refferalReson")[0].value = "";
		      /*   $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val('');
		        $(this).parent().parent().find('select[name="allergiesSensitivityCode"]').val('-1');
		        $(this).parent().parent().parent().find('input[name="allergiesDuration"]').val('');
		        $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val('');
		        $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val('');
		        $(this).parent().parent().parent().find('#allergiesDtlRemarksId').val(''); */
		      return false;
		    } 
		    if(deptName.trim()!=''){
		  	  count1++;
		  	  str='<tr>'+ 
		          '<td><input type="checkbox" type="hidden" class="referChkAll" id="referDtlChkId'+count1+'" checked name="referDtlChkId" value=""></td>'+
		          '<td>'+deptName+'</td>'+ '<td><input type="hidden" class="" id="referdeptCode"  name="referdeptCode" value='+deptCode+'></td>'+'<td><input name="referdeptName" value='+deptName+' type="hidden"></td>'+
		          '<td>'+referType+'</td>'+'<td><input type="hidden" class="" id="referTypeCode"  name="referTypeCode" value='+referTypeCode+'></td>'+'<td><input name="referTypeName" value='+referType+' type="hidden"></td>'+
		          '<td>'+referReason+'</td>'+'<td><input name="referReason" type="hidden" value='+referReason+'></td>';
		  	  
		         /*  if(referReason!=''){
		            str+='<td><a class="allergiesDtlInstructionsModalBtn" style="color: #109f1c" allergyInstructions="'+referReason+'" onclick="$(\'#allergiesDtlInstructionsModal .modal-body p\').text($(this).attr(\'allergyInstructions\'));$(\'#allergiesDtlInstructionsModal\').modal(\'show\');">'+remarks.substring(0, 4)+'..'+'</a></td>';
		          }
		          else{
		            str+='<td></td>';
		          } */

		          str+='<td><button class="btn btn-xs btn-danger allergiesDtlRemoveRow" type="button" onclick="$(this).parent().parent().remove();" id="removeBtnId2" style="background-color: white;border: 0px;"><span class="glyphicon glyphicon-trash" style="color: red;"></span></button></td>'+
		          '</tr>';
		        //$(this).parent().parent().parent().find('.table').children('tbody').append(str);
		        $('#refer').append(str);

		        document.getElementsByName("refferlPatientDept")[0].value ="0#0#0#0#0#0#0#0";
			    document.getElementsByName("refferalReson")[0].value = "";
		         /*  $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR4"]').val('');
		          $(this).parent().parent().find('select[name="allergiesSensitivityCode"]').val('-1');
		          $(this).parent().parent().parent().find('input[name="allergiesDuration"]').val('');
		          $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR5"]').val('');
		          $(this).parent().parent().parent().find('input[name="txt-snomed-ct-search_VR6"]').val('');
		          $(this).parent().parent().parent().find('#allergiesDtlRemarksId').val('');
		           */
		          var temp='#referDtlChkId'+count1;
		          $(temp).val(hiddenField);
		    }
		    else{
		      swal("Please select Refer Department.");
		    }   
	});
});

function savePatientReferralData()
{ 
	var currLen = $('#referDtlListTable tbody').find('tr').length;
	//var form = $('#idDivPageSPPatEncounterTile input, #idDivPageRoV input, #idDivPageRoV select');
	    var deptName;
        var referDept = document.getElementsByName("refferlPatientDept")[0];
		if(referDept.selectedIndex=="0")
		{
			deptName = "";
			//deptCode = "";
		}
		else
		{
		deptName = referDept.options[referDept.selectedIndex].text;
		}
		//alert(deptName);
       if(deptName.trim()!='' || currLen > 0)
           {
            
    	   $('.referDtlAddRows').click();
           }
       else
           {
           swal("Please select refer department");
             return false;
           }
      		
	var form = $('#referDtlListTable input');
	//alert(form.serialize());
		$.ajax({
			   url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_REFERRAL.cnt?hmode=PATCLINICALDOC_PAT_REFERRAL_SAVE"),  
			    type : 'POST',
			    data : form.serialize(), 
			    async: false,
				success: function(data) {

					//alert(data);
					populateReferralRow(data);   
					swal("Patient Referral", "Data Saved Successfully!", "success");
			    		
			      },
			      error: function(data)
			      {
			    	  swal('request failed :');
			    	}
		}); 

		
}


$(document).ready(function(){
	//alert("hi");

	<%
	
	String referJSON = (String)request.getSession().getAttribute("previouslyReferredDeptListJSON");
	System.out.println("referJSON"+referJSON);
	%>
	//var referred = referJSON;
	var referred = '<%=(String)request.getSession().getAttribute("previouslyReferredDeptListJSON")%>';
    //alert(referred);
    referred= JSON.parse(referred);
   //alert(referred.length);
   if(referred != 'undefined' && referred!=null && referred!='' && referred.length>0)
	  {
    for (var i = 0; i < referred.length; i++) {
 	   var referredDeptName = referred[i].referdeptName;
       //alert(referredDeptName);
       var referredDeptCode = referred[i].referdeptCode;
       var referredTypeName = referred[i].referTypeName;
       var referReason = referred[i].referReason;
       var serialNo = referred[i].slNo;
       //alert(serialNo);
       if(typeof(referReason) != 'undefined' && referReason != null)
           {
    	   referReason = referred[i].referReason;
           }
       else
           {
    	   referReason = "";
           }
       var str= str+'<tr>'+ 
      
       '<td>'+referredDeptName+'</td>'+ '<td><input type="hidden" class="" id="prevreferdeptCode"  name="prevreferdeptCode" value='+referredDeptName+'></td>'+'<td><input name="prevreferdeptName" value='+referredDeptName+' type="hidden"></td>'+
       '<td>'+referredTypeName+'</td>'+'<td><input type="hidden" class="" id="prevreferTypeCode"  name="prevreferTypeCode" value='+referredTypeName+'></td>'+'<td><input name="prevreferTypeName" value='+referredTypeName+' type="hidden"></td>'+
       '<td>'+referReason+'</td>'+'<td><input name="prevreferReason" type="hidden" value='+referReason+' ></td>'+
       '<td><button class="btn btn-sm btn-info" type="button" onclick=" deleteReferRow('+serialNo+',this);" id="removeBtnId2" >Revoke</button></td>'+
       '</tr>';
	  

       //str+=;

 } 
   // $(this).parent().parent().parent().find('.table').children('tbody').append(str);
   
   document.getElementById("prevReferralData").style.display = "block";
   $('#Prevrefer').append(str); 

   
	  }
});

function deleteReferRow(slno,obj)
{
   //alert(slno);
   swal({
       title: "Are you sure?",
       text: "You will not be able to recover it again!",
       icon: "warning",
       buttons: [
         'No, cancel it!',
         'Yes, I am sure!'
       ],
       dangerMode: true,
     }).then(function(isConfirm) {
       if (isConfirm) {
         swal({
           title: 'Referral Revoked!',
           text: '',
           icon: 'success'
         }).then(function() {

        	 $.ajax({
     			url: createFHashAjaxQuery("/HISClinical/emr/ehrComposition_PAT_REFERRAL.cnt?hmode=DELETE_PREV_PAT_REFERRAL_AJAX&slno="+slno+""),  
     		    type : 'GET',
     		    datatype: "json",
     		    async : false,
     			success: function(data)
     			{
     				//hideOperationRow(idx);
     				//obj.parent().parent().remove();
     				var row = obj.parentNode.parentNode;
     				  row.parentNode.removeChild(row);
     				
     		      },
     			
     		      error: function(data)
     		      {
     		    	    swal('request failed :');
     		    	}

     			});
             

         });
       } else {
         swal("Cancelled");
       }
     });
}


function populateReferralRow(data)
{
	var referRows = data;
	referRows1 = JSON.parse(referRows);
	//alert(referRows1);
	//alert(referRows1.length);
	//return false;
	if(referRows != null)
	  {
		 $('#PrevReferDtlListTable tbody').find('tr').remove();
          
		 
		
		 for (var i = 0; i < referRows1.length; i++) {
		 	   var referredDeptName = referRows1[i].referdeptName;
		       //alert(referredDeptName);
		       //return false;
		       var referredDeptCode = referRows1[i].referdeptCode;
		       var referredTypeName = referRows1[i].referTypeName;
		       var referReason = referRows1[i].referReason;
		       var serialNo = referRows1[i].slNo;
		       //alert(serialNo);
		       if(typeof(referReason) != 'undefined' && referReason != null)
           {
    	   referReason = referRows1[i].referReason;
           }
           else
           {
    	   referReason = "";
           }
		       var str= str+'<tr>'+ 
		      
		       '<td>'+referredDeptName+'</td>'+ '<td><input type="hidden" class="" id="prevreferdeptCode"  name="prevreferdeptCode" value='+referredDeptName+'></td>'+'<td><input name="prevreferdeptName" value='+referredDeptName+' type="hidden"></td>'+
		       '<td>'+referredTypeName+'</td>'+'<td><input type="hidden" class="" id="prevreferTypeCode"  name="prevreferTypeCode" value='+referredTypeName+'></td>'+'<td><input name="prevreferTypeName" value='+referredTypeName+' type="hidden"></td>'+
		       '<td>'+referReason+'</td>'+'<td><input name="prevreferReason" type="hidden" value='+referReason+'></td>'+
		       '<td><button class="btn btn-sm btn-info" type="button" onclick=" deleteReferRow('+serialNo+',this);" id="removeBtnId2" >Revoke</button></td>'+
		       '</tr>';
			  

		       //str+=;

		 } 
		   // $(this).parent().parent().parent().find('.table').children('tbody').append(str);
		   
		   
		   $('#referDtlListTable tbody').find('tr').remove();
		   
		   document.getElementById("prevReferralData").style.display = "block";
		   $('#Prevrefer').append(str); 
				  
	  }
	
}
</script>
<body>
<div class="table">
	<div class="row">
		<div class="col-sm-6" width="15%" style="font-size:1.2em;font-weight:bold;" align="left">
			Patient Referral&nbsp;
		</div>
		<div class="col-sm-6" width="15%" style="font-size:1.2em;font-weight:bold;" align="right">
			<button class="btn btn-sm btn-success" id="" type="button" onClick="savePatientReferralData();">Save</button>
		</div>
	</div>
	</div>
	<div class="prescriptionTile referralDtl" >
                   <div class="row">
                   <div class="col-md-1">
                         <p><label><b>Refer To:</b></label></p>  
                        </div>
                        
                       
                          <div class="col-md-2" >
                              
                           
                          <select class="form-control" name="refferlPatientDept" autocomplete="off">
                             <option value="0#0#0#0#0#0#0#0">Select Department</option> 
                        	<%
                        	
                        	List referDeptItems = (List)request.getSession().getAttribute("referDepartmentList");
		                      if(referDeptItems!=null && referDeptItems.size()>0){
                        	
                        		Iterator<Entry> itera =referDeptItems.iterator();
								
									while(itera.hasNext()){
									Entry obj=itera.next();
									
                        		 String reffDeptCode=obj.getValue();
                        		 String reffDeptName=obj.getLabel();
							   
							   %>
		            
			                  <option value="<%=reffDeptCode%>"><%=reffDeptName %></option> 
			                  <%
			                  }
		                      }else{
		                    	  
		                    	  %>
		      		            
				                  <option value="0#0#0#0#0#0#0#0">Select Department</option> 
				                  <%
		                    	  
		                      }
	                 			 %> 
                        </select>  
                        
                          </div>   
                         
                       <div class="col-md-1">
                         <p><label><b>Type:</b></label></p>  
                        </div>
                        
                        <div class="col-md-2">
                         <select class="form-control" name="refferlType" autocomplete="off">
                             <option value="1">Routine</option> 
                        	  <option value="2">Emergency</option>
                        </select>  
                        </div>
                        
                          <div class="col-md-5" >
                           <input type="text" class="form-control" onkeypress="" name="refferalReson"  id="refferalResonId" value="" placeholder="Enter Refferal Reason">
                          </div>
                          
                        <div class = "col-md-1">
                           
                        
                          <button class="btn btn-sm btn-info referDtlAddRows" type="button">Add</button>
                        
                        </div>
                   
                   </div>
                  <div class="table-responsive">
                          <table id="referDtlListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;margin-top: 15px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb /* #acd6ff */, white);">
                               
                            </thead>
                            <tbody id = "refer">
                            
                            </tbody> 
                        </table>
                      </div>
                      
                      <div id ="prevReferralData" style="display:none;">
                      <div class ="row" style="margin-top: 15px;">
                      <div class="col-md-12" align="left">
                         <p><label><b>Previous Referral Details:</b></label></p>  
                        </div>
                        </div>
                      <div class="table-responsive">
                          <table id="PrevReferDtlListTable" class="table table-condensed table-hover" style="margin-bottom: 0px;"> 
                            <thead>
                              <tr style="background: linear-gradient(to bottom, #dbdbdb, white);">
                               
                            </thead>
                            <tbody id = "Prevrefer">
                            
                            </tbody> 
                        </table>
                        </div>
                      </div>
                      
                  
                  </div>
  
<html:hidden name="EHRSection_PatientReferralFB" property="patCrNo"/>
<html:hidden name="EHRSection_PatientReferralFB" property="episodeCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="episodeVisitNo"/>
<html:hidden name="EHRSection_PatientReferralFB" property="departmentUnitCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="roomCode"/>
<html:hidden name="EHRSection_PatientReferralFB" property="ftlValueForPatReferral"/>
</body>