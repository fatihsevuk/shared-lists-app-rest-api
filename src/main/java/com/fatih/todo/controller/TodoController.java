package com.fatih.todo.controller;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fatih.todo.entity.Comment;
import com.fatih.todo.entity.Listing;
import com.fatih.todo.entity.Todo;
import com.fatih.todo.entity.User;
import com.fatih.todo.model.BaseModel;
import com.fatih.todo.model.ListingDetailModel;
import com.fatih.todo.model.TodoDetailModel;
import com.fatih.todo.model.TodoSummaryModel;
import com.fatih.todo.service.CommentService;
import com.fatih.todo.service.ListingService;
import com.fatih.todo.service.TodoService;
import com.fatih.todo.service.UserService;
import com.fatih.todo.service.impl.TodoServiceImpl;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TodoController {

	private TodoService todoService;
	private UserService userService;
	private ListingService listingService;
	private CommentService commentService;

	@Autowired
	public TodoController(TodoServiceImpl todoService, UserService userService, CommentService commentService,ListingService listingService) {
		this.todoService = todoService;
		this.userService = userService;
		this.listingService = listingService;
		this.commentService=commentService;
	}

	@RequestMapping(value = "/todo", method = RequestMethod.PUT)
	public BaseModel add(@RequestBody TodoDetailModel todoDetailModel) {

		BaseModel baseModel = new BaseModel();

		try {

			Todo todo = todoService.createTodo();

			todo.setTodoContent(todoDetailModel.getTodoContent());

			User user = userService.findUserByUserName(todoDetailModel.getUserName());
			todo.setUser(user);

			// Listing listing =
			// listingService.findByListingName(todoDetailModel.getListingName());
			Listing listing = listingService.findByListingId(todoDetailModel.getListingId());
			todo.setListing(listing);

			todoService.saveTodo(todo);

			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());

		}
		return baseModel;
	}

	@RequestMapping(value = "/todos1/{listingName}", method = RequestMethod.GET)
	public TodoSummaryModel findByListingName(@PathVariable String listingName) {

		TodoSummaryModel todoSummaryModel = new TodoSummaryModel();

		try {

			todoSummaryModel.setTodoDetailList(new ArrayList<>());

			List<Todo> todos = todoService.findByListingName(listingName);

			for (Todo todo : todos) {

				TodoDetailModel todoDetailModel = new TodoDetailModel();

				todoDetailModel.setTodoId(todo.getTodoId());

				todoDetailModel.setTodoContent(todo.getTodoContent());

				if (todo.getUser() != null) {
					todoDetailModel.setUserName(todo.getUser().getUserName());
				}

				if (todo.getListing() != null) {
					todoDetailModel.setListingName(todo.getListing().getListingName());
				}

				todoDetailModel.setErrorCode(0);
				todoDetailModel.setErrorMessage("Oldu");

				todoSummaryModel.getTodoDetailList().add(todoDetailModel);
			}

			todoSummaryModel.setErrorCode(0);
			todoSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			e.printStackTrace();
			todoSummaryModel.setErrorCode(1);
			todoSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}
		return todoSummaryModel;
	}

	@RequestMapping(value = "/todos2/{userName}", method = RequestMethod.GET)
	public TodoSummaryModel findByUserName(@PathVariable String userName) {

		TodoSummaryModel todoSummaryModel = new TodoSummaryModel();
		todoSummaryModel.setTodoDetailList(new ArrayList<>());

		try {

			List<Todo> todos = todoService.findByUserName(userName);

			for (Todo todo : todos) {

				TodoDetailModel todoDetailModel = new TodoDetailModel();

				todoDetailModel.setTodoId(todo.getTodoId());

				todoDetailModel.setTodoContent(todo.getTodoContent());
				
				todoDetailModel.setRateAverage(todo.getRateAverage());
				
				

				if (todo.getUser() != null) {
					todoDetailModel.setUserName(todo.getUser().getUserName());
				}

				if (todo.getListing() != null) {
					todoDetailModel.setListingName(todo.getListing().getListingName());
					todoDetailModel.setListingId(todo.getListing().getListingId());
					todoDetailModel.setListingUserName(todo.getListing().getUser().getUserName());
				}

				todoDetailModel.setErrorCode(0);
				todoDetailModel.setErrorMessage("Oldu");

				todoSummaryModel.getTodoDetailList().add(todoDetailModel);
			}

			todoSummaryModel.setErrorCode(0);
			todoSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();

			todoSummaryModel.setErrorCode(1);
			todoSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}
		return todoSummaryModel;

	}

	@RequestMapping(value = "/todos3/{listingId}", method = RequestMethod.GET)
	public TodoSummaryModel findbyListingId(@PathVariable String listingId) {

		TodoSummaryModel todoSummaryModel = new TodoSummaryModel();

		try {

			todoSummaryModel.setTodoDetailList(new ArrayList<>());

			List<Todo> todos = todoService.findByListingId(listingId);

			for (Todo todo : todos) {

				TodoDetailModel todoDetailModel = new TodoDetailModel();

				todoDetailModel.setTodoId(todo.getTodoId());

				todoDetailModel.setTodoContent(todo.getTodoContent());
				todoDetailModel.setRateAverage(todo.getRateAverage());

				if (todo.getUser() != null) {
					todoDetailModel.setUserName(todo.getUser().getUserName());
				}

				if (todo.getListing() != null) {
					todoDetailModel.setListingName(todo.getListing().getListingName());
				}

				todoSummaryModel.getTodoDetailList().add(todoDetailModel);
			}

			todoSummaryModel.setErrorCode(0);
			todoSummaryModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			todoSummaryModel.setErrorCode(1);
			todoSummaryModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}
		return todoSummaryModel;

	}

	@RequestMapping(value = "/todo/{todoId}", method = RequestMethod.GET)
	public TodoDetailModel findByTodoId(@PathVariable String todoId) {

		TodoDetailModel todoDetailModel = new TodoDetailModel();

		try {

			Todo todo = todoService.findByTodoId(todoId);

			todoDetailModel.setTodoId(todo.getTodoId());

			todoDetailModel.setTodoContent(todo.getTodoContent());

			if (todo.getUser() != null) {
				todoDetailModel.setUserName(todo.getUser().getUserName());
			}

			if (todo.getListing() != null) {

				todoDetailModel.setListingName(todo.getListing().getListingName());

			}

			todoDetailModel.setErrorCode(0);
			todoDetailModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			todoDetailModel.setErrorCode(1);
			todoDetailModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return todoDetailModel;

	}

	@RequestMapping(value = "/deleteTodo/{todoId}", method = RequestMethod.DELETE)
	public BaseModel delete(@PathVariable String todoId) {

		BaseModel baseModel = new BaseModel();

		try {

			List<Comment> comments =commentService.findByTodoId(todoId);
			
			for (Comment comment : comments) {
				commentService.deleteComment(comment);
			}
			
			
			Todo todo = todoService.findByTodoId(todoId);
			todoService.deletTodo(todo);

			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;
	}

	@RequestMapping(value = "/rate/{todoId}", method = RequestMethod.POST)
	public BaseModel rate(@RequestBody TodoDetailModel todoDetailModel , @PathVariable String todoId) {
		BaseModel baseModel = new BaseModel();

	

		try {
			Todo todo = todoService.findByTodoId(todoId);

			if (todo != null){
				
				int count=todo.getRateCount()+1;
				int rate=todo.getRate()+todoDetailModel.getRate();
				double average=(double)rate/count;
				
				DecimalFormat newFormat=new DecimalFormat("#.##");
				DecimalFormatSymbols sfs = new DecimalFormatSymbols();
				sfs.setDecimalSeparator('.'); 
				newFormat.setDecimalFormatSymbols(sfs);
				
				String formattedValue=newFormat.format(average);
				double average2=Double.parseDouble(formattedValue);
				
				
				todo.setRate(rate);
				todo.setRateCount(count);
				todo.setRateAverage(average2);
				
				
				
				
				todoService.saveTodo(todo);
				baseModel.setErrorCode(0);
				baseModel.setErrorMessage("Oldu");

			} else{
				baseModel.setErrorCode(1);
				baseModel.setErrorMessage("Bu Id de todo yok");
			}

		} catch (Exception e) {
			e.printStackTrace();

			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());
		}

		return baseModel;
	}
	
	@RequestMapping(value = "deleteAll/{listingId}", method = RequestMethod.DELETE)
	public BaseModel deleteAllListTodo(@PathVariable String listingId){

		BaseModel baseModel = new BaseModel();

		try {
			
			List<Todo> todos = todoService.findByListingId(listingId);

			for (Todo todo : todos) {
				todoService.deletTodo(todo);
			}
			baseModel.setErrorCode(0);
			baseModel.setErrorMessage("Oldu");

		} catch (Exception e) {
			e.printStackTrace();
			baseModel.setErrorCode(1);
			baseModel.setErrorMessage("Yanlışlık " + e.getClass().getName() + " " + e.getMessage());

		}

		return baseModel;

	}

}
