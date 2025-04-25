package uk.gov.hmcts.reform.dev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.gov.hmcts.reform.dev.dto.CreateTaskDTO;
import uk.gov.hmcts.reform.dev.dto.TaskResponseDTO;
import uk.gov.hmcts.reform.dev.dto.UpdateTaskDTO;
import uk.gov.hmcts.reform.dev.exceptions.ResourceNotFoundException;
import uk.gov.hmcts.reform.dev.models.Task;
import uk.gov.hmcts.reform.dev.repositories.TaskRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<TaskResponseDTO> getAllTasks() {
        return taskRepository.findAll().stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    public TaskResponseDTO getTaskById(Integer id) throws ResourceNotFoundException {
        return taskRepository.findById(id)
            .map(this::toResponseDTO)
            .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + id + " not found."));
    }

    public TaskResponseDTO saveTask(CreateTaskDTO dto) {
        Task task = fromCreateDTO(dto);
        Task savedTask = taskRepository.save(task);
        return toResponseDTO(savedTask);
    }

    public TaskResponseDTO updateTask(UpdateTaskDTO dto) throws ResourceNotFoundException {
        Task task = taskRepository.findById(dto.getId())
            .orElseThrow(() -> new ResourceNotFoundException("Task with ID " + dto.getId() + " not found."));

        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());

        return toResponseDTO(taskRepository.save(task));
    }

    // --- Mapping Methods ---
    private Task fromCreateDTO(CreateTaskDTO dto) {
        Task task = new Task();
        task.setCaseNumber(dto.getCaseNumber());
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    private TaskResponseDTO toResponseDTO(Task task) {
        TaskResponseDTO dto = new TaskResponseDTO();
        dto.setId(task.getId());
        dto.setCaseNumber(task.getCaseNumber());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setDueDate(task.getDueDate());
        return dto;
    }
}
