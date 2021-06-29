
package usermgmt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import HisGlobal.HisMethods;
import HisGlobal.HisResultSet;
public class umgmtGlobal
{
	
	//in this file we define global varibal for investigation
    public String html="";
    public String moduleId 			= "16"; 
	public String metatableId_Dept 	= "101";
	public String metatableId_Lab	= "106";
	public String prefix_BillReq 	= "01001";
	boolean	error			=	false;
    public int i;
    generalQuery gq= new generalQuery();
    HisResultSet rs	=	null;
 
  
    public String getPagination(String query,int noOfColumns,ArrayList colSize,ArrayList hList,int recordPerPage,int noOfPage,String TransactionName,String pagePerBlock,int pageNo,String minPage,String maxPage,String blockNo,int noOfBlock,String box,ArrayList bList,ArrayList retList)
    {
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs1 = null;
        
        int recordNum=0;
        html+="    <table width='80%' border='1' align='center'>"+
              "    <tr><td class='tdfont' colspan="+noOfColumns+1+"><b>"+TransactionName+"</td></tr>"+
              "    <tr><td class='header' align='right' colspan="+noOfColumns+1+">"+getPaginationHeader(pagePerBlock,noOfPage,minPage,maxPage,blockNo,noOfBlock,pageNo)+"</td></tr>"+
              "    <tr><td class='header'></td>"/*+getBoxHeaderType(box)*/;
        for(i=0;i<+noOfColumns;i++)
        { 
            html+= "<td class ='tdfont'  bgcolor='#F5F3F3' width='"+colSize.get(i)+"'><b>"+hList.get(i)+"</td>";
        }
        html+="</tr>";
        try
        {
            conn =    new HisMethods().getConnection();
            stmt =    conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            rs1 =     stmt.executeQuery(query);
           
            int abc = (pageNo*recordPerPage)-(recordPerPage-1);
            int counter=1;
            if(rs1.next())
             {
                rs1.relative(abc);
                while(rs1.next()&&counter<=recordPerPage)
                {
                    html+=    "<tr>";
                    html+=    getBoxType(box,rs1.getString(1))+"</td>";
                    for(int i=1;i<=noOfColumns;i++)
                    {
                        html+="<td class='tdfont'>"+rs1.getString(i+1)+"</td>";
                    }
                    html+=    "</tr>"; 
                    counter++;
                }  
             }
          
           else
			{
				html = this.setError("Record Not Found");
			}//end of else record found
        
        }
        catch(Exception e)
        {
            System.out.println("Exception is"+e);
        }
        finally
        {
             try
             {
                 rs1.close();
                 stmt.close();
                 conn.close();   
             }
             catch(Exception e1)
             {
                 System.out.println("Exception is"+e1);
             }
             
        }
        html+=    "    <table border='0' align='center'>"+
                  "    <tr>";
                      for(int i=0;i<bList.size();i++)
                      {
                          html+=    "    <td class='addtoolbar'><img style='cursor:hand' src="+bList.get(i)+" tabindex=1 "+
							         retList.get(i)+
							        "    </td>"; 
                      }
        html+=    "    </tr>"+
                   "    </table>"; 
        
     return html;
    }//END OF FUN , made by Neetu Joshi
    /* public String getBoxHeaderType(String  box)
     {
      String html="";
      
      if(box.equals("checkbox"))
        {
            html+="<td class='header'>";
            html+="<input type='checkbox' name='chk' value='' onClick = 'selectAll(this);'>";
        }
  
     if(box.equals("radio"))
        {
            html+="<td class='header'></td>";
        }
  
     
     return html;
     }*/
        
  
    
