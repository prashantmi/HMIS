/*
 ## Copyright Information				: C-DAC, Noida  
 ## Project Name				       	: NIMS
 ## Name of Developer		 			: Manisha Gangwar
 ## Module Name					        : MRD
 ## Process/Database Object Name	    : UNIT WISE ESTIMATE PROCEDURE MAPPING MASTER
 ## Purpose						        : 
 ## Date of Creation					: 04-NOV-2015 
 ## Modification Log					:				
 ##		Modify Date				        :  
 ##		Reason	(CR/PRS)			    : 
 ##		Modify By				        : 
*/

package mrd.masters.dao;
import java.util.HashMap;
import java.util.Map;

import mrd.MrdConfig;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;
import opd.OpdConfig;





import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.UserVO;
public class UnitWiseEstProcedureMappingMstDAO extends DataAccessObject
{
	Logger log;
	
	public UnitWiseEstProcedureMappingMstDAO(TransactionContext _tx)
	{
		super(_tx);
		log = LogManager.getLogger(this.getClass());
	}


	
	public void create(UnitWiseEstProcedureMappingMstVO _unitProcListMasterVO,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "INSERT.HPMRT_PROCEDURE_UNIT_MAPPING";
		
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
			populateMAP.put(sq.next(),_unitProcListMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(),_unitProcListMasterVO.getTariffId());
			populateMAP.put(sq.next(),Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(),_unitProcListMasterVO.getIsDefault());
			//sereal no
			populateMAP.put(sq.next(),_unitProcListMasterVO.getDeptUnitCode());
			populateMAP.put(sq.next(),_unitProcListMasterVO.getTariffId());
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
			populateMAP.put(sq.next(),_userVO.getSeatId());
			//entryDate
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			populateMAP.put(sq.next(),_unitProcListMasterVO.getLastModifiedSeatID());
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
		public void update(String _unitCode,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = MrdConfig.QUERY_FILE_FOR_MRD_MASTER_DAO;
		String queryKey = "UPDATE.HPMRT_PROCEDURE_UNIT_MAPPING"; 
		 
		
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
			populateMAP.put(sq.next(),Config.IS_VALID_DELETED);
			populateMAP.put(sq.next(),_unitCode);
			populateMAP.put(sq.next(),_userVO.getHospitalCode());
			
			
		}
		catch (Exception e)
		{
			throw new HisApplicationExecutionException("UnitImageMasterDAO.populateMAP::" + e);
		}
		
		try
		{
			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext().getConnection(), query, populateMAP);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new HisDataAccessException("HelperMethodsDAO.getResultset" + e);
		}
	}
	
	
	
	
}
