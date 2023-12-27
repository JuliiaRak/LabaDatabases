package solvd.laba.itcompany.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Vacation {
    private Long id;
    private Employee employee;
    private Date startDate;
    private Date endDate;
}
