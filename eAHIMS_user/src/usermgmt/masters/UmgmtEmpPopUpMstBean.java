

package usermgmt.masters;
import HisGlobal.HisResultSet;
import usermgmt.*;

public class UmgmtEmpPopUpMstBean extends FuncLib
{
	public  String[] chk;			
	private String searchText		=	"";
	private String searchCombo		=	"";
	private String hospitalCode	="";
	
	
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

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}
	
	
	public String getEmpInfo(String searchText,String searchCombo)
	{
		
		if(searchCombo.equals("1"))
			//commented by amit on 110908
			//searchCombo="PSRSTR_EMP_NO";
			searchCombo="STR_EMP_NO";
		if(searchCombo.equals("2"))
			//commented by amit on 110908
			//searchCombo="PSRSTR_FNAME || PSRSTR_MNAME||PSRSTR_LNAME";
			searchCombo="STR_EMP_FULL_NAME";
		HisResultSet rs=null;
		String query="";
		String html=null;
		try
		{
			html =	"	<tr><td class='addHeaderNew'><b>Employee Id</td><td class='addHeaderNew'><b>Employee Name</td><td class='addHeaderNew'><b>User Id</td></tr>";
			//commented by amit on 110908
			/*query =	"	SELECT psrstr_emp_no,(initcap(PSRSTR_FNAME) ||' '|| initcap(PSRSTR_MNAME)||' '||initcap(PSRSTR_LNAME))fullname"+
					"	FROM psrt_employee_mst a  WHERE UPPER("+searchCombo+") LIKE upper('"+searchText+"%') ";	
		*/
			
			
			//Commment by ankur on 20th jul 09
			
			/*query =	"	SELECT  STR_EMP_NO,(initcap(STR_FIRST_NAME) ||' '|| initcap(STR_MIDDLE_NAME)||' '||initcap(STR_LAST_NAME))fullname, "+
					"   nvl((SELECT str_desig_name FROM pist_designation_MST	WHERE num_desig_id = (select num_desig_id from pist_emp_cur_detail_dtl "+ 
					"   where STR_EMP_NO=a.STR_EMP_NO and GNUM_ISVALID=1 and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"')),'--') desig, " +
					"   nvl((   select GSTR_USER_NAME from GBLT_USER_MST where PSRSTR_EMP_NO=a.str_emp_no and gnum_isvalid='1'),'NA') userid "+
					"	FROM pist_emp_personnel_dtl a  WHERE UPPER("+searchCombo+") LIKE upper('"+searchText+"%') and a.GNUM_ISVALID=1 and " +
					"   a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and TRUNC(a.GDT_EFFECT_DATE)<=TRUNC(SYSDATE) ";
		*/	
		
			///Query chenged on 09 nov 2009 due to single row subquery error
			
/*	query =	"	SELECT  STR_EMP_NO,(initcap(STR_FIRST_NAME) ||' '|| initcap(STR_MIDDLE_NAME)||' '||initcap(STR_LAST_NAME))fullname, "+
			"   nvl((SELECT distinct str_desig_name FROM pist_designation_MST	WHERE num_desig_id = (select num_desig_id from pist_emp_cur_detail_dtl "+ 
			"   where STR_EMP_NO=a.STR_EMP_NO and GNUM_ISVALID=1 and GNUM_HOSPITAL_CODE='"+this.hospitalCode+"')),'--') desig, " +
			"	nvl(gstr_user_name,'NA') as userid "+
			"	FROM pist_emp_personnel_dtl a ,gblt_user_mst b where " +
			"	psrstr_emp_no(+) = a.str_emp_no  AND b.gnum_isvalid(+) = '1' and  b.gnum_hospital_code(+)=a.gnum_hospital_code and " +
			" 	UPPER("+searchCombo+") LIKE upper('"+searchText+"%') and a.GNUM_ISVALID=1 and " +
			"   a.GNUM_HOSPITAL_CODE='"+this.hospitalCode+"' and TRUNC(a.GDT_EFFECT_DATE)<=TRUNC(SYSDATE) ";
*/	
			
	
	
	/* query = "  SELECT str_emp_no,(   INITCAP (str_first_name)  || ' '  || INITCAP (str_middle_name)  || ' '  || INITCAP (str_last_name)  ) fullname, "+
			"  NVL ((SELECT str_desig_name  FROM pist_designation_mst   WHERE num_desig_id =a.num_desig_id "+
			"   AND gnum_isvalid = 1  AND gnum_hospital_code = '"+this.hospitalCode+"'   AND (gdt_effective_frm <= TRUNC (SYSDATE)  AND NVL (gdt_effective_to, SYSDATE + 1) >=TRUNC (SYSDATE) )),'--' ) desig,"+ 
			"  NVL (gstr_user_name, 'NA') AS userid   FROM pist_emp_entry_dtl a, gblt_user_mst b  WHERE psrstr_emp_no(+) = a.str_emp_no  AND b.gnum_isvalid(+) = '1'   AND b.gnum_hospital_code(+) = a.gnum_hospital_code and  "+
			"  UPPER ("+searchCombo+") LIKE  UPPER ('"+searchText+"%') AND a.gnum_isvalid = 1 AND a.gnum_hospital_code = '"+this.hospitalCode+"'  AND TRUNC (a.gdt_effect_date) <= TRUNC (SYSDATE)";   
    */

/* Employee data fetch from pist_emp_registration_dtl table */			
	query = "  SELECT str_emp_no,( INITCAP (str_emp_full_name)  ) fullname, "+
			"  NVL ((SELECT gstr_desig_name  FROM gblt_designation_mst   WHERE gnum_desig_code =a.gnum_desig_code "+
			"   AND gnum_isvalid = 1  AND gnum_hospital_code = '"+this.hospitalCode+"' ),'--' ) desig,"+ 
			"  NVL (gstr_user_name, 'NA') AS userid   FROM pist_emp_registration_dtl a, gblt_user_mst b  WHERE psrstr_emp_no(+) = a.str_emp_no  AND b.gnum_isvalid(+) = '1'   AND b.gnum_hospital_code(+) = a.gnum_hospital_code and  "+
			"  UPPER ("+searchCombo+") LIKE  UPPER ('"+searchText+"%') AND a.gnum_isvalid = 1 AND a.gnum_hospital_code = '"+this.hospitalCode+"' ";   
	
	System.out.println("Employee Search Query....."+query);
			
			
			int i=0;
			rs=super.getRecord(query);
			if(rs.next())
			{
				rs.previous();
				while(rs.next())
				{
				i++;
					String empPopNo=rs.getString(1);
					String uPopName=rs.getString(2);
					String uPopDesig=rs.getString(3);
					String uPopUserid=rs.getString(4);
					String condata=empPopNo+"^"+uPopName+"^"+uPopDesig+"^"+uPopUserid;
					
					
	
			System.out.println("uPopUserid -------->"+uPopUserid);
				if(uPopUserid.equals("NA")   )
					{
					uPopUserid="";
						System.out.println(" inside if uPopUserid -------->"+uPopUserid);
					html +="<tr>";
					html +="<td class='adddatavalueNew'><input type='radio'  name='chk' id='chk"+i+"' value='"+i+"' onClick='ivalue(this,"+i+");'>"+empPopNo+"</td><td class='adddatavalueNew'>"+uPopName+"</td><td class='adddatavalueNew'>"+uPopUserid+" <input type='hidden' name='ncondata' value='"+condata+"'></td>";
					html +="</tr>";
				    }
					else
					{   System.out.println(" inside else uPopUserid -------->"+uPopUserid);
						html +="<tr>";
						html +="<td class='adddatavalueNew'><input type='radio' disabled name='chk' id='chk"+i+"' value='"+i+"' onClick='ivalue(this,"+i+");'>"+empPopNo+"</td><td class='adddatavalueNew'>"+uPopName+"</td><td class='adddatavalueNew'>"+uPopUserid+" <input type='hidden' name='ncondata' value='"+condata+"'></td>";
						html +="</tr>";
					 }	
						
						
					
				}//while closed
			}//if closed
			else
			{
				html="<tr><td colspan='2' class='tdfont' width'100%'><div align='center'><b><font color='#FF0000'>No Employee found</font></b></div></td></tr>";
			}
		
		}
		catch(Exception e)
		{
			System.out.println("Exception is"+e);
		}
		return html;
	}

	
}


