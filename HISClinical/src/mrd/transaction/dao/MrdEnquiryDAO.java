package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import mrd.MrdConfig;
import mrd.vo.CaseSheetEnquiryVO;
import mrd.vo.CommonCaseSheetEnquiryVO;
import opd.OpdConfig;

public class MrdEnquiryDAO extends DataAccessObject implements MrdEnquiryDAOi 
{
	
	public  MrdEnquiryDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
public CommonCaseSheetEnquiryVO[] searchPatientCaseSheet(HashMap _finalQueryMap,UserVO _userVO)
	
	{
		//CommonEnquiryVO [] commonEnquiryVOs=null;
		CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=null;
		ValueObject[] valueObjects=null;
		Map _populateMap=new HashMap();
		//PatientVO[] _patVO=null;
		String query="";
		String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SEARCH_PATIENT_CASESHEET_ENQUIRY.HIPT_PATADMDISC_DTL";
		String whereCOndition="";
		String finalQuery="";
		ResultSet rs=null;
		String orderBy=" ORDER BY trim(b.hrgstr_fname), trim(b.hrgstr_mname), trim(b.hrgstr_lname)" ;
		Sequence sq = new Sequence();
		
		//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE); 
		//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		//_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),_userVO.getHospitalCode());
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		
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
			if(mapKey.equals("hrgstr_fname") || mapKey.equals("hrgstr_mname") || mapKey.equals("hrgstr_lname") )
					
			{
				whereCOndition=whereCOndition+" AND "+"( UPPER (b."+mapKey+") LIKE UPPER ('"+mapValue+"%'))";
			}
			if(mapKey.equals("fromdate"))
			{
				whereCOndition=whereCOndition +" AND "+ "hipdt_disdatetime >trunc(to_date('" +mapValue +"','dd-Mon-yyyy'))";
			}
			if(mapKey.equals("todate"))
			{
				whereCOndition=whereCOndition +" AND "+ "trunc(hipdt_disdatetime) <= trunc(to_date('" +mapValue +"','dd-Mon-yyyy'))";
			}
			if(mapKey.equals("hipnum_admno") || mapKey.equals("hrgnum_mlc_no") ||
					mapKey.equals("hrgnum_puk") || mapKey.equals("hipdt_disstatus_code"))
			{
				whereCOndition= whereCOndition + " AND a."+ mapKey + "=" + "'" +mapValue +"'";
			}
			if(mapKey.equals("hrgnum_isunknown")  || mapKey.equals("hrgnum_is_broughtdead"))
			{
				whereCOndition= whereCOndition + " AND b."+ mapKey + "=" + "'" +mapValue +"'";
			}
			
			
		}
		finalQuery=query + whereCOndition + orderBy;
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
				throw new HisRecordNotFoundException("No Patient found matching the search criteria");
			}
			else{
				rs.beforeFirst();
				valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
				commonCaseSheetEnquiryVOs=new CommonCaseSheetEnquiryVO[valueObjects.length];
				for(int i=0;i<valueObjects.length;i++){
					commonCaseSheetEnquiryVOs[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
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
		
		return commonCaseSheetEnquiryVOs;
	}

		public CommonCaseSheetEnquiryVO[] searchChronicCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO)
		
		{
			//CommonEnquiryVO [] commonEnquiryVOs=null;
			CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=null;
			ValueObject[] valueObjects=null;
			Map _populateMap=new HashMap();
			//PatientVO[] _patVO=null;
			String query="";
			String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "SEARCH_CHRONIC_CASESHEET_ENQUIRY.HIPT_PATADMDISC_DTL";
			//String whereCOndition="";
			String finalQuery="";
			ResultSet rs=null;
			String orderBy=" ORDER BY trim(b.hrgstr_fname), trim(b.hrgstr_mname), trim(b.hrgstr_lname)" ;
			Sequence sq = new Sequence();
			
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),caseSheetEnquiryVO.getHgnum_pat_alert_id());
			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("Query " + query);
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			//////////////Preparing query to fetch patient records using patient details/////////
					
			
			
			finalQuery=query + orderBy;
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
					throw new HisRecordNotFoundException("No Patient found matching the search criteria");
				}
				else{
					rs.beforeFirst();
					valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
					commonCaseSheetEnquiryVOs=new CommonCaseSheetEnquiryVO[valueObjects.length];
					for(int i=0;i<valueObjects.length;i++){
						commonCaseSheetEnquiryVOs[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
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
			
			return commonCaseSheetEnquiryVOs;
		}

		public CommonCaseSheetEnquiryVO[] searchAllergyCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO)
		
		{
			//CommonEnquiryVO [] commonEnquiryVOs=null;
			CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=null;
			ValueObject[] valueObjects=null;
			Map _populateMap=new HashMap();
			//PatientVO[] _patVO=null;
			String query="";
			String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "SEARCH_ALLERGY_CASESHEET_ENQUIRY.HIPT_PATADMDISC_DTL";
			//String whereCOndition="";
			String finalQuery="";
			ResultSet rs=null;
			String orderBy=" ORDER BY trim(b.hrgstr_fname), trim(b.hrgstr_mname), trim(b.hrgstr_lname)" ;
			Sequence sq = new Sequence();
			
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),caseSheetEnquiryVO.getHgnum_allergy_type_code());
			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("Query " + query);
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			//////////////Preparing query to fetch patient records using patient details/////////
					
			
			
			finalQuery=query + orderBy;
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
					throw new HisRecordNotFoundException("No Patient found matching the search criteria");
				}
				else{
					rs.beforeFirst();
					valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
					commonCaseSheetEnquiryVOs=new CommonCaseSheetEnquiryVO[valueObjects.length];
					for(int i=0;i<valueObjects.length;i++){
						commonCaseSheetEnquiryVOs[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
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
			
			return commonCaseSheetEnquiryVOs;
		}
		
		public CommonCaseSheetEnquiryVO[] searchDiagnosisCaseSheet(CaseSheetEnquiryVO caseSheetEnquiryVO,UserVO _userVO)
		
		{
			//CommonEnquiryVO [] commonEnquiryVOs=null;
			CommonCaseSheetEnquiryVO[] commonCaseSheetEnquiryVOs=null;
			ValueObject[] valueObjects=null;
			Map _populateMap=new HashMap();
			//PatientVO[] _patVO=null;
			String query="";
			String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "SEARCH_DIAGNOSIS_CASESHEET_ENQUIRY.HIPT_PATADMDISC_DTL";
			//String whereCOndition="";
			String finalQuery="";
			ResultSet rs=null;
			String orderBy=" ORDER BY trim(b.hrgstr_fname), trim(b.hrgstr_mname), trim(b.hrgstr_lname)" ;
			Sequence sq = new Sequence();
			
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),OpdConfig.PROFILE_TYPE_DISCHARGE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),_userVO.getHospitalCode());
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(),caseSheetEnquiryVO.getIcdCode().trim());
			
			try {
				query = HelperMethodsDAO.getQuery(filename, queryKey);
				System.out.println("Query " + query);
			} catch (Exception e) {
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+ e);
			}
			//////////////Preparing query to fetch patient records using patient details/////////
					
			
			
			finalQuery=query + orderBy;
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
					throw new HisRecordNotFoundException("No Patient found matching the search criteria");
				}
				else{
					rs.beforeFirst();
					valueObjects=HelperMethods.populateVOfrmRS(CommonCaseSheetEnquiryVO.class, rs);
					commonCaseSheetEnquiryVOs=new CommonCaseSheetEnquiryVO[valueObjects.length];
					for(int i=0;i<valueObjects.length;i++){
						commonCaseSheetEnquiryVOs[i]=(CommonCaseSheetEnquiryVO)valueObjects[i];
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
			
			return commonCaseSheetEnquiryVOs;
		}
		
		
		

		public MrdRecordDtlVO[] fetchRecordStorageDetail(CommonCaseSheetEnquiryVO _commonCaseSheetEnquiryVO,UserVO userVO)
		{
			
			MrdRecordDtlVO[] mrdRecordDtlVO=null;
			ValueObject vo[] = null;
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
			String queryKey = "SELECT_RECORD_STORAGE.HPMRT_MRD_RECORD_DTL";
		
			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
		
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.MRD_RACK_STATUS_WORKING);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _commonCaseSheetEnquiryVO.getMrdRecordId());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_GENERAL_CASESHEET);
			populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_MLC_CASESHEET);
			try
			{
				ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				/*if (!rs.next())
				{
					throw new HisRecordNotFoundException("No record found");
				}*/
				rs.beforeFirst();
		
				vo = HelperMethods.populateVOfrmRS(MrdRecordDtlVO.class, rs);
				mrdRecordDtlVO = new MrdRecordDtlVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					mrdRecordDtlVO[i] = (MrdRecordDtlVO) vo[i];
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
		
			return mrdRecordDtlVO;
		}

}
