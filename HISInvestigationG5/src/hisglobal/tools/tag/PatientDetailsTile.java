package hisglobal.tools.tag;


import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.Tag;
import javax.sql.rowset.WebRowSet;




import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.tools.tag.GlobalPatientVO;
import hisglobal.transactionmgnt.HisDAO;
import hisglobal.utility.HelperMethods;
import hisglobal.utility.filetransfer.FTPFileTransfer;
import hisglobal.utility.filetransfer.config.FTPStaticConfigurator;
import hisglobal.utility.filetransfer.config.FileTransferConfig;
import hisglobal.vo.HospitalMstVO;
import hisglobal.vo.UserVO;

public class PatientDetailsTile implements Tag,ServletRequestAware, ServletResponseAware, SessionAware 
{

	static PageContext  pageContext;
	private String crNo = "0";
	private boolean skipOnPatDtlNotFound=false;
	private boolean skipOnPatIsDead=false;
	private String errMsg = "";
	public static String USER_VO = "keyUserVO";
	public static String PATIENT_VO = "keyPatientVO";
	public static String HOSPITAL_VO = "keyHospitalVO";
	public static String PATIENT_ADD_TYPE_CURRENT = "1";
	public static String PATIENT_ISUNKNOWN_TRUE="1";
	private String strProcessId="11";
	private String strCRNoHospCode = "0";
	private String strFileName="11_Image_01";
	private String patIsDead = "0";
	@SuppressWarnings("rawtypes") private Map sessionMap = null;
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	
		
	public String getCrNo() 
	{
		return crNo;
	}
	public void setCrNo(String crNo) 
	{
		this.crNo = crNo;
	}
	
	public boolean isSkipOnPatDtlNotFound() {
		return skipOnPatDtlNotFound;
	}
	public void setSkipOnPatDtlNotFound(boolean skipOnPatDtlNotFound) {
		this.skipOnPatDtlNotFound = skipOnPatDtlNotFound;
	}
	public boolean isSkipOnPatIsDead() {
		return skipOnPatIsDead;
	}
	public void setSkipOnPatIsDead(boolean skipOnPatIsDead) {
		this.skipOnPatIsDead = skipOnPatIsDead;
	}
	public String getErrMsg() 
	{
		return errMsg;
	}
	public void setErrMsg(String errMsg) 
	{
		this.errMsg = errMsg;
	}
	public void setSession(Map sessionMap)
	{
		this.sessionMap = sessionMap;
	}

	@SuppressWarnings("rawtypes")
	public Map getSession()
	{
		return this.sessionMap;
	}
	@Override
	public void setServletRequest(HttpServletRequest request)
	{
		this.request = request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response)
	{
		this.response = response;
	}
	public HttpServletRequest getRequest()
	{
		return this.request;
	}

