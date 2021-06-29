package mrd.reports.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

public class MrdCommonReportACTION extends DispatchAction
{

	public ActionForward unspecified(ActionMapping mapping,ActionForm form,HttpServletRequest request,HttpServletResponse response){
		
		return mapping.findForward("NEW");
	}
	
	public ActionForward SPECIALITYWISEOUTDOORPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYWISEOUTDOORPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYWISEOUTDOORPATIENTS");
		}
	}
	
	public ActionForward SPECIALITYWISEINDOORPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYWISEINDOORPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYWISEINDOORPATIENTS");
		}
	}
	
	
	public ActionForward REGISTEREDMLCPATIENTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("REGISTEREDMLCPATIENTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("REGISTEREDMLCPATIENTREPORT");
		}
	}

	
	public ActionForward BIRTHDETAILSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("BIRTHDETAILSREPORTVIEW");
		}
		else
		{
			return mapping.findForward("BIRTHDETAILSREPORT");
		}
	}

	
	public ActionForward DEATHDETAILSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DEATHDETAILSREPORTVIEW");
		}
		else
		{
			return mapping.findForward("DEATHDETAILSREPORT");
		}
	}

	public ActionForward TWENTYPOINTREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("TWENTYPOINTREPORTVIEW");
		}
		else
		{
			return mapping.findForward("TWENTYPOINTREPORT");
		}
	}

	
	
	public ActionForward DISEASECODEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DISEASECODEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("DISEASECODEREPORT");
		}
	}

	
	public ActionForward PATIENTNOTDISCHARGEDREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("PATIENTNOTDISCHARGEDREPORTVIEW");
		}
		else
		{
			return mapping.findForward("PATIENTNOTDISCHARGEDREPORT");
		}
	}
	public ActionForward LICREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("LICREPORTVIEW");
		}
		else
		{
			return mapping.findForward("LICREPORT");
		}
	}


	public ActionForward SPECIALITYUNITWISEOPDPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYUNITWISEOPDPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYUNITWISEOPDPATIENTS");
		}
	}

	public ActionForward SPECIALITYGENDERWISEOPDPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYGENDERWISEOPDPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYGENDERWISEOPDPATIENTS");
		}
	}

	
	public ActionForward SPECIALITYWISESUNDAYCLINICPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYWISESUNDAYCLINICPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYWISESUNDAYCLINICPATIENTS");
		}
	}

	public ActionForward SPECIALITYUNITWISESPLCLINICOPDPAT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYUNITWISESPLCLINICOPDPATVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYUNITWISESPLCLINICOPDPAT");
		}
	}

	
	public ActionForward SPECIALITYWISEOPERATION(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYWISEOPERATIONVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYWISEOPERATION");
		}
	}
	
	public ActionForward SPECIALITYWISEINVESTIGATION(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SPECIALITYWISEINVESTIGATIONVIEW");
		}
		else
		{
			return mapping.findForward("SPECIALITYWISEINVESTIGATION");
		}
	}
	
	public ActionForward GENDERWISEOUTDOORPATIENTS(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("GENDERWISEOUTDOORPATIENTSVIEW");
		}
		else
		{
			return mapping.findForward("GENDERWISEOUTDOORPATIENTS");
		}
	}
	
	public ActionForward AGESEXRELIGIONHOSREGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("AGESEXRELIGIONHOSREGREPORTVIEW");
		}
		else
		{
			return mapping.findForward("AGESEXRELIGIONHOSREGREPORT");
		}
	}

	
	public ActionForward AGESEXRELIGIONDEPTREGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("AGESEXRELIGIONDEPTREGREPORTVIEW");
		}
		else
		{
			return mapping.findForward("AGESEXRELIGIONDEPTREGREPORT");
		}
	}

	
	public ActionForward COMMUNICABLEDISREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("COMMUNICABLEDISREPORTVIEW");
		}
		else
		{
			return mapping.findForward("COMMUNICABLEDISREPORT");
		}
	}
	public ActionForward NONCOMMUNICABLEDISREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("NONCOMMUNICABLEDISREPORTVIEW");
		}
		else
		{
			return mapping.findForward("NONCOMMUNICABLEDISREPORT");
		}
	}
	public ActionForward INDOORPATIENTSBETWEENTWODATESREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("INDOORPATIENTSBETWEENTWODATESREPORTVIEW");
		}
		else
		{
			return mapping.findForward("INDOORPATIENTSBETWEENTWODATESREPORT");
		}
	}
	
	public ActionForward SEXRATIOREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("SEXRATIOREPORTVIEW");
		}
		else
		{
			return mapping.findForward("SEXRATIOREPORT");
		}
	}
	
	public ActionForward PEDIATRICSIMMUNIZATIONSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("PEDIATRICSIMMUNIZATIONSREPORTVIEW");
		}
		else
		{
			return mapping.findForward("PEDIATRICSIMMUNIZATIONSREPORT");
		}
	}
	


	public ActionForward DELIVERIESANDCUTINSERTIONREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DELIVERIESANDCUTINSERTIONREPORTVIEW");
		}
		else
		{
			return mapping.findForward("DELIVERIESANDCUTINSERTIONREPORT");
		}
	}
	
	public ActionForward HOSPITALPERFORMANCEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("HOSPITALPERFORMANCEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("HOSPITALPERFORMANCEREPORT");
		}
	}

	public ActionForward FORMPREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("FORMPREPORTVIEW");
		}
		else
		{
			return mapping.findForward("FORMPREPORT");
		}
	}

	public ActionForward AGESEXRELIGIONHOSPREGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("AGESEXRELIGIONHOSPREGREPORTVIEW");
		}
		else
		{
			return mapping.findForward("AGESEXRELIGIONHOSPREGREPORT");
		}
	}
		
	public ActionForward CANCEL(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
		return mapping.findForward("CANCEL");
	}
	public ActionForward DEPTWISEMONTHLYABSTRACTOFPATIENT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("DEPTWISEMONTHLYABSTRACTOFPATIENTVIEW");
		}
		else
		{
			return mapping.findForward("DEPTWISEMONTHLYABSTRACTOFPATIENT");
		}
	}
	public ActionForward FLUOROSISERADICATIONPROGRAMMEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("FLUOROSISERADICATIONPROGRAMMEREPORTVIEW");
		}
		else
		{
			return mapping.findForward("FLUOROSISERADICATIONPROGRAMMEREPORT");
		}
	}
	
	public ActionForward REGISTRATIONCENSUSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("REGISTRATIONCENSUSREPORTVIEW");
		}
		else
		{
			return mapping.findForward("REGISTRATIONCENSUSREPORT");
		}
	}
	public ActionForward REGISTRATIONPATIENTLISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))) 
		{
			return mapping.findForward("REGISTRATIONPATIENTLISTINGREPORTVIEW");
		}
		else
		{
			return mapping.findForward("REGISTRATIONPATIENTLISTINGREPORT");
		}
	}
	
	public ActionForward OPDSTATICREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("OPDSTATICREPORTVIEW");
			}else{
				return mapping.findForward("OPDSTATICREPORT");
			}
	}
	
	public ActionForward ADMISSIONANDDISCHARGESTATICREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("ADMISSIONANDDISCHARGESTATICREPORTVIEW");
			}else{
				return mapping.findForward("ADMISSIONANDDISCHARGESTATICREPORT");
			}
	}
	
	public ActionForward ADMISSIONANDDISCHARGELISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("ADMISSIONANDDISCHARGELISTINGREPORTVIEW");
			}else{
				return mapping.findForward("ADMISSIONANDDISCHARGELISTINGREPORT");
			}
	}
	
	
	public ActionForward DISEASEWISEPATIENTSTATICREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("DISEASEWISEPATIENTSTATICREPORTVIEW");
			}else{
				return mapping.findForward("DISEASEWISEPATIENTSTATICREPORT");
			}
	}
	
	public ActionForward DISEASEWISEPATIENTLISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("DISEASEWISEPATIENTLISTINGREPORTVIEW");
			}else{
				return mapping.findForward("DISEASEWISEPATIENTLISTINGREPORT");
			}
	}
	
	public ActionForward DEATHPATIENTLISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("DEATHPATIENTLISTINGREPORTVIEW");
			}else{
				return mapping.findForward("DEATHPATIENTLISTINGREPORT");
			}
	}
	
	public ActionForward MLCPATIENTLISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("MLCPATIENTLISTINGREPORTVIEW");
			}else{
				return mapping.findForward("MLCPATIENTLISTINGREPORT");
			}
	}
	
	
	public ActionForward BROUGHTDEATHPATIENTLISTINGREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("BROUGHTDEATHPATIENTLISTINGREPORTVIEW");
			}else{
				return mapping.findForward("BROUGHTDEATHPATIENTLISTINGREPORT");
			}
	}
	
	public ActionForward WARDCENSUSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("WARDCENSUSREPORTVIEW");
			}else{
				return mapping.findForward("WARDCENSUSREPORT");
			}
	}
	public ActionForward OPERATIONCENSUSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("OPERATIONCENSUSREPORTVIEW");
			}else{
				return mapping.findForward("OPERATIONCENSUSREPORT");
			}
	}
	
	public ActionForward ISSUEANDRECEIPTOFCASEPAPERINTERFACEREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("ISSUEANDRECEIPTOFCASEPAPERINTERFACEREPORTVIEW");
			}else{
				return mapping.findForward("ISSUEANDRECEIPTOFCASEPAPERINTERFACEREPORT");
			}
	}
	
	public ActionForward LABCENSUSREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("LABCENSUSREPORTVIEW");
			}else{
				return mapping.findForward("LABCENSUSREPORT");
			}
	}
	
	public ActionForward MEDICALCAMPREPORT(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{
			String reportMode = request.getParameter("reportMode");
			if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT") || reportMode.equalsIgnoreCase("GRAPH"))){
				return mapping.findForward("MEDICALCAMPREPORTVIEW");
			}else{
				return mapping.findForward("MEDICALCAMPREPORT");
			}
	}
	
	
	//Added by Prachi on 5-8-19 for bulletin Generation //
	
	public ActionForward BULLETINGENERATION(ActionMapping mapping,ActionForm form,
			HttpServletRequest request, HttpServletResponse response)throws Exception 
	{

			String reportMode = request.getParameter("reportMode");
			
		if (reportMode != null && (reportMode.equalsIgnoreCase("TEXT"))) 
		{
			return mapping.findForward("BULLETINGENERATIONVIEW");
		}
		else
		{
			return mapping.findForward("BULLETINGENERATIONVIEW");
		}
	}

}
