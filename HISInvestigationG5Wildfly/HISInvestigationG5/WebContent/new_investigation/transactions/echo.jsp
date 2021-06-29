<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="new_investigation.vo.Inv_RequisitionRaisingPatientVO"%>
<%@page import="new_investigation.vo.Inv_EpisodeVO"%>
<%@page import="new_investigation.vo.Inv_PatientAdmissionDtlVO"%>

<%@page import="new_investigation.vo.Inv_ictc_VO"%>
<%@page import="new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB"%>
<%@page import="new_investigation.vo.LabTestVO"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@ taglib uri="/WEB-INF/AppointmentTools.tld" prefix="appt"%>
<%@page import="new_investigation.InvestigationConfig"%>
<%@page import="hisglobal.presentation.WebUTIL"%>

<%@page import="java.util.*"%>
<his:javascript src="/hisglobal/js/calendar.js" />
<%@page import="hisglobal.hisconfig.Config"%>

<%@page import="hisglobal.tools.tag.PaginationFB"%>
<%@page import="hisglobal.tools.tag.PaginationTag"%>



<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Echo Template</title>
<his:css src="/new_investigation/media/echo/bootstrap.min.css" />
<his:javascript src="/new_investigation/media/bootstrap4/echo/jquery.min.js" />
<his:javascript src="/new_investigation/media/bootstrap4/echo/bootstrap.min.js" />
<his:css src="/new_investigation/media/echo/glymph.css" />

<his:css src="/new_investigation/media/echo/bootstrap-multiselect.css" />
<his:javascript src="/new_investigation/media/bootstrap4//echo/bootstrap-multiselect.js" />

<his:javascript src="/new_investigation/js/specialCharacterRemover.js" />

<style>
.container img {
  width: 100%;
}

.container {
  width: 40%;
  background: #777;
  margin: 0 auto;
}

h2 {
  text-align: center;
}
</style>

<style>

@media print {

  @page {                
    size: A4;
    margin: 0mm;
  }

  html, body {
    width: 1024px;
  }

  body {
    margin: 0 auto;
    line-height: 1em;
    word-spacing:1px;
    letter-spacing:0.2px;
    font: 14px "Times New Roman", Times, serif;
    background:white;
    color:black;
    width: 100%;
    float: none;
  }

  /* avoid page-breaks inside a listingContainer*/
  .listingContainer{
    page-break-inside: avoid;
  }

  h1 {
    font: 28px "Times New Roman", Times, serif;
  }

  h2 {
    font: 24px "Times New Roman", Times, serif;
  }

  h3 {
    font: 20px "Times New Roman", Times, serif;
  }

  /* Improve colour contrast of links */
  a:link, a:visited {
    color: #781351
  }

  /* URL */
  a:link, a:visited {
    background: transparent;
    color:#333;
    text-decoration:none;
  }

  a[href]:after {
    content: "" !important;
  }

  a[href^="http://"] {
    color:#000;
  }

  #header {
    height:75px;
    font-size: 24pt;
    color:black
  }
}


.no-border {
    border: 0;
    box-shadow: none; /* You may want to include this as bootstrap applies these styles too */
}

body {
  padding-top: 20px;
  padding-bottom: 20px;
    padding-right: 10px;
  padding-left: 10px;

  background-color: #ddd;
  
  

}
		
		  $(document).ready(function() {
            $('#t5').multiselect({
                buttonWidth: '100%'
            });
        //caret is in the middle, switch it to right
        $(".caret").css('float', 'right');
        $(".caret").css('margin', '8px 0');
        });
		
		
</style>
<script type="text/javascript">
        
		
	

