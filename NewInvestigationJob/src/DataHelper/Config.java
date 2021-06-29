/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataHelper;

/**
 *
 * @author Siddharth Srivastava <siddharthsrivastava@cdac.in>
 */
public class Config {

    public static String pagewidthprinting = "595.0";
    public static String pagewidthheight = "A4867.6";

    //Start of Color Coding for template
    public static String PRIORITY_NORMALCOLOR = "#F1ECE2";
    public static String PRIORITY_HIGHCOLOR = "#FFCCCC";
    public static String PRIORITY_LOWCOLOR = "#BDE4B8";
	//end of color coding for template

    // For Template Processing
    public static int minimumNoOfColumnsinSimpleTemplate = 6;
    public static String XML_TESTTEMPLATE = "1";
    public static String XML_TESTSAMPLETEMPLATE = "2";
    public static String XML_TESTREQUISITIONTEMPLATE = "3";
    public static String XML_TEMPLATEQUERYFILE = "4";
    public static String XML_PRINTINGTEMPLATE = "5";
    public static String XML_LABTESTGROUPTEMPLATE = "6";

    public static String XSL_PRINTINGTEMPLATE = "1";
    public static String XSL_TESTPROCEDURESTYLESHEET = "2";
    public static String XSL_VIEWTEMPLATE_STYLESHEET = "3";
    public static String XSL_SAMPLEFORMSTYLESHEET = "4";
    public static String XSL_SAMPLEFORMREPORTSTYLESHEET = "5";
    public static String XSL_RESULTENTRYSTYLESHEET = "6";
    public static String XSL_REQUISITIONFORMSTLYESHEET = "7";
    public static String XSL_REQUISITIONFORMREPORTSTYLESHEET = "8";
    public static String XSL_REPORTWITHOUTNORMALVALUES = "9";
    public static String XSL_REPORTWITHNORMALVALUES = "10";
    public static String XSL_REPORTDESIGNSTYLESHEET = "11";
    public static String XSL_REPORT = "12";
    public static String XSL_REDESIGNSTYLESHEET = "13";
    public static String XSL_PRINTINGSTYLESHEET_HEADER = "14";
    public static String XSL_PRINTINGSTYLESHEET_FOOTER = "15";
    public static String XSL_PRINTINGSTYLESHEET_BODY = "21";
    public static String XSL_COMMONLIBRARYTEMPLATESREPORT = "17";
    public static String XSL_COMMONLIBRARYTEMLATES = "18";
    //End Template Porcessing
    public static String HOSPITAL_CODE = "100";

    public static String STATUS_REQUISITIONTYPE_WITHCRNO_IPD = "1";// REQUISITION HEADER
    // TABLE
    public static String STATUS_REQUISITIONTYPE_WITHCRNO_OPD = "2";

    public static String hospitalnameatreport="Nizam's Institute of Medical Sciences";
    public static String hospitaladdressatreport="Hyderabad";
	public static String specialstringatreport="This is computer generated report. Signature not required";
	public static String disclaimeratreport = "All reports need clinical correlation. Kindly discuss if necessary. No part of the report can be reproduced without written permission of the department.";
	
	//dept template xml id
	 public static String XML_DEPT_TESTTEMPLATE = "2";
	 /*public static String HEADER_HEIGHT = "270.0";*/
	 
	 /*public static String HEADER_HEIGHT = "217.0";*/
	 
	 //public static String HEADER_HEIGHT = "268.0";
	 //public static String HEADER_HEIGHT = "215.0";
	 public static String HEADER_HEIGHT = "200.0";
	 //public static String HEADER_HEIGHT = "245.0";
	 //public static String HEADER_HEIGHT_WITH_HEADER = "210.0";
	 public static String HEADER_HEIGHT_WITH_HEADER = "150.0";
	 //public static String HEADER_HEIGHT_WITHOUT_HEADER = "210.0";
	 public static String HEADER_HEIGHT_WITHOUT_HEADER = "210.0";
	 //public static String HEADER_HEIGHT_WITHOUT_HEADER = "252.0";
	 public static String FOOTER_HEIGHT = "34.0";

	 public static String XSL_REPORTWITHNORMALVALUES_LATEST = "77";

		public static String disclaimeratreport_immuno_name = "<br/>DR ASHISH K.GUPTA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DR RAKESH K. GUPTA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DR VANDITA SINGH&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DR AMIT BUGALLA&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;DR NIGHAT HUSSAIN";

		public static String disclaimeratreport_immuno_desig ="ASST PROFESSOR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ASST PROFESSOR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ASST PROFESSOR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ASSO PROFESSOR&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ADD PROFESSOR";
		
		public static String disclaimeratreport_immuno_deg = "(MD,PATHOLOGY)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MD,PATHOLOGY)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MD,PDF NEUROPATH)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MD,PDF HEMATPATH)&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(MD,MBAHCS)";
		

}
