
<%@ include file="fileA.jsp"%>

<%

/*   These are the fields to be filled by the developer*/

record_per_page			=	10;
no_of_fields			=	3;
no_of_primary_keys		=	1;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 

isValid				="B.GNUM_ISVALID";
pagename     =	"umgmtUserSeat_lstMst.jsp";
nextpage     =	"umgmtUserSeat_cntMst.jsp";

title         = "List Page of User-Seat Master";
table_heading = "User-Seat Master";
 
query1	=	"	select  nvl((SELECT gstr_user_name FROM gblt_user_mst WHERE GNUM_USERID = b.GNUM_USERID),'--') uname, "+
			"	(SELECT INITCAP (gstr_seat_name) FROM gblt_seat_mst WHERE gnum_seatid = b.gnum_seatid) sname,"+
			"		to_char(B.GDT_EFFECT_TO_DATE,'dd-Mon-yyyy') "+ 
			"  		FROM 	gblt_user_seat_dtl b";
			
query2	=	"  SELECT B.GNUM_USERID  "+			
			"  FROM		gblt_user_seat_dtl B";
			
			
action	=	" B.GNUM_USERID=GNUM_USERID";
			 					

//Header 
headlist.add("User Name");
headlist.add("Seat Associates");

headlist.add("Expiry Date");

//Sorting Field
Map sortmap = new HashMap();
sortmap.put("User Name","B.GNUM_USERID");
//Searching Field
Map searchmap = new HashMap();
searchmap.put("User Name","B.GSTR_USER_NAME");
%>
<%@ include file="fileB.jsp"%>