$(window).bind("load", function() {
   // code here
  // alert(document.forms[0]);
   document.forms[0].setAttribute("class", "form-horizontal");
  // document.getElementsByTagName("H1")[0].setAttribute("class", "democlass"); 
   //alert("ss");
   
   var data="";
 
// data="t1$$$select$$$One@@@t2$$$select$$$Two@@@t5$$$select$$$Thickened$$$Calcification$$$Doming@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1";
  //data="t1$$$select$$$One@@@t2$$$select$$$Two@@@t5$$$select@@@t3$$$checkbox$$$1@@@t4$$$checkbox$$$0"; 
 //data="t1$$$select$$$-1@@@t2$$$select$$$-1@@@t5$$$select@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$0";
 //data="t1$$$select$$$-1@@@t2$$$select$$$-1@@@t5$$$select$$$Thickened@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1";
 
 //multi
//data="t1$$$select$$$One@@@t2$$$select$$$Three@@@t5$$$select$$$Calcification$$$Doming@@@t8$$$select@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1";
//data="t1$$$select$$$One@@@t2$$$select$$$Three@@@t5$$$select$$$Calcification$$$Doming@@@t8$$$select$$$Flutter@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1@@@t6$$$checkbox$$$0@@@t7$$$checkbox$$$1";
 //data="t1$$$select$$$One@@@t2$$$select$$$Three@@@t5$$$select@@@t8$$$select$$$Flutter@@@t3$$$checkbox$$$1@@@t4$$$checkbox$$$0@@@t6$$$checkbox$$$0@@@t7$$$checkbox$$$1";
 //data="t1$$$select$$$One@@@t2$$$select$$$Three@@@t5$$$select@@@t8$$$select$$$Flutter@@@t3$$$checkbox$$$1@@@t4$$$checkbox$$$0@@@t6$$$checkbox$$$0@@@t7$$$checkbox$$$1@@@t9$$$textbox$$$dcdcdcd@@@t10$$$textbox$$$cdcd@@@t11$$$textbox$$$@@@t12$$$textbox$$$@@@t13$$$textbox$$$@@@t14$$$textbox$$$cdcdc";
 //alert("echodata"+echodata);

 //alert("hello"+opener.document.getElementsByName('echovar')[0].value);
     
// data="t1$$$select$$$Two@@@t2$$$select$$$Three@@@t5$$$select$$$Thickened$$$Calcification@@@t8$$$select$$$Doming$$$Flutter@@@t15$$$select$$$One@@@t16$$$select$$$One@@@t25$$$select$$$Two@@@t35$$$select$$$Prolapse$$$SAM@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1@@@t6$$$checkbox$$$0@@@t7$$$checkbox$$$1@@@t9$$$textbox$$$dcdcdcd@@@t10$$$textbox$$$cdcd@@@t11$$$textbox$$$@@@t12$$$textbox$$$@@@t13$$$textbox$$$dcdd@@@t14$$$textbox$$$cdcdc@@@t14$$$textbox$$$@@@t17$$$textbox$$$dfdvfvf@@@t18$$$textbox$$$@@@t19$$$textbox$$$vfvf@@@t20$$$textbox$$$vfvfv@@@t21$$$textbox$$$@@@t22$$$textbox$$$@@@t23$$$textbox$$$fvfvf@@@t24$$$textbox$$$@@@t26$$$textbox$$$vfvfv@@@t27$$$textbox$$$@@@t28$$$textbox$$$@@@t29$$$textbox$$$@@@t30$$$textbox$$$@@@t31$$$textbox$$$@@@t32$$$textbox$$$vfvfvf@@@t33$$$checkbox$$$0@@@t34$$$checkbox$$$1";
//data="t1$$$select$$$Two@@@t2$$$select$$$Three@@@t5$$$select$$$Thickened$$$Calcification@@@t8$$$select$$$Doming$$$Flutter@@@t16$$$select$$$One@@@t17$$$select@@@t26$$$select@@@t36$$$select@@@t37$$$select$$$-1@@@t40$$$select$$$-1@@@t42$$$select$$$-1@@@t45$$$select@@@t3$$$checkbox$$$0@@@t4$$$checkbox$$$1@@@t6$$$checkbox$$$0@@@t7$$$checkbox$$$1@@@t9$$$textbox$$$dcdcdcd@@@t10$$$textbox$$$cdcd@@@t11$$$textbox$$$chanks@@@t12$$$textbox$$$gupta@@@t13$$$textbox$$$dcdd@@@t14$$$textbox$$$cdcdc@@@t15$$$textbox$$$One@@@t18$$$textbox$$$@@@t19$$$textbox$$$vfvf@@@t20$$$textbox$$$vfvfv@@@t21$$$textbox$$$@@@t22$$$textbox$$$@@@t23$$$textbox$$$fvfvf@@@t24$$$textbox$$$@@@t25$$$textbox$$$Two@@@t27$$$textbox$$$@@@t28$$$textbox$$$@@@t29$$$textbox$$$@@@t30$$$textbox$$$@@@t31$$$textbox$$$@@@t32$$$textbox$$$vfvfvf@@@t33$$$textbox$$$@@@t34$$$checkbox$$$1@@@t35$$$checkbox$$$0@@@t38$$$textbox$$$@@@t39$$$textbox$$$@@@t41$$$textbox$$$@@@t43$$$checkbox$$$0@@@t44$$$checkbox$$$0";
var gh="true";
if(opener.document.getElementsByName('echovar')[0].value!="")
	{

         var dataw=opener.document.getElementsByName('echovar')[0].value;
    //     alert("inside"+dataw);
         var moredata=dataw.split("***");

       //  alert("moredata.length"+moredata.length);

      for(var p=0;p<moredata.length;p++)
          {	

          
         
        var newmoredata=moredata[p].split("^^^");
        var req=newmoredata[0];
        var final=newmoredata[1];

    //  alert("req"+req);
    //  alert("reqdno"+reqdno);
      
        if(req==reqdno)
            {
        //    alert("inside match");
            
        	data=newmoredata[1];
        	gh="false";
        
            }
          }
     	
	}


if(gh=="true" && echodata!="")
	{
	//alert("not in ");
	data=echodata;
	}
else
	{
	}

//alert("datadata"+data);

 if(data!="")
 {

data=data.replace(/@ampersand@/g , "&");
data=data.replace(/@percent@/g , "%");

     var noofdata=data.split("@@@");


      for(var f=0;f<noofdata.length;f++)
	  {
		  var data=noofdata[f];
		  var maindata=data.split("$$$");
		  
		  if(data.includes("select"))
		  {
			  if(maindata.length==3)
			  {
				  if(document.getElementById(maindata[0]).multiple==false)
				  {
					  document.getElementById(maindata[0]).value=maindata[2];
				  }else
				  {
					  
					  
        var valArr =maindata[2];
        	var tid="#"+maindata[0];
            $(tid).multiselect('select', valArr);
        
				  }
			  
			  }
		  
		   if(maindata.length<3)
			  {
				  
				  document.getElementById(maindata[0]).value="-1";
			  
			  }
			  
		

            if(maindata.length>3)
			  {
				  
				 var data1="";
				  for(var l=2;l<maindata.length;l++)
				  {
					  data1=data1+maindata[l]+",";
				  }
			
           
        var valArr = data1.split(",");
        var i = 0, size = valArr.length;
        for (i; i < size; i++) {
			var tid="#"+maindata[0];
            $(tid).multiselect('select', valArr[i]);
        }
		
			  }
			  
		  }


		  
		  
		   if(data.includes("checkbox"))
		  {
		  
		      if(maindata.length==3)
			  {
				  if(maindata[2]==1)
				  {
				  document.getElementById(maindata[0]).checked=true;
				  call1(document.getElementById(maindata[0]));
				  }
				  if(maindata[2]==0)
				  {
				  document.getElementById(maindata[0]).checked=false;
				  //call1(document.getElementById(maindata[0]));
				  }
			  
			  }
		  
		  }
		  
		     if(data.includes("textbox"))
		  {
		  
		      if(maindata.length==3)
			  {
				  if(maindata[2]!="")
				  {
				  document.getElementById(maindata[0]).value=maindata[2];
				  
				  }
			  
			  }
		  
		  }



		     if(data.includes("textarea"))
			  {
			  
			      if(maindata.length==3)
				  {
					  if(maindata[2]!="")
					  {
					  document.getElementById(maindata[0]).value=maindata[2];
					  
					  }
				  
				  }
			  
			  }


			  
	  }

       

 
	 
 }	 
   
 
});




		$(function () {
   $('.sev_check').change(function(e) {
       e.preventDefault();
       $('.sev_check').not(this).prop('checked', false);
       $(this).prop('checked', true);
   });
});


	$(function () {
   $('.sev_check2').change(function(e) {
       e.preventDefault();
       $('.sev_check2').not(this).prop('checked', false);
       $(this).prop('checked', true);
   });
});

