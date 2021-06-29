package mortuary.transaction.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import mortuary.MortuaryConfig;

import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DeceasedDetailVO;
import hisglobal.vo.UserVO;

public class MortuaryDAO extends DataAccessObject implements MortuaryDAOi
{
	public MortuaryDAO(TransactionContext _tx)
	{
		super(_tx);
	}
	
	public DeceasedDetailVO getDeceasedDetailByDeceasedNo(DeceasedDetailVO deceasedVO,UserVO userVO)
	{
		DeceasedDetailVO deceasedDtlVO=new DeceasedDetailVO();
		String query = "";
		Map populateMap= new HashMap();
		String filename = MortuaryConfig.QUERY_FILE_FOR_MORTUARY_DAO;
		String queryKey = "GET_DECEASED_DETAIL_BY_DECEASED_NO.HMRT_DECEASED_DTL";
		
		Sequence sq = new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), Config.IS_VALID_ACTIVE);
		populateMap.put(sq.next(), deceasedVO.getDeceasedNo());
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
					HelperMethods.populateVOfrmRS(deceasedDtlVO,rs);
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
		return deceasedDtlVO;
	}
	
}
