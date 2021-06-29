package mrd.transaction.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.RecordEnclosureDtlVO;
import hisglobal.vo.UserVO;

import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;

public class RecordEnclosureDtlDAO extends DataAccessObject implements RecordEnclosureDtlDAOi
{
	public RecordEnclosureDtlDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public void create(RecordEnclosureDtlVO recordEnclosureDtlVO,UserVO userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_DAO;
		String queryKey = "INSERT.HPMRT_RECORD_ENCLOSURE_DTL";
		
		try
		{
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file" + e);
		}
		
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getMrdRecordId());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getEnclosureId());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getMrdRecordId());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getEnclosureId());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getPages());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getIsLost());
		populateMAP.put(sq.next(), recordEnclosureDtlVO.getRemarks());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMAP.put(sq.next(), userVO.getSeatId());
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
			
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
}
