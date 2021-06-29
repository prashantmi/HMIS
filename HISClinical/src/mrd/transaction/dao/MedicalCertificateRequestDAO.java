/**
## Copyright Information		: 	C-DAC, Noida  
## Project Name					: 	HIS-NIMS
## Name of Developer		 	: 	Akash Singh
## Module Name					: 	MRD
## Process/Database Object Name	:	Medical Certificate Request
## Purpose						:	online request raise from OPD Doctor Desk or OPD Bay Desk or IPD Doctor Desk. Doctor provide request slip to patient with complete medical certificate information like rest dates, fitness dates etc.
## Date of Creation				: 	19-November-2014
## Modification Log				:					
##		Modify Date				: 	
##		Reason	(CR/PRS)		: 
##		Modify By				: 
*/
package mrd.transaction.dao;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import mrd.MrdConfig;


public class MedicalCertificateRequestDAO extends DataAccessObject
{

	public MedicalCertificateRequestDAO(TransactionContext _transactionContext)
	{
		super(_transactionContext);
	}

	public String getDiagnosisDtl(HisDAO hisDAO_p, String p_mode,PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String patientCountObj;
		System.out.println("seat id of USER :"+userVO.getSeatId()+"  user id :"+userVO.getUserId());		
		try
		{
			Procedure strProc=new Procedure(MrdConfig.PROC_GET_EPISODE_DIAGNOSIS_DTL);
			strProc.addInParameter(1,Types.VARCHAR,p_mode);// Mode
			strProc.addInParameter(2,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(3,Types.VARCHAR,patMedicalDtlVO.getPatCrNo());
		    strProc.addInParameter(4,Types.VARCHAR,patMedicalDtlVO.getEpisodeCode());
			strProc.setReturnType(Types.VARCHAR);
			return	patientCountObj = (String)strProc.execute(super.getTransactionContext().getConnection());
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
		catch (HisException e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
	}

	public List getMedicalCertificateReqAdvisedBy(String departmentUnitCode,UserVO userVO, String consultentType) 
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PEON_LIST);
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR,consultentType);
			strProc.addInParameter(3, Types.VARCHAR, departmentUnitCode);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);//OracleTypes.CURSOR);
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		// log.error(query + "\n");
		/*
		 * log.debug("Execute query"); log.error("Error find"); log.fatal("Fatal Error");
		 */

		List alRecord = new ArrayList();

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjectsCallable(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return alRecord;}

