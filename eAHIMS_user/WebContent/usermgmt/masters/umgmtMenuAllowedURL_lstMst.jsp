<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%

/* These are the fields to be filled by the developer*/
record_per_page			=	10;
no_of_fields			=	3;
no_of_primary_keys		=	2;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 
hospitalCode=" a.GNUM_HOSPITAL_CODE ";  
String hosCodeValue = (String)session.getAttribute("HOSPITAL_CODE");

isValid = " GNUM_ISVALID ";
pagename     =	"umgmtMenuAllowedURL_lstMst.jsp";
nextpage     =	"umgmtMenuAllowedURL_cntMst.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Menu Allowed URL Master";
table_heading = "Menu Allowed URL Master";


query1	=	"	SELECT pkg_usermgmt.fun_menu_name(GNUM_MENU_ID,GNUM_HOSPITAL_CODE), NVL(GSTR_ALLOWEDURL,' '), "+
            "	NVL((SELECT GSTR_MODULE_NAME FROM GBLT_METATABLE_TYPE_MST WHERE GNUM_MODULE_ID = A.GNUM_MODULE_ID and gnum_isvalid=1),' ') ";
query1  +=	"   FROM GBLT_MENU_ALLOWEDURL_MST A  ";


query2	=	"SELECT GNUM_MENU_ID, GSTR_ALLOWEDURL";

query2	+=	" FROM GBLT_MENU_ALLOWEDURL_MST a ";
			 
action = "  1=1 ";

//Header 

headlist.add("Menu Name");
headlist.add("Allowed URL");
headlist.add("Module Name");

//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Menu Name","pkg_usermgmt.fun_menu_name(GNUM_MENU_ID,GNUM_HOSPITAL_CODE)");

//Searching Field
Map searchmap = new HashMap();
searchmap.put("Menu Name","pkg_usermgmt.fun_menu_name(GNUM_MENU_ID,GNUM_HOSPITAL_CODE)");
searchmap.put("Allowed URL","GSTR_ALLOWEDURL");

%>
<%@ include file="filenewB.jsp"%>
