package enquiry.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.vo.BloodStockEnquiryVO;
import hisglobal.vo.CommonEnquiryVO;
import hisglobal.vo.ConsultantDetailVO;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.DeceasedHandoverDtlVO;
import hisglobal.vo.DepartmentLocationEnquiryVO;
import hisglobal.vo.EpisodeEnquiryVO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.MortuaryDeceasedImageDtlVO;
import hisglobal.vo.OpdEnquiryVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;




import mortuary.MortuaryConfig;
import registration.RegistrationConfig;
import enquiry.enquiryConfig;
import enquiry.vo.BloodDonorEnquriyVO;
import enquiry.vo.GuestHouseEnquiryVO;
import enquiry.vo.HospitalConsultantEnquiryVO;
import enquiry.vo.HospitalDepartmentEnquiryVO;
import enquiry.vo.HospitalHolidayEnquiryVO;
import enquiry.vo.HospitalLabEnquiryVO;
import enquiry.vo.HospitalRegistrationTimingsVO;
import enquiry.vo.HospitalTelephoneEnquiryVO;
import enquiry.vo.HospitalWardEnquiryVO;
import enquiry.vo.InPatientEnquiryVO;
import enquiry.vo.OpdScheduleEnquiryVO;
import enquiry.vo.OperationTheaterEnquiryVO;
import enquiry.vo.StaffEnquiryVO;


public class EnquiryDAO extends DataAccessObject{
	
private static Object lock = new Object();

	
	public EnquiryDAO(TransactionContext _tx) {
		super(_tx);
	}
	public Object getLock() {
		return lock;		
	}
	
	public CommonEnquiryVO[] searchPatient(HashMap _finalQueryMap,UserVO _userVO,String isCurrentHospitalSearch_p)
	
	{
		CommonEnquiryVO [] commonEnquiryVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		PatientVO[] _patVO=null;
		String query="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "SEARCH_PATIENT_BY_PATIENT_ADREESS_DEPARTMENT";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY trim(hrgstr_fname), trim(hrgstr_mname), trim(hrgstr_lname)";
		Sequence sq = new Sequence();
		boolean isDefaultState=false;
		
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		//Multi hospital Change done by Singaravelan on 01-Aug-2014
		if("1".equals(isCurrentHospitalSearch_p))
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
		else
			_populateMap.put(sq.next(),"%");
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			//System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//////////////Preparing query to fetch patient records using patient details/////////
				
		
		Set keySetTemp=(Set)_finalQueryMap.keySet();
		
		Iterator keySetItrTemp=keySetTemp.iterator();
		
		//For Unknown MLC patient Change done by Singaravelan on 26-Aug-2014 
		if(keySetTemp.contains("gnum_state_code")){
		while(keySetItrTemp.hasNext()){
			String mapKey=(String)keySetItrTemp.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			if(mapKey.equals("gnum_state_code"))
				if(mapValue.equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE))
					isDefaultState=true;
		}
		}
		Set keySet=(Set)_finalQueryMap.keySet();
		
		Iterator keySetItr=keySet.iterator();
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname") ||
					mapKey.equals("hrgstr_father_name") || mapKey.equals("hrgstr_spousename"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
			}
			
