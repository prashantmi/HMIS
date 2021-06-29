package usermgmt.masters;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

import HisGlobal.HisResultSet;

import usermgmt.FuncLib;


public class umgmtSeatMasterBean extends FuncLib
{
	
	private String combo1		  =	"";
	private String seatName = "";
	private String seatDesc = "";
	private String dept = "";
	private String group = "";
	private String effectDate = "";
	private String hmode = "";
	private String seatId = "";
	private String status = "";
	private String hl7Code = "";
	private String hospCode="";
	private String[] chk = null;
	private String userSeatId = "";
	private String strHtlmlModi="";
	private String isvalid = "1";
	private String prevGroup = "";
	private String groupName="";
	private String deptName="";
	private String ipadd1[]=null;
	private String ipadd2[]=null;
	private String ipadd3[]=null;
	private String ipadd4[]=null;
	private String sizeMultiRow="";
	private String ipAddress = "";
	private String ipBound = "";
	
	
	public String getCombo1() {
		return combo1;
	}

	public void setCombo1(String combo1) {
		this.combo1 = combo1;
	}

	
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public void setSeatName( java.lang.String seatName )
	{
		this.seatName = seatName;
	}

	public java.lang.String getSeatName( )
	{
		return seatName;
	}
	
	

	public void setPrevGroup( java.lang.String prevGroup )
	{
		this.prevGroup = prevGroup;
	}

	public java.lang.String getPrevGroup( )
	{
		return prevGroup;
	}
	

	public void setIsvalid( java.lang.String isvalid )
	{
		this.isvalid = isvalid;
	}

	public java.lang.String getIsvalid( )
	{
		return isvalid;
	}
	

	public void setUserSeatId( java.lang.String userSeatId )
	{
		this.userSeatId = userSeatId;
	}

	public java.lang.String getUserSeatId( )
	{
		return userSeatId;
	}

	public java.lang.String[] getChk( )
	{
		return chk;
	}

	public void setChk( java.lang.String[] chk )
	{
		this.chk = chk;
	}

	public void setHl7Code( java.lang.String hl7Code )
	{
		this.hl7Code = hl7Code;
	}

	public java.lang.String getHl7Code( )
	{
		return hl7Code;
	}

	public void setStatus( java.lang.String status )
	{
		this.status = status;
	}

	public java.lang.String getStatus( )
	{
		return status;
	}

	public java.lang.String getSeatId( )
	{
		return seatId;
	}

	public void setSeatId( java.lang.String seatId )
	{
		this.seatId = seatId;
	}
	

	public void setSeatDesc( java.lang.String seatDesc )
	{
		this.seatDesc = seatDesc;
	}

	public java.lang.String getSeatDesc( )
	{
		return seatDesc;
	}

	public void setHmode( java.lang.String hmode )
	{
		this.hmode = hmode;
	}

	public java.lang.String getHmode( )
	{
		return hmode;
	}

	public void setGroup( java.lang.String group )
	{
		this.group = group;
	}

	public java.lang.String getGroup( )
	{
		return group;
	}

	public void setEffectDate( java.lang.String effectDate )
	{
		this.effectDate = effectDate;
	}

	public java.lang.String getEffectDate( )
	{
		return effectDate;
	}

