package mongoapp;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import DataHelper.PGDataHelper;
import DataHelper.PGDataHelper_kpran;
import DataHelper.PropertiesHelper;
import DataProcessing.PGPDFProcessing;
import DataProcessing.PGTemplateProcessing;

public class FetchBillData implements Job {

	public  static boolean jobProcessContinue = true;
	public  static String ServciceId=""; 
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		// TODO Auto-generated method stub
		
		
		System.out.println("BEEP BILL ");
     
		
		   try
            	{
			   Map<Integer,String> mp=PGDataHelper.getInstance().getBilldetailsdata("");
         
                        if(mp!=null && mp.size()>0)
                        {
                        	
                        	Iterator itrLab=mp.keySet().iterator();
                        	
                        	 while(itrLab.hasNext())
							  {
								  int keyy=(Integer)itrLab.next();
								 String data=mp.get(keyy);
								 
								 String[] dataval=data.split("\\*\\*\\*");
							
								 
								 String hospitalcode=dataval[0];
								 String billno=dataval[1];
								 String billdate=dataval[2];
								 
								 String reqno="";
								 
								 if(dataval[3].contains("!"))
								 {
									String[] reqno1=dataval[3].split("\\!");
									
									for(int k=0;k<reqno1.length;k++)
									{
										
											  reqno=reqno1[k];
										
											  reqno=reqno.split("\\|")[0].replace("%", "");

											 
											 String testorgrpcode=dataval[4];

											 
											 String istestexist=PGDataHelper.getInstance().istestexist(reqno,testorgrpcode,hospitalcode);
											 
											        if(istestexist.equals("0")) //group
											        {
														 String isgroupexist=PGDataHelper.getInstance().isgroupexist(reqno,testorgrpcode,hospitalcode);

														 String datavall="";
														 
														 if(!isgroupexist.equals("0"))
														  datavall=PGDataHelper.getInstance().getpatdetailsonbasisofbilldetailsgroup(data,reqno);
														 
														 if(!datavall.equals(""))
														{
															// System.out.println("value to be inserted in testtable ::::"+datavall);
															
															 
															 
												       
															 boolean newlfag=false;

															 if(PropertiesHelper.get_IS_BILLDATA_MOVE_SAMETBL())
															 {
																 
																 PGDataHelper.getInstance().deleteduplicaterequisition(datavall.split("\\*\\*\\*")[11]);	 

																 
																 boolean flg= PGDataHelper.getInstance().inserttest(datavall,billno,billdate,"1");
															 

															 if(flg==true)
																  newlfag=true;
															 }
															 
															 if(PropertiesHelper.get_IS_BILLDATA_MOVE_KOPRANTBL())
															 {
																 
																 PGDataHelper_kpran.getInstance().deleteduplicaterequisition_KPRAN(datavall.split("\\*\\*\\*")[11]);	 
																 
														 boolean flg_kpran=  PGDataHelper_kpran.getInstance().inserttest_kpran(datavall,billno,billdate,"1");
														 
												       if(flg_kpran==true)
															  newlfag=true;
															 }
															 
															 
													    if(newlfag==true)
														 PGDataHelper.getInstance().updatebilltableaftersave(dataval[3],testorgrpcode);
														System.out.println("Succesfully done bill data move in our tbl");
														
														 PGDataHelper.getInstance().updatebillnoindtltbl(datavall.split("\\*\\*\\*")[11],billno);
														
														}
														}
											        else  //test
											        {
											        	
														 String istestexistinInv=PGDataHelper.getInstance().istestexistintestmst(reqno,testorgrpcode,hospitalcode);

														 if(!istestexistinInv.equals("0"))
														 {
															 String datavall=PGDataHelper.getInstance().getpatdetailsonbasisofbilldetails(data,reqno);
														 

														 boolean newlfag=false;
														 
														
														 if(PropertiesHelper.get_IS_BILLDATA_MOVE_SAMETBL())
														 {
															 
															 PGDataHelper.getInstance().deleteduplicaterequisition(datavall.split("\\*\\*\\*")[11]);	 

														 boolean flg=  PGDataHelper.getInstance().inserttest(datavall,billno,billdate,"0");
														 
														 if(flg==true)
														  newlfag=true;
														 }
														 
														 
														 if(PropertiesHelper.get_IS_BILLDATA_MOVE_KOPRANTBL())
														 {
														
															 PGDataHelper_kpran.getInstance().deleteduplicaterequisition_KPRAN(datavall.split("\\*\\*\\*")[11]);	 

															 boolean flg_kpran=  PGDataHelper_kpran.getInstance().inserttest_kpran(datavall,billno,billdate,"0");
														 
                                                         if(flg_kpran==true)
														  newlfag=true;
														
														 }
														 
														 if(newlfag==true)
														 PGDataHelper.getInstance().updatebilltableaftersave(dataval[3],testorgrpcode);
														  
														 System.out.println("Succesfully done bill data move in our tbl");
											             
														 PGDataHelper.getInstance().updatebillnoindtltbl(datavall.split("\\*\\*\\*")[11],billno);

														 
														 }
													}
										
									}
									
								 }
								 else
								 {
								 if(dataval[3].contains("|"))
								  reqno=dataval[3].split("\\|")[0];
							
								
								 
								 String testorgrpcode=dataval[4];

								 
								 String istestexist=PGDataHelper.getInstance().istestexist(reqno,testorgrpcode,hospitalcode);
								 
								        if(istestexist.equals("0")) //group
								        {
											 String isgroupexist=PGDataHelper.getInstance().isgroupexist(reqno,testorgrpcode,hospitalcode);

											 String datavall="";
											 
											 if(!isgroupexist.equals("0"))
											  datavall=PGDataHelper.getInstance().getpatdetailsonbasisofbilldetailsgroup(data,reqno);
											 boolean newlfag=false;

											 if(PropertiesHelper.get_IS_BILLDATA_MOVE_SAMETBL())
											 {
											 
										     boolean flg= PGDataHelper.getInstance().inserttest(datavall,billno,billdate,"1");
											 
											 if(flg==true)
												  newlfag=true;
											 }
											 
											 if(PropertiesHelper.get_IS_BILLDATA_MOVE_KOPRANTBL())
											 {
												 
											   boolean flg_kpran=  PGDataHelper_kpran.getInstance().inserttest_kpran(datavall,billno,billdate,"1");
												
											   if(flg_kpran==true)
													  newlfag=true;
											 
											 }
											 
											 
											if(newlfag==true)
											 PGDataHelper.getInstance().updatebilltableaftersave(dataval[3],testorgrpcode);
											System.out.println("Succesfully done bill data move in our tbl");
											
											 PGDataHelper.getInstance().updatebillnoindtltbl(datavall.split("\\*\\*\\*")[11],billno);

								        }
								        else //test
								        {
								        	
								        	String istestexistinInv=PGDataHelper.getInstance().istestexistintestmst(reqno,testorgrpcode,hospitalcode);

											 if(!istestexistinInv.equals("0"))
											 {
											 String datavall=PGDataHelper.getInstance().getpatdetailsonbasisofbilldetails(data,reqno);
											 
											 boolean newlfag=false;
											 
											 if(PropertiesHelper.get_IS_BILLDATA_MOVE_SAMETBL())
											 {
											 boolean flg=  PGDataHelper.getInstance().inserttest(datavall,billno,billdate,"0");
											 
											 if(flg==true)
												  newlfag=true;
											 }
											 
											 
											 if(PropertiesHelper.get_IS_BILLDATA_MOVE_KOPRANTBL())
											 {
											   boolean flg_kpran=  PGDataHelper_kpran.getInstance().inserttest_kpran(datavall,billno,billdate,"0");
											
											   if(flg_kpran==true)
													  newlfag=true;
											 }
											 
											 
											   
											 if(newlfag==true)
											 PGDataHelper.getInstance().updatebilltableaftersave(dataval[3],testorgrpcode);
											  
											 System.out.println("Succesfully done bill data move in our tbl");
											 
											 PGDataHelper.getInstance().updatebillnoindtltbl(datavall.split("\\*\\*\\*")[11],billno);

											 
								              }     
										}   
								        	
								 
								 
								 String hospitalcode1=dataval[0];
								 }	 
							  }
                        	
                        }
            
            	}
		   catch(Exception e)
		   {
			   e.printStackTrace();
		   }

        // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
		

	

}