$(function () {
   $('.sev_check3').change(function(e) {
       e.preventDefault();
       $('.sev_check3').not(this).prop('checked', false);
       $(this).prop('checked', true);
   });
});


		$(function () {
   $('.sev_check1').change(function(e) {
       e.preventDefault();
       $('.sev_check1').not(this).prop('checked', false);
       $(this).prop('checked', true);
   });
});

		$(document).ready(function() {
            $('.multiselectt').multiselect({
           
            buttonWidth: 800,
            enableFiltering: true
        });
        });
		
		function call1(obj)
		{
	//	alert(obj.checked);
		var id=obj.id;
		var idd=obj.name;
		var id1="."+idd;
		var id2="."+idd+" option:selected";
		
		id="show1"+idd;
		//alert("id"+id);
		if(obj.value==1)
		{
            document.getElementById(id).style.display="";
           	
	    }
		else
		{
		
	$(id2).each(function() {
        $(this).prop('selected', false);
    })
    $(id1).multiselect('refresh');
	
			  
		            document.getElementById(id).style.display="none";

		}
		
		}

		function ValidateSpecialChar(){
			
			/* $("input:text") */
			/* $(".textInput"); */
			var inputTypeText=$("input:text");
			var inputTypeTextArea = document.getElementsByTagName('textarea');
			
			for(var i=0; i<inputTypeText.length; i++){
				var checkReservedChar=checkReservedCahracters(inputTypeText[i].value+" ");
				
				if(checkReservedChar==true) {
					
					return false;
				}
				else{ }
			}
			
			for(var i=0; i<inputTypeTextArea.length; i++){
				var checkReservedChar=checkReservedCahracters(inputTypeTextArea[i].value+" ");
				
				if(checkReservedChar==true) {
					
					return false;
				}
				else{ }
			}
			
			return true;
		}
		
		
		function printt(obj)
		{
			if(ValidateSpecialChar()){
				
			}
			else{
			
				alert("Document contains one of the reserved characters '@!#$^|\\`' which is not allowed. Please remove them.");
    				return null;
			}
			
		        var data="";
		// alert(document.getElementsByTagName('select').length);
		 
		 for(var k=0;k<document.getElementsByTagName('select').length;k++)
		 {
		 
		 
		 var x= document.getElementsByTagName('select');
		var id=x[k].id;
		var cl=x[k].className;
		cl=cl+" no-border";
		  document.getElementById(id).setAttribute("class", cl);

		  if(data=="")
            data=data+""+id+"$$$select";
			else
			data=data+"@@@"+id+"$$$select";
      //    alert("id"+id);
		for (var kk=0;kk<x[k].length ;kk++)
	        	  {
                      
				//&& document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[0].value!="-1"
	        	 if(x[k].options[kk].selected==true)
	        	  {  //alert(document.getElementById(splitTheCheckBoxId+'templateValue').getElementsByTagName("select")[j].options[kk].value)
	        	   	 var  multiValue=x[k].options[kk].value;
	        	    data=data+"$$$"+multiValue;
				
	        	  }
				  else
				  {
				      //data=data+"$$$"+"-1";
				  }
				  
	        	  }
				
				  
		
		 }
		 
		  for(var k=0;k<document.getElementsByTagName('input').length;k++)
		 {
		 
		 		 var x= document.getElementsByTagName('input');

		                       	  var typ=x[k].type;
								  var id=x[k].id;
								  
								  
                              // alert("typ"+typ);
							   if(id!="" && typ=="checkbox" 	)
		                               {
										   
										   var cl=x[k].className;
		cl=cl+" no-border";
		  document.getElementById(id).setAttribute("class", cl);
		  
									//   alert("typ1"+typ);
									   		
                                         var chkvalue="0";
									//	 alert("id:-"+id);
										if( document.getElementById(id)!=null )
										 {
										 if( document.getElementById(id).checked)
										 { chkvalue="1";}
										 else
										 {
										 chkvalue="0";
										 }
									   
									   if(data=="")
                              data=data+""+id+"$$$checkbox$$$"+chkvalue;
		                    	else
		                  	data=data+"@@@"+id+"$$$checkbox$$$"+chkvalue;
				        	    }

									   
									   }
									   
									   
									   	   
									    if(id!="" && typ=="text" 	)
		                               {
										   
										   var cl=x[k].className;
		cl=cl+" no-border";
		  document.getElementById(id).setAttribute("class", cl);
		  
									//   alert("typ1"+typ);
									   		
                                         var chkvalue=x[k].value;
									//	 alert("id:-"+id);
									   
									   if(data=="")
                              data=data+""+id+"$$$textbox$$$"+chkvalue;
		                    	else
		                  	data=data+"@@@"+id+"$$$textbox$$$"+chkvalue;
				        	    
								}
								
									   
		 }
	//	   alert("data:->"+data);
		   
		   
		     for(var k=0;k<document.getElementsByTagName('textarea').length;k++)
		 {
		 
		 		 var x= document.getElementsByTagName('textarea');

		                       	  var typ=x[k].type;
								  var id=x[k].id;
								  
								  
                             //  alert("typ"+typ);
							 
									   
									   
									   	   
									    if(id!="" && typ=="textarea" 	)
		                               {
										   
										   var cl=x[k].className;
		cl=cl+" no-border";
		  document.getElementById(id).setAttribute("class", cl);
		  
									//   alert("typ1"+typ);
									   		
                                         var chkvalue=x[k].value;
									//	 alert("id:-"+id);
									   
									   if(data=="")
                              data=data+""+id+"$$$textarea$$$"+chkvalue;
		                    	else
		                  	data=data+"@@@"+id+"$$$textarea$$$"+chkvalue;
				        	    
								}
								
									   
		 }
		   
		   var finaltbl=data.split("@@@");
		   
		   for(var g=0;g<finaltbl.length;g++)
		   {
		   
		   var getid=finaltbl[g].split("$$$");
		   
		   
		   
		   
		   
		   }
		   
		   
		               var oldPage = document.body.innerHTML;
                        //alert(oldPage);
                         
                       var arrays=[];

                       if(opener.document.getElementsByName('echovar')[0].value=="")
                           {
                    	   opener.document.getElementsByName('echovar')[0].value+=reqdno+"^^^"+data+"***";
                           }
                       else
                           {
                    	var val= opener.document.getElementsByName('echovar')[0].value;
                    	opener.document.getElementsByName('echovar')[0].value="";
                                   arrays=val.split("***");
                                     var fl="false";
                                 for(var g=0;g<arrays.length;g++)
                                     {
                                           if(arrays[g].includes(reqdno))
                                               {
                                        	   arrays[g]=reqdno+"^^^"+data+"***";
                                        	   opener.document.getElementsByName('echovar')[0].value+=reqdno+"^^^"+data+"***";
                                        	   fl="true";
                                               }
                                           else if(arrays[g]!="")
                                               {
                                         	   opener.document.getElementsByName('echovar')[0].value+=arrays[g]+"***";
                                               }
                                          
                                                
                                     }

                                 if(fl=="false")
                                     {
                              	   opener.document.getElementsByName('echovar')[0].value+=reqdno+"^^^"+data+"***";
                                   
                                     }
                    	
                           }
                   
                              //  alert("final val"+opener.document.getElementsByName('echovar')[0].value);
                        
                        self.close();
		    //window.print();

		}
		
		
    </script>

