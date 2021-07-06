<html>
    <head>
    <%@taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="java.util.*"%>
<%@page import="org.apache.commons.collections.MultiMap"%>
<%@page import="org.apache.commons.collections.map.MultiValueMap"%>
<%@page import="org.json.*"%>
     <!--    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"> </script>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="draggableStyle.css"/> -->
        
         
<!--  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
 -->
 

 
 <link rel="stylesheet" type="text/css" href="/HISDRDESK/hisglobal/template/css/jqui_1_9_2.css"/>
  <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/bs_45.css">
 <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/fa_5.css">
 <link rel="stylesheet" href="../../hisglobal/template/js/swal.css">
 <link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/fb.css">
 <link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/opdtemplate.css">  
 
 <script src="/HISDRDESK/hisglobal/template/js/jq_3_5_1.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/jqui_1_12_1.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/poper.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/bs_45.js"></script>
  <script src="/HISDRDESK/hisglobal/template/js/fa_5.min.js"></script>
 
 
<!--  <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/js/all.min.js"></script>
 -->  <script type="text/javascript" src="../../hisglobal/js/util.js"></script>
  <script src="../../hisglobal/template/js/swal.js"></script>
 
 <script type="text/javascript" src="/HISDRDESK/new_opd/js/fb.js"></script>
 

 <script src="../../hisglobal/js/validation.js"></script>
        	<script type="text/javascript" src="/HISDRDESK/new_opd/js/OpenTemplate.js"></script> 
<!--         	  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
  -->
    
<!--   <script src="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.js"></script>    
<link rel="stylesheet" href="/HIS/hisglobal/drDeskAssets/flexdatalist/jquery.flexdatalist.min.css"> -->

	<style>
	

		@media print {
		  body * {
		    visibility:hidden;
		  }
		  #printSection, #printSection * {
		    visibility:visible;
		  }
		  #printSection {
		    position:absolute;
		    max-width:auto;
		    top:1vh;
			margin: 1mm 25mm 25mm 15mm;
		  }
		}
		
		.card-header{
		border-bottom:1px solid cadetblue;
		max-height: 40;
		background:linear-gradient(to bottom right,#b1dbff8a,#5f9ea000)
		}
		
	</style>
	
	
    </head>
    
    <body>
       
     <div style="border:1px solid #57b0b33d" class="tmp-container"> 
        <%
        String strTemplateType =(String) request.getSession().getAttribute("strTemplateType");
       
        String strcat =(String) request.getSession().getAttribute("strtype");
        
        %>
		
 			<%
			HashMap<String,String> DrugProfileBookmark= (HashMap) request.getSession().getAttribute("strTemplateHtmlMap");
				 // System.out.println("DrugProfileBookmark::::::::::::: "+DrugProfileBookmark.size());
				  int countvar=0;
				  if(DrugProfileBookmark!=null){
				
				
				for(Map.Entry<String,String> m:DrugProfileBookmark.entrySet()){ 
					String TemplateId=m.getKey();
					if((TemplateId.split("#")[2]).equalsIgnoreCase(strTemplateType) && strcat.equalsIgnoreCase(TemplateId.split("#")[3]) ){
						countvar++;
					String TemplateValue=m.getValue();
					System.out.println(TemplateId+"::::::::::::: "+TemplateValue);
					%>
					
					  <div class="tmp-cont">
      <div class="card-header" onclick="togBtn('<%=TemplateId.split("#")[0] %>',this)" >
        <h6 class="card-title" >
          <a style="color: #39f;line-height:25%"  class="collapsed card-link" ><%=TemplateId.split("#")[1] %></a><span id="DiagnosisDivId" style="text-align: right; float: right;" class="spnaRight">
       			 <button style="display:none;margin-top:-10px;" type="button" id="btn<%=TemplateId.split("#")[0] %>" class="btn btn-link btnCol" onclick="saveHtmlData(<%=TemplateId.split("#")[0] %>,this)">Save & Print</button>
       			 <button style="display:none" type="button" id="print<%=TemplateId.split("#")[0] %>" class="btn btn-link printCol" onclick="printDiv(<%=TemplateId.split("#")[0] %>)">print</button>
        </h6>
      </div>
      <div class="collapse" id="collapse<%=TemplateId.split("#")[0] %>" >
        <div class="card-body " id="DiagnosisDivId">
        
       
        			<input type="hidden" name="strTemplatecode" id="strTemplatecodeId" value=<%=TemplateId.split("#")[0] %>>        			
         <div class="container-fluid">
            <div class="formSection" > 
            <div class="row"> 
  
					 <form name="FormData" id=<%=TemplateId.split("#")[0] %> style="width:110%">
					
					
					<%=TemplateValue %>
					
					</form>
					 
       			
					</div>
             </div>
         </div>
        </div>
      </div>
    </div>
					<%
					
				} 
			}
			}
			
			%>
			
<% if(DrugProfileBookmark == null)
	//System.out.println(DrugProfileBookmark);
{
	%>
	<B>No Template Mapped</B>	   
<%
}
%>				
			
			
<% if(DrugProfileBookmark != null  && (DrugProfileBookmark.size() ==0 || countvar==0))
	
{
	%>
	<B>No Template Mapped</B>	   
<%
}
%>		   
              
   </div>         
 
    <div id="printSection"></div>
    
    <input type="hidden" name="strTemplateType" value="${DoctorDeskFB.strTemplateType}"/>
    
         <script>

 		window.parent.$(".tmp-search").on("keyup",function(){
			let reg=new RegExp($(this).val().trim().toUpperCase(),"g");
			$(".tmp-container .card-link").each((i,a)=>{
				if($(a).text().toUpperCase().match(reg)){
					$(a).closest(".tmp-cont").css("display","");
				}else{
					$(a).closest(".tmp-cont").css("display","none");
				}	 
				
			});
		});
		
     </script>
    </body>
    
</html>