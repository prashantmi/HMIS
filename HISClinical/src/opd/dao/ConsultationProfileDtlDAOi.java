package opd.dao;

import java.util.List;

import hisglobal.vo.ConsultationProfileDtlVO;
import hisglobal.vo.UserVO;

public interface ConsultationProfileDtlDAOi {

	public ConsultationProfileDtlVO create(ConsultationProfileDtlVO consultationDtlVO , UserVO _userVO);
	
	public List<ConsultationProfileDtlVO> getPatientProfile(UserVO _userVO,String _mailRequestId);
}
