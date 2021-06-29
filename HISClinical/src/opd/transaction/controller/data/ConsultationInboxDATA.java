package opd.transaction.controller.data;

import hisglobal.presentation.ControllerDATA;
import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.UserVO;

import java.util.List;

import opd.bo.delegate.OpdEssentialDelegate;
import opd.bo.delegate.OpdPatientDelegate;

public class ConsultationInboxDATA extends ControllerDATA {
	
	public static  java.util.Map getEssentials(UserVO _userVO)
	{
		OpdEssentialDelegate opdEssentialDelegate= new OpdEssentialDelegate();
		return opdEssentialDelegate.getConsultionInboxEssentials(_userVO);
	}
	public static void sendMail(ConsultationDtlVO consultationDtlVO,UserVO userVO){
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		 opdPatientDelegate.sendOpdConsultantData(consultationDtlVO,null, userVO);
	}
	
	public static void updateMailAckStatus(ConsultationDtlVO consultationDtlVO,String mailId,String ackStatus,UserVO userVO){
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		 opdPatientDelegate.updateMailAckStatus(consultationDtlVO,mailId, ackStatus, userVO);
	}
	
	public static void deleteMails(String[] mailIdArray,UserVO userVO){
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		opdPatientDelegate.deleteMails(mailIdArray, userVO);
	}
	
	public static ConsultationDtlVO getMailDetailsByMailId(UserVO _userVO,String _mailId)
	{
		OpdPatientDelegate opdPatientDelegate=new OpdPatientDelegate();
		return opdPatientDelegate.getMailDetailsByMailId(_userVO, _mailId);
	}
	
	public static List<ConsultationProfileDtlVO> getPatientProfilesForInbox(String _mailRequestId, UserVO _userVO)
	{
		OpdPatientDelegate delegate = new OpdPatientDelegate();
		return delegate.getPatientProfilesForInbox(_mailRequestId, _userVO);
	}
}
