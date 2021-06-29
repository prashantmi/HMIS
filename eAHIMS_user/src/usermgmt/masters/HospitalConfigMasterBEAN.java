package usermgmt.masters;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import login.PasswordHash;
import login.SecurityUtil;

import usermgmt.FuncLib;
import HisGlobal.AuditLog;
import HisGlobal.HisMethods;
import HisGlobal.HisResultSet;

public class HospitalConfigMasterBEAN extends FuncLib {
		
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
		private String conPwd			=	"";
		private String lunchHourFrom = "";
		private String lunchMinFrom = "";
		private String lunchHourTo = "";
		private String lunchMinTo = "";
		private String combo1		  =	"";
		private String seatId="";
		private String hmode="";
		private String userId =	"";
		private String password = "";
		boolean retValue = true;
		private String isvalid="1";
		private String[] chk;
		private String entryDate		=	"";
		private String strUserName    ="";
		private String strPassword    ="";
		
		
		//added by Anshul Vaid on 10 July 2012 for Auysh
		
		private String strNoOfLicences="";
		
		
		
		
		public String getStrNoOfLicences() {
			return strNoOfLicences;
		}

		public void setStrNoOfLicences(String strNoOfLicences) {
			this.strNoOfLicences = strNoOfLicences;
		}

		public String getCombo1() {
			return combo1;
		}

		public void setCombo1(String combo1) {
			this.combo1 = combo1;
		}

		public String getConPwd() {
			return conPwd;
		}

		public void setConPwd(String conPwd) {
			this.conPwd = conPwd;
		}
		
//		public String getEntryDate() {
//			return entryDate;
//		}
//
//		public void setEntryDate(String entryDate) {
//			this.entryDate = entryDate;
//		}

		public String[] getChk() {
			return chk;
		}
		public void setChk(String[] chk) {
			this.chk = chk;
		}
		public String getIsvalid()
		{
			return ((isvalid==null)?" ":isvalid);
		}
		public void setIsvalid(String ISVALID)
		{
			this.isvalid=ISVALID;
		}

		public String getWeekHourFrom() {
			return ((weekHourFrom==null)?" ":weekHourFrom);
		}

		public void setWeekHourFrom(String weekHourFrom) {
			this.weekHourFrom = weekHourFrom;
		}

		public String getWeekMinFrom() {
			return ((weekMinFrom==null)?" ":weekMinFrom);
		}

		public void setWeekMinFrom(String weekMinFrom) {
			this.weekMinFrom = weekMinFrom;
		}

		public String getWeekHourTo() {
			return ((weekHourTo==null)?" ":weekHourTo);
		}

		public void setWeekHourTo(String weekHourTo) {
			this.weekHourTo = weekHourTo;
		}

		public String getWeekMinTo() {
			return ((weekMinTo==null)?" ":weekMinTo);
		}

		public void setWeekMinTo(String weekMinTo) {
			this.weekMinTo = weekMinTo;
		}

		public String getSatHourFrom() {
			return ((satHourFrom==null)?" ":satHourFrom);
		}

		public void setSatHourFrom(String satHourFrom) {
			this.satHourFrom = satHourFrom;
		}

		public String getSatMinFrom() {
			return ((satMinFrom==null)?" ":satMinFrom);
		}

		public void setSatMinFrom(String satMinFrom) {
			this.satMinFrom = satMinFrom;
		}

		public String getSatHourTo() {
			return ((satHourTo==null)?" ":satHourTo);
		}

		public void setSatHourTo(String satHourTo) {
			this.satHourTo = satHourTo;
		}

		public String getSatMinTo() {
			return ((satMinTo==null)?" ":satMinTo);
		}

		public void setSatMinTo(String satMinTo) {
			this.satMinTo = satMinTo;
		}

		public String getLunchHourFrom() {
			return ((lunchHourFrom==null)?" ":lunchHourFrom);
		}

		public void setLunchHourFrom(String lunchHourFrom) {
			this.lunchHourFrom = lunchHourFrom;
		}

		public String getLunchMinFrom() {
			return ((lunchMinFrom==null)?" ":lunchMinFrom);
		}

