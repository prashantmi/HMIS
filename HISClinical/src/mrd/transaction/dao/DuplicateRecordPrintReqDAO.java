package mrd.transaction.dao;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mrd.MrdConfig;
import mrd.vo.DupRecPrintReqVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.Procedure;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class DuplicateRecordPrintReqDAO extends DataAccessObject {

	public DuplicateRecordPrintReqDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}

	public List getRecordType(UserVO _userVO)
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
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		System.out.println("query" + query);
		Sequence sq = new Sequence();
		populateMAP.put(sq.next(), Config.SUPER_HOSPITAL_CODE);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					"No Record Found  ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public List getEmpList(UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET_EMP_LIST.PIST_EMP_REGISTRATION_DTL";
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
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.PROCESS_ID_FOR_DUPLICATE_RECORD_PRINTING);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) throw new HisRecordNotFoundException(
					" ");
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

			throw new HisDataAccessException("HisDataAccessException  :getIcdGroupList" + e);
		}

		return alRecord;
	}
	
	public void saveDuplicateRecordPrintReqDtl(DupRecPrintReqVO dupRecPrintReqVO,String certificateId,UserVO userVO)
	{
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_DUPLICATE_RECORD_REQUEST);
					if(dupRecPrintReqVO.getPatCrNo().equals(""))
					{
						strProc.addInParameter(1, Types.VARCHAR,"1");
					}
					else
						strProc.addInParameter(1, Types.VARCHAR,dupRecPrintReqVO.getPatCrNo() );	
					strProc.addInParameter(2, Types.VARCHAR,"" );
					strProc.addInParameter(3, Types.VARCHAR,certificateId);
					strProc.addInParameter(4, Types.VARCHAR,dupRecPrintReqVO.getReqType() );
					strProc.addInParameter(5, Types.VARCHAR,dupRecPrintReqVO.getPatName() );
					strProc.addInParameter(6, Types.VARCHAR,MrdConfig.REQUESTED );
					strProc.addInParameter(7, Types.VARCHAR,dupRecPrintReqVO.getPatGenderCode() );
					strProc.addInParameter(8, Types.VARCHAR,dupRecPrintReqVO.getPatAge());
					strProc.addInParameter(9, Types.VARCHAR,dupRecPrintReqVO.getPatientAddress() );
					strProc.addInParameter(10, Types.VARCHAR,dupRecPrintReqVO.getPatContactNo() );
					strProc.addInParameter(11, Types.VARCHAR,dupRecPrintReqVO.getRecordType() );
					strProc.addInParameter(12, Types.VARCHAR,Config.IS_VALID_ACTIVE );
					strProc.addInParameter(13, Types.VARCHAR,dupRecPrintReqVO.getRecordDesc() );
					strProc.addInParameter(14, Types.VARCHAR,dupRecPrintReqVO.getReqReason() );
					strProc.addInParameter(15, Types.VARCHAR,userVO.getSeatId() );
					strProc.addInParameter(16, Types.VARCHAR,dupRecPrintReqVO.getRequestBy() );
					strProc.addInParameter(17, Types.VARCHAR,userVO.getHospitalCode() );
					strProc.addInParameter(18, Types.VARCHAR,dupRecPrintReqVO.getRequestByName() );
					strProc.addInParameter(19, Types.VARCHAR,dupRecPrintReqVO.getRequestedByAddress());
					strProc.addInParameter(20, Types.VARCHAR,dupRecPrintReqVO.getRequestedByRel());
					strProc.addInParameter(21, Types.VARCHAR,dupRecPrintReqVO.getRequestedByContact());
					strProc.addInParameter(22, Types.VARCHAR,dupRecPrintReqVO.getApprovedBy());
					strProc.addInParameter(23, Types.VARCHAR,dupRecPrintReqVO.getRemarks());
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_BIRTH_NOTIFICATION))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_BIRTH_NOTIFICATION);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_DEATH_NOTIFICATION))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_DEATH_NOTIFICATION);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_GENERAL_CASESHEET);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_ESTIMATE_CERTIFICATE);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_FITNESS))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_FITNESS);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_MLC_CASESHEET))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_MLC_CASESHEET);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_MEDICAL))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_MEDICAL);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_OPD_FILE))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_OPD_FILE);
					}
					strProc.addOutParameter(25, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
	
	
	
	public void updateDuplicateRecordPrintReqDtl(DupRecPrintReqVO dupRecPrintReqVO,String certificateId,UserVO userVO)
	{
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_DUPLICATE_RECORD_REQUEST_HANDOVER);
					if(dupRecPrintReqVO.getPatCrNo().equals(""))
					{
						strProc.addInParameter(1, Types.VARCHAR,"1");
					}
					else
					strProc.addInParameter(1, Types.VARCHAR,dupRecPrintReqVO.getReqNo());	
					strProc.addInParameter(2, Types.VARCHAR,dupRecPrintReqVO.getBillNo());
					strProc.addInParameter(3, Types.VARCHAR,dupRecPrintReqVO.getHandoverTo());
					strProc.addInParameter(4, Types.VARCHAR,dupRecPrintReqVO.getHandoverToName());
					strProc.addInParameter(5, Types.VARCHAR,dupRecPrintReqVO.getHandoverToRel());
					strProc.addInParameter(6, Types.VARCHAR,dupRecPrintReqVO.getHandoverToAddress());
					strProc.addInParameter(7, Types.VARCHAR,dupRecPrintReqVO.getHandoverToContact());
					strProc.addInParameter(8, Types.VARCHAR,userVO.getUserId());
					strProc.addInParameter(9, Types.VARCHAR,userVO.getHospitalCode());
					
					
					/*strProc.addInParameter(10, Types.VARCHAR,dupRecPrintReqVO.getRecordType());		// Teriff ID
					strProc.addInParameter(11, Types.VARCHAR,"1");*/									// Quantity
					strProc.addOutParameter(10, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection());
			
		}