	public List<PatMedicalDtlVO> getPrevMedicalReqDtl1(HisDAO hisDAO, String pmode,	PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PREV_MEDICAL_REQUEST_DTL);
			strProc.addInParameter(1, Types.VARCHAR,pmode);
			strProc.addInParameter(2, Types.VARCHAR, userVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(4, Types.VARCHAR, patMedicalDtlVO.getEpisodeCode());
			strProc.addInParameter(5, Types.VARCHAR, patMedicalDtlVO.getEpisodeVisitNo());
			strProc.addInParameter(6, Types.VARCHAR, patMedicalDtlVO.getPatCrNo());
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.REF);			
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(7);
			rs = (ResultSet) strProc.getParameterAt(8);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		
		finally {
			if (hisDAO != null) {
				hisDAO.free();
				
			}
			hisDAO = null;
		}
		List prevRecord = new ArrayList();
		try
		{
			prevRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);
		}
		catch (Exception e)
		{

			throw new HisDataAccessException("HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		finally
		{
			if (rs != null)
			{
				try
				{
					rs.close();
				}
				catch (SQLException e)
				{
					e.printStackTrace();
				}
			}
		}
		return prevRecord;
	}

	public void saveMedicalCertificateRequest(String pmode, String certificateId, PatMedicalDtlVO patMedicalDtlVO,UserVO userVO) 
	{
		String errorMsg = null;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_MEDICAL_CERTIFICATE_REQUEST);
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, certificateId);
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
					strProc.addInParameter(19, Types.VARCHAR, patMedicalDtlVO.getDepartmentUnitCode().substring(0, 3));
					strProc.addInParameter(20, Types.VARCHAR, patMedicalDtlVO.getStrCategoryCode()); //pat_treat_cat
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

					errorMsg = (String) strProc.getParameterAt(34);

					
                  if(errorMsg!=null)
                  {
                	  System.out.println("got a error in billing...."+errorMsg);
                	  throw new Exception("Billing Error::simple::"+errorMsg);
                  }
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MedicalCertificateRequestDAO.saveMedicalCertificateRequest()::HelperMethods.populateVOfrmRS -> " + e);
		}

	}

	public List<PatMedicalDtlVO> getPrevMedicalReqDtl(HisDAO hisDAO_p, String strMode_p, PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet objResSet;
		try
		{
			nProcedureIndex = hisDAO_p.setProcedure(MrdConfig.PROCEDURE_GET_PREV_MEDICAL_REQUEST_DTL);

			// Setting and Registering In and Out Parameters 
			hisDAO_p.setProcInValue(nProcedureIndex, "pmode", strMode_p,1);
			hisDAO_p.setProcInValue(nProcedureIndex, "hcode", userVO.getHospitalCode(),2);
			hisDAO_p.setProcInValue(nProcedureIndex, "isvalid", Config.IS_VALID_ACTIVE,3);
			hisDAO_p.setProcInValue(nProcedureIndex, "episodecode", patMedicalDtlVO.getEpisodeCode(),4);
			hisDAO_p.setProcInValue(nProcedureIndex, "visitno", patMedicalDtlVO.getEpisodeVisitNo(),5);
			hisDAO_p.setProcInValue(nProcedureIndex, "crno", patMedicalDtlVO.getPatCrNo(),6);
			hisDAO_p.setProcOutValue(nProcedureIndex, "err", 1,7); // varchar
			hisDAO_p.setProcOutValue(nProcedureIndex, "resultset", 2,8); // Cursor

			// Executing Procedure 
			hisDAO_p.executeProcedureByPosition(nProcedureIndex);

			// Getting out parameters 
			strDBErr = hisDAO_p.getString(nProcedureIndex, "err");
			objResSet = hisDAO_p.getWebRowSet(nProcedureIndex, "resultset");

			// If Database Error Occurs, No farther processing is required. 
			if (strDBErr != null && !strDBErr.equals(""))
			{
				throw new Exception("Data Base Error:" + strDBErr);
			}
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MedicalCertificateRequestDAO.getPrevMedicalReqDtl()::hisDAO_p.execute" + MrdConfig.PROCEDURE_GET_PREV_MEDICAL_REQUEST_DTL 
					+ ") -> " + e.getMessage());
		}
		finally {
			if (hisDAO_p != null) {
				hisDAO_p.free();
				
			}
			hisDAO_p = null;
		}
		List<PatMedicalDtlVO> lst = new ArrayList<PatMedicalDtlVO>();
		ValueObject[] arrVOs = {};
		try
		{
			if (objResSet.next())
			{
				objResSet.beforeFirst();
				arrVOs = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, objResSet);
				for (ValueObject obj : arrVOs)
					lst.add((PatMedicalDtlVO) obj);
			}
		}
		catch (Exception e)
		{e.printStackTrace();
			throw new HisDataAccessException("MedicalCertificateRequestDAO.getPrevMedicalReqDtl()::HelperMethods.populateVOfrmRS -> " + e);
		}
		return lst;
	}

	public void saveFitnessCertificateRequest(String pmode,String certificateId, PatMedicalDtlVO patMedicalDtlVO, UserVO userVO) 
	{
		String errorMsg = null;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_FITNESS_CERTIFICATE_REQUEST);
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, certificateId);
					strProc.addInParameter(3, Types.VARCHAR, patMedicalDtlVO.getPatCrNo());
					strProc.addInParameter(4, Types.VARCHAR, patMedicalDtlVO.getEpisodeCode());
					strProc.addInParameter(5, Types.VARCHAR, patMedicalDtlVO.getEpisodeVisitNo());
					strProc.addInParameter(6, Types.VARCHAR, "0" );
					//strProc.addInParameter(7, Types.VARCHAR, (patMedicalDtlVO.getAdmissionNo()==null) ? "0" : patMedicalDtlVO.getAdmissionNo() );
					strProc.addInParameter(7, Types.VARCHAR, MrdConfig.ESTIMATE_CERTIFICATE_REQUEST_STATUS);
					strProc.addInParameter(8, Types.VARCHAR, patMedicalDtlVO.getMedicalCertificateId()); //Emp No
					strProc.addInParameter(9, Types.VARCHAR, patMedicalDtlVO.getAdvisedBy()); //Emp No
					strProc.addInParameter(10, Types.VARCHAR, patMedicalDtlVO.getFitnessDate());
					strProc.addInParameter(11, Types.VARCHAR, patMedicalDtlVO.getRemarks());
					strProc.addInParameter(12, Types.VARCHAR, patMedicalDtlVO.getWardCode());
					strProc.addInParameter(13, Types.VARCHAR, patMedicalDtlVO.getStrPatName() );
					strProc.addInParameter(14, Types.VARCHAR, MrdConfig.RECORD_TYPE_FITNESS );
					strProc.addInParameter(15, Types.VARCHAR, patMedicalDtlVO.getDepartmentUnitCode());
					strProc.addInParameter(16, Types.VARCHAR, patMedicalDtlVO.getStrCategoryCode()); //pat_treat_cat
					strProc.addInParameter(17, Types.VARCHAR, Config.IS_VALID_ACTIVE );
					strProc.addInParameter(18, Types.VARCHAR, userVO.getHospitalCode() );
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

					errorMsg = (String) strProc.getParameterAt(28);


	                  if(errorMsg!=null)
	                  {
	                	  System.out.println("got a error in billing....");
	                	  throw new Exception("Billing Error::simple::"+errorMsg);
	                  }
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MedicalCertificateRequestDAO.saveFitnessCertificateRequest()::HelperMethods.populateVOfrmRS -> " + e);
		}

	}
	
}

