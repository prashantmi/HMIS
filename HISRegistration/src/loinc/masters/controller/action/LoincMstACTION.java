/**
 * 
 */
package loinc.masters.controller.action;

import java.util.ArrayList;

import hisglobal.masterutil.GenericController;
import hisglobal.presentation.WebUTIL;
import loinc.masters.controller.data.LoincMstDATA;
import loinc.masters.controller.util.LoincMstUTIL;
import loinc.vo.LoincVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * @author Sheeldarshi
 *
 */
public class LoincMstACTION extends GenericController 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String message;
	private loinc.vo.LoincVO locModel;
	public HttpServletRequest request = null;
	public HttpServletResponse response = null;
	private String flagAddMod;


	public LoincMstACTION() 
	{
		super(new LoincMstUTIL(),"Loinc","registration");
	}

	public String execute()
	{
		super.LIST();
		return SUCCESS;
	}



	public String report()
	{
		super.REPORT("Loinc");

		return "report";

	} 



	public String add()
	{
		try
		{
			HttpServletRequest request= super.getRequest();
			WebUTIL.refreshTransState(request);
			if(locModel == null)
			{
				locModel = new LoincVO();
				locModel.setIsLoincName("1");
			}
			else
				locModel.setIsLoincName("1");
			LoincMstDATA.getLoincTestName(request);	
			LoincMstUTIL.GetPropertyCombo(request,response);
		}
		catch (Exception e) 
		{

			e.printStackTrace();
		}
		flagAddMod="ADD";
		return "input";
	}






	public String searchSuggestive()
	{
		try
		{	 
			HttpServletRequest request= super.getRequest();	
			LoincMstDATA. searchSuggestiveLoinc(locModel);
			//set combo values after grid is populated	
			String strTestParaNameCode = request.getParameter("strTestParaNameCode").toString();
			String strTestParaNameCodeSplit =	strTestParaNameCode.replace('^', '#');
			String[] Split = strTestParaNameCodeSplit.split("#");
			String StrScale = Split[1]; 
			locModel.setStrScale(StrScale);
			locModel.setstrTestParaNameCode(strTestParaNameCode);
			String strTestSampleCode = request.getParameter("strTestSampleCode").toString();
			locModel.setstrTestSampleCode(strTestSampleCode);

		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		flagAddMod="search";
		return "input";
	}

	public String searchParam()
	{
		String strUOMCode = null;
		String strUOMCode1=null;
		String strUOMCode2=null;
		try
		{	 
			HttpServletRequest request= super.getRequest();	
			LoincMstDATA. searchLoinc(locModel);
			//set combo values after grid is populated	
			String strTestNameCode = request.getParameter("strTestNameCode").toString();
			locModel.setStrTestNameCode(strTestNameCode);
			
			String strTestParaNameCode = request.getParameter("strTestParaNameCode").toString();
			locModel.setstrTestParaNameCode(strTestParaNameCode);
			
			String strTestSampleCode = request.getParameter("strTestSampleCode").toString();
			locModel.setstrTestSampleCode(strTestSampleCode);
			
			if( request.getParameter("strTestSampleCode") != null  && !"".equals(request.getParameter("strTestSampleCode").toString()))
			{
				String strTestSampleCodeSplit =	strTestSampleCode.replace('^', '#');
				if(strTestSampleCodeSplit != null && !"".equals(strTestSampleCodeSplit) )
				{
					String[] Split = strTestSampleCodeSplit.split("#");
					if(Split != null && Split.length >=2)
					
					 strUOMCode1=Split[1];
					 strUOMCode2=Split[2];
					String append="^";
					strUOMCode =  strUOMCode1 + append + strUOMCode2; 
					locModel.setStrUOMCode(strUOMCode);
				}
				
			}

		}
		catch (Exception e) 
		{
			e.printStackTrace();

		}
		flagAddMod="MODIFY";
		return "input";
	}
	
	public void TESTPARANAME()
	{
		try
		{	 
			HttpServletRequest request= super.getRequest();	 
			HttpServletResponse response= super.getResponse();	
			request.getSession().setAttribute("testParaName", new ArrayList());
			request.getSession().setAttribute("testSample", new ArrayList());
			
			LoincMstUTIL.getTestParaName_AJAX(locModel,request,response);
		 }

	catch (Exception e) 
	{
		e.printStackTrace();
	}
}

		 
	 public String save()
	 {
		 HttpServletRequest request= super.getRequest();
		 WebUTIL.refreshTransState(request);
		 if(!LoincMstDATA.saveLoincDtl(locModel, "1"))
		 {//enters this loop is save is successful

			 try
			 {
				 
				 locModel.reset();
				 flagAddMod="ADD";
				 message="Data added successfully";
			 }
			 catch(Exception e)
			 {
				 e.printStackTrace();
			 }
			 return "input";
		  }
		 else
		 {
			 locModel.reset();
			 this.addActionMessage(locModel.getStrWarning());
			 flagAddMod="ADD";
			 return "input";
		 }
	 }
	 



	public String modify()
	{
		HttpServletRequest request= super.getRequest();
		LoincMstDATA.getLoincTestName(request);	
		LoincMstUTIL.GetPropertyCombo(request,response);
		locModel=LoincMstUTIL.getModifyComboValues(request,response);
		flagAddMod="MODIFY";
		return "input";
	}

	public String update()
	{
		HttpServletRequest request= super.getRequest();
		if(LoincMstUTIL.updateLoincDtl( request,  response,locModel))
		{
			try
			{
				super.LIST();
				message="Data modified successfully";
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}
		else
		{
			this.addActionMessage(locModel.getStrWarning());
			flagAddMod="MODIFY";
			return "input";
		}


	}


	public String getMessage()
	{
		return message;
	}

	public void setMessage(String message) 
	{
		this.message = message;
	}
	public HttpServletRequest getRequest()
	{
		return request;
	}

	public void setRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	public HttpServletResponse getResponse()
	{
		return response;
	}

	public void setResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	public LoincVO getLocModel() 
	{
		return locModel;
	}

	public void setLocModel(LoincVO locModel)
	{
		this.locModel = locModel;
	}

	public String getFlagAddMod()
	{
		return flagAddMod;
	}

	public void setFlagAddMod(String flagAddMod) 
	{
		this.flagAddMod = flagAddMod;
	}

}
