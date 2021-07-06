<!DOCTYPE html>
<html
 <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> >
<head>

  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  <script src="/HISDRDESK/new_opd/js/serachemr.js"></script> 
  <script src="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.js"></script>    
<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.css">
  <style>
  body{
     background-color: rgb(234, 238, 243);
  }
  
  .card {
    margin: 2% 0;
    border-bottom: 1px solid #d7d7d7;
    padding-bottom: 10px;
    padding: 1% 2% 0.5% 2%;
    background-color: #fff;
    transition: 0.5s;
    box-shadow: 0 0.5px 10px 2px #b0acac;
    border-radius: .25rem;
    color: #666;
    color: rgba(75, 75, 75, 0.7);
}
.printPrescPage{
			letter-spacing: inherit !important;
			color: #5a5a5a !important;
			font-weight:600;
			text-align: justify;
		}
		
		.flexdatalist-multiple-value, .flexdatalist-alias{
		width: 100% !important;
		}
		.spnaRight
		{
		float:right;
		}
		</style>
</head>


<body>
<html:form action="/transaction/SearchemrAction.cnt"  name="SearchemrActionFB" type="new_opd.transaction.controller.fb.SearchemrActionFB" method="post" >


<br>

<div class="container-fluid">


         
              
  <div class="card">                      
<!-- <input type="button" onclick="serachemrData(this)" value="Go"></button> --> 
 <div class="row">
  <div class="col-sm-4">
     <h2>Clinical Research Record</h2>
  </div>
  <div class="col-sm-7" style="margin-top: 20px;">
    <input type='text'
       placeholder='Enter Search Parameter'
       class='flexdatalist form-control'
       data-min-length='1'
       list='languages'
       name='SearchPara' id="SearchParaIs" onkeypress="serachemrData(this)"   >
  </div>
  <div class="col-sm-1" style="margin-top: 19px;"> 
  <input type="button" onclick="serachemrData(this)" value="Go"  class="btn btn-primary" > </button>
   </div>
 </div>
  
  <div class="row">
  <div class="col-sm-10"> 
  
  <!-- <input type='text'
       placeholder='Enter Search Parameter'
       class='flexdatalist form-control'
       data-min-length='1'
       list='languages'
       name='SearchPara' id="SearchParaIs" onkeypress="serachemrData(this)"   > -->
       <br>
  <input type="checkbox" class="custom-control-input"	checked="checked" onclick="" name="searcgPrarametreId" value="1">&nbsp;&nbsp;All Parameter &nbsp&nbsp
  <input type="checkbox" name="searcgPrarametreId" value="2">&nbsp;&nbsp;Diagnosis	&nbsp&nbsp
  <input type="checkbox" name="searcgPrarametreId" value="3">&nbsp;&nbsp;Chief Complaint	&nbsp&nbsp
  <input type="checkbox" name="searcgPrarametreId" value="4">&nbsp;&nbsp;Test Name	&nbsp&nbsp
  <input type="checkbox" name="searcgPrarametreId" value="5">&nbsp;&nbsp;Drug Name	&nbsp&nbsp
  <input type="checkbox" name="searcgPrarametreId" value="6">&nbsp;&nbsp;CR No.	&nbsp&nbsp
  <input type="checkbox" name="strCheckbyDate" id="strCheckbyDateId" onchange="checkbydate()" value="7">&nbsp;&nbsp;Date	&nbsp&nbsp  
  <input type="checkbox" name="strCheckByAge" id="strCheckByAgeId" onchange="strCheckByAgeFun()" value="8">&nbsp;&nbsp;Age	&nbsp&nbsp 
  <input type="checkbox" name="strCheckByGender" id="strCheckByGenderId" onchange="strCheckByGenderFun()" value="9">&nbsp;&nbsp;Gender	&nbsp&nbsp 
  <input type="checkbox" name="strCheckByDept" id="strCheckByDeptId" onchange="strCheckByDeptFun()" value="10">&nbsp;&nbsp;Dept	&nbsp&nbsp  
  <input type="checkbox" name="strVitalSerach" id="strVitalSerachId" onchange="" value="11">&nbsp;&nbsp;Vital	&nbsp&nbsp  


<br>
<div class="">
<div id="DivId" class="col-md-3" style="display: none;">
<div class="">From Date
<div class="">
  <input type="date" style="padding-top: 0;border-radius: 8px;" class="form-control"  name="strfromDate" id="strfromDateId" >
</div>
</div>

<br>

<div class="">To Date
<div class="">
  <input type="date" style="padding-top: 0;border-radius: 8px;" class="form-control"  name="strToDate" id="strToDateId" >
</div>
</div>

</div>


<div id="AgeDivId" class="col-md-3" style="display: none;">
<div class="">
<div class="">
  <input type="text" style="padding-top: 0;border-radius: 8px;" class="form-control" placeholder="From Age" name="strFromAge" id="strFromAgeId" >
</div>
</div>

<br>

<div class="">
<div class="">
  <input type="text" style="padding-top: 0;border-radius: 8px;" class="form-control" placeholder="To Age"  name="strToAge" id="strToAgeId" >
</div>
</div>
</div>