/*	public void updateDuplicateRecordPrintReqDtl(DupRecPrintReqVO dupRecPrintReqVO,String certificateId,UserVO userVO)
	{
		String errorMsg = null;
		String recordStatus=MrdConfig.CERTIFICATE_DISPATCH_RECORD_STATUS_SEND_HANDOVER;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_DUPLICATE_RECORD_REQUEST_HANDOVER);
					if(dupRecPrintReqVO.getPatCrNo().equals(""))
					{
						strProc.addInParameter(1, Types.VARCHAR,"1");
					}
					else
					strProc.addInParameter(1, Types.VARCHAR,dupRecPrintReqVO.getPatCrNo() );	
					strProc.addInParameter(2, Types.VARCHAR,"" );
					strProc.addInParameter(3, Types.VARCHAR,certificateId);
					strProc.addInParameter(4, Types.VARCHAR,dupRecPrintReqVO.getReqType() );
					strProc.addInParameter(5, Types.VARCHAR,dupRecPrintReqVO.getPatName() );
					strProc.addInParameter(6, Types.VARCHAR,MrdConfig.HANDED_OVER );
					strProc.addInParameter(7, Types.VARCHAR,dupRecPrintReqVO.getPatGenderCode() );
					strProc.addInParameter(8, Types.VARCHAR,dupRecPrintReqVO.getPatAge());
					strProc.addInParameter(9, Types.VARCHAR,dupRecPrintReqVO.getPatientAddress() );
					strProc.addInParameter(10, Types.VARCHAR,dupRecPrintReqVO.getPatContactNo() );
					strProc.addInParameter(11, Types.VARCHAR,dupRecPrintReqVO.getRecordType() );
					strProc.addInParameter(12, Types.VARCHAR,Config.IS_VALID_ACTIVE );
					strProc.addInParameter(13, Types.VARCHAR,dupRecPrintReqVO.getRecordDesc() );
					strProc.addInParameter(14, Types.VARCHAR,dupRecPrintReqVO.getReqReason() );
					strProc.addInParameter(15, Types.VARCHAR,userVO.getSeatId() );
					strProc.addInParameter(16, Types.VARCHAR,dupRecPrintReqVO.getRequestBy() );
					strProc.addInParameter(17, Types.VARCHAR,userVO.getHospitalCode() );
					strProc.addInParameter(18, Types.VARCHAR,dupRecPrintReqVO.getRequestByName() );
					strProc.addInParameter(19, Types.VARCHAR,dupRecPrintReqVO.getRequestedByAddress());
					strProc.addInParameter(20, Types.VARCHAR,dupRecPrintReqVO.getRequestedByRel());
					strProc.addInParameter(21, Types.VARCHAR,dupRecPrintReqVO.getRequestedByContact());
					strProc.addInParameter(22, Types.VARCHAR,dupRecPrintReqVO.getApprovedBy());
					strProc.addInParameter(23, Types.VARCHAR,dupRecPrintReqVO.getRemarks());
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_BIRTH_NOTIFICATION))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_BIRTH_NOTIFICATION);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_DEATH_NOTIFICATION))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_DEATH_NOTIFICATION);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_GENERAL_CASESHEET))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_GENERAL_CASESHEET);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_ESTIMATE_CERTIFICATE))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_ESTIMATE_CERTIFICATE);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_FITNESS))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_FITNESS);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_MLC_CASESHEET))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_MLC_CASESHEET);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_MEDICAL))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_MEDICAL);
					}
					if(dupRecPrintReqVO.getRecordType().equals(MrdConfig.RECORD_TYPE_OPD_FILE))
					{
						strProc.addInParameter(24, Types.VARCHAR,MrdConfig.TARRIF_ID_OPD_FILE);
					}
					strProc.addOutParameter(25, Types.VARCHAR);
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}*/
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
}