    public String getBoxType(String box,String boxValue)
    {
        String html="";
       if(box.equals("checkbox"))
        {
             html+="<td class='header'>";
            html+="<input type='checkbox' name='chk' onClick='callme();' value="+boxValue+">";
            
        }
   if(box.equals("radio"))
       {
           html+="<td class='header'>";
           html+="<input type='radio' name='chk' value="+boxValue+">";
       }  
    
     return html;
    
    }
    
    
    public String getPaginationHeader(String pagePerBlock,int noOfPage,String minPage,String maxPage,String blockNo,int noOfBlock,int pageNo)//method for getting pagination header
    {
        int i=0;
        String html="";
        html+="<table border=0 ><tr><td><</td>";
        if(Integer.parseInt(pagePerBlock) < noOfPage) //first time and after every submittion it checks this if condition ( ** next page is come ** )
        {    
            if(blockNo.equals("1")) //first time we initialize blockNo=1; ie default selected page is 1
            {
                for(i=1;i<=Integer.parseInt(pagePerBlock);i++)
                {
                    html+=getPages(i,pageNo,blockNo,pagePerBlock); // method for getting page numbers on the pagination header 
                }
                html+="<td style=CURSOR:HAND onClick=myNext("+blockNo+","+pagePerBlock+"); > Next> </td></tr></table>";
            }
            else if (blockNo.equals(""+noOfBlock))     //this is the last block for displaying. ie last is come
            {
                html+="<td  style=CURSOR:HAND onClick=myPrev("+blockNo+","+pagePerBlock+");>prev</td>";
                for(i=Integer.parseInt(minPage);i<=noOfPage;i++)
                {
                    html+=getPages(i,pageNo,blockNo,pagePerBlock);
                }
                html+="</tr></table>";
            }
            else  //all between conditions of last and first page
            {
                html+="<td  style=CURSOR:HAND onClick=myPrev("+blockNo+","+pagePerBlock+");>prev</td>";
                for(i=Integer.parseInt(minPage);i<=Integer.parseInt(maxPage);i++)
                {   
                   
                   html+=getPages(i,pageNo,blockNo,pagePerBlock);
                }
                html+="<td style=CURSOR:HAND onClick=myNext("+blockNo+","+pagePerBlock+");> Next> </td></tr></table>";
            }
        }
        else // when no of pages is less then page per block
        {
            for( i=1;i<=noOfPage;i++)
            {
                if(pageNo==i)
                {
                    html+= "<td onClick=myMethod("+i+","+blockNo+");"+i+" style=CURSOR:HAND><font color='#CC0000'>"+pageNo+"</font></td>";
                }
                else
                {
                    html+= "<td onClick=myMethod("+i+","+blockNo+");"+i+" style=CURSOR:HAND>"+i+"</td>";
                }
            }
            html+="</tr></table>";
        }
        return html;
    }//END OF FUN , made by Neetu Joshi
   
     public String getPages( int i,int pageNo,String blockNo,String pagePerBlock)//method for number of pages on the pagination header
     {
        String html="";
        if(pageNo==i)
        {
             html+= "<td onClick=myMethod("+i+","+blockNo+","+pagePerBlock+");"+i+" style=CURSOR:HAND><font color='#CC0000'>"+pageNo+"</font></td>";
        }
        else
        {
         html+= "<td onClick=myMethod("+i+","+blockNo+","+pagePerBlock+");"+i+" style=CURSOR:HAND>"+i+"</td>";
        }     
        return html;
     }//END OF FUN 
    
    public String getHeaderPath()
	{		
		return "../../HisGlobal/header.jsp";	
	}
	
	public String getReportHeaderPath()
	{				
		return "../../HisGlobal/reportHeader.jsp";	
	}
	
	public String systemconfigPath()
	{
		String OS_NAME	=	System.getProperties().getProperty("os.name");
		String xmlPath	=	"";
		
		if(OS_NAME.startsWith("Linux"))
			xmlPath = "/root/investigation/systemconfig.xml";
		else
			xmlPath = "C:\\PStudio30\\AHIMS\\systemconfig.xml";
		
		return xmlPath;
	}
	
	public String billGlobalPath()
	{
		String OS_NAME	=	System.getProperties().getProperty("os.name");
		String xmlPath	=	"";
		
		if(OS_NAME.startsWith("Linux"))
			xmlPath = "/root/investigation/billGlobal.xml";
		else
			xmlPath = "C:\\PStudio30\\AHIMS\\billGlobal.xml";
		return xmlPath;
	}
	
	public String invConfigurePath()
	{
		String OS_NAME	=	System.getProperties().getProperty("os.name");
		String xmlPath	=	"";
		
		if(OS_NAME.startsWith("Linux"))
			xmlPath = "/root/investigation/investigation.xml";
		else
			xmlPath = "C:\\PStudio30\\AHIMS\\investigation.xml";
		
		return xmlPath;
	}
	