		public void setLunchMinFrom(String lunchMinFrom) {
			this.lunchMinFrom = lunchMinFrom;
		}

		public String getLunchHourTo() {
			return ((lunchHourTo==null)?" ":lunchHourTo);
		}

		public void setLunchHourTo(String lunchHourTo) {
			this.lunchHourTo = lunchHourTo;
		}

		public String getLunchMinTo() {
			return ((lunchMinTo==null)?" ":lunchMinTo);
		}

		public void setLunchMinTo(String lunchMinTo) {
			this.lunchMinTo = lunchMinTo;
		}

		public String getHospitalName() {
			return ((hospitalName==null)?" ":hospitalName);
		}

		public void setHospitalName(String hospitalName) {
			this.hospitalName = hospitalName;
		}

		public String getHospitalShortName() {
			return ((hospitalShortName==null)?" ":hospitalShortName);
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
			 return (state == null)?"":state;
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
			return ((hospitalcode==null)?" ":hospitalcode);
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

		public void setHmode( java.lang.String hmode )
		{
			this.hmode = hmode;
		}

		public java.lang.String getHmode( )
		{
			return hmode;
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
		
		
		
		
		public String getStrUserName() {
			return((strUserName==null)?" ":strUserName);
		}

		public void setStrUserName(String strUserName) {
			this.strUserName = strUserName;
		}

		public String getStrPassword() {
			return ((strPassword==null)?" ":strPassword);
		}

		public void setStrPassword(String strPassword) {
			this.strPassword = strPassword;
		}

		public void initializeNewMode()
		{
			this.hospitalName = "";
			this.hospitalShortName= "";
			this.userId = "";
			this.password= "";
			this.address1="";
			this.address2="";
			this.bedCapacity="";
			this.busRouteNo="";
			this.city="";
//			this.effectDate = "";
//			this.hl7Code = "";
			this.setStatus("Record is successfully saved");
			
		}
		
		
		
		
		public List initializeUpdateMode()throws Exception
		{
			String query = new String("");
			List AL_List = new ArrayList();
			String[] arrayTime=null;
			String[] arrayTimeFrom=null;
			String[] arrayTimeTo=null;
			String hosCode="";

			hosCode = chk[0].substring(0,chk[0].length()-1);
			int nhosCode=Integer.parseInt(hosCode);
			this.setHospitalcode(hosCode);
			

			query 	=	"SELECT GNUM_HOSPITAL_CODE as hospitalCode, nvl(GSTR_USER_NAME,' ') as userId , nvl(GSTR_PASSWORD,' ') as password, nvl(gstr_hospital_name,'') as hospitalName, nvl(gstr_hosp_short_name,' ') as hospShortName, nvl(gstr_hospital_add1,' ') as addr1, " +
			" nvl(gstr_hospital_add2,' ') as addr2, nvl(gstr_city,' ') as city, nvl(gnum_state_code,0) as state, nvl(gstr_phone,' ') as phone, nvl(gstr_fax,' ') as fax, " + 
			" nvl(gstr_email,' ') as email, nvl(gstr_contact_person,' ') as contactPerson,nvl(gnum_hospital_type,0)as hospitalType, nvl(gstr_busroutes_no,' ') as busRouteNo,nvl(gstr_bed_capacity,' ') as bedCapacity, nvl(gstr_weekdays_timing,' ') as weekDayTimings,nvl(gstr_sat_timing,' ') as satTimings, " +
			" nvl(gstr_lunch_break,' ') as lunchTimings, nvl(GNUM_USR_LICENCE_ALLOWED,0) as strNoOfLicences," +
			" GNUM_ISVALID as isValid, nvl(num_dist_id,0) as district,decode(gnum_pin_code,NULL,' ',gnum_pin_code) as pinCode"+
	        " FROM GBLT_HOSPITAL_MST WHERE gnum_hospital_code = "+nhosCode;
			System.out.println("update  initialize query"+query);
			
			HisResultSet rst = getRecord(query);
			System.out.println("update   query"+rst);
			if(rst.next())
			{
				//this.hospitalcode = rst.getString(1);
				System.out.println("update  hoscode"+this.hospitalcode);
				this.userId =rst.getString(2);
				//this.password = rst.getString(3);
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
				this.isvalid=rst.getString(19); 
				this.strNoOfLicences=rst.getString(20);
				this.district=rst.getString(22);
				this.pinCode=rst.getString(23);

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
			AL_List.add(hospitalcode);
			AL_List.add(userId);
			//AL_List.add(password);
			AL_List.add(hospitalName);
			AL_List.add(hospitalShortName);
			AL_List.add(address1);
			AL_List.add(address2);
			AL_List.add(city);
			AL_List.add(state);
			AL_List.add(phoneNo);
			AL_List.add(faxNo);
			AL_List.add(emailId);
			AL_List.add(contactPerson);
			AL_List.add(hospitalType);
			AL_List.add(busRouteNo);
			AL_List.add(bedCapacity);
			AL_List.add(weekHourFrom);
			AL_List.add(weekMinFrom);
			AL_List.add(weekHourTo);
			AL_List.add(satHourFrom);
			AL_List.add(satMinFrom);
			AL_List.add(satHourTo);
			AL_List.add(satMinTo);
			AL_List.add(lunchHourFrom);
			AL_List.add(lunchMinFrom);
			AL_List.add(lunchHourTo);
			AL_List.add(lunchMinTo);
			AL_List.add(strNoOfLicences);
			AL_List.add(isvalid);
			AL_List.add(district);
			AL_List.add(pinCode);

			return AL_List;
	    
		}
		
		
		
		
		
	/////Function for Saving the Hospital Details
		
		

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
		
		
		public boolean insertRecord()throws Exception
		{
			String query  = new String();	
			String redquery  = new String();	
			String query2  = new String();	
	    	System.out.println("into save function");
	    	
	    	usermgmt.FuncLib f=new usermgmt.FuncLib(); 
	    	entryDate	=	getSysdate();
	    	String values = new String();
			//String userpassword= SecurityUtil.encrypt(this.password);
			
			//String userPasswardHashed= PasswordHash.createHash(this.password,userId);

			
			String hoscode;
			Connection conn = null;
			Statement stmt=null;
			conn = super.getConnection();	
			conn.setAutoCommit(false);	
			stmt= conn.createStatement();
			String[] arrayTime=null;
			String[] arrayTimeFrom=null;
			String[] arrayTimeTo=null;
			boolean redundantname  	= 	false;
			boolean redundantUsername  	= 	false;
			
			if(pinCode=="")
				pinCode="0";
			
			//query=" select nvl(max( GNUM_HOSPITAL_CODE +1),1) from GBLT_HOSPITAL_MST WHERE	GNUM_ISVALID = '1'";
			
			//query=" select nvl(max( GNUM_HOSPITAL_CODE +1),to_number("+this.state+"||''||'101')) from GBLT_HOSPITAL_MST where gnum_state_code="+this.state+" ";
			query=" select nvl(max( GNUM_HOSPITAL_CODE +1),to_number("+this.state+"||''||'911')) from GBLT_HOSPITAL_MST where gnum_state_code="+this.state+" ";
			System.out.println(query);
			hoscode   =  f.getField(query);
			
			
			redquery= "	select  initcap(gstr_hospital_name) from GBLT_HOSPITAL_MST WHERE UPPER(gstr_hospital_name)=UPPER('"+this.hospitalName+"') " +
			" 	and GNUM_ISVALID>0 and GNUM_ISVALID<3 ";
			query2  =  "	select  initcap(GSTR_USER_NAME) from GBLT_HOSPITAL_MST WHERE UPPER(GSTR_USER_NAME)=UPPER('"+this.userId+"')" +
			" 	and GNUM_ISVALID>0 and GNUM_ISVALID<3 ";
	
	   redundantname=super.isRedundentName(redquery);
	   redundantUsername=super.isRedundentName(query2);
	
	   if(redundantname || redundantUsername)
		{
		   retValue = false;
			this.status=	"	Data Already Exist , Please Write Other Hospital Name or User Id......!";
			
		}
	   else
	   {
			String weekdayTimings=this.weekHourFrom+":"+this.weekMinFrom+"#"+this.weekHourTo+":"+this.weekMinTo;
			String satTimings=this.satHourFrom+":"+this.satMinFrom+"#"+this.satHourTo+":"+this.satMinTo;
			String lunchTimings=this.lunchHourFrom+":"+this.lunchMinFrom+"#"+this.lunchHourTo+":"+this.lunchMinTo;
			
			values  	   = hoscode + ",'";
			values  	   += hospitalName + "','";
			
	        values 		  += hospitalShortName + "','";
			
			values 		  += userId + "','";
			
			values 		  += this.password + "','";
			
			values 		  += address1+ "','";
			
			values		  += address2 + "','";
			
			values 		  += city + "','";
			
			values 		  += state + "','";
			
			values 		  += district + "','";
			
			values 		  += pinCode + "','";
			
			values 		  += contactPerson + "','";
			
			values 		  += emailId + "','";
			
			values 		  += faxNo + "','";
			
			values 		  += phoneNo + "','";
			
			values 		  += hospitalType + "','";
			
			values 		  += bedCapacity + "','";
			
			values 		  += busRouteNo + "','";
			
			values 		  += isvalid + "','";
			
			values        += seatId + "','";
			
			values		  += entryDate + "','";
			
			values 		  += lunchTimings + "','";
			
			values 		  += satTimings + "','";
			
			values 		  += weekdayTimings +"','";
			
			values 		  += strNoOfLicences +"'";
			System.out.println("values"+values);
			
	        query 		=   " INSERT INTO GBLT_HOSPITAL_MST  ( "+ 
	        			" gnum_hospital_code, "+
	        			"gstr_hospital_name,"+
	        			"gstr_hosp_short_name," +
	        			"GSTR_USER_NAME, "+
	        			"GSTR_PASSWORD,"+
	        			" gstr_hospital_add1,"+
	        			"gstr_hospital_add2,"+
	        			"GSTR_CITY,"+
	        			"gnum_state_code,"+
	        			"num_dist_id,"+
	        			"gnum_pin_code,"+
	        			"gstr_contact_person,"+
	        			"gstr_email,"+
	        			"gstr_fax, "+
	        			"gstr_phone, "+
	        			"gnum_hospital_type,"+
	        			"gstr_bed_capacity,"+
	        			"gstr_busroutes_no,"+
	        			"GNUM_ISVALID,"+
	        			"GNUM_SEAT_ID,"+
	        			"GDT_ENTRY_DATE,"+
	        			"gstr_lunch_break, " +  
	        			"gstr_sat_timing ,"+
	        			"gstr_weekdays_timing,"+
	        			"GNUM_USR_LICENCE_ALLOWED"+
	        			" )VALUES ( ";
	        					query		+= values + ")";
			
			
			System.out.println("save Query1----"+query);
			
			try
			{

				if (!updateRecord(query))
				{
					status = "Error while inserting the record";
					retValue = false;
				}
			}
			catch(Exception e)
			{
				status = String.valueOf(e);
				System.out.println("Error While Inserting Record: "+e);
				retValue = false;
			}
	   }	
			return retValue;			
			
		}
			
			
	//For Updating the Hospital Master	
		
			public boolean updateRecord(HttpServletRequest request )throws Exception
	{
				String oldRecord		=	"";
				String oldVal			=	"";
				String query			=	"";
				String query1			=	"";
				String query2			=	"";
				String newVal			=	"";
				String pK				=	"";
				boolean flag 			=	false;
				boolean redundant    	= 	false;
			    boolean redundantname  	= 	false;
				boolean isRun			=	false;
			    Connection conn 		= 	null ; 
			    PreparedStatement ps1 	=	null;
			    Statement st 			= 	null ;
				int isValid;
				int pin;
				String strUpdateQry="";
				String updateQry="";
	    
				this.seatId=(String)request.getSession().getAttribute("SEAT_ID");
				this.hospitalName=checkApostrophe(this.hospitalName);
				pK= this.hospitalcode;
			//	String userPasswardNtHashed =SecurityUtil.encrypt(this.password);
				String password= this.password;
				
				/*if(this.pinCode=="")
					this.pinCode="0";*/
				
				System.out.println("value of pK"+pK);
				 oldRecord	=	"select 'GSTR_USER_NAME'||'^'||GSTR_USER_NAME||'#'||'GSTR_PASSWORD'||'^'||GSTR_PASSWORD||'#'||'gstr_hospital_name'||'^'||gstr_hospital_name||'#'||'GSTR_HOSP_SHORT_NAME'||'^'||GSTR_HOSP_SHORT_NAME||'#'|| 'GSTR_HOSPITAL_ADD1'||'^'||GSTR_HOSPITAL_ADD1||'#'||"+    
				 			"'GSTR_HOSPITAL_ADD2'||'^'||GSTR_HOSPITAL_ADD2||'#'||'GSTR_CITY'||'^'||GSTR_CITY||'#'||'GNUM_STATE_CODE'||'^'||GNUM_STATE_CODE||'#'||'NUM_DIST_ID'||'^'||NUM_DIST_ID||'#'||'GNUM_PIN_CODE'||'^'||GNUM_PIN_CODE||'#'||'GSTR_PHONE'||'^'||GSTR_PHONE||'#'||'GSTR_FAX'||'^'||GSTR_FAX||'#'|| 'GSTR_EMAIL'||'^'||GSTR_EMAIL||'#'||'GSTR_CONTACT_PERSON'||'^'||GSTR_CONTACT_PERSON||'#'||"+
				 			"'GNUM_HOSPITAL_TYPE'||'^'||GNUM_HOSPITAL_TYPE||'#'||'GSTR_BUSROUTES_NO'||'^'||GSTR_BUSROUTES_NO||'#'||'GSTR_BED_CAPACITY'||'^'||GSTR_BED_CAPACITY||'#'|| 'GSTR_WEEKDAYS_TIMING'||'^'||GSTR_WEEKDAYS_TIMING||'#'|| 'GSTR_SAT_TIMING'||'^'||GSTR_SAT_TIMING||'#'||'GSTR_LUNCH_BREAK'||'^'||GSTR_LUNCH_BREAK||'#'||gnum_usr_licence_allowed||'^'||'gnum_usr_licence_allowed'" +
				 			" FROM GBLT_HOSPITAL_MST WHERE gnum_hospital_code ='"+this.hospitalcode+"'";
				 
				 	System.out.println("oldRecord is"+oldRecord);
 
				 	oldVal 	= super.getField(oldRecord);
				 	
				 	String weekdayTimings=this.weekHourFrom+":"+this.weekMinFrom+"#"+this.weekHourTo+":"+this.weekMinTo;
					String satTimings=this.satHourFrom+":"+this.satMinFrom+"#"+this.satHourTo+":"+this.satMinTo;
					String lunchTimings=this.lunchHourFrom+":"+this.lunchMinFrom+"#"+this.lunchHourTo+":"+this.lunchMinTo;
				 	
					newVal =	"	GSTR_USER_NAME^"+this.userId+"#GSTR_PASSWORD^"+password+"#gstr_hospital_name^"+this.hospitalName+
				 					"#gstr_hosp_short_name^"+this.hospitalShortName+"#gstr_hospital_add1^"+this.address1+"#gstr_hospital_add2^"+this.address2+"#gstr_city^"+this.city+
				 					"#gnum_state_code^"+this.state+"#num_dist_id^"+this.district+"#gnum_pin_code^"+this.pinCode+"#gstr_phone^"+this.phoneNo+"#gstr_fax^"+this.faxNo+"#gstr_email^"+this.emailId+"#gstr_contact_person^"+this.contactPerson+"#gnum_hospital_type^"+this.hospitalType+
				 					"	#gstr_busroutes_no^"+this.busRouteNo+"#GNUM_ISVALID^"+this.isvalid+
				 					"	#gstr_weekdays_timing^"+weekdayTimings+"#gstr_sat_timing^"+satTimings+"#gstr_lunch_break^"+lunchTimings+"#gnum_usr_licence_allowed^"+this.strNoOfLicences;
	         
					System.out.println("newVal is"+newVal);
					 //updateQry =" update  gblt_hospital_mst set  GNUM_ISVALID=0  WHERE UPPER(GSTR_USER_NAME)=UPPER('"+this.userId+"')AND  gnum_hospital_code = '"+this.hospitalcode+"' "; 
					   
					
					query1= "	select initcap(gstr_hospital_name) from GBLT_HOSPITAL_MST WHERE  gnum_hospital_code <> '"+this.hospitalcode+ "' AND gstr_hospital_name=('"+this.hospitalName.trim()+"')  	and GNUM_ISVALID>0 and GNUM_ISVALID<3 ";;
					
					
					query2  =  "	select  initcap(GSTR_USER_NAME) from GBLT_HOSPITAL_MST WHERE  gnum_hospital_code <> '"+this.hospitalcode+ "' 	and UPPER(GSTR_USER_NAME)=UPPER('"+this.userId+"') AND GNUM_ISVALID>0 and GNUM_ISVALID<3 ";
			
			   redundantname=super.isRedundentName(query1);
			   redundant=super.isRedundentName(query2);
			
			   if(redundantname || redundant)
				{
				   retValue = false;
					this.status=	"	Data Already Exist , Please Write Other Hospital Name or User Id......!";
					
				}
					   else
					   {
						   strUpdateQry =" update  GBLT_HOSPITAL_MST set  GSTR_USER_NAME='"+this.userId+"'  ,GSTR_PASSWORD = '"+password+"', GSTR_HOSPITAL_NAME='"+this.hospitalName+"' ," +
							" GSTR_HOSPITAL_ADD1='"+this.address1+"', GSTR_HOSPITAL_ADD2='"+this.address2+"'," +
							" GSTR_CITY='"+this.city+"', GNUM_STATE_CODE='"+this.state+"', NUM_DIST_ID='"+this.district+"', GNUM_PIN_CODE=NVL('"+this.pinCode+"',NULL), " +
							" GSTR_PHONE='"+this.phoneNo+"', GSTR_FAX='"+this.faxNo+"', GSTR_EMAIL='"+this.emailId+"', " +
							" GSTR_CONTACT_PERSON='"+this.contactPerson+"', " +
							" GSTR_HOSP_SHORT_NAME='"+this.hospitalShortName+"', GDT_LSTMOD_DATE=SYSDATE, " +
							" GNUM_LSTMOD_SEATID='"+this.seatId+"',GNUM_HOSPITAL_TYPE='"+this.hospitalType+"'," +
							" GSTR_BUSROUTES_NO='"+this.busRouteNo+"', GSTR_BED_CAPACITY='"+this.bedCapacity+"', " +
							" GSTR_WEEKDAYS_TIMING='"+weekdayTimings+"', GSTR_SAT_TIMING='"+satTimings+"'," +
							" GSTR_LUNCH_BREAK='"+lunchTimings+"', gnum_usr_licence_allowed='"+this.strNoOfLicences+"',GNUM_ISVALID='"+this.isvalid+"'  WHERE  gnum_hospital_code = '"+this.hospitalcode+"' "; 
						   
						   System.out.println("Query_update_HospitalMaster----->"+strUpdateQry);
							isRun=true;
					   }
					   
					   if(isRun)
						{
							conn = new HisMethods().getConnection();
							conn.setAutoCommit(false);
							st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
							try
							{
								st.addBatch(strUpdateQry);
								st.executeBatch();
								ps1  =  new AuditLog(pK,oldVal,newVal,"update by admin","GBLT_HOSPITAL_MST").updateAuditLog(request,conn);
								conn.commit(); 
								flag=true;
							}
							catch( Exception e ) // it handles user defined exception throws by AuditLog.java file
							{   
								this.status = "Error while Updating Record(s) "+e;
								System.out.println("exception in excution ="+e);
								try
								{ 		    
									conn.rollback();  
								}
								catch(Exception se ) 
								{ 
									System.out.println("error in try block..."+se);
								}	
							}
							finally
							{
								try
								{
									if(ps1!= null) 
										ps1.close();
									if(st!= null)
										st.close(); 
									if(conn!=null)
										conn.close();
								}
								catch(SQLException se1 ) 
								{ 
									System.out.println("Exception in finally block"+se1);
								}
							}
						}
						else
						{
							this.status	="	Data Already Exist , Please Write Other Hospital Name or User Id......!";
						}
						
				        return flag;
	}

			
		public String deleteRecord()throws Exception
			{
			status	=	"Successfully deleted !";
			int i	=	0;
			String hosCode="";
			String code =null;

			//hosCode = chk[0].substring(0,chk[0].length()-1);
			//this.setHospitalcode(hosCode);
			Connection conn	=	super.getConnection();
			conn.setAutoCommit(false);
			try{
			String query=  " UPDATE GBLT_HOSPITAL_MST SET GNUM_ISVALID= '0'  WHERE  GNUM_HOSPITAL_CODE=?";
			String status="";
				
			
			
			PreparedStatement 	ps = conn.prepareStatement(query);
//			StringTokenizer 	st = null;
			
				for(i=0; i<chk.length; i++)
				{
					hospitalcode = chk[i].substring(0,chk[i].length()-1);
					
					ps.setString(1,hospitalcode);
					ps.execute();
				}
				conn.commit();
				conn.setAutoCommit(true);
				status = i + " Record(s) Deleted Successfully.";

			}
			catch(Exception e)
			{
				status = "Error in deleting Record(s): " + e;
			}
			finally
			{
				super.closeConnection(conn);	
			}
			return status;
			}

		public List initializeviewMode()throws Exception
		{
		  	String query = new String("");
			List AL_List = new ArrayList();
			String hosCode="";

			hosCode = chk[0].substring(0,chk[0].length()-1);
			this.setHospitalcode(hosCode);
			
			query 	=	"SELECT  nvl(gstr_hospital_name,''), nvl(gstr_hosp_short_name,' ') , nvl(GSTR_USER_NAME,' ') , nvl(gstr_hospital_add1,' ') , " +
			//" nvl(gstr_hospital_add2,' ') , nvl(gstr_city,' '),(select distinct Initcap(a.GSTR_STATE_NAME) from GBLT_STATE_MST a ,GBLT_HOSPITAL_MST b where a.gnum_state_code=b.gnum_state_code And b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+"), nvl(gstr_phone,' ') , nvl(gstr_fax,' ') , " +
			//" nvl(gstr_hospital_add2,' ') , (select distinct Initcap(a.GSTR_DISTRICT_NAME) from GBLT_DISTRICT_MST a ,GBLT_HOSPITAL_MST b where a.gnum_district_code=b.num_dist_id AND b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+ "),(select distinct Initcap(a.GSTR_STATE_NAME) from GBLT_STATE_MST a ,GBLT_HOSPITAL_MST b where a.gnum_state_code=b.gnum_state_code And b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+"), nvl(gstr_phone,' ') , nvl(gstr_fax,' ') , " +
			" nvl(gstr_hospital_add2,' '), decode(gnum_pin_code,'0',' ',gnum_pin_code), (select distinct Initcap(a.GSTR_DISTRICT_NAME) from GBLT_DISTRICT_MST a ,GBLT_HOSPITAL_MST b where a.gnum_district_code=b.num_dist_id AND b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+ "),(select distinct Initcap(a.GSTR_STATE_NAME) from GBLT_STATE_MST a ,GBLT_HOSPITAL_MST b where a.gnum_state_code=b.gnum_state_code And b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+"), nvl(gstr_phone,' ') , nvl(gstr_fax,' ') , " + 
			" nvl(gstr_email,' ') , nvl(gstr_contact_person,' ') ,decode(gnum_hospital_type,1,'Govt Hospital',2,'Dispensary',3,'Private Hospital',4,'Nursing Home',0,''), nvl(gstr_busroutes_no,' ') as busRouteNo,nvl(gstr_bed_capacity,' ') , nvl(gstr_weekdays_timing,' '),nvl(gstr_sat_timing,' ') , " +
			" nvl(gstr_lunch_break,' '),nvl(GNUM_USR_LICENCE_ALLOWED,0) as strNoOfLicences  " +
			//" (select distinct Initcap(a.GSTR_DISTRICT_NAME) from GBLT_DISTRICT_MST a ,GBLT_HOSPITAL_MST b where a.gnum_district_code=b.num_dist_id AND b.GNUM_ISVALID=a.GNUM_ISVALID AND b.gnum_hospital_code = "+this.hospitalcode+ ")"+
	        " FROM GBLT_HOSPITAL_MST WHERE gnum_hospital_code = "+hosCode;
			System.out.println("Initilizeview detail query =  "+query);
			try
			{
				AL_List = getDetail(query);
				if((AL_List.get(1)==null)||(AL_List.get(1).equals("")))
					AL_List.set(1,"No Hospital Short Name");
				if((AL_List.get(3)==null)||(AL_List.get(3).equals("")))
					AL_List.set(3,"No Hospital Address1");
				if((AL_List.get(4)==null)||(AL_List.get(4).equals("")))
					AL_List.set(4,"No Hospital Address2");
			//	if((AL_List.get(5)==null)||(AL_List.get(5).equals("")))
			//		AL_List.set(5,"No Hospital City");
				if((AL_List.get(5)==null)||(AL_List.get(5).equals("")))
					AL_List.set(5,"No Hospital PIN Code");
				if((AL_List.get(6)==null)||(AL_List.get(6).equals("")))
					AL_List.set(6,"No Hospital District");
				if((AL_List.get(7)==null)||(AL_List.get(7).equals("")))
					AL_List.set(7,"No Hospital State");
				if((AL_List.get(8)==null)||(AL_List.get(8).equals("")))
					AL_List.set(8,"No Hospital Phone No");
				if((AL_List.get(9)==null)||(AL_List.get(9).equals("")))
					AL_List.set(9,"No Hospital Fax No");
				if((AL_List.get(10)==null)||(AL_List.get(10).equals("")))
					AL_List.set(10,"No Hospital EmailId");
				if((AL_List.get(11)==null)||(AL_List.get(11).equals("")))
					AL_List.set(11,"No Hospital Contact Person");
				
//				if((AL_List.get(1)==null)||(AL_List.get(1).equals("")))
//					AL_List.set(1,"No Designation");
//				if((AL_List.get(2)==null)||(AL_List.get(2).equals("")))
//					AL_List.set(2,"No Effect Date");
//				if((AL_List.get(3)==null)||(AL_List.get(3).equals("")))
//					AL_List.set(3,"No Expiry Date");
				
			}
			catch(Exception e)
			{
				System.out.println("Error in Initilizeview detail function =  "+e);
			}
				hospitalcode= (String)AL_List.get(0);
				hospitalName =(String)AL_List.get(1);		
				hospitalShortName = (String)AL_List.get(2);	
				userId = (String)AL_List.get(3);
				address1 = (String)AL_List.get(4);		
				address2 = (String)AL_List.get(5);	
				//city = (String)AL_List.get(6);
				pinCode = (String)AL_List.get(6);
				district=(String)AL_List.get(7);
				state = (String)AL_List.get(8);
				phoneNo = (String)AL_List.get(9);			
				faxNo = (String)AL_List.get(10);
				emailId = (String)AL_List.get(11);		
				contactPerson = (String)AL_List.get(12);
				hospitalType = (String)AL_List.get(13);
				busRouteNo = (String)AL_List.get(14);		
				bedCapacity = (String)AL_List.get(15);
				strNoOfLicences=(String)AL_List.get(18);

//			hospitalShortName	=	(String)AL_List.get(5);
//				designation	=	(String)AL_List.get(1);
//		        effDate		=	(String)AL_List.get(2);
//		        expDate		=	(String)AL_List.get(3);
	        return AL_List;
	    
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

		public String getEntryDate() {
			return entryDate;
		}

		public void setEntryDate(String entryDate) {
			this.entryDate = entryDate;
		}	
		
		public String getPinCode() {
			return pinCode;
		}

		public void setPinCode(String pinCode) {
			this.pinCode = pinCode;
		}
}
