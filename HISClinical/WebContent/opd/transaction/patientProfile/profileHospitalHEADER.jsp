
<%--##		Creation Date			: 	20-01-2015
	##		Reason	(CR/PRS)		: 	CR
	##		Created By				:	AKASH SINGH
--%><%@page language="java" contentType="text/html" pageEncoding="UTF-8" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/HisTools.tld" prefix="his"%>
<%@page import="hisglobal.vo.HospitalMstVO"%>
<%@page import="hisglobal.hisconfig.Config"%>
<%@page import="hisglobal.presentation.ControllerUTIL"%>
<his:javascript src="/hisglobal/js/validation.js"/>
<his:javascript src="/hisglobal/transactionutil/js/master.js"/>
<his:javascript src="/hisglobal/js/util.js"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<%
    HospitalMstVO hospitalVO=ControllerUTIL.getHospitalVO(request);
	/* ControllerUTIL.getHospitalVO(request);
	HospitalMstVO hospitalMasterVO=(HospitalMstVO)session.getAttribute(Config.HOSPITAL_VO);
	if(hospitalMasterVO!=null) */
	
	String hospCode = hospitalVO.getHospitalCode();
	String imgPath = "/HIS/hisglobal/images/aiims_header_"+hospCode+".png";
	
	if(hospitalVO!=null)
	{
%>

<!-- By VASU on 15.Nov.2017 -->
 <table width='100%'>
    <tr>
      <td>
       <div align='center'>
        <!--   <img src="/HISClinical/hisglobal/images/logo_10911.jpg" alt="Hospital Logo" height="100px"> -->
          <!-- <img src="/../HIS/hisglobal/images/login/logo2.png" alt="logo" height="100px;" /> -->
          <!-- <img src='/HISClinical/hisglobal/images/aiims_header.png' height='100px'/> -->
          <img src="<%=imgPath%>" height='100px'/>
          
          </div>
      </td>
      <%-- <td width="60%" align="center">
         <br>
             <font style="font-size: 16px; font-weight:bold;">
                <%=hospitalVO.getHospitalName()%>
             </font>
         <br>
             <font style="font-size: 14px;">
                <%=hospitalVO.getAddress1()%>
             </font>
         <br>
             <font style="font-size: 14px;">
                <%=hospitalVO.getAddress2()%>
             </font>
         <br>
            <font style="font-size: 14px;">
                <%=hospitalVO.getCity()+","+hospitalVO.getCity()+"-"+hospitalVO.getPinCode()+","+hospitalVO.getStateName()%>
            </font>
         <br>
             <font style="font-size: 14px;">
                <%="Phone"+":"+hospitalVO.getPhone()+",Fax:"+hospitalVO.getFax()+",Email:"+hospitalVO.getEmail() %>
             </font>
         <br>
       </td> --%>
     </tr>
</table>
 <br>
<!-- End Vasu  -->

<%
	}
%>
</body>
<html:hidden name="GenericPatientProfileFB" property="deskType" />
<html:hidden name="GenericPatientProfileFB" property="profileType" />

