package inpatient.masters.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Entry;
import hisglobal.vo.AbortionMethodMasterVO;
import hisglobal.vo.AbortionTypeMasterVO;
import hisglobal.vo.AnomalyTypeMasterVO;
import hisglobal.vo.ComplicationMasterVO;
import hisglobal.vo.DeliveryPlaceMasterVO;
import hisglobal.vo.HealthWorkerMasterVO;
import hisglobal.vo.IntakeOutputParaMasterVO;
import hisglobal.vo.LaborRoomAreaMasterVO;
import hisglobal.vo.LaborRoomMasterVO;
import hisglobal.vo.MethodMasterVO;
import hisglobal.vo.PlacentaTypeMasterVO;
import hisglobal.vo.UnitInvParaMappingVO;
import hisglobal.vo.UserVO;
import inpatient.InpatientConfig;
import inpatient.masters.dao.AbortionMethodMasterDAO;
import inpatient.masters.dao.AbortionMethodMasterDAOi;
import inpatient.masters.dao.AbortionTypeMstDAO;
import inpatient.masters.dao.AbortionTypeMstDAOi;
import inpatient.masters.dao.AnomalyTypeMstDAO;
import inpatient.masters.dao.AnomalyTypeMstDAOi;
import inpatient.masters.dao.ComplicationMstDAO;
import inpatient.masters.dao.ComplicationMstDAOi;
import inpatient.masters.dao.DeliveryPlaceMstDAO;
import inpatient.masters.dao.DeliveryPlaceMstDAOi;
import inpatient.masters.dao.HealthWorkerMstDAO;
import inpatient.masters.dao.HealthWorkerMstDAOi;
import inpatient.masters.dao.IntakeOutputParaMstDAO;
import inpatient.masters.dao.IntakeOutputParaMstDAOi;
import inpatient.masters.dao.LaborRoomAreaMstDAO;
import inpatient.masters.dao.LaborRoomMasterDAO;
import inpatient.masters.dao.LaborRoomMasterDAOi;
import inpatient.masters.dao.MethodMasterDAO;
import inpatient.masters.dao.MethodMasterDAOi;
import inpatient.masters.dao.PlacentaTypeMstDAO;
import inpatient.masters.dao.PlacentaTypeMstDAOi;
import inpatient.masters.dao.UnitInvParaMappingMstDAO;
import inpatient.transaction.dao.InPatientEssentialDAO;
import inpatient.transaction.dao.InPatientEssentialDAOi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InpatientMasterBO implements InpatientMasterBOi
{

	// //////////////////////////////////////////////////

	public void saveAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String abortionTypeName = "";

		try
		{
			tx.begin();
			AbortionTypeMstDAOi abortionDAO = new AbortionTypeMstDAO(tx);
			abortionTypeName = abortionDAO.checkDuplicateAbortionTypeName(abortionTypeMasterVO, userVO);

			if (abortionTypeName.equals("0"))
			{
				abortionDAO.create(abortionTypeMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Abortion Type Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public AbortionTypeMasterVO getDataForAbortionTypeModify(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		AbortionTypeMasterVO vo = new AbortionTypeMasterVO();
		try
		{
			tx.begin();
			AbortionTypeMstDAOi abortionDAO = new AbortionTypeMstDAO(tx);
			vo = abortionDAO.getDataForModify(abortionTypeMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String abortionName = "";

		try
		{
			tx.begin();
			AbortionTypeMstDAOi abortionDAO = new AbortionTypeMstDAO(tx);
			abortionName = abortionDAO.checkDuplicateNameForModify(abortionTypeMasterVO, _UserVO);

			if (abortionName.equals("0"))
			{
				abortionDAO.updateAbortionTypeMaster(abortionTypeMasterVO, _UserVO);
				abortionDAO.modifySaveAbortionTypeMaster(abortionTypeMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Abortion Type Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj = new UnitInvParaMappingMstDAO(tx);
			daoobj.createUnitWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void updateTableUnitWise(String _unitId, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj = new UnitInvParaMappingMstDAO(tx);
			daoobj.updateTableUnitWise(_unitId, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void updateTableWardWise(String _unitId, String _wardCode, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj = new UnitInvParaMappingMstDAO(tx);
			daoobj.updateTableWardWise(_unitId, _wardCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj = new UnitInvParaMappingMstDAO(tx);
			daoobj.createUnitWardWise(_voUDMT, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public List gettingWards(String _deptUnitCode, UserVO _UserVO)
	{

		List wards = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			UnitInvParaMappingMstDAO daoobj = new UnitInvParaMappingMstDAO(tx);
			wards = daoobj.gettingWards(_deptUnitCode, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return wards;
	}

	public void saveAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String abortionMethodName = "";

		try
		{
			tx.begin();
			AbortionMethodMasterDAOi abortionDAO = new AbortionMethodMasterDAO(tx);
			abortionMethodName = abortionDAO.checkDuplicateAbortionMethodName(abortionMethodMasterVO, userVO);

			if (abortionMethodName.equals("0"))
			{
				abortionDAO.create(abortionMethodMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Abortion Method Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public AbortionMethodMasterVO getDataForAbortionMethodModify(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		AbortionMethodMasterVO vo = new AbortionMethodMasterVO();
		try
		{
			tx.begin();
			AbortionMethodMasterDAOi abortionDAO = new AbortionMethodMasterDAO(tx);
			vo = abortionDAO.getDataForModify(abortionMethodMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String abortionMethodName = "";

		try
		{
			tx.begin();
			AbortionMethodMasterDAOi abortionDAO = new AbortionMethodMasterDAO(tx);
			abortionMethodName = abortionDAO.checkDuplicateNameForModify(abortionMethodMasterVO, _UserVO);

			if (abortionMethodName.equals("0"))
			{
				abortionDAO.updateAbortionMethodMaster(abortionMethodMasterVO, _UserVO);
				abortionDAO.modifySaveAbortionMethodMaster(abortionMethodMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Abortion Method Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public String getAbortionTypeName(String typeID, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String typeName = "";

		try
		{
			tx.begin();
			AbortionMethodMasterDAOi abortionDAO = new AbortionMethodMasterDAO(tx);
			typeName = abortionDAO.getAbortionTypeName(typeID, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....  BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return typeName;

	}

	public void saveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String compName = "";
		try
		{
			tx.begin();
			ComplicationMstDAOi abortionDAO = new ComplicationMstDAO(tx);
			compName = abortionDAO.checkDuplicateCompName(complicationMasterVO, userVO);
			if (compName.equals("0"))
			{
				abortionDAO.create(complicationMasterVO, userVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Complication Name Already Exists");
			}
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
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
	}

	public ComplicationMasterVO getDataForCompModify(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		ComplicationMasterVO vo = new ComplicationMasterVO();
		try
		{
			tx.begin();
			ComplicationMstDAOi abortionDAO = new ComplicationMstDAO(tx);
			vo = abortionDAO.getDataForModify(complicationMasterVO, _UserVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
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
		return vo;
	}

	public void saveModCompMaster(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String compName = "";
		try
		{
			tx.begin();
			ComplicationMstDAOi abortionDAO = new ComplicationMstDAO(tx);
			compName = abortionDAO.checkDuplicateNameForModify(complicationMasterVO, _UserVO);

			if (compName.equals("0"))
			{
				abortionDAO.updateCompMaster(complicationMasterVO, _UserVO);
				abortionDAO.modifySaveCompMaster(complicationMasterVO, _UserVO);
			}
			else
			{
				throw new HisDuplicateRecordException("Complication name Already Exists");
			}
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();
			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			throw new HisDataAccessException();
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
	}

	public void saveDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String placeName = "";

		try
		{
			tx.begin();
			DeliveryPlaceMstDAOi placeDAO = new DeliveryPlaceMstDAO(tx);

			placeName = placeDAO.checkDuplicateDeliveryPlaceName(deliveryPlaceMasterVO, userVO);

			if (placeName.equals("0"))
			{
				placeDAO.create(deliveryPlaceMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Delivery Place Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public DeliveryPlaceMasterVO getDataForDeliveryPlaceModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		DeliveryPlaceMasterVO vo = new DeliveryPlaceMasterVO();
		try
		{
			tx.begin();
			DeliveryPlaceMstDAOi placeDAO = new DeliveryPlaceMstDAO(tx);
			vo = placeDAO.getDataForModify(deliveryPlaceMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String placeName = "";

		try
		{
			tx.begin();
			DeliveryPlaceMstDAOi placeDAO = new DeliveryPlaceMstDAO(tx);
			placeName = placeDAO.checkDuplicateNameForModify(deliveryPlaceMasterVO, _UserVO);

			if (placeName.equals("0"))
			{
				placeDAO.updateDeliveryPlaceMaster(deliveryPlaceMasterVO, _UserVO);
				placeDAO.modifySaveDeliveryPlaceMaster(deliveryPlaceMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Delivery Place Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public void saveMethodMaster(MethodMasterVO methodMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String methodName = "";

		try
		{
			tx.begin();
			MethodMasterDAOi methodDAO = new MethodMasterDAO(tx);

			methodName = methodDAO.checkDuplicateMethodName(methodMasterVO, userVO);

			if (methodName.equals("0"))
			{
				methodDAO.create(methodMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Method Name Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public MethodMasterVO getDataForMethodMasterModify(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		MethodMasterVO vo = new MethodMasterVO();
		try
		{
			tx.begin();
			MethodMasterDAOi methodDAO = new MethodMasterDAO(tx);
			vo = methodDAO.getDataForModify(methodMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModMethodMaster(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String methodName = "";

		try
		{
			tx.begin();
			MethodMasterDAOi methodDAO = new MethodMasterDAO(tx);
			methodName = methodDAO.checkDuplicateNameForModify(methodMasterVO, _UserVO);

			if (methodName.equals("0"))
			{
				methodDAO.updateMethodMaster(methodMasterVO, _UserVO);
				methodDAO.modifySaveMethodMaster(methodMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Method Name Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public void savePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String placentaTypeName = "";

		try
		{
			tx.begin();
			PlacentaTypeMstDAOi placentaDAO = new PlacentaTypeMstDAO(tx);

			placentaTypeName = placentaDAO.checkDuplicatePlacentaTypeName(placentaTypeMasterVO, userVO);

			if (placentaTypeName.equals("0"))
			{
				placentaDAO.create(placentaTypeMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Placenta Type Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public PlacentaTypeMasterVO getDataForPlacentaTypeMasterModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		PlacentaTypeMasterVO vo = new PlacentaTypeMasterVO();
		try
		{
			tx.begin();
			PlacentaTypeMstDAOi placentaDAO = new PlacentaTypeMstDAO(tx);
			vo = placentaDAO.getDataForModify(placentaTypeMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModPlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String placentaTypeName = "";

		try
		{
			tx.begin();
			PlacentaTypeMstDAOi placentaDAO = new PlacentaTypeMstDAO(tx);
			placentaTypeName = placentaDAO.checkDuplicateNameForModify(placentaTypeMasterVO, _UserVO);

			if (placentaTypeName.equals("0"))
			{
				placentaDAO.updatePlacentaTypeMaster(placentaTypeMasterVO, _UserVO);
				placentaDAO.modifySavePlacentaTypeMaster(placentaTypeMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Placenta Type Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public void saveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String anomalyTypeName = "";

		try
		{
			tx.begin();
			AnomalyTypeMstDAOi anomalyDAO = new AnomalyTypeMstDAO(tx);
			anomalyTypeName = anomalyDAO.checkDuplicateAnomalyTypeName(anomalyTypeMasterVO, userVO);

			if (anomalyTypeName.equals("0"))
			{
				anomalyDAO.create(anomalyTypeMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Anomaly Type Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public AnomalyTypeMasterVO getDataForAnomalyTypeModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		AnomalyTypeMasterVO vo = new AnomalyTypeMasterVO();
		try
		{
			tx.begin();
			AnomalyTypeMstDAOi anomalyDAO = new AnomalyTypeMstDAO(tx);
			vo = anomalyDAO.getDataForModify(anomalyTypeMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String abortionName = "";

		try
		{
			tx.begin();
			AnomalyTypeMstDAOi anomalyDAO = new AnomalyTypeMstDAO(tx);
			abortionName = anomalyDAO.checkDuplicateNameForModify(anomalyTypeMasterVO, _UserVO);

			if (abortionName.equals("0"))
			{
				anomalyDAO.updateAnomalyTypeMaster(anomalyTypeMasterVO, _UserVO);
				anomalyDAO.modifySaveAnomalyTypeMaster(anomalyTypeMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Anomaly Type Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public void saveHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String workerName = "";

		try
		{
			tx.begin();
			HealthWorkerMstDAOi anomalyDAO = new HealthWorkerMstDAO(tx);
			workerName = anomalyDAO.checkDuplicateHealthWorkerName(healthWorkerMasterVO, userVO);

			if (workerName.equals("0"))
			{
				anomalyDAO.create(healthWorkerMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Health Worker Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
	}

	public HealthWorkerMasterVO getDataForHealthWorkerModify(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		HealthWorkerMasterVO vo = new HealthWorkerMasterVO();
		try
		{
			tx.begin();
			HealthWorkerMstDAOi workerDAO = new HealthWorkerMstDAO(tx);
			vo = workerDAO.getDataForModify(healthWorkerMasterVO, _UserVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}

	public boolean saveModHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String abortionName = "";

		try
		{
			tx.begin();
			HealthWorkerMstDAOi workerDAO = new HealthWorkerMstDAO(tx);
			abortionName = workerDAO.checkDuplicateNameForModify(healthWorkerMasterVO, _UserVO);

			if (abortionName.equals("0"))
			{
				workerDAO.updateHealthWorkerMaster(healthWorkerMasterVO, _UserVO);
				workerDAO.modifySaveHealthWorkerMaster(healthWorkerMasterVO, _UserVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Health Worker Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}

	public Map getLaborRoomAreaMstEssentails(UserVO _userVO)
	{
		Map essentialMap = new HashMap();
		List laborRoomList = null;
		// List areaTypeList=null;
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			InPatientEssentialDAO inpatientEssDAO = new InPatientEssentialDAO(tx);

			laborRoomList = inpatientEssDAO.getLaborRoomList(_userVO);
			// areaTypeList=inpatientEssDAO.getAreaType(_userVO);

			essentialMap.put(InpatientConfig.ESSENTIALBO_LIST_LABOR_ROOM, laborRoomList);
			// essentialMap.put(InpatientConfig.ESSENTIALBO_LIST_AREA_TYPE, areaTypeList);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			LaborRoomAreaMstDAO daoObject = new LaborRoomAreaMstDAO(tx);
			boolean flag = daoObject.checkDuplicateAreaType(laborRoomAreaMstVO, _userVO);
			if (flag)
			{
				daoObject.create(laborRoomAreaMstVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Description already exists.");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Inpatient Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public LaborRoomAreaMasterVO getModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		LaborRoomAreaMasterVO _laborRoomAreaMstVO = new LaborRoomAreaMasterVO();
		try
		{
			tx.begin();
			LaborRoomAreaMstDAO daoObject = new LaborRoomAreaMstDAO(tx);

			_laborRoomAreaMstVO = daoObject.fetchRecord(laborRoomAreaMstVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _laborRoomAreaMstVO;
	}

	public void saveModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			LaborRoomAreaMstDAO daoObject = new LaborRoomAreaMstDAO(tx);

			boolean flag = daoObject.check_Modify_Duplicate(laborRoomAreaMstVO, _userVO);
			if (flag)
			{
				daoObject.update(laborRoomAreaMstVO, _userVO);
				daoObject.modifySave(laborRoomAreaMstVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Already Exists.");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public boolean saveDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			LaborRoomMasterDAO daoObject = new LaborRoomMasterDAO(tx);
			flag = daoObject.checkDuplicateLaborRoom(laborRoomMasterVO, _userVO);
			if (flag)
			{
				daoObject.create(laborRoomMasterVO, _userVO);
				return flag;
			}
			else
			{

				throw new HisRecordNotFoundException("This Menu For This Desk Already Added.");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... inpatient Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public LaborRoomMasterVO getModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		LaborRoomMasterVO _laborRoomMasterVO = new LaborRoomMasterVO();
		try
		{
			tx.begin();
			LaborRoomMasterDAO daoObject = new LaborRoomMasterDAO(tx);

			_laborRoomMasterVO = daoObject.fetchRecord(laborRoomMasterVO, _userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return _laborRoomMasterVO;
	}

	public Map<String, Object> getLaborRoomMasterEssentails(UserVO _userVO)
	{
		Map<String, Object> essentialMap = new HashMap<String, Object>();
		// List laborRoomList=null;
		List<Entry> lstDepts = new ArrayList<Entry>();
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			InPatientEssentialDAOi inpEssDAO = new InPatientEssentialDAO(tx);
			lstDepts = inpEssDAO.getAllDepartmentList(_userVO);
			essentialMap.put(InpatientConfig.INPATIENT_ESSENTIAL_LIST_ALL_CLINICAL_DEPARTMENTS, lstDepts);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage(), essentialMap);
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}

		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Essential BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return essentialMap;
	}

	public void saveModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		try
		{
			tx.begin();
			LaborRoomMasterDAOi daoObject = new LaborRoomMasterDAO(tx);

			boolean flag = daoObject.check_Modify_DuplicateLaborRoom(laborRoomMasterVO, _userVO);
			if (flag)
			{
				daoObject.update(laborRoomMasterVO, _userVO);
				daoObject.modifySave(laborRoomMasterVO, _userVO);
			}
			else
			{

				throw new HisRecordNotFoundException("This Menu For This Desk Already Added.");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... Opd Master BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	public void saveIntakeOutputParaMaster(IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String paraName = "";

		try
		{
			tx.begin();
			IntakeOutputParaMstDAOi inoutparaDAO = new IntakeOutputParaMstDAO(tx);
			paraName = inoutparaDAO.checkDuplicateParaName(inoutparaMasterVO, userVO);

			if (paraName.equals("0"))
			{
			inoutparaDAO.saveIntakeOutputParaMaster(inoutparaMasterVO, userVO);

			}
			else
			{
				throw new HisDuplicateRecordException("Anomaly Type Already Exists");
			}

		}

		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDuplicateRecordException e)
		{
			tx.rollback();

			throw new HisDuplicateRecordException(e.getMessage());
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
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
		
		
	}
	public IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();

		IntakeOutputParaMasterVO vo = new IntakeOutputParaMasterVO();
		try
		{
			tx.begin();
			IntakeOutputParaMstDAOi inoutparaDAO = new IntakeOutputParaMstDAO(tx);
			vo = inoutparaDAO.getDataForModify(inoutparaMasterVO, userVO);

		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error.... BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return vo;

	}
	public boolean saveModInOutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		boolean flag = false;
		String paraName = "";

		try
		{
			tx.begin();
			IntakeOutputParaMstDAOi inoutparaDAO = new IntakeOutputParaMstDAO(tx);
			paraName = inoutparaDAO.checkDuplicateNameForModify(inoutparaMasterVO, userVO);

			if (paraName.equals("0"))
			{
				inoutparaDAO.updateInoutParaMaster(inoutparaMasterVO, userVO);
				inoutparaDAO.modifySaveInoutParaMaster(inoutparaMasterVO, userVO);
				flag = true;
			}

			else
			{
				throw new HisDuplicateRecordException("Anomaly Type Already Exists");
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

			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();

			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			System.out.println("HisException:: " + e);

			throw new HisException();
		}
		catch (Exception e)
		{

			System.out.println("error....BO");
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return flag;

	}
}
