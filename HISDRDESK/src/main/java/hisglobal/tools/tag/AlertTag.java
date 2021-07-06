package hisglobal.tools.tag;

import hisglobal.presentation.ControllerDATA;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import alertmanagement.transactions.controller.utl.AutomaticAlertUTL;
import alertmanagement.transactions.delegate.AlertDelegate;
import alertmanagement.transactions.vo.CommonAlertVO;

public class AlertTag extends TagSupport
{	
	public int doStartTag() throws JspException
	{

		try
		{
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			JspWriter out = this.pageContext.getOut();
			CommonAlertVO[] _commonAlertVO=null;		
			AlertDelegate alertDelegate = new AlertDelegate();
			int msgLength=0;
			
			alertDelegate.callAutoAlertProc(ControllerUTIL.getUserVO(request));
			_commonAlertVO=alertDelegate.getAllAutomaticAlertBySeatID(ControllerUTIL.getUserVO(request));
			
			
			
	if(_commonAlertVO!=null && _commonAlertVO.length > 0)
		{
		for(int i=0 ; i< _commonAlertVO.length ; i++)
			if(_commonAlertVO[i].getAlertAction().equals("1"))
				msgLength ++;
		}
		
	if(msgLength > 0)
		out.println(this.getTab(msgLength));

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return this.EVAL_BODY_INCLUDE;
	}

	public String getTab(int length)
	{
		String alertMsg=" ("+length+")";
		
		String strReturn = "";
		strReturn = "<div id='alertInboxId' style='position: absolute;left: 64%;top: 20%; ' class='alertDeskButton' ><strong><blink>"+alertMsg+"</blink></strong></div>";

		return strReturn;
	}

	

}