</head>
<!-- Updated by PrashantMi -->
<body>
<form  id="myForm3" >
                      <script>
             var reqdno='<%= (String)request.getParameter("requisitionDNo") %>';
             var echodata='<%= (String)request.getParameter("echodata") %>';
             
             </script>
           <div align='center'>  <h1 ><i><b>Echocardiography</b></i></h1></div>
           <!--   <div class="container ">
  <img src="/HISInvestigationG5/hisglobal/images/doctor.jpg" alt="Snow" style="width:200;">

</div> -->
         <!--   <div width="20%" class="container">
  <img src="/HISInvestigationG5/hisglobal/images/doctor.jpg" />
</div> -->
<!-- <div class="col-sm-3">
  <img src="/HISInvestigationG5/hisglobal/images/doctor.jpg" style="width: auto; height: 195px;">
</div> -->
</div>
	<div class="form-group " > <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss">Procedure Done BY</label>
		<div class="col-sm-10 ss ">
			<select style="" class="form-control  custom-select ss " id="t1">
  <option selected value="-1"> Select Value</option>
  <option value="Dr. Shaheen Ahmad (DM)">Dr. Shaheen Ahmad (DM)</option>
  <option value="Senior Resident">Senior Resident</option>
  <option value="Faculty">Faculty</option>
</select>
		</div>  
	</div>

	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2">Echo Window</label>
		<div class="col-sm-10">
			<select class="form-control  custom-select" id="t2">
  <option selected value="-1"> Select Value</option>
  <option value="Adequate">Adequate</option>
  <option value="Good">Good</option>
  <option value="Poor">Poor</option> 
   <option value="Very Poor">Very Poor</option>
  
</select>
		</div>  
	</div>
	
	<div class="alert alert-dark">
    <strong>Morphology</strong>
  </div>
	
	<div class="form-group "> <!-- Frequency Field -->
		<label class="control-label col-sm-2 ">AML</label>
		<div class="col-sm-5">
 <label class="custom-control-label" for="defaultUnchecked">Normal</label>	
	<input type="checkbox" name="AML" class="custom-control-input sev_check" id="t3" checked onclick="call1(this)" value="0">
   
</div>
		<div class="col-sm-2">
 <label class="custom-control-label" for="defaultUnchecked">Abnormal</label>	
	<input type="checkbox" name="AML" class="custom-control-input sev_check" id="t4" onclick="call1(this)" value="1">
   
