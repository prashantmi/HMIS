/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: Amit Garg 	
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate issue after Request
	 ## Purpose						:Certificate Issue Process
	 ## Date of Creation			: 23-Nov-2014
	
 	 */


package mrd.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.rowset.WebRowSet;

import mrd.MrdConfig;
import mrd.vo.EstimateCertificateIssueVO;
import mrd.vo.PackageServiceMstVO;
import hisglobal.backutil.HisDAO;
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
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

public class EstimateCertificateIssueDAO extends DataAccessObject{

	public EstimateCertificateIssueDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List getEstimateCertificateReqDtl(String pmode,  UserVO strUserVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_CERTIFICATE_REQUEST_DTL);
			strProc.addInParameter(1, Types.VARCHAR,pmode);
			strProc.addInParameter(2, Types.VARCHAR, strUserVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(4, Types.VARCHAR);
			strProc.addOutParameter(5, Types.REF);			
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(4);
			rs = (ResultSet) strProc.getParameterAt(5);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		List reqStatusRecord = new ArrayList();

		try
		{
			reqStatusRecord = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);
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
		return reqStatusRecord;
	}
	
	
	public List getEstimateCertificateIssueDtl(String pmode,EstimateCertificateIssueVO estimateCertificateIssueVO ,  UserVO strUserVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_CERTIFICATE_ISSUE_PAT_DTL);
			strProc.addInParameter(1, Types.VARCHAR,pmode);
			strProc.addInParameter(2, Types.VARCHAR,estimateCertificateIssueVO.getPatCrNo());
			strProc.addInParameter(3, Types.VARCHAR,estimateCertificateIssueVO.getEpisodeCode());
			strProc.addInParameter(4, Types.VARCHAR,estimateCertificateIssueVO.getEpisodeVisitNo());
			strProc.addInParameter(5, Types.VARCHAR, strUserVO.getHospitalCode());
			strProc.addInParameter(6, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.REF);			
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(7);
			rs = (ResultSet) strProc.getParameterAt(8);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		List ipdPatDtllst = new ArrayList();

