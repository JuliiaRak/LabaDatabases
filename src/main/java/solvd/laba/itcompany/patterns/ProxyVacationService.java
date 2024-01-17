package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Vacation;
import solvd.laba.itcompany.service.VacationService;

public class ProxyVacationService implements VacationService {
    VacationService realVacationService;
    Employee admin;

    public ProxyVacationService(VacationService vacationService, Employee admin){
        this.realVacationService = vacationService;
        this.admin = admin;
    }

    private boolean checkAccess(Vacation vacation) {
        return vacation.getEmployee().equals(admin);
    }

    @Override
    public void create(Vacation vacation) {
        if (checkAccess(vacation)) {
            realVacationService.create(vacation);
        }
    }
}
