package hisglobal.tools.tag;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.utility.generictemplate.TemplateDesignerUtility;
import hisglobal.utility.generictemplate.controller.utl.GenericTemplateUTL;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.UserVO;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

public class GenericTemplateTag extends TagSupport 
{
	private String templateId;
	private String mode;
	
	public GenericTemplateTag()
	{
		this.mode = null;
	}	
	
	public GenericTemplateTag(String _templateId, String _mode)
	{
		this.templateId = _templateId;
		this.mode = _mode;
	}	

	public int doStartTag() throws JspException
	{
		try
		{
			HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
			HttpSession session=request.getSession();
			
			/*// Template Para Values
			Map<String, String> mapParaValues = null;
			Map<String, GenericTemplateUtility.TempParameter> mapParaDetail = null;
			if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP) != null)
			{
				Map<String, Map<String, String>> mapAllTempParaValues = (Map<String, Map<String, String>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP);
				mapParaValues = mapAllTempParaValues.get(this.templateId);
			}
			if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP) != null)
			{
				Map<String, Map<String, GenericTemplateUtility.TempParameter>> mpAllTempParaDtl = (Map<String, Map<String, GenericTemplateUtility.TempParameter>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP);
				mapParaDetail = mpAllTempParaDtl.get(this.templateId);				
			}
					
			
			if(this.mode==null)
				this.mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
			if(!(this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE) 
					|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_REPORT) 
					|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT) 
					|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_NON_ENTERED) 
					|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_FOCUS_ENTERED)))
				this.mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
				
			// Getting Gender Type from Session
			String genderType = GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_INITIAL_GENDER_TYPE;
			if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE)!= null)
				genderType = (String)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE);

			// Statically from HTML File
			String tempCode = GenericTemplateUtility.readTemplateFromHTMLFile(this.templateId);

			// Dynamically From Database
			if(tempCode == null)
				tempCode = GenericTemplateUTL.generateHTMLTemplate(this.templateId, mapParaValues, request);

			TemplateMasterVO vo = GenericTemplateUTL.getTemplateDataById(this.templateId, request);
			// Setting Template Values on Mode
			tempCode = TemplateDesignerUtility.fillParameterValuesIntoHTMLCode(tempCode, mapParaValues, mapParaDetail, this.mode, vo.getTemplateType(), genderType);*/
			
			String tempCode = templateLogic(request, session);
			
			JspWriter out = this.pageContext.getOut();
			System.out.print(tempCode);
			out.println(tempCode);
		}
		catch (IOException e) 
		{ 
			e.printStackTrace();
		}
		return TagSupport.EVAL_BODY_INCLUDE;
	}

