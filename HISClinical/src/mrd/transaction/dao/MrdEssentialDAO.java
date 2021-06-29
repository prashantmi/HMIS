/*Copyright Information			: C-DAC, Noida  
	 ## Project Name				: NIMS
	 ## Name of Developer		 	: 
	 ## Module Name					: MRD
	 ## Process/Database Object Name:Estimate Certificate issue after Request
	 ## Purpose						:Certificate Issue Process
	 ## Date of Creation			: 
	 ## Modification Log			:				
     ## Modify Date				    :23-Nov-2014
     ##	Reason	(CR/PRS)			: CertificateID Generation for Certificate Issue Process functionality
 	 ## Modify By				    :Amit Garg 	
 	 */


package mrd.transaction.dao;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.AddressVO;
import hisglobal.vo.CaseSheetDispatchVO;
import hisglobal.vo.CertificateIssueDtlVO;
import hisglobal.vo.DischargePrintingVO;
import hisglobal.vo.DocReceivingFormDtlVO;
import hisglobal.vo.EpisodeRestAdviceVO;
import hisglobal.vo.InsuranceDetailVO;
import hisglobal.vo.MrdIcdDtlVO;
import hisglobal.vo.MrdRecordDtlVO;
import hisglobal.vo.PatAdmissionDtlVO;
import hisglobal.vo.PatFitnessDtlVO;
import hisglobal.vo.PatMedicalDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientProfileDetailVO;
import hisglobal.vo.PatientVO;
import hisglobal.vo.RackMstVO;
import hisglobal.vo.RackShelfMstVO;
import hisglobal.vo.RecordTypeCheckListMstVO;
import hisglobal.vo.RecordTypeWiseEnclosureMstVO;
import hisglobal.vo.ReportVO;
import hisglobal.vo.StaffDetailVO;
import hisglobal.vo.SummonDetailVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import inpatient.InpatientConfig;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.sql.rowset.WebRowSet;

import mrd.MrdConfig;
import mrd.vo.BulletinGenerationVO;
import mrd.vo.BulletinHeadDataVO;
import mrd.vo.BulletinHeadVO;
import opd.OpdConfig;
//import oracle.jdbc.driver.OracleTypes;
import registration.RegistrationConfig;

public class MrdEssentialDAO extends DataAccessObject implements MrdEssentialDAOi
{
	public MrdEssentialDAO(TransactionContext _tx)
	{
		super(_tx);
	}

