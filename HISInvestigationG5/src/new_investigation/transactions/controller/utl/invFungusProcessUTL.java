package new_investigation.transactions.controller.utl;

import new_investigation.transactions.controller.fb.InvestigationRaisingDtlFB;
import new_investigation.transactions.controller.fb.invFungusProcessFB;
import hisglobal.presentation.ControllerUTIL;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.controller.data.InvResultEntryDATA;
import new_investigation.transactions.controller.data.InvValueAuditDATA;
import new_investigation.transactions.controller.data.invFungusProcessDATA;
import new_investigation.transactions.controller.fb.InvValueAuditFB;
import new_investigation.vo.InvValueAuditVO;
import new_investigation.vo.antibioticprocessVO;
import new_investigation.vo.invFungusProcessVO;
import new_investigation.vo.template.ResultEntryVO;
import new_investigation.vo.template.ResultEntryVOGroupByValidatedBy;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.exceptions.HisException;
import hisglobal.exceptions.HisRecordNotFoundException;
import hisglobal.hisconfig.Config;
import hisglobal.presentation.ControllerUTIL;
import hisglobal.presentation.Status;
import hisglobal.presentation.WebUTIL;
import hisglobal.utility.HelperMethods;
import hisglobal.vo.UserVO;
import hisglobal.exceptions.HisApplicationExecutionException;
import hisglobal.exceptions.HisDataAccessException;
import hisglobal.persistence.HelperMethodsDAO;
import hisglobal.persistence.JDBCTransactionContext;
import hisglobal.utility.Sequence;
import hisglobal.vo.TestTemplateVO;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.lang.reflect.Field;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import new_investigation.InvestigationConfig;
import new_investigation.transactions.dao.Helper.InvestigationTemplateDataHelper;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;














