
<%
try
{
	//set the variables
	no_of_combo = no_of_combo +1;
	his1.setVariables(no_of_fields,no_of_primary_keys,record_per_page,
	pagename,page_per_block,searchFld,searchVal,no_of_combo,strcombo,isValid);
	
	String isValCond="";
	String cmbName = "";
		
	int val = no_of_combo-1;
	
	if(strcombo[val]==null || strcombo[val].equals("")) strcombo[val] = "1";
		
	cmbName = "combo" + String.valueOf(val+1);
	
	combohdr[val] = "Status";	
	combohtml[val] = "<select  name='"+cmbName+"' class='cbo' onChange = 'submitpage(\"DEFAULT\");'>";
	combohtml[val] += his1.getIsValidCombo(strcombo[val]);
	combohtml[val] += "</select>";
					 
	
	isValCond=  isValid +" ='"+strcombo[val]+"'";
	
	String hosCodeQry = hospitalCode + " = " + (String)session.getAttribute("HOSPITAL_CODE");//Change by Gurpreet Kaur on 17NOv2008, Please contact Mr. ramneet before making any change here in this line of code
	
	if (action != "")
	{
		query1 = query1 + " WHERE " + action + " AND "+isValCond;
		query2 = query2 + " WHERE " + action + " AND "+isValCond;
		
		if( hospitalCode!=null && !hospitalCode.equals("") )
		{
        	query1 = query1 + " AND " + hosCodeQry;
			query2 = query2 + " AND " + hosCodeQry;
		}
	
        if (searchQry != "")
		{
        	query1 = query1 + " AND " + searchQry;
			query2 = query2 + " AND " + searchQry;
		}
	}
	else	
	{
		query1 = query1 + " WHERE "+isValCond;
		query2 = query2 + " WHERE "+isValCond;		
		if( hospitalCode!=null && !hospitalCode.equals("") )
		{
        	query1 = query1 + " AND " + hosCodeQry;
			query2 = query2 + " AND " + hosCodeQry;
		}
		if (searchQry != "")
		{
			query1 = query1 + " AND " + searchQry;
			query2 = query2 + " AND " + searchQry;
		}
	}
	
	
	ordervar = ordervar.trim();
	if(ordervar.equals(""))
	{
		String temp1 = String.valueOf(headlist.get(0));
		if(sortmap.containsKey(temp1))
		{
			String temp2 = String.valueOf(sortmap.get(temp1));
			query1 += " ORDER BY UPPER (" + temp2+")";
			query2 += " ORDER BY UPPER (" + temp2+")";
		}
	}
	else
	{
		query1 += " " + ordervar;
		query2 += " " + ordervar;
	}
	System.out.println("query1 inside B	----->"+ query1	);
	System.out.println("query2	inside B ----->"+ query2	);
	//for combo
	for(int i = 0; i<no_of_combo-1;i++)
	{
		cmbName = "combo" + String.valueOf(i+1);
		combohtml[i] = "<select  name='" + cmbName + "' class='cbo' onChange = 'submitpage(\"DEFAULT\");'>";
		combohtml[i] += his1.getCombo(cmbName,comboquery[i],strcombo[i],"",0);
		combohtml[i] += "</select>";
	
	}
	l1 = his1.getDetails(query1,no_of_fields);
	System.out.println("query1 for listing"+query1);
	System.out.println("query2 for listing"+query2);
	primary = his1.getDetails(query2,no_of_primary_keys);
	
	
		int totRec	= l1.size()/no_of_fields;
		int no_of_pages = his1.getNoOfPages(l1.size());
		paginationtable = his1.paginationTable(no_of_pages,var1,var2,block,totRec,page_no);
		table = his1.getTable(page_no,l1,primary,headlist,sortmap,searchmap);
	
		//for combo
		cmbHtml = his1.getComboList(combohdr, combohtml);
			
}

catch(Exception e)
{
		System.out.println("Exception "+e);
		e.printStackTrace();
		System.out.println("\nQuery1="+query1);
		System.out.println("\nQuery2="+query2);
}




/***************************************************/

%>

<html>

<head>
	<title>
		<%=title%>
	</title>
	<script language="JAVASCRIPT" src="../js/functionality.js"></script>
</head>
<!--  
 <body background="../../images/MainFlatNew.png" width="100%" topmargin="0" > -->
<body  width="100%" topmargin="0" >
<link rel="stylesheet" href="../../css/hisStyle.css" type="text/css">
<form name=form1 method=post action="<%=nextpage%>" topmargin="0">

	
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr> 
<td align="center" >
<table width='<%=dataPage_width%>' border="0" cellspacing="1" cellpadding="0">
<%out.print(his1.tableHeading(table_heading,paginationtable,cmbHtml));%>
<%out.print(table);%>
</table>
</td>
</tr>
			
<tr><%out.print(his1.toolbar(previouspage,dataPage_width));%></tr>  		 
</table>

<input type=hidden name=hprimary value=null>
<input type=hidden name=hmode>
<input type=hidden name=hcounter>

<input type=hidden name=combo1 value=<%=strcombo[0]%>>
<input type=hidden name=combo2 value=<%=strcombo[1]%>>
<input type=hidden name=combo3 value=<%=strcombo[2]%>>

<input type=hidden name=cboSearch value=<%=searchFld%>>
<input type=hidden name=txtSearch value=<%=searchVal%>>		

<input type=hidden name=query value="<%=query1%>">
<input type=hidden name=header value="<%=combohdr[0]%>">			
<input type=hidden name=header value="<%=combohdr[1]%>">
<input type=hidden name=header value="<%=combohdr[2]%>">

<input type=hidden name="page_no" value="<%=page_no%>">
<input type=hidden name="var1" value="<%=var1%>">
<input type=hidden name="var2" value="<%=var2%>">
<input type=hidden name="var3" value="<%=block%>">


</form>

</body>

</html>