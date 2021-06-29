/**
 * 
 */
package opd.dao;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.DataAccessObject;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.TransactionContext;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.Sequence;
import hisglobal.vo.DepartmentHosDiseaseMstVO;
import hisglobal.vo.UserVO;
import hisglobal.vo.ValueObject;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;

/**
 * @author ashas
 *
 */
public class DepartmentUnitHosDiseaseMstDAO extends DataAccessObject implements DepartmentUnitHosDiseaseMstDAOi{

	public DepartmentUnitHosDiseaseMstDAO(TransactionContext _tx) {
		super(_tx);	
	}
	
	public void create(DepartmentHosDiseaseMstVO _departmentHosDisVOLst,UserVO _userVO) {

		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "INSERT.HGBT_DEPT_HOSDISEASE_MST";

		try {
			query = HelperMethodsDAO.getQuery(filename, queryKey);
		} catch (Exception e) {
			throw new HisDataAccessException(
					"HelperMethodsDAO.loadPropertiesFile(filename)OR getting query out of property file"
							+ e);
		}

		Sequence sq = new Sequence();
		try {
			populateMAP.put(sq.next(), _departmentHosDisVOLst.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _departmentHosDisVOLst.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _departmentHosDisVOLst.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _departmentHosDisVOLst.getDepartmentUnitCode());
			populateMAP.put(sq.next(), _departmentHosDisVOLst.getHospitalDiseaseCode());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _userVO.getSeatId());
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
		} catch (Exception e) {
			throw new HisApplicationExecutionException(
					"ChartMasterDAO.populateMAP::" + e);
		}

		try {

			HelperMethodsDAO.excecuteUpdate(super.getTransactionContext()
					.getConnection(), query, populateMAP);

		} catch (Exception e) {

			throw new HisDataAccessException("HelperMethodsDAO.executeUpdate"
					+ e);
		}

	}
	
	
	
	public DepartmentHosDiseaseMstVO[] getDeptUnitHosDiseaseForModify(String _deptUnitCode, UserVO _userVO)
	{

		ResultSet rs = null;
		String query = "";
		ValueObject[] vo={};
		DepartmentHosDiseaseMstVO[] departmentHosDiseaseMstVO=null;
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HGBT_DEPT_HOSDISEASE_MST";
		// first call the getQueryMethod with arguments filename,querykey from prop file
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

		populateMAP.put(sq.next(), _deptUnitCode);
		populateMAP.put(sq.next(), _userVO.getHospitalCode());
		populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
		

		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
			if (!rs.next()) 
			{
				//throw new HisRecordNotFoundException("No audio Video File found For This Unit.");
			}
			else
			{
				rs.beforeFirst();
				vo=HelperMethods.populateVOfrmRS(DepartmentHosDiseaseMstVO.class,rs);
				departmentHosDiseaseMstVO=new DepartmentHosDiseaseMstVO[vo.length];
				for(int i=0;i<vo.length;i++)
				{
					departmentHosDiseaseMstVO[i]=(DepartmentHosDiseaseMstVO)vo[i];
				}
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

		

		return departmentHosDiseaseMstVO;
	}
	
	
	
	
	
	public List getNotMappedHosDisease(String _deptCode,UserVO _userVO)
	{
		ResultSet rs = null;
		String query = "";
		Map populateMAP = new HashMap();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "SELECT.HOSPITAL.DISEASE_NOT_ADDED.HGBT_DEPT_HOSDISEASE_MST";
		List diseaseNotAddedList=new ArrayList();;
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
			populateMAP.put(sq.next(), _userVO.getHospitalCode());
			populateMAP.put(sq.next(), Config.IS_VALID_ACTIVE);
			populateMAP.put(sq.next(), _deptCode);
		}
		catch(Exception e)
		{
			throw new HisApplicationExecutionException("OpdEssentialDAO.populateMAP::" + e);
		}
		
		try
		{
			rs = HelperMethodsDAO.executeQuery(super.getTransactionContext().getConnection(), query, populateMAP);
				if(rs.next())
				{
					diseaseNotAddedList=HelperMethodsDAO.getAlOfEntryObjects(rs);	
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
			return diseaseNotAddedList;
	}
	public void update(String _deptUnitCode,UserVO _userVO)
	{
		String query = "";
		Map populateMAP = new HashMap();
		Sequence sq = new Sequence();
		String filename = OpdConfig.QUERY_FILE_FOR_OPD_MASTERSDAO;
		String queryKey = "UPDATE.HGBT_DEPT_HOSDISEASE_MST";
		
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
			populateMAP.put(sq.next(),_deptUnitCode );
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