		try
		{
			ipdPatDtllst = HelperMethodsDAO.getAllOfResultSetAsListColumnWise(rs);
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
		return ipdPatDtllst;
	}
	public EstimateCertificateIssueVO[] getEstimateCertificateIssuePatDtl(String pmode,EstimateCertificateIssueVO estimateCertificateIssueVO ,  UserVO strUserVO) 
	{
		EstimateCertificateIssueVO[] PatientDtlVOs = null;
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);
		daoObj.setProcInValue(nProcIndex, "crno", estimateCertificateIssueVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "episodecode", estimateCertificateIssueVO.getEpisodeCode(),3);
		daoObj.setProcInValue(nProcIndex, "visitno",estimateCertificateIssueVO.getEpisodeVisitNo(),4);
		daoObj.setProcInValue(nProcIndex, "hcode", strUserVO.getHospitalCode(),5);
		daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,6);
		daoObj.setProcInValue(nProcIndex, "tarrifid", "",7);
		daoObj.setProcInValue(nProcIndex, "certificateid", estimateCertificateIssueVO.getCertificateId(),8);
		daoObj.setProcOutValue(nProcIndex, "err", 1,9);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
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
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(EstimateCertificateIssueVO.class, rs);
				PatientDtlVOs = new EstimateCertificateIssueVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					PatientDtlVOs[i] = (EstimateCertificateIssueVO) valueObjects[i];
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
		return PatientDtlVOs;
	}

	
	public EstimateCertificateIssueVO[] getTariffsListForEstimateCert(String pmode,EstimateCertificateIssueVO estimateCertificateIssueVO ,  UserVO strUserVO) 
	{
		EstimateCertificateIssueVO[] PatientDtlVOs = null;
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.proc_hrgt_episode_dtl(?,?,?,?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("Registration","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);
		daoObj.setProcInValue(nProcIndex, "crno", estimateCertificateIssueVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "episodecode", estimateCertificateIssueVO.getEpisodeCode(),3);
		daoObj.setProcInValue(nProcIndex, "visitno",estimateCertificateIssueVO.getEpisodeVisitNo(),4);
		daoObj.setProcInValue(nProcIndex, "hcode", strUserVO.getHospitalCode(),5);
		daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,6);
		daoObj.setProcInValue(nProcIndex, "tarrifid", "",7);
		daoObj.setProcInValue(nProcIndex, "certificateid", estimateCertificateIssueVO.getCertificateId(),8);
		daoObj.setProcOutValue(nProcIndex, "err", 1,9);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,10);
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
				throw new HisRecordNotFoundException("No Patient Record");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(EstimateCertificateIssueVO.class, rs);
				PatientDtlVOs = new EstimateCertificateIssueVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					PatientDtlVOs[i] = (EstimateCertificateIssueVO) valueObjects[i];
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
		return PatientDtlVOs;
	}

	
	
	
	
	
	
	
	public void saveEstimateCertificateIssueDtl(String pmode,String recordType,String recordStatus,EstimateCertificateIssueVO estimateCertificateIssueVO, UserVO userVO)
	{
		String errorMsg = null;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_ESTIMATE_CERTIFICATE_ISSUE_DTL_UPDATE);
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, estimateCertificateIssueVO.getGivenBy());
					strProc.addInParameter(3, Types.VARCHAR, estimateCertificateIssueVO.getRelationCode());
					strProc.addInParameter(4, Types.VARCHAR, estimateCertificateIssueVO.getRelativeName() );
					strProc.addInParameter(5, Types.VARCHAR, estimateCertificateIssueVO.getRelativeAddr());
					strProc.addInParameter(6, Types.VARCHAR, estimateCertificateIssueVO.getRelativeContactNo());
					strProc.addInParameter(7, Types.VARCHAR, estimateCertificateIssueVO.getCertificateId());
					strProc.addInParameter(8, Types.VARCHAR, userVO.getHospitalCode());
					strProc.addInParameter(9, Types.VARCHAR, userVO.getSeatId());
					strProc.addInParameter(10, Types.VARCHAR, estimateCertificateIssueVO.getBillNo());
					strProc.addInParameter(11, Types.VARCHAR, estimateCertificateIssueVO.getDeptUnitCode());
					strProc.addInParameter(12, Types.VARCHAR, estimateCertificateIssueVO.getWardCode());
					strProc.addInParameter(13, Types.VARCHAR, recordType);
					strProc.addInParameter(14, Types.VARCHAR, recordStatus);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
	
	public PackageServiceMstVO[] generateEstimateCertificate(String pmode,EstimateCertificateIssueVO estimateCertificateIssueVO ,  UserVO strUserVO) 
	{
		PackageServiceMstVO[] PatientServiceMstVOs = null;
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.HBLT_PACKAGE_SERVICE_MST(?,?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("MRD","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);	
		daoObj.setProcInValue(nProcIndex, "certificateId", estimateCertificateIssueVO.getCertificateId(),2);
		daoObj.setProcInValue(nProcIndex, "hcode", strUserVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "isvalid", Config.IS_VALID_ACTIVE,4);
		daoObj.setProcInValue(nProcIndex, "tarrifid", "",5);
		daoObj.setProcOutValue(nProcIndex, "err", 1,6);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,7);
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
				valueObjects = HelperMethods.populateVOfrmRS(PackageServiceMstVO.class, rs);
				PatientServiceMstVOs = new PackageServiceMstVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					PatientServiceMstVOs[i] = (PackageServiceMstVO) valueObjects[i];
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
		return PatientServiceMstVOs;
	}


	public EstimateCertificateIssueVO[] getDiagnosisList(String pmode,
			EstimateCertificateIssueVO estimateCertificateIssueVO, UserVO userVO) {
		// TODO Auto-generated method stub
		EstimateCertificateIssueVO[] diagnosisListVo = null;
		//String queryKey = "SELECT.HRGT_DAILY_PATIENT_DTL.RETRIEVE_BY_CRNO";
		ValueObject[] valueObjects = null;
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call pkg_mrd_view.proc_hrgt_episode_diagnosis_dtl(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr = "";
		try 
		{
		daoObj = new HisDAO("MRD","EssentialDAO");
		nProcIndex = daoObj.setProcedure(strProcName);
		
		daoObj.setProcInValue(nProcIndex, "pmode", pmode,1);	
		//daoObj.setProcInValue(nProcIndex, "certificateId", estimateCertificateIssueVO.getCertificateId(),2);
		daoObj.setProcInValue(nProcIndex, "patcr", estimateCertificateIssueVO.getPatCrNo(),2);
		daoObj.setProcInValue(nProcIndex, "hcode", userVO.getHospitalCode(),3);
		daoObj.setProcInValue(nProcIndex, "episodecode", estimateCertificateIssueVO.getEpisodeCode(),4);
		daoObj.setProcOutValue(nProcIndex, "err", 1,5);
		daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
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
				//throw new HisRecordNotFoundException("No Detail Found");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(EstimateCertificateIssueVO.class, rs);
				diagnosisListVo = new EstimateCertificateIssueVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					diagnosisListVo[i] = (EstimateCertificateIssueVO) valueObjects[i];
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
		return diagnosisListVo;
	}
	
	
	
	
	

	
}
