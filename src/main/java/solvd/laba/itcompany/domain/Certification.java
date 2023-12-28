package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Certification {
    private Long id;
    private Employee employee;
    private String certificationName;
    private Date date;
}
