 /***************************Start of program*****************************\

 ## Copyright Information				: C-DAC, Noida 2008-2009 
 ## Client's Name						: Delhi
 ## Project Name						: AHIMS
 ## Phase								: Development
 ## Name of Developer		 			: Renuka Singh
 ## Module Name							: User Management
 ## Date of creation					: 12-11-08
 ## Purpose								: Admin change password
 ## Previous Form(Calling)				: login.jsp
 ## Functions Used						: Getter & Setters of variables
 ## Name of Tables used for reference 	: 
 ## Name of Tables used for data updation/insertion	: GBLT_HOSPITAL_MST
 ## Next Form	(Called)				: 
 ## Date of Modification				: 
 ## Unit Tested By	& Date				: Renuka Singh & 17-11-2008
 ## Comment	after Test				:
     1). All front end formats followed (Y/N)              :Y
 			if No then Detail   		:
     2). All functions working properly (Y/N)             :Y
 			if No then Detail   	            :
     3). Is there some Java Script Error (Y/N)              :N
 			if YES then Detail   	                        :
     4). Connections/ recordsets used properly (Y/N)  :Y
 				if No then Detail   	:
     5). All Standard nomenclatures used (Y/N) 	:Y
 				if No then Detail	:
     6). Internal documentation done (Y/N)                 :Y
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
 
*/   
 
package usermgmt.masters;

import usermgmt.*;
import java.sql.*;
import java.util.*;
import HisGlobal.*;
import usermgmt.masters.umgmtSessionConfigXmlHandler;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import login.*;

public class HospitalMstBean extends FuncLib{
	
	private String hospitalcode="";
	private String hospitalName = "";			
	private String hospitalShortName = "";		
	private String address1 = "";			
	private String address2 = "";		
	private String city = "";			 
	private String state = "";
	private String district = "";
	private String pinCode = "";

	private String phoneNo = "";			
	private String faxNo = "";		
	private String emailId = "";			
	private String contactPerson = "";		
	private String hospitalType = "";	
	private String busRouteNo = "";			
	private String bedCapacity = "";		
	private String status = "";	
	
	private String weekHourFrom = "";
	private String weekMinFrom = "";
	private String weekHourTo = "";
	private String weekMinTo = "";	
	
	private String satHourFrom = "";
	private String satMinFrom = "";
	private String satHourTo = "";
	private String satMinTo = "";
	
	private String lunchHourFrom = "";
	private String lunchMinFrom = "";
	private String lunchHourTo = "";
	private String lunchMinTo = "";
	
	private String seatId="";
	private String hmode="";
	boolean retValue = true;
	private String userId =	"";
	private String password = "";


	//added by Anshul Vaid on 10 July 2012 for Auysh
	
	private String strNoOfLicences="";
	
	
	

	public String getStrNoOfLicences() {
		return strNoOfLicences;
	}

	public void setStrNoOfLicences(String strNoOfLicences) {
		this.strNoOfLicences = strNoOfLicences;
	}

	public String getWeekHourFrom() {
		return weekHourFrom;
	}

	public void setWeekHourFrom(String weekHourFrom) {
		this.weekHourFrom = weekHourFrom;
	}

	public String getWeekMinFrom() {
		return weekMinFrom;
	}

	public void setWeekMinFrom(String weekMinFrom) {
		this.weekMinFrom = weekMinFrom;
	}

	public String getWeekHourTo() {
		return weekHourTo;
	}

	public void setWeekHourTo(String weekHourTo) {
		this.weekHourTo = weekHourTo;
	}

	public String getWeekMinTo() {
		return weekMinTo;
	}

	public void setWeekMinTo(String weekMinTo) {
		this.weekMinTo = weekMinTo;
	}

	public String getSatHourFrom() {
		return satHourFrom;
	}

	public void setSatHourFrom(String satHourFrom) {
		this.satHourFrom = satHourFrom;
	}

	public String getSatMinFrom() {
		return satMinFrom;
	}

	public void setSatMinFrom(String satMinFrom) {
		this.satMinFrom = satMinFrom;
	}

	public String getSatHourTo() {
		return satHourTo;
	}

	public void setSatHourTo(String satHourTo) {
		this.satHourTo = satHourTo;
	}

	public String getSatMinTo() {
		return satMinTo;
	}

	public void setSatMinTo(String satMinTo) {
		this.satMinTo = satMinTo;
	}

