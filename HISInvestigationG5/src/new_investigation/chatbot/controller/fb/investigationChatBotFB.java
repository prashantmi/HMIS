package new_investigation.chatbot.controller.fb;

import org.apache.struts.action.ActionForm;

public class investigationChatBotFB extends ActionForm{
	
	private String hospitalCode;
	private String crNo;
	private String reqNo;
	private String reportUrl;
	private String ques;
	private String ans;
	private String show_chatbot;
	private String show_chatbot_con;
	private String quescat;
	private String show_rcmd;
	private String test_codes;
	private String statecode;
	private String ip;
	private String key;
	

	public String getReportUrl() {
		return reportUrl;
	}

	public void setReportUrl(String reportUrl) {
		this.reportUrl = reportUrl;
	}

	public String getCrNo() {
		return crNo;
	}

	public void setCrNo(String crNo) {
		this.crNo = crNo;
	}
	
	public String getReqNo() {
		return reqNo;
	}

	public void setReqNo(String reqNo) {
		this.reqNo = reqNo;
	}

	public String getHospitalCode() {
		return hospitalCode;
	}

	public void setHospitalCode(String hospitalCode) {
		this.hospitalCode = hospitalCode;
	}

	public String getQues() {
		return ques;
	}

	public void setQues(String ques) {
		this.ques = ques;
	}

	public String getAns() {
		return ans;
	}

	public void setAns(String ans) {
		this.ans = ans;
	}

	public String getShow_chatbot() {
		return show_chatbot;
	}

	public void setShow_chatbot(String show_chatbot) {
		this.show_chatbot = show_chatbot;
	}

	public String getQuesCat() {
		return ques;
	}

	public void setQuesCat(String ques) {
		this.ques = ques;
	}

	public String getShow_chatbot_con() {
		return show_chatbot_con;
	}

	public void setShow_chatbot_con(String show_chatbot_con) {
		this.show_chatbot_con = show_chatbot_con;
	}
	
	public String getShow_rcmd() {
		return show_rcmd;
	}
	public void setShow_rcmd(String show_rcmd) {
		this.show_rcmd = show_rcmd;
	}
	public String getstatecode() {
		return statecode;
	}
	public void setstatecode(String statecode) {
		this.statecode = statecode;
	}
	public String getTest_codes() {
		return test_codes;
	}
	public void setTest_codes(String test_codes) {
		this.test_codes = test_codes;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	
}