</div>
		</div>
	</div>							
	 
	 <div class="form-group " style="display:none" id="show1AML"> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-10">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t5" multiple="multiple">
            <option value="Thickened">Thickened</option>
            <option value="Calcification">Calcification</option>
            <option value="Doming">Doming</option>
            <option value="Flutter" >Flutter</option>
            <option value="Vegetation">Vegetation</option>
            <option value="Prolapse">Prolapse</option>
            <option value="SAM">SAM</option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div>        
	
	
	<div class="form-group "> <!-- Frequency Field -->
		<label class="control-label col-sm-2 ">PML</label>
		<div class="col-sm-5">
 <label class="custom-control-label" for="defaultUnchecked">Normal</label>	
	<input type="checkbox" name="PML" class="custom-control-input sev_check1"  id="t6" checked onclick="call1(this)" value="0">
   
</div>
		<div class="col-sm-2">
 <label class="custom-control-label" for="defaultUnchecked">Abnormal</label>	
	<input type="checkbox" name="PML" class="custom-control-input sev_check1" id="t7" onclick="call1(this)" value="1">
   
</div>
		</div>
	</div>							
	 
	 <div class="form-group " style="display:none" id="show1PML"> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-10">
			<div class="form-group"> 
					<div class="col-sm-12">
<select class="PML multiselectt" id="t8" multiple="multiple">
            <option value="Thickened">Thickened</option>
            <option value="Calcification">Calcification</option>
            <option value="Flutter ">Flutter </option>
            <option value="Vegetation" >Vegetation</option>
            <option value="Prolapse">Prolapse</option>
            <option value="Paradoxical Motion">Paradoxical Motion</option>
            <option value="Fixed">Fixed</option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
	
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Doppler</label>
		<div class="col-sm-2 ss">
		  E wave(cm/s)<input type="text" class="form-control" id="t9">
		</div>  
		<div class="col-sm-2 ss">
		  A wave(cm/s)<input type="text" class="form-control " id="t10" >
		</div>  
		<div class="col-sm-3 ss">
		  DT<input type="text" class="form-control " id="t11" >
		</div>  
		<div class="col-sm-2 ss">
		  E/A<input type="text" class="form-control " id="t12" >
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Tissue Doppler</label>
		<div class="col-sm-2 ss">
		  E"<input type="text" class="form-control" id="t13">
		</div>  
		<div class="col-sm-2 ss">
		  A"<input type="text" class="form-control" id="t14">
		</div>  
		<div class="col-sm-3 ss">
		  E/e"<input type="text" class="form-control" id="t15">
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Diastolic Function</label>
		<div class="col-sm-10">
			<select class="form-control  custom-select" id="t16">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Grade 1 Dysfunction">Grade 1 Dysfunction</option>
  <option value="Grade 2  Dysfunction">Grade 2  Dysfunction</option>
    <option value="Grade 3  Dysfunction">Grade 3  Dysfunction</option>
      <option value="Grade 4  Dysfunction">Grade 4  Dysfunction</option>
  
</select>
		</div>  
	</div>
	
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Mitral Stenosis</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t17">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Progressive">Progressive</option>
   <option value="Very Severe">Severe</option>
  <option value="Very Severe">Very Severe</option>
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		  Max DG<input type="text" class="form-control" id="t18">
		</div>  
		
		<div class="col-sm-3 ss">
		  Mean DG(MmHg)<input type="text" class="form-control" id="t19">
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">MVA</label>
		<div class="col-sm-2 ss">
		By Planimetry(Cmsq)<input type="text" class="form-control" id="t20">
		</div>  
		<div class="col-sm-2 ss">
		 By PHT(Cmsq)<input type="text" class="form-control" id="t21">
		</div>  
	</div>
	
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">WILKINS SCORE</label>
		<div class="col-sm-2 ss">
		Leaflet Mobility<input type="text" class="form-control" id="t22">
		</div>  
		<div class="col-sm-2 ss">
		 Valve Thickness<input type="text" class="form-control" id="t23">
		</div>  
		<div class="col-sm-3 ss">
		  Subvalvular Thickening<input type="text" class="form-control" id="t24">
		</div>  
		<div class="col-sm-2 ss">
		 Valve Calcification<input type="text" class="form-control" id="t25">
		</div>  
	</div>

	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Mitral Regurgitation</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t26">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
  
</select>
		</div>  
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss">A4C</label>
		<div class="col-sm-2 ss" style="font-size: 13;">
		LA Area(Cmsq)<input type="text" class="form-control" id="t28">
		</div> 
		<div class="col-sm-2 ss">
		JetArea(Cmsq)<input type="text" class="form-control" id="t27">
		</div>  
  
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss">PLAX</label> 
		<div class="col-sm-2 ss" style="font-size: 13;">
		 LA Area(Cmsq)<input type="text" class="form-control" id="t30">
		</div> 
		<div class="col-sm-3 ss">
		  JetArea(Cmsq)<input type="text" class="form-control" id="t29">
		</div>  
 
	</div>
	
	<div class="form-group "> <!-- Full Name -->
	
		<label for="full_name_id" class="control-label col-sm-2 ss"></label>
		<div class="col-sm-2 ss">
		Vena Contracta(mm)<input type="text" class="form-control" id="t31">
		</div>  
		<div class="col-sm-2 ss">
		Regurgitant Volume <input type="text" class="form-control" id="t32">
		</div>  
		<div class="col-sm-3 ss">
		  ERO<input type="text" class="form-control" id="t33">
		</div>  
		</div>
		
		<!-- <div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss ">TRISCUPID VALUE</label>
		<div class="col-sm-10 ss">
		</div>
		</div>
		 -->
		
	<div class="alert alert-dark">
    <strong>TRISCUPID VALVE</strong>
  </div>
  
		<div class="form-group "> <!-- Frequency Field -->
		<label class="control-label col-sm-2 ">Morphology</label>
		<div class="col-sm-5">
 <label class="custom-control-label" for="defaultUnchecked">Normal</label>	
	<input type="checkbox" name="Morphology" class="custom-control-input sev_check2"  id="t34" checked onclick="call1(this)" value="0">
   
