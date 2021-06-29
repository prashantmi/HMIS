package inpatient.masters.bo;

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

import java.util.List;
import java.util.Map;

public interface InpatientMasterBOi
{

	public Map getLaborRoomAreaMstEssentails(UserVO userVO);

	public void saveDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO);

	public LaborRoomAreaMasterVO getModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO);

	public void saveModifyDetail(LaborRoomAreaMasterVO laborRoomAreaMstVO, UserVO _userVO);

	public void saveAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO userVO);

	public AbortionTypeMasterVO getDataForAbortionTypeModify(AbortionTypeMasterVO abortionTypeMasterVO, UserVO userVO);

	public boolean saveModAbortionTypeMaster(AbortionTypeMasterVO abortionTypeMasterVO, UserVO userVO);

	public void saveAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO userVO);

	public AbortionMethodMasterVO getDataForAbortionMethodModify(AbortionMethodMasterVO abortionMethodMasterVO, UserVO userVO);

	public boolean saveModAbortionMethodMaster(AbortionMethodMasterVO abortionMethodMasterVO, UserVO userVO);

	public void saveCompMaster(ComplicationMasterVO complicationMasterVO, UserVO userVO);

	public ComplicationMasterVO getDataForCompModify(ComplicationMasterVO complicationMasterVO, UserVO userVO);

	public void saveModCompMaster(ComplicationMasterVO complicationMasterVO, UserVO userVO);

	public void saveDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO userVO);

	public DeliveryPlaceMasterVO getDataForDeliveryPlaceModify(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO userVO);

	public boolean saveModDeliveryPlaceMaster(DeliveryPlaceMasterVO deliveryPlaceMasterVO, UserVO userVO);

	public void saveMethodMaster(MethodMasterVO methodMasterVO, UserVO userVO);

	public MethodMasterVO getDataForMethodMasterModify(MethodMasterVO methodMasterVO, UserVO userVO);

	public boolean saveModMethodMaster(MethodMasterVO methodMasterVO, UserVO userVO);

	public void savePlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO userVO);

	public PlacentaTypeMasterVO getDataForPlacentaTypeMasterModify(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO userVO);

	public boolean saveModPlacentaTypeMaster(PlacentaTypeMasterVO placentaTypeMasterVO, UserVO userVO);

	public void saveUnitWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO);

	public void updateTableUnitWise(String _unitId, UserVO _UserVO);

	public void updateTableWardWise(String _unitId, String _wardCode, UserVO _UserVO);

	public void saveUnitWardWise(UnitInvParaMappingVO _voUDMT, UserVO _UserVO);

	public List gettingWards(String _deptUnitCode, UserVO _UserVO);

	public String getAbortionTypeName(String typeID, UserVO _UserVO);

	public void saveAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO userVO);

	public AnomalyTypeMasterVO getDataForAnomalyTypeModify(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO userVO);

	public boolean saveModAnomalyTypeMaster(AnomalyTypeMasterVO anomalyTypeMasterVO, UserVO userVO);

	public void saveHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO userVO);

	public HealthWorkerMasterVO getDataForHealthWorkerModify(HealthWorkerMasterVO healthWorkerMasterVO, UserVO userVO);

	public boolean saveModHealthWorkerMaster(HealthWorkerMasterVO healthWorkerMasterVO, UserVO userVO);

	public boolean saveDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO);

	public LaborRoomMasterVO getModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO);

	public Map<String, Object> getLaborRoomMasterEssentails(UserVO _userVO);

	public void saveModifyDetail(LaborRoomMasterVO laborRoomMasterVO, UserVO _userVO);

	public void saveIntakeOutputParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public IntakeOutputParaMasterVO getDataForModify(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

	public boolean saveModInOutParaMaster(
			IntakeOutputParaMasterVO inoutparaMasterVO, UserVO userVO);

}