import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class invFungusProcessUTL extends ControllerUTIL {

	static String path=(InvestigationConfig.deploymentMode==0)?InvestigationConfig.windowsPath:InvestigationConfig.linuxPath;
	
	
	private static InvestigationTemplateDataHelper instance;
	private static int checkedOut = 0;
	private static DocumentBuilderFactory builderFactory;
	private static DocumentBuilder builder;
	XPathFactory factory = null;
	public static String databaseusername = null;
	public static String databasepassword = null;
	private static TransformerFactory transformerFactory;
	
	
	public static void getEssential(invFungusProcessFB fb,HttpServletRequest request)
	{
		
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
        
		invFungusProcessVO vo=new invFungusProcessVO();
		
		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 
          
			HelperMethods.populate(vo,fb);

			ControllerUTIL.setSysdate(request);
			Date dt= (Date)request.getSession().getAttribute(Config.SYSDATEOBJECT); 
			WebUTIL.getSession(request).setAttribute(InvestigationConfig.SYSDATEOBJECT,dt);

			mp=invFungusProcessDATA.LabComboForAudit(fb, userVO);
			WebUTIL.setMapInSession(mp, request);
		
			  
			
				objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			

	}  
	
	
	public static void saveSearchData(invFungusProcessFB fb,HttpServletRequest request) throws SAXException, IOException, ParserConfigurationException    
	{
		HttpSession session = request.getSession();
		Status objStatus= new Status();
    
		UserVO userVO = ControllerUTIL.getUserVO(request);
      
		invFungusProcessVO vo=new invFungusProcessVO();
		Document tempDoc = null;
		java.io.InputStream ip = null;
		boolean found=false;
		try{	
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			//factory = XPathFactory.newInstance();
			transformerFactory = TransformerFactory.newInstance();
			
			String[] chkvalue=fb.getChkSamplePatient();
			StringBuffer sb=new StringBuffer();
			StringBuffer sbb=new StringBuffer();
			List<String> sb1=new ArrayList<String>();
			List<String> sb2=new ArrayList<String>();
			List<String> DiagnosisList=new ArrayList<String>();
			for(int i=0;i<chkvalue.length;i++)
			{
			sb=new StringBuffer();
			sbb=new StringBuffer();
			String[] selectval=fb.getChkInfo();
			    sb.append(fb.getOrganismCode());
			    sb.append("#");
			    sb.append(chkvalue[i].split("#")[0]);
		     	sb.append("#");
		     	sb.append(selectval[i]);
		     	sb.append("#");
		     		
		     	
		     	sbb.append(fb.getOrganismName());
			    sbb.append("#");
			    sbb.append(chkvalue[i].split("#")[1]);
		     	sbb.append("#");
		     	sbb.append(selectval[i]);
		     	sbb.append("#");
		     	
		     	
				//sb.setLength(sb.length()-1);
				System.out.println(sb.toString());    
                sb1.add(sb.toString());
                sb2.add(sbb.toString());
			}
			System.out.println(sbb);
			String value1="";
			String value2="";
			List<invFungusProcessVO> hyprlistdata=new ArrayList<invFungusProcessVO>();
			
			for(int i=0;i<sb1.size();i++)
			{
				
				String[] val=sb1.get(i).split("#");
				
				 value1+=val[0]+"$$"+val[1]+"$$"+val[2]+"$$"+fb.getRequisitionDNo()+"$$"+fb.getTestParaCode()+"$$$";
				
				 String[] val1=sb2.get(i).split("#");
					
				System.out.println(val1);
				 
				 invFungusProcessVO voResultEntryForParaMeterDtl=new invFungusProcessVO();
				 
				 voResultEntryForParaMeterDtl.setOrganismName(val1[0]);
				 voResultEntryForParaMeterDtl.setAntibioticName(val1[1]);
				 voResultEntryForParaMeterDtl.setValue(val1[2]);	
				 hyprlistdata.add(voResultEntryForParaMeterDtl);
			}
			
			
			

	 		Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
	 		Map<String,List<invFungusProcessVO>> mpBilled12=new LinkedHashMap<String, List<invFungusProcessVO>>();      
			Map mpp=new HashMap(); 

	 		
					                    if(mpBilled!=null)
					                    {
					            			List<invFungusProcessVO> hyprlistdata1=new ArrayList<invFungusProcessVO>();

					          Iterator itr=mpBilled.keySet().iterator();
					          Iterator itr1=mpBilled.keySet().iterator();
					          while(itr1.hasNext())
						 		{
					        	  hyprlistdata=new ArrayList<invFungusProcessVO>();
						 			String organisgm1=(String)itr1.next();
					            
						 			 
						 			List<antibioticprocessVO> lstVOSample=mpBilled.get(organisgm1);
						 			int rowSpanSize=lstVOSample.size();
						 			String dno=fb.getRequisitionDNo();
						 			String dno1=dno;
						 			String reqno=dno.substring(0,dno.length() - 2);
						 			if(organisgm1.contains(dno1))
						 			for(int k=0;k<lstVOSample.size();k++)
						 			{
						 				antibioticprocessVO voo=lstVOSample.get(k);
						 			 invFungusProcessVO voResultEntryForParaMeterDtl=new invFungusProcessVO();
									 
									 voResultEntryForParaMeterDtl.setOrganismName(voo.getOrganismname());
									 voResultEntryForParaMeterDtl.setAntibioticName(voo.getAntibioticname());
									 voResultEntryForParaMeterDtl.setAntibioticClassName(voo.getAntibioticcode());
                                     voResultEntryForParaMeterDtl.setValue(voo.getResult());
									 voResultEntryForParaMeterDtl.setGrowth(voo.getGrowth());
									 voResultEntryForParaMeterDtl.setGrowth(voo.getGrowth());
									 voResultEntryForParaMeterDtl.setGrowthname(voo.getGrowthname());
									 voResultEntryForParaMeterDtl.setRemark(voo.getRemark());

									 hyprlistdata.add(voResultEntryForParaMeterDtl);
						 			}
						 			
						 			if(hyprlistdata!=null && hyprlistdata.size()>0)
						 			mpBilled12.put(organisgm1, hyprlistdata);
						 		}
					                    }
			
			
			System.out.println(hyprlistdata);
			WebUTIL.setAttributeInSession(request, "mappingList5", value1);
		
			
			
			/*List<ResultEntryVOGroupByValidatedBy> resultEntryList=(List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("SELECTEDWORKORDERLIST");*/
			List<ResultEntryVO> resultEntryList=(List<ResultEntryVO>)session.getAttribute(InvestigationConfig.LIST_RESULT_ENTRY);
			
					
			
		//	String requisitionFormClob=invFungusProcessDATA.getxml(fb, userVO);
			String requisitionFormClob=(String)session.getAttribute("requiredXML");
			
			if (requisitionFormClob != null)
			{
				ip = new ByteArrayInputStream(
						requisitionFormClob.getBytes());
				
			}
	
	
	     found = true;		
      	if (found) {
      		
      		tempDoc = builder.parse(ip);				
      	
      		
      		/*String str="xmlset";*/
	    /* this.loadEachNodeOfDocumentSeparately(vObj,str, session);*/

      	}		
				
      	if(tempDoc!=null)
      	{
      		System.out.println(tempDoc);
      	}
      	
      	
      	
      	List<invFungusProcessVO> invFungusProcessVOes=new ArrayList<invFungusProcessVO>();
		for(int abClassIndex=0;abClassIndex<fb.getChkSamplePatient().length;abClassIndex++)
		{
			String antibioticClassCode=fb.getChkSamplePatient()[abClassIndex];
			invFungusProcessVO invFungusProcessVO=new invFungusProcessVO();
			invFungusProcessVO.setAntibioticClassName(fb.getChkSamplePatientName()[abClassIndex]);
			invFungusProcessVO.setAntibioticsClassCode(fb.getChkSamplePatient()[abClassIndex]);
			List<invFungusProcessVO> antibioticsList=new ArrayList<invFungusProcessVO>();
			
			for(int abindex=0;abindex<fb.getChkSamplePatient().length;abindex++)
			{
				//LOGGER_INV.log(Level.INFO,abindex+"  :: "+fb.getSelectedAntibioticCode()[abindex].replace("_", "#").split("#")[0]+"  :: "+antibioticClassCode);
				if(fb.getChkSamplePatient()[abindex].equals(antibioticClassCode))
				{
					invFungusProcessVO antiBioticVO=new invFungusProcessVO();
					antiBioticVO.setAntibioticCode(fb.getChkSamplePatient()[abindex]);
					antiBioticVO.setAntibioticName(fb.getChkSamplePatientName()[abindex]);
					antibioticsList.add(antiBioticVO);
					List<invFungusProcessVO> organismVOList=new ArrayList<invFungusProcessVO>();
					for(int orgIndex=0;orgIndex<fb.getChkSamplePatient().length;orgIndex++)
					{
						invFungusProcessVO organismVO=new invFungusProcessVO();
						organismVO.setOrganismCode(fb.getChkSamplePatient()[orgIndex]);
						organismVO.setOrganismName(fb.getChkSamplePatientName()[orgIndex]);
						String value=request.getParameter(invFungusProcessVO.getAntibioticsClassCode()+"_"+antiBioticVO.getAntibioticCode()+"#"+organismVO.getOrganismCode());
						//LOGGER_INV.log(Level.						
								//INFO,"Parameter Name :: "+invFungusProcessVO.getAntibioticsClassCode()+"_"+antiBioticVO.getAntibioticCode()+"#"+organismVO.getOrganismCode()+" Value :: "+value);
						organismVO.setValue(value);
						organismVOList.add(organismVO);
						
					}
					
					antiBioticVO.setOrganismList(organismVOList);
				}
			}
			
			invFungusProcessVO.setAntibioticList(antibioticsList);
			invFungusProcessVOes.add(invFungusProcessVO);
		}
		//createNodeResultTemplatePage(request,resultEntryList,invFungusProcessVOes,fb,tempDoc);
		//createTableStringForTheResultTemplatePage(request,invFungusProcessVOes,fb);

		createTableStringForTheResultTemplatePage(request,hyprlistdata,mpBilled12);
		//	createNodeResultTemplatePage(request,resultEntryList,sb1,fb,tempDoc);
      	
    	/*Node tempNode=null;
    	NodeList tempNodeList=tempDoc.getElementsByTagName("element");
    	for(int i=0;i<tempNodeList.getLength();i++)
    	{
    		Node node=tempNodeList.item(i);
    		if(node.getAttributes().getNamedItem("name").getNodeValue().equals("331011000216080810000101#null#"+"template#"+testcode+testparacode"100091007"));
    		{
    			
    			tempNode=node;
    		}
    		
    	}
    	
    	//checkForExistingSiteDiagnosisDetailNode
    	
    	if(tempNode!=null)
    	{
    	
    		NodeList tempNodeChild=tempNode.getChildNodes();
    		for(int i=0;i<tempNodeChild.getLength();i++)
    		{
    			Node node=tempNodeChild.item(i);
    			System.out.println();
    			if(node.getNodeName().equals("antisusceptibility"))
    			{
    				tempNode.removeChild(node);
    				break;
    			}
    		}
    		
    		Element siteDiagnosisDetailNode=tempDoc.createElement("siteDiagnosisDetail");
    		tempNode.appendChild(siteDiagnosisDetailNode);
    		createSiteDiagnosisNode(tempDoc,sb1,siteDiagnosisDetailNode);
    		
    		String innerHtmlStr=createInnerHtml(sb1);
    		
    		request.setAttribute("INNERHTMLSTRING", innerHtmlStr);
    	}
    	*/
    		
    		
     
    	
      	
      		
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			

}

	
	/*public static void modifydata(invFungusProcessFB fb,HttpServletRequest request) throws SAXException, IOException, ParserConfigurationException    
	{
		HttpSession session = request.getSession();
		Status objStatus= new Status();
    
		UserVO userVO = ControllerUTIL.getUserVO(request);
      
		invFungusProcessVO vo=new invFungusProcessVO();
		Document tempDoc = null;
		java.io.InputStream ip = null;
		boolean found=false;
		try{	
			builderFactory = DocumentBuilderFactory.newInstance();
			builder = builderFactory.newDocumentBuilder();
			//factory = XPathFactory.newInstance();
			transformerFactory = TransformerFactory.newInstance();
			
			String[] chkvalue=fb.getChkSamplePatient();
			StringBuffer sb=new StringBuffer();
			List<String> sb1=new ArrayList<String>();
			List<String> DiagnosisList=new ArrayList<String>();
			for(int i=0;i<chkvalue.length;i++)
			{
			sb=new StringBuffer();
			String[] selectval=fb.getChkInfo();
			    sb.append(fb.getOrganismCode());
			    sb.append("#");
			    sb.append(chkvalue[i]);
		     	sb.append("#");
		     	sb.append(selectval[i]);
		     	sb.append("#");
                
				//sb.setLength(sb.length()-1);
				System.out.println(sb.toString());    
                sb1.add(sb.toString());
			}
			
			String value1="";
			for(int i=0;i<sb1.size();i++)
			{
				
				String[] val=sb1.get(i).split("#");
				 value1+=val[0]+"$$"+val[1]+"$$"+val[2]+"$$$";
				
			}
			
			
			
			WebUTIL.setAttributeInSession(request, "mappingList5", value1);
		
			
			
			List<ResultEntryVOGroupByValidatedBy> resultEntryList=(List<ResultEntryVOGroupByValidatedBy>)request.getSession().getAttribute("SELECTEDWORKORDERLIST");
			List<ResultEntryVO> resultEntryList=(List<ResultEntryVO>)session.getAttribute("VOLIST");
			
			ResultEntryVO voPat=resultEntryList.get(0);
			String parameter=voPat.getParantParaCode();
			String testcode=voPat.getTestCode();
			String testparacode=voPat.getTestParaMeterCode();
					
			
		//	String requisitionFormClob=invFungusProcessDATA.getxml(fb, userVO);
			String requisitionFormClob=(String)session.getAttribute("requiredXML");
			
			if (requisitionFormClob != null)
			{
				ip = new ByteArrayInputStream(
						requisitionFormClob.getBytes());
				
			}
	
	
	     found = true;		
      	if (found) {
      		
      		tempDoc = builder.parse(ip);				
      	
      		
      		String str="xmlset";
	     this.loadEachNodeOfDocumentSeparately(vObj,str, session);

      	}		
				
      	if(tempDoc!=null)
      	{
      		System.out.println(tempDoc);
      	}
      	
      	
      	
      	List<invFungusProcessVO> invFungusProcessVOes=new ArrayList<invFungusProcessVO>();
		for(int abClassIndex=0;abClassIndex<fb.getChkSamplePatient().length;abClassIndex++)
		{
			String antibioticClassCode=fb.getChkSamplePatient()[abClassIndex];
			invFungusProcessVO invFungusProcessVO=new invFungusProcessVO();
			invFungusProcessVO.setAntibioticClassName(fb.getChkSamplePatientName()[abClassIndex]);
			invFungusProcessVO.setAntibioticsClassCode(fb.getChkSamplePatient()[abClassIndex]);
			List<invFungusProcessVO> antibioticsList=new ArrayList<invFungusProcessVO>();
			
			for(int abindex=0;abindex<fb.getChkSamplePatient().length;abindex++)
			{
				//LOGGER_INV.log(Level.INFO,abindex+"  :: "+fb.getSelectedAntibioticCode()[abindex].replace("_", "#").split("#")[0]+"  :: "+antibioticClassCode);
				if(fb.getChkSamplePatient()[abindex].equals(antibioticClassCode))
				{
					invFungusProcessVO antiBioticVO=new invFungusProcessVO();
					antiBioticVO.setAntibioticCode(fb.getChkSamplePatient()[abindex]);
					antiBioticVO.setAntibioticName(fb.getChkSamplePatientName()[abindex]);
					antibioticsList.add(antiBioticVO);
					List<invFungusProcessVO> organismVOList=new ArrayList<invFungusProcessVO>();
					for(int orgIndex=0;orgIndex<fb.getChkSamplePatient().length;orgIndex++)
					{
						invFungusProcessVO organismVO=new invFungusProcessVO();
						organismVO.setOrganismCode(fb.getChkSamplePatient()[orgIndex]);
						organismVO.setOrganismName(fb.getChkSamplePatientName()[orgIndex]);
						String value=request.getParameter(invFungusProcessVO.getAntibioticsClassCode()+"_"+antiBioticVO.getAntibioticCode()+"#"+organismVO.getOrganismCode());
						//LOGGER_INV.log(Level.						
								//INFO,"Parameter Name :: "+invFungusProcessVO.getAntibioticsClassCode()+"_"+antiBioticVO.getAntibioticCode()+"#"+organismVO.getOrganismCode()+" Value :: "+value);
						organismVO.setValue(value);
						organismVOList.add(organismVO);
						
					}
					
					antiBioticVO.setOrganismList(organismVOList);
				}
			}
			
			invFungusProcessVO.setAntibioticList(antibioticsList);
			invFungusProcessVOes.add(invFungusProcessVO);
		}
		createNodeResultTemplatePage(request,resultEntryList,invFungusProcessVOes,fb,tempDoc);
		createTableStringForTheResultTemplatePage(request,invFungusProcessVOes,fb);
      //	createNodeResultTemplatePage(request,resultEntryList,sb1,fb,tempDoc);
      	
    	Node tempNode=null;
    	NodeList tempNodeList=tempDoc.getElementsByTagName("element");
    	for(int i=0;i<tempNodeList.getLength();i++)
    	{
    		Node node=tempNodeList.item(i);
    		if(node.getAttributes().getNamedItem("name").getNodeValue().equals("331011000216080810000101#null#"+"template#"+testcode+testparacode"100091007"));
    		{
    			
    			tempNode=node;
    		}
    		
    	}
    	
    	//checkForExistingSiteDiagnosisDetailNode
    	
    	if(tempNode!=null)
    	{
    	
    		NodeList tempNodeChild=tempNode.getChildNodes();
    		for(int i=0;i<tempNodeChild.getLength();i++)
    		{
    			Node node=tempNodeChild.item(i);
    			System.out.println();
    			if(node.getNodeName().equals("antisusceptibility"))
    			{
    				tempNode.removeChild(node);
    				break;
    			}
    		}
    		
    		Element siteDiagnosisDetailNode=tempDoc.createElement("siteDiagnosisDetail");
    		tempNode.appendChild(siteDiagnosisDetailNode);
    		createSiteDiagnosisNode(tempDoc,sb1,siteDiagnosisDetailNode);
    		
    		String innerHtmlStr=createInnerHtml(sb1);
    		
    		request.setAttribute("INNERHTMLSTRING", innerHtmlStr);
    	}
    	
    		
    		
      	
    	
      	
      		
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			

}*/
	
	
	
	//added by chandan
	public static void createTableStringForTheResultTemplatePage(HttpServletRequest request, List<invFungusProcessVO> siteDiagnosisList,Map<String,List<invFungusProcessVO>> mp)
	{
		String tablefinal1="";
		  if(mp!=null)
		  {
			  Iterator itr=mp.keySet().iterator();
			  while(itr.hasNext())
		 		{
		 			String organisgm1=(String)itr.next();

		 			List<invFungusProcessVO> lstVOSample=mp.get(organisgm1);

		 			
				  String tableString="<table width='100%'>";
					
					String firstRow="<tr><td width='50%'><div align='left'><b> Fungus Name </b></div></td>";
					String width=50+"%";
					String width111=30+"%";
						invFungusProcessVO voPat=lstVOSample.get(0);
						
						firstRow+="<td width='"+width+"'><div align='left'>"+voPat.getOrganismName()+"</div></td>";
						
						tableString+=firstRow+"</tr>";
					
						String firstRow121="<tr><td width='50%'><div align='left'><b> Growth  </b></div></td>";	
						firstRow121+="<td width='"+width+"'><div align='left'>"+voPat.getGrowthname()+"</div></td>";
						tableString+=firstRow121+"</tr>";

						
						String tableString1="";
					String firstRow1="";
					firstRow1+="<tr><td width='35%'><div align='left'><b>"+"Antibiotic Name"+"</b></div></td>";	
					firstRow1+="<td width='35%'><div align='left'><b>"+"Result"+"</b></div></td>";
			         firstRow1+="<td width='30%'><div align='left'><b>"+"Remark"+"</b></div></td></tr>";
			         for(int k=0;k<lstVOSample.size();k++)
			 			{
						invFungusProcessVO voPat1=lstVOSample.get(k);
						
						 firstRow1+="<tr><td width='35%'><div align='left'>"+voPat1.getAntibioticName()+"</div></td>";
						 if(voPat1.getValue().equals("1"))
						firstRow1+="<td width='35%'><div align='left'>"+"R"+"</div></td>";
						 if(voPat1.getValue().equals("2"))
									firstRow1+="<td width='35%'><div align='left'>"+"S"+"</div></td>";
						 if(voPat1.getValue().equals("3"))
									firstRow1+="<td width='35%'><div align='left'>"+"MS"+"</div></td>";
									
							firstRow1+="<td width='30%'><div align='left'>"+voPat1.getRemark()+"</div></td></tr>";

					}
					
					

					tableString1+=firstRow1+"</table><br/><br/>";
					
					 tablefinal1+=	tableString+tableString1;

		 		}
		  }
		/*String tableString="<table width='50%'>";
		
		String firstRow="<tr><td width='50%'><div align='center'><b> Organisms Name </b></div></td>";
		String width=50+"%";
		String width111=30+"%";
			invFungusProcessVO voPat=siteDiagnosisList.get(0);
			
			firstRow+="<td width='"+width+"'><div align='center'>"+voPat.getOrganismName()+"</div></td>";
			
			tableString+=firstRow+"</tr>";
			
			
			
			String tableString1="";
		String firstRow1="";
		firstRow1+="<tr><td width='20%'><div align='center'><b>"+"Antibiotic Name"+"</b></div></td>";	
		firstRow1+="<td width='20%'><div align='center'><b>"+"Result"+"</b></div></td>";
         firstRow1+="<td width='10%'><div align='center'><b>"+"Remark"+"</b></div></td></tr>";
for(int i=0;i<siteDiagnosisList.size();i++)
		{
			invFungusProcessVO voPat1=siteDiagnosisList.get(i);
			
			 firstRow1+="<tr><td width='20%'><div align='center'>"+voPat1.getAntibioticName()+"</div></td>";
			 if(voPat1.getValue().equals("1"))
			firstRow1+="<td width='20%'><div align='center'>"+"R"+"</div></td>";
			 if(voPat1.getValue().equals("2"))
						firstRow1+="<td width='20%'><div align='center'>"+"S"+"</div></td>";
			 if(voPat1.getValue().equals("3"))
						firstRow1+="<td width='20%'><div align='center'>"+"IS"+"</div></td>";
						
				firstRow1+="<td width='10%'><div align='center'>"+voPat1.getRemark()+"</div></td></tr>";

		}
		
		

		tableString1+=firstRow1+"</table>";*/
		
		
		//System.out.println(tableString+tableString1);

		
		String table="<table width='100%'><tr><td width='100%'><fieldset><font size='1px'><u>Interpretaion</u>:<br/><p>Sensitive (S): Infection is expected to respond to treatment with normal doses.</p><br/><p>Moderate Sensitive (MS): Infection may respond to treatment with higher doses.</p><br/><p>Resistance (R): Clinical respose is unlikey even with maximum tolerated doses.</p></font></fieldset></td></tr></table>";	

		
		
		
	String tablefinal=	tablefinal1+table;
	//return tablefinal;
	request.getSession().setAttribute("tableString", tablefinal);
		/*String[] organismrows=fb.getChkSamplePatient();
		String tableString="<table width='100%'>";
		String firstRow="<tr><td width='100%'><table width='100%' cellspacing='1px' cellpadding='1px' bordercolor='#FFB366' border='1px'><tr><td width='34%'><div align='center'><b>AntiBiotics / Organisms </b></td>";
		String width=66/organismrows.length+"%";
		for(String organismtd:organismrows)
		{
			firstRow+="<td width='"+width+"'><div align='center'><b>"+organismtd+"</b></div></td>";
		}
		tableString+=firstRow+"</tr></table></td></tr>";
		
		for(invFungusProcessVO antiBioticVOClass:antiBioticVOClasses)
		{
			String rowString="<tr><td width='100%'><table width='100%' cellspacing='1px' cellpadding='1px' bordercolor='#FFB366' border='1px'><tr><td width='34%'><div align='left'><b>"+antiBioticVOClass.getAntibioticClassName()+"</b></div></td></tr>";
			for(invFungusProcessVO antiBioticVO : antiBioticVOClass.getAntibioticList())
			{
				rowString+="<tr><td width='34%'><div align='right'><b>"+antiBioticVO.getAntibioticName()+"</b></div></td>";
				for(invFungusProcessVO organismVO  :antiBioticVO.getOrganismList())
				{
					rowString+="<td width='"+width+"'><div align='center'>"+organismVO.getValue()+"</div></td>";
				}
				rowString+="</tr>";
				
			}
			
			rowString+="</table></td></tr>";
			tableString+=rowString;
		}
		
		
		
		tableString+="</table>";
		//LOGGER_INV.log(Level.INFO,tableString);
		request.getSession().setAttribute("tableString", tableString);*/
		/*System.out.println(tableString);*/
		
	}
	
	
	public static void createNodeResultTemplatePage(HttpServletRequest request, List<ResultEntryVO> resultEntryList, List<invFungusProcessVO> antiBioticVOClasses, invFungusProcessFB fb,Document document1)
	{
		
		Document document=document1;
		//Document document=resultEntryVO.getTestDocument();
		String[] organismrows=fb.getChkSamplePatient();
		String[] organismCoderows=fb.getChkSamplePatientName();
		Element antiSusceptibilityElement=document.createElement("antisusceptibility");
		Element rowsElement=document.createElement("rows");
		antiSusceptibilityElement.appendChild(rowsElement);
		String width=66/organismrows.length+"%";
		int j=0;
		for(String organism:organismrows)
		{
			Element organismElement=document.createElement("organismrow");
			organismElement.setAttribute("value", organismCoderows[j++]);
			organismElement.setAttribute("code", organism);
			organismElement.setAttribute("width", width);
			rowsElement.appendChild(organismElement);
		}
		
		
		Element antibioticclassesElement=document.createElement("antibioticclasses");
		antiSusceptibilityElement.appendChild(antibioticclassesElement);
		for(invFungusProcessVO antiBioticVOClass:antiBioticVOClasses)
		{
			Element antibioticclassElement=document.createElement("antibioticclass");
			antibioticclassElement.setAttribute("value", antiBioticVOClass.getAntibioticClassName());
			antibioticclassElement.setAttribute("code", antiBioticVOClass.getAntibioticsClassCode());
			antibioticclassElement.setAttribute("width", "34%");
			antibioticclassesElement.appendChild(antibioticclassElement);
			
			
			for(invFungusProcessVO antiBioticVO : antiBioticVOClass.getAntibioticList())
			{
				Element antibioticElement=document.createElement("antibiotic");
				antibioticElement.setAttribute("value", antiBioticVO.getAntibioticName());
				antibioticElement.setAttribute("code", antiBioticVO.getAntibioticCode());
				antibioticElement.setAttribute("width", "34%");
				antibioticclassElement.appendChild(antibioticElement);
				
				for(invFungusProcessVO organismVO  :antiBioticVO.getOrganismList())
				{
					Element organismElement=document.createElement("organism");
					organismElement.setAttribute("value", organismVO.getOrganismName());
					organismElement.setAttribute("code", organismVO.getOrganismCode());
					organismElement.setAttribute("data", organismVO.getValue());
					organismElement.setAttribute("width", width);
					antibioticElement.appendChild(organismElement);
					
				}
				
				
			}
			
			
			
		}
		
		
		
		System.out.println(document);
		javax.xml.parsers.DocumentBuilderFactory documentBuilderFactory=javax.xml.parsers.DocumentBuilderFactory.newInstance();
		DocumentBuilder documentBuilder=null;
		try {
			
			documentBuilder = documentBuilderFactory.newDocumentBuilder();
			 TransformerFactory transformerFactory=TransformerFactory.newInstance();
			    Transformer transformer=transformerFactory.newTransformer();
			    
			   	    
		Document testDocument= document1;
		transformer.transform(new DOMSource(testDocument), new StreamResult(path+"withmicrobialData1.xml"));
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		String xQuery="/test/testtemplate/table/rowDetails/columnDetails/table/tr/td/element[@idC='hyperlink'][@name='"+"331011000216081010002001#null#template#100091006"+"']";
		/*LOGGER_INV.log(Level.INFO,"XQuery ::"+xQuery);*/
		XPathExpression expr = xpath.compile(xQuery);
	    Object result = expr.evaluate(testDocument, XPathConstants.NODESET);
	    NodeList nodeList=(NodeList)result;
	    NodeList oldAntiSuspList= nodeList.item(0).getChildNodes();
	    for(int i=0;i<oldAntiSuspList.getLength();i++)
	    {
	    	   nodeList.item(0).removeChild(oldAntiSuspList.item(i));
	    	   i--;
	    }
	    
	    
	    nodeList.item(0).appendChild(antiSusceptibilityElement);
	    
	   
	    transformer.transform(new DOMSource(testDocument), new StreamResult());
	    
	    
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			
			e.printStackTrace();
		} catch (TransformerException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	public static void createTableStringForTheResultTemplatePage(HttpServletRequest request, List<invFungusProcessVO> antiBioticVOClasses, invFungusProcessFB fb,Map<String,List<invFungusProcessVO>> mp)
	{
		
		String[] organismrows=fb.getChkSamplePatient();
		String tableString="<table width='100%'>";
		String firstRow="<tr><td width='100%'><table width='100%' cellspacing='1px' cellpadding='1px'  border='1px'><tr><td width='34%'><div align='center'><b>AntiBiotics / Organisms </b></td>";
		String width=66/organismrows.length+"%";
		for(String organismtd:organismrows)
		{
			firstRow+="<td width='"+width+"'><div align='center'><b>"+organismtd+"</b></div></td>";
		}
		tableString+=firstRow+"</tr></table></td></tr>";
		
		for(invFungusProcessVO antiBioticVOClass:antiBioticVOClasses)
		{
			String rowString="<tr><td width='100%'><table width='100%' cellspacing='1px' cellpadding='1px'  border='1px'><tr><td width='34%'><div align='left'><b>"+antiBioticVOClass.getAntibioticClassName()+"</b></div></td></tr>";
			for(invFungusProcessVO antiBioticVO : antiBioticVOClass.getAntibioticList())
			{
				rowString+="<tr><td width='34%'><div align='right'><b>"+antiBioticVO.getAntibioticName()+"</b></div></td>";
				for(invFungusProcessVO organismVO  :antiBioticVO.getOrganismList())
				{
					rowString+="<td width='"+width+"'><div align='center'>"+organismVO.getValue()+"</div></td>";
				}
				rowString+="</tr>";
				
			}
			
			rowString+="</table></td></tr>";
			tableString+=rowString;
		}
		
		
		
		tableString+="</table>";
		//LOGGER_INV.log(Level.INFO,tableString);
		request.getSession().setAttribute("tableString", tableString);
		System.out.println(tableString);
		
	}
		
		
	
	private static String createInnerHtml(List<String> siteDiagnosisList)
	{
		String htmlStr="<table width=\"100%\" border=\"1px\" bordercolor=\"#ffb366\" cellpadding=\"1px\" cellspacing=\"1px\">";
		htmlStr+="<tr><td width=\"30%\"><div align=\"center\"><b>Site</b></div></td><td  width=\"70%\"><div align=\"center\"><b>Diagnosis</b></div></td>";
		
		/*for(SiteVO siteVO:siteDiagnosisList)
		{
			
			htmlStr+="<tr><td width=\"30%\"><div align=\"right\">";
			htmlStr+=siteVO.getSiteName()+"("+siteVO.getSiteCode()+"):"+"</div></td><td width=\"70%\"><div align=\"left\">";
			for(int i=0;i<siteVO.getDiagnosisDtl().size();i++)
			{
				DiagnosisVO diagnosisVO=siteVO.getDiagnosisDtl().get(i);
				
				if(i==0)
				{
					htmlStr+=diagnosisVO.getDiagnosisName()+"("+diagnosisVO.getDiagnosisCode()+")";
				}
				else
					htmlStr+=","+diagnosisVO.getDiagnosisName()+"("+diagnosisVO.getDiagnosisCode()+")";
			}
			htmlStr+="</div></td></tr>";
		}*/
		htmlStr+="</table>";
		
	return htmlStr;
	}
	
	
	
	private static void createSiteDiagnosisNode(Document doc, List<String> siteDiagnosisList, Element siteDiagnosisDetailNode) throws ParserConfigurationException 
	{
		Element siteElement=null;
		/*for(String siteVO:siteDiagnosisList)
		{
			siteElement=doc.createElement("site");
			siteDiagnosisDetailNode.appendChild(siteElement);
			
			siteElement.setAttribute("name",siteVO.getSiteName());
			siteElement.setAttribute("code", siteVO.getSiteCode());
			siteElement.setAttribute("internalCode",siteVO.getInSiteCode());
			
			for(invFungusProcessVO diagnosisVO:siteVO.getDiagnosisDtl())
			{
				Element diagnosisElement=doc.createElement("diagnosis");
				diagnosisElement.setAttribute("name",diagnosisVO.getDiagnosisName());
				diagnosisElement.setAttribute("code", diagnosisVO.getDiagnosisCode());
				diagnosisElement.setAttribute("internalCode",diagnosisVO.getInDiagnosisCode());
				siteElement.appendChild(diagnosisElement);
				
			}//2nd loop
			
		}*///first loop 
		
		
	}
		
	/*	public void loadEachNodeOfDocumentSeparately(Document vObj,String str,HttpSession session) {
			// LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately");
			XPath xpath = factory.newXPath();
			try {

				XPathExpression expr = xpath.compile("/templates/testtemplate");
				Object result = expr.evaluate(vObj, XPathConstants.NODESET);
				NodeList nodes = (NodeList) result;
				// LOGGER_INV.log(Level.INFO,"loadEachNodeOfDocumentSeparately ->"+nodes.getLength());
				for (int i = 0; i < nodes.getLength(); i++) {
					Node node = nodes.item(i);
					// investigationDocumentCacheVO.put(str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue(),
					// node);
					String r = node.getAttributes().getNamedItem("labTestCode")
							.getNodeValue();
					session.setAttribute(str
							+ "_"
							+ node.getAttributes().getNamedItem("labTestCode")
									.getNodeValue(), node);

					// LOGGER_INV.log(Level.INFO,"Loading key"+str+"_"+node.getAttributes().getNamedItem("labTestCode").getNodeValue());
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}

		}*/

	public static void getAntibioticName(invFungusProcessFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();

		UserVO userVO = ControllerUTIL.getUserVO(request);
      
		invFungusProcessVO vo=new invFungusProcessVO();
		
		try{	
			Map mp=new HashMap(); 
			Map mpp=new HashMap(); 

             HelperMethods.populate(vo,fb);
             
			mp=invFungusProcessDATA.getAntibioticName(vo, userVO);
			WebUTIL.setMapInSession(mp, request);
			
				objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			

}
	
	
	public ActionForward PAGINATION(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response)
	{
		invFungusProcessFB fb=(invFungusProcessFB)form;
		Status  objStatus=new Status();
		  objStatus.add(Status.TRANSINPROCESS);
		WebUTIL.setStatus(request,objStatus);
		return mapping.findForward("NEW");
	}

	
	
	public static String adddatainlist(invFungusProcessFB fb,HttpServletRequest request)
	{
		Status objStatus= new Status();
		HttpSession session = request.getSession();

		UserVO userVO = ControllerUTIL.getUserVO(request);
      
		invFungusProcessVO vo=new invFungusProcessVO();
		String organismcode="";
	
		try{	
			Map<String,List<antibioticprocessVO>> mp=new HashMap(); 
			Map mpp=new HashMap(); 

              if(fb.getData()!=null && !fb.getData().equals(""))
              {
            	  List<antibioticprocessVO> vv=new ArrayList<>();
            	  String data=fb.getData();
            	   organismcode=fb.getOrganismCode();
            	  String organismname= fb.getOrganismnamee();
            	  String growth=fb.getGrowthCode();
            		String growthname=fb.getGrowthname();
            		String reqno=fb.getRequisitionNo();
            		String reqdno=fb.getRequisitionDNo();
            		String testparacode=fb.getTestParaCode();
            		
            	 for(int i=0;i<data.split("\\$\\$\\$").length;i++)
            	 {
            	  String dataString=data.split("\\$\\$\\$")[i];
            	  
            	  String antibioticname=dataString.split("@@@")[0].split("@")[1];
            	  String antibioticCode=dataString.split("@@@")[0].split("@")[0];
            	  String result=dataString.split("@@@")[1]==null?"":dataString.split("@@@")[1];
            	  String remarks=dataString.split("@@@")[2]==null?"":dataString.split("@@@")[2];
            	 
            	  antibioticprocessVO vpo=new antibioticprocessVO();
            	  
            	  vpo.setAntibioticname(antibioticname);
            	  vpo.setAntibioticcode(antibioticCode);
            	  vpo.setResult(result);
            	  vpo.setRemark(remarks);
            	  vpo.setGrowth(growth);
            	  vpo.setGrowthname(growthname);
            	  vpo.setOrganismcode(organismcode);
            	  vpo.setOrganismname(organismname);
            	  vpo.setRequisitionNo(reqno);
            	  vpo.setRequisitionDNo(reqdno);
            	  vpo.setTestParaCode(testparacode);
            	  
            	  vv.add(vpo);
            	 }
            	 
            	 if(vv!=null && vv.size()>0)
            	 {
            		 mp.put(reqdno+"#"+organismcode, vv);
            		 
            		 
            			Map<String,List<antibioticprocessVO>> mpBilled= (Map<String,List<antibioticprocessVO>>)session.getAttribute(InvestigationConfig.list_fungus_in_sessionhyperlinkdata);
				 		
	                    if(mpBilled!=null)
	                    {
	          Iterator itr=mpBilled.keySet().iterator();
		 		int index=0;
		 		boolean sameReqNo=false;
		 		int j1=0;
		 		while(itr.hasNext())
		 		{
		 			sameReqNo=true;
		 			String organisgm=(String)itr.next();
	            
		 			if(organismcode.equalsIgnoreCase(organisgm))
		 			{
		 				mpBilled.put(reqdno+"#"+organismcode, vv);
		 				mpp.put(InvestigationConfig.list_fungus_in_sessionhyperlinkdata,mpBilled);
	            		// mpp.put(InvestigationConfig.list_fungus_in_sessionhyperlinkdata,mp);

		 				
		 			}
		 			else
		 			{
		 				mpBilled.put(reqdno+"#"+organismcode, vv);
		 				mpp.put(InvestigationConfig.list_fungus_in_sessionhyperlinkdata,mpBilled);
		 			}
		 			
		 		}
		 		
		 		if(sameReqNo==false)
		 		{
           		 mpp.put(InvestigationConfig.list_fungus_in_sessionhyperlinkdata,mp);

		 		}
	                    }
	                    else
            		 mpp.put(InvestigationConfig.list_fungus_in_sessionhyperlinkdata,mp);
            	 }
            	 
            	 
            	 WebUTIL.setMapInSession(mpp, request);
            	  
             System.out.println("ss");
              }
			/*for(int i=0;i<fb.getChkSamplePatient().length;i++)
			{
				String data=fb.getChkSamplePatient()[i];
				System.out.println("data:"+data);
				
			}*/
			
			
			
			
				objStatus.add(Status.TRANSINPROCESS);
		}catch(HisRecordNotFoundException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.UNSUCESSFULL,"","Data Not Found");
		}
		catch(HisDataAccessException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_DA,"","Data Access Error");
		}
		catch(HisApplicationExecutionException e)
		{		
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR_AE,"","Application Execution Error");
		}
		catch(HisException e)
		{
			System.out.println(e.getMessage());
			objStatus.add(Status.ERROR,"","Error");
		}			
		return organismcode;

}
	
}
