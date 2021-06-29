
<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%

/*   These are the fields to be filled by the developer*/

record_per_page			=	10;
no_of_fields			=	5;
no_of_primary_keys		=	1;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 

isValid				="b.GNUM_ISVALID";
hospitalCode  =    "b.GNUM_HOSPITAL_CODE";
pagename     =	"umgmtUser_lstMst.jsp";
nextpage     =	"umgmtUser_cntMst.jsp";

title         = "List Page of User Master";
table_heading = "User Master";
 
/*query1	=	"		select GSTR_USER_NAME, "+
			"		initcap(E.GSTR_SEAT_NAME),"+
			"		to_char(B.GDT_EXPIRY_DATE,'dd-Mon-yyyy'), "+
			" DECODE (gnum_islock, '0', 'Unlocked', 'Locked')"+
			"  		FROM 	GBLT_USER_MST B ,"+
			"		GBLT_SEAT_MST E ";*/


query1=" SELECT   gstr_user_name,"+
      " NVL ((SELECT INITCAP (e.gstr_seat_name) FROM gblt_seat_mst e "+ 
      " WHERE e.gnum_seatid = b.gnum_user_seatid "+
      " AND e.gnum_hospital_code = b.gnum_hospital_code "+
      " AND e.gnum_isvalid = 1), 'No Seat Associated') gstr_seat_name,"+
      " TO_CHAR (b.gdt_expiry_date, 'dd-Mon-yyyy'), "+
      " DECODE (gnum_islock, '0', 'Unlocked', 'Locked'), "+
      " (SELECT ghsn.gstr_hosp_short_name "+
      " FROM gblt_hospital_mst ghsn " +
      " WHERE b.gnum_hospital_code = ghsn.gnum_hospital_code "+
      " AND ghsn.gnum_isvalid = 1) gstr_hospital_name "+
      " FROM gblt_user_mst b ";
      //,GBLT_SEAT_MST E ";
      
			
System.out.println("query1	------------->"+query1);			
query2	=	"  SELECT B.GNUM_USERID  "+			
			"  FROM		GBLT_USER_MST B";
			
System.out.println("query2	------------->"+query2);			
//action	=	" e.gnum_seatid = b.gnum_user_seatid  AND e.gnum_hospital_code = b.gnum_hospital_code "; 
            
			 					

//Header 
headlist.add("User Id");
headlist.add("Seat Associates");
headlist.add("Expiry Date");
headlist.add("Lock Status");
headlist.add("Hospital");
//Sorting Field
Map sortmap = new HashMap();
sortmap.put("User Id","GSTR_USER_NAME");
//Searching Field
Map searchmap = new HashMap();
searchmap.put("User Id","GSTR_USER_NAME");

%>
<%@ include file="fileB.jsp"%>

