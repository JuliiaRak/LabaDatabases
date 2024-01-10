package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Employee {
    private Long id;
    private Department department;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;

    @XmlElementWrapper(name = "skills")
    @XmlElement(name = "skill")
    private List<Skill> skills;
}