	public String getButtonLayerPath(int layerCode)
	{
		if(layerCode == 1)	//give cancel and print button
			return "../../HisGlobal/printBtn.jsp";
		else if(layerCode == 2)				//give cancel ptin & PDF button
			return "../../HisGlobal/printPdfBtn.jsp";
		else if(layerCode == 3)				//give cancel ptin & PDF button
			return "../../HisGlobal/printBtn_v2.jsp";
		else if(layerCode == 4)				//give cancel ptin & PDF button
			return "../../HisGlobal/printPdfBtn_v2.jsp";
		else
		 if(layerCode == 5)				//give cancel ptin & PDF button
			return "../../HisGlobal/printBtnWithPgBreak.jsp";
		else
			return "Error Code Pass to getButton";					
	}
	
	public String getModuleLink()
	{
		return "/InvestigationWeb/investigation/";	
	}
	
	public String getPatientDetails(String crNo)
	{
		String html		=	"";
		String query	=	"";
		
		if(!crNo.equals(""))
		{
			usermgmt.generalQuery	gq	=	new	usermgmt.generalQuery();
			HisResultSet 				rs	=	null;				 
			
			query	=	" SELECT INITCAP(A.HRGSTR_FNAME||' '||A.HRGSTR_MNAME||' '||A.HRGSTR_LNAME) PATIENT_NAME, "+
						" 	GETAGE(A.HRGDT_DOB)||' / '|| (SELECT INITCAP(GSTR_GENDER_NAME) FROM GBLT_GENDER_MST WHERE GNUM_GENDER_CODE=A.GNUM_GENDER_CODE ) AGE_SEX, "+
						" 	NVL(INITCAP(A.HRGSTR_FHNAME),'****')F_H_NAME, "+
						" 	NVL(INITCAP(HRGSTR_MOMNAME),'****')MOM_NAME, "+
						" 	( "+
						" 		SELECT INITCAP(HGSTR_PAT_STATUS) "+
						" 	 	FROM HGBT_PATIENT_STATUS_MST "+
						" 	 	WHERE HGNUM_PAT_STATUS_CODE = A.HGNUM_PAT_STATUS_CODE "+
						"  	) IP_OP, "+
						" 	NVL "+
						" 	( "+
						" 		( "+
						" 		SELECT INITCAP(GSTR_DEPT_NAME) "+
						" 		FROM GBLT_DEPARTMENT_MST "+
						"  		WHERE GNUM_DEPT_CODE = A.GNUM_DEPT_CODE "+
						"  		),'****' "+
						"  	) DEPT_NAME, "+
						" 	( "+
						" 		SELECT INITCAP(HRGSTR_REG_CAT_NAME) "+
						" 		FROM HRGT_REG_CAT_MST "+
						" 		WHERE HRGNUM_REG_CAT_CODE = A.HRGNUM_REG_CAT_CODE "+
						" 	) REG_CAT, "+
						" 	( "+
						" 		SELECT INITCAP(HGSTR_PATIENT_CAT_NAME) "+
						" 		FROM HGBT_PATIENT_CAT_MST "+
						" 		WHERE HGNUM_PATIENT_CAT_CODE=A.HGNUM_PATIENT_CAT_CODE "+
						" 	) PATIENT_CAT, "+
						" 	NVL "+
						" 	( "+
						" 		( "+
						" 			SELECT INITCAP(HASTR_WARD_NAME) "+
						" 			FROM HGBT_WARD_MST W "+
						" 			WHERE EXISTS "+
						" 				( "+
						" 					SELECT 'X' "+
						" 					FROM HADT_PATIENT_ADDM_DTL "+
						" 					WHERE HANUM_PUK = A.HRGNUM_PUK "+
						" 						AND TO_CHAR(SUBSTR(HANUM_BED_CODE,3,2)) = W.HANUM_WARD_CODE "+
						" 				) "+
						" 		),'****' "+
						" 	) WARD, "+
						" 	NVL "+
						" 	( "+
						" 		( "+
						" 		SELECT TO_CHAR(SUBSTR(HANUM_BED_CODE,8)) "+
						" 		FROM HADT_PATIENT_ADDM_DTL "+
						" 		WHERE HANUM_PUK = A.HRGNUM_PUK "+
						" 		),'****' "+
						" 	) BED_NO "+
						" FROM HRGT_PATIENT_DTL A "+
						" WHERE A.HRGNUM_PUK = '"+crNo+"' ";
			
			//System.out.println("global patient dtl query "+query);
			try
			{	
				rs	=	gq.getRecord(query);
				while(rs.next())
				{
					html	=	" <table width='100%'> "+
								"  <tr>   "+
								"  	<td colspan='7' width='85%' class='header'><b>Patient Details</b></td> "+
								"  	<td colspan='1' width='15%' class='header'>  "+
								"  		<img style='cursor:hand' src='../../images/plus.gif' tabindex=1 onClick=\"document.all.patientDtl.style.display='block';\" onKeyPress=\"document.all.patientDtl.style.display='block';\">  "+
								"  	   	<img style='cursor:hand' src='../../images/minus.gif' tabindex=1 onClick=\"document.all.patientDtl.style.display='none';\" onKeyPress=\"document.all.patientDtl.style.display='none';\"'>  "+
								//" 		<input type='button' name='diagnosis' value='Diagnosis' style='cursor:hand'> "+
								//" 		<input type='button' name='Complaint' value='Complaint' style='cursor:hand'> "+
								"   </td>  "+
								"  </tr>  "+
								" </table> "+
								" <div id='patientDtl' style='position:relative'> "+
								" 	<table width='100%' border='0'> "+
								"    	<tr>  "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Patient Name</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(1)+"</div></td> "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Age/Sex</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(2)+"</div></td> "+
								"  		</tr> "+
								"   	<tr>  "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Father/Husband Name</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(3)+"</div></td> "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Mother Name</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(4)+"</div></td> "+
								"   	</tr> "+
								"  	 	<tr>  "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>IPD/OPD</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(5)+"</div></td> "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Department</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(6)+"</div></td> "+
								"   		</tr> "+
								"   	<tr>  "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Registration Category</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(7)+"</div></td> "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Patient Category</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(8)+"</div></td> "+
								"   	</tr> "+
								"   	<tr>  "+
								"     		<td width='20%' class='tdfonthead' nowrap><strong>Ward</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(9)+"</div></td> "+
								"    		<td width='20%' class='tdfonthead' nowrap><strong>Bed</strong></td> "+
								"     		<td width='30%' class='tdfont' nowrap><div align='left'>"+rs.getString(10)+"</div></td> "+
								"   	</tr> "+
								"   	<tr>  "+
								"     		<td colspan='4' class='header'>&nbsp;</td> "+
								"   	</tr> "+
								" 	</table> "+
								" </div>";
				}	//end of while
			}
			catch(Exception e)
			{
			    System.out.println("Error : in Getting Patient Details "+e);
			}
			finally
			{
				rs.close();
			}	
		}//end of if	
		return html;
	}//end of fun : getPatientDetails()
	
