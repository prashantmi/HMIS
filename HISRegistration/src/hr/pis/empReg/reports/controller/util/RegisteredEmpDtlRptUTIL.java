package hr.pis.empReg.reports.controller.util;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisDuplicateRecordException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisInvalidTokenNumberException;
import hisglobal.exceptions.HisUpdateUnsuccesfullException;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.ReportUtil;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.Entry;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;
import hr.pis.config.PisConfig;
import hr.pis.empReg.reports.controller.actionsupport.RegisteredEmpDtlRptSUP;






import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisteredEmpDtlRptUTIL extends ControllerUTIL
{
	private static final String prop_File_Path_global = "hr.pis.common.config.ResourceBundle_Common";
	private static final String prop_File_Path_de_global = "hr.pis.common.config.ResourceBundle_Common_de";
		
		
		public static void createReport(RegisteredEmpDtlRptSUP form,HttpServletRequest objRequest,HttpServletResponse objResponse) throws Exception
		{
			
			String isCancelButtonReq="1";
			UserVO userVO=getUserVO(objRequest);
			String strHospitalCode =userVO.getHospitalCode();
			
			if(form.getIntRptMode().equalsIgnoreCase("1"))
			{
				
				form.setStrEmpNo(userVO.getUserEmpID());
				System.out.println("userVO.getUserEmpID():"+userVO.getUserEmpID());
			}
			
			System.out.print("Cancel button :::::::::::::"+objRequest.getParameter("cancelButton"));
			if(objRequest.getParameter("cancelButton")!= null) // cancelButton is used hyperLink of emp no in list of registered employee report
			{
				isCancelButtonReq="0";
				
				if(objRequest.getParameter("header").equals("true"))
					form.setStrIsHeaderReq("on");
				
				if(objRequest.getParameter("logo").equals("true"))
					form.setStrIsLogoReq("on");
				
				if(objRequest.getParameter("footer").equals("true"))
					form.setStrIsFooterReq("on");
				
				if(objRequest.getParameter("waterMark").equals("true"))
					form.setStrIsWatermarkReq("on");
			}
			
			try{
				
				Boolean Htype;
				
				if(form.getStrIsHeaderReq().equals("on"))
				{
					Htype=true;
				}
				else
				{
					Htype=false;
				}
				
				Boolean Ftype;
				
				if(form.getStrIsFooterReq().equals("on"))
				{
					Ftype=true;
				}
				else
				{
					Ftype=false;
				}
				
				Boolean Ltype;
				
				if(form.getStrIsLogoReq().equals("on"))
				{
					Ltype=true;
				}
				else
				{
					Ltype=false;
				}
				
				Boolean Wtype;
				
				if(form.getStrIsWatermarkReq().equals("on"))
				{
					Wtype=true;
				}
				else
				{
					Wtype=false;
				}
				
				String ReportType;
				if(form.getStrReportType().equals("2"))
				{
					 ReportType="PDF";
				}
				else if(form.getStrReportType().equals("3"))
				{
					 ReportType="xls";
				}
				else
				{
					 ReportType="HTML";
				}
				
				//e-english ,r-retional ,b-both
				
			String elabel1,elabel2,elabel3,elabel4,elabel5,elabel6,elabel7,elabel8,elabel9,elabel10,elabel11,elabel12,elabel13,elabel14;
			String rlabel1,rlabel2,rlabel3,rlabel4,rlabel5,rlabel6,rlabel7,rlabel8,rlabel9,rlabel10,rlabel11,rlabel12,rlabel13,rlabel14;
			String blabel1,blabel2,blabel3,blabel4,blabel5,blabel6,blabel7,blabel8,blabel9,blabel10,blabel11,blabel12,blabel13,blabel14;
				
				
			
			elabel1=HelperMethods.getQuery(1, "global.footer.heading" ,prop_File_Path_global );
			elabel2=HelperMethods.getQuery(1, "global.date" ,prop_File_Path_global  )+"/"+HelperMethods.getQuery(1, "global.time" ,prop_File_Path_global  );
			elabel3=HelperMethods.getQuery(1, "global.pageno" ,prop_File_Path_global  );
			
		
			rlabel1=HelperMethods.getQuery(1, "global.footer.heading" ,prop_File_Path_de_global );
			rlabel2=HelperMethods.getQuery(1, "global.date" ,prop_File_Path_de_global  )+"/"+HelperMethods.getQuery(1, "global.time" ,prop_File_Path_de_global  );
			rlabel3=HelperMethods.getQuery(1, "global.pageno" ,prop_File_Path_de_global  );

			
			
			 blabel1=elabel1+" \n "+rlabel1;
			 blabel2=elabel2+" \n "+rlabel2;
			 blabel3=elabel3+" \n "+rlabel3;
			
			
			
			
			Map<String, Object> params = new HashMap<String, Object>();
			String reportPath ="/hr/pis/empReg/reports/rptDesigns/registeredEmpDtlRpt.rptdesign";
			
			params.put("empno", form.getStrEmpNo());
			params.put("isCancelButtonReq",isCancelButtonReq);
			params.put("isHeaderReq", Htype);
			params.put("isFooterReq", Ftype);
			params.put("isLogoReq",Ltype);
			params.put("isWatermarkReq",Wtype);
			
			if((form.getStrReportType().equals("1")||form.getStrReportType().equals("3"))&& form.getStrLabelLang().equals("3"))
			{
				params.put("lFHeading", blabel1);
				params.put("lRptDateAndTime", blabel2);
				params.put("lRptPageNo", blabel3);
			}
			else if((form.getStrReportType().equals("1")||form.getStrReportType().equals("3"))&& form.getStrLabelLang().equals("2"))
			{
				params.put("lFHeading", rlabel1);
				params.put("lRptDateAndTime", rlabel2);
				params.put("lRptPageNo", rlabel3);
				
			}
			else
			{
				params.put("lFHeading", elabel1);
				params.put("lRptDateAndTime", elabel2);
				params.put("lRptPageNo", elabel3);
			}
			
			
			System.out.println("form.getStrEmpNo()" +form.getStrEmpNo());
			System.out.println("form.getStrIsHeaderReq()" +Htype);
			System.out.println("form.getStrIsFooterReq()" +Ftype);
			System.out.println("Report Type" +ReportType);
			System.out.println("logo" +Ltype);
			System.out.println("Watermark" +Wtype);
			
			ReportUtil ts = new ReportUtil();
			ts.displayReport(objRequest, objResponse, reportPath, ReportType, params,strHospitalCode);
			}
			catch(Exception e)
			{
				System.out.println("Exception Occured in createReport method : ");
				 e.printStackTrace();
			}
		}
		
		
		
		public static void populateLoginEmpDetails(RegisteredEmpDtlRptSUP objSUP,HttpServletRequest objRequest, HttpServletResponse objResponse){		
			Status objStatus=new Status();		
			try
			{		
				UserVO userVO=getUserVO(objRequest);
				
				objSUP.setStrEmpNo(userVO.getUserEmpID());
				System.out.println("userVO:"+userVO);
				System.out.println("userVO.getUserEmpID():"+userVO.getUserEmpID());
				
			}		
			catch(Exception e){
				objStatus.add(Status.ERROR, "","Error");
				e.printStackTrace();
			}		
			
		}
	

}
