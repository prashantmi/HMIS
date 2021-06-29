package new_investigation.reports.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import new_investigation.InvestigationConfig;
import new_investigation.vo.InvestigationStatisticalReportVO;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.UserVO;

public class InvestigationStatisticalReportDAO extends DataAccessObject {

	public InvestigationStatisticalReportDAO(TransactionContext _tx) {
		super(_tx);
		// TODO Auto-generated constructor stub
	}
	
	
	public List getLab(InvestigationStatisticalReportVO reqVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
 		String queryKey="SELECT.LAB_INV_COMBO.HIVT_LABORATORY_MST";
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), reqVO.getDeptCode());
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



	public List getTest(InvestigationStatisticalReportVO reqVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
 		String queryKey="SELECT.TEST_COMBO.HIVT_LABORATORY_MST";

 		if(reqVO.getLabCode()!="-1")
		{
			 reqVO.setTestCode("%");
		}
 		
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

	
	public List getDept(UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
		String queryKey="SELECT.DEPT_COMBO.HIVT_LABORATORY_MST.STATISTICAL_REPORT";

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
	
	public List getSample(InvestigationStatisticalReportVO reqVO, UserVO _UserVO)
	{
		String query="";
		Map populateMap= new HashMap();
		ResultSet rs=null;
		List lstMandName=new ArrayList();
		String filename= InvestigationConfig.QUERY_FILE_FOR_INVESTIGATION_REPORTSDAO;
		String queryKey="SELECT.SAMPLE.HIVT_SAMPLE_MST.STATISTICAL_REPORT";
		if(reqVO.getLabCode()!="-1")
		{
			 reqVO.setSampleCode("%");
		}
		Sequence sq= new Sequence();
		Connection conn=super.getTransactionContext().getConnection();
		populateMap.put(sq.next(), _UserVO.getHospitalCode());
		populateMap.put(sq.next(), reqVO.getLabCode());
		populateMap.put(sq.next(), reqVO.getTestCode());
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

	

}
