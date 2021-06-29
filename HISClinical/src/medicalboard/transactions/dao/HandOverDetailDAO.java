package medicalboard.transactions.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.HandOverDetailVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import medicalboard.MedicalBoardConfig;

public class HandOverDetailDAO extends DataAccessObject implements HandOverDetailDAOi
{
	public HandOverDetailDAO(TransactionContext _tx) {
		super(_tx);
	}
	
	public void create(HandOverDetailVO handOverDetailVO, UserVO userVO)
	{
		String query="";
		Map populateMap= new HashMap();
//		ResultSet rs=null;
		String filename=MedicalBoardConfig.QUERY_FILE_FOR_MEDICALBOARD_TRANSACTIONDAO;
		String queryKey="INSERT.HMBT_HANDOVER_DTL";
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
			
			populateMap.put(sq.next(), handOverDetailVO.getRequisitionId());
			
			//sereal number
			populateMap.put(sq.next(), handOverDetailVO.getRequisitionId());
			populateMap.put(sq.next(), userVO.getHospitalCode());
			
			populateMap.put(sq.next(), handOverDetailVO.getHandOverTo());
			populateMap.put(sq.next(), handOverDetailVO.getIsDuplicate());
			populateMap.put(sq.next(), handOverDetailVO.getDuplicateReason());
			populateMap.put(sq.next(), handOverDetailVO.getDispatchDate());
			populateMap.put(sq.next(), handOverDetailVO.getDeliveryType());
			populateMap.put(sq.next(), handOverDetailVO.getReferenceNo());
			populateMap.put(sq.next(), handOverDetailVO.getRelativeName());
			populateMap.put(sq.next(), handOverDetailVO.getRelationShipCode());
			populateMap.put(sq.next(), handOverDetailVO.getRelativeAddr());
			populateMap.put(sq.next(), handOverDetailVO.getRelativeContactNo());
			populateMap.put(sq.next(), handOverDetailVO.getRelativeIdRemark());
			populateMap.put(sq.next(), handOverDetailVO.getHandOverBy());
			populateMap.put(sq.next(), handOverDetailVO.getIsAuthorityProved());
			populateMap.put(sq.next(), handOverDetailVO.getAuthorityProofDescription());
			populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMap.put(sq.next(), handOverDetailVO.getHandOverDate());
			populateMap.put(sq.next(), userVO.getSeatId());
			//entrydate
			populateMap.put(sq.next(), handOverDetailVO.getRemarks());
			populateMap.put(sq.next(), userVO.getHospitalCode());
						
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("BldReqExtBldBankDtlDAO.populateMAP::" + e);
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
