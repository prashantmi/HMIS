package inpatient.transaction.dao;

import java.util.List;

import hisglobal.transactionmgnt.HisDAO;
import hisglobal.vo.DrugReactionVO;
import hisglobal.vo.PatDrugTreatmentDetailVO;
import hisglobal.vo.UserVO;

public interface DrugReactionDtlDAOi 
{
	public void save(DrugReactionVO drugReactionVO,UserVO userVO);
	public List getTemplateIdList(UserVO userVO);
	public String getMaxSlNo(String patCrNo,UserVO userVO);
	public String getdrugReactionStatus(PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
	public List getPreviousDrugReactionListByCrNo(String CrNo,String episodeCode,UserVO userVO);
	public List getDrugReactionDetail(DrugReactionVO drugReacVO, UserVO _userVO);
	public String getdrugAdviceAlerts(String pmode, HisDAO hisDAO,PatDrugTreatmentDetailVO patDrugDtlVO,UserVO _userVO);
}
