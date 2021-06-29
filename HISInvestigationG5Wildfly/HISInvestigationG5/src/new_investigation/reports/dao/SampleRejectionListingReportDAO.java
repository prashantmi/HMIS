package new_investigation.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;






import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.presentation.Status;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import new_investigation.vo.MandatoryComboMasterVO;
import new_investigation.vo.SampleRejectionListingReportVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;
import new_investigation.InvestigationConfig;

public class SampleRejectionListingReportDAO extends DataAccessObject 
{

	public SampleRejectionListingReportDAO(TransactionContext _tx)
	{
		super(_tx);
		// TODO Auto-generated constructor stub
	}





	// For lab combo

	public List getLab(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
		String queryKey="SELECT.LAB_DEPT_COMBO.HIVT_LABORATORY_MST";

		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstMandName=HelperMethodsDAO.getAlOfEntryObjects(rs);
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
		return lstMandName;
	}


	public List getTest(SampleRejectionListingReportVO reqVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
 		String queryKey="SELECT.TEST_COMBO.HIVT_LABORATORY_MST";
          /* if(reqVO.getLabCode()==null)
           {
        	   reqVO.setLabCode("%");
           }
 		if(reqVO.getTestCode()=="$")
        {
     	   reqVO.setLabCode("%");
        }*/
 		
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), reqVO.getLabCode());
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
			rs = HelperMethodsDAO.executeQuery(conn, query, populateMap);
			if (!rs.next())
			{
				throw new HisRecordNotFoundException();
			}
			else
			{
				rs.beforeFirst();
				lstMandName=HelperMethodsDAO.getAlOfEntryObjects(rs);
			}
		}
		catch(Exception e)
		{
			if(e.getClass()==HisRecordNotFoundException.class)
			{
				//throw new HisRecordNotFoundException();	
			}
			else			 		
				throw new HisDataAccessException("HisDataAccessException:: "+e);			 
		}	
		return lstMandName;
	}


}
