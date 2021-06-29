<%@page import="usermgmt.umgmtGlobal"%>
<%@ include file="fileA.jsp"%>
	<script>
	window.history.forward() ;
	</script>
<%
/* These are the fields to be filled by the developer*/

record_per_page			=	10;
no_of_fields			=	2;
no_of_primary_keys		=	1;
page_per_block			= 	10;
isValid				="GNUM_ISVALID";
hospitalCode =      "GNUM_HOSPITAL_CODE";
//for combo
//no_of_combo				=	0;



pagename     =	"hospital_master_list.jsp";
nextpage     =	"hospital_master_cnt.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Hospital Master";
table_heading = "Hospital Master";
       
query1 = 	" SELECT "+
			" NVL(initcap(gstr_hospital_name),''), "+
			" NVL(GSTR_USER_NAME,'') "+
			" FROM gblt_hospital_mst "; 			
		
query2	=	"  SELECT gnum_hospital_code"+			
			" FROM gblt_hospital_mst  ";
			 
action	=	"";
						
					

//Header 
//headlist.add("#");
headlist.add("Hospital Name");
headlist.add("User ID");


//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Hospital Name","GBLT_HOSPITAL_MST.GSTR_HOSPITAL_NAME");
//Searching Field
Map searchmap = new HashMap();
searchmap.put("Hospital Name","GBLT_HOSPITAL_MST.GSTR_HOSPITAL_NAME");
%>
<%@ include file="hosp_list_page.jsp"%>
