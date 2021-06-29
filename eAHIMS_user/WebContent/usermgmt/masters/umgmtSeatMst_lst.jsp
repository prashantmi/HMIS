
<%@ include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>
<%
/* These are the fields to be filled by the developer*/
record_per_page			=	10;
no_of_fields			=	3;
no_of_primary_keys		=	1;
page_per_block			= 	10;
//for combo
no_of_combo				=	0; 

isValid = "a.GNUM_ISVALID";
hospitalCode		="a.GNUM_HOSPITAL_CODE";
pagename     =	"umgmtSeatMst_lst.jsp";
nextpage     =	"umgmtSeatMst_cnt.jsp";
previouspage =	"listing_training.jsp";

title         = "List Page of Seat Master";
table_heading = "Seat Master";


query1 = " select initcap(a.GSTR_SEAT_NAME),initcap(b.GSTR_GROUP_NAME),to_char(a.GDT_EFFECT_DATE,'dd-Mon-yyyy')"+
		" from GBLT_SEAT_MST a,"+
		" GBLT_GROUP_MST b";
		
query2 = " select a.GNUM_SEATID"+
		" from GBLT_SEAT_MST a,"+
		" GBLT_GROUP_MST b";

			 
action = " a.GNUM_GROUP_CODE = b.GNUM_GROUP_CODE 	  and a.gnum_hospital_code=b.gnum_hospital_code ";

//Header 
//headlist.add("#");
headlist.add("Seat Name");
headlist.add("Group Associated");
headlist.add("Effective Date");


//Sorting Field
Map sortmap = new HashMap();

sortmap.put("Seat Name","UPPER(a.GSTR_SEAT_NAME)");
sortmap.put("Group Associated","UPPER(b.GSTR_GROUP_NAME)");



//Searching Field
Map searchmap = new HashMap();
searchmap.put("Group Associated","b.GSTR_GROUP_NAME");
searchmap.put("Seat Name","a.GSTR_SEAT_NAME");


%>
<%@ include file="fileB_withoutStatus.jsp"%>