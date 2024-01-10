package solvd.laba.itcompany;

import solvd.laba.itcompany.domain.*;
import solvd.laba.itcompany.service.*;
import solvd.laba.itcompany.service.impl.*;

import java.math.BigDecimal;
import java.util.Date;

public class Main {
    public static void main(String[] args) {

        //department
        DepartmentService departmentService = new DepartmentServiceImpl();
        Department department = new Department();
        department.setDepartmentName("GameDev3");
        department.setId(6L);
//        departmentService.create(department);
//        System.out.println(departmentService.findAll());
//        System.out.println(departmentService.findById(department.getId()));

        //skills
        SkillService skillService = new SkillServiceImpl();
        Skill skill = new Skill();
        skill.setSkillName("Game Developing2");
        skill.setId(4L);
        //skillService.create(skill);

        //employees
        EmployeeService employeeService = new EmployeeServiceImpl();
        Employee employee = new Employee(null, department, "Marta", "Rak", "Java Developer", new BigDecimal(30000), null);
        //employee.setId(4L);
//        employeeService.create(employee);
        System.out.println(employeeService.findById(1L));
        System.out.println(employeeService.findSkillsByEmployeeId(1L));
//        System.out.println(employeeService.findSkillsByEmployeeId(4L));
//        System.out.println(employeeService.findAll());

        //certifications
        CertificationService certificationService = new CertificationServiceImpl();
        Certification certification = new Certification(null, employee, "Java Certification Rak", new Date(38563493));
        certification.setId(3L);
//        certificationService.create(certification);
//        System.out.println(certificationService.findAll());

        //vacations
        VacationService vacationService = new VacationServiceImpl();
        Vacation vacation = new Vacation(null, employee, new Date(999634938), new Date(999634938));
        vacation.setId(3L);
//        vacationService.create(vacation);

        //clients
        ClientService clientService = new ClientServiceImpl();
        Client client = new Client(null, "Starbacks");
        client.setId(3L);
//        clientService.create(client);
//        System.out.println(clientService.findById(client.getId()));

        //contactOfClient
        ContactsOfClientService contactsOfClientService = new ContactsOfClientServiceImpl();
        ContactOfClient contactOfClient = new ContactOfClient(null, client, "06725649962", "369 Khrechatyk St");
        contactOfClient.setId(3L);
//        contactsOfClientService.create(contactOfClient);

        //project
        ProjectService projectService = new ProjectServiceImpl();
        Project project = new Project(null, "Project DDD", client, null);
        project.setId(3L);
//        projectService.create(project);
//        System.out.println(projectService.findAll());
//        System.out.println(projectService.findById(project.getId()));

        //meeting
        MeetingService meetingService = new MeetingServiceImpl();
        Meeting meeting = new Meeting(null, project, new Date(63834904), 30);
        meeting.setId(3L);
//        meetingService.create(meeting);

        //projectFeedback
        ProjectFeedbackService projectFeedbackService = new ProjectFeedbackServiceImpl();
        ProjectFeedback projectFeedback = new ProjectFeedback(null, project, "Good job");
        projectFeedback.setId(4L);
//        projectFeedbackService.create(projectFeedback);
//        System.out.println(projectFeedbackService.findAll());

        //projectEmployees
        ProjectEmployeeService projectEmployeeService = new ProjectEmployeeServiceImpl();
        ProjectEmployee projectEmployee = new ProjectEmployee(project, employee, "Game Developer");
        //projectEmployeeService.create(projectEmployee.xml);

        //service
        ServiceService serviceService = new ServiceServiceImpl();
        Service service = new Service(null, "Development", "Develop game", new BigDecimal(30000));
        service.setId(3L);
//        serviceService.create(service);
//        System.out.println(serviceService.findAll());

        //task
        TaskService taskService = new TaskServiceImpl();
        Task task = new Task(null, project, employee, "Develop a part of the game", "Code and test part of the game", "Created");
        task.setId(3L);
//        taskService.create(task);
    }
}
