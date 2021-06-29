
<%
try
{
	//set the variables
	his1.setVariables(no_of_fields,no_of_primary_keys,record_per_page,
	pagename,page_per_block,searchFld,searchVal,no_of_combo,strcombo);
	
	String hosCodeQry = hospitalCode + " = " + (String)session.getAttribute("HOSPITAL_CODE");//Change by Gaurav Gotra on 17NOv2008, Please contact Mr. Gaurav Gotra before making any change here in this line of code
	
	//if (action != "")
	//{
	//	query1 = query1 + " WHERE " + action;
	//	query2 = query2 + " WHERE " + action;
	//	if (searchQry != "")
	//	{
	//		query1 = query1 + " AND " + searchQry;
	//		query2 = query2 + " AND " + searchQry;
	//	}
	//	}
	//	else
	//	{
	//	if (searchQry != "")
	//	{
	//		query1 = query1 + " WHERE " + searchQry;
	//		query2 = query2 + " WHERE " + searchQry;
	//	}
	//	}
	
	if (action != "")
	{
		query1 = query1 + " WHERE " + action;
		query2 = query2 + " WHERE " + action;
		
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
		query1 = query1 + " WHERE ";
		query2 = query2 + " WHERE ";		
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
	
	//for combo
	for(int i = 0; i<no_of_combo;i++)
	{
		String cmbName = "combo" + String.valueOf(i+1);
		combohtml[i] = "<select  name='" + cmbName + "' class='cbo' onChange = 'submitpage(\"DEFAULT\");'>";
		combohtml[i] += his1.getCombo(cmbName,comboquery[i],strcombo[i],"",0);
		combohtml[i] += "</select>";
	}
	
	l1 = his1.getDetails(query1,no_of_fields);
	
	
	
	System.out.println("The Size of L1"+l1.size());
	
	if(l1.size()==0)
	{
		String display="";
		display	+=	"<table><tr><td colspan='2'>&nbsp;</td>";
		display	+="<td colspan='2'><font color='red' size=3>&nbsp;No Record found !</font></td></tr></table>";
		//out.print(display);
	}
	
	primary = his1.getDetails(query2,no_of_primary_keys);
	
	//if(l1.size() > 0)
	//{
		int totRec	= l1.size()/no_of_fields;
		int no_of_pages = his1.getNoOfPages(l1.size());
	
		//paginationtable = his1.paginationTable(no_of_pages,var1,var2,block,totRec,combostr);
		paginationtable = his1.paginationTable(no_of_pages,var1,var2,block,totRec,page_no);
		//table = his1.getTable(page_no,l1,primary,headlist,sortmap,searchmap,combostr);
		table = his1.getTable(page_no,l1,primary,headlist,sortmap,searchmap);
		
		//for combo
		cmbHtml = his1.getComboList(combohdr, combohtml);
		
		 
		
	//}
	//else
	//{
		//String is="";
	//}
}

catch(Exception e)
{
		out.println("Exception "+e);
		System.out.println("\nQuery1="+query1);
		System.out.println("\nQuery2="+query2);
}

/***************************************************/

%>
<html>

<head>
<title><%=title%></title>
<script language="JAVASCRIPT" src="../js/functionality.js"></script>
</head>



<body width="100%" topmargin="0">
<link rel="stylesheet" href="../../css/hisStyle.css" type="text/css">
<form name=form1 method=post action="<%=nextpage%>" topmargin="0">


<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td align="center">
		<table width='<%=dataPage_width%>' border="0" cellspacing="1"
			cellpadding="0">
			<%out.print(his1.tableHeading(table_heading,paginationtable,cmbHtml));%>
			<%out.print(table);%>
		</table>
		</td>
	</tr>
	<!--<tr>
		<td>&nbsp;</td>
	</tr>
	--><tr>
		<td colspan='2'>
		<div class="control_button2" align='center'>
		<a class="button" href="#" onClick=' edit123();'> <span class="reset"> Reset</span></a>
		<a class="button" href="#" onClick=' unlockPassword();'> <span class="unlock"> Unlock</span></a>
			</div>
		</td>
	</tr>
</table>
<!--  *************** --> <input type=hidden name=hprimary value=null> <input
	type=hidden name=hmode> <input type=hidden name=hcounter> <input
	type=hidden name=combo1 value=<%=strcombo[0]%>> <input type=hidden
	name=combo2 value=<%=strcombo[1]%>> <input type=hidden name=combo3
	value=<%=strcombo[2]%>> <input type=hidden name=cboSearch
	value=<%=searchFld%>> <input type=hidden name=txtSearch
	value=<%=searchVal%>> <input type=hidden name=query value="<%=query1%>">
<input type=hidden name=header value="<%=combohdr[0]%>"> <input
	type=hidden name=header value="<%=combohdr[1]%>"> <input type=hidden
	name=header value="<%=combohdr[2]%>">
	
	<input type=hidden name="page_no" value="<%=page_no%>">
	<input type=hidden name="var1" value="<%=var1%>">
	<input type=hidden name="var2" value="<%=var2%>">
	<input type=hidden name="var3" value="<%=block%>">
	</form>

</body>

</html>
