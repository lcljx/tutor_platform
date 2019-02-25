package com.ljx.tutor_platform.service;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Task;

public interface TaskService {

	List<Task> getTaskByType(String taskType);

	List<Task> findTaskList(Map<String, Object> map);

	Long getTotal(Map<String, Object> map);

}
