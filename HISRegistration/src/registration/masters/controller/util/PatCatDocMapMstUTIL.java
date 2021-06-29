/**
 * 
 */
package registration.masters.controller.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import registration.bo.RegMasterBO;
import registration.config.RegistrationConfig;
import registration.masters.controller.data.PatCatDocMapMstDATA;
import vo.registration.PatCatDocVO;
import vo.registration.PatCategoryVO;
import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.masterutil.MasterInterface;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.vo.UserVO;

/**
 * @author s.singaravelan
 * Creation Date:- 07-Feb-2013
 * Modifying Date:- 
 * Used For:-	
 * Team Lead By:- 
 * Module:- Registration(HIS Project)
 *
 */
public class PatCatDocMapMstUTIL 
{
		
	private static final long serialVersionUID = 1L;
	HttpSession httpSession = null;
	public HttpServletRequest request = null;

	
	@SuppressWarnings("rawtypes")
	public static boolean saveCategoryVerificationDocument(PatCatDocVO patCatDoc_vo,HttpServletRequest request,String strMode) {
		RegMasterBO bo = new RegMasterBO();
		PatCatDocVO[] insertpatCatVerDocVO=null;
		PatCatDocVO[] updatepatCatVerDocVO=null;
		boolean status=true;
		List insertList=new ArrayList();
		List updateList=new ArrayList();
		try {
			
			List oldMappedList=getOldMappedList(patCatDoc_vo, request);	
			List newMappedList=getNewMappedList(patCatDoc_vo, request);		
			UserVO uservo=ControllerUTIL.getUserVO(request);
			if(newMappedList==null)
				newMappedList=new ArrayList();
				
			if(oldMappedList==null)
				oldMappedList=new ArrayList();
			
			insertList=getCompareList(newMappedList, oldMappedList);			
			updateList=getCompareList(oldMappedList, newMappedList);
			if(insertList!=null)				
				insertpatCatVerDocVO=getPatCatVerDocVO(insertList, patCatDoc_vo);
				
			if(updateList!=null )		
				updatepatCatVerDocVO=getPatCatVerDocVO(updateList, patCatDoc_vo);
				
			status=PatCatDocMapMstDATA.saveCategoryVerificationDocument(insertpatCatVerDocVO,updatepatCatVerDocVO,strMode,uservo);
			
		} catch (Exception e) {
			e.printStackTrace();
			status=false;
		}
		
		return status;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getOldMappedList(PatCatDocVO _fb, HttpServletRequest _request){
		List oldMappedListFromSession=(ArrayList)_request.getSession().getAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY);
		List oldMappedList=new ArrayList();		
		try
		{
			Iterator itr=oldMappedListFromSession.iterator();			
			while(itr.hasNext()){				
				Entry obj=(Entry)itr.next();					
				oldMappedList.add(obj.getValue());
			}			
		}catch (Exception e)
		{
		  e.printStackTrace();			
		}		
		return oldMappedList;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getNewMappedList(PatCatDocVO _fb, HttpServletRequest _request){
		List newMappedList=new ArrayList();		
		try
		{
			if(_fb.getStrMappedDocCodes()!=null && _fb.getStrMappedDocCodes().length!=0){
				
				for(int i=0; i <  _fb.getStrMappedDocCodes().length; i++){				
					newMappedList.add(_fb.getStrMappedDocCodes()[i]);			
				}			
			}
		}catch (Exception e)
		{
			e.printStackTrace();	
			
		}	
		return newMappedList;
		
	}
	
	public static PatCatDocVO[] getPatCatVerDocVO(List documentList,PatCatDocVO _fb){
		PatCatDocVO[] _patCatVerDocVO=null;		
		try
		{
			if(documentList!=null && documentList.size()!=0){
				_patCatVerDocVO=new PatCatDocVO[documentList.size()];				
				for(int i=0; i < _patCatVerDocVO.length; i++){
					_patCatVerDocVO[i]=new PatCatDocVO();
					_patCatVerDocVO[i].setStrPatCategoryCode(_fb.getStrPatCategoryCode());
					_patCatVerDocVO[i].setStrDocCode((String)documentList.get(i));
					_patCatVerDocVO[i].setStrIsReq(RegistrationConfig.PATIENT_CATEGORY_VERIFICATION_MST_IS_REQ);
				}
			}
		}		
		catch (Exception e)
		{
			e.printStackTrace();			
		}
		return _patCatVerDocVO;
		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static List getCompareList(List list1, List list2){
		List resultantList=new ArrayList();		
		try
		{
			for(int i=0; i < list1.size(); i++){			
				String  str1=(String)list1.get(i);					
				if(!list2.contains(str1))
					resultantList.add(str1);			
				}		
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		
		return resultantList;
}
	
	@SuppressWarnings("rawtypes")
	public static PatCatDocVO getAddEssentials(HttpServletRequest request) 
	{
		RegMasterBO bo = new RegMasterBO();
		String strMsgText = "";

		String strTemp[] = null;
		String strTemp2[] = null;
		PatCatDocVO patCatDocModel =new PatCatDocVO();
		PatCategoryVO patCatModel =new PatCategoryVO();

		String strChk = "";

		try {

			UserVO uservo=ControllerUTIL.getUserVO(request);
			WebUTIL.refreshTransState(request,"PatCatDocMapMstACTION");
			List optionPrimaryCat=PatCatDocMapMstDATA.getPatientPrimaryCategory(uservo);
			request.getSession().setAttribute(RegistrationConfig.ESSENTIALBO_OPTION_PRIMARY_CATEGORY,optionPrimaryCat);
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY, new ArrayList());
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY, new ArrayList());

		} 
		catch (Exception e) 
		{
			strMsgText = "PatCatDocMapMstDATA.modifyRecord(fb,request) --> "			+ e.getMessage();
			patCatDocModel.setStrErrorMsg("Application Error [ERROR ID : " +strMsgText);
			HisException eObj = new HisException("registration","PatCatDocMapMstDATA->modifyRecord()", strMsgText);
			eObj = null;
		}
		finally 
		{
			strMsgText = null;
		}
		return patCatDocModel;

	}
	
	public static PatCatDocVO getModEssentials(PatCatDocVO patCatDocModel,HttpServletRequest request) 
	{
		try{
			UserVO uservo=ControllerUTIL.getUserVO(request);
			Map essentialMap=PatCatDocMapMstDATA.getCategoryWiseMappedUnMappedDocument(patCatDocModel.getStrPatCategoryCode(),uservo);
			if(!patCatDocModel.getStrPatCategoryCode().equals("-1"))
			{
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY, essentialMap.get(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY));
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY, essentialMap.get(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY));
			}
			else
			{
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_MAPPED_IN_PRIMARY_CATEGORY, new ArrayList());
			request.getSession().setAttribute(RegistrationConfig.VERIFICATION_DOCUMENT_NOT_MAPPED_IN_PRIMARY_CATEGORY, new ArrayList());
			}
				

		}catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return patCatDocModel;		
	}



	public void setHttpSession(HttpSession session)
	{
		httpSession = session;

	}

	public void setHttpSessionMap(Map session)
	{
		// TODO Auto-generated method stub

	}
	
	public void setHttpRequest(HttpServletRequest request)
	{
		this.request = request;
	}

}
