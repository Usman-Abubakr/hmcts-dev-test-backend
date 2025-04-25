package uk.gov.hmcts.reform.dev.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.dev.dto.TaskResponseDTO;
import uk.gov.hmcts.reform.dev.exceptions.ResourceNotFoundException;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetTaskByIdReturnsTask() throws ResourceNotFoundException {
        Task task = new Task(1, "CASE123", "Test Task", "Desc", "todo", LocalDate.of(2025, 2, 5));
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        TaskResponseDTO result = taskService.getTaskById(1);

        assertEquals("CASE123", result.getCaseNumber());
        assertEquals("Test Task", result.getTitle());
        assertEquals("Desc", result.getDescription());
        assertEquals("todo", result.getStatus());
        assertEquals("2025-02-05", result.getDueDate().toString());

    }
}
