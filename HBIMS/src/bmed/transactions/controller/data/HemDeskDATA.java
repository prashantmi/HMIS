package bmed.transactions.controller.data;

import hisglobal.exceptions.HisException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.utility.HisUtil;
import hisglobal.vo.UserVO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.WebRowSet;

import bmed.EMMSStaticConfigurator;
import bmed.global.controller.util.BmedConfigUtil;
import bmed.transactions.bo.BmedGlobalBO;
import bmed.transactions.bo.BmedTransBO;
import bmed.transactions.controller.fb.ComplaintMaintenanceStatusFB;
import bmed.transactions.controller.fb.HemDeskFB;
import bmed.vo.ComplaintRequestDtlVO;
import bmed.vo.HemDeskVO;
import bmed.vo.HemtComplaintEscalationDtlVO;
import bmed.vo.HemtItemMcDtlVO;
import bmed.vo.WarrantyDtlVO;

public class HemDeskDATA 
{
	
	 /**
     * 
     * @param hemComplaintApprovalDeskFB
     * @param request_p
     */
	public static String initializeGrievances(HemDeskFB HemDeskFB_p,HttpServletRequest request_p) 
	{
	  
		String strErrMsg;
		String strWarrantyDetailsTable;
		String strMaintenanceContractDetailsTable;
		String strUrl;
		String strReqType="";
		
		String strChk ;
		String strHospitalCode_p;
		String strEnggItemTypeCmb;
		String strHemDesk="";
		
		UserVO userVo     = null;
		//String strCombo[] = null;
		String strTmp[]   = null;
		BmedTransBO     bmedTransBO = null;
		BmedGlobalBO   bmedGlobalBO = null;
		HemDeskVO       hemDeskVO_p = null;
		WarrantyDtlVO warrantyDtlVO = null;
		HemtItemMcDtlVO hemtItemMcDtlVO = null;
		ComplaintRequestDtlVO complaintRequestDtlVO = null;
				
		try
		{
			                                
			                                   bmedGlobalBO = new BmedGlobalBO();
			                                    bmedTransBO = new BmedTransBO();
			                                  warrantyDtlVO = new WarrantyDtlVO();
			                      			hemtItemMcDtlVO = new HemtItemMcDtlVO();
			                      	  complaintRequestDtlVO = new ComplaintRequestDtlVO();
			                      			    hemDeskVO_p = new HemDeskVO();
							                         userVo = ControllerUTIL.getUserVO(request_p);
							              strHospitalCode_p = userVo.getHospitalCode();
								                     strUrl = "/bmed"+request_p.getParameter("cnt_page")+".cnt";
									
									if(request_p.getParameter("cnt_page") == null)
									{
										             strUrl = request_p.getParameter("strPath");
									}
									HemDeskFB_p.setStrPath(strUrl.trim());
							                       //strCombo = request_p.getParameterValues("combo");
									 if (request_p.getParameter("chk") == null) 
									 {
											         strChk = HemDeskFB_p.getStrChk();
									 } 
									 else 
									 {
											         strChk = request_p.getParameter("chk");
									 }
							 HemDeskFB_p.setStrChk(strChk);
							                         strTmp = strChk.split("\\@");
							 warrantyDtlVO.setStrHospitalCode(strHospitalCode_p);
							 hemtItemMcDtlVO.setStrHospCode(strHospitalCode_p);
							 HemDeskFB_p.setStrReqType(strTmp[3].split("\\$")[0]);
							 hemDeskVO_p.setStrReqType(strTmp[3].split("\\$")[0]);
							 
							 complaintRequestDtlVO.setStrReqId(strTmp[0]);			
							 complaintRequestDtlVO.setStrHospitalCode(strHospitalCode_p);
							 
							 hemDeskVO_p.setStrHospCode(strHospitalCode_p);
							 hemDeskVO_p.setStrComplaintId(strTmp[0]);
					 
			 
			    // Calling BO method here [ Here we get All Complaint details,Warranty details and Maintenance details ]			    			    
			    bmedTransBO.initializeComplaintApprovalActions(complaintRequestDtlVO,warrantyDtlVO,hemtItemMcDtlVO);
			    
			    bmedTransBO.getPreviousEscInternalDtl(hemDeskVO_p,"1");
			    // Only in Case of External
			    if(strTmp[3].split("\\$")[0].equals("2"))
			    {
			    	// Calling BO method
			    	bmedTransBO.getCommunicationIDCmb(hemDeskVO_p,"3");
			    	bmedTransBO.getEscLevelTypeCmb(hemDeskVO_p, "1");
			    	// Set value in formBean
			    	HemDeskFB_p.setStrEscalationLevelCmb(hemDeskVO_p.getStrEscalationLevelCmb());
			    	HemDeskFB_p.setStrCommunicationCmb(hemDeskVO_p.getStrCommunicationCmb());
			    }
			    else
			    {
			    	 strEnggItemTypeCmb = bmedGlobalBO.getEnggItemTypeComboOptions(Config.SUPER_USER_HOSPITAL_CODE);
			    	 HemDeskFB_p.setStrEnggItemTypeCmb(strEnggItemTypeCmb);
			    }	
			    // Set Value in FormBean
			    HemDeskFB_p.setStrComplaintId(complaintRequestDtlVO.getStrReqId());
			    HemDeskFB_p.setStrComplaintDate(complaintRequestDtlVO.getStrReqDate());
			    HemDeskFB_p.setStrItemName(complaintRequestDtlVO.getStrItemName());
			    HemDeskFB_p.setStrItemBatchNo(complaintRequestDtlVO.getStrBatchNo());
			    HemDeskFB_p.setStrItemSerialNo(complaintRequestDtlVO.getStrSerialNo());
			    HemDeskFB_p.setStrManufacturerSerialNo(complaintRequestDtlVO.getStrManufSerialNo());
			    HemDeskFB_p.setStrComplaintDescription(complaintRequestDtlVO.getStrComplaintDes());
			    HemDeskFB_p.setStrContactNo(complaintRequestDtlVO.getStrContactNo());
			    HemDeskFB_p.setStrDeptName(complaintRequestDtlVO.getStrDeptName());
			    HemDeskFB_p.setStrStoreName(complaintRequestDtlVO.getStrStoreName());
			    HemDeskFB_p.setStrReqId(complaintRequestDtlVO.getStrReqId());
			
			    
			   
			    
                // Calling Warranty & MC Details 
				           strWarrantyDetailsTable = getWarrantyDetailsTable(warrantyDtlVO.getWrsResultData(), "WITHOUT_RADIO_BUTTON");
				strMaintenanceContractDetailsTable = getMaintenanceContractDetailsTable(hemtItemMcDtlVO.getWrsMCDetails(), "WITHOUT_RADIO_BUTTON"); 
			
				HemDeskFB_p.setStrWarrantyDetailsTable(strWarrantyDetailsTable);
				HemDeskFB_p.setStrMaintenanceContractDetailsTable(strMaintenanceContractDetailsTable);
				HemDeskFB_p.setStrPrevEsclationDtl(hemDeskVO_p.getStrPrevEsclationDtl());
				
				
				strReqType=complaintRequestDtlVO.getStrReqType();
				strHemDesk=request_p.getParameter("strHemDesk");
				if(strHemDesk==null) {
					/*strHemDesk may not be set if it comes from HemDesk*/
					strHemDesk="1";
				}
				HemDeskFB_p.setStrHemDesk(strHemDesk);
		}
		catch (Exception e) 
		{
//	        e.printStackTrace();
			      strErrMsg = "HemDeskDATA.initializeGrievances() --> "+ e.getMessage();
		  HisException eObj = new HisException("BMED", "HemDeskDATA",strErrMsg);
		  HemDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
		return strReqType;
		
	}
	
	/**
     * This method is used to get Engg Item Sub Type Combo
     * @param HemDeskFB_p
     * @param request_p
     * @param response_p
     */
    public static void getEnggItemSubType(HemDeskFB hemDeskFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strEnggItemSubTypeComboOptions;
	String strHospitalCode_p=null;
	UserVO userVo = null;
	String strEnggItemTypeId_p;
	try
	{
		                  bmedGlobalBO = new BmedGlobalBO();
		                        userVo = ControllerUTIL.getUserVO(request_p);
		             strHospitalCode_p = userVo.getHospitalCode();
		           strEnggItemTypeId_p = (String) request_p.getParameter("enggItemTypeId");
		
		//Calling BO method here
		strEnggItemSubTypeComboOptions = bmedGlobalBO.getItemSubTypeComboOptions(strHospitalCode_p, strEnggItemTypeId_p);
		
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strEnggItemSubTypeComboOptions);
				
		}
		catch(Exception e)
		{
//				e.printStackTrace();
		}
		

		} catch (Exception e) {
	
			strErrMsg = "HemDeskDATA.getEnggItemSubType() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		hemDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
    
    /**
     * This method is used to get Engg Item Sub Type Combo
     * @param HemDeskFB_p
     * @param request_p
     * @param response_p
     */
    public static void getServiceEnggName(HemDeskFB hemDeskFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strServiceEnggComboOptions;
	String strHospitalCode_p=null;
	UserVO userVo = null;
	HemDeskVO hemDeskVO_p = null;
	String strEnggItemTypeId_p,strEnggItemSubTypeId_p;
	try
	{
		                  bmedGlobalBO = new BmedGlobalBO();
		                   hemDeskVO_p = new HemDeskVO();
		                        userVo = ControllerUTIL.getUserVO(request_p);
		             strHospitalCode_p = userVo.getHospitalCode();
		           strEnggItemTypeId_p = (String) request_p.getParameter("enggItemTypeId");
		        strEnggItemSubTypeId_p = (String) request_p.getParameter("enggItemSubTypeId");
		        
		        hemDeskVO_p.setStrMode("6");
		        hemDeskVO_p.setStrEnggItemTypeId(strEnggItemTypeId_p);
		        hemDeskVO_p.setStrEnggItemSubTypeId(strEnggItemSubTypeId_p);
		        hemDeskVO_p.setStrHospCode(strHospitalCode_p);
		      
		//Calling BO method here
		    strServiceEnggComboOptions = bmedGlobalBO.getServiceEnggNameCombo(hemDeskVO_p);
		
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strServiceEnggComboOptions);
				
		}
		catch(Exception e)
		{
		//		e.printStackTrace();
		}
		

		} catch (Exception e) {
	
			strErrMsg = "HemDeskDATA.getServiceEnggName() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		hemDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
    
    
    /**
     * This method is used to get Engg Item Sub Type Combo
     * @param HemDeskFB_p
     * @param request_p
     * @param response_p
     */
    public static void getEscLevel(HemDeskFB hemDeskFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedGlobalBO bmedGlobalBO=null;
	String strServiceEnggComboOptions;
	String strHospitalCode_p=null;
	UserVO userVo = null;
	HemDeskVO hemDeskVO_p = null;
	String strEnggItemTypeId_p,strEnggItemSubTypeId_p,strEmpId_p;
	try
	{
		                  bmedGlobalBO = new BmedGlobalBO();
		                   hemDeskVO_p = new HemDeskVO();
		                        userVo = ControllerUTIL.getUserVO(request_p);
		             strHospitalCode_p = userVo.getHospitalCode();
		           strEnggItemTypeId_p = (String) request_p.getParameter("enggItemTypeId");
		        strEnggItemSubTypeId_p = (String) request_p.getParameter("enggItemSubTypeId");
		                    strEmpId_p = "0";        
		        hemDeskVO_p.setStrMode("2");
		        hemDeskVO_p.setStrEnggItemTypeId(strEnggItemTypeId_p);
		        hemDeskVO_p.setStrEnggItemSubTypeId(strEnggItemSubTypeId_p);
		        hemDeskVO_p.setStrHospCode(strHospitalCode_p);
		        hemDeskVO_p.setStrEmpId(strEmpId_p);
		    //Calling BO method here
		    strServiceEnggComboOptions = bmedGlobalBO.getEscLevelCombo("2",strHospitalCode_p,strEnggItemTypeId_p,strEnggItemSubTypeId_p,strEmpId_p);
		
		try 
		{									
			response_p.setHeader("Cache-Control", "no-cache");
			response_p.getWriter().print(strServiceEnggComboOptions);
				
		}
		catch(Exception e)
		{
		//		e.printStackTrace();
		}
		

		} catch (Exception e) {
	
			strErrMsg = "HemDeskDATA.getEscLevel() --> "
				+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",
				strErrMsg);
		hemDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "
				+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
    
    /**
     * Method is Used to Save Data into table HEMT_COMPLAINT_ESCALATION_DTL
     * @param ItemWarrantyDtlsFB_p
     * @param request_p
     */		    
    public static void save(HemDeskFB hemDesk_p, HttpServletRequest request_p) 
    {
		String strErrMsg;
		String strSeatId_p;
		String strHospitalCode_p;
		UserVO userVo = null;
		
		HemtComplaintEscalationDtlVO hemtComplaintEscalationDtlVO =null;
		HisUtil hisutil = null;
		String strChk;
		String strTmp[] = null;
		try
		{					     
						
			           userVo = ControllerUTIL.getUserVO(request_p);
			strHospitalCode_p = userVo.getHospitalCode();
			      strSeatId_p = userVo.getSeatId();
			      
			      hisutil  = new HisUtil("","");
			      hemtComplaintEscalationDtlVO = new HemtComplaintEscalationDtlVO();
			
				strChk = hemDesk_p.getStrChk();
			
			strTmp = strChk.split("\\@");
			
			hemtComplaintEscalationDtlVO.setStrMode("1");
			hemtComplaintEscalationDtlVO.setStrReqId(strTmp[0]);
			hemtComplaintEscalationDtlVO.setStrHospitalCode(strHospitalCode_p);
			hemtComplaintEscalationDtlVO.setStrEscId("0"); //Enter by function
			hemtComplaintEscalationDtlVO.setStrReqType(strTmp[3].split("\\$")[0]);
			hemtComplaintEscalationDtlVO.setStrEscLevel(hemDesk_p.getStrEscalationLevelId().split("\\^")[0]);
			
			if(strTmp[3].split("\\$")[0].equals("1"))
			{	
			    hemtComplaintEscalationDtlVO.setStrVendorCommId("0");
			    hemtComplaintEscalationDtlVO.setStrEscLevel(hemDesk_p.getStrEscalationLevelId().split("\\^")[0]);
			    hemtComplaintEscalationDtlVO.setStrServiceEnggId(hemDesk_p.getStrServiceEnggId());
			    hemtComplaintEscalationDtlVO.setStrEscTo(hemDesk_p.getStrEscalationLevelId().split("\\^")[1]);
			}
			else
			{
				hemtComplaintEscalationDtlVO.setStrVendorCommId(hemDesk_p.getStrCommunicationId());	
				hemtComplaintEscalationDtlVO.setStrEscLevel(hemDesk_p.getStrEscalationLevelId());
				hemtComplaintEscalationDtlVO.setStrServiceEnggId("0");
				hemtComplaintEscalationDtlVO.setStrEscTo("0");
			}
			
			hemtComplaintEscalationDtlVO.setStrEscMode(hemDesk_p.getStrModeofEscId());
			hemtComplaintEscalationDtlVO.setStrEscModeNo("1");		
			hemtComplaintEscalationDtlVO.setStrEscToName(hemDesk_p.getStrName());
			hemtComplaintEscalationDtlVO.setStrEscToDesignation(hemDesk_p.getStrDesignation());			
			hemtComplaintEscalationDtlVO.setStrEscDate(hemDesk_p.getStrEscDate()+" "+hemDesk_p.getStrEscTime());
			hemtComplaintEscalationDtlVO.setStrRemarks(hemDesk_p.getStrRemarks());
			hemtComplaintEscalationDtlVO.setStrEntryDate(hisutil.getDSDate("DD-Mon-YYYY"));
			hemtComplaintEscalationDtlVO.setStrSeatId(strSeatId_p);
			hemtComplaintEscalationDtlVO.setStrIsValid("1");
					
			
		       /**Calling BO Method Here**/
			   BmedTransBO.save(hemtComplaintEscalationDtlVO);
			
		}
		catch (Exception e) 
		{
			e.printStackTrace();
			     strErrMsg = "HemDeskDATA.save() --> "	+ e.getMessage();
		 HisException eObj = new HisException("BMED", "HemDeskDATA",strErrMsg);
		 hemDesk_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");

		eObj = null;
		}
		
	}
    
    
    /**
     * This method is used to get Engg Item Sub Type Combo
     * @param HemDeskFB_p
     * @param request_p
     * @param response_p
     */
    public static void getDetails(HemDeskFB hemDeskFB_p, HttpServletRequest request_p,HttpServletResponse response_p) {
	String strErrMsg;
	BmedTransBO     bmedTransBO = null;
	String strDtl="";
	String strHospitalCode_p=null;
	UserVO userVo = null;
	
	String strEmpId_p;
	try
	{
		                   bmedTransBO = new BmedTransBO();
		                        userVo = ControllerUTIL.getUserVO(request_p);
		             strHospitalCode_p = userVo.getHospitalCode();
		                    strEmpId_p = (String) request_p.getParameter("escalationLevelId").split("\\^")[1];
		       		       		      
		    //Calling BO method here
		                        strDtl = bmedTransBO.getDtl("1",strHospitalCode_p,strEmpId_p);
		
			try 
			{									
				response_p.setHeader("Cache-Control", "no-cache");
				response_p.getWriter().print(strDtl);
					
			}
			catch(Exception e)
			{
					//e.printStackTrace();
			}
		

		} 
	    catch (Exception e) 
	    {
	
			strErrMsg = "HemDeskDATA.getDetails() --> "+ e.getMessage();
		HisException eObj = new HisException("BMED", "ItemMaintContractDtlsDATA",strErrMsg);
		hemDeskFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
		eObj = null;
		}
		
	}
    
    
    
	/**
	 * 
	 * @param viewValuesWS
	 * @return
	 * @throws Exception
	 */
	
	public static String getPreviousEsc(WebRowSet viewValuesWS,String strMode)throws Exception
	{

		StringBuffer br = new StringBuffer("");
		WebRowSet wb = null;
		
		try 
		{
			wb = viewValuesWS;
			
            /*			
         
			Value in WebRowSet----

			 1.HEMNUM_REQ_ID 
			 2.GNUM_HOSPITAL_CODE 
			 3.HEMNUM_ESCALATION_ID
			 4.HEMNUM_REQ_TYPE 
			 5.HEMNUM_ESCALATION_LEVEL
			 6.HEMNUM_VENDOR_COMM_ID 
			 7.HEMNUM_ESCALATION_MODE
			 8.HEMSTR_ESCALATION_MODE_NUMBER
             9.HEMSTR_SERVICE_ENGG_ID
             10.HEMSTR_ESCALATION_TO
             11.HEMSTR_ESCALATION_TO_NAME 
             12.HEMSTR_ESCALATION_TO_DESIG
             13.TO_CHAR(HEMSTR_ESCALATION_DATE , 'DD-Mon-yyyy')
             14.GSTR_REMARKS
             15.TO_CHAR(GDT_ENTRY_DATE , 'DD-Mon-yyyy')
             16.GNUM_ISVALID 
             17.GNUM_SEATID
             18.BMED_FUNCTION.get_level_type_name ( GNUM_HOSPITAL_CODE,HEMNUM_ESCALATION_LEVEL)
             19.DECODE(HEMNUM_REQ_TYPE,'1','Internal','2','External')AS Req_type,
             20.DECODE(HEMNUM_ESCALATION_MODE,'1','Phone','2','E-Mail','3','Fax','4','Post')                             
                                	       
            */
			
			
			if (wb != null) 
			{
				if(wb.size() != 0)
				{
				 
					br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
				    br.append("<tr class='FOOTER_TR'>"); 
				    if(strMode.equals("1"))
				        br.append("<th colspan='8' align='left'>Previous Escalations (Internal)</th>");
				    else
				    	br.append("<th colspan='8' align='left'>Previous Escalations (External)</th>");	
				    br.append("</tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='20%'>Escalation Level</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='32%'>Name of Person</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='24%'>Designation</td>");
					br.append("<td CLASS='LABEL_TD' style='text-align:center' width='24%'>Date/Time</td>");
					
					br.append("</tr>");
					
					br.append("</table>");
					br.append("</his:ContentTag>");
		        
						
						
							    br.append("<table class='TABLE_STYLE'>");
							    while(wb.next())
								{
							     br.append("<tr>");
								 br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='20%'>"+wb.getString(18)+"</td>");
								 br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='32%'>"+wb.getString(11)+"</td>");
							     br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='24%'>"+wb.getString(12)+"</td>");
								 br.append("<td CLASS='CONTROL_TD' style='text-align:center' width='24%'>"+wb.getString(13)+"</td>");
								 br.append("</tr>");
								}
								br.append("</table>");
								br.append("</his:ContentTag>");

				        
				
			  
			   }
			   else
			   {
				    br.append("<his:ContentTag>");
					br.append("<table class='TABLE_STYLE'>");
				    br.append("<tr class='FOOTER_TR'>"); 
				    br.append("<th colspan='8' align='left'>Previous Escalations (Internal)</th>");
				    br.append("</tr>");
					br.append("</table>");
					br.append("</his:ContentTag>");
					br.append("<his:ContentTag>");
				    br.append("<table class='TABLE_STYLE'>");
					br.append("<TR>");
					br.append("<TD WIDTH='25%' CLASS='CONTROL_TD' style='text-align:center'><font color = 'red'>"
								+ "No Previous Escalations Details Found" + "</font></TD>");
					br.append("</TR>");
					br.append("</table>");
					br.append("<his:ContentTag>");
				
			   }   
					
		   }
		}	
		
		catch (Exception e) 
		{
			throw new Exception("HemDeskDATA.getPreviousEsc()->"+e.getMessage());
			
		}
		return br.toString();
	}
	
    
	

    
	
	private static String getMaintenanceContractDetailsTable(
			WebRowSet wrsData_p, String strDisplayMode_p) throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);
		int nWidth;
		int nColspan;
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			nWidth = 19;
			nColspan = 6;
		} else {
			nWidth = 20;
			nColspan = 5;
		}
		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			sbHeader.append("<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\"></td>");
		}
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Supplier Name</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Start Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">End Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Terms &amp; Condition</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">File</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HEMSTR_MC_TYPE: 1
			// HEMNUM_ITEM_SL_NO: 2
			// HEMNUM_ITEM_ID: 3
			// HEMSTR_BATCH_NO: 4
			// HEMNUM_MANUF_SL_NO: 5
			// HEMNUM_SL_NO: 6
			// HEMSTR_TERM_N_CON: 7
			// HEMNUM_IS_ITEM: 8
			// HEMNUM_ROUTINE_VISIT: 9
			// HEMSTR_MC_NAME: 10
			// HEMNUM_BREAK_VISIT: 11
			// HEMNUM_MANUF_ID: 12
			// HEMSTR_RESPONSE_TIME: 13
			// HEMNUM_COST: 14
			// HEMSTR_PENALTY_CON: 15
			// GDT_ENTRY_DATE: 16
			// GNUM_ISVALID: 17
			// GNUM_SEAT_ID: 18
			// GNUM_HOSPITAL_CODE: 19
			// HEMNUM_ROUTINE_FREQ: 20
			// HEMSTR_FREQ_UNIT: 21
			// HEMSTR_RES_TIME_UNIT: 22
			// HEMDT_START_DATE: 23
			// HEMDT_END_DATE: 24
			// HEMSTR_TENDER_NO: 25
			// HPURNUM_UPLOAD_NO: 26
			// HPURSTR_DOC_REF_NO: 27
			// HEMDT_TENDER_DATE: 28
			// HPURDT_DOC_REF_DATE: 29
			// HEMSTR_ORDER_NO: 30
			// HEMNUM_CANCEL_SEATID: 31
			// HEMDT_ORDER_DATE: 32
			// HEMDT_FINANCIAL_START_YEAR: 33
			// GDT_EFFECTIVE_FROM: 34
			// GSTR_REMARKS: 35
			// HEMDT_FINANCIAL_END_YEAR: 36
			// HEMNUM_IS_RENEWED: 37
			// HEMNUM_CANCEL_ID: 38
			// HEMDT_CANCEL_DATE: 39
			// HEMSTR_CANCEL_REMARKS: 40
			// MAINTE_TYPE_NAME: 41
			// ITEM_NAME: 42
			// FREQ_UNIT_NAME: 43
			// RES_TIME_UNIT_NAME: 44
			// VENDOR_NAME: 45

			while (wrsData_p.next()) {

				String strSupplierName = wrsData_p.getString("VENDOR_NAME");
				String strStartDate = wrsData_p.getString("HEMDT_START_DATE");
				String strEndDate = wrsData_p.getString("HEMDT_END_DATE");
				String strTermsAndCondition = wrsData_p
						.getString("HEMSTR_TERM_N_CON");
				String strFileName = wrsData_p.getString("HPURSTR_DOC_REF_NO");

				String strMcSlNo = wrsData_p.getString("HEMNUM_SL_NO");
				if (strSupplierName == null) {
					strSupplierName = "---";
				}
				if (strStartDate == null) {
					strStartDate = "---";
				}
				if (strEndDate == null) {
					strEndDate = "---";
				}

				if (strTermsAndCondition == null) {
					strTermsAndCondition = "---";
				}
				if (strFileName == null) {
					strFileName = "";
				}
				if (strMcSlNo == null) {
					strMcSlNo = "0";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
					sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" value=\""
							+ strMcSlNo + "^MAINTENANCE\"/></td>");
				}
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strSupplierName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strStartDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strEndDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strTermsAndCondition + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style=\"cursor: pointer;\" onclick=\"showDoc('"
						+ strFileName + "')\">" + strFileName + "</a></td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\""
					+ nColspan
					+ "\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}
	
	
	private static String getWarrantyDetailsTable(WebRowSet wrsData_p,
			String strDisplayMode_p) throws SQLException {

		StringBuffer sbHeader = new StringBuffer(100);
		StringBuffer sbBody = new StringBuffer(100);

		int nWidth;
		int nColspan;
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			nWidth = 19;
			nColspan = 6;
		} else {
			nWidth = 20;
			nColspan = 5;
		}

		/*
		 * Header Row:
		 */

		sbHeader.append("<tr>");
		if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
			sbHeader.append("<td width=\"5%\" class=\"LABEL_TD\" style=\"text-align: center;\"></td>");
		}
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Supplier Name</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Warranty Date/Upto</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Extend Date</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">Terms &amp; Condition</td>");
		sbHeader.append("<td width=\""
				+ nWidth
				+ "%\" class=\"LABEL_TD\" style=\"text-align: center;\">File</td>");
		sbHeader.append("</tr>");

		if (wrsData_p != null && wrsData_p.size() > 0) {
			/* Result Index */
			// HSTNUM_ITEM_ID:1
			// HSTNUM_ITEMBRAND_ID:2
			// HSTSTR_BATCH_SL_NO:3
			// GNUM_HOSPITAL_CODE:4
			// HSTNUM_ITEM_SL_NO:5
			// HSTNUM_MANUF_ID:6
			// HSTNUM_MANUF_SL_NO:7
			// HSTNUM_SL_NO:8
			// HSTDT_WARRENTY_DATE:9
			// HSTNUM_WARRENTY_UPTO:10
			// HSTNUM_WARRENTY_UPTO_UNIT:11
			// HSTNUM_IS_ITEM:12
			// HSTDT_FINANCIAL_START_YEAR:13
			// HSTDT_FINANCIAL_END_YEAR:14
			// HPURNUM_UPLOAD_NO:15
			// HPURSTR_DOC_REF_NO:16
			// HEMSTR_TERM_N_CON:17
			// HPURDT_DOC_REF_DATE:18
			// GSTR_REMARKS:19
			// GDT_ENTRY_DATE:20
			// GNUM_SEATID:21
			// GNUM_ISVALID:22
			// HEMSTR_TENDER_NO:23
			// GDT_LSTMOD_DATE:24
			// HEMDT_TENDER_DATE:25
			// GNUM_LSTMOD_SEATID:26
			// HEMSTR_ORDER_NO:27
			// HEMDT_ORDER_DATE:28
			// HEMNO_CANCEL_ID:29
			// HEMDT_CANCEL_DATE:30
			// HEMSTR_EXT_TERM_N_CON:31
			// HEMSTR_CANCEL_REMARKS:32
			// HEMNUM_IS_EXTENDED:33
			// HEMDT_EXTENDED_START_DATE:34
			// HEMNUM_EXTENDED_UPTO:35
			// HEMNUM_EXTENDED_UPTO_UNIT:36
			// HPURNUM_EXT_UPLOAD_NO:37
			// HPURNUM_EXT_DOC_REF_NO:38
			// HPURDT_EXT_DOC_REF_DATE:39
			// ITEM_NAME:40
			// VENDOR_NAME:41
			// WARRENTY_UPTO_UNIT_NAME:42
			// WARRANTY_EXTEND_DATE:43

			while (wrsData_p.next()) {

				String strSupplierName = wrsData_p.getString("VENDOR_NAME");
				String strWarrantyDate = wrsData_p
						.getString("HSTDT_WARRENTY_DATE");
				String strWarrantyUpto = wrsData_p
						.getString("HSTNUM_WARRENTY_UPTO");
				String strWarrantyUptoUnit = wrsData_p
						.getString("WARRENTY_UPTO_UNIT_NAME");
				String strExtendDate = wrsData_p
						.getString("WARRANTY_EXTEND_DATE");
				String strTermsAndCondition = wrsData_p
						.getString("HEMSTR_TERM_N_CON");
				String strFileName = wrsData_p.getString("HPURSTR_DOC_REF_NO");
				String strWarrantySlNo = wrsData_p.getString("HSTNUM_SL_NO");

				if (strSupplierName == null) {
					strSupplierName = "---";
				}
				if (strWarrantyDate == null) {
					strWarrantyDate = "---";
				}
				if (strWarrantyUpto == null) {
					strWarrantyUpto = "---";
				}
				if (strWarrantyUptoUnit == null) {
					strWarrantyUptoUnit = "---";
				}
				if (strExtendDate == null) {
					strExtendDate = "---";
				}
				if (strTermsAndCondition == null) {
					strTermsAndCondition = "---";
				}
				if (strFileName == null) {
					strFileName = "";
				}
				if (strWarrantySlNo == null) {
					strWarrantySlNo = "";
				}

				/*
				 * Table Body
				 */

				sbBody.append("<tr>");
				if (strDisplayMode_p.equals("WITH_RADIO_BUTTON")) {
					sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><input type=\"radio\" name=\"strWarrantyOrMaintenanceSlNoAndType\" value=\""
							+ strWarrantySlNo + "^WARRANTY\"/></td>");
				}
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strSupplierName + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strWarrantyDate
						+ " / "
						+ strWarrantyUpto
						+ " "
						+ strWarrantyUptoUnit + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strExtendDate + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\">"
						+ strTermsAndCondition + "</td>");
				sbBody.append("<td class=\"CONTROL_TD\" style=\"text-align: center;\"><a style=\"cursor: pointer;\" onclick=\"showDoc('"
						+ strFileName + "')\">" + strFileName + "</a></td>");
				sbBody.append("</tr>");

			}

		} else {
			sbBody.append("<tr>");
			sbBody.append("<td colspan=\""
					+ nColspan
					+ "\" class=\"CONTROL_TD\" style=\"text-align: center; color: red;\">No Data Found!</td>");
			sbBody.append("</tr>");
		}

		return sbHeader.toString() + sbBody.toString();
	}

	public static void getUploadedFile(
			HemDeskFB complaintMaintenanceStatusFB_p,
			HttpServletRequest request_p, HttpServletResponse response_p)

	{
		String strmsgText = null;
		String strFileName = "";
		File f = null;
		FileInputStream fis = null;
		byte[] fileContent = new byte[1024];
		BmedConfigUtil bmed =null;
		String strFtpFolderName ="";
		UserVO userVo = null;
		
		try 
		{
			   
			   bmed = new BmedConfigUtil();
			   userVo = ControllerUTIL.getUserVO(request_p);
			   strFtpFolderName = bmed.getStrFtpFileFolder("15", userVo.getHospitalCode());
		       
			  
			   
			   if(strFtpFolderName.equals("")||strFtpFolderName==null)
			   {
				    strFtpFolderName = "bmedDOCS";
			   } 
			
			
			strFileName = complaintMaintenanceStatusFB_p.getStrUploadFileId();
						
			//System.out.println("File Name::::::"+ItemWarrantyDtlsFB_p.getStrUploadFileId());
			String[] strTemp = strFileName.replace(".", "#").split("#");
			String strExt = strTemp[strTemp.length - 1];
			
			 if (strExt.equalsIgnoreCase("txt")
					|| strExt.equalsIgnoreCase("txt")) {
				
				response_p.setContentType("application/txt");
				response_p.setHeader("Content-disposition",
						" filename="+strFileName);
				
			}
			 else if (strExt.equalsIgnoreCase("pdf")) 
			{
                
				response_p.setContentType("application/pdf");
				response_p.setHeader("Content-disposition",	"attachment; filename="+strFileName);

			} else if (strExt.equalsIgnoreCase("html")
					|| strExt.equalsIgnoreCase("htm")) {
				
				response_p.setContentType("text/html;charset=utf-8");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			}else if (strExt.equalsIgnoreCase("xml")) {
				
				response_p.setContentType("application/xml");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("doc") || strExt.equalsIgnoreCase("docx")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("rdf")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if (strExt.equalsIgnoreCase("rtf")) {
				
				response_p.setContentType("application/msword");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if(strExt.equalsIgnoreCase("png")){

				response_p.setContentType("image/png");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			} else if(strExt.equalsIgnoreCase("gif")){

				response_p.setContentType("image/gif");
				response_p.setHeader("Content-disposition",
					"attachment; filename="+strFileName);
			
			} else if(strExt.equalsIgnoreCase("jpeg") || strExt.equalsIgnoreCase("jpg")){
	
				response_p.setContentType("image/jpg");
				response_p.setHeader("Content-disposition",
					"attachment; filename="+strFileName);
			
			} else {

				response_p.setContentType("text/html;charset=utf-8");
				response_p.setHeader("Content-disposition",
						"attachment; filename="+strFileName);
				
			}			/*******************************************************************/

			 // String sessionFtpAddress=EMMSStaticConfigurator.bmedpath; //populate from session 10.0.5.152/ftpserver
			  //String logicalName = sessionFtpAddress.replace('/', '#').split("#")[1];
			/*  String Fileurl= "ftp://"+sessionFtpAddress+"/"+strFtpFolderName;			
			 
			  System.out.println("test:::"+Fileurl);
			 
			  URL                  urlftp = new URL(Fileurl+"/"+strFileName);
			  URLConnection          urlc =	urlftp.openConnection();
			  InputStream              io = urlc.getInputStream();
			  	*/	  
				 fis = new FileInputStream( new File(HisUtil.getParameterFromHisPathXML("TEMP_PATH")+"/"+strFileName));

			        FileOutputStream fos = new FileOutputStream(strFileName);
			        byte[] buf = new byte[4096];
			        int read = 0;
			        while ((read = fis.read(buf)) > 0) 
			        {
			            fos.write(buf, 0, read);
			        }	    				  				  	  
			  
			     f = new File(strFileName);

				if (!f.isFile()) 
				{

					throw new Exception("Invalid File Path");
				}

				fis = new FileInputStream(f);

				while (fis.read(fileContent) != -1) 
				{
	                
					response_p.getOutputStream().write(fileContent);
				}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			strmsgText = "HemDeskDATA.getUploadedFile --> "
					+ e.getMessage();
			HisException eObj = new HisException("bmed","HemDeskDATA->getUploadedFile()", strmsgText);
			complaintMaintenanceStatusFB_p.setStrErrMsg("Application Error [ERROR ID : "+ eObj.getErrorID() + "],Contact System Administrator! ");
	
			eObj = null;
		} finally {
	
			if (f != null)
				f = null;
			if (fis != null)
				fis = null;
		}
	}

}
