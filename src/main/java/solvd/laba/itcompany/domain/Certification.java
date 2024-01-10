package solvd.laba.itcompany.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.xml.bind.annotation.*;
import solvd.laba.itcompany.parsers.MyDateAdapter;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Certification {
    private Long id;
    private Employee employee;
    private String certificationName;

    @JsonDeserialize(using = MyDateAdapter.class)
    private Date date;
}
