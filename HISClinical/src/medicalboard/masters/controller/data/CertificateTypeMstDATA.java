package medicalboard.masters.controller.data;

import hisglobal.vo.MbCertificateTypeMstVO;
import hisglobal.vo.UserVO;
import java.util.Map;
import medicalboard.masters.delegate.MbMasterDelegate;
import medicalboard.masters.delegate.MbMasterEssentialDelegate;


public class CertificateTypeMstDATA {
	
	public static Map getCertificateEssentials(UserVO _UserVO)
	{
		MbMasterEssentialDelegate mEssentialDelegate=new MbMasterEssentialDelegate();
		Map mp=mEssentialDelegate.getCertificateEssentials(_UserVO);
		return mp;		
	}	
	
	public static void saveCertificateTypeDtl(MbCertificateTypeMstVO mTypeMstVO,String[] districtId, UserVO userVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		mstDelegate.saveCertificateTypeDtl(mTypeMstVO,districtId,userVO);
	}
	public static Map getDataCertificateType(MbCertificateTypeMstVO mTypeMstVO, UserVO _UserVO)
	{
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		return(mstDelegate.getDataCertificateType(mTypeMstVO, _UserVO));
	}

	public static boolean saveModCertificateType(MbCertificateTypeMstVO mTypeMstVO,String []districtId,UserVO _UserVO)
	{
		boolean  flag=false;
		MbMasterDelegate mstDelegate=new MbMasterDelegate();
		flag= mstDelegate.saveModCertificateType(mTypeMstVO,districtId, _UserVO);
		return flag;
	}
	
}
