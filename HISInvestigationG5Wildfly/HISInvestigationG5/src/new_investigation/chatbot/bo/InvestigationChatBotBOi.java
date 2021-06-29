package new_investigation.chatbot.bo;

import hisglobal.vo.UserVO;

import java.util.List;

import new_investigation.chatbot.vo.InvestigationChatbotVO;

public interface InvestigationChatBotBOi{

	List<InvestigationChatbotVO> getRequisitionNo(String crNo, UserVO _UserVO);
	
}