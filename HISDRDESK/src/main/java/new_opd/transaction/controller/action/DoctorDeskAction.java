package new_opd.transaction.controller.action;


/**
## 		Modification Log		: PATIENTSUMMARY					
##		Modify Date				: 20-01-2015
##		Reason	(CR/PRS)		: CR
##		Modify By				: Akash Singh
*/

import hisglobal.config.HISConfig;
import hisglobal.vo.UserVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_opd.transaction.controller.data.DoctorDeskDATA;
import new_opd.transaction.controller.fb.DoctorDeskFB;
import new_opd.transaction.controller.util.OPDTemplateMstUtil;

import org.apache.commons.fileupload.FileItem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.upload.MultipartRequestWrapper;

import application.filters.UploadMultipartRequestWrapper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;



public class DoctorDeskAction extends DispatchAction
{
	/*private static Set<Session> clients = 
		    Collections.synchronizedSet(new HashSet<Session>());
		  
		  @OnMessage
		  public void onMessage(String message, Session session) 
		    throws IOException {
		    
		    synchronized(clients){
		      // Iterate over the connected sessions
		      // and broadcast the received message
		      for(Session client : clients){
		        if (!client.equals(session)){
		          client.getBasicRemote().sendText(message);
		        }
		      }
		    }
		    
		  }
		  
		  @OnOpen
		  public void onOpen (Session session) {
		  // Add session to the connected sessions set
		    clients.add(session);
		  }

		  @OnClose
		  public void onClose (Session session) {
		    // Remove session from the connected sessions set
		    clients.remove(session);
		  }*/
	public ActionForward unspecified(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws Exception
	{
		//System.out.println("System.getProperty "+System.getProperty("catalina.base") );
		//System.out.println("new opd desk:::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrInitialMode("1");
		String HsopitalCode=(String) request.getSession().getAttribute("HOSPITAL_CODE");
		//if(HsopitalCode.equals("10911") && HsopitalCode.equals("22914")){
			DoctorDeskDATA.getDeptDtlData(formBean, request);
			return this.NEW(mapping, formBean, request, response);
			//return mapping.findForward("INITIAL");
		/*}else{
			return this.NEW1(mapping, form, request, response);
		}*/
			
			
		//DoctorDeskDATA.getInitailData(formBean ,request);
		//return this.NEW1(mapping, form, request, response);
		
	}

	/**
	 * sets the view to Patient Listing View
	 * 
	 * @param mapping -object of ActionMapping
	 * @param form - object of ActionForm
	 * @param request - HttpServletRequest
	 * @param response - HttpServletResponse
	 * @return action forwards to the output view called "NEW"
	 */
	public ActionForward NEW(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//System.out.println("123::::::::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrInitialMode("2");
		formBean.setStrmode("");
		DoctorDeskDATA.getInitailData(formBean ,request);
		formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
	
		return mapping.findForward("NEW");
	}
	public ActionForward NEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//System.out.println("123::::::::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrInitialMode("1");
		
		//formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		//formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
		
		if(request.getParameter("modeFlag") != null){
			if(request.getParameter("modeFlag").equals("M")){
				String seatId=request.getParameter("seat_id");
				String hospCode=request.getParameter("hospCode");
				String userName=request.getParameter("user_name");
				
				request.getSession().setAttribute("HOSPITAL_CODE", hospCode);
				request.getSession().setAttribute("SEATID",seatId);
				request.getSession().setAttribute("USER_NAME",userName);
				
				UserVO voGlobalUser = new UserVO();
				voGlobalUser.setSeatId(seatId);
				voGlobalUser.setUsrName(userName);
				voGlobalUser.setHospitalCode(hospCode);
				request.getSession().setAttribute(HISConfig.USER_VO,voGlobalUser);
			}
		}
		
		
		DoctorDeskDATA.getInitailDataForMobileApp(formBean ,request);
		return mapping.findForward("NEW");
	}
	/*
	public ActionForward MOBILENEW1(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		System.out.println("123::::::::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrInitialMode("1");
		
		String seatId=request.getParameter("seat_id");
		String hospCode=request.getParameter("hospCode");
		String userName=request.getParameter("user_name");
		
		request.getSession().setAttribute("HOSPITAL_CODE", hospCode);
		request.getSession().setAttribute("SEATID",seatId);
		request.getSession().setAttribute("USER_NAME",userName);
		
		UserVO voGlobalUser = new UserVO();
		voGlobalUser.setSeatId(seatId);
		voGlobalUser.setUserName(userName);
		voGlobalUser.setHospitalCode(hospCode);
		request.getSession().setAttribute(HISConfig.USER_VO,voGlobalUser);
		//objSession_p.setAttribute(HISConfig.USER_VO, voGlobalUser);
		
		
		DoctorDeskDATA.getInitailDataMobile(formBean ,request);
		return mapping.findForward("NEW");
	}
	*/
	public void GETPREV(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//System.out.println("123::::::::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		DoctorDeskDATA.getPrevDtl(request, response);
		//return mapping.findForward("NEW");
	}
	
