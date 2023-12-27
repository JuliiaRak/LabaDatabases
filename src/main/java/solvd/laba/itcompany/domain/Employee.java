package solvd.laba.itcompany.domain;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class Employee {
    private Long id;
    private Department department;
    private String firstName;
    private String lastName;
    private String position;
    private BigDecimal salary;
    private List<Skill> skills;
}