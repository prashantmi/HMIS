package hisglobal.tools.tag;


import investigation.InvestigationConfig;
import java.io.File;
import java.io.IOException;
import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.BodyTagSupport;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Source;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class ViewResultsTag extends BodyTagSupport{
	private String workOrderNo;
	private String patCRNo;
	
	

	public int doStartTag() throws JspTagException
		{
		JspWriter out=pageContext.getOut();
		
		
		System.out.println("workOrderNo  :"+workOrderNo+" patCRNo"+patCRNo);
		
		 if(workOrderNo==null || workOrderNo.equals("") )
		 {
			 System.out.println("WorkOrder No. not provided");
		 }
		 else if(patCRNo==null || patCRNo.equals("") )
		 {
			 System.out.println("CR No. not provided");
		 }
		 else
		 {
			 
			 transformDocument(out ,workOrderNo,patCRNo);
			
			 
		 }
		  return EVAL_BODY_BUFFERED ;
		}

	public String getWorkOrderNo() {
		return workOrderNo;
	}

	public void setWorkOrderNo(String workOrderNo) {
		this.workOrderNo = workOrderNo;
	}

	public String getPatCRNo() {
		return patCRNo;
	}

	public void setPatCRNo(String patCRNo) {
		this.patCRNo = patCRNo;
	}

	private void transformDocument(JspWriter out,String workOrderNo,String patCRNo ) {
		try {
				
				String resultEntryString=null;
				resultEntryString =getTemplatedocument(workOrderNo, patCRNo);
				out.print(resultEntryString);	
				
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	static String path=(InvestigationConfig.deploymentMode==0)?InvestigationConfig.windowsPath:InvestigationConfig.linuxPath;
	public static synchronized String getTemplatedocument(String requisitionDno,String patCRNo)
	{
		String templateString=null;
		javax.xml.parsers.DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
		javax.xml.transform.TransformerFactory tansformerFactory=TransformerFactory.newInstance();
		javax.xml.transform.Transformer transformer=null;
		String xquery=null;
		try
		{
		DocumentBuilder db=dbf.newDocumentBuilder();
		transformer = tansformerFactory.newTransformer(new StreamSource(path+"reportWithNormalValues.xsl"));
		transformer.setParameter("normalValues","Yes");
		Document testTemplateDocument=null;
		
		testTemplateDocument = db.parse(new File(path+"insiderequisition/"+patCRNo+"/"+patCRNo+".xml"));
		
	
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		
		xquery="//patient/episode/requisition[@number='"+requisitionDno.substring(0,18)+"']/laboratory/test[@requisitionDNo='"+requisitionDno+"']";
		System.out.println("xQuery  ="+xquery);
		XPathExpression expr = xpath.compile(xquery);
	    Object result = expr.evaluate(testTemplateDocument, XPathConstants.NODESET);
	    NodeList nodes = (NodeList) result;
	    System.out.println("Length  ="+nodes.getLength());
	    for (int i = 0; i < nodes.getLength(); i++) 
	    {
	    	System.out.println("inside template creation show");
	    	Node testDocumentNode=nodes.item(i);
	        Document testDocument=db.newDocument();
	        //processTheTestDocument(resultEntryVO,testDocument);
	        
	        testDocument.appendChild(testDocument.importNode(testDocumentNode, true));
	       
	        Source domSource=new DOMSource(testDocument);
		 	java.io.CharArrayWriter baos=new java.io.CharArrayWriter();
		 	StreamResult streamResult=new StreamResult(baos) ;
		 	transformer.transform(domSource,streamResult);
		 	templateString=baos.toString();
		 	
	  	}
	    
		}
		catch(Exception Ex)
		{
			Ex.printStackTrace();
		}
		return templateString;		
	}
	

	public int doEndTag() throws JspTagException
		{
		   	return EVAL_PAGE;
		}


}
