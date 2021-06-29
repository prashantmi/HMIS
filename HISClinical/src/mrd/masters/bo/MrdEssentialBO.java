package mrd.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.ChartUnitMapppingVO;
import hisglobal.vo.EprRestrictedCategoryVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import registration.RegistrationConfig;
import mrd.MrdConfig;
import mrd.masters.dao.EprRestrictedCategoryMstDAO;
import mrd.masters.dao.EprRestrictedCategoryMstDAOi;
import mrd.masters.dao.EssentialDAO;
import mrd.masters.dao.EssentialDAOi;
import mrd.masters.dao.RecordBoundingDAO;
import mrd.masters.dao.RecordBoundingDAOi;
import mrd.transaction.dao.MrdEssentialDAO;
import mrd.transaction.dao.MrdEssentialDAOi;
import mrd.vo.UnitWiseEstProcedureMappingMstVO;

public class MrdEssentialBO implements MrdEssentialBOi{

	
	// Functions for Rack Shelf Master
	public List getRackNamelist(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rackNameList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAO daoobj = new EssentialDAO(tx);
			rackNameList=daoobj.getRackNameList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rackNameList;
	}
	
	public Map getMrdBoundingEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essMap=new HashMap();
		List mrdList=new ArrayList();
		List recordTypeList=new ArrayList();
		
		try
		{
			tx.begin();
			MrdEssentialDAOi daoobj = new MrdEssentialDAO(tx);
			EssentialDAO essDAO=new EssentialDAO(tx);
			
			recordTypeList=daoobj.getRecordType(userVO);
			essMap.put(MrdConfig.RECORD_TYPE, recordTypeList);
			
			mrdList=essDAO.getMrdList(userVO);
			essMap.put(MrdConfig.LIST_ALL_MRD, mrdList);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essMap;
	}
	
