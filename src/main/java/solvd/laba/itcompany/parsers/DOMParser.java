package solvd.laba.itcompany.parsers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import solvd.laba.itcompany.domain.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DOMParser {
    private static final Logger LOGGER = LogManager.getLogger(DOMParser.class);

    public static void main(String[] args) {
        //LOGGER.info("PROGRAM STARTED");

        try {
            File xmlFile = new File("src/main/resources/xml/projectEmployee.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);

            doc.getDocumentElement().normalize();

            Element projectEmployeeElement = doc.getDocumentElement();
            ProjectEmployee projectEmployeeXML = createProjectEmployee(projectEmployeeElement);

            LOGGER.info(projectEmployeeXML);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static ProjectEmployee createProjectEmployee(Element projectEmployeeElement) {
        ProjectEmployee projectEmployee = new ProjectEmployee();

        Element projectElement = (Element) projectEmployeeElement.getElementsByTagName("project").item(0);
        Project project = createProject(projectElement);
        projectEmployee.setProject(project);

        Element employeeElement = (Element) projectEmployeeElement.getElementsByTagName("employee").item(0);
        Employee employee = createEmployee(employeeElement);
        projectEmployee.setEmployee(employee);

        projectEmployee.setRole(getTagValue("role", projectEmployeeElement));

        return projectEmployee;
    }

    private static Project createProject(Element projectElement) {
        Project project = new Project();

        project.setId(Long.parseLong(getTagValue("id", projectElement)));
        project.setProjectName(getTagValue("projectName", projectElement));

        Element clientElement = (Element) projectElement.getElementsByTagName("client").item(0);
        Client client = createClient(clientElement);
        project.setClient(client);

        NodeList serviceNodeList = projectElement.getElementsByTagName("service");
        List<Service> services = new ArrayList<>();
        for (int i = 0; i < serviceNodeList.getLength(); i++) {
            Node serviceNode = serviceNodeList.item(i);
            if (serviceNode.getNodeType() == Node.ELEMENT_NODE) {
                Element serviceElement = (Element) serviceNode;
                Service service = createService(serviceElement);
                services.add(service);
            }
        }
        project.setServices(services);
        return project;
    }

    private static Client createClient(Element clientElement) {
        Client client = new Client();
        client.setId(Long.parseLong(getTagValue("id", clientElement)));
        client.setClientName(getTagValue("clientName", clientElement));
        return client;
    }

    private static Service createService(Element serviceElement) {
        Service service = new Service();
        service.setId(Long.parseLong(getTagValue("id", serviceElement)));
        service.setServiceName(getTagValue("serviceName", serviceElement));
        service.setDescription(getTagValue("description", serviceElement));
        service.setCost(new BigDecimal(getTagValue("cost", serviceElement)));
        return service;
    }

    private static Employee createEmployee(Element employeeElement) {
        Employee employee = new Employee();
        employee.setId(Long.parseLong(getTagValue("id", employeeElement)));
        Element departmentElement = (Element) employeeElement.getElementsByTagName("department").item(0);
        Department department = createDepartment(departmentElement);
        employee.setDepartment(department);

        employee.setFirstName(getTagValue("firstName", employeeElement));
        employee.setLastName(getTagValue("lastName", employeeElement));
        employee.setPosition(getTagValue("position", employeeElement));
        employee.setSalary(new BigDecimal(getTagValue("salary", employeeElement)));

        NodeList skillList = employeeElement.getElementsByTagName("skill");
        List<Skill> skills = new ArrayList<>();
        for (int i = 0; i < skillList.getLength(); i++) {
            Node skillNode = skillList.item(i);
            if (skillNode.getNodeType() == Node.ELEMENT_NODE) {
                Element skillElement = (Element) skillNode;
                Skill skill = createSkill(skillElement);
                skills.add(skill);
            }
        }
        employee.setSkills(skills);
        return employee;
    }

    private static Department createDepartment(Element departmentElement) {
        Department department = new Department();
        department.setId(Long.parseLong(getTagValue("id", departmentElement)));
        department.setDepartmentName(getTagValue("departmentName", departmentElement));
        return department;
    }

    private static Skill createSkill(Element skillElement) {
        Skill skill = new Skill();
        skill.setId(Long.parseLong(getTagValue("id", skillElement)));
        skill.setSkillName(getTagValue("skillName", skillElement));
        return skill;
    }

    private static String getTagValue(String tag, Element element) {
        NodeList nodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
        Node node = nodeList.item(0);
        return node.getNodeValue();
    }
}
