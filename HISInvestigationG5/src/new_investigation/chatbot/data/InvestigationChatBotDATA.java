package new_investigation.chatbot.data;

import hisglobal.vo.UserVO;

import java.util.List;
//import java.util.Map;

import new_investigation.chatbot.delegate.InvestigationChatBotDelegate;
import new_investigation.chatbot.vo.InvestigationChatbotVO;
//import new_investigation.transactions.delegate.InvResultEntryDelegate;

public class InvestigationChatBotDATA
{
	 
	
	public static List<InvestigationChatbotVO> getRequisitionNo(String crNo, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.getRequisitionNo(crNo, _UserVO);
	}
	
	/*public static List<InvestigationChatbotVO> getReport(String reqNo, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.getReport(reqNo, _UserVO);
	}*/
	public static List<InvestigationChatbotVO> getQues(String ques, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.getQues(ques, _UserVO);
	}
	
	public static List<InvestigationChatbotVO> getAns(String ans, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.getAns(ans,  _UserVO);
	}
	public static String show_chatbot(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.show_chatbot(flg, _UserVO);
	}
	public static String show_chatbot_con(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.show_chatbot_con(flg, _UserVO);
	}
	public static String show_rcmd(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.show_rcmd(flg, _UserVO);
	}
	public static String statecode(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.statecode(flg, _UserVO);
	}
	public static String ip(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.ip(flg, _UserVO);
	}
	public static String key(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.key(flg, _UserVO);
	}
	public static List<InvestigationChatbotVO> test_codes(String flg, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.test_codes(flg, _UserVO);
	}
	public static List<InvestigationChatbotVO> getQuesCat(String ques, UserVO _UserVO)
	{
		InvestigationChatBotDelegate masterDelegate = new InvestigationChatBotDelegate();
		return masterDelegate.getQuesCat(ques, _UserVO);
	}
}
	 