	// Getting the List of Diagnosis Given to the Patient on a Particular Episode ICD Code Wise
	public List getPatDiagnosisListICD(String diagCodeType, EpisodeRestAdviceVO epiRestAdviceVO, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_PAT_DIAGNOSIS_ICD.HRGT_EPISODE_DIAGNOSIS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), epiRestAdviceVO.getPatCrNo());
			populateMAP.put(sq.next(), epiRestAdviceVO.getEpisodeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), diagCodeType);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Diagnosis Found"); }
			 */

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of All the Consultant Name
	public List getAllConsultantForMC(String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_UNITWISE_CONSULTANT_MC.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.PROCESS_TYPE_MEDICAL_CERTIFICATE);
			populateMAP.put(sq.next(), unitCode);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Consultant Found"); }
			 */

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of Generated Medical Certificate
	public PatMedicalDtlVO[] getAllGeneratedMCList(String crNo, UserVO userVO)
	{
		PatMedicalDtlVO[] arrPatMedDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_GENERATED_MC.HRGT_PAT_MEDICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No record found"); }
			 */
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
			arrPatMedDtlVO = new PatMedicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatMedDtlVO[i] = (PatMedicalDtlVO) vo[i];
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

		return arrPatMedDtlVO;
	}

	// Getting the List of Generated Fitness Certificate
	public PatFitnessDtlVO[] getAllGeneratedFCList(String crNo, UserVO userVO)
	{
		PatFitnessDtlVO[] arrPatFitDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_GENERATED_FC.HRGT_PAT_FITNESS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No record found"); }
			 */
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatFitnessDtlVO.class, rs);
			arrPatFitDtlVO = new PatFitnessDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatFitDtlVO[i] = (PatFitnessDtlVO) vo[i];
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

		return arrPatFitDtlVO;
	}

	// Getting the List of Duplicate Medical Certificate
	public PatMedicalDtlVO[] getAllDuplicateMCList(String crNo, UserVO userVO)
	{
		PatMedicalDtlVO[] arrPatMedDupDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DUPLICATE_MC.HRGT_PAT_MEDICAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No record found"); }
			 */
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatMedicalDtlVO.class, rs);
			arrPatMedDupDtlVO = new PatMedicalDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatMedDupDtlVO[i] = (PatMedicalDtlVO) vo[i];
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

		return arrPatMedDupDtlVO;
	}

	// Getting the List of Duplicate Medical Certificate
	public PatFitnessDtlVO[] getAllDuplicateFCList(String crNo, UserVO userVO)
	{
		PatFitnessDtlVO[] arrPatFitDupDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DUPLICATE_FC.HRGT_PAT_FITNESS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_IS_DUPLICATE_NO);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No record found"); }
			 */
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(PatFitnessDtlVO.class, rs);
			arrPatFitDupDtlVO = new PatFitnessDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrPatFitDupDtlVO[i] = (PatFitnessDtlVO) vo[i];
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

		return arrPatFitDupDtlVO;
	}

	public List getAllUnit(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Diagnosis Found"); }
			 */

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of All Certificate Issue Dtl
	public CertificateIssueDtlVO[] getAllCertificateIssueDtlVOList(CertificateIssueDtlVO _certificateIssueDtlVO, UserVO userVO)
	{
		CertificateIssueDtlVO[] arrCertificateIssueDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL.HPMRT_CERTIFICATE_ISSUE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_RECORD_STATUS_IN_DEPT_UNIT);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Certificate Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(CertificateIssueDtlVO.class, rs);
			arrCertificateIssueDtlVO = new CertificateIssueDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrCertificateIssueDtlVO[i] = (CertificateIssueDtlVO) vo[i];
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

		return arrCertificateIssueDtlVO;
	}

	// Getting The List of All Rack
	public RackMstVO[] getAllRack(UserVO userVO)
	{
		RackMstVO[] arrRackVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_RACK.HPMRT_RACK_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
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
				throw new HisRecordNotFoundException("No Rack Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RackMstVO.class, rs);
			arrRackVO = new RackMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrRackVO[i] = (RackMstVO) vo[i];
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

		return arrRackVO;
	}

	// Getting The List Of All Shelf
	public RackShelfMstVO[] getAllRackshelf(UserVO userVO)
	{
		RackShelfMstVO[] arrRackShelfVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_SHELF.HPMRT_RACK_SHELF_MST";

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
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shelf Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(RackShelfMstVO.class, rs);
			arrRackShelfVO = new RackShelfMstVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrRackShelfVO[i] = (RackShelfMstVO) vo[i];
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

		return arrRackShelfVO;
	}

	// Getting Patient Info For Medical Certificate
	public PatMedicalDtlVO getPUKForMedicalCert(MrdRecordDtlVO mrdRecordDtlVO, UserVO _userVO)
	{
		PatMedicalDtlVO patMedicalDtlVO = new PatMedicalDtlVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_MEDICAL_PUK.HRGT_PAT_MEDICAL_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(patMedicalDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return patMedicalDtlVO;

	}

	// Getting Patient Info For Fitness Certificate
	public PatFitnessDtlVO getPUKForFitnessCert(MrdRecordDtlVO mrdRecordDtlVO, UserVO _userVO)
	{
		PatFitnessDtlVO patFitnessDtlVO = new PatFitnessDtlVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_FITNESS_PUK.HRGT_PAT_FITNESS_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), mrdRecordDtlVO.getRecordId());
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(patFitnessDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return patFitnessDtlVO;

	}

	// not in use

	// Getting The List of Certificate From Movement Table(In Case of ONLINE)
	public CertificateIssueDtlVO[] getAllMovementCertificateVO(CertificateIssueDtlVO _CertificateIssueDtlVO, UserVO userVO)
	{
		CertificateIssueDtlVO[] arrCertificateDtlVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_MOV_CERTIFICATE.HPMRT_RECORD_DISPATCH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		// populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_RECORD_STATUS_SENT_TO_MRD);
		// populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_RECORD_STATUS_SENT_TO_MRD);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		// populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_MOVEMENT_RECORD_STATUS_SEND);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_TYPE_MEDICAL_CERTIFICATE);
		populateMAP.put(sq.next(), MrdConfig.CERTIFICATE_TYPE_FITNESS_CERTIFICATE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Certificate Found");
			}
			rs.beforeFirst();

			vo = HelperMethods.populateVOfrmRS(CertificateIssueDtlVO.class, rs);
			arrCertificateDtlVO = new CertificateIssueDtlVO[vo.length];
			for (int i = 0; i < vo.length; i++)
			{
				arrCertificateDtlVO[i] = (CertificateIssueDtlVO) vo[i];
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

		return arrCertificateDtlVO;
	}

	public void updateCertificateStatus(PatMedicalDtlVO patMedicalDtlVO, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE_MC.HRGT_EPISODE_RESTADVICE_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), MrdConfig.REST_ADVICE_CERTIFICATE_STATUS_GENERATED);
			populateMAP.put(sq.next(), patMedicalDtlVO.getPatCrNo());
			populateMAP.put(sq.next(), patMedicalDtlVO.getEpisodeCode());
			populateMAP.put(sq.next(), patMedicalDtlVO.getEpisodeVisitNo());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MedicalCertificateDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}

	public String getEmpMaxDaysOnline(UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_MAX_DAYS_EMPLOYEE_ONLINE.HGBT_DOCTOR_DESIG_MAPPING";
		ResultSet rs;
		Sequence sq = new Sequence();

		populateMAP.put(sq.next(), userVO.getUserEmpID());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.PROCESS_TYPE_MEDICAL_CERTIFICATE);

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No File Name Exists ... ");
			rs.first();
			return rs.getString(1);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	public List getDeptUnitList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_UNITS_BY_SEATID";
		String queryKey = "SELECT.DEPT_UNIT_FOR_CASESHEET.HGBT_UNIT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getAllDeptHavingUnits(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GET_CLINICALDEPT_HAVINGUNITS";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), RegistrationConfig.DEPT_TYPE_CLINICAL_VALUE);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of Diagnosis Given to the Patient on a Particular Episode Hospital Code Wise
	public List getPatDiagnosisListHospital(String diagCodeType, EpisodeRestAdviceVO epiRestAdviceVO, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_PAT_DIAGNOSIS_HOSPITAL.HRGT_EPISODE_DIAGNOSIS_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), epiRestAdviceVO.getPatCrNo());
			populateMAP.put(sq.next(), epiRestAdviceVO.getEpisodeCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), diagCodeType);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Diagnosis Found"); }
			 */

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// Getting the List of ward on the basis of unitCode
	public List getWardOnBasisOfUnitCode(String unitCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "WARD_LIST.HIPT_DUWRBED_MST.RETRIEVE_BY_DEPTUNITCODE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Ward Alloted For This Unit");
			}

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
			throw new HisDataAccessException("InPatientEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getPhysicianType(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_PHYSICIAN_TYPE.HPMRT_FLAG_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		/*
		 * Sequence sq = new Sequence(); try { populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		 *  } catch (Exception e) { throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e); }
		 */

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Diagnosis Found"); }
			 */

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public PatientVO getPatDtlByCrNo(String crNo, UserVO userVO)
	{
		PatientVO patDtlVO = new PatientVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_NOT_USED_CRNO_DETAIL.HRGT_EPISODE_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), "1");

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(patDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return patDtlVO;
	}

	public void updatePatientValidStatus(String validStr, String crNo, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE_IS_VALID_STATUS.HRGT_PATIENT_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), validStr);
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}

	public void updatePatientAllEpisodeValidStatus(String validStr, String crNo, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE_IS_VALID_STATUS.HRGT_EPISODE_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), validStr);
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}

	public int countSearchPatient(HashMap _searchMap, UserVO _userVO)
	{
		Map _populateMap = new HashMap();
		String query = "";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.COUNT.HRGT_PATIENT_DTL.SEARCH.PATIENT";
		String whereCOndition = "";
		String finalQuery = "";
		ResultSet rs = null;
		String orderBy = " ORDER BY a.hrgstr_fname, a.hrgstr_mname, a.hrgstr_lname";
		Sequence sq = new Sequence();

		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
	//	_populateMap.put(sq.next(), "0");//cr no is not merged
		_populateMap.put(sq.next(), "1");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		// Preparing query to fetch patient records using patient details

		Set keySet = (Set) _searchMap.keySet();

		Iterator keySetItr = keySet.iterator();

		while (keySetItr.hasNext())
		{
			String mapKey = (String) keySetItr.next();
			String mapValue = (String) _searchMap.get(mapKey);

			if (mapKey.equals("gnum_city_loc_code")) whereCOndition = whereCOndition + " AND " + " (b." + mapKey + ") = ('" + mapValue + "')";
			else whereCOndition = whereCOndition + " AND " + "( UPPER (a." + mapKey + ") LIKE UPPER ('" + mapValue + "%'))";

		}
		 //finalQuery = query + whereCOndition + orderBy;
		finalQuery = query + whereCOndition;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery, _populateMap);
		}
		catch (Exception e)
		{

			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		int count=0;
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else
			{
				rs.first();
				String strCount = rs.getString(1);
				count = Integer.parseInt(strCount);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:countSearchPatient() Data access exception " + e);
		}

		return count;
	}
	
	public PatientVO[] searchPatient(HashMap _searchMap, UserVO _userVO)
	{
		PatientVO[] patientVOs = null;
		AddressVO[] addressVOs = null;
		ValueObject[] valueObjects = null;
		Map _populateMap = new HashMap();
		// PatientVO[] _patVO=null;
		String query = "";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.HRGT_PATIENT_DTL.SEARCH.PATIENT";
		String whereCOndition = "";
		String finalQuery = "";
		ResultSet rs = null;
		String orderBy = " ORDER BY a.hrgstr_fname, a.hrgstr_mname, a.hrgstr_lname";
		Sequence sq = new Sequence();

		//_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		//_populateMap.put(sq.next(), "0");//cr no is not merged		
		_populateMap.put(sq.next(), "1");
		_populateMap.put(sq.next(), _userVO.getHospitalCode());
		_populateMap.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		// ////////////Preparing query to fetch patient records using patient details/////////

		Set keySet = (Set) _searchMap.keySet();

		Iterator keySetItr = keySet.iterator();

		while (keySetItr.hasNext())
		{
			String mapKey = (String) keySetItr.next();
			String mapValue = (String) _searchMap.get(mapKey);

			if (mapKey.equals("gnum_city_loc_code")) whereCOndition = whereCOndition + " AND " + " (b." + mapKey + ") = ('" + mapValue + "')";
			else whereCOndition = whereCOndition + " AND " + "( UPPER (a." + mapKey + ") LIKE UPPER ('" + mapValue + "%'))";

		}
		finalQuery = query + whereCOndition + orderBy;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), finalQuery, _populateMap);
		}
		catch (Exception e)
		{

			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("NO Patient found matching the search criteria");
			}
			else
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(AddressVO.class, rs);
				addressVOs = new AddressVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					addressVOs[i] = (AddressVO) valueObjects[i];

				}
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				patientVOs = new PatientVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					patientVOs[i] = (PatientVO) valueObjects[i];
					patientVOs[i].setPatAddress(addressVOs[i]);
				}

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("PatientDAO:searchPatient() Data access exception " + e);
		}

		return patientVOs;
	}

	public void updatePatientMergeStatus(String validStr, String crNo, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE_IS_MERGED_STATUS.HRGT_PATIENT_DTL";
		Sequence sq = new Sequence();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), validStr);
			populateMAP.put(sq.next(), crNo);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}
		try
		{

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HisDataAccessException While UPDATING");
		}
	}
	
	/* ***********************************Case Sheet Acceptance**************************************** */

	public List<CaseSheetDispatchVO> getAllDispatchedCaseSheet(UserVO userVO)
	{
		List<CaseSheetDispatchVO> caseSheetDispatchVOList = new ArrayList<CaseSheetDispatchVO>();
		CaseSheetDispatchVO caseSheetDispatchVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_ALL_DISPATCHED_CASE_SHEET.HPMRT_RECORD_DISPATCH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCH_RECORD_STATUS_SEND);
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), MrdConfig.CASE_SHEET_DISPATCHED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Case Sheet Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				caseSheetDispatchVO = new CaseSheetDispatchVO();
				HelperMethods.populateVOfrmRS(caseSheetDispatchVO, rs);
				caseSheetDispatchVOList.add(caseSheetDispatchVO);
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

		return caseSheetDispatchVOList;
	}

	/**
	 * get List of enclosure send with case sheet to mrd
	 */
	public List<RecordTypeWiseEnclosureMstVO> getEnclosureByRecordId(String recordId, UserVO userVO)
	{
		List<RecordTypeWiseEnclosureMstVO> enclosureVOList = new ArrayList<RecordTypeWiseEnclosureMstVO>();
		RecordTypeWiseEnclosureMstVO enclosureVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.BY_RECORDID_HPMRT_RECORD_ENCLOSURE_SUMMARY";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), recordId);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Enclosure Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				enclosureVO = new RecordTypeWiseEnclosureMstVO();
				HelperMethods.populateVOfrmRS(enclosureVO, rs);
				enclosureVOList.add(enclosureVO);
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

		return enclosureVOList;
	}

	/**
	 * get List of Checklist compulsory at mrd level
	 */
	public List<RecordTypeCheckListMstVO> getChecklistByRecordId(String recordId, UserVO userVO)
	{
		List<RecordTypeCheckListMstVO> checklistVOList = new ArrayList<RecordTypeCheckListMstVO>();
		RecordTypeCheckListMstVO checklistVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.CHECKLIST_AT_MRD.HPMRT_RECORD_TYPE_CHECKLIST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_CASESHEET);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.COMPLUSORY_AT_MRD_LEVEL);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No CheckList Found"); } rs.beforeFirst();
			 */
			while (rs.next())
			{
				checklistVO = new RecordTypeCheckListMstVO();
				HelperMethods.populateVOfrmRS(checklistVO, rs);
				checklistVOList.add(checklistVO);
			}
		}
		catch (Exception e)
		{
			/*
			 * if (e.getClass() == HisRecordNotFoundException.class) { throw new HisRecordNotFoundException(e.getMessage()); }
			 * else
			 */throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return checklistVOList;
	}

	/**
	 * get List of todays's discharged patients for printing
	 */
	public List<DischargePrintingVO> getDisPatientListForPrinting(String unitCode, String wardCode, UserVO userVO)
	{
		List<DischargePrintingVO> dischargePatList = new ArrayList<DischargePrintingVO>();
		DischargePrintingVO dischargePatientVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.DISCHARGE_PAT.FOR_PRINTING";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), unitCode);
		populateMAP.put(sq.next(), wardCode);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Discharge Patient Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				dischargePatientVO = new DischargePrintingVO();
				HelperMethods.populateVOfrmRS(dischargePatientVO, rs);
				dischargePatList.add(dischargePatientVO);
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

		return dischargePatList;
	}

	/**
	 * get List of discharged patient for printing by crNo
	 */
	public List<DischargePrintingVO> getDisPatientListForPrintingByCrNo(String patCrnNo, UserVO userVO)
	{
		List<DischargePrintingVO> dischargePatList = new ArrayList<DischargePrintingVO>();
		DischargePrintingVO dischargePatientVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.DISCHARGE_PAT.FOR_PRINTING_CRNO_WISE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), patCrnNo);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				dischargePatientVO = new DischargePrintingVO();
				HelperMethods.populateVOfrmRS(dischargePatientVO, rs);
				dischargePatList.add(dischargePatientVO);
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

		return dischargePatList;
	}

	public ANCNeonatalDetailVO[] getListOfBirth(UserVO userVO)
	{
		ANCNeonatalDetailVO[] arrBirthVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_BIRTH_CHILD_TO_UPLOAD.HANCT_NEWNATAL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Birth Child Found ");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(ANCNeonatalDetailVO.class, rs);
				arrBirthVO = new ANCNeonatalDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrBirthVO[i] = (ANCNeonatalDetailVO) vo[i];
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

		return arrBirthVO;
	}

	public List getRelationList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_RELATION.GBLT_RELATION_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMstEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException("No Relation Found"); }
			 */

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
			throw new HisDataAccessException("MortuaryMstEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public PatientVO getMotherDetail(String motherCrNo, UserVO userVO)
	{
		PatientVO motherDtlVO = new PatientVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_MOTHER.HRGT_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), motherCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(motherDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return motherDtlVO;
	}

	public PatientVO getChildDetail(String childCrNo, UserVO userVO)
	{
		PatientVO childDtlVO = new PatientVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_CHILD.HRGT_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), childCrNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(childDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return childDtlVO;
	}

	public PatientVO getBirthSlipDetail(String crNo, UserVO userVO)
	{
		PatientVO birthDtlVO = new PatientVO();
		String query = "";
		ResultSet rs;
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_BIRTH_SLIP_DETAIL.HRGT_PATIENT_DTL";
		Connection conn = super.getTransactionContext().getConnection();

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMAP);

			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(birthDtlVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return birthDtlVO;
	}

	public PatientVO[] getListOfDeath(UserVO userVO)
	{
		PatientVO[] arrDeathVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT_DEATH_PAT_TO_UPLOAD.HPMRT_PAT_DEATH_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
		populateMAP.put(sq.next(), MrdConfig.BIRTH_DEATH_ENTRY_MODE_UPLOAD);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Death Patient Found ");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(PatientVO.class, rs);
				arrDeathVO = new PatientVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrDeathVO[i] = (PatientVO) vo[i];
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

		return arrDeathVO;
	}

	public List getGender(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GBLT_GENDER_MST";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for Gender found in database.");
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getSummonTypeList(UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HPMRT_SUMMON_TYPE_MST";

		// first call the getQueryMethod with arguments filename,querykey from prop file
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException("No records for Gender found in database.");
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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}
		return alRecord;
	}

	public List getSummonReceivedList(UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.RECEIVED_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_RECEIVED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getSearchUsersByEmpName(String _firstName, String _middleName, String _lastName, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.USERS_BY_EMPNAME.PIST_EMP_PERSONNEL_DTL";
		try
		{
			// query = HelperMethodsDAO.getQuery(filename, queryKey);
			query = "select (a.STR_FIRST_NAME ||' '|| a.STR_MIDDLE_NAME||' '|| a.STR_LAST_NAME) as empName,"
					+ " a.STR_EMP_NO as empId,a.STR_GENDER_NAME as empGender,ahis_util.dob_age_on (a.DT_BIRTH_DATE, SYSDATE) empAge,decode(a.str_gender_name,'M','Male','F','Female','O','Other') as empgendercode,"
					+ " NVL((SELECT str_desig_name FROM pist_designation_mst WHERE num_desig_id = b.num_desig_id AND gnum_isvalid =b.GNUM_ISVALID"
					+ " AND gnum_hospital_code = b.gnum_hospital_code),'-') empDesignation"
					+ " from PIST_EMP_PERSONNEL_DTL a ,pist_emp_cur_detail_dtl b where"
					+ " a.NUM_HOSPITAL_ID=b.GNUM_HOSPITAL_CODE and a.GNUM_ISVALID=b.GNUM_ISVALID"
					+ " and a.STR_EMP_NO=b.STR_EMP_NO and a.GNUM_HOSPITAL_CODE=? and a.GNUM_ISVALID=?";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(a.STR_FIRST_NAME) LIKE ('#1') order by empName";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(a.STR_MIDDLE_NAME) LIKE ('#2') order by empName";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(a.STR_LAST_NAME) LIKE ('#3') order by empName";
			}

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if (_firstName != null && !_firstName.equals(""))
			{
				query = query.replace("#1", _firstName + "%");
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query.replace("#2", _middleName + "%");
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query.replace("#3", _lastName + "%");
			}

			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List lstEmployee = new ArrayList();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(SummonDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstEmployee.add((SummonDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstEmployee;
	}

	public List getPostSummonList(UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.POST_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_ASSIGN);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_ACCEPTED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getSummonAssignChangeList(UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.CHANGE_ASSIGN_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_ASSIGN);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getAllSummonDtlVOList(String fromDate, String toDate, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getAllAttendedSummonDtlVOList(String fromDate, String toDate, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_ATTENDED_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), MrdConfig.PRESENT_IN_COURT);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getAllNotAttendedSummonDtlVOList(String fromDate, String toDate, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_NOT_ATTENDED_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), MrdConfig.NOT_PRESENT_IN_COURT);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getAllPostDtlSummonDtlVOList(String fromDate, String toDate, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_POST_SUMMON_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), MrdConfig.NOT_PRESENT_IN_COURT);
		populateMAP.put(sq.next(), MrdConfig.PRESENT_IN_COURT);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getNextHearingSummonDtlVOList(String fromDate, String toDate, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.NEXT_HEARING_SUMMON.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), MrdConfig.NOT_PRESENT_IN_COURT);
		populateMAP.put(sq.next(), MrdConfig.PRESENT_IN_COURT);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getParticularEmpSummonDtlVOList(String fromDate, String toDate, String empId, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.EMPLOYEE.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), empId);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getParticularSummonTypeSummonDtlVOList(String fromDate, String toDate, String summonTypeId, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.SUMMON_TYPE.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), toDate);
		populateMAP.put(sq.next(), fromDate);
		populateMAP.put(sq.next(), summonTypeId);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getAllEmployeeList(UserVO userVO)
	{

		List allEmployeeList = new ArrayList();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.EMPLOYEE.PIST_EMP_PERSONNEL_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DisasterAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Disaster Area Type Not Found");
			}
			else
			{
				allEmployeeList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		return allEmployeeList;
	}

	public List getPatDetailByMLCNo(String mlcNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.PATIENT_BY_MLCNO.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), mlcNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getPatDetailByPostMortemId(String postMortemId, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.PATIENT_BY_POSTMORTEMID.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), postMortemId);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public AddressVO getPatAddress(String crNo, UserVO userVO)
	{
		AddressVO patAddressVO = new AddressVO();
		String query = "";
		Map populateMap = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.PATIENT.HRGT_PATIENT_ADD_DTL";

		Sequence sq = new Sequence();

		Connection conn = super.getTransactionContext().getConnection();

		populateMap.put(sq.next(), crNo);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), MrdConfig.ADDRESS_TYPE_CODE);
		populateMap.put(sq.next(), userVO.getHospitalCode());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
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
				while (rs.next())
				{
					HelperMethods.populateVOfrmRS(patAddressVO, rs);
				}
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HisDataAccessException:: " + e);
		}
		return patAddressVO;
	}

	public List getPatPostMortemDtlByCRNo(String crNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.POSTMORTEM_BY_CRNO.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), crNo);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				// throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return summonDetailVOList;
	}

	public List getPatPostMortemDtlByAdmNo(String admNO, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.POSTMORTEM_BY_ADMNO.HMRT_POSTMORTEM_REQ_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.POSTMORTEM_STATUS_REQUEST_POSTMORTEM_DONE);
		populateMAP.put(sq.next(), admNO);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				// throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Application Execution Exception" + e);
		}

		return summonDetailVOList;
	}

	public List getPatMLCDtlByCRNo(String mlcNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.MLC_DTL_BY_CRNO.HMRT_DECEASED_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		populateMAP.put(sq.next(), mlcNo);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getPatMLCDtlByAdmNo(String admNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.MLC_DTL_BY_ADMNO.HRGT_PATIENT_MLC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), admNo);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getPatAdmissionDtlByCRNo(String crNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ADM_DTL_BY_CRNO.HIPT_PATADMISSION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), crNo);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getPatAdmissionDtlByAdmNo(String admNo, UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ADM_DTL_BY_ADMNO.HIPT_PATADMISSION_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), admNo);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);
		populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getPatMLCDtlByName(String _firstName, String _middleName, String _lastName, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.PAT_MLC_DTL.HRGT_PATIENT_MLC_DTL";
		try
		{
			// query = HelperMethodsDAO.getQuery(filename, queryKey);
			query = "select distinct a.HRGNUM_PUK as patCrNo,initcap(b.HRGSTR_FNAME)||' '|| initcap(b.HRGSTR_MNAME)||' '|| initcap(b.HRGSTR_LNAME) as patName,b.GNUM_GENDER_CODE as patGenderCode,"
					+ " b.HRGSTR_FATHER_NAME as fatherName, b.HRGSTR_MOMNAME as motherName, b.HRGSTR_AGE as patAge, b.HRGSTR_SPOUSENAME as spouseName,to_char(b.HRGDT_DOB,'dd-Mon-yyyy') as patDOB,"
					+ " decode(b.GNUM_GENDER_CODE,1,'Male',2,'Female',3,'Other') as patGender,a.HRGSTR_MLC_NO,a.HRGNUM_EPISODE_CODE as episodeCode,a.HRGNUM_VISIT_NO as episodeVisitNo, "
					+ " to_char(a.HRGDT_MLC_DATE,'dd-Mon-yyyy') as mlcDate,a.HRGSTR_MLC_NO as MLCNo, "
					+ " (SELECT x.hmrnum_postmortem_id FROM hmrt_postmortem_req_dtl x,HMRT_POSTMORTEM_DTL y "
					+ "  WHERE a.hrgstr_mlc_no = x.hrgstr_mlc_no AND a.gnum_hospital_code = x.gnum_hospital_code "
					+ "  AND a.gnum_isvalid = x.gnum_isvalid and x.hmrnum_postmortem_id=y.hmrnum_postmortem_id "
					+ "	AND y.gnum_hospital_code = x.gnum_hospital_code AND y.gnum_isvalid = x.gnum_isvalid "
					+ "	and y.GDT_VERIFY_DATE!=null "
					+ "	AND x.hrgnum_srno = (SELECT MAX (e.hrgnum_srno) FROM hmrt_postmortem_req_dtl e "
					+ "  WHERE x.hmrnum_postmortem_id = e.hmrnum_postmortem_id) "
					+ "	AND y.hrgnum_srno = (SELECT MAX (d.hrgnum_srno) FROM HMRT_POSTMORTEM_DTL d "
					+ "  WHERE x.hmrnum_postmortem_id = d.hmrnum_postmortem_id)  "
					+ "	) AS postmortemid "
					+ " from HRGT_PATIENT_MLC_DTL a,HRGT_PATIENT_DTL b "
					+ " where a.GNUM_ISVALID=? and  a.GNUM_HOSPITAL_CODE=? "
					+ " and a.HRGNUM_PUK =b.HRGNUM_PUK and a.GNUM_ISVALID=b.GNUM_ISVALID and a.GNUM_HOSPITAL_CODE=b.GNUM_HOSPITAL_CODE";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_LNAME) LIKE ('#3') ";
			}
			query = query + "order by patName";

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if (_firstName != null && !_firstName.equals(""))
			{
				query = query.replace("#1", _firstName + "%");
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query.replace("#2", _middleName + "%");
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query.replace("#3", _lastName + "%");
			}

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List lstPatMlc = new ArrayList();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(SummonDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatMlc.add((SummonDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstPatMlc;
	}

	public List getPatAdmissionDtlByName(String _firstName, String _middleName, String _lastName, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.HIPT_PATADMISSION_DTL.HRGT_PATIENT_DTL";
		try
		{
			// query = HelperMethodsDAO.getQuery(filename, queryKey);
			query = "select a.HIPNUM_ADMNO as patAdmNo, a.HRGNUM_PUK as patCrNo ,to_char(a.HIPDT_ADMDATETIME,'dd-Mon-yyyy') as patAdmissionDate, initcap(b.hrgstr_fname) || ' ' || initcap(b.hrgstr_mname) || ' ' || initcap(b.hrgstr_lname) AS patname,"
					+ " DECODE (b.gnum_gender_code,1, 'Male',2, 'Female',3, 'Other') AS patgender,b.hrgstr_age AS patage"
					+ ", b.hrgstr_father_name AS fathername,b.hrgstr_spousename AS spousename,b.hrgstr_momname AS mothername"
					+ " from HIPT_PATADMISSION_DTL a,HRGT_PATIENT_DTL b"
					+ " where a.GNUM_ISVALID=? and a.GNUM_HOSPITAL_CODE=?"
					+ " and a.HRGNUM_PUK=b.HRGNUM_PUK and a.GNUM_HOSPITAL_CODE=b.GNUM_HOSPITAL_CODE and a.GNUM_ISVALID=b.GNUM_ISVALID"
					+ " and a.HIPDT_ADMSTATUS_CODE not in (?,?)";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_LNAME) LIKE ('#3') ";
			}
			query = query + "order by patName";

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if (_firstName != null && !_firstName.equals(""))
			{
				query = query.replace("#1", _firstName + "%");
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query.replace("#2", _middleName + "%");
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query.replace("#3", _lastName + "%");
			}

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_NOT_REPORTED);
			populateMAP.put(sq.next(), InpatientConfig.PATIENT_ADMISSION_STATUS_ADMISSION_CANCELLED);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List lstPatMlc = new ArrayList();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(SummonDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatMlc.add((SummonDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstPatMlc;
	}

	public List getPatPostMortemDtlByName(String _firstName, String _middleName, String _lastName, UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.PAT_POSTMORTEM_DTL.HMRT_POSTMORTEM_REQ_DTL";
		try
		{
			// query = HelperMethodsDAO.getQuery(filename, queryKey);
			query = "select initcap(HMRSTR_FNAME)||' '||initcap(HMRSTR_MNAME)||' '|| initcap(HMRSTR_LNAME) as patName, HMRSTR_FATHER_NAME as fatherName,"
					+ " HMRSTR_SPOUSE_NAME as spouseName,b.HMRNUM_POSTMORTEM_ID as postmortemId,"
					+ " HMRSTR_ADD1||' '|| HMRSTR_ADD2 as patAddress ,HMRSTR_MOTHER_NAME as motherName,HRGNUM_PUK as patCrNo, "
					+ " ahis_util.dob_age_on (a.HMRDT_DOB, SYSDATE) patAge,to_char(a.HMRDT_DOB,'dd-Mon-yyyy') as patDOB,GNUM_GENDER_CODE as patGenderCode,a.HRGNUM_EPISODE_CODE as episodeCode,a.HRGNUM_VISIT_NO as episodeVisitNo,  "
					+ " decode(a.GNUM_GENDER_CODE,1,'Male',2,'Female',3,'Other') as patGender,b.HRGSTR_MLC_NO as MLCNo,to_char(b.HMRDT_REQ_DATETIME,'dd-Mon-yyyy hh24:mi') as postMotemReqDate  "
					+ " from HMRT_DECEASED_DTL a,HMRT_POSTMORTEM_REQ_DTL b,HMRT_POSTMORTEM_DTL c   "
					+ " where a.HMRNUM_DECEASED_NO=b.HMRNUM_DECEASED_NO and a.GNUM_HOSPITAL_CODE=b.GNUM_HOSPITAL_CODE "
					+ " and a.GNUM_ISVALID=b.GNUM_ISVALID and a.GNUM_HOSPITAL_CODE=? and a.GNUM_ISVALID=?  "
					+ " and c.HMRNUM_POSTMORTEM_ID=b.HMRNUM_POSTMORTEM_ID AND c.gnum_hospital_code = b.gnum_hospital_code AND c.gnum_isvalid = b.gnum_isvalid "
					+ " and c.GDT_VERIFY_DATE is not null"
					+ " AND b.hrgnum_srno =(SELECT MAX (x.hrgnum_srno) FROM hmrt_postmortem_req_dtl x WHERE x.hmrnum_postmortem_id = b.hmrnum_postmortem_id) "
					+ " AND c.hrgnum_srno = (SELECT MAX (y.hrgnum_srno) FROM HMRT_POSTMORTEM_DTL y WHERE y.hmrnum_postmortem_id = b.hmrnum_postmortem_id) ";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(a.HMRSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(a.HMRSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(a.HMRSTR_LNAME) LIKE ('#3')";
			}
			query = query + "order by patName";

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if (_firstName != null && !_firstName.equals(""))
			{
				query = query.replace("#1", _firstName + "%");
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query.replace("#2", _middleName + "%");
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query.replace("#3", _lastName + "%");
			}

			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List lstPatMlc = new ArrayList();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(SummonDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstPatMlc.add((SummonDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstPatMlc;
	}

	public ANCNeonatalDetailVO[] searchForBirthRegUpload(PatientVO searchFindVO, UserVO userVO)
	{
		ANCNeonatalDetailVO[] arrBirthVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		try
		{
			query = "SELECT A.HRGNUM_PUK AS patCrNo, (SELECT B.HRGSTR_FNAME||' '||B.HRGSTR_MNAME||' '||B.HRGSTR_LNAME FROM HRGT_PATIENT_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK)  AS motherName, "
					+ "A.GNUM_GENDER_CODE AS genderCode,(SELECT D.GSTR_GENDER_SHORT FROM GBLT_GENDER_MST D WHERE D.GNUM_GENDER_CODE=A.GNUM_GENDER_CODE AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?) AS gender, "
					+ "A.HRGNUM_CHILD_PUK AS childCrNo, (SELECT C.HRGSTR_FNAME||' '||C.HRGSTR_MNAME||' '||C.HRGSTR_LNAME FROM HRGT_PATIENT_DTL C WHERE C.HRGNUM_PUK=A.HRGNUM_CHILD_PUK) AS childName, "
					+ "TO_CHAR(A.HANCDT_BIRTH_DATETIME,'dd-Mon-yyyy HH24:MI') AS birthDateTime , 'Not Uploaded' AS status, '0' AS statusCode "
					+ "FROM HANCT_NEWNATAL_DTL A WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? "
					+ "AND A.HRGNUM_CHILD_PUK IS NOT NULL "
					+ "AND A.HRGNUM_CHILD_PUK NOT IN (SELECT X.HRGNUM_PUK FROM HPMRT_BIRTHDEATH_UPLOAD_DTL X WHERE X.HPMRNUM_RECORD_TYPE = ? "
					+ "AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?)";
			if (searchFindVO.getPatCrNo() != null && !searchFindVO.getPatCrNo().equals("")) query = query + " AND HRGNUM_CHILD_PUK = "
					+ searchFindVO.getPatCrNo() + "  ";
			if (searchFindVO.getPatDOB() != null && !searchFindVO.getPatDOB().equals("")) query = query
					+ " AND TRUNC(a.hancdt_birth_datetime)=TO_DATE( '" + searchFindVO.getPatDOB() + "','dd-Mon-yyyy') ";

			query = query
					+ " UNION "
					+ "SELECT A.HRGNUM_PUK AS patCrNo, (SELECT B.HRGSTR_FNAME||' '||B.HRGSTR_MNAME||' '||B.HRGSTR_LNAME FROM HRGT_PATIENT_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK)  AS motherName, "
					+ "A.GNUM_GENDER_CODE AS genderCode,(SELECT D.GSTR_GENDER_SHORT FROM GBLT_GENDER_MST D WHERE D.GNUM_GENDER_CODE=A.GNUM_GENDER_CODE AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?) AS gender, "
					+ "A.HRGNUM_CHILD_PUK AS childCrNo, (SELECT C.HRGSTR_FNAME||' '||C.HRGSTR_MNAME||' '||C.HRGSTR_LNAME FROM HRGT_PATIENT_DTL C WHERE C.HRGNUM_PUK=A.HRGNUM_CHILD_PUK) AS childName, "
					+ "TO_CHAR(A.HANCDT_BIRTH_DATETIME,'dd-Mon-yyyy HH24:MI') AS birthDateTime , DECODE(E.HPMRNUM_ENTRY_MODE,1,'Uploaded',2,'Uploaded & Handover',3,'Duplicate & Handover') AS status, DECODE(E.HPMRNUM_ENTRY_MODE,1,'1',2,'2',3,'3') AS statusCode "
					+ "FROM HANCT_NEWNATAL_DTL A, HPMRT_BIRTHDEATH_UPLOAD_DTL E WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? "
					+ "AND A.HRGNUM_CHILD_PUK=E.HRGNUM_PUK AND E.HPMRNUM_RECORD_TYPE = ? "
					+ "AND A.HRGNUM_CHILD_PUK IS NOT NULL "
					+ "AND e.HPMRNO_SLNO = (SELECT MAX(x.HPMRNO_SLNO) FROM HPMRT_BIRTHDEATH_UPLOAD_DTL x WHERE x.HPMRNUM_RECORD_TYPE = ? AND x.HRGNUM_PUK=e.HRGNUM_PUK) ";

			if (searchFindVO.getPatCrNo() != null && !searchFindVO.getPatCrNo().equals("")) query = query + " AND HRGNUM_CHILD_PUK = "
					+ searchFindVO.getPatCrNo() + "  ";
			if (searchFindVO.getPatDOB() != null && !searchFindVO.getPatDOB().equals("")) query = query
					+ " AND TRUNC(a.hancdt_birth_datetime)=TO_DATE( '" + searchFindVO.getPatDOB() + "','dd-Mon-yyyy') ";

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Birth Registration Found ");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(ANCNeonatalDetailVO.class, rs);
				arrBirthVO = new ANCNeonatalDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrBirthVO[i] = (ANCNeonatalDetailVO) vo[i];
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

		return arrBirthVO;
	}

	public ANCNeonatalDetailVO[] searchForBirthRegUploadByMother(PatientVO searchFindVO, UserVO userVO)
	{
		ANCNeonatalDetailVO[] arrBirthVO = null;
		ValueObject vo[] = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();

		try
		{
			query = "SELECT A.HRGNUM_PUK AS patCrNo, (SELECT B.HRGSTR_FNAME||' '||B.HRGSTR_MNAME||' '||B.HRGSTR_LNAME FROM HRGT_PATIENT_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK)  AS motherName, "
					+ "A.GNUM_GENDER_CODE AS genderCode,(SELECT D.GSTR_GENDER_SHORT FROM GBLT_GENDER_MST D WHERE D.GNUM_GENDER_CODE=A.GNUM_GENDER_CODE AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?) AS gender, "
					+ "A.HRGNUM_CHILD_PUK AS childCrNo, (SELECT C.HRGSTR_FNAME||' '||C.HRGSTR_MNAME||' '||C.HRGSTR_LNAME FROM HRGT_PATIENT_DTL C WHERE C.HRGNUM_PUK=A.HRGNUM_CHILD_PUK) AS childName, "
					+ "TO_CHAR(A.HANCDT_BIRTH_DATETIME,'dd-Mon-yyyy HH24:MI') AS birthDateTime , 'Not Uploaded' AS status, '0' AS statusCode "
					+ "FROM HANCT_NEWNATAL_DTL A WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? "
					+ "AND A.HRGNUM_CHILD_PUK IS NOT NULL "
					+ "AND A.HRGNUM_CHILD_PUK NOT IN (SELECT X.HRGNUM_PUK FROM HPMRT_BIRTHDEATH_UPLOAD_DTL X WHERE X.HPMRNUM_RECORD_TYPE = ? "
					+ "AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?)";
			if (searchFindVO.getPatCrNo() != null && !searchFindVO.getPatCrNo().equals("")) query = query + " AND A.HRGNUM_PUK = "
					+ searchFindVO.getPatCrNo() + "  ";

			String subQuery = "";
			if (!searchFindVO.getPatFirstName().equals("") || !searchFindVO.getPatMiddleName().equals("")
					|| !searchFindVO.getPatLastName().equals(""))
			{
				subQuery = subQuery
						+ " AND a.hrgnum_puk IN (SELECT HRGNUM_PUK FROM HRGT_PATIENT_DTL WHERE GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ? ";
				if (searchFindVO.getPatFirstName() != null && !searchFindVO.getPatFirstName().equals("")) subQuery = subQuery
						+ " AND UPPER(HRGSTR_FNAME) LIKE UPPER('" + searchFindVO.getPatFirstName() + "%') ";
				if (searchFindVO.getPatMiddleName() != null && !searchFindVO.getPatMiddleName().equals("")) subQuery = subQuery
						+ " AND UPPER(HRGSTR_MNAME) LIKE UPPER('" + searchFindVO.getPatMiddleName() + "%') ";
				if (searchFindVO.getPatLastName() != null && !searchFindVO.getPatLastName().equals("")) subQuery = subQuery
						+ " AND UPPER(HRGSTR_LNAME) LIKE UPPER('" + searchFindVO.getPatLastName() + "%') ";

				subQuery = subQuery + ")";
			}

			query = query + subQuery;

			query = query
					+ " UNION "
					+ "SELECT A.HRGNUM_PUK AS patCrNo, (SELECT B.HRGSTR_FNAME||' '||B.HRGSTR_MNAME||' '||B.HRGSTR_LNAME FROM HRGT_PATIENT_DTL B WHERE B.HRGNUM_PUK=A.HRGNUM_PUK)  AS motherName, "
					+ "A.GNUM_GENDER_CODE AS genderCode,(SELECT D.GSTR_GENDER_SHORT FROM GBLT_GENDER_MST D WHERE D.GNUM_GENDER_CODE=A.GNUM_GENDER_CODE AND GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?) AS gender, "
					+ "A.HRGNUM_CHILD_PUK AS childCrNo, (SELECT C.HRGSTR_FNAME||' '||C.HRGSTR_MNAME||' '||C.HRGSTR_LNAME FROM HRGT_PATIENT_DTL C WHERE C.HRGNUM_PUK=A.HRGNUM_CHILD_PUK) AS childName, "
					+ "TO_CHAR(A.HANCDT_BIRTH_DATETIME,'dd-Mon-yyyy HH24:MI') AS birthDateTime , DECODE(E.HPMRNUM_ENTRY_MODE,1,'Uploaded',2,'Uploaded & Handover',3,'Duplicate & Handover') AS status, DECODE(E.HPMRNUM_ENTRY_MODE,1,'1',2,'2',3,'3') AS statusCode "
					+ "FROM HANCT_NEWNATAL_DTL A, HPMRT_BIRTHDEATH_UPLOAD_DTL E WHERE A.GNUM_ISVALID = ? AND A.GNUM_HOSPITAL_CODE = ? "
					+ "AND A.HRGNUM_CHILD_PUK=E.HRGNUM_PUK AND E.HPMRNUM_RECORD_TYPE = ? "
					+ "AND A.HRGNUM_CHILD_PUK IS NOT NULL "
					+ "AND e.HPMRNO_SLNO = (SELECT MAX(x.HPMRNO_SLNO) FROM HPMRT_BIRTHDEATH_UPLOAD_DTL x WHERE x.HPMRNUM_RECORD_TYPE = ? AND x.HRGNUM_PUK=e.HRGNUM_PUK) ";

			if (searchFindVO.getPatCrNo() != null && !searchFindVO.getPatCrNo().equals("")) query = query + " AND A.HRGNUM_PUK = "
					+ searchFindVO.getPatCrNo() + "  ";

			String subQuery1 = "";
			if (!searchFindVO.getPatFirstName().equals("") || !searchFindVO.getPatMiddleName().equals("")
					|| !searchFindVO.getPatLastName().equals(""))
			{
				subQuery1 = subQuery1
						+ " AND a.hrgnum_puk IN (SELECT HRGNUM_PUK FROM HRGT_PATIENT_DTL WHERE GNUM_ISVALID = ? AND GNUM_HOSPITAL_CODE = ?  ";
				if (searchFindVO.getPatFirstName() != null && !searchFindVO.getPatFirstName().equals("")) subQuery1 = subQuery1
						+ " AND UPPER(HRGSTR_FNAME) LIKE UPPER('" + searchFindVO.getPatFirstName() + "%') ";
				if (searchFindVO.getPatMiddleName() != null && !searchFindVO.getPatMiddleName().equals("")) subQuery1 = subQuery1
						+ " AND UPPER(HRGSTR_MNAME) LIKE UPPER('" + searchFindVO.getPatMiddleName() + "%') ";
				if (searchFindVO.getPatLastName() != null && !searchFindVO.getPatLastName().equals("")) subQuery1 = subQuery1
						+ " AND UPPER(HRGSTR_LNAME) LIKE UPPER('" + searchFindVO.getPatLastName() + "%') ";

				subQuery1 = subQuery1 + ")";
			}

			query = query + subQuery1;

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		if (!searchFindVO.getPatFirstName().equals("") || !searchFindVO.getPatMiddleName().equals("") || !searchFindVO.getPatLastName().equals(""))
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_BIRTH);

		if (!searchFindVO.getPatFirstName().equals("") || !searchFindVO.getPatMiddleName().equals("") || !searchFindVO.getPatLastName().equals(""))
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Birth Registration Found ");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(ANCNeonatalDetailVO.class, rs);
				arrBirthVO = new ANCNeonatalDetailVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrBirthVO[i] = (ANCNeonatalDetailVO) vo[i];
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

		return arrBirthVO;
	}

	public List getCIDNoInfoList(UserVO userVO)
	{
		List DocReceivingFormDtlVOLst = new ArrayList();
		DocReceivingFormDtlVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.CIDNO.FTS_DOC_RECEIVING_FORM_TRN";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MrdConfig.SUMMON_TYPE_NUM_CATEGORY_ID);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new DocReceivingFormDtlVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				DocReceivingFormDtlVOLst.add(vo);
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

		return DocReceivingFormDtlVOLst;
	}

	public List getCIDNoInfoListForInsurance(UserVO userVO)
	{
		List DocReceivingFormDtlVOLst = new ArrayList();
		DocReceivingFormDtlVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.INSURANCE_CIDNO.FTS_DOC_RECEIVING_FORM_TRN";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), MrdConfig.INSURANCE_TYPE_NUM_CATEGORY_ID);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("No file found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new DocReceivingFormDtlVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				DocReceivingFormDtlVOLst.add(vo);
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

		return DocReceivingFormDtlVOLst;
	}

	public List getAllInsuranceCompanyList(UserVO userVO)
	{

		List companyList = new ArrayList();
		ResultSet rs;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL_INSURANCE_COMPANY.HTRT_COMPANY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.INSURANCE_COMPANY_TYPE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("DisasterAreaMstDAO.populateMAP::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Disaster Area Type Not Found");
			}
			else
			{
				companyList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else
			{
				e.printStackTrace();
				throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
			}
		}
		return companyList;
	}

	public List getPatInfoList(String patCrNo, String patAdmNo, String _firstName, String _middleName, String _lastName, UserVO userVO)
	{
		List patInfoList = new ArrayList();
		InsuranceDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "SELECT.PAT_INFO_BY_ADMNO.HIPT_PATADMISSION_DTL";

		try
		{
			query = " select a.HIPNUM_ADMNO as patAdmNo, a.HRGNUM_PUK as patCrNo, a.HRGNUM_EPISODE_CODE as episodeCode, "
					+ " a.HRGNUM_VISIT_NO as episodeVisitNo, initcap(b.HRGSTR_FNAME)||' '|| initcap(b.HRGSTR_MNAME)||' '|| initcap(b.HRGSTR_LNAME) as patName, "
					+ " (select GSTR_GENDER_NAME from GBLT_GENDER_MST x  where x.GNUM_ISVALID=a.gnum_isvalid and x.gnum_hospital_code = a.gnum_hospital_code and x.gnum_gender_code=b.gnum_gender_code) AS patgender, "
					+ " ahis_util.dob_age_on (b.HRGDT_DOB, SYSDATE) patAge,to_char(a.HIPDT_ADMDATETIME,'dd-Mon-yyyy') as admDate, "
					+ " (select HGSTR_UNITNAME from HGBT_UNIT_MST x where a.GNUM_DEPTUNIT_CODE=x.HGNUM_DEPTUNITCODE and a.GNUM_HOSPITAL_CODE=x.GNUM_HOSPITAL_CODE and a.GNUM_ISVALID=x.GNUM_ISVALID) as unitName, "
					+ " (select GSTR_DEPT_NAME from GBLT_DEPARTMENT_MST y where substr(a.GNUM_DEPTUNIT_CODE,1,3)=y.GNUM_DEPT_CODE and a.GNUM_HOSPITAL_CODE=y.GNUM_HOSPITAL_CODE and a.GNUM_ISVALID=y.GNUM_ISVALID) as deptName, "
					+ " to_char(HIPDT_DISDATETIME,'dd-Mon-yyyy') as dischargeDate" + " from HIPT_PATADMISSION_DTL a ,HRGT_PATIENT_DTL b "
					+ " where a.HRGNUM_PUK=b.HRGNUM_PUK and a.GNUM_ISVALID=b.GNUM_ISVALID and a.GNUM_HOSPITAL_CODE=b.GNUM_HOSPITAL_CODE "
					+ " and a.GNUM_HOSPITAL_CODE=? and a.GNUM_ISVALID=? ";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(b.HRGSTR_LNAME) LIKE ('#3') ";
			}
			if (patCrNo != null && !patCrNo.equals(""))
			{
				query = query + " and a.HRGNUM_PUK=?1 ";
			}
			if (patAdmNo != null && !patAdmNo.equals(""))
			{
				query = query + " and  a.HIPNUM_ADMNO=?2 ";
			}
			query = query + "order by patName";

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		if (_firstName != null && !_firstName.equals(""))
		{
			query = query.replace("#1", _firstName + "%");
		}
		if (_middleName != null && !_middleName.equals(""))
		{
			query = query.replace("#2", _middleName + "%");
		}
		if (_lastName != null && !_lastName.equals(""))
		{
			query = query.replace("#3", _lastName + "%");
		}
		if (patCrNo != null && !patCrNo.equals(""))
		{
			query = query.replace("?1", patCrNo);
		}
		if (patAdmNo != null && !patAdmNo.equals(""))
		{
			query = query.replace("?2", patAdmNo);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new InsuranceDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				patInfoList.add(vo);
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

		return patInfoList;
	}

	public List getPatDeathUploadList(String patCrNo, String deathDate, String _firstName, String _middleName, String _lastName, UserVO userVO)
	{
		List patInfoList = new ArrayList();
		PatientVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "SELECT.PAT_INFO_BY_ADMNO.HIPT_PATADMISSION_DTL";

		try
		{
			query = " SELECT a.hrgnum_puk AS patcrno,a.hrgstr_fname || ' ' || a.hrgstr_mname || ' ' || a.hrgstr_lname AS patfirstname, "
					+ " a.gnum_gender_code AS patgendercode, a.hrgstr_age AS patage, TO_CHAR (b.hpmrdt_death_datetime,'dd-Mon-yyyy HH24:MI') AS deathdatetime, "
					+ " b.hpmrdt_death_datetime AS datetime, "
					+ " (SELECT d.gstr_gender_short FROM GBLT_GENDER_MST d WHERE d.gnum_gender_code = a.gnum_gender_code AND d.gnum_isvalid = ? AND d.gnum_hospital_code = ?) AS patgender, "
					+ " 'Not Uploaded' AS patstatus, '0' AS patstatuscode FROM HRGT_PATIENT_DTL a, HPMRT_PAT_DEATH_DTL b "
					+ " WHERE a.hrgnum_puk = b.hrgnum_puk AND b.hpmrnum_slno = (SELECT MAX (c.hpmrnum_slno) "
					+ " FROM HPMRT_PAT_DEATH_DTL c WHERE c.hrgnum_puk = b.hrgnum_puk) AND b.hrgnum_puk NOT IN ( "
					+ " SELECT x.hrgnum_puk FROM HPMRT_BIRTHDEATH_UPLOAD_DTL x "
					+ " WHERE x.hpmrnum_record_type = ? AND gnum_isvalid = ? AND gnum_hospital_code = ?) ";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_LNAME) LIKE ('#3') ";
			}
			if (patCrNo != null && !patCrNo.equals(""))
			{
				query = query + " and b.HRGNUM_PUK=?1 ";
			}
			if (deathDate != null && !deathDate.equals(""))
			{
				query = query + " and  trunc(b.HPMRDT_DEATH_DATETIME)=to_date('" + deathDate + "','dd-mon-yyyy') ";
			}

			query = query
					+ " UNION "
					+ " SELECT a.hrgnum_puk AS patcrno, a.hrgstr_fname || ' ' || a.hrgstr_mname || ' ' || a.hrgstr_lname AS patfirstname, "
					+ " a.gnum_gender_code AS patgendercode, a.hrgstr_age AS patage, TO_CHAR (b.hpmrdt_death_datetime, 'dd-Mon-yyyy HH24:MI') AS deathdatetime, "
					+ " b.hpmrdt_death_datetime AS datetime, "
					+ " (SELECT d.gstr_gender_short FROM GBLT_GENDER_MST d WHERE d.gnum_gender_code = a.gnum_gender_code AND d.gnum_isvalid = ? "
					+ " AND d.gnum_hospital_code = ?) AS patgender, "
					+ " DECODE (e.hpmrnum_entry_mode, 1, 'Uploaded',2,'Upload & Handover',3,'Duplicate & Handover') AS patstatus, "
					+ " DECODE (e.hpmrnum_entry_mode, 1, '1',2,'2',3,'3') AS patstatuscode "
					+ " FROM HRGT_PATIENT_DTL a, HPMRT_PAT_DEATH_DTL b,HPMRT_BIRTHDEATH_UPLOAD_DTL e "
					+ " WHERE a.hrgnum_puk = b.hrgnum_puk AND e.hrgnum_puk = b.hrgnum_puk " + " AND b.hpmrnum_slno = (SELECT MAX (c.hpmrnum_slno) "
					+ " FROM HPMRT_PAT_DEATH_DTL c WHERE c.hrgnum_puk = b.hrgnum_puk) "
					+ " AND a.gnum_isvalid = ? AND a.gnum_hospital_code = ? AND e.hpmrnum_record_type = ? "
					+ " AND e.hpmrno_slno = (SELECT MAX (x.hpmrno_slno) " + " FROM HPMRT_BIRTHDEATH_UPLOAD_DTL x "
					+ " WHERE x.hpmrnum_record_type = ? AND x.hrgnum_puk = e.hrgnum_puk) ";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_FNAME) LIKE ('#4') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_MNAME) LIKE ('#5') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(a.HRGSTR_LNAME) LIKE ('#6') ";
			}
			if (patCrNo != null && !patCrNo.equals(""))
			{
				query = query + " and b.HRGNUM_PUK=?3 ";
			}
			if (deathDate != null && !deathDate.equals(""))
			{
				query = query + " and  trunc(b.HPMRDT_DEATH_DATETIME)=to_date('" + deathDate + "','dd-mon-yyyy') ";
			}

			query = query + "ORDER BY datetime";

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);
		populateMAP.put(sq.next(), MrdConfig.RREGISTRATION_UPLOAD_MODE_DEATH);

		if (_firstName != null && !_firstName.equals(""))
		{
			query = query.replace("#1", _firstName + "%");
			query = query.replace("#4", _firstName + "%");
		}
		if (_middleName != null && !_middleName.equals(""))
		{
			query = query.replace("#2", _middleName + "%");
			query = query.replace("#5", _middleName + "%");
		}
		if (_lastName != null && !_lastName.equals(""))
		{
			query = query.replace("#3", _lastName + "%");
			query = query.replace("#6", _lastName + "%");
		}
		if (patCrNo != null && !patCrNo.equals(""))
		{
			query = query.replace("?1", patCrNo);
			query = query.replace("?3", patCrNo);
		}
		if (deathDate != null && !deathDate.equals(""))
		{
			query = query.replace("?2", deathDate);
			query = query.replace("?4", deathDate);
		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new PatientVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				patInfoList.add(vo);
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

		return patInfoList;
	}

	public List getRecordType(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RECORD_TYPE.HPMRT_MRD_RECORDTYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 04-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getSummonAssignDtlList(UserVO userVO)
	{
		List summonDetailVOList = new ArrayList();
		SummonDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ASSIGN_SUMMON_DETAIL.HPMRT_SUMMON_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_ASSIGN);
		populateMAP.put(sq.next(), MrdConfig.SUMMON_ACCEPTED);
		populateMAP.put(sq.next(), userVO.getUserEmpID());

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				// throw new HisRecordNotFoundException("Patient is either not addmited or not discharged.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new SummonDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				summonDetailVOList.add(vo);
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

		return summonDetailVOList;
	}

	public List getRecordTypeBasedOnMrd(String mrdCode, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RECORD_TYPE_BASED_ON_MRD.HPMRT_MRD_RECORDTYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE);
			populateMAP.put(sq.next(), mrdCode);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Type Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getRecordTypeBasedOnMovableType(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RECORD_TYPE_BASED_ON_MOVABLE_TYPE.HPMRT_MRD_RECORDTYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.RECORD_MOVABLE_TYPE_MOVABLE_ONLY);
			populateMAP.put(sq.next(), MrdConfig.RECORD_MOVABLE_TYPE_DISPATCH_AND_MOVE_ONLY);
			populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_PATIENT_CENTRIC_YES);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	//added by swati s agar on date:20-02-2019
	
	public List getPatientRecord(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RECORD_TYPE_BASED_ON_MOVABLE_TYPE.HPMRT_MRD_RECORDTYPE_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.RECORD_MOVABLE_TYPE_MOVABLE_ONLY);
			populateMAP.put(sq.next(), MrdConfig.RECORD_MOVABLE_TYPE_DISPATCH_AND_MOVE_ONLY);
			populateMAP.put(sq.next(), MrdConfig.RECORD_TYPE_PATIENT_CENTRIC_YES);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	
	
	public List getMrdList(String recordType, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_MRD.HPMRT_MRD_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE);
			populateMAP.put(sq.next(), recordType);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public RackMstVO[] getRackBasedOnMrd(String recordType, String mrdCode, UserVO userVO)
	{
		RackMstVO[] arrRackVO = null;
		ValueObject vo[] = null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RACK_BASED_ON_MRD.HPMRT_RACK_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.MRD_RACK_STATUS_WORKING);
			populateMAP.put(sq.next(), mrdCode);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_RACK_WISE);
			populateMAP.put(sq.next(), recordType);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrRackVO = null;
				// throw new HisRecordNotFoundException("");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(RackMstVO.class, rs);
				arrRackVO = new RackMstVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrRackVO[i] = (RackMstVO) vo[i];
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

		return arrRackVO;
	}

	
	//swati sagar on date:14-may-2019
	
	
	
     	public RackMstVO[] getRackDtlForCrAdmNo(String recordType, String mrdCode, UserVO userVO)
	{
		RackMstVO[] arrRackVO = null;
		ValueObject vo[] = null;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RACK_BASED_ON_MRD.HPMRT_RACK_MST_ADMCRNO";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			System.out.println("GET_RACK_BASED_ON_MRD.HPMRT_RACK_MST_ADMCRNO::"+query);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				arrRackVO = null;
				// throw new HisRecordNotFoundException("");
			}
			else
			{
				rs.beforeFirst();

				vo = HelperMethods.populateVOfrmRS(RackMstVO.class, rs);
				arrRackVO = new RackMstVO[vo.length];
				for (int i = 0; i < vo.length; i++)
				{
					arrRackVO[i] = (RackMstVO) vo[i];
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

		return arrRackVO;
	}

	
	
	
	
	
	
	public List getShelfBasedOnRack(String recordType, String rackId, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_SHELF_BASED_ON_RACK.HPMRT_RACK_SHELF_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.MRD_RACK_SHELF_STATUS_SPACE_AVAILABLE);
			populateMAP.put(sq.next(), rackId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE);
			populateMAP.put(sq.next(), recordType);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shelf Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	
	
	
	//added by swati sagar
	//date:14-may2019
	public List getShelfBasedOnRackForCrAdmNo(String recordType, String rackId, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_SHELF_BASED_ON_RACK.HPMRT_RACK_SHELF_MST_ADMCR_NO";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.MRD_RACK_SHELF_STATUS_SPACE_AVAILABLE);
			populateMAP.put(sq.next(), rackId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shelf Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getMrdListUserBased(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_MRD_USER_BASED.HPMRT_MRD_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}
	

	public List getRecordPutByList(String deptCode, UserVO userVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			//Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PEON_LIST);
			//Added by Dheeraj on 25-Sept-2018
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_EMP_LIST);
			strProc.addInParameter(1, Types.VARCHAR, userVO.getHospitalCode());
			//strProc.addInParameter(2, Types.VARCHAR, MrdConfig.DECEASED_ACCEPTANCE_BROUGHT_BY_PEON);
			
			// Added by Dheeraj on 25-Sept-2018 to get MRD_Record_Reported by List
			strProc.addInParameter(2, Types.VARCHAR, MrdConfig.MRD_RECORD_REPORTED_BY_PROCESS_ID);
			strProc.addInParameter(3, Types.VARCHAR, deptCode);
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
		return alRecord;
	}

	public List getCheckListByRecordType(String recordType, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_CHECKLIST_BY_RECORDTYPE.HPMRT_RECORD_CHECKLIST_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 05-Jul-2012
			populateMAP.put(sq.next(), recordType);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
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
			if (rs.next())
				alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getMrdListBasedOnRecordType(String recordType, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_MRD_LIST_BY_RECORD_TYPE.HPMRT_MRD_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_MRD_WISE);
			populateMAP.put(sq.next(), recordType);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No MRD Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getExternalLabList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_EXT_LAB.GBLT_LABORATORY_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMstEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException(""); }
			 */

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
			throw new HisDataAccessException("MortuaryMstEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getExternalLabTestList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_EXT_LABTEST.GBLT_TEST_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode()); #commented by Shruti shail on 21-Apr-2017

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MortuaryMstEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			/*
			 * if (!rs.next()) { throw new HisRecordNotFoundException(""); }
			 */

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
			throw new HisDataAccessException("MortuaryMstEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public List getInsuranceReceivedDtlList(UserVO userVO)
	{
		List patInfoList = new ArrayList();
		InsuranceDetailVO vo = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.INSURANCE_RECEIVED.HPMRT_INSURANCE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.INSURENCE_STATUS_REQUEST_RECEIVE);
		}
		catch (Exception e)
		{

		}

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				vo = new InsuranceDetailVO();
				HelperMethods.populateVOfrmRS(vo, rs);
				patInfoList.add(vo);
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

		return patInfoList;
	}

	public List getDiseaseType(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GET_DISEASE_TYPE";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Record Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public String getRecordPreviousLocation(String selRecordId, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_RECORD_PREVIOUS_LOCATION.HPMRT_MRD_RECORD_DTL";
		ResultSet rs;
		Sequence sq = new Sequence();
		String str = "";

		populateMAP.put(sq.next(), selRecordId);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file::" + e);
		}
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) str = "Not Archived" + "@" + "0" + "@" + MrdConfig.NO;
			else
			{
				rs.first();
				str = rs.getString(1) + "@" + MrdConfig.YES;
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return str;
	}

	public List getAllCheckListList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_CHECKLIST.HPMRT_CHECKLIST_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			//populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.SUPER_USER_HOSPITAL_CODE); // Added by Pawan Kumar B N on 03-Jul-2012

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	/**
	 * get record for duplicate discharge printing by crno
	 */
	public List<DischargePrintingVO> getRecordForDuplicateDischargePrinting(String patCrnNo, UserVO userVO)
	{
		List<DischargePrintingVO> dischargePatList = new ArrayList<DischargePrintingVO>();
		DischargePrintingVO dischargePatientVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.FOR_DUPLICATE_DISCHARGE_PRINTING.HIPT_PATADMDISC_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), OpdConfig.PROFILE_TYPE_DISCHARGE);
		populateMAP.put(sq.next(), RegistrationConfig.PATIENT_STATUS_CODE_DEAD);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), patCrnNo);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Discharge Printing Found to Print.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				dischargePatientVO = new DischargePrintingVO();
				HelperMethods.populateVOfrmRS(dischargePatientVO, rs);
				dischargePatList.add(dischargePatientVO);
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

		return dischargePatList;
	}

	/**
	 * get record for duplicate discharge printing by crno
	 */
	public List getPatientProfileByCrNo(String patCrnNo, UserVO userVO)
	{
		List<PatientProfileDetailVO> patProfileDtlVOList = new ArrayList<PatientProfileDetailVO>();
		PatientProfileDetailVO patProfileVO = null;
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.PROFILE_BY_PUK.HPMRT_PAT_PROFILE_DTL";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		populateMAP.put(sq.next(), patCrnNo);
		populateMAP.put(sq.next(), OpdConfig.HPMRT_PAT_PROFILE_DTL_PROFILE_STATUS_GENERATED);
		populateMAP.put(sq.next(), userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Profile Found.");
			}
			rs.beforeFirst();
			while (rs.next())
			{
				patProfileVO = new PatientProfileDetailVO();
				HelperMethods.populateVOfrmRS(patProfileVO, rs);
				patProfileDtlVOList.add(patProfileVO);
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

		return patProfileDtlVOList;
	}

	public List searchInsuranceDetail(String _firstName, String _middleName, String _lastName, String companyId, String crNo, String policyNo,
			UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		// String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.PAT_MLC_DTL.HRGT_PATIENT_MLC_DTL";
		try
		{
			// query = HelperMethodsDAO.getQuery(filename, queryKey);
			query = " select (select HTRSTR_COMPANY_NAME from HTRT_COMPANY_MST x where  x.GNUM_HOSPITAL_CODE=a.GNUM_HOSPITAL_CODE "
					+ " and x.GNUM_ISVALID=a.GNUM_ISVALID and x.HTRNUM_COMPANY_ID=a.HTRNUM_COMPANY_ID) as companyName,"
					+ " decode(HPMRNUM_STATUS,1,'Receive',2,'Filled',3,'Send') as status,HPMRSTR_POLICY_NO as policyNo,a.HRGNUM_PUK as patCrNo, a.HRGNUM_ADMISSION_NO as patAdmNo, "
					+ " INITCAP (c.hrgstr_fname)|| ' ' || INITCAP (c.hrgstr_mname) || ' ' || INITCAP (c.hrgstr_lname) AS patName, "
					+ " (SELECT gstr_gender_name FROM gblt_gender_mst x WHERE x.gnum_isvalid = c.gnum_isvalid AND x.gnum_hospital_code "
					+ " = c.gnum_hospital_code AND x.gnum_gender_code = c.gnum_gender_code) AS patGender, "
					+ " ahis_util.dob_age_on (c.hrgdt_dob, SYSDATE) patAge,TO_CHAR (b.hipdt_admdatetime, 'dd-Mon-yyyy') AS admDate, "
					+ " (SELECT hgstr_unitname FROM hgbt_unit_mst x WHERE b.gnum_deptunit_code = x.hgnum_deptunitcode AND b.gnum_hospital_code = "
					+ " x.gnum_hospital_code AND b.gnum_isvalid = x.gnum_isvalid) AS unitName, "
					+ " (SELECT gstr_dept_name FROM gblt_department_mst y WHERE SUBSTR (b.gnum_deptunit_code, 1, 3) = y.gnum_dept_code "
					+ " AND b.gnum_hospital_code = y.gnum_hospital_code AND b.gnum_isvalid = y.gnum_isvalid) AS deptName, "
					+ " TO_CHAR (hipdt_disdatetime, 'dd-Mon-yyyy') AS dischargeDate,TO_CHAR (HPMRDT_RECEIVE_DATETIME, 'dd-Mon-yyyy hh24:mi') AS receiveDateTime,HPMRSTR_CID_NO as CIDNo "
					+ " from HPMRT_INSURANCE_DTL a,hipt_patadmission_dtl b,hrgt_patient_dtl c "
					+ " where a.gnum_isvalid = b.gnum_isvalid AND a.gnum_hospital_code = b.gnum_hospital_code and "
					+ " a.HRGNUM_ADMISSION_NO=b.HIPNUM_ADMNO and b.gnum_isvalid = c.gnum_isvalid AND "
					+ " b.gnum_hospital_code = c.gnum_hospital_code and b.HRGNUM_PUK=c.HRGNUM_PUK and a.GNUM_ISVALID=? and a.GNUM_HOSPITAL_CODE=? ";

			if (_firstName != null && !_firstName.equals(""))
			{
				query = query + " and upper(c.HRGSTR_FNAME) LIKE ('#1') ";
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query + " and upper(c.HRGSTR_MNAME) LIKE ('#2') ";
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query + " and upper(c.HRGSTR_LNAME) LIKE ('#3') ";
			}
			if (companyId != null && !companyId.equals("-1"))
			{
				query = query + " and a.HTRNUM_COMPANY_ID LIKE ('#4') ";
			}
			if (crNo != null && !crNo.equals(""))
			{
				query = query + " and a.HRGNUM_PUK = '#5' ";
			}
			if (policyNo != null && !policyNo.equals(""))
			{
				query = query + " and a.HPMRSTR_POLICY_NO = '#6' ";
			}
			query = query + "order by patName";

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			if (_firstName != null && !_firstName.equals(""))
			{
				query = query.replace("#1", _firstName + "%");
			}
			if (_middleName != null && !_middleName.equals(""))
			{
				query = query.replace("#2", _middleName + "%");
			}
			if (_lastName != null && !_lastName.equals(""))
			{
				query = query.replace("#3", _lastName + "%");
			}
			if (companyId != null && !companyId.equals("-1"))
			{
				query = query.replace("#4", companyId + "%");
			}
			if (crNo != null && !crNo.equals(""))
			{
				query = query.replace("#5", crNo);
			}
			if (policyNo != null && !policyNo.equals(""))
			{
				query = query.replace("#6", policyNo);
			}

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getHospitalCode());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		List lstInsuranceDtl = new ArrayList();
		ValueObject[] valueObjects = null;
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(InsuranceDetailVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstInsuranceDtl.add((InsuranceDetailVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{

			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		return lstInsuranceDtl;
	}

	public List getRequestedRecordId(String empId, UserVO userVO)
	{
		ResultSet rs = null;
		List alRecord = new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_ALL_REQUESTED_RECORD_ID_BY_USER";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), empId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_REQUEST_STATUS_RAISED);
			populateMAP.put(sq.next(), MrdConfig.MRD_RECORD_REQUEST_STATUS_IN_PROCESS);
			populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_RAISED);
			populateMAP.put(sq.next(), MrdConfig.REQUESTED_RECORD_STATUS_ACCEPTED_AT_DEPT);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				alRecord = new ArrayList();
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	/* ***************************Function for EMR******************************************************** */

	public List getDepartmentUnitForEmr(UserVO userVO)
	{
		ResultSet rs = null;
		List alRecord = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.DEPARMENT_UNIT_BY_USER_ROLE.HGBT_UNIT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), userVO.getSeatId());

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Department Unit Found");
			}
			if (rs.next())
			{
				alRecord = new ArrayList();
			}

		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		try
		{
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	public int getEmrRestrictedCategory(String patCategory, UserVO userVO)
	{
		ResultSet rs = null;
		// List alRecord = new ArrayList();
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.COUNT.HPMRT_EPR_RESTRICTED_CAT";
		int count = 0;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{

			populateMAP.put(sq.next(), patCategory);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);

		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				count = rs.getInt(1);
			}

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return count;
	}

	public List getAllDeptUnitList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		// String queryKey = "ESSENTIAL.HOPT_DEPT_UNIT_ROSTER_DTL.RETRIEVE_UNITS_BY_SEATID";
		String queryKey = "SELECT.ALL_DEPT_UNIT.HGBT_UNIT_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), Config.IS_VALID_INACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// get the list of the epr tab
	public List getEprTabList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.ALL.HPMRT_EPR_TAB_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);// userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Tab Found");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// get the list of the userid & username
	public List getUserIdList(UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.USER.GBLT_USER_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Unit Alloted For This Seat ID");
			}

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
			throw new HisDataAccessException("GenderDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	// get patient admission detai by crno
	public List<PatAdmissionDtlVO> getPatientAdmissionDetailByCrNo(String patCrNo, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "SELECT.BY_CRNO.HIPT_PATADMISSION_DTL";
		List<PatAdmissionDtlVO> patientAdmissionDtlVOList = new ArrayList<PatAdmissionDtlVO>();
		PatAdmissionDtlVO patAdmissionDtlVO = null;
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		System.out.println("query" + query);
		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), patCrNo);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("EssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			while (rs.next())
			{
				patAdmissionDtlVO = new PatAdmissionDtlVO();
				HelperMethods.populateVOfrmRS(patAdmissionDtlVO, rs);
				patientAdmissionDtlVOList.add(patAdmissionDtlVO);
			}

		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}

		return patientAdmissionDtlVOList;
	}

	public List getAllDesignations(UserVO _userVO)
	{
		List designationList = new ArrayList();
		Map _populateMap = new HashMap();
		String query = "";
		String queryKey = "";
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		queryKey = "SELECT.DESIGNATION_NAME.GBLT_DEPARTMENT_MST";
		// List deparmentList=new ArrayList();
		Connection conn = super.getTransactionContext().getConnection();
		Sequence sq = new Sequence();
		_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		_populateMap.put(sq.next(), _userVO.getHospitalCode());

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, query, _populateMap);
			if (rs.next())
			{
				designationList = HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EnquiryDAO: :: " + e);
		}
		return designationList;
	}

