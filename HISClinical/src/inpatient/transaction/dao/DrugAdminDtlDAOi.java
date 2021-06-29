package inpatient.transaction.dao;

import hisglobal.vo.DrugAdminDtlVO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.UserVO;

import java.util.List;

public interface DrugAdminDtlDAOi 
{
	public void save(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public String getConsentStatus(DrugAdminDtlVO vo,UserVO userVO);
	public void updateDrugExec(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public String getMaxSlNo(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public String getMaxAdminDate(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public void updateIsReactionStatus(DrugReactionVO drugReactionVO,UserVO userVO);
	public void updateIvFluidDrugExec(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public void saveDrugAdminDetail(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public void updateDrugAdminDetail(DrugAdminDtlVO drugAdminDtlVO,UserVO userVO);
	public String getdrugStatus(DrugAdminDtlVO vo,UserVO userVO);
	// public List getDrugBatchNoLstFromStore(String storeId,String itemBrandId,UserVO _userVO); // Commented by Manisha Gangwar date : 04.dec.2015 for Pharmacy Linkage at Drug Administration
	public List getDrugBatchNoLstFromStore(PatientDetailVO patientDetailVO,String itemIdList,UserVO _userVO);
	public List getDrugBrandLstFromGenericType(PatientDetailVO patientDetailVO,String itemIdList,UserVO _userVO);

}
