package com.polarisfinder.task.dao;

import java.util.List;

import com.polarisfinder.task.entity.Task;

public interface TaskDAO {

    void createTask(Task Task);
	List<Task> getTaskById(int id, int paging);
	void deleteTask(Task Task);
	

	List<Task> getTask(int id, int paging);
	List<Task> getTasking(int id, int paging);
	List<Task> getTasker(int id, int paging);
	
	
}
