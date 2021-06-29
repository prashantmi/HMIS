/**
   list of methods used in this Class

   	 1>		getCombo()
   	 2>		empNoCombo()
   	 3>		getOptions()
   	 4>		get_readonly_textfield()
   	 5>		getDetail()
   	 6>		getNextNo()
   	 7>		getNextChar()
   	 8>		getRadio()
   	 9>		getField()
   	10> 	getValue()
   	11> 	toInitCap()
   	12>		populateCombo()
	13>		sysMonthDateNumeric()
	14>		getSysDate()
*/

package usermgmt;
//import investigation.*;

import HisGlobal.*;

import java.sql.*;
import java.util.*;
//import his.longDuration.*;

public class generalQuery extends HisGlobal.HisMethods
{

	/**
	   getCombo-parameters
	   1.comboName 2.tableName 3.select fields 4.where field
	   5.selected value 6.some action like onchange
	*/
	
	public List getDetails(String query,int noofcol)throws Exception
	{
		List l1=new ArrayList();
		HisResultSet rs=getRecord(query);
		while(rs.next())
		{
			for(int i=1;i<=noofcol;i++)
				l1.add(rs.getString(i));

		}
		rs.close();
		return l1;
	}//3

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String getCombo(String comboName, String tableName, String fieldString, String condition, String selectedValue, String action)throws Exception
	{

		String 		html		= 	"";
		HisResultSet	rs			=	null;
		List 		AL_Value 	=	new ArrayList();
		//Connection 	conn		=	makeConnection();
		String		query		=	"select " + fieldString + " from " + tableName;

		if(!condition.equals(""))
			query += " where " + condition;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getCombo():\n"+se);
			System.out.println("query="+query);
		}
		try
		{
		while(rs.next())
		{
			if(fieldString.indexOf(',') != -1)
			{
				AL_Value.add((rs.getString(1)).trim());
				AL_Value.add((rs.getString(2)).trim());
			}
			else//if combo-value & combo-text is 2 b populated with the same field
			{
				AL_Value.add((rs.getString(1)).trim());
				AL_Value.add((rs.getString(1)).trim());
			}
		}

		rs.close();
		//conn.close();

		html=html+"<select name=" + comboName + " " + action + ">";
		html=html+"<option selected value=0>Select</option>";

		for(int i = 0; i < AL_Value.size(); i+=2 )
		{
			if(selectedValue.compareTo( (String)AL_Value.get(i) ) == 0 )//if equal
				html=html+"<option selected value="+AL_Value.get(i)+">"+AL_Value.get(i+1)+"</option>";
			else
				html=html+"<option value="+AL_Value.get(i)+">"+AL_Value.get(i+1)+"</option>";
		}

		html=html+"</select>";
		}
		catch(Exception e)
		{
			System.out.println("error while populating combo inside getCombo():\n");
			System.out.println("query="+query);
			e.printStackTrace();
		}