			if(isDefaultState){
				if(mapKey.equals("hrgstr_house_no") || mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
						mapKey.equals("hrgstr_city") || mapKey.equals("gstr_tehsil") )
				{
					whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
				}
			//	if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
				if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
						mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
						mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
						mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
				{
					whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
				}				
				
			}
			else{
				if(mapKey.equals("hrgstr_house_no") || mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
						mapKey.equals("hrgstr_city") || mapKey.equals("gstr_tehsil") )
				{
					whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
				}
				if(keySet.contains("hrgnum_is_mlc"))
				{
					//if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
							mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
							mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
							mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
					{
						whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
					}	
				}
				else
				{
				//if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
						//mapKey.equals("hrgstr_state_name") || mapKey.equals("gnum_state_code") || mapKey.equals("gnum_country_code") || mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||
						mapKey.equals("hrgstr_state_name") || mapKey.equals("gnum_state_code") || mapKey.equals("gstr_country_code") || mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||
						mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
						mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
				{
					whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
				}
				}
				
			}
			
			
			/*if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname") ||
					mapKey.equals("hrgstr_father_name") || mapKey.equals("hrgstr_spousename") || mapKey.equals("hrgstr_house_no") || 
					mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
					mapKey.equals("hrgstr_city") || mapKey.equals("hrgstr_state_name") || mapKey.equals("gstr_tehsil") )
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			
			if(keySet.contains("hrgnum_is_mlc"))
			{
				if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
						mapKey.equals("gnum_dept_code") ||mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
						mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
						mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
				{
					whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
				}	
			}
			else
			{
			if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					mapKey.equals("gnum_state_code") || mapKey.equals("gnum_country_code") || mapKey.equals("gnum_dept_code") ||mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
					mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
					mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
			{
				whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
			}
			}*/
			
			
			if(mapKey.equals("fromdate"))
			{
				whereCOndition=whereCOndition +" AND "+ "hrgdt_register_date >trunc(to_date('" +mapValue +"','dd-mon-yyyy'))";
			}
			if(mapKey.equals("todate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hrgdt_register_date) <= trunc(to_date('" +mapValue +"','dd-mon-yyyy'))";
			}
			if(mapKey.equals("hrgstr_contact_no"))
			{
				whereCOndition= whereCOndition + " AND b."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgnum_puk"))
			{
				whereCOndition= whereCOndition + " AND a."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgnum_is_urban"))
			{
				whereCOndition= whereCOndition + " AND a."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgstr_age")){
				System.out.println("hrgstr_age="+mapValue);
				String lowerLimit=mapValue.split("-")[0];
				String upperLimit=mapValue.split("-")[1];

				whereCOndition=whereCOndition+" AND round(DATE_PART('days',(sysdate- HRGDT_DOB))/365)  > " + lowerLimit ;
				whereCOndition=whereCOndition+" AND round(DATE_PART('days',(sysdate- HRGDT_DOB))/365)  <=" + upperLimit ;
			}
			if(mapKey.equals("gnum_dept_code")){
				//if(mapValue.equals("%"))
					whereCOndition=whereCOndition+" AND "+"( "+mapKey+" LIKE '"+mapValue+"')";
				//else
				//	whereCOndition=whereCOndition+" AND "+"( "+mapKey+" = "+mapValue+"))";
			}
			
		}
		finalQuery=query + whereCOndition + orderBy;
		//System.out.println("final query "+finalQuery);
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(CommonEnquiryVO.class, rs);
				commonEnquiryVOs=new CommonEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					commonEnquiryVOs[i]=(CommonEnquiryVO)valueObjects[i];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		
		return commonEnquiryVOs;
	}
	
	
	public OpdEnquiryVO[] getOpdEnquirySearchDetail(HashMap _finalQueryMap,UserVO _userVO)
	
	{
		OpdEnquiryVO [] opdEnquiryVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		PatientVO[] _patVO=null;
		String query="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "SEARCH_OPD_RELATED_DETAIL";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		
		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),RegistrationConfig.IS_HOU_TRUE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//////////////Preparing query to fetch patient records using patient details/////////
				
		
		Set keySet=(Set)_finalQueryMap.keySet();
		
		Iterator keySetItr=keySet.iterator();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hgnum_deptunitcode"))
			{
				whereCOndition= whereCOndition + " AND a."+mapKey + " like " + "'" +mapValue +"'";
				
			}
			
			if(mapKey.equals("hopnum_day_of_week") && !mapValue.equals("0"))
			{
				whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
			}
			
			if(mapKey.equals("hopnum_week_of_month") && !mapValue.equals("0"))
			{
				whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
			}
			if( mapKey.equals("gnum_dept_code"))
			{
				//whereCOndition= whereCOndition + " AND "+ mapKey + " like " + "'" +mapValue +"'";
				whereCOndition= whereCOndition + " AND a."+ mapKey + " like " + "'" +mapValue +"'";
			}
		}
		//finalQuery=query + whereCOndition +" order by HGSTR_ROOM_NAME,b.HOPNUM_DAY_OF_WEEK";
		finalQuery=query + whereCOndition +" order by hopnum_week_of_month,b.HOPNUM_DAY_OF_WEEK";
		System.out.println("final query "+finalQuery);
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO record found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(OpdEnquiryVO.class, rs);
				opdEnquiryVOs=new OpdEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					opdEnquiryVOs[i]=(OpdEnquiryVO)valueObjects[i];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		
		return opdEnquiryVOs;
	}
	
	
	public EpisodeEnquiryVO getOpdPatientDetail(String  patientCRNo,UserVO _userVO)
	
	{
		EpisodeEnquiryVO  episodeEnquiryVO=new EpisodeEnquiryVO();
		String query="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "ENQUIRY.OPD_DETAIL_BY_CRNO";
		ResultSet rs=null;
		Map _populateMap=new HashMap();
		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),patientCRNo);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),patientCRNo);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
					
		
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No OPD record found");
			}
			else{
				
				//HelperMethods.populateVOfrmRS(episodeEnquiryVO, rs);
				episodeEnquiryVO.setGnum_dept_code(rs.getString(2));
				episodeEnquiryVO.setUnit(rs.getString(3));
				episodeEnquiryVO.setLastVisitDate(rs.getString(4));
				episodeEnquiryVO.setRoomNo(rs.getString(5));
				
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		
		return episodeEnquiryVO;
	}	
	
	
	// getting details of IPD
	public InPatientEnquiryVO getInPatientDetail(String  patientCRNo,UserVO _userVO)
	
	{
		InPatientEnquiryVO  inPatientEnquiryVO=new InPatientEnquiryVO();
		String query="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "ENQUIRY.IPD_DETAIL_BY_CRNO";
		ResultSet rs=null;
		Map _populateMap=new HashMap();
		Sequence sq = new Sequence();
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),patientCRNo);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("No IPD record found ");
			}
			else{
				
				//HelperMethods.populateVOfrmRS(inPatientEnquiryVO, rs);
				inPatientEnquiryVO.setHipnum_admno(rs.getString(1));
				inPatientEnquiryVO.setAdmDate(rs.getString(2));
				inPatientEnquiryVO.setDept(rs.getString(3));
				inPatientEnquiryVO.setUnit(rs.getString(4));
				inPatientEnquiryVO.setWardNo(rs.getString(5));
				inPatientEnquiryVO.setStatus(rs.getString(6));
				inPatientEnquiryVO.setDischargeDate(rs.getString(7));
				inPatientEnquiryVO.setBedNo(rs.getString(8));
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
				throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		
		return inPatientEnquiryVO;
	}	

	public DepartmentLocationEnquiryVO searchDepartmentLocation(String _departmentCode,UserVO _userVO)
	
	{
	DepartmentLocationEnquiryVO daDepartmentLocationEnquiryVO=new DepartmentLocationEnquiryVO();
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	String queryKey=""; 
if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
	queryKey = "SEARCH_DEPARTMENT_LOCATION.HGBT_LOCATION_MST.LOCATION_MAPPING_YES";
	else
if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_NO))	
	queryKey = "SEARCH_DEPARTMENT_LOCATION.HGBT_LOCATION_MST.LOCATION_MAPPING_NO";
	
	Connection conn = super.getTransactionContext().getConnection();
	Sequence sq = new Sequence();
	
	
	_populateMap.put(sq.next(),_departmentCode);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	System.out.println("query" + query);
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("No Location Record Found");
		} else {
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(daDepartmentLocationEnquiryVO,rs);
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("Enquiry Dao :location enquiry " + e);
	}
		return daDepartmentLocationEnquiryVO;
	}



	public OpdEnquiryVO[] getAllConsulatantDetailsForUnit(String _deptUnitCode,UserVO _userVO){
		
		OpdEnquiryVO[] opdEnquiryVOs=null;
		ResultSet rs;
		ValueObject[] valueObjects=null;
		String query="";
		
		String fileName=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey="SELECT_ALL_CONSULANT_DETAIL_OPD_ENQUIRY";
		Map populateMap=new HashMap();
		Sequence sq=new Sequence();
		
		populateMap.put(sq.next(),_deptUnitCode);
		populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		try {
			query = HelperMethodsDAO.getQuery(fileName, queryKey);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO record found for consultant");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(OpdEnquiryVO.class, rs);
				opdEnquiryVOs=new OpdEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					opdEnquiryVOs[i]=(OpdEnquiryVO)valueObjects[i];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
		}

		return opdEnquiryVOs;
	}


	public ConsultantDetailVO[] searchConsultantDetail(HashMap _finalQueryMap,UserVO _userVO)
	
	{
	ConsultantDetailVO[] consultantDetailVOs=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String whereCOndition="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	String queryKey = "SEARCH_CONSULTANT_DETAIL.PSRT_EMPLOYEE_MST";
	Connection conn = super.getTransactionContext().getConnection();
	String finalQuery="";
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),RegistrationConfig.UNIT_CONSULTANT_DESIGNATION_MAPPING_PROCESSID);
	_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	/*_populateMap.put(sq.next(),_userVO.getHospitalCode());
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	*/				
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	Set keySet=(Set)_finalQueryMap.keySet();
	
	Iterator keySetItr=keySet.iterator();

	
	while(keySetItr.hasNext())
	{
		String mapKey=(String)keySetItr.next();
		String mapValue=(String)_finalQueryMap.get(mapKey);
		if(mapKey.equals("str_first_name") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(mapKey.equals("str_middle_name") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(mapKey.equals("str_last_name") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(mapKey.equals("str_cur_locality") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER (d."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(mapKey.equals("num_cur_dist_id") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER (d."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(mapKey.equals("num_cur_city_id") ){
			whereCOndition=whereCOndition+" AND "+"( UPPER (d."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
		}
		if(!mapKey.equals("str_first_name") && !mapKey.equals("str_middle_name") && !mapKey.equals("str_last_name") && !mapKey.equals("str_cur_locality") && !mapKey.equals("num_cur_dist_id") && !mapKey.equals("num_cur_city_id")){
			whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
		}
	}
	
	//System.out.println("query" + query);
	finalQuery=query+whereCOndition;
	System.out.println("final query === "+ finalQuery);
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, finalQuery,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("No Consultant Record Found");
		} else {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(ConsultantDetailVO.class, rs);
			consultantDetailVOs=new ConsultantDetailVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				consultantDetailVOs[i]=(ConsultantDetailVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO:Consultant Detail :: " + e);
	}
		return consultantDetailVOs;
	}
	
public BloodStockEnquiryVO[] getBloodStock(UserVO _userVO,String _choice)
	
	{
	BloodStockEnquiryVO[] bloodStockVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	Connection conn = super.getTransactionContext().getConnection();
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	if(_choice.equals(enquiryConfig.ALL_BLOOD_COMPONENT_STOCK_ENQUIRY)){
		queryKey = "SELECT.ALL_COMPONENT.BLOOD_STOCK_ENQUIRY";
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
	}	
	if(_choice.equals(enquiryConfig.IN_STOCK_BLOOD_COMPONENT_STOCK_ENQUIRY)){
		queryKey = "SELECT.IN_STOCK_COMPONENT.BLOOD_STOCK_ENQUIRY";
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
	}				
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("No Blood Stock Found");
		} else {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(BloodStockEnquiryVO.class, rs);
			bloodStockVO=new BloodStockEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				bloodStockVO[i]=(BloodStockEnquiryVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO:bloodStock Detail :: " + e);
	}
		return bloodStockVO;
	}

	public BloodDonorEnquriyVO[] getBloodDonorGroups(UserVO _userVO)
	
	{
		BloodDonorEnquriyVO[] bloodStockVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	
		queryKey = "SELECT.BLD_GROUP.HBBT_BLDGRP_MST";
	
	
	Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("No Blood Stock Found");
		} else {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(BloodStockEnquiryVO.class, rs);
			bloodStockVO=new BloodDonorEnquriyVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				bloodStockVO[i]=(BloodDonorEnquriyVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO:bloodStock Detail :: " + e);
	}
		return bloodStockVO;
	}
	

	public BloodDonorEnquriyVO[] getBloodDonorEnquiryDetails(UserVO _userVO,BloodDonorEnquriyVO _bloodDonorVO)
		
		{
			BloodDonorEnquriyVO[] bloodDonorsVO=null;
		Map _populateMap = new HashMap();
		ValueObject[] valueObjects=null;
		String query = "";
		String queryKey="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		
			queryKey = "SELECT.VOLUNTARY_DONOR_ENQUIRY.HBBT_DONOR_DTL";
		
			Connection conn = super.getTransactionContext().getConnection();
		
		
		Sequence sq=new Sequence();
		
		
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		
						
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			query+=" AND hbnum_emergency_call IN "+_bloodDonorVO.getDonationEmergencycode(); 
			query+=" AND b.hbnum_bldgrp_code like "+_bloodDonorVO.getBloodGroupCode();
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("No Voluntary Donor Found");
			} else {
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(BloodDonorEnquriyVO.class, rs);
				bloodDonorsVO=new BloodDonorEnquriyVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					bloodDonorsVO[i]=(BloodDonorEnquriyVO)valueObjects[i];
				}
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
			return bloodDonorsVO;
		}
	
	
	public List getAllDeparments(UserVO _userVO)
	
	{
		List departmentList=new ArrayList();
		Map _populateMap = new HashMap();
		String query = "";
		String queryKey="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.DEPARMENT_NAME.GBLT_DEPARTMENT_MST";
		List deparmentList=new ArrayList();
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if (rs.next()){ 
				departmentList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return departmentList;
	}
	
	
	public List getAllDesignations(UserVO _userVO)
	
	{
		List designationList=new ArrayList();
		Map _populateMap = new HashMap();
		String query = "";
		String queryKey="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.DESIGNATION_NAME.GBLT_DEPARTMENT_MST";
		List deparmentList=new ArrayList();
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq=new Sequence();
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if (rs.next()) {
				designationList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return designationList;
	}
	
	
	public StaffEnquiryVO[] searchStaffDetail(HashMap staffDetailMap,UserVO _userVO)
	
	{
	StaffEnquiryVO[] staffEnquiryVOs=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String whereCOndition="";
	Connection conn = super.getTransactionContext().getConnection();
	String finalQuery="";
	String orderby="order by  str_emp_full_name";
	Sequence sq=new Sequence();
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES)){
		queryKey = "SEARCH_STAFF_DETAIL.PIST_EMP_PERSONNEL_DTL.ESTATE_MAPPING_REQ";
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
	}
	else{
		queryKey = "SEARCH_STAFF_DETAIL.PIST_EMP_PERSONNEL_DTL";
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
	}			
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		System.out.println("Query " + query);
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	Set keySet=(Set)staffDetailMap.keySet();
	
	Iterator keySetItr=keySet.iterator();

	
	while(keySetItr.hasNext())
	{
		String mapKey=(String)keySetItr.next();
		String mapValue=(String)staffDetailMap.get(mapKey);
		if(mapKey.equals("str_emp_full_name") ){
			if(!mapValue.equals(" "))
			whereCOndition=whereCOndition+" AND "+"( UPPER (str_emp_full_name) SIMILAR TO UPPER ('%"+mapValue+"%'))";
		}
		if(mapKey.equals("str_middle_name") ){
			if(!mapValue.equals(" "))
			whereCOndition=whereCOndition+" AND "+"( UPPER (str_emp_full_name) SIMILAR TO UPPER ('%"+mapValue+"%'))";
		}
		if(mapKey.equals("str_last_name") ){
			if(!mapValue.equals(" "))
			whereCOndition=whereCOndition+" AND "+"( UPPER (str_emp_full_name) SIMILAR TO UPPER ('%"+mapValue+"%'))";
		}
		if(mapKey.equals("num_desig_id") ){
			if(!mapValue.equals("%"))
			whereCOndition=whereCOndition+" AND "+"(UPPER (A.GNUM_DESIG_CODE) LIKE UPPER ('"+mapValue+"'))";
		}
		if(mapKey.equals("num_dept_id") ){
			if(!mapValue.equals("%"))
			whereCOndition=whereCOndition+" AND "+"(UPPER (A.GNUM_DEPT_CODE) LIKE UPPER ('"+mapValue+"'))";
		}
		
		if(mapKey.equals("str_gender_name") ){
			//if(!mapValue.equals("%"))
			//	whereCOndition=whereCOndition+" AND "+"(UPPER ("+mapKey+")=(select upper(GSTR_GENDER_SHORT) from GBLT_GENDER_MST where GNUM_GENDER_CODE  like ('"+mapValue+"')))";
			if(!mapValue.equals("%"))
				whereCOndition=whereCOndition+" AND "+"(UPPER (GSTR_GENDER_CODE) like ('"+mapValue+"'))";
			
		}
		 
	}
	
	System.out.println("query" + query);
	finalQuery=query+" "+whereCOndition+" "+orderby;
	System.out.println("final query === "+ finalQuery);
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, finalQuery,_populateMap);
		if (rs.next()){
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(StaffEnquiryVO.class, rs);
			staffEnquiryVOs=new StaffEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				staffEnquiryVOs[i]=(StaffEnquiryVO)valueObjects[i];
			}
		}
		else {
			throw new HisRecordNotFoundException("No Staff Record Found");
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
	}
		return staffEnquiryVOs;
	}
	
	
	public List getFreePatientCatList(UserVO userVO)
	{
		List patCatList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.FREE_PATIENT.GBLT_PATIENT_CAT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
	
		_populateMap.put(sq.next(),Config.PATIENT_CAT_TYPE);
		_populateMap.put(sq.next(),Config.PATIENT_CAT_TYPE_IS_PAID_NO);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			while(rs.next()){
				patCatList.add(rs.getString(1));
		}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return patCatList;
	}
	
	public List getAllDepartmentWithType(UserVO userVO)
	{
		List list=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.DEPARTMENT_AND_TYPE.GBLT_DEPARTMENT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
	
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_INACTIVE);
		
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("No Record For Departments Found");
			list=HelperMethodsDAO.getAlOfEntryObjects(rs);
		
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return list;
	}
	
	public HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetailWithUnit(String _deptCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
		queryKey = "SELECT.DEPARTMENT_DETAILS.GBLT_DEPARTMENT_MST.HGBT_UNIT_MST";
	else
		queryKey = "SELECT.DEPARTMENT_DETAILS_WITHOUT_LOCATION_MAPPING";
		Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_deptCode);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("Department Details Not Found");
		} else {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
			departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO: :: " + e);
	}
		return departmentEnquiryVO;
	}

	//getting all consultants 

	public List getAllConsultant(String processType,UserVO userVO)
	{
		HospitalConsultantEnquiryVO  consultantVO=null;
		List<HospitalConsultantEnquiryVO> consultantVOList=new ArrayList<HospitalConsultantEnquiryVO>();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_CONSULTANT.HGBT_UNIT_CONSULTANT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
	
		_populateMap.put(sq.next(),processType);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
			
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("No Consultant Found");
			else{
				rs.beforeFirst();
				while(rs.next()){
					consultantVO=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantVO, rs);
					consultantVOList.add(consultantVO);
				}
			}
		
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return consultantVOList;
	}
	
	public HospitalConsultantEnquiryVO[] getConsultantDetailsBYEmpNo(String empNo,UserVO userVO)
	{
		HospitalConsultantEnquiryVO [] consultantVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
			queryKey = "SELECT.CONSULTANT_DETAIL_BY_EMPNO.HGBT_UNIT_CONSULTANT_MST";
		else
			queryKey = "SELECT.CONSULTANT_DETAIL_BY_EMPNO_WITHOUT_LOCATION_MAPPING.HGBT_UNIT_CONSULTANT_MST";
		
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),empNo);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()) len++;
				consultantVO=new HospitalConsultantEnquiryVO[len];
				len=0;
				rs.beforeFirst();
				while(rs.next()){
					consultantVO[len]=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantVO[len], rs);
					len++;
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return consultantVO;
	}


	public HospitalDepartmentEnquiryVO[] getDepartmentEnquiryDetailWithoutUnit(String _deptCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
		queryKey = "SELECT.DEPARTMENT_DETAILS_WITH_MAPPING.GBLT_DEPARTMENT_MST";
	else
		queryKey = "SELECT.DEPARTMENT_DETAILS_WITHOUT_LOCATION_MAPPING.GBLT_DEPARTMENT_MST";
		Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_deptCode);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (!rs.next()) {
			throw new HisRecordNotFoundException("Department Details Not Found");
		} else {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
			departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO: :: " + e);
	}
		return departmentEnquiryVO;
	}
	
	public HospitalDepartmentEnquiryVO[] getParaClinicDepartmentEnquiryDetail(String _deptCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
		Map _populateMap = new HashMap();
		ValueObject[] valueObjects=null;
		String query = "";
		String queryKey="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
			queryKey = "SELECT.PARA_CLINIC_DEPARTMENT_DETAILS_WITH_MAPPING.GBLT_LABORATORY_MST";
		else
			queryKey = "SELECT.PARA_CLINIC_DEPARTMENT_DETAILS_WITHOUT_LOCATION_MAPPING.GBLT_LABORATORY_MST";
		Connection conn = super.getTransactionContext().getConnection();
		
		
		Sequence sq=new Sequence();
		
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_deptCode);
		
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if (!rs.next()) {
				throw new HisRecordNotFoundException("Department Details Not Found");
			} else {
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
				departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
				}
			}
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return departmentEnquiryVO;
	}
	
