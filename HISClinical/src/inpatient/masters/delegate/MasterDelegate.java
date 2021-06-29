package inpatient.masters.delegate;

import hisglobal.business.Delegate;
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
import inpatient.masters.bo.InpatientMasterBO;
import inpatient.masters.bo.InpatientMasterBOi;

import java.util.List;
import java.util.Map;

public class MasterDelegate extends Delegate
{

	public MasterDelegate()
	{
		super(new InpatientMasterBO()); // /<<Setting the service provider
	}

	public void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveUnitWise(_voUDMT, _UserVO);
	}

	public void updateTableUnitWise(String _unitId, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.updateTableUnitWise(_unitId, _UserVO);
	}

	public void updateTableWardWise(String _unitId, String _wardCode, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.updateTableWardWise(_unitId, _wardCode, _UserVO);
	}

	public void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveUnitWardWise(_voUDMT, _UserVO);
	}

	public List gettingWards(String _deptUnitCode, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return serviceBO.gettingWards(_deptUnitCode, _UserVO);
	}

	public void saveAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveAbortionTypeMaster(abortionTypeMasterVO, userVO);
	}

	public AbortionTypeMasterVO getDataForAbortionTypeModify(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForAbortionTypeModify(abortionTypeMasterVO, _UserVO));
	}

	public boolean saveModAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModAbortionTypeMaster(abortionTypeMasterVO, _UserVO);
		return flag;

	}

	public void saveAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveAbortionMethodMaster(abortionMethodMasterVO, userVO);
	}

	public AbortionMethodMasterVO getDataForAbortionMethodModify(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForAbortionMethodModify(abortionMethodMasterVO, _UserVO));
	}

	public boolean saveModAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModAbortionMethodMaster(abortionMethodMasterVO, _UserVO);
		return flag;

	}

	public String getAbortionTypeName(String typeID, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return serviceBO.getAbortionTypeName(typeID, _UserVO);
	}

	public void saveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveCompMaster(complicationMasterVO, userVO);
	}

	public ComplicationMasterVO getDataForCompModify(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForCompModify(complicationMasterVO, _UserVO));
	}

	public void saveModCompMaster(ComplicationMasterVO complicationMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveModCompMaster(complicationMasterVO, _UserVO);
	}

	public void saveDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveDeliveryPlaceMaster(deliveryPlaceMasterVO, userVO);
	}

	public DeliveryPlaceMasterVO getDataForDeliveryPlaceModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForDeliveryPlaceModify(deliveryPlaceMasterVO, _UserVO));
	}

	public boolean saveModDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModDeliveryPlaceMaster(deliveryPlaceMasterVO, _UserVO);
		return flag;

	}

	public void saveMethodMaster(MethodMasterVO methodMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveMethodMaster(methodMasterVO, userVO);
	}

	public MethodMasterVO getDataForMethodModify(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForMethodMasterModify(methodMasterVO, _UserVO));
	}

	public boolean saveModMethodMaster(MethodMasterVO methodMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModMethodMaster(methodMasterVO, _UserVO);
		return flag;

	}

	public void savePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.savePlacentaTypeMaster(placentaTypeMasterVO, userVO);
	}

	public PlacentaTypeMasterVO getDataForPlacentaTypeModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForPlacentaTypeMasterModify(placentaTypeMasterVO, _UserVO));
	}

	public boolean saveModPlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModPlacentaTypeMaster(placentaTypeMasterVO, _UserVO);
		return flag;

	}

	public void saveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveAnomalyTypeMaster(anomalyTypeMasterVO, userVO);
	}

	public AnomalyTypeMasterVO getDataForAnomalyTypeModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForAnomalyTypeModify(anomalyTypeMasterVO, _UserVO));
	}

	public boolean saveModAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModAnomalyTypeMaster(anomalyTypeMasterVO, _UserVO);
		return flag;

	}

	public void saveHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveHealthWorkerMaster(healthWorkerMasterVO, userVO);
	}

	public HealthWorkerMasterVO getDataForHealthWorkerModify(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForHealthWorkerModify(healthWorkerMasterVO, _UserVO));
	}

	public boolean saveModHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO _UserVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModHealthWorkerMaster(healthWorkerMasterVO, _UserVO);
		return flag;

	}

	// //////////////////////////////////////////////////////////

	public Map getLaborRoomAreaMstEssentails(UserVO userVO)
	{
		// TODO Auto-generated method stub
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getLaborRoomAreaMstEssentails(userVO));

	}

	public void saveDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		// TODO Auto-generated method stub
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveDetail(laborRoomAreaMstVO, _userVO);

	}

	public LaborRoomAreaMasterVO getModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return serviceBO.getModifyDetail(laborRoomAreaMstVO, _userVO);
	}

	public void saveModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyDetail(laborRoomAreaMstVO, _userVO);
	}

	public Map<String, Object> getLaborRoomMasterEssentails(UserVO _userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getLaborRoomMasterEssentails(_userVO));
	}

	public boolean saveDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveDetail(laborRoomMasterVO, _userVO);
		return flag;
	}

	public LaborRoomMasterVO getModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return serviceBO.getModifyDetail(laborRoomMasterVO, _userVO);
	}

	public void saveModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveModifyDetail(laborRoomMasterVO, _userVO);
	}
	
	public void saveIntakeOutputParaMaster(IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO)
	{
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		serviceBO.saveIntakeOutputParaMaster(inoutparaMasterVO, userVO);
	}

	public IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		return (serviceBO.getDataForModify(inoutparaMasterVO, userVO));
	}

	public boolean saveModInOutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO) {
		// TODO Auto-generated method stub
		boolean flag = false;
		InpatientMasterBOi serviceBO = (InpatientMasterBOi) super.getServiceProvider();
		flag = serviceBO.saveModInOutParaMaster(inoutparaMasterVO, userVO);
		return flag;

	}
}
