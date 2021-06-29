package hisglobal.tools.tag;

import hisglobal.hisconfig.Config;
import hisglobal.presentation.WebUTIL;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.vo.HospitalMstVO;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.struts2.views.jsp.TagUtils;


public class InputCrNoTag extends TagSupport
{
//	private PatientVO crNoFB = new PatientVO();
	protected String strCRNO;
	public String getStrCRNO() {
		return strCRNO;
	}

	public void setStrCRNO(String strCRNO) {
		this.strCRNO = strCRNO;
	}

	public int doStartTag() throws JspException
	{
		try
		{
			//this.crNoFB.setPatCrNo((String) TagUtil.invokeGetterFor(this.getBean(), "PatCrNo"));
			this.strCRNO = TagUtils.getStack(pageContext).findString("patCrNo") ;
			//this.crNoFB.setPatCrNo("");
			
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			boolean isStatusNew =true;
			String status= (TagUtils.getStack(pageContext).findString("AfterGo"));
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
			String hosCode=hosVo.getHospitalCode();
			
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
		String crno=this.strCRNO;
		if(crno==null)
			crno="";
		if (!_isReadOnly)
		{
			isReadOnly = "readonly=\"readonly\"";
			isMandatory = "";
		}
		//By Mukund to put the cursor after "3310116"
		String script = "<script>function getFocus() {	var input = document.getElementsByName(\"patCrNo\")[0];	var len = input.value.length; input.focus(); input.setSelectionRange(len, len); }window.onload=getFocus;</script>";	
		this.setCRNoValue();
		crno=this.strCRNO;
		//strReturn = "<!--Cr No Input Tile-->" + "\n<div class='div-table'> <div class='div-table-row '> <div class='div-table-col label width25'>"
		strReturn = script+"<!--Cr No Input Tile-->" + "\n<div class='div-table'> <div class='div-table-row '> <div class='div-table-col label width25'>"
				+ "\n<font color='red' >"
				+ isMandatory
				+ "</font>"
				+ "CR Number"
				+ "\n</div>"
				+ "\n<div class='div-table-col  control' style='width 13%' >"
				+ "\n<input type=\"text\" name=\"patCrNo\" maxlength=\""
				+ size
				+ "\" tabindex=\"1\" size=\""
				+ (size + 2)
				+ "\" value=\""
				+ this.strCRNO
				+ "\"  onkeydown=\"setPrevValue(this, event);\"  onkeypress=\"if(event.keyCode!=13) return validateNumericOnly(this,event); else "
				+ "return submitFormOnValidate(validateCRNoCHECK('"+ size+ "'),'GETPATDTL')\"" + isReadOnly + ">" +

				"\n</div>" + "\n<div class='div-table-col control width20'>";
		if (isReadOnly == "")
		{
			//strReturn += "\n<a href='#' class='button' onclick='return submitFormOnValidate(validateCRNoCHECK(\""+ size+ "\"),\"GETPATDTL\")'><span class='cancel'>Go</span></a> "
			strReturn +=  "\n<img id='imgGoCrNoId' src='../../hisglobal/images/GO.png' style='cursor:pointer' onclick='return submitFormOnValidate(validateCRNoCHECK(\""+ size+ "\"),\"GETPATDTL\")' />"
					+ "</div>"
					+ "<div class='div-table-col label width40'>"
					//+ "<img id='imgSearchId' src='../../hisglobal/images/search_icon1.gif' style='cursor:pointer' onclick=\"openPopup('/AHIMS/registration/searchByNamePopup.cnt',event,350,750)\" />";
					+ "<img id='imgSearchId' src='../../hisglobal/images/search_icon1.gif' style='cursor:pointer' onclick=\"openPopupWithEventHeightWidth('../../registration/transactions/PatientSearch.action',event,300,900)\" />";
					//+ "<a href='#' class='button' onclick=\"openPopup('/AHIMS/registration/searchByNamePopup.cnt',event,350,750)\" ><span class='cancel'>Search</span></a> "
		}
		strReturn+= "</div>";
		//System.out.println(strReturn);
		return strReturn;
	}

	protected String getBodyClose()
	{
		String strReturn = "\n</div>" + "\n</div>" + "<!--Cr No Input Tile ends here-->";

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
		this.bean ="CRNoSUP"; 
			//	TagUtil.getAttribute(this.pageContext, this.getName());
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
		if (this.strCRNO == null || this.strCRNO.trim().length() == 0)
		{
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String valuePatCrNo = (String) WebUTIL.getSession(request).getAttribute(Config.DEFAULT_VALUE_CR_NO_FORMAT);
			this.strCRNO = valuePatCrNo;

		}
		
	}

}
