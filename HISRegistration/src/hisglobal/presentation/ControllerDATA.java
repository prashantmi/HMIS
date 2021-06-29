package hisglobal.presentation;

import hisglobal.business.GlobalEssentialBO;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

import java.util.List;

public class ControllerDATA
{
	public static HospitalMstVO getHospitalDetail(String hospitalCode_p) {
		GlobalEssentialBO serviceBO = new GlobalEssentialBO();
		return (serviceBO.getHospitalDetail(hospitalCode_p));
	}

	public static List getSystemDateAndFormat(UserVO objUserVO_p)
	{
		GlobalEssentialBO serviceBO = new GlobalEssentialBO();
		return (serviceBO.getSystemDateAndFormat(objUserVO_p));
	}
	

}
