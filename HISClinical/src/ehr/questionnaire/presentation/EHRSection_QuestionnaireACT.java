/**
##		Date					: 26-08-2019
##		Reason	(CR/PRS)		: SINGLE PAGE PRESCRIPTION NEW PROCESS 
##		Created By				: Vasu
*/


package ehr.questionnaire.presentation;

import hisglobal.presentation.CSRFGardTokenAction;
import hisglobal.utility.generictemplate.GenericTemplateConfig;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;







import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;




import ehr.examination.presentation.EHRSection_ExaminationFB;
import ehr.examination.presentation.EHRSection_ExaminationUTL;
import ehr.patientTile.dataentry.EHRSection_PatientTileFB;

public class EHRSection_QuestionnaireACT extends CSRFGardTokenAction
{
		
		public ActionForward PATCLINICALDOC_QUESTIONNAIRE_SELECT(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
		{
			EHRSection_QuestionnaireFB fb = (EHRSection_QuestionnaireFB) form;
			EHRSection_QuestionnaireUTL.getTemplateTileEssentials(fb, request);
			return mapping.findForward("NEW");
		}
		
		public ActionForward ENC_QUESTIONNAIRE_AJAX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
		{

			EHRSection_QuestionnaireFB fb = (EHRSection_QuestionnaireFB) form;
			EHRSection_QuestionnaireUTL.getEssentialDataToPopulate(fb, request,response);
			return null;
		}
}