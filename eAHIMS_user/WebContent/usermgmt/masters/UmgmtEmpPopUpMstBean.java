

package usermgmt.masters;
import HisGlobal.HisResultSet;
import usermgmt.*;

public class UmgmtEmpPopUpMstBean extends FuncLib
{
	public  String[] chk;			
	private String searchText		=	"";
	private String searchCombo		=	"";
	
	public void setChk(String[] chk0)
	{
		chk=chk0;
	}
	
	public void setSearchText(String searchText)
	{
		this.searchText=searchText;
	}
	
	public String getSearchText()
	{
		return searchText;
	}
	
	public void setSearchCombo(String searchCombo)
	{
		this.searchCombo=searchCombo;
	}
	
	public String getSearchCombo()
	{
		return searchCombo;
	}

	public String getEmpInfo(String searchText,String searchCombo)
	{
		
		if(searchCombo.equals("1"))
			searchCombo="PSRSTR_EMP_NO";
		if(searchCombo.equals("2"))
			searchCombo="PSRSTR_FNAME || PSRSTR_MNAME||PSRSTR_LNAME";
		HisResultSet rs=null;
		String query="";
		String html=null;
		try
		{
			html=	"	<tr><td class='header'><b>Employee Id</td><td class='header'><b>Employee Name</td></tr>";
			query=  " 	SELECT  PSRSTR_EMP_NO,(PSRSTR_FNAME || PSRSTR_MNAME||PSRSTR_LNAME) fullName  FROM PSRT_EMPLOYEE_MST"+
					" 	WHERE "+searchCombo+"  LIKE '"+searchText+"%'";
			
			rs=super.getRecord(query);
			if(rs.next())
			{
				rs.previous();
				while(rs.next())
				{
					String empPopNo=rs.getString(1);
					String uPopName=rs.getString(2);
					System.out.println("uPopName"+uPopName);
					html +="<tr>";
					html +="<td class='tdfont'><input type='checkbox' name='chk' value="+empPopNo+"^"+uPopName+">"+empPopNo+"</td><td class='tdfont'>"+uPopName+"</td>";
					html +="</tr>";
				}
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return html;
	}
}


