package new_investigation.masters.controller.data;



	import hisglobal.vo.UserVO;

	import java.util.Map;

	import new_investigation.masters.InvestigationMasterDelegate;
import new_investigation.vo.FilmMstVO;
import new_investigation.vo.TestNewMasterVO;
import new_investigation.vo.invOrganicMstVO;

	public class invOrganicMstDATA {


		public static void saveOrganism(invOrganicMstVO testNewMasterVO,UserVO _UserVO)
		{
			InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
			 masterDelegate.saveOrganism(testNewMasterVO, _UserVO);
		}

		public static Map fetchOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
		{
			InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
			return masterDelegate.fetchOrganism(testNewMasterVO, _UserVO,namecode);
		}
		
		
		public static Map fetchOrgqanismD(invOrganicMstVO testNewMasterVO, UserVO _UserVO,String namecode)
		{
			InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
			return masterDelegate.fetchOrganism(testNewMasterVO, _UserVO,namecode);
		}
		
		public static void savemodifyOrganism(invOrganicMstVO testNewMasterVO, UserVO _UserVO)
		{
			InvestigationMasterDelegate masterDelegate = new InvestigationMasterDelegate();
			 masterDelegate.savemodifyOrganism(testNewMasterVO, _UserVO);
		}
		
	}

