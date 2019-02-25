package com.ljx.tutor_platform.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ljx.tutor_platform.dao.TaskDao;
import com.ljx.tutor_platform.entity.Task;
import com.ljx.tutor_platform.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService{

	@Autowired
	private TaskDao taskDao;
	@Override
	public List<Task> getTaskByType(String taskType) {
		// TODO Auto-generated method stub
		return taskDao.getTaskByType(taskType);
	}
	@Override
	public List<Task> findTaskList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taskDao.findTaskList(map);
	}
	@Override
	public Long getTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return taskDao.getTotal(map);
	}

}