	public void setDept( java.lang.String dept )
	{
		this.dept = dept;
	}
	public java.lang.String getDept( )
	{
		return dept;
	}
	
	
	public java.lang.String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(java.lang.String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	

	public java.lang.String getIpBound() {
		return ipBound;
	}

	public void setIpBound(java.lang.String ipBound) {
		this.ipBound = ipBound;
	}

	/**
	 * This function is used to set initial parameters for department combo 
	 * @return String
	 */
	public String getDeptCombo()
	{
		
		String html = "";
		String query = "select GNUM_DEPT_CODE, initcap(GSTR_DEPT_NAME) from GBLT_DEPARTMENT_MST	where " +
				" GNUM_ISVALID ='1'  and " +
				" GNUM_HOSPITAL_CODE='" +this.hospCode +"'  order by initcap(GSTR_DEPT_NAME)";
		
		
		System.out.println("Department  is "+query);
		
		
		try
		{
			html = super.getCombo("",query,this.dept,"",0);
		}
		catch(Exception e)
		{
		} 
		return html;
	}
	/**
	 * This function is used to set initial parameters for group combo
	 * @return String 
	 */
	public String getGroupCombo()
	{
		String query = "select GNUM_GROUP_CODE, initcap(GSTR_GROUP_NAME) from GBLT_GROUP_MST"+
		" where  GNUM_ISVALID ='1'  and TRUNC(GDT_EFFECT_DATE) <= TRUNC(SYSDATE) and " +
		" GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' " +
		"order by initcap(GSTR_GROUP_NAME)";
		
		
		System.out.println("Group  is "+query);
		String html = "";
		try
		{
			html = super.getCombo("",query,this.group,"",0);
		}
		catch(Exception e)
		{
		} 
		return html;
	}
	/**
	 * This function is used check whether entered seat name exist in the database or not 
	 * @return true if duplicate name exist otherwise return false.
	 */
	public boolean checkRedundentName()
	{
		HisResultSet rs=null;
		String query="";
		boolean result=false;
		query= "select  GSTR_SEAT_NAME from GBLT_SEAT_MST WHERE GSTR_SEAT_NAME='"+this.seatName+"' " +
				" and GNUM_HOSPITAL_CODE='" +this.getHospCode() +"'";
		try
		{
			rs=super.getRecord(query);
			if( rs != null )
			{	
				if(!rs.next())
					result=true; 
				else 
					result = false ;
			}
			else
				result=true; 
			}
		
		catch(Exception e)
		{
			result=false;
			System.out.println("Exception is "+e);
		}
		return result;
	}
	/**
	 * This function is used to insert data into data base
	 * @return true if new record is to be inserted otherwise return false
	 */
	public boolean insertRecord()
	{
		System.out.println("inside save function   is "+this.hospCode);
		String seatname	="";
		boolean retValue = true;
		String query  = new String();
		String ipQuery= new String();
		Connection conn = null;
		CallableStatement cs = null;
		String ipadd[]=new String[ipadd1.length-1];
		String isIpValid[]=new String[ipadd.length];
		int size=ipadd1.length-1;
		if(size!=1)
		{System.out.println("inside save function  2 "+this.hospCode);
			for(int i=0;i<this.ipadd1.length-1;i++)
			{
				ipadd[i]=this.ipadd1[i]+"."+this.ipadd2[i]+"."+this.ipadd3[i]+"."+this.ipadd4[i];
			}
		}
		else
		{System.out.println("inside save function  3 "+this.hospCode);
			if(this.ipadd1[0].equals(""))
			{
				ipadd[0]="";
			}
			else
			{
				ipadd[0]=this.ipadd1[0]+"."+this.ipadd2[0]+"."+this.ipadd3[0]+"."+this.ipadd4[0];
			}
		}
		query=  " select  GSTR_SEAT_NAME from GBLT_SEAT_MST WHERE UPPER(GSTR_SEAT_NAME) =UPPER('"+this.seatName+"')" +
				" and GNUM_GROUP_CODE='"+this.group+"' and GNUM_ISVALID>0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode();
		
		if(this.ipAddress=="" || this.ipAddress.equals("") )
		{
		ipQuery=  " select  GNUM_IPADDRESS from GBLT_SEAT_MST WHERE GNUM_IPADDRESS IS NULL " +
				" and GNUM_ISVALID>0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode();
		}
		else
		{
			ipQuery=  " select  GNUM_IPADDRESS from GBLT_SEAT_MST WHERE GNUM_IPADDRESS ='"+this.ipAddress+"' " +
			" and GNUM_ISVALID>0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode();
			
		}
	
		System.out.println("query-----> "+query);
		
		System.out.println(ipQuery);
		
		boolean redundant = isRedundentName(query);
		boolean redundantIp=true;
		if(this.ipAddress=="" || this.ipAddress.equals("") )
		{
			redundantIp=false;
		}
		else
		{
		redundantIp = isRedundentName(ipQuery);
		}
		System.out.println("redundant---> "+redundant);
		System.out.println("redundant IP---> "+redundantIp);
		/*for(int i=0;i<ipadd.length;i++)
		{
			query="select count(*) from GBLT_SEAT_IPADDRESS_DTL where GNUM_IPADDRESS='"+ipadd[i] +"' and GNUM_HOSPITAL_CODE=108";
			int temp=isRedundentIpAddress(query);
			isIpValid[i]=temp+"";
			
		}	
		for(int i=0;i<isIpValid.length;i++)
		{
			if(Integer.parseInt(isIpValid[i])>=1)
			{
				status="IP address Already Exist,Please Enter Other IP address";
				return false;
			}
		}*/
		
		System.out.println("inside save function 4 "+this.hospCode);
		if(!redundant && (!redundantIp))
        {
		
			if(this.seatName!=null)
			{	if((this.seatName.length())<2)
				seatname	=this.seatName.substring(0,1).toUpperCase();
				else
					seatname=this.seatName.substring(0,1).toUpperCase()+this.seatName.substring(1).toLowerCase();
			}
			else
			{
				seatname ="";
				
			}
			
		try
		{System.out.println("inside save function   5 "+this.hospCode);
			
			conn = super.getConnection();
			conn.setAutoCommit(false);
			String sql="{call seat_ins(?::Numeric,?,?::Numeric,?::Numeric,?,to_date(?,'dd-Mon-yyyy'),?::Numeric,?::Numeric,?::Numeric,?)}";
			cs = conn.prepareCall(sql);
			String newSeatId = generateSeatId();
			System.out.println("seat id   5 "+newSeatId);
			cs.setString(1,newSeatId);
			cs.setString(2,seatname);
			cs.setString(3,this.group);
			cs.setString(4,this.dept);
			cs.setString(5,this.seatDesc);
			cs.setString(6,this.effectDate);
			cs.setString(7,this.seatId);			
			cs.setString(8,this.isvalid);	
			cs.setString(9,this.getHospCode());
			if(this.getIpAddress()=="" || this.getIpAddress().equals("") )
				cs.setString(10,null);
			else
				cs.setString(10,this.getIpAddress());
			//cs.executeQuery();
			  cs.execute();
			conn.commit();
			//conn.setAutoCommit(true);
			System.out.println(size);
			if(size==1)
			{System.out.println("inside save function  6 "+this.hospCode);
				if(!(ipadd[0].equals("")))
				{
					sql="{call seat_ipaddress_ins(?::Numeric,?,?::Numeric,?::Numeric,?::timestamp)}";
					cs=conn.prepareCall(sql);
					cs.setString(1, newSeatId);
					cs.setString(2, ipadd[0]);
					cs.setString(3,this.seatId);
					cs.setString(4,this.getHospCode());
					cs.setString(5,this.effectDate);
					cs.execute();
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
			else
			{System.out.println("inside save function  7"+this.hospCode);
			sql="{call seat_ipaddress_ins(?::Numeric,?,?::Numeric,?::Numeric,?::timestamp)}";
				for(int i=0;i<ipadd.length;i++)
				{
					cs=conn.prepareCall(sql);
					cs.setString(1, newSeatId);
					cs.setString(2, ipadd[i]);
					cs.setString(3,this.seatId);
					cs.setString(4,this.getHospCode());
					cs.setString(5,this.effectDate);
					  cs.execute();
					conn.commit();
					conn.setAutoCommit(true);
				}
			}
			
		}
		catch(Exception e)
		{
			this.status = "Error while Inserting Record(s) "+e;
			retValue = false;
			try
			{
				conn.rollback();
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}
		}
		finally
		{
			try
			{
				if(cs!=null)
					cs.close();		
				
					
				if(conn!=null)
					conn.close();
					
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}			
		}		
        }
        else 
        {
        	retValue=false;
        	status="Seat Name or IpAddress Already Exist,Please Enter Other Seat Name or IpAddress";
        }
		
		System.out.println("inside save function   last "+retValue);
		return retValue;
	}
	/**
	 * This function is to generate new seat Id 
	 * @return String
	 */
	private String generateSeatId()
	{
		String query = " select nvl((max(GNUM_SEATID)+1),'10001') from GBLT_SEAT_MST  " +
				       " where GNUM_HOSPITAL_CODE='" +this.getHospCode() +"'";
		
		System.out.println("seat id----"+query);
		List lst = null;
		try
		{
			lst = super.getDetail(query);
		}
		catch(Exception e)
		{
			
		}
		
		return lst.get(0).toString();
	}
	/**
	 * This function is used to initialize initial parameters for target JSP 
	 */
	public void initializeNewMode()
	{
		this.seatName = "";
		this.seatDesc = "";
		this.dept = "";
		this.group = "";
		this.effectDate = "";
		this.hl7Code = "";
		this.setStatus("Record is successfully saved");
		
	}
	/**
	 * This function is used to fetch initial details from the database that are to be displayed on modify page.
	 * @return List
	 */
	public List initializeUpdateMode()
	{
		HisResultSet rst=null;
		HisResultSet rstIp=null;
		List lst = new ArrayList();
		String tempSeatId = this.chk[0].substring(0,this.chk[0].length()-1);
		String strIpAddTemp[][]=null;
		int counter=0;
		String query  = null;
		String queryIp=null;
		StringBuffer br=new StringBuffer();
		
		query=	"SELECT  gstr_seat_name, gstr_seat_description,TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy'),"+
				"(SELECT distinct a.gstr_group_name FROM gblt_group_mst a WHERE"+ 
			 	" a.gnum_group_code = ( SELECT gnum_group_code  FROM  gblt_seat_mst WHERE  gnum_seatid=" + tempSeatId +
			 		" and GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE  and GNUM_HOSPITAL_CODE='"+this.hospCode+"' ))GROUP_NAME,gnum_dept_code, gnum_group_code, gnum_isvalid,NVL(GNUM_IPADDRESS,'')ipAddress"+
			 		" FROM gblt_seat_mst where GNUM_SEATID = "+tempSeatId+" and GNUM_HOSPITAL_CODE="+this.getHospCode();
					
		System.out.println("initializeUpdateMode------->"+query);
		
		queryIp="select GNUM_IPADDRESS from GBLT_SEAT_IPADDRESS_DTL where GNUM_SEATID="+tempSeatId+" and GNUM_ISVALID=1 and GNUM_HOSPITAL_CODE="+this.getHospCode();
		
		/*query=	"	SELECT  gstr_seat_name, gstr_seat_description,TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy'),"+
				"	("+
				"	SELECT distinct a.gstr_group_name FROM gblt_group_mst a, gblt_seat_mst b WHERE " +
				"	a.gnum_group_code =( SELECT gnum_group_code  FROM  gblt_seat_mst WHERE  gnum_seatid ="+tempSeatId+")"+
				"	)" +
				"	GROUP_NAME,"+
				"	GNUM_HL7_CODE," +
				"	SELECT distinct a.GSTR_DEPT_NAME FROM GBLT_DEPARTMENT_MST a, gblt_seat_mst b WHERE "+
				
				
				"	gnum_dept_code, gnum_group_code, gnum_isvalid"+
				"	FROM gblt_seat_mst where GNUM_SEATID = '"+tempSeatId+"'";
		
		
		
		*/
		try
		{
		rst = super.getRecord(query);
		rstIp=super.getIpAddressModi(queryIp);
		}
		catch(Exception e)
		{}
		if(rst.next())
		{
			this.userSeatId	=	tempSeatId;
			this.seatName  	=	rst.getString(1);
			this.seatDesc  	=	rst.getString(2);
			this.effectDate =	rst.getString(3);
			this.groupName	=	rst.getString(4);
			if(rst.getString(5)==null)
			{
				this.hl7Code	=	"";
			
			}
			else
			this.dept 		= 	rst.getString(5);
			this.group 		= 	rst.getString(6);		
			this.isvalid	= 	rst.getString(7);		
			this.ipAddress  =   rst.getString(8);
		}
		System.out.println("this.ipAddress...."+this.ipAddress);
		
		strIpAddTemp=new String[rstIp.size()][];
		while(rstIp.next())
		{
			strIpAddTemp[counter]=rstIp.getString(1).replace(".", "#").split("#");
			counter++;
		}
		
		this.setSizeMultiRow(rstIp.size()+"");
		for(int i=0;i<rstIp.size();i++)
		{
		
				br.append("<div id='id1-"+(i+1)+"' >");
				br.append("<table width='100%' align='center' cellspacing='1px'>");
				br.append("<tr>\n");
				br.append("<td width='90%' class='label'>\n");
				br.append("<div align='center'>\n");
				br.append("	<input type='text' name='ipadd1' id='ipadd11-"+(i+1)+"' maxlength='3' onkeypress='return validateData(event,5)' onblur='return checkIpSize(this);' class='textbox_small' value='");
				br.append(strIpAddTemp[i][0]+"'>.\n");
				br.append("	<input type='text' name='ipadd2' id='ipadd21-"+(i+1)+"' maxlength='3' onkeypress='return validateData(event,5)' onblur='return checkIpSize(this);' class='textbox_small' value='");
				br.append(strIpAddTemp[i][1]+"'>.\n");
				br.append("	<input type='text' name='ipadd3' id='ipadd31-"+(i+1)+"' maxlength='3' onkeypress='return validateData(event,5)' onblur='return checkIpSize(this);' class='textbox_small' value='");
				br.append(strIpAddTemp[i][2]+"'>.\n");
				br.append("	<input type='text' name='ipadd4' id='ipadd41-"+(i+1)+"'maxlength='3' onkeypress='return validateData(event,5)' onblur='return checkIpSize(this);' class='textbox_small' value='");
				br.append(strIpAddTemp[i][3]+"'>\n");
				br.append("</div>\n");	
				br.append("</td>\n");		
				br.append("<td width='10%' class='control'><img src='../../images/minus.gif' onClick=deleteRow('1-"+(i+1)+"','1','1');>\n");	
				br.append("</td>\n");
				br.append("</tr>\n");
				br.append("</table>");
				br.append("</div>");
		}
		this.setStrHtlmlModi(br.toString());
		lst.add(this.seatName);
		lst.add(this.seatDesc);
		lst.add(this.effectDate);
		lst.add(this.groupName);
		lst.add(this.hl7Code);
		lst.add(this.dept);
		lst.add(this.group);
		lst.add(this.isvalid);
		lst.add(this.ipAddress);
		lst.add(this.getStrHtlmlModi());
		return lst;
	}
	/**
	 * This Function is used to initialize parameters that are to be displayed on the view page.
	 * @return List 
	 */
	public List initializeUpdateModeView()
	{
		HisResultSet rst=null;
		HisResultSet rstIp=null;
		List lst = new ArrayList();
		String tempSeatId = this.chk[0].substring(0,this.chk[0].length()-1);
		String strIpAddTemp[][]=null;
		int counter=0;
		String query  = null;
		String queryIp=null;
		StringBuffer br=new StringBuffer();
		query=	"SELECT  gstr_seat_name, gstr_seat_description,TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy'),"+
				"(SELECT distinct a.gstr_group_name FROM gblt_group_mst a WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and "+ 
			 	" a.gnum_group_code = ( SELECT gnum_group_code  FROM  gblt_seat_mst WHERE  GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and gnum_seatid=" + tempSeatId 
			 		+	"))GROUP_NAME,gnum_dept_code, gnum_group_code, gnum_isvalid, GNUM_IPADDRESS"+
			 		" FROM gblt_seat_mst where GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and  GNUM_SEATID = "+tempSeatId;
					
		
		queryIp="select GNUM_IPADDRESS from GBLT_SEAT_IPADDRESS_DTL where GNUM_SEATID="+tempSeatId +" and GNUM_ISVALID=1 and GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' ";
		
		/*query=	"	SELECT  gstr_seat_name, gstr_seat_description,TO_CHAR (gdt_effect_date, 'dd-Mon-yyyy'),"+
				"	("+
				"	SELECT distinct a.gstr_group_name FROM gblt_group_mst a, gblt_seat_mst b WHERE " +
				"	a.gnum_group_code =( SELECT gnum_group_code  FROM  gblt_seat_mst WHERE  gnum_seatid ="+tempSeatId+")"+
				"	)" +
				"	GROUP_NAME,"+
				"	GNUM_HL7_CODE," +
				"	SELECT distinct a.GSTR_DEPT_NAME FROM GBLT_DEPARTMENT_MST a, gblt_seat_mst b WHERE "+
				
				
				"	gnum_dept_code, gnum_group_code, gnum_isvalid"+
				"	FROM gblt_seat_mst where GNUM_SEATID = '"+tempSeatId+"'";
		
		
		
		*/
		
		
		try
		{
		rst = super.getRecord(query);
		rstIp=super.getIpAddressModi(queryIp);
		}
		catch(Exception e)
		{}
		if(rst.next())
		{
			this.userSeatId	=	tempSeatId;
			this.seatName  	=	rst.getString(1);
			this.seatDesc  	=	rst.getString(2);
			this.effectDate =	rst.getString(3);
			this.groupName	=	rst.getString(4);
			
			if(rst.getString(5)==null)
			{
				this.hl7Code	=	"";
			
			}
			else
			this.dept 		= 	rst.getString(5);
			this.group 		= 	rst.getString(6);		
			this.isvalid	= 	rst.getString(7);
			if(rst.getString(8)==null)
			{
				this.ipAddress  =   "";
			}
			else
				this.ipAddress  =   rst.getString(8);
			
		}
		strIpAddTemp=new String[rstIp.size()][];
		while(rstIp.next())
		{
			strIpAddTemp[counter]=rstIp.getString(1).replace(".", "#").split("#");
			counter++;
		}
		br.append("<table width='100%' cellspacing='1px' align='center'> ");
		br.append("<tr>");
		br.append("<td class='label' colspan='2'><div align='center'>IP Address</div></td>");
		br.append("<td class='control' colspan='2'></td>");
		br.append("</tr>");
		if(rstIp.size()==0){
			strIpAddTemp=new String[1][1];
		}
		for(int i=0;i<rstIp.size();i++)
		{
			  
			   	br.append("<tr>\n");
			   	br.append("<td width='90%' class='label' colspan='2'>\n");
				br.append("<div align='center'>\n");
				br.append("	<input type='text' name='ipadd1' id='ipadd1#delIndex#' maxlength='3' onkeypress='return validateData(event,5)' class='textbox_small' value='");
				br.append(strIpAddTemp[i][0]+"'  readonly='readonly'>.\n");
				br.append("	<input type='text' name='ipadd2' id='ipadd2#delIndex#' maxlength='3' onkeypress='return validateData(event,5)' class='textbox_small' value='");
				br.append(strIpAddTemp[i][1]+"' readonly='readonly'>.\n");
				br.append("	<input type='text' name='ipadd3' id='ipadd3#delIndex#' maxlength='3' onkeypress='return validateData(event,5)' class='textbox_small' value='");
				br.append(strIpAddTemp[i][2]+"' readonly='readonly'>.\n");
				br.append("	<input type='text' name='ipadd4' id='ipadd4#delIndex#' maxlength='3' onkeypress='return validateData(event,5)' class='textbox_small' value='");
				br.append(strIpAddTemp[i][3]+"' readonly='readonly'>\n");
				br.append("</div>\n");	
				br.append("</td>\n");	
				br.append("<td class='control' colspan='2'></td>");
				br.append("</tr>\n");
		
		
		}
		br.append("</table>");
		this.setStrHtlmlModi(br.toString());
		lst.add(this.seatName);
		lst.add(this.seatDesc);
		lst.add(this.effectDate);
		lst.add(this.groupName);
		lst.add(this.hl7Code);
		lst.add(this.dept);
		lst.add(this.group);
		lst.add(this.isvalid);
		lst.add(this.ipAddress);
		lst.add(this.getStrHtlmlModi());
		return lst;
	}
	/**
	 * This function is used to update the selected record into database.
	 * @param request
	 * @return true if record is successfully updated otherwise return false
	 * @throws Exception
	 */
	public boolean updateRecord(HttpServletRequest request)throws Exception
	{
		
		String query				=	"";
		String query1				=	"";
		String query2				=	"";
		String query3				=	"";
		String ipQuery              =   "";
		String pK					=	"";
		String	oldVal				=	"";
		String	newVal				=	"";
		Connection conn 			=	null;
		PreparedStatement ps		= 	null;
		PreparedStatement psAudit 	= 	null;
		Statement stmt 				= 	null;
		boolean retValue			=	false;
		boolean	redundant			=	false;
		boolean	redundantname		=	false;
		boolean	redundantIp		=	false;
		boolean	isRun				=	false;
		CallableStatement cs = null;
		int isValid;
		List prevValuesList 		= 	prevValues();
		String ipadd[]=new String[ipadd1.length-1];
		/*for(int i=0;i<this.ipadd1.length-1;i++)
		{
			ipadd[i]=this.ipadd1[i]+"."+this.ipadd2[i]+"."+this.ipadd3[i]+"."+this.ipadd4[i];
		}*/
		
		int size=ipadd1.length-1;
		if(size!=1)
		{System.out.println("inside save function  2 "+this.hospCode);
			for(int i=0;i<this.ipadd1.length-1;i++)
			{
				ipadd[i]=this.ipadd1[i]+"."+this.ipadd2[i]+"."+this.ipadd3[i]+"."+this.ipadd4[i];
			}
		}
		else
		{System.out.println("inside save function  3 "+this.hospCode);
			if(this.ipadd1[0].equals(""))
			{
				ipadd[0]="";
			}
			else
			{
				ipadd[0]=this.ipadd1[0]+"."+this.ipadd2[0]+"."+this.ipadd3[0]+"."+this.ipadd4[0];
			}
		}
		
		pK = this.userSeatId;
		query3= "select initcap(GSTR_SEAT_NAME) from GBLT_SEAT_MST where UPPER(GSTR_SEAT_NAME)=UPPER('"+this.seatName+"')"+
				"and GNUM_ISVALID >0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode()+" and GNUM_SEATID<>"+this.getUserSeatId();
		
		ipQuery=  " select  GNUM_IPADDRESS from GBLT_SEAT_MST WHERE GNUM_IPADDRESS ='"+this.ipAddress+"' " +
		" and GNUM_ISVALID>0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode()+" and GNUM_SEATID<>"+this.getUserSeatId();
		System.out.println("query 3 "+query3);
		
		redundantname = isRedundentName(query3);
		
		if(this.ipAddress=="" || this.ipAddress.equals("") )
		{
			redundantIp=false;
		}
		else
		{
			redundantIp   = isRedundentName(ipQuery);
		}
		if(redundantname || redundantIp)
		{		
			this.status ="Seat Name or IPAddress Already Exist,Please Enter Other Seat Name or IP Address ";
			// if Record  Alredy Exist
		
				/*query3	=	"select GNUM_ISVALID from GBLT_SEAT_MST where initcap(GSTR_SEAT_NAME)=initcap('"+this.seatName+"')"+
						"and GNUM_ISVALID >0 and GNUM_ISVALID<3 and GNUM_HOSPITAL_CODE="+this.getHospCode();
		
			isValid=Integer.parseInt(this.isvalid);	
			redundant =  super.isRecordExist(query3,isValid);// Calling Funclib Method
			if(redundant)
			{
				this.status ="Seat Name Already Exist,Please Enter Other Seat Name ";
			}
			else
			{
					
					query	=	"	UPDATE GBLT_SEAT_MST SET "+
								" 	GSTR_SEAT_NAME = '"+this.seatName+"',"+
								" 	GSTR_SEAT_DESCRIPTION = '"+this.seatDesc+"',"+			
								" 	GNUM_DEPT_CODE = '"+this.dept+"',"+ 
								" 	GNUM_SEAT_ID= '"+this.seatId+"',"+					   
								" 	GDT_EFFECT_DATE= '"+this.effectDate+"',"+
								" 	GDT_ENTRY_DATE = sysdate, "+
								"	GNUM_ISVALID = '"+this.isvalid+"',"+
								" 	WHERE GNUM_SEATID = '"+this.userSeatId+"' and " +
								"   GNUM_HOSPITAL_CODE='" +this.getHospCode() +"'";
					
					
					 query1	=	"Update GBLT_SEAT_IPADDRESS_DTL set GNUM_ISVALID='0',GDT_EFFECT_TO_DATE=sysdate where GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and GNUM_SEATID='"+this.userSeatId;
					 query2 = 	"UPDATE GBLT_SEAT_ROLE_MST SET GBL_ISVALID ='"+this.isvalid+"' WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and  GNUM_SEATID = '"+this.userSeatId+"'";
					 isRun=true;
			}*/
		
		}
		else
		{
			if(this.getIpAddress()=="" || this.getIpAddress().equals("") )
			{
				 query	=	"	UPDATE GBLT_SEAT_MST SET "+
					" 	GSTR_SEAT_NAME = '"+this.seatName+"',"+
					" 	GSTR_SEAT_DESCRIPTION = '"+this.seatDesc+"',"+			
					" 	GNUM_DEPT_CODE = '"+this.dept+"',"+ 
					" 	GNUM_SEAT_ID= '"+this.seatId+"',"+					   
					" 	GDT_EFFECT_DATE= '"+this.effectDate+"',"+
					" 	GDT_ENTRY_DATE = sysdate, "+
					" 	GNUM_ISVALID = '"+this.isvalid+"',"+
					"   GNUM_IPADDRESS =NULL "+
					" 	WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and  GNUM_SEATID = '"+this.userSeatId+"'";
				
			}
			else
			{
				 query	=	"	UPDATE GBLT_SEAT_MST SET "+
							" 	GSTR_SEAT_NAME = '"+this.seatName+"',"+
							" 	GSTR_SEAT_DESCRIPTION = '"+this.seatDesc+"',"+			
							" 	GNUM_DEPT_CODE = '"+this.dept+"',"+ 
							" 	GNUM_SEAT_ID= '"+this.seatId+"',"+					   
							" 	GDT_EFFECT_DATE= '"+this.effectDate+"',"+
							" 	GDT_ENTRY_DATE = sysdate, "+
							" 	GNUM_ISVALID = '"+this.isvalid+"',"+
							"   GNUM_IPADDRESS ='"+this.ipAddress+"' "+
							" 	WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and  GNUM_SEATID = '"+this.userSeatId+"'";
				 
			}
			query1 = "Update GBLT_SEAT_IPADDRESS_DTL set GNUM_ISVALID='0',GDT_EFFECT_TO_DATE=sysdate where GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and  GNUM_SEATID='"+this.userSeatId+"'";
				 query2 = "{call seat_ipaddress_ins(?::Numeric,?,?::Numeric,?::Numeric,?::timestamp)}";
				 isRun=true;
		}
		
		if(isRun)
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			try
			{
				//stmt.execute(query);
				//conn.commit();
				stmt.execute(query1);
				stmt.addBatch(query);
				//stmt.addBatch(query1);
				//stmt.addBatch(query2);
				stmt.executeBatch();
				conn.commit();
				conn.setAutoCommit(true);
			/*	for(int i=0;i<ipadd.length;i++)
				{
					cs=conn.prepareCall(query2);
					cs.setString(1, this.userSeatId);
					cs.setString(2, ipadd[i]);
					cs.setString(3,this.seatId);
					cs.setString(4,this.getHospCode());
					cs.setString(5,this.effectDate);
					cs.executeQuery();	
					conn.commit();
					conn.setAutoCommit(true);
				}*/
				
				/*********************/
				if(ipadd.length==1)
				{System.out.println("inside save function  6 "+this.hospCode);
					if(!(ipadd[0].equals("")))
					{
						
						cs=conn.prepareCall(query2);
						cs.setString(1,  this.userSeatId);
						cs.setString(2, ipadd[0]);
						cs.setString(3,this.seatId);
						cs.setString(4,this.getHospCode());
						cs.setString(5,this.effectDate);
						//cs.executeQuery();	
						conn.setAutoCommit(false);
						cs.execute();
						conn.commit();
						conn.setAutoCommit(true);
					}
				}
				else
				{System.out.println("inside save function  7"+this.hospCode);
					
					for(int i=0;i<ipadd.length;i++)
					{
						cs=conn.prepareCall(query2);
						cs.setString(1, this.userSeatId);
						cs.setString(2, ipadd[i]);
						cs.setString(3,this.seatId);
						cs.setString(4,this.getHospCode());
						cs.setString(5,this.effectDate);
						//cs.executeQuery();
						conn.setAutoCommit(false);
						cs.execute();
						conn.commit();
						conn.setAutoCommit(true);
					}
				}
				
				
				
				/************************/
				retValue = true;
			}
			catch(Exception e)
			{
				this.status = "Error while Updating Record(s) "+e;
				System.out.println("Exception in update record "+e);
				try
				{
					conn.rollback();
				}
				catch(Exception e2)
				{
					System.out.println("Exception in rollback "+e2);
				}
			}
			finally
			{
				try
				{
					if(stmt!=null)
						stmt.close();
					
					if(ps!=null)
						ps.close();
					
					if(psAudit!=null)
						psAudit.close();
					
					if(conn!=null)
						conn.close();
					
				}
				catch(Exception e2)
				{
					System.out.println("error in finally block"+e2);
				}			
			}		
		}
		else
		{
			 System.out.println("No need to open connection");
			 this.status = " Seat Name or IP Address Already Exist , Please  Write  Other Seat Name or other IP Address......! ";
		
		}
		return retValue;
	}
	/**
	 * This function is used to delete the selected record from the database.
	 * @return String that contains status message whether given record is successfully deleted or not
	 */
	public String deleteRecord()
	{
		boolean retValue = true;
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;
		
		String message = null;
		try
		{
			conn = super.getConnection();
			conn.setAutoCommit(false);
			
		//	String query1 = "UPDATE GBLT_ROLE_SEAT_MENU_DTL SET GNUM_ISVALID ='0' WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and GNUM_SEATID = ?";
			String query2 = "UPDATE GBLT_SEAT_ROLE_MST SET GBL_ISVALID ='0' WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and GNUM_SEATID = ?";
			String query3 = "UPDATE GBLT_SEAT_MST SET GNUM_ISVALID ='0' WHERE GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and GNUM_SEATID = ?";
			
			//ps1 = conn.prepareStatement(query1);
			ps2 = conn.prepareStatement(query2);
			ps3 = conn.prepareStatement(query3);
			int counter = 0;
			
			for(int i=0;i<this.chk.length;i++)
			{
				String tempSeatId = this.chk[i].substring(0,this.chk[i].length()-1);
				
				//ps1.setString(1,tempSeatId);
				//ps1.execute();
				
				ps2.setString(1,tempSeatId);
				ps2.execute();
				
				ps3.setString(1,tempSeatId);
				ps3.execute();
				
				counter++;
			}
			conn.commit();
			conn.setAutoCommit(true);
			message = counter+" Record(s) Successfully Deleted";
			
		}
		catch(Exception e)
		{
			this.status = "Error while Deleting Record(s) "+e;
			System.out.println("Exception in update record "+e);
			retValue = false;
			try
			{
				conn.rollback();
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}
			message = "Error while Deleting Record(s)";
		}
		finally
		{
			try
			{		
				if(ps1!=null)
					ps1.close();
					
				if(ps2!=null)
					ps2.close();
				if(ps3!=null)
					ps3.close();	
					
					
				if(conn!=null)
					conn.close();
					
			}
			catch(Exception e2)
			{
				System.out.println("Exception in rollback "+e2);
			}			
		}		
		return message;
	}
	public List prevValues()
	{
		List lst = null;
		String tempSeatId = this.userSeatId;
		String query  = null;
		
		query = " select "+
				" GSTR_SEAT_NAME,GSTR_SEAT_DESCRIPTION, to_char(GDT_EFFECT_DATE,'dd-Mon-yyyy'),"+
				" GNUM_DEPT_CODE, GNUM_GROUP_CODE,  "+
				" GNUM_SEAT_ID,GNUM_ISVALID,GDT_ENTRY_DATE"+
				" from GBLT_SEAT_MST"+
				" where GNUM_HOSPITAL_CODE='" +this.getHospCode() +"' and GNUM_SEATID = '"+tempSeatId+"'";
		try
		{
			lst = super.getDetails(query,8);
		}
		catch(Exception e)
		{
			System.out.println("Exception in prevValues() "+e);
		}			
		return lst;
	}

	public String[] getIpadd1() {
		return ipadd1;
	}

	public void setIpadd1(String[] ipadd1) {
		this.ipadd1 = ipadd1;
	}

	public String[] getIpadd2() {
		return ipadd2;
	}

	public void setIpadd2(String[] ipadd2) {
		this.ipadd2 = ipadd2;
	}

	public String[] getIpadd3() {
		return ipadd3;
	}

	public void setIpadd3(String[] ipadd3) {
		this.ipadd3 = ipadd3;
	}

	public String[] getIpadd4() {
		return ipadd4;
	}

	public void setIpadd4(String[] ipadd4) {
		this.ipadd4 = ipadd4;
	}

	public String getStrHtlmlModi() {
		return strHtlmlModi;
	}

	public void setStrHtlmlModi(String strHtlmlModi) {
		this.strHtlmlModi = strHtlmlModi;
	}

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getSizeMultiRow() {
		return sizeMultiRow;
	}

	public void setSizeMultiRow(String sizeMultiRow) {
		this.sizeMultiRow = sizeMultiRow;
	}

	
	
}