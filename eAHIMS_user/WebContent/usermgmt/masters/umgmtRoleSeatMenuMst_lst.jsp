

<%@ include file="fileA.jsp"%>

<%

/* These are the fields to be filled by the developer*/


record_per_page			=	10;
no_of_fields			=	3;
no_of_primary_keys		=	3;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 




isValid = " d.gnum_isvalid";
pagename     =	"umgmtRoleSeatMenuMst_lst.jsp";
nextpage     =	"umgmtRoleSeatMenuMst_cnt.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Role Seat Menu Master";
table_heading = "Role Seat Menu Master";


query1 =   		" SELECT  initcap(d.module_name),initcap(d.role_name),initcap(d.seat_name)"+
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
				"	  		   SELECT INITCAP (gstr_seat_name) seatname"+
				"	          FROM GBLT_SEAT_MST"+
				"	         WHERE gnum_seatid = a.gnum_seatid"+
				"	)  seat_name,"+
				"	a.gnum_isvalid"+
				"	  FROM "+
				"	  ("+
				"	  		SELECT DISTINCT gnum_role_id, gnum_module_id, gnum_seatid, gnum_isvalid"+
				"	        FROM GBLT_ROLE_SEAT_MENU_DTL"+
				"		) a"+
				"	)d";


query2 =   		" SELECT  d.gnum_module_id,d.gnum_role_id,d.gnum_seatid"+
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
				"	  		   SELECT INITCAP (gstr_seat_name) seatname"+
				"	          FROM GBLT_SEAT_MST"+
				"	         WHERE gnum_seatid = a.gnum_seatid"+
				"	)  seat_name,"+
				"	a.gnum_module_id,"+
				"	a.gnum_role_id,"+
				"	a.gnum_seatid,"+
				"	a.gnum_isvalid"+
				"	  FROM "+
				"	  ("+
				"	  		SELECT DISTINCT gnum_role_id, gnum_module_id, gnum_seatid, gnum_isvalid"+
				"	        FROM GBLT_ROLE_SEAT_MENU_DTL"+
				"		) a"+
				"	)d";





action = "";

//Header 
//headlist.add("#");
headlist.add("Module Name");
headlist.add("Role Name");
headlist.add("Seat Name");


//Sorting Field
Map sortmap = new HashMap();

//sortmap.put("Module Name","module_name");
//sortmap.put("Role Name","a.rolename");
//sortmap.put("Seat Name","a.seatname");
//sortmap.put("Menu Name","a.menu_name");

sortmap.put("Module Name","d.module_name");
sortmap.put("Role Name","d.role_name");
sortmap.put("Seat Name","d.seat_name");



//Searching Field
Map searchmap = new HashMap();

searchmap.put("Module Name","d.module_name");
searchmap.put("Role Name","d.role_name");
searchmap.put("Seat Name","d.seat_name");


%>
<%@ include file="fileB.jsp"%>