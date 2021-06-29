package new_investigation.transactions.controller.utl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang.StringEscapeUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.Helper.TemplateProcessingUSE;
import new_investigation.transactions.controller.data.InvResultValidationDATA;
import new_investigation.transactions.controller.fb.invRequisitionFormFB;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;
import new_investigation.vo.InvCriteriaCodeVO;
import new_investigation.vo.Inv_RequisitionRaisingPatientVO;
import new_investigation.vo.Inv_SampleCollectionVO;
import new_investigation.vo.InvResultEntryVO;
import new_investigation.vo.InvResultValidationVO;
import new_investigation.vo.LabTestVO;
import new_investigation.vo.TestMandRefMasterVO;
import new_investigation.vo.invRequisitionFormVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.TemplateQueryParameterVO;
import new_investigation.vo.template.TriStringObject;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.Inv_RequisitionRaisingEpisodeVO;
import hisglobal.vo.UserVO;

public class invRequisitionFormUTIL extends ControllerUTIL {

	
	
	public static void showMandatory(HttpServletRequest request,invRequisitionFormFB fb)
	{
		
		try
		{
			
			HttpSession session=request.getSession();
			
			List<LabTestVO> invresultentryvo=null;
			ResultEntryVO invresultentryv = new ResultEntryVO();
			List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
			List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);

			 Inv_RequisitionRaisingPatientVO  lstPatVO=(Inv_RequisitionRaisingPatientVO)session.getAttribute(InvestigationConfig.PATIENT_VO);
			 
			        
					invresultentryv.setTestCode(fb.getTestCode());
					invresultentryv.setLabCode(fb.getLabCode());
					invresultentryv.setParaType("2");
					invresultentryv.setHospitalcode(userVO.getHospitalCode());
					invresultentryv.setPatAge(lstPatVO.getPatAge());
					invresultentryv.setPatGender(lstPatVO.getPatGender());
					invresultentryv.setRequisitionDNo(fb.getRequisitionDNo());
					lrvo.add(invresultentryv);
					
					invresultentryv.setResultEntryVOListValidatedBy(lrvo); 
					lstInvResultEntryVO.add(invresultentryv);
						
			
			
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=TemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
					

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static void showSampleCollection(HttpServletRequest request,invRequisitionFormFB fb)
	{
		
		try
		{
			
			HttpSession session=request.getSession();
			
			List<LabTestVO> invresultentryvo=null;
			ResultEntryVO invresultentryv = new ResultEntryVO();
			List<ResultEntryVO> lstInvResultEntryVO=new ArrayList<ResultEntryVO>();
			List<ResultEntryVO> lrvo = new ArrayList<ResultEntryVO>();
			List<String> reqList=new ArrayList();
			//fb.setisPatDetailPage("1");
			UserVO userVO = ControllerUTIL.getUserVO(request);

			Inv_SampleCollectionVO voSampleCollection= (Inv_SampleCollectionVO)session.getAttribute(InvestigationConfig.SELECTED_PAT_DETAILS);
		    
			if(voSampleCollection!=null)
			{
			System.out.println("voSampleCollection"+voSampleCollection);
				
			System.out.println("voSampleCollection"+voSampleCollection.getpatName());   
			System.out.println("voSampleCollection"+voSampleCollection.getPatGuardianName());
			invresultentryv.setTestCode(fb.getTestCode());
			invresultentryv.setLabCode(fb.getLabCode());
			invresultentryv.setParaType("2");
			invresultentryv.setHospitalcode(userVO.getHospitalCode());
			invresultentryv.setPatAge(voSampleCollection.getPatAge());
			invresultentryv.setPatGender(voSampleCollection.getPatGender());
			invresultentryv.setRequisitionDNo(fb.getRequisitionDNo());
			lrvo.add(invresultentryv);
			}
			else
			{
				invresultentryv.setHospitalcode(userVO.getHospitalCode());
				invresultentryv.setTestCode(fb.getTestCode());
				invresultentryv.setLabCode(fb.getLabCode());
				invresultentryv.setParaType("2");
				invresultentryv.setRequisitionDNo(fb.getRequisitionDNo());
				lrvo.add(invresultentryv);
				
			}
			
				
					invresultentryv.setResultEntryVOListValidatedBy(lrvo); 
					lstInvResultEntryVO.add(invresultentryv);
						
			
			
			List<ResultEntryVO> lstInvResultEntryTemplateVO=null;
			lstInvResultEntryTemplateVO=TemplateProcessingUSE.groupSelectedWorkOrders(lstInvResultEntryVO, session);
					

		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
}
