/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: 
	 ## Module Name					: MRD
	 ## Process/Database Object 	:Medical and fitness Certificate issue after Request
	 ## Purpose						:Medical and fitness Certificate Issue Process
	 ## Date of Creation			: 
	 ## Modification Log			:				
     ## Modify Date				    :05-Dec-2014
     ##	Reason	(CR/PRS)			: To fetch billNo and Quantity for Medical and fitness certificate issue functionality 
 	 
 	 ## Modify By				    :Amit Garg 	
 	 ## Modify Date				    :05-DEC-2014
     ##	Reason	(CR/PRS)			: To fetch billNo and Quantity for Medical and fitness certificate issue functionality 
 	
 	 */


package mrd.transaction.dao;

import hisglobal.backutil.HisDAO;
import hisglobal.backutil.HisException;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.EpisodeVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import hisglobal.vo.PatMedicalDtlVO;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.HashMap;
import java.util.Map;

import javax.sql.rowset.WebRowSet;


import mrd.MrdConfig;
import mrd.vo.EstimateCertificateIssueVO;
import mrd.vo.PackageServiceMstVO;
import opd.OpdConfig;
import registration.RegistrationConfig;

public class MedicalCertificateDAO extends DataAccessObject implements MedicalCertificateDAOi
{
	public  MedicalCertificateDAO(TransactionContext _tx) 
	{
		super(_tx);
	}
	
