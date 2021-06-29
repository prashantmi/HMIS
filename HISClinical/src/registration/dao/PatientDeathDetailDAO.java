package registration.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import registration.RegistrationConfig;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.PatientDeathDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;


public class PatientDeathDetailDAO extends DataAccessObject implements PatientDeathDetailDAOi
{
	public PatientDeathDetailDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}
	
	public void create(PatientDeathDetailVO patDeathDtlVO,UserVO userVO)
	{
		String query  = "";
		 Map populateMAP =new HashMap();
		 String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
		 String queryKey="INSERT.HPMRT_PAT_DEATH_DTL";
		 Sequence sq=new Sequence();
		 
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
			  populateMAP.put(sq.next(), patDeathDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getEpisodeCode());
			  //populateMAP.put(sq.next(), patDeathDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getEpisodeVisitNo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getPatAdmNo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getDepartmentCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getDepartmentUnitCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getWardCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getDeathDate());
			  //populateMAP.put(sq.next(), patDeathDtlVO.getDeathTime());	//time is concate with date
			  populateMAP.put(sq.next(), patDeathDtlVO.getRoomCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getBedCode());
			//  populateMAP.put(sq.next(), patDeathDtlVO.getAttConsultantId());
			  populateMAP.put(sq.next(), userVO.getUserEmpID());
			  populateMAP.put(sq.next(), patDeathDtlVO.getPatMlcNo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getConsultantRemarks());
			  populateMAP.put(sq.next(), patDeathDtlVO.getIsBroughtDead());
			  populateMAP.put(sq.next(), patDeathDtlVO.getBodyHandoverTo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getBodyHandoverDateTime());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOnSetDate());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeName());
			  populateMAP.put(sq.next(), patDeathDtlVO.getImmediateCause1());
			  populateMAP.put(sq.next(), patDeathDtlVO.getImmediateCause2());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeAddress());
			  populateMAP.put(sq.next(), patDeathDtlVO.getImmediateCause3());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getPregnancyRemarks());
			  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), userVO.getSeatId());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOtherCause());
			  populateMAP.put(sq.next(), patDeathDtlVO.getIsPregnant());
			  populateMAP.put(sq.next(), patDeathDtlVO.getIsDelivery());
			  populateMAP.put(sq.next(), patDeathDtlVO.getInjuryNatureCode());
			 // populateMAP.put(sq.next(), patDeathDtlVO.getInjuryTypeCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getInjuryRemarks());
		//	  populateMAP.put(sq.next(), patDeathDtlVO.getEpisodeDate());
			  populateMAP.put(sq.next(), patDeathDtlVO.getDeathMannerCode());
		//	  populateMAP.put(sq.next(), patDeathDtlVO.getVerificationTime());
			  populateMAP.put(sq.next(), patDeathDtlVO.getVerificationDate());
			  populateMAP.put(sq.next(), userVO.getHospitalCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerName());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerDesignation());
			  populateMAP.put(sq.next(), userVO.getSeatId());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerBadgeNo());
			  populateMAP.put(sq.next(), "");		//handover To Emp Id
			  populateMAP.put(sq.next(), patDeathDtlVO.getDeathCertificateId());
					  
			 
		  }
		  catch(Exception e)
		  {
			   	throw new HisApplicationExecutionException("populateMap::"+e);
		  }
		  try
		  {
		   	int i=HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
		   	if(i==0)
		   	{
				throw new HisUpdateUnsuccesfullException();
			}
		  }
		  catch(Exception e)
		  {
		    	e.printStackTrace();
		    	throw new HisUpdateUnsuccesfullException("HelperMethodsDAO.getResultset"+e);
		  } 
		
	}
	
	public boolean checkRecordAdded(String crNo,UserVO userVO)
	{
		boolean exist=false;
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="EXIST.HPMRT_PAT_DEATH_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(),crNo);
	
		
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
				exist=false;
			}
			else
			{
				exist=true;
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
		return exist;
	}
	
	public String generateDeathCertificateId(PatientDeathDetailVO patDeathDtlVO,String genMode,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="GENERATE_DEATH_CERTIFICATE_ID";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), RegistrationConfig.CERTIFICATE_TYPE_DEATH_CERTIFICATE);
		populateMap.put(sq.next(), patDeathDtlVO.getDepartmentUnitCode());
		populateMap.put(sq.next(), genMode);
		populateMap.put(sq.next(), patDeathDtlVO.getDeathCertificateDesc());
		
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
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
	
	
	

/**
## 		Modification Log		: saveHandoverToDetail					
##		Modify Date				: 07-01-2015	
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh 
*/	
	
	public PatientDeathDetailVO getDeathDetailByCrNo(String crNo,UserVO userVO)
	{
		PatientDeathDetailVO patDeathVO=new PatientDeathDetailVO();
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT.DETAIL_BY_CRNO.HPMRT_PAT_DEATH_DTL";
		
		Sequence sq= new Sequence();
		
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), crNo);
		populateMap.put(sq.next(), crNo);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMap.put(sq.next(),userVO.getHospitalCode());
		
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
					HelperMethods.populateVOfrmRS(patDeathVO,rs);
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
		return patDeathVO;
	}
	
	
	
	
