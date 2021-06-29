

<%@page import="usermgmt.umgmtGlobal"%>
<jsp:useBean id="beanpage" class="usermgmt.masters.UmgmtRoleMstBean"
	scope="request">
	<jsp:setProperty name="beanpage" property="*" />
</jsp:useBean>

<%@include file="fileA.jsp"%>
<script>
	window.history.forward() ;
	</script>

<%

/*   These are the fields to be filled by the developer*/

isValid			="GBL_ISVALID";

record_per_page	=	10 ;
no_of_fields	=	2 ;
no_of_primary_keys=	2 ;
page_per_block	=   10;


hospitalCode  = "b.GNUM_HOSPITAL_CODE";
pagename     =	"umgmtRole_lstMst.jsp";
nextpage     =	"umgmtRole_cntMst.jsp";
previouspage =	"listing_training.jsp";

title         = "Role";
table_heading = "Role Master";
       

query1	=	"	SELECT  initcap(b.GSTR_ROLE_NAME),(select initcap(a.GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST a "+ 
			"	where b.GNUM_MODULE_ID =a.GNUM_MODULE_ID "+
			//" and a.gnum_hospital_code=b.gnum_hospital_code "+
			") module_name "+
	 		"	 from GBLT_ROLE_MST b  ";  

//query1	=	"	SELECT  initcap(b.GSTR_ROLE_NAME),(select initcap(a.GSTR_MODULE_NAME) from GBLT_METATABLE_TYPE_MST a "+ 
//"	where b.GNUM_MODULE_ID =a.GNUM_MODULE_ID and a.gnum_hospital_code=100) module_name "+
	//"	 from GBLT_ROLE_MST b  ";
	  


query2	=	" SELECT GNUM_ROLE_ID,GNUM_MODULE_ID  " + 
			" FROM GBLT_ROLE_MST b" ;

//action	=	" b.gnum_hospital_code='"+hospitalCode+"'";
			 
//Header 
//headlist.add("#");
headlist.add("Role Name");
headlist.add("Module Name");
	

//Sorting Field
Map sortmap = new HashMap();
sortmap.put("Role Name","UPPER(GSTR_ROLE_NAME)");


//Searching Field
Map searchmap = new HashMap();
searchmap.put("Role Name","GSTR_ROLE_NAME");

%>

<%@ include file="fileB_withoutStatus.jsp"%>