	public HttpServletResponse getResponse()
	{
		return this.response;
	}
	public int doEndTag() throws JspException 
	{
		System.out.println("PatientDetailsTile :: doEndTag");
		System.out.println("errMsg :"+errMsg+", skipOnPatIsDead :"+skipOnPatIsDead);
		
		if(!errMsg.isEmpty() && skipOnPatDtlNotFound){
			printButtonToolBarHtml();
			return  SKIP_PAGE;
		}else if (skipOnPatIsDead && this.patIsDead.equals("1")){
			printButtonToolBarHtml();
			return  SKIP_PAGE;
		}
		else
			return EVAL_PAGE;
	}
	public int doStartTag() throws JspException 
	{
		GlobalPatientVO voObj = null;
		JspWriter jw = null;
		this.setErrMsg("");
		System.out.println("PatientDetailsTile :: doStartTag");
		try 
		{
			voObj = new GlobalPatientVO();
			jw = pageContext.getOut();
			System.out.println("crNo"+crNo);
			voObj.setPatCrNo(crNo);
			
			System.out.println("crNo"+crNo);
			if(crNo!=null && !crNo.equals("") && !crNo.equals("0"))
			{
				voObj=getPatientDtlByCrno(voObj);
				System.out.println("voObj.getPatIsDead() :"+voObj.getPatIsDead());
				
				if(voObj!=null && "1".equals(voObj.getPatIsDead()))
					this.patIsDead="1";
				
				StringBuffer sb=new StringBuffer();
				String firstName=(voObj.getPatFirstName()!=null&&voObj.getPatFirstName()!="")?voObj.getPatFirstName():" ";
				String middleName=(voObj.getPatMiddleName()!=null&&voObj.getPatMiddleName()!="")?voObj.getPatMiddleName():" ";
				String lastName=(voObj.getPatLastName()!=null&&voObj.getPatLastName()!="")?voObj.getPatLastName():" ";
				String fatherName=(voObj.getPatGuardianName()!=null&&voObj.getPatGuardianName()!="")?voObj.getPatGuardianName():" ";
				String spouseName=(voObj.getPatHusbandName()!=null&&voObj.getPatHusbandName()!="")?voObj.getPatHusbandName():" ";
			
				if(errMsg.equals(""))
				{
					//voObj.setIsNewBorn("1");
					//voObj.setPatIsDead("1");
			
					/*sb.append("<div class='div-table' id='divPatDtlTileHeaderId' style='cursor: pointer;'>");
					sb.append("<div class='div-table-row title'>");
					sb.append("<div class='div-table-col'>);*/
					//sb.append("<div class='col-sm-12' style=' background-color:  #d0d3d4 ; color:black; margin-top:3px; padding-top:6px; font-size: normal; text-align:center; word-wrap: break-word; height:59px;'><i class='fa fa-angle-double-left' aria-hidden='true' style='color:#0d7cc5; font-size:42px;font-weight:700;'></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b style='font-size: 14px;font-weight: 400;'>");*/
					sb.append("<div class='row'>");
					sb.append("<div class='col-sm-12'>");
					sb.append("<div align='center'></i>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<strong style='font-size: 14px;font-weight: 400; letter-spacing: 3px; color: rgba(75,75,75, 0.7); text-transform: uppercase;'>");
					if(voObj.getIsNewBorn().equals("1"))
						sb.append("<i class='fas fa-baby' style='font-size: 26px; color: rgba(33, 32, 32, 0.7);'></i>");
					else if(voObj.getPatGenderCode().equalsIgnoreCase("M"))
					sb.append("<i class='fas fa-user' style='font-size: 26px; color: #6363ff;'></i>");
					else if(voObj.getPatGenderCode().equalsIgnoreCase("F"))
						sb.append("<i class='fa fa-female'aria-hidden='true' style='font-size: 26px; color: #dd8d9b;'></i>");

					else
					sb.append("<img src='/HISRegistration/hisglobal/images/patIcon.png' style=' height: 26px;vertical-align: text-bottom;padding-right: 10px;'> ");
					
					
				
					sb.append("&nbsp;&nbsp;&nbsp;<b style='letter-spacing: 3px;'>"+firstName+" "+middleName+" "+lastName+ " </b>" +"("+voObj.getPatGenderCode()+"&nbsp;/&nbsp;"+voObj.getPatAge()+""+voObj.getPatAgeUnit()+"&nbsp;/"+" ");
					if(voObj.getPatPrimaryCat()!=null){
						if(voObj.getIsCatExpired().equals("1"))
						sb.append("<font color='#FF0000' style='letter-spacing: 3px;' >"+voObj.getPatPrimaryCat()+"&nbsp;(Expired)</font>");
						else							
						sb.append("<font style='letter-spacing: 3px;font-size: 14px;font-weight: 400 !important;'>"+voObj.getPatPrimaryCat()+"</font>");
						}else sb.append("DATA NOT FOUND");
					sb.append("/"+"<font style='letter-spacing: 3px;font-size: 14px;font-weight: 400 !important;'>"+voObj.getPatAddMobileNo()+"</font>"+")"+"</strong>&nbsp;&nbsp;<b style='letter-spacing: 3px;'>CR No : </b><font style='letter-spacing: 3px;font-size: 14px;font-weight: 400 !important;'> &nbsp;"+voObj.getPatCrNo()+" </font>");
					sb.append("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
					//sb.append("<i class='fa fa-angle-double-right' aria-hidden='true' style='color:#0d7cc5;font-size:42px;font-weight:700;'></i></div>");
					
					if((voObj.getIsMLC()!=null && voObj.getIsMLC()!="") && voObj.getIsMLC().equalsIgnoreCase("1"))
						sb.append("<i class='fa fa-flag text-danger' title='"+voObj.getMlcDetail()+"' style='cursor: pointer; cursor: hand; float: left;'>MLC</i> &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;");
					
					//if(voObj.getPatIsDead().equals("1"))
					if((voObj.getPatIsDead()!=null && voObj.getPatIsDead()!="") && voObj.getPatIsDead().equalsIgnoreCase("1"))
					sb.append("<label style='color: red; font-weight: 600;'>Dead</label>");
					
					//if(voObj.getPatStatusCode().equals("2"))
					System.out.println("Check If String is null "+(voObj.getPatStatusCode()!=null));
					//System.out.println("Check If String is  empty"+(!(voObj.getPatStatusCode().isEmpty())));
					System.out.println("Check If String is  blank"+(""!=voObj.getPatStatusCode()));
						if((null!=voObj.getPatStatusCode() && ""!=voObj.getPatStatusCode()) && voObj.getPatStatusCode().equalsIgnoreCase("2"))
						sb.append("<label style='font-weight: 600; color: green;'>(Admitted)</label>");
					
					sb.append("<button id='patSideListBtn' title='Patient More Info' style='padding:4px 12px; color:#4067c5; margin-left: 40px;' class='btn btn-link' type='button'><i class='fa fa-users' style='font-size: 21px;'></i></button>");
					sb.append("</div>");//for col end
					sb.append("</div>");//for row end
					sb.append("</div>");
					
					sb.append("<input type='hidden' name='patAge' id='patAgeId' value='"+voObj.getPatAge()+"'>");
					sb.append("<input type='hidden' name='patAgeUnit' id='patAgeUnitId' value='"+voObj.getPatAgeUnit()+"'>");
					sb.append("<input type='hidden' name='patGenderCode' id='patGenderCodeId' value='"+voObj.getPatGenderCode()+"'>");
					sb.append("<input type='hidden' name='patGender' id='patGenderId' value='"+voObj.getPatGender()+"'>");
					
					/*sb.append("<div class='div-table' id='divPatDtlTileHeaderId' style='cursor: pointer;' title='Click to Hide Patient Detail'>");
					sb.append("<div class='div-table-row title'>");
					sb.append("<div class='div-table-col width13'>Patient Details</div> <div class='div-table-col width55'><div class='div-table' id='divHiddenPatContentId' style='display : none;'><div class='div-table-row'><div class='div-table-col width100'>");
					sb.append("<font color='yellow'>("+firstName+" "+middleName+" "+lastName+" :"+voObj.getPatCrNo()+")</font></div></div></div></div>");
					sb.append("<div class='div-table-col label width29 '>Registration Date&nbsp;");
					sb.append("<b>"+voObj.getRegisterDate()+"</b></div>");
					sb.append("<div class='div-table-col width3 alignRight'>");
					sb.append("<img id='imgPatDtlTileHeaderId' style='vertical-align: middle;' src='/HIS/hisglobal/images/avai/arrow-up.png'></div>");
					sb.append("</div></div>");
					sb.append("<div class='div-table'>");
					sb.append("<div class='div-table-row'>");
					sb.append("<div class='div-table-col width100' id='divPatDtlTileContentId'>");
					sb.append("<div class='div-table'>");
					sb.append("<div class='div-table-row '>");
					sb.append("<div class='div-table-col' style='width: 95%'>");
					sb.append("<div class='div-table'>");
					sb.append("<div class='div-table-row '>");
					sb.append("<div class='div-table-col  label ' style='width: 25%'>Name:</div>");
					sb.append("<div class='div-table-col  control width25 '>");
					sb.append(firstName+" "+middleName+" "+lastName);
					sb.append("</div>");
					sb.append("<div class='div-table-col  label width25 '>CR No.:</div>");
					sb.append("<div class='div-table-col  control width25 '>");
					sb.append(voObj.getPatCrNo());
					sb.append("</div>");
					sb.append("</div>");
					sb.append("<div class='div-table-row '>");
					sb.append("<div class='div-table-col  label width25 '>Age/Gender:</div>");
					sb.append("<div class='div-table-col  control width25 '>");
					sb.append(voObj.getPatAge()+" "+voObj.getPatAgeUnit()+"/"+voObj.getPatGender());
					sb.append("<input type='hidden' name='patAge' id='patAgeId' value='"+voObj.getPatAge()+"'>");
					sb.append("<input type='hidden' name='patAgeUnit' id='patAgeUnitId' value='"+voObj.getPatAgeUnit()+"'>");
					sb.append("<input type='hidden' name='patGenderCode' id='patGenderCodeId' value='"+voObj.getPatGenderCode()+"'>");
					sb.append("<input type='hidden' name='patGender' id='patGenderId' value='"+voObj.getPatGender()+"'>");
					sb.append("</div>");
					sb.append("<div class='div-table-col  label width25 '>Category:</div>");
					sb.append("<div class='div-table-col  control width25 '>");
					if(voObj.getPatPrimaryCat()!=null){
					if(voObj.getIsCatExpired().equals("1"))
					sb.append("<font color='#FF0000'>"+voObj.getPatPrimaryCat()+"&nbsp;(Expired)</font>");
					else							
					sb.append(voObj.getPatPrimaryCat());
					}else sb.append("DATA NOT FOUND");
					sb.append("</div>");
					sb.append("</div>");
		
					sb.append("<div class='div-table-row '>");
					sb.append("<div class='div-table-col  label width25 '>Father Name:</div>");
					sb.append("<div class='div-table-col  control width25 ' style='word-wrap: break-word;'>");
					sb.append(fatherName);
					sb.append("</div>");
					sb.append("<div class='div-table-col  label width25 '>Spouse Name:</div>");
					sb.append("<div class='div-table-col  control width25 ' style='word-wrap: break-word;'>");
					sb.append(spouseName);
					sb.append("</div>");
					sb.append("</div>");
		*/			
					
					/*if(voObj.getIsNewBorn().equals("1"))
					{
						sb.append("<div class='div-table-row '>");
						sb.append("<div class='div-table-col  label width25 '>Mother Name:</div>");
						sb.append("<div class='div-table-col  control width25 '>");
						sb.append(voObj.getPatMotherName());
						sb.append("</div>");
						sb.append("<div class='div-table-col  label width25 '>Mother CR No:</div>");
						sb.append("<div class='div-table-col  control width25 '>");
						sb.append(voObj.getPatMotherCrNo());
						sb.append("</div>");
						sb.append("</div>");
					}*/
					
					/*if(voObj.getMlcDetail()!=null && !voObj.getMlcDetail().equals("") || (voObj.getPatIsDead()!=null && voObj.getPatIsDead().equals("1")))
					{
						sb.append("<div class='div-table-row '>");
						if(voObj.getMlcDetail()!=null && !voObj.getMlcDetail().equals(""))
						{
							sb.append("<div class='div-table-col width25 label'>MLC No</div>");	
							sb.append("<div class='div-table-col width25 control colorRed'>"+voObj.getMlcDetail()+"</div>");
						}
						else
						sb.append("<div class='div-table-col width50 label'>&nbsp;</div>");	
						if(voObj.getPatIsDead().equals("1"))
						sb.append("<div class='div-table-col width25 colorRed label'>Patient is Dead</div>");
						sb.append("</div>");
					}*/

					sb.append("</div>");
					
					sb.append("<div id='patSideListId' class='patSideList' style=''>");
					if(voObj.getPatGenderCode().equals("M"))
					sb.append("<legend class='text-center totalPatHeader'style='color: #fff'><img src='/HIS/hisglobal/images/img_avatar_Male.png' style='height: 50px; width: 50px; border-radius: 24px;'> </img> &nbsp;&nbsp;&nbsp; Patient's Information </legend>");
					else if(voObj.getPatGenderCode().equals("F"))
					sb.append("<legend class='text-center totalPatHeader'style='color: #fff'><img src='/HIS/hisglobal/images/img_avatar_Female.png' style='height: 50px; width: 50px;'> </img> &nbsp;&nbsp;&nbsp; Patient's Information </legend>");
					else
						sb.append("<legend class='text-center totalPatHeader'style='color: #fff'><i class='fas fa-user-circle' style='font-size: 36px;  color: #405562;'> </i> &nbsp;&nbsp;&nbsp; Patient's Information </legend>");
					
					String strHusbandName="";
					String strNewBornString="";
					
					System.out.println("Spouse Name OR"+(voObj.getPatHusbandName()!=null || voObj.getPatHusbandName()!=""));
					System.out.println("Spouse Name AND"+(voObj.getPatHusbandName()!=null && voObj.getPatHusbandName().trim()!=""));
					if(voObj.getPatHusbandName()!=null || voObj.getPatHusbandName()!="")
						strHusbandName=voObj.getPatHusbandName();
					else
						strHusbandName="--";
					
					if(voObj.getIsNewBorn().equals("1"))
						strNewBornString="Yes ( Mother CR No. - )"+voObj.getPatMotherCrNo();
					else
						strNewBornString="No";
					
					sb.append("<ul id='patOtherDtl'> ");
					
					sb.append("<li style=' border: none;'>Registration Date		:<label class='label'>" +voObj.getRegisterDate()+ "</label></li>");
					
					sb.append("<li style=' border: none;'> Father / Spouse Name:<label class='label'>"+voObj.getPatGuardianName()+ "  / "+strHusbandName+ "</label></li>");
					/*sb.append("<li>" +voObj.getPatGuardianName()+ "</li>");*/
					sb.append("<li style=' border: none;'> Address				:<label class='label'>" +voObj.getPatAddDistrict()+ ", "+ voObj.getPatAddState()+ "</label></li>");
					/*sb.append("<li>" +voObj.getPatOccupation()+ "</li>");*/
					/*sb.append("<li>" +voObj.getRegisterDate()+ "</li>");*/
					sb.append("<li style=' border: none;'> Date of Birth		:<label class='label'>" +voObj.getPatDOB()+ "<label></li>");
					
					sb.append("<li style=' border: none;'> Borned in Hospital		:<label class='label'>" +strNewBornString+ "<label></li>");
					/*sb.append("<li>" +voObj.getPatDOB()+ "</li>");*/
					sb.append("</ul>");
					sb.append("</div>");
					
					sb.append("</div>");
					sb.append("<div class='div-table-col' style='width: 5%'>");
					if(voObj.getIsImageUploaded()!=null && voObj.getIsImageUploaded().equals("1"))
					{
						
						
						this.strCRNoHospCode=crNo;
						this.strFileName="11_Image_01";
						this.strProcessId="11";
						/*sb.append("<img class='button' style='cursor:pointer' src='/HIS/hisglobal/images/image-icon.PNG' alt='View Photo' title='View Photo'");
						sb.append("onclick='show(event);'/>");
						*/
						//added by manisha gangwar date: 28.3.2018
						sb.append("<img width='65px' height='50px' style='cursor:pointer' class='patTileImg' src='../../registration/transactions/EnlargedImage.action' alt='' title=''");
						
						
						//displayPatientImage(strCRNoHospCode,strProcessId,strFileName,jw);
						
						//commented by manisha gangwar date: 28.3.2018
						//sb.append("<img width='65px' height='50px' style='cursor:pointer' class='patTileImg' src='/HISRegistration/ImageRendererServlet?crNo="+voObj.getPatCrNo()+"&isImageStoredFTP="+voObj.getIsImageStoredFTP()+"' alt='' title='"+voObj.getPatCrNo()+"'>");
						//end
						
						//sb.append("<img id='patientImage' style='cursor:pointer;display:none;' onmouseout='hideLargeImage();' src='/HISOPDADTBILLING/ImageRendererServlet?crNo="+voObj.getPatCrNo()+"' alt='View Photo' title='"+voObj.getPatCrNo()+"'>");
					}
					
					
					
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					sb.append("</div>");
					
					sb.append("<input type='hidden' name='processID' value='11'/>");
					sb.append("<input type='hidden' name='filname'  value='"+voObj.getImageFileName()+"'/>");
					sb.append("<input type='hidden' name='crNo' value='"+voObj.getPatCrNo()+"'/>");
					
					
						
					sb.append("<input type='hidden' name='patPrimaryCategoryName' value='"+voObj.getPatPrimaryCat()+"'/>");//By Mukund
					sb.append("<script type='text/javascript'>");
					
					sb.append("$('#divPatDtlTileHeaderId').click(function(){");
					sb.append("if($('#divPatDtlTileContentId').is(':visible')){");
					sb.append("	$('#divPatDtlTileContentId').slideUp(500);");
					sb.append("$('#divHiddenPatContentId').fadeIn(500);");
					sb.append("$('#divPatDtlTileHeaderId').prop('title','Click to Show Patient Detail');");
					sb.append("$('#imgPatDtlTileHeaderId').attr('src','/HIS/hisglobal/images/avai/arrow-down.png');");
					sb.append("}else{");
					sb.append("$('#divPatDtlTileContentId').slideDown(500);");
					sb.append("$('#divHiddenPatContentId').fadeOut(500);");
					sb.append("$('#divPatDtlTileHeaderId').prop('title','Click to Hide Patient Detail');");
					sb.append("$('#imgPatDtlTileHeaderId').attr('src','/HIS/hisglobal/images/avai/arrow-up.png');");
					sb.append("}");
					sb.append("});");
					sb.append("function show(e)");
					sb.append("{");
					sb.append("var processID = document.getElementsByName('processID')[0].value;");
					sb.append("var crNo = document.getElementsByName('crNo')[0].value;");
					sb.append("var filname = document.getElementsByName('filname')[0].value;");
					//sb.append("var url = '/HISOPDADTBILLING/ImageRendererServlet?crNo="+voObj.getPatCrNo()+"';"); 
					
					// commented by manisha gangwar date: 28.3.2018
					//sb.append("var url = '/HISRegistration/ImageRendererServlet?crNo="+voObj.getPatCrNo()+"';");
					//end
					
					// added by manisha gangwar date: 28.3.2018
					sb.append("var url = '../../registration/transactions/EnlargedImage.action';");
					//end
					
					
					sb.append("openURLInPopup(url,600,400,0,0);");
					sb.append("}");
					sb.append("function showLargeImage()");
					sb.append("{");
					sb.append("document.getElementById('patientImage').style.display=\"block\";");
					sb.append("}");
					sb.append("function hideLargeImage()");
					sb.append("{");
					sb.append("document.getElementById('patientImage').style.display=\"none\";");
					sb.append("}");
					
					
					sb.append("</script>");
					jw.print(sb.toString());
					System.out.println("Inside Patient Details Tile Tag");
				}
				else
				{
					sb.append("<div class='div-table' id='divPatDtlTileHeaderId'");
					sb.append("<div class='div-table-row title'>");
					sb.append("<div class='div-table-col width100'>Patient Details</div>");
					sb.append("</div></div>");
					
					sb.append("<div class='div-table'>");
					sb.append("<div class='div-table-row'>");
					sb.append("<div class='div-table-col width100 fontred text-center' id='divPatDtlTileContentId'>Patient Details Not Found.</div>");
					sb.append("</div></div>");
					
					jw.print(sb.toString());
				}
			
			}			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			try 
			{
				if(errMsg.equals(""))
					jw.print("Patient Details Not Found!!!/Error"+e.getMessage());
				else
					jw.print("Patient Details Not Found!!!");
			}
			catch(Exception e1) 
			{				
			}
		}
		finally 
		{			
		}
		return EVAL_BODY_INCLUDE;
	}

	public Tag getParent() {
		return null;
	}

	public void release() {
	}

	public void setPageContext(PageContext pageContext) 
	{
		this.pageContext = pageContext;
	}
	public static PageContext getPageContext() 
	{
		return pageContext;
	}
	public void setParent(Tag tag) {
	}

    public GlobalPatientVO getPatientDtlByCrno(GlobalPatientVO voObj)
	{
		
    	HttpSession session = getPageContext().getSession();
    	HttpServletRequest _request = (HttpServletRequest)getPageContext().getRequest();
    	
		try
		{
			String constraint = _request.getParameter("eligible");
			
			UserVO userVO = getUserVO(_request);
			GlobalPatientVO patVO = (GlobalPatientVO) session.getAttribute(PATIENT_VO);
			
			
			if (patVO != null)
			{
				if (!patVO.getOtherHospitalFlag().equals("1") && (patVO.getPatPrimaryCatCode() == null && patVO.getPatPrimaryCat() == null))
				{
					errMsg="Patient Record Not Found!!!";
					throw new Exception("Patient Record Not Found");
				}
			}
			if (patVO == null || !patVO.getPatCrNo().equalsIgnoreCase(voObj.getPatCrNo()))
			{
				GlobalPatientVO patientVO = new GlobalPatientVO();
				
				patientVO.setPatAddTypeCode(PATIENT_ADD_TYPE_CURRENT);
				patientVO.setPatCrNo(voObj.getPatCrNo());
				if (constraint != null && !constraint.equalsIgnoreCase(""))
				{
					//searchPatientByCrNo(patientVO, userVO, constraint);
				}
				else
				{
					patVO=searchPatientByCrNo(patientVO, userVO);
					System.out.println("patVO"+patVO.getPatFirstName());
					session.setAttribute(PATIENT_VO, patVO);
				}
			}
			
				voObj=patVO;			
				
		}
		catch (Exception e)
		{
			e.printStackTrace();
			errMsg="Patient Record Not Found!!!";
			System.out.println("Inside HisException");
		}
		finally
		{
		}
		return voObj;
		
	}
    public static UserVO getUserVO(HttpServletRequest request)
	{
		UserVO userVO=null;
		try
		{
			userVO = (UserVO) request.getSession().getAttribute(USER_VO);			
			HospitalMstVO objHospitalMstVO=(HospitalMstVO)request.getSession().getAttribute(HOSPITAL_VO);
			userVO.setStrHospitalMstVo(objHospitalMstVO);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return userVO;

	}
    /**
	 * Retrieves patient details on the basis of CR No from the Patient Dtl Table. Provides age of the patient according to
	 * the DOB stored in DB. Sets all the values to null in case the patient is Unknown.
	 * 
	 * @param objPatientVO_p Provides CR No to be searched.
	 * @param objUserVO_p Provides User details.
	 * @return PatientVO with values stored in DB.
	 */
	public  GlobalPatientVO searchPatientByCrNo(GlobalPatientVO objPatientVO_p, UserVO objUserVO_p)
	{
		//JDBCTransactionContext tx = new JDBCTransactionContext();
		String strPatientCatErrMsg="Current Patient Category is invalid please change the Patient Category first";
		try
		{
			//tx.begin();
			objPatientVO_p.setPatAddTypeCode(PATIENT_ADD_TYPE_CURRENT);		
			String hospitalCode=objUserVO_p.getHospitalCode();
			if(hospitalCode!=null){
				//To Check the Hospital Code in CRNO on the Basis hosCode Length Added by Singaravelan on 09-Jan-2015
				if(hospitalCode.length()==5){
					if(!hospitalCode.equals(objPatientVO_p.getPatCrNo().substring(0,5)))
							objPatientVO_p.setOtherHospitalFlag("1");		
					else
						objPatientVO_p.setOtherHospitalFlag("0");			
				}
				else{
					if(!hospitalCode.equals(objPatientVO_p.getPatCrNo().substring(0,3)))
						objPatientVO_p.setOtherHospitalFlag("1");				
					else
						objPatientVO_p.setOtherHospitalFlag("0");			
					
				}
			}
			else{
				objPatientVO_p.setOtherHospitalFlag("0");
			}

			objPatientVO_p = retrieveByCrNo(objPatientVO_p, objUserVO_p);
			String fname = "(Unknown)" + objPatientVO_p.getPatFirstName();

			if (objPatientVO_p.getIsUnknown().equals(PATIENT_ISUNKNOWN_TRUE)) objPatientVO_p.setPatFirstName(fname);
			if (objPatientVO_p.getPatAge() != null)
			{
				String patAgeWithUnit = objPatientVO_p.getPatAge();
				int startidx = patAgeWithUnit.lastIndexOf(" ");
				String ageunit = patAgeWithUnit.substring(startidx + 1);
				patAgeWithUnit = patAgeWithUnit.substring(0, startidx);
				objPatientVO_p.setPatAge(patAgeWithUnit);

				if (ageunit.equalsIgnoreCase("yr")) objPatientVO_p.setPatAgeUnit("Yr");
				if (ageunit.equalsIgnoreCase("mth")) objPatientVO_p.setPatAgeUnit("Mth");
				if (ageunit.equalsIgnoreCase("d")) objPatientVO_p.setPatAgeUnit("D");
				if (ageunit.toLowerCase().startsWith("w")) objPatientVO_p.setPatAgeUnit("Wk");
				if (ageunit.equalsIgnoreCase("min")) objPatientVO_p.setPatAgeUnit("D");
			}
			if (!objPatientVO_p.getOtherHospitalFlag().equals("1") && (objPatientVO_p.getPatCatCode() == null || objPatientVO_p.getPatCatCode().equals(""))) 
				throw new Exception(strPatientCatErrMsg);
		
		}
		catch (Exception e)
		{
			if(e.getMessage().equals(strPatientCatErrMsg)){
			}else{
				objPatientVO_p=null;
			}
			e.printStackTrace();	
		}
		finally
		{
			//tx.close();
		}
		return objPatientVO_p;
	}
	/**
	 * Retrieves the details of a patient on the basis of CR No. Calculates the
	 * age of the patient as on today.
	 * @param objPatientVO_p	Provides patient details.
	 * @param _addressVO	Provides address details.
	 * @param objUserVO_p	Provides User details.
	 * @return PatientVO containing patient details for the given CR No.
	 */
	public   GlobalPatientVO retrieveByCrNo(GlobalPatientVO objPatientVO_p,  UserVO objUserVO_p) 
	{
		WebRowSet rs = null;
		HisDAO daoObj = null;
		String strProcName = "{call Pkg_Reg_View.PROC_PATIENT_DETAIL_WITH_CRNO(?,?,?,?,?,?)}";
		int nProcIndex = 0;
		String strErr="",strMode = "0";
		GlobalAddressVO _addressVO= new GlobalAddressVO();
		try 
		{
			daoObj = new HisDAO("HISGlobal","PatientDetailsTile");
			nProcIndex = daoObj.setProcedure(strProcName);
			if(objPatientVO_p.getOtherHospitalFlag()!=null && objPatientVO_p.getOtherHospitalFlag().equals("1"))
				strMode="1";
	
			daoObj.setProcInValue(nProcIndex, "p_mode", strMode,1);
			daoObj.setProcInValue(nProcIndex, "p_hosp_code", objUserVO_p.getHospitalCode(),2);
			daoObj.setProcInValue(nProcIndex, "p_crno", objPatientVO_p.getPatCrNo(),3);
			daoObj.setProcInValue(nProcIndex, "p_isvalid","1",4);
			daoObj.setProcOutValue(nProcIndex, "err", 1,5);
			daoObj.setProcOutValue(nProcIndex, "resultset", 2,6);
			daoObj.executeProcedureByPosition(nProcIndex);
	
			strErr = daoObj.getString(nProcIndex, "err");
			rs = daoObj.getWebRowSet(nProcIndex, "resultset");
			
			System.out.println("patient details tile ws size"+rs.size());
	
			strErr = daoObj.getString(nProcIndex, "err");
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		try 
		{
			if (!rs.next()) 
			{
				errMsg="Patient Details Not Found!!!";
				//throw new Exception("Patient Details Not Found!!!");
			} 
			else 
			{
				rs.beforeFirst();
				HelperMethods.populateVOfrmRS(objPatientVO_p, rs);
				HelperMethods.populate(_addressVO, objPatientVO_p);
				objPatientVO_p.setGlobalPatAddress(_addressVO);
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			if (daoObj != null) 
			{
				daoObj.free();
				daoObj = null;
			}
		}
		return objPatientVO_p;
	}
	public String displayPatientImage(String crNo,String processId,String fileName,JspWriter jw)
	{
		OutputStream os = null;
		BufferedOutputStream bos = null;
		FileInputStream fileInStream=null;
		byte[] fileData=null;
		System.out.println("STEP1");
		HttpSession session = getPageContext().getSession();
		System.out.println("STEP2");
    	//HttpServletRequest _request = (HttpServletRequest)getPageContext().getRequest();
    	System.out.println("STEP3");
    	//HttpServletResponse response = (HttpServletResponse)getPageContext().getResponse();
    	HttpServletResponse response = this.getResponse();
    	System.out.println("STEP4");
		try
		{
			System.out.println("STEP5");
			//os = response.getOutputStream();
			System.out.println("STEP6");
			//bos = new BufferedOutputStream(os);
			
			System.out.println("STEP7"+strProcessId+"---"+strCRNoHospCode+"---"+strFileName);
			if(strProcessId==null || strProcessId.trim().equals("") || strCRNoHospCode==null || strCRNoHospCode.trim().equals("") || strFileName==null || strFileName.trim().equals(""))
			{
				System.out.println("STEP8");
				//throw new HisApplicationExecutionException("Servlet Parameters are not sufficient:: Please Contact Administrator");
				System.out.println("--Session---"+ sessionMap.get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT)+"---------");

				//fileInStream = (FileInputStream)this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
				//fileData=(byte[])this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_CONTENT);
				fileInStream=new FileInputStream((String)sessionMap.get("keyUploadedFilePath"));
				System.out.println("STEP99");
				String uploadedFile  = (String) sessionMap.get(FileTransferConfig.KEY_UPLOADED_FILE_NAME);
				System.out.println("STEP100");
				uploadedFile="11_Image_01";
				response.setContentType(FTPFileTransfer.getFileContentType(uploadedFile));
				System.out.println("STEP101");

				System.out.println("--File In Stream---"+ fileInStream+"---------");
				System.out.println("--File Name---"+ (String) this.getSession().get(FileTransferConfig.KEY_UPLOADED_FILE_NAME)+"---------");

				
				//System.out.println("--File Avilability---"+ fileInStream.available()+"---------");
				//os.write(fileData, 0, fileData.length);
				//System.out.println("--File Read---"+ fileInStream.read()+"---------");

							
				int c;
				while ((c = fileInStream.read()) != -1)
				{
					System.out.println("STEP102");
					//System.out.println("---------"+(char) c);
					jw.write(c);
					//System.out.print((char) c);
				}    
			}
			else
			{
				
				System.out.println("STEP9");
				// Setting File Content Type into Response
				//response.setContentType("image/jpeg");
				System.out.println("STEP10");
				if(!retrieveWriteFile(strProcessId, strFileName, strCRNoHospCode, response,jw))
				{
					System.out.println("STEP16");
					String msg="<B>This file does not exist:: Please Contact Administrator</B>";
					byte[] bytes=msg.getBytes();
					response.setHeader("Pragma", "no-cache");
					bos.write(bytes, 0, bytes.length);
				}
			}
		}
		catch(HisApplicationExecutionException e)
		{
			String msg="<B>"+ e.getMessage()+"</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
			String msg="<B>This file does not exist:: Please Contact Administrator</B>";
			byte[] bytes=msg.getBytes();
			response.setHeader("Pragma", "no-cache");
			try
			{
				bos.write(bytes, 0, bytes.length);
			}
			catch(IOException ee)
			{
			}
		}
		finally
		{
			try
			{
				jw.flush();
				if(bos!=null)	bos.close();
				if(fileInStream!=null) fileInStream.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * Retrieving and Writing File to FTP Server
	 * Test Case : Concurrent Folder Structure Creation, File Save and File Modify
	 * 
	 * @param strProcessId_p	Process ID
	 * @param strFileName_p		File Name
	 * @param inputStream_p		File Content 
	 * @param strCRNoHosp_p		In case of Patient-Wise CRNo otherwise Hospital Code
	 * @throws IOException
	 */
	public static boolean retrieveWriteFile(String strProcessId_p, String strFileName_p, String strCRNoHosp_p, HttpServletResponse response_p,JspWriter jw) throws FileNotFoundException
	{
		InputStream inputStream = null;
		BufferedOutputStream bos = null;
		try
		{
			// Fetching FTP Server Address with Complete URL and User Name: Password
			String ftpServerURL = FTPStaticConfigurator.HIS_FTP_SERVER_ADDRESS_COMPLETE; // <ftpServerUserName>:<ftpServerPassword>@<ftpServerURL>;
			//String ftpServerUserName = FTPStaticConfigurator.HIS_FTP_SERVER_USERNAME;
			//String ftpServerPassword = FTPStaticConfigurator.HIS_FTP_SERVER_PASSWORD;
			System.out.println("STEP11");
			String strFileFtpURL = "ftp://" + ftpServerURL;
			String strFTPFilePath = FTPFileTransfer.getFilePath(strProcessId_p, strCRNoHosp_p);
			System.out.println("STEP12");
			URL urlftp = new URL(strFileFtpURL + strFTPFilePath + "/" + strFileName_p);

			URLConnection urlc = urlftp.openConnection();
			System.out.println("STEP13");
			urlc.connect();
			System.out.println("STEP14");
			//OutputStream os = response_p.getOutputStream();
			//bos = new BufferedOutputStream(os);
			System.out.println("STEP15");
			inputStream = urlc.getInputStream();
			
			int c;
			while ((c = inputStream.read()) != -1)
			{
				jw.write(c);
			}    
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				if(inputStream!=null) inputStream.close();
				jw.flush();
				if(bos!=null)	bos.close();
			}
			catch(Exception e)
			{
			}
		}
		return true;
	}
	
	void printButtonToolBarHtml(){
		StringBuffer sb=new StringBuffer();
		JspWriter jw= null;
		
		try{
			jw= pageContext.getOut();
			
			sb.append("<div class='div-table-button'>");
			sb.append("<div class='div-table-row footerBar'>");
			sb.append("<div class='div-table-col' > </div>");
			sb.append("</div>");
			sb.append("<div class='div-table-row emptyBar'><div class='div-table-col'> </div></div>");
			sb.append("<div class='div-table-row' align='center'>");
			sb.append("<a href='#' class='button' id='cancelId'><span class='cancel'>Cancel</span></a>");
			sb.append("</div>");
			sb.append("</div>");
			
			jw.print(sb.toString());
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
	}
	
}
