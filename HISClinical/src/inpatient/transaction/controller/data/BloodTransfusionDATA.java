package inpatient.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.BloodTransfusionDtlVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TransfusionReactionDtlVO;
import hisglobal.vo.TransfusionReactionParaDtlVO;
import hisglobal.vo.UserVO;
import inpatient.transaction.delegate.InPatientEssentialDelegate;
import inpatient.transaction.delegate.InpatientDelegate;

import java.util.List;
import java.util.Map;

public class BloodTransfusionDATA extends ControllerDATA
{
	public static Map getBloodTransfusionEssential(PatientDetailVO patientDetailVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getBloodTransfusionEssential(patientDetailVO, _userVO);
	}
	public static List<BloodTransfusionDtlVO> getPreviousBloodTransDtl(String patCrNo,List<BloodTransfusionDtlVO> lstBldTransDtlVO, UserVO _userVO)
	{
		InPatientEssentialDelegate delegate = new InPatientEssentialDelegate();
		return delegate.getPreviousBloodTransDtl(patCrNo,lstBldTransDtlVO, _userVO);
	}
	
	public static void saveBloodTransfusionDtl(Map<String, TransfusionReactionDtlVO> mapTrasReaction,Map<String, List<TransfusionReactionParaDtlVO>> mapTrasReactionPara,List bloodTrasVOList,List patIntakeOutDtlVOList,String[] selectedBag,UserVO _userVO)
	{
		InpatientDelegate delegate=new InpatientDelegate();
		delegate.saveBloodTransfusionDtl(mapTrasReaction,mapTrasReactionPara,bloodTrasVOList,patIntakeOutDtlVOList,selectedBag,_userVO);
	}
	
	//Added by Pawan on Jan 18 2012
	public static Map getExtPatTransReactionEssential(UserVO _userVO){
		
		InpatientDelegate delegate=new InpatientDelegate();
		return delegate.getExtPatTransReaction(_userVO);
	}
	
	public static void saveExtBloodTransReactionDtl(TransfusionReactionDtlVO transReactionDtlVO,List bloodTrasReactionParaVOList,UserVO _userVO){
		InpatientDelegate objDelegate=new InpatientDelegate();
		objDelegate.saveExtBloodTransReactionDtl(transReactionDtlVO,bloodTrasReactionParaVOList,_userVO);
	}
}
