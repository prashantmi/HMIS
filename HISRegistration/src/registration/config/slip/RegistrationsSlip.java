package registration.config.slip;

import java.io.File;

import hisglobal.utility.HisPrinterSupport;


import registration.config.slip.RegistrationSlip;

public abstract class RegistrationsSlip {
	public String tmpFileName;
	public abstract RegistrationSlip preapareSlip();
	/*public  void printRegistrationlabel(RegistrationSlip _rSlip) {
		 
		 try{
				StringBuffer fileContents	=	new StringBuffer();
				fileContents.append("\n\n\n  Hospital Name \n\n");
				fileContents.append("\n\n");				
				fileContents.append("CR No. :: "+_rSlip.getPatCrNo()+"\t\t Registration Date :: "+_rSlip.getEpisodeDate());
				fileContents.append("\n");
				//fileContents.append(_episodeVO.getgetPatCrNo()+"\t\t"+_episodeVO.getgetAgetEntryDate());
				fileContents.append("Name ::  "+_rSlip.getPatFirstName()+" "+_rSlip.getPatLastName()+"\t\t Category :: "+_rSlip.getPatPrimaryCat());
				fileContents.append("\n");
				fileContents.append("Dept/Unit      Room No.     Queue No.");
				fileContents.append("\n");
				for(int i=0;i<_rSlip.getDepartmentToBeVisited().length;i++){
					fileContents.append(_rSlip.getDepartmentToBeVisited()[i]+"/"+_rSlip.getDepartmentUnit()[i]);
					fileContents.append("      "+ _rSlip.getRoom()[i]);			
					fileContents.append("      "+_rSlip.getQueNo()[i]);
					fileContents.append("\n");
				}
				
				HisPrinterSupport.createTempFile(fileContents.toString(), tmpFileName);				
				//for controlling the printing of slip
				HisPrinterSupport.printSlip(_rSlip.getHostName(),tmpFileName+".dat");
				System.out.println("createSlip() Exited");				
			}
			catch(Exception e){
				System.out.println("Exception in createSlip "+e);	
			}
		}*/

	public abstract void genrateSlip();
	
}

