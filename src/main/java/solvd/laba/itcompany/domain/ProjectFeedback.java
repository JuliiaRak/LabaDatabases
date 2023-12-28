package solvd.laba.itcompany.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectFeedback {
    private Long id;
    private Project project;
    private String feedbackText;
}
