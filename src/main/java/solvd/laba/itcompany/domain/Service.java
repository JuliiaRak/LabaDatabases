package solvd.laba.itcompany.domain;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class Service {
    private Long id;
    private String serviceName;
    private String description;
    private BigDecimal cost;
}
