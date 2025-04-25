package uk.gov.hmcts.reform.dev.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uk.gov.hmcts.reform.dev.dto.CreateTaskDTO;
import uk.gov.hmcts.reform.dev.dto.TaskResponseDTO;
import uk.gov.hmcts.reform.dev.dto.UpdateTaskDTO;
import uk.gov.hmcts.reform.dev.exceptions.ResourceNotFoundException;
import uk.gov.hmcts.reform.dev.services.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable int id) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponseDTO> saveTask(@RequestBody CreateTaskDTO createTaskDTO) {
        return ResponseEntity.ok(taskService.saveTask(createTaskDTO));
    }

    @PutMapping
    public ResponseEntity<TaskResponseDTO> updateTask(@RequestBody UpdateTaskDTO updateTaskDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(taskService.updateTask(updateTaskDTO));
    }
}
