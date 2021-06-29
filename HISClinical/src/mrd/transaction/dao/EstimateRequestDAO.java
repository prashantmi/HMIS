package mrd.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import mrd.MrdConfig;
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
import mrd.vo.EstimateCertificateReqVO;
import hisglobal.vo.UserVO;

public class EstimateRequestDAO extends DataAccessObject {

	public EstimateRequestDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	public List<EstimateCertificateReqVO> getEstimateReqTariffDtl(String deptUnitCode,  UserVO strUserVO)
	{
		EstimateCertificateReqVO estimateCertificateReqDtlVO=null;
		List<EstimateCertificateReqVO> estimateCertificateReqVOlist=new ArrayList<EstimateCertificateReqVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET.ESTIMATE_TARIFF_DTL_HBLT_TARIFF_MST";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), deptUnitCode);
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), strUserVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					estimateCertificateReqDtlVO=new EstimateCertificateReqVO();
					HelperMethods.populateVOfrmRS(estimateCertificateReqDtlVO, rs);
					estimateCertificateReqVOlist.add(estimateCertificateReqDtlVO);
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
		return estimateCertificateReqVOlist;
	}
	public List<EstimateCertificateReqVO> getEstimateReqTariffDtlMst(UserVO strUserVO)
	{
		EstimateCertificateReqVO estimateCertificateReqDtlVO=null;
		List<EstimateCertificateReqVO> estimateCertificateReqVOlist=new ArrayList<EstimateCertificateReqVO>();
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_ESSENTIALDAO;
		String queryKey = "GET.ESTIMATE_TARIFF_DTL_HBLT_TARIFF_MST_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), strUserVO.getHospitalCode());
		populateMAP.put(sq.next(), MrdConfig.HBLNUM_IS_ESTIMATION_STATUS);
			
		try
		{
			ResultSet rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if(rs.next()){
				rs.beforeFirst();
				while(rs.next()){
					estimateCertificateReqDtlVO=new EstimateCertificateReqVO();
					HelperMethods.populateVOfrmRS(estimateCertificateReqDtlVO, rs);
					estimateCertificateReqVOlist.add(estimateCertificateReqDtlVO);
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
		return estimateCertificateReqVOlist;
	}
	
	public List getEstimateReqAdvisedBy(String deptUnitCode,  UserVO strUserVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PEON_LIST);
			strProc.addInParameter(1, Types.VARCHAR, strUserVO.getHospitalCode());
			strProc.addInParameter(2, Types.VARCHAR, MrdConfig.MEDICAL_CERTIFICATE_REQUEST);
			strProc.addInParameter(3, Types.VARCHAR, deptUnitCode);
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
	
	public void create(String pmode,String certificateId,String id,EstimateCertificateReqVO estReqVO, UserVO userVO)
	{
		String errorMsg = null;
		try
		{
			
					
					Procedure strProc = new Procedure(MrdConfig.PROCEDURE_ESTIMATE_CERTIFICATE_REQUEST);
					strProc.addInParameter(1, Types.VARCHAR, pmode);
					strProc.addInParameter(2, Types.VARCHAR, estReqVO.getDepartmentUnitCode());
					strProc.addInParameter(3, Types.VARCHAR, estReqVO.getStrCategoryCode());
					strProc.addInParameter(4, Types.VARCHAR, (estReqVO.getWardCode()==null) ? "0" : estReqVO.getWardCode() );
					strProc.addInParameter(5, Types.VARCHAR, userVO.getSeatId());
					strProc.addInParameter(6, Types.VARCHAR, id.substring(0, 3));
					strProc.addInParameter(7, Types.VARCHAR, certificateId);
					strProc.addInParameter(8, Types.VARCHAR, userVO.getHospitalCode());
					strProc.addInParameter(9, Types.VARCHAR, estReqVO.getPatCrNo());
					strProc.addInParameter(10, Types.VARCHAR, estReqVO.getEpisodeCode());
					strProc.addInParameter(11, Types.VARCHAR, estReqVO.getEpisodeVisitNo());
					strProc.addInParameter(12, Types.VARCHAR, (estReqVO.getAdmissionNo()==null) ? "0" : estReqVO.getAdmissionNo() );
					strProc.addInParameter(13, Types.VARCHAR, MrdConfig.ESTIMATE_CERTIFICATE_REQUEST_STATUS);
					strProc.addInParameter(14, Types.VARCHAR, id);
					strProc.addInParameter(15, Types.VARCHAR, estReqVO.getAdvisedBy());
					strProc.addInParameter(16, Types.VARCHAR,estReqVO.getRemarks() );
					strProc.addInParameter(17, Types.VARCHAR,Config.IS_VALID_ACTIVE );
					strProc.execute(super.getTransactionContext().getConnection())	;
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}

	}
	
	
	
	
	public List getPrevEstimateReqDtl(String pmode,EstimateCertificateReqVO estReqDtlVO,  UserVO strUserVO)
	{
		String errorMsg = "";
		ResultSet rs = null;
		Connection conn = super.getTransactionContext().getConnection();
		try
		{
			Procedure strProc = new Procedure(MrdConfig.PROCEDURE_GET_PREV_REQUEST_DTL);
			strProc.addInParameter(1, Types.VARCHAR,pmode);
			strProc.addInParameter(2, Types.VARCHAR, strUserVO.getHospitalCode());
			strProc.addInParameter(3, Types.VARCHAR, Config.IS_VALID_ACTIVE);
			strProc.addInParameter(4, Types.VARCHAR, estReqDtlVO.getEpisodeCode());
			strProc.addInParameter(5, Types.VARCHAR, estReqDtlVO.getEpisodeVisitNo());
			strProc.addInParameter(6, Types.VARCHAR, estReqDtlVO.getPatCrNo());
			strProc.addOutParameter(7, Types.VARCHAR);
			strProc.addOutParameter(8, Types.REF);			
			strProc.execute(conn);
			errorMsg = (String) strProc.getParameterAt(7);
			rs = (ResultSet) strProc.getParameterAt(8);

		}
		catch (Exception e)
		{
			throw new HisDataAccessException((errorMsg != null ? "" : errorMsg) + e);
		}		List prevRecord = new ArrayList();

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
	
	

}
