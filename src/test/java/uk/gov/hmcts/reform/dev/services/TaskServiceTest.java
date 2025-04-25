package uk.gov.hmcts.reform.dev.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.hmcts.reform.dev.dto.CreateTaskDTO;
import uk.gov.hmcts.reform.dev.dto.TaskResponseDTO;
import uk.gov.hmcts.reform.dev.dto.UpdateTaskDTO;
import uk.gov.hmcts.reform.dev.exceptions.ResourceNotFoundException;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void givenId_whenGetTaskById_thenReturnTask () throws ResourceNotFoundException {
        Task task = new Task(1, "CASE123", "Test Task", "Desc", "todo", LocalDate.of(2025, 2, 5));
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));

        TaskResponseDTO result = taskService.getTaskById(1);

        assertEquals("CASE123", result.getCaseNumber());
        assertEquals("Test Task", result.getTitle());
        assertEquals("Desc", result.getDescription());
        assertEquals("todo", result.getStatus());
        assertEquals("2025-02-05", result.getDueDate().toString());
    }

    @Test
    public void givenTask_whenSaveTask_thenReturnSavedTask () {
        CreateTaskDTO dto = new CreateTaskDTO();
        dto.setCaseNumber("CASE123");
        dto.setTitle("New Task");
        dto.setDescription("Something to do");
        dto.setStatus("todo");
        dto.setDueDate(LocalDate.now());

        Task savedTask = new Task(1, dto.getCaseNumber(), dto.getTitle(), dto.getDescription(), dto.getStatus(), dto.getDueDate());
        when(taskRepository.save(any(Task.class))).thenReturn(savedTask);

        TaskResponseDTO result = taskService.saveTask(dto);

        assertEquals("New Task", result.getTitle());
        assertEquals("todo", result.getStatus());
    }

    @Test
    public void givenNewTitle_whenTaskExist_thenUpdateTitle() throws ResourceNotFoundException {
        int taskId = 1;
        Task existingTask = new Task(
            taskId,
            "CASE123",
            "Old Title",
            "Description",
            "todo",
            LocalDate.now());

        UpdateTaskDTO updateDto = new UpdateTaskDTO(
            taskId,
            "New Title",
            "Description",
            "todo",
            LocalDate.now()
        );

        when(taskRepository.findById(taskId)).thenReturn(Optional.of(existingTask));
        when(taskRepository.save(any(Task.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TaskResponseDTO result = taskService.updateTask(updateDto);

        assertEquals("New Title", result.getTitle());
        assertEquals(taskId, result.getId());

        verify(taskRepository).save(existingTask);
    }

    @Test
    public void givenTask_whenTaskNotExist_thenThrow () {
        UpdateTaskDTO dto = new UpdateTaskDTO();
        dto.setId(99);
        when(taskRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> taskService.updateTask(dto));
    }

    @Test
    void givenId_whenTaskExist_thenDeleteTask() throws ResourceNotFoundException {
        int taskId = 1;
        when(taskRepository.existsById(taskId)).thenReturn(true);

        taskService.deleteTaskById(taskId);

        verify(taskRepository).deleteById(taskId);
    }

    @Test
    void givenId_whenTaskNotExist_thenThrow() {
        int taskId = 2;
        when(taskRepository.existsById(taskId)).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> taskService.deleteTaskById(taskId));
        verify(taskRepository, never()).deleteById(any());
    }
}
