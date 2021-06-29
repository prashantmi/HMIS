package hisglobal.utility.generictemplate.bo;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.tools.tag.GenericTemplateTag;
import hisglobal.utility.Entry;
import hisglobal.utility.dynamicdesk.DynamicDeskConfig;
import hisglobal.utility.generictemplate.GenericTemplateConfig;
import hisglobal.utility.generictemplate.GenericTemplateUtility;
import hisglobal.utility.generictemplate.InformationControlBean;
import hisglobal.utility.generictemplate.dao.GenericTemplateEssentialDAO;
import hisglobal.utility.generictemplate.dao.GenericTemplateEssentialDAOi;
import hisglobal.utility.generictemplate.dao.ParameterMasterDAO;
import hisglobal.utility.generictemplate.dao.TemplateMasterDAO;
import hisglobal.utility.generictemplate.dao.TemplateMasterDAOi;
import hisglobal.utility.generictemplate.dao.TemplateParameterMasterDAO;
import hisglobal.vo.GrowthChartVO;
import hisglobal.vo.ParameterMasterVO;
import hisglobal.vo.ParameterRangeMasterVO;
import hisglobal.vo.PatientDetailVO;
import hisglobal.vo.TemplateCategoryVO;
import hisglobal.vo.TemplateGroupVO;
import hisglobal.vo.TemplateMasterVO;
import hisglobal.vo.TemplateParameterMasterVO;
import hisglobal.vo.UserDeskMenuTemplateMasterVO;
import hisglobal.vo.UserVO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import registration.controller.data.newDeptVisitDATA;
import emr.vo.PatientClinicalDocDetailVO;

/**
 * @copyright CDAC
 * @developer Pragya Sharma
 */

public class GenericTemplateEssentialBO implements GenericTemplateEssentialBOi
{
	/** 
	 * Getting Template Group List
	 * @param _userVO UserVO 
	 * @return List of TemplateGroupVO objects
	 */
	public List<TemplateGroupVO> getTemplateGroupList(UserVO _userVO)
	{
		List<TemplateGroupVO> lstGrp = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstGrp = dao.getTemplateGroupList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstGrp;
	}

