

<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%

/* These are the fields to be filled by the developer*/


record_per_page			=	10;
no_of_fields			=	3;
no_of_primary_keys		=	4;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 




isValid = " d.gnum_isvalid";
pagename     =	"umgmtRoleMenuLst_Mst.jsp";
nextpage     =	"umgmtRoleMenuCnt_Mst.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Role Menu Master";
table_heading = "Role  Menu Master";


query1=   		" SELECT  initcap(d.module_name),initcap(d.role_name),initcap(d.menuname),d.GNUM_ROLE_MENU_SLNO"+
				" FROM"+
				" ("+
				"	SELECT "+
				"	("+
				"	 	   SELECT INITCAP (gstr_module_name)"+
				"	       FROM GBLT_METATABLE_TYPE_MST"+
				"	        WHERE gnum_module_id = a.gnum_module_id"+
				"	) module_name,"+
				"	 ("+
				"	   			  SELECT INITCAP (gstr_role_name)"+
				"	          	  FROM GBLT_ROLE_MST"+
				"	        	   WHERE gnum_role_id = a.gnum_role_id"+
				"	           AND gnum_module_id = a.gnum_module_id"+
				"	) role_name,"+
				"	("+
				"	  		   SELECT INITCAP (gstr_menu_name) menuname"+
				"	          FROM GBLT_MENU_MST"+
				"	         WHERE gnum_menu_id = a.gnum_menu_id"+
				"	)  menuname,"+
				"	a.gnum_isvalid,a.GNUM_ROLE_MENU_SLNO"+
				"	  FROM "+
				"	  ("+
				"	  		SELECT DISTINCT gnum_role_id, gnum_module_id, gnum_menu_id, gnum_isvalid,GNUM_ROLE_MENU_SLNO"+
				"	        FROM GBLT_ROLE_MENU_MST"+
				"		) a"+
				"	)d";
System.out.println("query11111"+query1);

query2 =   		" SELECT  d.gnum_module_id,d.gnum_role_id,d.gnum_menu_id,d.GNUM_ROLE_MENU_SLNO"+
				" FROM"+
				" ("+
				"	SELECT "+
				"	("+
				"	 	   SELECT INITCAP (gstr_module_name)"+
				"	       FROM GBLT_METATABLE_TYPE_MST"+
				"	        WHERE gnum_module_id = a.gnum_module_id"+
				"	) module_name,"+
				"	 ("+
				"	   			  SELECT INITCAP (gstr_role_name)"+
				"	          	  FROM GBLT_ROLE_MST"+
				"	        	   WHERE gnum_role_id = a.gnum_role_id"+
				"	           AND gnum_module_id = a.gnum_module_id"+
				"	) role_name,"+
				"	("+
				"	  		   SELECT INITCAP (gstr_menu_name) menuname"+
				"	          FROM GBLT_MENU_MST"+
				"	         WHERE gnum_menu_id = a.gnum_menu_id"+
				"	)  menuname,"+
				"	a.gnum_module_id,"+
				"	a.gnum_role_id,"+
				"	a.gnum_menu_id,"+
				"	a.gnum_isvalid,"+
				"a.GNUM_ROLE_MENU_SLNO"+
				"	  FROM "+
				"	  ("+
				"	  		SELECT DISTINCT gnum_role_id, gnum_module_id, gnum_menu_id, gnum_isvalid,GNUM_ROLE_MENU_SLNO"+
				"	        FROM GBLT_ROLE_MENU_MST"+
				"		) a"+
				"	)d";


System.out.println("query22222"+query2);


action = "";

//Header 
//headlist.add("#");
headlist.add("Module Name");
headlist.add("Role Name");
headlist.add("Menu Name");


//Sorting Field
Map sortmap = new HashMap();

//sortmap.put("Module Name","module_name");
//sortmap.put("Role Name","a.rolename");
//sortmap.put("Seat Name","a.seatname");
//sortmap.put("Menu Name","a.menu_name");

sortmap.put("Module Name","d.module_name");
sortmap.put("Role Name","d.role_name");
sortmap.put("Menu Name","d.menuname");



//Searching Field
Map searchmap = new HashMap();

searchmap.put("Module Name","d.module_name");
searchmap.put("Role Name","d.role_name");
searchmap.put("Menu Name","d.menuname");


%>
<%@ include file="fileB.jsp"%>