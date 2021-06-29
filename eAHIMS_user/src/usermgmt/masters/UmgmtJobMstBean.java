
package usermgmt.masters;
import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;

public class UmgmtJobMstBean extends FuncLib
{
	private String 	combo1		 	 =	"";
	private int     job_code;
	private String  job_name   		 =	"";
	private String  last_run_date  	 =	"";
	private int 	isvalid=1;
	private String 	next_run_date  	 =	"";
	private String 	status        	 =	"";
	private String hospitalcode="";
	boolean retValue;
	private String[] chk;

 
	public String getNext_run_date() {
		return next_run_date;
	}

	public void setNext_run_date(String next_run_date) {
		this.next_run_date = next_run_date;
	}

	public String getLast_run_date() {
		return last_run_date;
	}

	public void setLast_run_date(String last_run_date) {
		this.last_run_date = last_run_date;
	}

	public String getJob_name() {
		return job_name;
	}

	public void setJob_name(String job_name) {
		this.job_name = job_name;
	}

	public int getIsvalid()
    {
        return isvalid;
    }

    public String getStatus()
	{
		return status;
	}

    public void setCombo1( java.lang.String combo1 )
 	{
 		 this.combo1 = combo1;
  	}

    public void setChk(String[] chk0)
	{
		chk = chk0;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	
    public void setIsvalid(int valid)
    {
        isvalid = valid;
    }

	public java.lang.String getCombo1( )
 	{
  		return combo1;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}
	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}

    /**
		other methods
	*/

		 
	public void initializeNewMode()
	{
		job_name	=	"";
	}
    
    public List initializeUpdateMode() throws Exception
	{
		String query ="";
		List l1=new ArrayList();
		job_code = Integer.parseInt(chk[0].substring(0,chk[0].length()-1));

        query =	"	SELECT GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME),"+
			   	"	to_char(GBLT_GROUP_MST.GDT_EFFECT_DATE,'DD-Mon-YYYY'),GNUM_SEAT_ID"+
			   	" 	FROM GBLT_GROUP_MST"+
				" 	where GNUM_HOSPITAL_CODE='"+this.hospitalcode+"' and  GNUM_GROUP_CODE="+job_code;

		HisResultSet rst = getRecord(query);
		if(rst.next())
		{
			//group_name  = rst.getString(2);
			//effect_date = rst.getString(3);
		    //seatId=rst.getString(4);
        }
		//l1.add(group_name);
		//l1.add(effect_date);
		
		return l1;
	}


	
}
