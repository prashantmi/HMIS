<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%

/* These are the fields to be filled by the developer*/
record_per_page			=	10;
no_of_fields			=	4;
no_of_primary_keys		=	2;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 
//hospitalCode=" a.GNUM_HOSPITAL_CODE ";  
//String hosCodeValue = (String)session.getAttribute("HOSPITAL_CODE");

isValid = " GNUM_ISVALID ";
pagename     =	"umgmtMenu_lstMst.jsp";
nextpage     =	"umgmtMenu_cntMst.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Menu Master";
table_heading = "Menu Master";


//query1	=	"	SELECT initcap(GSTR_MENU_NAME),GNUM_MENU_LEVEL,"+
            //"	NVL((SELECT initcap(GSTR_MENU_NAME) FROM GBLT_MENU_MST WHERE GNUM_MENU_ID = A.GNUM_PARENT_ID and gnum_hospital_code =100 and gnum_isvalid=a.gnum_isvalid ),' '), ";
			//"	NVL((SELECT initcap(GSTR_MENU_NAME) FROM GBLT_MENU_MST WHERE GNUM_MENU_ID = A.GNUM_PARENT_ID and gnum_isvalid=a.gnum_isvalid ),' ') as Parent, ";
			//"	NVL((SELECT initcap(GSTR_MENU_NAME) FROM GBLT_MENU_MST WHERE GNUM_MENU_ID = A.GNUM_PARENT_ID and gnum_hospital_code =a.gnum_hospital_code and gnum_isvalid=a.gnum_isvalid ),' '), ";
//query1  +=	" NVL(GSTR_URL,' ') FROM GBLT_MENU_MST A  ";

query1	=  "select GSTR_MENU_NAME,GNUM_MENU_LEVEL,Parent,URL,GNUM_ISVALID from "+
             "(SELECT initcap(GSTR_MENU_NAME) as GSTR_MENU_NAME ,GNUM_MENU_LEVEL,	NVL((SELECT initcap(GSTR_MENU_NAME)"+ 
             "FROM GBLT_MENU_MST WHERE GNUM_MENU_ID = A.GNUM_PARENT_ID and gnum_isvalid=a.gnum_isvalid ),' ') as Parent,"+  
             "NVL(GSTR_URL,' ') as URL,GNUM_ISVALID FROM GBLT_MENU_MST A ) ";



query2	=	"SELECT GNUM_MENU_ID,GNUM_MENU_LEVEL";

//query2	+=	" FROM GBLT_MENU_MST a ";

query2	+= " FROM (SELECT GNUM_MENU_ID,initcap(GSTR_MENU_NAME) as GSTR_MENU_NAME ,GNUM_MENU_LEVEL,NVL((SELECT initcap(GSTR_MENU_NAME)"+
            "FROM GBLT_MENU_MST WHERE GNUM_MENU_ID = A.GNUM_PARENT_ID and gnum_isvalid=a.gnum_isvalid ),' ') as Parent,"+
            "NVL(GSTR_URL,' ') as URL,GNUM_ISVALID FROM GBLT_MENU_MST  A) ";
			 
action = "  GNUM_MENU_LEVEL!=0 ";

//Header 

headlist.add("Menu Name");
headlist.add("Menu Level");
headlist.add("Parent");
headlist.add("Url");

//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Menu Name","INITCAP(GSTR_MENU_NAME)");
sortmap.put("Menu Level","INITCAP(GNUM_MENU_LEVEL)");

//Searching Field
Map searchmap = new HashMap();
searchmap.put("Menu Name","GSTR_MENU_NAME");
searchmap.put("Menu Level","GNUM_MENU_LEVEL");
searchmap.put("Parent","Parent");

%>
<%@ include file="filenewB.jsp"%>