</div>
		<div class="col-sm-2">
 <label class="custom-control-label" for="defaultUnchecked">Abnormal</label>	
	<input type="checkbox" name="Morphology" class="custom-control-input sev_check2" id="t35" onclick="call1(this)" value="1">
   
</div>
		</div>
	</div>							
	 
	 <div class="form-group " style="display:none" id="show1Morphology"> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-10">
			<div class="form-group"> 
					<div class="col-sm-12">
<select class="Morphology multiselectt" id="t36" multiple="multiple">
            <option value="Thickened">Thickened</option>
            <option value="Atresia ">Atresia </option>
            <option value="Calcification">Calcification</option>
                        <option value="Non Coapting">Non Coapting</option>
                        <option value="Vegetation">Vegetation</option>
            <option value="Doming">Doming</option>
            <option value="Prolapse">Prolapse</option>
           
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
	
		
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Tricuspid Stenosis</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t37">
<option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		  Max DG<input type="text" class="form-control" id="t38">
		</div>  
		
		<div class="col-sm-3 ss">
		  Mean DG(MmHg)<input type="text" class="form-control" id="t39">
		</div>  
	</div>
	
	
		<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Tricuspid Regurgitation</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t40">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
    <option value="Trivial">Trivial</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		  RVSP(mmHg)=RAP+<input type="text" class="form-control" id="t41">
		</div>  
		</div>
		
		
		<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Pulmonary Hypertension</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t42">
 <option selected value="-1"> Select Value</option>
  <option value="None" selected>None</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
</select>
		</div>  
		
		</div>
		
		
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">AORTIC VALVE</label>
		<div class="col-sm-10 ss">
		</div>
		</div>
		
		
		<div class="form-group "> <!-- Frequency Field -->
		<label class="control-label col-sm-2 ">Morphology</label>
		<div class="col-sm-5">
 <label class="custom-control-label" for="defaultUnchecked">Normal</label>	
	<input type="checkbox" name="Morphology1" class="custom-co	ntrol-input sev_check3"  id="t43" checked onclick="call1(this)" value="0">
   
</div>
		<div class="col-sm-2">
 <label class="custom-control-label" for="defaultUnchecked">Abnormal</label>	
	<input type="checkbox" name="Morphology1" class="custom-control-input sev_check3" id="t44" onclick="call1(this)" value="1">
   
</div>
		</div>
	</div>							
	 
	 <div class="form-group " style="display:none" id="show1Morphology1"> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-10">
			<div class="form-group"> 
					<div class="col-sm-12">
<select class="Morphology1 multiselectt" id="t45" multiple="multiple">
            <option value="Thickened">Thickened</option>
            <option value="Calcification">Calcification</option>
                        <option value="Restricted Opening">Restricted Opening</option>
                        <option value="Flutter" >Flutter</option>
            <option value="Vegetation">Vegetation</option>
            
         
    </select>
 </div>
		</div>  
	</div>
	
	
		
	
	
	</div> 
	
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">CUSPS</label>
		  <div class="form-group " style="" id=""> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-8">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t95" multiple="multiple">
            <option value="Unicuspid">Unicuspid </option>
            <option value="Bicuspid">Bicuspid </option>
            <option value="Tricuspid" >Tricuspid </option>
            <option value="Quadricuspid">Quadricuspid </option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
		</div>  
		
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Doppler</label>
		<div class="col-sm-10 ss" >
		  Aortic peak systolic velocity(m/sec) <input type="text" class="form-control" id="t96">
		</div>  
			
		
	</div>
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 "> Aortic Stenosis </label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t97">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
      <option value="Very Severe">Very Severe</option>
  
</select>
		</div>  
		
	
	</div>
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 "> &nbsp; </label>
		<div class="col-sm-2">&nbsp;
			
				
		  PSG(mmHg)<input type="text" class="form-control" id="t98">
	
			
		</div>  
		
		<div class="col-sm-2 ss">
		   MSG(mmHg)<input type="text" class="form-control" id="t99">
		</div>  
		
		<div class="col-sm-3 ss">
		    AVA(VTI)(cmsq)<input type="text" class="form-control" id="t100">
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 "> &nbsp; </label>
		<div class="col-sm-3" >&nbsp;
			
				
		   Aortic annulus Size(mm)<input type="text" class="form-control" id="t101">
	
			
		</div>  
		
		<div class="col-sm-2 ss">
		   Aorta at Sinus (mm) <input type="text" class="form-control" id="t102">
		</div>  
		
		<div class="col-sm-3 ss">
		     Ascending Arota at STJ(mm)<input type="text" class="form-control" id="t103">
		</div>  
	</div>
	
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">  Aortic Regurgitation</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t104">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected> Absent</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
  
</select>
		</div>  
		
	
	</div>
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 "> &nbsp; </label>
		<div class="col-sm-2">&nbsp;
			
				
		   jet Width(mm) <input type="text" class="form-control" id="t105">
	
			
		</div>  
		
		<div class="col-sm-2 ss">
		   LVOT width(mm) <input type="text" class="form-control" id="t106">
		</div>  
		
		<div class="col-sm-3 ss">
		    PHT <input type="text" class="form-control" id="t107">
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 "> &nbsp; </label>
		<div class="col-sm-3" >&nbsp;
			
				
		    Vena Contracta(mm) <input type="text" class="form-control" id="t108">
	
			
		</div>  
		
		
	</div>
	
	<!-- <div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss "> PULMONARY VALVE </label>
			
		
	</div> -->
	
	<div class="alert alert-dark">
    <strong>PULMONARY VALVE</strong>
  </div>
  
  
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">  Morphology</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t109">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Thickened">Thickened</option>
  <option value="Atresia">Atresia</option>
    <option value="Vegetation">Vegetation</option>
  
