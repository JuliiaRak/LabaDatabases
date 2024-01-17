package solvd.laba.itcompany.patterns;

import solvd.laba.itcompany.domain.*;
import solvd.laba.itcompany.parsers.*;
import solvd.laba.itcompany.patterns.decorator.Notificator;
import solvd.laba.itcompany.persistence.CertificationRepository;
import solvd.laba.itcompany.persistence.SkillRepository;
import solvd.laba.itcompany.service.impl.VacationServiceImpl;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class PatternsMain {
    public static void main(String[] args) {
        //factory
        CertificationRepositoryFactory certificationRepositoryFactory = new CertificationRepositoryFactory();
        CertificationRepository certificationRepository = certificationRepositoryFactory.getCertificationRepository("JDBC");


        //abstract factory
        AbstractFactory skillRepositoryFactory = FactoryGenerator.getFactory("Skill");
        SkillRepository skillRepository = skillRepositoryFactory.getSkillRepository("MyBatis");


        //Builder
        Skill skill = new Skill(1L, "Java");
        Employee employee = new Employee.Builder()
                .id(1L)
                .department(new Department(1L, "Development"))
                .firstName("John")
                .lastName("Doe")
                .position("Developer")
                .salary(new BigDecimal("50000"))
                .skills(new ArrayList<>(Arrays.asList(skill)))
                .build();


        //Listener (observer)
        Employee employee1 = new Employee.Builder()
                .firstName("Julia").lastName("Rak").build();
        Employee employee2 = new Employee.Builder()
                .firstName("Olena").lastName("Rak").build();

        Meeting meeting = new Meeting();
        meeting.setProject(new Project(1l, "Project 1", null, null));

        meeting.attach(employee1);
        meeting.attach(employee2);

        meeting.setMeetingDate(new Date());


        //Facade
        File file = new File("src/main/resources/xml/certification.xml");
        Object xmlFileLikeObject = XmlConverter.convertXml(file, "SAX");

        //Decorator
        Notificator.notify("Notification that should be written only to log ",
                true, true);

        //Proxy (замісник)
        ProxyVacationService proxyVacationService =
                new ProxyVacationService(new VacationServiceImpl(), employee);
        proxyVacationService.create(new Vacation(1L, employee, new Date(), new Date()));


        //Strategy
        Context context = new Context();
        String how = "DOM";

        if (how.equalsIgnoreCase("SAX")) {
            context.setConverter(new SAXParser());
        } else if (how.equalsIgnoreCase("DOM")) {
            context.setConverter(new DOMParser());
        } else if (how.equalsIgnoreCase("JAXB")) {
            context.setConverter(new JAXBParser());
        }

        File xmlFile = new File("src/main/resources/xml/projectEmployee.xml");
        Object xmlObject = context.convertXml(file);


        //SOLID
        /*Single Responsibility
        Open/Closed
        Liskov Substitution
        Interface Segregation
        Dependency Inversion*/
    }
}
