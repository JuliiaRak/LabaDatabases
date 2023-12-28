package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEmployee {
    private Project project;
    private Employee employee;
    private String role;
}
