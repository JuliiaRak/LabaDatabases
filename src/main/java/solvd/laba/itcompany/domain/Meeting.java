package solvd.laba.itcompany.domain;

import lombok.Data;
import java.util.Date;

@Data
public class Meeting {
    private Long id;
    private Project project;
    private Date meetingDate;
    private int duration;
}
