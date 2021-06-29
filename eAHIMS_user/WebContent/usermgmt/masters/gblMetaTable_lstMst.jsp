<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%
/*   These are the fields to be filled by the developer*/

record_per_page			=	10;
no_of_fields			=	2;
no_of_primary_keys		=	1;
page_per_block			= 	10;
isValid		            ="GBL_ISVALID";
hospitalCode 			="GNUM_HOSPITAL_CODE";  

//for combo


pagename     =	"gblMetaTable_lstMst.jsp";
nextpage     =	"gblMetaTable_cntMst.jsp";
previouspage =	"listing_training.jsp";

title         = "Module Master";
table_heading = "Module Master";
       
query1	=	"SELECT initcap(GSTR_MODULE_NAME),GNUM_MODULE_ID ";
query1  +=	"FROM GBLT_METATABLE_TYPE_MST ";

query2	=	"SELECT GNUM_MODULE_ID ";
query2	+=	"FROM GBLT_METATABLE_TYPE_MST";

//ordervar=" order by GSTR_MODULE_NAME ";

/*if(strcombo[0].equals("") || strcombo[0].equals("0"))
	action	=	"GBL_ISVALID  = 1"; 
else
	action	=	"GNUM_MODULE_ID = '" + strcombo[0] + "' AND GBL_ISVALID  = 1";
*/
//For combo

action	=	"";
//Header 
//headlist.add("#");
headlist.add("Module Name");
headlist.add("Module Id");



//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Module Name","GSTR_MODULE_NAME");
sortmap.put("Module Id","GNUM_MODULE_ID");


//Searching Field
Map searchmap = new HashMap();
searchmap.put("Module Name","GSTR_MODULE_NAME");
%>

<%@ include file="fileB.jsp"%>
