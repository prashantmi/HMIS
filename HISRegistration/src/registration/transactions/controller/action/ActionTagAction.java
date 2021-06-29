package registration.transactions.controller.action;

import com.opensymphony.xwork2.ActionSupport;

public class ActionTagAction extends ActionSupport{
	
	public String execute() {
		System.out.println("ActionTagAction :: execute()");
		return SUCCESS;
	}

}