	//  Getting The List of All The Episodes of the Patient
	public EpisodeVO[] getAllEpisodeOfThePatient(String crNo,UserVO userVO)
	{
		EpisodeVO[] arrEpisodeVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_EPISODE_MC.HRGT_EPISODE_DTL";

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
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), OpdConfig.SLNO);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			arrEpisodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeVO[i] = (EpisodeVO) vo[i];
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

		return arrEpisodeVO;
	}
	
	//  Saving the Medical Certificate Details 
	public void create(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		String query  = "";
        Map populateMAP =new HashMap();
        Sequence sq=new Sequence();
        String filename=MrdConfig.QUERY_FILE_FOR_MRD_DAO;
        String queryKey="INSERT.HRGT_PAT_MEDICAL_DTL";
        
       
        try
        {
        	query =HelperMethodsDAO.getQuery(filename,queryKey);
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
        }
        
        try
        {
        	populateMAP.put(sq.next(), patMedicalDtlVO.getPatCrNo());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getMedicalCertificateId());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getEpisodeCode());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getEpisodeVisitNo());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getMedicalCertificateDesc());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getMedicalCertificateId());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getEmpNo());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getPatAdmNo());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getSufferingFrom());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getAdviceDays());
        	populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
        	populateMAP.put(sq.next(), patMedicalDtlVO.getFromDate());
        	populateMAP.put(sq.next(), userVO.getSeatId());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getToDate());
        	populateMAP.put(sq.next(), userVO.getHospitalCode());
        	populateMAP.put(sq.next(), patMedicalDtlVO.getRemarks());
        	      	
        }
        catch(Exception e)
        {
        	throw new HisApplicationExecutionException("MedicalCertificateDAO.populateMAP::"+e);
        }
        try
        {
        	
        	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	throw new HisDataAccessException("HisDataAccessException While ADDING");
        }
	}
	
	// Checking for Already Generated Medical Certificate
	public PatMedicalDtlVO[] checkExistingRecord(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO)
	{
		PatMedicalDtlVO[] arrPatMedVO = null;
		ValueObject vo[] = null;
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="CHECK_EXISTING_RECORD.HRGT_PAT_MEDICAL_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),patMedicalDtlVO.getFromDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getToDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getFromDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getToDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getPatCrNo());
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
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
			arrPatMedVO = new PatMedicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatMedVO[i] = (PatMedicalDtlVO) vo[i];
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

		return arrPatMedVO;
	}
	
	// Getting the List of Medical Certificate Generated for the Patient On a particular Episode
	public PatMedicalDtlVO[] getIssuedMedicalCertificate(String crNo,String epiCode,UserVO userVO)
	{
		PatMedicalDtlVO[] arrIssueMCVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ISSUED_MC.HRGT_PAT_MEDICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.PROCESS_TYPE_MEDICAL_CERTIFICATE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), epiCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
			arrIssueMCVO = new PatMedicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrIssueMCVO[i] = (PatMedicalDtlVO) vo[i];
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

		return arrIssueMCVO;
	}
	
	public String generateCertificateId(PatMedicalDtlVO patMedicalDtlVO,String unitCode,String genMode,UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="GENERATE_CERTIFICATE_ID";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		//Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), userVO.getHospitalCode());
		populateMap.put(sq.next(), MrdConfig.RECORD_TYPE_MEDICAL);
		populateMap.put(sq.next(), unitCode);
		populateMap.put(sq.next(), genMode);
		populateMap.put(sq.next(), patMedicalDtlVO.getMedicalCertificateDesc());
		
		
		
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
	
	public void update(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "UPDATE_MC.HRGT_PAT_MEDICAL_DTL";
		//Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		
		
		 try
	     {
	       	query =HelperMethodsDAO.getQuery(filename,queryKey);
	     }
	     catch(Exception e)
	     {
	       	throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"+e);
	     }
	     
	     try
	     {
	    	 populateMAP.put(sq.next(), userVO.getSeatId());
	    	 populateMAP.put(sq.next(), patMedicalDtlVO.getMedicalCertificateId());
	    	 populateMAP.put(sq.next(), patMedicalDtlVO.getSNo());
	    	 populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
	    	 populateMAP.put(sq.next(), userVO.getHospitalCode());
	    	 
	     }
	    catch(Exception e)
	    {
	       	throw new HisApplicationExecutionException("MedicalCertificateDAO.populateMAP::"+e);
	    }
	    try
	    {
	        	
	      	HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(),query,populateMAP);
	    }
	    catch(Exception e)
	    {
	       	e.printStackTrace();
	       	throw new HisDataAccessException("HisDataAccessException While UPDATING");
	    }
	}
	
	// Checking for Already Existing Record
	public PatMedicalDtlVO[] checkExistingRecordForExtend(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO)
	{
		PatMedicalDtlVO[] arrPatMedVO = null;
		ValueObject vo[] = null;
		String query="";
		Map populateMap= new HashMap();
		String filename= MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey="CHECK_EXISTING_RECORD_FOR_MODIFY.HRGT_PAT_MEDICAL_DTL";
		ResultSet rs;
		Sequence sq= new Sequence();
		
		
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),RegistrationConfig.EPISODE_VISIT_TYPE_IPD);
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),RegistrationConfig.EPISODE_VISIT_TYPE_IPD);
		populateMap.put(sq.next(),patMedicalDtlVO.getFromDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getToDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getFromDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getToDate());
		populateMap.put(sq.next(),patMedicalDtlVO.getPatCrNo());
		populateMap.put(sq.next(),Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(),userVO.getHospitalCode());
		populateMap.put(sq.next(),patMedicalDtlVO.getMedicalCertificateId());
		
		
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
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMap);
			/*if (!rs.next())
			{
				throw new HisRecordNotFoundException("No record found");
			}*/
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
			arrPatMedVO = new PatMedicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatMedVO[i] = (PatMedicalDtlVO) vo[i];
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

		return arrPatMedVO;
	}
	
	public EpisodeVO[] getAllEpisodeOfThePatientTodayVisited(String crNo,UserVO userVO)
	{
		EpisodeVO[] arrEpisodeVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT_ALL_EPISODE_MC_TODAY.HRGT_EPISODE_DTL";

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
		populateMAP.put(sq.next(), crNo);
		//populateMAP.put(sq.next(), OpdConfig.SLNO);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Patient Did Not Visited Today");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(EpisodeVO.class, rs);
			arrEpisodeVO = new EpisodeVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrEpisodeVO[i] = (EpisodeVO) vo[i];
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

		return arrEpisodeVO;
	}
	
	
	public PatMedicalDtlVO[] getMedicalCertificatePatReqList(String pmode ,  UserVO strUserVO) 
	{
		PatMedicalDtlVO[] PatMedicalDtlVOs = null;
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.proc_hpmrt_pat_medical_dtl(?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("MRD","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);		
		daoObj.setProcInValue(nProcIndex, "hcode", strUserVO.getHospitalCode(),2);
		daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,3);
		daoObj.setProcInValue(nProcIndex, "episodecode", " ",4);
		daoObj.setProcInValue(nProcIndex, "visitno", "0",5);
		daoObj.setProcInValue(nProcIndex, "crno", "0",6);
		daoObj.setProcOutValue(nProcIndex, "err", 1,7);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,8);
		daoObj.executeProcedureByPosition(nProcIndex);
		strErr = daoObj.getString(nProcIndex, "err");
		rs = daoObj.getWebRowSet(nProcIndex, "resultset");
		
		strErr = daoObj.getString(nProcIndex, "err");
		System.out.println("strErr----------------------->"+strErr);
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Detail Found");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
				PatMedicalDtlVOs = new PatMedicalDtlVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					PatMedicalDtlVOs[i] = (PatMedicalDtlVO) valueObjects[i];
				}
			}
			
			
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("PatientDAO:retrieveByCrNo:HelperMethods :: " + e);
		}
		return PatMedicalDtlVOs;
	}
	
	public String getBillNoQtyDtl(PatMedicalDtlVO patMedicalDtlVO,UserVO userVO) 
	{
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String billNoQty;		
		try
		{
			Procedure strProc=new Procedure(MrdConfig.MEDICAL_CERTIFICATE_ONLINE_BILL_DTL);
			strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(2,Types.VARCHAR,patMedicalDtlVO.getRecordType());
			strProc.addInParameter(3,Types.VARCHAR,"1");
		    strProc.addInParameter(4,Types.VARCHAR,"8");
		    strProc.addInParameter(5,Types.VARCHAR,patMedicalDtlVO.getCertificateId()); 
			strProc.addInParameter(6,Types.VARCHAR,"0");
			strProc.setReturnType(Types.VARCHAR);
			return	billNoQty = (String)strProc.execute(super.getTransactionContext().getConnection());
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
	}
	
	public void saveMedicalCertificateIssueDtl(String pmode,PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		System.out.println("MedicalCertificateDAO.saveMedicalCertificateIssueDtl()");
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_MEDICAL_CERTIFICATE_REQUEST);
					
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, patMedicalDtlVO.getCertificateId());
					strProc.addInParameter(3, Types.VARCHAR, patMedicalDtlVO.getPatCrNo());
					strProc.addInParameter(4, Types.VARCHAR, patMedicalDtlVO.getEpisodeCode());
					strProc.addInParameter(5, Types.VARCHAR, patMedicalDtlVO.getEpisodeVisitNo());
					strProc.addInParameter(6, Types.VARCHAR, "0" );
					//strProc.addInParameter(7, Types.VARCHAR, (patMedicalDtlVO.getAdmissionNo()==null) ? "0" : patMedicalDtlVO.getAdmissionNo() );
					strProc.addInParameter(7, Types.VARCHAR, MrdConfig.ESTIMATE_CERTIFICATE_REQUEST_STATUS);
					strProc.addInParameter(8, Types.VARCHAR, patMedicalDtlVO.getAdvisedBy()); //Emp No
					strProc.addInParameter(9, Types.VARCHAR, patMedicalDtlVO.getDiagnosis()); //Recheck
					strProc.addInParameter(10, Types.VARCHAR, patMedicalDtlVO.getSurgery());
					strProc.addInParameter(11, Types.VARCHAR, patMedicalDtlVO.getAdviceDays());
					strProc.addInParameter(12, Types.VARCHAR, patMedicalDtlVO.getFromDate());
					strProc.addInParameter(13, Types.VARCHAR, patMedicalDtlVO.getToDate());
					strProc.addInParameter(14, Types.VARCHAR, patMedicalDtlVO.getFitnessDate());
					strProc.addInParameter(15, Types.VARCHAR, patMedicalDtlVO.getRemarks());
					strProc.addInParameter(16, Types.VARCHAR, patMedicalDtlVO.getWardCode());
					strProc.addInParameter(17, Types.VARCHAR, patMedicalDtlVO.getStrPatName() );
					strProc.addInParameter(18, Types.VARCHAR, MrdConfig.RECORD_TYPE_MEDICAL );
					strProc.addInParameter(19, Types.VARCHAR, "");
					strProc.addInParameter(20, Types.VARCHAR, ""); //pat_treat_cat
					strProc.addInParameter(21, Types.VARCHAR, Config.IS_VALID_ACTIVE );
					strProc.addInParameter(22, Types.VARCHAR, userVO.getHospitalCode() );
					strProc.addInParameter(23, Types.VARCHAR, userVO.getSeatId());
					strProc.addInParameter(24, Types.VARCHAR, patMedicalDtlVO.getQuantity());
					strProc.addInParameter(25, Types.VARCHAR, patMedicalDtlVO.getBillNo());
					strProc.addInParameter(26, Types.VARCHAR, patMedicalDtlVO.getGivenBy());
					strProc.addInParameter(27, Types.VARCHAR, patMedicalDtlVO.getRelationCode());
					strProc.addInParameter(28, Types.VARCHAR, patMedicalDtlVO.getRelativeName() );
					strProc.addInParameter(29, Types.VARCHAR, patMedicalDtlVO.getRelativeAddr());
					strProc.addInParameter(30, Types.VARCHAR, patMedicalDtlVO.getRelativeContactNo());
					strProc.addInParameter(31, Types.VARCHAR, patMedicalDtlVO.getRecordType());
					strProc.addInParameter(32, Types.VARCHAR, patMedicalDtlVO.getPatDesignation());
					strProc.addInParameter(33, Types.VARCHAR, patMedicalDtlVO.getPatOrganisation());
					strProc.addOutParameter(34, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
	public void saveFitnessCertificateIssueDtl(String pmode,PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_FITNESS_CERTIFICATE_REQUEST);
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, patMedicalDtlVO.getCertificateId());
					strProc.addInParameter(3, Types.VARCHAR, patMedicalDtlVO.getPatCrNo());
					strProc.addInParameter(4, Types.VARCHAR, "");
					strProc.addInParameter(5, Types.VARCHAR, "");
					strProc.addInParameter(6, Types.VARCHAR, "");
					strProc.addInParameter(7, Types.VARCHAR, recordStatus);
					strProc.addInParameter(8, Types.VARCHAR, "");
					strProc.addInParameter(9, Types.VARCHAR, "");
					strProc.addInParameter(10, Types.VARCHAR, "");
					strProc.addInParameter(11, Types.VARCHAR, "");
					strProc.addInParameter(12, Types.VARCHAR, "");
					strProc.addInParameter(13, Types.VARCHAR, "");
					strProc.addInParameter(14, Types.VARCHAR, patMedicalDtlVO.getRecordType());
					strProc.addInParameter(15, Types.VARCHAR, "");
					strProc.addInParameter(16, Types.VARCHAR, "");
					strProc.addInParameter(17, Types.VARCHAR,Config.IS_VALID_ACTIVE);
					strProc.addInParameter(18, Types.VARCHAR, userVO.getHospitalCode());
					strProc.addInParameter(19, Types.VARCHAR, userVO.getSeatId());					
					strProc.addInParameter(20, Types.VARCHAR, patMedicalDtlVO.getQuantity());
					strProc.addInParameter(21, Types.VARCHAR, patMedicalDtlVO.getBillNo());
					strProc.addInParameter(22, Types.VARCHAR, patMedicalDtlVO.getGivenBy());
					strProc.addInParameter(23, Types.VARCHAR, patMedicalDtlVO.getRelationCode());
					strProc.addInParameter(24, Types.VARCHAR, patMedicalDtlVO.getRelativeName() );
					strProc.addInParameter(25, Types.VARCHAR, patMedicalDtlVO.getRelativeAddr());
					strProc.addInParameter(26, Types.VARCHAR, patMedicalDtlVO.getRelativeContactNo());
					strProc.addInParameter(27, Types.VARCHAR, patMedicalDtlVO.getRecordType());
					strProc.addOutParameter(28, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}	
	

}