	//Added to Show the Hospital Name by Singaravelan on 26-Nov-13
	public String getHospitalName(String hosCode)
	{
		String query	=	"";
		String hosName  = 	"";
		if(hosCode!=null && !hosCode.equals(""))
		{
			usermgmt.generalQuery	gQuery	=	new	usermgmt.generalQuery();
			HisResultSet 			rs	=	null;	
			
			query	=	" SELECT GSTR_HOSPITAL_NAME FROM GBLT_HOSPITAL_MST "+
						" WHERE GNUM_HOSPITAL_CODE ='"+hosCode+"'AND GNUM_ISVALID=1";
			
			try
			{
				rs	=	gQuery.getRecord(query);
				//System.out.println("-----Result Set------"+rs.first()+"--------");
				if(rs.first())
					hosName=rs.getString(1);
				
			}
			catch(Exception e)
			{
			    System.out.println("Error : in Getting Hospital Name Details "+e);
			}
			finally
			{
				rs.close();
			}	
		}
		
		return hosName;
	}

public String setError(String errorStr)
	{
		String errHtml	=	"";
		errHtml	=	" <tr>  "+
					" 	<td colspan='8' class='tdfont'> "+
					"		<div align='center'> "+
					"			<font color='#FF0000' size='2'>"+errorStr+"</font> "+
					"		</div> "+
					"	</td> "+
					" </tr> ";

		return errHtml;
	}
//public static String getCustomisedSysDate(Date _dt, String _formatString)
//{
//
////	System.out.println("inside getCustomisedSysDate");
//	//System.out.println("_formatString" + _formatString);
////	System.out.println("_dt" + _dt);
////	SimpleDateFormat sf = (SimpleDateFormat) DateFormat.getInstance();
////	if (_formatString == null)
////	{
////		sf.applyPattern("dd/MM/yyyy HH:mm");
////	}
////	else
////	{
////		sf.applyPattern(_formatString);
////	}
////	String date = sf.format(_dt);
////	//System.out.println("date in dao" + date);
////	return date;
//}
//


}//end of class