package medicalboard.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.MedicalBoardMasterVO;
import hisglobal.vo.UserVO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import medicalboard.MedicalBoardConfig;
import medicalboard.masters.dao.MbMasterEssentialDAO;
import medicalboard.masters.dao.MbMasterEssentialDAOi;


public class MedicalBoardMasterEssentialBO implements MedicalBoardMasterEssentialBOi{ 

	
	public Map getCertificateEssentials(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List certificateCategoryList=new ArrayList();
		List boardTypeList=new ArrayList();
		List depUnitList=new ArrayList();
		List districtList =new ArrayList();
		List templateList = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			certificateCategoryList=mEssentialDAOi.getCertificateCategory(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_CERTIFICATE_CATEGORY,certificateCategoryList);	
			
			boardTypeList=mEssentialDAOi.getBoardTypeList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_BOARD_TYPE, boardTypeList);
			
			depUnitList=mEssentialDAOi.getDepartmentUnitList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_DEPARTMENT_UNIT_CODE, depUnitList);
			
			districtList=mEssentialDAOi.getDistrictList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_DISTRICT, districtList);
			

			templateList=mEssentialDAOi.getTemplateList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_TEMPLATE, templateList);
			
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
	
	
	
	
	public Map getBoardEssentials(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List empDoctorList=new ArrayList();
		List empEscortList=new ArrayList();
		List rollList=new ArrayList();
		List boardTypeList=new ArrayList();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			empDoctorList=mEssentialDAOi.getEmpDoctorList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_EMP_DOCTOR,empDoctorList);	
			
			empEscortList=mEssentialDAOi.getEmpEscortList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_EMP_ESCORTS, empEscortList);
			
			rollList=mEssentialDAOi.getRollList(userVO);
			essentialMap.put(MedicalBoardConfig.LIST_OF_ROLL, rollList);
			
			boardTypeList=mEssentialDAOi.getBoardTypeList(userVO);
			essentialMap.put(MedicalBoardConfig.BOARD_TYPE_LIST, boardTypeList);
			
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
	
	
	
	
	
	public Map getCertificateBoardEssentials(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		
		MedicalBoardMasterVO[] mBoardMasterVO=null;
		MbCertificateTypeMstVO[] mCertificateTypeMstVO=null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			mBoardMasterVO=mEssentialDAOi.getBoardDetail(userVO);
			essentialMap.put(MedicalBoardConfig.BOARD_DETAIL_VO, mBoardMasterVO);
			
			
			mCertificateTypeMstVO=mEssentialDAOi.getCertificateDetail(userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_VO, mCertificateTypeMstVO);
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
	
	
	// refer Mapping Master 
	
	public Map getReferEssentials(UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List certificatetypeList=new ArrayList();
		List departmentList=new ArrayList();
		List unitList=new ArrayList();
		
		List departmentListOld=new ArrayList();
		List unitListOld=new ArrayList();
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			certificatetypeList=mEssentialDAOi.getCertificateTypeList(userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_LIST,certificatetypeList);	
			
			departmentList=mEssentialDAOi.getAllDepartmentList(userVO);
			essentialMap.put(MedicalBoardConfig.ALL_DEPARTMENT_LIST, departmentList);
			
			unitList=mEssentialDAOi.getSpecialDeptUnitList(userVO);
			essentialMap.put(MedicalBoardConfig.ALL_UNIT_LIST, unitList);
			
			departmentListOld=mEssentialDAOi.getAllDepartmentList(userVO);
			essentialMap.put(MedicalBoardConfig.ALL_DEPARTMENT_LIST_OLD, departmentListOld);
			
			unitListOld=mEssentialDAOi.getAllDeptUnitList(userVO);
			essentialMap.put(MedicalBoardConfig.ALL_UNIT_LIST_OLD, unitListOld);
			
			
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
	
	
	public Map getInvestigationMappingMstEssentials(String certificateTypeId,UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List certificatetypeList=null;
		List labTestCodeList=null;		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			certificatetypeList=mEssentialDAOi.getCertificateTypeList(userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_LIST,certificatetypeList);	
			
			labTestCodeList=mEssentialDAOi.getlabTestList(certificateTypeId,userVO);
			essentialMap.put(MedicalBoardConfig.ALL_LAB_TEST_LIST,labTestCodeList);
			 
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
	
	public Map getInvestigationModifyEssentials(String certificateTypeId,UserVO userVO)
	{
		Map essentialMap=new HashMap();
		List certificatetypeList=null;
		List labTestCodeList=null;	
		List selLabTestList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		
		try
		{
			tx.begin();
			MbMasterEssentialDAOi mEssentialDAOi=new MbMasterEssentialDAO(tx); 
		
			certificatetypeList=mEssentialDAOi.getCertificateTypeList(userVO);
			essentialMap.put(MedicalBoardConfig.CERTIFICATE_TYPE_LIST,certificatetypeList);	
			
			labTestCodeList=mEssentialDAOi.getlabTestList(certificateTypeId,userVO);
			essentialMap.put(MedicalBoardConfig.ALL_LAB_TEST_LIST,labTestCodeList);
			 
			selLabTestList=mEssentialDAOi.getSelLabTestList(certificateTypeId, userVO);
			essentialMap.put(MedicalBoardConfig.ALL_SELECTED_INVASTIGATION_MAPPING_VO_LIST,selLabTestList);
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