public HospitalDepartmentEnquiryVO getDepartmentUnitEnquiryDetail(String _deptUnitCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO departmentEnquiryVO=new HospitalDepartmentEnquiryVO();
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	
	if(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
		queryKey = "SELECT.DEPARTMENT_UNIT_DETAILS_LOCATION_MAPPING.HGBT_UNIT_MST";
	else
		queryKey = "SELECT.DEPARTMENT_UNIT_DETAILS_LOCATION_MAPPING_NO.HGBT_UNIT_MST";
		Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_deptUnitCode);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (rs.next()) {
			rs.beforeFirst();
			HelperMethods.populateVOfrmRS(departmentEnquiryVO, rs);
		} 
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO: :: " + e);
	}
		return departmentEnquiryVO;
	}

	public HospitalDepartmentEnquiryVO[] getDepartmentUnitConsultantDetail(String _deptUnitCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	
	
		queryKey = "SELECT.UNIT_CONSULTANT_DETAILS.HGBT_UNIT_CONSULTANT_MST";
		Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_deptUnitCode);
	_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (rs.next()) {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
			departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
			}
		}
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO: :: " + e);
	}
		return departmentEnquiryVO;
	}
	
public HospitalDepartmentEnquiryVO[] getDepartmentUnitRoomDetail(String _deptUnitCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	
	
		queryKey = "SELECT.UNIT_ROOM_DETAILS.HGBT_UNIT_CONSULTANT_MST";
		Connection conn = super.getTransactionContext().getConnection();
	
	
	Sequence sq=new Sequence();
	
	_populateMap.put(sq.next(),_deptUnitCode);
	_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
					
	try {
		query = HelperMethodsDAO.getQuery(filename, queryKey);
		
	} catch (Exception e) {
		throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
	}
	try {
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
		if (rs.next()) {
			rs.beforeFirst();
			valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
			departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
			for(int i=0;i<valueObjects.length;i++){
				departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
			}
		} 
	} catch (Exception e) {
		if (e.getClass() == HisRecordNotFoundException.class) {
			throw new HisRecordNotFoundException(e.getMessage());
		} else
			throw new HisDataAccessException("EnquiryDAO: :: " + e);
	}
		return departmentEnquiryVO;
	}

