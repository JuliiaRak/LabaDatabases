package solvd.laba.itcompany.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import solvd.laba.itcompany.domain.Certification;
import solvd.laba.itcompany.domain.Employee;
import solvd.laba.itcompany.domain.Skill;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SAXCertificationHandler extends DefaultHandler {
    public Certification certification;
    private StringBuilder currentValue;
    private Employee employee;
    private Skill currentSkill;
    private boolean isEmployee;
    private boolean isSkill;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentValue = new StringBuilder();
        if ("certification".equals(qName)) {
            certification = new Certification();
        } else if ("employee".equals(qName)) {
            employee = new Employee();
            isEmployee = true;
        } else if ("skill".equals(qName)) {
            currentSkill = new Skill();
            isSkill = true;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        currentValue.append(ch, start, length);
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if ("certificationName".equals(qName)) {
            certification.setCertificationName(currentValue.toString());
        } else if ("id".equals(qName)) {
            if (isEmployee) {
                employee.setId(Long.parseLong(currentValue.toString()));
            } else {
                certification.setId(Long.parseLong(currentValue.toString()));
            }
        } else if ("date".equals(qName)) {
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currentValue.toString());
                certification.setDate(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else if ("firstName".equals(qName) && isEmployee) {
            employee.setFirstName(currentValue.toString());
        } else if ("lastName".equals(qName) && isEmployee) {
            employee.setLastName(currentValue.toString());
        } else if ("position".equals(qName) && isEmployee) {
            employee.setPosition(currentValue.toString());
        } else if ("salary".equals(qName) && isEmployee) {
            employee.setSalary(new BigDecimal(currentValue.toString()));
        } else if ("skillName".equals(qName) && isSkill) {
            currentSkill.setSkillName(currentValue.toString());
        } else if ("employee".equals(qName)) {
            certification.setEmployee(employee);
            isEmployee = false;
        } else if ("skill".equals(qName)) {
            employee.getSkills().add(currentSkill);
            isSkill = false;
        }
    }
}