</select>
		</div>  
		
	
	</div>
	
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Doppler</label>
		<div class="col-sm-4 ss" >
		 Peak Systolic Velocity(M/sec)<input type="text" class="form-control" id="t46">
		</div>  
		<div class="col-sm-4 ss" >
		 Pulm Acceleration Time(M/sec)<input type="text" class="form-control" id="t47">
		</div>  
		
	</div>
		
		<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Pulmonary Stenosis</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t48">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Mild">Mild</option>
  <option value="Moderate">Moderate</option>
    <option value="Severe">Severe</option>
  
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		  PSG(mmHg)<input type="text" class="form-control" id="t49">
		</div>  
		
		<div class="col-sm-3 ss">
		  Mean Grd(mmHg)<input type="text" class="form-control" id="t50">
		</div>  
	</div>
		
		
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Pulmonary Annulus size(mm)</label>
		<div class="col-sm-2 ss">
		 &nbsp;<input type="text" class="form-control" id="t51">
		</div> 
		<div class="col-sm-2 ss">
		  MPA(mm)<input type="text" class="form-control" id="t52">
		</div>  
		<div class="col-sm-3 ss">
		  RPA(mm)<input type="text" class="form-control" id="t53">
		</div>  
		<div class="col-sm-2 ss">
		  LPA(mm)<input type="text" class="form-control" id="t54">
		</div>  
	</div>
		
		<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">Pulmonary Regurgitation</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t55">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Present">Present</option>
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		  Early DG(MmHg)<input type="text" class="form-control" id="t56">
		</div>  
		
		<div class="col-sm-3 ss">
		  End DG(MmHg)<input type="text" class="form-control" id="t57">
		</div>  
	</div>
		
		
		<div class="form-group "> <!-- Full Name -->
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">MEASUREMENTS</label>
		
		<div class="col-sm-2 ss">
		<label class="control-label ">VALUES</label>
		</div> 
		 
		<div class="col-sm-2 ss">
		<label class="control-label ">NORMAL VALUES</label>
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">MEASUREMENTS</label>
		
		<div class="col-sm-2 ss">
		<label class="control-label ">VALUES</label>
		</div>  
		
		<div class="col-sm-2 ss">
		<label class="control-label ">NORMAL VALUES</label>
		</div>
	</div>
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">IVsd</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t58">
		</div>  
		<div class="col-sm-2 ss">
		  (6-10)mm
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">Aorta</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t59">
		</div>  
		<div class="col-sm-2 ss">
		  (20-37)mm
		</div> 
	</div>
		
		
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LVIDd</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t60">
		</div>  
		<div class="col-sm-2 ss">
		  (36-52)mm
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">LA</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t61">
		</div>  
		<div class="col-sm-2 ss">
		  (19-40)mm
		</div> 
	</div>
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LVPWd</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t62">
		</div>  
		<div class="col-sm-2 ss">
		  (7-11)mm
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">RVIDd</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t63">
		</div>  
		<div class="col-sm-2 ss">
		  (4-14)mm
		</div> 
		
	</div>
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">IVss</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t64">
		</div>  
		<div class="col-sm-2 ss">
		  (7-12)mm
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">RV Free Wall</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t65">
		</div>  
		<div class="col-sm-2 ss">
		  (upto 5 mm)
		</div>  
		
	</div>
	

	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LVIDs</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t66">
		</div>  
		<div class="col-sm-2 ss">
		  &nbsp;
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">FS</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t67">
		</div>  
		<div class="col-sm-2 ss">
		  (34-44)mm
		</div> 
		
	</div>
	

	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LVPWs</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t68">
		</div>  
		<div class="col-sm-2 ss">
		  (8-12)mm
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">TAPSE</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t69">
		</div>  
		<div class="col-sm-2 ss">
		  (>16)mm
		</div>
		
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">EF</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t70">
		</div>  
		<div class="col-sm-2 ss">
		  (>55%)
		</div>  
		
		<label for="full_name_id" class="control-label col-sm-2 ss ">Visual EF</label>
		<div class="col-sm-2 ss">
		  <input type="text" class="form-control" id="t71">
		</div>  
		<div class="col-sm-2 ss">
		  (>55%)
		</div>  
		
	</div>
	
	
	<div class="form-group "> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2 ">EF By SIMPSON Method</label>
		
		
		<div class="col-sm-2 ss">
		  LVEDV<input type="text" class="form-control" id="t72">
		</div>  
		
		<div class="col-sm-2 ss">
		  LVESV<input type="text" class="form-control" id="t73">
		</div>  
		
		<div class="col-sm-2 ss">
		  EF<input type="text" class="form-control" id="t110">
		</div>  
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">IVS Motion</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t74">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Flat">Flat</option>
  <option value="Paradoxical">Paradoxical</option>
    <option value="Jerky">Jerky</option>
  
</select>
		</div>  
	</div>
	
	
	<div class="form-group "> <!-- Email -->
		<label  for="email_id" class="control-label col-sm-2 ">CHAMBERS</label>
		<div class="col-sm-10">
		</div>  
	</div>
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LA</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t75">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Enlarged">Enlarged</option>
  <option value="Thrombus">Thrombus</option>
    <option value="SEC">SEC</option>
</select>
		</div>  
	</div>
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LV</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t76">
   <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Enlarged">Enlarged</option>
      <option value="Hypertrophy">Hypertrophy</option>
  <option value="Thrombus">Thrombus</option>
</select>
		</div>  
	</div>
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">RA</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t77">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Enlarged">Enlarged</option>
  <option value="Thrombus">Thrombus</option>
    <option value="SEC">SEC</option>
  
