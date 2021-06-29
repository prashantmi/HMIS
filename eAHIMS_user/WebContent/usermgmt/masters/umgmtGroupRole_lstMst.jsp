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
 ## Previous Form(Calling)				: umgmtGroupRole_cntMst.jsp
 ## Functions Used					: No
 ## Name of Tables used for reference 	            : GBLT_GROUP_MST,GBLT_ROLE_MST,GBLT_METATABLE_TYPE_MST,GBLT_GROUP_ROLE_MST
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
<jsp:useBean id="beanpage"
	class="usermgmt.masters.UmgmtGroupRoleMstBean" scope="request">
	<jsp:setProperty name="beanpage" property="*" />
</jsp:useBean>


<%@ include file="fileA.jsp"%>

<script>
	window.history.forward() ;
	</script>
<%

/*   These are the fields to be filled by the developer*/

record_per_page	  =	10 ;
no_of_fields	  =	3 ;
no_of_primary_keys=	1 ;
page_per_block	=   10;
isValid				="GNUM_ISVALID";

pagename     =	"umgmtGroupRole_lstMst.jsp";
nextpage     =	"umgmtGroupRole_cntMst.jsp";
previouspage =	"listing_training.jsp";

title         = "Role";
table_heading = "Group Role Master";
//hospitalCode=" d.GNUM_HOSPITAL_CODE ";   
String hosCodeValue = (String)session.getAttribute("HOSPITAL_CODE");

query1	=	" SELECT  initcap(d.group_name),initcap(d.module_name),initcap(d.role_name)"+
			" FROM "+
			"(  SELECT A.GNUM_GROUP_CODE,a.GNUM_MODULE_ID,A.GNUM_ROLE_ID,A.gnum_isvalid,( select initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_GROUP_CODE = A.GNUM_GROUP_CODE and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) group_name, "+
    		 "  ( select initcap(GSTR_ROLE_NAME) from  GBLT_ROLE_MST where  GNUM_ROLE_ID = A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.gnum_module_id and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) role_name,"+ 
      		" ( select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID = A.GNUM_MODULE_ID"+
      				//" and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
      				") module_name "+
     		 " FROM GBLT_GROUP_ROLE_MST A where a.gnum_hospital_code ='"+hosCodeValue+"' ) d " ;


//query1	=	" SELECT  initcap(d.group_name),initcap(d.module_name),initcap(d.role_name)"+
//" FROM "+
//"(  SELECT A.GNUM_GROUP_CODE,a.GNUM_MODULE_ID,A.GNUM_ROLE_ID,A.gnum_isvalid,( select initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_GROUP_CODE = A.GNUM_GROUP_CODE and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) group_name, "+
 //"  ( select initcap(GSTR_ROLE_NAME) from  GBLT_ROLE_MST where  GNUM_ROLE_ID = A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.gnum_module_id and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) role_name,"+ 
	//" ( select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID = A.GNUM_MODULE_ID and GNUM_HOSPITAL_CODE=100 ) module_name "+
	// " FROM GBLT_GROUP_ROLE_MST A where a.gnum_hospital_code ='"+hosCodeValue+"'  "+
	// "and A.GNUM_GROUP_CODE in (select gnum_group_code from gblt_group_mst where"+ 
     //  " gnum_hospital_code = '"+hosCodeValue+"' and gnum_isvalid=1)) d " ;
System.out.println("query1	----->"+ query1	);

query2	=	" SELECT GNUM_GROUP_CODE||'^'||  GNUM_ROLE_ID||'^'|| GNUM_MODULE_ID" + 
			" FROM "+
			"(  SELECT A.GNUM_GROUP_CODE,a.GNUM_MODULE_ID,A.GNUM_ROLE_ID,A.gnum_isvalid,( select initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_GROUP_CODE = A.GNUM_GROUP_CODE and gnum_hospital_code = a.gnum_hospital_code) group_name, "+
		     "  ( select initcap(GSTR_ROLE_NAME) from  GBLT_ROLE_MST where  GNUM_ROLE_ID = A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.gnum_module_id and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) role_name,"+ 
		      " ( select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID = A.GNUM_MODULE_ID"+
		    		  //"and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "+
		    		  ") module_name "+
		      " FROM GBLT_GROUP_ROLE_MST A where a.gnum_hospital_code ='"+hosCodeValue+"') d " ;

//query2	=	" SELECT GNUM_GROUP_CODE||'^'||  GNUM_ROLE_ID||'^'|| GNUM_MODULE_ID" + 
//" FROM "+
//"(  SELECT A.GNUM_GROUP_CODE,a.GNUM_MODULE_ID,A.GNUM_ROLE_ID,A.gnum_isvalid,( select initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST where GNUM_GROUP_CODE = A.GNUM_GROUP_CODE and gnum_hospital_code = a.gnum_hospital_code) group_name, "+
 //"  ( select initcap(GSTR_ROLE_NAME) from  GBLT_ROLE_MST where  GNUM_ROLE_ID = A.GNUM_ROLE_ID and GNUM_MODULE_ID =a.gnum_module_id and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE ) role_name,"+ 
 // " ( select initcap(GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST where GNUM_MODULE_ID = A.GNUM_MODULE_ID and GNUM_HOSPITAL_CODE=100) module_name "+
 // " FROM GBLT_GROUP_ROLE_MST A where a.gnum_hospital_code ='"+hosCodeValue+"' "+
 // "and A.GNUM_GROUP_CODE in (select gnum_group_code from gblt_group_mst where"+ 
//  " gnum_hospital_code = '"+hosCodeValue+"' and gnum_isvalid=1)) d " ;
System.out.println("query2	----->"+ query2	);
action	=	"";
			 
//ordervar=" order by d.group_name ";	

//Header 
//headlist.add("#");
headlist.add("Group Name");
headlist.add("Module Name");
headlist.add("Role Name");

//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Group Name","group_name");
sortmap.put("Role Name","role_name");
sortmap.put("Module Name","module_name");

//Searching Field
Map searchmap = new HashMap();
searchmap.put("Role Name","d.role_name");

%>


<%@ include file="fileB_withoutStatus.jsp"%>