public String getUnitWorkingDays(String unitCode,UserVO _userVO)
{

	String query =  "" ;
	Map populateMap =new HashMap();
	String filename=RegistrationConfig.QUERY_FILE_FOR_MASTERSDAO;
	String queryKey="SELECT.UNIT_WORKING_DAYS_FROM_DUAL"; 	  
	

	
	Sequence sq=new Sequence();
	
	populateMap.put(sq.next(),unitCode);
	populateMap.put(sq.next(),_userVO.getHospitalCode());
	
	
	String msg;	
	Connection conn =super.getTransactionContext().getConnection();
	
	try{
 	      query =HelperMethodsDAO.getQuery(filename,queryKey);
	}catch(Exception e){
		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
	}
	
	try{
		ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
		/*if(!rs.next())
		{
			throw new HisRecordNotFoundException("");
		}*/
		rs.beforeFirst();
 	    rs.next();

 	   msg=rs.getString(1); 
 	  
 	    
	}
	catch(Exception e){
		e.printStackTrace();
		
		if(e.getClass()==HisRecordNotFoundException.class){
			throw new HisRecordNotFoundException();	
		}
		else			 		
		 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
	 }			 	 	
	return msg;
}

	public HospitalDepartmentEnquiryVO[] getDepartmentUnitWardDetail(String _deptUnitCode,UserVO _userVO)
	
	{
		HospitalDepartmentEnquiryVO[] departmentEnquiryVO=null;
	Map _populateMap = new HashMap();
	ValueObject[] valueObjects=null;
	String query = "";
	String queryKey="";
	String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;

	
	queryKey = "SELECT.UNIT_WARD_DETAILS.HIPT_DUWRBED_MST";
	Connection conn = super.getTransactionContext().getConnection();

		
		Sequence sq=new Sequence();
		
		_populateMap.put(sq.next(),_deptUnitCode);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		
						
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if (rs.next()) {
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(HospitalDepartmentEnquiryVO.class, rs);
				departmentEnquiryVO=new HospitalDepartmentEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					departmentEnquiryVO[i]=(HospitalDepartmentEnquiryVO)valueObjects[i];
				}
			} 
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
			return departmentEnquiryVO;
	}

	

	public HospitalConsultantEnquiryVO[] getConsultantDetailsByName(HospitalConsultantEnquiryVO consultantVO,UserVO userVO)
	{
		HospitalConsultantEnquiryVO [] consultantVOArray=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.CONSULTANT_DETAIL_BY_NAME.HGBT_UNIT_CONSULTANT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),consultantVO.getConsultantName());
		_populateMap.put(sq.next(),consultantVO.getDepartmentCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()) len++;
				consultantVOArray=new HospitalConsultantEnquiryVO[len];
				len=0;
				rs.beforeFirst();
				while(rs.next()){
					consultantVOArray[len]=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantVOArray[len], rs);
					len++;
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return consultantVOArray;
	}
	
	public String getRoomByDeptUnit(String deptUnit,UserVO userVO)
	{
		String room="";
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ROOM_BY_DEPT_UNIT.HGBT_ROOM_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),deptUnit);
			try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				{//throw new HisRecordNotFoundException("Detail Not Found");
				}
				
			else{
				rs.beforeFirst();
				while(rs.next()){
					room=room+rs.getString(1)+"#";
				}

			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return room;
	}
	
	public String getUnitWorkingDetail(String deptUnit,UserVO userVO)
	{
		String workingDetail="";
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.UNIT_WORKING_DTL.HGBT_UNIT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),deptUnit);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),deptUnit);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
			{//throw new HisRecordNotFoundException("Detail Not Found");
			}
			
			else{
				rs.beforeFirst();
				while(rs.next()){
					workingDetail=rs.getString(1);
				}
				
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return workingDetail;
	}
	
	/* ****************************ward Enquiry********************************/
	
	public List getWardList(UserVO _uservo) {
		List wardList=new ArrayList();
		HospitalWardEnquiryVO  wardEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_WARD.HIPT_WARD_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					wardEnquiryVO=new HospitalWardEnquiryVO();
					HelperMethods.populateVOfrmRS(wardEnquiryVO, rs);
					wardList.add(wardEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return wardList;
	}
	
	public List getWardEnquiryDetail(String wardCode, UserVO _uservo) {
		List wardList=new ArrayList();
		HospitalWardEnquiryVO  wardEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.WARD_DETAIL.HIPT_WARD_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),wardCode);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					wardEnquiryVO=new HospitalWardEnquiryVO();
					HelperMethods.populateVOfrmRS(wardEnquiryVO, rs);
					wardList.add(wardEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return wardList;
	}
	

	/* ********************************* Opd Schedule Enquiry *****************************/
	
	public List getAllDepartmentList(UserVO _uservo) {
		List voList=new ArrayList();
		OpdScheduleEnquiryVO  opdScheduleEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_DEPARTMENT_FOR_OPD_SCHEDULE.GBLT_DEPARTMENT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_INACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
			
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
					HelperMethods.populateVOfrmRS(opdScheduleEnquiryVO, rs);
					voList.add(opdScheduleEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return voList;
	}
	
	public List getSpecialClinicDeptList(UserVO _uservo) {
		List voList=new ArrayList();
		OpdScheduleEnquiryVO  opdScheduleEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.SPECIAL_CLINIC_DEPT.GBLT_DEPARTMENT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_INACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
					HelperMethods.populateVOfrmRS(opdScheduleEnquiryVO, rs);
					voList.add(opdScheduleEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO::: " + e);
		}
		return voList;
	}
	
	public List getOpdScheduleEnquiryDetail(String departmentCode,UserVO _uservo) {
		List voList=new ArrayList();
		OpdScheduleEnquiryVO  opdScheduleEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.UNIT_WORKING_DETAIL.HGBT_UNIT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),departmentCode);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
					HelperMethods.populateVOfrmRS(opdScheduleEnquiryVO, rs);
					voList.add(opdScheduleEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getSpecialClinicDetail(String departmentUnitCode,UserVO _uservo) {
		List voList=new ArrayList();
		OpdScheduleEnquiryVO  opdScheduleEnquiryVO=null;
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.SPECIAL_CLINIC_WORKING_DETAIL.HGBT_UNIT_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),departmentUnitCode);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					opdScheduleEnquiryVO=new OpdScheduleEnquiryVO();
					HelperMethods.populateVOfrmRS(opdScheduleEnquiryVO, rs);
					voList.add(opdScheduleEnquiryVO);
				}
			}
			
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getRoomByDept(String departmentCode,UserVO _uservo) {
		List roomList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ROOM_BY_DEPT.HRGT_DEPT_UNIT_ROOM_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),departmentCode);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				roomList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return roomList;
	}
	
	public List getTelePhoneDetailOfIsImportant(UserVO _uservo) {
		HospitalTelephoneEnquiryVO telephoneEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.TELEPHONE_DETAIL.EST_TELEPHONE_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),enquiryConfig.TELEPHONE_ENQUIRY_IS_IMPORTANT_YES);
				
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					telephoneEnqVO=new HospitalTelephoneEnquiryVO();
					HelperMethods.populateVOfrmRS(telephoneEnqVO, rs);
					voList.add(telephoneEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getTelePhoneDetailOfDept(UserVO _uservo) {
		HospitalTelephoneEnquiryVO telephoneEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.TELEPHONE_DETAIL_OF_DEPT.EST_TELEPHONE_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("Detail Not Found");
			}	
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					telephoneEnqVO=new HospitalTelephoneEnquiryVO();
					HelperMethods.populateVOfrmRS(telephoneEnqVO, rs);
					voList.add(telephoneEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getTelePhoneDetailOfEmployee(UserVO _uservo) {
		HospitalTelephoneEnquiryVO telephoneEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.TELEPHONE_DETAIL.PIST_EMP_REGISTRATION_DTL";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("Detail Not Found");
			}	
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					telephoneEnqVO=new HospitalTelephoneEnquiryVO();
					HelperMethods.populateVOfrmRS(telephoneEnqVO, rs);
					voList.add(telephoneEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getOperationTheaterList(UserVO _uservo) {
		OperationTheaterEnquiryVO operationTheaterEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_OPERATION_THEATER.HOTT_OPERATION_THEATER_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					operationTheaterEnqVO=new OperationTheaterEnquiryVO();
					HelperMethods.populateVOfrmRS(operationTheaterEnqVO, rs);
					voList.add(operationTheaterEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public HospitalMstVO getHospitalEssentials1(UserVO userVO)
	{
		HospitalMstVO _hospitalVO=new HospitalMstVO(); 
		
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.GBLT_HOSPITAL_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
	
		
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			
			
			if(rs.next())
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(_hospitalVO,rs);
				
			}
			else
				throw new HisRecordNotFoundException("No Record For Departments Found");
			
						
			
		
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return _hospitalVO;
	}	
	
	public HospitalRegistrationTimingsVO[] getHospitalEssentials2(UserVO userVO)
	{
		HospitalRegistrationTimingsVO[] _hospitalTimingsVO=null;
		ValueObject[] valueObjects=null;
		
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT_REG_TIMINGS.HRGT_REG_CAT_TIMINGS";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
	
		
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(rs.next())
			{
				rs.beforeFirst();
								
				valueObjects=HelperMethods.populateVOfrmRS(HospitalRegistrationTimingsVO.class, rs);
				_hospitalTimingsVO=new HospitalRegistrationTimingsVO[valueObjects.length];
			
				for(int i=0;i<valueObjects.length;i++){
					_hospitalTimingsVO[i]=(HospitalRegistrationTimingsVO)valueObjects[i];
													  }
			}
			else
				throw new HisRecordNotFoundException("No Record For Departments Found");
			
		
		} catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return _hospitalTimingsVO;
	}	
	
	public List getLaboratoryList(UserVO _uservo) {
		HospitalLabEnquiryVO labEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.ALL_LABORATORY_LIST.GBLT_LABORATORY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next()){
				//throw new HisRecordNotFoundException("Detail Not Found");
			}
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					labEnqVO=new HospitalLabEnquiryVO();
					HelperMethods.populateVOfrmRS(labEnqVO, rs);
					voList.add(labEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getLabTestList(String labCode,UserVO _uservo) {
		HospitalLabEnquiryVO labEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.TESTS.GBLT_TEST_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),labCode);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					labEnqVO=new HospitalLabEnquiryVO();
					HelperMethods.populateVOfrmRS(labEnqVO, rs);
					voList.add(labEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	/* *****************************OT Consultant Enquiry *****************************************/
	
	public List getOTConsultantList(UserVO _uservo) {
		HospitalConsultantEnquiryVO consultantEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.OT_CONSULTANT.HOTT_SURGEON_ROSTER_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					consultantEnqVO=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantEnqVO, rs);
					voList.add(consultantEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getOTConsultantWorkingDays(UserVO _uservo) {
		HospitalConsultantEnquiryVO consultantEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.OT_CONSULTANT_WORKING_DAYS.HOTT_SURGEON_ROSTER_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				int len=0;
				while(rs.next()){
					consultantEnqVO=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantEnqVO, rs);
					voList.add(consultantEnqVO);
				}
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getOTConsultantDetail(String empNo ,UserVO _uservo) {
		HospitalConsultantEnquiryVO consultantEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.CONSULTANT_DETAIL.HOTT_SURGEON_ROSTER_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),empNo);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				while(rs.next()){
					consultantEnqVO=new HospitalConsultantEnquiryVO();
					HelperMethods.populateVOfrmRS(consultantEnqVO, rs);
					voList.add(consultantEnqVO);
				}	
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getHolidayList(String year ,UserVO _uservo) {
		HospitalHolidayEnquiryVO holidayEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.HOLIDAY.PIST_HOLIDAY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),year);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				while(rs.next()){
					holidayEnqVO=new HospitalHolidayEnquiryVO();
					HelperMethods.populateVOfrmRS(holidayEnqVO, rs);
					voList.add(holidayEnqVO);
				}	
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getHolidayYearList(UserVO _uservo) {
		
		List yearList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.YEAR.PIST_HOLIDAY_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				yearList=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return yearList;
	}
	
	
	public List getGuestHouseList(UserVO _uservo) {
		GuestHouseEnquiryVO guestHouseEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.GUEST_HOUSE.EST_GUEST_HOUSE_MST";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				while(rs.next()){
					guestHouseEnqVO=new GuestHouseEnquiryVO();
					HelperMethods.populateVOfrmRS(guestHouseEnqVO, rs);
					voList.add(guestHouseEnqVO);
				}	
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List getGuestHouseBedDetail(String guestHouse,UserVO _uservo) {
		GuestHouseEnquiryVO guestHouseEnqVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT.GUEST_HOUSE_DETAIL.EST_GUEST_HOUSE_BEDID_MAP";
		Sequence sq=new Sequence();
		try{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		} 
		catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(),_uservo.getHospitalCode());
		_populateMap.put(sq.next(),guestHouse);
		
		try {
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else{
				rs.beforeFirst();
				while(rs.next()){
					guestHouseEnqVO=new GuestHouseEnquiryVO();
					HelperMethods.populateVOfrmRS(guestHouseEnqVO, rs);
					voList.add(guestHouseEnqVO);
				}	
			}
			
		} 
		catch (Exception e) {
			if (e.getClass() == HisRecordNotFoundException.class) {
				throw new HisRecordNotFoundException(e.getMessage());
			} else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public List<Entry> getTemplateListForGuidlines(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "ESSENTIAL.TEMPLATE_LIST_FOR_GUIDLINE.HGBT_TEMPLATE_MST";
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), GenericTemplateConfig.PARAMETER_TYPE_GUIDELINES);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);
			//populateMAP.put(sq.next(), _userVO.getHospitalCode());
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("OpdEssentialDAO.getDosageFrequecy::populateMAP" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			//if (!rs.next()) throw new HisRecordNotFoundException( "No EXT Treatment Exists  ");
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		List alRecord = new ArrayList();
		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException:getDosageFrequecy" + e);
		}
		return alRecord;
	}
	
	public List<DeceasedDetailVO> getAllDeceasedListInMortuary(UserVO userVO)
	{
		DeceasedDetailVO deceasedDtlVO=null; 
		List voList=new ArrayList();
		Map _populateMap = new HashMap();
		String query="";
		String queryKey="";
		Connection conn = super.getTransactionContext().getConnection();
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		queryKey = "SELECT_DECEASED_DETAIL_LIST.HMRT_DECEASED_DTL";
		Sequence sq=new Sequence();
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} 
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), userVO.getHospitalCode());
		_populateMap.put(sq.next(), MortuaryConfig.BODY_STATUS_HANDOVER);
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query,_populateMap);
			if(!rs.next())
				throw new HisRecordNotFoundException("Detail Not Found");
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					deceasedDtlVO=new DeceasedDetailVO();
					HelperMethods.populateVOfrmRS(deceasedDtlVO, rs);
					voList.add(deceasedDtlVO);
				}	
			}
			
		} 
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
				throw new HisRecordNotFoundException(e.getMessage());
			else
				throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return voList;
	}
	
	public MortuaryDeceasedImageDtlVO getDeceasedDefaultImage(String deceasedNo,UserVO userVO)
	{
		MortuaryDeceasedImageDtlVO deceasedImageVO=new MortuaryDeceasedImageDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey="SELECT_DECEASED_DEFAULT_IMAGE.HMRT_DECEASED_IMAGE_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),MortuaryConfig.IS_DEFAULT_IMAGE_YES);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				deceasedImageVO=null;
				//throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedImageVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return deceasedImageVO;
	}
	
	public DeceasedDetailVO getDeceasedGeneralAppearance(String deceasedNo,UserVO userVO)
	{
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey="SELECT_DECEASED_GENERL_APPEARANCE_BY_DECEASED_NO.HMRT_DECEASED_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedDtlVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return deceasedDtlVO;
	}
	
	public String getPostmortemStatusForHandover(String deceasedNo,UserVO userVO)
	{
		String query  = "";
		String str="";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	    String queryKey="GET_POSTMORTEM_STATUS_FOR_HANDOVER.HMRT_POSTMORTEM_REQ_DTL";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        	{
        		str="Not Raised@0";
        	}
        	else
        	{
        		rs.first();
        		str=rs.getString(1);
        	}
           
            
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return str;
	}
	
	public String getDeceasedStorageDetail(String deceasedNo,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	    String queryKey="GET_DECEASED_STORAGE_DETAIL_BY_DECEASED_NO";
	    
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),deceasedNo);
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	if(!rs.next())
        		throw new HisRecordNotFoundException("");
            rs.first();
            return rs.getString(1);
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
	}
	
	public DeceasedDetailVO[] searchDeceased(String fName,String mName,String lName,String genderCode,String fromDate,String toDate,String chkUnknown,String chkUnclaimed,UserVO userVO)
	{
		DeceasedDetailVO[] arrDeceasedDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		

		try
		{
			query="SELECT A.HMRNUM_DECEASED_NO AS deceasedNo, A.HRGNUM_PUK AS patCrNo, A.HMRNUM_ENTRY_MODE AS entryMode, A.HMRSTR_FNAME AS patFirstName, " 
					+"TO_CHAR(A.HMRDT_RECEIVED_DATETIME,'dd-Mon-yyyy HH24:MI') AS receivedDateTime, A.HMRSTR_MNAME AS patMiddleName, A.HMRSTR_LNAME AS patLastName, " 
					+"A.GNUM_GENDER_CODE AS patGenderCode, TO_CHAR(A.HMRDT_DEATH_DATETIME,'dd-Mon-yyyy HH24:MI') AS deathDate, A.HMRNUM_BODY_STATUS AS bodyStatus, " 
					+"A.HMRNUM_ISBROUGHT_DEAD AS isBroughtDead, A.HMRNUM_ISCLAIMED AS isClaimed, A.HMRNUM_UNIDENTIFIED_BODY AS unidentifiedBody, AHIS_UTIL.DOB_AGE_ON(A.HMRDT_DOB,SYSDATE) AS patAge, " 
					+"(SELECT D.GSTR_GENDER_NAME FROM GBLT_GENDER_MST D WHERE D.GSTR_GENDER_CODE =A.GNUM_GENDER_CODE AND A.GNUM_ISVALID = A.GNUM_ISVALID) AS patGender, "
					+" DECODE(A.HMRNUM_BODY_STATUS,0,'Stretcher',1,'Chamber',3,'Handover') AS bodyStatusName "
					+"FROM HMRT_DECEASED_DTL A WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? ";
			
			if(fName!=null && !fName.equals(""))
				query=query+" AND UPPER(A.HMRSTR_FNAME) LIKE UPPER('"+fName+"%') ";
			if(mName!=null && !mName.equals(""))
				query=query+" AND UPPER(A.HMRSTR_MNAME) LIKE UPPER('"+mName+"%') ";
			if(lName!=null && !lName.equals(""))
				query=query+" AND UPPER(A.HMRSTR_LNAME) LIKE UPPER('"+lName+"%') ";
			if(!genderCode.equals("-1"))
				query=query+" AND A.GNUM_GENDER_CODE= '"+genderCode+"'" ;
			if(fromDate!=null && !fromDate.equals(""))
				query=query+" AND trunc(A.HMRDT_DEATH_DATETIME)>=TO_DATE('"+fromDate+"','dd-Mon-yyyy') ";
			if(toDate!=null && !toDate.equals(""))
				query=query+" AND trunc(A.HMRDT_DEATH_DATETIME)<=TO_DATE('"+toDate+"','dd-Mon-yyyy') ";
			if(chkUnknown!=null && chkUnknown.equals(MortuaryConfig.YES))
				query=query+" AND A .HMRNUM_UNIDENTIFIED_BODY = "+chkUnknown;
			if(chkUnclaimed!=null && chkUnclaimed.equals(MortuaryConfig.NO))	
				query=query+" AND A.HMRNUM_ISCLAIMED = "+chkUnclaimed;
			
			query=query+" ORDER BY A.HMRSTR_FNAME, A.HMRSTR_MNAME, A.HMRSTR_LNAME ";
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				//arrDeceasedDtlVO = null;
				throw new HisRecordNotFoundException("No Record Found");
			}
			else
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(DeceasedDetailVO.class, rs);
				arrDeceasedDtlVO = new DeceasedDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeceasedDtlVO[i] = (DeceasedDetailVO) vo[i];
				}
			}	
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}
		return arrDeceasedDtlVO;
	}
	
	public DeceasedHandoverDtlVO getDeceasedHandoverDetail(String deceasedNo,UserVO userVO)
	{
		DeceasedHandoverDtlVO deceasedHandoverDtlVO=new DeceasedHandoverDtlVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey="SELECT_DECEASED_HANDOVER_DETAIL_BY_DECEASED_NO.HMRT_DECEASED_HANDOVER_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		
		populateMap.put(sq.next(),deceasedNo);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename,queryKey);
		}
		catch(Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::"+e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while(rs.next())
				{
					HelperMethods.populateVOfrmRS(deceasedHandoverDtlVO,rs);
				}
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();	
			}
			else			 		
			 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		 }	
		return deceasedHandoverDtlVO;
	}
	
	

	public List getUnitWorkingSchedule(String departmentUnitCode,UserVO userVO)
	{
		String query  = "";
	    Map populateMAP =new HashMap();
	    ResultSet rs;
	    Sequence sq=new Sequence();
	    String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
	    String queryKey="SELECT.UNIT_WORKING_ROOM_DAYS_TIMING";
	    
	    List unitWorkingDetailList=new ArrayList();
	    try
		{
		    query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),departmentUnitCode);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
	    }
		try
        {
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
        	/*if(!rs.next())
        		throw new HisRecordNotFoundException("");*/
            //rs.beforeFirst();
            while(rs.next()){
            	unitWorkingDetailList.add(rs.getString(1));
            }
            
        }
        catch(Exception e)
        {
        	if(e.getClass()==HisRecordNotFoundException.class)
        	{
        		throw new HisRecordNotFoundException(e.getMessage());	
        	}
        	else
        		throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
        }
        return unitWorkingDetailList;
	}
	
	
	public String getFloorNameByFloorId(String floorId,UserVO userVO)
	{
		String query  = "";
		Map populateMAP =new HashMap();
		ResultSet rs;
		Sequence sq=new Sequence();
		String filename= enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey="SELECT.FLOOR_NAME.EST_FLOOR_MST";
		String floorName="";
		
		List unitWorkingDetailList=new ArrayList();
		try
		{
			query =HelperMethodsDAO.getQuery(filename,queryKey);   		 	 
		}
		catch(Exception e)
		{	
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);	 		  
		}
		try
		{
			populateMAP.put(sq.next(),userVO.getHospitalCode());
			populateMAP.put(sq.next(),floorId);
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("populateMAP::"+e);
		}
		try
		{
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMAP);
			if(rs.next()){
				rs.first();
				floorName=rs.getString(1);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else
				throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		return floorName;
	}
	