</select>
		</div>  
	</div>
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">RV</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t78">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Enlarged">Enlarged</option>
  <option value="Hypertrophy">Hypertrophy</option>
</select>
		</div>  
	</div>
	
	
	<!--  //because of less space
	<div class="form-group "> 
	
	<label for="full_name_id" class="control-label col-sm-2 ss ">Pericardium</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t79">
  <option selected value="-1"> Select Value</option>
  <option value="Normal" selected>Normal</option>
  <option value="Thickened">Thickened</option>
  <option value="Calcification">Calcification</option>
</select>
		</div>  
	</div>
	<!-- <div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss ">Effusion</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t80">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Present">Present</option>
</select>
		</div>  
	</div> -->
	
	
	<!-- because of less space
	<div class="form-group "> <!-- Email -->
	<!-- 
		<label for="email_id" class="control-label col-sm-2 ">Effusion</label>
		<div class="col-sm-2">&nbsp;
			<select class="form-control  custom-select" id="t80">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Present">Present</option>
</select>
		</div>  
		
		<div class="col-sm-2 ss">
		 Anterior To RV(mm)<input type="text" class="form-control" id="t81">
		</div>  
		
		<div class="col-sm-3 ss">
		 Posterior To LV(Mm)<input type="text" class="form-control" id="t82">
		</div>  
		<div class="col-sm-3 ss">
		  Lateral(Mm)<input type="text" class="form-control" id="t83">
		</div> 
	</div>
	 -->
	
	<!-- <div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss ">Anterior To RV(mm)</label>
<div class="col-sm-10">
			<input type="text" class="form-control" id="t81">
		</div>  
	</div>
		
		
	<div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss ">Posterior To LV(Mm)</label>
<div class="col-sm-10">
			<input type="text" class="form-control" id="t82">
		</div>  
	</div>
			<div class="form-group "> Full Name
		<label for="full_name_id" class="control-label col-sm-2 ss ">Lateral(Mm)</label>
<div class="col-sm-10">
			<input type="text" class="form-control" id="t83">
		</div>  
	</div> -->
	
	<!--  because less space
	<div class="form-group "> <!-- Full Name -->
	<!-- 
		<label for="full_name_id" class="control-label col-sm-2 ss ">IVC Diameter</label>
		<div class="col-sm-2 ss">
		 Expiration(mm)<input type="text" class="form-control" id="t84">
		</div>  
		<div class="col-sm-2 ss">
		 Inspiration(mm)<input type="text" class="form-control" id="t85">
		</div>  
		<div class="col-sm-3 ss">
		  Collapsibility(%)<input type="text" class="form-control" id="t86">
		</div>  
		
	</div>
	 -->
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">RWMA</label>
<div class="col-sm-10">
			<select class="form-control  custom-select" id="t87">
  <option selected value="-1"> Select Value</option>
  <option value="Absent" selected>Absent</option>
  <option value="Hypokinesia">Hypokinesia</option>
  <option value="Akinesia">Akinesia</option>
    <option value="Dyskinesia">Dyskinesia</option>
  
</select>
		</div>  
	</div>
	
	
	
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">BASAL LV</label>
		  <div class="form-group " style="" id=""> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-8">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t88" multiple="multiple">
            <option value="Anterior">Anterior</option>
            <option value="Anteroseptal">Anteroseptal</option>
            <option value="Inferoseptal">Inferoseptal</option>
            <option value="Inferior" >Inferior</option>
            <option value="Inferolateral">Inferolateral</option>
            <option value="Anterolateral">Anterolateral</option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
		</div>  
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">MID LV</label>
		  <div class="form-group " style="" id=""> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-8">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t89" multiple="multiple">
            <option value="Anterior">Anterior</option>
            <option value="Anteroseptal">Anteroseptal</option>
            <option value="Inferoseptal">Inferoseptal</option>
            <option value="Inferior" >Inferior</option>
            <option value="Inferolateral">Inferolateral</option>
            <option value="Anterolateral">Anterolateral</option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
		</div>  
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">APICAL LV</label>
		  <div class="form-group " style="" id=""> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-8">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t90" multiple="multiple">
            <option value="Anterior">Anterior</option>
            <option value="Septal">Septal</option>
            <option value="Inferior" >Inferior</option>
            <option value="Lateral">Lateral</option>
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
		</div>  
	
	
	
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">LV APEX</label>
		  <div class="form-group " style="" id=""> <!-- Email -->
		<label for="email_id" class="control-label col-sm-2"></label>
		<div class="col-sm-8">
			<div class="form-group"> 
					<div class="col-sm-10">
<select class="AML multiselectt" id="t91" multiple="multiple">
            <option value="Apical Cap">Apical Cap</option>
       
         
    </select>
 </div>
		</div>  
	</div>
	
	
	</div> 
		</div> 
		
		
		<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">Remarks</label>
<div class="col-sm-10">
           <textarea rows="4" cols="50" id="t92"></textarea>
 </div>  
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">IMPRESSION</label>
<div class="col-sm-10">
           <textarea rows="4" cols="50" id="t93"></textarea>
 </div>  
	</div>
	
	<div class="form-group "> <!-- Full Name -->
		<label for="full_name_id" class="control-label col-sm-2 ss ">PLAN</label>
<div class="col-sm-10">
           <textarea rows="4" cols="50" id="t94"></textarea>
 </div>  
	</div>
		
<div class="form-group"> <!-- Submit Button -->
		<div  align='center'>                     
			<button style="width:200" type="button" class="btn btn-primary" onclick="printt(this)" id="gg">Save</button>
		</div>
	</div>	
		


		
		
		
</form>  
</body>
</html>                             