<%@page import="javax.sql.rowset.WebRowSet"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
	<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%> 
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.21/css/jquery.dataTables.min.css"> 
<link rel="stylesheet" href="https://cdn.datatables.net/fixedcolumns/3.3.1/css/fixedColumns.dataTables.min.css">
<link rel="stylesheet" href="https://cdn.datatables.net/select/1.3.1/css/select.dataTables.min.css">

 <link rel="stylesheet" type="text/css" href="/HISDRDESK/hisglobal/template/css/jqui_1_9_2.css"/>
 <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/bs_45.css">
 <link rel="stylesheet" href="/HISDRDESK/hisglobal/template/css/fa_5.css">
 
  <link rel="stylesheet" href="../../hisglobal/template/js/swal.css">
   <link rel="stylesheet" type="text/css" href="/HISDRDESK/new_opd/css/fb.css">
   
   
 <script src="/HISDRDESK/hisglobal/template/js/jq_3_5_1.min.js"></script>
<!-- <script src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script> 
<script src="https://cdn.datatables.net/fixedcolumns/3.3.1/js/dataTables.fixedColumns.min.js"></script>
<script src="https://cdn.datatables.net/select/1.3.1/js/dataTables.select.min.js"></script> -->

<script type="text/javascript" src="/HISDRDESK/hisglobal/template/js/dtable.min.js"></script>
<script type="text/javascript" src="/HISDRDESK/hisglobal/template/js/dtable_fixedCol.min.js"></script>
<script type="text/javascript" src="/HISDRDESK/hisglobal/template/js/dtable_select.min.js"></script>

 <script src="/HISDRDESK/hisglobal/template/js/fa_5.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/jqui_1_12_1"></script>
 <script src="/HISDRDESK/hisglobal/template/js/poper.min.js"></script>
 <script src="/HISDRDESK/hisglobal/template/js/bs_45.js"></script>
 <script type="text/javascript" src="/HISDRDESK/new_opd/js/fb.js"></script>

<script src="/HISDRDESK/new_opd/js/templateListing.js"></script>
<style>
 
.prescriptionTile{
width:98% !important;
margin-left:1% !important;
margin-right:1% !important;

}
.selected{
background-color:#d4e3ff !important;
}
 th{
color: #747e86;
/*text-align:center;*/
/*background-color:#e0f1ff96;*/

} 
#mstTable_info{
/*font-weight:bold;*/
color:#5cb6f9;
}
#mstTable_length{
/*font-weight:bold;*/
color:#5cb6f9;
}

#mstTable_length  select{
border:1px solid #ced4da !important;
}
.dataTables_scrollBody{
border:none !important;
}
.tdColored{
color:#0d7f8a;
/*font-weight:bold;*/
}

.badge{
font-weight:400 !important;
padding: .45em .7em !important;
}
   </style>
  
 
   <script type="text/javascript">
   $(document).ready(function() {
	   $('#mstTable').DataTable({language: {
		    paginate: {
			      next: '<i style="color:#2e6bac" class="fas fa-chevron-circle-right"></i>',
			      previous: '<i style="color:#2e6bac" class="fas fa-chevron-circle-left"></i>'
			    }
			  },
		         
		          "scrollY":"48vh",

		          columnDefs: [ {
		              orderable: false,
		              className: 'select-checkbox',
		              targets:   0
		                      
		              
		          } ],
		          select: {
		              style:    'os',
		              selector: 'td:first-child'
		          }		          
             
	   }) ;
	    $("#mstTable_filter label").html($("#mstTable_filter input").clone(true));
	   $("#mstTable_filter input").attr("placeholder","Search");
	   $("#mstTable_filter input").addClass("form-control form-control-sm");   
	  $("#mstTable_length select").addClass("form-control-sm");
	} );
   
  function callCheck(ob){
$("#mstTable input[type='checkbox']").prop("checked",false);
	   if(ob.firstElementChild.checked ==false){
			ob.firstElementChild.checked = true; 

	  }
		
  }
   </script> 
    
<title>Insert title here</title>
</head>
<body>

<html:form action="/transaction/OPDTemplateMstAction.cnt"  name="OPDTemplateMstFb" type="new_opd.transaction.controller.fb.OPDTemplateMstFb" method="post" >  

<div style="width: 90%; margin-left: 5%;" class="prescriptionTile">

<table id="mstTable" class="display" style="width:100%">
        <thead>
            <tr>
            	<!-- <th><input type="checkbox" ></th> -->
                <th></th>
                <th>Template Name</th>
                <th>Cateogry</th>
                <th>Dept Name</th>
                <th>Template Type</th>
                <th>Is Default</th>
                <th>Active Date</th>
            </tr>
        </thead>
        <tbody>
        <%
        
        WebRowSet strTemlateListingData= (WebRowSet) request.getSession().getAttribute("TEMPLTELISTINGDATA");
       	System.out.println("strTemlateListingData"+strTemlateListingData);
       	if(strTemlateListingData!=null && strTemlateListingData.size() > 0)
       	{
       		int i=0;
       		String[] col={"primary","secondary","success","danger","warning","info","light","dark"};
       		
 			while(strTemlateListingData.next()){		
 				
 			%>
 			<tr>
 			<td onclick="callCheck(this)">
                 <input  style="display:none" type="checkbox" name="chk" value="<%=strTemlateListingData.getString(13) %> " onclick="getTemplateCode(this, <%=strTemlateListingData.getString(1) %> , <%=strTemlateListingData.getString(5) %>)">
          	</td>
                <td><h5><span class="badge badge-<%=col[i] %>"><%=strTemlateListingData.getString(3) %></span></h5></td>
                <td><%=strTemlateListingData.getString(7) %></td>
                <td class="tdColored"><%=strTemlateListingData.getString(2) %></td>
                <td><%=strTemlateListingData.getString(14) %></td>
                <td><%=strTemlateListingData.getString(8) %></td>
                <td><%=strTemlateListingData.getString("Entry_Date") %></td> 
            </tr>	
 			
 			<%
 			i+=1;
	       		if(i==8)
	       			i=0;
 			}
       		
       	}
       	
        
        %>
        
        </tbody>
       
    </table>
   </div>
    <table align="center"><tbody><tr><td class="addtoolbar" height="23" align="center"> 
	
	<button type="button" class="btn btn-outline-success"  onclick="ADD(this)"><b>Add</b></button>
	<button type="button" class="btn btn-outline-primary" onclick="MODIFY(this)"><b>Modify</b></button>
	<button type="button" class="btn btn-outline-warning" onclick="viewTemplate()"><b>View</b></button>
	 <button type="button" class="btn btn-outline-danger" onclick="deleteRecord()"><b>Delete</b></button>

	  </td>
	  </tr>
	  </tbody>
	  </table>
	   <div class="modal fade" id="printPrescriptionModal" role="dialog">
    <div id="printPrescriptionModalId12" class='modal-dialog modal-xl' style="max-width:95vw">       <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
        <label id="modaldeptName">General Medicine</label> &nbsp;&nbsp;
         <label id="modalTemplateName">General Medicine</label>
        <!--   <label id="deptName">General Medicine</label>
           <label id="deptName">General Medicine</label> -->
          <button type="button" class="close" data-dismiss="modal">&times;</button> 
        </div>
        <div class="modal-body" id="prevCont">
        </div>  
      </div> 
    </div>
  </div> 
	     <input type="hidden" name="hmode"/>
	     <input type="hidden" name="strTemplateDeleteParm" value="${OPDTemplateMstFb.strTemplateDeleteParm}"/>
		</html:form>
</body>
</html>