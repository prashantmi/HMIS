package hisglobal.tools.tag;

import java.io.IOException;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.PatientVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;



public class InputCrNoTag extends TagSupport
{
	private PatientVO crNoFB = new PatientVO();
	
	public int doStartTag() throws JspException
	{
		try
		{
			//this.crNoFB.setPatCrNo((String) TagUtil.invokeGetterFor(this.getBean(), "PatCrNo"));
			this.crNoFB.setPatCrNo((String) TagUtil.invokeGetterFor(this.getBean(), "PatCrNo"));//TagUtil.getStack(pageContext).findString("patCrNo") ;
			//this.crNoFB.setPatCrNo("");
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			boolean isStatusNew =true;
			//boolean isStatusNew = TagUtil.isStatusNew((Status) (TagUtil.getAttribute(this.pageContext, Config.STATUS_OBJECT)));
			String status= "0";//(String) TagUtil.invokeGetterFor(this.getBean(), "AfterGo");//(TagUtils.getStack(pageContext).findString("AfterGo"));
			if(status=="1"){
				isStatusNew=false;
			}
			 
		    //To Check the CRNO Size on the basis of HospitalCode(3 digit (13 digit CRNO)||5 digit(15 digit CRNO) ), Added by Singaravelan on 08-Jan-2015
			HospitalMstVO hosVo=(HospitalMstVO)(request.getAttribute(Config.HOSPITAL_VO));
			if(hosVo!=null)
				System.out.println("-----HosCode From HosMst in InputCRNOTag(1) :-------"+hosVo.getHospitalCode()+"-------------");
			else{
				hosVo=ControllerUTIL.getHospitalVO(request);
				System.out.println("-----HosCode From HosMst in InputCRNOTag(2) :-------"+hosVo.getHospitalCode()+"-------------");
			}
				
			//boolean isStatusNew =true;
					//TagUtil.isStatusNew((Status) (TagUtil.getAttribute(this.pageContext, Config.STATUS_OBJECT)));
			JspWriter out = this.pageContext.getOut();
			
			String sizeValue="";
			String hosCode=(hosVo.getHospitalCode()!=null && !hosVo.getHospitalCode().equals(""))?hosVo.getHospitalCode():(String)request.getSession().getAttribute(Config.HOSPITAL_CODE);
			System.out.println("hosCode"+hosCode);
			
			if(hosCode.length()==5)
				sizeValue= Config.CR_NO_FORMAT_SIZE_FIFTEEN;
			else
				sizeValue= Config.CR_NO_FORMAT_SIZE;
			
			int size = Integer.parseInt(sizeValue);
			out.println(this.getBodyTop(isStatusNew, request, size));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}

	public int doEndTag() throws JspException
	{
		return endingTag();
	}

	public int endingTag() throws JspException
	{
		try
		{
			//System.out.println("do after body............................................................");
			JspWriter out = this.pageContext.getOut();
			out.println(this.getBodyClose());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return TagSupport.EVAL_PAGE;
	}

	
	protected String getBodyTop(boolean _isReadOnly, HttpServletRequest _request, int size)
	{
		String strReturn;
		String isReadOnly = "";
		String isMandatory = "*";
		String crno=this.crNoFB.getPatCrNo();
		if(crno==null)
			crno="";
		if (!_isReadOnly)
		{
			isReadOnly = "readonly=\"readonly\"";
			isMandatory = "";
		}

		this.setCRNoValue();
		crno=this.crNoFB.getPatCrNo();
		strReturn = "<!--Cr No Input Tile-->" + "\n<table width = \"100%\"  border=\"0\" cellpadding=\"5\" cellspacing=\"1\">" + "\n<tr>"
				+ "\n<td width=\"17%\" nowrap Class=\"tdfonthead\">" + "\n<div align=\"left\">"
				+ "\n<font color=\"#FF0000\" size=\"1\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
				+ isMandatory
				+ "</font>"
				+ "<b><font color=\"#000000\" size=\"2\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
				+ "CR Number"
				+ "\n</font></b>"
				+ "\n</div>"
				+ "\n</td>"
				+ "\n<td width=\"17%\"  Class = \"tdfont\" nowrap>"
				+ "\n<font color=\"#000000\" size=\"1em\" face=\"Verdana, Arial, Helvetica, sans-serif\">"
				+ "\n<input type=\"text\" name=\"patCrNo\" maxlength=\""
				+ size
				+ "\" tabindex=\"1\" size=\""
				+ (size + 2)
				+ "\" value=\""
				+ this.crNoFB.getPatCrNo()
				+ "\"  onkeydown=\"setPrevValue(this, event);\"  onkeypress=\"if(event.keyCode!=13) return validateNumericOnly(this,event); else "
				+ "return submitFormOnValidate(validateCRNoCHECK('"+ size+ "'),'GETPATDTL')\"" + isReadOnly + ">" +

				"\n</font>" + "\n</td>" + "\n<td width=\"49%\" align=\"left\" class=\"tdfont\">";
		if (isReadOnly == "")
		{
			strReturn += "\n<img class=\"button\" src='/HISClinical/hisglobal/images/GO.png' tabindex=\"1\" style=cursor:pointer onclick=\" return submitFormOnValidate(validateCRNoCHECK('"
					+ size
					+ "'),'GETPATDTL')\" onkeypress=\"if(event.keyCode==13)return submitFormOnValidate(validateCRNoCHECK('"
					+ size
					+ "'),'GETPATDTL')\" size = '7' border=\"0\"> </td>"
					+ "<td width=\"17%\" class=\"tdfont\"><div align=\"right\"><img class=\"button\" src='/HISClinical/hisglobal/images/btn-search.png' tabindex=\"1\" style=cursor:pointer onclick=\"openPopup('/HISClinical/registration/searchByNamePopup.cnt"
					+ "',event,350,750)\" onkeypress=\"if(event.keyCode==13) openPopup('/HISClinical/registration/searchByNamePopup.cnt',event,350,750)\"></div>";
		}
		//strReturn+= "</td>";
		//System.out.println(strReturn);
		return strReturn;
	}

	protected String getBodyClose()
	{
		String strReturn = "\n</td>" + "\n</tr>" + "\n</table>" + "<!--Cr No Input Tile ends here-->";

		return strReturn;
	}

	String name;
	Object bean;

	public Object getBean()
	{
		return bean;
	}

	public void setBean() throws JspException
	{
		this.bean = TagUtil.getAttribute(this.pageContext, this.getName());
		if (this.bean == null) throw new RuntimeException("No bean with name: " + this.getName());
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name) throws JspException
	{
		this.name = name;
		this.setBean();
	}

	public void setCRNoValue()
	{
		if (this.crNoFB.getPatCrNo() == null || this.crNoFB.getPatCrNo().trim().length() == 0)
		{
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String valuePatCrNo = (String) WebUTIL.getSession(request).getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT);
			this.crNoFB.setPatCrNo(valuePatCrNo);

		}
	}

}