//modified by:NehaRajgariya Date:9march2017 Purpose:-UAT changes                    											 
	public StaffDetailVO[] searchStaffDetail(HashMap staffDetailMap, UserVO _userVO)

	{
		StaffDetailVO[] staffEnquiryVOs = null;
		Map _populateMap = new HashMap();
		ValueObject[] valueObjects = null;
		String query = "";
		String queryKey = "";
		String whereCOndition = "";
		Connection conn = super.getTransactionContext().getConnection();
		String finalQuery = "";
		String orderby = "group by  str_emp_full_name,str_emp_no";
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		if (Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED.equals(Config.LOCATION_MAPPING_WITH_ESTATE_REQUIRED_YES))
		{
			queryKey = "SEARCH_STAFF_DETAIL.PIST_EMP_PERSONNEL_DTL.ESTATE_MAPPING_REQ";
			/*
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			*/
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		else
		{
			queryKey = "SEARCH_STAFF_DETAIL.PIST_EMP_PERSONNEL_DTL";

			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
			_populateMap.put(sq.next(), _userVO.getHospitalCode());
		}
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
			System.out.println("Query " + query);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		Set keySet = (Set) staffDetailMap.keySet();

		Iterator keySetItr = keySet.iterator();

		while (keySetItr.hasNext())
		{
			String mapKey = (String) keySetItr.next();
			String mapValue = (String) staffDetailMap.get(mapKey);
			if (mapKey.equals("str_emp_full_name"))
			{
				if (!mapValue.equals(" ")) whereCOndition = whereCOndition + " AND " + "( UPPER (" + mapKey + ") LIKE UPPER ('%" + mapValue + "%'))";
			}
			if (mapKey.equals("str_emp_no"))
			{
				if (!mapValue.equals(" ")) whereCOndition = whereCOndition + " AND " + "( UPPER (" + mapKey + ") LIKE UPPER ('" + "%" +mapValue + "%'))";
			}
			if (mapKey.equals("str_old_emp_no"))
			{
				if (!mapValue.equals(" ")) whereCOndition = whereCOndition + " AND " + "( UPPER (" + mapKey + ") LIKE UPPER ('" + "%" +mapValue + "%'))";
			}
			
			if (mapKey.equals("gnum_desig_code"))
			{
				if (!mapValue.equals("%")) whereCOndition = whereCOndition + " AND " + "(UPPER (" + mapKey + ") LIKE UPPER ('" + mapValue + "'))";
			}
			if (mapKey.equals("gnum_dept_code"))
			{
				if (!mapValue.equals("%")) whereCOndition = whereCOndition + " AND " + "(UPPER (" + mapKey + ") LIKE UPPER ('" + mapValue + "'))";
			}

			if (mapKey.equals("str_gender_name"))
			{
				if (!mapValue.equals("%")) whereCOndition = whereCOndition + " AND " + "(UPPER (" + mapKey
						+ ")=(select upper(GSTR_GENDER_SHORT) from GBLT_GENDER_MST where GNUM_GENDER_CODE  like ('" + mapValue + "')))";
			}

		}

		System.out.println("query" + query);
		finalQuery = query + " " + whereCOndition + " " + orderby;
		System.out.println("final query === " + finalQuery);
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(conn, finalQuery, _populateMap);
			if (rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(StaffDetailVO.class, rs);
				staffEnquiryVOs = new StaffDetailVO[valueObjects.length];
				for (int i = 0; i < valueObjects.length; i++)
				{
					staffEnquiryVOs[i] = (StaffDetailVO) valueObjects[i];
				}
			}
			else
			{
				throw new HisRecordNotFoundException("No Staff Record Found");
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("EnquiryDAO:Staff Detail :: " + e);
		}
		return staffEnquiryVOs;
	}

	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HIPT_DUWRBED_MST.WARD";
		//first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _hosCode);
		populateMAP.put(sq.next(), _deptCode);
		populateMAP.put(sq.next(), _deptUnitCode);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		System.out.println("inside getWardBasedOnDepartment()...." + alRecord);
		return alRecord;

	}
	
	public List getRoomBasedOnHospitalDeptUnitWard(String _hosCode,String _deptUnitCode,String _wardCode)
	{
		List alRecord = new ArrayList();
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.HIPT_DUWRBED_MST.ROOM";
		//first call the getQueryMethod with arguments filename,querykey from prop file    		 	  
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), _hosCode);
		populateMAP.put(sq.next(), _deptUnitCode);
		populateMAP.put(sq.next(), _wardCode);
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("Record against specified Department Not Found");
			}
			else rs.beforeFirst();
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}

		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException();
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		System.out.println("inside getWardBasedOnDepartment()...." + alRecord);
		return alRecord;

	}

	public List getLaboratoryAsPerDepartment(UserVO _userVO,String departmentCode) {
		
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.GBLT_LABORATORY_MST.LABORATORY";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), departmentCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Laboratory Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}

	/**
	 * Retrieves all consultants of a multiple hospital.
	 * @param	_userVO	provides user details
	 * @return	List of the departments.
	 */
	public List getAllConsultant(UserVO _userVO)
	{
		//_userVO.get seatid to be obtained from userVO

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "ESSENTIAL.ALL_CONSULTANT.HGBT_UNIT_CONSULTANT_MST";

		//first call the getQueryMethod with arguments filename,querykey from prop file
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		List alRecord = new ArrayList();
		try
		{
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Consultant Found");
			}
			else rs.beforeFirst();
			
			alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.executeQuery(" + e);
		}
		
		return alRecord;
	}
	
	public void getdeptComboDetails(ReportVO voObj) {

		HisDAO daoObj = null;
		WebRowSet ws = null;

		String strProcName = "{call Pkg_Ipd_Rpt.RPT_GBLT_DEPARTMENT_MST(?,?,?,?,?)}";
		int nProcIndex = 0;

		String strErr = "";

		try {

			daoObj = new HisDAO("MRD Reports","EssentialDAO");
			
			
			
			nProcIndex = daoObj.setProcedure(strProcName);
			daoObj.setProcInValue(nProcIndex, "modeVal", "1", 1);
			daoObj.setProcInValue(nProcIndex, "seat_id", voObj.getStrSeatId(),2);
			daoObj.setProcInValue(nProcIndex, "hosp_code", voObj.getHospitalCode(),3);
			daoObj.setProcOutValue(nProcIndex, "err", 1,4);
			daoObj.setProcOutValue(nProcIndex, "RESULTSET", 2, 5);
			daoObj.executeProcedureByPosition(nProcIndex);

			strErr = daoObj.getString(nProcIndex, "err");

			if (strErr == null)
				strErr = "";

			if (strErr.equals("")) {

				ws = daoObj.getWebRowSet(nProcIndex, "RESULTSET");
		
				voObj.setDeptWs(ws.toString());
				
				
			} else {
				throw new Exception(strErr);
			}

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			if (daoObj != null) 
				daoObj.free();
				daoObj = null;
			
		}

	}
	
	// Generate MRD Certificate IDs
	public String generateCertificateId(String certificateType, String deptUnitCode, String genMode, UserVO userVO)
	{
		String count = "";
		ResultSet rs = null;
		String errorMsg="";
		String certificateId;	
		try
		{
			Procedure strProc=new Procedure(MrdConfig.PROC_GENERATE_CERTIFICATE_ID);
			strProc.addInParameter(1,Types.VARCHAR,userVO.getHospitalCode());
			strProc.addInParameter(2,Types.VARCHAR,certificateType);
			strProc.addInParameter(3,Types.VARCHAR,deptUnitCode);
		    strProc.addInParameter(4,Types.VARCHAR,genMode);
		    strProc.addInParameter(5,Types.VARCHAR,""); 
			strProc.setReturnType(Types.VARCHAR);
			return	certificateId = (String)strProc.execute(super.getTransactionContext().getConnection());
		}
		catch(HisRecordNotFoundException e)
		{
			throw new HisRecordNotFoundException("No Record Found");
		}
	}
	
	//Added by Dheeraj on 05-Nov-2018
	public List getStaffMembers(String recordType, String rackId, UserVO userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_SHELF_BASED_ON_RACK.HPMRT_RACK_SHELF_MST";

		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}

		Sequence sq = new Sequence();
		try
		{
			populateMAP.put(sq.next(), MrdConfig.MRD_RACK_SHELF_STATUS_SPACE_AVAILABLE);
			populateMAP.put(sq.next(), rackId);
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), userVO.getHospitalCode());
			populateMAP.put(sq.next(), MrdConfig.RECORD_BOUNDING_MODE_SHELF_WISE);
			populateMAP.put(sq.next(), recordType);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MrdEssentialDAO.populateMAP::" + e);
		}

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException("No Shelf Found");
			}

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
			throw new HisDataAccessException("MrdEssentialDAO:HelperMethodsDAO.getAlOfEntryObjects(rs)" + e);
		}

		return alRecord;
	}

	
	//Added by Vasu on 06.March.2019
	public List<MrdIcdDtlVO> getPreviousIcdCodeDetails(PatientDetailVO patDtlVO, UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "SELECT.PREVIOUS_ICD_DIAGNOSIS_RECORDS.HPMRT_PAT_DIAGNOSIS_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), patDtlVO.getAddmissionNo());
		//populateMAP.put(sq.next(), _userVO.getHospitalCode());

		List<MrdIcdDtlVO> _previousICDRecordsVO = new ArrayList<MrdIcdDtlVO>();
		ValueObject vo[] = null;
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (rs.next())
			{
				rs.beforeFirst();
	
				vo = HelperMethods.populateVOfrmRS(MrdIcdDtlVO.class, rs);			
				for (ValueObject v : vo)
					_previousICDRecordsVO.add((MrdIcdDtlVO)v);
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
		return _previousICDRecordsVO;
	}

	//Added by Vasu on 07.March.2019
	public void deleteIcdIndexDetail(MrdIcdDtlVO lcdMrdVo, UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "UPDATE.PREVIOUS_ICD_DIAGNOSIS_RECORD.HPMRT_PAT_DIAGNOSIS_DTL";
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
			populateMAP.put(sq.next(), lcdMrdVo.getAddmissionNo());
			populateMAP.put(sq.next(), lcdMrdVo.getIcdCodeList());
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("MRDRecordDtlDAO.populateMAP::"+e);
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
	
	
	
//-------------------Bulletin Generation--------------------	
	public List getBulletinelist(UserVO userVO)
	{
		int nProcedureIndex;
		String strDBErr;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		HisDAO daoObj = new HisDAO("MRD","MrdEssentialDAO");
		try
		{
	
			
			nProcedureIndex = daoObj.setProcedure("{call pkg_bulletin_generation.proc_bulletin_type_list(?,?,?,?)}");
				daoObj.setProcInValue(nProcedureIndex, "p_seatId",userVO.getUserSeatId() ,1);
				daoObj.setProcInValue(nProcedureIndex, "p_hospcode", userVO.getHospitalCode(),2);
			
				daoObj.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
				daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

				// Executing Procedure 
				daoObj.executeProcedureByPosition(nProcedureIndex);
				// Getting out parameters 
				strDBErr = daoObj.getString(nProcedureIndex, "err");
				rs = daoObj.getWebRowSet(nProcedureIndex, "resultset");
				
				// If Database Error Occurs, No further processing is required. 
				if (strDBErr != null && !strDBErr.equals(""))
				{
					throw new Exception("Data Base Error:" + strDBErr);
				}
				
		}
		
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}
		List alRecord = new ArrayList();
		try
		{
			if (rs.next()) alRecord = HelperMethodsDAO.getAlOfEntryObjects(rs);
		}
		catch (Exception e)
		{
			throw new HisDataAccessException("HisDataAccessException  :getNotBulletins" + e);
		}
		return alRecord;
		
	}
	
	
	public List<BulletinHeadVO> getBulletinTemplatePrintList(BulletinGenerationVO bulletinVO,UserVO _userVO)

	{
		
		String query = "";
		Sequence sq = new Sequence();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		ResultSet rs = null;
		int nProcedureIndex;
		String strDBErr;
		HisDAO daoObj = new HisDAO("MRD","MrdEssentialDAO");
		try
		{
			
			nProcedureIndex = daoObj.setProcedure("{call pkg_bulletin_generation.proc_bulletin_detail_list(?,?,?,?)}");
				daoObj.setProcInValue(nProcedureIndex, "p_bulletinId",bulletinVO.getBulletinId() ,1);
				daoObj.setProcInValue(nProcedureIndex, "p_hospcode", _userVO.getHospitalCode(),2);
			
				daoObj.setProcOutValue(nProcedureIndex, "err", 1,3); // varchar
				daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,4); // Cursor

				// Executing Procedure 
				daoObj.executeProcedureByPosition(nProcedureIndex);
				// Getting out parameters 
				strDBErr = daoObj.getString(nProcedureIndex, "err");
				rs = daoObj.getWebRowSet(nProcedureIndex, "resultset");
			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}
		List<BulletinHeadVO> lstDetailVO = new ArrayList<BulletinHeadVO>();
		ValueObject[] valueObjects = null;
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(BulletinHeadVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstDetailVO.add((BulletinHeadVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			if (e.getClass() == HisRecordNotFoundException.class)
			{
				throw new HisRecordNotFoundException(e.getMessage());
			}
			else throw new HisDataAccessException("Exception at quey end :: " + e);
		}
		return lstDetailVO;
	}

	
	public List<BulletinHeadDataVO> getDatafromQuery(String query, UserVO voUser)
	{
		ResultSet rs = null;
		Map populateMAP = new HashMap();
		List<BulletinHeadVO> lstdatafromquery= new ArrayList<BulletinHeadVO>();
		
		System.out.println("   -------> query :: " + query);
		Sequence sq = new Sequence();
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

		List<BulletinHeadDataVO> lstDetailVO = new ArrayList<BulletinHeadDataVO>();
		ValueObject[] valueObjects = null;
		try
		{
			if(rs.next())
			{
				rs.beforeFirst();
				valueObjects = HelperMethods.populateVOfrmRS(BulletinHeadDataVO.class, rs);
				for (int i = 0; i < valueObjects.length; i++)
					lstDetailVO.add((BulletinHeadDataVO) valueObjects[i]);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return lstDetailVO;
	}

	
	public void insertHtmlData(String hmode,BulletinGenerationVO bulletinVO)
	{
		String query = "";
		Sequence sq = new Sequence();
		Connection conn = super.getTransactionContext().getConnection();    
		String errorMsg = "";
		ResultSet rs = null;
		int nProcedureIndex;
		String strDBErr;
		HisDAO daoObj = new HisDAO("MRD","MrdEssentialDAO");
		try
		{
			
			nProcedureIndex = daoObj.setProcedure("{call pkg_bulletin_generation.proc_insertHTMLData(?,?,?,?,?,?,?,?,?)}");
				daoObj.setProcInValue(nProcedureIndex, "p_hmode",hmode,1);
				daoObj.setProcInValue(nProcedureIndex, "p_bulletinId",bulletinVO.getBulletinId(),2);
				daoObj.setProcInValue(nProcedureIndex, "p_hospcode",bulletinVO.getHospitalCode(),3);
				daoObj.setProcInValue(nProcedureIndex, "p_headingName",bulletinVO.getBulletinId(),4);
				daoObj.setProcInValue(nProcedureIndex, "p_duration",bulletinVO.getDuration(),5);
				daoObj.setProcInValue(nProcedureIndex, "p_durationtype",bulletinVO.getDuration(),6);
				daoObj.setProcInValue(nProcedureIndex, "p_HTMLData",bulletinVO.getHtmlPreview(),7);
				daoObj.setProcInValue(nProcedureIndex, "p_bulletinDate",bulletinVO.getBulletinDate(),8);
				daoObj.setProcOutValue(nProcedureIndex, "err", 1,9); // varchar
				//daoObj.setProcOutValue(nProcedureIndex, "resultset", 2,10); // Cursor

				// Executing Procedure 
				daoObj.executeProcedureByPosition(nProcedureIndex);
				// Getting out parameters 
				strDBErr = daoObj.getString(nProcedureIndex, "err");
			

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}
		finally {
			if (daoObj != null) {
				daoObj.free();
				
			}
			daoObj = null;
		}
	}

	public boolean getEssentialsForBulletin(BulletinHeadVO headVO, BulletinGenerationVO bulletinVO)
	{		boolean isExists = false;
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		try
		{

			query = "select gnum_bulletin_id,gdt_bulletin_date,gnum_duration_type,gnum_bulletin_gen_sno from hbut_bulletin_dtl where gnum_bulletin_id="+bulletinVO.getBulletinId()+" and gdt_bulletin_date="+bulletinVO.getBulletinDate()+" and gnum_hospital_code="+bulletinVO.getHospitalCode()+"and gstr_bulletin_name="+headVO.getHeadingName()+"and gnum_isvalid=1;";
			System.out.println("query for check data "+query);
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			
			if (!rs.next())
			{
				isExists = true;
				throw new HisRecordNotFoundException();
			}
		}
		
		
		
		catch(Exception e)
		{
			isExists= true;
			e.printStackTrace();
		}

		return isExists;
	}

	
	
	
}// end class

