<!--
 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2007-2008 
 ## Client's Name					: PGI
 ## Project Name					: HIMS
 ## Phase						: Development
 ## Name of Developer		 		: Mr. Ankur Mehrotra
 ## Module Name					: User Managment
 ## Date of creation					: 12/11/2008
 ## Purpose						: List Page
 ## Previous Form(Calling)				: 
 ## Functions Used					: No
 ## Name of Tables used for reference 	            : 
 ## Name of Tables used for data updation/insertion	: Dynamic
 ## Next Form	(Called)				: Modify page,View Page,Report Page Depending upon hmode
 ## Date of Modification				: No
 ## Unit Tested By	& Date				: 17/11/2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :
 				if No then Detail   	:
     7). Name of Objects Used		            	:
 		i). Object Name		 	:
 		ii) Purpose				:
 		iii). No. of times called		:
     8). Any suggestion					:
     9) Other Deviation					:
 ## Remark						:
 ## Finalization Date					:
 ## Future Alteration (1)				:
 ## Any major change
 	1) Reason					:
 	2) Time in days (Hour)			:
 	3) Change Raised By				:
 	4) Tested(Y/N)				:
 	5) Remark					:
 
-->

<%@ include file="fileA.jsp"%>
	<script>
	window.history.forward() ;
	</script>
<%
/* These are the fields to be filled by the developer*/

record_per_page			=	10;
no_of_fields			=	4;
no_of_primary_keys		=	1;
page_per_block			= 	10;
//isValid				="GNUM_ISVALID";
//hospitalCode = "GNUM_HOSPITAL_CODE";
//for combo
//no_of_combo				=	0;

pagename     =	"umgmtJob_lstMst.jsp";
nextpage     =	"umgmtJobList_cntMst.jsp";
//previouspage =	"listing_training.jsp";

title         = "Job List Master";
table_heading = "Job List Master";
       
//query1 = 	" SELECT "+
//			" NVL(initcap(GSTR_GROUP_NAME),''), "+
//			" NVL(to_char(GDT_EFFECT_DATE,'DD-Mon-YYYY'),''), "+
//			" NVL(to_char(GDT_EFFECT_DATE,'DD-Mon-YYYY'),'') ,"+
//			" NVL(to_char(GDT_EFFECT_DATE,'DD-Mon-YYYY'),'') "+
//			" FROM GBLT_GROUP_MST "; 

query1 = 	" select a.jobid, a.jobname,a.jobnextrun,a.joblastrun,b.jlgstatus "+
			" from pgagent.pga_job a,pgagent.pga_joblog b "+
			" where "+
			" to_char(a.joblastrun,'yyyy-mm-dd hh:mm')=to_char(b.jlgstart,'yyyy-mm-dd hh:mm') and"+
			" a.jobid=b.jlgjobid ";
			
		
//query2	=	"  SELECT GNUM_GROUP_CODE"+			
//			" FROM GBLT_GROUP_MST ";
			 
action	=	"";
						
					

//Header 
//headlist.add("#");
headlist.add("Job Name");
headlist.add("Last Run Date");
headlist.add("Next Run Date");
headlist.add("Status");




//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Group Name","GBLT_GROUP_MST.GSTR_GROUP_NAME");
//Searching Field
Map searchmap = new HashMap();
searchmap.put("Group Name","GBLT_GROUP_MST.GSTR_GROUP_NAME");
%>
<%@ include file="fileB_joblist.jsp"%>
