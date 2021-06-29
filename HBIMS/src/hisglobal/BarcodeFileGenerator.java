package hisglobal;

import investigation.vo.PrinterVO;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

public class BarcodeFileGenerator {
	//files copied from pramati
	private static final String TEMP_DIR_NAME	=	System.getProperties().getProperty("java.io.tmpdir");
	private static final String OS_NAME			=	System.getProperties().getProperty("os.name");
	/* public BeanRegPrinterSupport()
	{
	System.out.println( OS_NAME +" OS Detected. ");
	System.out.println( TEMP_DIR_NAME +" IS Temp Dir. ");
	}*/



	public void createBarCodeFile(String crno,String patName,String labName,String sampleNo,String sampleName,String printFileName,String formatType)
	{	
				String barCodeDataString;
				//get the String content to be written on the text file
				barCodeDataString = getBarCodeFormatString(crno,patName,labName,sampleNo,sampleName,formatType);
				System.out.println("------after executing barCodeDataString function in barcodefilegenerator-----------");//create the file at the specified location
				createTempFile(barCodeDataString,printFileName);
	}
						
		
			//return barCodeDataString;
		
	

	public void createBarCodeFile_duplicateLabel(String crno,String patName,String labName,String sampleNo,String sampleName,String formatType,String printFileName)
	{	
		String barCodeDataString;
		//get the String content to be written on the text file
		barCodeDataString = getBarCodeFormatString(crno,patName,labName,sampleNo,sampleName,formatType);
		System.out.println("------after executing createBarCodeFile_duplicateLabel function in barcodefilegenerator-----------");//create the file at the specified location
		createTempFile(barCodeDataString,printFileName);
	}
						
	 
	public synchronized static boolean  createTempFile(String contents,String printFileName)
		{
		System.out.println("contents :: "+contents);
			boolean retValue	=	false;
		//System.out.println("TEMP_DIR_NAME "+TEMP_DIR_NAME);
		String fileName	=	TEMP_DIR_NAME+"";
		if(OS_NAME.startsWith("Linux"))
			 fileName="/root/"+printFileName;
		else
			fileName += printFileName;

		try
		{
				//System.out.println("In createtempFile (File Name) "+fileName);
				FileWriter FR 			=   new FileWriter(fileName,false);
				BufferedWriter OS		=	new BufferedWriter(FR);
				for(int i=0;i<contents.length();i++)
				{
					OS.write(contents.charAt(i));
					OS.flush();
				}
				OS.close();
				retValue	=	true;
				System.out.println("fileName ="+fileName+"  ");
				//printSlip("10.103.0.250",printFileName);
			}
			catch(Exception e)
			{
				System.out.println("Exception in createTempFile() "+e);
				retValue	=	false;
			  // investigation.investigationLogFile	logFile		=	new investigation.investigationLogFile();
			 createLogFile("barCodeFileGenerator_createTempFile",""+e);
			}
		return retValue;
	}



	public static boolean printSlip(String hostName,String printFileName)
	{
		System.out.println("inside printSlip function");
		System.out.println("hostName is "+hostName);
		boolean retValue	=	false;
		if(OS_NAME.startsWith("Linux"))
		{
		retValue = linuxPrinting(hostName,printFileName);
		}
		else
		{
			System.out.println("printSlip"+"\\\\"+hostName+"\\printer");
			if(OS_NAME.startsWith("Windows"))
				retValue = windowsPrinting("\\\\"+hostName+"\\printer",printFileName);//For windows based printing
			else
			{
				//System.out.println("Unable to detect the Operating System. Pritnting Aborted");
				retValue = false;
			}
		}
		return retValue;
	}

	public static boolean linuxPrinting(String hostName,String printFileName)
	{
		System.out.println("linuxPrinting");
		boolean retValue	=	false;
		String fileName		=	"/root/"+printFileName;
		String printCommand	=	"echo \"print\" "+fileName+"|smbclient //"+hostName+"/printer -U Administrator%";
		try
		{
			FileOutputStream 	os 	= 	new FileOutputStream("/root/printSlip.sh");
			PrintStream 		ps 	= 	new PrintStream(os,false);
			ps.print(printCommand+"\n");
			//ps.flush();
			ps.close();
			Runtime rt	=	Runtime.getRuntime();
			Process prs	=	rt.exec("/root/changeMode.sh");
			prs.waitFor();
		retValue = true;
		}
		catch(Exception e)
		{
			System.out.println("Exception occurred in linuxPrinting() "+e);
			retValue = false;
		   //investigation.investigationLogFile	logFile		=	new investigation.investigationLogFile();
		   createLogFile("barCodeFileGenerator_linuxPrinting",""+e);
		}
		return retValue;
		
	}


