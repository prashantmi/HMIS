	package hisglobal.tools.tag;

	import hisglobal.vo.ResultEntryVO;
	import investigation.controller.transaction.data.InvRequisitionFormDATA;
	import investigation.controller.transaction.util.InvRequisitionFormUTIL;

	import java.io.IOException;

	import javax.servlet.jsp.JspTagException;
	import javax.servlet.jsp.JspWriter;
	import javax.servlet.jsp.tagext.BodyTagSupport;



	public class TestRemarksTag extends BodyTagSupport {
		
		private String testCode;
		
		

		public String getTestCode() {
			return testCode;
		}

		public void setTestCode(String testCode) {
			this.testCode = testCode;
		}

		

		public int doStartTag() throws JspTagException
		{
			JspWriter out=pageContext.getOut();
			System.out.println("test code-->inside TestRemarksTag  "+testCode);
		if(testCode!=null)
		{
			System.out.println("inside not null");
			getTestRemarks( out, testCode);
		}
		else
		{
			try {
				out.print("<table width='100%'><tr><td width 100%><div align='center'><font color='red'>Test Remarks Not Available</font></div></td></tr></table>");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return EVAL_BODY_BUFFERED ;
		}

		private void getTestRemarks(JspWriter out,String testCode) {
			try {
				String testTemarkString= InvRequisitionFormUTIL.getTestRemarks(pageContext, testCode);
				String  templateString ="<table width='100%'><tr><td class = 'tdfonthead' width='16%' ><div align='left'><b>Test Remarks: </b></div></td><td class='tdfont'><div align='left'>";
				templateString+=testTemarkString;
				templateString+="</div></td></tr></table>";
				System.out.println(templateString);
				out.print((testTemarkString!=null)?templateString:"<table width='100%'><tr><td width='33%' class='tdfont'><div align='left'><b>Test Remarks:</b></div></td><td class='tdfonthead'><div align='left'><font color='red'>Test remarks Not Available</font></div></td></tr></table>");
				
			} catch (IOException e)
			{
				e.printStackTrace();
			}
			
		}

		public int doEndTag() throws JspTagException
			{
			   	return EVAL_PAGE;
			}

		
	}
