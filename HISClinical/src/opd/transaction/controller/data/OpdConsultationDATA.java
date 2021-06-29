package opd.transaction.controller.data;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;
import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.UserVO;

import java.util.*;
public class OpdConsultationDATA extends ControllerDATA {
	public static Map getOpdConsultationEssentials(ConsultationDtlVO consultationDtlVO, UserVO userVO){
		OpdEssentialDelegate  opdessentialDelegate=new OpdEssentialDelegate();
		return opdessentialDelegate.getEConsultionEssentials(consultationDtlVO,userVO);
	}
	
	public static void sendCosultantData(ConsultationDtlVO consultationDtlVO,List<ConsultationProfileDtlVO> consultationProfileDtlVOList, UserVO userVO){
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		 opdPatientDelegate.sendOpdConsultantData(consultationDtlVO, consultationProfileDtlVOList,userVO);
	}
}