public CommonEnquiryVO[] searchInPatient(HashMap _finalQueryMap,UserVO _userVO, String isCurrentHospitalSearch_p)
	
	{
		CommonEnquiryVO [] commonEnquiryVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		PatientVO[] _patVO=null;
		String query="";
		String filename=enquiryConfig.QUERY_FILE_FOR_ENQUIRY_DAO;
		String queryKey = "SEARCH_INPATIENT_BY_PATIENT_ADREESS_DEPARTMENT";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY trim(hrgstr_fname), trim(hrgstr_mname), trim(hrgstr_lname)";
		Sequence sq = new Sequence();
		boolean isDefaultState=false;
		
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
//		_populateMap.put(sq.next(),Config.SUPER_USER_HOSPITAL_CODE);
		_populateMap.put(sq.next(),(String)_finalQueryMap.get("gnum_dept_code"));
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		if("1".equals(isCurrentHospitalSearch_p))
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
		else
			_populateMap.put(sq.next(),"%");
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(),_userVO.getHospitalCode());
	
		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			//System.out.println("Query " + query);
		} catch (Exception e) {
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
		}
		//////////////Preparing query to fetch patient records using patient details/////////
				
		
		Set keySetTemp=(Set)_finalQueryMap.keySet();
		
		Iterator keySetItrTemp=keySetTemp.iterator();
		
		//For Unknown MLC patient Change done by Singaravelan on 26-Aug-2014 
		if(keySetTemp.contains("gnum_state_code")){
		while(keySetItrTemp.hasNext()){
			String mapKey=(String)keySetItrTemp.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			if(mapKey.equals("gnum_state_code"))
				if(mapValue.equals(RegistrationConfig.REGISTRATIONDESK_DEFAULT_STATE_CODE))
					isDefaultState=true;
		}
		}
		
		Set keySet=(Set)_finalQueryMap.keySet();
		
		Iterator keySetItr=keySet.iterator();
		
		HashMap _tempQueryMap=new HashMap();
		
		while(keySetItr.hasNext())
		{
			String mapKey=(String)keySetItr.next();
			String mapValue=(String)_finalQueryMap.get(mapKey);
			
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname") ||
					mapKey.equals("hrgstr_father_name") || mapKey.equals("hrgstr_spousename"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
			}
			
			if(isDefaultState){
				if(mapKey.equals("hrgstr_house_no") || mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
						mapKey.equals("hrgstr_city") || mapKey.equals("gstr_tehsil") )
				{
					whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
				}
				//if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
				if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
				mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
						mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
						mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
				{
					whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
				}				
				
			}
			else{
				if(mapKey.equals("hrgstr_house_no") || mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
						mapKey.equals("hrgstr_city") || mapKey.equals("gstr_tehsil") )
				{
					whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue.trim()+"%'))";
				}
				if(keySet.contains("hrgnum_is_mlc"))
				{
					//if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
							mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||  
							mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
							mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
					{
						whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
					}	
				}
				else
				{
				//if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					if(mapKey.equals("gstr_gender_code") || mapKey.equals("gnum_city_loc_code") ||
						//mapKey.equals("hrgstr_state_name") || mapKey.equals("gnum_state_code") || mapKey.equals("gnum_country_code") || mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||
							mapKey.equals("hrgstr_state_name") || mapKey.equals("gnum_state_code") || mapKey.equals("gstr_country_code") || mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code") ||
						mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
						mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
				{
					whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
				}
				}
				
			}
			
			/*if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname") ||
					mapKey.equals("hrgstr_father_name") || mapKey.equals("hrgstr_spousename") || mapKey.equals("hrgstr_house_no") || 
					mapKey.equals("hrgstr_street_no") || mapKey.equals("hrgstr_district") || mapKey.equals("hrgstr_city_location") || 
					mapKey.equals("hrgstr_city") || mapKey.equals("hrgstr_state_name") || mapKey.equals("gstr_tehsil"))
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER ("+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			if(mapKey.equals("fromdate")||mapKey.equals("todate")||mapKey.equals("admissionno")||mapKey.equals("admissionwithin")|| mapKey.equals("gnum_dept_code") ||mapKey.equals("hgnum_deptunitcode") || mapKey.equals("hgnum_ward_code"))
			{
				_tempQueryMap.put(mapKey, mapValue);
			}
			//if(mapKey.equals("todate"))
			//{
				//whereCOndition=whereCOndition +" AND "+ "trunc(hrgdt_register_date) <= trunc(to_date('" +mapValue +"','dd-mm-yyyy'))";
			//}
			if(mapKey.equals("gnum_gender_code") || mapKey.equals("gnum_city_loc_code") ||
					mapKey.equals("gnum_state_code") || mapKey.equals("gnum_country_code")  ||  
					mapKey.equals("hrgnum_pincode") ||   mapKey.equals("hrgnum_isunknown") || mapKey.equals("num_dist_id") || 
					mapKey.equals("hrgnum_is_mlc") || mapKey.equals("hrgnum_is_broughtdead"))
			{
				whereCOndition= whereCOndition + " AND "+ mapKey + "=" + "'" +mapValue +"'";
			}
			*/
			
			
			if(mapKey.equals("hrgstr_contact_no"))
			{
				whereCOndition= whereCOndition + " AND b."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgnum_puk"))
			{
				whereCOndition= whereCOndition + " AND a."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgnum_is_urban"))
			{
				whereCOndition= whereCOndition + " AND a."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgstr_age")){
				System.out.println("hrgstr_age="+mapValue);
				String lowerLimit=mapValue.split("-")[0];
				String upperLimit=mapValue.split("-")[1];

				whereCOndition=whereCOndition+" AND round(DATE_PART('days',(sysdate)-(HRGDT_DOB))/365)  > " + lowerLimit ;
				whereCOndition=whereCOndition+" AND round(DATE_PART('days',(sysdate)-(HRGDT_DOB))/365)  <=" + upperLimit ;
			}
			
		}
		if(!_tempQueryMap.isEmpty())
		{
			Set _tkeySet=(Set)_tempQueryMap.keySet();			
			Iterator _tkeySetItr=_tkeySet.iterator();
			
			//whereCOndition=whereCOndition +" AND a.hrgnum_puk in (SELECT hrgnum_puk FROM hipt_patadmission_dtl WHERE gnum_hospital_code= b.gnum_hospital_code and ";
			
			while(_tkeySetItr.hasNext())
			{
				String mapKey=(String)_tkeySetItr.next();
				String mapValue=(String)_finalQueryMap.get(mapKey);
				if(mapKey.equals("admissionno"))
				{
					whereCOndition=whereCOndition +" AND HIPNUM_ADMNO ="+mapValue+" ";
				}
				if(mapKey.equals("fromdate"))
				{
					whereCOndition=whereCOndition +" AND TRUNC(HIPDT_ADMDATETIME)>trunc(to_date('" +mapValue +"','dd-mon-yyyy'))";
				}
				if(mapKey.equals("todate"))
				{
					whereCOndition=whereCOndition +" AND TRUNC(HIPDT_ADMDATETIME)<=trunc(to_date('" +mapValue +"','dd-mon-yyyy'))";
				}
				if(mapKey.equals("admissionwithin"))
				{
					whereCOndition=whereCOndition +" AND TRUNC (hipdt_admdatetime) BETWEEN TRUNC(SYSDATE-" +mapValue +") AND TRUNC(SYSDATE)";
				}
				if(mapKey.equals("gnum_dept_code"))
				{
					whereCOndition=whereCOndition +" AND GNUM_DEPT_CODE=" +mapValue +" ";
				}
				if(mapKey.equals("hgnum_deptunitcode"))
				{
					whereCOndition=whereCOndition +" AND GNUM_DEPTUNIT_CODE=" +mapValue +" ";
				}
				if(mapKey.equals("hgnum_ward_code"))
				{
					whereCOndition=whereCOndition +" AND HIPNUM_WARD_CODE=" +mapValue +" ";
				}
				
			}
			//whereCOndition=whereCOndition +")";
		}
		finalQuery=query + whereCOndition + orderBy;
		//System.out.println("final query "+finalQuery);
		try {
			rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery,_populateMap);
		} catch (Exception e) {
			
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset"+e);
		}
		
		try {
			if(!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(CommonEnquiryVO.class, rs);
				commonEnquiryVOs=new CommonEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					commonEnquiryVOs[i]=(CommonEnquiryVO)valueObjects[i];
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			if(e.getClass()==HisRecordNotFoundException.class){
				throw new HisRecordNotFoundException(e.getMessage());	
			}
			else			 		
			throw new HisDataAccessException("Enquiry Data access exception "+e);
		}
		
		return commonEnquiryVOs;
	}
	
}
