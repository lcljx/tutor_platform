package com.ljx.tutor_platform.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ljx.tutor_platform.entity.PageBean;
import com.ljx.tutor_platform.entity.Task;
import com.ljx.tutor_platform.service.TaskService;

@RestController
@RequestMapping(value="/task")//任务相关处理
@EnableAutoConfiguration
public class TaskController {

	@Autowired
	private TaskService tService;
	
	/**
	 *具体课程页 如小学课程页显示
	 * */
	@RequestMapping(value="showTaskByType")
	public List<Task> showTaskByType(String taskType){
		List<Task> tasks = new ArrayList<Task>();
		tasks = tService.getTaskByType(taskType);
		return tasks;
	}
	
	/**
	 *显示到后台管理页
	 * */
	@RequestMapping(value="taskList")
	public Map<String, Object> TaskList(@RequestParam(value="page",required=false)String page,
			@RequestParam(value="rows",required=false)String rows,
			Task task) throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("taskName", task.getTaskName());
		map.put("ranks", task.getRanks());
		map.put("start", pageBean.getStart());
		map.put("size", pageBean.getPageSize());
		List<Task> typetList =tService.findTaskList(map);
		Long total=tService.getTotal(map);
		Map<String,Object> m = new HashMap<>();
		m.put("rows", typetList);
		m.put("total", total);
		return m;
	}
}