	public String templateLogic(HttpServletRequest request, HttpSession session) throws IOException 
	{

		Map<String, Object> mapSessionContent = new HashMap<String, Object>();
		
		// Template Para Values
		Map<String, String> mapParaValues = null;
		Map<String, GenericTemplateUtility.TempParameter> mapParaDetail = null;
		if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP) != null)
		{
			Map<String, Map<String, String>> mapAllTempParaValues = (Map<String, Map<String, String>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP);
			mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mapAllTempParaValues);
			//mapParaValues = mapAllTempParaValues.get(this.templateId);
		}
		if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP) != null)
		{
			Map<String, Map<String, GenericTemplateUtility.TempParameter>> mpAllTempParaDtl = (Map<String, Map<String, GenericTemplateUtility.TempParameter>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP);
			mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP, mpAllTempParaDtl);
			//mapParaDetail = mpAllTempParaDtl.get(this.templateId);				
		}
				
		//if(this.mode==null)
			//this.mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
		//if(!(this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE) 
			//	|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_REPORT) 
			//	|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT) 
			//	|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_NON_ENTERED) 
			//	|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_FOCUS_ENTERED)
			//	|| this.mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT)))
			//this.mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
			
		// Getting Gender Type and Age from Session
		String genderType = GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_INITIAL_GENDER_TYPE;
		if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE)!= null)
			genderType = (String)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE);
		mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE, genderType);
		
		String age="1";
		if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE)!= null)
			age = (String)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE);
		mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE, age);
		
		UserVO userVO = ControllerUTIL.getUserVO(request);
		mapSessionContent.put("UserVO", userVO);
		
		InformationControlBean infoBean = null;
		if(request.getSession().getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_INFORMATION_BEAN) != null)
			infoBean = (InformationControlBean)request.getSession().getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_INFORMATION_BEAN);
		else
			infoBean = new InformationControlBean();
		mapSessionContent.put("InformationControlBean", infoBean);
		
		

		String tempCode = null;
		// Statically from HTML File
		//tempCode = GenericTemplateUtility.readTemplateFromHTMLFile(this.templateId);// Commented By Pragya 2015.01.02 We will fetch data from database each time as we are already firing a no. of queries for template
		
		// Dynamically From Database
		mapSessionContent.put("mode", this.mode);
		tempCode= templateLogicFinal(this.templateId, this.mode, mapSessionContent);

		return tempCode;
	}

	/*
	 * _templateId String :: Target Template Id
	 * _mapParaValues Map<String, String> GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP :: Map of Temp Parameters
	 * _mapParaDetail Map<String, GenericTemplateUtility.TempParameter> GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP :: MAp Temp Paraid and Para Value details
	 * _mode String GenericTemplateConfig.GENERIC_TEMPLATE_MODE....  :: Template Content Generation Mode 
	 * _genderType String  GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE TemplateDesignerUtility.GENDER_TYPES_NONE  :: Patient Gender Type
	 */
	public static String templateLogicFinal(String _templateId, String _mode, Map<String, Object> _mapSessionContent) throws IOException 
	{
		// Template Para Values
		Map<String, String> mapParaValues = null;
		Map<String, GenericTemplateUtility.TempParameter> mapParaDetail = null;

		//if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP) != null)
		if(_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP) != null)
		{
			//Map<String, Map<String, String>> mapAllTempParaValues = (Map<String, Map<String, String>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP);
			Map<String, Map<String, String>> mapAllTempParaValues = (Map<String, Map<String, String>>)_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP);
			mapParaValues = mapAllTempParaValues.get(_templateId);
		}
		//if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP) != null)
		if(_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP) != null)
		{
			//Map<String, Map<String, GenericTemplateUtility.TempParameter>> mpAllTempParaDtl = (Map<String, Map<String, GenericTemplateUtility.TempParameter>>)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP);
			Map<String, Map<String, GenericTemplateUtility.TempParameter>> mpAllTempParaDtl = (Map<String, Map<String, GenericTemplateUtility.TempParameter>>)_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP);
			mapParaDetail = mpAllTempParaDtl.get(_templateId);				
		}
				
		if(_mode==null)
			_mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
		if(!(_mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE) 
				|| _mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_REPORT) 
				|| _mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT) 
				|| _mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_NON_ENTERED) 
				|| _mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE_FOCUS_ENTERED)
				|| _mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT)))
			_mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_EDITABLE;
			
		// Getting Gender Type and Age from Session
		String genderType = GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_INITIAL_GENDER_TYPE;
		//if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE)!= null)
		if(_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE)!= null)
			//genderType = (String)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE);
			genderType = (String)_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE);
		String age="1";
		//if(session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE)!= null)
		if(_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE)!= null)
			//age = (String)session.getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE);
			age = (String)_mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE);
		
		UserVO userVO = (UserVO)_mapSessionContent.get("UserVO");

		String tempCode = null;
		// Statically from HTML File
		//tempCode = GenericTemplateUtility.readTemplateFromHTMLFile(_templateId);// Commented By Pragya 2015.01.02 We will fetch data from database each time as we are already firing a no. of queries for template
		
		// Dynamically From Database
		if(tempCode == null)
			tempCode = GenericTemplateUTL.generateHTMLTemplateFinal(_templateId, mapParaValues, _mode, _mapSessionContent);
			//tempCode = GenericTemplateUTL.generateHTMLTemplate(this.templateId, mapParaValues, this.mode, request);

		if(!_mode.equals(GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT))
		{
			//TemplateMasterVO vo = (TemplateMasterVO) request.getSession().getAttribute(GenericTemplateConfig.GENERIC_TEMPLATE_DETAIL_VO);
			TemplateMasterVO vo = (TemplateMasterVO) _mapSessionContent.get(GenericTemplateConfig.GENERIC_TEMPLATE_DETAIL_VO);
			if(vo == null)
			{
				vo = GenericTemplateUTL.getTemplateDataById(_templateId, userVO);
			}
			// Setting Template Values on Mode
			tempCode = TemplateDesignerUtility.fillParameterValuesIntoHTMLCode(tempCode, mapParaValues, mapParaDetail, _mode, vo.getTemplateType(), genderType);
		}

		return tempCode;
	}

	public String getTemplateId()
	{
		return templateId;
	}

	public void setTemplateId(String templateId)
	{
		this.templateId = templateId;
	}

	public String getMode()
	{
		return mode;
	}

	public void setMode(String mode)
	{
		this.mode = mode;
	}	
}
