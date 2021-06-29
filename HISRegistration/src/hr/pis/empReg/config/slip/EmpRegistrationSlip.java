package hr.pis.empReg.config.slip;


import hisglobal.hisconfig.Config;
import hisglobal.vo.HospitalMstVO;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import vo.hr.pis.empReg.transactions.EmpRegistrationVO;

public class EmpRegistrationSlip {
	
	public static String print(EmpRegistrationVO employeeRegisterVOForPrintData, HospitalMstVO objHospitalMstVO, HttpServletRequest _request)
	{
		
  	  	StringBuilder str=new StringBuilder();
  		
  		
  		String outsideCircleOpenForPrint = "<br><div class='wrapper rounded width99' id='divEmpDtlPrintId'><div class='div-table'><div class='div-table-row emptyBar'><div class='div-table-col'></div></div>";
  		String outsideCircleOpen = "<br><div class='wrapper rounded width95' id='divEmpDtlPrintId'><div id='reportForm' class='div-table'><div class='div-table-row emptyBar'><div class='div-table-col'></div></div>";
  		String outsideCircleClose = "<div class='div-table-row emptyBar'><div class='div-table-col'></div></div></div></div><br>";
  		
  		String hospitalName=objHospitalMstVO.getHospitalName();
  		String hospitalAdd=objHospitalMstVO.getAddress1();
  		String hospitalAddExt=objHospitalMstVO.getAddress2();
  		String hospitalCity=objHospitalMstVO.getCity();
  		String hospitalDistrict=objHospitalMstVO.getDistrictName();
  		String hospitalState=objHospitalMstVO.getStateName();
  		
  		String  empHeaderRow =   "";
  		String  empFooterRow =   "";
  		String  empDtlRow = "";
  		
  		Date dateObj = null;
		if((_request.getSession().getAttribute(Config.SYSDATEOBJECT))!=null)
			dateObj = (Date)_request.getSession().getAttribute(Config.SYSDATEOBJECT);
		else
			dateObj=null;
		
		SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
		sf.applyPattern("dd-MMM-yyyy"); String date = sf.format(dateObj);
		sf.applyPattern("HH:mm:ss"); String time = sf.format(dateObj);
		
  		empHeaderRow +=   	"<div class='div-table-row' id='rptPrintImageDivId' >" +
  								"<div class='div-table-col label' style='width: 100%; position:absolute; right:12px; top:8px;'>" +
  									"<img src='/HIS/hisglobal/images/nonclinical/nc_printer.png' width='28' height='28' onclick='printEmployeeRegistrationSlip();' style='vertical-align: middle;' title='Print' />" +
  									"&nbsp;&nbsp;&nbsp;&nbsp;"+
  									"<img src='/HIS/hisglobal/images/nonclinical/nc_cancel.png' width='20' height='20' onclick='initialPage();' style='vertical-align: middle;' title='Cancel' />" +
  									"</div>"+
  							"</div>"+
  							"<div id='divHospitalName' class='div-table-row' align='center'>"+hospitalName+"</div>"+
  							"<div id='divHospitalAdd1' class='div-table-row' align='center'>"+hospitalAdd+"</div>"+
  							"<div id='divHospitalAdd2' class='div-table-row' align='center'>" +	(hospitalAddExt==null?"":hospitalAddExt)+"</div>"+
  							"<div id='divHospitalCity' class='div-table-row' align='center'>"+hospitalDistrict+", "+hospitalState +"</div>"+
  							/*"<div class='div-table-row'>" +
  								"<div class='div-table-col control' style='width: 100%;'><center>" +
  								"<font face='Verdana, Arial, Helvetica, sans-serif' size='2'>"+hospitalCity+"</font>" +
  								"</center></div>"+
  							"</div>"+
  							"<div class='div-table-row'>" +
  								"<div class='div-table-col control' style='width: 100%;'><center>" +
  								"<font face='Verdana, Arial, Helvetica, sans-serif' size='2'>"+hospitalState+"</font>" +
  								"</center></div>"+
  							"</div>"+*/
  							"<div class='div-table-row'>" +
  								"<div class='div-table-col label' style='width: 100%;'>" +
  								"Date/Time : "+date+" / "+time+
  								"&nbsp;&nbsp;</div>"+
  							"</div>"+
  							"<div class='div-table-row'>" +
  								"<div class='div-table-col label' style='width: 100%;'><center>" +
  								"<u><font size='2'>EMPLOYEE REGISTRATION SLIP</font></u>"+
  								"</center></div>"+
  							"</div>";

  		empFooterRow += " <div class='div-table-row'><div class='div-table-col label' style='width: 100%;'><center>**End of Report**</center></div> " +
  						" <div class='div-table-col label' style='width: 100%;'><center>Computer Generated Report</center></div> "+ 
  						"</div>";
  		
  		
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 100%'>&nbsp;</div>"+
  						"</div>"+
  						"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Emp. No.</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrEmployeeNumber()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Is Existing Emp. ?</div>"+
  							"<div class='div-table-col label alignCenter' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrIsExistingEmployee()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Last Employment Type</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrLastEmploymentType()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Nature Of Job</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntNatureOfJobId()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Appellation 1</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntAppellationCode1()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Appellation 2</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntAppellationCode2()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Emp. Full Name</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrEmployeeFullName()+" / "+employeeRegisterVOForPrintData.getStrEmployeeFullRegionalLangName()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Emp. Short Name</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrEmployeeShortName()+" / "+employeeRegisterVOForPrintData.getStrEmployeeShortRegionalLangName()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
		  					"<div class='div-table-col label' style='width: 24%'>Date of Birth</div>"+
							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getDtEmployeeDOB()+"</div>" +
  							/*"<div class='div-table-col label' style='width: 24%'>Suffix</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntSuffixCode()+"</div>"+*/
  							"<div class='div-table-col label' style='width: 24%'></div>"+
  							"<div class='div-table-col label' style='width: 2%'><center></center></div>"+
  							"<div class='div-table-col control' style='width: 24%'></div>"+
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Gender</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrGenderCode()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Nationality</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntNationalityId()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Department</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntDepartmentCode()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Designation</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntDesignationCode()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Old Employment Reference No.</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrOldEmployeeNumber()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Employee Final Status</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrEmployeeFinalStatus()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>Mobile No.</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getIntMobileNumber()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'>Email Id</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrPersonalEmailId()+"</div>" +
  						"</div>";
  					
  		empDtlRow +=	"<div class='div-table-row'>" +
  							"<div class='div-table-col label' style='width: 24%'>PAN Card No.</div>"+
  							"<div class='div-table-col label' style='width: 2%'><center>:</center></div>"+
  							"<div class='div-table-col control' style='width: 24%'>"+employeeRegisterVOForPrintData.getStrPANNumber()+"</div>"+
  							"<div class='div-table-col label' style='width: 24%'></div>"+
  							"<div class='div-table-col label' style='width: 2%'><center></center></div>"+
  							"<div class='div-table-col control' style='width: 24%'></div>" +
  						"</div>";

  		
  		str.append(outsideCircleOpen+empHeaderRow+empDtlRow+empFooterRow+outsideCircleClose);
  		
  		return str.toString();
  		
	}

}//end class
