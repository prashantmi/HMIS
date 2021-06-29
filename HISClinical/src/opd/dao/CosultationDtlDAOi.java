package opd.dao;

import hisglobal.vo.ConsultationDtlVO;
import hisglobal.vo.UserVO;

public interface CosultationDtlDAOi {
	public ConsultationDtlVO create(ConsultationDtlVO consultaionDtlVO , UserVO _userVO);
	public String getMaxRequestId(UserVO _userVO);
	public int updateConsultationAckStatus(String mailId,String ackStatus,UserVO _userVO);
	public String getNoOfNewMailsBySeatId(String seatId,String ackStatus);
	public ConsultationDtlVO getMailDetailsByMailId(UserVO _userVO,String _mailRequestId);
}