	return html;
	}//end of getCombo


	public String getCombo(String comboName, String query, String selectedValue, String action)throws Exception
	{

		String 		text		=	"";
		String 		value		=	"";
		HisResultSet	rs			=	null;
		List 		AL_Value 	=	new ArrayList();
		//Connection 	conn		=	makeConnection();
		String 		html		=	"<select name=" + comboName + " " + action + ">";

					html		+=	"<option selected value=0>Select</option>";

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getCombo():\n"+se);
			System.out.println("query="+query);
		}

		try
		{
			/*HisResultSetMetaData rsmd = rs.getMetaData();
			int noOfColumn = rsmd.getColumnCount();*/

			while(rs.next())
			{
				value = (rs.getString(1)).trim();
				text  = (rs.getString(2)).trim();

				if(selectedValue.equals(value))//if equal
					html=html+"<option selected value="+value+">"+text+"</option>";
				else
					html=html+"<option value="+value+">"+text+"</option>";
			}

			rs.close();
			//conn.close();

			html=html+"</select>";
		}
		catch(Exception e)
		{
			System.out.println("error while populating combo inside getCombo():\n");
			System.out.println("query="+query);
			e.printStackTrace();
		}

	return html;
	}//end of getCombo

	
	public String getField(String fieldName, String tableName, String condition)throws SQLException
	{

		String 		str		=	"";
		HisResultSet 	rs		=	null;
		//Connection 	conn 	= 	makeConnection();
		String 		query 	= 	"Select " + fieldName + " from " + tableName +
								" where " + condition;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("2.error while fetching data from getField():\n"+se);
		}

		if(rs.next())
			str=rs.getString(1);

		rs.close();
		//conn.close();

	return str;
	}

	public String empNoListing(String query)throws Exception
	{

		int 		i		=	3;
		String 		bgColor	= 	"";
		String 		value 	= 	"";
		String 		text  	= 	"";
		String 		html  	= 	"";
		List 		AL_Data = 	new ArrayList();

		//Connection 	conn 	= 	makeConnection();
		HisResultSet 	rs 		= 	getRecord(query);

		html	= 	"<table width='39%' border='0' cellspacing='0' cellpadding='0' align='center'>";
		html 	+=	"<tr bgcolor='#eeeeee'>";
		html 	+=	"<td width='5%' height='23' bgcolor='#eeeeee'><b></b></td>";
		html 	+=	"<td width='44%' height='23' align='center'><b>";
		html 	+=	"<input type='submit' name='EmpNo' value='Employee No' onClick='orderByFun(\"to_number(SRSTR_EMPNO)\");'>";
		html 	+=	"</b></td>";
		html 	+=	"<td width='51%' height='23' align='center'><b>";
		html 	+=	"<input type='submit' name='EmpName' value='Employee Name' onClick='orderByFun(\"SRSTR_EMPNAME\");'>";
		html 	+=	"</b></td>";
		html 	+=	"</tr>";

		while(rs.next())
		{

			if(i%2==0)
				bgColor="#eeeeee";
			else
				bgColor="#ffffff";

			value = (rs.getString(1)).trim();
			text  = (rs.getString(2)).trim();

			html 	+=	"<tr>";
			html 	+=	"<td width='5%' bgcolor='"+ bgColor +"'>";
			html 	+=	"<input type='checkbox' name='chk' value='"+value+","+text+"' onClick='checkAsRadio(this,document.form1.chk,"+i+");'>";
        	html 	+=	"</td>";
        	html 	+=	"<td width='44%' align='center' bgcolor='"+ bgColor +"'><font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>"+value+"</font></td>";
			html 	+=	"<td width='51%' bgcolor='"+ bgColor +"'><font color='#000000' face='Verdana, Arial, Helvetica, sans-serif' size='2'>"+text ;
			html 	+=	"</font></td>";
    		html 	+=	"</tr>";
    		i=i+3;
		}

		rs.close();
		//conn.close();

		html += "</table>";

	return html;
	}//end of getCombo



	public String getOptions(String tableName, String fieldString, String condition, String selectedValue, String action)throws Exception
	{

		HisResultSet	rs			=	null;
		List 		AL_Value 	=	new ArrayList();
		//Connection 	conn		=	makeConnection();
		String		query		=	"select " + fieldString + " from " + tableName;

		if(!condition.equals(""))
			query += " where " + condition;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getCombo():\n"+se);
		}

		while(rs.next())
		{
			if(fieldString.indexOf(',') != -1)
			{
				AL_Value.add((rs.getString(1)).trim());
				AL_Value.add((rs.getString(2)).trim());
			}
			else//if combo-value & combo-text is 2 b populated with the same field
			{
				AL_Value.add((rs.getString(1)).trim());
				AL_Value.add((rs.getString(1)).trim());
			}
		}

		rs.close();
		//conn.close();

		String html	= "<option selected value=0>Select</option>";

		for(int i = 0; i < AL_Value.size(); i+=2 )
		{
			if(selectedValue.compareTo( (String)AL_Value.get(i) ) == 0 )//if equal
				html=html+"<option selected value="+AL_Value.get(i)+">"+AL_Value.get(i+1)+"</option>";
			else
				html=html+"<option value="+AL_Value.get(i)+">"+AL_Value.get(i+1)+"</option>";
		}


	return html;
	}//end of getOptions

	public String get_readonly_textfield(String fieldname, String query)throws Exception
	{

		//Connection 	conn 	= 	makeConnection();
		HisResultSet	rs		=	getRecord(query);
		String	value		=	"";

		if(rs.next())
			value		=	rs.getString(1);

		rs.close();
		//conn.close();

		String	textHtml = " <input type=text name=" + fieldname +
						   " value=\"" + value +
						   "\" class='mystyle1' size=25 maxlength=25 readonly>";

	return textHtml;
	}


	public String get_readonly_textfield(String name)throws Exception
	{

		String textHtml =  " <input type=text name=" + name +
						   " value=\"\" class='mystyle1' size=25 maxlength=25 readonly>";

	return textHtml;
	}
	
	public String getNextNo(String field, String table, String def)throws Exception
	{

		String 		nextNo 	= 	"";
		HisResultSet 	rs		=	null;
		//Connection 	conn 	= 	makeConnection();
		String 		query	= 	"select max(" + field + ") from " + table;

		try
		{
			rs = getRecord(query);
			///System.out.println("rs "+rs);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getNextNo():\n"+se);
		}

		rs.next();

		nextNo = rs.getString(1);

		if(nextNo==null)
			nextNo=def;
		else
			nextNo=(Integer.parseInt(nextNo)+1)+"";

	return nextNo;
	}



	public String getNextNo(String field, String table, String def, String condition)throws Exception
	{

		String 		query  	= 	"";
		String 		nextNo 	= 	"";
		HisResultSet 	rs		=	null;
		//Connection 	conn 	= 	makeConnection();

		if(condition.compareTo("")==0)
			query = "select max(" + field + ") from " + table;
		else
			query = "select max(" + field + ") from " + table + condition;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getNextNo:\n"+se+"\nquery="+query);
		}

		rs.next();

		nextNo = rs.getString(1);

		if(nextNo==null)
			nextNo=def;
		else
			nextNo=(Integer.parseInt(nextNo)+1)+"";

	return nextNo;
	}



	public String getNextChar(String field, String table, String def)throws Exception
	{


		String 		nextNo 	= 	"";
		HisResultSet 	rs		=	null;
		//Connection 	conn 	= 	makeConnection();
		String 		query 	= 	"select max(" + field + ") from " + table;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("1.error while fetching data from getNextChar:\n"+se);
		}

		rs.next();
		nextNo = rs.getString(1);
		rs.close();

		if(nextNo == null)
			nextNo = def;
		else
		{
			query = "select ascii('" + nextNo + "') from dual";

			try{rs = getRecord(query);}
			catch(Exception se)
			{
				System.out.println("2.error while fetching data from getNextChar:\n"+se);
			}

			rs.next();
			nextNo = Integer.parseInt(rs.getString(1))+1+"";

		}

		rs.close();
		//conn.close();

	return nextNo;
	}

	public int getNext(String field, String table, int def)throws Exception
	{

		HisResultSet 	rs		=	null;
		//Connection 	conn	= 	makeConnection();
		String 		query 	= 	"select max(" + field + ") from " + table;

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("error while fetching data from getNext():\n"+se);
		}

		rs.next();

		int nextNo = Integer.parseInt(rs.getString(1));

		if(nextNo == 0)
			nextNo=def;
		else
			nextNo++;

	return nextNo;
	}

	public String getNextReqNo(String field, String table)throws Exception
	{

		String 		temp 	= 	"";
		HisResultSet 	rs		=	null;
		//Connection 	conn 	= 	makeConnection();
		String 		query 	="	select substr(to_char(sysdate,'dd-mon-yyyy'),8,4) from dual";

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("1.error while fetching data from getNextReqNo:\n"+se);
		}

		rs.next();

		String currentYear = rs.getString(1);

		query = " select max(" + field + ") from " + table +
				" where " + field + " like '" + currentYear + "%'";

		try
		{
			rs = getRecord(query);
		}
		catch(Exception se)
		{
			System.out.println("2.error while fetching data from getNextReqNo:\n"+se);
		}
		rs.next();

		int nextNo = Integer.parseInt(rs.getString(1));

		if(nextNo == 0)
			nextNo = Integer.parseInt(currentYear + "001");
		else
		{
			temp  	= 	(String.valueOf(nextNo)).substring(4);
			int no 	= 	Integer.parseInt(temp);

			no++;
			temp = String.valueOf(no);

			int len = temp.length();

			for(int i=0; i<(3-len); i++)
				temp = "0" + temp;

		}

	return temp;
	}

	public String getRadio(String radioName, String value, String chk)
	{

		String radio = new String();

		if(value.compareTo("1")==0)
			radio="<input type=radio name=" + radioName + " value=1 " + chk + ">";
		else
			radio="<input type=radio name=" + radioName + " value=0 " + chk + ">";

	return radio;
	}

	
	public String toInitCap(String mystring)
	{

		String 			temp1	=	"";
		String 			temp	=	"";
		String 			newstr	=	"";
		StringTokenizer st 		= 	new StringTokenizer(mystring," ");

		while(st.hasMoreTokens())
		{
			temp 	= 	st.nextToken();
			temp1 	= 	temp.substring(0,1).toUpperCase();
			temp1	=	temp1+temp.substring(1).toLowerCase() ;
			newstr	=	newstr+temp1+" ";
		}

	return newstr;
	}

	public String populateCombo(String query, String selectedValue)throws Exception
	{
		String 		text		=	"";
		String 		value		=	"";
		String 		html		=	"";
		
		//System.out.println("in genralQuery Query is  "+query);

		HisResultSet	rs 			= 	getRecord(query);

		try
		{
			while(rs.next())
			{
				value = (rs.getString(1)).trim();
				text  = (rs.getString(2)).trim();

				if(selectedValue.equals(value))//if equal
					html=html+"<option selected value="+value+">"+text+"</option>";
				else
					html=html+"<option value="+value+">"+text+"</option>";
			}
		}
		catch(Exception e)
		{
			System.out.println("Error query is "+query);
			System.out.println("Error1 while populating combo:<><><><><><><>genral<><><><"+e);
		}
		rs.close();
		//conn.close();

	return html;
	}//end of populateCombo

	public String populateCombo(String query, String selectedValue, Connection conn)throws Exception
	{
		String 		text		=	"";
		String 		value		=	"";
		String 		html		=	"";
		HisResultSet	rs 			= 	getRecord(query);

		try
		{
			while(rs.next())
			{
				value = (rs.getString(1)).trim();
				text  = (rs.getString(2)).trim();

				if(selectedValue.equals(value))//if equal
					html=html+"<option selected value="+value+">"+text+"</option>";
				else
					html=html+"<option value="+value+">"+text+"</option>";
			}
		}
		catch(Exception e)
		{
			System.out.println("Error while populating combo:"+e);
		}
		rs.close();

	return html;
	}//end of populateCombo
	
	public String getSysMonthDateNumeric() throws Exception
	{
		String query = new String("");
		String retValue = new String("");

		query = "SELECT TO_CHAR(SYSDATE,'YYYYMM') ";
		query += "FROM DUAL";

		HisResultSet rs = getRecord(query);
		rs.next();
		if (rs == null)
			return retValue;
		else
			return rs.getString(1);

	 }//13
	
	public String getSysdate() throws Exception
	{
		String query = new String("");
		String retValue = new String("");

		query = "SELECT TO_CHAR(SYSDATE,'DD-MON-YYYY') "+
				"FROM DUAL";

		HisResultSet rs = getRecord(query);
		rs.next();
		if (rs == null)
			return retValue;
		else
			return rs.getString(1);

	 }//14
	 
	public String getSysdate(String formate) throws Exception
	{
		String query = new String("");
		String retValue = new String("");
		
		query = "SELECT TO_CHAR(SYSDATE,'"+formate+"') "+
				"FROM DUAL";

		HisResultSet rs = getRecord(query);
		rs.next();
		if (rs == null)
			return retValue;
		else
			return rs.getString(1);

	 }//14
}//end of class
