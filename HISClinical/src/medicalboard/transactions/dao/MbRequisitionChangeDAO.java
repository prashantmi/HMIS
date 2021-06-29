package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.MedicalBoardRequisitionChangeVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;



public class MbRequisitionChangeDAO extends DataAccessObject{

	public MbRequisitionChangeDAO(TransactionContext _tx) {
		super(_tx);
	}

	public void create(MedicalBoardRequisitionChangeVO requisitionChangeVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_ESSENTIALDAO;
		String queryKey="INSERT.HMBT_REQ_CHANGE_DTL";
		Sequence sq = new Sequence();
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
			populateMap.put(sq.next(), requisitionChangeVO.getReqID());
			populateMap.put(sq.next(), requisitionChangeVO.getReqID());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), requisitionChangeVO.getOldExamDate());
			populateMap.put(sq.next(), requisitionChangeVO.getNewExamDate());
			populateMap.put(sq.next(), requisitionChangeVO.getChangeReason());
			populateMap.put(sq.next(), requisitionChangeVO.getApprovedDate());
			populateMap.put(sq.next(), requisitionChangeVO.getApprovedBy());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), userVO.getSeatId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			populateMap.put(sq.next(), requisitionChangeVO.getReqMode());
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("MbRequisitionChangeDAO.populateMAP::" + e);
		}
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMap);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}

	
}