/*	public String getHandoverToDetail(String crNo,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= RegistrationConfig.QUERY_FILE_FOR_DAO;
		String queryKey="SELECT_HANDOVER_DETAIL.HRGT_EPISODE_DEATH_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		populateMap.put(sq.next(), crNo);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), userVO.getHospitalCode());
	
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
        	rs=HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(),query,populateMap);
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
*/
	
	
	  public PatientDeathDetailVO getHandoverToDetail(String crNo,UserVO userVO) {
	    	  PatientDeathDetailVO pDetailVO=new PatientDeathDetailVO();
	    	 Map populateMap =new HashMap();
	    	
	    	Sequence sq=new Sequence();
	    	String query="";
	    	String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
	    	String queryKey="SELECT_HANDOVER_DETAIL.HPMRT_PAT_DEATH_DTL";
	    	
	    	populateMap.put(sq.next(), crNo);
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getHospitalCode());
	           	
	    	Connection conn =super.getTransactionContext().getConnection();    	
	    	try{
	    		query =HelperMethodsDAO.getQuery(filename,queryKey);
	    		System.out.println("Query "+query);
	    	}
	    	catch(Exception e){
	    		throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	    	}
	    	System.out.println("query"+query); 	  
	    	try{
	    		System.out.println("before executing query");
	    		ResultSet rs=HelperMethodsDAO.executeQuery(conn, query,populateMap);
	    		    		
	    		if (!rs.next()){
	    			System.out.println("no records");
	    			throw new HisRecordNotFoundException("Patient Death Detail is not added");       			
	    		}
	    		else
	    		{
	    			System.out.println("rsxzczc");
	    			rs.beforeFirst();
	    			HelperMethods.populateVOfrmRS(pDetailVO, rs);
	    		}
	    	}
	    	catch(Exception e){
	    		if(e.getClass()==HisRecordNotFoundException.class){
	    			throw new HisRecordNotFoundException();    			
	    		}
	    		else    		
	    		throw new HisDataAccessException("AddressDAO:retrieveByCrNo:HelperMethods :: "+e);
	    	}
	    	return pDetailVO;
	    } 
	    
	
	  
	  
	  public void updateHandoverDetailTo(PatientDeathDetailVO patDeathDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
			String queryKey = "UPDATE_HANDOVER_DETAIL.HPMRT_PAT_DEATH_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}

			  populateMAP.put(sq.next(), patDeathDtlVO.getBodyHandoverTo());
			  populateMAP.put(sq.next(), patDeathDtlVO.getBodyHandoverDateTime());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeName());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeAddress());
			  populateMAP.put(sq.next(), patDeathDtlVO.getRelativeCode());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerName());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerDesignation());
			  populateMAP.put(sq.next(), patDeathDtlVO.getHandoverBy());
			  populateMAP.put(sq.next(), patDeathDtlVO.getOfficerBadgeNo());
			  populateMAP.put(sq.next(), "");		//handover To Emp Id
			  populateMAP.put(sq.next(), patDeathDtlVO.getDeathCertificateId());
			  populateMAP.put(sq.next(), patDeathDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), _userVO.getHospitalCode());
			  
			try
			{
				HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
		}

	     
	  
	  public void updateCertificateId(PatientDeathDetailVO patDeathDtlVO, UserVO _userVO)
		{
			String query = "";
			Map populateMAP = new HashMap();
			Sequence sq = new Sequence();
			String filename = RegistrationConfig.QUERY_FILE_FOR_DAO;
			String queryKey = "UPDATE_CERTIFICAREID_DETAIL.HPMRT_PAT_DEATH_DTL";

			try
			{
				query = HelperMethodsDAO.getQuery(filename, queryKey);
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
			}
			  populateMAP.put(sq.next(), patDeathDtlVO.getDeathCertificateId());
			  populateMAP.put(sq.next(), patDeathDtlVO.getPatCrNo());
			  populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			  populateMAP.put(sq.next(), _userVO.getHospitalCode());
			  
			try
			{
				HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			}
			catch (Exception e)
			{
				if (e.getClass() == HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());
				}
				else throw new HisDataAccessException("Application Execution Exception" + e);
			}
		}

	
	  
	public PatientDeathDetailVO[] getDeadPatientList(UserVO userVO)
	 {
		PatientDeathDetailVO[] pDeathDetailVOs=null;
			ValueObject[] vo=null;
			String query="";
			Map populateMap= new HashMap();
			ResultSet rs=null;
			String filename=RegistrationConfig.QUERY_FILE_FOR_DAO;
			String queryKey="SELECT.DEAD_PATIENT_LIST.HPMRT_PAT_DEATH_DTL";
			Sequence sq = new Sequence();
			Connection conn=super.getTransactionContext().getConnection();
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
				populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
				populateMap.put(sq.next(), userVO.getHospitalCode());
			}
			catch (Exception e)
			{
				throw new HisApplicationExecutionException("PatientDeathDetailDAO.populateMAP::" + e);
			}
			try
			{
				rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
				
				if (!rs.next())
				{
					throw new HisRecordNotFoundException("Patient Death Detail is not added");
				}
				else
				{
					rs.beforeFirst();
					vo = HelperMethods.populateVOfrmRS(PatientDeathDetailVO.class, rs);
					System.out.println("length" + vo.length);
					pDeathDetailVOs = new PatientDeathDetailVO[vo.length];
					System.out.println("pDeathDetailVOs.length:: " + pDeathDetailVOs.length);
					for (int i = 0; i < vo.length; i++) {
						System.out.println("before casting");
						pDeathDetailVOs[i] = (PatientDeathDetailVO) vo[i];
					}
				}
			}
			catch(Exception e)
			{
				if(e.getClass()==HisRecordNotFoundException.class)
				{
					throw new HisRecordNotFoundException(e.getMessage());	
				}
				else			 		
				 throw new HisDataAccessException("HisDataAccessException:: "+e);			 
			 }
			return pDeathDetailVOs;
		} 
	
}
