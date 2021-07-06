<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<html>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
	
    <head>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"> </script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="/HISDRDESK/new_opd/css/draggableStyle.css"/>
        <script type="text/javascript">
        $(document).ready(function(){
        	var myJSON;
        	var myHTML;
        	var EsstentialJson;
        	
        	  if(localStorage.getItem("FormattedHTML1"))
        	  { 
            	// myJSON = localStorage.getItem("OPDTemplateJson");
        		 myHTML = localStorage.getItem("FormattedHTML1");
        		 $("#rednderHtmlForm").html(myHTML);
        		}
        	  //console.log('myJSON \n'+myJSON);
        	  //console.log('myJSON \n'+myHTML);

        	  
        	 
        });
        </script>
        
        <script type="text/javascript">
     $(document).ready(function(){
        $("#prescSaveBtnId").click(function(){
        	 // alert("The paragraph was clicked.");
        	  //$('#printPrescriptionModal').modal('show');
        	//  $(window.parent('#printPrescriptionModal .modal-header').find(".close")).click();
        	 document.forms[0].hmode.value="SAVE" ;
     		 document.forms[0].submit(); 
        	}); 
     });
        </script>
    </head>
    
    <body>
      <html:form action="/transaction/OPDTemplateMstAction.cnt"  name="OPDTemplateMstFb" type="new_opd.transaction.controller.fb.OPDTemplateMstFb" method="post" >
        <!-- <div style="right:8px; position:fixed">
        <button class="btn btn-success prescSaveBtn" style="z-index:9999;" type="button" id="prescSaveBtnId" >Save</button>
        </div>
         -->
        
         <div class="container-fluid">
            <div class="formSection"> 
            <div class="row" id="rednderHtmlForm"> 
           
		<!--   <div class="rendered-form"><div class="formbuilder-date form-group field-date-1582610636555 col-sm-4"><label for="date-1582610636555" class="formbuilder-date-label">Date Field</label><input type="date" class="form-control" name="date-1582610636555" access="false" columns="4" id="date-1582610636555"></div><div class=""><h1 access="false" columns="4" id="control-8430126">Header</h1></div></div> -->
		   
		   
             </div>
             </div>
         </div>
  <input type="hidden" name="hmode"/> 
<input type="hidden" name="strJsonParaMetereIdString" value="${OPDTemplateMstFb.strJsonParaMetereIdString}"/>
<input type="hidden" name="strHtmlString" value="${OPDTemplateMstFb.strHtmlString}"/>
<input type="hidden" name="strEssentilaJson" value="${OPDTemplateMstFb.strEssentilaJson}"/>
</html:form> 
    </body>
    
          

</html>