	public static boolean windowsPrinting(String printerPath,String printFileName)
	{
		System.out.println("windowsPrinting"+printerPath);
		StringBuffer cnt	=	new StringBuffer();
		String fileName		=	TEMP_DIR_NAME+printFileName;
		boolean retValue	=	false;
		try
		{
			boolean eof			=	false;
			int 				j  =	0;
			FileOutputStream 	os 	= 	new FileOutputStream(printerPath);
			PrintStream 		ps 	= 	new PrintStream(os,false);
			FileInputStream 	fs 	=	new FileInputStream(fileName);
			BufferedInputStream br	=	new BufferedInputStream(fs);
			java.io.File				f	=	new java.io.File(fileName);
			char msg[]			   =	new char[(int)f.length()+1];
			while(!eof)
			{
				int i=	br.read();
				if(i==-1)
					eof=true;
				else
				{
					msg[j] +=(char)(i);
					cnt.append(msg[j]);
					j++;
				}
		}
			cnt.append("\n");
			ps.print(cnt);
		System.out.println("Contents are "+cnt);
			//ps.print("\f");	//form feed char to go to next page
		ps.close();
			retValue	=	true;			
		}
		catch (Exception e)
		{
			System.out.println("Exception occurred in windowsPrinting() " + e);
			retValue	=	false;
	       createLogFile("barCodeFileGenerator_windowsPrinting",""+e);
	 }
	 return retValue;
	}		

/* @ This file is used for previous barcode printer other than TSC TTP-243E*/
  private String getBarCodeFormatString(String crNo,String pName,String labName,String sampleNo,String sampleName,String formatType)
	{
		System.out.println("In function getBarCodeFormatString sampleNo is --------"+sampleNo);
		String barCodeDataString = "";
		if(formatType.equals("FORMAT_A")){
		barCodeDataString ="	Q203,O19\n"
			+"q831\n"
			+"rN\n"
			+"S2\n"
			+"D2\n"
			+"ZT\n"
			+"JB\n"
			+"OD\n"
			+"R192,0\n"
			+"N\n"
			+"B40,0,0,E30,1,4,130,N,\""+ sampleNo  +"\"\n"
			+"A26,140,0,1,1,1,N,\""+ sampleNo  +"\"\n"
			+"B280,0,0,E30,1,4,130,N,\""+ sampleNo  +"\"\n"
			+"A240,140,0,1,1,1,N,\""+ sampleNo  +"\"\n"
			+"P1";	
		}
		else if(formatType.equals("FORMAT_B"))
		{
			barCodeDataString +="" +  crNo     	+"\n";		
			barCodeDataString +="" +  pName	  	+"\n";
			barCodeDataString +="" +  labName  	+"\n";
			barCodeDataString +="" +  sampleNo 	+"\n";
			barCodeDataString +="\n\n";			
		}
		System.out.println("barCodeDataString={"+barCodeDataString+"}");
	return barCodeDataString;
	}

/*Function created by Shweta Kulshreshtha on 12/May/2010
 * This file is used for  barcode printer TSC TTP-243E
 */
	private String getBarCodeFormatStringForTSCTTP243E(String crNo,String pName,String labName,String sampleNo,String sampleName,String formatType,String labShortName)
	{
		System.out.println("In new function getBarCodeFormatString sampleNo is --------"+sampleNo+"lab name is-->"+labShortName);
		StringBuffer fileContents=new StringBuffer();
        fileContents.append("REFERENCE 0,0" +"\n");
        fileContents.append("DIRECTION 0,0" +"\n");
        fileContents.append("SPEED 3.0"           +"\n");
        fileContents.append("GAP 3 mm,0 mm" +"\n");
        fileContents.append("SIZE 101.6 mm,50.8 mm"     +"\n");
        fileContents.append("SET PEEL OFF"        +"\n");
        fileContents.append("DENSITY 8"           +"\n");
        fileContents.append("OFFSET 0 mm"         +"\n");
        fileContents.append("SHIFT 0"       +"\n");
        fileContents.append("SET CUTTER OFF"            +"\n");
        fileContents.append("SET STRIPER ON"            +"\n");
        fileContents.append("CLS" +"\n");
        fileContents.append("BARCODE 787,371,\"128M\",58,1,180,1,6,\""+ sampleNo.trim()+"\"\n\n");
        fileContents.append("CODEPAGE 850"+ "\n");                        
        fileContents.append("TEXT 787,280,\"1\",180,1,1, \""+ "CR No.: "+"\"\n") ;
        fileContents.append("TEXT 680,280,\"1\",180,1,1, \""+ crNo+ "\"\n");
        fileContents.append("TEXT 787,260,\"1\",180,1,1, \""+ "Name  : "+ "\"\n");
        fileContents.append("TEXT 680,260,\"1\",180,1,1, \""+ pName+ "\"\n");
        fileContents.append("TEXT 787,240,\"1\",180,1,1, \""+ "Lab   : "+"\"\n");
        //fileContents.append("TEXT 680,240,\"1\",180,1,1, \""+ labShortName +"\"\n");
        fileContents.append("TEXT 680,240,\"1\",180,1,1, \""+ labName +"\"\n");
        fileContents.append("TEXT 787,220,\"1\",180,1,1, \""+ "Sample: "+"\"\n");
        fileContents.append("TEXT 680,220,\"1\",180,1,1, \""+ sampleName +"\"\n");
        //fileContents.append("TEXT 787,220,\"2\",180,1,1, \""+ "Sample: "+"\"\n");
        //fileContents.append("TEXT 680,220,\"2\",180,1,1, \""+ sampleName+"\"\n");
        fileContents.append("PRINT 1,1" + "\n\n");
	
		System.out.println("barCodeDataString={"+fileContents.toString()+"}");
	return fileContents.toString();
	} 
	//log file methods
	public static boolean createLogFile(String printFileName,String contents)
	{
		boolean retValue		=	false;
		String	OS_NAME			=	System.getProperties().getProperty("os.name");
		String fileName			=	"";
		String errorDateTime	=	"";
	try
		{
	 	errorDateTime	="";//	this.getSysdate("YYYYMonDD HH12_MI AM_");
		 	if(OS_NAME.startsWith("Linux"))
				fileName		=	"/root/investigation/"+errorDateTime+printFileName+".log";
			else
				fileName 		=	"C:\\investigation\\"+errorDateTime+printFileName+".log";   
		}
		catch( Exception e)
		{
		    System.out.println("Exception "+e);
		}
		try
		{
			FileWriter FR 			=   new FileWriter(fileName,false);
			BufferedWriter OS		=	new BufferedWriter(FR);
			for(int i=0;i<contents.length();i++)
			{
				OS.write(contents.charAt(i));
				OS.flush();
			}
			OS.close();
			FR.close();
			retValue	=	true;
		}
		catch(Exception e)
		{
			System.out.println("Exception in create investigation log File "+e);
			retValue	=	false;
		}
		return retValue; 
	}
	//function created by Nitin Tyagi on 10/dec/2008
public String getStringAccToPrinterType(String OSName,String printerType)
{
	Map printerMap=new HashMap();
	String printString="";
	if(printerType.equals("TLP2284")){
		printString="TLP2844";}
	else if(printerType.equals("TSC TTP-243E")){
		printString="TSC TTP-243E";}
	else if(printerType.equals("BASAWA")){
			printString="Basawa";}
	
	
	printerMap.put(printerType, printString);
	
	
	return printString;
}
//function created by Shweta on 12/May/2010
public void createBarCodeFileForTSCTTP243E(String crno,String patName,String labName,String sampleNo,String sampleName,String printFileName,String formatType,String labShortName)
{	
			String barCodeDataString;
			//get the String content to be written on the text file
			barCodeDataString = getBarCodeFormatStringForTSCTTP243E(crno,patName,labName,sampleNo,sampleName,formatType,labShortName);
			System.out.println("------after executing barCodeDataString function in barcodefilegenerator-----------");//create the file at the specified location
			createTempFile(barCodeDataString,printFileName);
}	
//function create by Rabindra Nath on 26 Ahu 2011 for GGSH BarCode Printer Setup start
public void TSCTTP243E(PrinterVO printerVO)
{	
			String barCodeDataString;
			//get the String content to be written on the text file
			barCodeDataString = getBarCodeFormatStringForTSCTTP243E(printerVO.getPatCRNo(),printerVO.getPatName(),printerVO.getLabName(),printerVO.getSampleNo(),printerVO.getSampleName(),printerVO.getFormatType(),printerVO.getLabShortName());
			System.out.println("------after executing barCodeDataString function in barcodefilegenerator-----------");//create the file at the specified location
			createTempFile(barCodeDataString,printerVO.getPrinterFileName());
}
//function create by Rabindra Nath on 26 Ahu 2011 for GGSH BarCode Printer Setup end
}