	public ActionForward PRINTPRESC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
		return mapping.findForward("NEW1");
	}
	
	public ActionForward FILEUPLOAD(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		System.out.println(request.getParameter("vitalJSON"));
		formBean.setStrFileData(request.getParameter("vitalJSON"));
		formBean.setStrMsgType("0");
		//formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		//formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
		return mapping.findForward("FILEUPLOAD");
	}
	
	
	public ActionForward PRINTPRESCRIPTIONBOOKMARKING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
		return mapping.findForward("PRINTPRESCRIPTIONBOOKMARKING");
	}
	
	
	
	
	public ActionForward TEMPLATESAVE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		 // strTemplatetype
		//System.out.println("strTemplatetype"+request.getParameter("strTemplatetype")+"%$$$$"+request.getParameter("strtype"));
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrTemplateType(request.getParameter("strTemplatetype"));
		request.getSession().setAttribute("strTemplateType", request.getParameter("strTemplatetype"));
		request.getSession().setAttribute("strtype", request.getParameter("strtype"));
		
		return mapping.findForward("TEMPLATESAVE");
	}
	
	
	public ActionForward MOBILEPRINTPRESC(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrMobileCrno(request.getParameter("crNo")==null ? "0" : request.getParameter("crNo").toString());
		formBean.setStrMobileEpisodeCode(request.getParameter("episodecode")==null ? "0" : request.getParameter("episodecode").toString());
		formBean.setStrMobileDeptUnitCode(request.getParameter("deptunitcode")==null ? "0" : request.getParameter("deptunitcode").toString());
		formBean.setStrMobileHospitalCode(request.getParameter("hospcode")==null ? "0" : request.getParameter("hospcode").toString());
		formBean.setStrMobileEntryDate(request.getParameter("entryDate")==null ? "0" : request.getParameter("entryDate").toString());
		formBean.setStrMobileVisitNo(request.getParameter("VisiNo")==null ? "0" : request.getParameter("VisiNo").toString());
		DoctorDeskDATA.getHospitalHeader(request, response , formBean);
		return mapping.findForward("MOBILEPRINTPRESC");
	}
	
	public ActionForward PRESCRIPTIONBOOKMARKING(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		//System.out.println("123::::::::::");
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		formBean.setStrInitialMode("2");
		formBean.setStrmode("PRESCRIPTIONBOOKMARKING");
		DoctorDeskDATA.getDeptDtlData(formBean, request);
		DoctorDeskDATA.getInitailData(formBean ,request);
		
		formBean.setStrHospitalName(request.getSession().getAttribute("HOSPITAL_NAME").toString());
		formBean.setStrHospitalAddres(request.getSession().getAttribute("HOSPITAL_ADDRESS").toString());
	
		return mapping.findForward("PRESCRIPTIONBOOKMARKING");
	}
	public ActionForward SAVEFILE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		FormFile myFile =formBean.getStrLocation();
		
		/*if (myFile != null && myFile.getSize() > 0) {
		String	strFileExt = myFile.getName();
		System.out.println(strFileExt);*/
		//FormFile myFile = formBean.getStrLocation();
	    //strFileExt = myFile.getFileName();
		
		
		//System.out.println(formBean.getStrDocumenttype()+"uploaded file name is:::"+myFile.getFileName()+"::k::"+myFile.getFileData().toString());
		
		//out = new FileOutputStream( new File(fileSavePath+"/"+strHbReqNo+"_"+date.getTime()+"_"+k+"."+ext));
		/*out = new FileOutputStream( new File(fileSavePath+"/"+strHbReqNo+"."+ext));
		out.write(myfile.getFileData());
		out.close();*/
		String ftpPath = OPDTemplateMstUtil.uploadFileFromDrDesk(myFile , formBean);
		DoctorDeskDATA.saveFileData(request , response,  formBean, ftpPath);
		System.out.println();
		return mapping.findForward("FILEUPLOAD");
	}
	
	
	public ActionForward SHOWIPDLIST(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		ActionForward acFwd= new ActionForward();
		DoctorDeskFB formBean = (DoctorDeskFB) form;
		
		acFwd.setPath("/new_opd/transaction/IpdDoctorDeskAction.cnt?hmode=NEW");
		System.out.println("url for list---->"  );	
		acFwd.setContextRelative(true);
		return acFwd;
	
	}
	
}