	//By Mukund on 10.08.2017
	/** Getting Template  List
	 * @param _userVO UserVO 
	 * @return List of TemplateMasterVO objects
	 */
	public List<Entry> getTemplateList(String templateCategory, UserVO _userVO)
	{
		List<Entry> lstTemplt= null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAO dao = new GenericTemplateEssentialDAO(tx);
			lstTemplt = dao.getAllTemplateListwithCat(templateCategory, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTemplt;
	}//End on 10.08.2017

	/** Getting Template Category List
	 * @param _userVO UserVO 
	 * @return List of TemplateCategoryVO objects
	 */
	public List<TemplateCategoryVO> getTemplateCategoryList(UserVO _userVO)
	{
		List<TemplateCategoryVO> lstCat = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstCat = dao.getAllTemplateCategoryList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstCat;
	}
	
	
	
	/** Getting Age Range  List
	 * @param _userVO UserVO 
	 */
	public List<Entry> getAgeRangeList(UserVO _userVO)
	{
		List<Entry> lstAgeRange = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstAgeRange = dao.getAllAgeRangeList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstAgeRange;
	}
	
	
	
	/** Getting Gender Bound  List
	 * @param _userVO UserVO 
	 */
	public List<Entry> getGenderBoundList(UserVO _userVO)
	{
		List<Entry> lstgender = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstgender = dao.getAllGenderBoundList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstgender;
	}
	

	/** Getting Clinical Parameter List
	 * @param _userVO UserVO 
	 * @return List of Entry objects of Clinical Parameters Id/Value
	 */
	public List<Entry> getClinicalParameterList(UserVO _userVO)
	{
		List<Entry> lstPara = null;

		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstPara = dao.getAllClinicalParameterList(_userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstPara;
	}

	/** 
	 * Getting All Clinical Parameter List Group Wise
	 * @param _tempGroupId Template Group Id
	 * @param _userVO UserVO User Detail
	 * @return List of Entry objects of Clinical Parameters Id/Value
	 */
	public List<Entry> getClinicalParameterListGroupWise(String _tempGroupId, UserVO _userVO)
	{
		List<Entry> lstPara = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao = new GenericTemplateEssentialDAO(tx);
			lstPara = dao.getAllClinicalParameterListGroupWise(_tempGroupId, _userVO);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstPara;
	}

	/** Saving anf Getting List of Entry Objects of Parametre Id/Name 
	 * @param _lstNewParas List of Entry Objects for Parameter Name in Label
	 * @param _paraType Parameter Type
	 * @param _userVO UserVO
	 * @return List of Entry objects of ClicalParameters Id/Name
	 */
	public List<Entry> saveGetParameters(Set<String> _newParaNames, String _paraType, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		List<Entry> lstParams = new ArrayList<Entry>(); 
		try
		{
			tx.begin();
			ParameterMasterDAO dao =new ParameterMasterDAO(tx);
			for(String paraName : _newParaNames)
			{
				Entry ent = new Entry();
				ent.setLabel(paraName);
				ParameterMasterVO paraVO = new ParameterMasterVO();
				paraVO.setParaName(paraName);
				/*if(_paraType.equals(GenericTemplateConfig.PARAMETER_TYPE_PRESENT_PATIENT_ALERTS))
					paraVO.setParaBound(GenericTemplateConfig.PARAMETER_BOUND_PATIENT_CENTRIC);
				else*/
					paraVO.setParaBound(GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC);
				paraVO.setParaType(_paraType);
				String paraId = dao.fetchIdByName(paraName, _userVO);
				if(paraId == null)
				{
					paraId = dao.create(paraVO,_userVO);
				}
				ent.setValue(paraId);
				lstParams.add(ent);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return lstParams;
	}

	/**
	 * Saving and Getting Back Template Parameters Map 
	 * @param _lstNewParas List of Template Parameters VO to add
	 * @param _mpAllLocPara Map of All Parameters Location & ID of Template
	 * @param _tempGroupID Template Group ID
	 * @param _userVO User Detail
	 * @return Map of All Paremers Loc n ID with Added Ones
	 */
	public Map<String, ParameterMasterVO> saveNewAddedParameters(List<ParameterMasterVO> _lstNewParas, Map<String,ParameterMasterVO> _mpAllLocPara, String _tempGroupID, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();			
			ParameterMasterDAO dao =new ParameterMasterDAO(tx);
			
			//Creating Non-Parent-Based Parameters
			for(ParameterMasterVO paraVO : _lstNewParas)
			{
				if(paraVO.getIsParentBased().equalsIgnoreCase("0"))
				{
					paraVO.setParaBound(GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC);
					paraVO.setParaType(_tempGroupID);
					String paraId = dao.fetchIdByNameInGroup(paraVO.getParaName(), _tempGroupID, _userVO);
					if(paraId == null)	paraId = dao.create(paraVO,_userVO);
					paraVO.setParaId(paraId);
					_mpAllLocPara.put(paraVO.getKey(),paraVO);
				}				
			}
			
			//Creating Parent-Based Parameters
			for(ParameterMasterVO paraVO : _lstNewParas)
			{
				if(paraVO.getIsParentBased().equalsIgnoreCase("1"))
				{
					String parentPara = paraVO.getParentPara();					
					String arrParentsLoc[] = paraVO.getParentPara().split(GenericTemplateUtility.SEP_IN_PARA_PARENT);
					Integer arrParentIds[]= new Integer[arrParentsLoc.length];

					String parentIds=""; 
					int i=0;
					for(String loc : arrParentsLoc)
					{
						ParameterMasterVO parentParaVO = _mpAllLocPara.get(loc);
						arrParentIds[i++] = Integer.parseInt(parentParaVO.getParaId());						
					}
					Arrays.sort(arrParentIds);
					
					for(Integer Id : arrParentIds)	parentIds+= Id.toString()+GenericTemplateUtility.SEP_IN_PARA_PARENT;
					if(!parentIds.equals(""))	parentIds=parentIds.substring(0,parentIds.length()-1);
					
					paraVO.setParaBound(GenericTemplateConfig.PARAMETER_BOUND_NON_PATIENT_CENTRIC);
					paraVO.setParaType(_tempGroupID);
					paraVO.setParentPara(parentIds);
					
					if(arrParentIds.length==1)
						paraVO.setParaId(parentIds);
					else
					{
						String paraId = dao.fetchIdByParentInGroup(paraVO.getParentPara(), _tempGroupID, _userVO);
						if(paraId == null)	paraId = dao.create(paraVO,_userVO);
						paraVO.setParaId(paraId);
					}
					paraVO.setParentPara(parentPara);
					_mpAllLocPara.put(paraVO.getKey(),paraVO);
				}				
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return _mpAllLocPara;
	}

	/** Is Template Name Already Exists 
	 * @param _templateVO Template Master VO
	 * @param _userVO User VO
	 * @return true if exists otherwise false
	 */
	public boolean exitsTemplateName(TemplateMasterVO _templateVO, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		boolean flag = false;
		try
		{
			tx.begin();
			TemplateMasterDAO dao = new TemplateMasterDAO(tx);
			flag = dao.exists(_templateVO,_userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return flag;
	}

	/** Saving Template
	 * @param _tmpltVO Template Master VO
	 * @param _userVO UserVO
	 * @return Template Id
	 */
	public String saveTemplate(TemplateMasterVO _templateVO, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		String templateId = null;
		try
		{
			tx.begin();
			TemplateMasterDAO dao = new TemplateMasterDAO(tx);
			_templateVO.setTemplateId("");
			_templateVO.setTempSerialNo("");
			if (dao.exists(_templateVO,_userVO))
			{
				throw new HisRecordNotFoundException("Template Name already exists");
			}
			else
			{
				templateId = dao.create(_templateVO,_userVO);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return templateId;
	}

	/** Saving Parameters To Template
	 * @param _lstVOs List of TemplateParameterMasterVO Objects
	 * @param _userVO UserVO
	 */
	public void saveParametersToTemplate(List<TemplateParameterMasterVO> _lstVOs ,UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateParameterMasterDAO dao = new TemplateParameterMasterDAO(tx);
			for(TemplateParameterMasterVO vo :_lstVOs)
			{
				dao.create(vo, _userVO);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}

	/** Getting Template Record Data By Template ID
	 * @param _voTemp TemplateMasterVO
	 * @param _userVO UserVO
	 * @return Template Data in VO 
	 */
	public TemplateMasterVO getTemplateDataById(TemplateMasterVO _voTemp, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		TemplateMasterVO vo = null;
		try
		{
			tx.begin();
			TemplateMasterDAO dao=new TemplateMasterDAO(tx);
			vo=dao.getTemplateDataById(_voTemp,_userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return vo;
	}

	/** Updating Template Data
	 * @param _temoVO Template Master VO 
	 * @param _userVO User VO
	 */
	public void updateTemplateData(TemplateMasterVO _temoVO, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateMasterDAO dao =new TemplateMasterDAO(tx);
			
			if (dao.exists(_temoVO,_userVO))
			{
				throw new HisRecordNotFoundException("Template Name already exists");
			}
			else
			{
				dao.updateOld(_temoVO,_userVO);
				dao.createNew(_temoVO,_userVO);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
	}

	/** Getting Template Parameter Detail List
	 * @param _tempId Template Id
	 * @param _userVO User VO
	 * @return List Template Parameter VOs
	 */
	public List<TemplateParameterMasterVO> getTemplateParaDetailListByTempId(String _tempId, UserVO _userVO)
	{
		List<TemplateParameterMasterVO> lstTempPara = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateParameterMasterDAO dao = new TemplateParameterMasterDAO(tx);
			lstTempPara = dao.getTempParaList(_tempId, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTempPara;
	}

	/** Deleting Old Template Para Detail By Template Id 
	 * @param _TempId Template Id
	 * @param _userVO User VO
	 */
	public void deleteOldTempParaDetialByTempId(String _tempId, UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		try
		{
			tx.begin();
			TemplateParameterMasterDAO dao =new TemplateParameterMasterDAO(tx);
			dao.deleteOld(_tempId,_userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
	}

	/** Getting Parameter Dynamic Data 
	 * @param _query Dynamic Query
	 * @param _userVO User VO
	 * @return Entry Object of Value/Label 
	 */
	public List<Entry> getParameterDynamicData(String _query, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstData = null;
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao =new GenericTemplateEssentialDAO(tx);
			lstData = dao.getParameterDynamicData(_query, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstData;
	}

	/** Getting Parameter Range Data on basis of Gender and Age
	 * @param _lstParaIds List Parameter Ids
	 * @param _gender Gender Code 
	 * @param _age Age for Age Limit
	 * @param _userVO User VO
	 * @return Map of Parameter Id by Parameter Range VO 
	 */
	public Map<String,ParameterRangeMasterVO> getParameterRangeData(List<String> _lstParaIds, String _gender,String _age, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		Map<String, ParameterRangeMasterVO> map = new HashMap<String, ParameterRangeMasterVO>();
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao =new GenericTemplateEssentialDAO(tx);
			for(String paraId :_lstParaIds)
			{
				map.put(paraId, dao.getParameterRangeData(paraId,_gender,_age, _userVO));
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return map;
	}

	/**
	 * Getting Template List by Unit, Ward, Seat, Desk, Desk Menu Wise
	 * @param _deskType DEsk Type
	 * @param _userDeskTempVO User Desk Template Mapping VO containing required Info
	 * @param _userVO User VO
	 * @return List of Entry objects in format tempId#defaultFlag as Value and Temp Name as Label 
	 */
	public List<Entry> getDeskMenuTemplateList(String _deskType, String _patCrNo , UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstTemplates = null;
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao =new GenericTemplateEssentialDAO(tx);
			lstTemplates = dao.getDeskMenuTemplateList(_deskType, _patCrNo, _userDeskTempVO, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTemplates;
	}

	/**
	 * Getting Paremter List by Unit, Ward, Seat, Desk, Desk Menu Wise
	 * @param _deskType DEsk Type
	 * @param _userDeskTempVO User Desk Template Mapping VO containing required Info
	 * @param _userVO User VO
	 * @return List of Entry objects in format tempId#defaultFlag as Value and Temp Name as Label 
	 */
	/*public List<Entry> getDeskMenuTemplateParametersList(String _deskType, UserDeskMenuTemplateMasterVO _userDeskTempVO, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstTemplates = null;
		List<Entry> lstTempParas = null;
		try
		{
			tx.begin();

			GenericTemplateEssentialDAOi dao =new GenericTemplateEssentialDAO(tx);
			lstTemplates = dao.getDeskMenuTemplateList(_deskType, _userDeskTempVO, _userVO);
			if(lstTemplates.size()==0)
				lstTempParas = new ArrayList<Entry>();
			else
			{
				List<Entry> lst = new ArrayList<Entry>();
				for(Entry e:lstTemplates)
				{
					Entry ent = new Entry();
					ent.setValue(e.getValue().split("#")[0]);
					lst.add(ent);
				}
				lstTempParas = dao.getTemplateParametersList(lst, _userVO);
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTempParas;
	}*/

	// Entry Parameters List for Vital REcording Chart By Pragya Sharma on 15-Mar-2019
	public List<Entry> getEntryParametesListOfTemplates(List<Entry> lstTemplates, UserVO _userVO)
	{
		JDBCTransactionContext tx = new JDBCTransactionContext();
		List<Entry> lstTempParas = null;
		try
		{
			tx.begin();

			GenericTemplateEssentialDAO dao =new GenericTemplateEssentialDAO(tx);
			List<String> lstTempIds = new ArrayList<String>();
			for(Entry e:lstTemplates)
				if(!e.getValue().contains("#") ||(e.getValue().contains("#") && e.getValue().split("#")[1].equals("1")))
				{
					lstTempIds.add(e.getValue().split("#")[0]);
				}
				lstTempParas = dao.getEntryParametersListofTempIds(lstTempIds, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();		
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();		
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		return lstTempParas;
	}

	
	public boolean saveParameter(ParameterMasterVO _parameterVO ,UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		boolean flag=true;
		try
		{
			tx.begin();
			ParameterMasterDAO dao = new ParameterMasterDAO(tx);
			//String id=dao.fetchIdByName(_parameterVO.getParaName(), _userVO);
			String id=dao.fetchIdByNameInGroup(_parameterVO.getParaName(),_parameterVO.getParaType(), _userVO);
			if(id==null){
				dao.create(_parameterVO, _userVO);
			}
			else{
				flag=false;
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			flag=false;
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			flag=false;
   		 	e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			flag=false;
   		 	e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			flag=false;
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			flag=false;
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return flag;
	}
	
	public ParameterMasterVO getParameterById(String _paraId ,UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		ParameterMasterVO parameterVO=null;
		try
		{
			tx.begin();
			ParameterMasterDAO dao = new ParameterMasterDAO(tx);
			parameterVO=dao.getParameterById(_paraId, _userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return parameterVO;
	}
	
	public boolean updateParameter(ParameterMasterVO _parameterVO ,UserVO _userVO)
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		boolean flag=false;
		try
		{
			tx.begin();
			ParameterMasterDAO dao = new ParameterMasterDAO(tx);
			if (dao.exists(_parameterVO,_userVO))
			{
				throw new HisRecordNotFoundException("Parameter Name already exists");
			}
			else{
				dao.update(_parameterVO, _userVO);
				flag=true;
			}
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return flag;
	}

	/**
	 * Returns Created Template Count in Given Category for Unique Check
	 * @param catCode Template Category Code
	 * @param userVO User VO
	 * @return Template Count
	 */
	public String getIsUniqueNCountTemp(String catCode,UserVO userVO )
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		String str="";
		try
		{
			tx.begin();
			TemplateMasterDAOi dao = new TemplateMasterDAO(tx);
			str = dao.getIsUniqueNCountTemp(catCode,userVO);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();	
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return str;
	}
	
	public Boolean getIsDefaultTemp(String catCode,UserVO userVO )
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		Boolean isDefault=false;
		try
		{
			tx.begin();
			TemplateMasterDAOi dao = new TemplateMasterDAO(tx);
			isDefault = dao.getIsDefaultTemp(catCode,userVO);
			System.out.println("Is Default:"+isDefault);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return isDefault;
	}
	
	
	public String getIsDefaultValueTemp(String catCode,UserVO userVO, String _tempID )
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		String isDefault="";
		try
		{
			tx.begin();
			TemplateMasterDAOi dao = new TemplateMasterDAO(tx);
			isDefault = dao.getIsDefaultValueTemp(catCode,userVO,_tempID);
			System.out.println("Is Default:"+isDefault);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		return isDefault;
	}

	//Added by Vasu on 16.May.2019 to get All Template Clinical Data
	public Map<String, Object> getTemplateClinicalData(String deskMenuId, String deskType,String deskId, PatientDetailVO selectedPatientVO,PatientClinicalDocDetailVO clinicalDocVO, UserVO userVO)
	{
		Map<String, Object> mpTempParaVals = null;
		JDBCTransactionContext tx = new JDBCTransactionContext();
		String pmode = "";
		try
		{
			tx.begin();
			GenericTemplateEssentialDAO dao =new GenericTemplateEssentialDAO(tx);
			if(deskType.equals(DynamicDeskConfig.DESK_TYPE_OPD_DOCTOR_DESK))
			{
				pmode = "1";
				mpTempParaVals = dao.getTemplateClinicalData(deskMenuId,deskType,deskId,pmode,selectedPatientVO,clinicalDocVO,userVO);
			}
			if(deskType.equals(DynamicDeskConfig.DESK_TYPE_IPD_DOCTOR_DESK))
			{
				pmode = "2";
				mpTempParaVals = dao.getTemplateClinicalData(deskMenuId,deskType,deskId,pmode,selectedPatientVO,clinicalDocVO,userVO);	
			}
			// Generating Content of Each Template
			Map<String, Map<String, Map<String, String>>> mpParaTempValuesAll = (Map<String, Map<String, Map<String, String>>>) mpTempParaVals.get("A");
			List<GenericTemplateUtility.TempParameter> lstTempData = (List<GenericTemplateUtility.TempParameter>) mpTempParaVals.get("B");
			Map<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>> mpParaTempParaDtlAll = 
					(HashMap<String, Map<String, Map<String, GenericTemplateUtility.TempParameter>>>) mpTempParaVals.get("C");

			GenericTemplateUtility.TempParameter voTemplateEssentials= new GenericTemplateUtility.TempParameter(); 
			if(lstTempData!=null)
			for(GenericTemplateUtility.TempParameter voTempPara : lstTempData)
			{
				GenericTemplateTag tag = new GenericTemplateTag();
				Map<String, Object> mapSessionContent = new HashMap<String, Object>();
				mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_MAP_TEMPLATE_MAP, mpParaTempValuesAll.get(voTempPara.getRecordDate()));
				
				// Changes to summarize data 
				mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PARAMETER_VALUES_DETAIL_MAP, mpParaTempParaDtlAll.get(voTempPara.getRecordDate()));
				
				mapSessionContent.put("UserVO", userVO);
				//String mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT;
				//if(deskMenuId.equals(DynamicDeskConfig.DYNAMIC_DESK_MENU_ID_EXAMINATION))
				String mode = GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SHORT_SUMMARIZED_REPORT;
				//mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_GENDER_TYPE, genderType);
				//mapSessionContent.put(GenericTemplateConfig.GENERIC_TEMPLATE_PATIENT_AGE, age);
				//mapSessionContent.put("InformationControlBean", infoBean);
				mapSessionContent.put("mode", mode);
				String templateContent = GenericTemplateTag.templateLogicFinal(voTempPara.getTemplateId(), mode, mapSessionContent);
				voTempPara.setTemplateContent(templateContent);   
				//mpTempParaVals.put("TEMPLATE_CONTENT", voTempPara);

				templateContent = GenericTemplateTag.templateLogicFinal(voTempPara.getTemplateId(), GenericTemplateConfig.GENERIC_TEMPLATE_MODE_SUMMARIZED_REPORT, mapSessionContent);
				voTempPara.setTemplateContentSummarized(templateContent);   
			}
			mpTempParaVals.put("TEMPLATE_CONTENT", lstTempData);
		}
		catch (HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage());
		}
		catch (HisApplicationExecutionException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisApplicationExecutionException();
		}
		catch (HisDataAccessException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisDataAccessException();
		}
		catch (HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();
		}
		
		return mpTempParaVals;
	}
	
	public static void GrowthChart(String crNo,HttpServletRequest request, HttpServletResponse response )
	{
		JDBCTransactionContext tx =new JDBCTransactionContext();
		//String isDefault="";
		List<GrowthChartVO> gChartVO=new ArrayList<GrowthChartVO>();
		GsonBuilder gsonBuilder=new GsonBuilder();
		Gson gson=gsonBuilder.create();
		try
		{
			tx.begin();
			GenericTemplateEssentialDAO dao = new GenericTemplateEssentialDAO(tx);
			gChartVO = dao.AJAX_GETChartEssential(crNo);
			String JSONObject_growthCart=gson.toJson(gChartVO);
			
			response.setContentType("application/Json");
			
			response.getWriter().write(JSONObject_growthCart.toString());
			System.out.println("Is Default:"+JSONObject_growthCart);
		}
		catch(HisRecordNotFoundException e)
		{
			tx.rollback();
			throw new HisRecordNotFoundException(e.getMessage()); 
		}
		catch(HisApplicationExecutionException e)
		{	   		   	
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisApplicationExecutionException();
   	 	}   	 
		catch(HisDataAccessException e)
		{		
			tx.rollback();
			e.printStackTrace();   		 
   		 	throw new HisDataAccessException();  	
		}
		catch(HisException e)
		{
			tx.rollback();
			e.printStackTrace();
			throw new HisException();
		}
		catch(Exception e)
		{
			e.printStackTrace();	
			tx.rollback();
			throw new HisApplicationExecutionException();
		}
		finally
		{
			tx.close();			
		}
		//return isDefault;
	}
}
