package in.cdac.rajashthan.services;

import in.cdac.invWebServices.service.InvService;
import in.cdac.mhealth.opdRoster.service.ConsultantEnquiryService;
import in.cdac.mhealth.opdRoster.service.OPDDepartmentService;
import in.cdac.mhealth.opdRoster.service.OPDRosterService;
import in.cdac.mhealth.service.LoginService;
import in.cdac.mhealth.services.DepartmentService;
import in.cdac.mhealth.services.GeneralService;
import in.cdac.mhealth.services.ProvisionalRegistration;
import in.cdac.mhealth.services.TariffService;
import in.cdac.rajashthan.services.DailyPatientList;
import in.cdac.rajashthan.services.DailyTransactionsCount;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Path;
import javax.ws.rs.core.Application;

import HisWeb.webservice.CheckSumGenration;
import HisWeb.webservice.IssuetoPatBilling;
import HisWeb.webservice.UserService;
import HisWeb.webservice.opdDrDeskService;

@Path(value="/rest")
public class MyApplication
extends Application {
    public Set<Class<?>> getClasses() {
        HashSet s = new HashSet();
        s.add(DailyTransactionsCount.class);
        s.add(DailyPatientList.class);
        s.add(DemographicOnCR.class);
        s.add(ImageRetrievalUtil.class);
        s.add(DepartmentService.class);
        s.add(GeneralService.class);
        s.add(ProvisionalRegistration.class);
        s.add(UserService.class);
        s.add(CheckSumGenration.class);
        s.add(IssuetoPatList.class);
        s.add(LoginService.class);
        s.add(OPDDepartmentService.class);
        s.add(OPDRosterService.class);
        s.add(ConsultantEnquiryService.class);
        s.add(TariffService.class);
        
        s.add(opdDrDeskService.class);
        s.add(InvService.class);
        s.add(IssuetoPatBilling.class);
        
        return s;
    }
}