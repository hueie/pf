package com.polarisfinder.task.service;

import java.util.List;

import com.polarisfinder.task.entity.Task;

public interface TaskService {
	boolean createTask(Task Task);
	List<Task> getTaskById(int id, int paging);
	boolean deleteTask(Task Task);
	List<Task> getTask(int id, int paging);
	
}
