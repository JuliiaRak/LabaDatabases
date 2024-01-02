package solvd.laba.itcompany.service.impl;

import solvd.laba.itcompany.domain.Task;
import solvd.laba.itcompany.domain.exception.ServiceException;
import solvd.laba.itcompany.persistence.TaskRepository;
import solvd.laba.itcompany.persistence.impl.TaskJdbsRepository;
import solvd.laba.itcompany.service.TaskService;

public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    public TaskServiceImpl() {
        taskRepository = new TaskJdbsRepository();
    }

    @Override
    public void create(Task task) {
        try {
            taskRepository.create(task);
        } catch (Exception e) {
            throw new ServiceException("Failed to create task", e);
        }
    }
}
