package opd.bo;

import hisglobal.vo.UserVO;

public class OpdPatientBOSupport {
		public static String genrateOpdConsultationNewRequestId(String oldRequestId,String sysdate,UserVO _userVO){
			String newRequestId="";
			int caseNo;
			String sequence="";
			int oldRequstIdYY=0;
			int oldRequstIdMM=0;
			String oldRequstIdYYStr="";
			String oldRequstIdMMStr="";
			int oldRequstIdSequence=0;
			String oldRequstIdSequenceStr="";
			String hospitalCode=_userVO.getHospitalCode();
			int nHosCodeSize= hospitalCode.length();
			String sysdateDDRemoved=sysdate.substring(sysdate.indexOf("/")+1);
			int sysdateMM=Integer.parseInt(sysdateDDRemoved.substring(0,sysdateDDRemoved.indexOf("/")));
			int sysdateYY=Integer.parseInt(sysdateDDRemoved.substring(sysdateDDRemoved.indexOf("/")+3,sysdateDDRemoved.indexOf("/")+5));
			String sysdateMMStr=Integer.toString(sysdateMM);
			String sysdateYYStr=Integer.toString(sysdateYY);
			
			if(!oldRequestId.equals("0")){
			//String hospitalCode=oldRequestId.substring(0,3);
			oldRequstIdYY=Integer.parseInt(oldRequestId.substring(nHosCodeSize,nHosCodeSize+2));
			oldRequstIdMM=Integer.parseInt(oldRequestId.substring(nHosCodeSize+2,nHosCodeSize+4));
			oldRequstIdSequence=Integer.parseInt(oldRequestId.substring(nHosCodeSize+4,nHosCodeSize+7));
			oldRequstIdSequence=oldRequstIdSequence+1;
			oldRequstIdSequenceStr=Integer.toString(oldRequstIdSequence);
			oldRequstIdYYStr=Integer.toString(oldRequstIdYY);
			oldRequstIdMMStr=Integer.toString(oldRequstIdMM);
			}
			if(sysdateMMStr.length()<2){
				sysdateMMStr="0"+sysdateMMStr;
			}
			
			if(sysdateYYStr.length()<2){
				sysdateYYStr="0"+sysdateYYStr;
			}
			
			
			
			if(!oldRequestId.equals("0")){
				if(oldRequstIdSequenceStr.length()<2){
					oldRequstIdSequenceStr="00"+oldRequstIdSequenceStr;
				}
				
				if(oldRequstIdSequenceStr.length()==2 && oldRequstIdSequenceStr.length()<3){
					oldRequstIdSequenceStr="0"+oldRequstIdSequenceStr;
				}	
			if(oldRequstIdYYStr.length()<2){
				oldRequstIdYYStr="0"+oldRequstIdYYStr;
			}
			if(oldRequstIdMMStr.length()<2){
				oldRequstIdMMStr="0"+oldRequstIdMMStr;
			}
			if(oldRequstIdYY!=sysdateYY){
				caseNo=1;
			}
			else{
				if(oldRequstIdMM!=sysdateMM){
					caseNo=2;
				}
				else{
					caseNo=3;
				}
			}
			}
			else{
				caseNo=0;
				sequence="001";
			}
			
			switch(caseNo){
			case 0:	newRequestId=hospitalCode+sysdateYYStr+sysdateMMStr+sequence;break;
			case 1: newRequestId=hospitalCode+sysdateYYStr+sysdateMMStr+oldRequstIdSequenceStr; break;
			case 2: newRequestId=hospitalCode+oldRequstIdYYStr+sysdateMMStr+oldRequstIdSequenceStr; break;
			case 3: newRequestId=hospitalCode+oldRequstIdYYStr+oldRequstIdMMStr+oldRequstIdSequenceStr; break;
			}
			
			return newRequestId;
		}
}
