package solvd.laba.itcompany.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Certification {
    private Long id;
    private Employee employee;
    private String certificationName;
    private Date date;
}
