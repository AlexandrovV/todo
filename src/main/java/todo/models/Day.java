package todo.models;

import java.util.List;


public class Day {
	
	String date;
	User user;
	List<TodoLine> todos;
	
	public Day() {}
	public Day(String date, List<TodoLine> todos) {
		this.date = date;
		this.todos = todos;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public List<TodoLine> getTodos() {
		return todos;
	}
	public void setTodos(List<TodoLine> todos) {
		this.todos = todos;
	}
	@Override
	public String toString() {
		return "Day [date=" + date + ", todos=" + todos + "]";
	}
	
}
