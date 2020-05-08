package com.fatih.sharedlist.model;

import java.util.List;

public class TodoSummaryModel extends BaseModel{
	
	private List<TodoDetailModel> todoDetailList;
	
	
	
	public List<TodoDetailModel> getTodoDetailList() {
		return todoDetailList;
	}
	
	public void setTodoDetailList(List<TodoDetailModel> todoDetailList) {
		this.todoDetailList = todoDetailList;
	}
}