<div id="GenderDivId" style="display: none;">
<div class="">
<div class="col-md-3">
  <input type="radio" style="padding-top: 0;border-radius: 8px;" class="" checked="checked"  name="strGenderVal" value="M" id="strGenderId" >M
  <input type="radio" style="padding-top: 0;border-radius: 8px;" class=""  name="strGenderVal" value="F" id="strGenderId" >F
</div>
</div>

<br>

<div class="" style="display: none;">
<div class="col-md-3">
  <input type="text" style="padding-top: 0;border-radius: 8px;" class="form-control" placeholder="To Age"  name="strToAge" id="strToAgeId" >
</div>
</div>
</div>


<div id="DeptDivId" style="display: none;">
<div class="">
<div class="col-md-3">
  
   <select name="strDeptCode" id="strDeptCodeId" class="form-control" style="color: #082b71;border-radius: 0px;" onchange="getDeltList(this);" >
            		<bean:write name="SearchemrActionFB" property="strDeptValues" filter="false"/>
                  </select>
                  
</div>
</div>

<br>

<div class="" style="display: none;">
<div class="col-md-3">
</div>
</div>
</div>


</div>
<datalist id="languages">
<bean:write name="SearchemrActionFB" property="strSearchParaValues" filter="false"/>
   <!--  <option value="fever">fever</option>
    <option value="Maleria">Maleria</option>
    <option value="Tonsil">Tonsil</option>
    <option value="Paracetamol">Paracetamol</option>
     <option value="CBC">CBC</option> -->
</datalist>
</div>
<div class="col-sm-2">  </div> 

</div>



  <p><strong></strong> <strong></strong></p>
  
  <div class="panel-group" id="accordion">
  
  
       <div class="row">
        <div class="col-sm-6">
        <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Diagnosis</a><span id="DiagnosisDivId" style="text-align: right; " class="spnaRight">
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body " id="DiagnosisDivId">
        <ol class="DiagnosisClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div>
        </div>
        <div class="col-sm-6">
         <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Chief Complaint</a><span id="ChiefcomplaintDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body " id="ChiefComplaintDivId">
        <ol class="ChiefComplaintClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div>
        </div>
       </div> 
  
       <div class="row" style="margin-top:5px;">
        <div class="col-sm-6">
         <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Test Name</a><span id="TestDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body " id="TestDivId">
        <ol class="TestDivIdClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div>
        </div>
        <div class="col-sm-6">
         <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Drug Name</a><span id="DrugDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body">
        <ol class="DrugDivIdClass printPrescPage">
        
		</ol>
        
        </div>
      </div>
    </div>
        </div>
       </div>
       
       <div class="row" style="margin-top:5px;">
        <div class="col-sm-6">
          <div class="panel panel-default" style="">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse5" >CR No.</a><span id="CRnoDivId" class="spnaRight"></span>
        </h4>
      </div>
      <div id="collapse5" class="panel-collapse collapse">
        <div class="panel-body " id="crNoId">
        <ol class="crNoIdDivIdClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div>
        </div>
        <div class="col-sm-6"></div>
       </div>
       
     <!-- <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse1">Diagnosis</a><span id="DiagnosisDivId" style="text-align: right; " class="spnaRight">
        </h4>
      </div>
      <div id="collapse1" class="panel-collapse collapse">
        <div class="panel-body " id="DiagnosisDivId">
        <ol class="DiagnosisClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div> -->
    
    
    
      <!--  <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse2">Chief Complaint</a><span id="ChiefcomplaintDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse2" class="panel-collapse collapse">
        <div class="panel-body " id="ChiefComplaintDivId">
        <ol class="ChiefComplaintClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div> -->
  
  
  
  
    <!-- <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Test Name</a><span id="TestDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body " id="TestDivId">
        <ol class="TestDivIdClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div> -->
    <!-- <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse4">Drug Name</a><span id="DrugDivId" style="text-align: right;" class="spnaRight">
        </h4>
      </div>
      <div id="collapse4" class="panel-collapse collapse">
        <div class="panel-body">
        <ol class="DrugDivIdClass printPrescPage">
        
		</ol>
        
        </div>
      </div>
    </div> -->
    
      <!-- <div class="panel panel-default" style="">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse5" >CR No.</a><span id="CRnoDivId" class="spnaRight"></span>
        </h4>
      </div>
      <div id="collapse5" class="panel-collapse collapse">
        <div class="panel-body " id="crNoId">
        <ol class="crNoIdDivIdClass printPrescPage">
					</ol>
        
        </div>
      </div>
    </div> -->
    

    
    <!-- <div class="panel panel-default">
      <div class="panel-heading">
        <h4 class="panel-title">
          <a data-toggle="collapse" data-parent="#accordion" href="#collapse3">Collapsible Group 3</a>
        </h4>
      </div>
      <div id="collapse3" class="panel-collapse collapse">
        <div class="panel-body">Lorem ipsum dolor sit amet, consectetur adipisicing elit,
        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</div>
      </div>
    </div> -->
  </div> 
</div>
</div>
<input type="hidden" name="hmode"/> 
   </html:form> 
</body>
</html>
