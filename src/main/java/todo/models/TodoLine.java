package todo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name = "todoline")
public class TodoLine {

	
	@Id
	@GeneratedValue
	int id;
	@Column(name = "user_id")
	int userId;
	String date;
	String todo;
	String till;
	boolean done;
	
	public TodoLine() {
		till="23:59";

	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTodo() {
		return todo;
	}
	public void setTodo(String todo) {
		this.todo = todo;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}

	public String getTill() {
		return till;
	}

	public void setTill(String till) {
		this.till = till;
	}

	@Override
	public String toString() {
		return "TodoLine [id=" + id + ", date=" + date + ", todo=" + todo + ", till=" + till + ", done=" + done + "]";
	}
	
	
}
