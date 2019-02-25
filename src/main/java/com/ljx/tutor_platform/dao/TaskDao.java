package com.ljx.tutor_platform.dao;

import java.util.List;
import java.util.Map;

import com.ljx.tutor_platform.entity.Task;

public interface TaskDao {

	List<Task> getTaskByType(String taskType);

	Long getTotal(Map<String, Object> map);

	List<Task> findTaskList(Map<String, Object> map);

}
