package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.xml.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import solvd.laba.itcompany.patterns.Observer;
import solvd.laba.itcompany.patterns.decorator.Notificator;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee implements Observer {

    private Long id;
    private Department department;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private List<Skill> skills;

    @Override
    public void notify(Meeting meeting) {
        Notificator.notify("Notification for " +
                firstName + " " + lastName +
                ": changed date of the meeting on project "
                + meeting.getProject().getProjectName() +
                " to " + meeting.getMeetingDate(),
                true, true);
    }

    public static class Builder {
        private Long id;
        private Department department;
        private String firstName;
        private String lastName;
        private String position;
        private BigDecimal salary;
        private List<Skill> skills;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder department(Department department) {
            this.department = department;
            return this;
        }

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder position(String position) {
            this.position = position;
            return this;
        }

        public Builder salary(BigDecimal salary) {
            this.salary = salary;
            return this;
        }

        public Builder skills(List<Skill> skills) {
            this.skills = skills;
            return this;
        }

        public Employee build() {
            Employee employee = new Employee();
            employee.id = this.id;
            employee.department = this.department;
            employee.firstName = this.firstName;
            employee.lastName = this.lastName;
            employee.position = this.position;
            employee.salary = this.salary;
            employee.skills = this.skills;
            return employee;
        }
    }
}