	public String getLunchHourFrom() {
		return lunchHourFrom;
	}

	public void setLunchHourFrom(String lunchHourFrom) {
		this.lunchHourFrom = lunchHourFrom;
	}

	public String getLunchMinFrom() {
		return lunchMinFrom;
	}

	public void setLunchMinFrom(String lunchMinFrom) {
		this.lunchMinFrom = lunchMinFrom;
	}

	public String getLunchHourTo() {
		return lunchHourTo;
	}

	public void setLunchHourTo(String lunchHourTo) {
		this.lunchHourTo = lunchHourTo;
	}

	public String getLunchMinTo() {
		return lunchMinTo;
	}

	public void setLunchMinTo(String lunchMinTo) {
		this.lunchMinTo = lunchMinTo;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalShortName() {
		return hospitalShortName;
	}

	public void setHospitalShortName(String hospitalShortName) {
		this.hospitalShortName = hospitalShortName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFaxNo() {
		return faxNo;
	}

	public void setFaxNo(String faxNo) {
		this.faxNo = faxNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getHospitalType() {
		return hospitalType;
	}

	public void setHospitalType(String hospitalType) {
		this.hospitalType = hospitalType;
	}

	public String getBusRouteNo() {
		return busRouteNo;
	}

	public void setBusRouteNo(String busRouteNo) {
		this.busRouteNo = busRouteNo;
	}

	public String getBedCapacity() {
		return bedCapacity;
	}

	public void setBedCapacity(String bedCapacity) {
		this.bedCapacity = bedCapacity;
	}

	public boolean isRetValue() {
		return retValue;
	}

	public void setRetValue(boolean retValue) {
		this.retValue = retValue;
	}

	public String getHospitalcode() {
		return hospitalcode;
	}

	public void setHospitalcode(String hospitalcode) {
		this.hospitalcode = hospitalcode;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getSeatId() {
		return seatId;
	}

	public void setSeatId(String seatId) {
		this.seatId = seatId;
	}

	public String getHmode() {
		return hmode;
	}

	public void setHmode(String hmode) {
		this.hmode = hmode;
	}
	public String getUserId() {
		return ((userId==null)?" ":userId);
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
/////Function for changing the Hospital Details
	
	public String getStateCombo()
	{	
		String COUNTRY_CODE_INDIA="IND";
		
		String html = "";
		//String query = "SELECT distinct gnum_statecode ,gstr_statename FROM gblt_state_mst WHERE gnum_countrycode = '"+COUNTRY_CODE_INDIA+"' AND GNUM_HOSPITAL_CODE='100' and GNUM_ISVALID=1 order by  gstr_statename  ";
		String query = "SELECT distinct gnum_state_code ,gstr_state_name FROM gblt_state_mst WHERE gstr_country_code = '"+COUNTRY_CODE_INDIA+"' and GNUM_ISVALID=1 order by  gstr_state_name  "; 

		System.out.println("getStateCombo query---------"+query);
		try
		{
			html = super.getCombo("",query,this.state,"",0);
			//System.out.println("state value in combo--------"+this.state);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getGroupCombo() "+ex);
		}
		return html;
	}
	
	//Added to show the District Combo by Singaravelan on 28-Nov-13
	public String getDistrictCombo(String stateCode)
	{	
		//String COUNTRY_CODE_INDIA="IND";
		
		String html = "";
		String query = "SELECT distinct gnum_district_code,gstr_district_name FROM gblt_district_mst WHERE GNUM_STATE_CODE="+stateCode+" AND GNUM_ISVALID=1 order by gstr_district_name "; 

		System.out.println("getDistrictCombo query---------"+query);
		try
		{
			//if(stateCode!=null)
				html = super.getCombo("",query,this.district,"",0);
			//System.out.println("state value in combo--------"+this.state);
		}
		catch(Exception ex)
		{
			System.out.println("Exception there in getGroupCombo() "+ex);
		}
		return html;
	}
	
	

	
//For getting the Hospital Data	
	
	public void setHospitalDetails() throws Exception
	{
		try{
		HisMethods connect = new HisMethods();
	
		String strPass="";
		String[] arrayTime=null;
		String[] arrayTimeFrom=null;
		String[] arrayTimeTo=null;
		
//		String strQuery = " SELECT GSTR_PASSWORD FROM GBLT_HOSPITAL_MST WHERE "+
//		  " GNUM_HOSPITAL_CODE = '"+this.hospitalcode+"' ";
//		HisResultSet rs1=super.getRecord(strQuery);
	
/*String selectQry =" SELECT nvl(gstr_hospital_name,'') as hospitalName, nvl(gstr_hosp_short_name,' ') as hospShortName, nvl(GSTR_USER_NAME,' ') as userId, nvl(GSTR_PASSWORD,' ') as password, nvl(gstr_hospital_add1,' ') as addr1, " +
		" nvl(gstr_hospital_add2,' ') as addr2, nvl(gstr_city,' ') as city, nvl(gnum_state_code,0) as state, nvl(gstr_phone,' ') as phone, nvl(gstr_fax,' ') as fax, " + 
		" nvl(gstr_email,' ') as email, nvl(gstr_contact_person,' ') as contactPerson,nvl(gnum_hospital_type,0)as hospType, nvl(gstr_busroutes_no,' ') as busRouteNo,nvl(gstr_bed_capacity,' ') as bedCapacity, nvl(gstr_weekdays_timing,' ') as weekDayTimings,nvl(gstr_sat_timing,' ') as satTimings, " +
		" nvl(gstr_lunch_break,' ') as lunchTimings, gnum_hospital_code,nvl(GNUM_USR_LICENCE_ALLOWED,'') as strNoOfLicences " +
        " FROM GBLT_HOSPITAL_MST_TMP  WHERE gnum_hospital_code = '"+this.hospitalcode+"' ";
		System.out.println("selectQry......."+selectQry);*/
		
		
		String selectQry =	"SELECT GNUM_HOSPITAL_CODE as hospitalCode, nvl(GSTR_USER_NAME,' ') as userId , nvl(GSTR_PASSWORD,' ') as password, nvl(gstr_hospital_name,'') as hospitalName, nvl(gstr_hosp_short_name,' ') as hospShortName, nvl(gstr_hospital_add1,' ') as addr1, " +
		" nvl(gstr_hospital_add2,' ') as addr2, nvl(gstr_city,' ') as city, nvl(gnum_state_code,0) as state, nvl(gstr_phone,' ') as phone, nvl(gstr_fax,' ') as fax, " + 
		" nvl(gstr_email,' ') as email, nvl(gstr_contact_person,' ') as contactPerson,nvl(gnum_hospital_type,0)as hospitalType, nvl(gstr_busroutes_no,' ') as busRouteNo,nvl(gstr_bed_capacity,' ') as bedCapacity, nvl(gstr_weekdays_timing,' ') as weekDayTimings,nvl(gstr_sat_timing,' ') as satTimings, " +
		" nvl(gstr_lunch_break,' ') as lunchTimings, nvl(GNUM_USR_LICENCE_ALLOWED,0) as strNoOfLicences," +
		" GNUM_ISVALID as isValid,nvl(num_dist_id,0) as district,decode(gnum_pin_code,NULL,' ',gnum_pin_code) as pinCode " +
        " FROM GBLT_HOSPITAL_MST WHERE gnum_hospital_code = "+this.hospitalcode;
		System.out.println("update  initialize query"+selectQry);
		
		HisResultSet rst=super.getRecord(selectQry);
		
		//Setting the values of the hospital details
		/*while(rs.next())
		{
			this.setHospitalName(rs.getString(1).trim());
			this.setHospitalShortName(rs.getString(2).trim());
			this.setUserId(rs.getString(3));
			this.setPassword(rs.getString(4));
			this.setAddress1(rs.getString(5).trim());
			this.setAddress2(rs.getString(6).trim());
			this.setCity(rs.getString(7).trim());
			this.setState(rs.getString(8).trim());
			this.setPhoneNo(rs.getString(9).trim());
			this.setFaxNo(rs.getString(10).trim());
			this.setEmailId(rs.getString(11).trim());
			this.setContactPerson(rs.getString(12).trim());
			this.setHospitalType(rs.getString(13).trim());
			this.setBusRouteNo(rs.getString(14).trim());
			this.setBedCapacity(rs.getString(15).trim());
			this.setStrNoOfLicences(rs.getString(20).trim());
		
		
		
			
			
			
		if(rs.getString(14)!=null &&  rs.getString(14).contains(":") &&  rs.getString(14).contains("#"))
		{
			arrayTime=rs.getString(14).split("#");
			
			arrayTimeFrom=arrayTime[0].split(":");
			
			arrayTimeTo=arrayTime[1].split(":");
			
			this.setWeekHourFrom(arrayTimeFrom[0]);
			this.setWeekMinFrom(arrayTimeFrom[1]);
			this.setWeekHourTo(arrayTimeTo[0]);
			this.setWeekMinTo(arrayTimeTo[1]);
		}
		
		
		if(rs.getString(15).trim()!=null &&  rs.getString(15).contains(":") &&  rs.getString(15).contains("#"))
		{
			arrayTime=rs.getString(15).split("#");
			
			arrayTimeFrom=arrayTime[0].split(":");
			
			arrayTimeTo=arrayTime[1].split(":");
			
			this.setSatHourFrom(arrayTimeFrom[0]);
			this.setSatMinFrom(arrayTimeFrom[1]);
			this.setSatHourTo(arrayTimeTo[0]);
			this.setSatMinTo(arrayTimeTo[1]);
		}	
	
		
		if(rs.getString(16).trim()!=null &&  rs.getString(16).contains(":") &&  rs.getString(16).contains("#"))
		{	
			arrayTime=rs.getString(16).trim().split("#");
			
			arrayTimeFrom=arrayTime[0].trim().split(":");
			
			arrayTimeTo=arrayTime[1].trim().split(":");
			
			this.setLunchHourFrom(arrayTimeFrom[0].trim());
			this.setLunchMinFrom(arrayTimeFrom[1].trim());
			this.setLunchHourTo(arrayTimeTo[0].trim());
			this.setLunchMinTo(arrayTimeTo[1].trim());
		}	
			

		
		
	}*///while closed
		
		if(rst.next())
		{
			//this.hospitalcode = rst.getString(1);
			System.out.println("update  hoscode"+this.hospitalcode);
			this.userId =rst.getString(2);
			this.password = rst.getString(3);
			this.hospitalName = rst.getString(4);
			this.hospitalShortName= rst.getString(5);
			this.address1= rst.getString(6);
			this.address2 = rst.getString(7);
			this.city=rst.getString(8);
			this.state= rst.getString(9);
			this.phoneNo=rst.getString(10);
			this.faxNo=rst.getString(11);
			this.emailId=rst.getString(12);
			this.contactPerson=rst.getString(13);
			this.hospitalType=rst.getString(14);
			this.busRouteNo=rst.getString(15);
			this.bedCapacity=rst.getString(16);
			this.district=rst.getString(22);
			this.pinCode=rst.getString(23).trim();

			 this.strNoOfLicences=rst.getString(20);
			  if(rst.getString(17)!=null &&  rst.getString(17).contains(":") &&  rst.getString(17).contains("#"))
				{
					arrayTime=rst.getString(17).split("#");
					
					arrayTimeFrom=arrayTime[0].split(":");
					
					arrayTimeTo=arrayTime[1].split(":");
					if(arrayTimeFrom.length==0 ||arrayTimeTo.length==0)
					{
						this.setWeekHourFrom("");
						  this.setWeekMinFrom("");
						  this.setWeekHourTo("");
						  this.setWeekMinTo(""); 
					}
					else{
					this.setWeekHourFrom(arrayTimeFrom[0]);
					this.setWeekMinFrom(arrayTimeFrom[1]);
					this.setWeekHourTo(arrayTimeTo[0]);
					this.setWeekMinTo(arrayTimeTo[1]);}
				}
				
				if(rst.getString(18)!=null &&  rst.getString(18).contains(":") &&  rst.getString(18).contains("#"))
				{
					arrayTime=rst.getString(18).split("#");
					
					arrayTimeFrom=arrayTime[0].split(":");
					
					arrayTimeTo=arrayTime[1].split(":");
					if(arrayTimeFrom.length==0 ||arrayTimeTo.length==0)
					{
						this.setSatHourFrom("");
						  this.setSatMinFrom("");
						  this.setSatHourTo("");
						  this.setSatMinTo(""); 
					}
					else{
					this.setSatHourFrom(arrayTimeFrom[0]);
					this.setSatMinFrom(arrayTimeFrom[1]);
					this.setSatHourTo(arrayTimeTo[0]);
					this.setSatMinTo(arrayTimeTo[1]);}
				}	
				
				if(rst.getString(19)!=null &&  rst.getString(19).contains(":") &&  rst.getString(19).contains("#"))
				{	
					arrayTime=rst.getString(19).split("#");
					
					arrayTimeFrom=arrayTime[0].split(":");
					
					arrayTimeTo=arrayTime[1].split(":");
					if(arrayTimeFrom.length==0 ||arrayTimeTo.length==0)
					{
						 this.setLunchHourFrom("");
						  this.setLunchMinFrom("");
						  this.setLunchHourTo("");
						  this.setLunchMinTo(""); 
					}
					else
					{
					this.setLunchHourFrom(arrayTimeFrom[0]);
					this.setLunchMinFrom(arrayTimeFrom[1]);
					this.setLunchHourTo(arrayTimeTo[0]);
					this.setLunchMinTo(arrayTimeTo[1]);}
				}	
        }	
}
catch(Exception e){
			e.printStackTrace();
			System.out.println("Exception Occured"+e);
		}
		
		
	}
//For Updating the Hospital Master	
	
public boolean  updateHospitalMaster(HttpServletRequest request) throws Exception
{
	try{
	HisMethods connect = new HisMethods();
	String strPass="";
	String status="";
    
  this.hospitalcode=(String)request.getSession().getAttribute("HOSPITAL_CODE"); 
  this.seatId=(String)request.getSession().getAttribute("SEAT_ID");
    
	String weekdayTimings=this.weekHourFrom+":"+this.weekMinFrom+"#"+this.weekHourTo+":"+this.weekMinTo;
	String satTimings=this.satHourFrom+":"+this.satMinFrom+"#"+this.satHourTo+":"+this.satMinTo;
	String lunchTimings=this.lunchHourFrom+":"+this.lunchMinFrom+"#"+this.lunchHourTo+":"+this.lunchMinTo;
	String userpassword=this.password;
	//String userpassword= SecurityUtil.encrypt(this.password);
	
	this.hospitalName=checkApostrophe(this.hospitalName);
	//if(this.pinCode=="")
		//this.pinCode="0";
	String strUpdateQry =" update  gblt_hospital_mst set  GSTR_HOSPITAL_NAME='"+this.hospitalName+"' ," +
			" GSTR_USER_NAME='"+this.userId+"', GSTR_PASSWORD='"+userpassword+"',"+
			" GSTR_HOSPITAL_ADD1='"+this.address1+"', GSTR_HOSPITAL_ADD2='"+this.address2+"'," +
			" GSTR_CITY='"+this.city+"', GNUM_STATE_CODE='"+this.state+"', NUM_DIST_ID='"+this.district+"'," +
			" GSTR_PHONE='"+this.phoneNo+"', GSTR_FAX='"+this.faxNo+"', GSTR_EMAIL='"+this.emailId+"', " +
			" GSTR_CONTACT_PERSON='"+this.contactPerson+"', " +
			" GNUM_PIN_CODE=NVL('"+this.pinCode+"',NULL), " +
			" GSTR_HOSP_SHORT_NAME='"+this.hospitalShortName+"', GDT_LSTMOD_DATE=SYSDATE, " +
			" GNUM_LSTMOD_SEATID='"+this.seatId+"',GNUM_HOSPITAL_TYPE='"+this.hospitalType+"'," +
			" GSTR_BUSROUTES_NO='"+this.busRouteNo+"', GSTR_BED_CAPACITY='"+this.bedCapacity+"', " +
			" GSTR_WEEKDAYS_TIMING='"+weekdayTimings+"', GSTR_SAT_TIMING='"+satTimings+"'," +
			" GSTR_LUNCH_BREAK='"+lunchTimings+ "',gnum_usr_licence_allowed= '"+this.strNoOfLicences +"'  WHERE gnum_hospital_code = '"+this.hospitalcode+"' "; 
		
		
	System.out.println("UpdateQry......."+strUpdateQry);
	retValue = connect.updateRecord(strUpdateQry);
	System.out.println("retValue......."+retValue);
	
	if(retValue)
		this.status="Hospital Details Modifed  Sucessfully !";
	else
		this.status="Error||";
	
	}catch(Exception e){
		System.out.println("Exception Occured"+e);
	}
	
	return retValue;
}

public String checkApostrophe(String name)
{
	int indx=name.indexOf('\'');
	if(indx>-1){
		name = new StringBuilder(name).insert(indx+1, "'").toString();
	}
	return name;
}

public String getDistrict() {
	return district;
}

public void setDistrict(String district) {
	this.district = district;
}

public String getPinCode() {
	return pinCode;
}

public void setPinCode(String pinCode) {
	this.pinCode = pinCode;
}






		

}//End of Class
