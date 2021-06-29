

<%@ page language="java"%>
<%@ page import = "java.util.*" %>

<jsp:useBean id = "his1" class="usermgmt.masters.MenuPage" scope="page"/>


<%
/***********************************************/
/* All the decelarations will be here only     */

int record_per_page		=	0;
int no_of_fields		=	0;
int no_of_primary_keys	=	0;
int page_no				=	1;
//for prev/next
int page_per_block		=	0;
int block				=	0;
//for combo(Max three combo allowed)
int no_of_combo			=	0;

String cmbHtml			=	"";
String isValid            ="";
String pagename			=	"";
String paginationtable	=	"";
String getPageHtml		=	"";
String table			=	"";
String tempstr			=	"";
String query1			=	"";
String query2			=	"";
//For action(where clause)
String action			=	"";
String searchQry		=	"";
//	
String table_heading	=	"";//heading of the table
String title			=	"";//title of the page
String nextpage			=	"";//on submit this jsp will be called
String previouspage	    =	"";//on cancel this jsp will be called
String ordervar			=	"";
//String queryvar			=	"";
String var1			=	"";
String var2			=	"";

//For Searching
String searchFld	=	"";
String searchVal	=	"";

//For Hospital COde
String hospitalCode =	""; 
//****************************
//Put it in Your List Page
//hospitalCode		="GNUM_HOSPITAL_CODE";// Hospital Code DB Field
//****************************/

String dataPage_width		=	"97%";
//For Color
String topColor		=	"#FEBC89";
String hdrColor		=	"#FFEBD5";
String rowColor		=	"#F1ECE2"; 
String font			=	"Trebuchet MS";	

//for combo Box
String[] comboquery	= new String[3];
String[] strcombo	= new String[3];
String[] combohdr	= new String[3];
String[] combohtml	= new String[3]; 


List l1			=	new ArrayList();
List primary	=	new ArrayList();
List headlist	=	new ArrayList();



/************* End of Decelaration block ************/
/* added */
tempstr	=	request.getParameter("page_no");
if(tempstr != null){
/* Added on 05 Feb 2007 by Mantosh Kumar for solving the problem of Status combo in case of inActive  */
	String isInActiveComb	=	"";
	isInActiveComb = request.getParameter("combo1");
	//System.out.println("mantosh test combo1="+isInActiveComb);
	if(!tempstr.equals("1")&& isInActiveComb.equals("2")){// isInActiveComb = 2 means Status = 'inActive' and page no is not equal to 1 (one).
		page_no = 1;	
	}
	else
	{
		page_no = Integer.parseInt(tempstr);
	}
	//System.out.println("page_no="+page_no);
	
}

//For sorting
var1	=	request.getParameter("var1");
var2	=	request.getParameter("var2");

//For moving next/previous
tempstr	=	request.getParameter("var3");
if (tempstr != null)
	block	=	Integer.parseInt(tempstr);

strcombo[0] = request.getParameter("combo1");
strcombo[1] = request.getParameter("combo2");
strcombo[2] = request.getParameter("combo3");

//for searching
searchFld 	= request.getParameter("cboSearch");
searchVal 	= request.getParameter("txtSearch");

if (searchFld == null || searchFld.equals("-1")) 
{
	searchFld = "-1";
	searchVal = "";
}
if (searchVal == null || searchVal.equals("")) 
{
	searchVal = "";
	searchFld = "-1";
}

if (!searchVal.equals("") && searchFld.equals("-1") == false)
	searchQry = "UPPER(" + searchFld.trim() + ") like '" + searchVal.trim().toUpperCase() + "%'"; 

for(int i=0;i<3;i++)
{
	if(strcombo[i] == null)
		strcombo[i] = "";
}


if(var1!=null)
{
	if((var1.trim()).length()>1)
		ordervar = " order by upper(" + var1+ ")";//update at 26/12/2006
}
else
	var1 = "";

if(var2!=null)
{
	ordervar = ordervar + " " + var2;
}
	else
	var2 = "";
	
//Setting the color value	
his1.setColor(topColor,hdrColor,rowColor,font);	
%>