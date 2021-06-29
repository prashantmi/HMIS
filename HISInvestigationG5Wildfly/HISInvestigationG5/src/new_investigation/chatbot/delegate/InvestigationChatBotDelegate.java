package new_investigation.chatbot.delegate;

import java.util.List;

import new_investigation.chatbot.bo.InvestigationChatBotBO;
//import new_investigation.chatbot.bo.InvestigationChatBotBOi;
import new_investigation.chatbot.vo.InvestigationChatbotVO;
//import new_investigation.transactions.bo.InvestigationEssentialBO;
//import new_investigation.transactions.bo.InvestigationEssentialBOi;
import hisglobal.business.Delegate;
import hisglobal.vo.UserVO;


public class InvestigationChatBotDelegate extends Delegate
{

	public InvestigationChatBotDelegate() {
		super(new InvestigationChatBotBO());
		// TODO Auto-generated constructor stub
	}

	public List<InvestigationChatbotVO> getRequisitionNo(String crNo,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.getRequisitionNo(crNo, _UserVO);
	}
	
	public List<InvestigationChatbotVO> getQues(String ques,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.getQues(ques, _UserVO);
	}
	
	public List<InvestigationChatbotVO> getAns(String ans,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.getAns(ans, _UserVO);
	}
	
	/*public List<InvestigationChatbotVO> getReport(String reqNO,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.getReport(reqNO, _UserVO);
	}*/
	public String show_chatbot(String ques,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.show_chatbot(ques, _UserVO);
	}
	public String show_chatbot_con(String ques,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.show_chatbot_con(ques, _UserVO);
	}
	public List<InvestigationChatbotVO> getQuesCat(String ques,
			UserVO _UserVO) {
		// TODO Auto-generated method stub
		InvestigationChatBotBO serviceBO = (InvestigationChatBotBO) super.getServiceProvider();
		return serviceBO.getQuesCat(ques, _UserVO);
	}
	
}