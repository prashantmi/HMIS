package medicalboard.masters.controller.data;

import hisglobal.vo.MbCertificateBoardMstVO;
import hisglobal.vo.UserVO;

import java.util.Map;

import medicalboard.masters.delegate.MbMasterDelegate;
import medicalboard.masters.delegate.MbMasterEssentialDelegate;

public class CertificateBoardMstDATA {

	
	public static Map getCertificateBoardEssentials(UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getCertificateBoardEssentials(_UserVO);
		return mp;		
	}	
	
	public static Map getBoard(MbCertificateBoardMstVO mBoardMstVO , UserVO _UserVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		return (mstDelegate.getBoard(mBoardMstVO,_UserVO));	
	}	
	
	public static void saveModCertificateBoardInfo(MbCertificateBoardMstVO mBoardMstVO,String[] boardTypeIdArray,UserVO _UserVO)
	{
		MbMasterDelegate masterDelegate = new MbMasterDelegate();
		masterDelegate.saveModCertificateBoardInfo(mBoardMstVO,boardTypeIdArray,_UserVO);
	}
	
	
	
	
 }
