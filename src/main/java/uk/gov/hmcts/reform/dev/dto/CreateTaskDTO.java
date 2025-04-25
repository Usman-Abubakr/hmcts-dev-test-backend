package uk.gov.hmcts.reform.dev.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateTaskDTO {
    private String caseNumber;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
}
