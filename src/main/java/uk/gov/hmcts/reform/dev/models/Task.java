package uk.gov.hmcts.reform.dev.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = true)
    private String caseNumber;

    @Column(nullable = true)
    private String title;

    @Column(nullable = true)
    private String description;

    // @Column(nullable = false, columnDefinition = "VARCHAR DEFAULT 'todo'")
    @Column(nullable = true)
    private String status;

    @Column(nullable = true)
    private LocalDate dueDate;
}
