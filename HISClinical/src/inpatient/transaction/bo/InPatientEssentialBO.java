package inpatient.transaction.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.persistence.GlobalClinicalDAO;
import hisglobal.persistence.GlobalEssentialDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.WebUTIL;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.ANCTrimesterChecklistMasterVO;
import hisglobal.vo.BloodRequisitionComponentDtlVO;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugFrequencyMstVO;
import hisglobal.vo.MonitoringModeMstVO;
import hisglobal.vo.PatAllergyDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.PatientAlertsDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UnitMasterVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.masters.dao.ANCTrimesterChklistMasterDAO;
import inpatient.masters.dao.ANCTrimesterChklistMasterDAOi;
import inpatient.masters.dao.UnitInvParaMappingMstDAO;
import inpatient.transaction.dao.ANCChildHandoverDtlDAO;
import inpatient.transaction.dao.ANCChildHandoverDtlDAOi;
import inpatient.transaction.dao.ANCDeliveryDtlDAO;
import inpatient.transaction.dao.ANCDeliveryDtlDAOi;
import inpatient.transaction.dao.ANCDtlDAO;
import inpatient.transaction.dao.ANCDtlDAOi;
import inpatient.transaction.dao.ANCNeonatalApgarDtlDAO;
import inpatient.transaction.dao.ANCNeonatalApgarDtlDAOi;
import inpatient.transaction.dao.ANCNeonatalDtlDAO;
import inpatient.transaction.dao.ANCNeonatalDtlDAOi;
import inpatient.transaction.dao.ANCTrimesterChklistDtlDAO;
import inpatient.transaction.dao.ANCTrimesterChklistDtlDAOi;
import inpatient.transaction.dao.DrugAdminDtlDAO;
import inpatient.transaction.dao.DrugAdminDtlDAOi;
import inpatient.transaction.dao.DrugReactionDAO;
import inpatient.transaction.dao.DrugReactionDtlDAOi;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;
import inpatient.transaction.dao.MonitorVitalsDtlDAO;
import inpatient.transaction.dao.TransfusionReactionDtlDAO;
import inpatient.transaction.dao.TransfusionReactionDtlDAOi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import opd.OpdConfig;
import opd.dao.OpdEssentialDAO;
import opd.dao.OpdEssentialDAOi;
import opd.dao.PatAlertsDetailDAO;
import opd.dao.PatAlertsDetailDAOi;
import opd.dao.PatAllergyDtlDAO;
import opd.dao.PatientClinicalDetailDAO;
import opd.dao.PatientClinicalDetailDAOi;
import registration.dao.DailyPatientDAO;
import registration.dao.EssentialDAO;

public class InPatientEssentialBO implements InPatientEssentialBOi
{
	//Getting the List of Unit
	public List getDeptUnitList(UserVO userVO)
	{
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getDeptUnitList(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstUnit;
	}
	
	public String getDrugAdminData(PatientDetailVO voPat, UserVO voUser)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		try
		{
			InPatientEssentialDAOi essentialDAO = new InPatientEssentialDAO(tx);
			tx.begin();
			count = essentialDAO.getDrugAdminData(voPat,voUser);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return count;

	}
	
	public String getProgressNotesData(PatientDetailVO voPat, UserVO voUser)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String count;
		try
		{
			InPatientEssentialDAOi essentialDAO = new InPatientEssentialDAO(tx);
			tx.begin();
			count = essentialDAO.getProgressNotesData(voPat,voUser);
		}
		catch (HisApplicationExecutionException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisDataAccessException(e.getMessage());
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisException(e.getMessage());
		}
		finally
		{
			tx.close();
		}
		return count;

	}
	
