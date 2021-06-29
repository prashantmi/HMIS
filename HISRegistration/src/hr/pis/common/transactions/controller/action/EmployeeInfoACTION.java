/* Module Name: Common List Page
*  Name of Process: List Generation 
*  Name of Developer: Sh. Ashwini Mishra
*  Date of Creation: 26-Mar-2015
*/

package hr.pis.common.transactions.controller.action;


import hr.pis.common.transactions.controller.actionsupport.EmployeeInfoSUP;
import hr.pis.common.transactions.controller.util.EmployeeInfoUTIL;
import hr.pis.config.PisConfig;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletContext;

public class EmployeeInfoACTION extends EmployeeInfoSUP {

	private String message;
		 
	public String execute() throws Exception
	{
		System.out.println("EmployeeTransferReqDtlACTION :: execute()");
		message = "Inside execute method";
		//boolean ff=objRequest.getSession().isNew();
		return init();
	}
	 
	
	public String init()
	{		   
		 System.out.println("EmployeeTransferDtlACTION :: init()");
		 message = "Inside init method";		
		 return "NEW";
			 
		
	}
		 
	 public String regEmpList()
	 {
		System.out.println("EmployeeInfoACTION :: regEmpList()");
		setStrPageFlag("list");
		setIntListType("1");
		if(objRequest.getParameter("calSeq")!=null) setIntCallingSeq(objRequest.getParameter("calSeq"));
		else setIntCallingSeq("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		return "NEW";
		
	 }
	 
	 
	 public String regEmpListWithRm()
	 {
		System.out.println("EmployeeInfoACTION :: regEmpListWithRm()");
		setStrPageFlag("list");
		setIntListType("2");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);		
		if(objRequest.getParameter("calSeq")!=null) setIntCallingSeq(objRequest.getParameter("calSeq"));
		else setIntCallingSeq("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		return "NEW";
		
	 }
	 
	 public String validEmpList()
	 {
		System.out.println("EmployeeInfoACTION :: validEmpList()");
		setStrPageFlag("list");
		setIntListType("3");
		if(objRequest.getParameter("calSeq")!=null) setIntCallingSeq(objRequest.getParameter("calSeq"));
		else setIntCallingSeq("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		return "NEW";		
	 }
	 
	 public String validEmpListWithRm()
	 {
		System.out.println("EmployeeInfoACTION :: validEmpListWithRm()");
		setStrPageFlag("list");
		setIntListType("4");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);
		if(objRequest.getParameter("calSeq")!=null) setIntCallingSeq(objRequest.getParameter("calSeq"));
		else setIntCallingSeq("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		/*
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("");
		if(objRequest.getParameter("deptCode")!=null) setIntDeptCode(objRequest.getParameter("deptCode"));
		else setIntDeptCode("");
		*/
		System.out.println("EmployeeInfoACTION :: validEmpListWithRm()"+objRequest.getParameter("condition"));
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		System.out.println("EmployeeInfoACTION :: validEmpListWithRm()"+getStrCondition());
		
		return "NEW";		
	 }
	 
	
	 public void empList()
	 {
	 	 System.out.println("EmployeeInfoACTION :: empList()");
	 	 message = "Inside list() method";
	 	 String pageFlag= (String)objRequest.getParameter("pageFlag");
	 	 String listType= (String)objRequest.getParameter("listType");
	 	 String rmTypeId= (String)objRequest.getParameter("rmTypeId");
	 	 String calSeq= (String)objRequest.getParameter("calSeq");
	 	 String salTypeId= (String)objRequest.getParameter("salTypeId");
	 	 String nojId= (String)objRequest.getParameter("nojId");
	 	 String cadreId= (String)objRequest.getParameter("cadreId");
	 	 String empOffId= (String)objRequest.getParameter("empOffId");
	 	 String estbId= (String)objRequest.getParameter("estbId");
	 	 String deptId= (String)objRequest.getParameter("deptId");
	 	 String serGrpId= (String)objRequest.getParameter("serGrpId");
	 	 String desigId= (String)objRequest.getParameter("desigId");
	 	 String empStatusId= (String)objRequest.getParameter("empStatusId");
	 	 String empFinalStatusId= (String)objRequest.getParameter("empFinalStatusId");
	 	 String plOfPostingId= (String)objRequest.getParameter("plOfPostingId");
	 	 String hospId= (String)objRequest.getParameter("hospId");		 	 
	 	 String condition= (String)objRequest.getParameter("condition");
	 	 String additionalData= (String)objRequest.getParameter("additionalData");
	 	 
	 	 
	 	 setStrPageFlag(pageFlag);
		 setIntListType(listType);
		 setIntRoleMgtTypeId(rmTypeId);
		 setIntCallingSeq(calSeq);
		 setIntSalaryTypeId(salTypeId);
		 setIntNojId(nojId);
		 setIntCadreId(cadreId);
		 setIntEmpOffId(empOffId);
		 setIntEstbId(estbId);
		 setIntDeptCode(deptId);
		 setIntSerGrpId(serGrpId);
		 setIntDesigCode(desigId);
		 setIntStatusId(empStatusId);
		 setIntFinalStatusId(empFinalStatusId);
		 setIntPlOfPostingId(plOfPostingId);
		 setIntHospId(hospId);
		 setStrCondition(condition);
		 setStrAdditionalData(additionalData);
		 EmployeeInfoUTIL.getEmpList_AJAX(this, objRequest, objResponse, mapSesion);
	 }
	 
	
	 public void goRegEmpData()
	 {
		System.out.println("EmployeeInfoACTION :: goRegEmpData()");
		setIntListType("1");
		if(objRequest.getParameter("empNo")!=null) setStrEmpNo(objRequest.getParameter("empNo"));
		else setStrEmpNo("0");
		if(objRequest.getParameter("calMode")!=null) setIntCalMode(objRequest.getParameter("calMode"));
		else setIntCalMode("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.getEmpData(this, objRequest,objResponse);
		setCharacterEncoding();		
	 }
	 
	 
	 public void goRegEmpDataWithRm()
	 {
		System.out.println("EmployeeInfoACTION :: goRegEmpDataWithRm()");
		setIntListType("2");
		if(objRequest.getParameter("empNo")!=null) setStrEmpNo(objRequest.getParameter("empNo"));
		else setStrEmpNo("0");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);
		if(objRequest.getParameter("calMode")!=null) setIntCalMode(objRequest.getParameter("calMode"));
		else setIntCalMode("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.getEmpData(this, objRequest,objResponse);
		setCharacterEncoding();
		
	 }
	 
	 public void goValidEmpData()
	 {
		System.out.println("EmployeeInfoACTION :: goValidEmpData()");
		setIntListType("3");
		if(objRequest.getParameter("empNo")!=null) setStrEmpNo(objRequest.getParameter("empNo"));
		else setStrEmpNo("0");
		if(objRequest.getParameter("calMode")!=null) setIntCalMode(objRequest.getParameter("calMode"));
		else setIntCalMode("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.getEmpData(this, objRequest,objResponse);
		setCharacterEncoding();	
	 }
	 
	 public void goValidEmpDataWithRm()
	 {
		System.out.println("EmployeeInfoACTION :: goValidEmpDataWithRm()");
		setIntListType("4");
		if(objRequest.getParameter("empNo")!=null) setStrEmpNo(objRequest.getParameter("empNo"));
		else setStrEmpNo("0");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);
		if(objRequest.getParameter("calMode")!=null) setIntCalMode(objRequest.getParameter("calMode"));
		else setIntCalMode("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.getEmpData(this, objRequest,objResponse);
		setCharacterEncoding();		
	 }
	 
	
	public void findRegEmp() throws IOException
	{			
		System.out.println("EmployeeInfoACTION :: findRegEmp()");
		message = "Inside findRegEmp method";
		setIntListType("1");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.findEmp(this, objRequest,objResponse);
		setCharacterEncoding();
	}
		
	

	public void findRegEmpWithRm() throws IOException
	{			
		System.out.println("EmployeeInfoACTION :: findRegEmpWithRm()");
		message = "Inside findRegEmp method";
		setIntListType("2");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.findEmp(this, objRequest,objResponse);
		setCharacterEncoding();
	}
	

	public void findValidEmp() throws IOException
	{			
		System.out.println("EmployeeInfoACTION :: findValidEmp()");
		message = "Inside findRegEmp method";
		setIntListType("3");
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.findEmp(this, objRequest,objResponse);
		setCharacterEncoding();
	}
	

	public void findValidEmpWithRm() throws IOException
	{			
		System.out.println("EmployeeInfoACTION :: findValidEmpWithRm()");
		message = "Inside findRegEmp method";
		setIntListType("4");
		if(objRequest.getParameter("rmTypeId")!=null) setIntRoleMgtTypeId(objRequest.getParameter("rmTypeId"));
		else setIntRoleMgtTypeId(PisConfig.GBLT_ROLE_MGT_TYPE_MST_GENERAL);
		if(objRequest.getParameter("salTypeId")!=null) setIntSalaryTypeId(objRequest.getParameter("salTypeId"));
		else setIntSalaryTypeId("%");
		if(objRequest.getParameter("nojId")!=null) setIntNojId(objRequest.getParameter("nojId"));
		else setIntNojId("%");
		if(objRequest.getParameter("cadreId")!=null) setIntCadreId(objRequest.getParameter("cadreId"));
		else setIntCadreId("%");
		if(objRequest.getParameter("empOffId")!=null) setIntEmpOffId(objRequest.getParameter("empOffId"));
		else setIntEmpOffId("%");
		if(objRequest.getParameter("estbId")!=null) setIntEstbId(objRequest.getParameter("estbId"));
		else setIntEstbId("%");
		if(objRequest.getParameter("deptId")!=null) setIntDeptCode(objRequest.getParameter("deptId"));
		else setIntDeptCode("%");
		if(objRequest.getParameter("serGrpId")!=null) setIntSerGrpId(objRequest.getParameter("serGrpId"));
		else setIntSerGrpId("%");
		if(objRequest.getParameter("desigId")!=null) setIntDesigCode(objRequest.getParameter("desigId"));
		else setIntDesigCode("%");
		if(objRequest.getParameter("empStatusId")!=null) setIntStatusId(objRequest.getParameter("empStatusId"));
		else setIntStatusId("%");
		if(objRequest.getParameter("empFinalStatusId")!=null) setIntFinalStatusId(objRequest.getParameter("empFinalStatusId"));
		else setIntFinalStatusId("%");
		if(objRequest.getParameter("plOfPostingId")!=null) setIntPlOfPostingId(objRequest.getParameter("plOfPostingId"));
		else setIntPlOfPostingId("%");
		if(objRequest.getParameter("hospId")!=null) setIntHospId(objRequest.getParameter("hospId"));
		else setIntHospId("%");
		if(objRequest.getParameter("condition")!=null) setStrCondition(objRequest.getParameter("condition"));
		else setStrCondition("");
		if(objRequest.getParameter("additionalData")!=null) setStrAdditionalData(objRequest.getParameter("additionalData"));
		else setStrAdditionalData("");
		EmployeeInfoUTIL.findEmp(this, objRequest,objResponse);		
		setCharacterEncoding();
	}
	
	 
	public void setServletContext(ServletContext context) {
		this.objContext=context;
	}
	
	

public void setCharacterEncoding() 
{
	System.out.println("Inside EmployeeTransferDtlACTION::setCharacterEncoding function");
	try
	{
		objRequest.setCharacterEncoding("UTF-8");
	}
	catch(UnsupportedEncodingException e)
	{
		System.out.println("Error in Reset -> Character Encoding Try Block = ");
		e.printStackTrace();
	}
		
}



//business logic
public String changeLocale() {

	return "CHANGELOCALE";

}

}
