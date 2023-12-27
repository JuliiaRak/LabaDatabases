package solvd.laba.itcompany.domain;

import lombok.Data;

@Data
public class ProjectFeedback {
    private Long id;
    private Project project;
    private String feedbackText;
}