	public Map getDeptUnitListForUnitWise(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getDeptUnitListForUnitWise(userVO);
			essentialMap.put(InpatientConfig.EssentialBO_LIST_ALL_UNITSFORUNITWISE, lstUnit);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
	
	public Map getDeptUnitListForUnitWiseForModify(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getDeptUnitListForUnitWiseForModify(userVO);
			essentialMap.put(InpatientConfig.EssentialBO_LIST_ALL_UNITSFORUNITWISE, lstUnit);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
	
	public Map getDeptUnitListForWardWise(UserVO userVO)
	{
		Map essentialMap = new HashMap();
		List lstUnit=null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getDeptUnitListForWardWise(userVO);
			essentialMap.put(InpatientConfig.EssentialBO_LIST_ALL_UNITSFORWARDWISE, lstUnit);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
	
	public List getParameter(UserVO userVO)
	{
		//Map essentialMap = new HashMap();
		List lstPara=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstPara=essentialDAO.getParameter(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstPara;
	}
	
	public List getParameterForModify(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		//Map essentialMap = new HashMap();
		List lstPara=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstPara=essentialDAO.getParameterForModify(unitInvParaMapVO,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstPara;
	}
	
	public List getParameterForWardWise(UnitInvParaMappingVO unitInvParaMapVO,UserVO userVO)
	{
		//Map essentialMap = new HashMap();
		List lstPara=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstPara=essentialDAO.getParameterForWardWise(unitInvParaMapVO,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstPara;
	}
	
	//Getting the List of ward on the basis of unitCode
	public List getWardOnBasisOfUnitCode(String unitCode,UserVO userVO)
	{
		List lstWard=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstWard=essentialDAO.getWardOnBasisOfUnitCode(unitCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstWard;
	}
	
	/** Getting the List of Ward on the basis of role 
	 * @param unitCode
	 * @param userVO
	 * @return
	 */
	public List getWardListBasedOnRole(UserVO userVO)
	{
		List lstWard=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstWard=essentialDAO.getWardListBasedOnRole(userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstWard;
	}
	
	//Getting the List of Admitted patient on the basis of unitCode & wardCode
	public PatientDetailVO[] getAdmittedPatientList(String roomCode,String unitCode,String wardCode,UserVO userVO)
	{
		PatientDetailVO[] patDtlVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			patDtlVO=essentialDAO.getAdmittedPatientList(roomCode,unitCode,wardCode,userVO);
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
		return patDtlVO;
	}

	public UnitInvParaMappingVO getData(UnitInvParaMappingVO _UnitInvParaMst, UserVO _UserVO)
	{
		UnitInvParaMappingVO unitInvParaVOArray = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO unitInvParaDAOi = new UnitInvParaMappingMstDAO(tx);
			unitInvParaVOArray = unitInvParaDAOi.fetchRecord(_UnitInvParaMst, _UserVO);
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
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitInvParaVOArray;
	}
	
	
	public UnitInvParaMappingVO fetchParameter(String _slno,String _unitId,String _wardCode, UserVO _UserVO)
	{
		UnitInvParaMappingVO unitInvParaVOArray = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO unitInvParaDAOi = new UnitInvParaMappingMstDAO(tx);
			unitInvParaVOArray = unitInvParaDAOi.fetchParameter(_slno,_unitId,_wardCode, _UserVO);
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
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return unitInvParaVOArray;
	}
	
	
	public List getWardsForModify(String _unitId, UserVO _userVO)
	{
		List ward = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssentialDAOi = new InPatientEssentialDAO(tx);
			ward = inpatientEssentialDAOi.getWardsForModify(_unitId, _userVO);
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return ward;
	}
	
	public UnitInvParaMappingVO getWardName(UnitInvParaMappingVO _unitInvParaMapVO, UserVO _userVO)
	{
		UnitInvParaMappingVO ward = new UnitInvParaMappingVO();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssentialDAOi = new InPatientEssentialDAO(tx);
			ward = inpatientEssentialDAOi.getWardName(_unitInvParaMapVO, _userVO);
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
			System.out.println("HisException:: " + e);
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return ward;
	}
	
	/*public List getWardList(UnitInvParaMappingVO voUDMT, UserVO _UserVO)
	{
		List list = new ArrayList();
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi essentialDAOi = new InPatientEssentialDAOi(tx);
	
			if(voUDMT.getAdditionMode()==InpatientConfig.UNITWARD_WISE)
				list= essentialDAOi.getWardList(voUDMT, _UserVO);
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
			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return list;
	}*/
	
	public UnitInvParaMappingVO getParameterName(String _paraCode,UserVO _UserVO)
	{
		
		UnitInvParaMappingVO paraName=null;
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			paraName=daoobj.getParameterName(_paraCode,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return paraName;
	}
	
	public List getParaListForModify(String _slno,String _unitId, String _wardCode,UserVO _UserVO)
	{
		
		List paraName=new ArrayList();
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
			paraName=daoobj.getParaListForModify(_slno,_unitId,_wardCode,_UserVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: "+e);
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			System.out.println("error.... Opd Master BO");
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return paraName;
	}


public List getParaListForWardWise(String _slno,String _unitId, String _wardCode,UserVO _UserVO)
{
	
	List paraName=new ArrayList();
	JDBCTransactionContext tx =new JDBCTransactionContext();
	try
	{
		tx.begin();
		UnitInvParaMappingMstDAO daoobj =new UnitInvParaMappingMstDAO(tx);
		paraName=daoobj.getParaListForWardWise(_slno,_unitId,_wardCode,_UserVO);
	}
	catch(HisRecordNotFoundException e)
	{
		tx.rollback();		
		throw new HisRecordNotFoundException(e.getMessage()); 
	}
	catch(HisApplicationExecutionException e)
	{	   		   	
		tx.rollback();
		 	e.printStackTrace();   		 
		 	throw new HisApplicationExecutionException();
	 	}   	 
	catch(HisDataAccessException e)
	{		
		tx.rollback();
		 	e.printStackTrace();   		 
		 	throw new HisDataAccessException();  	
	}
	catch(HisException e)
	{
		tx.rollback();
		System.out.println("HisException:: "+e);
		e.printStackTrace();
		throw new HisException();
	}
	catch(Exception e)
	{
		e.printStackTrace();	
		System.out.println("error.... Opd Master BO");
		tx.rollback();		
		throw new HisApplicationExecutionException();
	}
	finally
	{
		tx.close();			
	}
	return paraName;
}

	public List getEmployeeListUnitWise(String unitCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		List lstEmployee=null;
		try
		{
			tx.begin();
			lstEmployee=essentialDAO.getEmployeeListUnitWise(unitCode, userVO);
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
		return lstEmployee;
	}
	
	public List getOutParameterList(String type,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		List lstOutPara=null;
		try
		{
			tx.begin();
			lstOutPara=essentialDAO.getOutParameterList(type,userVO);
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
		return lstOutPara;
	}

	
	public MonitoringModeMstVO[] getMonitorMode(UserVO userVO)
	{
		MonitoringModeMstVO[] monitorModeMstVO;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			monitorModeMstVO=essentialDAO.getMonitorMode(userVO);
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
		return monitorModeMstVO;
	}

	
	/**
	 * Getting Vital Recording Essentials
	 * @param _patClinDtlVO
	 * @param _patClinicalDtlVO
	 * @param _lstTemplates
	 * @param _userVO
	 * @return Map of essentials
	 */
	public Map getVitalsRecordingEssentials(PatientMonitoringMstVO _patMonitringMstVO, PatientClinicalDetailVO _patClinicalDtlVO, 
			List<Entry> _lstTemplates, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map mpEssentials = new HashMap();		
		try
		{
			tx.begin();

			// Monitoring Vitals Array
			PatientMonitoringMstVO[] params = null;
			MonitorVitalsDtlDAO monitorVitalsDtlDAO = new MonitorVitalsDtlDAO(tx);			
			params = monitorVitalsDtlDAO.getVitalDetail(_patMonitringMstVO, _userVO);
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_ARR, params);
			
			// Monitoring Vitals Last Recorded Values
			List<PatientClinicalDetailVO> lstParaLastData = new ArrayList<PatientClinicalDetailVO>();
			if(params.length >0)
			{
				PatientClinicalDetailDAOi patCliDAO = new PatientClinicalDetailDAO(tx);
				PatientClinicalDetailVO patClinVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patClinVO, _patMonitringMstVO);			
				String[] parameters = new String[params.length];
				for(int i=0;i<params.length;i++)	parameters[i]=params[i].getParaId();				
				lstParaLastData = patCliDAO.getVitalsLastValues(patClinVO, parameters, _userVO);
			}			
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_LAST_RECORD_VALUES, lstParaLastData);

			// For Vital Chart			
			List<Entry> lstReportDates = new ArrayList<Entry>();
			PatientClinicalDetailDAOi daoPatCliDtl = new PatientClinicalDetailDAO(tx);
			lstReportDates = daoPatCliDtl.getClinicalRecordDateList(_patMonitringMstVO.getAdmissionNo(),"", _userVO);
			if(lstReportDates== null) lstReportDates = new ArrayList<Entry>();

			Map<String, Map<String, Map<String, String>>> mpTempParaVals = new HashMap<String, Map<String,Map<String,String>>>();
			for(Entry e : lstReportDates)
			{
				PatientClinicalDetailVO patCliVO = new PatientClinicalDetailVO();
				HelperMethods.populate(patCliVO, _patClinicalDtlVO);
				patCliVO.setRecordDate(e.getValue());
				Map<String, Map<String, String>> map = null;
				if(_lstTemplates!=null && _lstTemplates.size()>0)
				{
					//map = daoPatCliDtl.getPatientClinicalRecordDataTempWise(patCliVO, _lstTemplates, _userVO);
					GenericTemplateUtility.TempParameter[] tempParas = daoPatCliDtl.getPatientClinicalRecordDataTempWise(patCliVO, _lstTemplates, _userVO);
					
					map = new HashMap<String, Map<String,String>>();
					for(GenericTemplateUtility.TempParameter paraValVO : tempParas)
					{
						Map<String,String> mpTemporary = null;
						if(map.get(paraValVO.getTemplateId())!=null)
							mpTemporary = map.get(paraValVO.getTemplateId());
						else
							mpTemporary = new HashMap<String, String>();
						mpTemporary.put(paraValVO.getParaId(), paraValVO.getParaValue());
						map.put(paraValVO.getTemplateId(), mpTemporary);
					}
				}
				if(map!=null && map.keySet().size()>0)	mpTempParaVals.put(e.getValue(), map);
			}			
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_RECORD_DATE_LIST, lstReportDates);
			mpEssentials.put(InpatientConfig.ESSENTIALS_MAP_MONITORING_VITALS_VALUES_MAP, mpTempParaVals);	
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return mpEssentials;
	}
	
	public String getTotalAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
			count=essentialDAO.getTotalAdmittedPatient(userVO,unitCode,wardCode,roomCode);
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return count;
	}
	
	public String getTodayAdmittedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
			count=essentialDAO.getTodayAdmittedPatient(userVO,unitCode,wardCode,roomCode);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return count;
	}
	
	public String getTodayDischargedPatient(UserVO userVO,String unitCode,String wardCode,String roomCode)
	{
		String count = "";
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
			count=essentialDAO.getTodayDischargedPatient(userVO,unitCode,wardCode,roomCode );
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return count;
	}

	public List getRoomOnBasisOfWardCode(String unitCode,String wardCode,UserVO userVO)
	{
		List lstRoom=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstRoom=essentialDAO.getRoomOnBasisOfWardCode(unitCode,wardCode,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstRoom;
	}

	
	public List getProgressNotes(String processId,UserVO userVO)
	{
		List lstNotes=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstNotes=essentialDAO.getProgressNotes(processId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstNotes;
	}

	public List getCallRemarksNNotes(String processId,UserVO userVO)
	{
		List lstNotes=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstNotes=essentialDAO.getCallRemarksNNotes(processId,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstNotes;
	}

	
	public List getRouteList(String type,UserVO userVO)
	{
		List lstRoute=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstRoute=essentialDAO.getRouteList(type,userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstRoute;
	}

	
	public Map getTreatAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		
		
		List lstAllDrugRoutes=null;
		List lstDrugs=null;
		List todayDrugDtlLst=null;
		List<PatDrugTreatmentDetailVO> lstPrevDrugDetail = null;
		List prevDrugSchedule=null;
		List lstDrugDoses=null;
		List drugExecLstByEndDate=null;
		//List drugExecLst=null;
		List drugExecLstByCrNo=null;
		List<Entry> lstDosageFreq=null;
		DrugFrequencyMstVO[] drugFrequencyMstVO;
		//List prevInstructionList=null;
		//List prevActivityList=null;
		//List todayExtList=null;
		//List execExtTreatList=null;
		//List execActivityList=null;
		List batchNoLst=null;
		List drugBrandLst=null;
		List totalBatchNoLst=new ArrayList();
		HisDAO hisDAO = new HisDAO("IPD", "InPatientEssentialBO");
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			DrugAdminDtlDAOi drugAdminDao=new DrugAdminDtlDAO(tx);
			OpdEssentialDAOi opdEssenDao = new OpdEssentialDAO(tx);
			OpdEssentialDAO opdDao = new OpdEssentialDAO(tx);
			
			todayDrugDtlLst=dao.getTodayPatDrugDetail(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
			List todayDrugDetailList = new ArrayList();
			// for getting drug Consent Status
			Iterator itr=todayDrugDtlLst.iterator();
			while(itr.hasNext()) 
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)itr.next();
				String drugConsentStatus=drugAdminDao.getConsentStatus(vo, _userVO);
				vo.setDrugConsentStatus(drugConsentStatus);
				
//				batchNoLst=drugAdminDao.getDrugBatchNoLstFromStore("10101100", vo.getItemId(), _userVO);
//				vo.setBatchNoLst(batchNoLst);
				
				todayDrugDetailList.add(vo);
			}
			
			essentialMap.put(InpatientConfig.TODAY_DRUG_DETAIL_LIST_FOR_PATIENT, todayDrugDetailList);
			
			//for getting batch no list
			Set itemIdSet=new HashSet();// this list for contain distinct item id from today drug list
			
			for(int i=0;i<todayDrugDtlLst.size();i++)
			{
				DrugAdminDtlVO vo=(DrugAdminDtlVO)todayDrugDtlLst.get(i);
				
				itemIdSet.add(vo.getItemId()); 
			}
			
			
//			String drugid="";
//			
//			Iterator setItr=itemIdSet.iterator();
//			
//			while(setItr.hasNext())
//			{
//				String itemId=(String)setItr.next();
//				drugid=drugid+","+itemId;
//			}
//						
//			drugid=drugid.substring(1);
			
			Iterator setItr=itemIdSet.iterator();
			String itemIdList= "";
			while(setItr.hasNext())
			{
				//String itemId=(String)setItr.next();
				itemIdList+=(String)setItr.next()+",";
			}
			if(itemIdList.length()>0)
			{
			itemIdList=itemIdList.substring(0,itemIdList.length()-1);
			batchNoLst=drugAdminDao.getDrugBatchNoLstFromStore(patientDetailVO, itemIdList, _userVO);
			//batchNoLst=drugAdminDao.getDrugBatchNoLstFromStore("10101100", itemId, _userVO);  //Commented by Manisha Gangwar for Pharmacy Linkage at Drug Administration
			drugBrandLst=drugAdminDao.getDrugBrandLstFromGenericType(patientDetailVO, itemIdList, _userVO);  // used for 'from patient' case
			
			for(int i=0;i<batchNoLst.size();i++)
			{
				totalBatchNoLst.add(batchNoLst.get(i));
			}
			
			}
			
			
			essentialMap.put(InpatientConfig.ESSENTIALS_LIST_BATCHNO_FROM_STORE, totalBatchNoLst);
			
			essentialMap.put(InpatientConfig.ESSENTIALS_LIST_BRANDLIST_FROM_GENERICTYPE, drugBrandLst);
			
			lstDosageFreq = dao.getDosageFrequecy(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS_LIST_DOSAGE_FREQUECY, lstDosageFreq);
			
			
			
			PatientDetailVO patVO = new PatientDetailVO();
			patVO.setPatCrNo(patientDetailVO.getPatCrNo());
			patVO.setEpisodeCode(patientDetailVO.getEpisodeCode());
			patVO.setPatGenderType(patientDetailVO.getPatGenderType());
			patVO.setDepartmentUnitCode(patientDetailVO.getDepartmentUnitCode());
			//lstDrugs = dao.getDrugListDetail(hisDAO , "2", patVO, _userVO);//----- Here We need to call Unit Wise Drug MApped drugs thru master   // used for brand drug list
			lstDrugs = opdDao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);   //for Generic Drug List
			
			if (lstDrugs==null || lstDrugs.size()==0 )
			{
				//lstDrugs = dao.getDrugList(_userVO);
				//lstDrugs = dao.getDrugListDetail(hisDAO , "2", patVO, _userVO);     // used for brand drug list
				lstDrugs = opdDao.getGenericDrugListDetail(hisDAO , OpdConfig.lstDrugs, patVO, _userVO);   //for Generic Drug List
			}
			
		//	lstDrugs = dao.getDrugList(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS_LIST_ALL_DRUGS, lstDrugs);
			
			lstPrevDrugDetail = dao.getPrevPatDrugDetail(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.PREV_ALL_DRUG_DETAIL_LIST_OF_PATIENT, lstPrevDrugDetail);
			
			String maxEntryDate=dao.getMaxEntryDateFromDrugDetail(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.MAX_ENTRY_DATE_FROMDRUGDETAIL_BY_CRNO, maxEntryDate);
			
			
			prevDrugSchedule=dao.getPrevDrugSchedule(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(),_userVO);
			essentialMap.put(InpatientConfig.PREV_DRUG_SCHEDULE_OF_PATIENT, prevDrugSchedule);
			
			//lstAllDrugRoutes = dao.getAllDrugRouteList(_userVO);  // old
			lstAllDrugRoutes = opdDao.getDrugRouteList(_userVO);   //generic
			essentialMap.put(InpatientConfig.ESSENTIALS_LIST_ALL_DRUG_ROUTE_VO, lstAllDrugRoutes);
			
			lstDrugDoses = opdEssenDao.getDrugDosesList(_userVO);
			essentialMap.put(OpdConfig.ESSENTIALS_LIST_ALL_DRUG_DOSES, lstDrugDoses);
			
			drugExecLstByEndDate=dao.getAllDrugAdminListByAdminEndDate(patientDetailVO, _userVO);
			essentialMap.put(InpatientConfig.SELECTED_IVFLUIDS_LIST , drugExecLstByEndDate);
			
			drugExecLstByCrNo=dao.getExecDrugByCrNo(patientDetailVO, _userVO);
			essentialMap.put(InpatientConfig.EXECUTED_DRUG_LST_BY_CRNO , drugExecLstByCrNo);
			
			drugFrequencyMstVO = dao.getDrugFrequencyVOList(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS__DOSAGE_FREQUECY_ARRAY, drugFrequencyMstVO);
			
//			prevInstructionList=dao.getPrevInstListForPat(patientDetailVO.getPatCrNo(),patientDetailVO.getEpisodeCode(),_userVO);
//			essentialMap.put(InpatientConfig.PREV_INSTRUNCTION_LIST_FOR_PAT, prevInstructionList);
//			
//			prevActivityList=dao.getPrevActivityListForPat(patientDetailVO.getPatCrNo(),patientDetailVO.getEpisodeCode(),_userVO);
//			essentialMap.put(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT, prevActivityList);
//			
//			todayExtList=dao.getTodayExtDetailList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
//			essentialMap.put(InpatientConfig.TODAY_EXT_TREATMENT_LIST_FOR_PAT, todayExtList);
//			
//			execExtTreatList=dao.getExecutedExtTreatList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
//			essentialMap.put(InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST, execExtTreatList);
//			
//			execActivityList=dao.getExecutedActivityList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
//			essentialMap.put(InpatientConfig.EXECUTED_PAT_ACTIVITY_LST, execActivityList);
			
			/*
			
			
			
			
			drugExecLst=dao.getDrugAdminListByCRNo(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO, drugExecLst);
			
			
			
			
			
			
			*/
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if (hisDAO != null) 
				hisDAO.free();
			hisDAO = null;
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getExtAdministrationEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		
		List prevInstructionList=null;
		List prevActivityList=null;
		List todayExtList=null;
		List execExtTreatList=null;
		List execActivityList=null;
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
						
			prevInstructionList=dao.getPrevInstListForPat(patientDetailVO.getPatCrNo(),patientDetailVO.getEpisodeCode(),_userVO);
			essentialMap.put(InpatientConfig.PREV_INSTRUNCTION_LIST_FOR_PAT, prevInstructionList);
			
			prevActivityList=dao.getPrevActivityListForPat(patientDetailVO.getPatCrNo(),patientDetailVO.getEpisodeCode(),_userVO);
			essentialMap.put(InpatientConfig.PREV_ONE_TIME_ACTIVITY_LIST_FOR_PAT, prevActivityList);
			
			todayExtList=dao.getTodayExtDetailList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
			essentialMap.put(InpatientConfig.TODAY_EXT_TREATMENT_LIST_FOR_PAT, todayExtList);
			
			execExtTreatList=dao.getExecutedExtTreatList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
			essentialMap.put(InpatientConfig.EXECUTED_PAT_EXT_TREAT_LIST, execExtTreatList);
			
			execActivityList=dao.getExecutedActivityList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), patientDetailVO.getPatAdmNo(), _userVO);
			essentialMap.put(InpatientConfig.EXECUTED_PAT_ACTIVITY_LST, execActivityList);
			
			/*
				drugExecLst=dao.getDrugAdminListByCRNo(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
				essentialMap.put(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO, drugExecLst);
			*/
			
			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public List getDateWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		List drugExecLst=null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			
			drugExecLst=dao.getDateWiseTreatInfo(drugAdminDtlVO, _userVO);
			//essentialMap.put(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO, drugExecLst);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return drugExecLst;
	}
	
	public List getDrugWiseTreatInfo(DrugAdminDtlVO drugAdminDtlVO, UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List drugExecLst=null;
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			
			drugExecLst=dao.getDrugWiseTreatInfo(drugAdminDtlVO, _userVO);
			//essentialMap.put(InpatientConfig.ESSENTIALS_DRUG_EXEC_LIST_BY_CRNO, drugExecLst);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return drugExecLst;
	}
	
	
	public Map getPatDrugReactionEssential(PatientDetailVO patientDetailVO,DrugAdminDtlVO drugAdminDtlVO ,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List drugExecLst=null;
		List adminDateLst=null;
		List templateIdList=null;
		List prevDrugList=null;
		String maxAdminDate=""; 
				
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			DrugAdminDtlDAOi drugAdminDao=new DrugAdminDtlDAO(tx);
			DrugReactionDtlDAOi drugReactionDtlDAO=new DrugReactionDAO(tx);
			
			drugExecLst=dao.getALLDrugAdminListByCRNo(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS_ALL_DRUG_EXEC_LIST_BY_CRNO, drugExecLst);
			
			adminDateLst=dao.getAdminDateLst(patientDetailVO.getPatCrNo(),patientDetailVO.getEpisodeCode(),_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALS_ADMINISTRATION_DATE_LST , adminDateLst);
			
			maxAdminDate=drugAdminDao.getMaxAdminDate(drugAdminDtlVO, _userVO);
			essentialMap.put(InpatientConfig.MAX_ADMINISTRATION_DATE_BY_CRNO , maxAdminDate);
			
			templateIdList=drugReactionDtlDAO.getTemplateIdList(_userVO);
			essentialMap.put(InpatientConfig.TEMPLATEID_LIST_FOR_DRUG_REACTION , templateIdList);
			
			prevDrugList=drugReactionDtlDAO.getPreviousDrugReactionListByCrNo(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.PREV_DRUG_REACTION_LIST_BY_CRNO , prevDrugList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}


	public Map getStockEntryOfBloodEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List requestedComponentList=null;
		List aboList=null;
		List rhList=null;
		List componentListForCombo=null;
		List patBloodStockVOList=null;
		List patCrossMatchListByCrNo=null;
		List totalCrossMatchList=new ArrayList();
						
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
						
			requestedComponentList=dao.getReqBloodComponentList(patientDetailVO.getPatCrNo(), patientDetailVO.getEpisodeCode(), _userVO);
			essentialMap.put(InpatientConfig.ALL_REQUESTED_COMPONENT_LIST_BY_CRNO, requestedComponentList);
			
			for(int i=0;i<requestedComponentList.size();i++)
			{
				BloodRequisitionComponentDtlVO vo=new BloodRequisitionComponentDtlVO();
				vo=(BloodRequisitionComponentDtlVO)requestedComponentList.get(i);
				patCrossMatchListByCrNo=dao.getCrossMatchList(vo, _userVO);
				
				if(patCrossMatchListByCrNo!=null)
				{
					for(int j=0;j<patCrossMatchListByCrNo.size();j++)
					{
						PatBloodStockDtlVO stockVO=new PatBloodStockDtlVO();
						stockVO=(PatBloodStockDtlVO)patCrossMatchListByCrNo.get(j);
						String bagNo=stockVO.getBloodBagNo()+"("+stockVO.getBloodBagSequenceNo()+")";
						stockVO.setBloodBagNo(bagNo);
						totalCrossMatchList.add(stockVO);
					}
				}
			}
			
			
			
			
			essentialMap.put(InpatientConfig.CROSS_MATCH_LIST_BY_CRNO, totalCrossMatchList);
			
			aboList=dao.getBloodABO(_userVO);
			essentialMap.put(InpatientConfig.ALL_ABO_LIST, aboList);
			
			rhList=dao.getBloodRH();
			essentialMap.put(InpatientConfig.ALL_RH_LIST, rhList);	
			
			componentListForCombo=dao.getAllComponentListForCombo(_userVO);
			essentialMap.put(InpatientConfig.COMPONENT_LIST_FOR_COMBO, componentListForCombo);
			
			patBloodStockVOList=dao.getInStockBloodBagListByCrNo(patientDetailVO.getPatCrNo(), _userVO);
			essentialMap.put(InpatientConfig.INSTOCK_BLOODBAG_LIST_BYCRNO, patBloodStockVOList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public List getConsultantDetails(String unitCode,UserVO _UserVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List consultantLst=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			consultantLst = daoobj.getConsultantDetails(unitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return consultantLst;
	}

	public List getConsultantPhone(String empNo,UserVO _UserVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List phoneLst=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			phoneLst = daoobj.getConsultantPhone(empNo, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return phoneLst;
	}
	public List getUnitPhone(String unitCode,UserVO _UserVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List phoneLst=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			phoneLst = daoobj.getUnitPhone(unitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return phoneLst;
	}

	
	public List getPeonDetails(String unitCode,UserVO _UserVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List peonLst=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			peonLst = daoobj.getPeonDetails(unitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return peonLst;
	}

	
	public List getAllConsultantDetails(String unitCode,UserVO _UserVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List consultantLst=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			consultantLst = daoobj.getAllConsultantDetails(unitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return consultantLst;
	}

	
	public List getRoundByEssentials(String unitCode,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		List lstEmployee=null;
		try
		{
			tx.begin();
			lstEmployee=essentialDAO.getRoundByListUnitWise(unitCode, userVO);
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
		return lstEmployee;
	}

	
	public Map getBloodTransfusionEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List patBloodStockVOList=null;
								
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
						
			patBloodStockVOList=dao.getInStockNotTransfusedBloodBagListByCrNo(patientDetailVO.getPatCrNo(), _userVO);
			essentialMap.put(InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO, patBloodStockVOList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	public List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO)
	{
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
						
			lstBldTransDtlVO=dao.getPreviousBloodTransDtl(patCrNo,lstBldTransDtlVO, _userVO);
			//essentialMap.put(InpatientConfig.INSTOCK_NOTTRANSFUSED_BLOODBAG_LIST_BYCRNO, patBloodStockVOList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage()); //,essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstBldTransDtlVO;
	}
	
	
	
	public Map getBloodTransReactionEssential(PatientDetailVO patientDetailVO,UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List patBloodTransVOList=null;
		List templateIdList=null;
								
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			TransfusionReactionDtlDAOi transReactionDAO=new TransfusionReactionDtlDAO(tx);
						
			patBloodTransVOList=dao.getBloodTransReactionEssential(patientDetailVO.getPatCrNo(), _userVO);
			essentialMap.put(InpatientConfig.BLOOD_TRANSFUSION_VO_LIST, patBloodTransVOList);
			
			templateIdList=transReactionDAO.getTemplateIdList(_userVO);
			essentialMap.put(InpatientConfig.TEMPLATEID_LIST_FOR_TRANSFUSION_REACTION , templateIdList);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(),essentialMap);
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	/**
	 * Getting Essentials for ANC Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDetailEssentials(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		
		List<Entry> lstBloodGroups = null;
		List<Entry> lstCaste = null;
		List<Entry> lstOccupations = null;
		List<Entry> lstEducationStatus = null;
		List<Entry> lstMenstrualCycles = null;
		List<Entry> lstDeliveryPlaces = null;
		List<Entry> lstGenders = null;
		List<Entry> lstBirthTypes = null;
		List<Entry> lstDeliveryTypes = null;
		try
		{
			tx.begin();
			EssentialDAO regEssentialDAO = new EssentialDAO(tx);
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);

			lstBloodGroups=inpatientEssentialDAO.getBloodGroup(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST, lstBloodGroups);
			if(lstBloodGroups==null || lstBloodGroups.size()==0)
				throw new HisRecordNotFoundException("No Record for Blood Group Found");
			
			lstOccupations=regEssentialDAO.getOccupationDetail(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST, lstOccupations);
			
			lstCaste=inpatientEssentialDAO.getCasteList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST, lstCaste);

			lstEducationStatus=inpatientEssentialDAO.getEducationStatusList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST, lstEducationStatus);
			
			lstMenstrualCycles=inpatientEssentialDAO.getMenstrualCycleList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_MENSTRUAL_CYCLE_LIST, lstMenstrualCycles);

			lstDeliveryPlaces=inpatientEssentialDAO.getDeliveryPlaceList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_PLACE_LIST, lstDeliveryPlaces);

			lstGenders=regEssentialDAO.getGender(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST, lstGenders);

			lstBirthTypes=inpatientEssentialDAO.getBirthTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BIRTH_TYPE_LIST, lstBirthTypes);

			lstDeliveryTypes=inpatientEssentialDAO.getDeliveryTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST, lstDeliveryTypes);
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

	/**
	 * Getting ANC Detail Macros
	 * @param _userVO User Detail
	 * @return
	 */
	public List<Entry> getANCMacroDetail(String _processId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstMacros = null;
		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);

			lstMacros=inpatientEssentialDAO.getMacrosByProcessId(_processId, _userVO);
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
		return lstMacros;
	}

	/**
	 * Getting Essentials for ANC Delivery Detail
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCDeliveryDetailEssentials(UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		
		//List<Entry> lstBloodGroups = null;
		//List<Entry> lstCaste = null;
		//List<Entry> lstOccupations = null;
		//List<Entry> lstEducationStatus = null;
		//List<Entry> lstMenstrualCycles = null;
		//List<Entry> lstDeliveryPlaces = null;
		List<Entry> lstGenders = null;
		///List<Entry> lstBirthTypes = null;
		List<Entry> lstDeliveryTypes = null;
		List<Entry> lstInductionMethods = null;
		List<Entry> lstLaborRooms = null;
		List<Entry> lstLaborRoomAreas = null;
		List<Entry> lstPlacentaTypes = null;
		List<Entry> lstPlacentaRemovalMethods = null;
		List<Entry> lstComplications = null;
		try
		{
			tx.begin();
			//BloodBankEssentialDAOi bloodbankEssentialDAO = new BloodBankEssentialDAO(tx);
			EssentialDAO regEssentialDAO = new EssentialDAO(tx);
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);

			/*lstBloodGroups=bloodbankEssentialDAO.getBloodGroup(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST, lstBloodGroups);
			if(lstBloodGroups==null || lstBloodGroups.size()==0)
				throw new HisRecordNotFoundException("No Record for Blood Group Found");
			
			lstOccupations=regEssentialDAO.getOccupationDetail(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_OCCUPATION_LIST, lstOccupations);
			
			lstCaste=inpatientEssentialDAO.getCasteList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_CASTE_LIST, lstCaste);

			lstEducationStatus=inpatientEssentialDAO.getEducationStatusList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_EDUCATION_STATUS_LIST, lstEducationStatus);
			
			lstMenstrualCycles=inpatientEssentialDAO.getMenstrualCycleList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_MENSTRUAL_CYCLE_LIST, lstMenstrualCycles);



			lstBirthTypes=inpatientEssentialDAO.getBirthTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BIRTH_TYPE_LIST, lstBirthTypes);*/

			lstGenders=regEssentialDAO.getGender(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST, lstGenders);

			lstLaborRooms=inpatientEssentialDAO.getLaborRoomList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_LIST, lstLaborRooms);
			
			String[] areatypes = new String[]{InpatientConfig.LABOR_ROOM_AREATYPE_OT, InpatientConfig.LABOR_ROOM_AREATYPE_LABOR_RROM};
			lstLaborRoomAreas=inpatientEssentialDAO.getLaborRoomAreaListByAreaType(_userVO, areatypes);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_LABOR_ROOM_AREA_LIST, lstLaborRoomAreas);

			lstInductionMethods=inpatientEssentialDAO.getMethodList(InpatientConfig.METHOD_TYPE_INDUCTION_METHODS ,_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_INDUCTION_METHOD_LIST, lstInductionMethods);

			lstDeliveryTypes=inpatientEssentialDAO.getDeliveryTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_DELIVERY_TYPE_LIST, lstDeliveryTypes);

			lstPlacentaTypes=inpatientEssentialDAO.getPlacentaTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_TYPE_LIST, lstPlacentaTypes);

			lstPlacentaRemovalMethods=inpatientEssentialDAO.getMethodList(InpatientConfig.METHOD_TYPE_PLACENTA_REMOVAL_METHODS ,_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_PLACENTA_REMOVAL_METHOD_LIST, lstPlacentaRemovalMethods);
			
			lstComplications=inpatientEssentialDAO.getComplicationsList(InpatientConfig.COMPLICATION_TYPE_DELIVERY ,_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_COMPLICATIONS_LIST, lstComplications);
			
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

	/**
	 * Getting Essentials for ANC Neonatal Detail
	 * @param _ancNeoNatVO ANC Neo Natal Detail VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCNeonatalDetailEssentials(ANCNeonatalDetailVO _ancNeoNatVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		
		List<Entry> lstGenders = null;
		List<Entry> lstAnomolyType = null;
		List<Entry> lstBirthTraumaCause = null;
		List<Entry> lstApgarTime = null;
		List<ANCNeonatalDetailVO> lstNeoNatDtl = null;
		List<ANCNeonatalApgarVO> lstApgarDtl = null;
		try
		{
			tx.begin();
			//BloodBankEssentialDAOi bloodbankEssentialDAO = new BloodBankEssentialDAO(tx);
			EssentialDAO regEssentialDAO = new EssentialDAO(tx);
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);

			/*lstBloodGroups=bloodbankEssentialDAO.getBloodGroup(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BLOODGROUP_LIST, lstBloodGroups);
			if(lstBloodGroups==null || lstBloodGroups.size()==0)
				throw new HisRecordNotFoundException("No Record for Blood Group Found");*/
			
			lstGenders=regEssentialDAO.getGender(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_GENDER_LIST, lstGenders);

			lstAnomolyType=inpatientEssentialDAO.getAnomolyTypeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_ANOMOLY_TYPE_LIST, lstAnomolyType);

			lstBirthTraumaCause= inpatientEssentialDAO.getComplicationsList(InpatientConfig.COMPLICATION_TYPE_TRAUMA_CAUSE ,_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_BIRTH_TRAUMA_CAUSE_LIST, lstBirthTraumaCause);
			
			lstApgarTime = inpatientEssentialDAO.getApgarTimeList(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_APGAR_TIME_LIST, lstApgarTime);			
			
			// Outcome Datail
			ANCNeonatalDtlDAOi ancNeoNatDAO = new ANCNeonatalDtlDAO(tx);
			lstNeoNatDtl=ancNeoNatDAO.getLastAllNeoNatDtl(_ancNeoNatVO, _userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_LIST, lstNeoNatDtl);
			if(lstNeoNatDtl!=null && lstNeoNatDtl.size()>0)
			{
				_ancNeoNatVO.setGravidaNo(lstNeoNatDtl.get(0).getGravidaNo());
				// ANC Detail
				ANCDtlDAOi ancDetailDAO = new ANCDtlDAO(tx);
				ANCDetailVO ancDetailVO =  new ANCDetailVO();
				ancDetailVO.setPatCrNo(_ancNeoNatVO.getPatCrNo());
				ancDetailVO.setGravidaNo(_ancNeoNatVO.getGravidaNo());
				ancDetailVO = ancDetailDAO.getANCDetailByGravida(ancDetailVO, _userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_PATINET_ANC_DETAIL_VO, ancDetailVO);
	
				// ANC Delivery Detail
				ANCDeliveryDtlDAOi ancDeliveryDtlDAO = new ANCDeliveryDtlDAO(tx);
				ANCDeliveryDetailVO ancDelDtlVO =  new ANCDeliveryDetailVO();
				ancDelDtlVO.setPatCrNo(_ancNeoNatVO.getPatCrNo());
				ancDelDtlVO.setGravidaNo(_ancNeoNatVO.getGravidaNo());
				ancDelDtlVO = ancDeliveryDtlDAO.getANCDeliveryDetail(ancDelDtlVO, _userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_PATINET_ANC_DELIVERY_DETAIL, ancDelDtlVO);
			}
			
			ANCNeonatalApgarDtlDAOi ancNeoNatApgarDAO = new ANCNeonatalApgarDtlDAO(tx);
			lstApgarDtl = new ArrayList<ANCNeonatalApgarVO>();
			if(lstNeoNatDtl!=null && lstNeoNatDtl.size()>0)
			{
				ANCNeonatalApgarVO apgarVO = new ANCNeonatalApgarVO();
				ANCNeonatalDetailVO neonatDtlVO = lstNeoNatDtl.get(0);
				HelperMethods.populate(apgarVO, neonatDtlVO);				
				lstApgarDtl =  ancNeoNatApgarDAO.getAllNeoNatApgarDtl(apgarVO, _userVO);				
			}
			essentialMap.put(InpatientConfig.ANCDETAIL_DELIVERY_OUTCOME_APGAR_DETAIL_LIST, lstApgarDtl);
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
	
	
	public Map getPendingTasks(String deptUnitCode,String wardCode,String roomCode,UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List pendingConsentVOList=new ArrayList();
		List pendingTreatmentList=new ArrayList();
		List pendingSampleCollectionList=new ArrayList();
		List pendingInstructionList=new ArrayList();
		List pendingMonitoringList=new ArrayList();
		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);
			pendingConsentVOList=inpatientEssentialDAO.getPendingConsentList(deptUnitCode, wardCode, roomCode, _userVO);
			essentialMap.put(InpatientConfig.PENDING_CONSENT_PATIENT_LIST, pendingConsentVOList);
			pendingTreatmentList=inpatientEssentialDAO.getPendingTreatmentList(_userVO);
			essentialMap.put(InpatientConfig.PENDING_TREATMENT_LIST, pendingTreatmentList);
			pendingSampleCollectionList=inpatientEssentialDAO.getPendingSampleCollectionList(wardCode, deptUnitCode, roomCode, _userVO);
			essentialMap.put(InpatientConfig.PENDING_SAMPLE_COLLECTION_LIST, pendingSampleCollectionList);
			//pendingInstructionList=inpatientEssentialDAO.getPendingInstructionList(_userVO);
			essentialMap.put(InpatientConfig.PENDING_INSTRUCTION_LIST, pendingInstructionList);
			pendingMonitoringList=inpatientEssentialDAO.getPendingMonitoringList(_userVO);
			essentialMap.put(InpatientConfig.PENDING_MONITORING_LIST, pendingMonitoringList);
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
	
	public Map getPatientChronicNAllergy(String crNo,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		PatAllergyDtlVO[] arrPatAllergyDtlVO=null;
		PatientAlertsDetailVO[] arrPatAssignedAlertVO=null;
		List<PatientAlertsDetailVO> lstPatAssignedAlertVO=null;
		PatAllergyDtlVO patAllergyVO = new PatAllergyDtlVO();
		
		try
		{
			tx.begin();
			patAllergyVO.setPatCrNo(crNo);
			PatAllergyDtlDAO patAllergyDtlDAO = new PatAllergyDtlDAO(tx);
			PatAlertsDetailDAOi patAlertDAO=new PatAlertsDetailDAO(tx);
			
			arrPatAllergyDtlVO=patAllergyDtlDAO.getEpisodeAllergiesByPatient(patAllergyVO, userVO);
			essentialMap.put(InpatientConfig.PATIENT_CURRENT_ALLERGY_VO_ARR, arrPatAllergyDtlVO);
			
			
			lstPatAssignedAlertVO=patAlertDAO.getPatientAssignedAlert(crNo,userVO);
			arrPatAssignedAlertVO = new PatientAlertsDetailVO[lstPatAssignedAlertVO.size()];
			int i=0;
			for(PatientAlertsDetailVO vo : lstPatAssignedAlertVO)
				arrPatAssignedAlertVO[i++] = vo;
				
			essentialMap.put(InpatientConfig.PATIENT_CURRENT_CHRONIC_DISEASE_VO_ARR, arrPatAssignedAlertVO);
			
			
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
	
	public Map getDischargeStatusListNProfileStatus(PatientDetailVO patientVO,UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDischargeStatus=new ArrayList();
		String profileStatus="";
		
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			
			lstDischargeStatus=dao.getDischargeStatusList(userVO);
			essentialMap.put(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST, lstDischargeStatus);
			
			profileStatus=dao.getPatientProfileStatus(patientVO,userVO);
			essentialMap.put(InpatientConfig.ESSENTIAL_PROFILE_STATUS, profileStatus);
			
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	
	public List<Entry> getReactionSummaryDetail(String _processId, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstMacros = null;
		try
		{
			tx.begin();
			InPatientEssentialDAOi inpatientEssentialDAO = new InPatientEssentialDAO(tx);

			lstMacros=inpatientEssentialDAO.getMacrosByProcessId(_processId, _userVO);
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
		return lstMacros;
	}
	
	
	
	public Map getANCTeamDtlEssential(UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List consultantLst=null;
		List roleList=null;
				
		try
		{
			tx.begin();
			
			InPatientEssentialDAOi daoobj = new InPatientEssentialDAO(tx);
			
			consultantLst = daoobj.getAllConsultantDetails(InpatientConfig.ALL_DEPARTMENTS, userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_CONSULTANT_LIST_FOR_TEAM_DTL, consultantLst);
		
			roleList=daoobj.getAllRoleList(userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_ALL_ROLE_LIST, roleList);
			
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

	/**
	 * Getting Essentials for ANC Child Handover
	 * @param _ancChildHandoverVO ANC Child Handover VO
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCChildHandoverEssentials(ANCChildHandoverDetailVO _ancChildHandoverVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		
		List<Entry> lstRelationships = null;
		ANCNeonatalDetailVO voANCNeoNat = null;
		ANCChildHandoverDetailVO voANCChildHandover = null;
		try
		{
			tx.begin();
			
			ANCNeonatalDtlDAOi ancNeoNatDAO = new ANCNeonatalDtlDAO(tx);
			voANCNeoNat = new ANCNeonatalDetailVO();
			voANCNeoNat.setChildCrNo(_ancChildHandoverVO.getPatCrNo());
			voANCNeoNat.setChildAdmissionNo(_ancChildHandoverVO.getAdmissionNo());
			voANCNeoNat=ancNeoNatDAO.getNeoNatDtlByChild(voANCNeoNat, _userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_ADM_DETAIL, voANCNeoNat);

			if(voANCNeoNat!=null)
			{
				EssentialDAO regEssentialDAO = new EssentialDAO(tx);
				lstRelationships=regEssentialDAO.getRelationsList(_userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_RELATIONSHIP_LIST, lstRelationships);

				ANCChildHandoverDtlDAOi ancNeoNatHandoverDAO = new ANCChildHandoverDtlDAO(tx);
				voANCChildHandover=ancNeoNatHandoverDAO.get(_ancChildHandoverVO, _userVO);
				if(voANCChildHandover!=null)	essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_NEONAT_HANDOVER_DETAIL, voANCChildHandover);
			}
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

	/**
	 * Getting Essentials for ANC Trimester CheckList Entry
	 * @param _ancDtlVO ANC Detail VO
	 * @param _sysdate System Date Object
	 * @param _userVO User Detail
	 * @return
	 */
	public Map<String, Object> getANCTrimesterChklistEssentials(ANCDetailVO _ancDtlVO, Date _sysdate, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		
		List<ANCTrimesterChecklistMasterVO> lstAllTriChkList = null;
		ANCDetailVO detailVO = null;
		List<ANCChecklistDetailVO> lstDrugChklst = null;
		List<ANCChecklistDetailVO> lstInvstChklst = null;
		//List<Entry> lstRelationships = null;
		//ANCNeonatalDetailVO voANCNeoNat = null;
		//ANCChildHandoverDetailVO voANCChildHandover = null;
		try
		{
			tx.begin();
			
			ANCTrimesterChklistMasterDAOi ancTriChklstDAO = new ANCTrimesterChklistMasterDAO(tx);
			lstAllTriChkList = ancTriChklstDAO.getAll(_userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_CHECKLIST_ALL_TRIMESTER_LIST, lstAllTriChkList);

			ANCDtlDAOi dao=new ANCDtlDAO(tx);
			detailVO=dao.getANCDetailByEpisode(_ancDtlVO, _userVO);
			essentialMap.put(InpatientConfig.ANCDETAIL_PATIENT_ANC_DETAIL, detailVO);
			
			if(detailVO!=null)
			{
				// Finding Patient Trimester
				Date gestStartDate = WebUTIL.getDateFromString(detailVO.getGestationStartDate(), "dd-MMM-yyyy");
				Calendar calGestStartDate = Calendar.getInstance();
				calGestStartDate.setTime(gestStartDate);
				Calendar calSysdate = Calendar.getInstance();
				calSysdate.setTime(_sysdate);
				int trimester = 0;
				while(calGestStartDate.before(calSysdate) && trimester!=3)
				{
					trimester++;
					calGestStartDate.add(Calendar.MONTH, Integer.parseInt(InpatientConfig.TRIMESTER_DURATION_IN_MONTHS));
				}
				detailVO.setTrimester(Integer.toString(trimester));

				// Added CheckList Detail
				
				ANCTrimesterChklistDtlDAOi ancChklstDtlDAO = new ANCTrimesterChklistDtlDAO(tx);
				ANCChecklistDetailVO voChklstDtl = new ANCChecklistDetailVO();
				HelperMethods.populate(voChklstDtl, detailVO);
				
				lstDrugChklst = ancChklstDtlDAO.getChecklistDtlTypeWise(voChklstDtl, InpatientConfig.TRIMESTER_CHECKLIST_TYPE_DRUG, _userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_DRUGS_CHECKLIST, lstDrugChklst);
				
				lstInvstChklst = ancChklstDtlDAO.getChecklistDtlTypeWise(voChklstDtl, InpatientConfig.TRIMESTER_CHECKLIST_TYPE_INVESTIGATION, _userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_ADDED_INVESTIGATIONS_CHECKLIST, lstInvstChklst);

				lstInvstChklst = ancChklstDtlDAO.getChecklistDtlTypeWise(voChklstDtl, InpatientConfig.TRIMESTER_CHECKLIST_TYPE_INVESTIGATION, _userVO);
				essentialMap.put(InpatientConfig.ANCDETAIL_ESSENTIAL_GIVEN_INT_INVESTIGATIONS_CHECKLIST, lstInvstChklst);
			}
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

	// Getting ICD Code Essentials
	public Map getICDAllEssentials(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List diagnosisCodeList = null;
		
		/**Added by Vasu on 16.Nov.2018 to get ICD-O Essentials at OPD Desk footer*/
        List diagnosisSiteCodeList = null;
        List morphologyCodeList = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			
			OpdEssentialDAO opdEssDAO = new OpdEssentialDAO(tx);
			
			diagnosisCodeList = opdEssDAO.getICDDiseaseCodeList(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICD_DISEASE_LIST, diagnosisCodeList);
			
			diagnosisSiteCodeList = opdEssDAO.getDiagnosisSiteCodeListForICDEssentials(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICDO_DIAGNOSIS_SITE_LIST , diagnosisSiteCodeList);

			morphologyCodeList = opdEssDAO.getMorphologyCodeListForICDEssentials(_userVO);
			essentialMap.put(DynamicDeskConfig.DYNAMIC_DESK_ICDO_MORPHOLOGY_LIST , morphologyCodeList);
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
	
	public List getWardBasedOnHospitalDepartmentUnit(String _hosCode,String _deptCode,String _deptUnitCode, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List WardList = null;
		try {
			tx.begin();
			InPatientEssentialDAOi objEssentialDAO = new InPatientEssentialDAO(tx);
			WardList = objEssentialDAO.getWardBasedOnHospitalDepartmentUnit(_hosCode, _deptCode, _deptUnitCode, _userVO);
		} catch (HisRecordNotFoundException e) {

			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		}

		catch (HisDataAccessException e) {
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
		return WardList;
	}
	
	public Map getRegAndPatCategory(UserVO _userVO){
		Map essentialMap = new HashMap();
		List lstPrimaryCat = null;
		// List lstRegCat=null;
		// List lstDepartment=null;
		List lstregCat = null;
		List lstAllPrimaryCat = null;
		List hospitalList=null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			InPatientEssentialDAOi objEssentialDAO = new InPatientEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);
			
			lstAllPrimaryCat = objEssentialDAO.getPatientCategoryList(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALBO_OPTION_PATIENT_CATEGORY,lstAllPrimaryCat);
			
			lstPrimaryCat = objEssentialDAO.getPrimaryCat(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,lstPrimaryCat);
			
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
			lstregCat = objEssentialDAO.getRegistrationCategory();
			essentialMap.put(InpatientConfig.ESSENTIALBO_OPTION_REG_CATEGORY,lstregCat);

		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;
	}
	
	/**
	 * Getting Hospital Essential Combo
	 * @param _userVO
	 * @return
	 * Created By Adil Wasi at 04-Dec-2012
	 */
	public Map<String,String> getHospitalEssentialCombo(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List lstHospitalCombo = null;


		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			InPatientEssentialDAO objEssentialDAO = new InPatientEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(tx);

			lstHospitalCombo = objEssentialDAO.getAllHospitalsSeatIDWise(_userVO);
			essentialMap.put(InpatientConfig.HOSPITAL_COMBO_LIST, lstHospitalCombo);
				
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisException e)
		{
			tx.rollback();
			throw new HisException();
		}
		catch (Exception e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}
	
	public Map getAllGlobalDepartment(UserVO _userVO) {

		Map essentialMap = new HashMap();
		List lstDepartment = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			InPatientEssentialDAO objEssentialDAO = new InPatientEssentialDAO(tx);
			GlobalEssentialDAO objGlobalEssentialDAO = new GlobalEssentialDAO(
					tx);

			lstDepartment = objEssentialDAO.getAllGlobalDepartment(_userVO);
			essentialMap.put(InpatientConfig.ESSENTIAL_BO_OPTION_ALLDEPARMENT,lstDepartment);
			Date dt = objGlobalEssentialDAO.getSystemDate(new Date());
			essentialMap.put(Config.SYSDATEOBJECT, dt);
		} catch (HisRecordNotFoundException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return essentialMap;

	}

	public Map getDischargeStatus(UserVO _uservo) {
		
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map essentialMap=new HashMap();
		List lstDischargeStatus=new ArrayList();
		
		try
		{
			tx.begin();
			InPatientEssentialDAOi dao = new InPatientEssentialDAO(tx);
			
			lstDischargeStatus=dao.getDischargeStatusList(_uservo);
			essentialMap.put(InpatientConfig.PATIENT_DISCHARGE_REQUEST_LIST, lstDischargeStatus);
			
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
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public Map<String, Object> getTodayAllPatientList_AJAX(PatientDetailVO patientDetailVO, UserVO _userVO, int p_page,int p_limit, String p_sidx, String p_sord, String p_where, String deskType) 
	{	
		Map<String, Object> mapObj = null;		

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try {
			tx.begin();
			DailyPatientDAO ObjDAO = new DailyPatientDAO(tx);
			mapObj = ObjDAO.getAllPatientList_AJAX("1", patientDetailVO, _userVO, p_page, p_limit, p_sidx, p_sord, p_where, deskType);			
		}
		catch (HisRecordNotFoundException e) {
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		} catch (HisApplicationExecutionException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e) {
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		} catch (Exception e) {
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		} finally {
			tx.close();
		}
		return mapObj;
	}

	public List getIpdDeskEssentials(UserVO userVO) 
	{
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			lstUnit=essentialDAO.getDeptUnitList(userVO);
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstUnit;
	}
	
	//Added by Vasu on 15.sept.2018
	public List getIpdNursingDeskEssentials(UserVO userVO) 
	{
		List lstUnit=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		GlobalClinicalDAO daoGlobalClinical = new GlobalClinicalDAO();
		HisDAO hisDAO = new HisDAO("IPDNursingDesk","InPatientEssentialBO");
		
		UnitMasterVO voUnit_p = new UnitMasterVO();
		try
		{
			tx.begin();
			//lstUnit=essentialDAO.getDeptUnitList(userVO);
			lstUnit=essentialDAO.getDeptUnitListForIPDNursingDesk(userVO);
			
			
			//*---------
			//lstUnit = daoGlobalClinical.getUnitsList(hisDAO, "5", voUnit_p, userVO);
			
			
			/*synchronized (hisDAO)
			{
				hisDAO.fire();
			}*/

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			if (hisDAO != null) 
				hisDAO.free();
			hisDAO = null;
			
			tx.close();
		}
		return lstUnit;
	}
	
	//Added by Vasu on 15.Sept.2018
	public List getWardOnBasisOfUnitCodeForIPDNursing(String unitCode,UserVO userVO)
	{
		List lstWard=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		InPatientEssentialDAOi essentialDAO=new InPatientEssentialDAO(tx);
		try
		{
			tx.begin();
			//lstWard=essentialDAO.getWardOnBasisOfUnitCode(unitCode,userVO);
			lstWard=essentialDAO.getWardOnBasisOfUnitCodeForIPDNursing(unitCode,userVO);			
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			e.printStackTrace();
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
		return lstWard;
	}
	
}

