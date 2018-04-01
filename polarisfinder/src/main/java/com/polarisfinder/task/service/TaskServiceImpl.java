package com.polarisfinder.task.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.polarisfinder.task.dao.TaskDAO;
import com.polarisfinder.task.entity.Task;
@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO TaskDAO;
	
	@Override
	public synchronized boolean createTask(Task Task){
		TaskDAO.createTask(Task);
    	return true;
	}

	@Override
	public List<Task> getTaskById(int id, int paging) {
		return TaskDAO.getTaskById(id, paging);
	}
	
	@Override
	public synchronized boolean deleteTask(Task Task){
		TaskDAO.deleteTask(Task);
    	return true;
	}

	@Override
	public List<Task> getTask(int id, int paging) {
		return TaskDAO.getTask(id, paging);
	}
	@Override
	public List<Task> getTasking(int id, int paging) {
		return TaskDAO.getTasking(id, paging);
	}
	@Override
	public List<Task> getTasker(int id, int paging) {
		return TaskDAO.getTasker(id, paging);
	}
}