	public List getBoundedRecordType(String boundingMode,String boundingId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List lstRecordType=new ArrayList();
		
		try
		{
			tx.begin();

			RecordBoundingDAOi boundDAO = new RecordBoundingDAO(tx);
			lstRecordType=boundDAO.getBoundedRecordType(boundingMode,boundingId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstRecordType;
	}
	
	public List getRackBasedOnMrd(String mrdCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List rackList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAOi essDAO = new EssentialDAO(tx);
			rackList=essDAO.getRackBasedOnMrd(mrdCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return rackList;
	}
	
	public List getShelfBasedOnRack(String rackId,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List shelfList=new ArrayList();
		try
		{
			tx.begin();

			EssentialDAOi essDAO = new EssentialDAO(tx);
			shelfList=essDAO.getShelfBasedOnRack(rackId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return shelfList;
	}
	
	
	public Map getEssentialForEPRRestrictedCat(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List addedPatCatList=new ArrayList();
		List notAddedPatCatList=new ArrayList();
		Map essentialMap=new HashMap();
		try
		{
			tx.begin();
			
			EprRestrictedCategoryMstDAOi daoObj = new EprRestrictedCategoryMstDAO(tx);
			addedPatCatList=daoObj.getAddedPatientCategoryList(userVO);
			essentialMap.put(MrdConfig.EPR_ADDED_PAT_CAT_LIST, addedPatCatList);
			notAddedPatCatList=daoObj.getNotAddedPatCatList(userVO);
			essentialMap.put(MrdConfig.EPR_NOT_ADDED_PAT_CAT_LIST, notAddedPatCatList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	public void saveEprPatRestrictedCategory(EprRestrictedCategoryVO[] eprRestrictedCatMstVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			
			EprRestrictedCategoryMstDAOi daoObj = new EprRestrictedCategoryMstDAO(tx);
			
			daoObj.updateIsValid(userVO);
			
			if(eprRestrictedCatMstVO!=null)
				for(int i=0;i<eprRestrictedCatMstVO.length;i++){
					daoObj.create(eprRestrictedCatMstVO[i], userVO);
				}
			
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	
	}
	
	public Map getFormPReportEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MrdEssentialDAOi essentialDAO = new MrdEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstDepartment = essentialDAO.getAllDeptHavingUnits(_userVO);
			essentialMap.put(RegistrationConfig.ESSENTIAL_BO_OPTION_ALLDEPT, lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	// added by Manisha Gangwar on 05.11.2015 for getting Estimate Procedure Unit Wise Mapping ,  Module: MRD
		public Map getProcedureUnitListEssential(UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map essentialMap = new HashMap();
			List procedureList = null;
			List lstDept=null;
			List listAllUnit=null;
			List listNotMappedUnit=null;
			
			try
			{
				tx.begin();
				EssentialDAO essentialDAO = new EssentialDAO(tx);
				
				lstDept = essentialDAO.getAllClinicalDepartmentList(_userVO);
				essentialMap.put(MrdConfig.ALL_CLINICAL_DEPARTMENT_LIST, lstDept);
				
				listAllUnit = essentialDAO.getAllUnit(_userVO);
				essentialMap.put(MrdConfig.ALL_UNIT_LIST, listAllUnit);
				
				listNotMappedUnit=essentialDAO.getAllUnitNotMappedWithProcedureList(_userVO);
				essentialMap.put(MrdConfig.ALL_UNIT_LIST_NOT_MAPPED_WITH_PROCEDURE, listNotMappedUnit);
				
				procedureList = essentialDAO.getProcedureUnitListEssential(_userVO);
				essentialMap.put(MrdConfig.PROCEDURE_NAME_LIST,procedureList);
			} 
			catch (HisRecordNotFoundException e) {
				tx.rollback();
				e.printStackTrace();
				throw new HisRecordNotFoundException(e.getMessage());
			} catch (HisDataAccessException e) {
				tx.rollback();
				System.out.println("HisDataAccessException:: " + e);
				e.printStackTrace();
				throw new HisDataAccessException();
			} catch (HisApplicationExecutionException e) {
				tx.rollback();
				System.out.println("HisApplicationExecutionException:: " + e);
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			} catch (HisException e) {
				tx.rollback();
				System.out.println("HisException:: " + e);
				e.printStackTrace();
				throw new HisException();
			} catch (Exception e) {
				tx.rollback();
				System.out.println("Exception:: " + e);
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			} finally {
				tx.close();
			}
			return essentialMap;
		}
		
		//added by Manisha Gangwar Date: 05.Nov.15 for Unit Wise Estimate Procedure Mapping for modify ,  Module: MRD
		public Map getProcedureUnitListForModify(UnitWiseEstProcedureMappingMstVO _vo, UserVO _userVO)
		{
			JDBCTransactionContext tx = new JDBCTransactionContext();
			Map essentialMap=new HashMap();
			
			List listAllUnit=null;
			UnitWiseEstProcedureMappingMstVO[] procedureUnitMstVO=null;
			List listAllProcedureList=null;
			
			try
			{
				tx.begin();
				EssentialDAO essentialDAO = new EssentialDAO(tx);
				
							
				listAllUnit = essentialDAO.getAllUnit(_userVO);
				essentialMap.put(MrdConfig.ALL_UNIT_LIST, listAllUnit);
							
				listAllProcedureList=essentialDAO.getProcedureUnitListEssential(_userVO);
				essentialMap.put(MrdConfig.PROCEDURE_NAME_LIST, listAllProcedureList);
			
				procedureUnitMstVO=essentialDAO.getUnitProcedureByDeptUnit(_vo.getDeptUnitCode(), _userVO);
				essentialMap.put(MrdConfig.MAPPED_UNIT_PROCEDURE_LIST_VO_ARRAY, procedureUnitMstVO);
				
			}
			catch (HisRecordNotFoundException e)
			{
				tx.rollback();
				throw new HisRecordNotFoundException(e.getMessage());
			}
			catch (HisApplicationExecutionException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisApplicationExecutionException();
			}
			catch (HisDataAccessException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisDataAccessException();
			}
			catch (HisException e)
			{
				tx.rollback();
				e.printStackTrace();
				throw new HisException();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				tx.rollback();
				throw new HisApplicationExecutionException();
			}
			finally
			{
				tx.close();
			}
			return essentialMap;
		}
}






