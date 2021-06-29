package ehr.patientTile.dataentry;

import hisglobal.business.Delegate;
import hisglobal.vo.ANCChecklistDetailVO;
import hisglobal.vo.ANCChildHandoverDetailVO;
import hisglobal.vo.ANCDeliveryDetailVO;
import hisglobal.vo.ANCDetailVO;
import hisglobal.vo.ANCHistoryDeliveryDetailVO;
import hisglobal.vo.ANCHistoryDetailVO;
import hisglobal.vo.ANCNeonatalApgarVO;
import hisglobal.vo.ANCNeonatalDetailVO;
import hisglobal.vo.ANCVisitDetailVO;
import hisglobal.vo.DoctorCallBookVO;
import hisglobal.vo.DoctorRoundDtlVO;
import hisglobal.vo.DoctorWardRoundDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.JSYRegitrationVO;
import hisglobal.vo.MlcVO;
import hisglobal.vo.NurseRoundDtlVO;
import hisglobal.vo.PatBloodStockDtlVO;
import hisglobal.vo.PatDischargeReqDtlVO;
import hisglobal.vo.PatIntakeOutDtlVO;
import hisglobal.vo.PatientBulletinDetailVO;
import hisglobal.vo.PatientClinicalDetailVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.PatientMonitoringMstVO;
import hisglobal.vo.SecondaryCategoryChangeVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;


import java.util.List;
import java.util.Map;

public class EHRSection_PatientTileDelegate extends Delegate
{
	/**
	 * Constructor for Setting Service Provider
	 */
	public EHRSection_PatientTileDelegate()
	{
		super(new EHRSection_PatientTileBO()); //// Setting the service provider
	}
	
	
	/** Getting The Patient Detail By CR No For Desk
	 * @param deskType
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public PatientDetailVO getInpatientDetailByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileBOi serviceBO = (EHRSection_PatientTileBOi)super.getServiceProvider();
		return serviceBO.getInpatientDetailByCrNoNAdmNo(deskType,patDtlVO,userVO);
	}
	
	/** Getting The MLC No of The Patient For Desk
	 * @param patDtlVO
	 * @param userVO
	 * @return
	 */
	public MlcVO getMlcNo(PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileBOi serviceBO = (EHRSection_PatientTileBOi)super.getServiceProvider();
		return serviceBO.getMlcNo(patDtlVO,userVO);
	}
	
	
	
	
	public PatientDetailVO getInpatientDiscDetailByCrNoNAdmNo(String deskType,PatientDetailVO patDtlVO,UserVO userVO)
	{
		EHRSection_PatientTileBOi serviceBO = (EHRSection_PatientTileBOi)super.getServiceProvider();
		return serviceBO.getInpatientDiscDetailByCrNoNAdmNo(deskType,patDtlVO,userVO);
	}
	
}